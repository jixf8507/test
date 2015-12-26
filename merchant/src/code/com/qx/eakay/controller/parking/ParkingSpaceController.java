package com.qx.eakay.controller.parking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.base.PageHelp;
import com.qx.common.base.PageResults;
import com.qx.common.tools.JSONTools;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.parking.ParkingSpacePO;
import com.qx.eakay.service.parking.ParkingSpaceService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Controller
@RequestMapping("/parking/space")
public class ParkingSpaceController extends BaseController {

	@Autowired
	private ParkingSpaceService parkingSpaceService;

	/**
	 * 进入停车位列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("parking/space");
	}

	/**
	 * 进入停车位管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manager.htm")
	public ModelAndView manager() throws Exception {
		return new ModelAndView("parking/spaceManager");
	}

	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("parking/spaceAdd");
	}

	/**
	 * 保存新增停车位
	 * 
	 * @param spacePO
	 * @param paraData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(ParkingSpacePO spacePO, String paraData,
			HttpServletRequest request) {
		paraData = StringTools.decodeMethod(paraData);
		JSONObject jsonObject = JSONObject.fromObject(paraData);

		String position = request.getParameter("position");
		if ("0".equals(spacePO.getDeviceTypeNo())) {
			position = request.getParameter("position1");
		}
		spacePO.setPosition(position);
		return parkingSpaceService.createSpace(spacePO, jsonObject);
	}

	/**
	 * 进入停车位信息编辑页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		ParkingSpacePO spacePO = parkingSpaceService.getSpace(id);
		if("0".equals(spacePO.getDeviceTypeNo())){
			spacePO.setPosition1(spacePO.getPosition());
			spacePO.setPosition("");
		} 
		return new ModelAndView("parking/spaceEdite", "spacePO", spacePO);
	}

	/**
	 * 保存停车位修改
	 * 
	 * @param spacePO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(ParkingSpacePO spacePO, HttpServletRequest request,
			String paraData) throws Exception {
		paraData = StringTools.decodeMethod(paraData);
		JSONObject jsonObject = JSONObject.fromObject(paraData);

		String position = request.getParameter("position");
		if ("0".equals(spacePO.getDeviceTypeNo())) {
			position = request.getParameter("position1");
		}
		spacePO.setPosition(position);

		return parkingSpaceService.updateSpace(spacePO, jsonObject);
	}

	/**
	 * 检查停车位唯一
	 * 
	 * @return
	 */
	@RequestMapping(value = "checkOnly.htm")
	@ResponseBody
	public MSG checkOnly(String paraData, HttpSession session) {
		MSG msg = new MSG();
		JSONObject jsonObject = getJsonPara(paraData, session);

		msg.setSuccess(parkingSpaceService.checkOnly(jsonObject));
		return msg;
	}

	/**
	 * 删除停车位
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(parkingSpaceService.deleteSpace(id));
		return msg;
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = parkingSpaceService.findSpacePage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 得到请求参数条件
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	private JSONObject getJsonPara(String paraData, HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
		return jsonObject;
	}

	/**
	 * 导出下载停车位到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportToExcel.htm")
	public ModelAndView exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		parkingSpaceService.excelExportSpace(response, jsonObject);
		return null;
	}

}

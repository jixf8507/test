package com.qx.eakay.controller.car;

import java.util.List;
import java.util.Map;

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
import com.qx.eakay.model.car.CarManufacturerPO;
import com.qx.eakay.service.car.CarManufacturerService;
import com.qx.eakay.service.car.CarTypeService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年6月30日
 */
@Controller
@RequestMapping("/car/manufacturer")
public class CarManufacturerController extends BaseController {

	@Autowired
	private CarManufacturerService manufacturerService;

	@Autowired
	private CarTypeService carTypeService;

	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("car/carManufacturer");
	}

	@RequestMapping(value = "ajaxManufacturer.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxManufacturer(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return manufacturerService.findManufacturers(jsonObject);
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = manufacturerService.findManufacturerPage(
				jsonObject, pageHelp.getiDisplayLength(),
				pageHelp.getiDisplayStart());
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
		return jsonObject;
	}

	/**
	 * 导出下载车辆供应商到Excel表格
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
		manufacturerService.excelExportManufacturer(response, jsonObject);
		return null;
	}

	/**
	 * 进入车辆供应商添加界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("car/manufacturerAdd");
	}

	/**
	 * 保存新增车辆
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	public ModelAndView saveAdd(CarManufacturerPO manufacturerPO,
			HttpSession session) throws Exception {
		manufacturerPO.setMerchantId(this.getSessionUser(session)
				.getMerchantId());
		manufacturerService.saveManfacturer(manufacturerPO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}

	/**
	 * 进入车辆供应商编辑界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		CarManufacturerPO manufacturerPO = manufacturerService
				.getManufacturerPO(id);
		return new ModelAndView("car/manufacturerEdite", "manufacturerPO",
				manufacturerPO);
	}

	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(CarManufacturerPO manufacturerPO) throws Exception {
		MSG msg = new MSG();
		boolean bool = manufacturerService.updateManufacturer(manufacturerPO);
		msg.setSuccess(bool);
		return msg;
	}

	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(Integer id) throws Exception {
		MSG msg = new MSG();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("carManufacturerId", id);
		List<Map<String, Object>> types = carTypeService
				.findCarTypes(jsonObject);
		if (types.size() > 0) {
			msg.setSuccess(false);
			msg.setInfo("请先删除该供应商下的车辆型号！");
			return msg;
		}
		boolean bool = manufacturerService.deleteManfacturer(id);
		if (!bool) {
			msg.setInfo("系统异常！");
		}
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 进入车辆商家添加界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkName.htm")
	@ResponseBody
	public MSG checkName(String manufacturerName, Integer id,
			HttpSession session) throws Exception {
		MSG msg = new MSG();
		boolean bool = manufacturerService.checkManfacturerName(
				manufacturerName, this.getSessionUser(session).getMerchantId(),
				id);
		msg.setSuccess(bool);
		return msg;
	}

}

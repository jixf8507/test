package com.qx.eakay.controller.stake;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.qx.eakay.model.stake.StakeSiteAreaPO;
import com.qx.eakay.service.stake.SiteAreaService;
import com.qx.eakay.util.MSG;

@Controller
@RequestMapping("/stake/area")
public class SiteAreaController extends BaseController {

	@Autowired
	private SiteAreaService areaService;

	/**
	 * 进入充电设备页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("stake/siteArea");
	}

	/**
	 * 进入充电设备新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("stake/siteAreaAdd");
	}
	
	/**
	 * 保存充电设备新增
	 * 
	 * @param areaPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(StakeSiteAreaPO areaPO) throws Exception {
		MSG msg =new MSG();
		msg.setSuccess(areaService.createArea(areaPO));
		return msg;
	}

	/**
	 * 进入充电设备编辑页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		StakeSiteAreaPO areaPO = areaService.getArea(id);
		return new ModelAndView("stake/siteAreaEdite", "areaPO", areaPO);
	}
    
	/**
	 * 保存充电设备修改
	 * 
	 * @param batteryPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(StakeSiteAreaPO areaPO) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(areaService.updateArea(areaPO));
		return msg;
	}

	/**
	 * 删除充电设备
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(areaService.deleteArea(id));
		return msg;
	}

	/**
	 * 检测充电设备编号是否已存在
	 * 
	 * @param checkFactoryNo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkCode.htm")
	@ResponseBody
	public MSG checkCode(String areaCode, String siteCode, Integer id)
			throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(areaService.checkCode(areaCode, siteCode, id));
		return msg;
	}

	/**
	 * 分页查询充电设备信息
	 * 
	 * @param aoData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = areaService.findAreaPage(jsonObject,
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
	 * 导出下载充电设备到Excel表格
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
		areaService.excelExportAreas(response, jsonObject);
		return null;
	}

	@RequestMapping("ajaxAreaCode.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxAreaCode(String paraData,
			HttpSession session) throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 1; i < 100; i++) {
			String value = i + "";
			if (i < 10) {
				value = "0" + i;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("code", value);
			list.add(map);
		}
		return list;
	}
}

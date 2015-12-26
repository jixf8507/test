package com.qx.eakay.controller.car;

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
import com.qx.eakay.model.car.CarBatteryPO;
import com.qx.eakay.service.car.BatteryService;
import com.qx.eakay.util.MSG;

@Controller
@RequestMapping("/car/battery")
public class BatteryController extends BaseController {

	@Autowired
	private BatteryService batteryService;

	/**
	 * 进入电池信息管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("car/battery");
	}

	/**
	 * 进入新增电池信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("car/batteryAdd");
	}

	/**
	 * 保存电池信息
	 * 
	 * @param batteryPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	public ModelAndView saveAdd(CarBatteryPO batteryPO, HttpSession session)
			throws Exception {
		batteryPO.setMerchantId(this.getSessionUser(session).getMerchantId());
		batteryService.createBattery(batteryPO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}

	/**
	 * 进入电池信息编辑页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		CarBatteryPO batteryPO = batteryService.getBattery(id);
		return new ModelAndView("car/batteryEdite", "batteryPO", batteryPO);
	}

	/**
	 * 保存电池修改
	 * 
	 * @param batteryPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(CarBatteryPO batteryPO) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(batteryService.updateBattery(batteryPO));
		return msg;
	}

	/**
	 * 删除电池
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(batteryService.deleteBattery(id));
		return msg;
	}

	/**
	 * 检测电池VIN码是否已存在
	 * 
	 * @param vin
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkVin.htm")
	@ResponseBody
	public MSG checkVin(String vin, Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(batteryService.checkVin(vin, id));
		return msg;
	}

	/**
	 * 分页查询车辆状态信息
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

		PageResults pageResults = batteryService.findBatteryPage(jsonObject,
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

		return jsonObject;
	}

	/**
	 * 导出下载车辆信息到Excel表格
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
		batteryService.excelExportBatterys(response, jsonObject);
		return null;
	}

}

package com.qx.eakay.controller.stake;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.highcharts.ChartsBO;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.service.stake.StakeChargeService;

/**
 * 充电统计
 *  
 */
@Controller
@RequestMapping("stake/charge")
public class StakeChargeController extends BaseController {

	@Autowired
	private StakeChargeService stakeChargeService;


	/**
	 * 进入充电时间段统计页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("timeCharts.htm")
	public ModelAndView timeCharts() throws Exception {
		return new ModelAndView("stake/chargeTimeCharts");
	}

	/**
	 * 动态加载充电时间段统计图
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxChangeTimeCharts.htm")
	@ResponseBody
	public ChartsBO<Integer> ajaxChangeTimeCharts(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return stakeChargeService.getChargeTimeSeries(jsonObject);
	}
	
	/**
	 * 进入充电方式统计页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("wayCharts.htm")
	public ModelAndView wayCharts() throws Exception {
		return new ModelAndView("stake/chargeWayCharts");
	}

	/**
	 * 动态加载充电方式统计图
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxChangeWayCharts.htm")
	@ResponseBody
	public ChartsBO<Integer> ajaxChangeWayCharts(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return stakeChargeService.getChargeWaySeries(jsonObject);
	}
}

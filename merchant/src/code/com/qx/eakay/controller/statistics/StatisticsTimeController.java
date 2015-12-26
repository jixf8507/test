package com.qx.eakay.controller.statistics;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.highcharts.ChartsBO;
import com.qx.common.highcharts.PointData;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.service.statistics.StatisticsTimeService;

/**
 * 租赁次数分时段统计
 * 
 * @author sdf
 * @date 2015年8月21日
 */
@Controller
@RequestMapping("/statistics/time")
public class StatisticsTimeController extends BaseController {

	@Autowired
	private StatisticsTimeService timeService;

	/**
	 * 进入次数分时段统计页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("statistics/time");
	}

	/**
	 * 动态加载租赁次数分时段统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSiteTimeCountPie.htm")
	@ResponseBody
	public ChartsBO<PointData> ajaxSiteCount(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return timeService.statisticsSiteCountPie(jsonObject);
	}
	

	/**
	 * 动态加载租赁点的收费统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSiteTimeCountColumn.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxSiteCost(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return timeService.statisticsSiteCostColumn(jsonObject);
	}
}

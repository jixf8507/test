package com.qx.eakay.controller.statistics;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.highcharts.ChartsBO;
import com.qx.common.highcharts.PointData;
import com.qx.common.tools.MyDateUtil;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.service.statistics.StatisticsCostService;

/**
 * 租赁收费统计
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Controller
@RequestMapping("/statistics/cost")
public class StatisticsCostController extends BaseController {

	@Autowired
	private StatisticsCostService costService;

	/**
	 * 进入租赁收费统计页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("statistics/cost");
	}

	/**
	 * 动态加载每日的租赁收益
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxDayCost.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxDayCost(String paraData, HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (jsonObject.get("beginTime").equals("")) {
			Date date = MyDateUtil.getDaytoCurrDay(new Date(), -90);
			jsonObject.put("beginTime", MyDateUtil.getDayString(date));
		}
		if (jsonObject.get("endTime").equals("")) {
			jsonObject.put("endTime", MyDateUtil.getCurDate()+" 23:59:59");   //结束日期采用时分秒格式
			
		}
		
		return costService.statisticsDayCost(jsonObject);
	}

	/**
	 * 动态加载租赁点的收费统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSiteCost.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxSiteCost(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return costService.statisticsSiteCost(jsonObject);
	}

	/**
	 * 动态加载商家每月的收费统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxMonthCost.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxMonthCost(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return costService.statisticsMonthCost(jsonObject);
	}

	/**
	 * 动态加载租赁点的收费统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSiteCount.htm")
	@ResponseBody
	public ChartsBO<PointData> ajaxSiteCount(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return costService.statisticsSiteCount(jsonObject);
	}

}

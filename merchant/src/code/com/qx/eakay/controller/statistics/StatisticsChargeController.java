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
import com.qx.eakay.service.statistics.StatisticsChargeService;

/**
 * 充电收费统计
 * 
 * @author sdf
 * @date 2015年9月30日
 */
@Controller
@RequestMapping("/statistics/stake")
public class StatisticsChargeController extends BaseController {

	@Autowired
	private StatisticsChargeService chargeService;

	/**
	 * 进入充电收费统计页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chargePay.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("statistics/charge");
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
	 * 动态加载商家每月充电收费统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxMonthCost.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxMonthCost(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		return chargeService.statisticsMonthCost(jsonObject);
	}
	
	/**
	 * 动态加载商家每日充电收费统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxDayCost.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxDayCost(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		if (jsonObject.get("beginTime").equals("")) {
			Date date = MyDateUtil.getDaytoCurrDay(new Date(), -90);
			jsonObject.put("beginTime", MyDateUtil.getDayString(date));
		}
		if (jsonObject.get("endTime").equals("")) {
			jsonObject.put("endTime", MyDateUtil.getCurDate());
		}
		return chargeService.statisticsDayCost(jsonObject);
	}
	
	/**
	 * 动态加载商家租赁点充电收费统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSiteCost.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxSiteCost(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		return chargeService.statisticsSiteCost(jsonObject);
	}
	
	/**
	 * 动态加载商家租赁点充电次数统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSiteCount.htm")
	@ResponseBody
	public ChartsBO<PointData> ajaxSiteCount(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		return chargeService.statisticsSiteCount(jsonObject);
	}
	
	
	
	
	
	/**
	 * 进入充电电量统计页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chargeElectric.htm")
	public ModelAndView chargeElectric() throws Exception {
		return new ModelAndView("statistics/chargeElectric");
	}
	
	
	/**
	 * 动态加载商家租赁点充电电量统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSiteElec.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxSiteElec(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		return chargeService.statisticsSiteElec(jsonObject);
	}
	
	/**
	 * 动态加载商家租赁点不同充电方式充电电量统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxKindElec.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxStakeElec(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		return chargeService.statisticsKindElec(jsonObject);
	}
	
	/**
	 * 动态加载商家每月充电电量统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxMonthElec.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxMonthElec(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		return chargeService.ajaxMonthElec(jsonObject);
	}
	
	/**
	 * 动态加载商家每日充电电量统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxDayElec.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxDayElec(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		if (jsonObject.get("beginTime").equals("")) {
			Date date = MyDateUtil.getDaytoCurrDay(new Date(), -90);
			jsonObject.put("beginTime", MyDateUtil.getDayString(date));
		}
		if (jsonObject.get("endTime").equals("")) {
			jsonObject.put("endTime", MyDateUtil.getCurDate());
		}
		return chargeService.ajaxDayElec(jsonObject);
	}
}

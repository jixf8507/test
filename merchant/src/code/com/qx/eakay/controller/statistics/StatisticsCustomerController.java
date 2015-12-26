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
import com.qx.eakay.service.statistics.StatisticsCustomerService;

@Controller
@RequestMapping("/statistics/customer")
public class StatisticsCustomerController extends BaseController {

	@Autowired
	private StatisticsCustomerService customerService;

	/**
	 * 进入租赁次数统计页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("statistics/customer");
	}

	/**
	 * 动态加载租赁点的收费统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxCustomerCount.htm")
	@ResponseBody
	public ChartsBO<PointData> ajaxSiteCount(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return customerService.statisticsSiteCount(jsonObject);
	}
	
	/**
	 * 动态加载注销卡统计
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxCustomerCountByCancle.htm")
	@ResponseBody
	public ChartsBO<PointData> ajaxCustomerCountByCancle(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return customerService.statisticsSiteCountCancle(jsonObject);
	}
	
	

	/**
	 * 动态加载每日的租赁收益
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxDayCount.htm")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxDayCount(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (jsonObject.get("beginTime") == null) {
			Date date = MyDateUtil.getDaytoCurrDay(new Date(), -90);
			jsonObject.put("beginTime", MyDateUtil.getDayString(date));
		}
		if (jsonObject.get("endTime") == null) {
			jsonObject.put("endTime", MyDateUtil.getCurDate());
		}
		return customerService.statisticsDayCount(jsonObject);
	}

}

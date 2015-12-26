package com.qx.eakay.controller.customer;

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
import com.qx.eakay.service.customer.CustomerManagerService;

/**
 * 客户管理
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/customer/manager")
public class CustomerManagerController extends BaseController {

	@Autowired
	private CustomerManagerService managerService;

	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("customer/manager");
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
	 * 跳转到用户年龄统计页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cusCountPage.htm")
	public ModelAndView customerCountPage() throws Exception {
		return new ModelAndView("customer/age");
	}
	
	
	
	/**
	 * 加载用户各个年龄段统计的柱状图
	 * 
	 */
	@RequestMapping(value="ajaxCustomerCountColumn")
	@ResponseBody
	public ChartsBO<BigDecimal> ajaxCustomerCountColumn(String paraData, HttpSession session){
		JSONObject jsonObject=this.getJsonPara(paraData, session);
		return managerService.returnCountCustomerColumn(jsonObject);
	}
	
	
	/**
	 * 加载用户各个年龄段统计的柱状图
	 * 
	 */
	@RequestMapping(value="ajaxCustomerCountPie")
	@ResponseBody
	public ChartsBO<PointData> ajaxCustomerCountPie(String paraData, HttpSession session){
		JSONObject jsonObject=this.getJsonPara(paraData, session);
		return managerService.returnCountCustomerPie(jsonObject);
	}

}

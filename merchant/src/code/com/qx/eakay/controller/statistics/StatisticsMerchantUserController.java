package com.qx.eakay.controller.statistics;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.service.merchant.MerchantUserService;

/**
 * 商家工作人员每日工作业务统计
 * 
 * @author jixf
 * @date 2015年10月13日
 */
@Controller
@RequestMapping("/statistics/merchantUser")
public class StatisticsMerchantUserController extends BaseController {

	@Autowired
	private MerchantUserService merchantUserService;

	/**
	 * 进入每日工作统计页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("statistics/merchantUserPage");
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public Map<String, Object> ajaxData(String paraData, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject jsonObject = getJsonPara(paraData, session);
		resultMap.put("getCarList",
				merchantUserService.findGetCarList(jsonObject));
		resultMap.put("returnCarList",
				merchantUserService.findReturnCarList(jsonObject));
		resultMap.put("createCustomerList",
				merchantUserService.findCreateCustomerList(jsonObject));
		return resultMap;
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
	 * 导出下载充电收费信息到Excel表格
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
		merchantUserService.excelExportDay(response, jsonObject);
		return null;
	}

}

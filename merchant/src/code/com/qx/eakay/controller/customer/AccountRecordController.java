package com.qx.eakay.controller.customer;

import java.math.BigDecimal;
import java.util.Date;

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
import com.qx.common.highcharts.ChartsBO;
import com.qx.common.tools.JSONTools;
import com.qx.common.tools.MyDateUtil;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.service.customer.AccountRecordService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月10日
 */
@Controller
@RequestMapping("/customer/accountRecord")
public class AccountRecordController extends BaseController {

	@Autowired
	private AccountRecordService accountRecordService;

	/**
	 * 进入用户账户明细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("customer/accountRecord");
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = accountRecordService.findAccountRecordPage(
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
	 * 导出下载客户信息到Excel表格
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
		accountRecordService.excelExportAccountRecord(response, jsonObject);
		return null;
	}

	/**
	 * 核对充值收费页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkFee.htm")
	public ModelAndView checkFee(Integer id) throws Exception {
		AccountRecordPO accountRecordPO = accountRecordService.getAccountRecord(id);
		return new ModelAndView("customer/checkFee", "accountRecordPO", accountRecordPO);
	}
	
	/**
	 * 保存核对充值收费信息
	 * 
	 * @param orderPO
	 * @return
	 */
	@RequestMapping(value = "saveCheckFee.htm")
	@ResponseBody
	public MSG saveCheckFee(AccountRecordPO accountRecordPO, HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		accountRecordPO.setCheckUser(sessionUser.getName());
		msg.setSuccess(accountRecordService.checkForCharge(accountRecordPO));
		return msg;
	}
	/**
	 * 动态加载每日开户，注销人数
	 */
	@RequestMapping(value="countCustomerDay")
	@ResponseBody
	public ChartsBO<BigDecimal> countCustomerDay(String paraData,HttpSession session){
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (jsonObject.get("beginTime") == null || "".equals(jsonObject.get("beginTime")+"")) {
			Date date = MyDateUtil.getDaytoCurrDay(new Date(), -90);
			jsonObject.put("beginTime", MyDateUtil.getDayString(date));
		}
		if (jsonObject.get("endTime") == null ||  "".equals(jsonObject.get("endTime")+"")) {
			jsonObject.put("endTime", MyDateUtil.getCurDate()+" 23:59:59");
		}
		return accountRecordService.countCustomerDay(jsonObject);
	}
	
}

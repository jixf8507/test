package com.qx.eakay.controller.merchant;

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
import com.qx.eakay.service.merchant.RefundRecordService;

/**
 * 
 * @author jixf
 * @date 2015年7月11日
 */
@Controller
@RequestMapping("/merchant/refundRecord")
public class MerchantRefundRecordController extends BaseController {

	@Autowired
	private RefundRecordService refundRecordService;

	/**
	 * 进入商家退款记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("merchant/refundRecord");
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = refundRecordService.findRefudnRecordPage(
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
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
		return jsonObject;
	}

	/**
	 * 导出下载商家账户交易明细到Excel表格
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
		refundRecordService.excelExportRefundRecord(response, jsonObject);
		return null;
	}
	
	/**
	 * 导出下载单位会员商家账户交易明细到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportUnitToExcel.htm")
	public ModelAndView exportUnitToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		refundRecordService.exportUnitToExcel(response, jsonObject);
		return null;
	}

}

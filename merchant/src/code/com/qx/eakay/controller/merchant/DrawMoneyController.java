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
import com.qx.eakay.model.merchant.MerchantDrawMoneyPO;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.service.merchant.DrawMoneyService;
import com.qx.eakay.service.merchant.MerchantService;
import com.qx.eakay.util.MSG;

@Controller
@RequestMapping("/merchant/drawMoney")
public class DrawMoneyController extends BaseController {

	@Autowired
	private DrawMoneyService drawMoneyService;
	@Autowired
	private MerchantService merchantService;

	/**
	 * 进入提款列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page(HttpSession session) throws Exception {
		MerchantPO merchantPO = merchantService.getMerchant(this
				.getSessionUser(session).getMerchantId());
		return new ModelAndView("merchant/drawMoney", "merchantPO", merchantPO);
	}
	
	
	@RequestMapping("saveAdd.htm")
	public ModelAndView saveAdd(MerchantDrawMoneyPO drawMoneyPO,
			HttpSession session) throws Exception {
		drawMoneyPO.setMerchantId(this.getSessionUser(session).getMerchantId());
		drawMoneyService.createDrawMoney(drawMoneyPO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}
	
	/**
	 * 通过申请退款按钮的方式提交提款申请
	 */
	@RequestMapping("saveAdd2.htm")
	@ResponseBody
	public MSG saveAdd2(MerchantDrawMoneyPO drawMoneyPO,
			HttpSession session) throws Exception {
		MSG msg =new MSG();
		drawMoneyPO.setMerchantId(this.getSessionUser(session).getMerchantId());
		msg.setSuccess(drawMoneyService.createDrawMoney(drawMoneyPO));
		return msg;
	}
	
	//通过按钮跳转到申请提款页面
	@RequestMapping("applyMoney.htm")
	public ModelAndView applyMoney(HttpSession session) throws Exception {
		MerchantPO merchantPO = merchantService.getMerchant(this
				.getSessionUser(session).getMerchantId());
		return new ModelAndView("merchant/drawMoneyApply", "merchantPO", merchantPO);
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = drawMoneyService.findDrawMoneysPage(
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
	 * 导出下载商家提款记录到Excel表格
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
		drawMoneyService.excelExportDrawMoneys(response, jsonObject);
		return null;
	}

}

package com.qx.eakay.controller.stake;

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
import com.qx.eakay.model.stake.StakeRechargeCostPO;
import com.qx.eakay.service.stake.StakeRechargeCostService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Controller
@RequestMapping("/stake/rechargeCost")
public class StakeRechargeCostController extends BaseController {

	@Autowired
	private StakeRechargeCostService rechargeCostService;

	/**
	 * 进入充电订单页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("stake/rechargeCost");
	}

	/**
	 * 分页查询充电订单
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = rechargeCostService.findRechargeCostPage(
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
		rechargeCostService.excelExportRechargeCost(response, jsonObject);
		return null;
	}
	
	/**
	 * 进入票据打印页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("print.htm")
	public ModelAndView costPrint(Integer id) throws Exception {
		StakeRechargeCostPO costPO = rechargeCostService.getStakeRechargeCost(id);
		return new ModelAndView("stake/print","costPO",costPO);
	}
	
	/**
	 * 进入结算充电订单（刷卡充电方式）页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("charge.htm")
	public ModelAndView charge(Integer id) throws Exception {
		StakeRechargeCostPO costPO = rechargeCostService.getStakeRechargeCost(id);
		return new ModelAndView("stake/charge","costPO",costPO);
	}
	
	/**
	 * 结算充电订单（刷卡充电方式）
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chargeOrder.htm")
	@ResponseBody
	public MSG chargeOrder(Integer id,HttpSession session) throws Exception {
		String transactUser = this.getSessionUser(session).getName();
		return rechargeCostService.chargeOrder(id,transactUser);
	}
}

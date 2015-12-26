package com.qx.eakay.controller.order;

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
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.service.order.OrderService;
import com.qx.eakay.util.MSG;

@Controller
@RequestMapping("/order/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	/**
	 * 订单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("order/order");
	}

	/**
	 * 取消订单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "cancel.htm")
	@ResponseBody
	public MSG cancel(Integer id,Integer orderType) {
		MSG msg = new MSG();
		msg.setSuccess(orderService.cancel(id,orderType));
		return msg;
	}

	/**
	 * 查看订单详情
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail.htm")
	public ModelAndView detail(Integer id) throws Exception {
		OrderPO orderPO = orderService.getOrder(id);
		return new ModelAndView("order/orderDetail", "orderPO", orderPO);
	}

	/**
	 * 分页查询订单信息
	 * 
	 * @param aoData
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

		PageResults pageResults = orderService.findOrderPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	/**
	 * 分页查询订单信息（只查询表格显示内容）
	 * 
	 * @param aoData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxTableData.htm")
	@ResponseBody
	public PageResults ajaxTableData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);
		
		PageResults pageResults = orderService.findTableOrderPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
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
			String orderStatus = jsonObject.get("orderStatus") + "";
			String returnConfirm = jsonObject.get("returnConfirm") + "";
			String type = jsonObject.get("type") + "";
			if ("已预约".equals(orderStatus) || "已取消".equals(orderStatus)) {
				jsonObject.put("relityGetSiteId", sessionUser
						.getMerchantUserPO().getSiteId());
			} else if ("已取车".equals(orderStatus) && "getApply".equals(type)) {
				jsonObject.put("relityGetSiteId", sessionUser
						.getMerchantUserPO().getSiteId());
			} /*else if ("已还车".equals(orderStatus)) {
				jsonObject.put("relityReturnSiteId", sessionUser
						.getMerchantUserPO().getSiteId());
			}*/else if ("已关门".equals(returnConfirm)) {
				jsonObject.put("relityReturnSiteId", sessionUser
						.getMerchantUserPO().getSiteId());
			}
		}
		return jsonObject;
	}

	/**
	 * 导出下载车辆信息到Excel表格
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
		orderService.excelExportOrder(response, jsonObject);
		return null;
	}
	
	/**
	 * 核对租赁收费页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkFee.htm")
	public ModelAndView checkFee(Integer id) throws Exception {
		OrderPO orderPO = orderService.getOrder(id);
		return new ModelAndView("order/checkFee", "orderPO", orderPO);
	}
	
	/**
	 * 保存核对租赁收费信息
	 * 
	 * @param orderPO
	 * @return
	 */
	@RequestMapping(value = "saveCheckFee.htm")
	@ResponseBody
	public MSG saveCheckFee(OrderPO orderPO, HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		orderPO.setCheckUser(sessionUser.getName());
		msg.setSuccess(orderService.checkForCharge(orderPO));
		return msg;
	}
	
	
	
}

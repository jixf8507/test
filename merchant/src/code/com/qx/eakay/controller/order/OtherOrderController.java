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
import com.qx.eakay.service.order.OtherOrderService;

/**
 * 车辆调度,公务用车订单管理
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/order/otherOrder")
public class OtherOrderController extends BaseController {

	@Autowired
	private OtherOrderService orderService;

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
			} else if ("已还车".equals(orderStatus)) {
				jsonObject.put("relityReturnSiteId", sessionUser
						.getMerchantUserPO().getSiteId());
			} else if ("已关门".equals(returnConfirm)) {
				jsonObject.put("relityReturnSiteId", sessionUser
						.getMerchantUserPO().getSiteId());
			}
		}
		return jsonObject;
	}

	/**
	 * 导出在租订单信息信息到Excel表格
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
	 * 导出员工用车记录信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportTotleToExcel.htm")
	public ModelAndView exportTotleToExcel(String paraData,
			HttpSession session, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		orderService.exportTotleToExcel(response, jsonObject);
		return null;
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
		// 车辆调度，公务用车
		OrderPO orderPO = orderService.getOrder(id);
		return new ModelAndView("otherOrder/orderDetail", "orderPO", orderPO);
	}

	/**
	 * 进入员工用车记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("record.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("otherOrder/record");
	}

}

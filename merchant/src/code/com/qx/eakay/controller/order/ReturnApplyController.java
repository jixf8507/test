package com.qx.eakay.controller.order;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.service.order.OrderService;
import com.qx.eakay.service.order.OtherOrderService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月14日
 */
@Controller
@RequestMapping("/order/returnApply")
public class ReturnApplyController extends BaseController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OtherOrderService otherOrderService;

	/**
	 * 进入还车申请页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("order/returnApply");
	}

	/**
	 * 进入普通客户,企业会员取还车审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("returnCarEdite.htm")
	public ModelAndView returnCarEdite(Integer orderId) throws Exception {
		OrderPO orderPO = orderService.getOrder(orderId);
		return new ModelAndView("order/returnCarEdite", "orderPO", orderPO);
	}
	

	/**
	 * 进入车辆调度,公务用车取还车审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("returnOtherCarEdite.htm")
	public ModelAndView returnOtherCarEdite(Integer orderId) throws Exception {
		OrderPO orderPO = otherOrderService.getOrder(orderId);
		return new ModelAndView("otherOrder/returnCarEdite", "orderPO", orderPO);
	}

	/**
	 * 保存还车信息
	 * 
	 * @param orderPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveReturnCar.htm")
	@ResponseBody
	public MSG saveReturnCar(OrderPO orderPO, HttpSession session) {
		MSG msg = new MSG();
		
		OrderPO order = orderService.getOrderById(orderPO.getId());
		if (!"已取车".equals(order.getOrderStatus())) {
			return new MSG("当前租车订单"+order.getOrderStatus()+"！", 1);
		}
		
		SessionUserBO sessionUser = this.getSessionUser(session);
		orderPO.setMenForReturn(sessionUser.getName());
		boolean bool = orderService.saveOrderForReturn(orderPO);
		msg.setSuccess(bool);
		return msg;
	}


	/**
	 * 取消还车
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "cancel.htm")
	@ResponseBody
	public MSG cancel(Integer id) {
		MSG msg = new MSG();
		msg.setSuccess(orderService.cancelReturn(id));
		return msg;
	}
	
}

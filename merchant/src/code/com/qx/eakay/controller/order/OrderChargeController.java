package com.qx.eakay.controller.order;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.controller.BaseController;
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
@RequestMapping("/order/charge")
public class OrderChargeController extends BaseController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OtherOrderService otherOrderService;

	/**
	 * 进入收费页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("order/orderCharge");
	}

	/**
	 * 进入租赁收费页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id,Integer orderType) throws Exception {
		OrderPO orderPO = new OrderPO();
		//普通客户、企业租车
		if(1==orderType||2==orderType){
			orderPO = orderService.getOrder(id);
			return new ModelAndView("order/orderChargeEdite", "orderPO", orderPO);
			//车辆调度、公务用车
		}else{
			orderPO = otherOrderService.getOrder(id);
			return new ModelAndView("otherOrder/orderChargeEdite", "orderPO", orderPO);
		}
	}

	/**
	 * 保存租赁收费
	 * 
	 * @param orderPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(OrderPO orderPO, HttpSession session)  {
		orderPO.setMenForCharge(this.getSessionUser(session).getName());
		return orderService.saveOrderForCharge(orderPO) ;
	}
	
	/**
	 * 保存车辆调度、公务用车收费
	 * 
	 * @param orderPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveOtherEdite.htm")
	@ResponseBody
	public MSG saveOtherEdite(OrderPO orderPO, HttpSession session)  {
		orderPO.setMenForCharge(this.getSessionUser(session).getName());
		return orderService.saveOtherOrderForCharge(orderPO) ;
	}
	
//	/**
//	 * 保存租赁现金收费
//	 * 
//	 * @param orderPO
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("saveCashPay.htm")
//	@ResponseBody
//	public MSG saveCashPay(OrderPO orderPO,AccountRecordPO accountRecordPO, HttpSession session)  {
//		orderPO.setMenForCharge(this.getSessionUser(session).getName());
//		return orderService.saveCashPay(orderPO,accountRecordPO) ;
//	}

}

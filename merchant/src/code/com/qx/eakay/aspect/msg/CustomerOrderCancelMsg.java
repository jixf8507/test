package com.qx.eakay.aspect.msg;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.dao.merchant.MerchantDao;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.msg.CrMsgDao;

/**
 * 取消超时租赁订单
 * 
 * @author Administrator
 * 
 */
@Component
@Aspect
public class CustomerOrderCancelMsg {

	@Autowired
	private CrMsgDao crMsgDao;

	@Autowired
	private MerchantDao merchantDao;

	@Autowired
	private CustomerDao customerDao;

	@Pointcut("execution(* com.qx.eakay.service.order.OrderService.cancelOrders(..))")
	public void aspect() {
	}

	@AfterReturning(pointcut = "aspect()&&args(orders)")
	public void after(List<Map<String, Object>> orders) {

		if (orders != null && orders.size() > 0) {

			for (Map<String, Object> order : orders) {
				Integer orderId = Integer.parseInt(order.get("id") + "");
				String content = "由于您没有在规定的时间内取车，您预约的订单 ZCDD_" + orderId
						+ " 已被系统自动取消，如有需要请重新下单。";

				Integer customerId = Integer.parseInt(order.get("customerId")
						+ "");

				CustomerPO customerPo = customerDao.get(customerId);
				String phone = customerPo.getPhone();

				crMsgDao.send(content, phone);

			}
		}
	}
}

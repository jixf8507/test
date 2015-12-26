package com.qx.eakay.aspect.msg;

import java.math.BigDecimal;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qx.common.tools.MyDateUtil;
import com.qx.eakay.dao.customer.AccountDao;
import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.dao.merchant.MerchantDao;
import com.qx.eakay.dao.order.OrderDao;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.util.MSG;
import com.qx.msg.CrMsgDao;

/**
 * 客户消费
 * 
 * @author Administrator
 * 
 */
@Component
@Aspect
public class CustomerPayMsg {

	@Autowired
	private CrMsgDao crMsgDao;
	
	@Autowired
	private MerchantDao merchantDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private CustomerDao customerDao;

	@Pointcut("execution(* com.qx.eakay.service.order.OrderService.saveOrderForCharge(..))")
	public void aspect() {
	}

	@AfterReturning(pointcut = "aspect()&&args(orderPO)", returning = "msg")
	public void before(OrderPO orderPO, MSG msg) {

		if (msg.isSuccess()) {

			Integer orderId = orderPO.getId();

			OrderPO order = orderDao.get(orderId);
			Integer customerId = order.getCustomerId();

			BigDecimal totalCost = order.getTotalCost();

			Integer merchantId = order.getMerchantId();
			MerchantPO merchantPO = merchantDao.get(merchantId);
			String merchantName = merchantPO.getMerchantName();

			CustomerPO customerPo = customerDao.get(customerId);
			String phone = customerPo.getPhone();

			AccountPO accountPo = accountDao.getByCustomerId(customerId,
					merchantId);
			BigDecimal balance = accountPo.getBalance();

			String content = "您 " + merchantName + " 帐号于"
					+ MyDateUtil.getCurrentTime() + "支付了" + totalCost
					+ "元，当前余额" + balance + "元。";

			crMsgDao.send(content, phone);

		}
	}
}

package com.qx.eakay.aspect.msg;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qx.eakay.dao.merchant.MerchantDao;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.msg.CrMsgDao;

/**
 * 客户注册
 * 
 * @author Administrator
 * 
 */
// 声明这是一个组件
@Component
// 声明这是一个切面Bean
@Aspect
public class CustomerRegisterMsg {

	@Autowired
	private CrMsgDao crMsgDao;

	@Autowired
	private MerchantDao merchantDao;

	@Pointcut("execution(* com.qx.eakay.service.customer.CustomerService.createCustomer(..))")
	public void aspect() {
	}

	//@AfterReturning(pointcut = "aspect()&&args(customerPO)", returning = "flag")
	public void before(CustomerPO customerPO, boolean flag) {

		if (flag) {
			Integer merchantId = customerPO.getAccountPO().getMerchantId();
			MerchantPO merchantPO = merchantDao.get(merchantId);
			String merchantName = merchantPO.getMerchantName();

			String content = "恭喜您，您已被 " + merchantName
					+ " 通过了租车资格审核，可以在各租赁点享受租车服务。";
			String phone = customerPO.getPhone();

			crMsgDao.send(content, phone);

		}
	}
}

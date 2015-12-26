package com.qx.eakay.aspect.msg;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.dao.merchant.MerchantDao;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.msg.CrMsgDao;

/**
 * 客户审核办卡
 * 
 * @author Administrator
 * 
 */
@Component
@Aspect
public class CustomerCheckMsg {

	@Autowired
	private CrMsgDao crMsgDao;

	@Autowired
	private MerchantDao merchantDao;

	@Autowired
	private CustomerDao customerDao;

	@Pointcut("execution(* com.qx.eakay.service.customer.CustomerPotentialService.createAccount(..))")
	public void aspect() {
	}

	@AfterReturning(pointcut = "aspect()&&args(accountPO)", returning = "flag")
	public void before(AccountPO accountPO, Boolean flag) {

		if (flag){

			String content = "易开租车温馨提示：恭喜您已经通过审核啦，快来开启您的易开绿色出行之旅吧。";
			Integer customerId = accountPO.getCustomerId();
			CustomerPO customerPO = customerDao.get(customerId);
			String phone = customerPO.getPhone();

			crMsgDao.send(content, phone);

		}
	}
}

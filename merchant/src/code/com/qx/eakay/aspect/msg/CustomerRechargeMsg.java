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
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.msg.CrMsgDao;

/**
 * 客户充值
 * 
 * @author Administrator
 * 
 */
@Component
@Aspect
public class CustomerRechargeMsg {

	@Autowired
	private CrMsgDao crMsgDao;

	@Autowired
	private MerchantDao merchantDao;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private CustomerDao customerDao;

	/**
	 * 余额充值
	 */
	@Pointcut("execution(* com.qx.eakay.service.customer.CustomerAccountService.saveBalanceCharge(..))")
	public void aspect() {
	}

	// @AfterReturning(pointcut = "aspect()&&args(accountRecordPO)", returning =
	// "flag")
	public void before(AccountRecordPO accountRecordPO, boolean flag) {

		if (flag) {
			Integer accountId = accountRecordPO.getAccountId();

			String add = accountRecordPO.getAddBalance().toString();
			if (add.indexOf(".") == -1) {
				add = add + ".00";
			}

			AccountPO accountPO = accountDao.get(accountId);
			Integer merchantId = accountPO.getMerchantId();
			Integer customerId = accountPO.getCustomerId();
			BigDecimal balance = accountPO.getBalance();

			CustomerPO customerPo = customerDao.get(customerId);
			String phone = customerPo.getPhone();

			MerchantPO merchantPO = merchantDao.get(merchantId);
			String merchantName = merchantPO.getMerchantName();

			String content = "您 " + merchantName + " 帐号于"
					+ MyDateUtil.getCurrentTime() + "成功充值了" + add + "元，当前余额"
					+ balance + "元。";

			crMsgDao.send(content, phone);

		}
	}

	/**
	 * 保证金充值
	 */
	@Pointcut("execution(* com.qx.eakay.service.customer.CustomerAccountService.saveAssureCharge(..))")
	public void aspect1() {
	}

	@AfterReturning(pointcut = "aspect1()&&args(accountRecordPO)", returning = "flag")
	public void before1(AccountRecordPO accountRecordPO, boolean flag) {

		if (flag) {
			String add = accountRecordPO.getAddBalance().toString();
			if (add.indexOf(".") == -1) {
				add = add + ".00";
			}

			String content = "易开租车温馨提示：您已成功充值" + add
					+ "元，快来租车吧，小易已经等您很久啦，祝您用车愉快。";

			Integer customerId = accountRecordPO.getCustomerId();
			CustomerPO customerPO = customerDao.get(customerId);
			String phone = customerPO.getPhone();

			crMsgDao.send(content, phone);

		}
	}
}

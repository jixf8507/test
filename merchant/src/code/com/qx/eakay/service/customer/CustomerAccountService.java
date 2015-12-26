package com.qx.eakay.service.customer;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.eakay.dao.customer.AccountDao;
import com.qx.eakay.dao.customer.AccountRecordDao;
import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.dao.customer.CustomerMessageDao;
import com.qx.eakay.dao.customer.CustomerUnitDao;
import com.qx.eakay.dao.merchant.MerchantAccountRecordDao;
import com.qx.eakay.dao.merchant.MerchantDao;
import com.qx.eakay.dao.order.OrderDao;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.CustomerMessagePO;
import com.qx.eakay.model.customer.AccountPO.AccountStatus;
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.model.customer.AccountRecordPO.AccountRecordType;
import com.qx.eakay.model.customer.UnitCustomerPO;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月9日
 */
@Service
public class CustomerAccountService extends BaseService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private AccountRecordDao accountRecordDao;
	@Autowired
	private MerchantAccountRecordDao merchantAccountRecordDao;
	@Autowired
	private CustomerMessageDao customerMessageDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CustomerUnitDao unitDao;

	/**
	 * 更改设备的状态
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public MSG updateAccountStatus(AccountStatus status, String deletedUser,
			Integer id) {
		AccountPO accountPO = accountDao.get(id);
		MSG msg = new MSG();
		if (orderDao.findCuserUseOrders(accountPO.getCustomerId(),
				accountPO.getMerchantId()) > 0) {
			msg.setSuccess(false);
			msg.setInfo("该客户还有租赁车辆业务未结束，请先结束！");
			return msg;
		}
		msg.setSuccess(accountDao.updateStatus(status, "正常", deletedUser, id));
		return msg;
	}

	/**
	 * 获取用户帐户
	 * 
	 * @param id
	 * @return
	 */
	public AccountPO getAccount(Integer id) {
		return accountDao.get(id);
	}

	/**
	 * 注销卡退款
	 * 
	 * @param recordPO
	 * @return
	 */
	@Transactional
	public boolean refundCustomerAccount(AccountRecordPO recordPO) {
		// 得到用户账户
		AccountPO accountPO = accountDao.get(recordPO.getAccountId());
		// 创建的帐户保证金退还交易明细
		AccountRecordPO clearAssureRecord = AccountRecordPO
				.createClearAssureRecord(accountPO, AccountRecordType.保证金退款,
						recordPO.getDescribe(), recordPO.getTransactUser(),
						recordPO.getSiteId());
		accountRecordDao.create(clearAssureRecord);
		// 清空账户中的保证金余额
		accountDao.addAssure(
				new BigDecimal(0).subtract(accountPO.getMoneyOfassure()),
				accountPO.getId());

		// 清空账户中的冻结资金余额
		accountDao.clearFreezeBalance(accountPO.getFreezeBalance(),
				accountPO.getId());

		// 创建账户余额退款明细记录
		AccountRecordPO clearBalanceRecord = AccountRecordPO
				.createClearBalanceRecord(accountPO, AccountRecordType.余额退款,
						recordPO.getDescribe(), recordPO.getTransactUser(),
						recordPO.getSiteId());
		accountRecordDao.create(clearBalanceRecord);

		// 清空账户余额
		accountDao.addBalance(
				new BigDecimal(0).subtract(accountPO.getBalance()),
				accountPO.getId());
		// 修改商家账户的余额
		// this.updateMerchantAccountBalance(accountPO.getBalance(),
		// AccountRecordType.余额退款, recordPO.getTransactUser(),
		// recordPO.getSiteId(), accountPO.getMerchantId());
		// 更改账户的退款状态
		// return accountDao.updateStatus(AccountStatus.注销, "已退款",
		// accountPO.getDeletedUser(), accountPO.getId());
		// 更改账户的退款状态，现在只该变subStatus的状态，不改变status的状态，页面状态是正常，现在任然是正常
		return accountDao.updateStatus(AccountStatus.正常, "已退款",
				accountPO.getDeletedUser(), accountPO.getId());
	}

	/**
	 * 保存账户余额充值
	 * 
	 * @param accountRecordPO
	 * @return
	 */
	@Transactional
	public boolean saveBalanceCharge(AccountRecordPO accountRecordPO) {
		// 企业客户
		if (accountRecordPO.getAccountId() == -1) {
			// 获取充值账户
			UnitCustomerPO unitCustomerPO = unitDao
					.getUnitCustomer(accountRecordPO.getCustomerId());
			// 新增账户充值明细记录
			AccountRecordPO addBalanceRecord = AccountRecordPO
					.createUnitAddBalanceRecord(unitCustomerPO,
							accountRecordPO.getAddBalance(),
							accountRecordPO.getAccountId(),
							AccountRecordType.余额充值,
							accountRecordPO.getDescribe(),
							accountRecordPO.getTransactUser(),
							accountRecordPO.getSiteId(),
							accountRecordPO.getTicket());
			accountRecordDao.create(addBalanceRecord);
			// 修改账户余额
			unitDao.addUnitBalance(accountRecordPO.getAddBalance(),
					accountRecordPO.getCustomerId());

			// 更改商家账户的余额
			// BigDecimal addBalance = new
			// BigDecimal(0).subtract(accountRecordPO
			// .getAddBalance());
			// updateMerchantAccountBalance(addBalance, AccountRecordType.余额充值,
			// accountRecordPO.getTransactUser(),
			// accountRecordPO.getSiteId(), unitCustomerPO.getMerchantId());
			return true;
		}
		// 普通客户
		// 获取系统用户账户
		AccountPO accountPO = accountDao.get(accountRecordPO.getAccountId());
		// 新增账户充值明细记录
		AccountRecordPO addBalanceRecord = AccountRecordPO
				.createAddBalanceRecord(accountPO,
						accountRecordPO.getAddBalance(),
						AccountRecordType.余额充值, accountRecordPO.getDescribe(),
						accountRecordPO.getTransactUser(),
						accountRecordPO.getSiteId(),
						accountRecordPO.getTicket());
		accountRecordDao.create(addBalanceRecord);

		// 修改账户余额
		accountDao.addBalance(accountRecordPO.getAddBalance(),
				accountRecordPO.getAccountId());
		// 更改商家账户的余额
		// BigDecimal addBalance = new BigDecimal(0).subtract(accountRecordPO
		// .getAddBalance());
		// updateMerchantAccountBalance(addBalance, AccountRecordType.余额充值,
		// accountRecordPO.getTransactUser(), accountRecordPO.getSiteId(),
		// accountPO.getMerchantId());
		// 新增账户充值的消息
		// customerMessageDao.createBalanceChargeMsg(accountRecordPO);
		return true;
	}

	/**
	 * 更改商家账户的余额
	 * 
	 * @param accountRecordPO
	 * @param accountPO
	 */
	/*
	 * private void updateMerchantAccountBalance(BigDecimal addBalance,
	 * AccountRecordType type, String transactUser, Integer siteId, Integer
	 * merchantId) { // 查询得到商家账户 MerchantPO merchantPO =
	 * merchantDao.get(merchantId); // 创建商家账户交易记录记录 MerchantAccountRecordPO
	 * accountRecordPO = MerchantAccountRecordPO
	 * .createAddBalanceRecord(merchantPO, addBalance, type, transactUser,
	 * siteId); merchantAccountRecordDao.create(accountRecordPO); // 修改商家账户余额 //
	 * merchantDao.addBalance(addBalance, new BigDecimal(0), //
	 * merchantPO.getId()); }
	 */

	/**
	 * 保存帐号保证金充值
	 * 
	 * @param accountRecordPO
	 * @return
	 */
	@Transactional
	public boolean saveAssureCharge(AccountRecordPO accountRecordPO) {
		// 企业客户
		if (accountRecordPO.getAccountId() == -1) {
			// 获取充值账户
			UnitCustomerPO unitCustomerPO = unitDao
					.getUnitCustomer(accountRecordPO.getCustomerId());
			// 新增保证金充值记录
			AccountRecordPO addAssureRecord = AccountRecordPO
					.createUnitAddAssureRecord(unitCustomerPO,
							accountRecordPO.getAddBalance(),
							accountRecordPO.getAccountId(),
							AccountRecordType.保证金充值,
							accountRecordPO.getDescribe(),
							accountRecordPO.getTransactUser(),
							accountRecordPO.getSiteId(),
							accountRecordPO.getTicket());
			accountRecordDao.create(addAssureRecord);
			// 更改账户保证金
			return unitDao.addUnitAssure(accountRecordPO.getAddBalance(),
					accountRecordPO.getCustomerId());
		}

		// 普通客户
		// 获取充值账户
		AccountPO accountPO = accountDao.get(accountRecordPO.getAccountId());
		// 新增保证金充值记录
		AccountRecordPO addAssureRecord = AccountRecordPO
				.createAddAssureRecord(accountPO,
						accountRecordPO.getAddBalance(),
						AccountRecordType.保证金充值, accountRecordPO.getDescribe(),
						accountRecordPO.getTransactUser(),
						accountRecordPO.getSiteId(),
						accountRecordPO.getTicket());
		accountRecordDao.create(addAssureRecord);

		// 更改账户保证金
		return accountDao.addAssure(accountRecordPO.getAddBalance(),
				accountPO.getId());
	}

	/**
	 * 用户补卡
	 * 
	 * @param recordPO
	 * @return
	 */
	@Transactional
	public boolean repairCustomerAccount(AccountPO accountPO,
			String transactUser, Integer siteId) {
		// 获取旧的用户账户
		AccountPO oldAccountPO = accountDao.get(accountPO.getId());
		// 创建新的账户
		rePairNewAccount(oldAccountPO, accountPO, transactUser, siteId);
		// 清空旧账户
		return clearOldAccount(oldAccountPO, transactUser, siteId);
	}

	/**
	 * 根据旧账户复制新的账户
	 * 
	 * @param oldAccountPO
	 *            旧的客户账户
	 * @param accountPO
	 *            新账户
	 * @param transactUser
	 *            经办人
	 * @param siteId
	 *            处理站点ID
	 */
	private void rePairNewAccount(AccountPO oldAccountPO, AccountPO accountPO,
			String transactUser, Integer siteId) {
		accountPO.setMerchantId(oldAccountPO.getMerchantId());
		accountPO.setCustomerId(oldAccountPO.getCustomerId());
		int aid = accountDao.create(accountPO);
		accountPO.setId(aid);
		// 新增余额
		BigDecimal addBalance = oldAccountPO.getBalance();
		// 新增保证金
		BigDecimal addAssure = oldAccountPO.getMoneyOfassure();

		AccountRecordPO addBalanceRecord = AccountRecordPO
				.createAddBalanceRecord(accountPO, addBalance,
						AccountRecordType.余额转入, "补卡转入", transactUser, siteId,
						"");
		accountRecordDao.create(addBalanceRecord);

		// 添加新账户余额
		accountDao.addBalance(addBalance, aid);

		// 新卡保证金充值记录
		AccountRecordPO addAssureRecord = AccountRecordPO
				.createAddAssureRecord(accountPO, addAssure,
						AccountRecordType.保证金转入, "补卡转入", transactUser, siteId,
						"");
		accountRecordDao.create(addAssureRecord);

		// 添加新卡保证金
		accountDao.addAssure(addAssure, aid);
	}

	/**
	 * 清空旧账户
	 * 
	 * @param oldAccountPO
	 *            旧的账户
	 * @param transactUser
	 *            处理人
	 * @param siteId
	 *            处理站点ID
	 * @return
	 */
	private boolean clearOldAccount(AccountPO oldAccountPO,
			String transactUser, Integer siteId) {
		// 新增旧卡账户余额转出记录
		AccountRecordPO clearBalanceRecord = AccountRecordPO
				.createClearBalanceRecord(oldAccountPO, AccountRecordType.余额转出,
						"补卡转出", transactUser, siteId);
		accountRecordDao.create(clearBalanceRecord);

		BigDecimal addBalance = new BigDecimal(0).subtract(oldAccountPO
				.getBalance());
		// 清空旧账户余额
		accountDao.addBalance(addBalance, oldAccountPO.getId());

		// 旧卡保证金转出记录
		AccountRecordPO clearAssureRecord = AccountRecordPO
				.createClearAssureRecord(oldAccountPO, AccountRecordType.保证金转出,
						"补卡转出", transactUser, siteId);
		accountRecordDao.create(clearAssureRecord);

		BigDecimal addAssure = new BigDecimal(0).subtract(oldAccountPO
				.getMoneyOfassure());
		// 清空旧账户保证金
		accountDao.addAssure(addAssure, oldAccountPO.getId());
		// 修改账户的状态为已补卡
		return accountDao.updateStatus(AccountStatus.注销, "已补卡",
				oldAccountPO.getDeletedUser(), oldAccountPO.getId());
	}

	/**
	 * 更改状态（注销，冻结）
	 * 
	 * @param accountPO
	 * @return
	 */
	public MSG changeStatusSubmit(AccountPO accountPO) {
		MSG msg = new MSG();
		AccountPO account = accountDao.get(accountPO.getId());
		account.setStatus(accountPO.getStatus());
		if (orderDao.findCuserUseOrders(account.getCustomerId(),
				account.getMerchantId()) > 0) {
			msg.setSuccess(false);
			msg.setInfo("该卡还有租赁车辆业务未结束，请先结束！");
			return msg;
		}
		msg.setSuccess(accountDao.changeStatusSubmit(accountPO));
		return msg;
	}

	/**
	 * 退款操作
	 * 
	 * @param accountPO
	 * @return
	 */
	public MSG backMoneySubmit(AccountPO accountPO) {
		MSG msg = new MSG();
		AccountPO account = accountDao.get(accountPO.getId());
		account.setStatus(accountPO.getStatus());
		if (orderDao.findCuserUseOrders(account.getCustomerId(),
				account.getMerchantId()) > 0) {
			msg.setSuccess(false);
			msg.setInfo("该卡还有租赁车辆业务未结束，请先结束！");
			return msg;
		}
		// 改变substatus的状态，并且将保证金的钱全部移动到冻结资金中去
		accountPO.setMoneyOfassure(account.getMoneyOfassure());
		accountDao.backMoneySubmit(accountPO);
		msg.setSuccess(true);
		return msg;
	}

	/**
	 * 保存账户余额充值(充电卡脱机充值)
	 * 
	 * @param accountRecordPO
	 * @return
	 */
	@Transactional
	public boolean saveStakeCardBalanceCharge(AccountRecordPO accountRecordPO,
			String cardID) {
		// 新增账户充值明细记录
		accountRecordDao.create(accountRecordPO);

		// 充值消息
		return customerMessageDao.create(CustomerMessagePO.createStakeCardChargeMsg(
				accountRecordPO.getCustomerId(), cardID,
				accountRecordPO.getAddBalance(),
				accountRecordPO.getNewBalance()))>0;
	}

}

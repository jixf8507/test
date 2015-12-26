package com.qx.eakay.model.customer;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 
 * @author Administrator
 * 
 */

public class AccountRecordPO {

	/**
	 * 账户交易类型
	 * 
	 * @author jixf
	 * @date 2015年7月31日
	 */
	public static enum AccountRecordType {
		保证金充值, 保证金退款, 保证金转出, 保证金转入, 余额充值, 余额退款, 余额转出, 余额转入, 租赁付费, 车损付费, 违规付费, 充电消费, 电子钱包充值
	}

	private Integer id;
	private Integer customerId;
	/**
	 * 账户改变前金额.
	 */
	private BigDecimal oldBalance;
	/**
	 * 改变金额.
	 */
	private BigDecimal addBalance;
	/**
	 * 改变后金额.
	 */
	private BigDecimal newBalance;
	/**
	 * 经办人.人工操作有该值.
	 */
	private String transactUser;
	private Timestamp createdTime;
	/**
	 * 记录类型：人工充值，保证金充值，余额充值，租赁付费，车损付费，违规付费
	 */
	private AccountRecordType type;

	private Integer accountId;

	private Integer siteId;

	private String describe;
	
	private String customerTable;
	private String accountTable="account";
	
	private String ticket="";
	private String checkUser;
	private String checkDescribe;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getCheckDescribe() {
		return checkDescribe;
	}

	public void setCheckDescribe(String checkDescribe) {
		this.checkDescribe = checkDescribe;
	}

	public AccountRecordPO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(BigDecimal oldBalance) {
		this.oldBalance = oldBalance;
	}

	public BigDecimal getAddBalance() {
		return addBalance;
	}

	public void setAddBalance(BigDecimal addBalance) {
		this.addBalance = addBalance;
	}

	public BigDecimal getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(BigDecimal newBalance) {
		this.newBalance = newBalance;
	}

	public String getTransactUser() {
		return transactUser;
	}

	public void setTransactUser(String transactUser) {
		this.transactUser = transactUser;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public AccountRecordType getType() {
		return type;
	}

	public void setType(AccountRecordType type) {
		this.type = type;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getCustomerTable() {
		return customerTable;
	}

	public void setCustomerTable(String customerTable) {
		this.customerTable = customerTable;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	
	
	public String getAccountTable() {
		return accountTable;
	}

	public void setAccountTable(String accountTable) {
		this.accountTable = accountTable;
	}

	/**
	 * 创建新增账户余额记录
	 * 
	 * @param accountPO
	 *            清空账户对象
	 * @param type
	 *            交易记录类型
	 * @param describe
	 *            交易记录描述
	 * @param transactUser
	 *            经办人
	 * @param siteId
	 *            办理租赁点
	 */
	public static AccountRecordPO createClearBalanceRecord(AccountPO accountPO,
			AccountRecordType type, String describe, String transactUser,
			Integer siteId) {
		// 新增旧卡账户余额转出记录
		AccountRecordPO balanceClearRecord = new AccountRecordPO();// 余额转出记录
		balanceClearRecord.setAccountId(accountPO.getId());
		balanceClearRecord.setCustomerId(accountPO.getCustomerId());
		balanceClearRecord.setOldBalance(accountPO.getBalance());
		balanceClearRecord.setAddBalance(new BigDecimal(0).subtract(accountPO
				.getBalance()));
		balanceClearRecord.setNewBalance(new BigDecimal(0));
		balanceClearRecord.setTransactUser(transactUser);
		balanceClearRecord.setSiteId(siteId);
		balanceClearRecord.setType(type);
		balanceClearRecord.setDescribe(describe);
		balanceClearRecord.setCustomerTable("customer");
		return balanceClearRecord;
	}

	/**
	 * 创建新增账户保证金记录
	 * 
	 * @param accountPO
	 *            清空账户对象
	 * @param type
	 *            交易记录类型
	 * @param describe
	 *            交易记录描述
	 * @param transactUser
	 *            经办人
	 * @param siteId
	 *            办理租赁点ID
	 */
	public static AccountRecordPO createClearAssureRecord(AccountPO accountPO,
			AccountRecordType type, String describe, String transactUser,
			Integer siteId) {
		// 新卡保证金充值记录
		AccountRecordPO assureClearRecord = new AccountRecordPO();// 保证金转出记录
		assureClearRecord.setAccountId(accountPO.getId());
		assureClearRecord.setCustomerId(accountPO.getCustomerId());
		assureClearRecord.setOldBalance(accountPO.getMoneyOfassure());
		assureClearRecord.setAddBalance(new BigDecimal(0).subtract(accountPO
				.getFreezeBalance()));
		assureClearRecord.setNewBalance(new BigDecimal(0));
		assureClearRecord.setTransactUser(transactUser);
		assureClearRecord.setSiteId(siteId);
		assureClearRecord.setType(type);
		assureClearRecord.setDescribe(describe);
		assureClearRecord.setCustomerTable("customer");
		return assureClearRecord;
	}

	/**
	 * 创建账户余额增加交易记录
	 * 
	 * @param accountPO
	 *            账户对象
	 * @param addBalance
	 *            增加余额
	 * @param type
	 *            交易类型
	 * @param describe
	 *            交易描述
	 * @param transactUser
	 *            经办人
	 * @param siteId
	 *            办理站点ID
	 */
	public static AccountRecordPO createAddBalanceRecord(AccountPO accountPO,
			BigDecimal addBalance, AccountRecordType type, String describe,
			String transactUser, Integer siteId,String ticket) {
		AccountRecordPO balanceAddRecord = new AccountRecordPO();
		balanceAddRecord.setAccountId(accountPO.getId());
		balanceAddRecord.setCustomerId(accountPO.getCustomerId());
		balanceAddRecord.setOldBalance(accountPO.getBalance());
		balanceAddRecord.setAddBalance(addBalance);
		balanceAddRecord.setNewBalance(balanceAddRecord.getOldBalance().add(
				balanceAddRecord.getAddBalance()));
		balanceAddRecord.setTransactUser(transactUser);
		balanceAddRecord.setSiteId(siteId);
		balanceAddRecord.setType(type);
		balanceAddRecord.setDescribe(describe);
		balanceAddRecord.setCustomerTable("customer");
		balanceAddRecord.setTicket(ticket);
		return balanceAddRecord;
	}

	/**
	 * 创建账户保证金增加交易记录
	 * 
	 * @param accountPO
	 *            账户对象
	 * @param addBalance
	 *            增加保证金
	 * @param type
	 *            交易类型
	 * @param describe
	 *            交易描述
	 * @param transactUser
	 *            经办人
	 * @param siteId
	 *            办理站点ID
	 * @param string 
	 */
	public static AccountRecordPO createAddAssureRecord(AccountPO accountPO,
			BigDecimal addAssure, AccountRecordType type, String describe,
			String transactUser, Integer siteId, String ticket) {
		AccountRecordPO assureAddRecord = new AccountRecordPO();// 保证金增加记录
		assureAddRecord.setAccountId(accountPO.getId());
		assureAddRecord.setCustomerId(accountPO.getCustomerId());
		assureAddRecord.setOldBalance(accountPO.getMoneyOfassure());
		assureAddRecord.setAddBalance(addAssure);
		assureAddRecord.setNewBalance(assureAddRecord.getOldBalance().add(
				assureAddRecord.getAddBalance()));
		assureAddRecord.setTransactUser(transactUser);
		assureAddRecord.setSiteId(siteId);
		assureAddRecord.setType(type);
		assureAddRecord.setDescribe(describe);
		assureAddRecord.setCustomerTable("customer");
		assureAddRecord.setTicket(ticket);
		return assureAddRecord;
	}
	
	
	/**
	 * 创建单位会员账户余额增加交易记录
	 * 
	 * @param unitCustomerPO
	 * @param addBalance
	 * @param accountId
	 * @param type
	 * @param describe
	 * @param transactUser
	 * @param siteId
	 * @param string 
	 * @return
	 */
	public static AccountRecordPO createUnitAddBalanceRecord(UnitCustomerPO unitCustomerPO,
			BigDecimal addBalance,Integer accountId,  AccountRecordType type, String describe,
			String transactUser, Integer siteId, String ticket) {
		AccountRecordPO balanceAddRecord = new AccountRecordPO();
		balanceAddRecord.setAccountId(accountId);
		balanceAddRecord.setCustomerId(unitCustomerPO.getId());
		balanceAddRecord.setOldBalance(unitCustomerPO.getBalance());
		balanceAddRecord.setAddBalance(addBalance);
		balanceAddRecord.setNewBalance(balanceAddRecord.getOldBalance().add(
				balanceAddRecord.getAddBalance()));
		balanceAddRecord.setTransactUser(transactUser);
		balanceAddRecord.setSiteId(siteId);
		balanceAddRecord.setType(type);
		balanceAddRecord.setDescribe(describe);
		balanceAddRecord.setCustomerTable("merchant_enterprise_customer");
		balanceAddRecord.setTicket(ticket);
		return balanceAddRecord;
	}

	/**
	 * 创建单位会员账户保证金增加交易记录
	 * 
	 * @param unitCustomerPO
	 * @param addAssure
	 * @param type
	 * @param describe
	 * @param transactUser
	 * @param siteId
	 * @param string 
	 * @param tableName 
	 * @return
	 */
	public static AccountRecordPO createUnitAddAssureRecord(UnitCustomerPO unitCustomerPO,
			BigDecimal addAssure,Integer accountId, AccountRecordType type, String describe,
			String transactUser, Integer siteId, String ticket) {
		AccountRecordPO assureAddRecord = new AccountRecordPO();// 保证金增加记录
		assureAddRecord.setAccountId(accountId);
		assureAddRecord.setCustomerId(unitCustomerPO.getId());
		assureAddRecord.setOldBalance(unitCustomerPO.getMoneyOfassure());
		assureAddRecord.setAddBalance(addAssure);
		assureAddRecord.setNewBalance(assureAddRecord.getOldBalance().add(
				assureAddRecord.getAddBalance()));
		assureAddRecord.setTransactUser(transactUser);
		assureAddRecord.setSiteId(siteId);
		assureAddRecord.setType(type);
		assureAddRecord.setDescribe(describe);
		assureAddRecord.setCustomerTable("merchant_enterprise_customer");
		assureAddRecord.setTicket(ticket);
		return assureAddRecord;
	}
	
	/**
	 * 创建清空单位会员账户保证金记录
	 * 
	 * @param accountPO
	 *            清空账户对象
	 * @param type
	 *            交易记录类型
	 * @param describe
	 *            交易记录描述
	 * @param transactUser
	 *            经办人
	 * @param siteId
	 *            办理租赁点ID
	 */
	public static AccountRecordPO createClearUnitAssureRecord(UnitCustomerPO unitCustomerPO,
			AccountRecordType type, String describe, String transactUser) {
		// 新卡保证金充值记录
		AccountRecordPO assureClearRecord = new AccountRecordPO();// 保证金转出记录
		assureClearRecord.setAccountId(-1);
		assureClearRecord.setCustomerId(unitCustomerPO.getId());
		assureClearRecord.setOldBalance(unitCustomerPO.getMoneyOfassure());
		assureClearRecord.setAddBalance(new BigDecimal(0).subtract(unitCustomerPO
				.getMoneyOfassure()));
		assureClearRecord.setNewBalance(new BigDecimal(0));
		assureClearRecord.setTransactUser(transactUser);
		assureClearRecord.setSiteId(-1);
		assureClearRecord.setType(type);
		assureClearRecord.setDescribe(describe);
		assureClearRecord.setCustomerTable("merchant_enterprise_customer");
		return assureClearRecord;
	}

	/**
	 * 创建清空单位会员账户余额记录
	 * 
	 * @param accountPO
	 *            清空账户对象
	 * @param type
	 *            交易记录类型
	 * @param describe
	 *            交易记录描述
	 * @param transactUser
	 *            经办人
	 * @param siteId
	 *            办理租赁点
	 */
	public static AccountRecordPO createClearUnitBalanceRecord(UnitCustomerPO unitCustomerPO,
			AccountRecordType type, String describe, String transactUser) {
		// 新增旧卡账户余额转出记录
		AccountRecordPO balanceClearRecord = new AccountRecordPO();// 余额转出记录
		balanceClearRecord.setAccountId(-1);
		balanceClearRecord.setCustomerId(unitCustomerPO.getId());
		balanceClearRecord.setOldBalance(unitCustomerPO.getBalance());
		balanceClearRecord.setAddBalance(new BigDecimal(0).subtract(unitCustomerPO
				.getBalance()));
		balanceClearRecord.setNewBalance(new BigDecimal(0));
		balanceClearRecord.setTransactUser(transactUser);
		balanceClearRecord.setSiteId(-1);
		balanceClearRecord.setType(type);
		balanceClearRecord.setDescribe(describe);
		balanceClearRecord.setCustomerTable("merchant_enterprise_customer");
		return balanceClearRecord;
	}

}

package com.qx.eakay.model.merchant;

import java.math.BigDecimal;

import com.qx.eakay.model.customer.AccountRecordPO.AccountRecordType;

public class MerchantAccountRecordPO {

	private Integer id;
	private Integer merchantId;
	private BigDecimal oldBalance;
	private BigDecimal addBalance;
	private BigDecimal newBalance;
	private AccountRecordType type;
	private Integer siteId;

	private String transactUser;

	public MerchantAccountRecordPO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
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

	public AccountRecordType getType() {
		return type;
	}

	public void setType(AccountRecordType type) {
		this.type = type;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getTransactUser() {
		return transactUser;
	}

	public void setTransactUser(String transactUser) {
		this.transactUser = transactUser;
	}

	/**
	 * 创建商家账户余额增加交易记录明细
	 * 
	 * @param merchantPO
	 *            商家对象
	 * @param addBalance
	 *            增加余额
	 * @param type
	 *            交易类型
	 * @param transactUser
	 *            经办人
	 * @param siteId
	 *            办理站点ID
	 */
	public static MerchantAccountRecordPO createAddBalanceRecord(
			MerchantPO merchantPO, BigDecimal addBalance,
			AccountRecordType recordType, String transactUser, Integer siteId) {
		MerchantAccountRecordPO accountRecordPO = new MerchantAccountRecordPO();
		accountRecordPO.setMerchantId(merchantPO.getId());
		accountRecordPO.setOldBalance(merchantPO.getBalance());
		accountRecordPO.setAddBalance(addBalance);
		accountRecordPO.setNewBalance(merchantPO.getBalance().add(
				accountRecordPO.getAddBalance()));
		accountRecordPO.setType(recordType);
		accountRecordPO.setSiteId(siteId);
		accountRecordPO.setTransactUser(transactUser);
		return accountRecordPO;
	}

}

package com.qx.eakay.model.customer;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 
 * @author Administrator
 * 
 */
public class AccountPO {

	public static enum AccountStatus {
		正常, 冻结, 注销
	}

	private Integer id;
	private Integer customerId;
	private Integer merchantId;
	private BigDecimal balance = new BigDecimal(0);
	private BigDecimal moneyOfassure = new BigDecimal(0);
	private BigDecimal freezeBalance =new BigDecimal(0);
	private String cardNO;
	private String status;
	private String bankCardNO;
	private String bankType;
	private String describe;
	private String other;

	private Integer siteId;
	private Timestamp createdTime;
	private Timestamp deletedTime;
	private String transactUser;
	private String deletedUser;

	public AccountPO() {

	}

	public AccountPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.customerId = rs.getInt("customerId");
		this.merchantId = rs.getInt("merchantId");
		this.moneyOfassure = rs.getBigDecimal("moneyOfassure");
		this.balance = rs.getBigDecimal("balance");
		this.cardNO = rs.getString("cardNO");
		this.status = rs.getString("status");
		this.bankCardNO = rs.getString("bankCardNO");
		this.bankType = rs.getString("bankType");
		this.describe = rs.getString("describe");
		this.transactUser=rs.getString("transactUser") ;
		this.deletedUser=rs.getString("deletedUser") ;
		this.createdTime=rs.getTimestamp("createdTime") ;
		this.deletedTime=rs.getTimestamp("deletedTime") ;
		this.freezeBalance=rs.getBigDecimal("freezeBalance");
	}
	
	
	

	public BigDecimal getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(BigDecimal freezeBalance) {
		this.freezeBalance = freezeBalance;
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

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getMoneyOfassure() {
		return moneyOfassure;
	}

	public void setMoneyOfassure(BigDecimal moneyOfassure) {
		this.moneyOfassure = moneyOfassure;
	}

	public String getCardNO() {
		return cardNO;
	}

	public void setCardNO(String cardNO) {
		this.cardNO = cardNO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBankCardNO() {
		return bankCardNO;
	}

	public void setBankCardNO(String bankCardNO) {
		this.bankCardNO = bankCardNO;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Timestamp deletedTime) {
		this.deletedTime = deletedTime;
	}

	public String getTransactUser() {
		return transactUser;
	}

	public void setTransactUser(String transactUser) {
		this.transactUser = transactUser;
	}

	public String getDeletedUser() {
		return deletedUser;
	}

	public void setDeletedUser(String deletedUser) {
		this.deletedUser = deletedUser;
	}

}

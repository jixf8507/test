package com.qx.eakay.model.customer;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 单位会员信息
 * 
 * @author Administrator
 * 
 */
public class UnitCustomerPO {

	private Integer id;
	private Integer merchantId;
	private String enterpriseName;
	private String contactPerson;
	private String contactPhone;
	private String address;
	private BigDecimal balance;
	private BigDecimal moneyOfassure;
	private String status;
	private String subStatus;
	private String transactUser;
	private Timestamp createdTime;
	private String describe;
	private String other;
	private Timestamp deletedTime;
	private String deleteUser;
	private String bankCardNO;
	private String bankType;

	public AccountPO accountPO;

	public UnitCustomerPO() {

	}

	public UnitCustomerPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.merchantId = rs.getInt("merchantId");
		this.enterpriseName = rs.getString("enterpriseName");
		this.contactPerson = rs.getString("contactPerson");
		this.contactPhone = rs.getString("contactPhone");
		this.address = rs.getString("address");
		this.balance = rs.getBigDecimal("balance");
		this.moneyOfassure = rs.getBigDecimal("moneyOfassure");
		this.status = rs.getString("status");
		this.subStatus = rs.getString("subStatus");
		this.transactUser = rs.getString("transactUser");
		this.createdTime = rs.getTimestamp("createdTime");
		this.describe = rs.getString("describe");
		this.deletedTime = rs.getTimestamp("deletedTime");
		this.deleteUser = rs.getString("deleteUser");
		this.bankCardNO = rs.getString("bankCardNO");
		this.bankType = rs.getString("bankType");
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

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public AccountPO getAccountPO() {
		return accountPO;
	}

	public void setAccountPO(AccountPO accountPO) {
		this.accountPO = accountPO;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Timestamp getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Timestamp deletedTime) {
		this.deletedTime = deletedTime;
	}

	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
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


}

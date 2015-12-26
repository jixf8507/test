package com.qx.eakay.model.customer;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 客户信息
 * 
 * @author Administrator
 * 
 */
public class CustomerPO {

	private Integer id;
	private String idCard;
	private String name;
	private String phone;
	private String sex;
	private String address;
	private BigDecimal moneyOfassure;
	private BigDecimal balance;
	private String idCardImg;
	private String driveCardImg;
	private String verifyMan;
	private Timestamp verifyTime;
	private String status;
	private Timestamp createdTime;
	private String workUnit ;

	public AccountPO accountPO;

	public CustomerPO() {

	}

	public CustomerPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.idCard = rs.getString("idCard");
		this.name = rs.getString("name");
		this.phone = rs.getString("phone");
		this.address = rs.getString("address");
		this.sex = rs.getString("sex");
		this.idCardImg = rs.getString("idCardImg");
		this.driveCardImg = rs.getString("driveCardImg");
		this.verifyMan = rs.getString("verifyMan");
		this.status = rs.getString("status");
		this.createdTime = rs.getTimestamp("createdTime");
		this.workUnit = rs.getString("workUnit");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getMoneyOfassure() {
		return moneyOfassure;
	}

	public void setMoneyOfassure(BigDecimal moneyOfassure) {
		this.moneyOfassure = moneyOfassure;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getIdCardImg() {
		return idCardImg;
	}

	public void setIdCardImg(String idCardImg) {
		this.idCardImg = idCardImg;
	}

	public String getDriveCardImg() {
		return driveCardImg;
	}

	public void setDriveCardImg(String driveCardImg) {
		this.driveCardImg = driveCardImg;
	}

	public String getVerifyMan() {
		return verifyMan;
	}

	public void setVerifyMan(String verifyMan) {
		this.verifyMan = verifyMan;
	}

	public Timestamp getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Timestamp verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

}

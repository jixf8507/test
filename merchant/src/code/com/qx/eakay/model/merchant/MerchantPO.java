package com.qx.eakay.model.merchant;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 商户对象
 * 
 * @author Administrator
 * 
 */

public class MerchantPO {

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 登录邮箱号
	 */
	private String email;
	/**
	 * 登录密码
	 */
	private String loginPassword;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/*
	 * 法人
	 */
	private String corporation;
	/**
	 * 手机号码
	 */
	private String mobilePhone;
	/**
	 * 余额
	 */
	private BigDecimal balance;

	private BigDecimal freezeBalance;
	/**
	 * 状态('注册','使用','冻结','注销')
	 */
	private String status;
	/**
	 * 创建记录时间
	 */
	private Date createdTime;
	/**
	 * 修改时间
	 */
	private Date updatedTime;
	/**
	 * 身份证号
	 */
	private String idCard;
	/**
	 * 支付密码
	 */
	private String payPassword;

	/**
	 * 商家支付宝帐号
	 */
	private String alipayAccount;

	/**
	 * 商家支付宝账户名
	 */
	private String alipayAccountName;

	private String bankTypeName;
	/**
	 * 客户服务热线
	 */
	private String hotTel;

	/** 是否同意保证金 */
	private String agreeSystemMoney;

	/*** 设置保证金金额 */
	private BigDecimal rentalMoney;

	/*** 商家网站地址 */
	private String linkUrl;
	
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	/*** 商家所在省 */
	private String province;
	
	/*** 商家所在市 */
	private String city;

	public MerchantPO() {

	}

	public MerchantPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.merchantName = rs.getString("merchantName");
		this.email = rs.getString("email");
		this.loginPassword = rs.getString("loginPassword");
		this.mobilePhone = rs.getString("mobilePhone");
		this.idCard = rs.getString("idCard");
		this.corporation = rs.getString("corporation");
		this.status = rs.getString("status");
		this.balance = rs.getBigDecimal("balance");
		this.alipayAccount = rs.getString("alipayAccount");
		this.alipayAccountName = rs.getString("alipayAccountName");
		this.bankTypeName = rs.getString("bankTypeName");
		this.freezeBalance = rs.getBigDecimal("freezeBalance");
		this.hotTel = rs.getString("hotTel");
		this.agreeSystemMoney = rs.getString("agreeSystemMoney");
		this.rentalMoney = rs.getBigDecimal("rentalMoney");
		this.linkUrl = rs.getString("linkUrl");
		this.province=rs.getString("province");
		this.city=rs.getString("city");
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getAlipayAccountName() {
		return alipayAccountName;
	}

	public void setAlipayAccountName(String alipayAccountName) {
		this.alipayAccountName = alipayAccountName;
	}

	public String getBankTypeName() {
		return bankTypeName;
	}

	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}

	public BigDecimal getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(BigDecimal freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	public String getHotTel() {
		return hotTel;
	}

	public void setHotTel(String hotTel) {
		this.hotTel = hotTel;
	}

	public String getAgreeSystemMoney() {
		return agreeSystemMoney;
	}

	public void setAgreeSystemMoney(String agreeSystemMoney) {
		this.agreeSystemMoney = agreeSystemMoney;
	}

	public BigDecimal getRentalMoney() {
		return rentalMoney;
	}

	public void setRentalMoney(BigDecimal rentalMoney) {
		this.rentalMoney = rentalMoney;
	}
	
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUr(String linkUrl) {
		this.linkUrl =linkUrl;
	}
	public String getProvince() {
		return province;
	}

	public void setProvinc(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 商户状态
	 * 
	 * @author Administrator
	 * 
	 */
	public static enum MerchantStatus {
		注册, 使用, 冻结, 注销
	}

	@Override
	public String toString() {
		return "MerchantPO [id=" + id + ", email=" + email + ", loginPassword="
				+ loginPassword + ", merchantName=" + merchantName
				+ ", corporation=" + corporation + ", mobilePhone="
				+ mobilePhone + ", balance=" + balance + ", freezeBalance="
				+ freezeBalance + ", status=" + status + ", createdTime="
				+ createdTime + ", updatedTime=" + updatedTime + ", idCard="
				+ idCard + ", payPassword=" + payPassword + ", alipayAccount="
				+ alipayAccount + ", alipayAccountName=" + alipayAccountName
				+ ", bankTypeName=" + bankTypeName + ", agreeSystemMoney="
				+ agreeSystemMoney + ",rentalMoney=" + rentalMoney + "]";
	}

}

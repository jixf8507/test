package com.qx.eakay.model.merchant;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.qx.common.tools.MyDateUtil;
/**
 * 优惠券的实体类
 * @author Administrator
 *
 */
public class CouponPo {
	// 权限类型
		public static enum CouponPoStatus {
			待用, 作废, 已用 ,过期
		}
	
	
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 优惠券名字
	 */
	private String couponName;
	/**
	 * 优惠券金额
	 */
	private BigDecimal balance;
	/**
	 * 用户平台帐号id
	 */
	private Integer customerId;
	
	/*
	 * 商家平台id
	 */
	private Integer merchantId;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 优惠券编码
	 */
	private String couponNo ="";
	/**
	 * 优惠券状态 优惠卷状态（待用（默认）、作废、已用）
	 */
	private String status = "待用";
	
	
	/**
	 * 有效期限
	 */
	private Date toDate;
	
	/**
	 * 有效期字符串,用于转换字符串为时间
	 * 
	 */
	
	private String toDateStr;
	
	/**
	 * 创建人
	 * 
	 */
	private String createdUser;
	
	
	/**
	 * 优惠券类型
	 */
	private Integer couponType;
	
	/**
	 * 描述
	 */
	private String describe;
	
	
	
	
	
	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getToDateStr() {
		return toDateStr;
	}

	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
		if (toDateStr != null && !toDateStr.equals("")) {
			this.toDate = MyDateUtil.HfStrToDate(toDateStr);
		}
	}
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public CouponPo() {

	}
	
	public CouponPo(ResultSet rs) throws SQLException {		    
			this.id = rs.getInt("id");
			this.couponName = rs.getString("couponName");
			this.balance = rs.getBigDecimal("balance");
			this.customerId = rs.getInt("customerId");
			this.merchantId = rs.getInt("merchantId");
			this.createdTime = rs.getDate("createdTime");
			this.couponNo = rs.getString("couponNo");
			this.status = rs.getString("status");
			this.toDate = rs.getDate("toDate");
			this.createdUser = rs.getString("createdUser");
			this.describe = rs.getString("describe");
			this.couponType = rs.getInt("couponType");
	}

	@Override
	public String toString() {
		return "CouponPo [id=" + id + ", couponName=" + couponName
				+ ", balance=" + balance + ", customerId=" + customerId
				+ ", merchantId=" + merchantId + ", createdTime=" + createdTime
				+ ", couponNo=" + couponNo + ", status=" + status + ", toDate="
				+ toDate + "]";
	}

	
	


	
	

}
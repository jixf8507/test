package com.qx.eakay.model.order;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 租赁用车违章记录
 * 
 * @author Administrator
 * 
 */

/**
 * @author Administrator
 *
 */
public class OrderPeccancyRecordPO {

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 租赁订单ID
	 */
	private Integer orderId;

	private String peccancyTime;
	/**
	 * 违规地点.
	 */
	private String address;
	/**
	 * 违规描述.
	 */
	private String info = "";
	/**
	 * 违规照片.
	 */
	private String img = "";
	/**
	 * 违规费用.
	 */
	private BigDecimal payCost;
	/**
	 * 支付状态.
	 */
	private String payStatus;
	/**
	 * 检查人.
	 */
	private String checkMan;
	/**
	 * 生成时间.
	 */
	private Date createdTime;
	private String phone;
	private Integer merchantId;
	private String name;
	private String carNumber;
	private Timestamp schemingGetTime;
	private Timestamp schemingReturnTime;

	public OrderPeccancyRecordPO() {
	}

	public OrderPeccancyRecordPO(ResultSet rs) throws SQLException {

	}

	@Override
	public String toString() {
		return "OrderPeccancyRecord [id=" + id + ", orderId=" + orderId
				+ ", address=" + address + ", info=" + info + ", img=" + img
				+ ", payCost=" + payCost + ", payStatus=" + payStatus
				+ ", checkMan=" + checkMan + ", createdTime=" + createdTime
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getPeccancyTime() {
		return peccancyTime;
	}

	public void setPeccancyTime(String peccancyTime) {
		this.peccancyTime = peccancyTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public BigDecimal getPayCost() {
		return payCost;
	}

	public void setPayCost(BigDecimal payCost) {
		this.payCost = payCost;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Timestamp getSchemingGetTime() {
		return schemingGetTime;
	}

	public void setSchemingGetTime(Timestamp schemingGetTime) {
		this.schemingGetTime = schemingGetTime;
	}

	public Timestamp getSchemingReturnTime() {
		return schemingReturnTime;
	}

	public void setSchemingReturnTime(Timestamp schemingReturnTime) {
		this.schemingReturnTime = schemingReturnTime;
	}



}

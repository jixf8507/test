package com.qx.eakay.model.merchant;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantDrawMoneyPO {

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 退款金额
	 */
	private BigDecimal money;
	/**
	 * 提款状态：（申请中，处理中，不予提款，提款成功）。
	 */
	private String status = "申请中";
	/**
	 * 提款商家ID
	 */
	private Integer merchantId;
	/**
	 * 审核人。
	 */
	private String checkMan = "";
	/**
	 * 审核备注
	 */
	private String checkRemarks = "";
	/**
	 * 提款备注说明。
	 */
	private String remarks;
	/**
	 * 申请时间。
	 */
	private Date createdTime;
	/**
	 * 更新时间
	 */
	private Date updatedTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public String getCheckRemarks() {
		return checkRemarks;
	}

	public void setCheckRemarks(String checkRemarks) {
		this.checkRemarks = checkRemarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

}

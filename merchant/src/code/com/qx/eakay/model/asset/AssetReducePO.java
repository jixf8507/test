package com.qx.eakay.model.asset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 * 资产减少
 * 
 * @author Administrator
 *
 */
public class AssetReducePO {
	private Integer id;
	private Integer merchantId;
	private String reduceDate;
	private String reduceReason;
	private String reduceStatus;
	private String applyUser;
	private String transactUser;
	private String remarks;
	private Timestamp createdTime;
	
	
	public AssetReducePO() {
		
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

	public String getReduceDate() {
		return reduceDate;
	}

	public void setReduceDate(String reduceDate) {
		this.reduceDate = reduceDate;
	}

	public String getReduceReason() {
		return reduceReason;
	}

	public void setReduceReason(String reduceReason) {
		this.reduceReason = reduceReason;
	}


	public String getReduceStatus() {
		return reduceStatus;
	}

	public void setReduceStatus(String reduceStatus) {
		this.reduceStatus = reduceStatus;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public String getTransactUser() {
		return transactUser;
	}

	public void setTransactUser(String transactUser) {
		this.transactUser = transactUser;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	
	public AssetReducePO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.merchantId = rs.getInt("merchantId");
		this.reduceDate = rs.getString("reduceDate");
		this.reduceReason = rs.getString("reduceReason");
		this.reduceStatus = rs.getString("reduceStatus");
		this.applyUser = rs.getString("applyUser");
		this.transactUser = rs.getString("transactUser");
		this.remarks = rs.getString("remarks");
		this.createdTime = rs.getTimestamp("createdTime");
		
	}

}

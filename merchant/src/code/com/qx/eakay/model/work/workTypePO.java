package com.qx.eakay.model.work;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.qx.eakay.model.merchant.MerchantUserPO;

public class workTypePO {
	private Integer id;
	private String typeName;
	private String workDes;
	private Integer userId;
	private Timestamp createdTime;
	private MerchantUserPO merchantUserPO = new MerchantUserPO();

	public MerchantUserPO getMerchantUserPO() {
		return merchantUserPO;
	}

	public void setMerchantUserPO(MerchantUserPO merchantUserPO) {
		this.merchantUserPO = merchantUserPO;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getWorkDes() {
		return workDes;
	}

	public void setWorkDes(String workDes) {
		this.workDes = workDes;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public workTypePO() {
	}

	public workTypePO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.typeName = rs.getString("typeName");
		this.userId = rs.getInt("userId");
		this.workDes = rs.getString("workDes");
		this.createdTime = rs.getTimestamp(("createdTime"));
		this.merchantUserPO = new MerchantUserPO();
		this.merchantUserPO.setUserName(rs.getString("userName"));
		this.merchantUserPO.setMobilePhone(rs.getString("mobilePhone"));

	}

}

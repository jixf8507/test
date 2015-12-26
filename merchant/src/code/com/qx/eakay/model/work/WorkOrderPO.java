package com.qx.eakay.model.work;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.qx.common.tools.MyDateUtil;
import com.qx.eakay.model.merchant.MerchantUserPO;

/**
 * 工单
 * 
 * @author Administrator
 * 
 */
public class WorkOrderPO {
	private Integer id;
	private Integer userId;
	private String workName;
	private String typeName;
	private String workStatus;
	private String urgency;
	private Timestamp createdTime;
	private String transactUser;
	private Timestamp updatedTime;
	private String updated;
	private String created;
	private String workDes;
	private String transactDes;

	public static enum workStatus {
		待处理, 处理中, 已处理
	}

	private MerchantUserPO merchantUserPO = new MerchantUserPO();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getTransactUser() {
		return transactUser;
	}

	public void setTransactUser(String transactUser) {
		this.transactUser = transactUser;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getWorkDes() {
		return workDes;
	}

	public void setWorkDes(String workDes) {
		this.workDes = workDes;
	}

	public String getTransactDes() {
		return transactDes;
	}

	public void setTransactDes(String transactDes) {
		this.transactDes = transactDes;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
		if (updated != null && !updated.equals("")) {
			this.updatedTime = new Timestamp(MyDateUtil.HfStrToDate(updated)
					.getTime());
		}else{
			this.updatedTime = new Timestamp(new Date().getTime());
		}
	}
	
	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
		if (created != null && !created.equals("")) {
			this.createdTime = new Timestamp(MyDateUtil.HfStrToDate(created)
					.getTime());
		}else{
			this.createdTime = new Timestamp(new Date().getTime());
		}
	}

	public MerchantUserPO getMerchantUserPO() {
		return merchantUserPO;
	}

	public void setMerchantUserPO(MerchantUserPO merchantUserPO) {
		this.merchantUserPO = merchantUserPO;
	}
	
	public WorkOrderPO() { }

	public WorkOrderPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.userId = rs.getInt("userId");
		this.workName = rs.getString("workName");
		this.typeName = rs.getString("typeName");
		this.workStatus = rs.getString("workStatus");
		this.urgency = rs.getString("urgency");
		this.createdTime = rs.getTimestamp("createdTime");
		this.transactUser = rs.getString("transactUser");
		this.updatedTime = rs.getTimestamp("updatedTime");
		this.workDes = rs.getString("workDes");
		this.transactDes = rs.getString("transactDes");
		// 员工信息
		this.merchantUserPO = new MerchantUserPO();
		this.merchantUserPO.setUserName(rs.getString("userName"));
		this.merchantUserPO.setMobilePhone(rs.getString("mobilePhone"));

	}

}

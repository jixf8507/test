package com.qx.eakay.model.merchant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 员工签到
 * 
 * @author Administrator
 *
 */
public class MerchantUserSignPO {

	private Integer id;
	private Integer userId;
	private Integer siteId;
	private Timestamp inTime;
	private Timestamp outTime;
	private String inDescribe;
	private String outDescribe;
	private String singDate;
	private String status;
	private Integer merchantId;
	private Integer outSiteId;
	private float inLat;
	private float inLng;
	private float outLat;
	private float outLng;
	
	private String siteName;
	private String outSiteName;
	
	public MerchantUserSignPO() {
		
	}

	/**
	 * 将数据库查询结果封装成商家用户对象
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public MerchantUserSignPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.userId = rs.getInt("userId");
		this.siteId = rs.getInt("siteId");
		this.siteId = rs.getInt("siteId");
		this.inTime = rs.getTimestamp("inTime");
		this.outTime = rs.getTimestamp("outTime");
		this.inDescribe = rs.getString("inDescribe");
		this.outDescribe = rs.getString("outDescribe");
		this.singDate = rs.getString("singDate");
		this.status = rs.getString("status");
		this.merchantId = rs.getInt("merchantId");
		this.outSiteId = rs.getInt("outSiteId");
		this.inLat = rs.getFloat("inLat");
		this.inLng = rs.getFloat("inLng");
		this.outLat = rs.getFloat("outLat");
		this.outLng = rs.getFloat("outLng");
		this.siteName = rs.getString("siteName");
		this.outSiteName = rs.getString("outSiteName");
	}

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

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Timestamp getInTime() {
		return inTime;
	}

	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}

	public Timestamp getOutTime() {
		return outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}

	public String getInDescribe() {
		return inDescribe;
	}

	public void setInDescribe(String inDescribe) {
		this.inDescribe = inDescribe;
	}

	public String getOutDescribe() {
		return outDescribe;
	}

	public void setOutDescribe(String outDescribe) {
		this.outDescribe = outDescribe;
	}

	public String getSingDate() {
		return singDate;
	}

	public void setSingDate(String singDate) {
		this.singDate = singDate;
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

	public Integer getOutSiteId() {
		return outSiteId;
	}

	public void setOutSiteId(Integer outSiteId) {
		this.outSiteId = outSiteId;
	}

	public float getInLat() {
		return inLat;
	}

	public void setInLat(float inLat) {
		this.inLat = inLat;
	}

	public float getInLng() {
		return inLng;
	}

	public void setInLng(float inLng) {
		this.inLng = inLng;
	}

	public float getOutLat() {
		return outLat;
	}

	public void setOutLat(float outLat) {
		this.outLat = outLat;
	}

	public float getOutLng() {
		return outLng;
	}

	public void setOutLng(float outLng) {
		this.outLng = outLng;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getOutSiteName() {
		return outSiteName;
	}

	public void setOutSiteName(String outSiteName) {
		this.outSiteName = outSiteName;
	}


}

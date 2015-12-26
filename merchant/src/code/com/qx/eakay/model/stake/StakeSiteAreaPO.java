package com.qx.eakay.model.stake;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StakeSiteAreaPO {

	private Integer id;
	private String siteCode;
	private String areaCode;
	private String areaName;
	private String stakeNum;
	private String useStatus;
	private String flag;

	public StakeSiteAreaPO() {

	}

	public StakeSiteAreaPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.siteCode = rs.getString("site_code");
		this.areaCode = rs.getString("area_code");
		this.areaName = rs.getString("area_name");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getStakeNum() {
		return stakeNum;
	}

	public void setStakeNum(String stakeNum) {
		this.stakeNum = stakeNum;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}

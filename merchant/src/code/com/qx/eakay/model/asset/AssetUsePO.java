package com.qx.eakay.model.asset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 资产领用
 * 
 * @author Administrator
 *
 */
public class AssetUsePO {
	private Integer id;
	private String useDate;
	private Integer siteId;
	private Integer userId;
	private Integer merchantId;
	private Timestamp createdTime;
	private String transactUser;
	private String flag;
	private String remarks;
	
	private String userName;
	private String siteName;
	
	//资产领用详细
	public AssetUseDetailPO useDetailPO = new AssetUseDetailPO();
	
	
	private List<AssetUseDetailPO> useDetailPOs;
	
	public List<AssetUseDetailPO> getUseDetailPOs() {
		return useDetailPOs;
	}

	public void setUseDetailPOs(List<AssetUseDetailPO> useDetailPOs) {
		this.useDetailPOs = useDetailPOs;
	}

	public AssetUsePO() {
		useDetailPOs = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public AssetUseDetailPO getUseDetailPO() {
		return useDetailPO;
	}

	public void setUseDetailPO(AssetUseDetailPO useDetailPO) {
		this.useDetailPO = useDetailPO;
	}
	
	public AssetUsePO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.useDate = rs.getString("useDate");
		this.siteId = rs.getInt("siteId");
		this.userId = rs.getInt("userId");
		this.merchantId = rs.getInt("merchantId");
		this.createdTime = rs.getTimestamp("createdTime");
		this.transactUser = rs.getString("transactUser");
		this.flag = rs.getString("flag");
		this.remarks = rs.getString("remarks");
		this.userName = rs.getString("userName");
		this.siteName = rs.getString("siteName");
		
		//资产领用详细
//		this.useDetailPO = new AssetUseDetailPO();
//		this.useDetailPO.setId(rs.getInt("id"));
//		this.useDetailPO.setUseId(rs.getInt("useId"));
//		this.useDetailPO.setAssetsId(rs.getInt("assetsId"));
	}
}

package com.qx.eakay.model.asset;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.qx.eakay.model.car.CarManufacturerPO;

/**
 * 资产
 * 
 * @author Administrator
 *
 */
public class AssetPO {
	
	public static enum AssetFlag {
		闲置, 在用, 在修, 销毁, 出售
	}
	
	private Integer id;
	private Integer categoryId;
	private String assetsName;
	private Integer siteId;
	private Integer userId;
	private Integer merchantId;
	private String model;
	private String increasingMode;
	private String unit;
	private String purchaseDate;
	private Timestamp createdTime;
	private Integer manufacturId;
	private String transactUser;
	private BigDecimal fee;
	private String flag;
	
	//供应商
	public CarManufacturerPO manufacturerPO = new CarManufacturerPO();
	
	public AssetPO() {
		
	}
	
	//种类名称
	private String name;
	//站点
	private String siteName;
	//员工姓名
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getIncreasingMode() {
		return increasingMode;
	}

	public void setIncreasingMode(String increasingMode) {
		this.increasingMode = increasingMode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getManufacturId() {
		return manufacturId;
	}

	public void setManufacturId(Integer manufacturId) {
		this.manufacturId = manufacturId;
	}

	public String getTransactUser() {
		return transactUser;
	}

	public void setTransactUser(String transactUser) {
		this.transactUser = transactUser;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public CarManufacturerPO getManufacturerPO() {
		return manufacturerPO;
	}

	public void setManufacturerPO(CarManufacturerPO manufacturerPO) {
		this.manufacturerPO = manufacturerPO;
	}
	

	public AssetPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.categoryId = rs.getInt("categoryId");
		this.assetsName = rs.getString("assetsName");
		this.siteId = rs.getInt("siteId");
		this.userId = rs.getInt("userId");
		this.merchantId = rs.getInt("merchantId");
		this.model = rs.getString("model");
		this.increasingMode = rs.getString("increasingMode");
		this.unit = rs.getString("unit");
		this.purchaseDate = rs.getString("purchaseDate");
		this.createdTime = rs.getTimestamp("createdTime");
		this.manufacturId = rs.getInt("manufacturId");
		this.transactUser = rs.getString("transactUser");
		this.fee = rs.getBigDecimal("fee");
		this.flag = rs.getString("flag");
		this.name = rs.getString("name");
		this.siteName = rs.getString("siteName");
		this.userName = rs.getString("userName");
		
		// 供应商信息
		this.manufacturerPO = new CarManufacturerPO();
		this.manufacturerPO.setManufacturerName(rs.getString("manufacturerName"));
		this.manufacturerPO.setFullName(rs.getString("fullName"));
		this.manufacturerPO.setAddress(rs.getString("address"));
		this.manufacturerPO.setLinkman(rs.getString("linkman"));
		this.manufacturerPO.setEmail(rs.getString("email"));
	}
}

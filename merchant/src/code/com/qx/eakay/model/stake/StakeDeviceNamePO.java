package com.qx.eakay.model.stake;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 充电设备对象
 * 
 * @author jixf
 * @2014-12-5
 */

public class StakeDeviceNamePO {

	private Integer id;
	private String siteCode = "";
	private String areaCode = "";
	private String regionA = "";
	private String regionB = "";
	private String deviceNo = "";
	private String deviceTypeName = "";
	private String deviceKind = "交";
	private Integer deviceType = 1;
	private String chargerType = "";
	private String spec = "11";
	private Integer deviceNum = 1;
	private String factoryNo = "1";
	private String manufacturer = "";
	private Date startTime = new Date();
	private String canAddress;
	private String ipAddress;
	private String safetyLevle = "11";
	private Float fixedVoltage = 11f;
	private String deviceTypeNo = "1";
	private Float devicePower = 11f;
	private Integer serverPort = 53497;
	private Integer clientPort = 6001;
	private String status = "正常";
	private BigDecimal deviceVersion;
	private String chargePort = "旗翔1托2";
	private String flag = "正常";
	private String chargeStatus;
	private int merchantId;
	private float lng = 0f;
	private float lat = 0f;
	private String address;
	private String imgUrl;
	private String isAllowParking;
	private String parkType;
	private Integer priceid;
	private String groupNo;
	private Integer stakeNum;

	private String siteName;
	private String area_name;
	private String nameplate;

	public String getNameplate() {
		return nameplate;
	}

	public void setNameplate(String nameplate) {
		this.nameplate = nameplate;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getParkType() {
		return parkType;
	}

	public void setParkType(String parkType) {
		this.parkType = parkType;
	}

	public StakeDeviceNamePO() {

	}

	public StakeDeviceNamePO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.siteCode = rs.getString("site_code");
		this.areaCode = rs.getString("area_code");
		this.groupNo = rs.getString("groupNo");
		this.factoryNo = rs.getString("factoryNo");
		this.deviceTypeName = rs.getString("deviceTypeName");
		this.manufacturer = rs.getString("manufacturer");
		this.deviceKind = rs.getString("deviceKind");
		this.deviceType = rs.getInt("deviceType");
		this.chargerType = rs.getString("chargerType");
		this.spec = rs.getString("spec");
		this.deviceNum = rs.getInt("deviceNum");
		this.deviceNo = rs.getString("deviceNo");
		this.canAddress = rs.getString("canAddress");
		this.safetyLevle = rs.getString("safetyLevle");
		this.address = rs.getString("address");
		this.imgUrl = rs.getString("imgUrl");
		this.lat = rs.getFloat("lat");
		this.lng = rs.getFloat("lng");
		this.imgUrl = rs.getString("imgUrl");
		this.chargePort = rs.getString("chargePort");
		this.isAllowParking = rs.getString("isAllowParking");
		this.parkType = rs.getString("parkType");
		this.priceid = rs.getInt("priceid");
		this.status = rs.getString("status");
		this.devicePower = rs.getFloat("devicePower");
		this.fixedVoltage = rs.getFloat("fixedVoltage");
		this.deviceTypeNo = rs.getString("deviceTypeNo");
		this.siteName = rs.getString("siteName");
		this.area_name = rs.getString("area_name");
		this.nameplate = rs.getString("nameplate");
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

	public String getRegionA() {
		return regionA;
	}

	public void setRegionA(String regionA) {
		this.regionA = regionA;
	}

	public String getRegionB() {
		return regionB;
	}

	public void setRegionB(String regionB) {
		this.regionB = regionB;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public String getDeviceKind() {
		return deviceKind;
	}

	public void setDeviceKind(String deviceKind) {
		this.deviceKind = deviceKind;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getChargerType() {
		return chargerType;
	}

	public void setChargerType(String chargerType) {
		this.chargerType = chargerType;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(Integer deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getFactoryNo() {
		return factoryNo;
	}

	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getCanAddress() {
		return canAddress;
	}

	public void setCanAddress(String canAddress) {
		this.canAddress = canAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSafetyLevle() {
		return safetyLevle;
	}

	public void setSafetyLevle(String safetyLevle) {
		this.safetyLevle = safetyLevle;
	}

	public Float getFixedVoltage() {
		return fixedVoltage;
	}

	public void setFixedVoltage(Float fixedVoltage) {
		this.fixedVoltage = fixedVoltage;
	}

	public String getDeviceTypeNo() {
		return deviceTypeNo;
	}

	public void setDeviceTypeNo(String deviceTypeNo) {
		this.deviceTypeNo = deviceTypeNo;
	}

	public Float getDevicePower() {
		return devicePower;
	}

	public void setDevicePower(Float devicePower) {
		this.devicePower = devicePower;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public Integer getClientPort() {
		return clientPort;
	}

	public void setClientPort(Integer clientPort) {
		this.clientPort = clientPort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(BigDecimal deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getChargePort() {
		return chargePort;
	}

	public void setChargePort(String chargePort) {
		this.chargePort = chargePort;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getIsAllowParking() {
		return isAllowParking;
	}

	public void setIsAllowParking(String isAllowParking) {
		this.isAllowParking = isAllowParking;
	}

	public Integer getPriceid() {
		return priceid;
	}

	public void setPriceid(Integer priceid) {
		this.priceid = priceid;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public Integer getStakeNum() {
		return stakeNum;
	}

	public void setStakeNum(Integer stakeNum) {
		this.stakeNum = stakeNum;
	}

}

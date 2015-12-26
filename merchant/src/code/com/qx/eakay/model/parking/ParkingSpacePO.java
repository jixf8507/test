package com.qx.eakay.model.parking;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingSpacePO {

	private Integer id;
	private Integer parkingId;
	private String spaceNO;
	private String chargeNo;
	private String area;
	private Float lng = 0f;
	private Float lat = 0f;
	private String address;
	private String electric = "16/32";
	private String isCharge;
	private String isLock;
	private String isFree;
	private String status;
	private String spaceStatus;

	private String factoryNo;
	private String position;
	private String position1;
	private String grooveNo;
	private String parkType;
	private String deviceTypeNo;
	private String siteName;
	private String deviceNo;
	private String nameplate;

	public String getParkType() {
		return parkType;
	}

	public void setParkType(String parkType) {
		this.parkType = parkType;
	}

	public ParkingSpacePO() {
	}

	public ParkingSpacePO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.parkingId = rs.getInt("parkingId");
		this.spaceNO = rs.getString("spaceNO");
		this.area = rs.getString("area");
		this.lat = rs.getFloat("lat");
		this.lng = rs.getFloat("lng");
		this.address = rs.getString("address");
		this.electric = rs.getString("electric");
		this.isCharge = rs.getString("isCharge");
		this.status = rs.getString("status");
		this.factoryNo = rs.getString("factoryNo");
		this.position = rs.getString("position");
		this.grooveNo = rs.getString("grooveNo");
		this.parkType = rs.getString("parkType");
		this.siteName = rs.getString("siteName");
		this.deviceNo = rs.getString("deviceNo");
		this.nameplate = rs.getString("nameplate");
		this.deviceTypeNo = rs.getString("deviceTypeNo");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public String getSpaceNO() {
		return spaceNO;
	}

	public void setSpaceNO(String spaceNO) {
		this.spaceNO = spaceNO;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getElectric() {
		return electric;
	}

	public void setElectric(String electric) {
		this.electric = electric;
	}

	public String getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public String getFactoryNo() {
		return factoryNo;
	}

	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGrooveNo() {
		return grooveNo;
	}

	public void setGrooveNo(String grooveNo) {
		this.grooveNo = grooveNo;
	}

	public String getChargeNo() {
		return chargeNo;
	}

	public void setChargeNo(String chargeNo) {
		this.chargeNo = chargeNo;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

	public String getSpaceStatus() {
		return spaceStatus;
	}

	public void setSpaceStatus(String spaceStatus) {
		this.spaceStatus = spaceStatus;
	}

	public String getDeviceTypeNo() {
		return deviceTypeNo;
	}

	public void setDeviceTypeNo(String deviceTypeNo) {
		this.deviceTypeNo = deviceTypeNo;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getNameplate() {
		return nameplate;
	}

	public void setNameplate(String nameplate) {
		this.nameplate = nameplate;
	}

	public String getPosition1() {
		return position1;
	}

	public void setPosition1(String position1) {
		this.position1 = position1;
	}

}

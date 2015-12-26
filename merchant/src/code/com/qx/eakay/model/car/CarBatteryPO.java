package com.qx.eakay.model.car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CarBatteryPO {

	private Integer id;
	private Integer carId;
	private String vin;
	private String batteryType;
	private Float maxVoltag = 0f;
	private Float maxCurrent = 0f;
	private Float maxTemp = 0f;
	private Float minVoltage = 0f;
	private Float minCurrent = 0f;
	private Float minTemp = 0f;
	private Integer merchantId;
	private Timestamp createdTime;

	private CarPO carPO;

	public CarBatteryPO() {

	}

	public CarBatteryPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.carId = rs.getInt("carId");
		this.vin = rs.getString("vin");
		this.batteryType = rs.getString("batteryType");
		this.maxTemp = rs.getFloat("maxTemp");
		this.maxVoltag = rs.getFloat("maxVoltag");
		this.maxCurrent = rs.getFloat("maxCurrent");
		this.minVoltage = rs.getFloat("minVoltage");
		this.minCurrent = rs.getFloat("minCurrent");
		this.minTemp = rs.getFloat("minTemp");
		this.merchantId = rs.getInt("merchantId");
		// 车辆信息
		this.carPO = new CarPO();
		this.carPO.setCarNumber(rs.getString("carNumber"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBatteryType() {
		return batteryType;
	}

	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	public Float getMaxVoltag() {
		return maxVoltag;
	}

	public void setMaxVoltag(Float maxVoltag) {
		this.maxVoltag = maxVoltag;
	}

	public Float getMaxCurrent() {
		return maxCurrent;
	}

	public void setMaxCurrent(Float maxCurrent) {
		this.maxCurrent = maxCurrent;
	}

	public Float getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(Float maxTemp) {
		this.maxTemp = maxTemp;
	}

	public Float getMinVoltage() {
		return minVoltage;
	}

	public void setMinVoltage(Float minVoltage) {
		this.minVoltage = minVoltage;
	}

	public Float getMinCurrent() {
		return minCurrent;
	}

	public void setMinCurrent(Float minCurrent) {
		this.minCurrent = minCurrent;
	}

	public Float getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(Float minTemp) {
		this.minTemp = minTemp;
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

	public CarPO getCarPO() {
		return carPO;
	}

	public void setCarPO(CarPO carPO) {
		this.carPO = carPO;
	}

}

package com.qx.eakay.model.monitor;

import java.util.Map;

public class CarModel {

	private Integer id;
	private String carid;
	private String carModel;
	private String nowdistance;
	private String wgStatus;
	private String zcStatus;
	private String deviceNO;
	private String soc;
	private String remainingChargeTimes;
	/**
	 * 当前码表公里数
	 */
	private String curkms;
	/**
	 * 续航里程
	 */
	private String surplusKms;  

	public String getCurkms() {
		return curkms;
	}

	public void setCurkms(String curkms) {
		this.curkms = curkms;
	}

	public String getSurplusKms() {
		return surplusKms;
	}

	public void setSurplusKms(String surplusKms) {
		this.surplusKms = surplusKms;
	}

	public CarModel() {
		super();
	}

	public CarModel(Map<String, Object> device) {
		super();
		this.id = Integer.parseInt(device.get("ID") + "");
		this.carid = device.get("Carid") + "";
		this.carModel = device.get("CarModel") + "";
		this.nowdistance = device.get("Nowdistance") + "";
		this.wgStatus = device.get("WgStatus") + "";
		this.zcStatus = device.get("ZcStatus") + "";
		this.deviceNO = device.get("DeviceNO") + "";
		this.remainingChargeTimes = device.get("RemainingChargeTimes") + "";
		this.soc = device.get("SOC") + "";
		this.curkms=device.get("Curkms")+"";
		this.surplusKms=device.get("SurplusKms")+"";

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getNowdistance() {
		return nowdistance;
	}

	public void setNowdistance(String nowdistance) {
		this.nowdistance = nowdistance;
	}

	public String getWgStatus() {
		return wgStatus;
	}

	public void setWgStatus(String wgStatus) {
		this.wgStatus = wgStatus;
	}

	public String getZcStatus() {
		return zcStatus;
	}

	public void setZcStatus(String zcStatus) {
		this.zcStatus = zcStatus;
	}

	public String getDeviceNO() {
		return deviceNO;
	}

	public void setDeviceNO(String deviceNO) {
		this.deviceNO = deviceNO;
	}

	public String getSoc() {
		return soc;
	}

	public void setSoc(String soc) {
		this.soc = soc;
	}

	public String getRemainingChargeTimes() {
		return remainingChargeTimes;
	}

	public void setRemainingChargeTimes(String remainingChargeTimes) {
		this.remainingChargeTimes = remainingChargeTimes;
	}

	@Override
	public String toString() {
		return "CarModel [id=" + id + ", carid=" + carid + ", carModel="
				+ carModel + ", nowdistance=" + nowdistance + ", wgStatus="
				+ wgStatus + ", zcStatus=" + zcStatus + ", deviceNO="
				+ deviceNO + ", soc=" + soc + ", remainingChargeTimes="
				+ remainingChargeTimes + ", curkms=" + curkms + ", surplusKms="
				+ surplusKms + "]";
	}

	/*@Override
	public String toString() {
		return "CarModel [carModel=" + carModel + ", carid=" + carid
				+ ", deviceNO=" + deviceNO + ", id=" + id + ", nowdistance="
				+ nowdistance + ", wgStatus=" + wgStatus + ", zcStatus="
				+ zcStatus + "]";
	}*/

}

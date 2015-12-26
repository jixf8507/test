package com.qx.eakay.model.monitor;

public class BaseModel {
	private String Order;
	private String DeviceNO;
	private String CarId;

	private Integer orRecordId;

	public BaseModel() {
		this.Order = "";
		this.DeviceNO = "";
		this.CarId = "";
	}

	public String getOrder() {
		return Order;
	}

	public void setOrder(String order) {
		Order = order;
	}

	public String getDeviceNO() {
		return DeviceNO;
	}

	public void setDeviceNO(String deviceNO) {
		DeviceNO = deviceNO;
	}

	public String getCarId() {
		return CarId;
	}

	public void setCarId(String carId) {
		CarId = carId;
	}

	public Integer getOrRecordId() {
		return orRecordId;
	}

	public void setOrRecordId(Integer orRecordId) {
		this.orRecordId = orRecordId;
	}

}

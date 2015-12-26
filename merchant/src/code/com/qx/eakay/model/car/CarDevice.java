package com.qx.eakay.model.car;


public class CarDevice {

	private Integer id;
	private String deviceNO;
	private String status;
	private float surplusKms;
	private float curKms;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeviceNO() {
		return deviceNO;
	}
	public void setDeviceNO(String deviceNO) {
		this.deviceNO = deviceNO;
	}
	public float getSurplusKms() {
		return surplusKms;
	}
	public void setSurplusKms(float surplusKms) {
		this.surplusKms = surplusKms;
	}
	public float getCurKms() {
		return curKms;
	}
	public void setCurKms(float curKms) {
		this.curKms = curKms;
	}


}

package com.qx.eakay.model.car;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 车载设备卡
 * 
 * @author sdf
 * @date 2015年10月31日
 */
public class SimPO {

	private Integer id;
	private String simCard;
	private String facilitator;
	private String flowOfMonth;
	private Integer deviceId;
	private String status;
	private Integer merchantId;
	private String iccid;

	private CarDevice carDevice;

	public SimPO() {

	}

	public SimPO(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		simCard = rs.getString("simCard");
		facilitator = rs.getString("facilitator");
		flowOfMonth = rs.getString("flowOfMonth");
		deviceId = rs.getInt("deviceId");
		status = rs.getString("status");
		merchantId = rs.getInt("merchantId");
		iccid = rs.getString("iccid");
		
		// 当前车载终端信息
		this.carDevice = new CarDevice();
		this.carDevice.setDeviceNO(rs.getString("deviceNO"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSimCard() {
		return simCard;
	}

	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}

	public String getFacilitator() {
		return facilitator;
	}

	public void setFacilitator(String facilitator) {
		this.facilitator = facilitator;
	}

	public String getFlowOfMonth() {
		return flowOfMonth;
	}

	public void setFlowOfMonth(String flowOfMonth) {
		this.flowOfMonth = flowOfMonth;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
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

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public CarDevice getCarDevice() {
		return carDevice;
	}

	public void setCarDevice(CarDevice carDevice) {
		this.carDevice = carDevice;
	}

}

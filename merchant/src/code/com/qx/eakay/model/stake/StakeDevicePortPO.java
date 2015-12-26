package com.qx.eakay.model.stake;


/**
 * 充电设备端口对象
 * 
 * @author jixf
 * @2014-12-8
 */

public class StakeDevicePortPO {

	// 主键
	private Integer id;
	// 端口名称
	private String name;
	// 所在设备编号
	// 端口所在的位置
	private String pos;
	// 工作电压
	private String currents;
	private String status;
	private String grooveNo;
	private String factoryNo;
	private String portorder;
	private String memo;

	public StakeDevicePortPO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getCurrents() {
		return currents;
	}

	public void setCurrents(String currents) {
		this.currents = currents;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGrooveNo() {
		return grooveNo;
	}

	public void setGrooveNo(String grooveNo) {
		this.grooveNo = grooveNo;
	}

	public String getFactoryNo() {
		return factoryNo;
	}

	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}

	public String getPortorder() {
		return portorder;
	}

	public void setPortorder(String portorder) {
		this.portorder = portorder;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}

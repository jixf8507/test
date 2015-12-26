package com.qx.eakay.model.car;

import com.qx.common.model.BasePO;

/**
 * 
 * @author jixf
 * @date 2015年7月14日
 */
public class CarDetailPO extends BasePO {

	private Integer id;
	private Integer carId;
	private String component;
	private String status;
	private String describe;

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

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

}

package com.qx.eakay.model.price;

import java.sql.Timestamp;

public class CarPriceTypeRelationPO {

	private Integer id;
	private Integer priceTypeId;
	private Integer carId;
	private Timestamp createdTime;
	private String priceTypeTableName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPriceTypeId() {
		return priceTypeId;
	}

	public void setPriceTypeId(Integer priceTypeId) {
		this.priceTypeId = priceTypeId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getPriceTypeTableName() {
		return priceTypeTableName;
	}

	public void setPriceTypeTableName(String priceTypeTableName) {
		this.priceTypeTableName = priceTypeTableName;
	}

}

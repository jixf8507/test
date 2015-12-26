package com.qx.eakay.model.car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author jixf
 * @date 2015年7月1日
 */
public class CarTypePO {

	private Integer id;
	private String typeName;
	private Integer carManufacturerId;
	private Integer seatCount;
	private String color;
	private String energy;

	private List<CarTypeDetailPO> detailPOs;

	public CarTypePO() {
		detailPOs = new ArrayList<>();
	}

	public CarTypePO(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		carManufacturerId = rs.getInt("carManufacturerId");
		color = rs.getString("color");
		energy = rs.getString("energy");
		seatCount = rs.getInt("seatCount");
		typeName = rs.getString("typeName");

		detailPOs = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getCarManufacturerId() {
		return carManufacturerId;
	}

	public void setCarManufacturerId(Integer carManufacturerId) {
		this.carManufacturerId = carManufacturerId;
	}

	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String energy) {
		this.energy = energy;
	}

	public List<CarTypeDetailPO> getDetailPOs() {
		return detailPOs;
	}

	public void setDetailPOs(List<CarTypeDetailPO> detailPOs) {
		this.detailPOs = detailPOs;
	}

}

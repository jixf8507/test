package com.qx.eakay.model.car;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 车辆生产厂商
 * 
 * @author jixf
 * @date 2015年6月30日
 */
public class CarManufacturerPO {

	private Integer id;
	/**
	 * 制造商品牌名称.
	 */
	private String manufacturerName;
	/**
	 * 制造商全称.
	 */
	private String fullName;
	/**
	 * 制造商地址.
	 */
	private String address;
	/**
	 * 制造商联系人.
	 */
	private String linkman;
	/**
	 * 制造商联系电话.
	 */
	private String linkphone;
	/**
	 * 制造商email.
	 */
	private String email;
	/**
	 * 商户ID
	 */
	private Integer merchantId;

	public CarManufacturerPO() {

	}

	public CarManufacturerPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.email = rs.getString("email");
		this.address = rs.getString("address");
		this.fullName = rs.getString("fullName");
		this.linkman = rs.getString("linkman");
		this.linkphone = rs.getString("linkphone");
		this.merchantId = rs.getInt("merchantId");
		this.manufacturerName = rs.getString("manufacturerName");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkphone() {
		return linkphone;
	}

	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

}

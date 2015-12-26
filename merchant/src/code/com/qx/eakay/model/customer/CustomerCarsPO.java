package com.qx.eakay.model.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 
 * @author jixf
 * @date 2015年7月22日
 */
public class CustomerCarsPO {

	private Integer id;
	private String carNumber;
	private String carType;
	private String flag;
	private Timestamp createdTime;
	private Integer customerId;
	private String vin;

	private CustomerPO customerPO;

	public CustomerCarsPO() {

	}

	public CustomerCarsPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.carNumber = rs.getString("carNumber");
		this.carType = rs.getString("carType");
		this.customerId = rs.getInt("customerId");
		this.vin = rs.getString("vin");

		this.customerPO = new CustomerPO();
		this.customerPO.setName(rs.getString("name"));
		this.customerPO.setPhone(rs.getString("phone"));
		this.customerPO.setIdCard(rs.getString("idCard"));

		this.customerPO.setAccountPO(new AccountPO());
		this.customerPO.getAccountPO().setCardNO(rs.getString("cardNO"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public CustomerPO getCustomerPO() {
		return customerPO;
	}

	public void setCustomerPO(CustomerPO customerPO) {
		this.customerPO = customerPO;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

}

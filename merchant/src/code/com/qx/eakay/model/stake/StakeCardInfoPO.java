package com.qx.eakay.model.stake;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.qx.eakay.model.customer.CustomerPO;

/**
 * 
 * @author jixf
 * @date 2015年7月25日
 */
public class StakeCardInfoPO {

	private Integer id;
	private Integer merchantId;
	private String cardID;
	private String cardNumber;
	private String password;
	private Timestamp openAccountTime;
	private String idCard;
	private String carId;
	private Integer grayCard;
	private BigDecimal balance;
	private String isAnnul;
	private String siteCode;
	private String isUsing;
	private Timestamp lastCostTIme;
	private String cardType;
	private String flag;
	private String carTableName;
	private Integer customerId;

	public CustomerPO customerPO;

	public StakeCardInfoPO() {

	}

	public StakeCardInfoPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.merchantId = rs.getInt("merchantId");
		this.cardID = rs.getString("cardID");
		this.cardNumber = rs.getString("cardNumber");
		this.openAccountTime = rs.getTimestamp("openAccountTime");
		this.carId = rs.getString("carId");
		this.carTableName = rs.getString("carTableName");
		this.isAnnul = rs.getString("isAnnul");

		this.customerPO = new CustomerPO();
		this.customerPO.setId(rs.getInt("customerId"));
		this.customerPO.setName(rs.getString("name"));
		this.customerPO.setPhone(rs.getString("phone"));

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getOpenAccountTime() {
		return openAccountTime;
	}

	public void setOpenAccountTime(Timestamp openAccountTime) {
		this.openAccountTime = openAccountTime;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Integer getGrayCard() {
		return grayCard;
	}

	public void setGrayCard(Integer grayCard) {
		this.grayCard = grayCard;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getIsAnnul() {
		return isAnnul;
	}

	public void setIsAnnul(String isAnnul) {
		this.isAnnul = isAnnul;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(String isUsing) {
		this.isUsing = isUsing;
	}

	public Timestamp getLastCostTIme() {
		return lastCostTIme;
	}

	public void setLastCostTIme(Timestamp lastCostTIme) {
		this.lastCostTIme = lastCostTIme;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public CustomerPO getCustomerPO() {
		return customerPO;
	}

	public void setCustomerPO(CustomerPO customerPO) {
		this.customerPO = customerPO;
	}

	public String getCarTableName() {
		return carTableName;
	}

	public void setCarTableName(String carTableName) {
		this.carTableName = carTableName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

}

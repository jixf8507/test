package com.qx.eakay.model.stake;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.model.merchant.SitePO;

/**
 * 充电订单表
 * 
 * @author Administrator
 * 
 */
public class StakeRechargeCostPO {

	public static enum OrderStatus {
		预约中, 未结算, 已付费, 已取消
	}

	private Integer id;
	private String exchangeNo;
	private String Kind;
	private String site_code;
	private String factoryNo;
	private String deviceNo;
	private String chargePara;
	private String chargePosition;
	private String cardID;
	private String cardNumber;
	private String customer;
	private Timestamp startCharge;
	private Timestamp lastCharge;
	private BigDecimal chargingTime;
	private BigDecimal parkingTime;
	private BigDecimal EQ;
	private BigDecimal sparking_cost;
	private BigDecimal cost;
	private BigDecimal allCost;
	private BigDecimal oldMoney;
	private BigDecimal newMoney;
	private String chargeMode;
	private BigDecimal modePara;
	private BigDecimal startSOC;
	private BigDecimal endSOC;
	private String CarNo;
	private String VIN;
	private Timestamp recordTime;
	private Timestamp orderStartTime;
	private Timestamp orderEndTime;
	private Integer customerId;
	private String orderStatus;
	private String operateStatus;
	private String sparking_code;
	private Integer merchantId;
	private BigDecimal moneyOfassure;
	private Integer merchantAccountId;
	private String grooveNo;
	private Integer deviceType;

	private SitePO sitePO = new SitePO();
	private CustomerPO customerPO = new CustomerPO();
	private MerchantPO merchantPO = new MerchantPO();

	public StakeRechargeCostPO() {

	}

	public StakeRechargeCostPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.exchangeNo = rs.getString("exchangeNo");
		this.orderStatus = rs.getString("orderStatus");
		this.Kind = rs.getString("Kind");
		this.site_code = rs.getString("site_code");
		this.factoryNo = rs.getString("factoryNo");
		this.deviceNo = rs.getString("deviceNo");
		this.chargePara = rs.getString("chargePara");
		this.chargePosition = rs.getString("chargePosition");
		this.cardID = rs.getString("cardID");
		this.cardNumber = rs.getString("cardNumber");
		this.customer = rs.getString("customer");
		this.startCharge = rs.getTimestamp("startCharge");
		this.lastCharge = rs.getTimestamp("lastCharge");
		this.chargingTime = rs.getBigDecimal("chargingTime");
		this.parkingTime = rs.getBigDecimal("parkingTime");
		this.EQ = rs.getBigDecimal("EQ");
		this.sparking_cost = rs.getBigDecimal("sparking_cost");
		this.cost = rs.getBigDecimal("cost");
		this.allCost = rs.getBigDecimal("allCost");
		this.oldMoney = rs.getBigDecimal("oldMoney");
		this.newMoney = rs.getBigDecimal("newMoney");
		this.chargeMode = rs.getString("chargeMode");
		this.modePara = rs.getBigDecimal("modePara");
		this.startSOC = rs.getBigDecimal("startSOC");
		this.endSOC = rs.getBigDecimal("endSOC");
		this.CarNo = rs.getString("CarNo");
		this.VIN = rs.getString("VIN");
		this.recordTime = rs.getTimestamp("recordTime");
		this.orderStartTime = rs.getTimestamp("orderStartTime");
		this.orderEndTime = rs.getTimestamp("orderEndTime");
		this.customerId = rs.getInt("customerId");
		this.orderStatus = rs.getString("orderStatus");
		this.operateStatus = rs.getString("operateStatus");
		this.sparking_code = rs.getString("sparking_code");
		this.merchantId = rs.getInt("merchantId");
		this.moneyOfassure = rs.getBigDecimal("moneyOfassure");
		this.merchantAccountId = rs.getInt("merchantAccountId");
		this.grooveNo = rs.getString("grooveNo");
		this.deviceType = rs.getInt("deviceType");
		// 租赁点
		this.sitePO = new SitePO();
		this.sitePO.setSiteName(rs.getString("siteName"));
		// 租赁点
		this.merchantPO = new MerchantPO();
		this.merchantPO.setMerchantName(rs.getString("merchantName"));
		// 客户信息
		this.customerPO = new CustomerPO();
		this.customerPO.setName(rs.getString("name"));
		this.customerPO.setPhone(rs.getString("phone"));
		// 客户账户信息
		this.customerPO.setAccountPO(new AccountPO());
		this.customerPO.getAccountPO().setBalance(rs.getBigDecimal("balance"));
		this.customerPO.getAccountPO().setId(rs.getInt("accountId"));
		this.customerPO.getAccountPO().setCardNO(rs.getString("cardNO"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExchangeNo() {
		return exchangeNo;
	}

	public void setExchangeNo(String exchangeNo) {
		this.exchangeNo = exchangeNo;
	}

	public String getKind() {
		return Kind;
	}

	public void setKind(String kind) {
		Kind = kind;
	}

	public String getSite_code() {
		return site_code;
	}

	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}

	public String getFactoryNo() {
		return factoryNo;
	}

	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getChargePara() {
		return chargePara;
	}

	public void setChargePara(String chargePara) {
		this.chargePara = chargePara;
	}

	public String getChargePosition() {
		return chargePosition;
	}

	public void setChargePosition(String chargePosition) {
		this.chargePosition = chargePosition;
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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Timestamp getStartCharge() {
		return startCharge;
	}

	public void setStartCharge(Timestamp startCharge) {
		this.startCharge = startCharge;
	}

	public Timestamp getLastCharge() {
		return lastCharge;
	}

	public void setLastCharge(Timestamp lastCharge) {
		this.lastCharge = lastCharge;
	}

	public BigDecimal getChargingTime() {
		return chargingTime;
	}

	public void setChargingTime(BigDecimal chargingTime) {
		this.chargingTime = chargingTime;
	}

	public BigDecimal getParkingTime() {
		return parkingTime;
	}

	public void setParkingTime(BigDecimal parkingTime) {
		this.parkingTime = parkingTime;
	}

	public BigDecimal getEQ() {
		return EQ;
	}

	public void setEQ(BigDecimal eQ) {
		EQ = eQ;
	}

	public BigDecimal getSparking_cost() {
		return sparking_cost;
	}

	public void setSparking_cost(BigDecimal sparking_cost) {
		this.sparking_cost = sparking_cost;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getAllCost() {
		return allCost;
	}

	public void setAllCost(BigDecimal allCost) {
		this.allCost = allCost;
	}

	public BigDecimal getOldMoney() {
		return oldMoney;
	}

	public void setOldMoney(BigDecimal oldMoney) {
		this.oldMoney = oldMoney;
	}

	public BigDecimal getNewMoney() {
		return newMoney;
	}

	public void setNewMoney(BigDecimal newMoney) {
		this.newMoney = newMoney;
	}

	public String getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}

	public BigDecimal getModePara() {
		return modePara;
	}

	public void setModePara(BigDecimal modePara) {
		this.modePara = modePara;
	}

	public BigDecimal getStartSOC() {
		return startSOC;
	}

	public void setStartSOC(BigDecimal startSOC) {
		this.startSOC = startSOC;
	}

	public BigDecimal getEndSOC() {
		return endSOC;
	}

	public void setEndSOC(BigDecimal endSOC) {
		this.endSOC = endSOC;
	}

	public String getCarNo() {
		return CarNo;
	}

	public void setCarNo(String carNo) {
		CarNo = carNo;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public Timestamp getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

	public Timestamp getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(Timestamp orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public Timestamp getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(Timestamp orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

	public String getSparking_code() {
		return sparking_code;
	}

	public void setSparking_code(String sparking_code) {
		this.sparking_code = sparking_code;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public BigDecimal getMoneyOfassure() {
		return moneyOfassure;
	}

	public void setMoneyOfassure(BigDecimal moneyOfassure) {
		this.moneyOfassure = moneyOfassure;
	}

	public Integer getMerchantAccountId() {
		return merchantAccountId;
	}

	public void setMerchantAccountId(Integer merchantAccountId) {
		this.merchantAccountId = merchantAccountId;
	}

	public String getGrooveNo() {
		return grooveNo;
	}

	public void setGrooveNo(String grooveNo) {
		this.grooveNo = grooveNo;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public SitePO getSitePO() {
		return sitePO;
	}

	public void setSitePO(SitePO sitePO) {
		this.sitePO = sitePO;
	}

	public CustomerPO getCustomerPO() {
		return customerPO;
	}

	public void setCustomerPO(CustomerPO customerPO) {
		this.customerPO = customerPO;
	}

	public MerchantPO getMerchantPO() {
		return merchantPO;
	}

	public void setMerchantPO(MerchantPO merchantPO) {
		this.merchantPO = merchantPO;
	}

}

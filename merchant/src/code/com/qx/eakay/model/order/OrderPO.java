package com.qx.eakay.model.order;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.qx.common.tools.MyDateUtil;
import com.qx.eakay.model.car.CarPO;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.model.customer.UnitCustomerPO;
import com.qx.eakay.model.merchant.CouponPo;
import com.qx.eakay.model.merchant.MerchantUserPO;
import com.qx.eakay.model.merchant.SitePO;
import com.qx.eakay.model.price.MonthLeasePriceTypePO;
import com.qx.eakay.model.price.WhHoursPriceTypePO;

/**
 * 租赁订单表
 * 
 * @author Administrator
 * 
 */
public class OrderPO {

	public static enum OrderStatus {
		已预约, 已取车, 已还车, 已付费, 已取消, 已关门
	}

	private Integer id;
	private Integer customerId;
	private Integer carId;
	private Integer priceTypeId;
	private String priceTypeTableName;
	private String orderStatus;
	private String subOrderStatus;
	private Timestamp createdTime;
	private Float kmsForGet;
	private String imgForGet = "";
	private BigDecimal socForGet = new BigDecimal(0);
	private Float kmsForReturn;
	private String imgForReturn = "";
	private BigDecimal useCost;
	private BigDecimal totalCost;
	private BigDecimal backCost;
	private String backDescribe;
	private BigDecimal socForReturn = new BigDecimal(0);;
	private Timestamp schemingGetTime;
	private Timestamp schemingReturnTime;
	private Timestamp realityGetTime;
	private Timestamp realityReturnTime;
	private Integer schemingGetSiteId;
	private Integer schemingReturnSiteId;
	private Integer relityGetSiteId;
	private Integer relityReturnSiteId;
	private BigDecimal earlyGetCost;
	private BigDecimal earlyReturnCost;
	private BigDecimal laterGetCost;
	private BigDecimal laterReturnCost;
	private BigDecimal otherCost;
	private BigDecimal insuranceCost;
	private String priceTypeName;
	private String longPriceTypeName;
	private Integer merchantId;
	private String ticket;
	private String payment;
	private String checkUser;
	private String checkDescribe;
	private BigDecimal maintenanceCost;
	private Integer couponId;
	private BigDecimal couponCost;

	
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public BigDecimal getMaintenanceCost() {
		return maintenanceCost;
	}
	public void setMaintenanceCost(BigDecimal maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	public String getCheckDescribe() {
		return checkDescribe;
	}
	public void setCheckDescribe(String checkDescribe) {
		this.checkDescribe = checkDescribe;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}

	private Float surplusKmsForGet;
	private Float surplusKmsForReturn;
	public Float getSurplusKmsForGet() {
		return surplusKmsForGet;
	}
	public void setSurplusKmsForGet(Float surplusKmsForGet) {
		this.surplusKmsForGet = surplusKmsForGet;
	}
	public Float getSurplusKmsForReturn() {
		return surplusKmsForReturn;
	}
	public void setSurplusKmsForReturn(Float surplusKmsForReturn) {
		this.surplusKmsForReturn = surplusKmsForReturn;
	}

	private Float perKms;
	private BigDecimal perKmsCost;
	private BigDecimal perKmsCost1;

	public BigDecimal getPerKmsCost1() {
		return perKmsCost1;
	}
	public void setPerKmsCost1(BigDecimal perKmsCost1) {
		this.perKmsCost1 = perKmsCost1;
	}

	private String reaGetTime;
	private String reaReturnTime;
	private String scheReturnTime;

	private String menForGet;
	private String menForReturn;
	private String menForCharge;

	private String getCarStatus;
	private String returnCarStatus;

	private String otherDescribe;
	private String currentCarStatus;
	private String returnCarConfirm;
	private String customerTable;
	private Integer orderType;
	private String orderDescribe;
	
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getOrderDescribe() {
		return orderDescribe;
	}
	public void setOrderDescribe(String orderDescribe) {
		this.orderDescribe = orderDescribe;
	}
	public String getCustomerTable() {
		return customerTable;
	}
	public void setCustomerTable(String customerTable) {
		this.customerTable = customerTable;
	}
	public String getReturnCarConfirm() {
		return returnCarConfirm;
	}
	public void setReturnCarConfirm(String returnCarConfirm) {
		this.returnCarConfirm = returnCarConfirm;
	}
	public String getCurrentCarStatus() {
		return currentCarStatus;
	}
	public void setCurrentCarStatus(String currentCarStatus) {
		this.currentCarStatus = currentCarStatus;
	}

	public CouponPo couponPo = new CouponPo();
	public CouponPo getCouponPo() {
		return couponPo;
	}
	public void setCouponPo(CouponPo couponPo) {
		this.couponPo = couponPo;
	}
	public CarPO carPO = new CarPO();
	private CustomerPO customerPO = new CustomerPO();
	public UnitCustomerPO unitCustomerPO = new UnitCustomerPO();
	public MerchantUserPO merchantUserPO = new MerchantUserPO();
	private SitePO gsitePO = new SitePO();
	private SitePO rsitePO = new SitePO();
	private WhHoursPriceTypePO priceTypePO = new WhHoursPriceTypePO();
	public WhHoursPriceTypePO getWhHoursPriceTypePO() {
		return priceTypePO;
	}
	public void setWhHoursPriceTypePO(WhHoursPriceTypePO priceTypePO) {
		this.priceTypePO = priceTypePO;
	}
	
	private MonthLeasePriceTypePO monthPriceTypePO = new MonthLeasePriceTypePO();
	public MonthLeasePriceTypePO getMonthPriceTypePO() {
		return monthPriceTypePO;
	}
	public void setMonthPriceTypePO(MonthLeasePriceTypePO monthPriceTypePO) {
		this.monthPriceTypePO = monthPriceTypePO;
	}

	public UnitCustomerPO getUnitCustomerPO() {
		return unitCustomerPO;
	}
	public void setUnitCustomerPO(UnitCustomerPO unitCustomerPO) {
		this.unitCustomerPO = unitCustomerPO;
	}
	
	public MerchantUserPO getMerchantUserPO() {
		return merchantUserPO;
	}
	public void setMerchantUserPO(MerchantUserPO merchantUserPO) {
		this.merchantUserPO = merchantUserPO;
	}

	private String useTime;

	public OrderPO() {

	}

	public OrderPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.customerId = rs.getInt("customerId");
		this.carId = rs.getInt("carId");
		this.merchantId = rs.getInt("merchantId");
		this.priceTypeId = rs.getInt("priceTypeId");
		this.priceTypeTableName = rs.getString("priceTypeTableName");
		this.orderStatus = rs.getString("orderStatus");
		this.subOrderStatus = rs.getString("subOrderStatus");
		this.useCost = rs.getBigDecimal("useCost");
		this.totalCost = rs.getBigDecimal("totalCost");
		this.schemingGetTime = rs.getTimestamp("schemingGetTime");
		this.schemingReturnTime = rs.getTimestamp("schemingReturnTime");
		this.realityGetTime = rs.getTimestamp("realityGetTime");
		this.realityReturnTime = rs.getTimestamp("realityReturnTime");
		this.setSocForGet(rs.getBigDecimal("socForGet"));
		this.setSocForReturn(rs.getBigDecimal("socForReturn"));
		this.kmsForGet = rs.getFloat("kmsForGet");
		this.kmsForReturn = rs.getFloat("kmsForReturn");
		this.relityGetSiteId = rs.getInt("relityGetSiteId");
		this.relityReturnSiteId = rs.getInt("relityReturnSiteId");
		this.menForGet = rs.getString("menForGet");
		this.menForReturn = rs.getString("menForReturn");
		this.menForCharge = rs.getString("menForCharge");
		this.otherDescribe = rs.getString("otherDescribe");
		this.getCarStatus = rs.getString("getCarStatus");
		this.returnCarStatus = rs.getString("returnCarStatus");
		this.useTime = rs.getString("useTime");
		this.perKms = rs.getFloat("perKms") ;
		this.perKmsCost = rs.getBigDecimal("perKmsCost") ;
		this.otherCost = rs.getBigDecimal("otherCost") ;
		this.otherDescribe = rs.getString("otherDescribe") ;
		this.orderType = rs.getInt("orderType");
		this.customerId = rs.getInt("customerId");
		this.merchantId = rs.getInt("merchantId") ;
		this.surplusKmsForGet = rs.getFloat("surplusKmsForGet");
		this.surplusKmsForReturn = rs.getFloat("surplusKmsForReturn");
		this.ticket = rs.getString("ticket");
		this.payment = rs.getString("payment");
		this.checkUser = rs.getString("checkUser");
		this.checkDescribe = rs.getString("checkDescribe");
		this.maintenanceCost = rs.getBigDecimal("maintenanceCost");
		this.couponId = rs.getInt("couponId");
		this.couponCost = rs.getBigDecimal("couponCost");
		// 客户信息
		this.customerPO = new CustomerPO();
		this.customerPO.setName(rs.getString("name"));
		this.customerPO.setPhone(rs.getString("phone"));
		this.customerPO.setIdCard(rs.getString("idCard"));
		// 客户账户信息
		this.customerPO.setAccountPO(new AccountPO());
		this.customerPO.getAccountPO().setId(rs.getInt("accountId"));
		this.customerPO.getAccountPO().setCardNO(rs.getString("cardNO"));
		this.customerPO.getAccountPO().setBalance(rs.getBigDecimal("balance"));
		this.customerPO.getAccountPO().setMoneyOfassure(
				rs.getBigDecimal("accountMoneyOfassure"));
		// 车辆信息
		this.carPO = new CarPO();
		this.carPO.setCarNumber(rs.getString("carNumber"));
		this.carPO.setSoc(rs.getBigDecimal("soc"));
		// 取车租赁点
		this.gsitePO = new SitePO();
		this.gsitePO.setSiteName(rs.getString("gsiteName"));
		// 还车租赁点
		this.rsitePO = new SitePO();
		this.rsitePO.setSiteName(rs.getString("rsiteName"));
		// 租赁收费套餐
		this.priceTypePO = new WhHoursPriceTypePO();
		this.priceTypePO.setTypeName(rs.getString("typeName"));
		
		// 租赁收费套餐
		this.monthPriceTypePO = new MonthLeasePriceTypePO();
		this.monthPriceTypePO.setTypeName(rs.getString("typeName"));
		
		// 优惠券
		this.couponPo = new CouponPo();
		this.couponPo.setCouponName(rs.getString("couponName"));
		this.couponPo.setBalance(rs.getBigDecimal("coBalance"));
		this.couponPo.setCouponType(rs.getInt("couponType"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getPriceTypeId() {
		return priceTypeId;
	}

	public void setPriceTypeId(Integer priceTypeId) {
		this.priceTypeId = priceTypeId;
	}

	public String getPriceTypeTableName() {
		return priceTypeTableName;
	}

	public void setPriceTypeTableName(String priceTypeTableName) {
		this.priceTypeTableName = priceTypeTableName;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSubOrderStatus() {
		return subOrderStatus;
	}

	public void setSubOrderStatus(String subOrderStatus) {
		this.subOrderStatus = subOrderStatus;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Float getKmsForGet() {
		return kmsForGet;
	}

	public void setKmsForGet(Float kmsForGet) {
		this.kmsForGet = kmsForGet;
	}

	public String getImgForGet() {
		return imgForGet;
	}

	public void setImgForGet(String imgForGet) {
		this.imgForGet = imgForGet;
	}

	public BigDecimal getSocForGet() {
		return socForGet;
	}

	public void setSocForGet(BigDecimal socForGet) {
		this.socForGet = socForGet;
	}

	public Float getKmsForReturn() {
		return kmsForReturn;
	}

	public void setKmsForReturn(Float kmsForReturn) {
		this.kmsForReturn = kmsForReturn;
	}

	public String getImgForReturn() {
		return imgForReturn;
	}

	public void setImgForReturn(String imgForReturn) {
		this.imgForReturn = imgForReturn;
	}

	public BigDecimal getUseCost() {
		return useCost;
	}

	public void setUseCost(BigDecimal useCost) {
		this.useCost = useCost;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getBackCost() {
		return backCost;
	}

	public void setBackCost(BigDecimal backCost) {
		this.backCost = backCost;
	}

	public String getBackDescribe() {
		return backDescribe;
	}

	public void setBackDescribe(String backDescribe) {
		this.backDescribe = backDescribe;
	}

	public BigDecimal getSocForReturn() {
		return socForReturn;
	}

	public void setSocForReturn(BigDecimal socForReturn) {
		this.socForReturn = socForReturn;
	}

	public Timestamp getSchemingGetTime() {
		return schemingGetTime;
	}

	public void setSchemingGetTime(Timestamp schemingGetTime) {
		this.schemingGetTime = schemingGetTime;
	}

	public Timestamp getSchemingReturnTime() {
		return schemingReturnTime;
	}

	public void setSchemingReturnTime(Timestamp schemingReturnTime) {
		this.schemingReturnTime = schemingReturnTime;
	}

	public Timestamp getRealityGetTime() {
		return realityGetTime;
	}

	public void setRealityGetTime(Timestamp realityGetTime) {
		this.realityGetTime = realityGetTime;
	}

	public Timestamp getRealityReturnTime() {
		return realityReturnTime;
	}

	public void setRealityReturnTime(Timestamp realityReturnTime) {
		this.realityReturnTime = realityReturnTime;
	}

	public Integer getSchemingGetSiteId() {
		return schemingGetSiteId;
	}

	public void setSchemingGetSiteId(Integer schemingGetSiteId) {
		this.schemingGetSiteId = schemingGetSiteId;
	}

	public Integer getSchemingReturnSiteId() {
		return schemingReturnSiteId;
	}

	public void setSchemingReturnSiteId(Integer schemingReturnSiteId) {
		this.schemingReturnSiteId = schemingReturnSiteId;
	}

	public Integer getRelityGetSiteId() {
		return relityGetSiteId;
	}

	public void setRelityGetSiteId(Integer relityGetSiteId) {
		this.relityGetSiteId = relityGetSiteId;
	}

	public Integer getRelityReturnSiteId() {
		return relityReturnSiteId;
	}

	public void setRelityReturnSiteId(Integer relityReturnSiteId) {
		this.relityReturnSiteId = relityReturnSiteId;
	}

	public BigDecimal getEarlyGetCost() {
		return earlyGetCost;
	}

	public void setEarlyGetCost(BigDecimal earlyGetCost) {
		this.earlyGetCost = earlyGetCost;
	}

	public BigDecimal getEarlyReturnCost() {
		return earlyReturnCost;
	}

	public void setEarlyReturnCost(BigDecimal earlyReturnCost) {
		this.earlyReturnCost = earlyReturnCost;
	}

	public BigDecimal getLaterGetCost() {
		return laterGetCost;
	}

	public void setLaterGetCost(BigDecimal laterGetCost) {
		this.laterGetCost = laterGetCost;
	}

	public BigDecimal getLaterReturnCost() {
		return laterReturnCost;
	}

	public void setLaterReturnCost(BigDecimal laterReturnCost) {
		this.laterReturnCost = laterReturnCost;
	}

	public BigDecimal getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}

	public BigDecimal getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(BigDecimal insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public String getLongPriceTypeName() {
		return longPriceTypeName;
	}
	public void setLongPriceTypeName(String longPriceTypeName) {
		this.longPriceTypeName = longPriceTypeName;
	}
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Float getPerKms() {
		return perKms;
	}

	public void setPerKms(Float perKms) {
		this.perKms = perKms;
	}

	public BigDecimal getPerKmsCost() {
		return perKmsCost;
	}

	public void setPerKmsCost(BigDecimal perKmsCost) {
		this.perKmsCost = perKmsCost;
	}

	public String getReaGetTime() {
		return reaGetTime;
	}

	public void setReaGetTime(String reaGetTime) {
		this.reaGetTime = reaGetTime;
		if (reaGetTime != null && !reaGetTime.equals("")) {
			this.realityGetTime = new Timestamp(MyDateUtil.HfStrToDate(
					reaGetTime).getTime());
		}
	}

	public String getReaReturnTime() {
		return reaReturnTime;
	}

	public void setReaReturnTime(String reaReturnTime) {
		this.reaReturnTime = reaReturnTime;
		if (reaReturnTime != null && !reaReturnTime.equals("")) {
			this.realityReturnTime = new Timestamp(MyDateUtil.HfStrToDate(
					reaReturnTime).getTime());
		}
	}
	
	public String getScheReturnTime() {
		return scheReturnTime;
	}

	public void setScheReturnTime(String scheReturnTime) {
		this.scheReturnTime = scheReturnTime;
		if (scheReturnTime != null && !scheReturnTime.equals("")) {
			this.schemingReturnTime = new Timestamp(MyDateUtil.HfStrToDate(
					scheReturnTime).getTime());
		}
	}

	public String getMenForGet() {
		return menForGet;
	}

	public void setMenForGet(String menForGet) {
		this.menForGet = menForGet;
	}

	public String getMenForReturn() {
		return menForReturn;
	}

	public void setMenForReturn(String menForReturn) {
		this.menForReturn = menForReturn;
	}

	public String getMenForCharge() {
		return menForCharge;
	}

	public void setMenForCharge(String menForCharge) {
		this.menForCharge = menForCharge;
	}

	public String getGetCarStatus() {
		return getCarStatus;
	}

	public void setGetCarStatus(String getCarStatus) {
		this.getCarStatus = getCarStatus;
	}

	public String getReturnCarStatus() {
		return returnCarStatus;
	}

	public void setReturnCarStatus(String returnCarStatus) {
		this.returnCarStatus = returnCarStatus;
	}

	public String getOtherDescribe() {
		return otherDescribe;
	}

	public void setOtherDescribe(String otherDescribe) {
		this.otherDescribe = otherDescribe;
	}

	public CarPO getCarPO() {
		return carPO;
	}

	public void setCarPO(CarPO carPO) {
		this.carPO = carPO;
	}

	public CustomerPO getCustomerPO() {
		return customerPO;
	}

	public void setCustomerPO(CustomerPO customerPO) {
		this.customerPO = customerPO;
	}

	public SitePO getGsitePO() {
		return gsitePO;
	}

	public void setGsitePO(SitePO gsitePO) {
		this.gsitePO = gsitePO;
	}

	public SitePO getRsitePO() {
		return rsitePO;
	}

	public void setRsitePO(SitePO rsitePO) {
		this.rsitePO = rsitePO;
	}

	public WhHoursPriceTypePO getPriceTypePO() {
		return priceTypePO;
	}

	public void setPriceTypePO(WhHoursPriceTypePO priceTypePO) {
		this.priceTypePO = priceTypePO;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public BigDecimal getCouponCost() {
		return couponCost;
	}
	public void setCouponCost(BigDecimal couponCost) {
		this.couponCost = couponCost;
	}
	

}

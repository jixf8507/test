package com.qx.eakay.model.stake;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 充电套餐
 * 
 * @author sdf
 * @date 2015年10月29日
 */
public class StakePricePO {

	private Integer id;
	private Integer merchantId;
	private String name;
	private String Hour1;
	private String Minute1;
	private String Hour2;
	private String Minute2;
	private String Hour3;
	private String Minute3;
	private String Hour4;
	private String Minute4;
	private BigDecimal PriceA;
	private BigDecimal PriceB;
	private BigDecimal PriceC;
	private BigDecimal PriceD;
	private Timestamp createdTime;
	private String memo;
	private String time1;
	private String time2;
	private String time3;
	private String time4;
	
	public StakePricePO() {

	}

	public StakePricePO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.merchantId = rs.getInt("merchantId");
		this.name = rs.getString("name");
		this.Hour1 = rs.getString("Hour1");
		this.Minute1 = rs.getString("Minute1");
		this.Hour2 = rs.getString("Hour2");
		this.Minute2 = rs.getString("Minute2");
		this.Hour3 = rs.getString("Hour3");
		this.Minute3 = rs.getString("Minute3");
		this.Hour4 = rs.getString("Hour4");
		this.Minute4 = rs.getString("Minute4");
		this.setPriceA(rs.getBigDecimal("PriceA"));
		this.setPriceB(rs.getBigDecimal("PriceB"));
		this.setPriceC(rs.getBigDecimal("PriceC"));
		this.setPriceD(rs.getBigDecimal("PriceD"));
		this.createdTime = rs.getTimestamp("createdTime");
		this.memo = rs.getString("memo");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHour1() {
		return Hour1;
	}

	public void setHour1(String hour1) {
		Hour1 = hour1;
	}

	public String getMinute1() {
		return Minute1;
	}

	public void setMinute1(String minute1) {
		Minute1 = minute1;
	}

	public String getHour2() {
		return Hour2;
	}

	public void setHour2(String hour2) {
		Hour2 = hour2;
	}

	public String getMinute2() {
		return Minute2;
	}

	public void setMinute2(String minute2) {
		Minute2 = minute2;
	}

	public String getHour3() {
		return Hour3;
	}

	public void setHour3(String hour3) {
		Hour3 = hour3;
	}

	public String getMinute3() {
		return Minute3;
	}

	public void setMinute3(String minute3) {
		Minute3 = minute3;
	}

	public String getHour4() {
		return Hour4;
	}

	public void setHour4(String hour4) {
		Hour4 = hour4;
	}

	public String getMinute4() {
		return Minute4;
	}

	public void setMinute4(String minute4) {
		Minute4 = minute4;
	}

	public BigDecimal getPriceA() {
		return PriceA;
	}

	public void setPriceA(BigDecimal priceA) {
		PriceA = priceA;
	}

	public BigDecimal getPriceB() {
		return PriceB;
	}

	public void setPriceB(BigDecimal priceB) {
		PriceB = priceB;
	}

	public BigDecimal getPriceC() {
		return PriceC;
	}

	public void setPriceC(BigDecimal priceC) {
		PriceC = priceC;
	}

	public BigDecimal getPriceD() {
		return PriceD;
	}

	public void setPriceD(BigDecimal priceD) {
		PriceD = priceD;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String getTime3() {
		return time3;
	}

	public void setTime3(String time3) {
		this.time3 = time3;
	}

	public String getTime4() {
		return time4;
	}

	public void setTime4(String time4) {
		this.time4 = time4;
	}

	
}

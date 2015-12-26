package com.qx.eakay.model.price;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.qx.common.tools.MyDateUtil;
import com.qx.eakay.help.ReChargeHelp;
import com.qx.eakay.help.UseTimeHelp;
import com.qx.eakay.model.order.OrderPO;

/**
 * 
 * @author Administrator
 * 
 */
public class WhHoursPriceTypePO extends CarLeasePriceType {

	private BigDecimal oneHoursCost;
	private BigDecimal twoHoursCost;
	private BigDecimal threeHoursCost;
	private BigDecimal fourHoursCost;
	private BigDecimal fiveHoursCost;
	private BigDecimal maxCostForDay;
	private BigDecimal nightCost;
	private Float perHoursKmsForDay;
	private BigDecimal perKmsPrice;
	private Float maxKmsForNight;
	private Timestamp startTimeForDay;
	private Timestamp endTimeForDay;
	private String flag;
	private Float maxKmsForDay;

	private String startTime;
	private String endTime;

	private Integer maxDays;
	private Integer minDays;
	private Integer prepayment;
	private Integer reservedMinute;
	
	private Timestamp startTimeForWork;
	private Timestamp endTimeForWork;

	private String startWorkTime;
	private String endWorkTime;


	public WhHoursPriceTypePO() {

	}

	public WhHoursPriceTypePO(ResultSet rs) throws SQLException {
		super(rs);
		this.oneHoursCost = rs.getBigDecimal("oneHoursCost");
		this.twoHoursCost = rs.getBigDecimal("twoHoursCost");
		this.threeHoursCost = rs.getBigDecimal("threeHoursCost");
		this.fourHoursCost = rs.getBigDecimal("fourHoursCost");
		this.fiveHoursCost = rs.getBigDecimal("fiveHoursCost");
		this.maxCostForDay = rs.getBigDecimal("maxCostForDay");
		this.nightCost = rs.getBigDecimal("nightCost");
		this.endTimeForDay = rs.getTimestamp("endTimeForDay");
		this.startTimeForDay = rs.getTimestamp("startTimeForDay");
		this.perHoursKmsForDay = rs.getFloat("perHoursKmsForDay");
		this.perKmsPrice = rs.getBigDecimal("perKmsPrice");
		this.maxKmsForNight = rs.getFloat("maxKmsForNight");
		this.maxKmsForDay = rs.getFloat("maxKmsForDay");
		this.perHoursKmsForDay = rs.getFloat("perHoursKmsForDay");
		this.startTime = MyDateUtil.dateToStringHf(this.startTimeForDay)
				.substring(10);
		this.endTime = MyDateUtil.dateToStringHf(this.endTimeForDay).substring(
				10);
		this.maxDays = rs.getInt("maxDays");
		this.minDays = rs.getInt("minDays");
		this.prepayment = rs.getInt("prepayment");
		this.startTimeForWork = rs.getTimestamp("startTimeForWork");
		this.endTimeForWork = rs.getTimestamp("endTimeForWork");
		this.startWorkTime = MyDateUtil.dateToStringHf(this.startTimeForWork)
				.substring(10);
		this.endWorkTime = MyDateUtil.dateToStringHf(this.endTimeForWork).substring(
				10);
		this.reservedMinute = rs.getInt("reservedMinute");
	}

	public Integer getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(Integer prepayment) {
		this.prepayment = prepayment;
	}

	public Integer getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(Integer maxDays) {
		this.maxDays = maxDays;
	}

	public Integer getMinDays() {
		return minDays;
	}

	public void setMinDays(Integer minDays) {
		this.minDays = minDays;
	}

	public BigDecimal getOneHoursCost() {
		return oneHoursCost;
	}

	public void setOneHoursCost(BigDecimal oneHoursCost) {
		this.oneHoursCost = oneHoursCost;
	}

	public BigDecimal getTwoHoursCost() {
		return twoHoursCost;
	}

	public void setTwoHoursCost(BigDecimal twoHoursCost) {
		this.twoHoursCost = twoHoursCost;
	}

	public BigDecimal getThreeHoursCost() {
		return threeHoursCost;
	}

	public void setThreeHoursCost(BigDecimal threeHoursCost) {
		this.threeHoursCost = threeHoursCost;
	}

	public BigDecimal getFourHoursCost() {
		return fourHoursCost;
	}

	public void setFourHoursCost(BigDecimal fourHoursCost) {
		this.fourHoursCost = fourHoursCost;
	}

	public BigDecimal getFiveHoursCost() {
		return fiveHoursCost;
	}

	public void setFiveHoursCost(BigDecimal fiveHoursCost) {
		this.fiveHoursCost = fiveHoursCost;
	}

	public BigDecimal getMaxCostForDay() {
		return maxCostForDay;
	}

	public void setMaxCostForDay(BigDecimal maxCostForDay) {
		this.maxCostForDay = maxCostForDay;
	}

	public BigDecimal getNightCost() {
		return nightCost;
	}

	public void setNightCost(BigDecimal nightCost) {
		this.nightCost = nightCost;
	}

	public Float getPerHoursKmsForDay() {
		return perHoursKmsForDay;
	}

	public void setPerHoursKmsForDay(Float perHoursKmsForDay) {
		this.perHoursKmsForDay = perHoursKmsForDay;
	}

	public BigDecimal getPerKmsPrice() {
		return perKmsPrice;
	}

	public void setPerKmsPrice(BigDecimal perKmsPrice) {
		this.perKmsPrice = perKmsPrice;
	}

	public Float getMaxKmsForNight() {
		return maxKmsForNight;
	}

	public void setMaxKmsForNight(Float maxKmsForNight) {
		this.maxKmsForNight = maxKmsForNight;
	}

	public Float getMaxKmsForDay() {
		return maxKmsForDay;
	}

	public void setMaxKmsForDay(Float maxKmsForDay) {
		this.maxKmsForDay = maxKmsForDay;
	}

	public Timestamp getStartTimeForDay() {
		return startTimeForDay;
	}

	public void setStartTimeForDay(Timestamp startTimeForDay) {
		this.startTimeForDay = startTimeForDay;
	}

	public Timestamp getEndTimeForDay() {
		return endTimeForDay;
	}

	public void setEndTimeForDay(Timestamp endTimeForDay) {
		this.endTimeForDay = endTimeForDay;
	}
	

	public Timestamp getStartTimeForWork() {
		return startTimeForWork;
	}

	public void setStartTimeForWork(Timestamp startTimeForWork) {
		this.startTimeForWork = startTimeForWork;
	}

	public Timestamp getEndTimeForWork() {
		return endTimeForWork;
	}

	public void setEndTimeForWork(Timestamp endTimeForWork) {
		this.endTimeForWork = endTimeForWork;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
		this.startTimeForDay = new Timestamp(MyDateUtil.HfStrToDate(startTime)
				.getTime());
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
		this.endTimeForDay = new Timestamp(MyDateUtil.HfStrToDate(endTime)
				.getTime());
	}
	
	public String getStartWorkTime() {
		return startWorkTime;
	}
	
	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
		this.startTimeForWork = new Timestamp(MyDateUtil.HfStrToDate(startWorkTime)
				.getTime());
	}
	
	public String getEndWorkTime() {
		return endWorkTime;
	}
	
	public void setEndWorkTime(String endWorkTime) {
		this.endWorkTime = endWorkTime;
		this.endTimeForWork = new Timestamp(MyDateUtil.HfStrToDate(endWorkTime)
				.getTime());
	}

	public Integer getReservedMinute() {
		return reservedMinute;
	}

	public void setReservedMinute(Integer reservedMinute) {
		this.reservedMinute = reservedMinute;
	}

	/**
	 * 计算订单租赁费用
	 * 
	 * @param orderPO
	 * @return
	 */
	@Override
	public OrderPO countCostForOrder(OrderPO orderPO) {
		if (orderPO == null) {
			return null;
		}
		UseTimeHelp useTimeHelp = new UseTimeHelp(this, orderPO); // 根据租赁用车记录获取用车时间
		ReChargeHelp chargeHelp = new ReChargeHelp(useTimeHelp, this); // 根据用车时间和收费标准获取应收费信息

		float exceedKm = (orderPO.getKmsForReturn() - orderPO.getKmsForGet())
				- chargeHelp.getAllowDistance(); // 判断是否超出允许的行驶里程数
		BigDecimal exceedCharge = new BigDecimal(0);
		BigDecimal kmsCharge = new BigDecimal(0);
		if (exceedKm > 0) {
			/*exceedCharge = chargeHelp.getPriceOfKm().multiply(
					new BigDecimal(exceedKm)); // 计算超出里程数应收费
			kmsCharge = exceedCharge;
			// 如果租赁收费小于一天的最大收费并且超出公里数收费+租赁收费大于一天的最高收费，
			// 则超出公里数收费 = 一天最高收费-租赁收费。（租赁收费+超出公里数收费不能超出一天的最大收费）
			// if (chargeHelp.getUseReCharge().compareTo(this.maxCostForDay) <=
			// 0
			// && chargeHelp.getUseReCharge().add(exceedCharge)
			// .compareTo(this.maxCostForDay) > 0) {
			// exceedCharge = this.maxCostForDay.subtract(chargeHelp
			// .getUseReCharge());
			// }
			exceedCharge = exceedCharge.setScale(0, BigDecimal.ROUND_HALF_UP); // 超出收费不足一元部分算一元
*/
			int divideKms = (int) (exceedKm / getPerHoursKmsForDay());
			BigDecimal modKms =  new BigDecimal((exceedKm % getPerHoursKmsForDay()));
			if(modKms.compareTo(getOneHoursCost())==1){
				modKms = getOneHoursCost();
			}
			BigDecimal divideCharge = getOneHoursCost().multiply(new BigDecimal(divideKms));
			BigDecimal modCharge = getPerKmsPrice().multiply(modKms);
			kmsCharge = divideCharge.add(modCharge);
			exceedCharge = kmsCharge;
		} else {
			exceedKm = 0f;
		}
		orderPO.setPerKms(exceedKm);
		orderPO.setPerKmsCost(kmsCharge); // 设置超出行驶里程数收费
		orderPO.setPerKmsCost1(exceedCharge);// 设置超出公里数收费 = 一天最高收费-租赁收费。
		orderPO.setUseCost(chargeHelp.getUseReCharge());
		orderPO.setTotalCost(orderPO.getUseCost().add(exceedCharge));
		return orderPO;
	}

}

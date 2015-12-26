package com.qx.eakay.model.price;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qx.eakay.help.LongUseTimeHelp;
import com.qx.eakay.model.order.OrderPO;

/**
 * 
 * @author Administrator
 * 
 */
public class MonthLeasePriceTypePO extends CarLeasePriceType {

	private BigDecimal monthFee;
	private BigDecimal dayFee;
	private BigDecimal hourFee;

	public BigDecimal getMonthFee() {
		return monthFee;
	}

	public void setMonthFee(BigDecimal monthFee) {
		this.monthFee = monthFee;
	}

	public BigDecimal getDayFee() {
		return dayFee;
	}

	public void setDayFee(BigDecimal dayFee) {
		this.dayFee = dayFee;
	}

	public BigDecimal getHourFee() {
		return hourFee;
	}

	public void setHourFee(BigDecimal hourFee) {
		this.hourFee = hourFee;
	}

	public MonthLeasePriceTypePO() {

	}

	public MonthLeasePriceTypePO(ResultSet rs) throws SQLException {
		super();
		this.monthFee = rs.getBigDecimal("monthFee");
		this.dayFee = rs.getBigDecimal("dayFee");
		this.hourFee = rs.getBigDecimal("hourFee");
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
		LongUseTimeHelp useTimeHelp = new LongUseTimeHelp(orderPO); // 根据租赁用车记录获取用车时间

		Integer useMonths = useTimeHelp.getUseMonths(); // 使用月数
		Integer useDays = useTimeHelp.getUseDays(); // 使用天数
		Integer useHours = useTimeHelp.getUseHours(); // 使用小时数

		BigDecimal chargeMonthFee = getMonthFee().multiply(
				new BigDecimal(useMonths));
		BigDecimal chargeDayFee = getDayFee().multiply(new BigDecimal(useDays));
		BigDecimal chargeHourFee = getHourFee().multiply(
				new BigDecimal(useHours));

		BigDecimal totalFee = chargeMonthFee.add(chargeDayFee).add(
				chargeHourFee);

		orderPO.setUseCost(totalFee);
		orderPO.setTotalCost(totalFee);
		return orderPO;
	}

}

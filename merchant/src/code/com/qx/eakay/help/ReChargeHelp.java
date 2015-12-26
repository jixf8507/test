package com.qx.eakay.help;

import java.math.BigDecimal;

import com.qx.eakay.model.price.WhHoursPriceTypePO;

/**
 * 车辆租赁收费帮助类
 * 
 * @author jixf
 * 
 */
public class ReChargeHelp {

	private float MAX_KMS_OF_DAY = 99999F;

	private UseTimeHelp useTimeHelp; // 租赁使用时间

	private WhHoursPriceTypePO hoursPriceTypePO; // 租赁用车收费价格

	private float allowDistance = 0f; // 租赁小时内允许行驶公里数

	private BigDecimal useReCharge = new BigDecimal(0); // 租赁费用

	private BigDecimal priceOfKm = new BigDecimal(0); // 超出每公里收费

	public ReChargeHelp(UseTimeHelp useTimeHelp,
			WhHoursPriceTypePO hoursPriceTypePO) {
		this.hoursPriceTypePO = hoursPriceTypePO;
		this.useTimeHelp = useTimeHelp;
		MAX_KMS_OF_DAY = hoursPriceTypePO.getMaxKmsForDay();
		init();
	}

	public void init() {
		priceOfKm = hoursPriceTypePO.getPerKmsPrice();
		
		// 租赁天数>套餐的最大天数
		if (useTimeHelp.getUseDays() * 24 + useTimeHelp.getUseHours() > hoursPriceTypePO
				.getMaxDays() * 24) {

			Integer moreDays =  useTimeHelp.getUseDays() - hoursPriceTypePO.getMaxDays();//超出租赁套餐最大天数
			Integer moreHours = (int) (moreDays * 24 + useTimeHelp.getUseHours());//超出租赁套餐最大天数的小时数
			
			allowDistance = hoursPriceTypePO.getMaxDays() * MAX_KMS_OF_DAY + hoursPriceTypePO.getPerHoursKmsForDay() * moreHours;
			
			useReCharge = hoursPriceTypePO.getMaxCostForDay()
					.multiply(new BigDecimal(hoursPriceTypePO.getMaxDays()))
					.add(hoursPriceTypePO.getOneHoursCost().multiply(new BigDecimal(moreHours)));

			// 租赁天数<=套餐的最大天数
		} else {
			
			float syDaysKm = 0f;
			BigDecimal syDaysReCharge = new BigDecimal(0);
			
			//不包括夜租
			if(useTimeHelp.getNightTimes()==0){
				syDaysKm = getAllowKmByUseHours(useTimeHelp.getUseHoursInDayTimes()+useTimeHelp.getUseHoursInDayAndWorkTimes());
				
				syDaysReCharge = getCostByUseHours(useTimeHelp.getUseHoursInDayTimes()+useTimeHelp.getUseHoursInDayAndWorkTimes());
				//包括夜租
			}else{
				syDaysKm = useTimeHelp.getNightTimes()
						* hoursPriceTypePO.getMaxKmsForNight()
						+ getAllowKmByUseHours(useTimeHelp.getUseHoursInDayTimes());
				
				syDaysReCharge = hoursPriceTypePO
						.getNightCost()
						.multiply(new BigDecimal(useTimeHelp.getNightTimes()))
						.add(getCostByUseHours(useTimeHelp.getUseHoursInDayTimes()));
			}
			allowDistance = useTimeHelp.getUseDays() * MAX_KMS_OF_DAY
					+ syDaysKm;
			
			if (syDaysReCharge.compareTo(hoursPriceTypePO.getMaxCostForDay()) > 0) {
				syDaysReCharge = hoursPriceTypePO.getMaxCostForDay();
				allowDistance = useTimeHelp.getUseDays() * MAX_KMS_OF_DAY
						+ MAX_KMS_OF_DAY;
			}
			useReCharge = hoursPriceTypePO.getMaxCostForDay()
					.multiply(new BigDecimal(useTimeHelp.getUseDays()))
					.add(syDaysReCharge);
			
		}
	}

	/**
	 * 按小时计算使用公里数
	 * 
	 * @param hours
	 * @return
	 */
	public Float getAllowKmByUseHours(Float hours) {
		Float allowKm = hoursPriceTypePO.getPerHoursKmsForDay() * hours;
		if (allowKm > MAX_KMS_OF_DAY) {
			allowKm = MAX_KMS_OF_DAY;
		}
		return allowKm;
	}

	/**
	 * 按小时计算租赁费用金额
	 * 
	 * @param hours
	 * @return
	 */
	public BigDecimal getCostByUseHours(Float hours) {
		BigDecimal cost = hoursPriceTypePO.getOneHoursCost().multiply(
				new BigDecimal(hours));

		// 如果总费用超过一天允许的最大费用
		if (cost.compareTo(hoursPriceTypePO.getMaxCostForDay()) > 0) {
			cost = hoursPriceTypePO.getMaxCostForDay();
		}
		return cost;
	}

	public BigDecimal getUseReCharge() {
		return useReCharge;
	}

	public void setUseReCharge(BigDecimal useReCharge) {
		this.useReCharge = useReCharge;
	}

	public float getAllowDistance() {
		return allowDistance;
	}

	public void setAllowDistance(float allowDistance) {
		this.allowDistance = allowDistance;
	}

	public BigDecimal getPriceOfKm() {
		return priceOfKm;
	}

	public void setPriceOfKm(BigDecimal priceOfKm) {
		this.priceOfKm = priceOfKm;
	}

}

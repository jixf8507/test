package com.qx.eakay.help;

import java.util.Calendar;
import java.util.Date;

import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.model.price.WhHoursPriceTypePO;
import com.qx.eakay.util.DateUtil;

/**
 * 租赁用车时间帮助类
 * 
 * 如果 夜间租赁次数 >=1,则 上下班时间与套餐时间之间使用小时数 不计入 白天租赁使用小时数 如果 夜间租赁次数 <1,则
 * 上下班时间与套餐时间之间使用小时数 计入 白天租赁使用小时数
 * 
 * @author admin
 * 
 */
public class UseTimeHelp {

	// private static final int TIMES_OF_HOUR = 1000 * 60 * 60;

	private WhHoursPriceTypePO hoursPriceTypePO; // 芜湖收费标准

	private OrderPO orderPO; // 租赁用车记录

	private Integer useDays = 0; // 租赁使用天数
	private float useHours = 0; // 租赁使用小时数
	private Integer nightTimes = 0; // 夜间租赁次数
	private float useHoursInDayTimes = 0; // 白天租赁使用小时数
	private float useHoursInDayAndWorkTimes = 0; // 上下班时间与套餐时间之间使用小时数
	private Integer surplusMinutes = 0;
	private Integer surMinutes = 0;

	public UseTimeHelp(WhHoursPriceTypePO hoursPriceTypePO, OrderPO orderPO) {
		this.hoursPriceTypePO = hoursPriceTypePO;
		this.orderPO = orderPO;
		init();
	}

	/**
	 * 处理租赁时间
	 */
	public void init() {
		Date useBeginTime = orderPO.getRealityGetTime();
		Date useEndTime = orderPO.getRealityReturnTime();
		Calendar current = Calendar.getInstance();
		current.setTime(useBeginTime);
		current.add(Calendar.HOUR, 24);
		Date tempDate = current.getTime();

		while (tempDate.getTime() <= useEndTime.getTime()) {
			useDays++;
			current.add(Calendar.HOUR, 24);
			tempDate = current.getTime();
		}

		current.add(Calendar.HOUR, -24);
		tempDate = current.getTime();

		int num = 0;
		while (tempDate.getTime() < useEndTime.getTime()) {
			num++;

			// 不在夜间
			if (dateTimeInDayTimeFashtion(tempDate)) {
				// 在上下班时间与套餐时间之间
				if (dateTimeInDayAndWorkTimeFashtion(tempDate)) {
					surMinutes++;

					// if (surMinutes == 5) {
					// useHoursInDayAndWorkTimes = useHoursInDayAndWorkTimes +
					// 1f;
					// }

					if (surMinutes == 60) {
						useHoursInDayAndWorkTimes = useHoursInDayAndWorkTimes + 1f;
						surMinutes = 0;
					}
//					 surplusMinutes = 0;
				} else {
					surplusMinutes++;
					// if ("true".equals(SystemConfig.newInstance()
					// .getIsHalfhourCharge())) {
					// if (surplusMinutes == 6 || surplusMinutes == 31) {
					// useHoursInDayTimes = useHoursInDayTimes + 0.5f;
					// }
					// } else {
					// if (surplusMinutes == 5) {
					// useHoursInDayTimes = useHoursInDayTimes + 1f;
					// }
					// }

					if (surplusMinutes == 60) {
						useHoursInDayTimes = useHoursInDayTimes + 1f;
						surplusMinutes = 0;
					}
//					 surMinutes = 0;
				}
			} else {
				nightTimes = 1;
			}
			// if
			// ("true".equals(SystemConfig.newInstance().getIsHalfhourCharge()))
			// {
			// if (num == 6 || num == 31) {
			// useHours = useHours + 0.5f;
			// }
			// } else {
			if (num == 5) {
				useHours = useHours + 1f;
			}
			// }
			if (num == 60) {
				num = 0;
			}

			current.add(Calendar.MINUTE, 1);
			tempDate = current.getTime();
		}

		// if (dateTimeInDayWorkTimeFashtion(useBeginTime,useEndTime)) {
		// useHoursInDayAndWorkTimes = useHoursInDayAndWorkTimes - 1;
		// }
		//
		// if
		// (dateTimeOverFiveMinuteFashtion(useBeginTime,useEndTime,useHoursInDayTimes,useHoursInDayAndWorkTimes))
		// {
		// useHoursInDayTimes = 1;
		// }
		
		if(nightTimes!=1){
			if (surplusMinutes + surMinutes >= 65) {
				useHoursInDayTimes = useHoursInDayTimes + 2;
			} else if (surplusMinutes + surMinutes >= 5) {
				useHoursInDayTimes = useHoursInDayTimes + 1;
			}
		}else{
			if (surplusMinutes >= 5) {
				useHoursInDayTimes = useHoursInDayTimes + 1;
			}
		}
	}

	/**
	 * 判断该时间段是否在白天分时租赁时间段 (工作时间之间为白天分时租赁时间段)
	 * 
	 * @param date
	 * @return
	 */
	public boolean dateTimeInDayTimeFashtion(Date date) {
		try {
			int beginHours = hoursPriceTypePO.getStartTimeForWork().getHours();
			int beginMinutes = hoursPriceTypePO.getStartTimeForWork()
					.getMinutes();
			int endHours = hoursPriceTypePO.getEndTimeForWork().getHours();
			int endMinutes = hoursPriceTypePO.getEndTimeForWork().getMinutes();
			int thisHours = date.getHours();
			int thisMinutes = date.getMinutes();
			if ((thisHours > beginHours && thisHours < endHours)
					|| (thisHours == beginHours && thisMinutes >= beginMinutes && thisHours < endHours)
					|| (thisHours > beginHours && thisMinutes <= endMinutes && thisHours == endHours)) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	/**
	 * 判断套餐开始与结束时间在取还车时间之间 此情况下"白天租赁使用小时数 "和"上下班时间与套餐时间之间使用小时数"都会加上1
	 * 
	 * eg：17:00取 18:00还 eg：8:00取 9:00还
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public boolean dateTimeInDayWorkTimeFashtion(Date startDate, Date endDate) {
		try {
			// 工作开始时间
			int beginHours = hoursPriceTypePO.getStartTimeForWork().getHours();
			int beginMinutes = hoursPriceTypePO.getStartTimeForWork()
					.getMinutes();
			int beginSeconds = hoursPriceTypePO.getStartTimeForWork()
					.getSeconds();
			// 工作结束时间
			int endHours = hoursPriceTypePO.getEndTimeForWork().getHours();
			int endMinutes = hoursPriceTypePO.getEndTimeForWork().getMinutes();
			int endSeconds = hoursPriceTypePO.getEndTimeForWork().getSeconds();

			// 套餐开始时间
			int beginHours1 = hoursPriceTypePO.getStartTimeForDay().getHours();
			int beginMinutes1 = hoursPriceTypePO.getStartTimeForDay()
					.getMinutes();
			int beginSeconds1 = hoursPriceTypePO.getStartTimeForDay()
					.getSeconds();
			// 套餐结束时间
			int endHours1 = hoursPriceTypePO.getEndTimeForDay().getHours();
			int endMinutes1 = hoursPriceTypePO.getEndTimeForDay().getMinutes();
			int endSeconds1 = hoursPriceTypePO.getEndTimeForDay().getSeconds();

			// 租赁开始时间
			int startHours = startDate.getHours();
			int startMinutes = startDate.getMinutes();
			int startSeconds = startDate.getSeconds();

			// 租赁结束时间
			int finishHours = endDate.getHours();
			int finishMinutes = endDate.getMinutes();
			int finishSeconds = endDate.getSeconds();

			String beginWork = beginHours + ":" + beginMinutes + ":"
					+ beginSeconds;
			String endWork = endHours + ":" + endMinutes + ":" + endSeconds;
			String beginDay = beginHours1 + ":" + beginMinutes1 + ":"
					+ beginSeconds1;
			String endDay = endHours1 + ":" + endMinutes1 + ":" + endSeconds1;
			String startTime = startHours + ":" + startMinutes + ":"
					+ startSeconds;
			String finishTime = finishHours + ":" + finishMinutes + ":"
					+ finishSeconds;

			Date workBegin = DateUtil.formatToTime(beginWork);
			Date workEnd = DateUtil.formatToTime(endWork);
			Date dayBegin = DateUtil.formatToTime(beginDay);
			Date dayEnd = DateUtil.formatToTime(endDay);
			Date start = DateUtil.formatToTime(startTime);
			Date end = DateUtil.formatToTime(finishTime);

			// 5分钟不收费（5*60*1000）
			if ((start.getTime() + 5 * 60 * 1000 < dayEnd.getTime()
					&& end.getTime() > dayEnd.getTime() + 5 * 60 * 1000
					&& end.getTime() <= workEnd.getTime() && start.getTime() + 65 * 60 * 1000 > end
					.getTime())
					|| (start.getTime() >= workBegin.getTime()
							&& start.getTime() + 5 * 60 * 1000 < dayBegin
									.getTime()
							&& end.getTime() >= dayBegin.getTime() + 5 * 60 * 1000 && start
							.getTime() + 65 * 60 * 1000 > end.getTime())) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	/**
	 * 租赁开始时间到租赁结束时间大于5分钟并且"白天租赁使用小时数 "和"上下班时间与套餐时间之间使用小时数"都为0（当天）
	 * eg:17:26--17:34 中间17:30
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public boolean dateTimeOverFiveMinuteFashtion(Date startDate, Date endDate,
			float useHoursInDayTimes, float useHoursInDayAndWorkTimes) {
		try {
			// 租赁开始时间
			int startHours = startDate.getHours();
			int startMinutes = startDate.getMinutes();
			int startSeconds = startDate.getSeconds();

			// 租赁结束时间
			int finishHours = endDate.getHours();
			int finishMinutes = endDate.getMinutes();
			int finishSeconds = endDate.getSeconds();

			String startTime = startHours + ":" + startMinutes + ":"
					+ startSeconds;
			String finishTime = finishHours + ":" + finishMinutes + ":"
					+ finishSeconds;

			Date start = DateUtil.formatToTime(startTime);
			Date end = DateUtil.formatToTime(finishTime);

			if (useHoursInDayTimes == 0 && useHoursInDayAndWorkTimes == 0
					&& end.getTime() - start.getTime() > 5 * 60 * 1000) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	/**
	 * 判断该时间段是否在上下班时间与套餐时间之间
	 * 
	 * @param date
	 * @return
	 */
	public boolean dateTimeInDayAndWorkTimeFashtion(Date date) {
		try {
			// 上班时间-->套餐开始时间
			int beginHours = hoursPriceTypePO.getStartTimeForWork().getHours();
			int beginMinutes = hoursPriceTypePO.getStartTimeForWork()
					.getMinutes();
			int endHours = hoursPriceTypePO.getStartTimeForDay().getHours();
			int endMinutes = hoursPriceTypePO.getStartTimeForDay().getMinutes();
			int thisHours = date.getHours();
			int thisMinutes = date.getMinutes();
			if ((thisHours > beginHours && thisHours < endHours)
					|| (thisHours == beginHours && thisMinutes >= beginMinutes && thisHours < endHours)
					|| (thisHours > beginHours && thisMinutes <= endMinutes && thisHours == endHours)) {
				return true;
			}
			// 套餐结束时间-->下班时间
			int beginHours1 = hoursPriceTypePO.getEndTimeForDay().getHours();
			int beginMinutes1 = hoursPriceTypePO.getEndTimeForDay()
					.getMinutes();
			int endHours1 = hoursPriceTypePO.getEndTimeForWork().getHours();
			int endMinutes1 = hoursPriceTypePO.getEndTimeForWork().getMinutes();
			int thisHours1 = date.getHours();
			int thisMinutes1 = date.getMinutes();
			if ((thisHours1 > beginHours1 && thisHours1 < endHours1)
					|| (thisHours1 == beginHours1
							&& thisMinutes1 >= beginMinutes1 && thisHours1 < endHours1)
					|| (thisHours1 > beginHours1 && thisMinutes1 <= endMinutes1 && thisHours1 == endHours1)) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public Integer getUseDays() {
		return useDays;
	}

	public void setUseDays(Integer useDays) {
		this.useDays = useDays;
	}

	public float getUseHours() {
		return useHours;
	}

	public void setUseHours(float useHours) {
		this.useHours = useHours;
	}

	public Integer getNightTimes() {
		return nightTimes;
	}

	public void setNightTimes(Integer nightTimes) {
		this.nightTimes = nightTimes;
	}

	public float getUseHoursInDayTimes() {
		return useHoursInDayTimes;
	}

	public void setUseHoursInDayTimes(float useHoursInDayTimes) {
		this.useHoursInDayTimes = useHoursInDayTimes;
	}

	public Integer getSurplusMinutes() {
		return surplusMinutes;
	}

	public void setSurplusMinutes(Integer surplusMinutes) {
		this.surplusMinutes = surplusMinutes;
	}

	public float getUseHoursInDayAndWorkTimes() {
		return useHoursInDayAndWorkTimes;
	}

	public void setUseHoursInDayAndWorkTimes(float useHoursInDayAndWorkTimes) {
		this.useHoursInDayAndWorkTimes = useHoursInDayAndWorkTimes;
	}

	public Integer getSurMinutes() {
		return surMinutes;
	}

	public void setSurMinutes(Integer surMinutes) {
		this.surMinutes = surMinutes;
	}

}

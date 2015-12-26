package com.qx.eakay.help;

import java.util.Calendar;
import java.util.Date;

import com.qx.eakay.model.order.OrderPO;

/**
 * 租赁用车时间帮助类
 * 
 * @author admin
 * 
 */
public class LongUseTimeHelp {

	private OrderPO orderPO; // 租赁用车记录

	private Integer useMonths = 0; // 租赁使用月数
	private Integer useDays = 0; // 租赁使用天数
	private Integer useHours = 0; // 租赁使用小时数

	public LongUseTimeHelp(OrderPO orderPO) {
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
		current.add(Calendar.MONTH, 1);
		Date tempDate = current.getTime();

		while (tempDate.getTime() <= useEndTime.getTime()) {//超过一个月收费
			useMonths++;
			current.add(Calendar.MONTH, 1);
			tempDate = current.getTime();
		}

		current.add(Calendar.MONTH, -1);
		current.add(Calendar.HOUR, 24);
		tempDate = current.getTime();

		while (tempDate.getTime() <= useEndTime.getTime()) {//超过一天收费
			useDays++;
			current.add(Calendar.HOUR, 24);
			tempDate = current.getTime();
		}

		current.add(Calendar.HOUR, -24);
		current.add(Calendar.HOUR, 1);
		tempDate = current.getTime();

		while (tempDate.getTime() <= useEndTime.getTime()) {//超过一小时收费
			useHours++;
			current.add(Calendar.HOUR, 1);
			tempDate = current.getTime();
		}
		
	}
	
	public Integer getUseMonths() {
		return useMonths;
	}
	
	public void setUseMonths(Integer useMonths) {
		this.useMonths = useMonths;
	}

	public Integer getUseDays() {
		return useDays;
	}

	public void setUseDays(Integer useDays) {
		this.useDays = useDays;
	}

	public Integer getUseHours() {
		return useHours;
	}

	public void setUseHours(Integer useHours) {
		this.useHours = useHours;
	}


}

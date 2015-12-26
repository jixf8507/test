package com.qx.eakay.task;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qx.eakay.service.merchant.CouponService;
import com.qx.eakay.util.DateUtil;

@Component
public class CheckCouponTask {
	
	private static Logger logger = Logger.getLogger(CheckCouponTask.class);

	@Autowired
	private CouponService couponService;

	/**
	 * 取消租赁订单
	 */
	@Scheduled(cron = "0 5 0 * * ?")
//	@Scheduled(cron = "0 0/1 * * * *?")
	public void outCoupon() {
		logger.info("------------开始执行定时执行过期优惠券的任务");
		
		Timestamp currentDate= DateUtil.getMaxCurrentTime();	
		// 查找超时超出有效期限的优惠券
		List<Map<String, Object>> coupons = couponService.findOutTimeCoupon(currentDate);
		// 过期超时的优惠券
		couponService.outCoupon(coupons);

		logger.info("-----------结束执行定时执行过期优惠券的任务");
	}
	

}

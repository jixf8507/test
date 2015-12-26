package com.qx.eakay.task;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qx.eakay.service.stake.StakeRechargeCostService;

/**
 * 检查充电订单定时任务
 * 
 * @author sdf
 * @date 2015年12月3日
 */
@Component
public class CheckChargeOrderTask {
	private static Logger logger = Logger.getLogger(CheckChargeOrderTask.class);

	@Autowired
	private StakeRechargeCostService rechargeCostService;

	/**
	 * 取消充电订单
	 */
	@Scheduled(cron = "0 0/1 * * * *?")
	public void cancelChargeOrder() {
		logger.info("------------开始执行定时取消超时充电订单的任务");

		// 查找超时订单
		List<Map<String, Object>> orders = rechargeCostService.findOutTimeChargeOrder();
		// 取消超时（24h）的订单
		rechargeCostService.cancelChargeOrders(orders);

		logger.info("-----------结束执行定时取消超时充电订单的任务");
	}

}

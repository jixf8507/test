package com.qx.eakay.task;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qx.eakay.service.order.OrderService;

/**
 * 检查租赁订单定时任务
 * 
 * @author jixf
 * @date 2015年7月22日
 */
@Component
public class CheckOrderTask {
	private static Logger logger = Logger.getLogger(CheckOrderTask.class);

	@Autowired
	private OrderService orderService;

	/**
	 * 取消租赁订单
	 */
	@Scheduled(cron = "0 0/1 * * * *?")
	public void cancelOrder() {
		logger.info("------------开始执行定时取消超时订单的任务");
		
		// 查找超时订单
		List<Map<String, Object>> orders = orderService.findOutTimeOrder();
		if (null!=orders&&orders.size()>0) {
			// 取消超时的订单
			orderService.cancelOrders(orders);
		}
		logger.info("-----------结束执行定时取消超时订单的任务");
	}

}

package com.qx.eakay.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qx.eakay.socket.DeviceSocket;

/**
 * 定时连接车载设备控制中间件任务
 * 
 * @author jixf
 * @date 2015年7月23日
 */
@Component
public class CarDeviceLinkedTask {

	private static Logger logger = Logger.getLogger(CarDeviceLinkedTask.class);

	/**
	 * 取消租赁订单
	 */
	@Scheduled(cron = "0 0/1 * * * *?")
	public void request() {
		logger.info("------------开始执行定时获取车载设备中间件的车载设备监控数据的任务");

		DeviceSocket.monitoring();
	
		logger.info("-----------结束执行定时获取车载设备中间件的车载设备监控数据的任务");
	}

}

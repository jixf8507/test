package com.qx.eakay.task;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qx.eakay.service.car.CarDeviceService;

/**
 * 计算车载设备已还车状态续航里程定时任务
 * 
 * @author sdf
 * @date 2015年11月17日
 */
@Component
public class CountSurplusKmsTask {
	private static Logger logger = Logger.getLogger(CountSurplusKmsTask.class);

	@Autowired
	private CarDeviceService carDeviceService;

	@Scheduled(cron = "0 0/30 * * * *?")
	public void cancelOrder() {
		logger.info("------------开始执行定时计算已还车状态车载设备续航里程的任务");

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("currentCarStatus", "已还车");

		// 查找已还车状态的车载设备
		List<Map<String, Object>> returnCarDevices = carDeviceService
				.findDevices(jsonObject);
		// 计算车载设备续航里程（100%==160km）
		carDeviceService.countSurplusKms(returnCarDevices);

		logger.info("-----------结束执行定时计算已还车状态车载设备续航里程的任务");
	}

}

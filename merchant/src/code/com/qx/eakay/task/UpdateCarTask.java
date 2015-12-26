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
 * 修改车辆续航里程,soc定时任务
 * 车载设备正常状态
 * 
 * @author sdf
 * @date 2015年11月17日
 */
@Component
public class UpdateCarTask {
	private static Logger logger = Logger.getLogger(UpdateCarTask.class);

	@Autowired
	private CarDeviceService carDeviceService;

	/*@Scheduled(cron = "0 0/2 * * * *?")
	public void cancelOrder() {
		logger.info("------------开始执行定时修改设备正常车辆的续航里程,soc任务");

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("status", "正常");
		jsonObject.put("carTableName", "car");

		// 查找正常状态的车载设备
		List<Map<String, Object>> normalCarDevices = carDeviceService
				.findDevices(jsonObject);
		// 修改车辆续航里程,soc
		carDeviceService.updateCar(normalCarDevices);

		logger.info("-----------结束执行定时修改设备正常车辆的续航里程,soc任务");
	}
*/
}

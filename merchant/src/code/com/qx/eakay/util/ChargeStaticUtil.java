package com.qx.eakay.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.qx.common.bean.SystemConfig;

public class ChargeStaticUtil {

	// 超时时间
	public static int waitTime = SystemConfig.newInstance().getWaitTime();

	public static Map<String, Map<String, Object>> merchantDataMap = new HashMap<String, Map<String, Object>>();

	/**
	 * 根据租赁点查找设备状态
	 * 
	 * @param merchantId
	 * @return
	 */
	public static Map<String, Object> getMerchantDeviceStatus(Integer merchantId) {
		Date nowdate = new Date();
		Map<String, Object> res1 = merchantDataMap.get(merchantId + "");
		if (res1 != null) {
			Map<String, Object> devs = (Map<String, Object>) res1.get("devs");
			Map<String, Object> res3 = new HashMap<String, Object>();
			Date d = (Date) res1.get("time");
			boolean bool = ((nowdate.getTime() - d.getTime()) / 1000) > waitTime;
			for (String key : devs.keySet()) {
				try {
					Map<String, Object> device = (Map<String, Object>) devs
							.get(key);
					if (bool) {
						device.put("status", "");
					}
					res3.put(key, device);
				} catch (Exception e) {

				}
			}
			res3.put("time", res1.get("time"));
			return res3;
		}
		return new HashMap<>();
	}

}

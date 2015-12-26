package com.qx.eakay.socket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qx.common.app.BaseApplicationContext;
import com.qx.common.tools.GsonUtils;
import com.qx.common.tools.MyDateUtil;
import com.qx.eakay.dao.system.SysSettingDao;
import com.qx.eakay.model.monitor.CarContorlCmd;
import com.qx.eakay.model.monitor.CarModel;
import com.qx.eakay.model.monitor.Config;
import com.qx.eakay.model.monitor.KzServer;
import com.qx.eakay.util.AuthUtil;
import com.qx.eakay.util.MSG;
import com.qx.eakay.util.WebUtil;

/**
 * 车载无线网关通信
 * 
 * @author jixf
 * @2014-11-27
 */
public class DeviceSocket {
	private static Logger logger = Logger.getLogger(DeviceSocket.class);

	// 车载设备当前状态Map
	private static Map<String, CarModel> deviceNowMap = new HashMap<String, CarModel>();
	// 控制系统服务的当前状态
	private static Map<String, KzServer> kzxtMap = new HashMap<String, KzServer>();

	// 控制系统对象
	private static Map<String, String> controlServers = new HashMap<String, String>();
	private static final String isFetchStatusData = "true";
	private static final String format = "json";

	static {
		logger.info("DeviceSocket static start:");
		try {
			SysSettingDao settingDao = BaseApplicationContext.getBean(
					SysSettingDao.class, "settingDao");
			List<Map<String, Object>> sysSettingPOs = settingDao
					.findByKey("controllerSystemServer");
			for (Map<String, Object> info : sysSettingPOs) {
				final String ip = (info.get("value") + "").replace("http://",
						"").split(":")[0];
				controlServers.put(ip, info.get("value") + "");
			}
		} catch (NamingException e) {
			logger.error("DeviceSocket static error:", e);
		}
		logger.info("DeviceSocket static end:");
	}

	/**
	 * 监控所有车载无线网关的在线状态
	 */
	public static void monitoring() {
		Map<String, CarModel> deviceTempMap = new HashMap<String, CarModel>();
		Map<String, KzServer> kzxtTempMap = new HashMap<String, KzServer>();
		for (final String ip : controlServers.keySet()) {
			final String clientUrl = controlServers.get(ip);

			Map<String, String> params = createUrlParams(new CarContorlCmd(),
					isFetchStatusData);
			KzServer server = new KzServer(ip);
			logger.info("clientUrl==" + clientUrl + "  params== " + params);
			try {
				 String result = WebUtil.pageString(clientUrl, params);
//				HttpRequester request = new HttpRequester();
//				request.setDefaultContentEncoding("UTF-8");
//				HttpRespons hr = request.sendPost(clientUrl, params);
//				String result = hr.getContent();
				logger.info("clientUrl==" + clientUrl + "  params== " + params
						+ " result===  " + result);
				Map<String, Object> resMap = GsonUtils.parseJSON2Map(result);

				List<Map<String, Object>> devices = (List<Map<String, Object>>) resMap
						.get("modelList");
				for (Map<String, Object> device : devices) {
					String deviceNO = device.get("DeviceNO") + "";
					deviceTempMap.put(deviceNO, new CarModel(device));
				}
				Map<String, Object> xnDate = (Map<String, Object>) resMap
						.get("xnDate");
				server.setRate(xnDate);
				kzxtTempMap.put(ip, server);
			} catch (Exception e) {
				 e.printStackTrace();
				logger.warn("无线网关通信失败，设备离线！");
			}
		}
		deviceNowMap = deviceTempMap;
		kzxtMap = kzxtTempMap;
	}

	/**
	 * 发送车辆控制命令
	 * 
	 * @param carContorlCmd
	 * @return
	 */
	public static MSG sendCarContorlCmd(Map<String, Object> map) {
		MSG msg = new MSG();
		String info = "发送控制命令失败";
		for (final String ip : controlServers.keySet()) {
			final String clientUrl = controlServers.get(ip);
			Map<String, String> params = createUrlParams(map, "false");

			try {
				String result = WebUtil.pageString(clientUrl, params);
				Map<String, Object> msgInfo = GsonUtils.parseJSON2Map(result);
				Map<String, Object> data = (Map<String, Object>) msgInfo
						.get("msg");
				String success = data.get("success") + "";
				info = data.get("info") + "";

				if (success.equals("true")) {
					msg.setInfo(info);
					msg.setSuccess(true);
				} else {
					msg.setInfo(info);
					msg.setSuccess(false);
				}
				// System.out.println(".........."+result);
				// Gson gson = new Gson();
				// msg = gson.fromJson(result, MSG.class);
				return msg;
			} catch (Exception e) {
				logger.error(e.getMessage());
				msg.setInfo(info);
			}
		}
		msg.setSuccess(false);
		msg.setInfo(info);
		return msg;
	}

	/**
	 * 创建控制命令访问的参数
	 * 
	 * @return
	 */
	private static Map<String, String> createUrlParams(CarContorlCmd cmd,
			String isFetchStatus) {
		Map<String, String> params = new HashMap<String, String>();
		String timestamp = MyDateUtil.getCurrentTime();
		// CarContorlCmd cmd = new CarContorlCmd();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gson.toJson(cmd);

		params.put("data", data);
		params.put("isFetchStatusData", isFetchStatus);
		params.put("client_type", Config.CLIENT_TYPE_ANDROID);
		params.put("app_key", Config.APP_KEY);
		params.put("timestamp", timestamp);
		params.put("format", format);
		String sign = AuthUtil.sortAndJoint(params);
		params.put("sign", sign);
		params.put("test", "true");
		return params;
	}

	/**
	 * 创建控制命令访问的参数
	 * 
	 * @return
	 */
	private static Map<String, String> createUrlParams(Map<String, Object> map,
			String isFetchStatus) {
		Map<String, String> params = new HashMap<String, String>();
		String timestamp = MyDateUtil.getCurrentTime();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		for (String key : map.keySet()) {
			String value = gson.toJson(map.get(key));
			params.put(key, value);
		}
		params.put("isFetchStatusData", isFetchStatus);
		params.put("client_type", Config.CLIENT_TYPE_MANAGER);
		params.put("app_key", Config.APP_KEY);
		params.put("timestamp", timestamp);
		params.put("format", format);
		String sign = AuthUtil.sortAndJoint(params);
		params.put("sign", sign);
		// params.put("test", "true");
		return params;
	}

	/**
	 * 得到当前的所有设备状态
	 * 
	 * @return
	 */
	public static Map<String, Object> getDeviceStatusMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceNowMap", deviceNowMap);
		map.put("kzxtMap", kzxtMap);
		return map;
	}

	public static Map<String, CarModel> getDeviceNowMap() {
		return deviceNowMap;
	}

	/**
	 * 得到当前控制系统的运行状态
	 * 
	 * @return
	 */
	public static Map<String, String> getControlServers() {
		return controlServers;
	}

}

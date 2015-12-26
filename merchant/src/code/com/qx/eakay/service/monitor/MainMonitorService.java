package com.qx.eakay.service.monitor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.tools.MyDateUtil;
import com.qx.eakay.dao.car.CarDao;
import com.qx.eakay.dao.order.OrderDao;
import com.qx.eakay.dao.stake.StakeDeviceNameDao;
import com.qx.eakay.model.car.CarPO.CarStatus;
import com.qx.eakay.model.monitor.SiteResoucePO;
import com.qx.eakay.model.order.OrderPO.OrderStatus;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.ChargeStaticUtil;

/**
 * 
 * @author jixf
 * @date 2015年7月17日
 */
@Service
public class MainMonitorService extends BaseService {

	@Autowired
	private CarDao carDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private StakeDeviceNameDao deviceNameDao;

	/**
	 * 
	 * @param jsonObject
	 * @return
	 */
	public Map<String, SiteResoucePO> sitesResouces(JSONObject jsonObject) {
		Map<String, SiteResoucePO> siteMap = new HashMap<>();

		List<Map<String, Object>> cars = carDao.findCarDevices(jsonObject);
		for (Map<String, Object> car : cars) {
			String siteId = car.get("curSiteId") + "";
			String status = car.get("status") + "";
			String deviceId = car.get("carId") + "";
			String deviceStatus = car.get("dStatus") + "";
			SiteResoucePO sr = siteMap.get(siteId);
			if (sr == null) {
				sr = new SiteResoucePO();
				siteMap.put(siteId, sr);
			}
			sr.setTotleCar(sr.getTotleCar() + 1);
			if (CarStatus.空闲.name().equals(status)) {
				sr.setWaitCar(sr.getWaitCar() + 1);
			} else if (CarStatus.故障.name().equals(status)) {
				sr.setFaultCar(sr.getFaultCar() + 1);
			} else if (CarStatus.其他.name().equals(status)) {
				sr.setOtherCar(sr.getOtherCar() + 1);
			} else if (CarStatus.充电.name().equals(status)) {
				sr.setChargeCar(sr.getChargeCar() + 1);
			}
			if (StringUtils.isNotBlank(deviceId)) {
				sr.setCarDevice(sr.getCarDevice() + 1);
				if ("正常".equals(deviceStatus)) {
					sr.setOnlineDevice(sr.getOnlineDevice() + 1);
				} else {
					sr.setOutlineDevice(sr.getOutlineDevice() + 1);
				}
			}
		}

		List<Map<String, Object>> orders = orderDao
				.monitorSiteOrder(jsonObject);
		for (Map<String, Object> order : orders) {
			String siteId = order.get("siteId") + "";
			String orderStatus = order.get("orderStatus") + "";
			String carConfirm = order.get("carConfirm") + "";
			String orderType = order.get("orderType") + "";
			SiteResoucePO sr = siteMap.get(siteId);
			if (sr == null) {
				sr = new SiteResoucePO();
				siteMap.put(siteId, sr);
			}
			if ("2".equals(orderType) && !"已关门".equals(carConfirm)) {
				sr.setChargeOrder(sr.getChargeOrder() + 1);
			}
			if (("3".equals(orderType) || "4".equals(orderType))
					&& (OrderStatus.已取车.name().equals(orderStatus))) {
				sr.setMerchantUseOrder(sr.getMerchantUseOrder() + 1);
			}
			if ("1".equals(orderType)
					&& OrderStatus.已取车.name().equals(orderStatus)) {
				sr.setGetOrder(sr.getGetOrder() + 1);
			}
			if (OrderStatus.已预约.name().equals(orderStatus)) {
				sr.setBookOrder(sr.getBookOrder() + 1);
			}
			if (OrderStatus.已还车.name().equals(orderStatus)
					&& !"已关门".equals(carConfirm) && !"2".equals(orderType)) {
				sr.setReturnOrder(sr.getReturnOrder() + 1);
			}

		}

		List<Map<String, Object>> devices = deviceNameDao
				.findDevices(jsonObject);
		Integer merchantId = (Integer) jsonObject.get("merchantId");
		Map<String, Object> merchantDeviceStatus = ChargeStaticUtil
				.getMerchantDeviceStatus(merchantId);
		for (Map<String, Object> device : devices) {
			String siteId = device.get("site_code") + "";
			String factoryNo = device.get("factoryNo") + "";

			SiteResoucePO sr = siteMap.get(siteId);
			if (sr == null) {
				sr = new SiteResoucePO();
				siteMap.put(siteId, sr);
			}
			sr.setTotleStake(sr.getTotleStake() + 1);
			Map<String, Object> deviceStatus = (Map<String, Object>) merchantDeviceStatus
					.get(factoryNo);

			if (deviceStatus != null) {
				String status = deviceStatus.get("status") + "";
				if (status.equals("LostConnect")) {
					sr.setFaultStake(sr.getFaultStake() + 1);
				} else if ("Standby".equals(status)
						|| "UserUsing".equals(status)) {
					sr.setWaitStake(sr.getWaitStake() + 1);
				} else if ("Charging".equals(status)
						|| "Parking".equals(status)) {
					sr.setChargeStake(sr.getChargeStake() + 1);
				} else {
					sr.setFaultStake(sr.getFaultStake() + 1);
				}
			} else {
				sr.setOutLineStake(sr.getOutLineStake() + 1);
			}
		}
		return siteMap;
	}

	/**
	 * 统计租赁行驶公里数
	 * 
	 * @param jsonObject
	 * @return
	 */
	public Double statisticsOrderKms(JSONObject jsonObject) {
		List<Map<String, Object>> list = orderDao
				.statisticsOrderKms(jsonObject);
		if (list == null || list.isEmpty()) {
			return 0D;
		}

		Object obj = list.get(0).get("kms");
		return obj == null ? 0d : Double.parseDouble(obj + "");
	}

	public List<Map<String, Object>> statisticsDayOrderKms(JSONObject jsonObject) {
		List<Map<String, Object>> list = orderDao
				.statisticsDayOrderKms(jsonObject);
		List<Map<String, Object>> dataListAdd = new ArrayList<Map<String, Object>>();

		try {
			// 开始日期（今日前一个月）
			String beginTime = jsonObject.get("beginTime") + "";
			Date start = MyDateUtil.StrToDateyMd(beginTime);
			Calendar current = Calendar.getInstance();
			current.setTime(start);
			Date tempDate = current.getTime();
			// 结束日期（今日）
			Calendar currentEnd = Calendar.getInstance();
			Date end = currentEnd.getTime();

			for (Map<String, Object> dataMap : list) {
				String days = dataMap.get("day") + "";
				Date day = MyDateUtil.StrToDateyMd(days);

				while (tempDate.getTime() < day.getTime()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("kms", 0.0);
					map.put("day", MyDateUtil.getDayString(tempDate));
					dataListAdd.add(map);
					current.add(Calendar.HOUR, 24);
					tempDate = current.getTime();
				}
				current.add(Calendar.HOUR, 24);
				tempDate = current.getTime();
			}

			while (tempDate.getTime() < end.getTime()) {
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				map1.put("kms", 0.0);
				map1.put("day", MyDateUtil.getDayString(tempDate));
				dataListAdd.add(map1);
				current.add(Calendar.HOUR, 24);
				tempDate = current.getTime();
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		list.addAll(dataListAdd);
		// System.out.println("排序前  :"+list);

		// 排序
		List<Map<String, Object>> listMap = new LinkedList<Map<String, Object>>();
		Set<Map> setMap = new HashSet<Map>();
		for (Map<String, Object> map1 : list) {
			if (setMap.add(map1)) {
				listMap.add(map1);
			}
		}
		Collections.sort(listMap, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> map1,
					Map<String, Object> map2) {
				return map1.get("day").toString()
						.compareTo(map2.get("day").toString());
			}
		});
		// System.out.println("排序后  :"+listMap);
		return listMap;
	}

}

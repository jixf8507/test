package com.qx.eakay.service.statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.highcharts.ChartsBO;
import com.qx.common.highcharts.CommonSerieBO;
import com.qx.common.highcharts.PieSerieBO;
import com.qx.common.highcharts.PointData;
import com.qx.common.highcharts.SerieBO.SerieType;
import com.qx.eakay.dao.statistics.StatisticsTimeDao;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.DateUtil;

/**
 * 租赁类型统计
 * 
 * @author sdf
 * @date 2015年11月6日
 */
@Service
public class StatisticsTimeService extends BaseService {

	@Autowired
	private StatisticsTimeDao timeDao;

	/**
	 * 租赁次数分时段统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<PointData> statisticsSiteCountPie(JSONObject jsonObject) {
		ChartsBO<PointData> chartsBO = new ChartsBO<PointData>();
		PieSerieBO pieSerieBO = new PieSerieBO("租赁次数分时段统计比例图");
		pieSerieBO.setType(SerieType.pie);
		List<Map<String, Object>> list = timeDao.statisticsSiteTime(jsonObject);

		List<Map<String, Object>> data = useCountByTimeHelp(list);

		for (Map<String, Object> dataMap : data) {
			PointData pointData = new PointData(dataMap.get("name").toString());
			pointData.setY(Integer.parseInt(dataMap.get("count").toString()));
			pieSerieBO.getData().add(pointData);

		}
		chartsBO.getSeries().add(pieSerieBO);
		return chartsBO;
	}

	/**
	 * 租赁次数分时段统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsSiteCostColumn(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> list = timeDao.statisticsSiteTime(jsonObject);
		CommonSerieBO serieBO = new CommonSerieBO("租赁次数");

		List<Map<String, Object>> data = useCountByTimeHelp(list);

		for (Map<String, Object> dataMap : data) {
			serieBO.getData().add(
					new BigDecimal(Integer.parseInt(dataMap.get("count")
							.toString())));
			chartsBO.getxAxis().getCategories()
					.add(dataMap.get("name").toString());
		}

		chartsBO.getSeries().add(serieBO);
		return chartsBO;
	}

	/**
	 * 获取分时段租赁次数
	 * 
	 * @param list
	 * @return
	 */
	private List<Map<String, Object>> useCountByTimeHelp(
			List<Map<String, Object>> list) {
		Integer yearCount = 0; // 1年以上
		Integer halfYearCount = 0; // 半年---1年
		Integer monthCount = 0; // 1月---半年
		Integer dayCount = 0; // 1天---1月
		Integer hourCount = 0; // 1小时---1天
		Integer minuteCount = 0; // 1分钟---1小时
		for (Map<String, Object> data : list) {
			Date beginTime = DateUtil.formatToDateM(data.get("beginTime")
					.toString());
			Date endTime = DateUtil.formatToDateM(data.get("endTime")
					.toString());

			Calendar current = Calendar.getInstance();
			current.setTime(beginTime);

			current.add(Calendar.YEAR, 1);
			Date tempYear = current.getTime();

			current.add(Calendar.YEAR, -1);
			current.add(Calendar.MONTH, 6);
			Date tempHalfYear = current.getTime();

			current.add(Calendar.MONTH, -6);
			current.add(Calendar.MONTH, 1);
			Date tempMonth = current.getTime();

			current.add(Calendar.MONTH, -1);
			current.add(Calendar.HOUR, 24);
			Date tempDay = current.getTime();

			current.add(Calendar.HOUR, -24);
			current.add(Calendar.HOUR, 1);
			Date tempHour = current.getTime();

			if (tempHour.getTime() >= endTime.getTime()) {
				minuteCount++;
			}
			if (tempDay.getTime() >= endTime.getTime()
					&& tempHour.getTime() < endTime.getTime()) {
				hourCount++;
			}
			if (tempMonth.getTime() >= endTime.getTime()
					&& tempDay.getTime() < endTime.getTime()) {
				dayCount++;
			}
			if (tempHalfYear.getTime() >= endTime.getTime()
					&& tempMonth.getTime() < endTime.getTime()) {
				monthCount++;
			}
			if (tempYear.getTime() >= endTime.getTime()
					&& tempHalfYear.getTime() < endTime.getTime()) {
				halfYearCount++;
			}
			if (tempYear.getTime() <= endTime.getTime()) {
				yearCount++;
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", minuteCount);
		map.put("name", "1小时以下");

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("count", hourCount);
		map1.put("name", "1小时 - 1天");

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("count", dayCount);
		map2.put("name", "1天 - 1月");

		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("count", monthCount);
		map3.put("name", "1月 - 半年");

		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("count", halfYearCount);
		map4.put("name", "半年 - 1年");

		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("count", yearCount);
		map5.put("name", "1年以上");

		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		dataMap.add(0, map);
		dataMap.add(1, map1);
		dataMap.add(2, map2);
		dataMap.add(3, map3);
		dataMap.add(4, map4);
		dataMap.add(5, map5);

		return dataMap;
	}

}

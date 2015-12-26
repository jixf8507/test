package com.qx.eakay.service.statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.qx.eakay.dao.statistics.StatisticsTypeDao;
import com.qx.eakay.service.BaseService;

/**
 * 租赁类型统计
 * 
 * @author sdf
 * @date 2015年11月6日
 */
@Service
public class StatisticsTypeService extends BaseService {

	@Autowired
	private StatisticsTypeDao typeDao;

	/**
	 * 租赁类型统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<PointData> statisticsSiteCountPie(JSONObject jsonObject) {
		ChartsBO<PointData> chartsBO = new ChartsBO<PointData>();
		PieSerieBO pieSerieBO = new PieSerieBO("租赁类型统计比例图");
		pieSerieBO.setType(SerieType.pie);
		List<Map<String, Object>> list = typeDao.statisticsSiteType(jsonObject);

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
	 * 租赁类型统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsSiteCostColumn(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> list = typeDao.statisticsSiteType(jsonObject);
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
		Integer dayCount = 0; // 日租
		Integer nightCount = 0; // 夜租
		Integer hoursCount = 0; // 时租

		for (Map<String, Object> data : list) {
			Integer hours = Integer.parseInt(data.get("hours") + "");
			Integer cs = Integer.parseInt(data.get("cs") + "");

			if (hours >= 24) {
				dayCount = dayCount + cs;
			} else if (hours > 9) {
				nightCount = nightCount + cs;
			} else {
				hoursCount = hoursCount + cs;
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", dayCount);
		map.put("name", "日租");

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("count", nightCount);
		map1.put("name", "夜租");

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("count", hoursCount);
		map2.put("name", "时租");

		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		dataMap.add(0, map2);
		dataMap.add(1, map1);
		dataMap.add(2, map);

		return dataMap;
	}

}

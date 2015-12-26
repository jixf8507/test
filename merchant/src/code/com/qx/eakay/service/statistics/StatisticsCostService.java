package com.qx.eakay.service.statistics;

import java.math.BigDecimal;
import java.text.ParseException;
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
import com.qx.common.highcharts.TimeSerieBO;
import com.qx.common.tools.MyDateUtil;
import com.qx.eakay.dao.order.OrderDao;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Service
public class StatisticsCostService extends BaseService {

	@Autowired
	private OrderDao orderDao;

	/**
	 * 商家每日收益统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsDayCost(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> dayCosts = orderDao.statisticsDay(jsonObject);
		List<Map<String, Object>> dayGetCs = orderDao.statisticsDayGet(jsonObject);
		TimeSerieBO costSerieBO = new TimeSerieBO("每日收益");
		TimeSerieBO csSerieBO = new TimeSerieBO("还车次数");
		TimeSerieBO getCsSerieBO = new TimeSerieBO("取车次数");
		costSerieBO.setType(SerieType.area);
		csSerieBO.setType(SerieType.spline);
		getCsSerieBO.setType(SerieType.spline);
		csSerieBO.setyAxis(1);
		getCsSerieBO.setyAxis(1);
		Map<String, BigDecimal> costMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> csMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> getCsMap = new HashMap<String, BigDecimal>();
		try {
			String beginTime = jsonObject.get("beginTime") + "";
			String endTime = jsonObject.get("endTime") + "";
			for (int i = 0; i < dayCosts.size(); i++) {
				Map<String, Object> seriesMap = dayCosts.get(i);
				String tempDay = seriesMap.get("day") + "";
				costMap.put(tempDay, new BigDecimal(seriesMap.get("cost") + ""));
				csMap.put(tempDay, new BigDecimal(seriesMap.get("cs") + ""));
			}
			for (int i = 0; i < dayGetCs.size(); i++) {
				Map<String, Object> seriesMap = dayGetCs.get(i);
				String tempDay = seriesMap.get("day") + "";
				getCsMap.put(tempDay, new BigDecimal(seriesMap.get("cs") + ""));
			}

			Date todayDate = MyDateUtil.StrToDateyMdhms(endTime);   //结束日期采用对应的时分秒
			Date tempDate = MyDateUtil.StrToDateyMd(beginTime);
			tempDate = MyDateUtil.getDaytoCurrDay(tempDate, 1);
			costSerieBO.setPointStart(tempDate.getTime());
			csSerieBO.setPointStart(tempDate.getTime());
			getCsSerieBO.setPointStart(tempDate.getTime());
			tempDate = MyDateUtil.getDaytoCurrDay(tempDate, -1);
			while (tempDate.getTime() < todayDate.getTime()) {
				String tempDay = MyDateUtil.getDayString(tempDate);
				costSerieBO.getData().add(
						this.getBigDecimal(costMap.get(tempDay)));
				csSerieBO.getData().add(this.getBigDecimal(csMap.get(tempDay)));
				getCsSerieBO.getData().add(this.getBigDecimal(getCsMap.get(tempDay)));
				tempDate = MyDateUtil.getDaytoCurrDay(tempDate, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		chartsBO.getSeries().add(costSerieBO);		
		chartsBO.getSeries().add(getCsSerieBO);
		chartsBO.getSeries().add(csSerieBO);
		return chartsBO;
	}

	/**
	 * 商家租赁点的收益统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsSiteCost(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> list = orderDao.statisticsSite(jsonObject);
		CommonSerieBO serieBO = new CommonSerieBO("租赁点");
		for (Map<String, Object> dataMap : list) {
			String siteName = dataMap.get("siteName") + "";
			BigDecimal cost = this.getBigDecimal(dataMap.get("cost"));
			serieBO.getData().add(cost);
			chartsBO.getxAxis().getCategories().add(siteName);
		}
		chartsBO.getSeries().add(serieBO);
		return chartsBO;
	}

	/**
	 * 商家租赁点租赁次数统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<PointData> statisticsSiteCount(JSONObject jsonObject) {
		ChartsBO<PointData> chartsBO = new ChartsBO<PointData>();
		PieSerieBO pieSerieBO = new PieSerieBO("租赁点租赁次数比例");
		pieSerieBO.setType(SerieType.pie);
		List<Map<String, Object>> list = orderDao.statisticsSite(jsonObject);
		for (Map<String, Object> dataMap : list) {
			PointData pointData = new PointData(dataMap.get("siteName") + "");
			pointData.setY(Integer.parseInt(dataMap.get("cs") + ""));
			pieSerieBO.getData().add(pointData);
		}
		chartsBO.getSeries().add(pieSerieBO);
		return chartsBO;
	}

	/**
	 * 商家每月的租赁收费统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsMonthCost(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> dayCosts = orderDao
				.statisticsMonth(jsonObject);
		CommonSerieBO serieBO = new CommonSerieBO("月收费");
		for (Map<String, Object> serieMap : dayCosts) {
			String month = serieMap.get("month") + "";
			BigDecimal cost = this.getBigDecimal(serieMap.get("cost"));
			serieBO.getData().add(cost);
			chartsBO.getxAxis().getCategories().add(month);
		}
		chartsBO.getSeries().add(serieBO);
		return chartsBO;
	}
}

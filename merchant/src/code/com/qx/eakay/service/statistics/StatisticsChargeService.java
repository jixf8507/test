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
import com.qx.eakay.dao.stake.StakeChargeDao;
import com.qx.eakay.service.BaseService;

/**
 * 充电收费统计
 * 
 * @author sdf
 * @date 2015年9月30日
 */
@Service
public class StatisticsChargeService extends BaseService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private StakeChargeDao stakeChargeDao;

	/**
	 * 商家每月充电收费统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsMonthCost(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> dayCosts = stakeChargeDao
				.statisticsMonth(jsonObject);
		CommonSerieBO costSerieBO = new CommonSerieBO("月收费");
		CommonSerieBO csSerieBO = new CommonSerieBO("充电次数");
		costSerieBO.setType(SerieType.area);
		csSerieBO.setType(SerieType.spline);
		csSerieBO.setyAxis(1);
		Map<String, BigDecimal> costMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> csMap = new HashMap<String, BigDecimal>();

		for (int i = 0; i < dayCosts.size(); i++) {
			Map<String, Object> seriesMap = dayCosts.get(i);
			String month = seriesMap.get("month") + "";
			costMap.put(month, new BigDecimal(seriesMap.get("cost") + ""));
			csMap.put(month, new BigDecimal(seriesMap.get("cs") + ""));

			costSerieBO.getData().add(this.getBigDecimal(costMap.get(month)));
			csSerieBO.getData().add(this.getBigDecimal(csMap.get(month)));

			chartsBO.getxAxis().getCategories().add(month);
		}

		chartsBO.getSeries().add(costSerieBO);
		chartsBO.getSeries().add(csSerieBO);
		return chartsBO;
	}

	/**
	 * 商家每日充电收费统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsDayCost(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> dayCosts = stakeChargeDao
				.statisticsDay(jsonObject);
		TimeSerieBO costSerieBO = new TimeSerieBO("日收费");
		TimeSerieBO csSerieBO = new TimeSerieBO("充电次数");
		costSerieBO.setType(SerieType.area);
		csSerieBO.setType(SerieType.spline);
		csSerieBO.setyAxis(1);
		Map<String, BigDecimal> costMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> csMap = new HashMap<String, BigDecimal>();
		try {
			String beginTime = jsonObject.get("beginTime") + "";
			String endTime = jsonObject.get("endTime") + "";
			for (int i = 0; i < dayCosts.size(); i++) {
				Map<String, Object> seriesMap = dayCosts.get(i);
				String tempDay = seriesMap.get("day") + "";
				costMap.put(tempDay, new BigDecimal(seriesMap.get("cost") + ""));
				csMap.put(tempDay, new BigDecimal(seriesMap.get("cs") + ""));
			}

			Date todayDate = MyDateUtil.StrToDateyMd(endTime);
			Date tempDate = MyDateUtil.StrToDateyMd(beginTime);
			tempDate = MyDateUtil.getDaytoCurrDay(tempDate, 1);
			costSerieBO.setPointStart(tempDate.getTime());
			csSerieBO.setPointStart(tempDate.getTime());
			tempDate = MyDateUtil.getDaytoCurrDay(tempDate, -1);
			while (tempDate.getTime() <= todayDate.getTime()) {
				String tempDay = MyDateUtil.getDayString(tempDate);
				costSerieBO.getData().add(
						this.getBigDecimal(costMap.get(tempDay)));
				csSerieBO.getData().add(this.getBigDecimal(csMap.get(tempDay)));
				tempDate = MyDateUtil.getDaytoCurrDay(tempDate, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		chartsBO.getSeries().add(costSerieBO);
		chartsBO.getSeries().add(csSerieBO);
		return chartsBO;
	}

	/**
	 * 商家充电站充电收费统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsSiteCost(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> list = stakeChargeDao
				.statisticsSite(jsonObject);
		CommonSerieBO serieBO = new CommonSerieBO("充电站");
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
	 * 商家充电站充电次数统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<PointData> statisticsSiteCount(JSONObject jsonObject) {
		ChartsBO<PointData> chartsBO = new ChartsBO<PointData>();
		PieSerieBO pieSerieBO = new PieSerieBO("充电次数比例");
		pieSerieBO.setType(SerieType.pie);
		List<Map<String, Object>> list = stakeChargeDao
				.statisticsSite(jsonObject);
		for (Map<String, Object> dataMap : list) {
			PointData pointData = new PointData(dataMap.get("siteName") + "");
			pointData.setY(Integer.parseInt(dataMap.get("cs") + ""));
			pieSerieBO.getData().add(pointData);
		}
		chartsBO.getSeries().add(pieSerieBO);
		return chartsBO;
	}
	
	
	
	

	/**
	 * 商家充电站充电电量统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsSiteElec(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> list = stakeChargeDao
				.statisticsSite(jsonObject);
		CommonSerieBO serieBO = new CommonSerieBO("充电电量");
		for (Map<String, Object> dataMap : list) {
			String siteName = dataMap.get("siteName") + "";
			BigDecimal cost = this.getBigDecimal(dataMap.get("eq"));
			serieBO.getData().add(cost);
			chartsBO.getxAxis().getCategories().add(siteName);
		}
		chartsBO.getSeries().add(serieBO);
		return chartsBO;
	}
	
	/**
	 * 商家充电站不同充电方式充电电量统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsKindElec(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> list = stakeChargeDao
				.statisticsSiteKind(jsonObject);
		CommonSerieBO serieBO = new CommonSerieBO("充电电量");
		for (Map<String, Object> dataMap : list) {
			String Kind = dataMap.get("Kind") + "";
			BigDecimal cost = this.getBigDecimal(dataMap.get("eq"));
			serieBO.getData().add(cost);
			chartsBO.getxAxis().getCategories().add(Kind);
		}
		chartsBO.getSeries().add(serieBO);
		return chartsBO;
	}
	
	/**
	 * 商家每月充电电量统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> ajaxMonthElec(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> dayCosts = stakeChargeDao
				.statisticsMonthElec(jsonObject);
		CommonSerieBO costSerieBO = new CommonSerieBO("月充电电量");
		CommonSerieBO csSerieBO = new CommonSerieBO("充电次数");
		costSerieBO.setType(SerieType.area);
		csSerieBO.setType(SerieType.spline);
		csSerieBO.setyAxis(1);
		Map<String, BigDecimal> costMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> csMap = new HashMap<String, BigDecimal>();

		for (int i = 0; i < dayCosts.size(); i++) {
			Map<String, Object> seriesMap = dayCosts.get(i);
			String month = seriesMap.get("month") + "";
			costMap.put(month, new BigDecimal(seriesMap.get("eq") + ""));
			csMap.put(month, new BigDecimal(seriesMap.get("cs") + ""));

			costSerieBO.getData().add(this.getBigDecimal(costMap.get(month)));
			csSerieBO.getData().add(this.getBigDecimal(csMap.get(month)));

			chartsBO.getxAxis().getCategories().add(month);
		}

		chartsBO.getSeries().add(costSerieBO);
		chartsBO.getSeries().add(csSerieBO);
		return chartsBO;
	}

	/**
	 * 商家每日充电电量统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> ajaxDayElec(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> dayCosts = stakeChargeDao
				.statisticsDay(jsonObject);
		TimeSerieBO costSerieBO = new TimeSerieBO("日充电电量");
		TimeSerieBO csSerieBO = new TimeSerieBO("充电次数");
		costSerieBO.setType(SerieType.area);
		csSerieBO.setType(SerieType.spline);
		csSerieBO.setyAxis(1);
		Map<String, BigDecimal> costMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> csMap = new HashMap<String, BigDecimal>();
		try {
			String beginTime = jsonObject.get("beginTime") + "";
			String endTime = jsonObject.get("endTime") + "";
			for (int i = 0; i < dayCosts.size(); i++) {
				Map<String, Object> seriesMap = dayCosts.get(i);
				String tempDay = seriesMap.get("day") + "";
				costMap.put(tempDay, new BigDecimal(seriesMap.get("eq") + ""));
				csMap.put(tempDay, new BigDecimal(seriesMap.get("cs") + ""));
			}

			Date todayDate = MyDateUtil.StrToDateyMd(endTime);
			Date tempDate = MyDateUtil.StrToDateyMd(beginTime);
			tempDate = MyDateUtil.getDaytoCurrDay(tempDate, 1);
			costSerieBO.setPointStart(tempDate.getTime());
			csSerieBO.setPointStart(tempDate.getTime());
			tempDate = MyDateUtil.getDaytoCurrDay(tempDate, -1);
			while (tempDate.getTime() <= todayDate.getTime()) {
				String tempDay = MyDateUtil.getDayString(tempDate);
				costSerieBO.getData().add(
						this.getBigDecimal(costMap.get(tempDay)));
				csSerieBO.getData().add(this.getBigDecimal(csMap.get(tempDay)));
				tempDate = MyDateUtil.getDaytoCurrDay(tempDate, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		chartsBO.getSeries().add(costSerieBO);
		chartsBO.getSeries().add(csSerieBO);
		return chartsBO;
	}
}

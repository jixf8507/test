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
import com.qx.common.highcharts.PieSerieBO;
import com.qx.common.highcharts.PointData;
import com.qx.common.highcharts.SerieBO.SerieType;
import com.qx.common.highcharts.TimeSerieBO;
import com.qx.common.tools.MyDateUtil;
import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年10月7日
 */
@Service
public class StatisticsCustomerService extends BaseService {

	@Autowired
	private CustomerDao customerDao;

	/**
	 * 商家租赁点租赁次数统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<PointData> statisticsSiteCount(JSONObject jsonObject) {
		ChartsBO<PointData> chartsBO = new ChartsBO<PointData>();
		PieSerieBO pieSerieBO = new PieSerieBO("租赁点办卡数量比例");
		pieSerieBO.setType(SerieType.pie);
		List<Map<String, Object>> list = customerDao
				.statisticsSiteCount(jsonObject);
		for (Map<String, Object> dataMap : list) {
			PointData pointData = new PointData(dataMap.get("siteName") + "");
			pointData.setY(Integer.parseInt(dataMap.get("cs") + ""));
			pieSerieBO.getData().add(pointData);
		}
		chartsBO.getSeries().add(pieSerieBO);
		return chartsBO;
	}
	
	/**
	 * 商家租赁点注销卡次数统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<PointData> statisticsSiteCountCancle(JSONObject jsonObject) {
		ChartsBO<PointData> chartsBO = new ChartsBO<PointData>();
		PieSerieBO pieSerieBO = new PieSerieBO("租赁点注销卡数量比例");
		pieSerieBO.setType(SerieType.pie);
		List<Map<String, Object>> list = customerDao
				.statisticsSiteCountByCancel(jsonObject);
		for (Map<String, Object> dataMap : list) {
			PointData pointData = new PointData(dataMap.get("siteName") + "");
			pointData.setY(Integer.parseInt(dataMap.get("cs") + ""));
			pieSerieBO.getData().add(pointData);
		}
		chartsBO.getSeries().add(pieSerieBO);
		return chartsBO;
	}
	
	
	

	/**
	 * 商家每日收益统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<BigDecimal> statisticsDayCount(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		List<Map<String, Object>> dayCosts = customerDao
				.statisticsDay(jsonObject);
		TimeSerieBO csSerieBO = new TimeSerieBO("办卡数");
		csSerieBO.setType(SerieType.spline);		
		Map<String, BigDecimal> csMap = new HashMap<String, BigDecimal>();
		try {
			String beginTime = jsonObject.get("beginTime") + "";
			String endTime = jsonObject.get("endTime") + "";
			for (int i = 0; i < dayCosts.size(); i++) {
				Map<String, Object> seriesMap = dayCosts.get(i);
				String tempDay = seriesMap.get("day") + "";				
				csMap.put(tempDay, new BigDecimal(seriesMap.get("cs") + ""));
			}

			Date todayDate = MyDateUtil.StrToDateyMd(endTime);
			Date tempDate = MyDateUtil.StrToDateyMd(beginTime);
			tempDate = MyDateUtil.getDaytoCurrDay(tempDate, 1);

			csSerieBO.setPointStart(tempDate.getTime());
			tempDate = MyDateUtil.getDaytoCurrDay(tempDate, -1);
			while (tempDate.getTime() <= todayDate.getTime()) {
				String tempDay = MyDateUtil.getDayString(tempDate);

				csSerieBO.getData().add(this.getBigDecimal(csMap.get(tempDay)));
				tempDate = MyDateUtil.getDaytoCurrDay(tempDate, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		chartsBO.getSeries().add(csSerieBO);
		return chartsBO;
	}

}

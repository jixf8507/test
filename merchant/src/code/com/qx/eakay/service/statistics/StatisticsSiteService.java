package com.qx.eakay.service.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.common.highcharts.ChartsBO;
import com.qx.common.highcharts.PieSerieBO;
import com.qx.common.highcharts.PointData;
import com.qx.common.highcharts.SerieBO;
import com.qx.common.highcharts.SerieBO.SerieType;
import com.qx.eakay.dao.merchant.SiteDao;
import com.qx.eakay.dao.statistics.StatisticsSiteDao;
import com.qx.eakay.export.statistics.SiteCostStatisticsExport;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月24日
 */
@Service
public class StatisticsSiteService extends BaseService {

	@Autowired
	private SiteDao siteDao;

	@Autowired
	private StatisticsSiteDao statisticsSiteDao;

	/**
	 * 分页查找车辆使用统计
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findSiteCostPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return siteDao.findSiteCostPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出充电设备列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportSiteCosts(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new SiteCostStatisticsExport();
		List<Map<String, Object>> list = findSiteCosts(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索充电设备
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findSiteCosts(JSONObject jsonObject) {
		return siteDao.statisticsGetCount(jsonObject);
	}

	/**
	 * 商家每日收益统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<Integer> statisticsHourCount(JSONObject jsonObject) {
		ChartsBO<Integer> chartsBO = new ChartsBO<Integer>();
		List<Map<String, Object>> dayCosts = statisticsSiteDao
				.statisticsHourCount(jsonObject);
		SerieBO<Integer> getSerieBO = new SerieBO<>("取车");
		SerieBO<Integer> returnSerieBO = new SerieBO<>("还车");
		getSerieBO.setType(SerieType.spline);
		returnSerieBO.setType(SerieType.spline);
		Map<String, Integer> getMap = new HashMap<String, Integer>();
		Map<String, Integer> returnMap = new HashMap<String, Integer>();

		for (Map<String, Object> map : dayCosts) {
			String type = map.get("type") + "";
			Object obj = map.get("cs");
			int cs = Integer.parseInt(obj + "");
			String h = map.get("h") + "";
			if ("get".equals(type)) {
				getMap.put(h, cs);
			} else {
				returnMap.put(h, cs);
			}
		}

		for (int i = 0; i <= 9; i++) {
			String h = "0" + i;
			chartsBO.getxAxis().getCategories().add(h);
			getSerieBO.getData().add(getMap.get(h) == null ? 0 : getMap.get(h));
			returnSerieBO.getData().add(
					returnMap.get(h) == null ? 0 : returnMap.get(h));
		}

		for (int i = 10; i <= 23; i++) {
			String h = "" + i;
			chartsBO.getxAxis().getCategories().add(h);
			getSerieBO.getData().add(getMap.get(h) == null ? 0 : getMap.get(h));
			returnSerieBO.getData().add(
					returnMap.get(h) == null ? 0 : returnMap.get(h));
		}			
		chartsBO.getSeries().add(getSerieBO);
		chartsBO.getSeries().add(returnSerieBO);
		return chartsBO;
	}
	
	/**
	 * 动态加载租赁点取车次数
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<PointData> ajaxSiteGet(JSONObject jsonObject) {
		ChartsBO<PointData> chartsBO = new ChartsBO<PointData>();
		PieSerieBO pieSerieBO = new PieSerieBO("取车次数比例");
		pieSerieBO.setType(SerieType.pie);
		List<Map<String, Object>> siteGet = siteDao.statisticsGetCount(jsonObject);
		
		for (Map<String, Object> map : siteGet) {
			PointData pointData = new PointData(map.get("siteName") + "");
			Object get = map.get("getCount");
			if("".equals(get+"")){
				pointData.setY(0);
			}else{
				pointData.setY(Integer.parseInt(map.get("getCount") + ""));
			}
			pieSerieBO.getData().add(pointData);
		}
		chartsBO.getSeries().add(pieSerieBO);

		return chartsBO;
	}

	public ChartsBO<PointData> ajaxSiteReturn(JSONObject jsonObject) {
		ChartsBO<PointData> chartsBO = new ChartsBO<PointData>();
		PieSerieBO pieSerieBO = new PieSerieBO("还车次数比例");
		pieSerieBO.setType(SerieType.pie);
		List<Map<String, Object>> siteGet = siteDao.statisticsGetCount(jsonObject);
		
		for (Map<String, Object> map : siteGet) {
			PointData pointData = new PointData(map.get("siteName") + "");
			Object get = map.get("returnCount");
			if("".equals(get+"")){
				pointData.setY(0);
			}else{
				pointData.setY(Integer.parseInt(map.get("returnCount") + ""));
			}
			pieSerieBO.getData().add(pointData);
		}
		chartsBO.getSeries().add(pieSerieBO);

		return chartsBO;
	}

}

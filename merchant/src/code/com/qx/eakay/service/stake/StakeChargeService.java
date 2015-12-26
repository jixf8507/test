package com.qx.eakay.service.stake;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.highcharts.ChartsBO;
import com.qx.common.highcharts.SerieBO;
import com.qx.common.highcharts.SerieBO.SerieType;
import com.qx.eakay.dao.stake.StakeChargeDao;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.DateUtil;

/**
 * 充电统计
 * 
 */
@Service
public class StakeChargeService extends BaseService {

	@Autowired
	private StakeChargeDao stakeChargeDao;


	/**
	 * 加载充电时间段统计图
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<Integer> getChargeTimeSeries(JSONObject jsonObject) {
		ChartsBO<Integer> chartsBO = new ChartsBO<Integer>();
		List<Map<String, Object>> list = stakeChargeDao.getChargeTimeSeries(jsonObject);
		SerieBO<Integer> serieBO = new SerieBO<>("充电车辆");
		Integer[] dataArray = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		for (Map<String, Object> dataMap : list) {
			
			Integer startHour = Integer.parseInt(dataMap.get("h")+"");
			Date startCharge = DateUtil.formatToDateM(dataMap.get("startCharge")+"");
			Date lastCharge = DateUtil.formatToDateM(dataMap.get("lastCharge")+"");
			
			Integer hours = DateUtil.computerDivideHour(startCharge,lastCharge);
			
			if(hours>23){
				for(int i = 0 ; i <= 23 ; i++){
					dataArray[i]++;
				}
			}else{
				for(int i = 0 ; i <= hours ; i++){
					dataArray[startHour]++;
					startHour++;
					if(startHour>23){
						startHour = 0;
					}
				}
			}
		}
		
		for(int i = 0 ; i<dataArray.length; i++){
			
			serieBO.getData().add(dataArray[i]);//数量
			chartsBO.getxAxis().getCategories().add(i+"");//时间
		}
		chartsBO.getSeries().add(serieBO);
		return chartsBO;
	}


	/**
	 * 加载充电方式统计图
	 * 
	 * @param jsonObject
	 * @return
	 */
	public ChartsBO<Integer> getChargeWaySeries(JSONObject jsonObject) {

		ChartsBO<Integer> chartsBO = new ChartsBO<Integer>();
		List<Map<String, Object>> list = stakeChargeDao.getChargeWaySeries(jsonObject);
		SerieBO<Integer> bookSerieBO = new SerieBO<>("预约充电");
		SerieBO<Integer> cardSerieBO = new SerieBO<>("刷卡充电");
		bookSerieBO.setType(SerieType.spline);
		cardSerieBO.setType(SerieType.spline);
		Map<String, Integer> bookMap = new HashMap<String, Integer>();
		Map<String, Integer> cardMap = new HashMap<String, Integer>();

		for (Map<String, Object> map : list) {
			String month = map.get("month") + "";
			String type = map.get("kind") + "";
			Object obj = map.get("cs");
			int cs = Integer.parseInt(obj + "");
			
			if ("预约充电".equals(type)) {
				bookMap.put(month, cs);
				bookSerieBO.getData().add(cs);
			} else {
				cardMap.put(month, cs);
				cardSerieBO.getData().add(cs);
			}
			
			chartsBO.getxAxis().getCategories().add(month);
			
		}

		chartsBO.getSeries().add(bookSerieBO);
		chartsBO.getSeries().add(cardSerieBO);
		return chartsBO;
	
	}

}

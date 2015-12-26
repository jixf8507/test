package com.qx.eakay.service.customer;

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
import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.NumberUtils;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class CustomerManagerService extends BaseService {

	@Autowired
	private CustomerDao customerDao;

	
	
	/**
	 * 条件搜索正式用户
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findTypes(JSONObject jsonObject) {
		return customerDao.findTypes(jsonObject);
	}
	
	/**
	 * 得到所有的用户并且计算统计各个年龄段的用户的数量
	 * 1.取得身份证的号码。
	 * 2.取出身份证的出生年月。
	 * 3.用当前时间减去出生年月的到这个人的年龄。
	 * 4.用代码统计各个年龄段的用户数量（20以下，20-30，30-40，40-50，50-60，60以上）
	 * @throws Exception 
	 */
	public List<Map<String, Object>> countCustomerByAge(JSONObject jsonObject) throws Exception{
		//条件查询数据库用户的记录数
		List<Map<String, Object>> customers=this.findTypes(jsonObject);
		//20岁以下
		int below_20=0;
		//20岁到30岁人数
		int between_20_30=0;
		//30岁到40岁人数
		int between_30_40=0;
		//40岁到50岁人数
		int between_40_50=0;
		//50岁到60岁人数
		int between_50_60=0;
		//超过60的人数
		int over_60=0;

		//遍历list处理数据并返回controller层
		for (Map<String, Object> map : customers) {
			String idCard =(String) map.get("idCard");
			//int id=Integer.parseInt(map.get("id").toString());
			//自定义utils，调用方法取得身份证持有人的年龄
			int age=NumberUtils.getYearToCurrDateCard(idCard);
			
			if (0!=age) {
				if (age<=20) {
					below_20++;
				} else if(age>20 && age<=30){
					between_20_30++;
				} else if(age>30 && age<=40){
					between_30_40++;
				} else if(age>40 && age<=50){
					between_40_50++;
				} else if(age>50 && age<=60){
					between_50_60++;
				} else if(age>60){
					over_60++;
				}	
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", below_20);
		map.put("name", "20岁以下");
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("count", between_20_30);
		map1.put("name", "20 - 30 岁");
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("count", between_30_40);
		map2.put("name", "30 - 40岁");
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("count", between_40_50);
		map3.put("name", "40 - 50岁");
		
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("count", between_50_60);
		map4.put("name", "50 - 60岁");
		
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("count", over_60);
		map5.put("name", "60岁以上");
		

		List<Map<String, Object>> dataMap = new ArrayList<Map<String,Object>>();
		dataMap.add(0, map);
		dataMap.add(1, map1);
		dataMap.add(2, map2);
		dataMap.add(3, map3);
		dataMap.add(4, map4);
		dataMap.add(5, map5);
		
		return dataMap;
		
	}
	
	/**
	 * 将统计的年龄段封装到CharsBo然后返回到controller层用柱状图展示
	 * @throws  
	 */
	public ChartsBO<BigDecimal> returnCountCustomerColumn(JSONObject jsonObject) {
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		CommonSerieBO serieBO = new CommonSerieBO("人数");
		try {
			List<Map<String, Object>> data=this.countCustomerByAge(jsonObject);
			for (Map<String, Object> dataMap : data) {
				serieBO.getData().add(new BigDecimal(Integer.parseInt(dataMap.get("count").toString())));
				chartsBO.getxAxis().getCategories().add(dataMap.get("name").toString());
			}
			chartsBO.getSeries().add(serieBO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return chartsBO;
	}
	
	
	/**
	 * 将统计的年龄段封装到CharsBo然后返回到controller层用饼状图展示
	 * @throws  
	 */
	public ChartsBO<PointData> returnCountCustomerPie(JSONObject jsonObject) {
		ChartsBO<PointData> chartsBO = new ChartsBO<PointData>();
		PieSerieBO pieSerieBO = new PieSerieBO("租赁次数分时段统计比例图");
		pieSerieBO.setType(SerieType.pie);
		try {
			List<Map<String, Object>> data=this.countCustomerByAge(jsonObject);
			for (Map<String, Object> dataMap : data) {
				PointData pointData = new PointData(dataMap.get("name").toString());
				pointData.setY(Integer.parseInt(dataMap.get("count").toString()));
				pieSerieBO.getData().add(pointData);			
			}
			chartsBO.getSeries().add(pieSerieBO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return chartsBO;
	}

}

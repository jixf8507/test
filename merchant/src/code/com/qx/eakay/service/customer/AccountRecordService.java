package com.qx.eakay.service.customer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
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
import com.qx.common.highcharts.SerieBO.SerieType;
import com.qx.common.highcharts.TimeSerieBO;
import com.qx.common.tools.MyDateUtil;
import com.qx.eakay.dao.customer.AccountRecordDao;
import com.qx.eakay.export.customer.CustomerAccountRecordExport;
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月10日
 */
@Service
public class AccountRecordService extends BaseService {

	@Autowired
	private AccountRecordDao accountRecordDao;

	/**
	 * 分页查询用户账户交易明细
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findAccountRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return accountRecordDao.findAccountRecordPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 条件搜索用户账户交易明细
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findAccountRecord(JSONObject jsonObject) {
		return accountRecordDao.findAccountRecord(jsonObject);
	}

	/**
	 * 导出用户账户交易明细
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportAccountRecord(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CustomerAccountRecordExport();
		List<Map<String, Object>> list = findAccountRecord(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找交易记录
	 * 
	 * @param id
	 * @return
	 */
	public AccountRecordPO getAccountRecord(Integer id) {
		return accountRecordDao.getAccountRecord(id);
	}

	/**
	 * 保存核对充值收费信息
	 * 
	 * @param accountRecordPO
	 * @return
	 */
	public boolean checkForCharge(AccountRecordPO accountRecordPO) {
		return accountRecordDao.checkForCharge(accountRecordPO);
	}
	
	
	
	/**
	 * 统计用户日开户，注销次数的统计查询
	 * @param jsonObject 查询条件
	 * @return ChartsBO  一个封装了json字符串的对象。
	 * 
	 */
	public ChartsBO<BigDecimal> countCustomerDay(JSONObject jsonObject){
		ChartsBO<BigDecimal> chartsBO = new ChartsBO<BigDecimal>();
		//注销人数
		jsonObject.put("status", "注销");
		List<Map<String, Object>> countCancelList=accountRecordDao.getCountByCancel(jsonObject);
		//冻结人数
		jsonObject.put("status", "冻结");
		List<Map<String, Object>> countFrozenList=accountRecordDao.getCountByCancel(jsonObject);		
		//开户人数
		List<Map<String, Object>> countRegisteList=accountRecordDao.getCountByRegiste(jsonObject);
		TimeSerieBO countCancel =new TimeSerieBO("注销人数");
		TimeSerieBO countFrozen =new TimeSerieBO("冻结人数");
		TimeSerieBO countRegiste =new TimeSerieBO("开户人数");
		countCancel.setType(SerieType.spline);
		countRegiste.setType(SerieType.spline);
		countFrozen.setType(SerieType.spline);
		countCancel.setyAxis(1);
		countRegiste.setyAxis(1);
		countFrozen.setyAxis(1);
		Map<String, Object> cancelMap = new HashMap<String, Object>();
		Map<String, Object> registeMap = new HashMap<String, Object>();
		Map<String, Object> frozenMap = new HashMap<String, Object>();
		
		try {
				String beginTime = jsonObject.get("beginTime") + "";
				String endTime = jsonObject.get("endTime") + "";
				
				for (int i = 0; i < countCancelList.size(); i++) {
					Map<String, Object> seriesMap = countCancelList.get(i);
					String tempDate = seriesMap.get("day")+"";
					cancelMap.put(tempDate, new BigDecimal(seriesMap.get("cs")+""));
				}
				
				for (int i = 0; i < countFrozenList.size(); i++) {
					Map<String, Object> seriesMap = countFrozenList.get(i);
					String tempDate = seriesMap.get("day")+"";
					frozenMap.put(tempDate, new BigDecimal(seriesMap.get("cs")+""));
				}
				
				
				for (int i = 0; i < countRegisteList.size(); i++) {
					Map<String, Object> seriesMap =countRegisteList.get(i);
					String tempDate = seriesMap.get("day")+"";
					registeMap.put(tempDate, new BigDecimal(seriesMap.get("cs")+""));
				}
				
			
				Date todayDate = MyDateUtil.StrToDateyMdhms(endTime);//结束日期采用对应的时分秒
				Date tempDate = MyDateUtil.StrToDateyMd(beginTime);//开始时间对应的日期，没有！！！（时分秒）
				tempDate = MyDateUtil.getDaytoCurrDay(tempDate, 1);//日期加上一天
				//设置图标的起始点pointStart
				countCancel.setPointStart(tempDate.getTime());
				countRegiste.setPointStart(tempDate.getTime());
				countFrozen.setPointStart(tempDate.getTime());
				tempDate = MyDateUtil.getDaytoCurrDay(tempDate, -1);//日期减去一天
				while(tempDate.getTime()< todayDate.getTime()){
					//取得时期字符串
					String tempDay = MyDateUtil.getDayString(tempDate);
					//取出当天的值，放到data中去
					countCancel.getData().add(this.getBigDecimal(cancelMap.get(tempDay)));
					countRegiste.getData().add(this.getBigDecimal(registeMap.get(tempDay)));
					countFrozen.getData().add(this.getBigDecimal(frozenMap.get(tempDay)));
					//日期往后加上一天
					tempDate = MyDateUtil.getDaytoCurrDay(tempDate, 1);
				}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		chartsBO.getSeries().add(countRegiste);
		chartsBO.getSeries().add(countFrozen);
		chartsBO.getSeries().add(countCancel);
		
		
		return chartsBO;
	}

}

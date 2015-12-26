package com.qx.eakay.dao.stake;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;

/**
 * 充电统计
 * 
 */
@Repository
public class StakeChargeDao extends BaseDao {

	@Resource(name = "CHARGE_TIME_RECORD_SQL")
	private JSONSqlMapping chargeTimeRecordSql;

	@Resource(name = "CHARGE_WAY_RECORD_SQL")
	private JSONSqlMapping chargeWayRecordSql;

	@Resource(name = "CHARGE_MONTH_PAY_RECORD_SQL")
	private JSONSqlMapping chargeMonthPayRecordSql;

	@Resource(name = "CHANGE_DAY_PAY_RECORD_SQL")
	private JSONSqlMapping chargeDayPayRecordSql;
	
	@Resource(name = "CHANGE_SITE_PAY_RECORD_SQL")
	private JSONSqlMapping chargeSitePayRecordSql;
	
	@Resource(name = "CHANGE_SITE_STAKE_KIND_RECORD_SQL")
	private JSONSqlMapping chargeSiteStakeKindRecordSql;

	/**
	 * 加载充电时间段数据
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> getChargeTimeSeries(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(chargeTimeRecordSql, jsonObject);
	}

	/**
	 * 加载充电方式数据
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> getChargeWaySeries(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(chargeWayRecordSql, jsonObject);
	}

	/**
	 * 加载每月充电收费数据
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsMonth(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(chargeMonthPayRecordSql,jsonObject);
	}

	/**
	 * 加载每日充电收费数据
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsDay(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(chargeDayPayRecordSql, jsonObject);
	}
	
	/**
	 * 加载商家租赁点充电收费数据
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsSite(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(chargeSitePayRecordSql, jsonObject);
	}
	
	/**
	 * 加载商家租赁点不同充电方式充电电量数据
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsSiteKind(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(chargeSiteStakeKindRecordSql, jsonObject);
	}

	/**
	 * 加载每月充电电量数据
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsMonthElec(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(chargeMonthPayRecordSql,jsonObject);
	}

	/**
	 * 加载每日充电电量数据
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsDayElec(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(chargeDayPayRecordSql, jsonObject);
	}

}

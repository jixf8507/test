package com.qx.eakay.dao.stake;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Repository
public class StakeFaultRecordDao extends BaseDao {

	@Resource(name = "stake_faultrecord_select_sql")
	private JSONSqlMapping faultRecordSelectSQL;

	/**
	 * 分页查找充电故障记录列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findFaultRecordPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(faultRecordSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查找充电故障记录列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findFaultRecord(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(faultRecordSelectSQL, jsonObject);
	}

	@Resource(name = "stake_faultrecord_statistics_select_sql")
	private JSONSqlMapping faultRecordStatisticsSelectSQL;

	public List<Map<String, Object>> findFaultRecordStatistics(
			JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(faultRecordStatisticsSelectSQL,
				jsonObject);
	}

}

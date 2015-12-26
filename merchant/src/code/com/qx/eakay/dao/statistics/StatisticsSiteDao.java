package com.qx.eakay.dao.statistics;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;

/**
 * 
 * @author jixf
 * @date 2015年7月24日
 */
@Repository
public class StatisticsSiteDao extends BaseDao {

	@Resource(name = "statistics_site_hour_count_sql")
	private JSONSqlMapping hourCountSQL;

	/**
	 * 统计租赁点租赁次数
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsHourCount(
			JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(hourCountSQL, jsonObject);
	}

}

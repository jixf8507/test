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
 * @author sdf
 * @date 2015年8月21日
 */
@Repository
public class StatisticsTimeDao extends BaseDao {

	@Resource(name = "STATISTICS_SITE_TIME_SQL")
	private JSONSqlMapping statisticsSiteTimeSQL;

	/**
	 * 租赁点租赁次数分时段统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsSiteTime(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(statisticsSiteTimeSQL, jsonObject);
	}


}

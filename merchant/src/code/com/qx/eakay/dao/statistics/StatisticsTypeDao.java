package com.qx.eakay.dao.statistics;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;

/**
 * 租赁类型统计
 * 
 * @author sdf
 * @date 2015年11月6日
 */
@Repository
public class StatisticsTypeDao extends BaseDao {

	@Resource(name = "STATISTICS_SITE_TYPE_SQL")
	private JSONSqlMapping statisticsSiteTypeSQL;

	/**
	 * 租赁点租赁次数分时段统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsSiteType(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(statisticsSiteTypeSQL, jsonObject);
	}


}

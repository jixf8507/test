package com.qx.eakay.dao.order;

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
 * @date 2015年7月13日
 */
@Repository
public class OrderResuceDao extends BaseDao {

	@Resource(name = "order_resuce_select_sql")
	private JSONSqlMapping resuceSelectSQL;

	/**
	 * 分页查询救援信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findResucePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(resuceSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索救援信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findResuces(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(resuceSelectSQL, jsonObject);
	}

}

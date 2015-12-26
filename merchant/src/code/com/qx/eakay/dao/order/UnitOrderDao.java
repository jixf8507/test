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
 * 单位会员订单管理
 * 
 * @author Administrator
 * 
 */
@Repository
public class UnitOrderDao extends BaseDao {

	@Resource(name = "UNIT_ORDER_SELECT_SQL")
	private JSONSqlMapping unitOrderSelectSQL;
	
	@Resource(name = "TABLE_UNIT_ORDER_SELECT_SQL")
	private JSONSqlMapping tableUnitOrderSelectSQL;

	/**
	 * 分页查询订单列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(unitOrderSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}
	
	/**
	 * 分页查询订单列表（只查询表格显示内容）
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findTableOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(tableUnitOrderSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索订单列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findOrders(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(unitOrderSelectSQL, jsonObject);
	}
	
}

package com.qx.eakay.dao.price;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.MonthLeasePriceTypeCreator;
import com.qx.eakay.db.extractor.MonthLeasePriceTypeExtractor;
import com.qx.eakay.model.price.MonthLeasePriceTypePO;

@Repository
public class LongPriceTypeDao extends BaseDao  {

	@Resource(name = "longPrice_pricetype_select_sql")
	private JSONSqlMapping longPriceTypeSelectSQL;
	
	//保存新增长租收费套餐SQL
	private static final String INSERT_LONG_SQL = "insert into month_lease_price_type (merchantId,typeName,monthFee,dayFee,hourFee,flag)"
												+ " values (?,?,?,?,?,'正常')";
	//查找长租收费对象SQL
	private static final String SELECT_LONG_BY_TYPENAME_SQL = "select * from month_lease_price_type where typeName = ? ";
	// 删除长租收费套餐SQL
	private static final String DELETE_LONG_SQL = "update month_lease_price_type set flag='删除' where id=?";
	//查找长租收费对象SQL
	private static final String GET_SQL = "select * from month_lease_price_type where id = ? ";
		
		

		/**
		 * 保存新增长租收费套餐
		 * 
		 * @param monthLeasePriceTypePO
		 * @return
		 */
		public Integer createLongPriceType(MonthLeasePriceTypePO monthLeasePriceTypePO) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			this.getJdbcTemplate().update(
					new MonthLeasePriceTypeCreator(INSERT_LONG_SQL, monthLeasePriceTypePO), keyHolder);
			return keyHolder.getKey().intValue();
		}

		/**
		 * 检查长租收费套餐名称是否唯一
		 * 
		 * @param typeName
		 * @param longPriceTypeId
		 * @return
		 */
		public MonthLeasePriceTypePO checkTypeNameOnly(String typeName ) {
			return this.getJdbcTemplate().query(SELECT_LONG_BY_TYPENAME_SQL,
					new Object[] { typeName }, new MonthLeasePriceTypeExtractor());
		}

		/**
		 * 分页查询长租收费套餐列表
		 * 
		 * @param jsonObject
		 * @param pageSize
		 * @param iDisplayStart
		 * @return
		 */
		public PageResults findLongTypesPage(JSONObject jsonObject, int pageSize,
				int iDisplayStart) {
			return this.findPageByJSONSqlMapping(longPriceTypeSelectSQL, jsonObject,
					pageSize, iDisplayStart);
		}
		
		/**
		 * 条件搜索长租收费套餐列表
		 * 
		 * @param jsonObject
		 * @return
		 */
		public List<Map<String, Object>> findLongTypes(JSONObject jsonObject) {
			return this.findListByJSONSqlMapping(longPriceTypeSelectSQL, jsonObject);
		}

		/**
		 * 删除长租收费套餐
		 * 
		 * @param id
		 * @return
		 */
		public boolean deleteLongPriceType(Integer id) {
			int count = this.getJdbcTemplate().update(DELETE_LONG_SQL, id);
			return count > 0;
		}

		/**
		 * 根据ID查找长租收费套餐
		 * 
		 * @param priceTypeId
		 * @return
		 */
		public MonthLeasePriceTypePO get(Integer id) {
			return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
					new MonthLeasePriceTypeExtractor());
		}
		
}

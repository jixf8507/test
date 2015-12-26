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
import com.qx.eakay.db.creator.WhHourPriceTypeCreator;
import com.qx.eakay.db.extractor.WhHourPriceTypeExtractor;
import com.qx.eakay.model.price.WhHoursPriceTypePO;

/**
 * 
 * @author jixf
 * @date 2015年7月6日
 */
@Repository
public class PriceTypeDao extends BaseDao {

	@Resource(name = "price_pricetype_select_sql")
	private JSONSqlMapping priceTypeSelectSQL;

	/**
	 * 分页查询收费套餐列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findTypesPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(priceTypeSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索收费套餐列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findTypes(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(priceTypeSelectSQL, jsonObject);
	}

	/**
	 * 创建收费套餐
	 * 
	 * @param priceTypePO
	 * @return
	 */
	public Integer create(WhHoursPriceTypePO priceTypePO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new WhHourPriceTypeCreator(INSERT_SQL, priceTypePO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 根据ID查找收费套餐
	 * 
	 * @param id
	 * @return
	 */
	public WhHoursPriceTypePO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new WhHourPriceTypeExtractor());
	}

	public WhHoursPriceTypePO getByName(String typeName, Integer merchantId) {
		return this.getJdbcTemplate().query(GET_BY_TYPENAME,
				new Object[] { typeName, merchantId },
				new WhHourPriceTypeExtractor());
	}

	/**
	 * 删除车辆
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	//
	// /**
	// * 更改租赁业务套餐
	// *
	// * @param priceTypePO
	// * @return
	// */
	// public boolean update(WhHoursPriceTypePO priceTypePO) {
	// int count = this.getJdbcTemplate().update(UPDATE_SQL,
	// typePO.getTypeName(), typePO.getCarManufacturerId(),
	// typePO.getSeatCount(), typePO.getColor(), typePO.getEnergy(),
	// typePO.getId());
	// return count > 0;
	// }

	// 插入收费套餐SQL
	private static final String INSERT_SQL = "insert into wh_hours_price_type (merchantId,typeName,oneHoursCost,twoHoursCost,threeHoursCost,"
			+ "fourHoursCost,fiveHoursCost,maxCostForDay,nightCost,perHoursKmsForDay,perKmsPrice,maxKmsForNight,startTimeForDay,endTimeForDay,"
			+ "flag,maxKmsForDay,maxDays,minDays,prepayment,startTimeForWork,endTimeForWork,reservedMinute) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,'正常',?,?,?,?,?,?,?)";
	// 根据套餐名称查找收费套餐的SQL
	private static final String GET_BY_TYPENAME = "select * from  wh_hours_price_type where typeName=? and merchantId=?";
	// 根据ID查找收费套餐的SQL
	private static final String GET_SQL = "select * from  wh_hours_price_type where id=?";
	// 删除车辆的SQL
	private static final String DELETE_SQL = "update  wh_hours_price_type set flag='删除' where id=?";

	// // 修改收费套餐SQL
	// private static final String UPDATE_SQL =
	// "update car_type set typeName=?,carManufacturerId=?,seatCount=?,color=?,energy=? where id=?";
	
	
}

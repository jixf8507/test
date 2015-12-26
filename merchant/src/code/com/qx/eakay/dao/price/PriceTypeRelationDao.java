package com.qx.eakay.dao.price;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.qx.common.base.SqlHelp;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.model.price.CarPriceTypeRelationPO;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class PriceTypeRelationDao extends BaseDao {

	@Resource(name = "price_type_relation_select_by_carid_sql")
	private JSONSqlMapping priceTypeRelationSelectSQL;

	@Resource(name = "price_type_relation_left_select_by_carid_sql")
	private JSONSqlMapping priceTypeRelationLeftSelectSQL;

	/**
	 * 查找商家租赁套餐列表inner join
	 * 
	 * @param tableName
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findByCarId(JSONObject jsonObject) {
		return this.findListBySQL(
				new SqlHelp(priceTypeRelationSelectSQL).getSql(), new Object[] {
						jsonObject.get("tableName"), jsonObject.get("carId"),
						jsonObject.get("merchantId") });
	}

	/**
	 * 查找商家租赁套餐列表Left join
	 * 
	 * @param tableName
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findAllByCarId(JSONObject jsonObject) {
		return this.findListBySQL(
				new SqlHelp(priceTypeRelationLeftSelectSQL).getSql(),
				new Object[] { jsonObject.get("tableName"),
						jsonObject.get("carId"), jsonObject.get("tableName"),
						jsonObject.get("merchantId") });
	}

	/**
	 * 删除车辆的收费套餐
	 * 
	 * @param carId
	 * @return
	 */
	public boolean deleteRelationsByCarId(Integer carId, String tableName) {
		int count = this.getJdbcTemplate().update(DELETE_BY_CARID_SQL,
				new Object[] { carId, tableName });
		return count > 0;
	}

	/**
	 * 根据收费规则删除收费关系
	 * 
	 * @param priceId
	 * @param tableName
	 * @return
	 */
	public boolean deleteRelationsByPriceId(Integer priceId, String tableName) {
		int count = this.getJdbcTemplate().update(DELETE_BY_PRICEID_SQL,
				new Object[] { priceId, tableName });
		return count > 0;
	}

	/**
	 * 批量保存车辆收费套餐关系
	 * 
	 * @param relations
	 * @return
	 */
	public boolean batchSaveRelation(
			final List<CarPriceTypeRelationPO> relations, final String tableName) {
		final Timestamp now = new Timestamp(System.currentTimeMillis());
		int[] ids = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						CarPriceTypeRelationPO typeRelationPO = relations
								.get(i);
						ps.setInt(1, typeRelationPO.getPriceTypeId());
						ps.setInt(2, typeRelationPO.getCarId());
						ps.setTimestamp(3, now);
						ps.setString(4, tableName);
					}

					@Override
					public int getBatchSize() {
						return relations.size();
					}
				});
		return ids != null;
	}

	// 删除指定车辆ID的租赁收费套餐
	private static final String DELETE_BY_CARID_SQL = "delete from car_price_type_relation where carId =? and priceTypeTableName = ?";
	// 删除指定收费套餐的租赁车辆管理
	private static final String DELETE_BY_PRICEID_SQL = "delete from car_price_type_relation where priceTypeId =? and priceTypeTableName = ? ";
	// 保存车辆收费套餐关系
	private static final String INSERT_SQL = "insert into car_price_type_relation (priceTypeId,carId,createdTime,priceTypeTableName) values(?,?,?,?)";

}

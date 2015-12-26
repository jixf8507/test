package com.qx.eakay.dao.stake;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.StakePriceCreator;
import com.qx.eakay.db.extractor.StakePriceExtractor;
import com.qx.eakay.model.stake.StakePricePO;

/**
 * 充电套餐
 * 
 */
@Repository
public class StakePriceDao extends BaseDao {

	@Resource(name = "STAKE_PRICE_SELECT_SQL")
	private JSONSqlMapping stakePriceSelectSQL;

	/**
	 * 分页查询充电套餐信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findPricePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(stakePriceSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查找充电套餐信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findPriceList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(stakePriceSelectSQL, jsonObject);
	}

	/**
	 * 新增充电套餐
	 * 
	 * @param stakePricePO
	 * @return
	 */
	public boolean createPrice(StakePricePO stakePricePO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new StakePriceCreator(INSERT_SQL, stakePricePO), keyHolder);
		return keyHolder.getKey().intValue() > 0;
	}

	/**
	 * 修改充电套餐
	 * 
	 * @param stakePricePO
	 * @return
	 */
	public boolean editePrice(StakePricePO stakePricePO) {
		return this.getJdbcTemplate().update(UPDATE_SQL,
				stakePricePO.getMerchantId(), stakePricePO.getName(),
				stakePricePO.getHour1(), stakePricePO.getMinute1(),
				stakePricePO.getHour2(), stakePricePO.getMinute2(),
				stakePricePO.getHour3(), stakePricePO.getMinute3(),
				stakePricePO.getHour4(), stakePricePO.getMinute4(),
				stakePricePO.getPriceA(), stakePricePO.getPriceB(),
				stakePricePO.getPriceC(), stakePricePO.getPriceD(),
				stakePricePO.getMemo(), stakePricePO.getId()) > 0;
	}

	/**
	 * 删除充电套餐
	 * 
	 * @param id
	 * @return
	 */
	public boolean deletePrice(Integer id) {
		return this.getJdbcTemplate().update(DELETE_SQL, id) > 0;
	}

	/**
	 * 查找充电套餐
	 * 
	 * @param id
	 * @return
	 */
	public StakePricePO getStakePrice(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new StakePriceExtractor());
	}

	/**
	 * 查找充电套餐
	 * 
	 * @param name
	 * @param merchantId
	 * @return
	 */
	public StakePricePO getStakePriceByName(String name, Integer merchantId) {
		return this.getJdbcTemplate().query(GET_SQL_BY_NAME,
				new Object[] { name, merchantId }, new StakePriceExtractor());
	}

	/**
	 * 删除充电桩原有的收费关系
	 * 
	 * @param id
	 */
	public boolean deleteRelationsByPriceId(Integer id) {
		return this.getJdbcTemplate().update(DELETE_BY_PRICEID_SQL,
				new Object[] { 0, id }) > 0;
	}

	/**
	 * 保存充电套餐选择充电桩
	 * 
	 * @param id
	 * @param sids
	 * @return
	 */
	public boolean batchSavePriceStake(final Integer id,
			final List<Integer> sids) {
		int[] ids = this.getJdbcTemplate().batchUpdate(
				BATCH_UPDATE_PRICE_STAKE_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, id);
						ps.setInt(2, sids.get(i));
					}

					@Override
					public int getBatchSize() {
						return sids.size();
					}
				});

		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == 0) {
				logger.info("------>修改" + sids.get(i) + "充电桩套餐失败");
				return false;
			}
		}
		return ids.length == sids.size();
	}

	// 新增充电套餐SQL
	private static final String INSERT_SQL = "insert into stake_peakvalleyprice (merchantId,name,Hour1,Minute1,Hour2,"
			+ "Minute2,Hour3,Minute3,Hour4,Minute4,PriceA,PriceB,PriceC,PriceD,createdTime,memo) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	// 删除充电套餐SQL
	private static final String DELETE_SQL = "delete from stake_peakvalleyprice where id = ?";
	// 根据id查找充电套餐SQL
	private static final String GET_SQL = "select * from stake_peakvalleyprice where id = ?";
	// 根据套餐名称查找充电套餐SQL
	private static final String GET_SQL_BY_NAME = "select * from stake_peakvalleyprice where name = ? and merchantId = ?";
	// 修改充电套餐SQL
	private static final String UPDATE_SQL = "update stake_peakvalleyprice set merchantId=?,name=?,Hour1=?,Minute1=?,Hour2=?,"
			+ "Minute2=?,Hour3=?,Minute3=?,Hour4=?,Minute4=?,PriceA=?,PriceB=?,PriceC=?,PriceD=?,memo=? where id = ? ";
	// 更改充电桩充电套餐的SQL
	private static final String BATCH_UPDATE_PRICE_STAKE_SQL = "update stake_devicename set priceid = ?,isupdateprice = 0 where id=?";
	// 重置充电桩充电套餐的SQL
	private static final String DELETE_BY_PRICEID_SQL = "update stake_devicename set priceid = ?,isupdateprice = 0 where priceid=?";

	/**
	 * 重置下发费率为未下发
	 * 
	 * @param stakePricePO
	 * @return
	 */
	public boolean resetPrice(StakePricePO stakePricePO) {
		return this.getJdbcTemplate().update(DELETE_BY_PRICEID_SQL,
				new Object[] { stakePricePO.getId(), stakePricePO.getId() }) > 0;
	}

}

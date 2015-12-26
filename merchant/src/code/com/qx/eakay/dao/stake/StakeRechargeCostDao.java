package com.qx.eakay.dao.stake;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.extractor.StakeRechargeCostExtractor;
import com.qx.eakay.model.stake.StakeRechargeCostPO;
import com.qx.eakay.model.stake.StakeRechargeCostPO.OrderStatus;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Repository
public class StakeRechargeCostDao extends BaseDao {

	@Resource(name = "stake_chargerecost_select_sql")
	private JSONSqlMapping rechargecostSelectSQL;
	
	@Resource(name = "find_OutTime_Charge_Order_SQL")
	private JSONSqlMapping findOutTimeChargeOrderSQL;

	/**
	 * 分页查找充电收费记录列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findRechargeCostPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(rechargecostSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查找充电收费记录列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findRechargeCost(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(rechargecostSelectSQL, jsonObject);
	}

	/**
	 * 查找充电订单信息
	 * 
	 * @param id
	 * @return
	 */
	public StakeRechargeCostPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new StakeRechargeCostExtractor());
	}

	/**
	 * 修改订单状态
	 * 
	 * @param orderStatus
	 * @param id
	 */
	public boolean updateStatus(OrderStatus orderStatus, Integer id) {
		return this.getJdbcTemplate()
				.update(UPDATE_STATUS_SQL, orderStatus.name(), id) > 0;
	}

	// 根据ID查找订单的SQL
	private static final String GET_SQL = "select r.*,s.siteName,c.`name`,c.phone,m.merchantName,m.hotTel,a.cardNO,a.balance,a.id as accountId "
			+ "from stake_rechargecost r LEFT JOIN site s on r.site_code=s.id "
			+ "LEFT JOIN customer c on r.customerId=c.id "
			+ "LEFT JOIN merchant m on r.merchantId=m.id "
			+ "LEFT JOIN account a on a.customerId=c.id and a.merchantId=r.merchantId and a.`status`!='注销' "
			+ "where r.id=? ";
	// 修改订单状态的SQL
	private static final String UPDATE_STATUS_SQL = "update stake_rechargecost set orderStatus = ? where id=? ";

	/**
	 * 查找超时订单
	 * 
	 * @return
	 */
	public List<Map<String, Object>> findOutTimeChargeOrder() {
		return this.findListByJSONSqlMapping(findOutTimeChargeOrderSQL, null);
	}
	
	/**
	 * 批量更新订单状态
	 * 
	 * @param orderStatus
	 * @param oIds
	 * @return
	 */
	public boolean batchUpdateStaus(final OrderStatus orderStatus, final List<Integer> oIds) {
		int[] ids = this.getJdbcTemplate().batchUpdate(UPDATE_STATUS_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, orderStatus.name());
						ps.setInt(2, oIds.get(i));
					}

					@Override
					public int getBatchSize() {
						return oIds.size();
					}
				});
		return ids.length != oIds.size();
	}

}

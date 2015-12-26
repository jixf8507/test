package com.qx.eakay.dao.asset;

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
import com.qx.eakay.db.creator.AssetReduceCreator;
import com.qx.eakay.db.extractor.AssetReduceExtractor;
import com.qx.eakay.model.asset.AssetReducePO;

/**
 * 资产减少管理
 * 
 * @author sdf
 * @date 2015年9月25日
 */
@Repository
public class AssetsReduceDao extends BaseDao {

	@Resource(name = "REDUCE_SELECT_SQL")
	private JSONSqlMapping reduceSelectSql;

	@Resource(name = "REDUCE_DETAIL_SELECT_SQL")
	private JSONSqlMapping repairDetailSelectSql;

	/**
	 * 分页查询资产减少信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findReducePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(reduceSelectSql, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索资产减少信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findReduce(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(reduceSelectSql, jsonObject);
	}

	/**
	 * 分页查询资产减少详细信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findRepairDetailPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(repairDetailSelectSql, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索资产减少详细信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findReduceDetail(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(repairDetailSelectSql, jsonObject);
	}

	/**
	 * 查找资产减少信息
	 * 
	 * @param id
	 * @return
	 */
	public AssetReducePO getReduce(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new AssetReduceExtractor());
	}

	// 根据ID查找资产减少的SQL
	private static final String GET_SQL = "select r.* from merchant_assets_reduce r where r.id = ? ";
	// 新增资产减少的SQL
	private static final String INSERT_SQL = "insert into merchant_assets_reduce (merchantId,reduceDate,reduceReason,reduceStatus,"
			+ "applyUser,transactUser,remarks,createdTime) values (?,?,?,?,?,?,?,?)";
	// 新增资产减少详细的SQL
	private static final String BATCH_INSERT_SQL = "insert into merchant_assets_reduce_detail (reduceId,assetsId) values (?,?)";
	// 资产减少修改的SQL
	private static final String UPDATE_SQL = "update merchant_assets_reduce set merchantId = ?,reduceDate = ?,reduceReason = ?,reduceStatus = ?,"
			+ "applyUser = ?,transactUser = ?,remarks = ? where id=? ";

	/**
	 * 保存新增资产减少
	 * 
	 * @param assetUsePO
	 * @return
	 */
	public int create(AssetReducePO assetReducePO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new AssetReduceCreator(INSERT_SQL, assetReducePO), keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * 保存新增资产减少详细
	 * 
	 * @param assetId
	 * @param useId
	 * @return
	 */
	public boolean batchCreate(final Integer[] assetId, final int reduceId) {
		int[] ids = this.getJdbcTemplate().batchUpdate(BATCH_INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, reduceId);
						ps.setInt(2, assetId[i]);
					}

					@Override
					public int getBatchSize() {
						return assetId.length;
					}
				});
		return ids.length == assetId.length;
	}

	/**
	 * 保存修改资产减少信息
	 * 
	 * @param assetReducePO
	 * @return
	 */
	public boolean updateAssetReduce(AssetReducePO assetReducePO) {
		Integer count = this.getJdbcTemplate().update(UPDATE_SQL,
				assetReducePO.getMerchantId(), assetReducePO.getReduceDate(),
				assetReducePO.getReduceReason(),
				assetReducePO.getReduceStatus(), assetReducePO.getApplyUser(),
				assetReducePO.getTransactUser(), assetReducePO.getRemarks(),
				assetReducePO.getId());
		return count > 0;
	}

}

package com.qx.eakay.dao.asset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.AssetCreator;
import com.qx.eakay.db.creator.AssetUseCreator;
import com.qx.eakay.db.extractor.AssetUseExtractor;
import com.qx.eakay.model.asset.AssetPO;
import com.qx.eakay.model.asset.AssetUseDetailPO;
import com.qx.eakay.model.asset.AssetUsePO;

/**
 * 资产领用管理
 * 
 * @author sdf
 * @date 2015年9月24日
 */
/**
 * @author Administrator
 * 
 */
@Repository
public class AssetsUseDao extends BaseDao {

	@Resource(name = "USE_SELECT_SQL")
	private JSONSqlMapping useSelectSql;

	@Resource(name = "USE_DETAIL_SELECT_SQL")
	private JSONSqlMapping useDetailSelectSql;

	/**
	 * 分页查询资产领用信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUsePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(useSelectSql, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 分页查询资产领用详细信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUseDetailPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(useDetailSelectSql, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索资产领用信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findUse(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(useSelectSql, jsonObject);
	}

	/**
	 * 条件搜索资产领用信息详细列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findUseDetail(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(useDetailSelectSql, jsonObject);
	}

	/**
	 * 保存新增资产领用
	 * 
	 * @param assetPO
	 * @return
	 */
	public boolean createAsset(AssetPO assetPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new AssetCreator(INSERT_SQL, assetPO),
				keyHolder);
		return keyHolder.getKey().intValue() > 0;
	}

	/**
	 * 查找资产领用信息
	 * 
	 * @param id
	 * @return
	 */
	public AssetUsePO getUse(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new AssetUseExtractor());
	}

	/**
	 * 查找领用资产id
	 * 
	 * @param id
	 * @return
	 */
	public List<AssetUseDetailPO> getUseDetail(Integer useId) {
		List<AssetUseDetailPO> list = this.getJdbcTemplate().query(GET_ID_SQL,
				new Object[] { useId }, new RowMapper<AssetUseDetailPO>() {
					@Override
					public AssetUseDetailPO mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						AssetUseDetailPO assetUseDetailPO = new AssetUseDetailPO();

						assetUseDetailPO.setId(rs.getInt("id"));
						assetUseDetailPO.setUseId(rs.getInt("useId"));
						assetUseDetailPO.setAssetsId(rs.getInt("assetsId"));

						return assetUseDetailPO;
					}
				});
		return list;
	}

	/**
	 * 保存修改资产领用
	 * 
	 * @param assetPO
	 * @return
	 */
	public boolean updateUse(AssetUsePO assetUsePO) {
		Integer count = this.getJdbcTemplate().update(UPDATE_SQL,
				assetUsePO.getUseDate(), assetUsePO.getSiteId(),
				assetUsePO.getUserId(), assetUsePO.getMerchantId(),
				assetUsePO.getTransactUser(), assetUsePO.getRemarks(),
				assetUsePO.getId());
		return count > 0;
	}

	// 根据ID查找资产领用的SQL
	private static final String GET_SQL = "select u.*,mu.userName,s.siteName from merchant_assets_use u "
			+ "inner join merchant_user mu on (u.userId=mu.id) inner join site s on (u.siteId=s.id) where u.id = ? ";
	// 根据userId查找领用资产id的SQL
	private static final String GET_ID_SQL = "select d.* from merchant_assets_use_detail d where d.useId = ? ";

	// 新增资产领用的SQL
	private static final String INSERT_SQL = "insert into merchant_assets_use (useDate,siteId,userId,merchantId,"
			+ "createdTime,transactUser,flag,remarks) values (?,?,?,?,?,?,'在用',?) ";

	// 新增资产领用详细的SQL
	private static final String BATCH_INSERT_SQL = "insert into merchant_assets_use_detail (useId,assetsId) values (?,?) ";

	// 资产领用修改的SQL
	private static final String UPDATE_SQL = "update merchant_assets_use set useDate = ?,siteId = ?,userId = ?,merchantId = ?,"
			+ "transactUser = ?,remarks = ?  where id = ? ";

	// 修改领用单状态的SQL
	private static final String UPDATE_USE_FLAG_SQL = "update merchant_assets_use set flag = ? where id = ? ";

	/**
	 * 保存新增资产领用
	 * 
	 * @param assetUsePO
	 * @return
	 */
	public int create(AssetUsePO assetUsePO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new AssetUseCreator(INSERT_SQL, assetUsePO), keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * 保存新增资产领用详细
	 * 
	 * @param assetId
	 * @param useId
	 * @return
	 */
	public boolean batchCreate(final Integer[] assetId, final int useId) {
		int[] ids = this.getJdbcTemplate().batchUpdate(BATCH_INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, useId);
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
	 * 修改领用单状态
	 * 
	 * @param flag
	 * @param useId
	 * @return
	 */
	public boolean updateAssetUseFlag(String flag, Integer useId) {
		Integer count = this.getJdbcTemplate().update(UPDATE_USE_FLAG_SQL,
				flag, useId);
		return count > 0;
	}

}

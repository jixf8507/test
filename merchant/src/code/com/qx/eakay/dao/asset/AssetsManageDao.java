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
import com.qx.eakay.db.creator.AssetCreator;
import com.qx.eakay.db.extractor.AssetExtractor;
import com.qx.eakay.model.asset.AssetPO;

/**
 * 资产信息管理
 * 
 * @author sdf
 * @date 2015年9月23日
 */
@Repository
public class AssetsManageDao extends BaseDao {

	@Resource(name = "ASSET_SELECT_SQL")
	private JSONSqlMapping assetSelectSql;
	
	@Resource(name = "ASSET_USE_SELECT_SQL")
	private JSONSqlMapping assetUseSelectSql;
	
	@Resource(name = "ASSET_REPAIR_SELECT_SQL")
	private JSONSqlMapping assetRepairSelectSql;
	
	@Resource(name = "ASSET_REDUCE_SELECT_SQL")
	private JSONSqlMapping assetReduceSelectSql;

	/**
	 * 分页查询资产信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findAssetPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(assetSelectSql, jsonObject,
				pageSize, iDisplayStart);
	}
	
	/**
	 * 条件搜索资产信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findAsset(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(assetSelectSql, jsonObject);
	}
	
	
	
	/**
	 * 分页查询资产使用信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findAssetUsePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(assetUseSelectSql, jsonObject,
				pageSize, iDisplayStart);
	}
	
	/**
	 * 条件搜索资产使用信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findAssetUse(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(assetUseSelectSql, jsonObject);
	}
	
	
	
	/**
	 * 分页查询资产维修信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findAssetRepairPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(assetRepairSelectSql, jsonObject,
				pageSize, iDisplayStart);
	}
	
	/**
	 * 条件搜索资产维修信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findAssetRepair(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(assetRepairSelectSql, jsonObject);
	}
	
	
	
	/**
	 * 分页查询资产减少信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findAssetReducePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(assetReduceSelectSql, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索资产减少信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findAssetReduce(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(assetReduceSelectSql, jsonObject);
	}

	/**
	 * 保存新增资产
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
	 * 查找资产信息
	 * 
	 * @param id
	 * @return
	 */
	public AssetPO getAsset(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new AssetExtractor());
	}

	/**
	 * 保存修改资产
	 * 
	 * @param assetPO
	 * @return
	 */
	public boolean updateAsset(AssetPO assetPO) {
		Integer count = this.getJdbcTemplate().update(UPDATE_SQL,
				assetPO.getCategoryId(), assetPO.getAssetsName(),
				assetPO.getMerchantId(), assetPO.getModel(),
				assetPO.getIncreasingMode(), assetPO.getUnit(),
				assetPO.getPurchaseDate(), assetPO.getManufacturId(),
				assetPO.getTransactUser(), assetPO.getFee(), assetPO.getId());
		return count > 0;
	}
	
	/**
	 * 批量修改资产状态
	 * 
	 * @param flag
	 * @param assetId
	 * @return
	 */
	public boolean batchUpdateAssetFlag(final String flag, final List<Integer> assetIds) {
		int[] ids = this.getJdbcTemplate().batchUpdate(BATCH_UPDATE_FLAG_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
							ps.setString(1, flag);
							ps.setInt(2, assetIds.get(i));
					}

					@Override
					public int getBatchSize() {
						return assetIds.size();
					}
				});
		
		for(int i = 0 ; i < ids.length ; i++){
			if(ids[i]==0){
				logger.info("------>修改"+assetIds.get(i)+"资产状态失败");
				return false;
			}
		}
		return ids.length == assetIds.size();
	}

	// 根据ID查找资产的SQL
	private static final String GET_SQL = "select a.*,c.name,s.siteName,u.userName,m.manufacturerName,m.fullName,m.address,m.linkman,m.linkphone,m.email "
			+ "from merchant_assets a left join merchant_assets_category c on (a.categoryId = c.id) "
			+ "left join site s on (a.siteId = s.id) left join car_manufacturer m on (a.manufacturId = m.id) "
			+ "left join merchant_user u on (a.userId = u.id) where a.id = ? ";
	// 资产新增的SQL
	private static final String INSERT_SQL = "insert into merchant_assets (categoryId,assetsName,merchantId,model,"
			+ "increasingMode,unit,purchaseDate,createdTime,manufacturId,transactUser,fee,flag) values (?,?,?,?,?,?,?,?,?,?,?,'闲置')";
	// 资产修改的SQL
	private static final String UPDATE_SQL = "update merchant_assets set categoryId = ?,assetsName = ?,merchantId = ?,model = ?,"
			+ "increasingMode = ?,unit = ?,purchaseDate = ?,manufacturId = ?,transactUser = ?,fee = ? where id=?";
	// 更改资产状态的SQL
	private static final String BATCH_UPDATE_FLAG_SQL = "update merchant_assets set flag = ? where id=?";



}

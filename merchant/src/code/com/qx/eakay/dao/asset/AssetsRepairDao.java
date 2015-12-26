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
import com.qx.eakay.db.creator.AssetRepairCreator;
import com.qx.eakay.db.extractor.AssetRepairDetailExtractor;
import com.qx.eakay.db.extractor.AssetRepairExtractor;
import com.qx.eakay.model.asset.AssetRepairDetailPO;
import com.qx.eakay.model.asset.AssetRepairPO;

/**
 * 资产维修管理
 * 
 * @author sdf
 * @date 2015年9月25日
 */
@Repository
public class AssetsRepairDao extends BaseDao {

	@Resource(name = "REPAIR_SELECT_SQL")
	private JSONSqlMapping repairSelectSql;

	@Resource(name = "REPAIR_DETAIL_SELECT_SQL")
	private JSONSqlMapping repairDetailSelectSql;

	/**
	 * 分页查询资产维修信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findRepairPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(repairSelectSql, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索资产维修信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findRepair(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(repairSelectSql, jsonObject);
	}

	/**
	 * 分页查询资产维修详细信息列表
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
	 * 条件搜索资产维修详细信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findRepairDetail(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(repairDetailSelectSql, jsonObject);
	}

	/**
	 * 查找资产维修信息
	 * 
	 * @param id
	 * @return
	 */
	public AssetRepairPO getRepair(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new AssetRepairExtractor());
	}

	/**
	 * 查找资产维修详细信息
	 * 
	 * @param id
	 * @return
	 */
	public AssetRepairDetailPO getRepairDetail(Integer id) {
		return this.getJdbcTemplate().query(GET_DETAIL_SQL,
				new Object[] { id }, new AssetRepairDetailExtractor());
	}

	// 根据ID查找资产维修的SQL
	private static final String GET_SQL = "select r.*,m.* from merchant_assets_repair r inner join car_manufacturer m "
			+ "on (m.id=r.manufacturerId) where r.id = ? ";
	// 根据维修详细ID查找资产维修详细的SQL
	private static final String GET_DETAIL_SQL = "select d.*,a.assetsName from merchant_assets_repair_detail d inner join merchant_assets a on (a.id=d.assetsId) where d.id = ? ";
	// 新增资产维修的SQL
	private static final String INSERT_SQL = "insert into merchant_assets_repair (repairDate,repairReason,manufacturerId,applyUser,"
			+ "transactUser,fee,merchantId,remarks,createdTime) values (?,?,?,?,?,?,?,?,?)";
	// 新增资产维修详细的SQL
	private static final String BATCH_INSERT_SQL = "insert into merchant_assets_repair_detail (repairId,assetsId,repairReason,repairStatus,fee,accessories) values (?,?,?,?,?,?)";
	// 资产维修修改的SQL
	private static final String UPDATE_SQL = "update merchant_assets_repair set repairDate = ?,repairReason = ?,manufacturerId = ?,applyUser = ?,"
			+ "transactUser = ?,fee = ?,merchantId = ?,remarks = ? where id=? ";
	// 资产维修详细修改的SQL
	private static final String UPDATE_DETAIL_SQL = "update merchant_assets_repair_detail set repairReason = ?,repairStatus = ?,fee = ?,accessories = ? where id=? ";

	/**
	 * 保存新增资产维修
	 * 
	 * @param assetUsePO
	 * @return
	 */
	public int create(AssetRepairPO assetRepairPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new AssetRepairCreator(INSERT_SQL, assetRepairPO), keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * 保存新增资产维修详细
	 * 
	 * @param assetId
	 * @param useId
	 * @return
	 */
	public boolean batchCreate(final List<AssetRepairDetailPO> list,
			final int repairId) {
		int[] ids = this.getJdbcTemplate().batchUpdate(BATCH_INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						AssetRepairDetailPO detailPO = list.get(i);
						ps.setInt(1, repairId);
						ps.setInt(2, detailPO.getAssetsId());
						ps.setString(3, detailPO.getRepairReason());
						ps.setString(4, detailPO.getRepairStatus());
						ps.setBigDecimal(5, detailPO.getFee());
						ps.setString(6, detailPO.getAccessories());
					}

					@Override
					public int getBatchSize() {
						return list.size();
					}
				});
		return ids.length == list.size();
	}

	/**
	 * 保存修改资产维修信息
	 * 
	 * @param assetRepairPO
	 * @return
	 */
	public boolean updateAssetRepair(AssetRepairPO assetRepairPO) {
		Integer count = this.getJdbcTemplate().update(UPDATE_SQL,
				assetRepairPO.getRepairDate(), assetRepairPO.getRepairReason(),
				assetRepairPO.getManufacturerId(),
				assetRepairPO.getApplyUser(), assetRepairPO.getTransactUser(),
				assetRepairPO.getFee(), assetRepairPO.getMerchantId(),
				assetRepairPO.getRemarks(), assetRepairPO.getId());
		return count > 0;
	}

	/**
	 * 保存修改资产维修详细信息
	 * 
	 * @param assetRepairDetailPO
	 * @return
	 */
	public boolean updateAssetRepairDetail(
			AssetRepairDetailPO assetRepairDetailPO) {
		Integer count = this.getJdbcTemplate().update(UPDATE_DETAIL_SQL,
				assetRepairDetailPO.getRepairReason(),
				assetRepairDetailPO.getRepairStatus(),
				assetRepairDetailPO.getFee(),
				assetRepairDetailPO.getAccessories(),
				assetRepairDetailPO.getId());
		return count > 0;
	}

}

package com.qx.eakay.dao.asset;

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
import com.qx.eakay.db.creator.AssetCategoryCreator;
import com.qx.eakay.db.extractor.AssetCategoryExtractor;
import com.qx.eakay.model.asset.AssetCategoryPO;

/**
 * 资产种类管理
 * 
 * @author sdf
 * @date 2015年9月23日
 */
@Repository
public class AssetsCategoryDao extends BaseDao {

	@Resource(name = "ASSET_CATEGORY_SQL")
	private JSONSqlMapping assetCategorySql;

	/**
	 * 分页查询资产种类列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCategoryPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(assetCategorySql, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索资产种类列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCategory(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(assetCategorySql, jsonObject);
	}

	/**
	 * 根据ID查找种类
	 * 
	 * @param id
	 * @return
	 */
	public AssetCategoryPO getCategory(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new AssetCategoryExtractor());
	}

	/**
	 * 保存种类修改
	 * 
	 * @param categoryPO
	 * @return
	 */
	public boolean updateCategory(AssetCategoryPO categoryPO) {
		Integer count = this.getJdbcTemplate().update(UPDATE_SQL,
				categoryPO.getName(), categoryPO.getId());
		return count > 0;
	}

	/**
	 * 保存种类新增
	 * 
	 * @param categoryPO
	 * @return
	 */
	public boolean createCategory(AssetCategoryPO categoryPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new AssetCategoryCreator(INSERT_SQL, categoryPO), keyHolder);
		return keyHolder.getKey().intValue()>0;
	}

	/**
	 * 删除种类
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		Integer count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	/**
	 * 根据name查找种类
	 * 
	 * @param name
	 * @return
	 */
	public AssetCategoryPO getCategoryByName(String name) {
		return this.getJdbcTemplate().query(GET_SQL_BY_NAME, new Object[] { name },
				new AssetCategoryExtractor());
	}

	// 根据ID查找种类的SQL
	private static final String GET_SQL = "select * from merchant_assets_category where id=?";
	// 根据name查找种类的SQL
	private static final String GET_SQL_BY_NAME = "select * from merchant_assets_category where name=?";
	// 种类修改的SQL
	private static final String UPDATE_SQL = "update merchant_assets_category set name = ? where id=?";
	// 种类新增的SQL
	private static final String INSERT_SQL = "insert into merchant_assets_category (name,merchantId) values (?,?)";
	// 种类删除的SQL
	private static final String DELETE_SQL = "delete from merchant_assets_category where id = ?";




}

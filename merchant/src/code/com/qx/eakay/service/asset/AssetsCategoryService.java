package com.qx.eakay.service.asset;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.asset.AssetsCategoryDao;
import com.qx.eakay.export.asset.AssetCategoryExport;
import com.qx.eakay.model.asset.AssetCategoryPO;
import com.qx.eakay.service.BaseService;

/**
 * 资产种类管理
 * 
 * @author sdf
 * @date 2015年9月23日
 */
@Service
public class AssetsCategoryService extends BaseService {

	@Autowired
	private AssetsCategoryDao categoryDao;

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
		return categoryDao.findCategoryPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索资产种类
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findCategory(JSONObject jsonObject) {
		return categoryDao.findCategory(jsonObject);
	}

	/**
	 * 导出资产种类
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetPage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetCategoryExport();
		List<Map<String, Object>> list = findCategory(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找种类
	 * 
	 * @param id
	 * @return
	 */
	public AssetCategoryPO getCategory(Integer id) {
		return categoryDao.getCategory(id);
	}

	/**
	 * 保存种类修改
	 * 
	 * @param categoryPO
	 * @return
	 */
	public boolean updateCategory(AssetCategoryPO categoryPO) {
		return categoryDao.updateCategory(categoryPO);
	}

	/**
	 * 保存种类新增
	 * 
	 * @param categoryPO
	 */
	public boolean createCategory(AssetCategoryPO categoryPO) {
		return categoryDao.createCategory(categoryPO);
	}

	/**
	 * 删除资产种类
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		return categoryDao.delete(id);
	}

	/**
	 * 判断资产种类是否唯一
	 * 
	 * @param categoryPO
	 * @return
	 */
	public boolean check(String name,Integer id) {
		AssetCategoryPO assetCategoryPO = categoryDao.getCategoryByName(name);
		if(assetCategoryPO == null){
			return true;
		}
		if (id.equals(assetCategoryPO.getId())) {
			return true;
		}
		return false;
	}
	
	
}

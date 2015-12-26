package com.qx.eakay.service.asset;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.asset.AssetsManageDao;
import com.qx.eakay.export.asset.AssetReduceTotelExport;
import com.qx.eakay.export.asset.AssetRepairTotelExport;
import com.qx.eakay.export.asset.AssetTotelExport;
import com.qx.eakay.export.asset.AssetUseTotelExport;
import com.qx.eakay.model.asset.AssetPO;
import com.qx.eakay.service.BaseService;

/**
 * 资产信息管理
 * 
 * @author sdf
 * @date 2015年9月23日
 */
@Service
public class AssetsManageService extends BaseService {

	@Autowired
	private AssetsManageDao manageDao;

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
		return manageDao.findAssetPage(jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 条件搜索资产信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findAsset(JSONObject jsonObject) {
		return manageDao.findAsset(jsonObject);
	}

	/**
	 * 导出资产信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetPage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetTotelExport();
		List<Map<String, Object>> list = findAsset(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * 分页查询资产使用信息
	 * 
	 * @param jsonObject
	 * @param getiDisplayLength
	 * @param getiDisplayStart
	 * @return
	 */
	public PageResults findAssetUsePage(JSONObject jsonObject,
			 int pageSize,int iDisplayStart) {
		return manageDao.findAssetUsePage(jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 条件搜索资产使用信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findAssetUse(JSONObject jsonObject) {
		return manageDao.findAssetUse(jsonObject);
	}
	
	/**
	 * 导出资产使用信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetUsePage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetUseTotelExport();
		List<Map<String, Object>> list = findAssetUse(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 分页查询资产维修信息
	 * 
	 * @param jsonObject
	 * @param getiDisplayLength
	 * @param getiDisplayStart
	 * @return
	 */
	public PageResults findAssetRepairPage(JSONObject jsonObject,
			int pageSize,int iDisplayStart) {
		return manageDao.findAssetRepairPage(jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 条件搜索资产维修信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findAssetRepair(JSONObject jsonObject) {
		return manageDao.findAssetRepair(jsonObject);
	}
	
	/**
	 * 导出资产维修信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetRepair(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetRepairTotelExport();
		List<Map<String, Object>> list = findAssetRepair(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 分页查询资产减少信息
	 * 
	 * @param jsonObject
	 * @param getiDisplayLength
	 * @param getiDisplayStart
	 * @return
	 */
	public PageResults findAssetReducePage(JSONObject jsonObject,
			int pageSize,int iDisplayStart) {
		return manageDao.findAssetReducePage(jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 条件搜索资产减少信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findAssetReduce(JSONObject jsonObject) {
		return manageDao.findAssetReduce(jsonObject);
	}
	
	/**
	 * 导出资产减少信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetReducePage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetReduceTotelExport();
		List<Map<String, Object>> list = findAssetReduce(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 保存新增资产
	 * 
	 * @param assetPO
	 */
	public boolean createAsset(AssetPO assetPO) {
		return manageDao.createAsset(assetPO);
	}

	/**
	 * 查找资产信息
	 * 
	 * @param id
	 * @return
	 */
	public AssetPO getAsset(Integer id) {
		return manageDao.getAsset(id);
	}

	/**
	 * 保存修改资产
	 * 
	 * @param assetPO
	 * @return
	 */
	public boolean updateAsset(AssetPO assetPO) {
		return manageDao.updateAsset(assetPO);
	}
	
	/**
	 * 批量修改资产状态
	 * 
	 * @param assetPO
	 * @return
	 */
	public boolean batchUpdateAssetFlag(String flag, List<Integer> assetIds) {
		return manageDao.batchUpdateAssetFlag(flag, assetIds);
	}


	
}

package com.qx.eakay.service.asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.asset.AssetsManageDao;
import com.qx.eakay.dao.asset.AssetsUseDao;
import com.qx.eakay.export.asset.AssetUseDetailExport;
import com.qx.eakay.export.asset.AssetUseExport;
import com.qx.eakay.model.asset.AssetUseDetailPO;
import com.qx.eakay.model.asset.AssetUsePO;
import com.qx.eakay.service.BaseService;

/**
 * 资产领用管理
 * 
 * @author sdf
 * @date 2015年9月24日
 */
@Service
public class AssetsUseService extends BaseService {

	@Autowired
	private AssetsUseDao useDao;
	@Autowired
	private AssetsManageDao manageDao;

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
		return useDao.findUsePage(jsonObject, pageSize, iDisplayStart);
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
		return useDao.findUseDetailPage(jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 条件搜索资产领用信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findUse(JSONObject jsonObject) {
		return useDao.findUse(jsonObject);
	}

	/**
	 * 导出资产领用信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetUsePage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetUseExport();
		List<Map<String, Object>> list = findUse(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 条件搜索资产领用详细信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findUseDetail(JSONObject jsonObject) {
		return useDao.findUseDetail(jsonObject);
	}
	
	/**
	 * 导出资产领用详细信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetUseDetailPage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetUseDetailExport();
		List<Map<String, Object>> list = findUseDetail(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找资产领用信息
	 * 
	 * @param id
	 * @return
	 */
	public AssetUsePO getUse(Integer id) {
		return useDao.getUse(id);
	}

	/**
	 * 查找领用资产id
	 * 
	 * @param id
	 * @return
	 */
	public List<AssetUseDetailPO> getUseDetail(Integer useId) {
		return useDao.getUseDetail(useId);
	}
	
	/**
	 * 保存新增资产领用
	 * 
	 * @param assetPO
	 */
	@Transactional
	public boolean createAssetUse(AssetUsePO assetUsePO, Integer[] assetId) {
		int useId = useDao.create(assetUsePO);
		if(assetId != null){
			useDao.batchCreate(assetId, useId);
		}
		//更改资产状态
		List<Integer> listIds = new ArrayList<>();
		for (Integer id : assetId) {
			listIds.add(id);
		}
		manageDao.batchUpdateAssetFlag("在用",listIds);
		return useId > 0;
	}

	/**
	 * 修改领用单状态
	 * 
	 * @param flag
	 * @param useId
	 * @return
	 */
	public boolean updateAssetUseFlag(String flag, Integer useId) {
		return useDao.updateAssetUseFlag(flag,useId);
	}

	/**
	 * 保存修改资产领用
	 * 
	 * @param assetPO
	 * @return
	 */
	public boolean updateUse(AssetUsePO assetUsePO) {
		return useDao.updateUse(assetUsePO);
	}

	
}

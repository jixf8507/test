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
import com.qx.eakay.dao.asset.AssetsReduceDao;
import com.qx.eakay.export.asset.AssetReduceDetailExport;
import com.qx.eakay.export.asset.AssetReduceExport;
import com.qx.eakay.model.asset.AssetReducePO;
import com.qx.eakay.service.BaseService;

/**
 * 资产减少管理
 * 
 * @author sdf
 * @date 2015年9月25日
 */
@Service
public class AssetsReduceService extends BaseService {

	@Autowired
	private AssetsReduceDao reduceDao;
	@Autowired
	private AssetsManageDao manageDao;

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
		return reduceDao.findReducePage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 分页查询资产减少详细信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findRepairDetailPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return reduceDao.findRepairDetailPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索资产减少信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findReduce(JSONObject jsonObject) {
		return reduceDao.findReduce(jsonObject);
	}
	
	/**
	 * 条件搜索资产减少详细信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findReduceDetail(JSONObject jsonObject) {
		return reduceDao.findReduceDetail(jsonObject);
	}

	/**
	 * 导出资产减少信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetReducePage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetReduceExport();
		List<Map<String, Object>> list = findReduce(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出资产减少详细信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetReduceDetailPage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetReduceDetailExport();
		List<Map<String, Object>> list = findReduceDetail(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查找资产减少信息
	 * 
	 * @param id
	 * @return
	 */
	public AssetReducePO getReduce(Integer id) {
		return reduceDao.getReduce(id);
	}
	
	/**
	 * 保存新增资产减少
	 * 
	 * @param assetUsePO
	 * @param assetId
	 * @return
	 */
	@Transactional
	public boolean createAssetReduce(AssetReducePO assetReducePO, Integer[] assetId) {
		int reduceId = reduceDao.create(assetReducePO);
		if(assetId != null){
			reduceDao.batchCreate(assetId, reduceId);
		}
		//更改资产状态
		List<Integer> listIds = new ArrayList<>();
		for (Integer id : assetId) {
			listIds.add(id);
		}
		manageDao.batchUpdateAssetFlag(assetReducePO.getReduceStatus(),listIds);
		return reduceId > 0;
		
	}

	/**
	 * 保存修改资产减少信息
	 * 
	 * @param assetReducePO
	 * @return
	 */
	@Transactional
	public boolean updateAssetReduce(AssetReducePO assetReducePO) {
		// 修改减少信息
		reduceDao.updateAssetReduce(assetReducePO);
		//	查找 assetsId
		List<Integer> listIds = new ArrayList<>();
		
		JSONObject jsonObject = JSONObject.fromObject("{}");
		jsonObject.put("reduceId", assetReducePO.getId());
		List<Map<String, Object>> list = reduceDao.findReduceDetail(jsonObject);
		
		for(Map<String, Object> map : list){
			listIds.add(Integer.parseInt(map.get("assetsId")+""));
		}
		// 更改资产状态
		return manageDao.batchUpdateAssetFlag(assetReducePO.getReduceStatus(),listIds);
	}

	
}

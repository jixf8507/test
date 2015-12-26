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
import com.qx.eakay.dao.asset.AssetsRepairDao;
import com.qx.eakay.export.asset.AssetRepairDetailExport;
import com.qx.eakay.export.asset.AssetRepairExport;
import com.qx.eakay.model.asset.AssetRepairDetailPO;
import com.qx.eakay.model.asset.AssetRepairPO;
import com.qx.eakay.service.BaseService;

/**
 * 资产维修管理
 * 
 * @author sdf
 * @date 2015年9月25日
 */
@Service
public class AssetsRepairService extends BaseService {

	@Autowired
	private AssetsRepairDao repairDao;
	@Autowired
	private AssetsManageDao manageDao;

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
		return repairDao.findRepairPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 分页查询资产维修详细信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findRepairDetailPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return repairDao.findRepairDetailPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索资产维修信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findRepair(JSONObject jsonObject) {
		return repairDao.findRepair(jsonObject);
	}
	
	/**
	 * 条件搜索资产维修详细信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findRepairDetail(JSONObject jsonObject) {
		return repairDao.findRepairDetail(jsonObject);
	}

	/**
	 * 导出资产维修信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetRepairPage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetRepairExport();
		List<Map<String, Object>> list = findRepair(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出资产维修详细信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelAssetRepairDetailPage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new AssetRepairDetailExport();
		List<Map<String, Object>> list = findRepairDetail(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查找资产维修信息
	 * 
	 * @param id
	 * @return
	 */
	public AssetRepairPO getRepair(Integer id) {
		return repairDao.getRepair(id);
	}
	
	/**
	 * 查找资产维修详细信息
	 * 
	 * @param id
	 * @return
	 */
	public AssetRepairDetailPO getRepairDetail(Integer id) {
		return repairDao.getRepairDetail(id);
	}
	
	/**
	 * 保存修改资产维修详细信息
	 * 
	 * @param assetRepairPO
	 * @return
	 */
	public boolean updateAssetRepairDetail(AssetRepairDetailPO assetRepairDetailPO) {
		return repairDao.updateAssetRepairDetail(assetRepairDetailPO);
	}

	/**
	 * 保存新增资产维修
	 * 
	 * @param assetUsePO
	 * @param assetId
	 * @return
	 */
	@Transactional
	public boolean createAssetRepair(AssetRepairPO assetRepairPO, Integer[] assetId) {
		int repairId = repairDao.create(assetRepairPO);
		if(assetId != null){
			repairDao.batchCreate(assetRepairPO.getDetailPOs(), repairId);
		}
		//更改资产状态
		List<Integer> listIds = new ArrayList<>();
		for (Integer id : assetId) {
			listIds.add(id);
		}
//		manageDao.batchUpdateAssetFlag("在修",listIds);
		return repairId > 0;
	}

	/**
	 * 保存修改资产维修信息
	 * 
	 * @param assetRepairPO
	 * @return
	 */
	public boolean updateAssetRepair(AssetRepairPO assetRepairPO) {
		return repairDao.updateAssetRepair(assetRepairPO);
	}


	
}

package com.qx.eakay.service.stake;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.stake.StakeSiteAreaDao;
import com.qx.eakay.export.stake.StakeSiteAreaExport;
import com.qx.eakay.model.stake.StakeSiteAreaPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月27日
 */
@Service
public class SiteAreaService extends BaseService {

	@Autowired
	private StakeSiteAreaDao areaDao;

	/**
	 * 分页查找充电设备
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findAreaPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return areaDao.findAreaPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出充电设备列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportAreas(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new StakeSiteAreaExport();
		List<Map<String, Object>> list = areaDao.findAreas(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存充电设备新增
	 * 
	 * @param areaPO
	 * @return
	 */
	public boolean createArea(StakeSiteAreaPO areaPO) {
		return areaDao.create(areaPO) > 0;
	}

	public StakeSiteAreaPO getArea(Integer id) {
		return areaDao.get(id);
	}
	
	
	public boolean updateArea(StakeSiteAreaPO areaPO) {
	    return areaDao.update(areaPO);
	}

	public boolean deleteArea(Integer id) {
		return areaDao.delete(id);
	}

	public boolean checkCode(String areaCode, String siteCode, Integer id) {
		StakeSiteAreaPO areaPO = areaDao.findByCode(areaCode, siteCode);
		if (areaPO == null) {
			return true;
		}
		if (areaPO.getId().equals(id)) {
			return true;
		}
		return false;
	}

}

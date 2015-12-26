package com.qx.eakay.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.merchant.SiteDao;
import com.qx.eakay.dao.system.SysCityDao;
import com.qx.eakay.dao.system.SysProvinceDao;
import com.qx.eakay.export.merhcant.SiteExport;
import com.qx.eakay.model.merchant.SitePO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class SiteService extends BaseService {

	@Autowired
	private SiteDao siteDao;
	@Autowired
	private SysProvinceDao provinceDao;
	@Autowired
	private SysCityDao cityDao;

	/**
	 * 分页查询租赁点
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findSitePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return siteDao.findSitePage(jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 分页查询type为租赁点（type & 1 ==1）的租赁点
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findSitePageByType(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return siteDao.findSitePageByType(jsonObject, pageSize, iDisplayStart);
	}
	

	/**
	 * 条件搜索租赁点
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findSites(JSONObject jsonObject) {
		return siteDao.findSites(jsonObject);
	}

	/**
	 * 导出租赁点列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportSites(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new SiteExport();
		List<Map<String, Object>> list = findSites(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建租赁点
	 * 
	 * @param sitePO
	 * @return
	 */
	public boolean createSite(SitePO sitePO) {
		return siteDao.create(sitePO) > 0;
	}

	/**
	 * 修改租赁点
	 * 
	 * @param sitePO
	 * @return
	 */
	public boolean updateSite(SitePO sitePO) {
		return siteDao.update(sitePO);
	}

	/**
	 * 删除租赁点
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSite(Integer id) {
		return siteDao.delete(id);
	}

	/**
	 * 得到租赁点
	 * 
	 * @param id
	 * @return
	 */
	public SitePO getSite(Integer id) {
		return siteDao.get(id);
	}

	/**
	 * 验证租赁点名称是否重复
	 * 
	 * @param siteName
	 * @param merchantId
	 * @param id
	 * @return
	 */
	public boolean checkSiteName(String siteName, Integer merchantId, Integer id) {
		SitePO sitePO = siteDao.getByName(siteName, merchantId);
		if (sitePO == null) {
			return true;
		}
		if (sitePO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 查找省
	 * 
	 * @return
	 */
	public List<Map<String, Object>> findProvinces() {
		return provinceDao.findProvinces();
	}

	/**
	 * 查找租赁点
	 * 
	 * @param proID
	 * @return
	 */
	public List<Map<String, Object>> findCitys(String pName) {
		return cityDao.findCitys(pName);
	}

}

package com.qx.eakay.service.price;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.price.PriceTypeDao;
import com.qx.eakay.export.price.WhPriceTypeExport;
import com.qx.eakay.model.price.WhHoursPriceTypePO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月6日
 */
@Service
public class PriceTypeService extends BaseService {

	@Autowired
	private PriceTypeDao priceTypeDao;

	/**
	 * 分页查询收费套餐
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findTypesPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return priceTypeDao.findTypesPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索收费套餐
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findTypes(JSONObject jsonObject) {
		return priceTypeDao.findTypes(jsonObject);
	}

	/**
	 * 导出收费套餐列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportTypes(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new WhPriceTypeExport();
		List<Map<String, Object>> list = findTypes(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建收费规则套餐
	 * 
	 * @param priceTypePO
	 * @return
	 */
	public boolean createPriceType(WhHoursPriceTypePO priceTypePO) {
		return priceTypeDao.create(priceTypePO) > 0;
	}

	/**
	 * 根据ID获取收费规则套餐
	 * 
	 * @param id
	 * @return
	 */
	public WhHoursPriceTypePO getPriceType(Integer id) {
		return priceTypeDao.get(id);
	}

	/**
	 * 删除收费规则套餐
	 * 
	 * @param id
	 * @return
	 */
	public boolean deletePriceType(Integer id) {
		return priceTypeDao.delete(id);
	}

}

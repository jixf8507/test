package com.qx.eakay.service.price;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.price.LongPriceTypeDao;
import com.qx.eakay.export.price.MonthLeasePriceTypeExport;
import com.qx.eakay.model.price.MonthLeasePriceTypePO;

@Service
public class LongPriceTypeService {

	@Autowired
	private LongPriceTypeDao longPriceTypeDao;
	
	/**
	 * 创建长租收费规则套餐
	 * 
	 * @param monthLeasePriceTypePO
	 * @return
	 */
	public boolean createLongPriceType(MonthLeasePriceTypePO monthLeasePriceTypePO) {
		return longPriceTypeDao.createLongPriceType(monthLeasePriceTypePO) > 0;
	}

	/**
	 * 检查长租收费套餐名称是否唯一
	 * 
	 * @param typeName
	 * @param longPriceTypeId
	 * @return
	 */
	public boolean checkTypeNameOnly(String typeName) {
		MonthLeasePriceTypePO monthLeasePriceTypePO = longPriceTypeDao.checkTypeNameOnly(typeName);
		if(monthLeasePriceTypePO == null){
			return true;
		}
		return false;
	}

	
	/**
	 * 分页查询长租收费套餐
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findLongTypesPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return longPriceTypeDao.findLongTypesPage(jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 条件搜索长租收费套餐
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findLongTypes(JSONObject jsonObject) {
		return longPriceTypeDao.findLongTypes(jsonObject);
	}

	/**
	 * 导出长租收费套餐列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportLongTypes(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new MonthLeasePriceTypeExport();
		List<Map<String, Object>> list = findLongTypes(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除长租收费套餐
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteLongPriceType(Integer id) {
		return longPriceTypeDao.deleteLongPriceType(id);
	}

		
}

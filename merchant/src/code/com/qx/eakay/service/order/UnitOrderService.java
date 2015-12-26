package com.qx.eakay.service.order;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.order.UnitOrderDao;
import com.qx.eakay.export.order.UnitOrderExport;
import com.qx.eakay.export.order.UnitUseOrderExport;
import com.qx.eakay.service.BaseService;

/**
 * 单位会员订单管理
 * 
 * @author Administrator
 * 
 */
@Service
public class UnitOrderService extends BaseService {

	@Autowired
	private UnitOrderDao orderDao;

	/**
	 * 分页查询订单列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return orderDao.findOrderPage(jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 分页查询订单列表（只查询表格显示内容）
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findTableOrderPage(JSONObject jsonObject,int pageSize,
			int iDisplayStart) {
		return orderDao.findTableOrderPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索订单信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findOrders(JSONObject jsonObject) {
		return orderDao.findOrders(jsonObject);
	}

	/**
	 * 导出单位会员订单信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportOrder(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new UnitOrderExport();
		List<Map<String, Object>> list = findOrders(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出单位会员在租订单信息 
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void exportUseToExcel(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new UnitUseOrderExport();
		List<Map<String, Object>> list = findOrders(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

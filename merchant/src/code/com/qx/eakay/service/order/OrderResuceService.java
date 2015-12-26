package com.qx.eakay.service.order;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.order.OrderResuceDao;
import com.qx.eakay.export.order.OrderExport;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月13日
 */
@Service
public class OrderResuceService extends BaseService {

	@Autowired
	private OrderResuceDao orderResuceDao;

	/**
	 * 分页查询订单列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findResucePage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return orderResuceDao.findResucePage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 条件搜索订单信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findResuces(JSONObject jsonObject) {
		return orderResuceDao.findResuces(jsonObject);
	}

	/**
	 * 导出订单信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportResuces(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new OrderExport();
		List<Map<String, Object>> list = findResuces(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

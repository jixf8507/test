package com.qx.eakay.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.order.OrderDao;
import com.qx.eakay.export.merhcant.MerchantChargeRecordExport;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月10日
 */
@Service
public class ChargeRecordService extends BaseService {

	@Autowired
	private OrderDao orderDao;

	public PageResults findChargeRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return orderDao.findChargeRecordPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 导出商家账户交易明细记录
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportChargeRecord(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new MerchantChargeRecordExport();
		List<Map<String, Object>> list = findChargeRecord(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索商家账户交易明细记录信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findChargeRecord(JSONObject jsonObject) {
		return orderDao.findChargeRecord(jsonObject);
	}

}

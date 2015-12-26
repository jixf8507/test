package com.qx.eakay.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.customer.AccountRecordDao;
import com.qx.eakay.dao.customer.CustomerUnitDao;
import com.qx.eakay.export.merhcant.MerchantRefundRecordExport;
import com.qx.eakay.export.merhcant.MerchantUnitUserRefundRecordExport;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月11日
 */
@Service
public class RefundRecordService extends BaseService {

	@Autowired
	private AccountRecordDao accountRecordDao;
	@Autowired
	private CustomerUnitDao unitDao;
	
	/**
	 * 分页查询商家退款记录
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findRefudnRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return accountRecordDao.findRefudnRecordPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 导出商家账户交易明细记录
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportRefundRecord(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new MerchantRefundRecordExport();
		List<Map<String, Object>> list = findRefudnRecord(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出单位会员商家账户交易明细记录
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void exportUnitToExcel(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new MerchantUnitUserRefundRecordExport();
		List<Map<String, Object>> list = unitDao.findUnitCustomersTrade(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索商家账户退款明细记录信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findRefudnRecord(JSONObject jsonObject) {
		return accountRecordDao.findRefudnRecord(jsonObject);
	}

}

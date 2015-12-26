package com.qx.eakay.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.merchant.MerchantAccountRecordDao;
import com.qx.eakay.export.merhcant.MerchantAccountRecordExport;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月10日
 */
@Service
public class MerchantAccountRecordService extends BaseService {
	@Autowired
	private MerchantAccountRecordDao accountRecordDao;

	/**
	 * 分页查询商家账户交易明细记录
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findAccountRecordPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return accountRecordDao.findAccountRecordPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 导出商家账户交易明细记录
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportAccountRecord(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new MerchantAccountRecordExport();
		List<Map<String, Object>> list = findAccountRecord(jsonObject);
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
	public List<Map<String, Object>> findAccountRecord(JSONObject jsonObject) {
		return accountRecordDao.findAccountRecord(jsonObject);
	}

}

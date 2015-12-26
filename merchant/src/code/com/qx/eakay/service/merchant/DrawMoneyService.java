package com.qx.eakay.service.merchant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.merchant.MerchantDao;
import com.qx.eakay.dao.merchant.MerchantDrawMoneyDao;
import com.qx.eakay.export.merhcant.MerchantDrawMoneyExport;
import com.qx.eakay.model.merchant.MerchantDrawMoneyPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月11日
 */
@Service
public class DrawMoneyService extends BaseService {

	@Autowired
	private MerchantDrawMoneyDao drawMoneyDao;
	@Autowired
	private MerchantDao merchantDao;

	public PageResults findDrawMoneysPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return drawMoneyDao.findDrawMoneysPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 条件搜索租赁点
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findDrawMoneys(JSONObject jsonObject) {
		return drawMoneyDao.findDrawMoneys(jsonObject);
	}

	/**
	 * 导出租赁点列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportDrawMoneys(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new MerchantDrawMoneyExport();
		List<Map<String, Object>> list = findDrawMoneys(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增提款记录
	 * 
	 * @param drawMoneyPO
	 * @return
	 */
	@Transactional
	public boolean createDrawMoney(MerchantDrawMoneyPO drawMoneyPO) {
		// 更改商家账户余额
		merchantDao.addBalance(
				new BigDecimal(0).subtract(drawMoneyPO.getMoney()),
				drawMoneyPO.getMoney(), drawMoneyPO.getMerchantId());
		// 创建提款记录
		return drawMoneyDao.create(drawMoneyPO) > 0;
	}

}

package com.qx.eakay.service.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.eakay.dao.merchant.MerchantDao;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月10日
 */
@Service
public class MerchantService extends BaseService {

	@Autowired
	private MerchantDao merchantDao;

	/**
	 * 获取商家对象
	 * 
	 * @param id
	 * @return
	 */
	public MerchantPO getMerchant(Integer id) {
		return merchantDao.get(id);
	}

	/**
	 * 保存商家账户信息
	 * 
	 * @param merchantPO
	 */
	public boolean saveEdit(MerchantPO merchantPO) {
		return merchantDao.saveEdit(merchantPO);
	}

}

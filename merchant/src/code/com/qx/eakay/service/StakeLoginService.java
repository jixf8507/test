package com.qx.eakay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.eakay.dao.merchant.MerchantDao;
import com.qx.eakay.dao.merchant.MerchantUserDao;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.login.SessionUserBO.RightsTypes;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.model.merchant.MerchantUserPO;
import com.qx.eakay.util.MD5Util;

/**
 * 
 * @author jixf
 * @date 2015年7月25日
 */
@Service
public class StakeLoginService extends BaseService {

	@Autowired
	private MerchantDao merchantDao;

	@Autowired
	private MerchantUserDao merchantUserDao;

	/**
	 * 根据用户登录名和密码验证和获取用户信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public SessionUserBO validataUserLogin(String username, String password) {
		MerchantPO merchantPO = merchantDao.findByEmail(username);
		// 商家帐号登录
		if (merchantPO != null) {
			if (merchantPO.getLoginPassword().equals(MD5Util.MD516(password))) {
				SessionUserBO sessionUserBO = new SessionUserBO("stake");
				sessionUserBO.setMerchantId(merchantPO.getId());
				sessionUserBO.setName(merchantPO.getCorporation());
				sessionUserBO.setMerchantPO(merchantPO);
				sessionUserBO.setRightsName(RightsTypes.商家.name());
				// 设置商家的功能模块
				sessionUserBO.setMoudlesMunes(merchantDao
						.findMerchantMoudles(sessionUserBO.getSystemName()));
				return sessionUserBO;
			}
		}

		MerchantUserPO merchantUserPO = merchantUserDao.findByEmail(username);
		// 用户不存在
		if (merchantUserPO == null) {
			return null;
		}
		// 密码不正确
		if (!merchantUserPO.getPassword().equals(MD5Util.MD516(password))) {
			return null;
		}

		SessionUserBO sessionUserBO = new SessionUserBO("stake");
		sessionUserBO.setMerchantId(merchantUserPO.getMerchantId());
		sessionUserBO.setMerchantPO(merchantDao.get(merchantUserPO
				.getMerchantId()));
		sessionUserBO.setName(merchantUserPO.getUserName());
		sessionUserBO.setMerchantUserPO(merchantUserPO);
		sessionUserBO.setRightsName(merchantUserPO.getRights());
		// 设置商家用户登录后的功能模块
		sessionUserBO.setMoudlesMunes(merchantUserDao.findMerchantUserMoudles(
				merchantUserPO.getId(), sessionUserBO.getSystemName()));
		return sessionUserBO;

	}

}

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
 * 用户登录业务处理方法
 * 
 * @author Administrator
 * 
 */
@Service
public class LoginService extends BaseService {

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
				String city = merchantPO.getCity();
				if ("".equals(city) || city == null) {
					city = "北京市";
				}
				SessionUserBO sessionUserBO = new SessionUserBO();
				sessionUserBO.setMerchantId(merchantPO.getId());
				sessionUserBO.setName(merchantPO.getCorporation());
				sessionUserBO.setMerchantPO(merchantPO);
				sessionUserBO.setRightsName(RightsTypes.商家.name());
				sessionUserBO.setCity(city);
				// 设置商家的功能模块
				sessionUserBO.setMoudlesMunes(merchantDao
						.findMerchantMoudles(sessionUserBO.getSystemName()));
				return sessionUserBO;
			}
		} else {// 商家用户帐号登录
			MerchantUserPO merchantUserPO = merchantUserDao
					.findByEmail(username);
			if (merchantUserPO != null
					&& merchantUserPO.getPassword().equals(
							MD5Util.MD516(password))) {

				MerchantPO merchant = merchantDao.get(merchantUserPO
						.getMerchantId());
				String city = merchant.getCity();
				if ("".equals(city) || city == null) {
					city = "北京市";
				}
				SessionUserBO sessionUserBO = new SessionUserBO();
				sessionUserBO.setMerchantId(merchantUserPO.getMerchantId());
				sessionUserBO.setMerchantPO(merchant);
				sessionUserBO.setName(merchantUserPO.getUserName());
				sessionUserBO.setMerchantUserPO(merchantUserPO);
				sessionUserBO.setRightsName(merchantUserPO.getRights());
				sessionUserBO.setCity(city);
				// 设置商家用户登录后的功能模块
				sessionUserBO.setMoudlesMunes(merchantUserDao
						.findMerchantUserMoudles(merchantUserPO.getId(),
								sessionUserBO.getSystemName()));
				return sessionUserBO;
			}
		}
		return null;
	}

	/**
	 * 修改商家的登录页面
	 * 
	 * @param password
	 * @param merchantId
	 * @return
	 */
	public boolean updateMerchantPwd(String password, Integer merchantId) {
		return merchantDao.updatePassword(password, merchantId);
	}

	/**
	 * 修改商家员工的登录密码
	 * 
	 * @param password
	 * @param userId
	 * @return
	 */
	public boolean updateMerchantUserPwd(String password, Integer userId) {
		return merchantUserDao.updatePsd(password, userId);
	}
}

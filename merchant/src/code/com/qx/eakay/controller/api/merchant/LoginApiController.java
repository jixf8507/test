package com.qx.eakay.controller.api.merchant;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.service.LoginService;
import com.qx.eakay.util.MSG;

/**
 * 登录接口
 * 
 * @author sdf
 * @date 2015年12月9日
 */
@Controller
@RequestMapping("/api/merchant/login")
public class LoginApiController extends BaseController {

	@Autowired
	private LoginService loginService;

	/**
	 * 登录验证
	 * 
	 * @param request
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("validata.htm")
	@ResponseBody
	public HashMap<String, Object> validata(HttpServletRequest request)
			throws Exception {
		MSG msg = new MSG();
		HashMap<String, Object> result = new HashMap<>();

		String userName = request.getParameter("userName"); 
		String password = request.getParameter("password"); 

		if (userName == null || "".equals(userName)) {
			msg.setSuccess(false);
			msg.setInfo("用户名为空");
		} else if (password == null || "".equals(password)) {
			msg.setSuccess(false);
			msg.setInfo("密码为空");
		} else {
			SessionUserBO sessionUserBO = loginService.validataUserLogin(
					userName, password);

			if (sessionUserBO != null) {
				if ("商家".equals(sessionUserBO.getRightsName())) {
					msg.setSuccess(false);
					msg.setInfo("请使用员工账号登录");
				} else {
					// 登录成功
					msg.setSuccess(true);
					msg.setInfo("登录成功");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("merchantId", sessionUserBO.getMerchantId());
					map.put("userName", sessionUserBO.getMerchantUserPO()
							.getUserName());
					map.put("merchantName", sessionUserBO.getMerchantPO()
							.getMerchantName());
					map.put("mobilePhone", sessionUserBO.getMerchantUserPO()
							.getMobilePhone());
					map.put("siteId", sessionUserBO.getMerchantUserPO()
							.getSiteId());
					result.put("merchant", map);
				}
			} else {
				// 登录失败
				msg.setSuccess(false);
				msg.setInfo("用户名或密码不正确，登录失败");
			}
		}
		result.put("msg", msg);
		return result;
	}

}

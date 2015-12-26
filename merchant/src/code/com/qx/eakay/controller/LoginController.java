package com.qx.eakay.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.model.merchant.MerchantUserPO;
import com.qx.eakay.service.LoginService;
import com.qx.eakay.util.MD5Util;
import com.qx.eakay.util.MSG;

/**
 * 系统登录
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/system/login")
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;

	/**
	 * 
	 * 登录页面
	 * 
	 */
	@RequestMapping("login.htm")
	public ModelAndView login() throws Exception {
		return new ModelAndView("login");
	}

	/**
	 * 
	 * 登录用户验证
	 * 
	 */
	@RequestMapping("validata.htm")
	@ResponseBody
	public MSG validata(String username, String password, HttpSession session,HttpServletResponse response)
			throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUserBO = loginService.validataUserLogin(username,
				password);
		if (sessionUserBO != null) {
			// 登录成功
			msg.setSuccess(true);
			Cookie cookie = new Cookie("systemName",
					sessionUserBO.getSystemName());
			cookie.setMaxAge(60 * 60 * 24 * 30); // cookie 保存30天
			cookie.setPath("/"); // 这个不能少
			response.addCookie(cookie);
			
			// 保存session对象
			session.setAttribute(SESSION_USER, sessionUserBO);
			// 设置跳转到系统功能界面
			List<Map<String, Object>> fMenus = sessionUserBO.getFmenus();
			if (fMenus.isEmpty()) {
				msg.setInfo("system/message/noRights.htm");
			} else {
				msg.setInfo(fMenus.get(0).get("moduleUrl") + "");
			}
		} else {
			// 登录失败
			msg.setSuccess(false);
			msg.setInfo("用户名或密码不正确，登录失败");
		}
		return msg;
	}

	/**
	 * 
	 * 退出登录
	 * 
	 */
	@RequestMapping("out.htm")
	public ModelAndView out(HttpSession session) throws Exception {
		session.removeAttribute(SESSION_USER);
		return new ModelAndView("redirect:login.htm");
	}

	/**
	 * 修改密码
	 * 
	 * @param ypassword
	 * @param password
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updatePwd.htm")
	@ResponseBody
	public MSG updatePwd(String ypassword, String password, HttpSession session)
			throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUserBO = this.getSessionUser(session);
		MerchantUserPO merchantUserPO = sessionUserBO.getMerchantUserPO();
		if (merchantUserPO == null) {
			// 商家修改密码
			MerchantPO merchantPO = sessionUserBO.getMerchantPO();
			if (MD5Util.MD516(ypassword).equals(merchantPO.getLoginPassword())) {
				msg.setSuccess(loginService.updateMerchantPwd(
						MD5Util.MD516(password), merchantPO.getId()));
				return msg;
			}
			msg.setSuccess(false);
			msg.setInfo("原密码不正确。");
			return msg;
		}

		// 商家用户修改密码
		if (MD5Util.MD516(ypassword).equals(merchantUserPO.getPassword())) {
			msg.setSuccess(loginService.updateMerchantUserPwd(
					MD5Util.MD516(password), merchantUserPO.getId()));
			return msg;
		}
		msg.setSuccess(false);
		msg.setInfo("原密码不正确。");
		return msg;
	}
}

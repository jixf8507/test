package com.qx.eakay.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.service.StakeLoginService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月25日
 */
@Controller
@RequestMapping("/system/stakeLogin")
public class StakeLoginController extends BaseController {

	@Autowired
	private StakeLoginService loginService;

	/**
	 * 
	 * 登录页面
	 * 
	 */
	@RequestMapping("login.htm")
	public ModelAndView login() throws Exception {
		return new ModelAndView("stakeLogin");
	}

	/**
	 * 
	 * 登录用户验证
	 * 
	 */
	@RequestMapping("validata.htm")
	@ResponseBody
	public MSG validata(String username, String password, HttpSession session,
			HttpServletResponse response, HttpServletRequest request)
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
				msg.setInfo("/system/message/noRights.htm");
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

}

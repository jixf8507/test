package com.qx.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;

/**
 * 系统过滤器
 * 
 * @author Administrator
 * 
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("=========================>>请求 url " + url);
		if (url.indexOf("system") != -1) {
			return true;
		}
		if (url.indexOf("api") != -1) {
			return true;
		}

		SessionUserBO obj = (SessionUserBO) request.getSession().getAttribute(
				BaseController.SESSION_USER);

		if (obj == null) {
			String systemName = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("systemName")) {
						systemName = cookies[i].getValue();
					}
				}
			}
			PrintWriter out = response.getWriter();
			StringBuilder sb = new StringBuilder();
			sb.append("<script type='text/javascript' charset='utf-8'>");
			sb.append("alert('页面过期，请重新登录');");
			if ("stake".equals(systemName)) {
				sb.append("window.location.href='" + request.getContextPath()
						+ "/stake.html';");
			} else {
				sb.append("window.location.href='" + request.getContextPath()
						+ "/index.html';");
			}
			sb.append("</script>");
			out.print(sb.toString());
			out.close();
			return false;
		}
		String menuCode = request.getParameter("menuCode");
		if (menuCode != null) {
			System.out.println(menuCode);
			obj.setCurrenMenuCode(menuCode + "");
			request.getSession().setAttribute(BaseController.SESSION_USER, obj);
		}
		return super.preHandle(request, response, handler);
	}

}

package com.qx.eakay.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class URLUtil {
	/**
	 * 解析出请求url中的参数.
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> extractParam(HttpServletRequest request) {
		Enumeration<String> keys = request.getParameterNames();
		Map<String, String> params = new HashMap<String, String>();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			params.put(key, request.getParameter(key));
		}
		return params;
	}

}

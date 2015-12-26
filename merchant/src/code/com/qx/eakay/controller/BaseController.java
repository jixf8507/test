package com.qx.eakay.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qx.eakay.login.SessionUserBO;

public class BaseController {

	public static final String SESSION_USER = "user";
	protected String datafomat = "yyyy-MM-dd";
	protected Logger logger = Logger.getLogger(getClass());

	protected SessionUserBO getSessionUser(HttpSession session) {
		if (session.getAttribute(SESSION_USER) == null) {
			return null;
		}
		return (SessionUserBO) session.getAttribute(SESSION_USER);
	}

	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public String getJsonStr(Object obj) {
		Gson gson = new GsonBuilder().setDateFormat(this.datafomat).create();
		String json = null;
		try {
			json = gson.toJson(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("json " + json);
		return json;
	}

}

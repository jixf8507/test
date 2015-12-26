package com.qx.eakay.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class JsonUtil {
	public static void newSendJson(HttpServletResponse response,String str){
		
		//返回数据设为JSON格式
		response.setContentType("application/json;charset:utf-8");
		
		//设置浏览器禁用缓存
		response.setHeader("pragma", "no-cache");
		PrintWriter out;
		try{
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}

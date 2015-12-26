package com.qx.common.tools;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @author Administrator
 * 
 */
public class StringTools {

	public static boolean isNotBlank(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.toString().trim().equals("")) {
			return false;
		}
		return true;
	}

	public static String decodeMethod(String str) {
		String retStr = "";
		try {
			if (isNotBlank(str)) {
				retStr = java.net.URLDecoder.decode(str, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return retStr;
	}

}

package com.qx.eakay.util;


import java.security.MessageDigest;

/**
 * MD5加密方法
 * 
 * @author jixf
 * @2014-9-27
 */
public class MD5Util {

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			byte[] strTemp = s.getBytes("utf-8");
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// System.out.println((int)b);
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public final static String MD516(String arg) {
		String s = MD5(arg);
		s = s.substring(8, 24);
		return s.toUpperCase();
	}

	// 测试
	public static void main(String[] args) {
		String str = "123456";
		System.out.println("md5:" + MD5Util.MD516(str));
	}

}

package com.qx.eakay.model.monitor;


/**
 * 配置信息类.
 * 
 * @author chengyun.xia
 * @version V1.0
 */
public class Config {
	/** 生成用户的user_token的密钥 */
	// public static final String TOKEN_SECRET = "car";
	/** 分配给用户的app_secret,签名加密 */
	public static final String APP_SECRET = "car";
	/** 分配给用户的app_key,标识应用 */
	public static final String APP_KEY = "car";
	/** 标识请求的终端类型 */
	public static final String CLIENT_TYPE_MANAGER = "manager";
	/** 标识请求的终端类型 */
	public static final String CLIENT_TYPE_ANDROID = "android";
	// public static final String CLIENT_TYPE_IOS = "ios";
	//
	// public static final String VER = "1.0";

	public static final String CLIENT_URL = "http://192.168.1.47:9080/?";
}

package com.qx.eakay.util;

/**
 * 配置信息类.
 * 
 * @author
 * @version V1.0
 */
public class Config {
	/** 生成用户的user_token的密钥 */
	public static final String TOKEN_SECRET = "caruser";
	/** 分配给用户的app_secret,签名加密 */
	public static final String APP_SECRET = "car";
	/** 分配给用户的app_key,标识应用 */
	public static final String APP_KEY = "car";
	/** 标识请求的终端类型 */
	public static final String CLIENT_TYPE_ANDROID = "android";
	public static final String CLIENT_TYPE_IOS = "ios";
}

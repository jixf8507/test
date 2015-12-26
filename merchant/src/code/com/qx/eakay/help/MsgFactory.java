package com.qx.eakay.help;

public class MsgFactory {

	/**
	 * 获取验证码短信内容
	 * 
	 * @param velCode
	 *            验证码
	 * @return
	 */
	public static String getRegistMsg(String verCode) {
		return "易开租车验证码" + verCode + "，十分钟内有效";
	}
}

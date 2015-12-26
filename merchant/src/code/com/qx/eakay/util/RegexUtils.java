package com.qx.eakay.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式的工具类
 * @author xuwei
 *
 */
public class RegexUtils {
	/**
	 * @author xuwei
	 * 
	 * 身份证号码正则表达式
	 */
	public static final String ID_CARD_REGEX_STR="^(\\d{15}|\\d{17}[\\dx])$";
	
	
	
	/**
	 * @author xuwei
	 * 
	 * 正则表达式校验
	 * @param str 要检验的文本
	 * @param regex 正则表达式
	 * @return 返回布尔值是否匹配
	 */
	public static boolean matcher(String str,String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	
	
}

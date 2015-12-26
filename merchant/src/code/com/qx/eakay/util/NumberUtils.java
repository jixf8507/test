package com.qx.eakay.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 关于数字的操作的工具类
 * @author xuwei
 *
 */
public class NumberUtils {	
	/**
	 * @author xuwei
	 * 根据身份证的号码得到出生到现在有多少年
	 * @throws Exception 
	 */
	public static int getYearToCurrDateCard(String idCard) throws Exception{
		return getYearByIdCard(idCard, new Date());
	}
	
	/**
	 * @author xuwei
	 * 根据身份证的号码得到出生到指定时间有多少有多少年
	 * @param idCard
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static int getYearByIdCard(String idCard,Date date) throws Exception{
		Date birthday=getBirthdayByIdCard(idCard);
		int age=0;
		if (null!=birthday) {
			//计算生日到今年总共有多少年
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			int currentYear=cal.get(Calendar.YEAR);
			int currentMonth=cal.get(Calendar.MONTH);
			int currentMonthDate=cal.get(Calendar.DAY_OF_MONTH);
			
			cal.setTime(birthday);
			int birthYear=cal.get(Calendar.YEAR);
			int birthMonth=cal.get(Calendar.MONTH);
			int birthMonthDate=cal.get(Calendar.DAY_OF_MONTH);
			
			age=currentYear-birthYear;
			
			if (birthMonth==currentMonth&&birthMonthDate<=currentMonthDate) {
				age=age+1;
			}else if(birthMonth<=currentMonth){
				age=age+1;
			}
			
			//System.out.println(birthday+"到"+date+"有"+age+"年");
		}
		
		return age;
	}
	
	
	/**
	 * @author xuwei
	 * 根据身份证的号码得到生日
	 * @throws Exception 
	 * @throws ParseException 
	 */
	public static Date getBirthdayByIdCard(String idCard) throws Exception{
		Date date=null;
		//如果身份证号码不为空
		if (!StringUtils.isEmpty(idCard)) {
			//如果正则表达式通过确认是身份证号码，则提取时间信息
			if (RegexUtils.matcher(idCard, RegexUtils.ID_CARD_REGEX_STR)) {
				String dateStr = null;
				//15位身份证或者18位身份证
				if (idCard.length()==15) {
					dateStr=idCard.substring(6, 12);
					//15位身份证没有19的前缀
					dateStr="19"+dateStr;
				}else if(idCard.length()==18) {
					dateStr=idCard.substring(6, 14);
				}
				SimpleDateFormat sf =new SimpleDateFormat("yyyyMMdd");
				try {
					date=sf.parse(dateStr);
				} catch (ParseException e) {
					throw new Exception("身份证号码："+idCard+"|日期："+dateStr+"_字符串转换时间异常");
				}
				return date;
			}
		}
		return date;
	}
}

package com.qx.common.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

public class MyDateUtil extends DateUtils {

	/**
	 * 获取指定年份和月份对应的天数
	 * 
	 * @param year
	 *            指定的年份
	 * @param month
	 *            指定的月份
	 * @return int 返回天数
	 */
	public static int getDaysInMonth(int year, int month) {
		if ((month == 1) || (month == 3) || (month == 5) || (month == 7)
				|| (month == 8) || (month == 10) || (month == 12)) {
			return 31;
		} else if ((month == 4) || (month == 6) || (month == 9)
				|| (month == 11)) {
			return 30;
		} else {
			if (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0)) {
				return 29;
			} else {
				return 28;
			}
		}
	}

	/**
	 * 根据所给的起止时间来计算间隔的天数
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return int 返回间隔天数
	 */
	public static int getIntervalDays(Date startDate,
			Date endDate) {
		if(startDate==null || endDate==null){
			return 0 ;
		}
		long startdate = startDate.getTime();
		long enddate = endDate.getTime();
		long interval = enddate - startdate;
		int intervalday = (interval % (1000 * 60 * 60 * 24)==0)?(int) (interval / (1000 * 60 * 60 * 24)):(int) (interval / (1000 * 60 * 60 * 24))+1;
		return intervalday;
	}
	
	public static int getIntervalHours(Date startDate,
			Date endDate) {
		if(startDate==null || endDate==null){
			return 0 ;
		}
		long startdate = startDate.getTime();
		long enddate = endDate.getTime();
		long interval = enddate - startdate;
		int intervalday = (int) (interval / (1000 * 60 * 60)) +(interval % (1000 * 60 * 60)==0?0:1);
		return intervalday;
	}

	/**
	 * 根据所给的起止时间来计算间隔的月数
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return int 返回间隔月数
	 */
	public static int getIntervalMonths(java.sql.Date startDate,
			java.sql.Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		int startDateM = startCal.MONTH;
		int startDateY = startCal.YEAR;
		int enddatem = endCal.MONTH;
		int enddatey = endCal.YEAR;
		int interval = (enddatey * 12 + enddatem)
				- (startDateY * 12 + startDateM);
		return interval;
	}

	/**
	 * 返回当前日期时间字符串<br>
	 * 默认格式:yyyy-mm-dd hh:mm:ss
	 * 
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime() {
		String returnStr = null;
		Date date = new Date();
		returnStr = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"); // f.format(date);
		return returnStr;
	}

	/**
	 * 返回自定义格式的当前日期时间字符串
	 * 
	 * @param format
	 *            格式规则
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime(String format) {
		String returnStr = null;
		Date date = new Date();
		returnStr = DateFormatUtils.format(date, format);
		return returnStr;
	}

	/**
	 * 返回当前字符串型日期
	 * 
	 * @return String 返回的字符串型日期
	 */
	public static String getCurDate() {
		Calendar calendar = Calendar.getInstance();
		String strDate = DateFormatUtils.format(calendar.getTime(),
				"yyyy-MM-dd");
		return strDate;
	}
	
	/**
	 * 返回当前字符串型日期
	 * 
	 * @return String 返回的字符串型日期
	 */
	public static String getCurMonth() {
		Calendar calendar = Calendar.getInstance();
		String strDate = DateFormatUtils.format(calendar.getTime(),
				"yyyy-MM");
		return strDate;
	}
	
	/**
	 * 字符串转换为时间
	 */
	public static Date stringToDate(String str)throws ParseException{
		str = str.replaceAll("年", "-");
		str = str.replaceAll("月", "-");
		str = str.replaceAll("日", " ");
		str = str.replaceAll("点", ":");
		if(str.indexOf("分") != -1 && str.indexOf("秒") != -1)
		    str = str.replaceAll("分", ":");
		else if(str.indexOf("分") != -1 && str.indexOf("秒") == -1)
		    str = str.replaceAll("分", "");
		
		if(str.indexOf("秒")==-1)
			str += ":00";
		else
		    str = str.replaceAll("秒", "");
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
	}
	
	/**
	 * 时间转换成字符串
	 */
	public static String dateToString(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	/**
	 * 时间转换成字符串
	 */
	public static String dateToStringHf(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}
	
	/**
	 * 时间转换成字符串
	 */
	public static String dateToStringH(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH").format(date);
	}
	
	public static String getMonthString(Date date){
		return new SimpleDateFormat("yyyy-MM").format(date);
	}
	
	public static String getDayString(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	/**
	 * 把时间转化成年月日格式字符串
	 * @param date
	 * @return
	 */
	public static String dateToStringChian(Date date){
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		return sdf.format(new Date());
	}
	
	
	public static Date HourStrToDate(String str){
		Calendar current = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		Date date = null;
		try {
			date = sdf.parse(str);
			current.setTime(date) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date ;
	}
	
	public static Date HfStrToDate(String str){
		Calendar current = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(str);
			current.setTime(date) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date ;
	}
	
	public static String isExceedManagerTime(Date in_date,int in_hour) {
		Calendar current = Calendar.getInstance();
		current.add(Calendar.HOUR, -in_hour) ;
		Date executeDate = current.getTime();
		
		if(executeDate.getTime()<in_date.getTime()){
			return "true" ;
		}else{
			return "false";
		}
	}
	 
///// date to yyyy-MM-dd
	public static String DateToStry_M_d(Date date)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
	///// date to yyyyMMdd
	public static String DateToStryMd(Date date)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	public static String DateToStryMdhms(Date date)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	//// string to date
	 
	public static Date StrToDatey_M_d(String str) throws ParseException {
		return DateUtils.parseDate(str, new String[] { "yyyyMMdd" });
	}
     
	
	////string to date
	public static Date StrToDateyMd(String str) throws ParseException {
		return DateUtils.parseDate(str, new String[] { "yyyy-MM-dd" });
	}
	
	public static Date StrToDateyM(String str) throws ParseException {
		return DateUtils.parseDate(str, new String[] { "yyyy-MM" });
	}
	
	////string to date
	public static Date StrToDateyMdhms(String str) throws ParseException {
		return DateUtils.parseDate(str, new String[] { "yyyy-MM-dd HH:mm:ss" });
	}
	
	//// sunday=1 monday=2 TUESDAY=3 WEDNESDAY=4 thursday=5 friday=6 saturday=7
	public static int getWeekofDay(Date date)
	{
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(date);
		return gc.get(GregorianCalendar.DAY_OF_WEEK);
	}
	
	public static Date getDaytoCurrDay(Date date,int intDay)
	{
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.DATE, intDay);
		return gc.getTime();
	}
	
	
	public static String getStrDateAddMinitues(String strDate,int intMin) throws ParseException 
	{
		Date date=StrToDateyMdhms(strDate);
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.MINUTE, intMin);
		return DateToStryMdhms(gc.getTime()) ;
	}
	
	   public static String getWeekDayBefore(String day) {
			Calendar current = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			try {
				date = sdf.parse(day);
				current.setTime(date) ;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			current.add(Calendar.DATE, -1);
			Date executeDate = current.getTime();
			return sdf.format(executeDate);
		}
	   
	   /**
		 * 返回当前字符串型日期
		 * 
		 * @return String 返回的字符串型日期
		 */
		public static String getCurDate(int d) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, d);
			String strDate = DateFormatUtils.format(calendar.getTime(),
					"yyyy-MM-dd");
			return strDate;
		}
}
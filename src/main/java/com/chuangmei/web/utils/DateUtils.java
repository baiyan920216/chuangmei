package com.chuangmei.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**  
*   
* 项目名称：Kod  
* 包名 com.kod.utils
* 类名称：DateUtils  
* 类描述：  -== 日期工具类 ==-
* 创建人：yuanqi.jing
* 创建时间：2014-11-11 上午8:54:56  
* 修改备注：  
* @version  1.0 
*   
*/ 
public class DateUtils {

   protected static final Logger log = LoggerFactory.getLogger(DateUtils.class);
	
	// 将天转换成微秒
	public static final long DAY_TIME = 24 * 60 * 60 * 1000;
	public static final TimeZone TZ = TimeZone.getDefault();

	/**
	 * 取得当前日期字符串，格式自定义
	 * 
	 * @param dateFormat
	 * @return String
	 */
	public static String getDateTime(String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 取得当前日期字符串，格式："yyyy-MM-dd"
	 * @return String
	 */
	public static String getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	/**
	 * 取得当前月份字符串，格式："yyyy-MM"
	 * @return String
	 */
	public static String getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		return df.format(date);
	}
	
	/**
	 * 取得当前日期前一天的日期
	 * @param date
	 * @return
	 */
	public static Date getLastDay() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 取得当前日期前一天的日期字符串，格式："yyyy-MM-dd"
	 * @param date
	 * @return
	 */
	public static String getLastDayStr() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	/**
	 * 取得日期前一天的日期字符串，格式："yyyy-MM-dd"
	 * @param date
	 * @return
	 */
	public static String getLastDayStr(String dateStr) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			log.error("getLastDayStr()日期转换异常{}" + e);
		}
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return df.format(date);
	}
	
	
	/**
	 * 取得日期的相对的日期字符串，格式："yyyy-MM-dd"
	 * @param date
	 * @return
	 */
	public static String getDateStr(String dateStr,int dayNum) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			log.error("getLastDayStr()日期转换异常{}" + e);
		}
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayNum);
		date = calendar.getTime();
		return df.format(date);
	}

	/**
	 * 转换日历对象为日期字符串
	 * 
	 * @author xjb
	 * @param  Calendar  字符串
	 * @return Date   日期（yyyy-MM-dd HH:mm:ss）
	 */
	public static Date convertCalToDate(Calendar da) {
		Date date = da.getTime();
		return date;
	}

	/**
	 * 取得当前日期字符串，格式："yyyy-MM-dd HH:mm:ss"
	 * @return String
	 */
	public static String getCurrentDateTime() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currDate = df.format(date);
		return currDate;
	}
	
	/**
	 * 取得当前日期字符串，格式："yyyy-MM-dd HH:mm:ss"
	 * @return String
	 */
	public static String getDateTime(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(date);
		return dateStr;
	}
	
	/**
	 * 取得当前日期字符串，格式："yyyy-MM-dd"
	 * @return String
	 */
	public static String getDateStr(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(date);
		return dateStr;
	}

	/**
	 * 转换日历对象为时间对象
	 * 
	 * @param cal
	 * @return Date
	 */
	public static Date convertCalendarToDate(Calendar cal) {
		long day = cal.getTimeInMillis();
		day = (day + TZ.getOffset(day)) / DAY_TIME;
		day = day * DAY_TIME;
		return new Date(day - TZ.getOffset(day));
	}

	/**
	 * 按天比较两个时间对象的大小
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static int compareDay(Date arg0, Calendar arg1) {
		int firstDay = (int) ((arg0.getTime() + TZ.getOffset(arg0.getTime())) / DAY_TIME);
		int secendDay = (int) ((arg1.getTimeInMillis() + TZ.getOffset(arg0
				.getTime())) / DAY_TIME);
		return firstDay - secendDay;
	}

	/**
	 * 计算两个时间对象相差的天数
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static int calcWorkdayCount(Date arg0, Date arg1) {
		if (arg0 == null || arg1 == null)
			return 0;
		int firstDay = (int) (arg0.getTime() / DAY_TIME);
		int secendDay = (int) (arg1.getTime() / DAY_TIME);
		return (firstDay - secendDay) + 1;
	}

	/**
	 * 累加日期对象的时间
	 * 
	 * @param val
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date addDate(Date val, int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(val);
		if (year != 0)
			cal.add(Calendar.YEAR, year);
		if (month != 0)
			cal.add(Calendar.MONTH, month);
		if (day != 0)
			cal.add(Calendar.DATE, day);
		return convertCalendarToDate(cal);
	}
	public static Date addDate1(Date val, int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(val);
		if (year != 0)
			cal.add(Calendar.YEAR, year);
		if (month != 0)
			cal.add(Calendar.MONTH, month);
		if (day != 0)
			cal.add(Calendar.DATE, day);
		return cal.getTime();
	}
	
	public static Date addHours(Date val,int hours){
		Calendar cal = Calendar.getInstance();
		cal.setTime(val);
		if (hours != 0)
			cal.add(Calendar.HOUR, hours);
		return cal.getTime();
	}

	/**
	 * 比较当前时间在指定时间段是否过半
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static double compareDayToHalf(Date arg0, Date arg1, Calendar arg2) {
		double firstDay = arg0.getTime() / DAY_TIME;
		double secendDay = arg1.getTime() / DAY_TIME;
		double current = arg2.getTimeInMillis() / DAY_TIME;
		return ((secendDay - firstDay + 1) / 2 + firstDay) - current;
	}
	/**
	 * 
	 * @Title：getDayNum
	 * @Description: TODO 
	 * @date 2015年2月11日 上午9:48:05
	 * @param dateStr 时间
	 * @return 此月的天数
	 */
	public static int getDayNum(String dateStr) {
		Calendar c = Calendar.getInstance();// 获得一个日历的实例
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.setTime(date);// 设置日历时间
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
		//int dayNum = c.get(Calendar.DAY_OF_MONTH);
		return c.get(Calendar.DAY_OF_MONTH);
	}
}

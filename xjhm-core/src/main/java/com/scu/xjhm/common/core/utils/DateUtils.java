package com.scu.xjhm.common.core.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

//import org.apache.commons.lang.StringUtils;

/**
 * Copyright C 四川启明星银海科技有限公司 All right reserved. 创建者：杨猛 2009-3-23 Title :
 * DateUtils.java Description: DateUtils 类说明 备注 :
 * 使用开源包commons-logging.jar,log4j.jar version: V1.0 2009-3-23 <author> -
 * <version > - <time> - <desc> 杨猛 V1.0 2009-3-23 完成基本功能
 */
public class DateUtils {
	private DateUtils() {
	}

//	protected static final Log logger = LogFactory.getLog(DateUtils.class);

	/**
	 * 日期类型：yyyy-MM-dd
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * 日期类型：HH:mm:ss
	 */
	public static final String TIME_PATTERN = "HH:mm:ss";

	/**
	 * 日期类型：HH:mm
	 */
	public static final String SHORT_TIME_PATTERN = "HH:mm";

	/**
	 * 日期类型：yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_TIME_PATTERN = DATE_PATTERN + " "
			+ TIME_PATTERN;

	/**
	 * 日期类型：yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_TIME_PATTERN_ORACLE = "yyyy-MM-dd hh24:mi:ss";

	/**
	 * 一天的毫秒数
	 */
	public static final long MILLISECONDS_DAY = 1000 * 3600 * 24;

	/**
	 * 默认日期格式类
	 */
	private static final DateFormat defaultFormat = new SimpleDateFormat(
			DATE_PATTERN);

	public static final int DAY = Calendar.DAY_OF_YEAR;

	public static final int MONTH = Calendar.MONTH;

	public static final int YEAR = Calendar.YEAR;

	public static final int MINUTE = Calendar.MINUTE;

	public static DateUtils getInstance() {
		return new DateUtils();
	}

	/**
	 * 静态方法 说明：取得当前时间 采用默认格式 yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getCurrentlyTime() {
		return getCurrentlyTime(DATE_PATTERN);
	}

	/**
	 * 静态方法 取得当前的时间
	 * 
	 * @param patten
	 *            返回的时间格式
	 * @return
	 */
	public static String getCurrentlyTime(String patten) {
		Calendar cal = Calendar.getInstance();
		return convertDateToStr(cal.getTime(), patten);
	}

	public static Date getCurrentlyDate() {
		Calendar cal = Calendar.getInstance();
		return convertStrToDate(convertDateToStr(cal.getTime()));
	}

	/**
	 * 类方法说明：取得当前时间
	 * 
	 * @return
	 */
	public static Date getDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	/**
	 * @title  
     * @author ywang
     * @param 
     * @return
     * @version  
     *     20160813am
     *     1.new.
	 */
	public static long getTimeInMillis(){
		Calendar cal = Calendar.getInstance();
		return cal.getTimeInMillis();
	}

	public static int getDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 类方法说明：日期类型（yyyy-MM-dd）------->字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateToStr(Date date) {
		if (null == date) {
			return "";
		}
		return defaultFormat.format(date);
	}

	public static Timestamp convertUtilDateToTimestamp(Date date) {
		if (null == date) {
			return null;
		}
		Timestamp sDate = new Timestamp(date.getTime());
		return sDate;
	}
	
	public static Date parseToDate(String dateStr) throws ParseException{
		Pattern pattern=Pattern.compile("^\\d{4}\\-\\d{2}\\-\\d{2}$");
		Matcher matcher =pattern.matcher(dateStr);
		if(matcher.matches()){
			return defaultFormat.parse(dateStr); 
		}
		
		Pattern pattern2=Pattern.compile("^\\d{4}\\-\\d{2}\\-\\d{2} \\d{2}\\:\\d{2}\\:\\d{2}$");
		Matcher matcher2 =pattern2.matcher(dateStr);
		if(matcher2.matches()){
			DateFormat df = new SimpleDateFormat(
					DATE_TIME_PATTERN);
			return df.parse(dateStr);
		}
		throw new IllegalArgumentException(String.format("%s日期格式错误！",dateStr));
	}
	
	public static Date convertCTSStrToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);
		Date date = new Date();
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static java.sql.Date convertDateToSQLDate(Date date) {
		java.sql.Date sDate = java.sql.Date.valueOf(DateUtils.convertDateToStr(
				date, DateUtils.DATE_TIME_PATTERN));

		return sDate;
	}

	/**
	 * 类方法说明：根据传入的pattern，日期类型------->字符串
	 * 
	 * @param date
	 * @param pattern
	 *            日期的格式
	 * @return
	 */
	public static String convertDateToStr(Date date, String pattern) {
		if (null == date || null == pattern) {
			return "";
		}
		DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		if (null == date) {
			date = new Date();
		}
		return simpleDateFormat.format(date);
	}

	/**
	 * 类方法说明：字符串（yyyy-MM-dd）------->日期类型
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date convertStrToDate(String dateStr) {
		Date date = null;
		if (null == dateStr || "".equals(dateStr.trim())) {
			return null;
		}
		if (dateStr.length() > 10) {
//			logger.error(dateStr + "字符串格式错误");
			throw new java.lang.IllegalArgumentException("字符串格式错误(yyyy-MM-dd)"
					+ dateStr);

		}
		try {
			date = defaultFormat.parse(dateStr);
		} catch (ParseException e) {
//			logger.error("字符串---->日期出错", e);
			throw new java.lang.IllegalArgumentException("字符串格式错误(yyyy-MM-dd)"
					+ dateStr);
		}
		return date;

	}

	public static Date convertStrToDate(String dateStr, String pattern) {
		Date date = null;
		if (null == dateStr || "".equals(dateStr.trim())) {
			return null;
		}
		try {
			DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
//			logger.error("字符串---->日期出错", e);
//			throw new java.lang.IllegalArgumentException("字符串格式错误(" + pattern
//					+ ")" + dateStr);
		}
		return date;

	}

	/**
	 * 类方法说明：日期加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return
	 */
	public static Date add(Date date, int day) {
		return add(date, DateUtils.DAY, day);
	}

	/**
	 * 取得日期的当月最后一天
	 * 
	 * @param sDate1
	 * @return
	 */
	public static Date getLastDayOfMonth(Date sDate1) {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(sDate1);
		int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
		cDay1.set(Calendar.DAY_OF_MONTH, lastDay);
		return cDay1.getTime();
	}

	/**
	 * 取得日期的当月第一天
	 * 
	 * @param sDate1
	 * @return
	 */
	public static Date getFirestDayOfMonth(Date sDate1) {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(sDate1);
		int day = cDay1.getActualMinimum(Calendar.DAY_OF_MONTH);
		cDay1.set(Calendar.DAY_OF_MONTH, day);
		cDay1.set(Calendar.HOUR_OF_DAY, 0);
		cDay1.set(Calendar.MINUTE, 0);
		cDay1.set(Calendar.SECOND, 0);
		return cDay1.getTime();
	}

	/**
	 * 类方法说明：日期加
	 * 
	 * @param date
	 *            日期
	 * @param minute
	 *            分钟
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		return add(date, DateUtils.MINUTE, minute);
	}

	/**
	 * 类方法说明：增加时间 date==null 返回当前时间加
	 * 
	 * @param date
	 *            日期
	 * @param field
	 *            增加的类型（DAY,MONTH,MINUTE）
	 * @param arg
	 *            增量
	 * @return
	 */
	public static Date add(Date date, int field, int arg) {
		if (null == date) {
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, arg);
		return cal.getTime();
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param startTime
	 *            开始时间 yyyy-MM-dd HH:mm:SS;
	 * @param endDate
	 *            结束时间 yyyy-MM-dd HH:mm:SS
	 * @return 0为等于，1为大于，-1位小于
	 */
	public static int dateCompareTo(Date startTime, Date endDate) {
		int flag = 0;
		try {
			flag = startTime.compareTo(endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 类方法说明：开始时间大于结束时间返回true
	 * 
	 * @param startTime
	 * @param endDate
	 * @return
	 */
	public static boolean dateGreater(Date startTime, Date endDate) {
		boolean flag = false;
		int flagInt = dateCompareTo(startTime, endDate);
		if (flagInt == 1) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 类方法说明：开始时间大于或者等于结束时间返回true
	 * 
	 * @param startTime
	 * @param endDate
	 * @return
	 */
	public static boolean dateGreaterEquals(Date startTime, Date endDate) {
		boolean flag = false;
		int flagInt = dateCompareTo(startTime, endDate);
		if (flagInt == 1 || flagInt == 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 类方法说明：小于等于
	 * 
	 * @param startTime
	 * @param endDate
	 * @return
	 */

	public static boolean dateLessEquals(Date startTime, Date endDate) {
		boolean flag = false;
		int flagInt = dateCompareTo(startTime, endDate);
		if (flagInt == 0 || flagInt == -1) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 类方法说明：取得当前年份
	 * 
	 * @return
	 */
	public static String getYear() {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.YEAR));

	}

	/**
	 * 类方法说明：取得当前月份
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.MONTH) + 1);

	}

	/**
	 * 类方法说明：取得上个月
	 * 
	 * @return
	 */
	public static String getNextMonth() {
		String date = "";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, 26);
		date = convertDateToStr(cal.getTime());
		return date;
	}
	public static String getNewReportMonth(Date date){
		String reportDate = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 26);
		reportDate = convertDateToStr(cal.getTime());
		return reportDate;
	}

	/**
	 * 取得上月第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getUpMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, 1);
		date = cal.getTime();
		return date;
	}

	public static int getMonthByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getMonthByDate(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.convertStrToDate(date));
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getYearByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static int getYearByDate(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.convertStrToDate(date));
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 类方法说明：根据时间的年月 获得结算时间的开始时间和结束时间
	 * 
	 * @return
	 */
	public static Map<String, String> getSTimeETime(int year, int month) {
		Map<String, String> map = new HashMap<String, String>();
		if (month < 1 && month > 12)
			return map;
		// Calendar cal = Calendar.getInstance();
		// cal.set(year, month - 1, 25);
		// map.put("endTime", defaultFormat.format(cal.getTime()));
		// cal.add(Calendar.MONTH, -1);
		// cal.set(Calendar.DATE, 26);
		// map.put("startTime", defaultFormat.format(cal.getTime()));

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		map.put("startTime", defaultFormat.format(cal.getTime()));
		cal.add(Calendar.MONTH, 1);
		// cal.add(Calendar.DAY_OF_MONTH, -1);
		map.put("endTime", defaultFormat.format(cal.getTime()));
		return map;
	}

	/**
	 * 类方法说明：根据时间的年月 获得结算时间的开始时间和结束时间
	 * 
	 * @return
	 */
	public static Map<String, Object> getSDateEDate(int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (month < 1 && month > 12)
			return map;
		// Calendar cal = Calendar.getInstance();
		// cal.set(year, month - 1, 25);
		// map.put("endTime", defaultFormat.format(cal.getTime()));
		// cal.add(Calendar.MONTH, -1);
		// cal.set(Calendar.DATE, 26);
		// map.put("startTime", defaultFormat.format(cal.getTime()));

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		map.put("startTime", cal.getTime());
		cal.add(Calendar.MONTH, 1);
		// cal.add(Calendar.DAY_OF_MONTH, -1);
		map.put("endTime", cal.getTime());
		return map;
	}

	/**
	 * 取得开始时间和结束时间之间的时间
	 * 
	 * @param beginDate
	 *            开始时间 YYYY-MM-DD
	 * @param endDate
	 *            结束时间 YYYY-MM-DD
	 * @return
	 */
	public static String[] getDate(String beginDate, String endDate) {
		String[] dateList = null;
		try {
			// 如果结束日期为空，返回开始日期
			if (endDate == null || endDate.equals("")) {
				return new String[] { defaultFormat.format(defaultFormat
						.parse(beginDate)) };
			}
			Date bDate = defaultFormat.parse(beginDate);
			Date eDate = defaultFormat.parse(endDate);
			Calendar calB = Calendar.getInstance();
			Calendar calE = Calendar.getInstance();
			calB.setTime(bDate);
			calE.setTime(eDate);
			// 如果结束日期<=开始日期，返回开始日期
			if (calE.compareTo(calB) == 0 || calE.compareTo(calB) == -1) {
				return new String[] { defaultFormat.format(defaultFormat
						.parse(beginDate)) };
			} else if (calE.compareTo(calB) == 1) {// 如果结束日期>开始日期
				long daterange = calE.getTimeInMillis()
						- calB.getTimeInMillis();
				int theday = (int) (daterange / MILLISECONDS_DAY) + 1;
				dateList = new String[theday];
				for (int i = 0; i < theday; i++) {
					calB.setTime(bDate);
					calB.add(Calendar.DAY_OF_YEAR, i);
					dateList[i] = defaultFormat.format(calB.getTime());

				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateList;
	}

	/**
	 * 取得开始时间和结束时间之间的时间
	 * 
	 * @param beginDate
	 *            开始时间 YYYY-MM-DD
	 * @param endDate
	 *            结束时间 YYYY-MM-DD
	 * @return
	 */
	public static List<Date> getDate(Date beginDate, Date endDate) {
		List<Date> dateList = null;
		try {
			Calendar calB = Calendar.getInstance();
			Calendar calE = Calendar.getInstance();
			calB.setTime(beginDate);
			calE.setTime(endDate);
			if (calE.compareTo(calB) == -1) { // 如果结束日期<开始日期，返回NULL
				// dateList.add(beginDate);
				dateList = null;
			} else if (calE.compareTo(calB) == 0) { // 如果结束日期=开始日期，返回开始日期
				dateList = new ArrayList<Date>(1);
				dateList.add(beginDate);
			} else if (calE.compareTo(calB) == 1) {// 如果结束日期>开始日期
				long daterange = calE.getTimeInMillis()
						- calB.getTimeInMillis();
				int theday = (int) (daterange / MILLISECONDS_DAY);
				dateList = new ArrayList<Date>(theday);
				for (int i = 0; i < theday; i++) {
					calB.setTime(beginDate);
					calB.add(Calendar.DAY_OF_YEAR, i);
					dateList.add(calB.getTime());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList;
	}

	/**
	 * 取得开始时间和结束时间之间的时间
	 * 
	 * @param beginDate
	 *            开始时间 YYYY-MM-DD
	 * @param endDate
	 *            结束时间 YYYY-MM-DD
	 * @return
	 */
	public static List<Date> getDate2(Date beginDate, Date endDate) {
		List<Date> dateList = new ArrayList<Date>();
		try {
			Calendar calB = Calendar.getInstance();
			Calendar calE = Calendar.getInstance();
			calB.setTime(beginDate);
			calE.setTime(endDate);
			// 如果结束日期<=开始日期，返回开始日期
			if (calE.compareTo(calB) == 0 || calE.compareTo(calB) == -1) {
				dateList.add(beginDate);
			} else if (calE.compareTo(calB) == 1) {// 如果结束日期>开始日期
				long daterange = calE.getTimeInMillis()
						- calB.getTimeInMillis();
				int theday = (int) (daterange / MILLISECONDS_DAY);
				dateList = new ArrayList<Date>(theday);
				for (int i = 0; i < theday; i++) {
					calB.setTime(beginDate);
					calB.add(Calendar.DAY_OF_YEAR, i);
					dateList.add(calB.getTime());
				}
				dateList.add(endDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList;
	}

	public static List<Date> getHours(Date beginDate, Date endDate) {
		List<Date> dateList = null;
		try {
			Calendar calB = Calendar.getInstance();
			Calendar calE = Calendar.getInstance();
			calB.setTime(beginDate);
			calE.setTime(endDate);
			// 如果结束日期<=开始日期，返回开始日期
			if (calE.compareTo(calB) == 0 || calE.compareTo(calB) == -1) {
				// dateList.add(beginDate);
			} else if (calE.compareTo(calB) == 1) {// 如果结束日期>开始日期
				long daterange = calE.getTimeInMillis()
						- calB.getTimeInMillis();
				int theday = (int) (daterange * 24 / MILLISECONDS_DAY);
				dateList = new ArrayList<Date>(theday);
				for (int i = 0; i < theday; i++) {
					calB.setTime(beginDate);
					calB.add(Calendar.HOUR_OF_DAY, i);
					dateList.add(calB.getTime());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList;
	}

	/**
	 * 获得两个年份间的时间(跨年份时间)
	 * 
	 * @param beginYear
	 *            YYYY
	 * @param endYear
	 *            YYYY
	 * @return List<String> YYYY-1-1
	 */
	public static List<String> getYears(String beginYear, String endYear) {
		int by = Integer.parseInt(beginYear);
		int ey = Integer.parseInt(endYear);

		List<String> list = new ArrayList<String>();
		for (int i = by; i <= ey; i++)
			list.add(i + "-1-1");
		return list;
	}



	/**
	 * 类方法说明：取得两个时间之间的天数
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public static int getNumberOfDays(Date startTime, Date endTime) {
		int numberDays = 0;
		if (DateUtils.dateGreater(startTime, endTime)) {
			return 0;
		}
		Calendar calS = Calendar.getInstance();
		calS.setTime(startTime);
		Calendar calE = Calendar.getInstance();
		calE.setTime(endTime);
		// 两个时间相差的毫秒
		long daterange = calE.getTimeInMillis() - calS.getTimeInMillis();
		numberDays = (int) (daterange / MILLISECONDS_DAY) + 1;
		return numberDays;
	}



	/**
	 * 类方法说明：取得两个时间之间的相差的分钟数保留2位小数
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public static double getNumberOfMinute(Date startTime, Date endTime) {
		double numberHours = 0;
		if (DateUtils.dateGreater(startTime, endTime)) {
			return 0;
		}
		Calendar calS = Calendar.getInstance();
		calS.setTime(startTime);
		Calendar calE = Calendar.getInstance();
		calE.setTime(endTime);
		// 两个时间相差的毫秒
		long daterange = calE.getTimeInMillis() - calS.getTimeInMillis();
		numberHours = (double) (daterange * new Double(1) / (1000 * 60));
		return numberHours;
	}

	public static int getNumberOfSecond(Date startTime, Date endTime) {
		int numberHours = 0;
		if (DateUtils.dateGreater(startTime, endTime)) {
			return 0;
		}
		Calendar calS = Calendar.getInstance();
		calS.setTime(startTime);
		Calendar calE = Calendar.getInstance();
		calE.setTime(endTime);
		// 两个时间相差的毫秒
		long daterange = calE.getTimeInMillis() - calS.getTimeInMillis();
		numberHours = (int) (daterange * new Double(1) / (1000));
		return numberHours;
	}

	/**
	 * 设置年列表
	 * 
	 * @param attributeName
	 * @param request
	 */
	public static void setYearSelect(String attributeName, HttpServletRequest request) {
		String currYear = getYear();
		ArrayList<Map<String, String>> timeList = new ArrayList<Map<String, String>>();
		for (int i = -2; i < 5; i++) {
			String year = String.valueOf(Integer.parseInt(currYear) + i);
			Map<String, String> timeSetpForm = new LinkedHashMap<String, String>();
			timeSetpForm.put("value", year);
			timeSetpForm.put("text", year + "年");
			timeList.add(timeSetpForm);
		}
		request.setAttribute(attributeName, timeList);
	}

	/**
	 * 设置月列表
	 * 
	 * @param attributeName
	 * @param request
	 */
	public static void setMonthSelect(String attributeName, HttpServletRequest request) {
		ArrayList<Map<String, String>> timeList = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= 12; i++) {
			Map<String, String> timeSetpForm = new LinkedHashMap<String, String>();
			timeSetpForm.put("value", String.valueOf(i));
			timeSetpForm.put("text", i + "月");
			timeList.add(timeSetpForm);
		}
		request.setAttribute(attributeName, timeList);
	}

	/**
	 * 根据时间 改变字符串（时间）格式 如：yyyy-MM-dd HH:mm:SS 转为 yyyy-MM-dd
	 * 
	 * @param dateStr
	 *            字符串（时间）
	 * @param formPattern
	 *            原格式
	 * @param toPattern
	 *            目标格式
	 * @return 字符串（时间）
	 */
	public static String formatStrByDate(String dateStr, String formPattern,
			String toPattern) {
		return DateUtils.convertDateToStr(
				convertStrToDate(dateStr, formPattern), toPattern);
	}

	/**
	 * 功能说明：获取当前月第一天的时间
	 * 
	 * @return
	 */
	public static Date getFirstDayOfCurrentDate() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, 1);
		return ca.getTime();
	}

	/**
	 * 功能说明：获取当前月第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfCurrentDay() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, 1);

		return DateUtils.convertStrToDate(DateUtils.convertDateToStr(ca
				.getTime()));
	}

	/**
	 * 功能说明：获取当前月最后一天的时间
	 * 
	 * @return
	 */
	public static Date getLastDayOfCurrentDate() {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.MONTH, 1);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		return ca.getTime();
	}

	/**
	 * 功能说明：获取当前月最后一天的时间
	 * 
	 * @return
	 */
	public static Date getLastDayOfCurrentDay() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.MONTH, 1);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		return DateUtils.convertStrToDate(DateUtils.convertDateToStr(ca
				.getTime()));
	}

	/**
	 * @param date
	 *            获得给定时间的年份数值,月份数值等
	 * @param field
	 *            编号 MONTH or YEAR...
	 * @return
	 */
	public static int getFieldNumber(Date date, int field) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int i = ca.get(field);
		if (field == Calendar.MONTH)
			i++;
		return i;
	}



	/**
	 * 取得开始时间和结束时间之间的时间 左开 右闭
	 * 
	 * @param beginDate
	 *            开始时间 YYYY-MM-DD
	 * @param endDate
	 *            结束时间 YYYY-MM-DD
	 * @return
	 */
	public static String[] getDate2(String beginDate, String endDate) {
		String[] dateList = null;
		try {
			// 如果结束日期为空，返回开始日期
			if (endDate == null || endDate.equals("")) {
				return new String[] { defaultFormat.format(defaultFormat
						.parse(beginDate)) };
			}
			Date bDate = defaultFormat.parse(beginDate);
			Date eDate = defaultFormat.parse(endDate);
			Calendar calB = Calendar.getInstance();
			Calendar calE = Calendar.getInstance();
			calB.setTime(bDate);
			calE.setTime(eDate);
			calE.add(Calendar.DAY_OF_YEAR, -1);
			// 如果结束日期<=开始日期，返回开始日期
			if (calE.compareTo(calB) == 0 || calE.compareTo(calB) == -1) {
				return new String[] { defaultFormat.format(defaultFormat
						.parse(beginDate)) };
			} else if (calE.compareTo(calB) == 1) {// 如果结束日期>开始日期
				long daterange = calE.getTimeInMillis()
						- calB.getTimeInMillis();
				int theday = (int) (daterange / MILLISECONDS_DAY) + 1;
				dateList = new String[theday];
				for (int i = 0; i < theday; i++) {
					calB.setTime(bDate);
					calB.add(Calendar.DAY_OF_YEAR, i);
					dateList[i] = defaultFormat.format(calB.getTime());

				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateList;
	}
	
	public static String geCronexpressionstTime(Map<String, String> map) {
		String type = map.get("type");
		String time = "";
		if ("1".equals(type)) {
			time = map.get("timeS");
			if (StringUtils.isEmpty(time)) {
				time = "0:0";
			}
		} else if ("2".equals(type)) {
			time = map.get("timeT");
			if (StringUtils.isEmpty(time)) {
				time = "0:0:0";
			}
		} else if ("3".equals(type)) {
			time = map.get("timeT");
			String t = map.get("time");
			if (StringUtils.isEmpty(time)) {
				time = "0:0:0";
			}
			time = time+":"+t;

		} else if ("4".equals(type)) {
			time = map.get("timeT");
			String t = map.get("time");
			if (StringUtils.isEmpty(time)) {
				time = "0:0:0";
			}
			time = time+":"+t;
		}
		return time;
	}

//	/**
//	 * 取得当前数据库时间SQL表达式
//	 * 
//	 * @param date
//	 * @return yyyy-MM-dd HH:mm:ss格式
//	 */
//	public static String convertDataToDBString(Date date) {
//		String d = DateUtils
//				.convertDateToStr(date, DateUtils.DATE_TIME_PATTERN);
//		if ("oracle".equalsIgnoreCase(PreferencesConstants.DATATYPE)) {
//			return "to_date('" + d + "','" + DateUtils.DATE_TIME_PATTERN_ORACLE
//					+ "')";
//		} else {
//			return "'" + d + "'";
//		}
//	}

	/**
	 * 类方法说明：
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Date d1 = DateUtils.convertStrToDate("2009-04-01 10:00:00",
		// DateUtils.DATE_TIME_PATTERN);
		// Date d2 = DateUtils.convertStrToDate("2009-04-02 10:00:00",
		// DateUtils.DATE_TIME_PATTERN);
		//Date startTime = DateUtils.convertStrToDate("2010-1-12");
		//Date endTime = DateUtils.convertStrToDate("2010-1-1");
		// // List list = DateUtils.getHours(d1, d2);
		//String s = "other20091112155950.tmp";
		//System.out.println(DateUtils.convertDateToStr((DateUtils
		//		.getFirstDayOfCurrentDay())));
		// Iterator i = list.iterator();
		// while (i.hasNext()) {
		// System.out.println(DateUtils.convertDateToStr((Date) i.next(),
		// DateUtils.DATE_TIME_PATTERN));
		// }
	}
}

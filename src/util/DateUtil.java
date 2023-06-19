

package util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期处理类
 * @author admin
 *
 */
public class DateUtil {
	private static final String sdf1reg = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final String sdf2reg = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

	private static final String sdf3reg = "^\\d{2,4}\\/\\d{1,2}\\/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";
	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private static final String sdf4reg = "^\\d{2,4}\\/\\d{1,2}\\/\\d{1,2}$";
	private static final SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy/MM/dd");

	private static final String sdf5reg = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}$";
	private static final SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private static final String sdf6reg = "^\\d{2,4}\\d{1,2}\\d{1,2}$";
	private static final SimpleDateFormat sdf6 = new SimpleDateFormat("yyyyMMdd");

	private static final String el1 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

	/**
	 * 校验日期是否合法：格式：yyyy-mm-dd
	 * 
	 * @param date
	 * @return
	 */
	public static boolean checkDate(String date) {
		Pattern p1 = Pattern.compile(el1);
		Matcher m1 = p1.matcher(date);

		return m1.matches();
	}

	/**
	 * 校验日期是否合法：格式：yyyy-mm-dd
	 * 
	 * @param time
	 * @return
	 */
	public static boolean checkTime(String time) {
		Pattern p1 = Pattern.compile(sdf1reg);
		Matcher m1 = p1.matcher(time);

		return m1.matches();
	}

	/**
	 * <p/>
	 * 将日期字符串解析成日期对象，支持一下格式
	 * <li>yyyy-MM-dd HH:mm:ss
	 * <li>yyyy-MM-dd
	 * <li>yyyy/MM/dd HH:mm:ss
	 * <li>yyyy/MM/dd
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDate(String str) {
		boolean date = false;
		Pattern p1 = Pattern.compile(sdf1reg);
		Matcher m1 = p1.matcher(str);
		Pattern p2 = Pattern.compile(sdf2reg);
		Matcher m2 = p2.matcher(str);
		Pattern p3 = Pattern.compile(sdf3reg);
		Matcher m3 = p3.matcher(str);
		Pattern p4 = Pattern.compile(sdf4reg);
		Matcher m4 = p4.matcher(str);
		Pattern p5 = Pattern.compile(sdf5reg);
		Matcher m5 = p5.matcher(str);
		Pattern p6 = Pattern.compile(sdf6reg);
		Matcher m6 = p6.matcher(str);
		if (m1.matches()) {
			date = true;
		} else if (m2.matches()) {
			date = true;
		} else if (m3.matches()) {
			date = true;
		} else if (m4.matches()) {
			date = true;
		} else if (m5.matches()) {
			date = true;
		} else if (m6.matches()) {
			date = true;
		} else {
			date = false;
		}

		return date;
	}

	/**
	 * <p/>
	 * 将日期格式化成相应格式的字符串，如：
	 * <li>yyyy-MM-dd HH:mm:ss
	 * <li>yyyy-MM-dd
	 * <li>yyyy/MM/dd HH:mm:ss
	 * <li>yyyy/MM/dd
	 * </p>
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return " ";
		}
		final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 将Timestamp类型转换成字符串（yyyy-MM-dd）
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringTimestamp(Timestamp date) {
		if (date == null) {
			return " ";
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 将Timestamp类型转换字符串(yyyyMMddHHmmss)
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringTimestampE(Timestamp date) {
		if (date == null) {
			return " ";
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 将Timestamp类型转换成字符串（yyyyMMdd）
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringTimestamp1(Timestamp date) {
		if (date == null) {
			return " ";
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 将Timestamp类型转换字符串(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringTimestamp2(Timestamp date) {
		if (date == null) {
			return " ";
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 将Timestamp类型转换成字符串（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringTimestamp3(Timestamp date) {
		if (date == null) {
			return " ";
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 获取日本时间
	 * 
	 * @param date
	 * @return
	 */
	public String getStringTimestampDelSlash(Timestamp date) {

		if (date == null) {
			return " ";
		}

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);
		str = str.substring(0, 4) + str.substring(5, 7) + str.substring(8);

		return str;
	}

	/**
	 * 取得Calendar实例
	 * 
	 * @return
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 根据日期偏移天数取得日期。offset > 0 ,往后延迟offset天， offset < 0 向前推进 offset天
	 * 
	 * @param date
	 *            :基日期
	 * @param offset
	 *            :日期天数偏移量
	 * @return
	 */
	public static Date getDate(Date date, int offset) {
		if (date == null) {
			return date;
		}
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, offset);
		return calendar.getTime();
	}
	
	/**
	 * 忽略时分秒
	 * 根据日期偏移天数取得日期。offset > 0 ,往后延迟offset天， offset < 0 向前推进 offset天
	 * 
	 * @param date
	 *            :基日期
	 * @param offset
	 *            :日期天数偏移量
	 * @return
	 */
	public static Date getDate1(Date date, int offset) {
		if (date == null) {
			return date;
		}
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, offset);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * <p/>
	 * 将日期字符串解析成日期对象，支持一下格式
	 * <li>yyyy-MM-dd HH:mm:ss
	 * <li>yyyy-MM-dd
	 * <li>yyyy/MM/dd HH:mm:ss
	 * <li>yyyy/MM/dd
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static Date parse(String str) {
		Date date = null;
		Pattern p1 = Pattern.compile(sdf1reg);
		Matcher m1 = p1.matcher(str);
		Pattern p2 = Pattern.compile(sdf2reg);
		Matcher m2 = p2.matcher(str);
		Pattern p3 = Pattern.compile(sdf3reg);
		Matcher m3 = p3.matcher(str);
		Pattern p4 = Pattern.compile(sdf4reg);
		Matcher m4 = p4.matcher(str);
		Pattern p5 = Pattern.compile(sdf5reg);
		Matcher m5 = p5.matcher(str);
		Pattern p6 = Pattern.compile(sdf6reg);
		Matcher m6 = p6.matcher(str);
		try {
			if (m1.matches()) {
				date = sdf1.parse(str);
			} else if (m2.matches()) {
				date = sdf2.parse(str);
			} else if (m3.matches()) {
				date = sdf3.parse(str);
			} else if (m4.matches()) {
				date = sdf4.parse(str);
			} else if (m5.matches()) {
				date = sdf5.parse(str);
			} else if (m6.matches())
				date = sdf6.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("非法日期字符串，解析失败：" + str, e);
		}
		return date;
	}

	/**
	 * <p/>
	 * 将日期字符串解析成日期对象，支持一下格式
	 * <li>yyyy-MM-dd HH:mm:ss
	 * <li>yyyy-MM-dd
	 * <li>yyyy/MM/dd HH:mm:ss
	 * <li>yyyy/MM/dd
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp parse1(String str) {
		Timestamp time = null;
		Date date = null;
		Pattern p1 = Pattern.compile(sdf1reg);
		Matcher m1 = p1.matcher(str);
		Pattern p2 = Pattern.compile(sdf2reg);
		Matcher m2 = p2.matcher(str);
		Pattern p3 = Pattern.compile(sdf3reg);
		Matcher m3 = p3.matcher(str);
		Pattern p4 = Pattern.compile(sdf4reg);
		Matcher m4 = p4.matcher(str);
		Pattern p5 = Pattern.compile(sdf5reg);
		Matcher m5 = p5.matcher(str);
		Pattern p6 = Pattern.compile(sdf6reg);
		Matcher m6 = p6.matcher(str);
		try {
			if (m1.matches()) {
				date = sdf1.parse(str);
			} else if (m2.matches()) {
				date = sdf2.parse(str);
			} else if (m3.matches()) {
				date = sdf3.parse(str);
			} else if (m4.matches()) {
				date = sdf4.parse(str);
			} else if (m5.matches()) {
				date = sdf5.parse(str);
			} else if (m6.matches())
				date = sdf6.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("非法日期字符串，解析失败：" + str, e);
		}
		if (date != null) {
			time = new Timestamp(date.getTime());
		}

		return time;
	}

	/**
	 * 根据指定的格式 转换成类型
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Timestamp parseTimestamp(String str, String pattern) {
		Timestamp time = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			Date date = format.parse(str);
			time = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return time;
	}

	/**
	 * 根据指定的格式 转换成类型
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String str, String pattern) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 获取系统时间 yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String nowTime() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		dateStr = format.format(new Date(System.currentTimeMillis()));

		return dateStr;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static byte[] nowTimeByte() {
		byte[] b = new byte[7];
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = format.format(new Date(System.currentTimeMillis()));
		b[0] = Byte.parseByte(dateStr.substring(0, 2));
		b[1] = Byte.parseByte(dateStr.substring(2, 4));
		b[2] = Byte.parseByte(dateStr.substring(4, 6));
		b[3] = Byte.parseByte(dateStr.substring(6, 8));
		b[4] = Byte.parseByte(dateStr.substring(8, 10));
		b[5] = Byte.parseByte(dateStr.substring(10, 12));
		b[6] = Byte.parseByte(dateStr.substring(12, 14));

		return b;
	}

	/**
	 * 获取系统时间 yyMMddHHmmss
	 * 
	 * @return
	 */
	public static byte[] nowTimeByte(String date) {
		byte[] b = new byte[7];
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = format.format(parse1(date));
		b[0] = Byte.parseByte(dateStr.substring(0, 2));
		b[1] = Byte.parseByte(dateStr.substring(2, 4));
		b[2] = Byte.parseByte(dateStr.substring(4, 6));
		b[3] = Byte.parseByte(dateStr.substring(6, 8));
		b[4] = Byte.parseByte(dateStr.substring(8, 10));
		b[5] = Byte.parseByte(dateStr.substring(10, 12));
		b[6] = Byte.parseByte(dateStr.substring(12, 14));

		return b;
	}

	/**
	 * 获取系统时间 yyMMddHHmmss
	 * 
	 * @return
	 */
	public static byte[] nowTimeByte(long millis) {
		byte[] b = new byte[7];
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = format.format(new Date(millis));
		b[0] = Byte.parseByte(dateStr.substring(0, 2));
		b[1] = Byte.parseByte(dateStr.substring(2, 4));
		b[2] = Byte.parseByte(dateStr.substring(4, 6));
		b[3] = Byte.parseByte(dateStr.substring(6, 8));
		b[4] = Byte.parseByte(dateStr.substring(8, 10));
		b[5] = Byte.parseByte(dateStr.substring(10, 12));
		b[6] = Byte.parseByte(dateStr.substring(12, 14));

		return b;
	}
	
	/**
	 * 获取系统时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String nowTime1() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateStr = format.format(new Date(System.currentTimeMillis()));

		return dateStr;
	}

	/**
	 * 获取系统时间 yyyyMMdd
	 * 
	 * @return
	 */
	public static String nowDate() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		dateStr = format.format(new Date(System.currentTimeMillis()));

		return dateStr;
	}

	/**
	 * 获取系统时间 yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String nowDate1() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		dateStr = format.format(new Date(System.currentTimeMillis()));

		return dateStr;
	}
	
	/**
	 * 获取昨日
	 * 
	 * @return
	 */
	public static String yesTodate() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		dateStr = format.format(getDate(new Date(), -1));

		return dateStr;
	}

	/**
	 * 获取昨日
	 * 
	 * @return
	 */
	public static String yesTodate1() {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		dateStr = format.format(getDate(new Date(), -1));

		return dateStr;
	}

	/**
	 * 获取当时时间 HHMMSS
	 * 
	 * @return
	 */
	public static String nowTimeHms() {
		String hms = nowTime().substring(8);

		return hms;
	}

	/**
	 * 将yyyyMMddHHmmss转换成 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String parseTime(String time) {
		String dateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			dateStr = target.format(format.parse(time));
		} catch (ParseException e) {
			return time;
		}

		return dateStr;
	}

	/**
	 * 获取系统时间 yyyy年mm月 hh:MM:ss
	 * 
	 * @return
	 */
	public static String getTimeP() {
		String time = nowTime();
		String tmpTime = time.substring(0, 4) + "年" + time.substring(4, 6)
				+ "月" + time.substring(6, 8) + "日 " + time.substring(8, 10)
				+ ":" + time.substring(10, 12) + ":" + time.substring(12, 14);

		return tmpTime;
	}

	/**
	 * 将DATE类型转换成字符串（yyyy-MM-dd）
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringDate(Date date) {
		if (date == null) {
			return " ";
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 将DATE类型转换成字符串（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringDate1(Date date) {
		if (date == null) {
			return " ";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		DateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 将DATE类型转换成字符串（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringDate1(java.sql.Date date) {
		if (date == null) {
			return " ";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		DateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 将DATE类型转换成字符串（yyyyMMdd）
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringDateE(Date date) {
		if (date == null) {
			return " ";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		DateFormat form = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
		form.setTimeZone(TimeZone.getDefault());
		String str = form.format(date);

		return str;
	}

	/**
	 * 获取 hh:mm 格式的时间段
	 * 
	 * @param len
	 * @return
	 */
	public static String[] timeSection(int len) {
		String[] list = new String[len];
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int mi = calendar.get(Calendar.MINUTE);
		int tmp = (int) Math.ceil(mi / 10) * 10;
		calendar.set(Calendar.MINUTE, tmp);
		calendar.set(Calendar.SECOND, 0);
		for (int i = len - 1; i > -1; i--) {
			String hour = calendar.get(Calendar.HOUR_OF_DAY) < 10 ? String
					.valueOf("0" + calendar.get(Calendar.HOUR_OF_DAY)) : String
					.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
			String mini = calendar.get(Calendar.MINUTE) < 10 ? String
					.valueOf("0" + calendar.get(Calendar.MINUTE)) : String
					.valueOf(calendar.get(Calendar.MINUTE));
			list[i] = hour + ":" + mini;
			calendar.add(Calendar.MINUTE, -10);
		}

		return list;
	}

	/**
	 * 获取当年的第一天
	 * 
	 *
	 * @return
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 * 
	 *
	 * @return
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 获取当前日期 是年份中的第几天
	 * 
	 * @return
	 */
	public static int getDayOfYear() {
		Calendar calendar = getCalendar();
		calendar.setTime(new Date(System.currentTimeMillis()));
		int result = calendar.get(Calendar.DAY_OF_YEAR);

		return result;
	}

	
	/**
	 * 获取去年的今日
	 * 
	 *
	 * @return Date
	 */
	public static Date getPreYearDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		calendar.roll(Calendar.YEAR, -1);
		Date preYearNow = calendar.getTime();

		return preYearNow;
	}
	
}

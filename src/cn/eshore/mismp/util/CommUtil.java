package cn.eshore.mismp.util;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * <p>
 * Title: CommUtil
 * </p>
 * <p>
 * Description: 公共工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: Eshore
 * </p>
 * 
 * @author zhuoshangfei
 */
public class CommUtil {

	// 定义日期转换形式
	public static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	// 定义日期时间转换形式
	public static final DateFormat dateTimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	private static final DateFormat dateTimeFormat2 = new SimpleDateFormat(
	"yyyyMMddHHmmss");

	public static final String REG_DIGIT = "[0-9]*";

	/**
	 * 判断对象是否为空
	 * 
	 * @param o java.lang.Object
	 * @return boolean
	 */
	public static boolean isNullObject(Object o) {
		return o == null ? true : false;
	}

	/**
	 * 判断对象是否不为空
	 * 
	 * @param o java.lang.Object
	 * @return boolean
	 */
	public static boolean isNotNullObject(Object o) {
		return o != null ? true : false;
	}

	/**
	 * 判断字符窜是否为空
	 * 
	 * @param s java.lang.String
	 * @return boolean
	 */
	public static boolean isNullString(String s) {
		return s == null ? true : false;
	}

	/**
	 * 判断字符窜是否不为空
	 * 
	 * @param s java.lang.String
	 * @return boolean
	 */
	public static boolean isNotNullString(String s) {
		return s != null ? true : false;
	}

	/**
	 * 判断是否为空或空字符窜
	 * 
	 * @param s java.lang.String
	 * @return boolean
	 */
	public static boolean isNullOrEmptyString(String s) {
		return s == null || s.trim().equals("") ? true : false;
	}

	/**
	 * 把对象转换成字符窜
	 * 
	 * @param o java.lang.Object
	 * @return java.lang.String
	 */
	public static String convert2String(Object o) {
		return o == null ? null : o.toString();
	}

	/**
	 * 判断两个字符窜是否相同
	 * 
	 * @param s0 java.lang.String
	 * @param s1 java.lang.String
	 * @return boolean
	 */
	public static boolean sameString(String s0, String s1) {
		if (s0 == null && s1 == null)
			return true;
		if (s0 != null && s1 != null)
			return s0.equals(s1);
		return false;
	}

	/**
	 * 比较两个数值是否相等
	 * 
	 * @param d0 java.lang.double
	 * @param d1 java.lang.double
	 * @return
	 */
	public static boolean equalValue(double d0, double d1) {
		if (d0 != d1)
			return false;
		return true;
	}

	/**
	 * 获取当前日期时间
	 * 
	 * @return java.util.Data
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 将日期转换成字符窜
	 * 
	 * @param date java.util.Date
	 * @return java.lang.String
	 */
	public static String Date2String(Date date) {
		return date == null ? null : dateFormat.format(date);
	}

	/**
	 * 将日期时间转换成字符窜
	 * 
	 * @param date java.util.Date
	 * @return java.lang.String
	 */
	public static String DateTime2String(Date date) {
		return date == null ? null : dateTimeFormat.format(date);
	}

	/**
	 * 将字符串转换为日期
	 * 
	 * @param sDate java.lang.String
	 * @return java.util.Data
	 * @throws ParseException 
	 */
	public static Date String2Date(String sDate) throws ParseException {
		return dateFormat.parse(sDate);
	}

	/**
	 * 将字符串转换为日期时间
	 * 
	 * @param sDate java.lang.String
	 * @return java.util.Data
	 * @throws ParseException 
	 */
	public static Date String2DateTime(String sDateTime) throws ParseException {
		return dateTimeFormat.parse(sDateTime);
	}
	
	/**
	 * 将字符串数字转换为日期时间
	 * 
	 * @param snumDateTime java.lang.String
	 * @return java.util.Data
	 * @throws ParseException 
	 */
	public static Date StringNumber2DateTime(String snumDateTime)
			throws ParseException {
		return dateTimeFormat2.parse(snumDateTime.trim());
	}

	/**
	 * 将毫秒转换成"hh:mm:ss"格式
	 * 
	 * @param time java.lang.long
	 * @return java.lang.String
	 */
	public static String formatTime(long time) {
		StringBuffer sb = new StringBuffer();
		long hour, minute, second;
		hour = time / (60 * 60 * 1000);
		minute = (time - 60 * 60 * 1000 * hour) / (60 * 1000);
		second = (time - 60 * 60 * 1000 * hour - 60 * 1000 * minute) / 1000;
		if (hour < 10)
			sb.append("0");
		sb.append(hour).append(":");
		if (minute < 10)
			sb.append("0");
		sb.append(minute).append(":");
		if (second < 10)
			sb.append("0");
		sb.append(second);
		return sb.toString();
	}
	
	/**
	 * 得到13位数字组成的long类型时间戳
	 * 
	 * @return long
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 得到13位数字组成的时间戳字符窜
	 * 
	 * @return java.lang.String
	 */
	public static String getTimeStamp() {
		return String.valueOf(currentTimeMillis());
	}

	/**
	 * 得到18位数字组成的时间戳字符窜
	 * 
	 * @return java.lang.String
	 */
	public static String get18TimeStamp() {
		return getTimeStamp().concat(get5Random());
	}

	/**
	 * 得到5位随机数字组成的字符窜
	 *
	 * @return java.lang.String
	 */
	public static String get5Random() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 5; i++) {
			sb.append(ran.nextInt(10));
		}
		return sb.toString();
	}

	/**
	 * 将数组转换成字符串类型
	 * 
	 * @param o Object[] 
	 * @return java.lang.String
	 */
	public static String array2String(Object[] o) {
		if (o == null)
			return null;
		if (o.length < 1)
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < o.length; i++) {
			sb.append(o[i]).append(",");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}

	/**
	 * GBK转化为UTF-8
	 *
	 * @param s java.lang.String
	 * @return java.lang.String
	 */
	public static String getString4UTF8(String s) {
		try {
			return new String(s.getBytes("UTF-8"), "UTF-8");
		} catch (Exception e) {
			return s;
		}
	}
	
	/* 16进制输出byte[] */// 例如 byte[0] = 1 byte[1] = 2 ----> 0x3132
	public static String rhex(byte[] in) throws Exception {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(in));
		String str = "0x";
		for (int j = 0; j < in.length; j++) {
			String tmp = Integer.toHexString(data.readUnsignedByte());
			if (tmp.length() == 1) {
				tmp = "0" + tmp;
			}
			str = str + tmp;
		}
		return str;
	}
	
	/**
	 * 判断字符窜是否是数字
	 * 
	 * @param s java.lang.String
	 * @return boolean
	 */
	public static boolean isNumeric(String s) {
		return s.matches(REG_DIGIT);
	}
	
}

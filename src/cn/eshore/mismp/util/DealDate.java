/**
 * Created on Sep 7, 2006 8:22:49 AM
 */
package cn.eshore.mismp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Description:���ļ�Ϊ
 * 
 * @author Songming Yu
 * @version 1.0 Modified at: 
 * Modified by:
 */
public class DealDate {

	public static Date strToDate(String s) {

		try {
			DateFormat dateformat = DateFormat.getDateInstance();
			Date date = dateformat.parse(s);
			return date;
		} catch (Exception exception) {
			exception.printStackTrace();
			Calendar cal = Calendar.getInstance();
			cal.set(1900,0,1);
			return cal.getTime();
		}
	}

	public static Date strToDate(String s, String format) {

		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		try {
			Date date = simpledateformat.parse(s);
			return date;
		} catch (Exception exception) {
			exception.printStackTrace();
			Calendar cal = Calendar.getInstance();
			cal.set(1900,0,1);
			return cal.getTime();
		}
	}
	
	public static Date strToDate(String s, String format, Locale locale) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format,locale);
		try {
			Date date = simpledateformat.parse(s);
			return date;
		} catch (Exception exception) {
		}
		return null;
	}

	public static String dateToStr(Date d, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		String s1;
		try {
			s1 = sf.format(d);
		} catch (Exception e) {
			s1 = "1900-01-01";
		}
		return s1;
	}

	/** ȡ��ϵͳʱ�� */
	public static String getDateTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = f.format(new java.util.Date());
		return time;
	}
	
	/*
	 * }������֮��ıȽ�
	 */
	public static boolean compareDate(String strDate1,String strDate2,String format){
		Date date1 = strToDate(strDate1,format);
		Date date2 = strToDate(strDate2,format);
		if(date1.getTime()>=date2.getTime())
			return true;
		return false;
	}
	
	/*
	 * �Ƚ�һ�������Ƿ���ָ������֮��
	 */
	public static int compareDate(Date date,Date startDate,Date endDate){
		if(date==null||startDate==null||endDate==null)
			return -2;
		
		if(date.getTime()>=startDate.getTime()&&date.getTime()<=endDate.getTime())
			return 0;
		else if(date.getTime()>endDate.getTime())
			return 1;
		else
			return -1;
	}

	public static String formatDate(Date date, String format) {

		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		String s1 = null;
		try {
			s1 = simpledateformat.format(date);
		} catch (Exception e) {
			s1 = "1900-01-01";
		}
		return s1;

	}

	public static String formatDate(String dateString, String sourceFormat,
			String destFormat) {
		String s1;
		try {
			Date d1 = strToDate(dateString, sourceFormat);
			String s = formatDate(d1, destFormat);
			return s;
		} catch (Exception e) {
			s1 = "1900-01-01";
		}
		return s1;
	}
	
	public static void main(String arg[]){
		String s = "Thu, 06 Jan 2011 09:15:00";
		//s = "Thu Jan 06 12:55:52 CST 2011";
		DateFormat df = new SimpleDateFormat("E,dd MMM yyyy HH:mm:ss", Locale.US);
		Date date = null;
		try {
			date = df.parse("Thu, 06 Jan 2011 09:15:00");
			System.out.println(date.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(date.toString());
		System.out.println(DealDate.formatDate(date, "yyyy-MM-dd HH:mm:ss"));

	}

}

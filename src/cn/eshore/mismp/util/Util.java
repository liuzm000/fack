package cn.eshore.mismp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description: 常用方法
 * 
 * @author helin
 */
public class Util {
	
	public static int NORMAL_MILLISECOND = 1;

	public static int NORMAL_SECOND = 1000 * NORMAL_MILLISECOND;

	public static int NORMAL_MINUTE = 60 * NORMAL_SECOND;
	
	public static int NORMAL_HOUR=60*NORMAL_MINUTE;

	/**
	 * Description: 将null转换为空字符串
	 * 
	 * @author helin
	 * @version
	 */
	public static String nullToStr(String str) {
		if (str == null)
			str = "";
		return str.trim();
	}

	/**
	 * 将对象转换为字符串
	 * 
	 * @author helin
	 * @param
	 * @return
	 */
	public static String objectToStr(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 将对象转换为字符串
	 * 
	 * 
	 * @author yusm
	 * @param
	 * @return
	 */
	public static String object2Str(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return String.valueOf(obj);
		}
	}

	/**
	 * 将字符串转换为数字
	 * 
	 * @param str
	 * @return int
	 * @author yusm
	 */
	public static int String2Int(String str) {
		if (str == null) {
			return 0;
		} else {
			int m;
			try {
				m = Integer.parseInt(str);
			} catch (Exception e) {
				m = 0;
			}
			return m;
		}
	}

	public static long String2Long(String str) {
		if (str == null) {
			return 0;
		} else {
			long m;
			try {
				m = Long.parseLong(str);
			} catch (Exception e) {
				m = 0;
			}
			return m;
		}
	}

	/**
	 * 截取字符
	 * 
	 * @param str
	 * @param length
	 * @author yusm
	 */
	public static String cutString(String str, int length) {
		if (str == null)
			return "";
		if (length > 1 && length < str.length()) {
			str = str.substring(0, length);
		}
		return str.trim();
	}

	/**
	 * 转义sql语句中的‘’
	 * 
	 * @author yusm
	 */
	public static String escapeForSql(String str) {
		if (str == null)
			str = "";
		if (str.equals("null"))
			str = "";
		str = str.trim().replaceAll("'", "''");
		return str;
	}

	/**
	 * 根据日期字符串得到格式化的日期对象
	 * 
	 * @param dateString
	 *            要格式化的日期
	 * 
	 * @param format
	 *            日期格式，如yyyy-MM-dd
	 * @see java.text.SimpleDateFormat#parse(java.lang.String)
	 * @return Date 返回格式化后的日期，格式由参数<code>format</code>定义
	 * @author helin
	 */
	public static Date getFormatDate(String dateString, String format) {
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(format);
			return sdf.parse(dateString);
		} catch (Exception e) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(dateString);
			} catch (Exception ex) {
			}
			System.out.println("该字符串不能解析为日期");
		} 
		return null;
	}
	/**
	 * 判断一个字符串的长度是否大于给定的长度，如果是，返回true，否则返回false
	 * @param str 字符串
	 * @param length 字符串长度
	 * @author helin
	 */
	public static boolean StrTooLong(String str,int length){
		if(str==null||"".equals(str.trim())){
			return false;
		}
		if(str.length()>length){
			return true;
		}
		return false;
		
	}
	/*
	 * 得到当前具体日期
	 */
	public static String getNowDate(String dateString){
		SimpleDateFormat sdf= null;
		 try{
			 sdf= new SimpleDateFormat(dateString);
			 return sdf.format(new Date());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	}
	

	/**
	 * 返回给定日期的下一天
	 * 
	 * @param date 要取得下一天的日期
	 * @return 当前日期的下一天的字符串(yyyy-MM-dd)形式
	 * @throws
	 * @author helin
	 */
	public static String getNextDateString(Date date) {
		Calendar c = Calendar.getInstance();
		if(date==null){
			return "";
		}
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		Date newDate = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(newDate);
	}
	/**
	 * 返回给定日期的下一天
	 * 
	 * @param datestr 当前日期，(yyyy-MM-dd)形式
	 * @author helin
	 */
	public static String getNextDateString1(String datestr){
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		if (datestr==null || datestr.trim().equals("")) {
			cal = Calendar.getInstance();
		} else {
			try {
				Date d = df.parse(datestr);
				cal.setTime(d);
			} catch (ParseException pe) {
				cal = Calendar.getInstance();
			}
		}
		cal.add(Calendar.DAY_OF_MONTH, 1);
		result = df.format(cal.getTime());
		return result;
	}
	/**
	 * 返回给定日期所在周的第一天（星期日）
	 * 
	 * @param datestr 当前日期，(yyyy-MM-dd)形式
	 * @author helin
	 */
	public static String getFirstDayOfWeek(String datestr){
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		if (datestr==null || datestr.trim().equals("")) {
			cal = Calendar.getInstance();
		} else {
			try {
				Date d = df.parse(datestr);
				cal.setTime(d);
			} catch (ParseException pe) {
				cal = Calendar.getInstance();
			}
		}
		cal.set(Calendar.DAY_OF_WEEK, cal.getMinimum(Calendar.DAY_OF_WEEK));
		result = df.format(cal.getTime());
		return result;
	}
	/**
	 * 返回给定日期所在周的下一周第一天（星期日）
	 * 
	 * @param datestr 当前日期，(yyyy-MM-dd)形式
	 * @author helin
	 */
	public static String getFirstDayOfNextWeek(String datestr){
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		if (datestr==null || datestr.trim().equals("")) {
			cal = Calendar.getInstance();
		} else {
			try {
				Date d = df.parse(datestr);
				cal.setTime(d);
			} catch (ParseException pe) {
				cal = Calendar.getInstance();
			}
		}
		cal.add(Calendar.WEEK_OF_MONTH, 1);
		cal.set(Calendar.DAY_OF_WEEK, cal.getMinimum(Calendar.DAY_OF_WEEK));
		result = df.format(cal.getTime());
		return result;
	}
	/**
	 * 返回给定日期所在月的第一天
	 * 
	 * @param datestr 当前日期，(yyyy-MM-dd)形式
	 * @author helin
	 */
	public static String getFirstDayOfMonth(String datestr){
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		if (datestr==null || datestr.trim().equals("")) {
			cal = Calendar.getInstance();
		} else {
			try {
				Date d = df.parse(datestr);
				cal.setTime(d);
			} catch (ParseException pe) {
				cal = Calendar.getInstance();
			}
		}
		//cal.add(Calendar.WEEK_OF_MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
		result = df.format(cal.getTime());
		return result;
	}
	/**
	 * 返回给定日期所在月的第一天
	 * 
	 * @param datestr 当前日期，(yyyy-MM-dd)形式
	 * @author helin
	 */
	public static String getFirstDayOfNextMonth(String datestr){
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		if (datestr==null || datestr.trim().equals("")) {
			cal = Calendar.getInstance();
		} else {
			try {
				Date d = df.parse(datestr);
				cal.setTime(d);
			} catch (ParseException pe) {
				cal = Calendar.getInstance();
			}
		}
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
		result = df.format(cal.getTime());
		return result;
	}
	
	
	public static String getCurrentTimeStr() {
		String curTimeSr = "";

		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int milliSecond = cal.get(Calendar.MILLISECOND);
		curTimeSr = ((hour < 10) ? "0" + String.valueOf(hour) : String
				.valueOf(hour))
				+ ":";
		curTimeSr += ((minute < 10) ? "0" + String.valueOf(minute) : String
				.valueOf(minute))
				+ ":";
		curTimeSr += ((second < 10) ? "0" + String.valueOf(second) : String
				.valueOf(second));
	//	curTimeSr += "." + String.valueOf(milliSecond);

		return curTimeSr;
	}
	
	public static String getcur(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Calendar can = Calendar.getInstance();
		return sdf.format(can.getTime());
	}
	public static int getHourTime(){
		return Integer.parseInt(Util.getCurrentTimeStr().split(":")[0]);
	}
	public static int getMinTime(){
		return Integer.parseInt(Util.getCurrentTimeStr().split(":")[1]);
	}
	public static int getSecTime(){
		return Integer.parseInt(Util.getCurrentTimeStr().split(":")[2]);
	}
	public static String getAfterDate(int theDay){
		Calendar c= Calendar.getInstance();
		c.add(Calendar.DATE,theDay);
		return new SimpleDateFormat().format(c.getTime());
	}
	
	public static void main(String[] args) {
		System.out.println(getNextDateString1(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));	
	}
}

/**
 * Created on 2007-3-7 ����02:32:13
 */
package cn.eshore.mismp.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="StringUtil.java.html"><i>View Source</i></a></p>
 * @author <a href="Yusm@gsta.com">Yu Songming</a>
 * @version 1.0
 * Modified at:
 * Modified by:
 */
public class StringUtil {

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNull(String str) {
		return str == null || str.length() == 0 || str.equals("null");
	}

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	public static String[] trimNull(String[] str) {
		if (str == null)
			return null;
		for (int i = 0; i < str.length; i++) {
			if (StringUtil.isNull(str[i]))
				str[i] = "";
		}
		return str;
	}

	/** ��nullת��Ϊ"" */
	public static String trimNull(String str) {
		if (str == null)
			str = "";
		if (str.equals("null"))
			str = "";
		str = str.trim();
		return str;
	}

	/** ��ȡ�ַ� */
	public static String cutString(String str, int length) {
		if (str == null)
			return " ";
		if (length > 1 && length < str.length()) {
			// length--;
			// str = str.substring(0,length)+"...";
			str = str.substring(0, length);
		}
		return str;
	}

	public static String ISO2GBK(String str) {
		return changeEncode(str, "ISO-8859-1", "GBK");
	}

	/** ת������ */
	public static String changeEncode(String str, String sourceEncode,
			String destEncode) {
		try {
			str = new String(str.getBytes(sourceEncode), destEncode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/** ת��sql����еġ��� */
	public static String escapeForSql(String str) {
		if (str == null)
			str = " ";
		if (str.equals("null"))
			str = " ";
		str = str.trim().replaceAll("'", "''");
		return str;
	}

	/** ������ʽ��ת��"\"��"$" */
	public static String escapeRegex(String original) {
		StringBuffer buffer = new StringBuffer(original.length());
		for (int i = 0; i < original.length(); i++) {
			char c = original.charAt(i);
			if (c == '\\' || c == '$') {
				buffer.append("\\").append(c);
			} else {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}
	
	/** ת��"\" */
	public static String escapeBackslash(String original) { 
		StringBuffer buffer = new StringBuffer(original.length());
		for (int i = 0; i < original.length(); i++) {
			char c = original.charAt(i);
			if (c == '\\') {
				buffer.append(" ");
			} else {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}

	public static int String2Int(String str) {
		if (str == null || "-".equals(str.trim())) {
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
	
	
	
	
	
	
	
	
	/**
	 * 创建指定长度的任意字符串
	 * @param len
	 * @return
	 */
	public static String createRanStr(int len) {
		String str = "1DEFcdefG23qxw45OmnPQXWZ67KLMN890aopzABCijklHbghIJ";
		StringBuffer strbuf = new StringBuffer();
		java.util.Random random = new java.util.Random();
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(str.length());
			strbuf.append(str.charAt(index));
		}
		return strbuf.toString();
	}
	
	
	
	/**
	 * 得到13位数字组成的long类型时间戳
	 * 
	 * @return long
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();//当前系统时间的毫秒数
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
	 * 创建随机文件名
	 * @param fileName
	 * @return
	 */
	public static  String newFileName(String fileName){
		String regx=fileName.substring(fileName.lastIndexOf("."),fileName.length());//.jpg
		String ranStr=StringUtil.createRanStr(8);
		String newPicName=ranStr+"_"+StringUtil.getTimeStamp()+regx;
		return newPicName;
		
	}
	
	
	
	public static   String getHtmlString(String name, String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html><html lang='zh-CN'>");
		sb.append("<head>" + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>"
				+ "<meta http-equiv=\"Content-Language\" content=\"zh-CN\"/>" + "<title>" + name + "</title>"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/main.css\">" + "</head>" + "<body>");
		sb.append(content);
		sb.append("</body>");
		sb.append("</html>");

		return sb.toString();
	}
	
}

/**
 * Created on 2007-3-9 ����12:29:45
 */
package cn.eshore.mismp.util;

import java.net.URLDecoder;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="KeywordDecode.java.html"><i>View Source</i></a></p>
 * @author <a href="Yusm@gsta.com">Yu Songming</a>
 * @version 1.0
 * Modified at:
 * Modified by:
 */
public class KeywordDecode {

	public static String decode(String str,String encode){
		if(str == null)
			return null;
		try {
			str = URLDecoder.decode(str,encode);
		} catch (Exception e) {
			//System.out.println(str);
			//e.printStackTrace();
		}
		if(str.length()>=150)
			str = str.substring(0,150);
		return str.trim();
	}
	
	public static String toGBK(String str){
		if(str.indexOf("\\x")>=0){
			str = StringUtil.escapeBackslash(str);
			str = str.replaceAll(" x", "%");
			return decode(str,"GBK");
		}		
		return decode(str,"GBK");
	}
}

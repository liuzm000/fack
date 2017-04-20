package cn.eshore.mismp.web.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <p>HttpServletRequest装饰类，负责过滤乱码集<p>
 * <p>@author 蒋欢<p>
 * <p>时间：上午01:10:28 2012-5-6<p>
 * <p> CopyRight 2012<p>
 */
public class WrappedRequest extends HttpServletRequestWrapper {

	public WrappedRequest(HttpServletRequest request) {
		super(request);

	}

	public String getParameter(String name) {
		String result = super.getParameter(name);

		return getString(result);
	}

	private String getString(String message) {
		String str = null;
		try {
			if (message != null)
				str = new String(message.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.err.println("不支持此种编码机的转码" + e.getMessage());
		}

		return str;
	}
	

}

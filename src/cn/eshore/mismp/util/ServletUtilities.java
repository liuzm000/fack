/**
 * Created at:2008-9-30 下午12:58:28
 */
package cn.eshore.mismp.util;

/**
 * <p>Title: ServletUtilities</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="ServletUtilities.java.html"><i>View Source</i></a></p>
 * @author <a href="Yusm@gsta.com">Yu Songming</a>
 * @version 1.0
 * Modified at:
 * Modified by:
 */

public class ServletUtilities {
	public static final String DOCTYPE = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
			+ "Transitional//EN\">";

	public static String headWithTitle(String title, long contentLength) {
		StringBuffer sb = new StringBuffer();
		sb.append(DOCTYPE).append("\r\n");
		sb.append("<HTML>\n" + "<HEAD>\n<TITLE>" + title + "</TITLE>\r\n");
		sb.append("<META http-equiv=Content-Type content=\"text/plain; charset=utf8\">\r\n");
		sb.append("<META http-equiv=Cache-Control content=\"no-cache\">\r\n");
		sb.append("<META http-equiv=Connection content=\"keep-alive\">\r\n");
		sb.append("<META http-equiv=Content-Length content=").append(contentLength).append(">\r\n");
		sb.append("</HEAD>\r\n");
		return sb.toString();
	}

}

/**
 * Created at:2008-10-23 下午05:16:52
 */
package cn.eshore.mismp.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;

//import org.apache.axis.AxisProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.eshore.mismp.service.BusinessSupportService;
import cn.eshore.mismp.util.MobileGlobals;


/**
 * <p>Title: ProxyHttpServlet.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="ProxyHttpServlet.java.html"><i>View Source</i></a></p>
 * @author Yusm
 * @version 1.0
 */
public class ProxyHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 11234123141L;
	static String proxySet = "false";
	static{
		proxySet = MobileGlobals.getProperty("http.proxy.set");	
	}	
	protected static CacheManager manager = new CacheManager(ProxyHttpServlet.class.getResource("/ehcache.xml"));	
	protected BusinessSupportService businessSupportService;
	
	public void init() throws ServletException {
		if("true".equals(proxySet)){
//			AxisProperties.setProperty("http.proxySet", "true");
//			AxisProperties.setProperty("http.proxyHost", MobileGlobals.getProperty("http.proxy.server"));
//			AxisProperties.setProperty("http.proxyPort", MobileGlobals.getProperty("http.proxy.port") );
		}
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		businessSupportService = (BusinessSupportService)ctx.getBean("businessSupportService");
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);		
}
	
}

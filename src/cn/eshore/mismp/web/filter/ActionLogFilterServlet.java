/**
 * 每次用户的请求操作,都通过该过滤器来处理,记录其管理员用户的行为操作
 * 根据请求的路径来处理
 */
package cn.eshore.mismp.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author guonm
 * @version 1.0
 *
 */
public class ActionLogFilterServlet extends HttpServlet implements Filter {
    private static final Log log = LogFactory.getLog(ActionLogFilterServlet.class);
    FilterConfig config; 
    public void setFilterConfig(FilterConfig config) { 
    this.config = config; 
    } 
    public FilterConfig getFilterConfig() { 
    return config; 
    }
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sRequest;    
        HttpServletResponse response = (HttpServletResponse) sResponse; 
        HttpSession session = request.getSession();
        
       /* AdministratorSession user=(AdministratorSession) session.getAttribute("administratorSession");
        if(null!=user)
        {
	        String url="";
	        ServletContext context = getFilterConfig().getServletContext();
	        url=request.getServletPath();
	        url=url.trim();
	        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	        SystemService systemService=(SystemService) ctx.getBean("systemService");
	        Module module=systemService.getModuleByUrl(url);

	        if(null!=module)
	        {
		        SystemLog slog=new SystemLog();
		        slog.setAction(module.getModule_name());
		        slog.setIp(request.getRemoteAddr());
		        slog.setUser(user);
		        slog.setModule(module);
		        systemService.addSystemLog(slog);
	        }
        }*/
        filterChain.doFilter(sRequest, sResponse); 
        
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		 this.config=arg0;

	}

}

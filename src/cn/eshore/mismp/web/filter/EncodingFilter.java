package cn.eshore.mismp.web.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <p> 字符集过滤器<p>
 * <p> @author 蒋欢 <p>
 * <p> 时间：上午01:09:26 2012-5-6 <p>
 * <p> CopyRight 2012 <p>
 */
public class EncodingFilter implements Filter
{


   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
         ServletException
   {
      HttpServletRequest req = (HttpServletRequest)request;
      String requestMethod = req.getMethod();//获得当前的请求方式
      if("POST".equalsIgnoreCase(requestMethod))
      {
         req.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
      }else
      {
         req =new WrappedRequest(req);
      }
      chain.doFilter(req, response);
      
      
   }
      

   public void destroy()
   {
      
   }


   public void init(FilterConfig arg0) throws ServletException
   {
      
   }

}

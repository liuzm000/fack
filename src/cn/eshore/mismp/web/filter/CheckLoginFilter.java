package cn.eshore.mismp.web.filter;

/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Example filter that sets the character encoding to be used in parsing the
 * incoming request, either unconditionally or only if the client did not
 * specify a character encoding. Configuration of this filter is based on the
 * following initialization parameters:
 * </p>
 * <ul>
 * <li><strong>encoding</strong> - The character encoding to be configured for
 * this request, either conditionally or unconditionally based on the
 * <code>ignore</code> initialization parameter. This parameter is required,
 * so there is no default.</li>
 * <li><strong>ignore</strong> - If set to "true", any character encoding
 * specified by the client is ignored, and the value returned by the
 * <code>selectEncoding()</code> method is set. If set to "false,
 * <code>selectEncoding()</code> is called <strong>only</strong> if the
 * client has not already specified an encoding. By default, this parameter is
 * set to "true".</li>
 * </ul>
 * 
 * <p>
 * Although this filter can be used unchanged, it is also easy to subclass it
 * and make the <code>selectEncoding()</code> method more intelligent about
 * what encoding to choose, based on characteristics of the incoming request
 * (such as the values of the <code>Accept-Language</code> and
 * <code>User-Agent</code> headers, or a value stashed in the current user's
 * session.
 * </p>
 * 
 * @version $Revision: 1.1 $ $Date: 2008/12/03 06:55:30 $
 */

public class CheckLoginFilter implements Filter {

	/**
	 * The filter configuration object we are associated with. If this value is
	 * null, this filter instance is not currently configured.
	 */
	protected FilterConfig filterConfig = null;

	/**
	 * Take this filter out of service.
	 */
	public void destroy() {
		this.filterConfig = null;

	}

	/**
	 * Select and set (if specified) the character encoding to be used to
	 * interpret request parameters for this request.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param result
	 *            The servlet response we are creating
	 * @param chain
	 *            The filter chain we are processing
	 * 
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Object obj = httpRequest.getSession().getAttribute("account");
		if(obj == null){
			httpRequest.getSession().setAttribute("message", "��ĻỰ��Ч�������µ�½��");
			httpRequest.getSession().getServletContext().getRequestDispatcher(
					"/logout.jsp").forward(request, response);
//			httpResponse.sendRedirect("/index.jsp");
		}else{
			// Pass control on to the next filter
			chain.doFilter(request, response);
		}

	}

	/**
	 * Place this filter into service.
	 * 
	 * @param filterConfig
	 *            The filter configuration object
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}

package cn.eshore.mismp.version.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;

public class InitActionServlet extends ActionServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Log Log = LogFactory.getLog(InitActionServlet.class);

	public void init() throws ServletException {
		Log.info("VERSION struts startup...");
		super.init();
		Log.info("VERSION struts startup success...");
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		super.process(request, response);
	}

}

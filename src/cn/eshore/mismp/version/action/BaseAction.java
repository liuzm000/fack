package cn.eshore.mismp.version.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

/**
 * 本项目Action基类,继承DispatchAction
 * 
 * @version 1.0 03 Nov 2006
 * @author calvin
 * @see org.apache.struts.actions.DispatchAction
 */
public abstract class BaseAction extends DispatchAction {
	
	protected final Log log = LogFactory.getLog(getClass());	
	/* 设置时间处理格式 */
	public static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	/**
     * 通过response将数据返回到页面
     *
     * @param response HttpServletResponse
     * @param text
     */
    public void renderText(HttpServletResponse response,String text) {
        try {
            response.reset();
            response.setContentType("text/html;charset=UTF-8");
           /* 
            ServletOutputStream out = response.getOutputStream();
            out.print(text);
            out.flush();
            out.close();*/
            PrintWriter out = response.getWriter();
            out.print(text);
            out.flush();
            out.close();
        } catch (IOException e) {
        	log.error("rendText():" + e.getMessage(), e);
        }
    }

    /**
     * Convenience method to initialize messages in a subclass.
     *
     * @param request the current request
     * @return the populated (or empty) messages
     */
    public ActionMessages getMessages(HttpServletRequest request) {
        ActionMessages messages = null;
        HttpSession session = request.getSession();

        if (request.getAttribute(Globals.MESSAGE_KEY) != null) {
            messages = (ActionMessages) request.getAttribute(Globals.MESSAGE_KEY);
            saveMessages(request, messages);
        } else if (session.getAttribute(Globals.MESSAGE_KEY) != null) {
            messages = (ActionMessages) session.getAttribute(Globals.MESSAGE_KEY);
            saveMessages(request, messages);
            session.removeAttribute(Globals.MESSAGE_KEY);
        } else {
            messages = new ActionMessages();
        }

        return messages;
    }	
}


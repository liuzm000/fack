package cn.eshore.mismp.version.common;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;


/**
 * 实现<strong>struts ExceptionHandler</strong>用来处理所有action异常
 * 类Action里的try/catch可以去除
 *
 * <p>
 * <a href="ActionExceptionHandler.java.html"><i>查看源代码</i></a>
 * </p>
 *
 */
public final class ActionExceptionHandler extends ExceptionHandler {
    //~ Instance fields ========================================================

    private transient final Log log = LogFactory.getLog(ActionExceptionHandler.class);

    //~ Methods ================================================================

    /**
     * 这个方法用来处理任何没有被捕获的java.lang.Exception.
     * 该方法会循环获取exception chain,创建ActionErrors,
     * 把信息添加到request,然后forward to the input.
     */
    public ActionForward execute(Exception ex, ExceptionConfig ae,
                                 ActionMapping mapping,
                                 ActionForm formInstance,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
    throws ServletException {
        // if there's already errors in the request, don't process
        ActionErrors errors =
            (ActionErrors) request.getAttribute(Globals.ERROR_KEY);

        if (errors != null) {
            return null;
        }

        ActionForward forward =
            super.execute(ex, ae, mapping, formInstance, request, response);

        ActionMessage error = null;
        String property = null;
//      权限控制异常
//        if (ex instanceof AccessDeniedException && forward == null) {
//            storeException(request, "", new ActionMessage("errors.detail", ex.getMessage()), forward);
//            try {
//                response.sendError(HttpServletResponse.SC_FORBIDDEN);
//                return null;
//            } catch (IOException io) {
//                io.printStackTrace();
//                log.error(io.getMessage());
//            }
//        }
        
        // Get the chained exceptions (causes) and add them to the
        // list of errors as well
        while (ex != null) {
            String msg = ex.getMessage();
            error = new ActionMessage("errors.detail", msg);
            property = error.getKey();
            ex = (Exception) ex.getCause();

            if ((ex != null) && (ex.getMessage() != null)) {
                // check to see if the child message is the same
                // if so, don't store it
                if (msg.indexOf(ex.getMessage()) == -1) {
                    storeException(request, property, error, forward);
                }
            } else {
                storeException(request, property, error, forward);
            }
        }

        return forward;
    }

    /**
     * 这个方法会重载ExceptionHandler里的storeException
     * 用来创建多于一个错误的message
     *
     * @param request - The request we are handling
     * @param property  - The property name to use for this error
     * @param error - The error generated from the exception mapping
     * @param forward - The forward generated from the input path
     *                  (from the form or exception mapping)
     */
    protected void storeException(HttpServletRequest request, String property,
                                  ActionMessage error, ActionForward forward) {
        ActionMessages errors =
            (ActionMessages) request.getAttribute(Globals.ERROR_KEY);

        if (errors == null) {
            errors = new ActionMessages();
        }

        errors.add(property, error);

        request.setAttribute(Globals.ERROR_KEY, errors);
    }

    /**
     * Overrides logException method in ExceptionHandler to print the stackTrace
     * @see org.apache.struts.action.ExceptionHandler#logException(java.lang.Exception)
     */
    protected void logException(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        log.error(sw.toString());
    }
}


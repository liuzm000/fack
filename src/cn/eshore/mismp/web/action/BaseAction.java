package cn.eshore.mismp.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.eshore.mismp.service.BusinessSupportService;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;


/**
 * Implementation of <strong>ActionSupport</strong> that contains convenience
 * methods for subclasses. For example, getting the current user and saving
 * messages/errors. This class is intended to be a base class for all Action
 * classes.
 * 
 */
public abstract class BaseAction extends ActionSupport {
	protected transient final Logger log = Logger.getLogger(getClass());
	protected int pageNum = 1; 
	protected int pageSize = 10; 
	protected HttpServletRequest request;
	protected BusinessSupportService businessSupportService;

	/**
	 * Convenience method to get the request
	 * @return current request
	 */
	public HttpServletRequest getRequest() {
		return request != null ? request : ServletActionContext.getRequest();
	}

	/**
	 * Convenience method to get the response
	 * 
	 * @return current response
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * Convenience method to get the session
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public ActionContext getContext() {
		return ActionContext.getContext();
	}

	public Map getSessionScope() {
		return getContext().getSession();
	}

	public Map getApplicationScope() {
		return getContext().getApplication();
	}

	public Map getContextScope() {
		return getContext().getContextMap();
	}

	public Map getParameters() {
		return getContext().getParameters();
	}

	public Object getParameter(String key) {
		return getParameters().get(key);
	}

	/**
	 * @param pageNum
	 *            The pageNum to set.
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @param pageSize
	 *            The pageSize to set.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获得请求的Action名，如：editContent.action则返回editContent
	 */
	protected String getActionName() {
		return ActionContext.getContext().getActionInvocation().getProxy()
				.getActionName();
	}

	/**
	 * 由相对路径获得绝对路径

	 */
	public String getRealPath(String abstractPath) {
		return getRequest().getSession().getServletContext().getRealPath(
				abstractPath);
	}

	/**
	 * 返回错误页面
	 */
	public String error(String msg) {
		addActionError(getText(msg));
		return ERROR;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @return the businessSupportService
	 */
	public BusinessSupportService getBusinessSupportService() {
		return this.businessSupportService;
	}

	/**
	 * @param businessSupportService the businessSupportService to set
	 */
	public void setBusinessSupportService(
			BusinessSupportService businessSupportService) {
		this.businessSupportService = businessSupportService;
	}

}

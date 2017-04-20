package cn.eshore.mismp.version.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.eshore.mismp.util.PageBean;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.version.common.FcBizBeanFactory;
import cn.eshore.mismp.version.domain.form.SoftTypeForm;
import cn.eshore.mismp.version.service.TreeViewService;

/**
 * @author wanglei
 * 
 */
public class TreeViewAction extends BaseAction {

	private TreeViewService treeViewService = (TreeViewService) FcBizBeanFactory
			.getBean("treeViewService");

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String action = request.getParameter("action");
		String spId = request.getParameter("spId");
		String addUrl = request.getParameter("addUrl");
		String proId = request.getParameter("proId");
		log.debug("action : >>>>> " + action);
		log.debug("spId : >>>>> " + spId);
		log.debug("addUrl : >>>>> " + addUrl);
		log.debug("proId : >>>>> " + proId);
		if (StringUtils.isNotEmpty(action)
				&& StringUtils.equals(action, "ajaxShowSpXML")) {
			this.showSpTree(response, addUrl);
			return null;
		} else if (StringUtils.isNotEmpty(action)
				&& StringUtils.equals(action, "ajaxShowProsBySpId")) {
			this.showProductTree(response, spId, addUrl);
		} else if (StringUtils.isNotEmpty(action)
				&& StringUtils.equals(action, "showSoftTypeList")) {
			ActionForward returnPage = mapping.findForward("softTypeList");
			if (StringUtils.isNotEmpty(proId)) {
				this.listSoftTypeByProId(mapping, form, request, response,
						proId);
				return returnPage;
			}
		} else if (StringUtils.isNotEmpty(action)
				&& StringUtils.equals(action, "showVersionList")) {
			ActionForward returnPage = mapping.findForward("listAllVersions");
			if (StringUtils.isNotEmpty(proId)) {
				this.listAllVersionsByProId(mapping, form, request, response,
						proId);
				return returnPage;
			}
		} else if (StringUtils.isNotEmpty(action)
				&& StringUtils.equals(action, "newSoftType")) {
			ActionForward returnPage = mapping.findForward("newSoftType");
			if (StringUtils.isNotEmpty(proId)) {
				this.addNewVersionsByProId(mapping, form, request, response,
						proId);
				return returnPage;
			}
		}
		return null;
	}

	private void listSoftTypeByProId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String proId) {
		log.debug("do listSoftTypeByProId() start...");
		request.setAttribute("param1", proId);
		request.setAttribute("proId", proId);
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		int pageNumInt = 1;
		int pageSizeInt = 10;
		if(StringUtils.isEmpty(pageNum)){
			pageNum = "1";
		}
		if(StringUtils.isEmpty(pageSize)){
			pageSize = "10";
		}
		Pagination list = this.treeViewService
				.getAllSoftTypeByProId(proId,pageNum,pageSize);
		pageNumInt = new Integer(pageNum).intValue();
		pageSizeInt = new Integer(pageSize).intValue();
		if (list != null && list.size() > 0) {
			//组装分页数据
			PageBean pgBean = new PageBean(pageNumInt,pageSizeInt);
			pgBean.setRecordCount(list.getRecordCount());
			request.setAttribute("softTypeList", list);
			request.setAttribute("pgBean", pgBean);
		} else {
			PageBean pgBean = new PageBean(pageNumInt,pageSizeInt);
			pgBean.setRecordCount(0);
			request.setAttribute("pgBean", pgBean);
			log.debug("softtype List is null...");
		}
		log.debug("do listSoftTypeByProId() end...");
	}

	private void addNewVersionsByProId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String proId) {
		// 获取所有手机型号信息
	}

	private void listAllVersionsByProId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String proId) {
		log.debug("do listAllVersionsByProId() start...");
		request.setAttribute("proId", proId);
		List<SoftTypeForm> list = this.treeViewService
				.getAllSoftVersionByProId(proId);
		if (list != null && list.size() > 0) {
			request.setAttribute("verList", list);
		} else {
			log.debug("version List is null...");
		}
		log.debug("do listAllVersionsByProId() end...");
	}

	private void showProductTree(HttpServletResponse response, String spId,
			String addUrl) {
		log.debug("do showProductTree() start...");
		String returnXML = this.treeViewService.getProductsBySpId(addUrl, spId);
		this.renderText(response, returnXML);
		log.debug("do showProductTree() end...");
	}

	private void showSpTree(HttpServletResponse response, String addUrl) {
		log.debug("do showSpTree() start...");
		String returnXML = this.treeViewService.getSpManageXML(addUrl);
		this.renderText(response, returnXML);
		log.debug("do showSpTree() end...");
	}
}

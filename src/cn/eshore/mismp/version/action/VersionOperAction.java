/**
 * 
 */
package cn.eshore.mismp.version.action;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import cn.eshore.mismp.dao.domain.SoftAuditVO;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.PageBean;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.version.common.FcBizBeanFactory;
import cn.eshore.mismp.version.common.FileUpload;
import cn.eshore.mismp.version.domain.form.SoftTypeForm;
import cn.eshore.mismp.version.domain.vo.FactoryVO;
import cn.eshore.mismp.version.domain.vo.SoftTermVO;
import cn.eshore.mismp.version.domain.vo.SoftTypeVO;
import cn.eshore.mismp.version.domain.vo.SoftVersionVO;
import cn.eshore.mismp.version.domain.vo.TerUserGroupMapVO;
import cn.eshore.mismp.version.domain.vo.TerUserGroupVersionMapVO;
import cn.eshore.mismp.version.service.VersionOperService;

/**
 * @author wanglei
 * 
 */
public class VersionOperAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private VersionOperService versionOperService = (VersionOperService) FcBizBeanFactory.getBean("versionOperService");

	private static String uploadImagePath = null;

	static {
		uploadImagePath = MobileGlobals.getProperty("file.upload.path");
	}

	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":doCreate() - start");
		String proId = request.getParameter("proId");
		String redirectUrl = "";
		// 图标文件上传路径
		String filePath = "";
		if (StringUtils.isNotEmpty(proId)) {
			redirectUrl = "/spManage.do?action=showSoftTypeList&proId=" + proId;
			request.setAttribute("redirectUrl", redirectUrl);
		}
		SoftTypeForm stForm = (SoftTypeForm) form;
		SoftTypeVO vo = new SoftTypeVO();
		if (stForm != null) {
			vo.setProId(proId);
			vo.setStName(stForm.getStName());
			vo.setStEngName(stForm.getStEngName());
			vo.setStDesc(stForm.getStDesc());
			vo.setStMsgPush(stForm.getStMsgPush());
			vo.setStCreatetime(new Date());
			vo.setStMsgType(stForm.getStMsgType());

			int id = this.versionOperService.addSoftType(vo);

			// 上传图标文件20091110上除图标加入软件类型信息
			FormFile file = stForm.getTheStFile();
			filePath = uploadImagePath + "/softtype/icons/" + proId + "/" + id;
			String returnStr = "";
			String fileName = "";
			boolean bFlag = false;
			if (file != null) {
				fileName = file.getFileName();
				vo.setStIcon(fileName);
				returnStr = FileUpload.uploadFile(file, filePath);
				if (StringUtils.isEmpty(returnStr)) {
					// 设置ID【修复初次上传无发显示图片问题 20091130】
					vo.setStId(new Integer(id).toString());
					vo.setStIconPath("/softtype/icons/" + proId + "/" + id + "/" + fileName);
					vo.setStIcon(fileName);
					bFlag = true;
				}
			}
			// 上传列表图标文件
			FormFile listIconFile = stForm.getTheListStFile();
			filePath = uploadImagePath + "/softtype/listIcons/" + proId + "/" + id;
			returnStr = "";
			fileName = "";
			if (listIconFile != null) {
				fileName = listIconFile.getFileName();
				vo.setStListIcon(fileName);
				returnStr = FileUpload.uploadFile(listIconFile, filePath);
				if (StringUtils.isEmpty(returnStr)) {
					vo.setStId(new Integer(id).toString());
					vo.setStListIconPath("/softtype/listIcons/" + proId + "/" + id + "/" + fileName);
					vo.setStListIcon(fileName);
					bFlag = true;
				}
			}
			if (bFlag) {
				int rows = this.versionOperService.updateSoftType(vo);
				log.debug("[" + rows + "] records has updated!");
			}
		}
		log.info(":doCreate() - end");
		return mapping.findForward("list");
	}

	public ActionForward doNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":doNew() - start");
		String proId = request.getParameter("proId");
		if (StringUtils.isNotEmpty(proId)) {
			request.setAttribute("proId", proId);
		}
		log.info(":doNew() - end");
		return mapping.findForward("doNew");
	}

	public ActionForward doAddVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":doAddVersion() - start");
		String stId = request.getParameter("stId");
		String stName = request.getParameter("stName");
		if (StringUtils.isNotEmpty(stId)) {
			request.setAttribute("stName", stName);
			request.setAttribute("stId", stId);
		}
		log.info(":doAddVersion() - end");
		return mapping.findForward("doAddVersion");
	}

	public ActionForward showVersionList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":showVersionList() - start");
		String stId = request.getParameter("stId");
		String stName = request.getParameter("stName");
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		int pageNumInt = 1;
		int pageSizeInt = 10;
		if (StringUtils.isEmpty(pageNum)) {
			pageNum = "1";
		}
		if (StringUtils.isEmpty(pageSize)) {
			pageSize = "10";
		}
		if (StringUtils.isNotEmpty(stId)) {
			request.setAttribute("param2", stName);
			request.setAttribute("param1", stId);
			request.setAttribute("stName", stName);
			request.setAttribute("stId", stId);
			Pagination svList = this.versionOperService.getVersionsByStId(stId, pageNum, pageSize);
			pageNumInt = new Integer(pageNum).intValue();
			pageSizeInt = new Integer(pageSize).intValue();
			if (svList != null && svList.size() > 0) {
				// 组装分页数据
				PageBean pgBean = new PageBean(pageNumInt, pageSizeInt);
				pgBean.setRecordCount(svList.getRecordCount());
				request.setAttribute("verList", svList);
				request.setAttribute("pgBean", pgBean);
			} else {
				PageBean pgBean = new PageBean(pageNumInt, pageSizeInt);
				pgBean.setRecordCount(0);
				request.setAttribute("pgBean", pgBean);
				log.debug("softtype List is null...");
			}
		}
		log.info(":showVersionList() - end");
		return mapping.findForward("showVersionList");
	}

	public ActionForward addNewVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":addNewVersion() - start");
		String stId = request.getParameter("stId");
		String stName = request.getParameter("stName");
		String redirectUrl = "";
		// 图标文件上传路径
		String filePath = "";
		String previewPath = "";
		if (StringUtils.isNotEmpty(stId)) {
			redirectUrl = "/version/operator.do?action=showVersionList&stId=" + stId + "&stName=" + URLEncoder.encode(stName, "UTF-8");
			request.setAttribute("redirectUrl", redirectUrl);
		}
		SoftTypeForm stForm = (SoftTypeForm) form;
		SoftVersionVO vo = new SoftVersionVO();
		if (stForm != null) {
			String svPreviewPath = "";// 存储预览图片相对路径
			vo.setStId(stId);
			vo.setSvName(stForm.getSvName());
			vo.setSvVersion(stForm.getSvVersion());
			vo.setSvDesc(stForm.getSvDesc());
			vo.setSvForceupdate(stForm.getSvForceupdate());
			vo.setSvCreatetime(new Date());
			// 默认状态为1.未审核
			vo.setSvStatus("1");
			// 上传图标文件
			FormFile file = stForm.getTheSvFile();
			filePath = "/softversion/" + stId + "/" + stForm.getSvVersion();
			String returnStr = "";
			String fileName = "";
			if (file != null) {
				fileName = file.getFileName();
				// 没有设置文件名的属性
				returnStr = FileUpload.uploadFile(file, uploadImagePath + filePath);
				if (StringUtils.isEmpty(returnStr)) {
					vo.setSvFilePath(filePath + "/" + fileName);
				}
			}
			// 上传图片预览1
			FormFile previewFile1 = stForm.getThePreviewFile1();
			previewPath = "/softPreview/" + stId + "/" + stForm.getSvVersion();
			returnStr = "";
			fileName = "";
			if (file != null) {
				fileName = previewFile1.getFileName();
				// 没有设置文件名的属性
				returnStr = FileUpload.uploadFile(previewFile1, uploadImagePath + previewPath);
				if (StringUtils.isEmpty(returnStr)) {
					svPreviewPath = previewPath + "/" + fileName;
				}
			}
			// 上传图片预览2
			FormFile previewFile2 = stForm.getThePreviewFile2();
			previewPath = "/softPreview/" + stId + "/" + stForm.getSvVersion();
			returnStr = "";
			fileName = "";
			if (previewFile2 != null) {
				fileName = previewFile2.getFileName();
				// 没有设置文件名的属性
				returnStr = FileUpload.uploadFile(previewFile2, uploadImagePath + previewPath);
				if (StringUtils.isEmpty(returnStr)) {
					svPreviewPath = svPreviewPath + "," + previewPath + "/" + fileName;
				}
			}
			log.debug("预览图:" + svPreviewPath);
			// 添加上传者
			vo.setSvSubmitid("");
			vo.setSvPreviewPath(svPreviewPath);
			vo.setPublishType(stForm.getPublishType());
			vo.setDownloadUrl(stForm.getDownloadUrl());
			int id = this.versionOperService.addSoftVersion(vo);
			// 添加审核表信息
			SoftAuditVO auditVo = new SoftAuditVO();
			auditVo.setSvId(new Integer(id).toString());
			auditVo.setCreatetime(new Date());
			auditVo.setDesc(stForm.getSvDesc());
			auditVo.setFilePath(filePath + "/" + fileName);
			auditVo.setForceupdate(stForm.getSvForceupdate());
			auditVo.setName(stForm.getSvName());
			auditVo.setStatus("1");
			auditVo.setVersion(stForm.getSvVersion());
			this.versionOperService.addSoftAudit(auditVo);
			request.setAttribute("stName", stName);
			request.setAttribute("stId", stId);
		}
		log.info(":addNewVersion() - end");
		return mapping.findForward("showVersionList");
	}

	public ActionForward doMatchPhoneType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":doMatchPhoneType() - start");
		String stId = request.getParameter("stId");
		String proId = request.getParameter("proId");
		// 查找软件类型信息
		SoftTypeVO stVo = this.versionOperService.getSoftTypeInfoByStId(stId);
		if (StringUtils.isNotEmpty(stVo.getStId())) {
			request.setAttribute("stVo", stVo);
			request.setAttribute("proId", proId);
		}
		// 查找手机型号
		List<FactoryVO> faclist = this.versionOperService.getAllTerminals();
		if (faclist != null && faclist.size() > 0) {
			request.setAttribute("faclist", faclist);
		}
		// 查找stId现有的适配，即所有对应的手机vterId
		List<SoftTermVO> matchedlist = this.versionOperService.getAllMatchedTerminals(stId);
		if (matchedlist != null && matchedlist.size() > 0) {
			String matchedStr = "";
			for (int i = 0; i < matchedlist.size(); i++) {
				if (!StringUtils.equals(matchedStr, "")) {
					matchedStr += "," + matchedlist.get(i).getTerId();
				} else {
					matchedStr += matchedlist.get(i).getTerId();
				}
			}
			log.debug("matched terIds : " + matchedStr);
			request.setAttribute("matchedTerIds", matchedStr);
		}
		log.info(":doMatchPhoneType() - end");
		return mapping.findForward("doMatch");
	}

	public ActionForward updateMatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":updateMatch() - start");
		String selectedIds = request.getParameter("selectedIds");
		String canceledIds = request.getParameter("canceledIds");
		String stId = request.getParameter("stId");
		String proId = request.getParameter("proId");
		int count = 0;
		try {
			// 先处理全量删除
			int d = this.versionOperService.deleteSoftTermMatch(stId, "");
			log.debug(">>>>> " + d + " matched data has deleted...");
			// 处理添加对应关系
			if (StringUtils.isNotEmpty(selectedIds)) {
				String[] idArry = selectedIds.split(",");
				int f = 0;
				for (int i = 0; i < idArry.length; i++) {
					f += this.versionOperService.addSoftTermMap(stId, idArry[i], proId);
				}
				count += f;
				log.debug(">>>>> " + count + " matched data has added...");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			this.renderText(response, "FAIL");
			return null;
		}
		if (count > 0) {
			this.renderText(response, "SUCCESS");
		} else {
			this.renderText(response, "NOCHANGE");
		}
		log.info(":updateMatch() - end");
		return null;
	}

	public ActionForward doEditSoftType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":doEditSoftType() - start");
		String stId = request.getParameter("stId");
		String proId = request.getParameter("proId");
		SoftTypeVO vo = null;
		if (StringUtils.isNotEmpty(stId)) {
			vo = this.versionOperService.getSoftTypeInfoByStId(stId);
			request.setAttribute("stVo", vo);
			request.setAttribute("proId", proId);
		}
		log.info(":doEditSoftType() - end");
		return mapping.findForward("doEdit");
	}

	public ActionForward updateSoftType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":updateSoftType() - start");
		String proId = request.getParameter("proId");
		String redirectUrl = "";
		// 图标文件上传路径
		String filePath = "";
		if (StringUtils.isNotEmpty(proId)) {
			redirectUrl = "/spManage.do?action=showSoftTypeList&proId=" + proId;
			request.setAttribute("redirectUrl", redirectUrl);
		}
		SoftTypeForm stForm = (SoftTypeForm) form;
		// 转换对象
		SoftTypeVO vo = new SoftTypeVO();
		vo.setStId(stForm.getStId());
		vo.setStName(stForm.getStName());
		vo.setStEngName(stForm.getStEngName());
		vo.setStDesc(stForm.getStDesc());
		vo.setStMsgType(stForm.getStMsgType());
		vo.setStMsgPush(stForm.getStMsgPush());
		FormFile iconFile = stForm.getTheStFile();
		FormFile listIconFile = stForm.getTheListStFile();
		try {
			if (iconFile != null && !iconFile.getFileName().equals("")) {
				// 重新上传图标文件
				filePath = uploadImagePath + "/softtype/icons/" + proId + "/" + stForm.getStId();
				String returnStr = "";
				String fileName = iconFile.getFileName();
				returnStr = FileUpload.uploadFile(iconFile, filePath);
				if (StringUtils.isEmpty(returnStr)) {
					vo.setStIcon(fileName);
					vo.setStIconPath("/softtype/icons/" + proId + "/" + stForm.getStId() + "/" + fileName);
				}
			}
			if (listIconFile != null && !listIconFile.getFileName().equals("")) {
				// 重新上传图标文件
				filePath = uploadImagePath + "/softtype/listIcons/" + proId + "/" + stForm.getStId();
				String returnStr = "";
				String fileName = listIconFile.getFileName();
				returnStr = FileUpload.uploadFile(listIconFile, filePath);
				if (StringUtils.isEmpty(returnStr)) {
					vo.setStListIcon(fileName);
					vo.setStListIconPath("/softtype/listIcons/" + proId + "/" + stForm.getStId() + "/" + fileName);
				}
			}
			int rows = this.versionOperService.updateSoftType(vo);
			log.debug("[" + rows + "] records has updated!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info(":updateSoftType() - end");
		return mapping.findForward("list");
	}

	public ActionForward doEditVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":doEditVersion() - start");
		String svId = request.getParameter("svId");
		String stId = request.getParameter("stId");
		String stName = request.getParameter("stName");
		SoftVersionVO vo = null;
		if (StringUtils.isNotEmpty(svId)) {
			vo = this.versionOperService.getVersionBySvId(svId);
			request.setAttribute("svVo", vo);
			request.setAttribute("stId", stId);
			request.setAttribute("stName", stName);
		}
		log.info(":doEditVersion() - end");
		return mapping.findForward("doEditVersion");
	}

	public ActionForward updateVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":updateVersion() - start");
		String stId = request.getParameter("stId");
		String stName = request.getParameter("stName");
		String redirectUrl = "";
		// 版本文件上传路径
		String filePath = "";
		if (StringUtils.isNotEmpty(stId)) {
			redirectUrl = "/version/operator.do?action=showVersionList&stId=" + stId + "&stName=" + URLEncoder.encode(stName, "UTF-8");
			request.setAttribute("redirectUrl", redirectUrl);
		}
		SoftTypeForm svForm = (SoftTypeForm) form;
		// 转换对象
		SoftVersionVO vo = new SoftVersionVO();
		vo.setSvId(svForm.getSvId());
		vo.setSvName(svForm.getSvName());
		vo.setSvVersion(svForm.getSvVersion());
		vo.setSvDesc(svForm.getSvDesc());
		vo.setSvForceupdate(svForm.getSvForceupdate());
		FormFile verFile = svForm.getTheSvFile();
		FormFile previewFile1 = svForm.getThePreviewFile1();
		FormFile previewFile2 = svForm.getThePreviewFile2();
		String svPreviewFilePath = "";
		try {
			if (verFile != null && !verFile.getFileName().equals("")) {
				// 重新上传版本文件
				filePath = "/softversion/" + stId + "/" + svForm.getSvVersion();
				String returnStr = "";
				String fileName = verFile.getFileName();
				// 没有设置文件名的属性
				returnStr = FileUpload.uploadFile(verFile, uploadImagePath + filePath);
				if (StringUtils.isEmpty(returnStr)) {
					vo.setSvFilePath(filePath + "/" + fileName);
				}
			}
			if (previewFile1 != null && !previewFile1.getFileName().equals("")) {
				// 重新上传图片预览1文件
				filePath = "/softPreview/" + stId + "/" + svForm.getSvVersion();
				String returnStr = "";
				String fileName = previewFile1.getFileName();
				returnStr = FileUpload.uploadFile(previewFile1, uploadImagePath + filePath);
				if (StringUtils.isEmpty(returnStr)) {
					svPreviewFilePath = filePath + "/" + fileName;
				}
			}
			if (previewFile2 != null && !previewFile2.getFileName().equals("")) {
				// 重新上传图片预览2文件
				filePath = "/softPreview/" + stId + "/" + svForm.getSvVersion();
				String returnStr = "";
				String fileName = previewFile2.getFileName();
				returnStr = FileUpload.uploadFile(previewFile2, uploadImagePath + filePath);
				if (StringUtils.isEmpty(returnStr)) {
					svPreviewFilePath = svPreviewFilePath + "," + filePath + "/" + fileName;
				}
			}
			if (StringUtils.isNotEmpty(svPreviewFilePath)) {
				vo.setSvPreviewPath(svPreviewFilePath);
			}
			vo.setPublishType(svForm.getPublishType());
			vo.setDownloadUrl(svForm.getDownloadUrl());
			int rows = this.versionOperService.updateVersion(vo);
			log.debug("[" + rows + "] records has updated!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info(":updateVersion() - end");
		return mapping.findForward("list");
	}

	public ActionForward deleteVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":deleteVersion() - start");
		String svId = request.getParameter("svId");
		String stId = request.getParameter("stId");
		String stName = request.getParameter("stName");
		String redirectUrl = "";
		if (StringUtils.isNotEmpty(stId)) {
			redirectUrl = "/version/operator.do?action=showVersionList&stId=" + stId + "&stName=" + URLEncoder.encode(stName, "UTF-8");
			request.setAttribute("redirectUrl", redirectUrl);
		}
		if (StringUtils.isNotEmpty(svId)) {
			try {
				int m = this.versionOperService.deleteVersion(svId);
				// 删除文件，可以忽略
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
		log.info(":deleteVersion() - end");
		return mapping.findForward("list");
	}

	public ActionForward doMatchTerUserGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":doMatchPhoneType() - start");
		String stId = request.getParameter("stId");
		String proId = request.getParameter("proId");
		// 查找软件类型信息
		SoftTypeVO stVo = this.versionOperService.getSoftTypeInfoByStId(stId);
		if (StringUtils.isNotEmpty(stVo.getStId())) {
			request.setAttribute("stVo", stVo);
			request.setAttribute("proId", proId);
		}
		// 查找所有群组
		List<TerUserGroupMapVO> terUserGroupList = this.versionOperService.getAllTerUserGroups();
		if (terUserGroupList != null && terUserGroupList.size() > 0) {
			request.setAttribute("terUserGroupList", terUserGroupList);
		}
		// 查找stId现有的适配，即所有对应的手机vterId
		List<TerUserGroupMapVO> matchedlist = this.versionOperService.getAllMatchedGroups(stId);
		if (matchedlist != null && matchedlist.size() > 0) {
			String matchedStr = "";
			for (int i = 0; i < matchedlist.size(); i++) {
				if (!StringUtils.equals(matchedStr, "")) {
					matchedStr += "," + matchedlist.get(i).getTgId();
				} else {
					matchedStr += matchedlist.get(i).getTgId();
				}
			}
			log.debug("matched group ids : " + matchedStr);
			request.setAttribute("matchedGroups", matchedStr);
		}
		log.info(":doMatchPhoneType() - end");
		return mapping.findForward("doMatchGroup");
	}

	/**
	 * added by wanglei 20110418
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward doMatchTerUserGroupForVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":doMatchTerUserGroupForVersion() - start");
		String svId = request.getParameter("svId");
		String stId = request.getParameter("stId");
		String stName = request.getParameter("stName");
		// 查找软件版本信息
		try {
			SoftVersionVO svVo = this.versionOperService.getVersionBySvId(svId);
			if (StringUtils.isNotEmpty(svVo.getStId())) {
				request.setAttribute("svVo", svVo);
				request.setAttribute("stId", stId);
				request.setAttribute("stName", stName);
			}
			// 查找所有群组
			List<TerUserGroupMapVO> terUserGroupList = this.versionOperService.getAllTerUserGroups();
			if (terUserGroupList != null && terUserGroupList.size() > 0) {
				request.setAttribute("terUserGroupList", terUserGroupList);
			}
			// 查找stId现有的适配，即所有对应的手机vterId
			List<TerUserGroupVersionMapVO> matchedlist = this.versionOperService.getAllMatchedVersionGroups(svId);
			if (matchedlist != null && matchedlist.size() > 0) {
				String matchedStr = "";
				for (int i = 0; i < matchedlist.size(); i++) {
					if (!StringUtils.equals(matchedStr, "")) {
						matchedStr += "," + matchedlist.get(i).getTgId();
					} else {
						matchedStr += matchedlist.get(i).getTgId();
					}
				}
				log.debug("matched version group ids : " + matchedStr);
				request.setAttribute("matchedVersionGroups", matchedStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info(":doMatchTerUserGroupForVersion() - end");
		return mapping.findForward("doMatchVersionGroup");
	}

	public ActionForward updateMatchGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":updateMatchGroup() - start");
		String selectedIds = request.getParameter("selectedIds");
		String canceledIds = request.getParameter("canceledIds");
		String stId = request.getParameter("stId");
		// 先处理删除
		try {
			if (StringUtils.isNotEmpty(canceledIds)) {
				int d = this.versionOperService.deleteSoftGroupMatch(stId, canceledIds);
				log.debug(">>>>> " + d + " matched data has deleted...");
			}
			// 处理添加对应关系
			if (StringUtils.isNotEmpty(selectedIds)) {
				String[] idArry = selectedIds.split(",");
				int d = 0;
				for (int i = 0; i < idArry.length; i++) {
					d += this.versionOperService.addSoftGroupMap(stId, idArry[i]);
				}
				log.debug(">>>>> " + d + " matched data has added...");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			this.renderText(response, "FAIL");
			return null;
		}
		this.renderText(response, "SUCCESS");
		log.info(":updateMatchGroup() - end");
		return null;
	}

	public ActionForward updateMatchVersionGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(":updateMatchVersionGroup() - start");
		String selectedIds = request.getParameter("selectedIds");
		String canceledIds = request.getParameter("canceledIds");
		String svId = request.getParameter("svId");
		// 先处理删除
		try {
			if (StringUtils.isNotEmpty(canceledIds)) {
				int d = this.versionOperService.deleteSoftVersionGroupMatch(svId, canceledIds);
				log.debug(">>>>> " + d + " matched data has deleted...");
			}
			// 处理添加对应关系
			if (StringUtils.isNotEmpty(selectedIds)) {
				String[] idArry = selectedIds.split(",");
				int d = 0;
				for (int i = 0; i < idArry.length; i++) {
					d += this.versionOperService.addSoftVersionGroupMap(svId, idArry[i]);
				}
				log.debug(">>>>> " + d + " matched data has added...");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			this.renderText(response, "FAIL");
			return null;
		}
		this.renderText(response, "SUCCESS");
		log.info(":updateMatchVersionGroup() - end");
		return null;
	}
}
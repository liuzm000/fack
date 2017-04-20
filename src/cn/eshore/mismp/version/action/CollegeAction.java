/**
 * 
 */
package cn.eshore.mismp.version.action;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.eshore.mismp.dao.domain.SoftAuditVO;
import cn.eshore.mismp.dao.domain.TerUserGroupVO;
import cn.eshore.mismp.util.DealDate;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.version.common.FcBizBeanFactory;
import cn.eshore.mismp.version.common.FileUpload;
import cn.eshore.mismp.version.domain.form.SoftTypeForm;
import cn.eshore.mismp.version.domain.vo.FactoryVO;
import cn.eshore.mismp.version.domain.vo.ProductVO;
import cn.eshore.mismp.version.domain.vo.SoftTermVO;
import cn.eshore.mismp.version.domain.vo.SoftTypeVO;
import cn.eshore.mismp.version.domain.vo.SoftVersionVO;
import cn.eshore.mismp.version.domain.vo.TerminalVO;
import cn.eshore.mismp.version.service.VersionOperService;

/**
 * @author wanglei
 * 
 */
public class CollegeAction extends BaseAction {

	private static HttpClient httpClient = null;

	private static final long serialVersionUID = 1L;

	private VersionOperService versionOperService = (VersionOperService) FcBizBeanFactory
			.getBean("versionOperService");

	private static String uploadImagePath = null;

	private static String spId = "";

	private static String parentProId = "";

	static {
		uploadImagePath = MobileGlobals.getProperty("upload.file.path");
		spId = MobileGlobals.getProperty("college.sp.id");
		parentProId = MobileGlobals.getProperty("college.parent.pro.id");
	}

	public ActionForward doUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String referer = request.getHeader("Referer");
		log.info("http请求头信息 doupload referer = " + referer);
		// 只信任www.surfingcenter.cn过来请求
		if (referer == null || !referer.contains("www.surfingcenter.cn")) {
			return null;
		}

		log.info("college:doUpload() - start");
		// 获取URL参数
		String sender = request.getParameter("sender") == null ? "" : request
				.getParameter("sender");
		String submitId = request.getParameter("submitId") == null ? ""
				: request.getParameter("submitId");
		String type = request.getParameter("type") == null ? "" : request
				.getParameter("type");
		String receiver = request.getParameter("receiver") == null ? ""
				: request.getParameter("receiver");
		String softId = request.getParameter("softid") == null ? "" : request
				.getParameter("softid");
		receiver = receiver.replace("|", ",");
		// 页面参数显示
		request.setAttribute("sender", sender);
		request.setAttribute("submitId", submitId);
		// 1—发布给开发者自己；2—好友分享；3—平台发布
		if (StringUtils.isEmpty(type)) {
			type = "1";// 默认为1
		}
		request.setAttribute("type", type);
		// 如果type=3，则显示最近的一个版本
		List<?> list = null;
		// 查询所有可用手机型号。
		list = this.versionOperService.getAllTerminalsForCollege();
		if (StringUtils.equals(type, "3")) {
			// 根据softId查询最近的一个版本
			SoftVersionVO vo = this.versionOperService
					.getLatestVersionByStId(softId);
			if (vo.getSvId() != null) {
				request.setAttribute("vo", vo);
			}
			list = this.versionOperService.getAllMatchedTerminalsByStId(softId);
			request.setAttribute("softId", softId);
			// START 添加appType的显示。added by wanglei 20091112
			String appType = this.versionOperService.getSoftTypeInfoByStId(
					softId).getAppType();
			if (StringUtils.isNotEmpty(appType)) {
				request.setAttribute("appType", appType);
				// System.out.println("appType" + appType);
			}
			// END
		} else {
			if (StringUtils.isNotEmpty(softId)) {
				// 根据softId查询最近的一个版本
				SoftVersionVO vo = this.versionOperService
						.getLatestVersionByStId(softId);
				if (vo.getSvId() != null) {
					request.setAttribute("vo", vo);
				}
				list = this.versionOperService
						.getAllMatchedTerminalsByStId(softId);
				request.setAttribute("softId", softId);
				// START 添加appType的显示。added by wanglei 20091112
				String appType = this.versionOperService.getSoftTypeInfoByStId(
						softId).getAppType();
				if (StringUtils.isNotEmpty(appType)) {
					request.setAttribute("appType", appType);
					System.out.println("appType" + appType);
				}
				// END
			}
			if (StringUtils.isNotEmpty(receiver)) {
				if (StringUtils.equals(type, "1")) {
					receiver = sender;
				} else {
					receiver = receiver + "," + sender;
				}
				request.setAttribute("receiver", receiver);
			} else {
				receiver = sender;
				request.setAttribute("receiver", receiver);
			}
		}
		if (list != null && list.size() > 0) {
			request.setAttribute("list", list);
		}
		List list2 = null;
		list2 = this.versionOperService.getAllTerminalsForCollege();
		// add by jingfei 20091013 start
		String platDes = "";
		if (list2 != null && list2.size() > 0) {
			for (int i = 0; i < list2.size(); i++) {
				Object temp = list2.get(i);
				// ID
				String terId = "";
				// 平台
				String terPlat = "";
				if (temp instanceof TerminalVO) {
					TerminalVO tempVO = (TerminalVO) temp;
					terId = tempVO.getTerId();
					terPlat = tempVO.getTerPlat();
				} else {
					SoftTermVO tempVO = (SoftTermVO) temp;
					terId = tempVO.getTerId();
					terPlat = tempVO.getTerPlat();
				}

				platDes = platDes + terId + "|" + terPlat + ",";
				// 手机列表长度为1
				if (list.size() == 1) {
					if (terPlat.equals("brew")) {
						request.setAttribute("fileSuffix", "bpk");
					} else {
						request.setAttribute("fileSuffix", "zip");
					}
				} else {
					request.setAttribute("fileSuffix", "");
				}
			}
			String proPlatDes = platDes.substring(0, platDes.length() - 1);
			request.setAttribute("proPlatDes", proPlatDes);
			// add by jingfei 20091013 end
		}
		log.info("college:doUpload() - end");
		return mapping.findForward("doUpload");
	}

	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info("college:upload() - start");
		// 查询所有可用手机型号。
		SoftTypeForm collegeform = (SoftTypeForm) form;
		String type = collegeform.getType();
		String softId = collegeform.getSoftId();
		int stId = 0;
		try {
			// add by jingfei 20091019
			// String terId = collegeform.getTerId();
			String mobileType = collegeform.getMobileType();
			// //查询终端说明
			// List<TerminalVO> terVoList =
			// this.versionOperService.getAllTerminalsForCollege();
			// for(TerminalVO vo : terVoList){
			// if(terId.equals(vo.getTerId())){
			// mobileType = vo.getTerName();
			// break;
			// }
			// }

			if (StringUtils.equals(type, "1") || StringUtils.equals(type, "2")) {
				if (StringUtils.isEmpty(softId)) {
					// 新添版本、软件类型、产品、添加好友列表、添加ST关联
					stId = this.save1(request, collegeform);
				} else {
					stId = this.save2(request, collegeform);
				}

				// 好友分享需要发帖
				if (StringUtils.equals(type, "2")) {
					// 好友分享需要调用发帖接口
					String app_id = stId + "", app_name = collegeform
							.getSvName(), submitId = collegeform
							.getSvSubmitid(), ip = request.getRemoteAddr()
							.replaceAll("\\.", "_"), versionNum = collegeform
							.getSvVersion();
					String paramsString = "appId="
							+ app_id
							+ "&appName="
							+ URLEncoder.encode(app_name, "UTF-8")
							+ "&submitId="
							+ submitId
							+ "&ip="
							+ ip
							+ "&versionNum="
							+ versionNum
							+ "&type="
							+ collegeform.getType()
							+ "&submitTime="
							+ DealDate.getDateTime()
							+ "&mobileType="
							+ URLEncoder.encode(mobileType, "UTF-8")
							+ "&desc="
							+ URLEncoder.encode(collegeform.getSvDesc(),
									"UTF-8");
					String url = "http://www.surfingcenter.cn/afterUpload.php?"
							+ paramsString;
					log.info("CollegeAction url=" + url);
					ActionForward newForward = new ActionForward();
					newForward.setPath(url);
					newForward.setRedirect(true);
					return newForward;
				}

			} else {
				// type = 3
				// 发布产品：新添版本。
				stId = this.save2(request, collegeform);
				// ////////////////////////////////////////////////////////////////////////////
				// 平台发布需要调用发帖接口
				String app_id = stId + "", app_name = collegeform.getSvName(), submitId = collegeform
						.getSvSubmitid(), ip = request.getRemoteAddr()
						.replaceAll("\\.", "_"), versionNum = collegeform
						.getSvVersion();
				String paramsString = "appId=" + app_id + "&appName="
						+ URLEncoder.encode(app_name, "UTF-8") + "&submitId="
						+ submitId + "&ip=" + ip + "&versionNum=" + versionNum
						+ "&type=" + collegeform.getType() + "&submitTime="
						+ DealDate.getDateTime() + "&mobileType="
						+ URLEncoder.encode(mobileType, "UTF-8") + "&desc="
						+ URLEncoder.encode(collegeform.getSvDesc(), "UTF-8");
				String url = "http://www.surfingcenter.cn/afterUpload.php?"
						+ paramsString;
				log.info("CollegeAction url=" + url);
				ActionForward newForward = new ActionForward();
				newForward.setPath(url);
				newForward.setRedirect(true);
				return newForward;

				/*
				 * GetMethod getMethod = new GetMethod(url); try{
				 * httpClient.executeMethod(getMethod); }catch(Exception ex){
				 * log.info("调用第3方软件发布结果通知接口失败，错误代号："+ex.toString());
				 * log.info("url="+url); }
				 */
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			request.setAttribute("flag", "FAIL");
		}
		log.info("college:upload() - end");
		return mapping.findForward("doUpload");
	}

	private int save1(HttpServletRequest request, SoftTypeForm collegeform) {
		int retstId = 0;
		// 添加产品
		ProductVO proVo = new ProductVO();
		proVo.setProParentId(new Long(parentProId));
		proVo.setSpId(new Long(spId));
		proVo.setIsmpId(collegeform.getIsmpId());
		proVo.setProName(collegeform.getSvName());
		int proId = this.versionOperService.addProduct(proVo);
		// 添加类型
		SoftTypeVO stVo = new SoftTypeVO();
		stVo.setProId(new Integer(proId).toString());
		stVo.setStName(collegeform.getSvName());
		stVo.setStEngName(collegeform.getStEngName());
		stVo.setStDesc(collegeform.getSvDesc());
		stVo.setStMsgPush("1");
		stVo.setStCreatetime(new Date());
		stVo.setStMsgType("0");
		stVo.setAppType(collegeform.getAppType());// 添加应用类型 added by wanglei
		// 20091112
		// 上传图标文件
		FormFile file1 = collegeform.getTheStFile();
		String filePath1 = "/college/user_icons/" + proId;
		String returnStr1 = "";
		String fileName1 = "";
		if (file1 != null) {
			fileName1 = file1.getFileName();
			// 没有设置文件名的属性
			try {
				returnStr1 = FileUpload.uploadFile(file1, uploadImagePath
						+ filePath1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (StringUtils.isEmpty(returnStr1)) {
				stVo.setStIcon(fileName1);
				stVo.setStIconPath(filePath1 + "/" + fileName1);
			}
		} else {
			stVo.setStIcon("college.jpg");
			stVo.setStIconPath("/college/icons/college.jpg");
		}
		int stId = this.versionOperService.addSoftType(stVo);
		retstId = stId;
		// 添加适配:edited by wanglei ：多机型适配
		String terminalsIds = collegeform.getTerminalsIds();
		if (StringUtils.isNotEmpty(terminalsIds)) {
			String[] arrayStr = terminalsIds.split(",");
			for (int i = 0; i < arrayStr.length; i++) {
				String terId = arrayStr[i];
				this.versionOperService.addSoftTermMap(new Integer(stId)
						.toString(), terId, String.valueOf(proId));
			}
		}
		// 添加软件
		String friendConfig = collegeform.getFriendsConfig();

		SoftVersionVO svVo = new SoftVersionVO();
		svVo.setStId(new Integer(stId).toString());
		svVo.setSvName(collegeform.getSvName());
		svVo.setSvVersion(collegeform.getSvVersion());
		svVo.setSvDesc(collegeform.getSvDesc());
		svVo.setSvForceupdate(collegeform.getSvForceupdate());
		svVo.setSvCreatetime(new Date());
		// 保存发布者电话号码和ID
		svVo.setSvSender(collegeform.getSvSender());
		svVo.setSvSubmitid(collegeform.getSvSubmitid());
		// 默认状态为2.审核通过
		svVo.setSvStatus("2");
		// 添加发布类型
		svVo.setSvType(collegeform.getType());
		// 上传版本文件
		FormFile file = collegeform.getTheSvFile();
		String filePath = "/softversion/" + stId + "/"
				+ collegeform.getSvVersion();
		String returnStr = "";
		String fileName = "";
		if (file != null) {
			fileName = file.getFileName();
			// 没有设置文件名的属性
			try {
				returnStr = FileUpload.uploadFile(file, uploadImagePath
						+ filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (StringUtils.isEmpty(returnStr)) {
				svVo.setSvFilePath(filePath + "/" + fileName);
			}
		}

		// 添加审核表信息
		if (StringUtils.isNotEmpty(friendConfig)
				&& StringUtils.equals(friendConfig, "0")) {
			svVo.setSvStatus("1");
			int id = this.versionOperService.addSoftVersion(svVo);

			SoftAuditVO auditVo = new SoftAuditVO();
			auditVo.setSvId(new Integer(id).toString());
			auditVo.setCreatetime(new Date());
			auditVo.setDesc(collegeform.getSvDesc());
			auditVo.setFilePath(filePath + "/" + fileName);
			auditVo.setForceupdate(collegeform.getSvForceupdate());
			auditVo.setName(collegeform.getSvName());
			auditVo.setStatus("1");
			auditVo.setVersion(collegeform.getSvVersion());

			this.versionOperService.addSoftAudit(auditVo);
			request.setAttribute("flag", "SUCCESS");
		} else {
			String friendsNum = collegeform.getFriendsNum().trim();
			// StNumPolicyVO stnpVo = new StNumPolicyVO();
			// stnpVo.setPoNumSection(friendsNum);
			// stnpVo.setPoType("0");
			// stnpVo.setStId(new Integer(stId).toString());
			// stnpVo.setPoCreatetime(new Date());
			// stnpVo.setPoUpdatetime(null);
			TerUserGroupVO tugVo = new TerUserGroupVO();
			tugVo.setTgName(collegeform.getSvName());
			tugVo.setTgDesc(collegeform.getSvDesc());
			tugVo.setTgPhone(friendsNum);
			// 添加好友号码段管理列表
			int tgId = 0;
			try {
				tgId = this.versionOperService.addTerUserGroup(tugVo);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
			if (tgId != 0) {
				this.versionOperService.addSoftGroupMap(new Integer(stId)
						.toString(), new Integer(tgId).toString());
			}
			// 添加版本
			svVo.setSvStatus("2");
			int id = this.versionOperService.addSoftVersion(svVo);

			SoftAuditVO auditVo = new SoftAuditVO();
			auditVo.setSvId(new Integer(id).toString());
			auditVo.setCreatetime(new Date());
			auditVo.setDesc(collegeform.getSvDesc());
			auditVo.setFilePath(filePath + "/" + fileName);
			auditVo.setForceupdate(collegeform.getSvForceupdate());
			auditVo.setName(collegeform.getSvName());
			auditVo.setStatus("2");
			auditVo.setVersion(collegeform.getSvVersion());

			this.versionOperService.addSoftAudit(auditVo);
			request.setAttribute("flag", "SUCCESS");
		}
		return retstId;
	}

	private int save2(HttpServletRequest request, SoftTypeForm collegeform) {
		String softId = collegeform.getSoftId();
		// 更新下软件类型的图标文件
		String proId = "";
		if (StringUtils.isNotEmpty(softId)) {
			proId = this.versionOperService.getSoftTypeInfoByStId(softId).getProId();
			SoftTypeVO stVo = new SoftTypeVO();
			stVo.setStId(softId);
			// 上传图标文件
			FormFile file1 = collegeform.getTheStFile();
			String filePath1 = "/college/user_icons/" + proId;
			String returnStr1 = "";
			String fileName1 = "";
			if (file1 != null) {
				fileName1 = file1.getFileName();
				// 没有设置文件名的属性
				try {
					returnStr1 = FileUpload.uploadFile(file1, uploadImagePath
							+ filePath1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (StringUtils.isEmpty(returnStr1)) {
					stVo.setStIcon(fileName1);
					stVo.setStIconPath(filePath1 + "/" + fileName1);
				}
			} else {
				stVo.setStIcon("college.jpg");
				stVo.setStIconPath("/college/icons/college.jpg");
			}
			// 更新了应用图标
			this.versionOperService.updateSoftTypeIcon(stVo);
		}
		// 修改适配关系edited by wanglei
		String terminalsIds = collegeform.getTerminalsIds();
		if (StringUtils.isNotEmpty(terminalsIds)) {
			// 删除旧的适配关系
			int m = this.versionOperService
					.deleteAllSoftTermMatchByStId(softId);
			log.debug("删除了softId = " + softId + " 的适配关系 " + m + "条！");
			String[] arrayStr = terminalsIds.split(",");
			for (int i = 0; i < arrayStr.length; i++) {
				String terId = arrayStr[i];
				this.versionOperService.addSoftTermMap(new Integer(softId)
						.toString(), terId ,String.valueOf(proId));
			}
		}
		// 添加新的软件版本
		String friendConfig = collegeform.getFriendsConfig();

		SoftVersionVO svVo = new SoftVersionVO();
		svVo.setStId(new Integer(softId).toString());
		svVo.setSvName(collegeform.getSvName());
		svVo.setSvVersion(collegeform.getSvVersion());
		svVo.setSvDesc(collegeform.getSvDesc());
		svVo.setSvForceupdate(collegeform.getSvForceupdate());
		svVo.setSvCreatetime(new Date());
		// 保存发布者电话号码和ID
		svVo.setSvSender(collegeform.getSvSender());
		svVo.setSvSubmitid(collegeform.getSvSubmitid());
		// 默认状态为2.审核通过
		svVo.setSvStatus("2");
		svVo.setSvType(collegeform.getType());
		// 上传版本文件
		FormFile file = collegeform.getTheSvFile();
		String filePath = "/softversion/" + softId + "/"
				+ collegeform.getSvVersion();
		String returnStr = "";
		String fileName = "";
		if (file != null) {
			fileName = file.getFileName();
			// 没有设置文件名的属性
			try {
				returnStr = FileUpload.uploadFile(file, uploadImagePath
						+ filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (StringUtils.isEmpty(returnStr)) {
				svVo.setSvFilePath(filePath + "/" + fileName);
			}
		}

		// 添加审核表信息
		if (StringUtils.isNotEmpty(friendConfig)
				&& StringUtils.equals(friendConfig, "0")) {
			// 平台发布执行这里。
			log.debug("平台发布:type = 3...");
			svVo.setSvStatus("1");
			int id = this.versionOperService.addSoftVersion(svVo);

			SoftAuditVO auditVo = new SoftAuditVO();
			auditVo.setSvId(new Integer(id).toString());
			auditVo.setCreatetime(new Date());
			auditVo.setDesc(collegeform.getSvDesc());
			auditVo.setFilePath(filePath + "/" + fileName);
			auditVo.setForceupdate(collegeform.getSvForceupdate());
			auditVo.setName(collegeform.getSvName());
			auditVo.setStatus("1");
			auditVo.setVersion(collegeform.getSvVersion());

			this.versionOperService.addSoftAudit(auditVo);
			request.setAttribute("flag", "SUCCESS");
		} else {
			// 其他发布执行这里。
			log.debug("其他发布:type = 1 or 2 ...");
			String friendsNum = collegeform.getFriendsNum().trim();
			// StNumPolicyVO stnpVo = new StNumPolicyVO();
			// stnpVo.setPoNumSection(friendsNum);
			// stnpVo.setPoType("0");
			// stnpVo.setStId(new Integer(softId).toString());
			// stnpVo.setPoCreatetime(new Date());
			// stnpVo.setPoUpdatetime(null);
			// // 添加好友号码段管理列表
			//
			// this.versionOperService.addStNumPolicy(stnpVo);
			// TerUserGroupVO tugVo = new TerUserGroupVO();
			// tugVo.setTgName(collegeform.getSvName());
			// tugVo.setTgDesc(collegeform.getSvDesc());
			// tugVo.setTgPhone(friendsNum);
			// // 添加好友号码段管理列表
			// int tgId = 0;
			// try {
			// tgId = this.versionOperService.addTerUserGroup(tugVo);
			// } catch (Exception e) {
			// e.printStackTrace();
			// log.error(e.getMessage());
			// }
			// if (tgId != 0) {
			// this.versionOperService.addSoftGroupMap(new Integer(softId)
			// .toString(), new Integer(tgId).toString());
			// }
			// 添加版本
			int id = this.versionOperService.addSoftVersion(svVo);
			request.setAttribute("flag", "SUCCESS");
		}
		return Integer.parseInt(softId);
	}

	public ActionForward checkUniqueIsmpId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ismpId = request.getParameter("ismpId");
		log.debug("传递的参数ismpId:" + ismpId);
		String result = "";
		if (StringUtils.isEmpty(ismpId)) {
			result = "ERROR";
			this.renderText(response, result);
			return null;
		}
		boolean bl = this.versionOperService.checkUniqueImspId(ismpId);
		if (bl) {
			result = "TRUE";
		} else {
			result = "FALSE";
		}
		this.renderText(response, result);
		return null;
	}

	public ActionForward checkUniqueSvName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String svName = request.getParameter("svName");
		log.debug("传递的参数svName:" + svName);
		String svNameUft8 = java.net.URLDecoder.decode(svName, "UTF-8");
		log.debug("传递的参数svNameUft8:" + svNameUft8);
		String result = "";
		if (StringUtils.isEmpty(svName)) {
			result = "ERROR";
			this.renderText(response, result);
			return null;
		}

		boolean bl = this.versionOperService.checkUniqueSvName(svName);
		if (bl) {
			result = "TRUE";
		} else {
			result = "FALSE";
		}
		this.renderText(response, result);
		return null;
	}

	public ActionForward doMatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("college:doMatch() - start");
		log.info("college:doMatch() - end");
		return mapping.findForward("doMatchTerminal");
	}

	public ActionForward getMobileTerminal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("college:getMobileTerminal() - start");
		List<FactoryVO> facList = this.versionOperService.getMobileTerminal();
		if (facList != null && facList.size() > 0) {
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < facList.size(); i++) {
				JSONObject jsonFac = new JSONObject();
				FactoryVO facVo = facList.get(i);
				jsonFac.put("facId", facVo.getFacId());
				jsonFac.put("facName", facVo.getFacName());
				List<TerminalVO> terList = facVo.getTerList();
				if (terList != null && terList.size() > 0) {
					JSONArray jsonFacArray = new JSONArray();
					for (int j = 0; j < terList.size(); j++) {
						TerminalVO terVo = terList.get(j);
						JSONObject jsonTer = new JSONObject();
						jsonTer.put("terId", terVo.getTerId());
						jsonTer.put("terName", terVo.getTerName());
						jsonFacArray.put(j, jsonTer);
					}
					jsonFac.put("terminals", jsonFacArray);
				}
				jsonArray.put(i, jsonFac);
			}
			jsonObj.put("result", jsonArray);
			log.debug(jsonObj.toString());
			log.info("college:getMobileTerminal() - end");
			this.renderText(response, jsonObj.toString());
		}
		return null;
	}

	public ActionForward getBrewTerminal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("college:getBrewTerminal() - start");
		List<FactoryVO> facList = this.versionOperService.getBrewTerminal();
		if (facList != null && facList.size() > 0) {
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < facList.size(); i++) {
				JSONObject jsonFac = new JSONObject();
				FactoryVO facVo = facList.get(i);
				jsonFac.put("facId", facVo.getFacId());
				jsonFac.put("facName", facVo.getFacName());
				List<TerminalVO> terList = facVo.getTerList();
				if (terList != null && terList.size() > 0) {
					JSONArray jsonFacArray = new JSONArray();
					for (int j = 0; j < terList.size(); j++) {
						TerminalVO terVo = terList.get(j);
						JSONObject jsonTer = new JSONObject();
						jsonTer.put("terId", terVo.getTerId());
						jsonTer.put("terName", terVo.getTerName());
						jsonFacArray.put(j, jsonTer);
					}
					jsonFac.put("terminals", jsonFacArray);
				}
				jsonArray.put(i, jsonFac);
			}
			jsonObj.put("result", jsonArray);
			log.debug(jsonObj.toString());
			log.info("college:getBrewTerminal() - end");
			this.renderText(response, jsonObj.toString());
		}
		return null;
	}
}

//package cn.eshore.mismp.version.service;
//
//
///**
// * 2.1	软件版本更新接口
// * 获取最新软件版本信息；
// */
//
//import java.io.File;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import cn.eshore.mismp.common.Consts;
//import cn.eshore.mismp.dao.domain.SoftVersionVO;
//import cn.eshore.mismp.util.MobileGlobals;
//import cn.eshore.mismp.util.Util;
//import cn.eshore.mismp.web.servlet.BaseHttpServlet;
//
//public class SoftVersionServlet extends BaseHttpServlet {
//
//	protected static final Logger log = Logger.getLogger(SoftVersionServlet.class);
//
//	static {
//
//		registerInvokeMethod("updateSoftVersion", UpdateSoftVersionReqParam.class);
//
//		registerInvokeMethod("updateSoftVersionExt", UpdateSoftVersionReqParam.class);
//
//	}
//
//	public String updateSoftVersion(UpdateSoftVersionReqParam param) {
//		HttpServletRequest request = reqThreadLocal.get();
//		String phoneNum = Util.nullToStr(request.getHeader("X-Up-Calling-Line-ID"));
//		log.info("通过WAP网关头获取用户手机号码:phone = " + phoneNum);
//		if (StringUtils.isEmpty(phoneNum)) {
//			phoneNum = request.getParameter("testPhoneNum");
//		}
//		String result = "";
//		log.debug("【获取版本更新信息】[" + phoneNum + "] - 【提交参数】 >>> " + param.toString());
//		if (param.isValid()) {
//			param.setPhoneNum(phoneNum);
//			result = processSoftVersion(param, phoneNum);
//		} else {
//			result = Consts.XML_ERROR_JSON;
//		}
//		log.debug("【获取版本更新信息】[" + phoneNum + "] - 【返回结果】 >>> " + result);
//		return result;
//
//	}
//
//	private String processSoftVersion(UpdateSoftVersionReqParam param, String phoneNum) {
//
//		JSONObject jsonObject = new JSONObject();
//		// basePath
//		String basePath = reqThreadLocal.get().getScheme() + "://" + reqThreadLocal.get().getServerName() + ":"
//				+ reqThreadLocal.get().getServerPort() + reqThreadLocal.get().getContextPath();
//		// if(StringUtils.contains(basePath, "ifservice_1.3")){
//		// basePath = "http://125.88.123.227:8008/ifserviceOmaTest";
//		// }
//		TerminalVO terminalVO = this.businessSupportService.getSoftVersionService().getTerminalAndVirNameByTerCode(param.getPhone());
//		if(terminalVO == null){
//			param.setPhone("XT800");
//		}
//
//		SoftVersionVO vo = this.businessSupportService.getSoftVersionService().getLastSoftVersionForNew(
//				param.getPhone(), param.getClassid(), phoneNum);
//		if (vo != null && vo.getId() != 0) {
//			// String version = param.getVersion();
//			// String phone = param.getPhone();
//			// String classId = param.getClassid();
//			// if(StringUtils.equals(classId, "0x89999999")){
//			// if (StringUtils.isEmpty(version) && (StringUtils.equals(phone,
//			// "XT800") || StringUtils.equals(phone, "SCH-i909"))) {
//			// if(StringUtils.equals(phone, "XT800")){
//			// return
//			// "{\"softlength\":\"121920\",\"path\":\"http://125.88.123.227:8008/ifserviceOmaTest/downloadServlet.htm?path=/softversion/2320/2.0.0.8/V2.0.0.8_XT800_eStore_20101230.apk&imsi=123456&esn=1234567890&category=android&phone=XT800&classid=0x89999999&version=null&vsersion=2.0.0.8&stid=2320\",\"isoptional\":0,\"desc\":\"2.0.0.8过渡版本\",\"version\":\"2.0.0.8\"}";
//			// }
//			// if(StringUtils.equals(phone, "SCH-i909")){
//			// return
//			// "{\"softlength\":\"121920\",\"path\":\"http://125.88.123.227:8008/ifserviceOmaTest/downloadServlet.htm?path=/softversion/2921/2.0.0.8/V2.0.0.8_SCH-i909_eStore_20101230.apk&imsi=123456&esn=1234567890&category=android&phone=SCH-i909&classid=0x89999999&version=null&vsersion=2.0.0.8&stid=2921\",\"isoptional\":0,\"desc\":\"2.0.0.8过渡版本\",\"version\":\"2.0.0.8\"}";
//			// }
//			// }
//			// }
//			// ===================================================
//			try {
//				jsonObject.put("version", vo.getVersion());
//				jsonObject.put("isoptional", vo.getForceupdate());
//				jsonObject.put("desc", vo.getDesc());
//				String filePath = vo.getFiltPath();
//				if (StringUtils.contains(filePath, "http://")) {
//					jsonObject.put("path", vo.getFiltPath());
//				} else {
//					jsonObject.put("path",
//							basePath + "/downloadServlet.htm?path=" + vo.getFiltPath() + "&" + param.toString()
//									+ "&updateVersion=" + vo.getVersion() + "&stid=" + vo.getStId());
//				}
//				File versionFile = new File(MobileGlobals.getProperty("software.file.path") + "/" + vo.getFiltPath());
//				log.debug("File path=" + MobileGlobals.getProperty("software.file.path") + "/" + vo.getFiltPath());
//				jsonObject.put("softlength", "" + versionFile.length());
//
//			} catch (JSONException e) {
//				e.printStackTrace();
//				log.error(e.getMessage());
//			}
//
//			return jsonObject.toString();
//		} else {
//			return Consts.NO_RESULT_JSON;
//		}
//
//	}
//
//	public String updateSoftVersionExt(UpdateSoftVersionReqParam param) {
//
//		String result = "";
//		if (log.isDebugEnabled()) {
//			log.info("updateSoftVersionExt:" + param.toString());
//		}
//
//		if (param.isValid()) {
//			result = processSoftVersionExt(param);
//		} else {
//			result = Consts.XML_ERROR_JSON;
//		}
//
//		if (log.isDebugEnabled()) {
//			log.info("updateSoftVersion手机端返回结果result:" + result);
//		}
//
//		return result;
//
//	}
//
//	// 处理版本结果
//	private String processSoftVersionExt(UpdateSoftVersionReqParam param) {
//
//		String result = "";
//		StringBuffer resultBuffer = new StringBuffer();
//		// basePath
//		String basePath = reqThreadLocal.get().getScheme() + "://" + reqThreadLocal.get().getServerName() + ":"
//				+ reqThreadLocal.get().getServerPort() + reqThreadLocal.get().getContextPath();
//		// 针对天翼阅读
//		param.setClassid("0x20100702");
//
//		// 获取翻页信息
//		SoftVersionVO vo = this.businessSupportService.getSoftVersionService().getLastSoftVersion(param.getPhone(),
//				param.getClassid());
//		if (vo != null && vo.getId() != 0) {
//			resultBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//			resultBuffer.append("<Response>");
//			resultBuffer.append("<SoftVersion>");
//			resultBuffer.append("<version>");
//			resultBuffer.append(vo.getVersion());
//			resultBuffer.append("</version>");
//			resultBuffer.append("<path>");
//			resultBuffer.append("<![CDATA[" + basePath + "/downloadServlet.htm?path=" + vo.getFiltPath() + "&"
//					+ param.toString() + "&vsersion=" + vo.getVersion() + "&stid=" + vo.getStId() + "]]>");
//			resultBuffer.append("</path>");
//			resultBuffer.append("<isoptional>");
//			resultBuffer.append(vo.getForceupdate());
//			resultBuffer.append("</isoptional>");
//			resultBuffer.append("<softlength>");
//			File versionFile = new File(MobileGlobals.getProperty("software.file.path") + "/" + vo.getFiltPath());
//			resultBuffer.append(versionFile.length());
//			resultBuffer.append("</softlength>");
//			resultBuffer.append("</SoftVersion>");
//			resultBuffer.append("</Response>");
//
//		} else {
//
//			resultBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//			resultBuffer.append("<Response>");
//			/*
//			 * resultBuffer.append("<SoftVersion>");
//			 * resultBuffer.append("<version>");
//			 * resultBuffer.append("</version>"); resultBuffer.append("<path>");
//			 * resultBuffer.append("</path>");
//			 * resultBuffer.append("<isoptional>");
//			 * resultBuffer.append("</isoptional>");
//			 * resultBuffer.append("<softlength>");
//			 * resultBuffer.append("</softlength>");
//			 * resultBuffer.append("</SoftVersion>");
//			 */
//			resultBuffer.append("</Response>");
//
//		}
//
//		result = resultBuffer.toString();
//
//		return result;
//
//	}
//
//}

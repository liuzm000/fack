/**
 * 
 */
package cn.eshore.mismp.version.domain.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * @author wanglei
 * 
 */
public class SoftTypeForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = -3264076699715513182L;

	private String stId;

	private String stName;

	private String stDesc;

	private String proId;

	private Date stCreatetime;

	private String stIcon;

	private String stIconPath;

	private String stMsgPush;

	private String stEngName;
	
	private String stMsgType;
	
	private FormFile theStFile;//上传的图标文件
	
	private FormFile theSvFile;//上传的版本文件

	private String terId;

	private String terCode;

	private String facId;

	private String virId;

	private String terJava;

	private String terMms;

	private String terWap;

	private String terEvdo;

	private String terPlat;

	private String terPicPath;

	private String terDes;

	private String terName;

	private String svId;

	private String svVersion;

	private String svName;

	private String svForceupdate;

	private String svDesc;

	private String svFilePath;

	private String svStatus;

	private Date svCreatetime;
	
	private String terIds;//适配终端的ID集合
	
	private String cancelIds;//适配终端的ID集合
	
	//好友设置
	private String friendsConfig;
	
	private String friendsNum;
	
	//添加发布者电话号码和ID：适合校园创业
	
	private String svSender;
	
	private String svSubmitid;
	
	private String type;
	
	private String softId;
	
	private String ismpId;
	
	private String appType;//1.游戏，2.软件,3.主题
	
	private String terminalsIds;//多个适配终端的ID，以逗号隔开。
	
	private String mobileType;//多个适配终端的型号名称，以逗号隔开。
	
	private FormFile thePreviewFile1;//上传图片预览1
	
	private FormFile thePreviewFile2;//上传图片预览2
	
	private FormFile theListStFile;//上传的应用类型的列表图标文件 added by wanglei 20100111
	
	private String publishType;
	
	private String downloadUrl;

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Date getStCreatetime() {
		return stCreatetime;
	}

	public void setStCreatetime(Date stCreatetime) {
		this.stCreatetime = stCreatetime;
	}

	public String getStDesc() {
		return stDesc;
	}

	public void setStDesc(String stDesc) {
		this.stDesc = stDesc;
	}

	public String getStEngName() {
		return stEngName;
	}

	public void setStEngName(String stEngName) {
		this.stEngName = stEngName;
	}

	public String getStIcon() {
		return stIcon;
	}

	public void setStIcon(String stIcon) {
		this.stIcon = stIcon;
	}

	public String getStIconPath() {
		return stIconPath;
	}

	public void setStIconPath(String stIconPath) {
		this.stIconPath = stIconPath;
	}

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public String getStMsgPush() {
		return stMsgPush;
	}

	public void setStMsgPush(String stMsgPush) {
		this.stMsgPush = stMsgPush;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getTerCode() {
		return terCode;
	}

	public void setTerCode(String terCode) {
		this.terCode = terCode;
	}

	public String getTerDes() {
		return terDes;
	}

	public void setTerDes(String terDes) {
		this.terDes = terDes;
	}

	public String getTerEvdo() {
		return terEvdo;
	}

	public void setTerEvdo(String terEvdo) {
		this.terEvdo = terEvdo;
	}

	public String getTerId() {
		return terId;
	}

	public void setTerId(String terId) {
		this.terId = terId;
	}

	public String getTerJava() {
		return terJava;
	}

	public void setTerJava(String terJava) {
		this.terJava = terJava;
	}

	public String getTerMms() {
		return terMms;
	}

	public void setTerMms(String terMms) {
		this.terMms = terMms;
	}

	public String getTerName() {
		return terName;
	}

	public void setTerName(String terName) {
		this.terName = terName;
	}

	public String getTerPicPath() {
		return terPicPath;
	}

	public void setTerPicPath(String terPicPath) {
		this.terPicPath = terPicPath;
	}

	public String getTerPlat() {
		return terPlat;
	}

	public void setTerPlat(String terPlat) {
		this.terPlat = terPlat;
	}

	public String getTerWap() {
		return terWap;
	}

	public void setTerWap(String terWap) {
		this.terWap = terWap;
	}

	public String getVirId() {
		return virId;
	}

	public void setVirId(String virId) {
		this.virId = virId;
	}

	public Date getSvCreatetime() {
		return svCreatetime;
	}

	public void setSvCreatetime(Date svCreatetime) {
		this.svCreatetime = svCreatetime;
	}

	public String getSvDesc() {
		return svDesc;
	}

	public void setSvDesc(String svDesc) {
		this.svDesc = svDesc;
	}

	public String getSvFilePath() {
		return svFilePath;
	}

	public void setSvFilePath(String svFilePath) {
		this.svFilePath = svFilePath;
	}

	public String getSvForceupdate() {
		return svForceupdate;
	}

	public void setSvForceupdate(String svForceupdate) {
		this.svForceupdate = svForceupdate;
	}

	public String getSvId() {
		return svId;
	}

	public void setSvId(String svId) {
		this.svId = svId;
	}

	public String getSvName() {
		return svName;
	}

	public void setSvName(String svName) {
		this.svName = svName;
	}

	public String getSvStatus() {
		return svStatus;
	}

	public void setSvStatus(String svStatus) {
		this.svStatus = svStatus;
	}

	public String getSvVersion() {
		return svVersion;
	}

	public void setSvVersion(String svVersion) {
		this.svVersion = svVersion;
	}

	public FormFile getTheStFile() {
		return theStFile;
	}

	public void setTheStFile(FormFile theStFile) {
		this.theStFile = theStFile;
	}

	public FormFile getTheSvFile() {
		return theSvFile;
	}

	public void setTheSvFile(FormFile theSvFile) {
		this.theSvFile = theSvFile;
	}

	public String getTerIds() {
		return terIds;
	}

	public void setTerIds(String terIds) {
		this.terIds = terIds;
	}

	public String getStMsgType() {
		return stMsgType;
	}

	public void setStMsgType(String stMsgType) {
		this.stMsgType = stMsgType;
	}

	public String getFriendsNum() {
		return friendsNum;
	}

	public void setFriendsNum(String friendsNum) {
		this.friendsNum = friendsNum;
	}

	public String getFriendsConfig() {
		return friendsConfig;
	}

	public void setFriendsConfig(String friendsConfig) {
		this.friendsConfig = friendsConfig;
	}

	public String getSvSender() {
		return svSender;
	}

	public void setSvSender(String svSender) {
		this.svSender = svSender;
	}

	public String getSvSubmitid() {
		return svSubmitid;
	}

	public void setSvSubmitid(String svSubmitid) {
		this.svSubmitid = svSubmitid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSoftId() {
		return softId;
	}

	public void setSoftId(String softId) {
		this.softId = softId;
	}

	public String getIsmpId() {
		return ismpId;
	}

	public void setIsmpId(String ismpId) {
		this.ismpId = ismpId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getTerminalsIds() {
		return terminalsIds;
	}

	public void setTerminalsIds(String terminalsIds) {
		this.terminalsIds = terminalsIds;
	}

	public String getMobileType() {
		return mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	public FormFile getThePreviewFile1() {
		return thePreviewFile1;
	}

	public void setThePreviewFile1(FormFile thePreviewFile1) {
		this.thePreviewFile1 = thePreviewFile1;
	}

	public FormFile getThePreviewFile2() {
		return thePreviewFile2;
	}

	public void setThePreviewFile2(FormFile thePreviewFile2) {
		this.thePreviewFile2 = thePreviewFile2;
	}

	public FormFile getTheListStFile() {
		return theListStFile;
	}

	public void setTheListStFile(FormFile theListStFile) {
		this.theListStFile = theListStFile;
	}

	public String getPublishType() {
		return publishType;
	}

	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
	

}

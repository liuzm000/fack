/**
 * 
 */
package cn.eshore.mismp.version.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanglei
 *
 */
public class SoftVersionVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String stId;
	
	private String svId;

	private String svVersion;

	private String svName;

	private String svForceupdate;

	private String svDesc;

	private String svFilePath;

	private String svStatus;

	private Date svCreatetime;

	private String svSender;
	
	private String svSubmitid;
	
	private String svType;
	
	private String stEngName;
	
	private String svPreviewPath;
	
	private String previewPath1 = "";
	
	private String previewPath2 = "";
	
	private String publishType;    //0.预上线   1.正式上线
	
	private String downloadUrl = "";
	
	public SoftVersionVO(){
		if(svPreviewPath != null){
			String prePath[] = svPreviewPath.split(",");
			if(prePath.length == 1){
				previewPath1 = prePath[0];
			}
			else if(prePath.length == 2){
				previewPath2 = prePath[1];
			}
		}
		
	}
	
	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
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

	public String getSvType() {
		return svType;
	}

	public void setSvType(String svType) {
		this.svType = svType;
	}

	public String getStEngName() {
		return stEngName;
	}

	public void setStEngName(String stEngName) {
		this.stEngName = stEngName;
	}

	public String getSvPreviewPath() {
		return svPreviewPath;
	}

	public void setSvPreviewPath(String svPreviewPath) {
		this.svPreviewPath = svPreviewPath;
	}

	public String getPreviewPath1() {
		return previewPath1;
	}

	public void setPreviewPath1(String previewPath1) {
		this.previewPath1 = previewPath1;
	}

	public String getPreviewPath2() {
		return previewPath2;
	}

	public void setPreviewPath2(String previewPath2) {
		this.previewPath2 = previewPath2;
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

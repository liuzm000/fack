package cn.eshore.mismp.dao.domain;

import java.util.Date;

public class SoftVersionVO {

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

}

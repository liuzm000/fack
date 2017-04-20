package cn.eshore.mismp.dao.domain;

import java.util.Date;

public class SoftAuditVO {

	private String id;
	
	private String svId;

	private String version;

	private String name;

	private String forceupdate;
	
	private String filePath;
	
	private Date createtime;
	
	private String status;

	private String desc;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getForceupdate() {
		return forceupdate;
	}

	public void setForceupdate(String forceupdate) {
		this.forceupdate = forceupdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSvId() {
		return svId;
	}

	public void setSvId(String svId) {
		this.svId = svId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	

}

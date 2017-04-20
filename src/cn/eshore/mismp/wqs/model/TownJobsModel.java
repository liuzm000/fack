package cn.eshore.mismp.wqs.model;

import java.sql.Clob;

public class TownJobsModel {

	private long id;
	private String title;
	private String unit;
	private String contactUser;
	private String contactPhone;
	private String createTime;
	private String infoUrl;
	private Clob infoValue;
	private String jobUrl;
	private Clob jobValue;
	private String infoStr;
	private String jobStr;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getContactUser() {
		return contactUser;
	}
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getInfoUrl() {
		return infoUrl;
	}
	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}
	
	public Clob getInfoValue() {
		return infoValue;
	}
	public void setInfoValue(Clob infoValue) {
		this.infoValue = infoValue;
	}
	public String getJobUrl() {
		return jobUrl;
	}
	public void setJobUrl(String jobUrl) {
		this.jobUrl = jobUrl;
	}
	public Clob getJobValue() {
		return jobValue;
	}
	public void setJobValue(Clob jobValue) {
		this.jobValue = jobValue;
	}
	public String getInfoStr() {
		return infoStr;
	}
	public void setInfoStr(String infoStr) {
		this.infoStr = infoStr;
	}
	public String getJobStr() {
		return jobStr;
	}
	public void setJobStr(String jobStr) {
		this.jobStr = jobStr;
	}
	
	
	
	
	
}

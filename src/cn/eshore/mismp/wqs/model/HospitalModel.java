package cn.eshore.mismp.wqs.model;

import java.sql.Clob;

public class HospitalModel {

	private long id;
	private String name;
	private String address;
	private String tele1;
	private String tele2;
	private String tele3;
	private String htmlFile;
	private String fileUrl;
	
	
	
	
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	private Clob htmlValue;
	
	private String contentStr;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTele1() {
		return tele1;
	}
	public void setTele1(String tele1) {
		this.tele1 = tele1;
	}
	public String getTele2() {
		return tele2;
	}
	public void setTele2(String tele2) {
		this.tele2 = tele2;
	}
	public String getTele3() {
		return tele3;
	}
	public void setTele3(String tele3) {
		this.tele3 = tele3;
	}
	public String getHtmlFile() {
		return htmlFile;
	}
	public void setHtmlFile(String htmlFile) {
		this.htmlFile = htmlFile;
	}
	public Clob getHtmlValue() {
		return htmlValue;
	}
	public void setHtmlValue(Clob htmlValue) {
		this.htmlValue = htmlValue;
	}
	public String getContentStr() {
		return contentStr;
	}
	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}

	
	
	
}

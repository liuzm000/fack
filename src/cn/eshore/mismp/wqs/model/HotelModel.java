package cn.eshore.mismp.wqs.model;

import java.sql.Clob;

public class HotelModel {

	private long id;
	private String name;
	private String tele;
	private String addr;
	private String htmlInfo;
	private Clob htmlvalue;
	private String htmlContent;
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
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getHtmlInfo() {
		return htmlInfo;
	}
	public void setHtmlInfo(String htmlInfo) {
		this.htmlInfo = htmlInfo;
	}
	public Clob getHtmlvalue() {
		return htmlvalue;
	}
	public void setHtmlvalue(Clob htmlvalue) {
		this.htmlvalue = htmlvalue;
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	
	
	
	
}

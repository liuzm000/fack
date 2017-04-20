package cn.eshore.mismp.wqs.model;

import java.sql.Clob;

public class EnterpriseModel {

	private long id;
	private String name;
	private String htmlPath;
	private Clob htmlValue;
	private String icon;
	
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
	public String getHtmlPath() {
		return htmlPath;
	}
	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}
	public Clob getHtmlValue() {
		return htmlValue;
	}
	public void setHtmlValue(Clob htmlValue) {
		this.htmlValue = htmlValue;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getContentStr() {
		return contentStr;
	}
	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}
	
	
	
}

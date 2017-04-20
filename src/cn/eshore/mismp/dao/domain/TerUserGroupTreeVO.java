package cn.eshore.mismp.dao.domain;

import java.util.ArrayList;
import java.util.List;

public class TerUserGroupTreeVO {

	//数据库字段
	String ugtId;//模块ID
	String ugtName;//模块名
	String ugtDesc;//描述
	String ugtParentId;//父模块ID
	int ugtType;//模块类型  0：非菜单模块 1：菜单模块
	String ugtUrl;//模块url
	String ugtOrder;//模块在同级中的顺序
	
	//附加属性,主要是用来在页面上显示的
	String ugtTypeName;
	String ugtParentName;
	int readOnly;
	
	//存放子模块信息
	List childUgtList = new ArrayList();
	
	public int getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(int readOnly) {
		this.readOnly = readOnly;
	}
	public List getChildUgtList() {
		return childUgtList;
	}
	public void setChildUgtList(List childUgtList) {
		this.childUgtList = childUgtList;
	}
	public String getUgtDesc() {
		return ugtDesc;
	}
	public void setUgtDesc(String ugtDesc) {
		this.ugtDesc = ugtDesc;
	}
	public String getUgtId() {
		return ugtId;
	}
	public void setUgtId(String ugtId) {
		this.ugtId = ugtId;
	}
	public String getUgtName() {
		return ugtName;
	}
	public void setUgtName(String ugtName) {
		this.ugtName = ugtName;
	}
	public String getUgtOrder() {
		return ugtOrder;
	}
	public void setUgtOrder(String ugtOrder) {
		this.ugtOrder = ugtOrder;
	}
	
	public int getUgtType() {
		return ugtType;
	}
	public void setUgtType(int ugtType) {
		this.ugtType = ugtType;
	}
	public String getUgtTypeName() {
		return ugtTypeName;
	}
	public void setUgtTypeName(String ugtTypeName) {
		this.ugtTypeName = ugtTypeName;
	}
	public String getUgtUrl() {
		return ugtUrl;
	}
	public void setUgtUrl(String ugtUrl) {
		this.ugtUrl = ugtUrl;
	}
	public String getUgtParentId() {
		return ugtParentId;
	}
	public void setUgtParentId(String ugtParentId) {
		this.ugtParentId = ugtParentId;
	}
	public String getUgtParentName() {
		return ugtParentName;
	}
	public void setUgtParentName(String ugtParentName) {
		this.ugtParentName = ugtParentName;
	}
	
	
	
}

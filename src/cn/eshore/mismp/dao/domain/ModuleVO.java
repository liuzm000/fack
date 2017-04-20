package cn.eshore.mismp.dao.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModuleVO implements Serializable {

//	数据库字段
	String module_id;//模块ID
	String module_name;//模块名
	String description;//描述
	String parent_module_id;//父模块ID
	int module_type;//模块类型  0：非菜单模块 1：菜单模块
	String module_url;//模块url
	String module_order;//模块在同级中的顺序
	
//	附加属性,主要是用来在页面上显示的
	String module_type_name;
	String parent_module_name;
	int readOnly;
	
	//存放子模块信息
	List childModuleList = new ArrayList();
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public int getModule_type() {
		return module_type;
	}
	public void setModule_type(int module_type) {
		this.module_type = module_type;
	}
	public String getModule_url() {
		return module_url;
	}
	public void setModule_url(String module_url) {
		this.module_url = module_url;
	}
	public String getParent_module_id() {
		return parent_module_id;
	}
	public void setParent_module_id(String parent_module_id) {
		this.parent_module_id = parent_module_id;
	}
	
	public String getModule_order() {
		return module_order;
	}
	public void setModule_order(String module_order) {
		this.module_order = module_order;
	}
	public String getModule_type_name() {
		return module_type_name;
	}
	public void setModule_type_name(String module_type_name) {
		this.module_type_name = module_type_name;
	}
	public String getParent_module_name() {
		return parent_module_name;
	}
	public void setParent_module_name(String parent_module_name) {
		this.parent_module_name = parent_module_name;
	}
	public List getChildModuleList() {
		return childModuleList;
	}
	public void setChildModuleList(List childModuleList) {
		this.childModuleList = childModuleList;
	}
	public int getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(int readOnly) {
		this.readOnly = readOnly;
	}
	
	
}

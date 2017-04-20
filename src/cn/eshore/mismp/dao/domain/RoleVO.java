package cn.eshore.mismp.dao.domain;

import java.io.Serializable;

public class RoleVO implements Serializable {

	int role_id;//角色ID
	String role_name;//角色名
	String description;//描述
	int role_level;//角色级别
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public int getRole_level() {
		return role_level;
	}
	public void setRole_level(int role_level) {
		this.role_level = role_level;
	}
	
	
}

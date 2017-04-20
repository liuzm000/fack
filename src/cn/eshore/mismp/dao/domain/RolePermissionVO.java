package cn.eshore.mismp.dao.domain;

import java.io.Serializable;

public class RolePermissionVO implements Serializable {
	private static final long serialVersionUID = 1L;
	int permission_id;//权限ID
	int role_id;//角色ID
	String module_id;//模块ID
	int readOnly;
	
	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public int getPermission_id() {
		return permission_id;
	}
	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public int getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(int readOnly) {
		this.readOnly = readOnly;
	}
	
	
}

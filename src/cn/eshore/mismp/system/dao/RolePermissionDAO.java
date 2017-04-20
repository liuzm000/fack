package cn.eshore.mismp.system.dao;

import java.util.List;

import cn.eshore.mismp.dao.BaseDAO;
import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.RolePermissionVO;


public interface RolePermissionDAO extends BaseDAO {

	// 添加管理员权限
	public int addRolePermission(RolePermissionVO rolePermission);

	// 删除管理员权限
	public int deleteRolePermission(int permission_id);

	// 获取角色的权限
	public List<RolePermissionVO> getRolePermissionById(int roleId);

	// 删除角色的所有权限
	public int deleteRolePermissionByRoleId(int roleId);

	// 给角色添加权限
	public int addRolePermission(int roleId, List grantModuleIdsList);
	
	//获得角色所拥有的模块集合
	public List<ModuleVO> getModulesByRoleId(long role_id);

}

package cn.eshore.mismp.system.dao;

import java.util.ArrayList;
import java.util.List;

import cn.eshore.mismp.dao.BaseDAO;
import cn.eshore.mismp.dao.domain.RoleVO;
import cn.eshore.mismp.util.Pagination;


public interface RoleDAO extends BaseDAO {

	//添加角色
	public int addRole(RoleVO role);

	//修改角色
	public int updateRole(RoleVO role);

	//删除角色
	public int deleteRole(int role_id);

	//角色数量
	public long getRoleCount();
	
	public List<RoleVO> list(int level);

	public int deleteRoles(final ArrayList list);

	public RoleVO findRoleById(int roleId);

	public int findRoleCountByName(String roleName);
	
	public List listByName(String roleName);
	
	public Pagination list(int pageSize, int pageNum);
}

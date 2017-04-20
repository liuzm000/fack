/**
 * Created at:2008-10-21 下午05:16:08
 */
package cn.eshore.mismp.system.service;

import java.util.List;

import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.RolePermissionVO;
import cn.eshore.mismp.dao.domain.RoleVO;
import cn.eshore.mismp.dao.domain.UserSPVO;
import cn.eshore.mismp.dao.domain.UserVO;
import cn.eshore.mismp.util.Pagination;


/**
 * <p>Title: SystemService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="SystemService.java.html"><i>View Source</i></a></p>
 * @author Yusm
 * @version 1.0
 */
public interface SystemService {
	
	UserVO getUser(String account);
	
	public List<ModuleVO> getAllModules(int pagesize,int pagenum);
	
	public List<ModuleVO> getAllModules();
	
	public ModuleVO getModuleById(String module_id);
	
	public int addModule(ModuleVO module);
	
	public int modifyModule(ModuleVO module);
	
	public int deleteModule(String[] module_ids);
	
	public List<ModuleVO> getModulesByParentModuleId(String parentModuleId);
	
	public int modifyModuleOrder(String module_id, String order);
	
	
	
	

//	 通过角色名称取得角色信息
	// public List getRoleByName(String roleName);
	// 获得所有角色信息
	public List getAllRole();
	// 获得同级及下一级角色信息，level越低权限越高
	public List getRoleByLevel(int level);

	// 添加角色
	public int addRole(RoleVO role);

	// 根据角色名称查询个数
	public int getRoleCountByName(String roleName);

	// 根据角色名称查询
	public List getRoleListByName(String roleName);

	// 修改角色
	public int modifyRole(RoleVO role);

	// 根据角色id删除角色
	public int deleteRoleById(int roleId);

	// 批量删除角色
	public int deleteRoleByIds(String[] roleIds);

	// 根据id查找角色
	public RoleVO findRoleById(int roleId);

	// 查找所有角色
	public List searchRole();

	// 查找角色对应的权限
	public List<RolePermissionVO> seachRolePermissionByRoleId(int roleId);

	// 修改角色权限
	public int modifyRolePermission(int roleId, String[] moduleIds);

	// 先删除roleId的权限，再插入本次设定的权限
	public int updateRolePermission(int roleId, String[] ids);

	// 获得排序的模块信息
	public List getAllOrderedModule();
	
	public Pagination searchRole(int pageSize, int pageNum);
	
	public Pagination getAdminInfos(int roleLevel, int pageSize, int pageNum);
	
	//	 检查是否已经存在该帐号
	public int checkExistByAccount(String account);

	//	 管理员管理
	public int addAdministrator(UserVO admin);
	
	public UserVO getAdministratorById(String adminId);
	
	public int modifyAdministrator(UserVO admin);
	
	public int deleteAdministrator(String[] adminIds);
	
	
	public List<ModuleVO> getModulesByRoleId(long role_id);
	//获取用户SP信息
	public UserSPVO getUserSPVO(long userId);

	List<ModuleVO> doModulesQuery(ModuleVO moduleVO, int pageSize, int pageNum);
	
	
}

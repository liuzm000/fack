/**
 * Created at:2008-10-21 下午05:16:53
 */
package cn.eshore.mismp.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import cn.eshore.mismp.common.Consts;
import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.RolePermissionVO;
import cn.eshore.mismp.dao.domain.RoleVO;
import cn.eshore.mismp.dao.domain.UserSPVO;
import cn.eshore.mismp.dao.domain.UserVO;
import cn.eshore.mismp.system.dao.ModuleDAO;
import cn.eshore.mismp.system.dao.RoleDAO;
import cn.eshore.mismp.system.dao.RolePermissionDAO;
import cn.eshore.mismp.system.dao.UserDAO;
import cn.eshore.mismp.system.service.SystemService;
import cn.eshore.mismp.util.OBJ2XML;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.XML2OBJ;


/**
 * <p>Title: SystemServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="SystemServiceImpl.java.html"><i>View Source</i></a></p>
 * @author Yusm
 * @version 1.0
 */
public class SystemServiceImpl implements SystemService {
	
	protected static final Logger log = Logger
	.getLogger(SystemServiceImpl.class);
	
	private UserDAO userDAO;
	
	private ModuleDAO moduleDAO;
	
	private RoleDAO roleDAO;
	
	private RolePermissionDAO rolePermissionDAO;
	
	
	/* (non-Javadoc)
	 * @see com.poson.services.SystemService#getUser(java.lang.String, java.lang.String)
	 */
	public UserVO getUser(String account) {
		return userDAO.getUser(account);
	}
	
	public UserDAO getUserDAO(){
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public ModuleDAO getModuleDAO() {
		return moduleDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public RolePermissionDAO getRolePermissionDAO() {
		return rolePermissionDAO;
	}

	public void setRolePermissionDAO(RolePermissionDAO rolePermissionDAO) {
		this.rolePermissionDAO = rolePermissionDAO;
	}


	public List<ModuleVO> getAllModules(int pagesize,int pagenum) {
		return this.moduleDAO.getAllModules(pagesize,pagenum);
	}
	
	public List<ModuleVO> getAllModules(){
		return this.moduleDAO.getAllModules();
	}

	public ModuleVO getModuleById(String module_id) {
		return this.moduleDAO.getModuleById(module_id);
	}
	
	public int addModule(ModuleVO module) {
		return this.moduleDAO.addModule(module);
	}
	
	public int modifyModule(ModuleVO module) {
		return this.moduleDAO.updateModule(module);
	}
	
	public int deleteModule(String[] module_ids) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < module_ids.length; i++) {
			list.add(module_ids[i]);
		}
		return this.moduleDAO.deleteModule(list);
	}
	
	public List<ModuleVO> getModulesByParentModuleId(String parentModuleId){
		return this.moduleDAO.getModulesByParentModuleId(parentModuleId);
	}
	
	public int modifyModuleOrder(String module_id,String order){
		return this.moduleDAO.modifyModuleOrder(module_id, order);
	}
	
	
	
	
	//	角色管理
	public int addRole(RoleVO role) {
		int affect = this.getRoleDAO().addRole(role);
		return affect;
	}

	public int deleteRoleById(int roleId) {
		int affect = this.getRoleDAO().deleteRole(roleId);
		return affect;
	}

	public int deleteRoleByIds(String[] roleIds) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < roleIds.length; i++) {
			if(roleIds[i]==null||"".equals(roleIds[i].trim()))
				continue;
			list.add(roleIds[i]);
		}
		return this.getRoleDAO().deleteRoles(list);
	}

	public RoleVO findRoleById(int roleId) {
		return this.getRoleDAO().findRoleById(roleId);
	}


	public int getRoleCountByName(String roleName) {
		return this.getRoleDAO().findRoleCountByName(roleName);
	}

	public int modifyRole(RoleVO role) {
		return this.getRoleDAO().updateRole(role);
	}

	public List<RoleVO> searchRole() {
		return this.getRoleDAO().list(0);
	}

	public List getRoleListByName(String roleName) {
		return this.getRoleDAO().listByName(roleName);
	}
	
	public Pagination searchRole(int pageSize, int pageNum) {
		return this.getRoleDAO().list(pageSize, pageNum);
	}
	
	//	 add by longlianghua
	public List getAllOrderedModule() {
		List<ModuleVO> moduleList = getAllModules();
		return orderMoudle(moduleList);
	}
	
	public List<RoleVO> getAllRole() {
		return this.roleDAO.list(0);
	}
	
	public List<RoleVO> getRoleByLevel(int level) {
		return this.roleDAO.list(level);
	}

	
	private List orderMoudle(List moduleList) {
		// 排序列算法
		List<ModuleVO> newModuleList = new ArrayList<ModuleVO>();// 存放

		Map<String, ModuleVO> moduleMap = new HashMap();// 辅助构造树结构
		
		// 构造map树
		for (Iterator iter = moduleList.iterator(); iter.hasNext();) {
			ModuleVO module = (ModuleVO) iter.next();
			moduleMap.put(module.getModule_id(), module);
		}
		for (Iterator iter = moduleList.iterator(); iter.hasNext();) {
			ModuleVO module = (ModuleVO) iter.next();
			if (moduleMap.containsKey(module.getParent_module_id())) {
				ModuleVO parentModule = (ModuleVO) moduleMap.get(module
						.getParent_module_id());
				parentModule.getChildModuleList().add(module);
			}
		}
		// 取得根节点
		ModuleVO rootModule = getRootModule(Consts.ROOT_MODULE_PARENT_ID,
				moduleList);
		if (rootModule == null)
			return Collections.EMPTY_LIST;
		// 递归构造按级别，按次序的list
		constructOrderedList(newModuleList, rootModule);
		return newModuleList;
	}
	
	private void constructOrderedList(List<ModuleVO> newModuleList, ModuleVO module) {
		newModuleList.add(module);
		// System.out.println(module.getModule_name());
		List childList = module.getChildModuleList();
		if (childList != null) {
			for (Iterator iter = childList.iterator(); iter.hasNext();) {
				ModuleVO mod = (ModuleVO) iter.next();
				constructOrderedList(newModuleList, mod);
			}
		} else
			return;
	}
	
	private ModuleVO getRootModule(String rootModuleParentId, List moduleList) {
		for (Iterator iter = moduleList.iterator(); iter.hasNext();) {
			ModuleVO module = (ModuleVO) iter.next();
			if (module.getParent_module_id().equals(rootModuleParentId))
				return module;
			else
				continue;
		}
		return null;
	}
	
	public int modifyRolePermission(int roleId, String[] moduleIds) {
		this.getRolePermissionDAO().deleteRolePermissionByRoleId(roleId);
		List grantModuleIdsList =  new ArrayList();
		for (int i = 0; i < moduleIds.length; i++) {
			if(moduleIds[i]==null||"".equals(moduleIds[i].trim()))
				continue;
			grantModuleIdsList.add(moduleIds[i]);
		}
		this.getRolePermissionDAO().addRolePermission(roleId,grantModuleIdsList);
		return 0;
	}
	
	public List<RolePermissionVO> seachRolePermissionByRoleId(int roleId) {
		return this.getRolePermissionDAO().getRolePermissionById(roleId);
	}

	public List getAllModuleTree() {
		return this.getModuleDAO().getAllModuleTree();
	}
	// add by longlianghua
	//先删除，再插入
	public int updateRolePermission(int roleId, String[] ids) {
		List idsList = new ArrayList();
		//检查是否被删除
		RoleVO role = this.getRoleDAO().findRoleById(roleId);
		if (role == null)
			return 0;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]==null||"".equals(ids[i].trim()))
				continue;
			idsList.add(ids[i]);
		}
		if(idsList.size()==0)
			return 0;
		else
		{
			this.getRolePermissionDAO().deleteRolePermissionByRoleId(roleId);
			return this.getRolePermissionDAO().addRolePermission(roleId, idsList);
		}
	}
	
	public Pagination getAdminInfos(int roleLevel, int pageSize, int pageNum ) {
		return this.getUserDAO().listAdminInfo(roleLevel,pageSize,pageNum);
	}
	
	public int checkExistByAccount(String account) {
		return this.getUserDAO().checkExistByAccount(account);
	}
	
	//	 管理员管理
	public int addAdministrator(UserVO admin) {
		return this.getUserDAO().addAdministrator(admin);
	}
	
	public UserVO getAdministratorById(String adminId) {
		return this.getUserDAO().getAdministratorById(adminId);
	}
	
	public int modifyAdministrator(UserVO admin) {
		return this.getUserDAO().updateAdministrator(admin);
	}
	
	public int deleteAdministrator(String[] adminIds) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < adminIds.length; i++) {
			if (adminIds[i] == null || "".equals(adminIds[i].trim()))
				continue;
			list.add(adminIds[i]);
		}
		return this.getUserDAO().deleteAdministrator(list);
	}
	
	
	public List<ModuleVO> getModulesByRoleId(long role_id){
		return orderMoudle(this.rolePermissionDAO.getModulesByRoleId(role_id));
	}
	
	//获取用户SP信息
	public UserSPVO getUserSPVO(long userId){
		return userDAO.getUserSPVO(userId);
	}

	@Override
	public List<ModuleVO> doModulesQuery(ModuleVO moduleVO, int pageSize,int pageNum) {
		return moduleDAO.doModulesQuery(moduleVO,pageSize,pageNum);
	}
}

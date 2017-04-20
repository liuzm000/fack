package cn.eshore.mismp.system.web.action;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.RoleVO;
import cn.eshore.mismp.dao.domain.UserVO;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.Util;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.HotelModel;


/**
 * <p>Title: PermissionAction</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="RolePermissionAction.java.html"><i>View Source</i></a></p>
 * @author <a href="Yusm@gsta.com">Yu Songming</a>
 * @version 1.0
 * Modified at:
 * Modified by:
 */
public class PermissionAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	String errorInfo = null;
	private ModuleVO moduleVO;
	private String role_id2;
	private String actionName;

	public String listModule() {
		this.actionName = "listModule.action";
		List<ModuleVO> moduleList = this.businessSupportService.getSystemService().getAllModules(pageSize,pageNum);
		if(moduleList.size()>0) {
			for(int i=0;i<moduleList.size();i++) {
				ModuleVO module = moduleList.get(i);
				
				if(module.getModule_type()==0)
					module.setModule_type_name("非菜单模块");
				else
					if(module.getModule_type()==1)
						module.setModule_type_name("菜单模块");
					else module.setModule_type_name("未知类型模块");
				
				ModuleVO parent_module = this.businessSupportService.getSystemService().getModuleById(module.getParent_module_id());
				if(parent_module!=null)
					module.setParent_module_name(Util.nullToStr(parent_module.getModule_name()));
			}
			
		}
		
		this.getRequest().setAttribute("moduleList", moduleList);
		
		return SUCCESS;
	}
	
	public String queryModule() {
		this.actionName = "queryModule.action";
		List<ModuleVO> moduleList = this.businessSupportService.getSystemService().doModulesQuery(moduleVO, pageSize,pageNum);
		
		if(moduleList.size()>0) {
			for(int i=0;i<moduleList.size();i++) {
				ModuleVO module = moduleList.get(i);
				
				if(module.getModule_type()==0)
					module.setModule_type_name("非菜单模块");
				else
					if(module.getModule_type()==1)
						module.setModule_type_name("菜单模块");
					else module.setModule_type_name("未知类型模块");
				
				ModuleVO parent_module = this.businessSupportService.getSystemService().getModuleById(module.getParent_module_id());
				if(parent_module!=null)
					module.setParent_module_name(Util.nullToStr(parent_module.getModule_name()));
			}
			
		}
		
		this.getRequest().setAttribute("moduleList", moduleList);
		
		return SUCCESS;
	}
	
	public String goAddModule() {
		
		List<ModuleVO> moduleList = this.businessSupportService.getSystemService().getAllModules();
		this.getRequest().setAttribute("moduleList", moduleList);
		return SUCCESS;
		
	}
	
	public String addModule() {

		String module_name = null;
		String description = null;
		String parent_module_id = null;
		int module_type = 1 ;
		String module_url = null;
		
		HttpServletRequest req = this.getRequest();
		if(req.getParameter("module_name")!=null)
			module_name = req.getParameter("module_name");
		try {
			String newStr=new String(module_name.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(newStr);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(req.getParameter("description")!=null)
			description = req.getParameter("description");
		if(req.getParameter("parent_module_id")!=null)
			parent_module_id = req.getParameter("parent_module_id");
		if(req.getParameter("module_type")!=null)
			module_type = Integer.parseInt(req.getParameter("module_type"));
		if(req.getParameter("module_url")!=null)
			module_url = req.getParameter("module_url");
		
		if( module_name==null || module_name.equals("") )
			return "fail";
		if( parent_module_id==null || parent_module_id.equals("") )
			return "fail";
		
		String newModuleId = getNewModuleId(parent_module_id);
		if(newModuleId.equals("-1"))
			return "fail";
		
		ModuleVO module = new ModuleVO();
		module.setModule_id(newModuleId);
		module.setModule_name(module_name);
		module.setDescription(description);
		module.setParent_module_id(parent_module_id);
		module.setModule_type(module_type);
		module.setModule_url(module_url);
		module.setModule_order(parent_module_id+"9999");
		
		if(this.businessSupportService.getSystemService().addModule(module)>0)
			return "success";
		else 
			return "fail";
		
	}
	
	private String getNewModuleId(String parent_module_id) {
		int i = 1;
		while( i>=1 && i<=9999){
			NumberFormat nFormat=new DecimalFormat("0000");
			String newModuleId = parent_module_id+nFormat.format(i);
			ModuleVO existedModule = this.businessSupportService.getSystemService().getModuleById(newModuleId);
			if(existedModule==null) {
				return newModuleId;
			}
			else
			{
				i++;
			}
		}
		return "-1";//表示找不到合适的ID了
	}
	
	public String goModifyModule() {
		String idforEdit = null ;
		HttpServletRequest req = this.getRequest();
		if(req.getParameter("idforEdit")!=null)
			idforEdit = req.getParameter("idforEdit");
		if(idforEdit==null || idforEdit.equals(""))
			return "fail";
		
		ModuleVO module = this.businessSupportService.getSystemService().getModuleById(idforEdit);
		if(module==null)
			return "fail";
		req.setAttribute("module", module);
		
		List<ModuleVO> moduleList = this.businessSupportService.getSystemService().getAllModules(pageSize,pageNum);
		this.getRequest().setAttribute("moduleList", moduleList);
		
		return "success";
	}
	
	public String modifyModule() {
		
		String module_id = null;
		String module_name = null;
		String description = null;
		String parent_module_id = null;
		int module_type = 1 ;
		String module_url = null;
		
		HttpServletRequest req = this.getRequest();
		if(req.getParameter("module_id")!=null)
			module_id = req.getParameter("module_id");
		if(req.getParameter("module_name")!=null)
			module_name = req.getParameter("module_name");
		if(req.getParameter("description")!=null)
			description = req.getParameter("description");
		if(req.getParameter("parent_module_id")!=null)
			parent_module_id = req.getParameter("parent_module_id");
		if(req.getParameter("module_type")!=null)
			module_type = Integer.parseInt(req.getParameter("module_type"));
		if(req.getParameter("module_url")!=null)
			module_url = req.getParameter("module_url");
		
		if( module_name==null || module_name.equals("") )
			return "fail";
		if( parent_module_id==null || parent_module_id.equals("") )
			return "fail";
		
		ModuleVO module = new ModuleVO();
		module.setModule_id(module_id);
		module.setModule_name(module_name);
		module.setDescription(description);
		module.setParent_module_id(parent_module_id);
		module.setModule_type(module_type);
		module.setModule_url(module_url);
		
		if(this.businessSupportService.getSystemService().modifyModule(module)>0)
			return "success";
		else 
			return "fail";
	}
	
	public String deleteModule() {
		String[] ids =null;
		HttpServletRequest req = this.getRequest();
		if(req.getParameterValues("ids")!=null)
			ids = req.getParameterValues("ids");
		if(this.businessSupportService.getSystemService().deleteModule(ids)>0)
			return "success";
		else
			return "fail";
	}
	
	public String goOrderModule(){
		String module_id = null;
		HttpServletRequest req = this.getRequest();
		if(req.getParameter("module_id")!=null)
			module_id = req.getParameter("module_id");
		ModuleVO module = this.businessSupportService.getSystemService().getModuleById(module_id);
		List<ModuleVO> moduleList = this.businessSupportService.getSystemService().getModulesByParentModuleId(module.getParent_module_id());
		req.setAttribute("moduleList", moduleList);
		return "success";
		
	}
	
	public String orderModule() {
		String subjectlist = null;
		HttpServletRequest req = this.getRequest();
		if(req.getParameter("subjectlist")!=null)
			subjectlist = req.getParameter("subjectlist");
		String module_ids[] = subjectlist.split(",");
		for(int i=0;i<module_ids.length;i++){
			NumberFormat nFormat=new DecimalFormat("0000");
			ModuleVO module = this.getBusinessSupportService().getSystemService().getModuleById(module_ids[i].substring(0, module_ids[i].length()-4));
			String order = module.getModule_order()+nFormat.format(i+1);
			this.businessSupportService.getSystemService().modifyModuleOrder(module_ids[i], order);
		}
		return "success";
	}
	
	
	
	
	
	public String adminRoleList() {
		Pagination roleList = this.getBusinessSupportService().getSystemService().searchRole(pageSize, pageNum);
		if (roleList == null)
		{
			error("role_list_error");
			return "fail";
		}
		else {
			this.getRequest().setAttribute("roleList", roleList);
			return "success";
		}
	}
	
/*	public RoleVO role;

	public RoleVO getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String execute() {
		return "success";
	}*/

	public String goAddRole() {
		return "success";
	}

	public String addRole() {
		String roleName = null;// 角色名
		String description = null;// 描述
		HttpServletRequest req = this.getRequest();
		// if (req.getParameter("role_id") != null)
		// role_id = Integer.parseInt(req.getParameter("role_id"));
		if (req.getParameter("role_name") != null)
			roleName = req.getParameter("role_name");
		if (req.getParameter("description") != null)
			description = req.getParameter("description");
		RoleVO role = new RoleVO();
		if (null == roleName || "".equals(roleName)) {
			error("role_name_error");
			return "fail";
		}
		role.setRole_name(roleName);
		role.setDescription(description);
		if (this.getBusinessSupportService().getSystemService().getRoleCountByName(roleName) == 0) {
			if (this.getBusinessSupportService().getSystemService().addRole(role) < 1) {
				error("sys_error");
				return "fail";
			}
			return "success";
		} else {
			error("data_error");
			return "fail";
		}
	}

	public String modifyRole() {
		int roleId = -1;// 角色ID
		String roleName = null;// 角色名
		String description = null;// 描述
		HttpServletRequest req = this.getRequest();
		if (req.getParameter("role_id") != null)
			roleId = Integer.parseInt(req.getParameter("role_id"));
		if (req.getParameter("role_name") != null)
			roleName = req.getParameter("role_name");
		if (req.getParameter("description") != null)
			description = req.getParameter("description");
		RoleVO newRole = new RoleVO();
		if (roleId < 1) {
			error("role_id_error");
			return "fail";
		}
		if (null == roleName || "".equals(roleName)) {
			error("role_name_error");
			return "fail";
		}
		newRole.setRole_id(roleId);
		newRole.setRole_name(roleName);
		newRole.setDescription(description);
		
		if(this.getBusinessSupportService().getSystemService().modifyRole(newRole) > 0 ){
			return "success";
		}
		else{
			return "fail";
		}
	}

	public String deleteRoleById() {
		int roleId = -1;// 角色ID
		HttpServletRequest req = this.getRequest();
		if (req.getParameter("role_id") != null)
			roleId = Integer.parseInt(req.getParameter("role_id"));
		if (roleId < 1) {
			error("role_id_error");
			return "fail";
		}

		if (this.getBusinessSupportService().getSystemService().deleteRoleById(roleId) < 1) {
			error("sys_error");
			return "fail";
		}
		return "success";

	}

	public String deleteRoles() {
		HttpServletRequest req = this.getRequest();
		String[] roleIds = req.getParameterValues("roleIds");
		if (roleIds != null) {
			if (roleIds.length < 1)
			{
				error("role_ids_error");
				return "fail";
			}
			if (this.getBusinessSupportService().getSystemService().deleteRoleByIds(roleIds) < 1){
				error("sys_error");
				return "fail";
			}
			return "success";
		} else
		{
			error("sys_error");
			return "fail";
		}
	}

	public String findRoleDetail() {
		int roleId = -1;// 角色ID
		HttpServletRequest req = this.getRequest();
		if (req.getParameter("role_id") != null)
			roleId = Integer.parseInt(req.getParameter("role_id"));
		if (roleId < 1){
			error("role_id_error");
			return "fail";
		}
		RoleVO role;
		if ((role = this.getBusinessSupportService().getSystemService().findRoleById(roleId)) == null){
			error("data_error");
			return "fail";
		}
		req.setAttribute("role", role);
		return "success";
	}
	
	

	public String goModifyRolePermission() {
		HttpServletRequest req = this.getRequest();
		String roleId = null;
		roleId = req.getParameter("role_id");
		if (roleId == null || "".equals(roleId))
		{
			error("role_id_error");
			return "error";
		}
		RoleVO role = this.getBusinessSupportService().getSystemService()
				.findRoleById(Integer.parseInt(roleId));
		if (role == null)
		{
			error("data_error");
			return "error";
		}
		else {
			// 查询相关权限信息
			List rolePermissionList = this.getBusinessSupportService().getSystemService().seachRolePermissionByRoleId(role.getRole_id());
			// 查询所有模块信息
			List moduleList = this.getBusinessSupportService()
					.getSystemService().getAllOrderedModule();  
			//List moduleList = this.getBusinessSupportService().getSystemService().getAllModules();

			req.setAttribute("role", role);
			req.setAttribute("rolePermissionList", rolePermissionList);
			req.setAttribute("moduleList", moduleList);
			return "success";
		}
	}
	
	public String goModifyPermissionPage() {
		HttpServletRequest req = this.getRequest();
		String roleId = null;
		roleId = req.getParameter("role_id");
		if (roleId == null || "".equals(roleId))
		{
			error("role_id_error");
			return "error";
		}
		req.setAttribute("role_id", roleId);
		return "success";
	}

	public String modifyRolePermission() {
		HttpServletRequest req = this.getRequest();
		String roleId = null;
		String[] ids = null;
		roleId = req.getParameter("role_id");
		role_id2 = roleId;
		if (roleId == null || "".equals(roleId))
		{
			error("role_id_error");
			return "error";
		}
		ids = req.getParameterValues("ids");
		if (ids == null)
		{
			return INPUT;
		}

		else {
			
			int retNum = this.getBusinessSupportService().getSystemService().updateRolePermission(Integer.parseInt(roleId), ids);
			if (retNum > 0)
				return "success";
			else
			{
				error("sys_error");
				return "fail";
			}
		}
	}
	
	public String changePasswordInit(){	
		return SUCCESS;
	}
	
	public String changePassword(){		
		HttpServletRequest req = this.getRequest();
		UserVO vo = (UserVO)req.getSession().getAttribute("UserVO");
		String password = req.getParameter("password");
		if(vo.getPassword().equals(password)){
			String passwordNew = req.getParameter("passwordNew");
			vo.setPassword(passwordNew);
			int result = this.getBusinessSupportService().getSystemService().modifyAdministrator(vo);
			if(result > 0){
				errorInfo = "修改成功";
			} else {
				errorInfo = "修改失败";
			}
		} else {
			errorInfo = "修改失败。旧登录密码不正确";
		}
		return SUCCESS;
	}

	public String getRole_id2() {
		return role_id2;
	}

	public void setRole_id2(String role_id2) {
		this.role_id2 = role_id2;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	
	
	
	
	
	public String getAdminList() {
		HttpServletRequest req = this.getRequest();	
		int roleLevel = 10;
		Object level = req.getSession().getAttribute("roleLevel");
		if(level != null){
			roleLevel = Integer.parseInt(String.valueOf(level));
		}
		List adminList = this.getBusinessSupportService().getSystemService().getAdminInfos(roleLevel,pageSize, pageNum);	
		this.getRequest().setAttribute("admList", adminList);
		return "success";
	}
	
	public String goAddAdmin() {
		HttpServletRequest req = this.getRequest();
		int roleLevel = 10;
		Object level = req.getSession().getAttribute("roleLevel");
		if(level != null){
			roleLevel = Integer.parseInt(String.valueOf(level));
		}
		List roleList = new ArrayList();
		roleList = this.getBusinessSupportService().getSystemService().getRoleByLevel(roleLevel);
		this.getRequest().setAttribute("roleList", roleList);
		//2013-04-03
		List<HotelModel> hotelList=this.businessSupportService.getWqsTravelService().getHotelRestaurantList(1);//获取酒店和餐馆列表
		List<HotelModel> restaurantList=this.businessSupportService.getWqsTravelService().getHotelRestaurantList(2);//获取酒店和餐馆列表
		this.getRequest().setAttribute("hotelList", hotelList);
		this.getRequest().setAttribute("restaurantList", restaurantList);
		//获取天翼专区基地信息
//		List<ZoneTypeModel> zoneTypeList = this.getBusinessSupportService().getZoneService().listZoneType();
//		if(zoneTypeList != null && zoneTypeList.size() > 0){
//			this.getRequest().setAttribute("zoneTypeList", zoneTypeList);
//		}
		return "success";
	}
	public String addAdmin() {
		UserVO user = new UserVO();
		HttpServletRequest req = this.getRequest();
		
		String account = null;
		String password = null;
		String description = "";
		long role_id = 0;
		long zoneTypeId = 0;
		
		if (req.getParameter("account") != null)
			account = req.getParameter("account");
		if (req.getParameter("password") != null)
			password = (String) req.getParameter("password");		
		if (req.getParameter("role_id") != null
				&& !"".equals(req.getParameter("role_id")))
		role_id = Long.parseLong(req.getParameter("role_id"));
		
		if(req.getParameter("description")!=null)
			description = req.getParameter("description");
		if (null == password || "".equals(password.trim())) {
			error("password_error");
			return "fail";
		}
		if (null == account || "".equals(account.trim())) {
			error("account_error");
			return "fail";
		}
		if (role_id < 0) {
			error("role_id_error");
			return "fail";
		}
		
		user.setAccount(account);
		user.setPassword(password);
		user.setRoleId(role_id);
		user.setDescription(description);
		if (req.getParameter("zoneTypeId") != null
				&& !"".equals(req.getParameter("zoneTypeId")))
		zoneTypeId = Long.parseLong(req.getParameter("zoneTypeId"));
		user.setZoneTypeId(zoneTypeId);
		
		if (this.getBusinessSupportService().getSystemService().checkExistByAccount(account) == 0) {
			String hotelIdName=this.getRequest().getParameter("hotelId");
			String restaurantIdName=this.getRequest().getParameter("restaurantId");
			if(hotelIdName==null&&restaurantIdName==null){
				user.setHotelId("0");
				user.setHotelName(null);
			}else{
				if(hotelIdName==null){
					hotelIdName=restaurantIdName;
				}
				int index=hotelIdName.indexOf("_");
				String hotelId=hotelIdName.substring(0,index);
				user.setHotelId(hotelId);
				if(!"0".equals(hotelId)){
					user.setHotelName(hotelIdName.substring(index+1,hotelIdName.length()));
				}
			}
			if (this.getBusinessSupportService().getSystemService().addAdministrator(user) > 0)
				return "success";
			else
			{
				error("sys_error");
				return "fail";
			}
		} else
			return "fail";
	}
	
	public String goModifyAdmin() {
		HttpServletRequest req = this.getRequest();
		String adminId = null;
		adminId = this.getRequest().getParameter("administrator_id");
		if (adminId == null || adminId.equals(""))
		{
			error("admin_id_error");
			return "fail";
		}
		UserVO administrator = this.businessSupportService.getSystemService().getAdministratorById(adminId);
		if (administrator == null)
			return "fail";
		
		int roleLevel = 10;
		Object level = req.getSession().getAttribute("roleLevel");
		if(level != null){
			roleLevel = Integer.parseInt(String.valueOf(level));
		}
		List roleList = new ArrayList();
		roleList = this.getBusinessSupportService().getSystemService().getRoleByLevel(roleLevel);
		this.getRequest().setAttribute("roleList", roleList);
		this.getRequest().setAttribute("administrator", administrator);
		//获取天翼专区基地信息
//		List<ZoneTypeModel> zoneTypeList = this.getBusinessSupportService().getZoneService().listZoneType();
//		if(zoneTypeList != null && zoneTypeList.size() > 0){
//			this.getRequest().setAttribute("zoneTypeList", zoneTypeList);
//		}
		return "success";	
	}
	
	public String modifyAdmin() {
		
		HttpServletRequest req = this.getRequest();
		int administrator_id = -1;
		
		int role_id = 0;
		String description = "";
		long zoneTypeId = 0;
	
		if (req.getParameter("administrator_id") != null) {
			administrator_id = Integer.parseInt(req.getParameter("administrator_id"));
			this.getRequest().setAttribute("administrator_id", administrator_id);
		}
			
		if (req.getParameter("role_id") != null)
			role_id = Integer.parseInt(req.getParameter("role_id"));
		if (req.getParameter("description") != null)
			description = req.getParameter("description");
		if (req.getParameter("zoneTypeId") != null
				&& !"".equals(req.getParameter("zoneTypeId")))
		zoneTypeId = Long.parseLong(req.getParameter("zoneTypeId"));
		UserVO user = this.getBusinessSupportService().getSystemService().getAdministratorById(""+administrator_id);
		
		if(user == null){
			error("sys_error");
			return "fail";
		}
		else
		{
			user.setRoleId(role_id);
			user.setDescription(description);
			user.setZoneTypeId(zoneTypeId);
			if (this.getBusinessSupportService().getSystemService().modifyAdministrator(user) > 0)
				return "success";
			else
			{
				error("sys_error");
				return "fail";
			}
		}
	}
	public String deleteAdmins() {
		
		HttpServletRequest req = this.getRequest();
		String adminIds[] = new String[] {};
		adminIds = req.getParameterValues("checkedIds");
		if (adminIds != null) {
			if (this.getBusinessSupportService().getSystemService().deleteAdministrator(adminIds) > 0)
				return "success";
			else
			{
				error("sys_error");
				return "fail";
			}
		}
		error("admin_id_null_error");
		return "fail";
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public ModuleVO getModuleVO() {
		return moduleVO;
	}

	public void setModuleVO(ModuleVO moduleVO) {
		this.moduleVO = moduleVO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

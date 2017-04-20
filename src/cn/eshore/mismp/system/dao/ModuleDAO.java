package cn.eshore.mismp.system.dao;

import java.util.ArrayList;
import java.util.List;

import cn.eshore.mismp.dao.BaseDAO;
import cn.eshore.mismp.dao.domain.ModuleVO;


public interface ModuleDAO extends BaseDAO {

	//	 分页模块
	public List<ModuleVO> getAllModules(int pagesie,int pagenum);
	
	//	所有模块
	public List<ModuleVO> getAllModules();
	
	//	 根据module_id得到Module
	public ModuleVO getModuleById(String module_id);
	
	//	 添加模块
	public int addModule(ModuleVO module);
	
	//	 修改模块
	public int updateModule(ModuleVO module);
	
	//	 批量删除
	public int deleteModule(ArrayList module_ids);
	
	//	 根据parent_module_id得到Module集合
	public List<ModuleVO> getModulesByParentModuleId(String parent_module_id);

	// 修改module的顺序值
	public int modifyModuleOrder(String module_id, String order);
	
	//	 按树结构查询模块信息
	public List getAllModuleTree();
	
	// 模块查询
	public List<ModuleVO> doModulesQuery(ModuleVO moduleVO, int pageSize,
			int pageNum);
	
}

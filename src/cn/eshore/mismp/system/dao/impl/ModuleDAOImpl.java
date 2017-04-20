package cn.eshore.mismp.system.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.axis.utils.StringUtils;

import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.rowmapper.ModuleRowMapper;
import cn.eshore.mismp.dao.impl.BaseDAOJdbcTemplate;
import cn.eshore.mismp.system.dao.ModuleDAO;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.Util;



public class ModuleDAOImpl extends BaseDAOJdbcTemplate implements ModuleDAO {

	public List<ModuleVO> getAllModules(int pageSize,int pageNum) {
		String sql = "select module_id,module_name,description,parent_module_id,module_type,module_url,module_order from t_mismp_module  where active = '1' order by module_order ";
		String sqlcount = "select count(*) as c from t_mismp_module where active = '1'";
		final Object[] params = new Object[] {};
		return this.list(sql, sqlcount, params, new ModuleRowMapper(), pageSize,
				pageNum);
	}
	
	public List<ModuleVO> getAllModules(){
		String sql = "select module_id,module_name,description,parent_module_id,module_type,module_url,module_order from t_mismp_module  where  active = '1' order by module_order";
		final Object[] params = new Object[] {};
		return this.query(sql,  params, new ModuleRowMapper());
	}
	
	public ModuleVO getModuleById(String module_id) {
		String sql = "select module_id,module_name,description,parent_module_id,module_type,module_url,module_order from t_mismp_module  where module_id=? and active = '1' ";
		
		final Object[] params = new Object[] { module_id };
		List list = this.query(sql, params, new ModuleRowMapper());
		if (list == null || list.size() == 0)
			return null;
		ModuleVO module = (ModuleVO) list.get(0);
		return module;
	}
	
	public int addModule(ModuleVO module) {
		String sql = "insert into t_mismp_module(module_id,module_name,description,parent_module_id,module_type,module_url,module_order)"
			+ " values(?,?,?,?,?,?,?)";
		
		final Object[] params = new Object[] { Util.nullToStr(module.getModule_id()),
											   Util.nullToStr(module.getModule_name()),
											   Util.nullToStr(module.getDescription()),
											   Util.nullToStr(module.getParent_module_id()),
											   module.getModule_type(),
											   Util.nullToStr(module.getModule_url()),
											   Util.nullToStr(module.getModule_order())};
		return this.update(sql, params);
	}
	
	public int updateModule(ModuleVO module) {
		String sql = "update t_mismp_module set module_name=?,description=?,parent_module_id=?,module_type=?,module_url=? where module_id=?";
			
		final Object[] params = new Object[] { Util.nullToStr(module.getModule_name()),
										   Util.nullToStr(module.getDescription()),
										   Util.nullToStr(module.getParent_module_id()),
										   module.getModule_type(),
										   Util.nullToStr(module.getModule_url()),
										   Util.nullToStr(module.getModule_id())
										 };
		return this.update(sql, params);
	}
	
	public int deleteModule(ArrayList module_ids) {
		String sql = "delete from t_mismp_module";
		final Object[] params;
 		if(module_ids.size()>0)
		{
			sql += " where ";
			params = new Object[module_ids.size()];
			for(int i=0;i<module_ids.size();i++){
				if(i!=(module_ids.size()-1)){
					sql += " module_id=? or ";
				}
				if(i==(module_ids.size()-1)){
					sql += " module_id=? ";
				}
				params[i] = module_ids.get(i);
			}
			return this.update(sql, params);
		}
 		return 0;
	}
	
	public List<ModuleVO> getModulesByParentModuleId(String parent_module_id) {
		String sql = "select module_id,module_name,description,parent_module_id,module_type,module_url,module_order from t_mismp_module where parent_module_id=? and active = '1'order by module_order";
		final Object[] params = new Object[] { parent_module_id };
		List list = this.query(sql, params, new ModuleRowMapper());
		return list;
	}
	
	public int modifyModuleOrder(String module_id,String order) {
		String sql = "update t_mismp_module set module_order=? where module_id=?";
		final Object[] params = new Object[] { Util.nullToStr(order),
											Util.nullToStr(module_id)
				 };
		return this.update(sql, params);
	}
	
	public List getAllModuleTree() {
		String sql = "select module_id,module_name,description,parent_module_id," +
				"module_type,module_url,module_order " +
				"from t_mismp_module connect by " +
				"parent_module_id =prior module_id where active = '1' order by module_id,module_order";
		return this.query(sql, new ModuleRowMapper());
	}

	@Override
	public List<ModuleVO> doModulesQuery(ModuleVO moduleVO, int pageSize,
			int pageNum) {
		String fromSql = "from t_mismp_module where 1 =1  and active = '1'";
		if(StringUtils.isEmpty(moduleVO.getModule_name())) {
			fromSql = fromSql +"and module_name like%"+moduleVO.getModule_name()+"%"; 
		}
		if(StringUtils.isEmpty(moduleVO.getModule_name())) {
			fromSql = fromSql +"and description like%"+moduleVO.getDescription()+"%"; 
		}
		if(StringUtils.isEmpty(moduleVO.getModule_name())) {
			fromSql = fromSql +"and parent_module_id in ( select module_id from t_mismp_module where module_name like %"+moduleVO.getParent_module_name()+"%";
		}
		String sql = "select module_id,module_name,description,parent_module_id,module_type,module_url,module_order" + fromSql;
		String sqlcount = "select count(1) as c" + fromSql;
		String orderSql = "order by module_order";
		final Object[] params = new Object[] {};
		return this.list(sql+orderSql, sqlcount, params, new ModuleRowMapper(), pageSize,pageNum);
	}
}

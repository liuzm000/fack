package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.util.Util;


public class ModuleReadOnlyRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ModuleVO module = new ModuleVO();
		module.setModule_id(Util.nullToStr(rs.getString("module_id")));
		module.setModule_name(Util.nullToStr(rs.getString("module_name")));
		module.setDescription(Util.nullToStr(rs.getString("description")));
		module.setParent_module_id(Util.nullToStr(rs.getString("parent_module_id")));
		module.setModule_type(rs.getInt("module_type"));
		module.setModule_url(Util.nullToStr(rs.getString("module_url")));
		module.setModule_order(Util.nullToStr(rs.getString("module_order")));
		module.setReadOnly(rs.getInt("read_only"));
		return module;
	}

}

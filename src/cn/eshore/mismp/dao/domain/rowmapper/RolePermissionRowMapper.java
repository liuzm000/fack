package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.RolePermissionVO;
import cn.eshore.mismp.util.Util;



public class RolePermissionRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		RolePermissionVO rolePermission = new RolePermissionVO();
		
		rolePermission.setPermission_id(rs.getInt("permission_id"));
		rolePermission.setRole_id(rs.getInt("role_id"));
		rolePermission.setModule_id(Util.nullToStr(rs.getString("module_id")));
		rolePermission.setReadOnly(rs.getInt("read_only"));
		return rolePermission;
		
	}

}

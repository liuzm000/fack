package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.RoleVO;
import cn.eshore.mismp.util.Util;



public class RoleRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		RoleVO role = new RoleVO();
		
		role.setRole_id(rs.getInt("role_id"));
		role.setRole_name(Util.nullToStr(rs.getString("role_name")));
		role.setDescription(Util.nullToStr(rs.getString("description")));
		role.setRole_level(rs.getInt("role_level"));
		
		return role;
	}

}

package cn.eshore.mismp.spmanager.dao.rowmapper;

/**
 * @author OYK
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.spmanager.dao.model.Sp;


public class SpRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Sp sp = new Sp();

		
		sp.setSp_id(rs.getInt("sp_id"));
		sp.setSp_code(rs.getString("sp_code"));
		sp.setSp_type(rs.getInt("sp_type"));
		sp.setSp_local(rs.getString("sp_local"));
		sp.setSp_name(rs.getString("sp_name"));
		sp.setSp_desc(rs.getString("sp_desc"));
		sp.setSp_addr(rs.getString("sp_addr"));
		sp.setSp_person(rs.getString("sp_person"));
		sp.setSp_phone(rs.getString("sp_phone"));
		sp.setSp_createtime(rs.getDate("sp_createtime"));
		
		return sp;
	}

}

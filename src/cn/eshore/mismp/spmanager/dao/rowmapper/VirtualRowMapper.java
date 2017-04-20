package cn.eshore.mismp.spmanager.dao.rowmapper;

/**
 * @author OYK
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.spmanager.dao.model.Virtual;


public class VirtualRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Virtual vir = new Virtual();
		vir.setVir_id(rs.getInt("vir_id"));
		vir.setVir_desc(rs.getString("vir_desc"));
		vir.setVir_name(rs.getString("vir_name"));	
		return vir;
	}

}

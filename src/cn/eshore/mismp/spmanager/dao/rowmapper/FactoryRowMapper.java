package cn.eshore.mismp.spmanager.dao.rowmapper;

/**
 * @author OYK
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.spmanager.dao.model.Factory;

public class FactoryRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Factory fac = new Factory();
		fac.setFac_id(rs.getInt("fac_id"));
		fac.setFac_desc(rs.getString("fac_desc"));
		fac.setFac_addr(rs.getString("fac_addr"));
		fac.setFac_name(rs.getString("fac_name"));
		fac.setFac_phone(rs.getString("fac_phone"));
		
		return fac;
	}

}

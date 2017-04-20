package cn.eshore.mismp.spmanager.dao.rowmapper;

/**
 * @author OYK
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.spmanager.dao.model.Prov;


public class ProvRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Prov prov = new Prov();
		prov.setId(rs.getString("id"));
		prov.setName(rs.getString("name"));		
		
		return prov;
	}

}

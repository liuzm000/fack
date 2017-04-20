package cn.eshore.mismp.spmanager.dao.rowmapper;

/**
 * @author OYK
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.spmanager.dao.model.SoftVersion;

public class SoftVersionRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		SoftVersion sv = new SoftVersion();
		sv.setSt_id(rs.getInt("st_id"));
		sv.setSv_id(rs.getInt("sv_id"));		
		return sv;
	}

}

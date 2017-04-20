package cn.eshore.mismp.spmanager.dao.rowmapper;

/**
 * @author OYK
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.spmanager.dao.model.SoftAudit;

public class SoftAuditRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		SoftAudit sa = new SoftAudit();
		sa.setAudit_id(rs.getInt("audit_id"));
		sa.setSoft_version(rs.getString("soft_version"));
		sa.setSoft_name(rs.getString("soft_name"));
		sa.setSoft_forceupdate(rs.getInt("soft_forceupdate"));
		sa.setSoft_filepath(rs.getString("soft_filepath"));
		sa.setCreate_time(rs.getDate("create_time"));
		sa.setAudit_status(rs.getInt("audit_status"));
		sa.setSoft_desc(rs.getString("soft_desc"));
		sa.setAudit_time(rs.getDate("audit_time"));
		sa.setAudit_by(rs.getString("audit_by"));
		sa.setSv_id(rs.getInt("sv_id"));
		sa.setAudit_desc(rs.getString("audit_desc"));
		return sa;
	}

}

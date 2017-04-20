package cn.eshore.mismp.spmanager.dao.rowmapper;

/**
 * @author OYK
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.spmanager.dao.model.Terminal;


public class TerminalRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Terminal ter = new Terminal();
		ter.setTer_id(rs.getInt("ter_id"));
		ter.setTer_code(rs.getString("ter_code"));
		ter.setFac_id(rs.getInt("fac_id"));
		ter.setVir_id(rs.getInt("vir_id"));
		ter.setTer_java(rs.getInt("ter_java"));
		ter.setTer_mms(rs.getInt("ter_mms"));
		ter.setTer_wap(rs.getInt("ter_wap"));
		ter.setTer_evdo(rs.getInt("ter_evdo"));
		ter.setTer_plat(rs.getString("ter_plat"));
		ter.setTer_picpath(rs.getString("ter_picpath"));
		ter.setTer_des(rs.getString("ter_des"));
		ter.setTer_name(rs.getString("ter_name"));
		ter.setUserAgent(rs.getString("TER_USERAGENT"));
		
		return ter;
	}

}

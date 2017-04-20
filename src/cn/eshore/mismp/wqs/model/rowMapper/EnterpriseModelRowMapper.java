package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.EnterpriseModel;

public class EnterpriseModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(EnterpriseModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		EnterpriseModel vo = new EnterpriseModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setIcon(rs.getString("ICON"));
			vo.setHtmlPath(rs.getString("HTML_PATH"));
			vo.setHtmlValue(rs.getClob("HTML_VALUE"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

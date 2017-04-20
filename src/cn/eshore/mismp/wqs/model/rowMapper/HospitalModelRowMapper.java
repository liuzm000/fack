package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.HospitalModel;

public class HospitalModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(HospitalModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		HospitalModel vo = new HospitalModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setAddress(rs.getString("ADDRESS"));
			vo.setTele1(rs.getString("TELE1"));
			vo.setTele2(rs.getString("TELE2"));
			vo.setTele3(rs.getString("TELE3"));
			vo.setHtmlFile(rs.getString("HTML_FILE"));
			vo.setHtmlValue(rs.getClob("HTML_VALUE"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

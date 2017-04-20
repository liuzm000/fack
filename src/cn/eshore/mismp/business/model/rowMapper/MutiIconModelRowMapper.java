package cn.eshore.mismp.business.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.business.model.MutiIconModel;

public class MutiIconModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(MutiIconModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		MutiIconModel vo = new MutiIconModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setFilePath(rs.getString("FILE_PATH"));
			vo.setForId(rs.getLong("FOR_ID"));
			vo.setDesc(rs.getString("DESCRIPT"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

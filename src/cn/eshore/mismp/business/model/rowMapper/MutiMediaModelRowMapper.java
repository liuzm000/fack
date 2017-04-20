package cn.eshore.mismp.business.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.business.model.MutiMediaModel;

public class MutiMediaModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(MutiMediaModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		MutiMediaModel vo = new MutiMediaModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setIcon(rs.getString("ICON"));
			vo.setTitle(rs.getString("NAME"));
			vo.setDesc(rs.getString("DESCRIPT"));
			vo.setFilePath(rs.getString("FILE_PATH"));
			vo.setForId(rs.getLong("FOR_ID"));
			vo.setPs(rs.getString("PS"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

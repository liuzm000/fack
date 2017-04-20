package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.TownVideoModel;

public class TownVideoModelRowMapper implements RowMapper {
	protected final transient Logger log = Logger.getLogger(TownVideoModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		TownVideoModel vo = new TownVideoModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setFileName(rs.getString("FILE_NAME"));
			vo.setFilePath(rs.getString("FILE_PATH"));
			vo.setAuthor(rs.getString("AUTHOR"));
			vo.setCreateTime(rs.getDate("CREATE_TIME").toString());
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

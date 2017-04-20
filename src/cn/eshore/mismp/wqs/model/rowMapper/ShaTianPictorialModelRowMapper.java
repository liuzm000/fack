package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.ShaTianPictorialModel;

public class ShaTianPictorialModelRowMapper implements RowMapper {
	
	protected final transient Logger log = Logger.getLogger(ShaTianPictorialModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		ShaTianPictorialModel vo = new ShaTianPictorialModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setDescript(rs.getString("DESCRIPT"));
			vo.setFileName(rs.getString("FILENAME"));
			vo.setFilePath(rs.getString("FILEPATH"));
			vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("CREATE_TIME")));
			vo.setTypeId(rs.getLong("TYPE_ID"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.EcoTourismPictorialModel;

public class EcoTourismPictorialModelRowMapper implements RowMapper{

	protected final transient Logger log = Logger.getLogger(EcoTourismPictorialModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		EcoTourismPictorialModel vo = new EcoTourismPictorialModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setDescript(rs.getString("DESCRIPT"));
			vo.setFileName(rs.getString("FILE_NAME"));
			vo.setFilePath(rs.getString("FILE_PATH"));
			vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("CREATE_TIME")));
			vo.setTypeId(rs.getLong("TYPE_ID"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

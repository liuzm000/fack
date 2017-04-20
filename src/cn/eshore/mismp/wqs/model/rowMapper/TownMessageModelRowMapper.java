package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.TownMessageModel;

public class TownMessageModelRowMapper  implements RowMapper{

	protected final transient Logger log = Logger.getLogger(TownMessageModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		TownMessageModel vo = new TownMessageModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setUnit(rs.getString("UNIT"));
			vo.setCreateTime(rs.getDate("CREATETIME").toString());
			vo.setFileUrl(rs.getString("FILE_URL"));
			vo.setContent(rs.getClob("html_value"));
			vo.setTypeId(rs.getString("TYPE_ID"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

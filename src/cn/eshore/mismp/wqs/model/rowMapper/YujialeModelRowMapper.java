package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.YujialeModel;

public class YujialeModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(YujialeModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		YujialeModel vo = new YujialeModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setIcons(rs.getString("ICONS"));
			vo.setDesc(rs.getString("HTML_INFO"));
			vo.setContent(rs.getClob("HTML_VALUE"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

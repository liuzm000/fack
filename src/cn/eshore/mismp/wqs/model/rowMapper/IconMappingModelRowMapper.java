package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.IconMappingModel;

public class IconMappingModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(IconMappingModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		IconMappingModel vo = new IconMappingModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setPath(rs.getString("PATH"));
			vo.setForeignId(rs.getLong("HOTEL_ID"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

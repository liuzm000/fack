package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.BeautifulCountryVideoModel;

public class BeautifulCountryVideoModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(BeautifulCountryVideoModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		BeautifulCountryVideoModel vo = new BeautifulCountryVideoModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setIconName(rs.getString("ICON_NAME"));
			vo.setIconPath(rs.getString("ICON_PATH"));
			vo.setVideoName(rs.getString("VIDEO_NAME"));
			vo.setVideoPath(rs.getString("VIDEO_PATH"));
			vo.setCountryID(rs.getLong("VIDEO_ID"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

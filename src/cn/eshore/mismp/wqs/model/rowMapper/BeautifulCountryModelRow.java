package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.wqs.model.BeautifulCountryModel;


public class BeautifulCountryModelRow  implements RowMapper {
	protected final transient Logger log = Logger.getLogger(BeautifulCountryModelRow.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		BeautifulCountryModel vo = new BeautifulCountryModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setIcon(rs.getString("ICON"));
			vo.setDescript(rs.getString("DESCRIPT"));
			vo.setFileUrl(rs.getString("FILE_URL"));
			vo.setVideoId(rs.getLong("VIDEO_ID"));
			vo.setVideoIconPaths(rs.getString("VIDEO_ICON_PATHS"));
			vo.setVideoPaths(rs.getString("VIDEO_PATHS"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

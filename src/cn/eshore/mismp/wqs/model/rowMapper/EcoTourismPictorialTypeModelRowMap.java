package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.EcoTourismPictorialTypeModel;

public class EcoTourismPictorialTypeModelRowMap implements RowMapper {
	protected final transient Logger log = Logger.getLogger(EcoTourismPictorialTypeModelRowMap.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		EcoTourismPictorialTypeModel vo = new EcoTourismPictorialTypeModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setTypeName(rs.getString("TYPENAME"));
			vo.setIconName(rs.getString("ICON_NAME"));
			vo.setIconPath(rs.getString("ICON_PATH"));
			vo.setIsSkip(rs.getString("ISSKIP"));
			vo.setInfoHtml(rs.getString("INFO"));
			vo.setFilePaths(rs.getString("FILE_PATHS"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

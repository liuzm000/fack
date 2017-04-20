package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.ShaTianPictorialTypeModel;

public class ShaTianPictorialTypePicModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(ShaTianPictorialTypePicModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		ShaTianPictorialTypeModel vo = new ShaTianPictorialTypeModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setTypeName(rs.getString("TYPENAME"));
			vo.setIconName(rs.getString("ICON_NAME"));
			vo.setIconPath(rs.getString("ICON_PATH"));
			vo.setTypeDesc(rs.getString("TYPE_DESC"));
			vo.setFilePaths(rs.getString("FILEPATHS"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

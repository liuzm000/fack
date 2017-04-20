package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.TeruserBehaviorVO;
import cn.eshore.mismp.util.Util;

public class TeruserBehaviorRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		TeruserBehaviorVO teruserBehaviorVO = new TeruserBehaviorVO();
		teruserBehaviorVO.setId(rs.getLong("ID"));
		teruserBehaviorVO.setClassId(Util.nullToStr(rs.getString("CLASS_ID")));
		teruserBehaviorVO.setStartime(rs.getDate("START_TIME"));
		teruserBehaviorVO.setLast(rs.getInt("LAST"));
		teruserBehaviorVO.setPlatformId(Util.nullToStr(rs.getString("PLATFORM_ID")));
		teruserBehaviorVO.setAppVersion(Util.nullToStr(rs.getString("APP_VERSION")));
		teruserBehaviorVO.setPhoneNum(Util.nullToStr(rs.getString("PHONE_NUM")));
		
		return teruserBehaviorVO;
	}
}
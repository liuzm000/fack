package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.DownloadStatVO;
import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.util.Util;

public class DownloadStatRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		DownloadStatVO vo = new DownloadStatVO();
		vo.setSoftTypeId(Util.nullToStr(rs.getString("ST_ID")));
		vo.setDownloadCount(Util.nullToStr(rs.getString("downloadcount")));
		vo.setDownloadPhoneCount(Util.nullToStr(rs.getString("downloadphone")));
		return vo;
	}

}
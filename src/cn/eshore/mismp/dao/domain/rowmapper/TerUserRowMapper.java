package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.RoleVO;
import cn.eshore.mismp.dao.domain.TerUserVO;
import cn.eshore.mismp.util.Util;

public class TerUserRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		TerUserVO terUserVO = new TerUserVO();
		terUserVO.setId(rs.getLong("TU_ID"));
		terUserVO.setPhoneNum(Util.nullToStr(rs.getString("TU_PHONENUM")));
		terUserVO.setImsi(Util.nullToStr(rs.getString("TU_IMSI")));
		terUserVO.setEsn(Util.nullToStr(rs.getString("TU_ESN")));
		terUserVO.setTerId(Util.nullToStr(rs.getString("TER_ID")));
		long createTime = rs.getTimestamp("TU_CREATETIME").getTime();
		terUserVO.setCreateTime((new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new java.util.Date(createTime)));
		return terUserVO;
	}
}
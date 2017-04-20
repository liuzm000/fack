package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.SPVO;
import cn.eshore.mismp.dao.domain.UserVO;
import cn.eshore.mismp.util.Util;

public class SPVORowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		SPVO vo = new SPVO();
		vo.setId(rs.getLong("SP_ID"));
		vo.setCode(Util.nullToStr(rs.getString("SP_CODE")));
		vo.setType(rs.getInt("SP_TYPE"));
		vo.setLocal(Util.nullToStr(rs.getString("SP_LOCAL")));
		vo.setName(Util.nullToStr(rs.getString("SP_NAME")));
		vo.setDesc(Util.nullToStr(rs.getString("SP_DESC")));
		vo.setAddr(Util.nullToStr(rs.getString("SP_ADDR")));
		vo.setPerson(Util.nullToStr(rs.getString("SP_PERSON")));
		vo.setPhone(Util.nullToStr(rs.getString("SP_PHONE")));
		vo.setCreatetime(Util.nullToStr(rs.getString("SP_CREATETIME")));
		return vo;
	}

}


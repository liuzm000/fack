package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.TerUserGroupTreeVO;
import cn.eshore.mismp.util.Util;

public class TerUserGroupTreeRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		TerUserGroupTreeVO ugtVO = new TerUserGroupTreeVO();
		ugtVO.setUgtId(Util.nullToStr(rs.getString("UGT_ID")));
		ugtVO.setUgtName(Util.nullToStr(rs.getString("UGT_NAME")));
		ugtVO.setUgtDesc(Util.nullToStr(rs.getString("UGT_DESC")));
		ugtVO.setUgtParentId(Util.nullToStr(rs.getString("UGT_PARENT_ID")));
		ugtVO.setUgtType(rs.getInt("UGT_TYPE"));
		ugtVO.setUgtUrl(Util.nullToStr(rs.getString("UGT_URL")));
		ugtVO.setUgtOrder(Util.nullToStr(rs.getString("UGT_ORDER")));
		return ugtVO;
	}

}

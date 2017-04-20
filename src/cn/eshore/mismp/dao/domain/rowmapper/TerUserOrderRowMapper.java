package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.SPVO;
import cn.eshore.mismp.dao.domain.TerUserOrderVO;
import cn.eshore.mismp.util.Util;

public class TerUserOrderRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		TerUserOrderVO vo = new TerUserOrderVO();
		vo.setId(rs.getLong("TO_ID"));
		vo.setProId(rs.getLong("PRO_ID"));
		vo.setTuId(rs.getLong("TU_ID"));
		vo.setStatus(rs.getInt("TO_STATUS"));
		vo.setCreateTime(rs.getString("TO_CREATETIME"));
		vo.setPhoneNum(rs.getString("TU_PHONENUM"));
		vo.setTerId(rs.getLong("TER_ID"));
		vo.setProName(rs.getString("PRO_NAME"));
		return vo;
	}
}

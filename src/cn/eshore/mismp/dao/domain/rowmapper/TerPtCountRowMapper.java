package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.dao.domain.TerPtStatVO;


public class TerPtCountRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		TerPtStatVO terPtCountVO = new TerPtStatVO();
		terPtCountVO.setCount(rs.getInt("TPC_APP_COUNT"));
		terPtCountVO.setPtId(rs.getLong("PT_ID"));
		terPtCountVO.setTerId(rs.getLong("TER_ID"));
		return terPtCountVO;
	}

}
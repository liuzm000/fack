package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.StreamingNumberVO;

public class StreamingNumberRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		StreamingNumberVO streamingNumber = new StreamingNumberVO();
		streamingNumber.setSeg1(rs.getString("SEG_1"));
		streamingNumber.setSeg2(rs.getString("SEG_2"));
		streamingNumber.setSeg3(rs.getString("SEG_3"));
		streamingNumber.setSeg4(rs.getString("SEG_4"));
		return streamingNumber;
	}

}

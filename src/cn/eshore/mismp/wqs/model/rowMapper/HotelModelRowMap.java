package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.HotelModel;

public class HotelModelRowMap implements RowMapper {

	protected final transient Logger log = Logger.getLogger(HotelModelRowMap.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		HotelModel vo = new HotelModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setTele(rs.getString("TELE"));
			vo.setAddr(rs.getString("ADDR"));
			vo.setHtmlInfo(rs.getString("HTML_INFO"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

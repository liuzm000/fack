package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.RoomTypeModel;

public class RoomTypeModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(RoomTypeModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		RoomTypeModel vo = new RoomTypeModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setHotelid(rs.getLong("HOTELID"));
			vo.setPrice(rs.getString("PRICE"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

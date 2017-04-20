package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.HotelOrderDetailModel;

public class HotelOrderDetailModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(HotelOrderDetailModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		HotelOrderDetailModel vo = new HotelOrderDetailModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setTypeid(rs.getLong("TYPEID"));
			vo.setNum(rs.getLong("NUM"));
			vo.setOrderid(rs.getLong("ORDERID"));
			try {
				vo.setTypeName(rs.getString("TYPENAME"));
				vo.setPrice(rs.getDouble("PRICE"));
			} catch (Exception e) {
				this.log.error(e.getMessage());
			}

			try {
				vo.setRoomName(rs.getString("ROOM_NAME"));
				vo.setRoomPrice(rs.getDouble("ROOM_PRICE"));
				vo.setRoomIsvalid(rs.getString("ROOM_ISVALID"));
			} catch (Exception e) {
				this.log.error(e.getMessage());
			}
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

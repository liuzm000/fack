package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.HotelOrderModel;

public class HotelOrderModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(HotelOrderModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		HotelOrderModel vo = new HotelOrderModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setPhoneNumber(rs.getString("PHONE"));
			vo.setIntime(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("IN_TIME")));
			vo.setOuttime(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("OUT_TIME")));
			vo.setHotelid(rs.getString("HOTELID"));
			try {
				vo.setHotelName(rs.getString("DAYS"));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			vo.setGender(rs.getString("GENDER"));
			vo.setIsValid(rs.getString("ISVALID"));
			try {
				vo.setHotelName(rs.getString("HOTEL_NAME"));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.RestaurantOrderModel;

public class RestaurantOrderModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(RestaurantOrderModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		RestaurantOrderModel vo = new RestaurantOrderModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setPhoneNumber(rs.getString("PHONENUMBER"));
			vo.setNum(rs.getString("NUM"));
			vo.setRestTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rs.getTimestamp("REST_TIME")));
			vo.setPlace(rs.getString("PLACE"));
			vo.setGender(rs.getString("GENDER"));
			try {
				vo.setRestName(rs.getString("REST_NAME"));
				vo.setRestTele(rs.getString("REST_TELE"));
				vo.setRestAddr(rs.getString("REST_ADDR"));
			} catch (Exception e) {
				this.log.error(e.getMessage());
			}
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

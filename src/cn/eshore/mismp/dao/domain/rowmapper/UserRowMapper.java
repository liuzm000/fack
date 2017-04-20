package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.UserVO;
import cn.eshore.mismp.util.Util;



public class UserRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		UserVO user = new UserVO();
		
		user.setUserId(rs.getString("user_id"));
		user.setAccount(Util.nullToStr(rs.getString("account")));
		user.setPassword(Util.nullToStr(rs.getString("password")));
		user.setDescription(Util.nullToStr(rs.getString("description")));
		user.setRoleId(rs.getLong("role_id"));
		user.setRoleName(Util.nullToStr(rs.getString("role_name")));
		user.setZoneTypeId(rs.getLong("zone_type_id"));
		user.setHotelId (Util.nullToStr(rs.getString("hotel_id")));
		user.setHotelName(Util.nullToStr(rs.getString("hotel_name")));
		try{
			user.setZoneName(rs.getString("zone_name"));
		}catch(Exception e){
			
		}
		return user;
	}

}

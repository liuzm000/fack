package cn.eshore.mismp.spmanager.dao.rowmapper;

/**
 * @author OYK
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.spmanager.dao.model.SoftType;

public class SoftTypeRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		SoftType st = new SoftType();
		st.setSt_id(rs.getInt("st_id"));
		st.setSt_name(rs.getString("st_name"));
		st.setSt_desc(rs.getString("st_desc"));
		st.setPro_id(rs.getInt("pro_id"));
		st.setSt_createtime(rs.getDate("st_createtime"));
		st.setSt_icon(rs.getString("st_icon"));
		st.setSt_icon_path(rs.getString("st_icon_path"));
		st.setSt_msg_push(rs.getString("st_msg_push"));
		st.setSt_eng_name(rs.getString("st_eng_name"));
			
		return st;
	}

}

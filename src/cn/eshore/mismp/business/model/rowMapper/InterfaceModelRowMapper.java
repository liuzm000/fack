package cn.eshore.mismp.business.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.business.model.InterfaceModel;

public class InterfaceModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(InterfaceModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		InterfaceModel vo = new InterfaceModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setInterNum(rs.getString("INTERNUM"));
			vo.setParentInterNum(rs.getString("PARENT_INTERNUM"));
			vo.setInterName(rs.getString("INTERNAME"));
			vo.setDescript(rs.getString("DESCRIPT"));
			vo.setActive(rs.getString("ISACTIVE"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

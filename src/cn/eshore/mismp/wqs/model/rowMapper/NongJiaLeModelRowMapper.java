package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.wqs.model.NongJiaLeModel;

public class NongJiaLeModelRowMapper implements RowMapper {
	protected final transient Logger log = Logger.getLogger(NongJiaLeModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		NongJiaLeModel vo = new NongJiaLeModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setAddr(rs.getString("ADDR"));
			vo.setTele(rs.getString("TELE"));
			vo.setIsValid(rs.getString("ISVALID"));
			vo.setType(rs.getString("TYPE"));
			vo.setOrders(rs.getString("ORDERS"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

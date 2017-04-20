package cn.eshore.mismp.business.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.business.model.PubModel;

public class PubModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(PubModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		PubModel vo = new PubModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setForId(rs.getLong("FOR_ID"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setIcon(rs.getString("ICON"));
			vo.setFilePath(rs.getString("FILE_PATH"));
			vo.setIconHD(rs.getString("ICONHD"));
			vo.setIcons(rs.getString("ICONS"));
			vo.setDescript(rs.getString("DESCRIPT"));
			vo.setDetail(rs.getString("DETAIL"));
			vo.setHtmlValue(rs.getClob("HTML_VALUE"));
			vo.setAddr(rs.getString("ADDR"));
			vo.setTele(rs.getString("TELE"));
			vo.setUnit(rs.getString("UNIT"));
			vo.setAuthor(rs.getString("AUTHOR"));
			vo.setTime(rs.getDate("TIME").toString());
			vo.setFlag(rs.getString("FLAG"));
			vo.setIsActive(rs.getString("ISACTIVE"));
			vo.setValue1(rs.getString("VALUE1"));
			vo.setValue2(rs.getString("VALUE2"));
			vo.setInterNum(rs.getString("INTERNUM"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

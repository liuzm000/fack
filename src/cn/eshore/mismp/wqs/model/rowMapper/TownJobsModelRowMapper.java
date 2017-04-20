package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.TownJobsModel;

public class TownJobsModelRowMapper  implements RowMapper{

	protected final transient Logger log = Logger.getLogger(TownJobsModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		TownJobsModel vo = new TownJobsModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setTitle(rs.getString("TITLE"));
			vo.setUnit(rs.getString("UNIT"));
			vo.setCreateTime(rs.getDate("CREATETIME").toString());
			vo.setInfoUrl(rs.getString("INFO_URL"));
			vo.setInfoValue(rs.getClob("INFO_VALUE"));
			vo.setJobUrl(rs.getString("JOB_URL"));
			vo.setJobValue(rs.getClob("JOB_VALUE"));
			vo.setContactPhone(rs.getString("CONTACT_PHONE"));
			vo.setContactUser(rs.getString("CONTACT_USER"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

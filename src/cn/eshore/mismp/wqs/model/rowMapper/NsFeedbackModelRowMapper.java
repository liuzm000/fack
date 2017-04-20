package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.NsFeedbackModel;

public class NsFeedbackModelRowMapper implements RowMapper {

	protected final transient Logger log = Logger.getLogger(NsFeedbackModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		NsFeedbackModel vo = new NsFeedbackModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setOperatorName(rs.getString("OPERATORNAME"));
			vo.setPhone(rs.getString("PHONE"));
			vo.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("SUBMIT_TIME")));
			vo.setIsReply(rs.getString("ISREPLY"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}

}

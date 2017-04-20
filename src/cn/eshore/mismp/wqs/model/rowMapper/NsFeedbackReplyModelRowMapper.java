package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.NsFeedbackReplyModel;

public class NsFeedbackReplyModelRowMapper implements RowMapper{

	protected final transient Logger log = Logger.getLogger(NsFeedbackReplyModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		NsFeedbackReplyModel vo = new NsFeedbackReplyModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setFeedbackId(rs.getLong("FEEDBACK_ID"));
			vo.setContent(rs.getString("CONTENT"));
			vo.setReplyName(rs.getString("REPLY_NAME"));
			vo.setReplyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("REPLY_TIME")));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

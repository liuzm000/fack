package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.SmsHisVO;
import cn.eshore.mismp.util.Util;

public class SmsHisRowMapper implements RowMapper {

public Object mapRow(ResultSet rs, int arg1) throws SQLException {
	
		SmsHisVO vo = new SmsHisVO();
		vo.setId(rs.getString("ID"));
		vo.setUserId(rs.getString("USER_ID"));
		vo.setReceiver(rs.getString("RECEIVER"));
		vo.setGroupId(rs.getString("GROUP_ID"));
		vo.setContent(rs.getString("CONTENT"));
		vo.setSendTime(rs.getString("SEND_TIME"));
		vo.setRespStatus(rs.getString("RESP_STATUS"));
		vo.setReceiptStatus(rs.getString("RECEIPT_STATUS"));
		vo.setUserAccount(rs.getString("ACCOUNT"));
		return vo;
	}

}

package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.ServiceOperationVO;
import cn.eshore.mismp.util.Util;

public class ServiceOperationRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		ServiceOperationVO serviceOperation = new ServiceOperationVO();
		serviceOperation.setId(rs.getLong("ID"));
		serviceOperation.setStreamingNo(Util.nullToStr(rs.getString("STREAMING_NO")));
		serviceOperation.setProductId(Util.nullToStr(rs.getString("PRODUCT_ID")));
		serviceOperation.setPhoneNum(Util.nullToStr(rs.getString("PHONE_NUM")));
		serviceOperation.setOpeType(rs.getInt("OPE_TYPE"));
		serviceOperation.setDeviceSrc(Util.nullToStr(rs.getString("DEVICE_SRC")));
		serviceOperation.setResultCode(rs.getInt("RESULT_CODE"));
		serviceOperation.setEffectiveTime(Util.nullToStr(rs.getString("EFFECTIVE_TIME")));
		serviceOperation.setExpireTime(Util.nullToStr(rs.getString("EXPIRE_TIME")));
		serviceOperation.setCreateTime(rs.getDate("CREATE_TIME"));
		serviceOperation.setState(rs.getByte("STATE"));
		return serviceOperation;
	}

}

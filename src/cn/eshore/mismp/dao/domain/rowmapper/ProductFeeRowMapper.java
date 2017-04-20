package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.ProductFeeVO;
import cn.eshore.mismp.dao.domain.ProductVO;
import cn.eshore.mismp.util.Util;

public class ProductFeeRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		ProductFeeVO vo = new ProductFeeVO();
		vo.setDays(rs.getInt("PF_PRO_DAYS"));
		vo.setForFee(rs.getString("PF_FOR_FEE"));
		vo.setForType(rs.getInt("PF_FOR_TYPE"));
		vo.setForValid(rs.getInt("PF_FOR_VALID"));
		vo.setId(rs.getLong("PF_ID"));
		vo.setOrder(rs.getString("PF_FOR_ORDER"));
		vo.setProId(rs.getString("PRO_ID"));
		vo.setProType(rs.getInt("PF_PRO_TYPE"));
		vo.setProValid(rs.getInt("PF_PRO_VALID"));
		vo.setTimes(rs.getInt("PF_PRO_TIMES"));
		return vo;
	}

}

package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.ProductVO;
import cn.eshore.mismp.dao.domain.SPVO;
import cn.eshore.mismp.util.Util;

public class ProductRowMapper  implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		ProductVO vo = new ProductVO();
		vo.setId(rs.getLong("PRO_ID"));
		vo.setIsmpId(Util.nullToStr(rs.getString("ISMP_ID")));
		vo.setName(Util.nullToStr(rs.getString("PRO_NAME")));
		vo.setDesc(Util.nullToStr(rs.getString("PRO_DESC")));
		vo.setType(rs.getInt("PRO_TYPE"));
		vo.setFee(rs.getInt("PRO_FEE"));
		vo.setStatus(rs.getInt("PRO_STATUS"));
		vo.setSpId(rs.getLong("SP_ID"));
		vo.setCreateTime(rs.getString("PRO_CREATETIME"));
		return vo;
	}
}

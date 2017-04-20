package cn.eshore.mismp.dao.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.dao.domain.ProductTypeVO;


public class ProductTyperRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ProductTypeVO ptVO = new ProductTypeVO();
		ptVO.setId(rs.getInt("PT_ID"));
		ptVO.setName(rs.getString("PT_NAME"));		
		ptVO.setDesc(rs.getString("PT_DESC"));
		ptVO.setIconFileName(rs.getString("PT_ICON_NAME"));
		ptVO.setIconFilePath(rs.getString("PT_ICON_PATH"));
		ptVO.setParentId(rs.getLong("PT_PARENT_ID"));
		return ptVO;
	}
}
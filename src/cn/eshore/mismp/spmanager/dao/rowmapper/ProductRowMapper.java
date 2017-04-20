package cn.eshore.mismp.spmanager.dao.rowmapper;

/**
 * @author OYK
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import cn.eshore.mismp.spmanager.dao.model.Product;


public class ProductRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Product pro = new Product();
		pro.setPro_id(rs.getInt("pro_id"));
		pro.setIsmp_id(rs.getString("ismp_id"));
		pro.setPro_name(rs.getString("pro_name"));
		pro.setPro_desc(rs.getString("pro_desc"));
		pro.setPro_type(rs.getInt("pro_type"));
		pro.setPro_fee(rs.getInt("pro_fee"));
		pro.setPro_status(rs.getInt("pro_status"));
		pro.setSp_id(rs.getInt("sp_id"));
		pro.setPro_parent_id(rs.getInt("pro_parent_id"));
		pro.setPro_createtime(rs.getDate("pro_createtime"));
		pro.setPro_remark(rs.getString("pro_remark"));
		pro.setRegisterId(rs.getString("PRO_REGISTER_ID"));
		pro.setPtId(rs.getInt("pt_Id"));
		pro.setDeveloper(rs.getString("PRO_DEVELOPER"));
		pro.setSupplier(rs.getString("PRO_SUPPLIER"));
		pro.setProHwId(rs.getString("PRO_HW_ID"));
		pro.setIsVouch(rs.getInt("PRO_IS_VOUCH"));
		return pro;
	}

}

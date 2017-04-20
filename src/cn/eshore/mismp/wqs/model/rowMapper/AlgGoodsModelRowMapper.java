package cn.eshore.mismp.wqs.model.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.wqs.model.AlgGoodsModel;
import cn.eshore.mismp.wqs.model.BeautifulCountryModel;

public class AlgGoodsModelRowMapper implements RowMapper {
	protected final transient Logger log = Logger.getLogger(AlgGoodsModelRowMapper.class);

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		AlgGoodsModel vo = new AlgGoodsModel();
		try {
			vo.setId(rs.getLong("ID"));
			vo.setName(rs.getString("NAME"));
			vo.setIcon(rs.getString("ICON"));
			vo.setDescript(rs.getString("DESCRIPT"));
			vo.setPreviewUrl(rs.getString("PRIVIEW_URL"));
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		return vo;
	}
}

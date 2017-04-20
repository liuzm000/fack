package cn.eshore.mismp.business.dao.impl;


import java.util.List;

import cn.eshore.mismp.business.dao.PubBusinessDAO;
import cn.eshore.mismp.business.model.InterfaceModel;
import cn.eshore.mismp.business.model.rowMapper.InterfaceModelRowMapper;
import cn.eshore.mismp.business.model.rowMapper.MutiIconModelRowMapper;
import cn.eshore.mismp.business.model.rowMapper.MutiMediaModelRowMapper;
import cn.eshore.mismp.business.model.rowMapper.PubModelRowMapper;
import cn.eshore.mismp.dao.impl.BaseDAOJdbcTemplate;
import cn.eshore.mismp.util.Pagination;

public class PubBusinessDAOImpl extends BaseDAOJdbcTemplate implements PubBusinessDAO {

	@Override
	public Pagination getPubVideoList(String page, String pagesize,String interNum) {
		String sql = "FROM BS_PUB_BASEMSG T WHERE 1=1 AND INTERNUM = ?";
		String qrySql = "SELECT * " +sql;
		String countSql = "SELECT COUNT(1)"+sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Object param[]= {interNum};
		Pagination returnList = pagination(qrySql + orderSql, countSql + orderSql, param, new PubModelRowMapper(),
				Integer.valueOf(pagesize), Integer.valueOf(page));
		return returnList;
	}

	@Override
	public InterfaceModel getPubVideoList(String accessId) {
		String sql = "SELECT T.ID,T.INTERNUM,T.PARENT_INTERNUM,T.INTERNAME,T.DESCRIPT,T.ISACTIVE FROM BS_INTERFACE T WHERE T.ISACTIVE = '1' AND T.INTERNUM='"+accessId+"'";
		InterfaceModel model = (InterfaceModel) this.queryForObject(sql, new InterfaceModelRowMapper());
		return model;
	}

	@Override
	public List getIconList(long id) {
		String sql = "SELECT  T.ID,T.FILE_PATH,T.FOR_ID,T.DESCRIPT FROM BS_PUB_BASEMSG_ICON T  WHERE T.FOR_ID = ?";
		Object params [] = {id};	
	    return this.query(sql,params, new MutiIconModelRowMapper());
	}

	@Override
	public List getMediaList(long id) {
		String sql = "SELECT  T.ID,T.FILE_PATH,T.FOR_ID,T.DESCRIPT,T.NAME,T.ICON,T.PS FROM BS_PUB_BASEMSG_MEDIA T  WHERE T.FOR_ID = ?";
		Object params [] = {id};	
	    return this.query(sql,params, new MutiMediaModelRowMapper());
	}

	@Override
	public Pagination getInterfaceList(int pageNum, int pageSize) {
		String sql = "FROM BS_INTERFACE T ";
		String qrySql = "SELECT * " +sql;
		String countSql = "SELECT COUNT(1)"+sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Object param[]= {};
		Pagination returnList = pagination(qrySql + orderSql, countSql + orderSql, param, new InterfaceModelRowMapper(),
				Integer.valueOf(pageSize), Integer.valueOf(pageNum));
		return returnList;
	}
	
}

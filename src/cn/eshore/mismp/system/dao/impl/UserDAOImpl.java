/**
 * Created at:2008-10-21 下午05:12:51
 */
package cn.eshore.mismp.system.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import cn.eshore.mismp.dao.domain.UserSPVO;
import cn.eshore.mismp.dao.domain.UserVO;
import cn.eshore.mismp.dao.domain.rowmapper.UserRowMapper;
import cn.eshore.mismp.dao.impl.BaseDAOJdbcTemplate;
import cn.eshore.mismp.system.dao.UserDAO;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.Util;


/**
 * <p>Title: UserDAOImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="UserDAOImpl.java.html"><i>View Source</i></a></p>
 * @author Yusm
 * @version 1.0
 */
public class UserDAOImpl extends BaseDAOJdbcTemplate implements UserDAO {
	private static final String QUERY_SQL_BY_ID = "select u.user_id,u.account,u.password,u.description,u.role_id,u.hotel_id,u.hotel_name,u.zone_type_id,r.role_name,r.role_level " +
				" from t_mismp_user u,t_mismp_role r where u.account=? and u.role_id=r.role_id ";
	
	private static final String QUERY_USER_SP_BY_ID = " select tu.USER_ID, tu.ACCOUNT, ts.SP_ID, ts.SP_CODE, ts.SP_NAME from T_MISMP_USER tu left join T_MISMP_SP ts on tu.SP_ID = ts.SP_ID where tu.USER_ID = ? ";
	/* (non-Javadoc)
	 * @see com.poson.dao.UserDAO#getUser(java.lang.String, java.lang.String)
	 */
	public UserVO getUser(String account) {
		UserVO vo = new UserVO();
		final Object[] params = new Object[] { account };
		List<?> rows = this.queryForList(QUERY_SQL_BY_ID, params);
		Iterator<?> it = rows.iterator();
		if(it.hasNext()) {		
		    Map<?, ?> map = (Map<?, ?>) it.next();
		    vo.setUserId(Util.object2Str(map.get("USER_ID")));
		    vo.setAccount(Util.object2Str(map.get("ACCOUNT")));
		    vo.setPassword(Util.object2Str(map.get("PASSWORD")));
		    vo.setDescription(Util.object2Str(map.get("DESCRIPTION")));
		    vo.setRoleId(Long.parseLong(Util.objectToStr(map.get("role_id"))));
		    vo.setRoleName(Util.object2Str(map.get("role_name")));
		    vo.setRoleLevel(Util.String2Int(Util.objectToStr(map.get("role_level"))));
		    vo.setZoneTypeId(Util.String2Long(Util.objectToStr(map.get("ZONE_TYPE_ID"))));
		    vo.setHotelId(Util.object2Str(map.get("HOTEL_ID")));
		    vo.setHotelName(Util.object2Str(map.get("HOTEL_NAME")));
		}
		return vo;
	}
	
	public Pagination listAdminInfo(int roleLevel, int pageSize, int pageNum)
	{
		 String QUERY_SQL = "select u.user_id,u.account,u.password,u.description,u.role_id,u.hotel_id,u.hotel_name,u.zone_type_id,r.role_name,z.zone_name " +
		 		" from t_mismp_user u left join t_mismp_role r on u.role_id = r.role_id left join T_MISMP_ZONE_TYPE z on u.zone_type_id = z.zt_id where r.role_level >=  " + roleLevel;
		
		String COUNT_SQL = "select count(*) from t_mismp_user";
		String ORDRE_SQL=" ORDER BY U.USER_ID ";
		Pagination pageList = (Pagination)list(QUERY_SQL+ORDRE_SQL , COUNT_SQL , null,new UserRowMapper(),pageSize,pageNum);
		return pageList;
	}
	
	public int checkExistByAccount(String account) {
		String sql = "select count(*) from t_mismp_user where account=?";
		return this.queryForInt(sql, new Object[] { account });
	}
	
	public int addAdministrator(UserVO administrator) {
		String sql = "insert into t_mismp_user("
				+ "user_id,account,password,"
				+ "description,role_id,zone_type_id,hotel_id,hotel_name)"
				+ " values (seq_t_mismp_user.nextval,?,?,?,?,?,?,?)";

		final Object[] params = new Object[] {
				Util.nullToStr(administrator.getAccount()),
				Util.nullToStr(administrator.getPassword()),
				Util.nullToStr(administrator.getDescription()),
				administrator.getRoleId(),
				administrator.getZoneTypeId(),
				administrator.getHotelId(),
				administrator.getHotelName()
				};
		return this.update(sql, params);
	}
	
	public UserVO getAdministratorById(String adminId) {
		String sql = "select u.user_id,u.account,u.password,u.description,u.role_id,u.hotel_id,u.hotel_name,u.zone_type_id,r.role_name " +
				" from t_mismp_user u,t_mismp_role r where u.user_id=? and u.role_id=r.role_id";
		List list =  this.query(sql,new Object[] { adminId }, new UserRowMapper());
		if(list==null||list.size()==0)
			return null;
		else
			return (UserVO)list.get(0);
	}
	
	public int updateAdministrator(UserVO administrator) {
		String sql = "update t_mismp_user set account=?,password=?,description=?,role_id=?,zone_type_id=? where user_id=?";

		final Object[] params = new Object[] {
				Util.nullToStr(administrator.getAccount()),
				Util.nullToStr(administrator.getPassword()),
				Util.nullToStr(administrator.getDescription()),
				administrator.getRoleId(),
				administrator.getZoneTypeId(),
				administrator.getUserId() };
		return this.update(sql, params);
	}

	public int deleteAdministrator(final ArrayList list) {
		String sql = "delete from t_mismp_user where user_id=?";
		int retCode[] = this.batchUpdate(sql,
				new BatchPreparedStatementSetter() {
					public int getBatchSize() {
						return list.size();
					}

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						String id = (String) list.get(i);
						ps.setString(1, id);
					}
				});
		if (retCode.length == list.size())
			return retCode.length;
		else
			return 0;
	}
	
	public UserSPVO getUserSPVO(long userId){
		UserSPVO vo = new UserSPVO();
		final Object[] params = new Object[] { userId };
		List<?> rows = this.queryForList(QUERY_USER_SP_BY_ID, params);
		Iterator<?> it = rows.iterator();		
		if(it.hasNext()) {		
		    Map<?, ?> map = (Map<?, ?>) it.next();
		    vo.setUserId(Util.object2Str(map.get("USER_ID")));
		    vo.setUserAccount(Util.object2Str(map.get("ACCOUNT")));
		    vo.setSpId(Util.object2Str(map.get("SP_ID")));
		    vo.setSpCode(Util.object2Str(map.get("SP_CODE")));
		    vo.setSpName(Util.object2Str(map.get("SP_NAME")));
		}
		return vo;
	}
}

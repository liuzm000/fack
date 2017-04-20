package cn.eshore.mismp.system.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import cn.eshore.mismp.dao.domain.RoleVO;
import cn.eshore.mismp.dao.domain.rowmapper.RoleRowMapper;
import cn.eshore.mismp.dao.impl.BaseDAOJdbcTemplate;
import cn.eshore.mismp.system.dao.RoleDAO;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.Util;



public class RoleDAOImpl extends BaseDAOJdbcTemplate implements RoleDAO {

	public int addRole(RoleVO role) {
		String sql = "insert into t_mismp_role(role_id,role_name,description)"
				+ " values(seq_t_mismp_role.nextval,?,?)";

		final Object[] params = new Object[] {
				Util.nullToStr(role.getRole_name()),
				Util.nullToStr(role.getDescription()), };
		return this.update(sql, params);
	}

	public int deleteRole(int role_id) {
		String sql = "delete from t_mismp_role where role_id=?";
		return this.update(sql, new Object[] { role_id });
	}

	public long getRoleCount() {
		String sql = "select count(*) from t_mismp_role";
		return this.queryForLong(sql);
	}

	public int updateRole(RoleVO role) {
		String sql = "update t_mismp_role set role_name=?,description=? where role_id=?";

		final Object[] params = new Object[] {
				Util.nullToStr(role.getRole_name()),
				Util.nullToStr(role.getDescription()),
				role.getRole_id() };
		return this.update(sql, params);
	}

	public List<RoleVO> list(int level) {
		String sql = "select * from t_mismp_role where role_level >= ? order by role_level";
		return this.query(sql, new Object[] { level }, new RoleRowMapper());
	}

	public int deleteRoles(final ArrayList list) {
		
		String sql2 = "delete from t_mismp_role_permission where role_id=?";
		int[] retCode2 = this.batchUpdate(sql2,
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
		
		String sql = "delete from t_mismp_role where role_id=?";
		int[] retCode = this.batchUpdate(sql,
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

	public RoleVO findRoleById(int roleId) {
		String sql = "select * from t_mismp_role where role_id=?";
		return (RoleVO) this.queryForObject(sql, new Object[] { roleId },
				new RoleRowMapper());
	}

	public int findRoleCountByName(String roleName) {
		String sql = "select count(*) from t_mismp_role where role_name=?";
		return this.queryForInt(sql, new Object[] { roleName });
	}

	public List listByName(String roleName) {
		String sql = "select * from t_mismp_role where role_name=? order by role_level";
		return this.query(sql, new Object[] { roleName }, new RoleRowMapper());
	}
	
	private static final String QUERY_SQL = "select * from t_mismp_role order by role_level";
	
	private static final String COUNT_SQL = "select count(*) from t_mismp_role";
	
	public Pagination list(int pageSize, int pageNum) {
		Pagination pageList = (Pagination)list(QUERY_SQL, COUNT_SQL, null,new RoleRowMapper(),pageSize,pageNum);
		return pageList;
	}
}

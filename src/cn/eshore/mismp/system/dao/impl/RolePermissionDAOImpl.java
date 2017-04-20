package cn.eshore.mismp.system.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.RolePermissionVO;
import cn.eshore.mismp.dao.domain.rowmapper.ModuleReadOnlyRowMapper;
import cn.eshore.mismp.dao.domain.rowmapper.RolePermissionRowMapper;
import cn.eshore.mismp.dao.impl.BaseDAOJdbcTemplate;
import cn.eshore.mismp.system.dao.RolePermissionDAO;
import cn.eshore.mismp.util.Util;



public class RolePermissionDAOImpl extends BaseDAOJdbcTemplate
		implements RolePermissionDAO {

	public int addRolePermission(RolePermissionVO rolePermission) {
		String sql = "insert into t_mismp_role_permission(permission_id,role_id,module_id,read_only)"
				+ " values(seq_t_mismp_role_permission.nextval,?,?,?)";

		int readOnly = 0;
		if(rolePermission.getRole_id() == 101){
			readOnly = 1;
		}
		final Object[] params = new Object[] { rolePermission.getRole_id(),
				Util.nullToStr(rolePermission.getModule_id()), readOnly };
		return this.update(sql, params);
	}

	public int deleteRolePermission(int permission_id) {
		String sql = "delete from t_mismp_role_permission where permission_id=?";
		return this.update(sql, new Object[] { permission_id });
	}

	public List<RolePermissionVO> getRolePermissionById(int roleId) {
		String sql = "select * from t_mismp_role_permission where role_id=?";
		return this.query(sql, new Object[] { roleId },
				new RolePermissionRowMapper());
	}

	public int addRolePermission(final int roleId, final List grantModuleIdsList) {
		String sql = "insert into t_mismp_role_permission(permission_id,role_id,module_id,read_only)"
				+ " values(seq_t_mismp_role_permission.nextval,?,?,?)";

		int retCode[] = this.batchUpdate(sql,
				new BatchPreparedStatementSetter() {

					public int getBatchSize() {
						return grantModuleIdsList.size();
					}

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, roleId);
						ps.setString(2, (String) grantModuleIdsList.get(i));						
						if(roleId == 101){
							ps.setInt(3, 1);
						} else {
							ps.setInt(3, 0);
						}
					}

				});
		if (retCode != null)
			return 1;
		else
			return 0;
	}

	public int deleteRolePermissionByRoleId(int roleId) {
		String sql = "delete t_mismp_role_permission where role_id = ?";
		return this.update(sql, new Object[] { roleId });
	}

	public ModuleVO getRolePermissionByParentId(String rootModuleParentId) {
		String sql = "select * from t_mismp_role_permission where parent_module_id=?";
		List moList = this.query(sql, new Object[] { rootModuleParentId },
				new RolePermissionRowMapper());
		if (!moList.isEmpty())
			return (ModuleVO) moList.get(0);
		else 
			return null;
	}
	
	public List<ModuleVO> getModulesByRoleId(long role_id){
		String sql = "select m.module_id,m.module_name,m.description,m.parent_module_id,m.module_type,m.module_url,m.module_order,rp.read_only " +
					" from t_mismp_role_permission rp,t_mismp_module m where rp.role_id=? and rp.module_id=m.module_id order by m.module_order";
		return this.query(sql, new Object[] { role_id },
				new ModuleReadOnlyRowMapper());
	}

}

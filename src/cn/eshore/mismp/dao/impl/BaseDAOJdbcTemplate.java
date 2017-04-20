package cn.eshore.mismp.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.lob.LobHandler;

import cn.eshore.mismp.dao.BaseDAO;
import cn.eshore.mismp.util.Pagination;

/**
 * @author ������ 2007-08-15
 * 
 */
public abstract class BaseDAOJdbcTemplate extends JdbcTemplate implements
		BaseDAO {

	protected transient final Logger log = Logger.getLogger(getClass());

	private LobHandler lobHandler;

	public LobHandler getLobHandler() {
		return lobHandler;
	}

	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}

	public List list(String sql) {
		return this.queryForList(sql);
	}

	public List list(String sql, String countSql, Object[] params,
			RowMapper rowMapper, int pageSize, int pageNum) {
		Pagination result = new Pagination(pageNum, pageSize);
		int count = this.queryForInt(countSql, params);
		result.setRecordCount(count);
		if (pageNum != 0 && pageSize != 0) {
			sql = "SELECT * FROM (SELECT A.*, rownum r FROM (" + sql
					+ ") A WHERE rownum <= " + pageSize * pageNum
					+ ") B WHERE r >" + pageSize * (pageNum - 1);
		}
		List list = (List) this.query(sql, params,
				new RowMapperResultSetExtractor(rowMapper));
		result.addAll(list);
		return result;
	}

	public Pagination pagination(String sql, String countSql, Object[] params,
			RowMapper rowMapper, int pageSize, int pageNum) {
		Pagination result = new Pagination(pageNum, pageSize);
		int count = this.queryForInt(countSql, params);
		result.setRecordCount(count);
		if (pageNum != 0 && pageSize != 0) {
			sql = "SELECT * FROM (SELECT A.*, rownum r FROM (" + sql
					+ ") A WHERE rownum <= " + pageSize * pageNum
					+ ") B WHERE r >" + pageSize * (pageNum - 1);
		}
		List list = (List) this.query(sql, params,
				new RowMapperResultSetExtractor(rowMapper));
		result.addAll(list);
		return result;
	}

	/**
	 * 分页获取信息
	 * 
	 * @param sql
	 * @return Pagination
	 * @author yusm
	 */
	public Pagination list(String sql, String countSql, Object[] params,
			int pageSize, int pageNum) {
		Pagination result = new Pagination(pageNum, pageSize);
		int count = this.queryForInt(countSql, params);
		result.setRecordCount(count);
		if (pageNum > 0 && pageSize > 0) {
			sql = "SELECT * FROM (SELECT A.*, rownum r FROM (" + sql
					+ ") A WHERE rownum <= " + pageSize * pageNum
					+ ") B WHERE r >" + pageSize * (pageNum - 1);
		}
		List list = (List) this.queryForList(sql, params);
		result.addAll(list);
		return result;
	}

	/**
	 * 分页获取信息
	 * 
	 * @param sql
	 * @return Pagination
	 * @author yusm
	 */
	public Pagination list(String sql, String countSql, int pageSize,
			int pageNum) {
		Pagination result = new Pagination(pageNum, pageSize);
		int count = this.queryForInt(countSql);
		result.setRecordCount(count);
		if (pageNum > 0 && pageSize > 0) {
			sql = "SELECT * FROM (SELECT A.*, rownum r FROM (" + sql
					+ ") A WHERE rownum <= " + pageSize * pageNum
					+ ") B WHERE r >" + pageSize * (pageNum - 1);
		}
		List list = (List) this.queryForList(sql);
		result.addAll(list);
		return result;
	}

	public String getPageSql(String sql, int pageSize, int pageNum) {
		if (pageNum != 0 && pageSize != 0) {
			return sql = "SELECT * FROM (SELECT A.*, rownum r FROM (" + sql
					+ ") A WHERE rownum <= " + pageSize * pageNum
					+ ") B WHERE r >" + pageSize * (pageNum - 1);
		}
		return sql;
	}

}

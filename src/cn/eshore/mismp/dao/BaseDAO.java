/**
 * JDBC template公用DAO接口
 */
package cn.eshore.mismp.dao;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.wqs.model.HospitalModel;

/**
 * @author 郭南明 2007-08-15
 * 
 */
public interface BaseDAO  {
	/**
	 * 列出所有纪录
	 */
	public List list(String sql);

	/**
	 * @param sql 执行查询分页的语句
	 * @param countSql  执行统计数据的语句
	 * @param params 执行查询的参数条件
	 * @param rowMapper 数据对象关系映射 O/R Mapping
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List list(String sql, String countSql, Object[] params,
			RowMapper rowMapper, int pageSize, int pageNum);
	
	/**
     * 分页获取信息
     * @param sql
     * @return Pagination
     * @author yusm
     */
	public Pagination list(String sql, String countSql, Object[] params,
			int pageSize, int pageNum);
	
	/**
     * 分页获取信息
     * @param sql
     * @return Pagination
     * @author yusm
     */
	public Pagination list(String sql, String countSql, int pageSize,
			int pageNum);

}

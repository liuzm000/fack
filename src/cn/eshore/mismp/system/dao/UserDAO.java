/**
 * Created at:2008-10-21 下午05:08:20
 */
package cn.eshore.mismp.system.dao;

import java.util.ArrayList;
import java.util.List;

import cn.eshore.mismp.dao.BaseDAO;
import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.UserSPVO;
import cn.eshore.mismp.dao.domain.UserVO;
import cn.eshore.mismp.util.Pagination;


/**
 * <p>Title: UserDAO.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="UserDAO.java.html"><i>View Source</i></a></p>
 * @author Yusm
 * @version 1.0
 */
public interface UserDAO extends BaseDAO {

	UserVO getUser(String account);
	
	public Pagination listAdminInfo(int roleLevel, int pageSize, int pageNum);
	
	public int checkExistByAccount(String account);
	
	//	 添加管理员
	public int addAdministrator(UserVO administrator);
	
	//	根据id查询管理员
	public UserVO getAdministratorById(String adminId);
	
	//	 修改管理员
	public int updateAdministrator(UserVO administrator);
	
	//	 批量删除管理员
	public int deleteAdministrator(ArrayList list);
	
	//获取用户SP信息
	public UserSPVO getUserSPVO(long userId);



	
}

package cn.eshore.mismp.customer.dao;

import java.util.ArrayList;
import java.util.List;

import cn.eshore.mismp.dao.domain.ProductVO;
import cn.eshore.mismp.dao.domain.TerUserDetailVO;
import cn.eshore.mismp.dao.domain.TerUserGroupTreeVO;
import cn.eshore.mismp.dao.domain.TerUserGroupVO;
import cn.eshore.mismp.dao.domain.TerUserOrderVO;
import cn.eshore.mismp.dao.domain.TerUserVO;
import cn.eshore.mismp.util.Pagination;

public interface CustomerDAO {
    //客户信息列表查询
	public Pagination listCustomerInfo(TerUserVO vo, int pageSize, int pageNum);
	
	//客户详细信息查询
	public TerUserDetailVO getCustomerInfo(long id);
	
	//获取产品列表
	public List<ProductVO> getAllProducts();
	
	//获取用户订购列表
	public Pagination listCustomerOrder(TerUserOrderVO vo, int pageSize, int pageNum) ;
	

	//获取用户订购信息
	public TerUserOrderVO getCustomerOrder(long id);
	
	//更新订购状态
	public int updateCustomerOrderStatus(TerUserOrderVO vo);

	//获取所有群组定义信息
	public Pagination listAllUserGroups(int pageSize, int pageNum);

	public int addTerUserGroup(TerUserGroupVO terUserGroupVO);

	public TerUserGroupVO getTerUserGroupById(String idForEdit);

	public int updateTerUserGroup(TerUserGroupVO terUserGroupVO);

	public int deleteTerUserGroup(String idForDel);
	
	//用户群组结构查询
	public List<TerUserGroupTreeVO> getUserGroupTreeConfigList(int pageSize,int pageNum);
	
	//查找id对应菜单结构
	public TerUserGroupTreeVO getTerUserGroupTreeById(String ugtId);
	
	//删除群组结构
	public int deleteUserGroupTreeConfig(ArrayList ids);
	
	//用户群组结构查询
	public List<TerUserGroupTreeVO> getAllUserGroupTreeConfigList();
	
	//添加群组结构
	public int addUserGroupTree(TerUserGroupTreeVO vo) ;
	
	//修改群组结构
	public int modifyUserGroupTree(TerUserGroupTreeVO vo);
	
}

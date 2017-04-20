package cn.eshore.mismp.customer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.eshore.mismp.common.Consts;
import cn.eshore.mismp.customer.dao.CustomerDAO;
import cn.eshore.mismp.customer.service.CustomerService;
import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.ProductVO;
import cn.eshore.mismp.dao.domain.TerUserDetailVO;
import cn.eshore.mismp.dao.domain.TerUserGroupTreeVO;
import cn.eshore.mismp.dao.domain.TerUserGroupVO;
import cn.eshore.mismp.dao.domain.TerUserOrderVO;
import cn.eshore.mismp.dao.domain.TerUserVO;
import cn.eshore.mismp.util.Pagination;

public class CustomerServiceImpl implements CustomerService{
	
	private CustomerDAO customerDAO;
	
	protected static final Logger log = Logger
	.getLogger(CustomerServiceImpl.class);
	
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	
	//客户列表信息查询
	public Pagination listCustomerInfo(TerUserVO vo, int pageSize, int pageNum){
		return customerDAO.listCustomerInfo(vo, pageSize, pageNum);
	}

	//客户详细信息查询
	public TerUserDetailVO getCustomerInfo(long id){
		return customerDAO.getCustomerInfo(id);
	}
	
	//获取产品列表
	public List<ProductVO> getAllProducts(){
		return customerDAO.getAllProducts();
	}
	
	//获取用户订购列表
	public Pagination listCustomerOrder(TerUserOrderVO vo, int pageSize, int pageNum){
		return customerDAO.listCustomerOrder(vo, pageSize, pageNum);
	} 


	//获取用户订购信息
	public TerUserOrderVO getCustomerOrder(long id){
		return customerDAO.getCustomerOrder(id);
	}
	
	//更新订购状态
	public int updateCustomerOrderStatus(TerUserOrderVO vo){
		return customerDAO.updateCustomerOrderStatus(vo);
	}

	public Pagination listAllUserGroups(int pageSize, int pageNum) {
		return customerDAO.listAllUserGroups(pageSize,pageNum);
	}

	public int addTerUserGroup(TerUserGroupVO terUserGroupVO) {
		return customerDAO.addTerUserGroup(terUserGroupVO);
	}

	public TerUserGroupVO getTerUserGroupById(String idForEdit) {
		return customerDAO.getTerUserGroupById(idForEdit);
	}

	public int updateTerUserGroup(TerUserGroupVO terUserGroupVO) {
		return customerDAO.updateTerUserGroup(terUserGroupVO);
	}

	public int deleteTerUserGroup(String idForDel) {
		return customerDAO.deleteTerUserGroup(idForDel);
	}
	
	//用户群组结构查询
	public List<TerUserGroupTreeVO> getUserGroupTreeConfigList(int pageSize,int pageNum){
		return customerDAO.getUserGroupTreeConfigList(pageSize, pageNum);
	}
	
	//查找id对应菜单结构
	public TerUserGroupTreeVO getTerUserGroupTreeById(String ugtId){
		return customerDAO.getTerUserGroupTreeById(ugtId);
	}


	//删除群组结构
	public int deleteUserGroupTreeConfig(String ids[]){
		ArrayList list = new ArrayList();
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}
		return customerDAO.deleteUserGroupTreeConfig(list);
	}
	
	//用户群组结构查询
	public List<TerUserGroupTreeVO> getAllUserGroupTreeConfigList(){
		return orderGroupTree(customerDAO.getAllUserGroupTreeConfigList());
	}

	//添加群组结构
	public int addUserGroupTree(TerUserGroupTreeVO vo) {
		return customerDAO.addUserGroupTree(vo);
	}
	
	//修改群组结构
	public int modifyUserGroupTree(TerUserGroupTreeVO vo){
		return customerDAO.modifyUserGroupTree(vo);
	}
	
	private List orderGroupTree(List moduleList) {
		// 排序列算法
		List<TerUserGroupTreeVO> newModuleList = new ArrayList<TerUserGroupTreeVO>();// 存放

		Map<String, TerUserGroupTreeVO> moduleMap = new HashMap();// 辅助构造树结构
		
		// 构造map树
		for (Iterator iter = moduleList.iterator(); iter.hasNext();) {
			TerUserGroupTreeVO module = (TerUserGroupTreeVO) iter.next();
			moduleMap.put(module.getUgtId(), module);
		}
		for (Iterator iter = moduleList.iterator(); iter.hasNext();) {
			TerUserGroupTreeVO module = (TerUserGroupTreeVO) iter.next();
			if (moduleMap.containsKey(module.getUgtParentId())) {
				TerUserGroupTreeVO parentModule = (TerUserGroupTreeVO) moduleMap.get(module
						.getUgtParentId());
				parentModule.getChildUgtList().add(module);
			}
		}
		// 取得根节点
		TerUserGroupTreeVO rootModule = getRootGroup(Consts.ROOT_MODULE_PARENT_ID,
				moduleList);
		if (rootModule == null)
			return Collections.EMPTY_LIST;
		// 递归构造按级别，按次序的list
		constructOrderedList(newModuleList, rootModule);
		return newModuleList;
	}
	
	private void constructOrderedList(List<TerUserGroupTreeVO> newModuleList, TerUserGroupTreeVO module) {
		newModuleList.add(module);
		// System.out.println(module.getModule_name());
		List childList = module.getChildUgtList();
		if (childList != null) {
			for (Iterator iter = childList.iterator(); iter.hasNext();) {
				TerUserGroupTreeVO mod = (TerUserGroupTreeVO) iter.next();
				constructOrderedList(newModuleList, mod);
			}
		} else
			return;
	}
	
	private TerUserGroupTreeVO getRootGroup(String rootModuleParentId, List groupList) {
		for (Iterator iter = groupList.iterator(); iter.hasNext();) {
			TerUserGroupTreeVO module = (TerUserGroupTreeVO) iter.next();
			if (module.getUgtParentId().equals(rootModuleParentId))
				return module;
			else
				continue;
		}
		return null;
	}
}

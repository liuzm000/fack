package cn.eshore.mismp.spmanager.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import cn.eshore.mismp.dao.domain.ProductTypeVO;
import cn.eshore.mismp.spmanager.dao.SpDAO;
import cn.eshore.mismp.spmanager.dao.model.Product;
import cn.eshore.mismp.spmanager.service.SpService;


/**
 * <p>Title: SpServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: poson</p>
 * <p><a href="SpServiceImpl.java.html"><i>View Source</i></a></p>
 * @author OYK
 * @version 1.0
 */
public class SpServiceImpl implements SpService {
	
	protected static final Logger log = Logger.getLogger(SpServiceImpl.class);
	
	private SpDAO spDAO;

	public SpDAO getSpDAO() {
		return spDAO;
	}

	public void setSpDAO(SpDAO spDAO) {
		this.spDAO = spDAO;
	}
	
	/*
	 * 产品类型列表
	 */
	public List<ProductTypeVO> getProductTypeList(ProductTypeVO vo, int pageSize,
			int pageNum){
		return spDAO.getProductTypeList(vo, pageSize, pageNum);
	}
	
	/*
	 * 根据ID获取产品类型
	 */
	public ProductTypeVO getProductTypeById(String id) {
		return spDAO.getProductTypeById(id);
	}
	
	/*
	 * 查询产品类型ID对应产品
	 */
	public Product getProByPTId(String ptId){
		return spDAO.getProByPTId(ptId);
	}
	
	/*
	 * 删除类型id对应产品类型
	 */
	public int delProType(String ptId){
		return spDAO.delProType(ptId);
	}
	

	/*
	 * 添加产品类型
	 */
	public int addProductType(ProductTypeVO vo){
		return spDAO.addProductType(vo);
	}


	/*
	 * 修改产品类型
	 */
	public int modifyProductType(ProductTypeVO vo){
		return spDAO.modifyProductType(vo);
	}
}

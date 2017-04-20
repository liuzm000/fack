/**
 *@author OYK
 */
package cn.eshore.mismp.spmanager.service;

import java.util.List;

import cn.eshore.mismp.dao.domain.ProductTypeVO;
import cn.eshore.mismp.spmanager.dao.*;
import cn.eshore.mismp.spmanager.dao.model.Product;



/**
 * <p>Title: SystemService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="SystemService.java.html"><i>View Source</i></a></p>
 * @author OYK
 * @version 1.0
 */
public interface SpService {
	
	public SpDAO getSpDAO();
	
	/*
	 * 产品类型列表
	 */
	public List<ProductTypeVO> getProductTypeList(ProductTypeVO vo, int pageSize,
			int pageNum);
	
	/*
	 * 根据ID获取产品类型
	 */
	public ProductTypeVO getProductTypeById(String id);
	/*
	 * 查询产品类型ID对应产品
	 */
	public Product getProByPTId(String ptId);
	
	/*
	 * 删除类型id对应产品类型
	 */
	public int delProType(String ptId);
	

	/*
	 * 添加产品类型
	 */
	public int addProductType(ProductTypeVO vo);
	

	/*
	 * 修改产品类型
	 */
	public int modifyProductType(ProductTypeVO vo);
}

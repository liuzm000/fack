package cn.eshore.mismp.customer.web.action;

import java.util.List;

import cn.eshore.mismp.dao.domain.ProductVO;
import cn.eshore.mismp.dao.domain.TerUserDetailVO;
import cn.eshore.mismp.dao.domain.TerUserOrderVO;
import cn.eshore.mismp.dao.domain.TerUserVO;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.web.action.BaseAction;

public class CustomerOrderAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private TerUserOrderVO terUserOrderVO;
	private String actionName;

	//用户信息初始初始页方法
	public String terUserOrderList(){
		actionName = "terUserOrderList.action";
		terUserOrderVO = new TerUserOrderVO();
		List<ProductVO> productList = this.businessSupportService.getCustomerService().getAllProducts();
		this.getRequest().setAttribute("productList", productList);
		Pagination plist = this.businessSupportService.getCustomerService().listCustomerOrder(null, pageSize, pageNum);
		this.getRequest().setAttribute("dataList", plist);
		return SUCCESS;
	}
	
	//用户订购信息详细
	public String terUserOrderDetail(){
		actionName = "terUserOrderDetail.action";
	    terUserOrderVO = this.businessSupportService.getCustomerService().getCustomerOrder(id);
		this.getRequest().setAttribute("terUserOrderVO", terUserOrderVO);
		return SUCCESS;
	}
	
	
	//用户订购信息编辑
	public String terUserOrderEdit(){
		actionName = "terUserOrderEdit.action";
	    int i = this.businessSupportService.getCustomerService().updateCustomerOrderStatus(terUserOrderVO);
	    if(i > 0){
	    	return SUCCESS;
	    }
	    else 
	    {
	    	return ERROR;
	    }

	}
	
	//带条件查询方法
	public String terUserOrderSearch(){
		actionName = "terUserOrderSearch.action";
		List<ProductVO> productList = this.businessSupportService.getCustomerService().getAllProducts();
		this.getRequest().setAttribute("productList", productList);
		Pagination plist = this.businessSupportService.getCustomerService().listCustomerOrder(terUserOrderVO, pageSize, pageNum);
		this.getRequest().setAttribute("dataList", plist);
		return SUCCESS;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TerUserOrderVO getTerUserOrderVO() {
		return terUserOrderVO;
	}

	public void setTerUserOrderVO(TerUserOrderVO terUserOrderVO) {
		this.terUserOrderVO = terUserOrderVO;
	}
	
	
}

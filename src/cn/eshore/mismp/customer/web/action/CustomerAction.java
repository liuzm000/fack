package cn.eshore.mismp.customer.web.action;

import java.util.List;
import java.util.Map;

import cn.eshore.mismp.dao.domain.TerUserDetailVO;
import cn.eshore.mismp.dao.domain.TerUserVO;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.web.action.BaseAction;

public class CustomerAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private long id;
	private TerUserVO terUserVO;
	private TerUserDetailVO terUserDetailVO;
	private String actionName;
	
	public CustomerAction(){
		super();
		pageNum = 1;
		pageSize = 100;
	}
	

	//用户信息初始初始页方法
	public String terUserList(){
		actionName = "terUserList.action";
		terUserVO = new TerUserVO();
		Pagination plist = this.businessSupportService.getCustomerService().listCustomerInfo(null, pageSize, pageNum);
		this.getRequest().setAttribute("dataList", plist);
		return SUCCESS;
	}
	
	//用户详细信息查询
	public String terUserDetail(){
		actionName = "terUserDetail.action";
		terUserDetailVO = this.businessSupportService.getCustomerService().getCustomerInfo(id);
		this.getRequest().setAttribute("terUserDetailVO", terUserDetailVO);
		return SUCCESS;
	}
	
	//带条件查询方法
	public String terUserSearch(){
		actionName = "terUserSearch.action";
		Pagination plist = this.businessSupportService.getCustomerService().listCustomerInfo(terUserVO, pageSize, pageNum);
		this.getRequest().setAttribute("dataList", plist);
		return SUCCESS;
	}

	public TerUserVO getTerUserVO() {
		return terUserVO;
	}

	public void setTerUserVO(TerUserVO terUserVO) {
		this.terUserVO = terUserVO;
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
	
	
}

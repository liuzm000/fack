package cn.eshore.mismp.business.action;

import cn.eshore.mismp.business.model.InterfaceModel;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.web.action.BaseAction;

public class InterfaceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private InterfaceModel interModel;
	private String actionName;
	private String forward;
	private String tips;
	private String id;
	
	public String getInterfaceList() {
		this.actionName="getInterfaceList.action";
		this.actionName = "getTownMsgList.action";
		try {
			Pagination pageList = this.businessSupportService.getPubBusinessService().getInterfaceList(pageNum, pageSize);
			if ((pageList != null) && (pageList.size() > 0)) {
				getRequest().setAttribute("pageList", pageList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
	}
	
	
	
	
	
	public InterfaceModel getInterModel() {
		return interModel;
	}
	public void setInterModel(InterfaceModel interModel) {
		this.interModel = interModel;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
}

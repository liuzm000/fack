package cn.eshore.mismp.wqs.action;

import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.NongJiaLeModel;

@SuppressWarnings("serial")
public class NongJiaLeAction extends BaseAction {

	private String actionName;
	private String forward;
	private String tips;
	private String type;
	private String title;
	private String searchName;
	private String searchAdd;
	
	private NongJiaLeModel nongJiaLeModel;
	private int id;
	
	public String  getNongJiaLeList(){
		this.log.debug("[getNongJiaLeList]...");
		this.title=NongJiaLeTypeCode.getTypeName(type);
		this.actionName="getNongJiaLeList.action";
		try {
			Pagination pageList=this.businessSupportService.getWqsTravelService(). getNongJiaLeList(  type,  searchName,  searchAdd,  pageNum,  pageSize);
			if ((pageList != null) && (pageList.size() > 0)) {
				getRequest().setAttribute("pageList", pageList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}  
	
	
	public String  toAddNongJiaLe(){
		this.log.debug("[toAddNongJiaLe]...");
		this.title=NongJiaLeTypeCode.getTypeName(type);
		this.actionName="getNongJiaLeList.action?type="+type+"&searchName="+searchName+"&searchAdd="+searchAdd;
		return SUCCESS;
	}  
	
	
	public String   doNongJiaLeAdd(){
		log.debug("[doNongJiaLeAdd]...");
		this.title=NongJiaLeTypeCode.getTypeName(type);
		tips="添加成功!";
		this.forward="wqstravel/getNongJiaLeList.action?type="+type+"&searchName="+searchName+"&searchAdd="+searchAdd;
		nongJiaLeModel.setType(type);
		try {
			int d=this.businessSupportService.getWqsTravelService().doNongJiaLeAdd(nongJiaLeModel);
			if(d<1){
				tips="添加失败，请稍后再试!";
			}
		} catch (Exception e) {
			log.error("在新加农家乐餐馆时，出现异常："+e.toString());
			e.printStackTrace();
		}
		return SUCCESS;
	}  
	
	
	public String    doNongJiaLeDelete(){
		log.debug("doNongJiaLeDelete");
		this.tips = "删除失败,请重试!";
		this.forward="wqstravel/getNongJiaLeList.action?type="+type+"&searchName="+searchName+"&searchAdd="+searchAdd;
		
		NongJiaLeModel nongJiaLeModel=this.businessSupportService.getWqsTravelService().getNongJiaLe(id);
		if(nongJiaLeModel!=null){
			int d=this.businessSupportService.getWqsTravelService().doNongJiaLeDelete(id);
			if(d>0) this.tips="删除成功!";
		}else{
			this.tips = "该记录不存在!";
		}
		
		return SUCCESS;
	}  
	
	
	public String   toEditNongJiaLe(){
		this.log.debug("[toEditNongJiaLe]...");
		this.title=NongJiaLeTypeCode.getTypeName(type);
		this.actionName="getNongJiaLeList.action?type="+type+"&searchName="+searchName+"&searchAdd="+searchAdd;
		NongJiaLeModel nongJiaLeModel=this.businessSupportService.getWqsTravelService().getNongJiaLe(id);
		if(nongJiaLeModel!=null){
			this.getRequest().setAttribute("nongJiaLeModel", nongJiaLeModel);
		}else{
			this.tips="该记录不存在!";
			return ERROR;
		}
		
		return SUCCESS;
	}  
	
	
	public String   doNongJiaLeEdit(){
		log.debug("[doNongJiaLeEdit]...");
		tips="修改成功!";
		this.forward="wqstravel/getNongJiaLeList.action?type="+type+"&searchName="+searchName+"&searchAdd="+searchAdd;
		
		NongJiaLeModel oldNongJiaLeModel=this.businessSupportService.getWqsTravelService().getNongJiaLe(id);
		if(oldNongJiaLeModel!=null){
			oldNongJiaLeModel.setAddr(nongJiaLeModel.getAddr());
			oldNongJiaLeModel.setName(nongJiaLeModel.getName());
			oldNongJiaLeModel.setTele(nongJiaLeModel.getTele());
			int d=this.businessSupportService.getWqsTravelService().doNongJiaLeEdit(  oldNongJiaLeModel);
			if(d<1){
				this.tips="修改失败，请稍后再试!";
			}
		}else{
			this.tips="该记录不存在!";
		}
		return SUCCESS;
	}  
	
	
	
	
	
	
	
	
	
	
	
	
	public NongJiaLeModel getNongJiaLeModel() {
		return nongJiaLeModel;
	}


	public void setNongJiaLeModel(NongJiaLeModel nongJiaLeModel) {
		this.nongJiaLeModel = nongJiaLeModel;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSearchName() {
		return searchName;
	}


	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}


	public String getSearchAdd() {
		return searchAdd;
	}


	public void setSearchAdd(String searchAdd) {
		this.searchAdd = searchAdd;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}

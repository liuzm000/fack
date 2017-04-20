/**
 * 
 */
package cn.eshore.mismp.customer.web.action;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.TerUserGroupTreeVO;
import cn.eshore.mismp.dao.domain.TerUserGroupVO;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.Util;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.util.StringUtil;

/**
 * @author wanglei
 * 
 */
public class CustomerGroupAction extends BaseAction {

	private static final long serialVersionUID = 7852805640074993434L;

	private TerUserGroupVO terUserGroupVO;

	private String actionName;

	private String idForEdit;

	private String idForDel;
	
	private String ugtName;
	
	private String ugtDesc;
	
	private String ugtParentId;
	
	private String ugtType;
	
	private String ugtUrl;
	
	private String ugtId;

	
	public String getUgtDesc() {
		return ugtDesc;
	}

	public void setUgtDesc(String ugtDesc) {
		this.ugtDesc = ugtDesc;
	}

	public String getUgtName() {
		return ugtName;
	}

	public void setUgtName(String ugtName) {
		this.ugtName = ugtName;
	}

	public String getUgtParentId() {
		return ugtParentId;
	}

	public void setUgtParentId(String ugtParentId) {
		this.ugtParentId = ugtParentId;
	}

	public String getUgtType() {
		return ugtType;
	}

	public void setUgtType(String ugtType) {
		this.ugtType = ugtType;
	}

	public String getUgtUrl() {
		return ugtUrl;
	}

	public void setUgtUrl(String ugtUrl) {
		this.ugtUrl = ugtUrl;
	}

	
	public String getUgtId() {
		return ugtId;
	}

	public void setUgtId(String ugtId) {
		this.ugtId = ugtId;
	}

	public String terUserGroupList() {
		actionName = "terUserGroupList.action";
		Pagination plist = this.businessSupportService.getCustomerService()
				.listAllUserGroups(pageSize, pageNum);
		this.getRequest().setAttribute("dataList", plist);
		return SUCCESS;
	}

	public String addTerUserGroup() {
		return SUCCESS;
	}

	public String saveTerUserGroup() {
		int i = 0;
		try {
			i = this.businessSupportService.getCustomerService()
					.addTerUserGroup(terUserGroupVO);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (i > 0) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String editTerUserGroup() {
		TerUserGroupVO vo = null;
		if (StringUtils.isNotEmpty(idForEdit)) {
			vo = this.businessSupportService.getCustomerService()
					.getTerUserGroupById(idForEdit);
			if (StringUtils.isNotEmpty(vo.getTgId())) {
				this.getRequest().setAttribute("vo", vo);
			} else {
				return ERROR;
			}
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String updateTerUserGroup() {
		int i = 0;
		try {
			i = this.businessSupportService.getCustomerService()
					.updateTerUserGroup(terUserGroupVO);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (i > 0) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String deleteTerUserGroup() {
		int i = 0;
		try {
			if (StringUtils.isNotEmpty(idForDel)) {
				i = this.businessSupportService.getCustomerService()
						.deleteTerUserGroup(idForDel);
			}else{
				log.debug("idForDel is null...");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (i > 0) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public TerUserGroupVO getTerUserGroupVO() {
		return terUserGroupVO;
	}

	public void setTerUserGroupVO(TerUserGroupVO terUserGroupVO) {
		this.terUserGroupVO = terUserGroupVO;
	}

	public String getIdForDel() {
		return idForDel;
	}

	public void setIdForDel(String idForDel) {
		this.idForDel = idForDel;
	}

	public String getIdForEdit() {
		return idForEdit;
	}

	public void setIdForEdit(String idForEdit) {
		this.idForEdit = idForEdit;
	}
	
	
	//用户群组结构显示
	public String terUserGroupTreeConfigList() {
		actionName = "terUserGroupTreeConfigList.action";
		List<TerUserGroupTreeVO> ugtList = this.businessSupportService.getCustomerService().getUserGroupTreeConfigList(pageSize,pageNum);
		
		if(ugtList.size()>0) {
			for(int i=0;i<ugtList.size();i++) {
				TerUserGroupTreeVO ugtVO = ugtList.get(i);
				if (ugtVO.getUgtType() == 0) {
					ugtVO.setUgtTypeName("非叶子节点");
				} else if (ugtVO.getUgtType() == 1) {
					ugtVO.setUgtTypeName("叶子节点");
				} else {
					ugtVO.setUgtTypeName("未知类型");
				}
				
				TerUserGroupTreeVO parentUgtVO = this.businessSupportService.getCustomerService().getTerUserGroupTreeById(ugtVO.getUgtParentId());
				if(parentUgtVO!=null)
				{
					ugtVO.setUgtParentName(Util.nullToStr(parentUgtVO.getUgtName()));
				}
			}
			
		}
		
		this.getRequest().setAttribute("ugtList", ugtList);
		
		return SUCCESS;
	}
	
	//用户群组结构删除
	public String deleteTerUserGroupConfig() {
		String[] ids =null;
		HttpServletRequest req = this.getRequest();
		if(req.getParameterValues("ids")!=null)
			ids = req.getParameterValues("ids");
		if(this.businessSupportService.getCustomerService().deleteUserGroupTreeConfig(ids)>0)
			return "success";
		else
			return "fail";
	}
	
	//跳转到添加页面
	public String goAddTerUserGroupConfig() {
		List<TerUserGroupTreeVO> ugtList = this.businessSupportService.getCustomerService().getAllUserGroupTreeConfigList();
		this.getRequest().setAttribute("ugtList", ugtList);
		return SUCCESS;
	}
	
	//增加群组节点
	public String addTerUserGroupConfig() {
		
		if( StringUtil.isNull(ugtName))
			return "fail";
		
		if( StringUtil.isNull(ugtParentId))
			return "fail";
		
		String newId = getNewTerUserGroupId(ugtParentId);
		if(newId.equals("-1"))
			return "fail";
		
		TerUserGroupTreeVO treeVO = new TerUserGroupTreeVO();
		treeVO.setUgtId(newId);
		treeVO.setUgtName(ugtName);
		treeVO.setUgtDesc(ugtDesc);
		treeVO.setUgtParentId(ugtParentId);
		treeVO.setUgtType(new Integer(ugtType).intValue());
		//叶子节点
		if(ugtType.equals("1")){
			treeVO.setUgtUrl("/customer/userGroupTreeDateList.action&ugtId=" + newId );
		}
		else{
			treeVO.setUgtUrl("");
		}
		
		treeVO.setUgtOrder(ugtParentId + "9999");
		
		
		if(this.businessSupportService.getCustomerService().addUserGroupTree(treeVO)>0)
			return "success";
		else 
			return "fail";
		
	}
	
	//获取新的节点ID
	private String getNewTerUserGroupId(String parentId) {
		int i = 1;
		while( i>=1 && i<=9999){
			NumberFormat nFormat=new DecimalFormat("0000");
			String newUgtId = parentId+nFormat.format(i);
			TerUserGroupTreeVO existedUgt = this.businessSupportService.getCustomerService().getTerUserGroupTreeById(newUgtId);
			if(existedUgt==null) {
				return newUgtId;
			}
			else
			{
				i++;
			}
		}
		return "-1";//表示找不到合适的ID了
	}
	
	//跳转修改树形结构页面
	public String goEditTerUserGroupConfig() {
		String idforEdit = null ;
		HttpServletRequest req = this.getRequest();
		if(req.getParameter("idforEdit")!=null)
			idforEdit = req.getParameter("idforEdit");
		if(idforEdit==null || idforEdit.equals(""))
			return "fail";
		
		TerUserGroupTreeVO ugtVo = this.businessSupportService.getCustomerService().getTerUserGroupTreeById(idforEdit);
		if(ugtVo==null)
			return "fail";
		req.setAttribute("ugtVo", ugtVo);
		
		List<TerUserGroupTreeVO> ugtList = this.businessSupportService.getCustomerService().getAllUserGroupTreeConfigList();
		this.getRequest().setAttribute("ugtList", ugtList);
		
		return "success";
	}
	
	//修改树形结构页面
	public String editTerUserGroupConfig() {
		
		if( ugtName==null || ugtName.equals("") )
			return "fail";
		if( ugtParentId==null || ugtParentId.equals("") )
			return "fail";
		
		TerUserGroupTreeVO vo = new TerUserGroupTreeVO();
		vo.setUgtId(ugtId);
		vo.setUgtName(ugtName);
		vo.setUgtDesc(ugtDesc);
		
		if(this.businessSupportService.getCustomerService().modifyUserGroupTree(vo)>0)
			return "success";
		else 
			return "fail";
	}

	//群组结构展示
	public String viewTerUserGroupConfig(){
		//actionName = "viewTerUserGroupConfig.action";
		List<TerUserGroupTreeVO> ugtList = this.businessSupportService.getCustomerService().getAllUserGroupTreeConfigList();
		this.getRequest().setAttribute("ugtList", ugtList);
		return "success";
	}
}

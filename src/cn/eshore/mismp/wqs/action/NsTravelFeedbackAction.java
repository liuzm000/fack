package cn.eshore.mismp.wqs.action;

import java.util.List;

import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.NsFeedbackModel;
import cn.eshore.mismp.wqs.model.NsFeedbackReplyModel;
/**
 * title 反馈回复Action
 * @date  2012-12-6 上午09:59:58
 * @version 1.0
 * @author jianghuan
 */
public class NsTravelFeedbackAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private NsFeedbackReplyModel replyModel;
	private String tips;
	private String forward;
	private String actionName;
	/**
	 * 获取反馈列表
	 * @return
	 */
	public String  doFeedBackList() {
		log.debug("[doFeedBackList...]");
		this.actionName="feedbackList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getFeedbackList(this.pageNum, this.pageSize);
			if ((pageList != null) && (pageList.size() > 0)) {
				getRequest().setAttribute("pageList", pageList);
			}
			getRequest().setAttribute("nsFeedbackModel",new NsFeedbackModel());//设置一个空model 即不带查询条件
			return "success";
		} catch (Exception e) {
			this.log.debug(e.getMessage());
			e.printStackTrace();
		}
		return "error";
	}	
	/**
	 * 获取反馈信息和回复列表
	 * @return
	 */
	public String addFeedbackReply() {
		
		log.debug("【addFeedbackReply】... ");
		try {
			NsFeedbackModel model = this.businessSupportService.getWqsTravelService().getFeedbackById(id);
			List<?> reply = this.businessSupportService.getWqsTravelService().getFeedbackReplyList(id);
			getRequest().setAttribute("nsFeedbackModel",model);//设置一个空model 即不带查询条件
			getRequest().setAttribute("replyList", reply);
			return "success";
		} catch (Exception e) {
			this.log.debug(e.getMessage());
			e.printStackTrace();
		}
		
		return "error";
	}
	/**
	 * 增加回复
	 * @return
	 */
	public String doFeedbackReplySave() {
		log.debug("【doFeedbackReply】...");
		this.tips = "回复成功";
		this.forward = "/wqstravel/addFeedbackReply.action?id="+id;
		try {
			
			replyModel.setFeedbackId(Integer.valueOf(id));
			replyModel.setReplyName("南沙管理员");
			int d = this.businessSupportService.getWqsTravelService().doFeedbackReplySave(replyModel);
			if(d>0) {
				//更改是否回复状态
				int result  = this.businessSupportService.getWqsTravelService().updateFeedbackStatus(Integer.valueOf(id),"1");
				
			}
			return "success";
		} catch (Exception e) {
			this.log.debug(e.getMessage());
			e.printStackTrace();
		}
		return "error";
	}
	
	public String doFeedbackReplyDelete() {
		log.debug("[doFeedbackReplyDelete]");
		this.tips = "删除回复成功";
		String replyId = getRequest().getParameter("replyid");
		String feedbackId = getRequest().getParameter("feedbackId");
		this.forward = "/wqstravel/addFeedbackReply.action?id="+feedbackId;
		int d = this.businessSupportService.getWqsTravelService().doFeedbackReplyDelete(replyId);
		if(d >0) {
			List<?> reply = this.businessSupportService.getWqsTravelService().getFeedbackReplyList(feedbackId);
			if(reply == null || reply.size() ==0) {
				int result  = this.businessSupportService.getWqsTravelService().updateFeedbackStatus(Integer.valueOf(feedbackId),"0");
			}
		}
		return "success";
	}
	public String doFeedbackDelete() {
		
		log.debug("[doFeedbackDelete]");
		this.tips = "删除失败";
		String feedbackId = getRequest().getParameter("feedbackId");
		this.forward = "/wqstravel/feedbackList.action";
		String account = getSession().getAttribute("account").toString();
		int d = this.businessSupportService.getWqsTravelService().doFeedbackDelete(feedbackId);
		if(d >0) {
			this.tips = "删除成功";
		}
		
		
		return "success";
		
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public NsFeedbackReplyModel getReplyModel() {
		return replyModel;
	}
	public void setReplyModel(NsFeedbackReplyModel replyModel) {
		this.replyModel = replyModel;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	private String getAccount() {
		return getSession().getAttribute("account").toString();
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	
}

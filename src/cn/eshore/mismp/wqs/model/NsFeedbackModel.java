package cn.eshore.mismp.wqs.model;

import java.util.Date;

public class NsFeedbackModel {

	private long id; //id
	private String content; //反馈内容
	private String operatorName;//反馈人
	private String phone;//反馈人手机
	private String submitTime;//反馈时间
	private String isReply;//是否回复
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getIsReply() {
		return isReply;
	}
	public void setIsReply(String isReply) {
		this.isReply = isReply;
	}
	
	
}

package cn.eshore.mismp.wqs.model;

import java.util.Date;

public class NsFeedbackReplyModel {

	private long id;//回复ID
	private long feedbackId;//反馈ID
	private String content;//回复内容
	private String replyName;//回复人
	private String replyTime;//回复时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReplyName() {
		return replyName;
	}
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	
	
	
}

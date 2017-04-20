package cn.eshore.mismp.wqs.model;

import cn.eshore.mismp.util.StringUtil;

public class NsTravelFeedbackReqParam {

	private String imsi;
	private String esn;
	private String category;
	private String phone;
	private String content;
	private String phoneNumber;
	private String operator;

	public boolean isValid() {
		boolean isValid = true;
		if(StringUtil.isEmpty(content)) {
			isValid = false;
		}
		if(StringUtil.isEmpty(operator)) {
			isValid = false;
		}
		return isValid;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("imsi=").append(imsi)
		.append("&esn=").append(esn)
		.append("&category=").append(category)
		.append("&phone=").append(phone)
		.append("&phoneNumber=").append(phoneNumber)
		.append("&content=").append(content)
		.append("&operator=").append(operator);

		return sb.toString();
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getEsn() {
		return esn;
	}

	public void setEsn(String esn) {
		this.esn = esn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}

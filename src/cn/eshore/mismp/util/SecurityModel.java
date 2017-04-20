package cn.eshore.mismp.util;

/**
 * <p>
 * Title: SecurityModel
 * </p>
 * <p>
 * Description: 抽象安全模型
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: Eshore
 * </p>
 * 
 * @author zhuosf
 * @version 1.0
 */
public abstract class SecurityModel {
	
	protected String account;
	
	protected String password;
	
	protected String[] receivers;
	
	protected String timestamp;
	
	protected String hashcode;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getReceivers() {
		return receivers;
	}

	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getHashcode() {
		return hashcode;
	}

	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}

}

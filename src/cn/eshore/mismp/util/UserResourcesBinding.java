package cn.eshore.mismp.util;

import java.util.Map;

/**
 * <p>Title: UserResourceBinding</p>
 * <p>Description: 用户资源绑定</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Eshore</p>
 * 
 * @author zhuosf
 * @version 1.1
 */
public class UserResourcesBinding {
		
	/**
	 * 用户帐号与短信业务号码绑定Map
	 */
	private Map userSmsNumBinding;
	
	/**
	 * 用户帐号与彩信业务号码绑定Map
	 */
	private Map userMmsNumBinding;
	
	/**
	 * 用户帐号与短信通知URL绑定Map
	 */
	private Map userSmsNotifyURLBinding;
	
	/**
	 * 用户帐号与彩信通知URL绑定Map
	 */
	private Map userMmsNotifyURLBinding;
	
	/**
	 * 业务号码与短信通知URL绑定Map
	 */
	private Map activationSmsNotifyURLBinding;
	
	/**
	 * 业务号码与彩信通知URL绑定Map
	 */
	private Map activationMmsNotifyURLBinding;
	
	/**
	 * 业务号码与短信业务方法绑定Map
	 */
	private Map activationSmsServiceMethodBinding;

	public Map getActivationMmsNotifyURLBinding() {
		return activationMmsNotifyURLBinding;
	}

	public void setActivationMmsNotifyURLBinding(Map activationMmsNotifyURLBinding) {
		this.activationMmsNotifyURLBinding = activationMmsNotifyURLBinding;
	}

	public Map getActivationSmsNotifyURLBinding() {
		return activationSmsNotifyURLBinding;
	}

	public void setActivationSmsNotifyURLBinding(Map activationSmsNotifyURLBinding) {
		this.activationSmsNotifyURLBinding = activationSmsNotifyURLBinding;
	}

	public Map getUserMmsNotifyURLBinding() {
		return userMmsNotifyURLBinding;
	}

	public void setUserMmsNotifyURLBinding(Map userMmsNotifyURLBinding) {
		this.userMmsNotifyURLBinding = userMmsNotifyURLBinding;
	}

	public Map getUserMmsNumBinding() {
		return userMmsNumBinding;
	}

	public void setUserMmsNumBinding(Map userMmsNumBinding) {
		this.userMmsNumBinding = userMmsNumBinding;
	}

	public Map getUserSmsNotifyURLBinding() {
		return userSmsNotifyURLBinding;
	}

	public void setUserSmsNotifyURLBinding(Map userSmsNotifyURLBinding) {
		this.userSmsNotifyURLBinding = userSmsNotifyURLBinding;
	}

	public Map getUserSmsNumBinding() {
		return userSmsNumBinding;
	}

	public void setUserSmsNumBinding(Map userSmsNumBinding) {
		this.userSmsNumBinding = userSmsNumBinding;
	}

	public Map getActivationSmsServiceMethodBinding() {
		return activationSmsServiceMethodBinding;
	}

	public void setActivationSmsServiceMethodBinding(
			Map activationSmsServiceMethodBinding) {
		this.activationSmsServiceMethodBinding = activationSmsServiceMethodBinding;
	}

}

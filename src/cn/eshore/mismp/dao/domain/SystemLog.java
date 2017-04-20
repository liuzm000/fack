/**
 * 
 */
package cn.eshore.mismp.dao.domain;


/**
 * @author guonm
 * {@docRoot} 系统日志
 *
 */
public class SystemLog {
	private String id;
	private ModuleVO module;
	private String action;
	private String action_date;
	private String ip;
	/**
	 * @return the action
	 */
	public String getAction() {
		return this.action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return this.ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the module
	 */
	public ModuleVO getModule() {
		return this.module;
	}
	/**
	 * @param module the module to set
	 */
	public void setModule(ModuleVO module) {
		this.module = module;
	}
	
	/**
	 * @return the action_date
	 */
	public String getAction_date() {
		return this.action_date;
	}
	/**
	 * @param action_date the action_date to set
	 */
	public void setAction_date(String action_date) {
		this.action_date = action_date;
	}
}

/**
 * 
 */
package cn.eshore.mismp.dao.domain;

/**
 * @author wanglei
 *
 */
public class TerUserGroupVO {

	private String tgId;
	
	private String tgName;
	
	private String tgDesc;
	
	private String tgPhone;

	public String getTgDesc() {
		return tgDesc;
	}

	public void setTgDesc(String tgDesc) {
		this.tgDesc = tgDesc;
	}

	public String getTgId() {
		return tgId;
	}

	public void setTgId(String tgId) {
		this.tgId = tgId;
	}

	public String getTgName() {
		return tgName;
	}

	public void setTgName(String tgName) {
		this.tgName = tgName;
	}

	public String getTgPhone() {
		return tgPhone;
	}

	public void setTgPhone(String tgPhone) {
		this.tgPhone = tgPhone;
	}
}

/**
 * 
 */
package cn.eshore.mismp.version.domain.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

/**
 * @author wanglei
 * 
 */
public class SpForm extends ActionForm{

	private static final long serialVersionUID = -2458604670749244887L;

	private String spId; // 唯一标识SP

	private String spCode;// sp编码

	private String spType;// sp类型 【1：全国 2：省级】

	private String spLocal;// 与sp区域表对应

	private String spName;// sp名称

	private String spDesc;// sp描述

	private String spAddr;// sp地址

	private String spPerson;// 联系人

	private String spPhone;// 联系电话

	private Date spCreatetime;// 创建时间

	public String getSpAddr() {
		return spAddr;
	}

	public void setSpAddr(String spAddr) {
		this.spAddr = spAddr;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public Date getSpCreatetime() {
		return spCreatetime;
	}

	public void setSpCreatetime(Date spCreatetime) {
		this.spCreatetime = spCreatetime;
	}

	public String getSpDesc() {
		return spDesc;
	}

	public void setSpDesc(String spDesc) {
		this.spDesc = spDesc;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getSpLocal() {
		return spLocal;
	}

	public void setSpLocal(String spLocal) {
		this.spLocal = spLocal;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSpPerson() {
		return spPerson;
	}

	public void setSpPerson(String spPerson) {
		this.spPerson = spPerson;
	}

	public String getSpPhone() {
		return spPhone;
	}

	public void setSpPhone(String spPhone) {
		this.spPhone = spPhone;
	}

	public String getSpType() {
		return spType;
	}

	public void setSpType(String spType) {
		this.spType = spType;
	}
}

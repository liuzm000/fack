/**
 * 
 */
package cn.eshore.mismp.version.domain.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.action.ActionForm;

/**
 * @author wanglei
 *
 */
public class ProductForm extends ActionForm implements Serializable{
	
	private static final long serialVersionUID = 564592762087660001L;

	private String proId;
	
	private String imspId;
	
	private String proName;
	
	private String proDesc;
	
	private String proType;
	
	private String proFee;
	
	private String proStatus;
	
	private String spId;
	
	private Date proCreatetime;
	
	private String proParentId;

	public String getImspId() {
		return imspId;
	}

	public void setImspId(String imspId) {
		this.imspId = imspId;
	}

	public Date getProCreatetime() {
		return proCreatetime;
	}

	public void setProCreatetime(Date proCreatetime) {
		this.proCreatetime = proCreatetime;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public String getProFee() {
		return proFee;
	}

	public void setProFee(String proFee) {
		this.proFee = proFee;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProParentId() {
		return proParentId;
	}

	public void setProParentId(String proParentId) {
		this.proParentId = proParentId;
	}

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}
}

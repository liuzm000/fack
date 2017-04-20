/**
 * 
 */
package cn.eshore.mismp.version.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanglei
 *
 */
public class ProductVO implements Serializable{

	private static final long serialVersionUID = 1724152102277535325L;

	private Long proId;
	
	private String ismpId;
	
	private String proName;
	
	private String proDesc;
	
	private Integer proType;
	
	private Integer proFee;
	
	private Integer proStatus;
	
	private Long spId;
	
	private Date proCreatetime;
	
	private Long proParentId;
	
	private String proRemark;

	public String getIsmpId() {
		return ismpId;
	}

	public void setIsmpId(String ismpId) {
		this.ismpId = ismpId;
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

	public Integer getProFee() {
		return proFee;
	}

	public void setProFee(Integer proFee) {
		this.proFee = proFee;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Long getProParentId() {
		return proParentId;
	}

	public void setProParentId(Long proParentId) {
		this.proParentId = proParentId;
	}

	public Integer getProStatus() {
		return proStatus;
	}

	public void setProStatus(Integer proStatus) {
		this.proStatus = proStatus;
	}

	public Integer getProType() {
		return proType;
	}

	public void setProType(Integer proType) {
		this.proType = proType;
	}

	public Long getSpId() {
		return spId;
	}

	public void setSpId(Long spId) {
		this.spId = spId;
	}

	public String getProRemark() {
		return proRemark;
	}

	public void setProRemark(String proRemark) {
		this.proRemark = proRemark;
	}
}

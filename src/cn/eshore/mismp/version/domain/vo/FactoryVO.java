/**
 * 
 */
package cn.eshore.mismp.version.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanglei
 *
 */
public class FactoryVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String facId;
	
	private String facName;
	
	private String facAddr;
	
	private String facPhone;
	
	private String facDesc;
	
	List<TerminalVO> terList;

	public String getFacAddr() {
		return facAddr;
	}

	public void setFacAddr(String facAddr) {
		this.facAddr = facAddr;
	}

	public String getFacDesc() {
		return facDesc;
	}

	public void setFacDesc(String facDesc) {
		this.facDesc = facDesc;
	}

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public String getFacName() {
		return facName;
	}

	public void setFacName(String facName) {
		this.facName = facName;
	}

	public String getFacPhone() {
		return facPhone;
	}

	public void setFacPhone(String facPhone) {
		this.facPhone = facPhone;
	}

	public List<TerminalVO> getTerList() {
		return terList;
	}

	public void setTerList(List<TerminalVO> terList) {
		this.terList = terList;
	}

}

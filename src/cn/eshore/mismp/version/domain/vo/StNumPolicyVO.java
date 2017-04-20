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
public class StNumPolicyVO implements Serializable{

	private static final long serialVersionUID = 932387406548537364L;

	private String poId;
	
	private String stId;
	
	private String poNumSection;
	
	private String poType;
	
	private Date poCreatetime;
	
	private Date poUpdatetime;

	public Date getPoCreatetime() {
		return poCreatetime;
	}

	public void setPoCreatetime(Date poCreatetime) {
		this.poCreatetime = poCreatetime;
	}

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public String getPoNumSection() {
		return poNumSection;
	}

	public void setPoNumSection(String poNumSection) {
		this.poNumSection = poNumSection;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	public Date getPoUpdatetime() {
		return poUpdatetime;
	}

	public void setPoUpdatetime(Date poUpdatetime) {
		this.poUpdatetime = poUpdatetime;
	}

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}
}

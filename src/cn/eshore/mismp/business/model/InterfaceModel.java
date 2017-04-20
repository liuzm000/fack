package cn.eshore.mismp.business.model;
/**
 * <p> 接口映射配置信息model<p>
 * <p> @author jianghuan <p>
 * <p> 时间：2013-1-30上午10:53:42<p>
 * <p> CopyRight 2012 <p>
 */
public class InterfaceModel {

	private long id;
	private String interNum;
	private String parentInterNum;
	private String interName;
	private String descript;
	private String active;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInterNum() {
		return interNum;
	}
	public void setInterNum(String interNum) {
		this.interNum = interNum;
	}
	public String getParentInterNum() {
		return parentInterNum;
	}
	public void setParentInterNum(String parentInterNum) {
		this.parentInterNum = parentInterNum;
	}
	public String getInterName() {
		return interName;
	}
	public void setInterName(String interName) {
		this.interName = interName;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
	
	
	
	
	
}

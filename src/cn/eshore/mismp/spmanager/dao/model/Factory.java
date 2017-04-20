package cn.eshore.mismp.spmanager.dao.model;


import java.io.Serializable;

/**
 * 设备厂商 模型
 * @author OYK
 */
public class Factory implements Serializable{

	private static final long serialVersionUID = 1L;
	private int fac_id;//厂家id
	private String fac_name;//厂家名称
	private String fac_addr;//地址
	private String fac_phone;//联系电话
	private String fac_desc;//描述
	public int getFac_id() {
		return fac_id;
	}
	public void setFac_id(int fac_id) {
		this.fac_id = fac_id;
	}
	public String getFac_name() {
		return fac_name;
	}
	public void setFac_name(String fac_name) {
		this.fac_name = fac_name;
	}
	public String getFac_addr() {
		return fac_addr;
	}
	public void setFac_addr(String fac_addr) {
		this.fac_addr = fac_addr;
	}
	public String getFac_phone() {
		return fac_phone;
	}
	public void setFac_phone(String fac_phone) {
		this.fac_phone = fac_phone;
	}
	public String getFac_desc() {
		return fac_desc;
	}
	public void setFac_desc(String fac_desc) {
		this.fac_desc = fac_desc;
	}
	
}

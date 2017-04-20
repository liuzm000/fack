package cn.eshore.mismp.spmanager.dao.model;


import java.io.Serializable;

/**
 * 设备厂商 模型
 * @author OYK
 */
public class Virtual implements Serializable{

	private static final long serialVersionUID = 1L;
	private int vir_id;//厂家id
	private String vir_name;//厂家名称
	private String vir_desc;//描述
	public int getVir_id() {
		return vir_id;
	}
	public void setVir_id(int vir_id) {
		this.vir_id = vir_id;
	}
	public String getVir_name() {
		return vir_name;
	}
	public void setVir_name(String vir_name) {
		this.vir_name = vir_name;
	}
	public String getVir_desc() {
		return vir_desc;
	}
	public void setVir_desc(String vir_desc) {
		this.vir_desc = vir_desc;
	}

	
}

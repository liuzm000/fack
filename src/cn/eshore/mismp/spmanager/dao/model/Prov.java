package cn.eshore.mismp.spmanager.dao.model;


import java.io.Serializable;

/**
 * 省列表
 * @author OYK
 */
public class Prov implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;//ID
	private String name;//省名
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

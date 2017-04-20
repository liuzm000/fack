package cn.eshore.mismp.spmanager.dao.model;


import java.io.Serializable;

/**
 * 软件版本 简单模型（只用来关联）
 * @author OYK
 */
public class SoftVersion implements Serializable{

	private static final long serialVersionUID = 1L;
	int sv_id;//	number	n			软件版本id
	int st_id;//	number	y			软件类型id
	//for show
	SoftType  st;
	public int getSv_id() {
		return sv_id;
	}
	public void setSv_id(int sv_id) {
		this.sv_id = sv_id;
	}
	public int getSt_id() {
		return st_id;
	}
	public void setSt_id(int st_id) {
		this.st_id = st_id;
	}
	public SoftType getSt() {
		return st;
	}
	public void setSt(SoftType st) {
		this.st = st;
	}
	
}

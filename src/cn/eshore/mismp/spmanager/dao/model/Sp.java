package cn.eshore.mismp.spmanager.dao.model;


import java.io.Serializable;
import java.util.Date;

/**
 * SP模型
 * @author OYK
 */
public class Sp implements Serializable{

	private static final long serialVersionUID = 1L;
	int sp_id;	//唯一标识SP
	String sp_code;//sp编码
	int sp_type;//sp类型 【1：全国 2：省级】
	String sp_local;//与sp区域表对应
	String sp_name;//sp名称
	String sp_desc;//sp描述
	String sp_addr;//sp地址
	String sp_person;//联系人
	String sp_phone;//联系电话
	Date sp_createtime;//创建时间
	//for show
	String sp_local_name;
	
	public String getSp_local_name() {
		return sp_local_name;
	}
	public void setSp_local_name(String sp_local_name) {
		this.sp_local_name = sp_local_name;
	}
	public int getSp_id() {
		return sp_id;
	}
	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}
	public String getSp_code() {
		return sp_code;
	}
	public void setSp_code(String sp_code) {
		this.sp_code = sp_code;
	}
	public int getSp_type() {
		return sp_type;
	}
	public void setSp_type(int sp_type) {
		this.sp_type = sp_type;
	}

	public String getSp_local() {
		return sp_local;
	}
	public void setSp_local(String sp_local) {
		this.sp_local = sp_local;
	}
	public String getSp_name() {
		return sp_name;
	}
	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}
	public String getSp_desc() {
		return sp_desc;
	}
	public void setSp_desc(String sp_desc) {
		this.sp_desc = sp_desc;
	}
	public String getSp_addr() {
		return sp_addr;
	}
	public void setSp_addr(String sp_addr) {
		this.sp_addr = sp_addr;
	}
	public String getSp_person() {
		return sp_person;
	}
	public void setSp_person(String sp_person) {
		this.sp_person = sp_person;
	}
	public String getSp_phone() {
		return sp_phone;
	}
	public void setSp_phone(String sp_phone) {
		this.sp_phone = sp_phone;
	}
	public Date getSp_createtime() {
		return sp_createtime;
	}
	public void setSp_createtime(Date sp_createtime) {
		this.sp_createtime = sp_createtime;
	}
	
}

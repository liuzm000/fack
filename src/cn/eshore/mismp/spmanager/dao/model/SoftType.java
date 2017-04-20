package cn.eshore.mismp.spmanager.dao.model;


import java.io.Serializable;
import java.util.Date;
/**
 * 软件类型 模型
 * @author OYK
 */
public class SoftType implements Serializable{

	private static final long serialVersionUID = 1L;
	int st_id;//	number	n			软件类型id
	String st_name;//	nvarchar2(30)	n			软件类型名称
	String st_desc;//	nvarchar2(100)	y			软件类型描述
	int pro_id;//	number	n			与产品表关联
	Date st_createtime;//	date	n			创建时间
	String st_icon;//	varchar2(30)	y			图标文件名
	String st_icon_path;//	varchar2(100)	y			图标文件存储相对路径
	String st_msg_push;//	varchar2(2)	n	1		是否含有消息推送功能[0.有,1.无]
	String st_eng_name;//	varchar2(30)	y			可选填入,软件类型英文名称
	// for show
	Product pro;
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	public int getSt_id() {
		return st_id;
	}
	public void setSt_id(int st_id) {
		this.st_id = st_id;
	}
	public String getSt_name() {
		return st_name;
	}
	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}
	public String getSt_desc() {
		return st_desc;
	}
	public void setSt_desc(String st_desc) {
		this.st_desc = st_desc;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public Date getSt_createtime() {
		return st_createtime;
	}
	public void setSt_createtime(Date st_createtime) {
		this.st_createtime = st_createtime;
	}
	public String getSt_icon() {
		return st_icon;
	}
	public void setSt_icon(String st_icon) {
		this.st_icon = st_icon;
	}
	public String getSt_icon_path() {
		return st_icon_path;
	}
	public void setSt_icon_path(String st_icon_path) {
		this.st_icon_path = st_icon_path;
	}
	public String getSt_msg_push() {
		return st_msg_push;
	}
	public void setSt_msg_push(String st_msg_push) {
		this.st_msg_push = st_msg_push;
	}
	public String getSt_eng_name() {
		return st_eng_name;
	}
	public void setSt_eng_name(String st_eng_name) {
		this.st_eng_name = st_eng_name;
	}
}

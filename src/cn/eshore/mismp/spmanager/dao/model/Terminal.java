package cn.eshore.mismp.spmanager.dao.model;


import java.io.Serializable;

/**
 * 机型设备 模型
 * @author OYK
 */
public class Terminal implements Serializable{

	private static final long serialVersionUID = 1L;
	int ter_id;//设备id值
	String ter_code;//终端编码
	int fac_id;//与终端厂商表关联
	int vir_id;//与虚拟类型表关联
	int ter_java;//java支持【1：支持 0：不支持】
	int ter_mms;//mms支持【1：支持 0：不支持】
	int ter_wap;//wap支持【1：支持 0：不支持】
	int ter_evdo;//evdo支持【1：支持 0：不支持】
	String ter_plat;//平台【mobile/wince/brew】
	String ter_picpath;//终端展示图片路径
	String ter_des;//终端描述信息
	String ter_name;//终端名称
	//for show
	String fac_name;//厂商名
	String vir_name;//虚拟类型名
	String userAgent;//请求代理编号，华为平台使用
	
	public String getFac_name() {
		return fac_name;
	}
	public void setFac_name(String fac_name) {
		this.fac_name = fac_name;
	}
	public String getVir_name() {
		return vir_name;
	}
	public void setVir_name(String vir_name) {
		this.vir_name = vir_name;
	}
	public int getTer_id() {
		return ter_id;
	}
	public void setTer_id(int ter_id) {
		this.ter_id = ter_id;
	}
	public String getTer_code() {
		return ter_code;
	}
	public void setTer_code(String ter_code) {
		this.ter_code = ter_code;
	}
	public int getFac_id() {
		return fac_id;
	}
	public void setFac_id(int fac_id) {
		this.fac_id = fac_id;
	}
	public int getVir_id() {
		return vir_id;
	}
	public void setVir_id(int vir_id) {
		this.vir_id = vir_id;
	}
	public int getTer_java() {
		return ter_java;
	}
	public void setTer_java(int ter_java) {
		this.ter_java = ter_java;
	}
	public int getTer_mms() {
		return ter_mms;
	}
	public void setTer_mms(int ter_mms) {
		this.ter_mms = ter_mms;
	}
	public int getTer_wap() {
		return ter_wap;
	}
	public void setTer_wap(int ter_wap) {
		this.ter_wap = ter_wap;
	}
	public int getTer_evdo() {
		return ter_evdo;
	}
	public void setTer_evdo(int ter_evdo) {
		this.ter_evdo = ter_evdo;
	}
	public String getTer_plat() {
		return ter_plat;
	}
	public void setTer_plat(String ter_plat) {
		this.ter_plat = ter_plat;
	}
	public String getTer_picpath() {
		return ter_picpath;
	}
	public void setTer_picpath(String ter_picpath) {
		this.ter_picpath = ter_picpath;
	}
	public String getTer_des() {
		return ter_des;
	}
	public void setTer_des(String ter_des) {
		this.ter_des = ter_des;
	}
	public String getTer_name() {
		return ter_name;
	}
	public void setTer_name(String ter_name) {
		this.ter_name = ter_name;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	
}

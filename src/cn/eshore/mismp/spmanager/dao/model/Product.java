package cn.eshore.mismp.spmanager.dao.model;


import java.io.Serializable;
import java.util.Date;
/**
 * SP产品 模型
 * @author OYK
 */
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	int pro_id;//产品id，由后台分配
	String ismp_id;//ismp分配产品id
	String pro_name;//产品名称
	String pro_desc;//产品描述
	//费率默认免费
	int pro_type = 3;//类型【1：包月 2：按次 3：免费】
	//费率默认设置为0
	int pro_fee = 0;//费率【单位：分，免费设置为0】
	int pro_status;//产品状态值【0：开通 1：申请 2：暂停 3：预注销 4：注销】
	int sp_id;//与sp信息表关联
	Date pro_createtime;//创建时间
	int pro_parent_id;//父产品id
	//for show
	Sp sp;
	String pro_parent_name;
	String pro_remark;
	//计费产品注册ID
	private String registerId = "";
	//产品类型
	private int ptId;
	//产品开发者
	private String developer;
	//产品提供者
	private String supplier;
	
	private int isVouch;
	
	private String proHwId;
	
	public String getPro_parent_name() {
		return pro_parent_name;
	}
	public void setPro_parent_name(String pro_parent_name) {
		this.pro_parent_name = pro_parent_name;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getIsmp_id() {
		return ismp_id;
	}
	public void setIsmp_id(String ismp_id) {
		this.ismp_id = ismp_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_desc() {
		return pro_desc;
	}
	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}
	public int getPro_type() {
		return pro_type;
	}
	public void setPro_type(int pro_type) {
		this.pro_type = pro_type;
	}
	public int getPro_fee() {
		return pro_fee;
	}
	public void setPro_fee(int pro_fee) {
		this.pro_fee = pro_fee;
	}
	public int getPro_status() {
		return pro_status;
	}
	public void setPro_status(int pro_status) {
		this.pro_status = pro_status;
	}
	public int getSp_id() {
		return sp_id;
	}
	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}
	public Date getPro_createtime() {
		return pro_createtime;
	}
	public void setPro_createtime(Date pro_createtime) {
		this.pro_createtime = pro_createtime;
	}
	public int getPro_parent_id() {
		return pro_parent_id;
	}
	public void setPro_parent_id(int pro_parent_id) {
		this.pro_parent_id = pro_parent_id;
	}
	public Sp getSp() {
		return sp;
	}
	public void setSp(Sp sp) {
		this.sp = sp;
	}
	public String getPro_remark() {
		return pro_remark;
	}
	public void setPro_remark(String pro_remark) {
		this.pro_remark = pro_remark;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public int getPtId() {
		return ptId;
	}
	public void setPtId(int ptId) {
		this.ptId = ptId;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public int getIsVouch() {
		return isVouch;
	}
	public void setIsVouch(int isVouch) {
		this.isVouch = isVouch;
	}
	public String getProHwId() {
		return proHwId;
	}
	public void setProHwId(String proHwId) {
		this.proHwId = proHwId;
	}

	
	
}

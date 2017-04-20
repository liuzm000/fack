package cn.eshore.mismp.spmanager.dao.model;


import java.io.Serializable;
import java.util.Date;

/**
 * 软件审核 模型
 * @author OYK
 */
public class SoftAudit implements Serializable{

	private static final long serialVersionUID = 1L;
	int audit_id;//	 审核id
	String soft_version;// 	软件版本
	String soft_name;//	版本名称
	int soft_forceupdate;//是否强制更新
	String soft_filepath;//软件路径
	Date create_time;//申请时间
	int audit_status;//	审核状态【1：未审核 2：审核通过 3：审核未通过】
	String soft_desc;//	软件版本描述
	Date audit_time;//审核时间
	String audit_by;//	审核人
	int sv_id;// 与软件版本表关联
	String audit_desc;//审核描述	
	//for show
	SoftVersion sv;
	
	public SoftVersion getSv() {
		return sv;
	}
	public void setSv(SoftVersion sv) {
		this.sv = sv;
	}
	public int getAudit_id() {
		return audit_id;
	}
	public void setAudit_id(int audit_id) {
		this.audit_id = audit_id;
	}
	public String getSoft_version() {
		return soft_version;
	}
	public void setSoft_version(String soft_version) {
		this.soft_version = soft_version;
	}
	public String getSoft_name() {
		return soft_name;
	}
	public void setSoft_name(String soft_name) {
		this.soft_name = soft_name;
	}
	public int getSoft_forceupdate() {
		return soft_forceupdate;
	}
	public void setSoft_forceupdate(int soft_forceupdate) {
		this.soft_forceupdate = soft_forceupdate;
	}
	public String getSoft_filepath() {
		return soft_filepath;
	}
	public void setSoft_filepath(String soft_filepath) {
		this.soft_filepath = soft_filepath;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(int audit_status) {
		this.audit_status = audit_status;
	}
	public String getSoft_desc() {
		return soft_desc;
	}
	public void setSoft_desc(String soft_desc) {
		this.soft_desc = soft_desc;
	}
	public Date getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}
	public String getAudit_by() {
		return audit_by;
	}
	public void setAudit_by(String audit_by) {
		this.audit_by = audit_by;
	}
	public int getSv_id() {
		return sv_id;
	}
	public void setSv_id(int sv_id) {
		this.sv_id = sv_id;
	}
	public String getAudit_desc() {
		return audit_desc;
	}
	public void setAudit_desc(String audit_desc) {
		this.audit_desc = audit_desc;
	}
	
	
}

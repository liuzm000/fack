package cn.eshore.mismp.dao.domain;

import java.util.Date;

/**
 * 客户端用户行为记录
 * @author zhuosf
 * @version 1.0
 */
public class TeruserBehaviorVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String classId; // 插件classid
	
	private Date startime; // 启动时间
	
	private int last; // 时间间隔
	
	private String platformId; // 平台ID
	
	private String appVersion; // 插件当前版本
	
	private String phoneNum; // 手机号码

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public Date getStartime() {
		return startime;
	}

	public void setStartime(Date startime) {
		this.startime = startime;
	}
	
	

	

}

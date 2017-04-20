/**
 * 
 */
package cn.eshore.mismp.version.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanglei
 *
 */
public class SoftTypeVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String stId;

	private String stName;

	private String stDesc;

	private String proId;

	private Date stCreatetime;

	private String stIcon;

	private String stIconPath;

	private String stMsgPush;

	private String stEngName;
	
	private String stMsgType;
	
	private String appType;
	
	private String stListIcon;
	
	private String stListIconPath;

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Date getStCreatetime() {
		return stCreatetime;
	}

	public void setStCreatetime(Date stCreatetime) {
		this.stCreatetime = stCreatetime;
	}

	public String getStDesc() {
		return stDesc;
	}

	public void setStDesc(String stDesc) {
		this.stDesc = stDesc;
	}

	public String getStEngName() {
		return stEngName;
	}

	public void setStEngName(String stEngName) {
		this.stEngName = stEngName;
	}

	public String getStIcon() {
		return stIcon;
	}

	public void setStIcon(String stIcon) {
		this.stIcon = stIcon;
	}

	public String getStIconPath() {
		return stIconPath;
	}

	public void setStIconPath(String stIconPath) {
		this.stIconPath = stIconPath;
	}

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public String getStMsgPush() {
		return stMsgPush;
	}

	public void setStMsgPush(String stMsgPush) {
		this.stMsgPush = stMsgPush;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getStMsgType() {
		return stMsgType;
	}

	public void setStMsgType(String stMsgType) {
		this.stMsgType = stMsgType;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getStListIcon() {
		return stListIcon;
	}

	public void setStListIcon(String stListIcon) {
		this.stListIcon = stListIcon;
	}

	public String getStListIconPath() {
		return stListIconPath;
	}

	public void setStListIconPath(String stListIconPath) {
		this.stListIconPath = stListIconPath;
	}
}

package cn.eshore.mismp.util;

/**
 * <p>Title: MismpConfig</p>
 * <p>Description: mismp config infomation.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Eshore</p>
 * @author zhuosf
 * @version 1.0
 */
public class IsmpConfig {
	
	private String srcDeviceID;
	
	private int srcDeviceType;
	
	private String ismpCrmEngineAddress;
	
	private String portalEngineAddress;

	public String getIsmpCrmEngineAddress() {
		return ismpCrmEngineAddress;
	}

	public void setIsmpCrmEngineAddress(String ismpCrmEngineAddress) {
		this.ismpCrmEngineAddress = ismpCrmEngineAddress;
	}

	public String getPortalEngineAddress() {
		return portalEngineAddress;
	}

	public void setPortalEngineAddress(String portalEngineAddress) {
		this.portalEngineAddress = portalEngineAddress;
	}

	public String getSrcDeviceID() {
		return srcDeviceID;
	}

	public void setSrcDeviceID(String srcDeviceID) {
		this.srcDeviceID = srcDeviceID;
	}

	public int getSrcDeviceType() {
		return srcDeviceType;
	}

	public void setSrcDeviceType(int srcDeviceType) {
		this.srcDeviceType = srcDeviceType;
	}


}

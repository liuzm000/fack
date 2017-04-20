/**
 * Created at:2008-10-21 下午05:09:25
 */
package cn.eshore.mismp.dao.domain;

import java.io.Serializable;

/**
 * <p>Title: UserVO.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="UserVO.java.html"><i>View Source</i></a></p>
 * @author Yusm
 * @version 1.0
 */
public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String account;
	private String password;
	private String description;
	private long roleId;
	private int roleLevel;
	private long zoneTypeId;
	private String zoneName;
	
	//	附加属性,主要是用来在页面上显示的
	String roleName;//角色名
	
	
	
	//所属餐馆酒店
	private String hotelId;
	private String hotelName;
	
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}
	public long getZoneTypeId() {
		return zoneTypeId;
	}
	public void setZoneTypeId(long zoneTypeId) {
		this.zoneTypeId = zoneTypeId;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
}

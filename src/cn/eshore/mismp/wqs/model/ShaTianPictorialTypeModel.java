package cn.eshore.mismp.wqs.model;
/**
 * <p> 沙田画卷类型 <p>
 * <p> @author jianghuan <p>
 * <p> 时间：2012-12-19上午09:42:13<p>
 * <p> CopyRight 2012 <p>
 */
public class ShaTianPictorialTypeModel {

	private long id;
	private String typeName;
	private String iconName;
	private String iconPath;
	private String typeDesc;
	
	private String filePaths;
	
	public String getFilePaths() {
		return filePaths;
	}
	public void setFilePaths(String filePaths) {
		this.filePaths = filePaths;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
	
	
	
}

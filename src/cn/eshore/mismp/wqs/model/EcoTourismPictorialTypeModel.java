package cn.eshore.mismp.wqs.model;

public class EcoTourismPictorialTypeModel {

	private long id;
	private String typeName;
	private String iconName;
	private String iconPath;
	private String typeDesc;
	private String isSkip;
	private String infoHtml;
	
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
	public String getIsSkip() {
		return isSkip;
	}
	public void setIsSkip(String isSkip) {
		this.isSkip = isSkip;
	}
	public String getInfoHtml() {
		return infoHtml;
	}
	public void setInfoHtml(String infoHtml) {
		this.infoHtml = infoHtml;
	}
	
	
	
}

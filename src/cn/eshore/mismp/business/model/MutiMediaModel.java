package cn.eshore.mismp.business.model;
/**
 * <p> 公用信息model对应多个媒体model(包括video audio等)<p>
 * <p> @author jianghuan <p>
 * <p> 时间：2013-1-30上午10:50:40<p>
 * <p> CopyRight 2012 <p>
 */
public class MutiMediaModel {

	private long id;//id
	private String icon;//预览图路径
	private String title;//名称
	private String desc;//描述
	private String filePath;//文件路径
	private String ps;//备用
	private long forId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public long getForId() {
		return forId;
	}
	public void setForId(long forId) {
		this.forId = forId;
	}
	
	
}

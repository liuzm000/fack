package cn.eshore.mismp.business.model;
/**
 * <p> 公用信息model对应多张图片model <p>
 * <p> @author jianghuan <p>
 * <p> 时间：2013-1-30上午10:49:24<p>
 * <p> CopyRight 2012 <p>
 */
public class MutiIconModel {

	private long id;//id
	private String filePath;//图片路径
	private long forId; //外键id
	private String desc;//描述
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getForId() {
		return forId;
	}
	public void setForId(long forId) {
		this.forId = forId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
}

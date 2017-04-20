package cn.eshore.mismp.wqs.model;

public class IconMappingModel {

	private long id;
	private String name;
	private String path;
	private long foreignId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getForeignId() {
		return foreignId;
	}
	public void setForeignId(long foreignId) {
		this.foreignId = foreignId;
	}
	
	
	
}

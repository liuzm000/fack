package cn.eshore.mismp.wqs.model;

public class BeautifulCountryModel {

	private long id;
	private String name;
	private String icon;
	private String descript;
	private String fileUrl;
	private long videoId;
	
	
	private String videoIconPaths;
	private String videoPaths;
	
	 
	public String getVideoIconPaths() {
		return videoIconPaths;
	}
	public void setVideoIconPaths(String videoIconPaths) {
		this.videoIconPaths = videoIconPaths;
	}
	public String getVideoPaths() {
		return videoPaths;
	}
	public void setVideoPaths(String videoPaths) {
		this.videoPaths = videoPaths;
	}
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public long getVideoId() {
		return videoId;
	}
	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}
	
	
	
	
	
}

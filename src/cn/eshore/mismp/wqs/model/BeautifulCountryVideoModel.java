package cn.eshore.mismp.wqs.model;

public class BeautifulCountryVideoModel {

	private long id;
	private long countryID;
	private String iconName;
	private String iconPath;
	private String videoName;
	private String videoPath;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public long getCountryID() {
		return countryID;
	}
	public void setCountryID(long countryID) {
		this.countryID = countryID;
	}
	
	
	
}

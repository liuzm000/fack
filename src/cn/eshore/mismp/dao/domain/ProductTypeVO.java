package cn.eshore.mismp.dao.domain;

import java.util.List;

public class ProductTypeVO {

	private int id = 0;
	
	private String name = "";
	
	private String desc = "";
	
	private String iconFileName= "";
	
	private String iconFilePath = "";
	
	private long parentId = 0;
	
	private List<ProductVO> proList =  null;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconFileName() {
		return iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}

	public String getIconFilePath() {
		return iconFilePath;
	}

	public void setIconFilePath(String iconFilePath) {
		this.iconFilePath = iconFilePath;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public List<ProductVO> getProList() {
		return proList;
	}

	public void setProList(List<ProductVO> proList) {
		this.proList = proList;
	}
	
	
}

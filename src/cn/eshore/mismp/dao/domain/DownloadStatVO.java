package cn.eshore.mismp.dao.domain;

public class DownloadStatVO {
	
	private String softTypeId;
	
	private String softTypeName;
	
	private String softTypeDesc;
	
	private String downloadCount;
	
	private String downloadPhoneCount;
	
	private String submitId;
	
	private String productName;

	public String getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getDownloadPhoneCount() {
		return downloadPhoneCount;
	}

	public void setDownloadPhoneCount(String downloadPhoneCount) {
		this.downloadPhoneCount = downloadPhoneCount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSoftTypeId() {
		return softTypeId;
	}

	public void setSoftTypeId(String softTypeId) {
		this.softTypeId = softTypeId;
	}

	public String getSoftTypeName() {
		return softTypeName;
	}

	public void setSoftTypeName(String softTypeName) {
		this.softTypeName = softTypeName;
	}

	public String getSubmitId() {
		return submitId;
	}

	public void setSubmitId(String submitId) {
		this.submitId = submitId;
	}

	public String getSoftTypeDesc() {
		return softTypeDesc;
	}

	public void setSoftTypeDesc(String softTypeDesc) {
		this.softTypeDesc = softTypeDesc;
	}
	
	

}

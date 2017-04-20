package cn.eshore.mismp.dao.domain;

public class TariffFavVO {

	private int favId;

	private String favName;

	private String favContext;

	private String remarks;

	private java.util.Date createTime;

	private int state;

	private int favTypeId;// 优惠类型

	private float discount;

	private int presentTime;

	private int presentTimes;
	
	private java.util.Date updateTime;

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getFavContext() {
		return favContext;
	}

	public void setFavContext(String favContext) {
		this.favContext = favContext;
	}

	public int getFavId() {
		return favId;
	}

	public void setFavId(int favId) {
		this.favId = favId;
	}

	public String getFavName() {
		return favName;
	}

	public void setFavName(String favName) {
		this.favName = favName;
	}

	public int getFavTypeId() {
		return favTypeId;
	}

	public void setFavTypeId(int favTypeId) {
		this.favTypeId = favTypeId;
	}

	public int getPresentTime() {
		return presentTime;
	}

	public void setPresentTime(int presentTime) {
		this.presentTime = presentTime;
	}

	public int getPresentTimes() {
		return presentTimes;
	}

	public void setPresentTimes(int presentTimes) {
		this.presentTimes = presentTimes;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}

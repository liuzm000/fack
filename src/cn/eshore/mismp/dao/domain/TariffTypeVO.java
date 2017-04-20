package cn.eshore.mismp.dao.domain;

public class TariffTypeVO {

	private int etyeId = 1;

	private String etyeName = "";

	private String etyeContext = "";

	private float proFee = 0;

	private String unit = "";

	private String remarks = "";

	private int state = 0;

	private java.util.Date createTime;

	private java.util.Date updateTime;
	
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getEtyeContext() {
		return etyeContext;
	}

	public void setEtyeContext(String etyeContext) {
		this.etyeContext = etyeContext;
	}

	public int getEtyeId() {
		return etyeId;
	}

	public void setEtyeId(int etyeId) {
		this.etyeId = etyeId;
	}

	public String getEtyeName() {
		return etyeName;
	}

	public void setEtyeName(String etyeName) {
		this.etyeName = etyeName;
	}

	public float getProFee() {
		return proFee;
	}

	public void setProFee(float proFee) {
		this.proFee = proFee;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}

package cn.eshore.mismp.dao.domain;

public class TerUserOrderVO {
	
	private long id;
	
	private long proId = -1;
	
	private long tuId;
	
	private int status = -1;
	
	private String createTime;
	
	private String phoneNum;
	
	private long terId;
	
	private String proName;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public long getProId() {
		return proId;
	}

	public void setProId(long proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	

	public long getTerId() {
		return terId;
	}

	public void setTerId(long terId) {
		this.terId = terId;
	}

	public long getTuId() {
		return tuId;
	}

	public void setTuId(long tuId) {
		this.tuId = tuId;
	}
	
	

}

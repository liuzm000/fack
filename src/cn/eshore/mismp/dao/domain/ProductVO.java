package cn.eshore.mismp.dao.domain;

public class ProductVO {
	
	private long id;
	
	private String ismpId;
	
	private String name;
	
	private String desc;
	
	private int type;
	
	private int fee;
	
	private int status;
	
	private long spId;
	
	private String createTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
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

	public long getSpId() {
		return spId;
	}

	public void setSpId(long spId) {
		this.spId = spId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIsmpId() {
		return ismpId;
	}

	public void setIsmpId(String ismpId) {
		this.ismpId = ismpId;
	}
	
	
	
	
	
	
	
	

}

package cn.eshore.mismp.dao.domain;

public class ProductFeeVO {
	
	private long id;
	//1:时间段 2：次数
	private int proType = 1;
	//试用是否有效 0:有效 1：无效
	private int proValid = 1;
	//试用天数
	private int days = 0;
	//试用次数
	private int times = 0;
	//正式使用类型 1：包月 2：按次
	private int forType = 1;
	//正式是否有效 0：有效 1：无效
	private int forValid = 1;
	//订购指令
	private String order = "";
	//费率
	private String forFee = "0";
	//产品ID
	private String proId = "0";
	
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getForFee() {
		return forFee;
	}
	public void setForFee(String forFee) {
		this.forFee = forFee;
	}
	public int getForType() {
		return forType;
	}
	public void setForType(int forType) {
		this.forType = forType;
	}
	public int getForValid() {
		return forValid;
	}
	public void setForValid(int forValid) {
		this.forValid = forValid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public int getProType() {
		return proType;
	}
	public void setProType(int proType) {
		this.proType = proType;
	}
	public int getProValid() {
		return proValid;
	}
	public void setProValid(int proValid) {
		this.proValid = proValid;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}

	
	
}

package cn.eshore.mismp.wqs.model;

public class RestaurantOrderModel {

	private long id;
	private String name;
	private String phoneNumber;
	private String num;
	private String place;
	private String restTime;
	private String gender;
	
	private String restName;
	private String restTele;
	private String restAddr;
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getRestTime() {
		return restTime;
	}
	public void setRestTime(String restTime) {
		this.restTime = restTime;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getRestTele() {
		return restTele;
	}
	public void setRestTele(String restTele) {
		this.restTele = restTele;
	}
	public String getRestAddr() {
		return restAddr;
	}
	public void setRestAddr(String restAddr) {
		this.restAddr = restAddr;
	}
	
	
	
}

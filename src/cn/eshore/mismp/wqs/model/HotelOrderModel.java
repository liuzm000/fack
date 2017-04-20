package cn.eshore.mismp.wqs.model;

import java.util.List;

public class HotelOrderModel {

	private long id;
	private String name;//联系人
	private String phoneNumber;//电话
	private String hotelid; //酒店ID 
	private String intime; //入住时间
	private String outtime; //退房时间
	private int days;
	private String gender;
	private String isValid;
	
	private String hotelName;//关联字段
	
	private List<HotelOrderDetailModel> detailList;
	
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
	public String getHotelid() {
		return hotelid;
	}
	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	public String getIntime() {
		return intime;
	}
	public void setIntime(String intime) {
		this.intime = intime;
	}
	public String getOuttime() {
		return outtime;
	}
	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public List<HotelOrderDetailModel> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<HotelOrderDetailModel> detailList) {
		this.detailList = detailList;
	}
	
	
}

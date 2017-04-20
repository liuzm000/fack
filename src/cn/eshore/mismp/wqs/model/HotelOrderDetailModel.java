package cn.eshore.mismp.wqs.model;

public class HotelOrderDetailModel {

	private long id;
	private long typeid;
	private String typeName;
	private double price;
	private long num;
	private long orderid;
	
	private String roomName;
	private double roomPrice;
	private String roomIsvalid;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTypeid() {
		return typeid;
	}
	public void setTypeid(long typeid) {
		this.typeid = typeid;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public String getRoomIsvalid() {
		return roomIsvalid;
	}
	public void setRoomIsvalid(String roomIsvalid) {
		this.roomIsvalid = roomIsvalid;
	}
	
	
}

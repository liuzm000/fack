package cn.eshore.mismp.wqs.model;

public class RestOrderReqParam {

	private String imsi;
	private String esn;
	private String category;
	private String phone;
	private String name;
	private String phoneNumber;
	private String time;
	private String num;
	private String place;
	private String restid;
	private String gender;

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getEsn() {
		return esn;
	}

	public void setEsn(String esn) {
		this.esn = esn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public boolean isValid() {
		
		boolean isValid = true;
		
		return isValid;
	}

	public String toString() {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("imsi=").append(this.imsi).append("&esn=").append(
				this.esn).append("&category=").append(this.category).append(
				"&phone=").append(this.phone).append("&name=")
				.append(this.name).append("&phoneNumber=").append(this.phoneNumber)
				.append("&time=").append(this.time).append("&place=")
				.append(this.place).append("&num=").append(this.num)
				.append("&restid=").append(this.restid);
		return strBuffer.toString();
	}

	public String getRestid() {
		return restid;
	}

	public void setRestid(String restid) {
		this.restid = restid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}

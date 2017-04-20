package cn.eshore.mismp.business.model;

import org.apache.axis.utils.StringUtils;
/**
 * <p> 公用请求参数<p>
 * <p> @author jianghuan <p>
 * <p> 时间：2013-1-30上午10:54:18<p>
 * <p> CopyRight 2012 <p>
 */
public class PubReqParam {

    private String imsi;
    private String esn;
    private String category;//平台
    private String phone;//机型
    private String page;//页码
    private String pagesize;//每页记录数
    private String accessId; //接口Id
    private String typeId;//分类Id
    private String phoneNumber;//手机号码
    private String itemid;//XX Id(可通用)
    private String key;//校验码
    
    
    
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
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPagesize() {
		return pagesize;
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getAccessId() {
		return accessId;
	}
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("imsi=").append(this.imsi)
	    .append("&esn=").append(this.esn)
		.append("&category=").append(this.category)
		.append("&phone=").append(this.phone)
		.append("&accessId=").append(this.accessId)
		.append("&page=").append(this.page)
		.append("&pagesize=").append(this.pagesize)
		.append("&typeid=").append(this.typeId)
		.append("&phoneNumber=").append(this.phoneNumber)
		.append("&itemid=").append(this.itemid)
		.append("&key=").append(this.key);
		
		return result.toString();
		
	}
	/**
	 * 参数检验
	 * @return
	 */
	public boolean isValid() {
		boolean flag = true;
		if(StringUtils.isEmpty(this.getAccessId())) {
			flag = false;
		}
		
		return flag;
	}
      
      
      
}

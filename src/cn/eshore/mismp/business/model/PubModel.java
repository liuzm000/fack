package cn.eshore.mismp.business.model;

import java.sql.Clob;
/**
 * <p> 公用信息表model<p>
 * <p> @author jianghuan <p>
 * <p> 时间：2013-1-30上午10:52:41<p>
 * <p> CopyRight 2012 <p>
 */
public class PubModel {

	private long id;
	private long forId;
	private String title;
	private String name;
	private String icon;
	private String filePath;
	private String iconHD;
	private String icons;
	private String descript;
	private String detail;
	private Clob htmlValue;
	private String addr;
	private String tele;
	private String unit;
	private String author;
	private String time;
	private String flag;
	private String isActive;
	private String value1;
	private String value2;
	private String interNum;
	private String orders;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getForId() {
		return forId;
	}
	public void setForId(long forId) {
		this.forId = forId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getIconHD() {
		return iconHD;
	}
	public void setIconHD(String iconHD) {
		this.iconHD = iconHD;
	}
	public String getIcons() {
		return icons;
	}
	public void setIcons(String icons) {
		this.icons = icons;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Clob getHtmlValue() {
		return htmlValue;
	}
	public void setHtmlValue(Clob htmlValue) {
		this.htmlValue = htmlValue;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getOrders() {
		return orders;
	}
	public void setOrders(String orders) {
		this.orders = orders;
	}
	public String getInterNum() {
		return interNum;
	}
	public void setInterNum(String interNum) {
		this.interNum = interNum;
	}
	
	
	
	
}

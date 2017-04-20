/**
 * 
 */
package cn.eshore.mismp.version.domain.vo;

import java.io.Serializable;

/**
 * @author wanglei
 *
 */
public class SoftTermVO implements Serializable{

	private String stId;
	
	private String terId;
	
	private String terCode;

	private String facId;

	private String virId;

	private String terJava;

	private String terMms;

	private String terWap;

	private String terEvdo;

	private String terPlat;

	private String terPicPath;

	private String terDes;

	private String terName;
	
	private String facName;
	
	private String virName;

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public String getTerId() {
		return terId;
	}

	public void setTerId(String terId) {
		this.terId = terId;
	}

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public String getFacName() {
		return facName;
	}

	public void setFacName(String facName) {
		this.facName = facName;
	}

	public String getTerCode() {
		return terCode;
	}

	public void setTerCode(String terCode) {
		this.terCode = terCode;
	}

	public String getTerDes() {
		return terDes;
	}

	public void setTerDes(String terDes) {
		this.terDes = terDes;
	}

	public String getTerEvdo() {
		return terEvdo;
	}

	public void setTerEvdo(String terEvdo) {
		this.terEvdo = terEvdo;
	}

	public String getTerJava() {
		return terJava;
	}

	public void setTerJava(String terJava) {
		this.terJava = terJava;
	}

	public String getTerMms() {
		return terMms;
	}

	public void setTerMms(String terMms) {
		this.terMms = terMms;
	}

	public String getTerName() {
		return terName;
	}

	public void setTerName(String terName) {
		this.terName = terName;
	}

	public String getTerPicPath() {
		return terPicPath;
	}

	public void setTerPicPath(String terPicPath) {
		this.terPicPath = terPicPath;
	}

	public String getTerPlat() {
		return terPlat;
	}

	public void setTerPlat(String terPlat) {
		this.terPlat = terPlat;
	}

	public String getTerWap() {
		return terWap;
	}

	public void setTerWap(String terWap) {
		this.terWap = terWap;
	}

	public String getVirId() {
		return virId;
	}

	public void setVirId(String virId) {
		this.virId = virId;
	}

	public String getVirName() {
		return virName;
	}

	public void setVirName(String virName) {
		this.virName = virName;
	}
	
}

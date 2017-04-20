package cn.eshore.mismp.wqs.action;

import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.HotelModel;
import cn.eshore.mismp.wqs.model.TownMessageModel;

/**
 * <p> 商务服务Action <p>
 * <p> @author jianghuan <p>
 * <p> 时间：2013-3-2下午05:59:47<p>
 * <p> CopyRight 2012 <p>
 */
public class BusinessServiceAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private HotelModel hotelModel;
	private String actionName;
	private String forward;
	private String tips;
	private String id;
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	private String MSG_FILE_PATH = MobileGlobals.getProperty("path.town.msg");
	public String getHotelList() {
		
		return SUCCESS;
	}
	
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public HotelModel getHotelModel() {
		return hotelModel;
	}
	public void setHotelModel(HotelModel hotelModel) {
		this.hotelModel = hotelModel;
	}
	
	
	
	
	
	
	
}

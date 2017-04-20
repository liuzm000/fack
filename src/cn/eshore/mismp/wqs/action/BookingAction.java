/**
 * 
 */
package cn.eshore.mismp.wqs.action;

import java.util.ArrayList;
import java.util.List;

import cn.eshore.mismp.dao.domain.UserVO;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.HotelOrderDetailModel;
import cn.eshore.mismp.wqs.model.HotelOrderModel;

/**
 * @author wanglei
 *
 */
public class BookingAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private String actionName;
	private String forward;
	private String tips;
	private String searchName;
	
	private HotelOrderModel hoModel;
	private HotelOrderDetailModel hodModel;
	
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");

	public String getHotelOrderList() {
		this.log.debug("[getHotelOrderList]...");
		this.actionName = "getHotelOrderList.action";
		try {
			UserVO userVO=(UserVO) this.getRequest().getSession().getAttribute("UserVO" );//获取登陆用户的所属餐馆id
			if(userVO==null) return SUCCESS;
			String hotelId=userVO.getHotelId();
			if("0".equals(hotelId)) hotelId=null;
			
			Pagination pageList = this.businessSupportService.getWqsTravelService().getHotelOrderList(searchName,hotelId,pageNum, pageSize);
//			Pagination pageList = this.businessSupportService.getWqsTravelService().getHotelOrderList(pageNum, pageSize);
			List<HotelOrderModel> newlist = new ArrayList<HotelOrderModel>();
			if ((pageList != null) && (pageList.size() > 0)) {
				for (int i = 0; i < pageList.size(); i++) {
					HotelOrderModel model = (HotelOrderModel)pageList.get(i);
					List<HotelOrderDetailModel> detailList = this.businessSupportService.getWqsTravelService().getHotelOrderDetailList(model.getId());
					if(detailList != null && detailList.size() > 0){
						model.setDetailList(detailList);
					}
					newlist.add(model);
				}
				pageList.clear();
				pageList.addAll(newlist);
				this.getRequest().setAttribute("pageList", pageList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
	}
	
	public String getFoodOrderList() {
		this.log.debug("[getFoodOrderList]...");
		this.actionName = "getFoodOrderList.action";
		try {
			UserVO userVO=(UserVO) this.getRequest().getSession().getAttribute("UserVO" );//获取登陆用户的所属餐馆id
			if(userVO==null) return SUCCESS;
			String hotelId=userVO.getHotelId();
			if("0".equals(hotelId)) hotelId=null;
			
			
			Pagination pageList = this.businessSupportService.getWqsTravelService().getFoodOrderList(searchName,hotelId,pageNum, pageSize);
//			Pagination pageList = this.businessSupportService.getWqsTravelService().getFoodOrderList(pageNum, pageSize);
			if ((pageList != null) && (pageList.size() > 0)) {
				this.getRequest().setAttribute("pageList", pageList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
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

	public HotelOrderModel getHoModel() {
		return hoModel;
	}

	public void setHoModel(HotelOrderModel hoModel) {
		this.hoModel = hoModel;
	}

	public HotelOrderDetailModel getHodModel() {
		return hodModel;
	}

	public void setHodModel(HotelOrderDetailModel hodModel) {
		this.hodModel = hodModel;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
}

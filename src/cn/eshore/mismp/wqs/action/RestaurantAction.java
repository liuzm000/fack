package cn.eshore.mismp.wqs.action;

import java.io.File;
import java.util.List;

import cn.eshore.mismp.util.ClobUtil;
import cn.eshore.mismp.util.FileUploader;
import cn.eshore.mismp.util.FileUtil;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.StringUtil;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.HotelModel;
import cn.eshore.mismp.wqs.model.IconMappingModel;
import cn.eshore.mismp.wqs.model.RoomTypeModel;

public class RestaurantAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionName;
	private String forward;
	private String tips;
	
	
	
	private String id;
	private String searchName;
	private String searchAdd;
	
	private String[] hotelIconUrlsFileName;
	private String[] teles;
	private String[] roomTypes;
	private String[] prices;
	private File[] hotelIconUrls;
	private HotelModel hotel;
	private String contentStr;
	
	
	
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	private String HTML_PATH = MobileGlobals.getProperty("path.restaurant.html");
	private String ICON_PATH = MobileGlobals.getProperty("path.restaurant.icon");
	
	
	
	public String getRestaurantList(){
		log.debug("【getRestaurantList】... ");
		this.actionName="getRestaurantList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getRestaurantList(searchName,searchAdd,String.valueOf(pageNum), String.valueOf(pageSize));
			if ((pageList != null) && (pageList.size() > 0)) {
				getRequest().setAttribute("pageList", pageList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
	}
	
	
	public String doRestaurantAdd() {
		log.debug("[doRestaurantAdd]...");
		return SUCCESS;
	}
	
	
	
	public String doRestaurantSave() {
		log.debug("[doRestaurantSave]...");
		this.forward = "wqstravel/doRestaurantAdd.action?searchName="+searchName+"&searchAdd="+searchAdd;
		this.tips="添加失败!";
		
		if(this.teles!=null){
			String teleStr="";
			for(String tele:teles){
				
				if(tele!=null&&!"".equals(tele)){
					if(!"".equals(teleStr)) teleStr+=",";
					teleStr+=tele;
				} 
			}
			hotel.setTele(teleStr);
		}
		
		String htmlPath=null;
		if(contentStr!=null){
			String html = StringUtil.getHtmlString(hotel.getName(),contentStr);
			String htmlFileName = System.currentTimeMillis()+".html";
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.HTML_PATH, html, htmlFileName, false);
			if(!b) {
				this.tips = "保存介绍文件失败,请稍后 再试!";
				return SUCCESS;
			}
			htmlPath=this.HTML_PATH+htmlFileName;
			hotel.setHtmlInfo(htmlPath);//设置HTML文件的路径
			hotel.setHtmlContent(contentStr);//保存HTML内容
		}
		int restauranId = this.businessSupportService.getWqsTravelService().doRestaurantSave(hotel);//保存酒店基本信息
		
		if(restauranId>0){
			this.tips="添加成功!";
			this.forward = "wqstravel/getRestaurantList.action?searchName="+searchName+"&searchAdd="+searchAdd;
			
			//保存图片
			if(hotelIconUrlsFileName!=null&&hotelIconUrls!=null&&hotelIconUrlsFileName.length>0&&hotelIconUrls.length>0){
				int length=hotelIconUrlsFileName.length;
				long returnData = 0L;
				String newFileName;
				IconMappingModel iconModel = new IconMappingModel();
				iconModel.setForeignId(restauranId);
				int d=0;
				File file;
				for(int index=0;index<length;index++){
					newFileName=StringUtil.newFileName(this.hotelIconUrlsFileName[index]);
					returnData = FileUploader.upload(this.hotelIconUrls[index], this.FILE_UPLOAD_PATH + this.ICON_PATH, newFileName, false);
					//将上传成功的图片的记录保存到数据库
					if (returnData > 0L) {
						iconModel.setName(hotelIconUrlsFileName[index]);
						iconModel.setPath(this.ICON_PATH+newFileName);
						d=this.businessSupportService.getWqsTravelService().doRestaurantIconSave(iconModel);
						if(d<1){
							file=new File(this.ICON_PATH+newFileName);
							this.deleteFile(file);
						}
					}
				}
			}
		}else{
			File file=new File(this.FILE_UPLOAD_PATH+htmlPath);
			this.deleteFile(file);
			return SUCCESS;
		}
		
		
		
		return SUCCESS;				
	}
	
	
	@SuppressWarnings("unchecked")
	public String doRestaurantDelete(){
		log.debug("doRestaurantDelete");
		this.tips = "删除失败,请重试!";
		this.forward =  "wqstravel/getRestaurantList.action?searchName="+searchName+"&searchAdd="+searchAdd;
		int id=Integer.valueOf(this.id);
		this.hotel=this.businessSupportService.getWqsTravelService().getRestaurantById(id);
		try {
			List<IconMappingModel> iconList = this.businessSupportService.getWqsTravelService().getRestaurantIconById(id);//先获取数据库里餐馆图片信息
			
			int d = this.businessSupportService.getWqsTravelService().doRestaurantIconDeleteByHotelId(id);//删除数据库里酒店图片信息
			
			if(d <= 0 ) {// 删除失败
				return SUCCESS;
			}else{//数据库记录删除成功，接着删除图片文件
				if(iconList!=null){
					for(IconMappingModel icon:iconList){
						File hotelIcon = new File(this.FILE_UPLOAD_PATH+icon.getPath());
						if(hotelIcon.exists()) {
							log.debug("Delete"+this.FILE_UPLOAD_PATH+icon.getPath());
							hotelIcon.delete();
						}
					}
				}
			}
			d = this.businessSupportService.getWqsTravelService().doRestaurantDelete(id);//删除数据库里餐馆基本信息
			if(d > 0 ) {//数据库记录删除成功，接着删除HTML文件
				File hotelHtml = new File(this.FILE_UPLOAD_PATH+hotel.getHtmlInfo());
				if(hotelHtml.exists()) {
					log.debug("Delete"+this.FILE_UPLOAD_PATH+hotel.getHtmlInfo());
					hotelHtml.delete();
				}
				this.tips = "删除成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return SUCCESS;
	}
	
	
	
	public String getRestaurantDetail(){
		log.debug("【getRestaurantDetail】... ");
		this.actionName="getRestaurantList.action?searchName="+searchName+"&searchAdd="+searchAdd;
		try {
			List iconList = this.businessSupportService.getWqsTravelService().getRestaurantIconById(Integer.parseInt(id));
			hotel = this.businessSupportService.getWqsTravelService().getRestaurantById(Integer.parseInt(id));
			if ((iconList != null) && (iconList.size() > 0)) {
				getRequest().setAttribute("iconList", iconList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("查询[id="+id+"]餐馆详细信息时，出现异常："+e.getMessage());
		}
		return ERROR;
	}
	
	
	
	public String toEditRestaurant(){
		log.debug("【toEditRestaurant】... ");
		this.actionName="getRestaurantList.action?searchName="+searchName+"&searchAdd="+searchAdd;
		try {
			hotel = this.businessSupportService.getWqsTravelService().getRestaurantById(Integer.parseInt(id));
			contentStr=ClobUtil.clobToString(hotel.getHtmlvalue());
			List iconList = this.businessSupportService.getWqsTravelService().getRestaurantIconById(Integer.parseInt(id));
			if ((iconList != null) && (iconList.size() > 0)) {
				getRequest().setAttribute("iconList", iconList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("查询[id="+id+"]餐馆详细信息时，出现异常："+e.getMessage());
		}
		return ERROR;
	}
	
	
	
	public String doRestaurantEdit(){
		log.debug("【doRestaurantEdit】... ");
		this.tips = "修改成功!";
		this.forward =  "wqstravel/getRestaurantList.action?searchName="+searchName+"&searchAdd="+searchAdd;
		
		HotelModel oldHotel = this.businessSupportService.getWqsTravelService().getRestaurantById(Integer.parseInt(id));
		if(this.teles!=null){
			String teleStr="";
			for(String tele:teles){
				if(tele!=null&&!"".equals(tele)){
					if(!"".equals(teleStr)) teleStr+=",";
					teleStr+=tele;
				} 
			}
			oldHotel.setTele(teleStr);
		}
		
		
		String newHtmlPath=null;
		String oldHtmlPath=oldHotel.getHtmlInfo();
		if(this.contentStr!=null){
			String html = StringUtil.getHtmlString(hotel.getName(),this.contentStr);
			String htmlFileName = System.currentTimeMillis()+".html";
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.HTML_PATH, html, htmlFileName, false);
			if(!b) {
				this.tips = "保存介绍文件失败,请稍后 再试!";
				return SUCCESS;
			}
			newHtmlPath=this.HTML_PATH+htmlFileName;
		}
		oldHotel.setName(hotel.getName());
		oldHotel.setAddr(hotel.getAddr());
		oldHotel.setHtmlInfo(newHtmlPath);//设置HTML文件的路径
		oldHotel.setHtmlContent(this.contentStr);//保存HTML内容
		
		int d= this.businessSupportService.getWqsTravelService().doRestaurantUpdate(oldHotel);//保存餐馆基本信息
		File file;
		if(d>0){
			file=new File(this.FILE_UPLOAD_PATH +oldHtmlPath);
			this.deleteFile(file);
			
			//保存图片
			if(hotelIconUrlsFileName!=null&&hotelIconUrls!=null&&hotelIconUrlsFileName.length>0&&hotelIconUrls.length>0){
				long hotelId=Long.valueOf(id);
				List<IconMappingModel> iconList = this.businessSupportService.getWqsTravelService().getRestaurantIconById(hotelId);//先获取数据库里餐馆图片信息
				d = this.businessSupportService.getWqsTravelService().doRestaurantIconDeleteByHotelId(Integer.valueOf(id));//删除数据库里酒店图片信息
				if(d > 0 ) {//数据库记录删除成功，接着删除图片文件
					if(iconList!=null){
						for(IconMappingModel icon:iconList){
							File hotelIcon = new File(this.FILE_UPLOAD_PATH+icon.getPath());
							if(hotelIcon.exists()) {
								log.debug("Delete"+this.FILE_UPLOAD_PATH+icon.getPath());
								hotelIcon.delete();
							}
						}
					}
				}else{
					return SUCCESS;
				}
				
				int length=hotelIconUrlsFileName.length;
				long returnData = 0L;
				String newFileName;
				IconMappingModel iconModel = new IconMappingModel();
				iconModel.setForeignId(hotelId);
				for(int index=0;index<length;index++){
					newFileName=StringUtil.newFileName(this.hotelIconUrlsFileName[index]);
					returnData = FileUploader.upload(this.hotelIconUrls[index], this.FILE_UPLOAD_PATH + this.ICON_PATH, newFileName, false);
					//将上传成功的图片的记录保存到数据库
					if (returnData > 0L) {
						iconModel.setName(hotelIconUrlsFileName[index]);
						iconModel.setPath(this.ICON_PATH+newFileName);
						this.businessSupportService.getWqsTravelService().doRestaurantIconSave(iconModel);
					}
				}
			}
		}else{
			this.tips="修改失败，请稍后再试!";
			file=new File(this.FILE_UPLOAD_PATH +newHtmlPath);
			this.deleteFile(file);
			return SUCCESS;
		}
		
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void deleteFile(File file){
		//用来上传失败后删除文件
		if(file.exists()){
			file.delete();
		}
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
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchAdd() {
		return searchAdd;
	}
	public void setSearchAdd(String searchAdd) {
		this.searchAdd = searchAdd;
	}


	public String[] getHotelIconUrlsFileName() {
		return hotelIconUrlsFileName;
	}


	public void setHotelIconUrlsFileName(String[] hotelIconUrlsFileName) {
		this.hotelIconUrlsFileName = hotelIconUrlsFileName;
	}


	public String[]  getTeles() {
		return teles;
	}


	public void setTeles(String[] teles) {
		this.teles = teles;
	}


	public String[] getRoomTypes() {
		return roomTypes;
	}


	public void setRoomTypes(String[] roomTypes) {
		this.roomTypes = roomTypes;
	}


	public String[] getPrices() {
		return prices;
	}


	public void setPrices(String[] prices) {
		this.prices = prices;
	}


	public File[] getHotelIconUrls() {
		return hotelIconUrls;
	}


	public void setHotelIconUrls(File[] hotelIconUrls) {
		this.hotelIconUrls = hotelIconUrls;
	}


	public HotelModel getHotel() {
		return hotel;
	}


	public void setHotel(HotelModel hotel) {
		this.hotel = hotel;
	}


	public String getContentStr() {
		return contentStr;
	}


	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}
	
	
	
	
	

}

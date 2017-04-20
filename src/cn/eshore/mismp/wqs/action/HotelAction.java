package cn.eshore.mismp.wqs.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.eshore.mismp.util.ClobUtil;
import cn.eshore.mismp.util.FileUploader;
import cn.eshore.mismp.util.FileUtil;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.HotelModel;
import cn.eshore.mismp.wqs.model.IconMappingModel;
import cn.eshore.mismp.wqs.model.RoomTypeModel;

public class HotelAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String actionName;
	private String forward;
	private String tips;
	private String searchName;
	private String searchAdd;
	private HotelModel hotel;
	private String roomTypeStr;
	private String contentStr;
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	private String HOTEL_FILE_PATH = MobileGlobals.getProperty("path.local.hotel");
	private String HOTEL_FILE_PATH_ICON = MobileGlobals.getProperty("path.local.hotel.icon");
	private String[] hotelIconUrlsFileName;
	private File[] hotelIconUrls;
	
	public String getHOTEL_FILE_PATH_ICON() {
		return HOTEL_FILE_PATH_ICON;
	}

	public void setHOTEL_FILE_PATH_ICON(String hOTEL_FILE_PATH_ICON) {
		HOTEL_FILE_PATH_ICON = hOTEL_FILE_PATH_ICON;
	}
	
	public String[] getHotelIconUrlsFileName() {
		return hotelIconUrlsFileName;
	}

	public void setHotelIconUrlsFileName(String[] hotelIconUrlsFileName) {
		this.hotelIconUrlsFileName = hotelIconUrlsFileName;
	}

	public File[] getHotelIconUrls() {
		return hotelIconUrls;
	}

	public void setHotelIconUrls(File[] hotelIconUrls) {
		this.hotelIconUrls = hotelIconUrls;
	}

	public String getFILE_UPLOAD_PATH() {
		return FILE_UPLOAD_PATH;
	}

	public void setFILE_UPLOAD_PATH(String fILE_UPLOAD_PATH) {
		FILE_UPLOAD_PATH = fILE_UPLOAD_PATH;
	}

	public String getHOTEL_FILE_PATH() {
		return HOTEL_FILE_PATH;
	}

	public void setHOTEL_FILE_PATH(String hOTEL_FILE_PATH) {
		HOTEL_FILE_PATH = hOTEL_FILE_PATH;
	}

	public String getRoomTypeStr() {
		return roomTypeStr;
	}

	public void setRoomTypeStr(String roomTypeStr) {
		this.roomTypeStr = roomTypeStr;
	}

	public String getContentStr() {
		return contentStr;
	}

	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}

	public HotelModel getHotel() {
		return hotel;
	}

	public void setHotel(HotelModel hotel) {
		this.hotel = hotel;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getHotelList(){
		log.debug("【getHotelList】... ");
		this.actionName="getFarmProduceList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getHotelList(searchName,searchAdd,String.valueOf(pageNum), String.valueOf(pageSize));
//			Pagination pageList = this.businessSupportService.getWqsTravelService().getHotelList(String.valueOf(pageNum), String.valueOf(pageSize));
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
	
	public String getHotelDetail(){
		log.debug("【getHotelDetail】... ");
		this.actionName="getFarmProduceList.action?searchName="+searchName+"&searchAdd="+searchAdd;
		try {
			List iconList = this.businessSupportService.getWqsTravelService().getHotelIconById(Integer.parseInt(id));
			List roomTypeList = this.businessSupportService.getWqsTravelService().getHotelRoomType(id);
			hotel = this.businessSupportService.getWqsTravelService().getHotelById(Integer.parseInt(id));
			if ((iconList != null) && (iconList.size() > 0)) {
				getRequest().setAttribute("iconList", iconList);
			}
			if ((roomTypeList != null) && (roomTypeList.size() > 0)) {
				getRequest().setAttribute("roomTypeList", roomTypeList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
	}
	
	public String doHotelAdd() {
		log.debug("[doHotelAdd]...");
		return SUCCESS;
	}
	
	public String doHotelSave() {
		log.debug("[doHotelSave]...");
		this.forward = "wqstravel/doHotelAdd.action?searchName="+searchName+"&searchAdd="+searchAdd;
		if(hotel != null){
			this.tips = "添加失败,请重试!";	
			//---start---存储HTML介绍文件---
			//生成一个HTML文件,并且得到其路径
			File saveDirectory = new File(this.FILE_UPLOAD_PATH + this.HOTEL_FILE_PATH);
			// 如果上传文件的存放路径文件夹不存在，则创建
			if (!saveDirectory.isDirectory()) {
				saveDirectory.mkdirs();
				log.info("创建" +saveDirectory + "目录");
			}
			String html = getHtmlString(hotel.getName(),contentStr);
			String htmlFileName = System.currentTimeMillis()+".html";
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.HOTEL_FILE_PATH, html, htmlFileName, false);//覆盖原有的HTML文件
			if(!b) {
				this.tips = "介绍文件保存失败！";
				return SUCCESS;
			}
			hotel.setHtmlInfo(HOTEL_FILE_PATH+htmlFileName);//设置HTML文件的路径
			hotel.setHtmlContent(contentStr);//保存HTML内容
			//---end---存储HTML介绍文件---
			
			//---start---存储内容图片文件---
			long returnData = 0L;
			for(int index=0;index<this.hotelIconUrls.length;index++){
				returnData = 0L;
				returnData = FileUploader.upload(this.hotelIconUrls[index], this.FILE_UPLOAD_PATH + this.HOTEL_FILE_PATH_ICON,
						this.hotelIconUrlsFileName[index], false);
				if (returnData > 0L) {
					//存储成功
					log.debug(hotelIconUrlsFileName[index]+"存储成功");
				} else {
					this.forward = "wqstravel/doHotelAdd.action?searchName="+searchName+"&searchAdd="+searchAdd;
					if (returnData == -1L) {
						//上传失败后删除可能已存储的图片,以及HTML文件
						File htmlFile = new File(this.FILE_UPLOAD_PATH+this.HOTEL_FILE_PATH+htmlFileName);
						this.deleteFile(htmlFile);
						for(int i=0;i<=index;i++){
							File contentFile = new File(this.FILE_UPLOAD_PATH+this.HOTEL_FILE_PATH_ICON+this.hotelIconUrlsFileName[i]);
							this.deleteFile(contentFile);
						}
						
						this.tips = "酒店介绍图片文件为空，请重新上传！";
						return "success";
					}
					if (returnData == -2L) {
						//上传失败后删除可能已存储的图片,以及HTML文件
						File htmlFile = new File(this.FILE_UPLOAD_PATH+this.HOTEL_FILE_PATH+htmlFileName);
						this.deleteFile(htmlFile);
						for(int i=0;i<=index;i++){
							File contentFile = new File(this.FILE_UPLOAD_PATH+this.HOTEL_FILE_PATH_ICON+this.hotelIconUrlsFileName[i]);
							this.deleteFile(contentFile);
						}
						
						this.tips = "酒店介绍图片上传异常，请重新上传！";
						return "success";
					}
					if (returnData == -3L) {
						//上传失败后删除可能已存储的图片,以及HTML文件
						File htmlFile = new File(this.FILE_UPLOAD_PATH+this.HOTEL_FILE_PATH+htmlFileName);
						this.deleteFile(htmlFile);
						for(int i=0;i<index;i++){//不能<=，因为等于的话会将同名文件删除
							File contentFile = new File(this.FILE_UPLOAD_PATH+this.HOTEL_FILE_PATH_ICON+this.hotelIconUrlsFileName[i]);
							this.deleteFile(contentFile);
						}	
						this.tips = "酒店介绍图片已存在同名文件，请改名后上传！";
						return "success";
					}
				}	
			}
			//---end---存储内容图片文件---
			
			try {
				int d = this.businessSupportService.getWqsTravelService().doHotelSave(hotel);//保存酒店基本信息
				if (d <= 0) {//保存失败
					return SUCCESS;
				}
				
				String[] roomTypeAndPrices = this.roomTypeStr.split(",");
				for(String str:roomTypeAndPrices){//保存酒店房间类型
					String[] typeAndPrice = str.split("/");
					RoomTypeModel roomType = new RoomTypeModel();
					roomType.setHotelid(hotel.getId());
					roomType.setName(typeAndPrice[0]);
					roomType.setPrice(typeAndPrice[1]);
					d = this.businessSupportService.getWqsTravelService().doHotelRoomTypeSave(roomType);
					if (d <= 0) {//保存失败，删除可能已存储的图片,以及HTML文件
						File htmlFile = new File(this.FILE_UPLOAD_PATH+this.HOTEL_FILE_PATH+htmlFileName);
						this.deleteFile(htmlFile);
						for(int i=0;i<this.hotelIconUrls.length;i++){
							File contentFile = new File(this.FILE_UPLOAD_PATH+this.HOTEL_FILE_PATH_ICON+this.hotelIconUrlsFileName[i]);
							this.deleteFile(contentFile);
						}	
						return SUCCESS;
					}
				}
				
				for(int index=0;index<this.hotelIconUrls.length;index++){//保存酒店图片信息
					IconMappingModel iconMapping = new IconMappingModel();
					iconMapping.setForeignId(hotel.getId());
					iconMapping.setName(hotelIconUrlsFileName[index]);
					iconMapping.setPath(HOTEL_FILE_PATH_ICON+hotelIconUrlsFileName[index]);
					d = this.businessSupportService.getWqsTravelService().doHotelIconSave(iconMapping);
					if(d <= 0){//保存失败，删除可能已存储的图片,以及HTML文件
						File htmlFile = new File(this.FILE_UPLOAD_PATH+this.HOTEL_FILE_PATH+htmlFileName);
						this.deleteFile(htmlFile);
						for(int i=0;i<this.hotelIconUrls.length;i++){
							File contentFile = new File(this.FILE_UPLOAD_PATH+this.HOTEL_FILE_PATH_ICON+this.hotelIconUrlsFileName[i]);
							this.deleteFile(contentFile);
						}	
						return SUCCESS;
					}
				}
				
				this.tips = "添加成功！";
				this.forward = "wqstravel/getHotelList.action?searchName="+searchName+"&searchAdd="+searchAdd;
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				return SUCCESS;
			}
		}
		else
		{
			log.debug("[HotelNull]...");
			this.tips = "添加失败,请重试!";
		}
		return SUCCESS;				
	}
	
	public String doHotelDelete(){
		log.debug("doHotelDelete");
		this.tips = "删除失败,请重试!";
		this.forward =  "wqstravel/getHotelList.action?searchName="+searchName+"&searchAdd="+searchAdd;
		this.hotel=this.businessSupportService.getWqsTravelService().getHotelById(Integer.parseInt(id));
		try {
			List<IconMappingModel> iconList = this.businessSupportService.getWqsTravelService().getHotelIconById(Integer.parseInt(id));//先获取数据库里酒店图片信息
			int d=0;
			if(iconList!=null&&iconList.size()>0){
				d = this.businessSupportService.getWqsTravelService().doHotelIconDeleteByHotelId(id);//删除数据库里酒店图片信息
			}else if(iconList!=null&&iconList.size()==0){
				d=1;
			}
			log.debug("D1:"+d);
			if(d <= 0 ) {// 删除失败
				return SUCCESS;
			}else{//数据库记录删除成功，接着删除图片文件
				for(IconMappingModel icon:iconList){
					File hotelIcon = new File(this.FILE_UPLOAD_PATH+icon.getPath());
					if(hotelIcon.exists()) {
						log.debug("Delete"+this.FILE_UPLOAD_PATH+icon.getPath());
						hotelIcon.delete();
					}
				}
			}
			d = this.businessSupportService.getWqsTravelService().doHotelRoomTypeDeleteByHotelId(id);//删除数据库里酒店房间信息
			log.debug("D2:"+d);
			if(d <= 0 ) {// 删除失败
				return SUCCESS;
			}
			d = this.businessSupportService.getWqsTravelService().doHotelDelete(id);//删除数据库里酒店基本信息
			log.debug("D3:"+d);
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
	
	private void deleteFile(File file){
		//用来上传失败后删除文件
		if(file.exists()){
			file.delete();
		}
	}
	
	
	/**
	 * 跳转到页面更新页
	 * @return
	 */
	public String toEditHotel(){
		hotel = this.businessSupportService.getWqsTravelService().getHotelById(Integer.parseInt(id));
		hotel.setHtmlContent(ClobUtil.clobToString(hotel.getHtmlvalue()));
		List iconList = this.businessSupportService.getWqsTravelService().getHotelIconById(Integer.parseInt(id));
		List roomTypeList = this.businessSupportService.getWqsTravelService().getHotelRoomType(id);
		if ((iconList != null) && (iconList.size() > 0)) {
			getRequest().setAttribute("iconList", iconList);
		}
		if ((roomTypeList != null) && (roomTypeList.size() > 0)) {
			int length=roomTypeList.size();
			RoomTypeModel vo =null;
			if(length<10){
				int len=10-length;
				for(int i=0;i<len;i++){
					vo = new RoomTypeModel();
					roomTypeList.add(vo);
				}
			}
			getRequest().setAttribute("roomTypeList", roomTypeList);
		}else{
			roomTypeList=new ArrayList();
			RoomTypeModel vo =null;
			for(int i=0;i<10;i++){
				vo = new RoomTypeModel();
				roomTypeList.add(vo);
			}
			getRequest().setAttribute("roomTypeList", roomTypeList);
		}
		getRequest().setAttribute("test", "测试");
		return SUCCESS;
		
		
	}
	
	
	
	public String doHotelEdit(){
		log.debug("[doHotelEdit]...");
		this.forward = "wqstravel/getHotelList.action?searchName="+searchName+"&searchAdd="+searchAdd;
		
		HotelModel  oldHotelModel= this.businessSupportService.getWqsTravelService().getHotelById(Integer.parseInt(id));
		
		
		File saveDirectory = new File(this.FILE_UPLOAD_PATH + this.HOTEL_FILE_PATH);
		// 如果上传文件的存放路径文件夹不存在，则创建
		if (!saveDirectory.isDirectory()) {
			saveDirectory.mkdirs();
			log.info("创建" +saveDirectory + "目录");
		}
		String html = getHtmlString(hotel.getName(),contentStr);
		String htmlFileName = System.currentTimeMillis()+".html";
		boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.HOTEL_FILE_PATH, html, htmlFileName, false);//覆盖原有的HTML文件
		if(!b) {
			this.tips = "介绍文件保存失败！";
			return SUCCESS;
		}
		
		String oldHtmlPath=oldHotelModel.getHtmlInfo();
		String newHtmlPath=HOTEL_FILE_PATH+htmlFileName;
		oldHotelModel.setHtmlInfo(newHtmlPath);//设置HTML文件的路径
		oldHotelModel.setHtmlContent(contentStr);
		oldHotelModel.setAddr(hotel.getAddr());
		oldHotelModel.setName(hotel.getName());
		oldHotelModel.setTele(hotel.getTele());
		
		
		int d=this.businessSupportService.getWqsTravelService().doHotelUpdate(oldHotelModel);
		if(d>0){
			this.tips="修改成功!";
			File file=new File(this.FILE_UPLOAD_PATH+oldHtmlPath);
			this.deleteFile(file);
			
			
			//更新房间类型
			d = this.businessSupportService.getWqsTravelService().doHotelRoomTypeDeleteByHotelId(id);
			String[] roomTypeAndPrices = this.roomTypeStr.split(",");
			for(String str:roomTypeAndPrices){//保存酒店房间类型
				String[] typeAndPrice = str.split("/");
				RoomTypeModel roomType = new RoomTypeModel();
				roomType.setHotelid(Integer.valueOf(id));
				roomType.setName(typeAndPrice[0]);
				roomType.setPrice(typeAndPrice[1]);
				this.businessSupportService.getWqsTravelService().doHotelRoomTypeSave(roomType);
			}
			
			//更新房间图片
			if(hotelIconUrls!=null&&hotelIconUrlsFileName!=null){
				List<IconMappingModel> iconList = this.businessSupportService.getWqsTravelService().getHotelIconById(Integer.parseInt(id));
			    d = this.businessSupportService.getWqsTravelService().doHotelIconDeleteByHotelId(id);//删除数据库里酒店图片信息
			    if(d>0){
			    	for(IconMappingModel iconModel:iconList){
			    		file=new File(this.FILE_UPLOAD_PATH+iconModel.getPath());
			    		this.deleteFile(file);
			    	}
			    	long returnData=0L;
			    	for(int index=0;index<this.hotelIconUrls.length;index++){//保存酒店图片信息
			    		returnData = FileUploader.upload(this.hotelIconUrls[index], this.FILE_UPLOAD_PATH + this.HOTEL_FILE_PATH_ICON,
			    				this.hotelIconUrlsFileName[index], false);
			    		if (returnData > 0L) {
			    			IconMappingModel iconMapping = new IconMappingModel();
			    			iconMapping.setForeignId(Integer.valueOf(id));
			    			iconMapping.setName(hotelIconUrlsFileName[index]);
			    			iconMapping.setPath(HOTEL_FILE_PATH_ICON+hotelIconUrlsFileName[index]);
			    			d = this.businessSupportService.getWqsTravelService().doHotelIconSave(iconMapping);
			    		}
			    	}
			    }
			}
		}else{
			this.tips="修改失败!";
			File file=new File(this.FILE_UPLOAD_PATH+newHtmlPath);
			this.deleteFile(file);
			return SUCCESS;
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private String getHtmlString(String title,String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html><html lang='zh-CN'>");
		sb.append("<head>"
			+"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>"
			+"<meta http-equiv=\"Content-Language\" content=\"zh-CN\"/>"
			+"<title>"+title+"</title>"
			+"<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/main.css\">"
			+"</head>"
			+"<body>");
		sb.append(content);
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
}

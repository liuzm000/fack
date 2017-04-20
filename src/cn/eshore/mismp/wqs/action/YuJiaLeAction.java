package cn.eshore.mismp.wqs.action;

import java.io.File;
import java.util.List;

import cn.eshore.mismp.util.ClobUtil;
import cn.eshore.mismp.util.FileUploader;
import cn.eshore.mismp.util.FileUtil;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.StringUtil;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.IconMappingModel;
import cn.eshore.mismp.wqs.model.YujialeModel;
@SuppressWarnings("unchecked")
public class YuJiaLeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionName;
	private String forward;
	private String tips;
	
	private int id;
	private String name;
	private String contentStr;
	private String[] hotelIconUrlsFileName;
	private File[] hotelIconUrls;
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	
	private String ICON_PATH="/yujiale/";
	
	public String getYujiaInfo(){
		this.log.debug("[getYujiaInfo]...");
		this.actionName = "getYujiaInfo.action";
		List<YujialeModel> pageList = this.businessSupportService.getWqsTravelService().getYujiaInfo(String.valueOf(pageNum),String.valueOf(pageSize));
		if(pageList!=null&&pageList.size()>0){
			this.getRequest().setAttribute("pageList", pageList);
		}
		return SUCCESS;
	}
	public String toEditYuJiaLe(){
		this.actionName = "getYujiaInfo.action";
		YujialeModel yujialeModel= this.businessSupportService.getWqsTravelService().getYujiaInfoById(id);
		if(yujialeModel!=null){
			contentStr=ClobUtil.clobToString(yujialeModel.getContent());
			this.getRequest().setAttribute("model", yujialeModel);
			
		}
		return SUCCESS;
	}
	public String doYuJiaLeEdit(){
		log.debug("doYuJiaLeEdit");
		this.tips = "修改失败,请稍后再试!";
		this.forward =  "wqstravel/getYujiaInfo.action";
		YujialeModel oldYujialeModel= this.businessSupportService.getWqsTravelService().getYujiaInfoById(id);
		oldYujialeModel.setName(this.name);
		if(oldYujialeModel!=null){
			String newHtmlPath=null;
			String oldHtmlPath=oldYujialeModel.getDesc();
			if(this.contentStr!=null){
				String html = StringUtil.getHtmlString(this.name,this.contentStr);
				String htmlFileName = System.currentTimeMillis()+".html";
				boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.ICON_PATH, html, htmlFileName, false);
				if(!b) {
					this.tips = "保存介绍文件失败,请稍后 再试!";
					return SUCCESS;
				}
				newHtmlPath=this.ICON_PATH+htmlFileName;
				oldYujialeModel.setDesc(newHtmlPath);
				oldYujialeModel.setContentStr(this.contentStr);
			}
			
			String oldIcons=oldYujialeModel.getIcons();
			String newIcons="";
			if(hotelIconUrlsFileName!=null&&hotelIconUrls!=null){
				int length=hotelIconUrlsFileName.length;
				long returnData = 0L;
				String newFileName;
				for(int index=0;index<length;index++){
					newFileName=StringUtil.newFileName(this.hotelIconUrlsFileName[index]);
					returnData = FileUploader.upload(this.hotelIconUrls[index], this.FILE_UPLOAD_PATH + this.ICON_PATH, newFileName, false);
					//将上传成功的图片的记录保存到数据库
					if (returnData > 0L) {
						if(newIcons!=null&&!"".equals(newIcons)) newIcons+=",";
						newIcons+=this.ICON_PATH+newFileName;
					}
				}
			}
			
			if(newIcons!=null&&!"".equals(newIcons)) oldYujialeModel.setIcons(newIcons);
			int d=this.businessSupportService.getWqsTravelService().doYujiaInfoUpdate(oldYujialeModel);
			
			if(d>0){
				this.tips="修改成功";
				File file=new File(this.FILE_UPLOAD_PATH +oldHtmlPath);
				this.deleteFile(file);
				String[] iconList=oldIcons.split(",");
				for(String icon:iconList){
					file=new File(this.FILE_UPLOAD_PATH +icon);
					this.deleteFile(file);
				}
			}else{
				File file=new File(this.FILE_UPLOAD_PATH +newHtmlPath);
				this.deleteFile(file);
				if(newIcons!=null&&!"".equals(newIcons)){
					String[] iconList=newIcons.split(",");
					for(String icon:iconList){
						file=new File(this.FILE_UPLOAD_PATH +icon);
						this.deleteFile(file);
					}
				}
			}
			
		}else{
			this.tips = "该记录不存在!";
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentStr() {
		return contentStr;
	}
	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
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

	
	
	
	
	
	
	
	
}

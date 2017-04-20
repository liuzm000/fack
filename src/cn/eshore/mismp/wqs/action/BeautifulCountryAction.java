package cn.eshore.mismp.wqs.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.eshore.mismp.util.FileReadAndWrite;
import cn.eshore.mismp.util.FileUploader;
import cn.eshore.mismp.util.FileUtil;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.StringUtil;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.BeautifulCountryModel;
import cn.eshore.mismp.wqs.model.BeautifulCountryVideoModel;


public class BeautifulCountryAction extends BaseAction {
	
	private String actionName;
	private String forward;
	private String tips;
	
	
	private String searchCountryName;
	
	/*添加乡村的参数*/
	private String countryName;
	private String[] contentFileUrlsFileName;
	private File[] contentFileUrls;
	private String videoIconUrlFileName;
	private File videoIconUrl;
	private String videoFileUrlFileName;
	private File videoFileUrl;
	private String countryDesc;
	private String countryContent;
	private String preCountryContent;//正文内容修改前的数据
	private String preIcon;//之前的图片集
	
	private long countryId;
	
	
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	private String ICON_PATH = MobileGlobals.getProperty("path.beauCountry.icon");
	private String VIDEO_PATH = MobileGlobals.getProperty("path.beauCountry.video");
	private String HTML_PATH = MobileGlobals.getProperty("path.beauCountry.html");
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getBeautifulCountryList(){
		this.log.debug("[getBeautifulCountryList]...");
		this.actionName = "getBeautifulCountryList.action";
		try {
			Pagination dataList=this.businessSupportService.getWqsTravelService().getBeautifulCountryList(searchCountryName,pageNum, pageSize);
			if ((dataList != null) && (dataList.size() > 0)) {
				this.getRequest().setAttribute("pageList", dataList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("在查询美丽乡村列表时，出现异常："+e.toString());
		}
		
		return ERROR;
	}
	
	
	
	public String toAddBeautifulCountry(){
		log.debug("[toAddBeautifulCountry]...");
		return SUCCESS;
	}
	
	/*保存乡村信息*/
	public String doCountrySave(){            
		log.debug("[doCountrySave]...");
		tips="添加成功!";
		forward="wqstravel/getBeautifulCountryList.action?searchCountryName="+searchCountryName;
		
		BeautifulCountryModel beautifulCountryModel=new BeautifulCountryModel();
		beautifulCountryModel.setName(countryName);
		beautifulCountryModel.setDescript(countryDesc);
		/*先保存图片和文件*/
		long returnData = 0L;
		String newFileName;
		String iconPaths="";//各幅图片路径的字符串
		if(contentFileUrlsFileName!=null&&!"".equals(contentFileUrlsFileName)&&contentFileUrls!=null){
			for(int i=0,len=contentFileUrls.length;i<len;i++){
				newFileName=StringUtil.newFileName(contentFileUrlsFileName[i]);//重新定义文件名
				//保存图标图片
				returnData = FileUploader.upload(contentFileUrls[i] , this.FILE_UPLOAD_PATH + this.ICON_PATH, newFileName, false);
				if (returnData > 0L) {
					if(iconPaths!=null&&!"".equals(iconPaths)) iconPaths+=",";
					iconPaths+=this.ICON_PATH+newFileName;
				} else {
					this.forward = "wqstravel/toAddBeautifulCountry.action?searchCountryName="+searchCountryName;
					if (returnData == -1L) {
						//上传失败后删除可能已存储的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.ICON_PATH+newFileName);
						this.deleteFile(iconFile);
						this.tips = "图片文件为空，请重新上传！";
						return SUCCESS;
					}
					if (returnData == -2L) {
						//上传失败后删除可能已存储的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.ICON_PATH+newFileName);
						this.deleteFile(iconFile);
						
						this.tips = "图片上传异常，请重新上传！";
						return SUCCESS;
					}
					if (returnData == -3L) {
						this.tips = "图片已存在同名文件，请改名后上传！";
						return SUCCESS;
					}
				}
			}
			beautifulCountryModel.setIcon(iconPaths);
		}
		
		/*文本内容*/
		String htmlPath=null;
		if(countryContent!=null&&!"".equals(countryContent)){
			String html = getHtmlString(this.countryName,countryContent);
			String fileName =StringUtil.newFileName("fdsad.html");// System.currentTimeMillis() + ".html";
			File path = new File(this.FILE_UPLOAD_PATH +this.HTML_PATH  );
			if (!path.exists()) {
				path.mkdirs();
			}
			boolean b = new FileUtil().write(this.FILE_UPLOAD_PATH +this.HTML_PATH, html, fileName, false);// 覆盖原有的HTML文件
			if (!b) {
				this.forward = "wqstravel/toAddBeautifulCountry.action?searchCountryName="+searchCountryName;
				this.tips = "正文内容有误";
				return SUCCESS;
			}
			htmlPath=this.HTML_PATH+fileName;
			beautifulCountryModel.setFileUrl(htmlPath);
		}
		
		
		BeautifulCountryVideoModel beautifulCountryVideoModel=null;
		if(videoFileUrlFileName!=null&&videoFileUrl!=null&&videoIconUrlFileName!=null&&videoIconUrl!=null){
			beautifulCountryVideoModel=new BeautifulCountryVideoModel();
			//保存视频图标
			newFileName=StringUtil.newFileName(videoIconUrlFileName);//重新定义文件名
			returnData = FileUploader.upload(videoIconUrl , this.FILE_UPLOAD_PATH + this.VIDEO_PATH , newFileName, false);
			if (returnData > 0L) {
				beautifulCountryVideoModel.setIconName(videoIconUrlFileName);
				beautifulCountryVideoModel.setIconPath( this.VIDEO_PATH+newFileName);
			} else {
				this.forward = "wqstravel/toAddBeautifulCountry.action?searchCountryName="+searchCountryName;
				if (returnData == -1L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.VIDEO_PATH+newFileName);
					this.deleteFile(iconFile);
					this.tips = "图片文件为空，请重新上传！";
					return SUCCESS;
				}
				if (returnData == -2L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.VIDEO_PATH+newFileName);
					this.deleteFile(iconFile);
					
					this.tips = "图片上传异常，请重新上传！";
					return SUCCESS;
				}
				if (returnData == -3L) {
					this.tips = "图片已存在同名文件，请改名后上传！";
					return SUCCESS;
				}
			}
			
			
			
			//保存视频
			newFileName=StringUtil.newFileName(videoFileUrlFileName);//重新定义文件名
			returnData = FileUploader.upload(videoFileUrl , this.FILE_UPLOAD_PATH + this.VIDEO_PATH , newFileName, false);
			if (returnData > 0L) {
				beautifulCountryVideoModel.setVideoName (videoFileUrlFileName);
				beautifulCountryVideoModel.setVideoPath( this.VIDEO_PATH+newFileName);
			} else {
				this.forward = "wqstravel/toAddBeautifulCountry.action?searchCountryName="+searchCountryName;
				if (returnData == -1L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.VIDEO_PATH+newFileName);
					this.deleteFile(iconFile);
					this.tips = "图片文件为空，请重新上传！";
					return SUCCESS;
				}
				if (returnData == -2L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.VIDEO_PATH+newFileName);
					this.deleteFile(iconFile);
					
					this.tips = "图片上传异常，请重新上传！";
					return SUCCESS;
				}
				if (returnData == -3L) {
					this.tips = "图片已存在同名文件，请改名后上传！";
					return SUCCESS;
				}
			}
			
		}
		
		int countryId=this.getBusinessSupportService().getWqsTravelService().doBeautifulCountrySave(beautifulCountryModel);
		if(countryId>0){
			if(beautifulCountryVideoModel!=null){
				beautifulCountryVideoModel.setCountryID(countryId);
				this.getBusinessSupportService().getWqsTravelService().doBeautifulCountryVideoSave(beautifulCountryVideoModel);
			}
		}else{
			this.tips="添加失败！";
			this.forward = "wqstravel/toAddBeautifulCountry.action?searchCountryName="+searchCountryName;
		}
		
		
		
		return SUCCESS;
	}
	
	
	
	
	
	public String doBeautifulCountryDelete(){
		log.debug("doBeautifulCountryDelete");
		this.tips = "删除失败,请重试!";
		this.forward =  "wqstravel/getBeautifulCountryList.action?searchCountryName="+searchCountryName;	
		
		int d;
		File file; 
		String filePath;
		BeautifulCountryModel beautifulCountryModel= this.businessSupportService.getWqsTravelService().getBeautifulCountry(  countryId);
		if(beautifulCountryModel!=null){
			d=this.businessSupportService.getWqsTravelService().doBeautifulCountryDel(countryId);
			if(d>0){
				this.tips = "删除成功!";
				String icon=beautifulCountryModel.getIcon();//逗号间隔开
				String fileHtml=beautifulCountryModel.getFileUrl();
				if(fileHtml!=null){
					file= new File(this.FILE_UPLOAD_PATH+fileHtml);
					this.deleteFile(file);
				}
				if(icon!=null&&!"".equals(icon)){
					String[] iconPaths=icon.split(",");
					for(String iconPath:iconPaths){
						file= new File(this.FILE_UPLOAD_PATH+iconPath);
						this.deleteFile(file);
					}
				}
				
				List<BeautifulCountryVideoModel> videoModelList=this.businessSupportService.getWqsTravelService().getBeautifulCountryVideoList(countryId);
				if(videoModelList!=null&&videoModelList.size()>0){
					d=this.businessSupportService.getWqsTravelService().doBeautifulCountryVideoDel(countryId);
					if(d>0){
						for(BeautifulCountryVideoModel videoModel:videoModelList){
							filePath=videoModel.getIconPath();
							if(filePath!=null){
								file= new File(this.FILE_UPLOAD_PATH+filePath);
								this.deleteFile(file);
							}
							filePath=videoModel.getVideoPath();
							if(filePath!=null){
								file= new File(this.FILE_UPLOAD_PATH+filePath);
								this.deleteFile(file);
							}
						}
					}
				}
			}else{
				return SUCCESS;
			}
		}else{
			this.tips = "该记录不存在!";
			return SUCCESS;
		}
		
		return SUCCESS;
		
		
		
	}
	
	
	
	
	public String toEditBeautifulCountry(){
		log.debug("[toEditBeautifulCountry]...");
		
		BeautifulCountryModel countryModel=this.businessSupportService.getWqsTravelService().getBeautifulCountryAndVideo(countryId);
		if(countryModel!=null){
			String filePath=countryModel.getFileUrl();
			if(filePath!=null&&!"".equals(filePath)){
				String htmlStream=FileReadAndWrite.readFile( this.FILE_UPLOAD_PATH+filePath,"UTF-8");
				String beginRegx="<body>";
				String closeRegx="</body>";
				htmlStream=StringUtils.substringBetween(htmlStream, beginRegx, closeRegx);
				countryModel.setFileUrl(htmlStream);
			}
			this.getRequest().setAttribute("model",countryModel ); 
		}
		
		return SUCCESS;
	}
	
	
	public String doBeautifulCountryEdit(){
		log.debug("[doBeautifulCountryEdit]...");
		
		tips="修改成功!";
		forward="wqstravel/getBeautifulCountryList.action?searchCountryName="+searchCountryName;
		
		BeautifulCountryModel countryModel=this.businessSupportService.getWqsTravelService().getBeautifulCountryAndVideo(countryId);
		
		
		String iconPaths=countryModel.getIcon();//各幅图片路径的字符串
		BeautifulCountryModel beautifulCountryModel=new BeautifulCountryModel();
		beautifulCountryModel.setId(countryId);
		beautifulCountryModel.setName(countryName);
		beautifulCountryModel.setDescript(countryDesc);
		beautifulCountryModel.setIcon(iconPaths);
		/*先保存图片和文件*/
		long returnData = 0L;
		String newFileName;
		if(contentFileUrlsFileName!=null&&!"".equals(contentFileUrlsFileName)&&contentFileUrls!=null){
			for(int i=0,len=contentFileUrls.length;i<len;i++){
				newFileName=StringUtil.newFileName(contentFileUrlsFileName[i]);//重新定义文件名
				//保存图标图片
				returnData = FileUploader.upload(contentFileUrls[i] , this.FILE_UPLOAD_PATH + this.ICON_PATH, newFileName, false);
				if (returnData > 0L) {
					if(iconPaths!=null&&!"".equals(iconPaths)) iconPaths+=",";
					iconPaths+=this.ICON_PATH+newFileName;
				} else {
					this.forward = "wqstravel/toAddBeautifulCountry.action?searchCountryName="+searchCountryName;
					if (returnData == -1L) {
						//上传失败后删除可能已存储的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.ICON_PATH+newFileName);
						this.deleteFile(iconFile);
						this.tips = "图片文件为空，请重新上传！";
						return SUCCESS;
					}
					if (returnData == -2L) {
						//上传失败后删除可能已存储的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.ICON_PATH+newFileName);
						this.deleteFile(iconFile);
						
						this.tips = "图片上传异常，请重新上传！";
						return SUCCESS;
					}
					if (returnData == -3L) {
						this.tips = "图片已存在同名文件，请改名后上传！";
						return SUCCESS;
					}
				}
			}
			beautifulCountryModel.setIcon(iconPaths);
		}
		
		/*文本内容*/
		String htmlPath=null;
		if(countryContent!=null&&!"".equals(countryContent) ){
			String html = getHtmlString(this.countryName,countryContent);
			String fileName =StringUtil.newFileName("fdsad.html");// System.currentTimeMillis() + ".html";
			File path = new File(this.FILE_UPLOAD_PATH +this.HTML_PATH  );
			if (!path.exists()) {
				path.mkdirs();
			}
			boolean b = new FileUtil().write(this.FILE_UPLOAD_PATH +this.HTML_PATH, html, fileName, false);// 覆盖原有的HTML文件
			if (!b) {
				this.forward = "wqstravel/toAddBeautifulCountry.action?searchCountryName="+searchCountryName;
				this.tips = "正文内容有误";
				return SUCCESS;
			}
			htmlPath=this.HTML_PATH+fileName;
			beautifulCountryModel.setFileUrl(htmlPath);
			
			/*将之前的文件删除*/
			path=new File(this.FILE_UPLOAD_PATH +countryModel.getFileUrl());
			this.deleteFile(path);
			
		}
		
		
		
		//乡村视频
		BeautifulCountryVideoModel beautifulCountryVideoModel=null;
		if(videoFileUrlFileName!=null&&videoFileUrl!=null&&videoIconUrlFileName!=null&&videoIconUrl!=null){
			beautifulCountryVideoModel=new BeautifulCountryVideoModel();
			//保存视频图标
			newFileName=StringUtil.newFileName(videoIconUrlFileName);//重新定义文件名
			returnData = FileUploader.upload(videoIconUrl , this.FILE_UPLOAD_PATH + this.VIDEO_PATH , newFileName, false);
			if (returnData > 0L) {
				beautifulCountryVideoModel.setIconName(videoIconUrlFileName);
				beautifulCountryVideoModel.setIconPath( this.VIDEO_PATH+newFileName);
			} else {
				this.forward = "wqstravel/toAddBeautifulCountry.action?searchCountryName="+searchCountryName;
				if (returnData == -1L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.VIDEO_PATH+newFileName);
					this.deleteFile(iconFile);
					this.tips = "图片文件为空，请重新上传！";
					return SUCCESS;
				}
				if (returnData == -2L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.VIDEO_PATH+newFileName);
					this.deleteFile(iconFile);
					
					this.tips = "图片上传异常，请重新上传！";
					return SUCCESS;
				}
				if (returnData == -3L) {
					this.tips = "图片已存在同名文件，请改名后上传！";
					return SUCCESS;
				}
			}
			
			
			
			//保存视频
			newFileName=StringUtil.newFileName(videoFileUrlFileName);//重新定义文件名
			returnData = FileUploader.upload(videoFileUrl , this.FILE_UPLOAD_PATH + this.VIDEO_PATH , newFileName, false);
			if (returnData > 0L) {
				beautifulCountryVideoModel.setVideoName (videoFileUrlFileName);
				beautifulCountryVideoModel.setVideoPath( this.VIDEO_PATH+newFileName);
			} else {
				this.forward = "wqstravel/toAddBeautifulCountry.action?searchCountryName="+searchCountryName;
				if (returnData == -1L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.VIDEO_PATH+newFileName);
					this.deleteFile(iconFile);
					this.tips = "图片文件为空，请重新上传！";
					return SUCCESS;
				}
				if (returnData == -2L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.VIDEO_PATH+newFileName);
					this.deleteFile(iconFile);
					
					this.tips = "图片上传异常，请重新上传！";
					return SUCCESS;
				}
				if (returnData == -3L) {
					this.tips = "图片已存在同名文件，请改名后上传！";
					return SUCCESS;
				}
			}
			
		}
		
		
		
		
		
		
		
		
		
		int d=this.businessSupportService.getWqsTravelService().doBeautifulCountryUpdate(beautifulCountryModel);
		if(d<1){
			tips="修改失败!";
			forward="wqstravel/toEditBeautifulCountry.action?searchCountryName="+searchCountryName+"&countryId="+countryId;
		}else{
			if(beautifulCountryVideoModel!=null){
				beautifulCountryVideoModel.setCountryID(countryId);
				this.getBusinessSupportService().getWqsTravelService().doBeautifulCountryVideoSave(beautifulCountryVideoModel);
			}
		}
		return SUCCESS;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String getHtmlString(String name, String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html><html lang='zh-CN'>");
		sb.append("<head>" + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>"
				+ "<meta http-equiv=\"Content-Language\" content=\"zh-CN\"/>" + "<title>" + name + "</title>"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/main.css\">" + "</head>" + "<body>");
		sb.append(content);
		sb.append("</body>");
		sb.append("</html>");

		return sb.toString();
	}
	
	
	private void deleteFile(File file){
		//用来上传失败后删除文件
		if(file.exists()){
			file.delete();
		}
	}
	public String getSearchCountryName() {
		return searchCountryName;
	}



	public void setSearchCountryName(String searchCountryName) {
		this.searchCountryName = searchCountryName;
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



	public String getCountryName() {
		return countryName;
	}



	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public File[] getContentFileUrls() {
		return contentFileUrls;
	}



	public void setContentFileUrls(File[] contentFileUrls) {
		this.contentFileUrls = contentFileUrls;
	}




	public File getVideoIconUrl() {
		return videoIconUrl;
	}



	public void setVideoIconUrl(File videoIconUrl) {
		this.videoIconUrl = videoIconUrl;
	}



	public String getCountryDesc() {
		return countryDesc;
	}



	public void setCountryDesc(String countryDesc) {
		this.countryDesc = countryDesc;
	}



	public String getCountryContent() {
		return countryContent;
	}



	public void setCountryContent(String countryContent) {
		this.countryContent = countryContent;
	}


	public File getVideoFileUrl() {
		return videoFileUrl;
	}



	public void setVideoFileUrl(File videoFileUrl) {
		this.videoFileUrl = videoFileUrl;
	}



	public String[] getContentFileUrlsFileName() {
		return contentFileUrlsFileName;
	}



	public void setContentFileUrlsFileName(String[] contentFileUrlsFileName) {
		this.contentFileUrlsFileName = contentFileUrlsFileName;
	}



	public String getVideoIconUrlFileName() {
		return videoIconUrlFileName;
	}



	public void setVideoIconUrlFileName(String videoIconUrlFileName) {
		this.videoIconUrlFileName = videoIconUrlFileName;
	}



	public String getVideoFileUrlFileName() {
		return videoFileUrlFileName;
	}



	public void setVideoFileUrlFileName(String videoFileUrlFileName) {
		this.videoFileUrlFileName = videoFileUrlFileName;
	}



	public long getCountryId() {
		return countryId;
	}



	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}



	public String getPreCountryContent() {
		return preCountryContent;
	}



	public void setPreCountryContent(String preCountryContent) {
		this.preCountryContent = preCountryContent;
	}



	public String getPreIcon() {
		return preIcon;
	}



	public void setPreIcon(String preIcon) {
		this.preIcon = preIcon;
	}
	
	
	
	
	
	
	

}

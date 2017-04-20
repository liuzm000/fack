package cn.eshore.mismp.wqs.action;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.eshore.mismp.util.FileReadAndWrite;
import cn.eshore.mismp.util.FileUploader;
import cn.eshore.mismp.util.FileUtil;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.StringUtil;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.EcoTourismPictorialModel;
import cn.eshore.mismp.wqs.model.EcoTourismPictorialTypeModel;

public class EcoTourismAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String actionName;
	private String forward;
	private String tips;
	private String searchName;
	private long typeId;
	private int	 id;
	
	
	private String descript;
	private String contentFileUrlFileName;
	private File contentFileUrl;
	
	private String typeName;
	private String typeIconFileUrlFileName;
	private File typeIconFileUrl;
	
	private String[] contentFileUrlsFileName;
	private File[] contentFileUrls ;
	
	
	private String content;
	
	
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	private String HTML_PATH = MobileGlobals.getProperty("path.ecoTourism.html");
	private String ICON_PATH = MobileGlobals.getProperty("path.ecoTourism.icon");
	
	
	
	/**
	 * 生态旅游列表
	 * @return
	 */
	public String getEcotourismTypeList(){
		this.log.debug("[getEcotourismTypeList]...");
		this.actionName = "getEcotourismTypeList.action";
		try {
			Pagination typeList = this.businessSupportService.getWqsTravelService().getEcotourismTypeList(searchName,pageNum,pageSize);
			if ((typeList != null) && (typeList.size() > 0)) {
				this.getRequest().setAttribute("pageList", typeList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
	}
	
	
	
	public String toAddEcotourismType(){
		this.log.debug("[toAddEcotourismType]...");
		this.actionName = "toAddEcotourismType.action";
		return SUCCESS;
		
	}
	
	/*保存生态类型*/
	public String doEcotourismTypeSave(){
		this.log.debug("[doEcotourismTypeSave]...");
		this.actionName = "doEcotourismTypeSave.action";
		tips="添加成功!";
		this.forward="wqstravel/getEcotourismTypeList.action?searchName="+searchName;
		
		EcoTourismPictorialTypeModel typeModel=new EcoTourismPictorialTypeModel();
		typeModel.setTypeName(typeName);
		typeModel.setIsSkip("0");
		typeModel.setIconName(this.typeIconFileUrlFileName);
		
		
		long returnData = 0L;
		String newFileName;
		String iconPath=null;//相对路径
		newFileName=StringUtil.newFileName(this.typeIconFileUrlFileName);//重新定义文件名
		//保存图标图片
		returnData = FileUploader.upload(this.typeIconFileUrl , this.FILE_UPLOAD_PATH + this.ICON_PATH,
				newFileName, false);
		if (returnData > 0L) {
			iconPath=this.ICON_PATH+newFileName;
			typeModel.setIconPath(iconPath);
		} else {
			this.forward = "wqstravel/toAddEcotourismType.action?searchName="+searchName;
			if (returnData == -1L) {
				//上传失败后删除可能已存储的图片
				File iconFile = new File(this.FILE_UPLOAD_PATH+this.ICON_PATH+newFileName);
				this.deleteFile(iconFile);
				
				this.tips = "产品图标文件为空，请重新上传！";
				return SUCCESS;
			}
			if (returnData == -2L) {
				//上传失败后删除可能已存储的图片
				File iconFile = new File(this.FILE_UPLOAD_PATH+this.ICON_PATH+newFileName);
				this.deleteFile(iconFile);
				
				this.tips = "产品图标上传异常，请重新上传！";
				return SUCCESS;
			}
			if (returnData == -3L) {
				this.tips = "产品图标已存在同名文件，请改名后上传！";
				return SUCCESS;
			}
		}
		
		/*文本内容*/
		String htmlPath=null;
		if(content!=null&&!"".equals(content)){
			String html = StringUtil.getHtmlString(this.typeName,content);
			String fileName =StringUtil.newFileName("fdsad.html");// System.currentTimeMillis() + ".html";
			File path = new File(this.FILE_UPLOAD_PATH +this.HTML_PATH  );
			if (!path.exists()) {
				path.mkdirs();
			}
			boolean b = new FileUtil().write(this.FILE_UPLOAD_PATH +this.HTML_PATH, html, fileName, false);// 覆盖原有的HTML文件
			if (!b) {
				this.forward = "wqstravel/toAddEcotourismType.action?searchName="+searchName;
				this.tips = "正文内容有误";
				return SUCCESS;
			}
			htmlPath=this.HTML_PATH+fileName;
			typeModel.setInfoHtml(htmlPath);
		}
		
		
		
		int typeId=this.businessSupportService.getWqsTravelService().doEcotourismTypeSave(typeModel);
		if(typeId>0){
			//类型保存成功，则将相应的子列表也保存
			EcoTourismPictorialModel pritorialModel=new EcoTourismPictorialModel();
			pritorialModel.setTypeId(typeId);
			if(contentFileUrlsFileName!=null&&!"".equals(contentFileUrlsFileName)&&contentFileUrls!=null){
				String fileName;
				String descript;
				for(int i=0,len=contentFileUrls.length;i<len;i++){
					fileName=contentFileUrlsFileName[i];
					newFileName=StringUtil.newFileName(fileName);//重新定义文件名
					//保存图标图片
					returnData = FileUploader.upload(contentFileUrls[i] , this.FILE_UPLOAD_PATH + this.ICON_PATH, newFileName, false);
					if (returnData > 0L) {
						iconPath=this.ICON_PATH+newFileName;
					} else {
						this.forward = "wqstravel/toAddEcotourismType.action?searchName="+searchName;
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
					descript=fileName.substring(0,fileName.lastIndexOf("."));
					pritorialModel.setFileName(fileName);
					pritorialModel.setFilePath(iconPath);
					pritorialModel.setDescript(descript);
					this.businessSupportService.getWqsTravelService().doEcotourismPictureSave(pritorialModel);
				}
			}
			
		}else{
			this.forward = "wqstravel/toAddEcotourismType.action?searchName="+searchName;
			this.tips = "添加失败,稍后再试!";
			return SUCCESS;
		}
		return SUCCESS;
		
	}
	
	
	
	
	public String doEcotourismTypeDelete(){
		log.debug("doEcotourismTypeDelete");
		this.tips = "删除失败,请重试!";
		this.forward =  "wqstravel/getEcotourismTypeList.action?searchName="+searchName;	
		
		EcoTourismPictorialTypeModel typeModel=this.businessSupportService.getWqsTravelService(). getEcoTourismPictorialTypeModel(  typeId);
		if(typeModel!=null){
			int d;
			String filePath;
			File file;
			d=this.businessSupportService.getWqsTravelService().doEcoTourismPictorialTypeModelDel(typeId);
			if(d>0){
				this.tips = "删除成功!";
				/*删除图标*/
				filePath=this.FILE_UPLOAD_PATH+typeModel.getIconPath();
				file= new File(filePath);
				this.deleteFile(file);
				
				/*删除文本内容*/
				filePath=this.FILE_UPLOAD_PATH+typeModel.getInfoHtml();
				file= new File(filePath);
				this.deleteFile(file);
				
			    List<EcoTourismPictorialModel> pictorialModelList= this.businessSupportService.getWqsTravelService().getEcoTourismPictorialModelList(  typeId);
				if(pictorialModelList!=null&&pictorialModelList.size()>0){
					d=this.businessSupportService.getWqsTravelService().doEcoTourismPictorialModelDel(typeId);
					if(d>0){
						for(EcoTourismPictorialModel pictorialModel:pictorialModelList){
							filePath=pictorialModel.getFilePath();
							file= new File(this.FILE_UPLOAD_PATH+filePath);
							this.deleteFile(file);
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
	
	
	
	
	public String toEcotourismTypeEdit(){
		log.debug("[toEcotourismTypeEdit]...");
		
		EcoTourismPictorialTypeModel typeModel=this.businessSupportService.getWqsTravelService(). getEcoTourismPictorialTypeModel(  typeId);
		if(typeModel!=null){
			String filePath=typeModel.getInfoHtml();
			if(filePath!=null&&!"".equals(filePath)){
				String htmlStream=FileReadAndWrite.readFile( this.FILE_UPLOAD_PATH+filePath,"UTF-8");
				String beginRegx="<body>";
				String closeRegx="</body>";
				htmlStream=StringUtils.substringBetween(htmlStream, beginRegx, closeRegx);
				typeModel.setInfoHtml(htmlStream);
			}
			this.getRequest().setAttribute("model",typeModel ); 
		}
		
		return SUCCESS;
	}
	
	
	public String doEcotourismTypeEdit(){
		this.log.debug("[doEcotourismTypeEdit]...");
		this.actionName = "doEcotourismTypeEdit.action";
		this.tips="修改成功!";
		this.forward="wqstravel/getEcotourismTypeList.action?searchName="+searchName;
		
		EcoTourismPictorialTypeModel typeModel=this.businessSupportService.getWqsTravelService(). getEcoTourismPictorialTypeModel(  typeId);
		if(typeModel!=null){
			typeModel.setTypeName(typeName);
			/*文本内容*/
			String htmlPath=null;
			if(content!=null&&!"".equals(content)){
				String html = StringUtil.getHtmlString(this.typeName,content);
				String fileName =StringUtil.newFileName("fdsad.html");// System.currentTimeMillis() + ".html";
				File path = new File(this.FILE_UPLOAD_PATH +this.HTML_PATH  );
				if (!path.exists()) {
					path.mkdirs();
				}
				boolean b = new FileUtil().write(this.FILE_UPLOAD_PATH +this.HTML_PATH, html, fileName, false);// 覆盖原有的HTML文件
				if (!b) {
					this.forward = "wqstravel/toAddEcotourismType.action?searchName="+searchName;
					this.tips = "正文内容有误";
					return SUCCESS;
				}
				/*将旧文件删除*/
				htmlPath=this.FILE_UPLOAD_PATH+typeModel.getInfoHtml();
				path = new File(htmlPath );
				this.deleteFile(path);
				htmlPath=this.HTML_PATH+fileName;
				typeModel.setInfoHtml(htmlPath);
			}
			
//			更新:类型名和文本路径
			
			int d=this.businessSupportService.getWqsTravelService().doEcotourismTypeUpdate(typeModel);
			if(d>0){
				//类型保存成功，则将相应的子列表也保存
				EcoTourismPictorialModel pritorialModel ;
				if(contentFileUrlsFileName!=null&&!"".equals(contentFileUrlsFileName)&&contentFileUrls!=null){
					pritorialModel=new EcoTourismPictorialModel();
					pritorialModel.setTypeId(typeId);
					String fileName;
					String descript;
					String newFileName;
					String iconPath;
					long returnData=0L;
					for(int i=0,len=contentFileUrls.length;i<len;i++){
						fileName=contentFileUrlsFileName[i];
						newFileName=StringUtil.newFileName(fileName);//重新定义文件名
						//保存图标图片
						returnData = FileUploader.upload(contentFileUrls[i] , this.FILE_UPLOAD_PATH + this.ICON_PATH, newFileName, false);
						if (returnData > 0L) {
							iconPath=this.ICON_PATH+newFileName;
							descript=fileName.substring(0,fileName.lastIndexOf("."));
							pritorialModel.setFileName(fileName);
							pritorialModel.setFilePath(iconPath);
							pritorialModel.setDescript(descript);
							this.businessSupportService.getWqsTravelService().doEcotourismPictureSave(pritorialModel);
						}  
					}
				}
			}else{
				this.tips="修改失败!";
			}
		}else{
			tips="该记录不存在!";
			return SUCCESS;
		}
		return SUCCESS;
		
	}
	
	
	public String viewEcotourismList(){
		log.debug("viewEcotourismList");
		this.actionName = "viewEcotourismList.action";
		try {
			Pagination ecoTourismList= this.businessSupportService.getWqsTravelService().getEcoTourism(  pageNum,   pageSize,  (int)typeId);
			if ((ecoTourismList != null) && (ecoTourismList.size() > 0)) {
				this.getRequest().setAttribute("pageList", ecoTourismList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("在查询某个类型下的图片集时，出现异常："+e.toString());
		}
		return ERROR;
		
	}
	
	
	
	public String doEcotourismDelete(){
		log.debug("doEcotourismDelete");
		this.tips = "删除失败,请重试!";
		this.forward =  "wqstravel/viewEcotourismList.action?searchName="+searchName+"&typeId="+typeId;		
		EcoTourismPictorialModel pictorialModel= this.businessSupportService.getWqsTravelService().getEcoTourismPictorialModel(id);
		if(pictorialModel!=null){
			int d=this.businessSupportService.getWqsTravelService().doEcoTourismPictorialModelDelete(id);
			if(d>0){
				this.tips = "删除成功!";
				String iconPath=pictorialModel.getFilePath();
				File iconFile = new File(this.FILE_UPLOAD_PATH+iconPath);
				this.deleteFile(iconFile);
			}
		}else{
			this.tips = "该记录不存在!";
		}
		return SUCCESS;
	}
	
	
	public String toEcotourismEdit(){
		log.debug("toEcotourismEdit");
		EcoTourismPictorialModel pictorialModel= this.businessSupportService.getWqsTravelService().getEcoTourismPictorialModel(id);
		if(pictorialModel!=null){
			this.getRequest().setAttribute("model", pictorialModel);
		}
		return SUCCESS;
	}
	
	
	
	
	
	public String doEcotourismEdit(){
		log.debug("doEcotourismEdit");
		this.tips = "修改成功!";
		this.forward =  "wqstravel/viewEcotourismList.action?searchName="+searchName+"&typeId="+typeId;	
		
		EcoTourismPictorialModel pictorialModel= this.businessSupportService.getWqsTravelService().getEcoTourismPictorialModel(id);
		if(pictorialModel!=null){
			pictorialModel.setDescript(descript);
			if(contentFileUrlFileName!=null&&!"".equals(contentFileUrlFileName)&&contentFileUrl!=null){
				String newFileName=StringUtil.newFileName(contentFileUrlFileName);
				
				long returnData = FileUploader.upload(contentFileUrl, this.FILE_UPLOAD_PATH + this.ICON_PATH,
						newFileName, false);
				if (returnData > 0L) {
					//删除之前的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+pictorialModel.getFilePath());
					this.deleteFile(iconFile);
					//更新图片
					pictorialModel.setFilePath(this.ICON_PATH+newFileName);
					pictorialModel.setFileName(contentFileUrlFileName);
				} else {
					if (returnData == -2L||returnData == -1L) {
						//上传失败后删除可能已存储的图片
						this.tips = "上传图片出现异常，请稍后再试!";
						this.forward =  "wqstravel/toEcotourismEdit.action?searchName="+searchName+"&typeId="+typeId+"&id="+id;	
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.ICON_PATH+newFileName);
						this.deleteFile(iconFile);
						return SUCCESS;
					}
				}
				
			}
			
			//更新操作
			int d=this.businessSupportService.getWqsTravelService().doEcotourismPictureUpdate(pictorialModel);
			if(d<1){
				this.tips = "修改失败!";
				return SUCCESS;
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
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}



	public String getTypeName() {
		return typeName;
	}



	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	public String getTypeIconFileUrlFileName() {
		return typeIconFileUrlFileName;
	}



	public void setTypeIconFileUrlFileName(String typeIconFileUrlFileName) {
		this.typeIconFileUrlFileName = typeIconFileUrlFileName;
	}



	public File getTypeIconFileUrl() {
		return typeIconFileUrl;
	}



	public void setTypeIconFileUrl(File typeIconFileUrl) {
		this.typeIconFileUrl = typeIconFileUrl;
	}


	public String getContent() {
		return content;
	}



	public String[] getContentFileUrlsFileName() {
		return contentFileUrlsFileName;
	}



	public void setContentFileUrlsFileName(String[] contentFileUrlsFileName) {
		this.contentFileUrlsFileName = contentFileUrlsFileName;
	}



	public File[] getContentFileUrls() {
		return contentFileUrls;
	}



	public void setContentFileUrls(File[] contentFileUrls) {
		this.contentFileUrls = contentFileUrls;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public long getTypeId() {
		return typeId;
	}



	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDescript() {
		return descript;
	}



	public void setDescript(String descript) {
		this.descript = descript;
	}



	public String getContentFileUrlFileName() {
		return contentFileUrlFileName;
	}



	public void setContentFileUrlFileName(String contentFileUrlFileName) {
		this.contentFileUrlFileName = contentFileUrlFileName;
	}



	public File getContentFileUrl() {
		return contentFileUrl;
	}



	public void setContentFileUrl(File contentFileUrl) {
		this.contentFileUrl = contentFileUrl;
	}
	
}

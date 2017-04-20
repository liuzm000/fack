/**
 * 
 */
package cn.eshore.mismp.wqs.action;

import java.io.File;
import java.util.List;

import cn.eshore.mismp.util.FileUploader;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.StringUtil;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.ShaTianPictorialModel;
import cn.eshore.mismp.wqs.model.ShaTianPictorialTypeModel;

/**
 * @author wanglei
 * 
 */
public class TownPictureAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionName;
	private String forward;
	private String tips;
	
	
	
	private String picTypeName;
	private String picTypeDesc;
	
	private File picTypeIconFileUrl;
	private String picTypeIconFileUrlFileName;
	
	private String[] produceContentFileUrlsFileName;
	private File[] produceContentFileUrls;
	
	
	
	private int id;
	private int typeId;
	private String searchName;
	private String descript;
	

	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	private String PICTORIAL_PATH = MobileGlobals.getProperty("path.town.pictorial");

	
	/**
	 * 画卷类型列表
	 * @return
	 */
	public String getTownPictureList() {
		this.log.debug("[getTownPictureList]...");
		this.actionName = "getTownPictureList.action";
		try {
			Pagination typeList = this.businessSupportService.getWqsTravelService().getPictorialTypeAndPics(searchName,pageNum,pageSize);
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

	
	public String viewPicList(){
		log.debug("viewPicList");
		this.actionName = "viewPicList.action";
		try {
			Pagination picList= this.businessSupportService.getWqsTravelService(). getShaTianPictorial(  pageNum,  pageSize,id);
			if ((picList != null) && (picList.size() > 0)) {
				this.getRequest().setAttribute("pageList", picList);
			}
			return SUCCESS;
		} catch (Exception e) {
			this.log.error(e.getMessage());
			e.printStackTrace();
		}
		return ERROR;
		
	
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 跳转到画卷类型添加页面
	 * @return
	 */
	public String toAddTownPicture() {
		log.debug("[toAddTownPicture]...");
		return SUCCESS;
	}
	
	public String toPicEdit() {
		log.debug("[toPicEdit]...");
		
		ShaTianPictorialModel shaTianPictorialModel= this.businessSupportService.getWqsTravelService().getShaTianOnePictorialDelete(  id);
		
		if(shaTianPictorialModel!=null){
			this.getRequest().setAttribute("model", shaTianPictorialModel);
		}
		
		
		return SUCCESS;
	}
	
	public String toAddPicList() {
		log.debug("[toAddPicList]...");
		ShaTianPictorialTypeModel typeModel= this.businessSupportService.getWqsTravelService().getShaTianPictorial(id);
		if ( typeModel != null  ) {
			this.getRequest().setAttribute("model", typeModel);
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 保存画卷类型
	 * @return
	 */
	public String doAddTownPictureSave() {
		log.debug("[doAddTownPictureSave]...");
		tips="添加成功!";
		forward="wqstravel/getTownPictureList.action?searchName="+searchName;
		
		long returnData = 0L;
		String newFileName;
		String iconPath=null;//相对路径
		int typeId=0;
		if(id<1){//在新建画卷类型
			newFileName=this.newFileName(this.picTypeIconFileUrlFileName);//重新定义文件名
			//保存图标图片
			returnData = FileUploader.upload(this.picTypeIconFileUrl , this.FILE_UPLOAD_PATH + this.PICTORIAL_PATH,
					newFileName, false);
			if (returnData > 0L) {
				iconPath=this.PICTORIAL_PATH+newFileName;
			} else {
				this.forward = "wqstravel/toAddTownPicture.action?searchName="+searchName;
				if (returnData == -1L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.PICTORIAL_PATH+newFileName);
					this.deleteFile(iconFile);
					
					this.tips = "产品图标文件为空，请重新上传！";
					return SUCCESS;
				}
				if (returnData == -2L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.PICTORIAL_PATH+newFileName);
					this.deleteFile(iconFile);
					
					this.tips = "产品图标上传异常，请重新上传！";
					return SUCCESS;
				}
				if (returnData == -3L) {
					this.tips = "产品图标已存在同名文件，请改名后上传！";
					return SUCCESS;
				}
			}
			
			/*保存画卷类型*/
			ShaTianPictorialTypeModel param=new ShaTianPictorialTypeModel();
			param.setIconName(this.picTypeIconFileUrlFileName);
			param.setIconPath(iconPath);
			param.setTypeDesc(picTypeDesc);
			param.setTypeName(picTypeName);
			typeId= this.businessSupportService.getWqsTravelService().doShaTianPictorialSave(param);
			if (typeId < 1) {
				this.tips="添加失败！";
				this.forward =  "wqstravel/toAddTownPicture.action?searchName="+searchName;
				File iconFile = new File(this.FILE_UPLOAD_PATH+this.PICTORIAL_PATH+newFileName);
				this.deleteFile(iconFile);
				return SUCCESS;
			}
			
		}else{//为画卷类型添加画卷
			typeId=id;
			
			int d=this.businessSupportService.getWqsTravelService().doShaTianPictorialUpdate(id,picTypeName,picTypeDesc);
			if(d<0){
				this.tips="修改失败！";
				this.forward =  "wqstravel/toAddTownPicture.action?searchName="+searchName;
				return SUCCESS;
			}
			
		}
		
		
		/*保存成功，则将相应的画卷集也上传 */
		if(produceContentFileUrlsFileName!=null&&produceContentFileUrlsFileName.length>0
				&&produceContentFileUrls!=null&&produceContentFileUrls.length>0){
			int length=produceContentFileUrls.length;
			String contentPicName;
			String descName;
			int flag=0;
			ShaTianPictorialModel model ;
			for(int index=0;index<length;index++){
				returnData = 0L;
				contentPicName=this.produceContentFileUrlsFileName[index];
				newFileName=this.newFileName(contentPicName);
				returnData = FileUploader.upload(this.produceContentFileUrls[index], this.FILE_UPLOAD_PATH + this.PICTORIAL_PATH,
						newFileName, false);
				
				if (returnData > 0L) {
					iconPath=this.PICTORIAL_PATH+newFileName;
					descName=contentPicName.substring(0,contentPicName.lastIndexOf("."));//截取图片名
					 model=new ShaTianPictorialModel();
					 model.setFileName(contentPicName);
					 model.setDescript(descName);
					 model.setFilePath(iconPath);
					 model.setTypeId(typeId);
					 flag= this.businessSupportService.getWqsTravelService().doShaTianPictorialListSave(model);
					 if (flag < 1) {
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.PICTORIAL_PATH+newFileName);
						this.deleteFile(iconFile);
					}
				} else {
					if (returnData == -2L||returnData == -1L) {
						//上传失败后删除可能已存储的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.PICTORIAL_PATH+newFileName);
						this.deleteFile(iconFile);
					}
				}
			}
			
		}
		
		return SUCCESS;
	}
	
	
	
	
	
	
	/*删除画卷类型
	 * 
	 * （1）查询该画卷类型，获取相应的图片路径，然后删除该记录和相应的图片
	 * （2）查询画卷集，获取相应的图片路径，然后删除该记录和相应的图片
	 * 
	 * */
	public String doTownPictureDelete(){
		log.debug("doTownPictureDelete");
		this.tips = "删除失败,请重试!";
		this.forward =  "wqstravel/getTownPictureList.action?searchName="+searchName;		
		
		ShaTianPictorialTypeModel typeModel= this.businessSupportService.getWqsTravelService().getShaTianPictorial(id);
		if(typeModel==null){
			this.tips = "删除失败,该记录不存在!";
			return SUCCESS;
		}
		
		int flag=this.businessSupportService.getWqsTravelService().doShaTianPictorialDelete(id);
		if(flag>0){
			this.tips = "删除成功!";
			/*删除图标*/
			String iconPath=typeModel.getIconPath();
			File iconFile = new File(this.FILE_UPLOAD_PATH+iconPath);
			this.deleteFile(iconFile);
			
			/*将画卷列表删除*/
			List<ShaTianPictorialModel> shaTianPictorialModelList=this.businessSupportService.getWqsTravelService().getShaTianPictorialList(id);
			flag=this.businessSupportService.getWqsTravelService().doShaTianPictorialListDelete(id);
			//删除成功，则将相应的图片删除
			if(flag>0){
				if(shaTianPictorialModelList!=null&&shaTianPictorialModelList.size()>0){
					iconFile=null;
					for(ShaTianPictorialModel shaTianPictorialModel:shaTianPictorialModelList){
						iconFile = new File(this.FILE_UPLOAD_PATH+shaTianPictorialModel.getFilePath());
						this.deleteFile(iconFile);
					}
				}
			}
		} 
		
		return SUCCESS;
		
		
	}
	
	
	/**
	 * 删除画卷中的某个图片记录
	 * @return
	 */
	public String doTownOnePictureDelete(){
		log.debug("doTownOnePictureDelete");
		this.tips = "删除失败,请重试!";
		this.forward =  "wqstravel/viewPicList.action?id="+typeId+"&searchName="+searchName;	
		
		ShaTianPictorialModel pictorialModel= this.businessSupportService.getWqsTravelService().getShaTianOnePictorialDelete(id);
		if(pictorialModel==null){
			this.tips = "删除失败,该记录不存在!";
			return SUCCESS;
		}
		
		int flag=this.businessSupportService.getWqsTravelService().doShaTianOnePictorialDelete(id);
		if(flag>0){
			this.tips = "删除成功!";
			/*删除图片*/
			String iconPath=pictorialModel.getFilePath();
			File iconFile = new File(this.FILE_UPLOAD_PATH+iconPath);
			this.deleteFile(iconFile);
		} 
		
		return SUCCESS;
		
		
	}
	
	
	
	
	public String doPictureEdit(){
		this.forward ="wqstravel/toPicEdit.action?id="+typeId+"&searchName="+searchName;	
		this.tips="修改失败，请稍后尝试!";
		ShaTianPictorialModel shaTianPictorialModel= this.businessSupportService.getWqsTravelService().getShaTianOnePictorialDelete(  id);
		if(shaTianPictorialModel!=null){
			shaTianPictorialModel.setDescript(descript);
			//上传图片
			if(produceContentFileUrlsFileName!=null&&produceContentFileUrlsFileName.length>0
					&&produceContentFileUrls!=null&&produceContentFileUrls.length>0){
				int length=produceContentFileUrls.length;
				String contentPicName;
				long returnData;
				String newFileName;
				for(int index=0;index<length;index++){
					returnData = 0L;
					contentPicName=this.produceContentFileUrlsFileName[index];
					newFileName=this.newFileName(contentPicName);
					returnData = FileUploader.upload(this.produceContentFileUrls[index], this.FILE_UPLOAD_PATH + this.PICTORIAL_PATH,
							newFileName, false);
					if (returnData > 0L) {
						//删除之前的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+shaTianPictorialModel.getFilePath());
						this.deleteFile(iconFile);
						//更新图片
						shaTianPictorialModel.setFilePath(this.PICTORIAL_PATH+newFileName);
						shaTianPictorialModel.setFileName(contentPicName);
					} else {
						if (returnData == -2L||returnData == -1L) {
							//上传失败后删除可能已存储的图片
							File iconFile = new File(this.FILE_UPLOAD_PATH+this.PICTORIAL_PATH+newFileName);
							this.deleteFile(iconFile);
							return SUCCESS;
						}
					}
				}
			}
			int d=this.businessSupportService.getWqsTravelService().doShaTianOnePictorialUpdate(shaTianPictorialModel);
			if(d>0){
				this.forward ="wqstravel/viewPicList.action?id="+typeId+"&searchName="+searchName ;
				this.tips="更新成功!";
			}
		}else{
			this.forward ="wqstravel/viewPicList.action?id="+typeId+"&searchName="+searchName ;
			this.tips="该记录不存在!";
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
	 * 创建随机文件名
	 * @param fileName
	 * @return
	 */
	private String newFileName(String fileName){
		String regx=fileName.substring(fileName.lastIndexOf("."),fileName.length());//.jpg
		String ranStr=StringUtil.createRanStr(8);
		String newPicName=ranStr+"_"+StringUtil.getTimeStamp()+regx;
		return newPicName;
		
	}
	
	
	
	

	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	public String getActionName() {
		return actionName;
	}

	 
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getPicTypeName() {
		return picTypeName;
	}


	public void setPicTypeName(String picTypeName) {
		this.picTypeName = picTypeName;
	}


	public String getPicTypeDesc() {
		return picTypeDesc;
	}


	public void setPicTypeDesc(String picTypeDesc) {
		this.picTypeDesc = picTypeDesc;
	}




	public String[] getProduceContentFileUrlsFileName() {
		return produceContentFileUrlsFileName;
	}


	public void setProduceContentFileUrlsFileName(
			String[] produceContentFileUrlsFileName) {
		this.produceContentFileUrlsFileName = produceContentFileUrlsFileName;
	}


	public File[] getProduceContentFileUrls() {
		return produceContentFileUrls;
	}


	public File getPicTypeIconFileUrl() {
		return picTypeIconFileUrl;
	}


	public void setPicTypeIconFileUrl(File picTypeIconFileUrl) {
		this.picTypeIconFileUrl = picTypeIconFileUrl;
	}


	public String getPicTypeIconFileUrlFileName() {
		return picTypeIconFileUrlFileName;
	}


	public void setPicTypeIconFileUrlFileName(String picTypeIconFileUrlFileName) {
		this.picTypeIconFileUrlFileName = picTypeIconFileUrlFileName;
	}


	public void setProduceContentFileUrls(File[] produceContentFileUrls) {
		this.produceContentFileUrls = produceContentFileUrls;
	}


	public String getFILE_UPLOAD_PATH() {
		return FILE_UPLOAD_PATH;
	}

	public void setFILE_UPLOAD_PATH(String fILE_UPLOAD_PATH) {
		FILE_UPLOAD_PATH = fILE_UPLOAD_PATH;
	}


	public String getSearchName() {
		return searchName;
	}


	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}


	public String getDescript() {
		return descript;
	}


	public void setDescript(String descript) {
		this.descript = descript;
	}
	
}

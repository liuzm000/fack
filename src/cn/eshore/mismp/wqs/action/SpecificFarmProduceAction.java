package cn.eshore.mismp.wqs.action;

import java.io.File;

import cn.eshore.mismp.util.FileUploader;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.AlgGoodsModel;

public class SpecificFarmProduceAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private AlgGoodsModel algGoodsModel;
	private String id;
	private String actionName;
	private String forward;
	private String tips;
	private File produceIconFileUrl;
	private String produceIconFileUrlFileName;
	private String[] produceContentFileUrlsFileName;
	private File[] produceContentFileUrls;
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	private String PRODUCE_FILE_PATH = MobileGlobals.getProperty("path.local.produce");
	
	private String searchName;
	
	
	
	public String getFarmProduceList(){
		log.debug("【getFarmProduceList】... ");
		this.actionName="getFarmProduceList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getAlgGoods(searchName,pageNum, pageSize);
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
	
	public String doFarmProduceAdd() {
		log.debug("[doFarmProduceAdd]...");
		return SUCCESS;
	}
	public String toEditFarmProduce() {
		log.debug("[toEditFarmProduce]...");
		AlgGoodsModel  algGoodsModel=this.businessSupportService.getWqsTravelService().getAlgGoodsById(id);
		if(algGoodsModel!=null){
			this.getRequest().setAttribute("model", algGoodsModel);
		}
		
		return SUCCESS;
	}
	
	public String doFarmProduceSave(){
		log.debug("[doFarmProduceSave]...");
		tips="添加成功!";
		forward="wqstravel/getFarmProduceList.action?searchName="+searchName;
		if(null!=this.algGoodsModel){
			//保存图标图片
			long returnData = 0L;
			returnData = FileUploader.upload(this.produceIconFileUrl, this.FILE_UPLOAD_PATH + this.PRODUCE_FILE_PATH,
					this.produceIconFileUrlFileName, false);
			if (returnData > 0L) {
				this.algGoodsModel.setPreviewUrl(this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
				log.debug(this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
			} else {
							
				this.forward = "wqstravel/doFarmProduceAdd.action?searchName="+searchName;
				if (returnData == -1L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
					this.deleteFile(iconFile);
					
					this.tips = "产品图标文件为空，请重新上传！";
					return "success";
				}
				if (returnData == -2L) {
					//上传失败后删除可能已存储的图片
					File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
					this.deleteFile(iconFile);
					
					this.tips = "产品图标上传异常，请重新上传！";
					return "success";
				}
				if (returnData == -3L) {
					this.tips = "产品图标已存在同名文件，请改名后上传！";
					return "success";
				}
			}
			//保存内容图片
			StringBuilder filesPath=new StringBuilder();
			for(int index=0;index<this.produceContentFileUrls.length;index++){
				returnData = 0L;
				returnData = FileUploader.upload(this.produceContentFileUrls[index], this.FILE_UPLOAD_PATH + this.PRODUCE_FILE_PATH,
						this.produceContentFileUrlsFileName[index], false);
				if (returnData > 0L) {
					filesPath.append(this.PRODUCE_FILE_PATH+this.produceContentFileUrlsFileName[index]+",");
				} else {
					this.forward = "wqstravel/doFarmProduceAdd.action?searchName="+searchName;
					if (returnData == -1L) {
						//上传失败后删除可能已存储的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
						this.deleteFile(iconFile);
						for(int i=0;i<=index;i++){
							File contentFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceContentFileUrlsFileName[i]);
							this.deleteFile(contentFile);
						}
						
						this.tips = "产品内容图片文件为空，请重新上传！";
						return "success";
					}
					if (returnData == -2L) {
						//上传失败后删除可能已存储的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
						this.deleteFile(iconFile);
						for(int i=0;i<=index;i++){
							File contentFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceContentFileUrlsFileName[i]);
							this.deleteFile(contentFile);
						}
						
						this.tips = "产品内容图片上传异常，请重新上传！";
						return "success";
					}
					if (returnData == -3L) {
						//上传失败后删除可能已存储的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
						this.deleteFile(iconFile);
						for(int i=0;i<index;i++){//不能<=，因为等于的话会将同名文件删除
							File contentFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceContentFileUrlsFileName[i]);
							this.deleteFile(contentFile);
						}
						
						this.tips = "产品内容图片已存在同名文件，请改名后上传！";
						return "success";
					}
				}	
			}
			this.algGoodsModel.setIcon(filesPath.substring(0,filesPath.length()-1));
			try {
				int d = this.businessSupportService.getWqsTravelService().doAlgGoodsSave(algGoodsModel);
				if (d > 0) {
					this.tips="添加成功！";
					this.forward =  "wqstravel/getFarmProduceList.action?searchName="+searchName;
					return SUCCESS;
				}
				//操作失败删除文件
				//上传失败后删除可能已存储的图片
				File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
				this.deleteFile(iconFile);
				for(int i=0;i<this.produceContentFileUrls.length;i++){
					File contentFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceContentFileUrlsFileName[i]);
					this.deleteFile(contentFile);
				}
				
				this.tips = "添加失败,请重试！";
				this.forward = "wqstravel/doFarmProduceAdd.action?searchName="+searchName;
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				//操作失败删除文件
				//上传失败后删除可能已存储的图片
				File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
				this.deleteFile(iconFile);
				for(int i=0;i<this.produceContentFileUrls.length;i++){
					File contentFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceContentFileUrlsFileName[i]);
					this.deleteFile(contentFile);
				}
				
				this.forward = "wqstravel/doFarmProduceAdd.action?searchName="+searchName;
				return "success";
			}
			//log.debug(filesPath.substring(0,filesPath.length()));
			//return SUCCESS;		
		}
		return SUCCESS;
	}
	
	
	//更新产品
	public String doFarmProduceUpdate(){
		log.debug("[doFarmProduceUpdate]...");
		tips="修改成功!";
		forward="wqstravel/getFarmProduceList.action?searchName="+searchName;
		
		String id=String.valueOf(algGoodsModel.getId());
		AlgGoodsModel  oldAlgGoodsModel=this.businessSupportService.getWqsTravelService().getAlgGoodsById(id);
		if(oldAlgGoodsModel!=null){
			oldAlgGoodsModel.setName(algGoodsModel.getName());
			oldAlgGoodsModel.setDescript(algGoodsModel.getDescript());
			long returnData = 0L;
			String oldPreViewUrl=oldAlgGoodsModel.getPreviewUrl();
			String oldIcons=oldAlgGoodsModel.getIcon();
			String newPreViewUrl=null;
			//更新图标
			if(produceIconFileUrl!=null&&produceIconFileUrlFileName!=null&&!"".equals(produceIconFileUrlFileName)){
				returnData = FileUploader.upload(this.produceIconFileUrl, this.FILE_UPLOAD_PATH + this.PRODUCE_FILE_PATH,
						this.produceIconFileUrlFileName, false);
				if (returnData > 0L) {
					newPreViewUrl=this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName ;
				} else {
					this.forward = "wqstravel/toEditFarmProduce.action?searchName="+searchName+"&id="+id;
					if (returnData == -1L) {
						//上传失败后删除可能已存储的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
						this.deleteFile(iconFile);
						
						this.tips = "产品图标文件为空，请重新上传！";
						return "success";
					}
					if (returnData == -2L) {
						//上传失败后删除可能已存储的图片
						File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
						this.deleteFile(iconFile);
						
						this.tips = "产品图标上传异常，请重新上传！";
						return "success";
					}
					if (returnData == -3L) {
						this.tips = "产品图标已存在同名文件，请改名后上传！";
						return "success";
					}
				}
			}
			
			
			//保存内容图片
			String icons=null;
			if(produceContentFileUrls!=null&&produceContentFileUrlsFileName!=null){
				StringBuilder filesPath=new StringBuilder();
				for(int index=0;index<this.produceContentFileUrls.length;index++){
					returnData = 0L;
					returnData = FileUploader.upload(this.produceContentFileUrls[index], this.FILE_UPLOAD_PATH + this.PRODUCE_FILE_PATH,
							this.produceContentFileUrlsFileName[index], false);
					if (returnData > 0L) {
						filesPath.append(this.PRODUCE_FILE_PATH+this.produceContentFileUrlsFileName[index]+",");
					} else {
						this.forward = "wqstravel/toEditFarmProduce.action?searchName="+searchName+"&id="+id;
						if (returnData == -1L) {
							//上传失败后删除可能已存储的图片
							File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
							this.deleteFile(iconFile);
							for(int i=0;i<=index;i++){
								File contentFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceContentFileUrlsFileName[i]);
								this.deleteFile(contentFile);
							}
							
							this.tips = "产品内容图片文件为空，请重新上传！";
							return "success";
						}
						if (returnData == -2L) {
							//上传失败后删除可能已存储的图片
							File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
							this.deleteFile(iconFile);
							for(int i=0;i<=index;i++){
								File contentFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceContentFileUrlsFileName[i]);
								this.deleteFile(contentFile);
							}
							this.tips = "产品内容图片上传异常，请重新上传！";
							return "success";
						}
						if (returnData == -3L) {
							//上传失败后删除可能已存储的图片
							File iconFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceIconFileUrlFileName);
							this.deleteFile(iconFile);
							for(int i=0;i<index;i++){//不能<=，因为等于的话会将同名文件删除
								File contentFile = new File(this.FILE_UPLOAD_PATH+this.PRODUCE_FILE_PATH+this.produceContentFileUrlsFileName[i]);
								this.deleteFile(contentFile);
							}
							this.tips = "产品内容图片已存在同名文件，请改名后上传！";
							return "success";
						}
					}	
				}
				icons=filesPath.substring(0,filesPath.length()-1);
			}
			
			
			if(newPreViewUrl!=null&&!"".equals(newPreViewUrl)){
				oldAlgGoodsModel.setPreviewUrl(newPreViewUrl);
			}
			
			if(icons!=null&&!"".equals(icons)){
				oldAlgGoodsModel.setIcon(icons);
			}
			
			
			int d=this.businessSupportService.getWqsTravelService().doAlgGoodsUpdate(oldAlgGoodsModel);
			if(d>0){
				
				if(newPreViewUrl!=null&&!"".equals(newPreViewUrl)){//假如有上传新图片，则将旧的图片删除
					File iconFile = new File(this.FILE_UPLOAD_PATH+oldPreViewUrl);
					this.deleteFile(iconFile);
				}
				
				if(icons!=null&&!"".equals(icons)){
					File iconFile ;
					String[] iconList=oldIcons.split(",");
					for(String icon:iconList){
						iconFile = new File(this.FILE_UPLOAD_PATH+icon);
						this.deleteFile(iconFile);
					}
				}
			}else{
				this.tips="修改失败，请稍后再试！";
				this.forward = "wqstravel/toEditFarmProduce.action?searchName="+searchName+"&id="+id;
				if(newPreViewUrl!=null&&!"".equals(newPreViewUrl)){//假如有上传新图片，则将旧的图片删除
					File iconFile = new File(this.FILE_UPLOAD_PATH+newPreViewUrl);
					this.deleteFile(iconFile);
				}
				if(icons!=null&&!"".equals(icons)){
					File iconFile ;
					String[] iconList=icons.split(",");
					for(String icon:iconList){
						iconFile = new File(this.FILE_UPLOAD_PATH+icon);
						this.deleteFile(iconFile);
					}
				}
			}
		}else{
			tips="该产品记录不存在!";
		}
		return SUCCESS;
	}
	
	public String doFarmProduceDelete(){
		log.debug("doFarmProduceDelete");
		this.tips = "删除失败,请重试!";
		this.forward =  "wqstravel/getFarmProduceList.action?searchName="+searchName;
		this.algGoodsModel=this.businessSupportService.getWqsTravelService().getAlgGoodsById(id);
		String previewUrl = algGoodsModel.getPreviewUrl();
		String icon = algGoodsModel.getIcon();
		try {
			int d = this.businessSupportService.getWqsTravelService().doAlgGoodsDelete(id);
			if(d > 0 ) {
				this.tips = "删除成功";
				//删除产品图标图片
				File produceIcon = new File(this.FILE_UPLOAD_PATH+previewUrl);
				if(produceIcon.exists()) {
					log.debug("Delete"+this.FILE_UPLOAD_PATH+previewUrl);
					produceIcon.delete();
				}
				//删除产品内容图片
				String[] picturePath = icon.split(",");
				for(String path:picturePath){
					File producePicture = new File(this.FILE_UPLOAD_PATH+path);
					if(producePicture.exists()) {
						log.debug("Delete"+this.FILE_UPLOAD_PATH+path);
						producePicture.delete();
					}
				}
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

	public File getProduceIconFileUrl() {
		return produceIconFileUrl;
	}

	public void setProduceIconFileUrl(File produceIconFileUrl) {
		this.produceIconFileUrl = produceIconFileUrl;
	}

	public File[] getProduceContentFileUrls() {
		return produceContentFileUrls;
	}

	public void setProduceContentFileUrls(File[] produceContentFileUrls) {
		this.produceContentFileUrls = produceContentFileUrls;
	}

	public String[] getProduceContentFileUrlsFileName() {
		return produceContentFileUrlsFileName;
	}

	public void setProduceContentFileUrlsFileName(
			String[] produceContentFileUrlsFileName) {
		this.produceContentFileUrlsFileName = produceContentFileUrlsFileName;
	}

	public String getProduceIconFileUrlFileName() {
		return produceIconFileUrlFileName;
	}

	public void setProduceIconFileUrlFileName(String produceIconFileUrlFileName) {
		this.produceIconFileUrlFileName = produceIconFileUrlFileName;
	}

	public String getFILE_UPLOAD_PATH() {
		return FILE_UPLOAD_PATH;
	}

	public void setFILE_UPLOAD_PATH(String fILE_UPLOAD_PATH) {
		FILE_UPLOAD_PATH = fILE_UPLOAD_PATH;
	}

	public String getPRODUCE_FILE_PATH() {
		return PRODUCE_FILE_PATH;
	}

	public void setPRODUCE_FILE_PATH(String pRODUCE_FILE_PATH) {
		PRODUCE_FILE_PATH = pRODUCE_FILE_PATH;
	}

	public AlgGoodsModel getAlgGoodsModel() {
		return algGoodsModel;
	}

	public void setAlgGoodsModel(AlgGoodsModel algGoodsModel) {
		this.algGoodsModel = algGoodsModel;
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
	
}

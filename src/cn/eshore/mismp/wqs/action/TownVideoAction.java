package cn.eshore.mismp.wqs.action;

import java.io.File;

import cn.eshore.mismp.util.FileUploader;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.TownVideoModel;
/**
 * <p>名镇视频 TownVideoAction.java <p>
 * <p> @author jianghuan <p>
 * <p> 时间：2012-12-21上午10:39:38<p>
 * <p> CopyRight 2012 <p>
 */
public class TownVideoAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private TownVideoModel videoModel;
	private String actionName;
	private String tips;
	private String forward;
	private String id;
	private File townVideoFileUrl; //上传的视频文件
	private String townVideoFileUrlFileName;//视频文件名称
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	private String VIDEO_FILE_PATH = MobileGlobals.getProperty("path.town.video");
	
	public String getTownVideoList() {
		log.debug("[getTownVideoList]...");
		this.actionName = "getTownVideoList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownVideo(this.pageNum,this.pageSize);
			if ((pageList != null) && (pageList.size() > 0)) {
				getRequest().setAttribute("pageList", pageList);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return "error";
		
	}
	public String getTownVideoQuery() {
		log.debug("[getTownVideoQuery]...");
		this.actionName = "getTownVideoQuery.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownVideoQuery(this.pageNum,this.pageSize,videoModel);
			if ((pageList != null) && (pageList.size() > 0)) {
				getRequest().setAttribute("pageList", pageList);
			}
			getRequest().setAttribute("model", videoModel);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return "error";
		
	}
	
	
	public String doTownVideoAdd() {
		log.debug("[doTownVideoAdd]...");
		
		return "success";
	}
	
	public String doTownVideoSave() {
		log.debug("[doTownVideoSave...]");

		if (this.videoModel != null) {
			if (this.townVideoFileUrl != null) {
				long returnData = 0L;
				//视频文件的名称
				String videoName = String.valueOf(System.currentTimeMillis())+ "."+ this.townVideoFileUrlFileName.substring(this.townVideoFileUrlFileName.lastIndexOf(".") + 1);
				returnData = FileUploader.upload(this.townVideoFileUrl,this.FILE_UPLOAD_PATH + this.VIDEO_FILE_PATH,videoName, false);
				if (returnData > 0L) {
					this.videoModel.setFilePath(this.VIDEO_FILE_PATH+ videoName);//设置文件路径
					this.videoModel.setFileName(videoName); //设置文件名称
				} else {
					this.tips = "视频上传失败，请重新上传！";
					this.forward = "wqstravel/doTownVideoAdd.action";
					return SUCCESS;
				}
			}
			try {
				int d = this.businessSupportService.getWqsTravelService().doTownVideoSave(videoModel);
				if (d > 0) {
					this.tips = "添加成功！";
					this.forward = "wqstravel/getTownVideoList.action";
					return SUCCESS;
				}
				
			} catch (Exception e) {
				//失败删除新上传图片
				File f = new File(this.FILE_UPLOAD_PATH + videoModel.getFilePath());
				if(f.exists()) {
					f.delete();
				}
				this.tips = "数据保存失败！请重试！";
				this.forward = "wqstravel/doTownVideoAdd.action";
				e.printStackTrace();
				log.debug(e.getMessage());
			}
		}
	
		return SUCCESS;
	
	}
	
	public String doTownVideoEdit() {
		log.debug("[doTownVideoEdit...]");
		TownVideoModel townVideoModel =  businessSupportService.getWqsTravelService().getTownVideoById(id);
		getRequest().setAttribute("model", townVideoModel);
		return "success";
	}
	
	public String doTownVideoUpdate() {
		log.debug("[doTownVideoUpdate...]");
		this.forward ="wqstravel/getTownVideoList.action";
		if (videoModel != null) {
			String oldFilePath = videoModel.getFilePath();
			if (this.townVideoFileUrl != null) {
				String iconName = String.valueOf(System.currentTimeMillis())+ this.townVideoFileUrlFileName.substring(this.townVideoFileUrlFileName.lastIndexOf("."));
				long returnData = 0L;
				returnData = FileUploader.upload(this.townVideoFileUrl,this.FILE_UPLOAD_PATH + this.VIDEO_FILE_PATH, iconName,false);
				if (returnData > 0L) {
					videoModel.setFilePath(this.VIDEO_FILE_PATH + iconName);//设置上传视频路径
					videoModel.setFileName(iconName);

				} else {		 
					this.tips = "图标文件为空，请重新上传！";
					return "success";
				}
			}
			
			try {
				int d = this.businessSupportService.getWqsTravelService().doTownVideoUpdate(videoModel);
				if (d > 0) {
					this.tips = "修改成功";
					//删除老图片
					if(! oldFilePath.equals(videoModel.getFilePath())){
						File file = new File(this.FILE_UPLOAD_PATH+ oldFilePath);
						if (file.exists()) {
							file.delete();
						}
					}
					return "success";
				}
				
			} catch (Exception e) {
				//修改失败删除新图片
				if(! oldFilePath.equals(videoModel.getFilePath())){
					File file = new File(this.FILE_UPLOAD_PATH+ videoModel.getFilePath());
					if (file.exists()) {
						file.delete();
					}
				}
				log.debug(e.getMessage());
				e.printStackTrace();
				this.tips = "数据保存失败！请重试！";
			}
		}

		return SUCCESS;
		
		
		
	}
	
	public String doTownVideoDelete() {
		log.debug("[doTownVideoDelete]...");
		this.tips = "删除失败，请重试";
		this.forward = "wqstravel/getTownVideoList.action";
		String url = getRequest().getParameter("url");
		int d = this.businessSupportService.getWqsTravelService().doTownVideoDel(id);
		
		if(d >0) {
			File file = new File(this.FILE_UPLOAD_PATH+ url);
			if (file.exists()) {
				file.delete();
			}
			this.tips = "删除成功";
		}
		
		return "success";
	}
	  
	
	
	
	
	
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	
	
	public File getTownVideoFileUrl() {
		return townVideoFileUrl;
	}
	public void setTownVideoFileUrl(File townVideoFileUrl) {
		this.townVideoFileUrl = townVideoFileUrl;
	}
	public String getTownVideoFileUrlFileName() {
		return townVideoFileUrlFileName;
	}
	public void setTownVideoFileUrlFileName(String townVideoFileUrlFileName) {
		this.townVideoFileUrlFileName = townVideoFileUrlFileName;
	}
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public TownVideoModel getVideoModel() {
		return videoModel;
	}
	public void setVideoModel(TownVideoModel videoModel) {
		this.videoModel = videoModel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	

}

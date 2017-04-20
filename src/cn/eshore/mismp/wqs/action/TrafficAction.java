
package cn.eshore.mismp.wqs.action;

import java.io.File;

import cn.eshore.mismp.util.ClobUtil;
import cn.eshore.mismp.util.FileUtil;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.StringUtil;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.HospitalModel;
import cn.eshore.mismp.wqs.model.TownMessageModel;
import cn.eshore.mismp.wqs.model.TrafficReguModel;
/**
 * <p>  名镇信息 TownMsgAction.java <p>
 * <p> @author jianghuan <p>
 * <p> 时间：2013-1-23上午10:57:43<p>
 * <p> CopyRight 2012 <p>
 */
public class TrafficAction extends BaseAction {

	private static final long serialVersionUID = 1L;



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


	public String getTypeId() {
		return typeId;
	}


	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	private String actionName;
	private String forward;
	private String tips;
	private String id;
	
	
	private String searchName;
	private String searchUnit;
	
	public String getId() {
		return id;
	}


	public String getSearchName() {
		return searchName;
	}


	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}


	public String getSearchUnit() {
		return searchUnit;
	}


	public void setSearchUnit(String searchUnit) {
		this.searchUnit = searchUnit;
	}


	public void setId(String id) {
		this.id = id;
	}

	private String typeId;
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	private String MSG_FILE_PATH = MobileGlobals.getProperty("path.town.msg");//名镇信息文件路径
	private String NEWS_FILE_PATH = MobileGlobals.getProperty("path.local.news");//新闻文件路径
	private String JOBS_FILE_PATH = MobileGlobals.getProperty("path.local.jobs");//招聘文件路径
	
	//服务器上传路径，暂定
	private HospitalModel hospitalModel;
	private String HOSPITALS_FILE_PATH = MobileGlobals.getProperty("path.local.hospitals");//医院文件路径
	private String TRAFFIC_FILE_PATH = MobileGlobals.getProperty("path.local.traffic");//医院文件路径
	
	
	private TrafficReguModel trafficReguModel;
	
	public HospitalModel getHospitalModel() {
		return hospitalModel;
	}


	public void setHospitalModel(HospitalModel hospitalModel) {
		this.hospitalModel = hospitalModel;
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
	
	
	public String getTrafficReguList() {
		this.log.debug("[getTrafficReguList]...2222");
		this.actionName = "getTrafficReguList.action";
		
	
		
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTrafficReguList(searchName,searchUnit,pageNum+"", pageSize+"");
//			Pagination pageList = this.businessSupportService.getWqsTravelService().getTrafficReguList(pageNum+"", pageSize+"");
			if ((pageList != null) && (pageList.size() > 0)) {
//				List<TownMessageModel> modelList = new ArrayList<TownMessageModel>();
//				for (int i = 0; i < pageList.size(); i++) {
//					TownMessageModel model = (TownMessageModel)pageList.get(i);
//					model.setContentStr(StringUtil.trimNull(ClobUtil.clobToString(model.getContent())));
//					modelList.add(model);
//				}
//				pageList.clear();
//				pageList.addAll(modelList);
				getRequest().setAttribute("pageList", pageList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
	}
	/*public String getHospitalList() {
		this.log.debug("[getHospitalList]...");
		this.actionName = "getHospitalList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getHospitalList(pageNum+"", pageSize+"");
			if ((pageList != null) && (pageList.size() > 0)) {
//				List<TownMessageModel> modelList = new ArrayList<TownMessageModel>();
//				for (int i = 0; i < pageList.size(); i++) {
//					TownMessageModel model = (TownMessageModel)pageList.get(i);
//					model.setContentStr(StringUtil.trimNull(ClobUtil.clobToString(model.getContent())));
//					modelList.add(model);
//				}
//				pageList.clear();
//				pageList.addAll(modelList);
				getRequest().setAttribute("pageList", pageList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
	}*/
	
	public String doTrafficReguAdd() {
		log.debug("[doTrafficReguAdd]...");
		
		return SUCCESS;
	}
	
	public String doTrafficReguSave() {
		log.debug("[doTrafficReguSave]...");
		this.forward = "wqstravel/getTrafficReguList.action?searchName="+searchName+"&searchUnit="+searchUnit;
		if(trafficReguModel != null){
			this.tips = "添加失败,请重试!";
			
			log.debug("[doTrafficReguSave2]...");
			
			File saveDirectory = new File(this.FILE_UPLOAD_PATH + this.TRAFFIC_FILE_PATH);
			// 如果上传文件的存放路径文件夹不存在，则创建
			if (!saveDirectory.isDirectory()) {
				saveDirectory.mkdirs();
				log.info("创建" +saveDirectory + "目录");
			}
			//生成一个HTML文件,并且得到其路径
			String html = getHtmlString(trafficReguModel.getTitle(), trafficReguModel.getContentStr());
			String fileName = System.currentTimeMillis()+".html";
			log.debug("[doTrafficReguSave21]...");
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.TRAFFIC_FILE_PATH, html, fileName, false);//覆盖原有的HTML文件
			if(!b) {
				log.debug("[doTrafficReguSave3]...");
				this.tips = "";
				return SUCCESS;
			}
			log.debug("[doTrafficReguSave4]...");
			trafficReguModel.setFileUrl(this.TRAFFIC_FILE_PATH + fileName);//设置HTML文件的路径
			
			/*hospitalModel.setTypeId(typeId);*/
			log.debug("[doTrafficReguSave5]...");
			try {
				int d = this.businessSupportService.getWqsTravelService().doTrafficReguSave(trafficReguModel);
				if (d > 0) {
					this.tips="添加成功!";
					log.debug("[doTrafficReguSave]..."+this.forward);
					return SUCCESS;
				}
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				return SUCCESS;
			}
		}
		else
		{
			log.debug("[doTrafficReguSave]...");
			this.tips = "添加失败,请重试!";
		}
		return SUCCESS;				
	}
	
	public TrafficReguModel getTrafficReguModel() {
		return trafficReguModel;
	}


	public void setTrafficReguModel(TrafficReguModel trafficReguModel) {
		this.trafficReguModel = trafficReguModel;
	}


	public String doTrafficReguDelete() {
		log.debug("doTrafficReguDelete"+id);
		this.tips = "删除失败,请重试!";
		this.forward = "wqstravel/getTrafficReguList.action?searchName="+searchName+"&searchUnit="+searchUnit;
		try {
			int d = this.businessSupportService.getWqsTravelService().doTrafficReguDelete(id);
			if(d > 0 ) {
				this.tips = "删除成功";
				String filePath = getRequest().getParameter("url");
				//删除HTML文件
				File file = new File(FILE_UPLOAD_PATH+filePath);
				if(file.exists()) {
					file.exists();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		log.debug("doTrafficReguDelete"+id);
		return SUCCESS;
	}
	
	
	public String toTrafficReguEdit(){
		TrafficReguModel  trafficReguModel=this.businessSupportService.getWqsTravelService(). getTrafficReguModel(  Integer.valueOf(id));	
		if(trafficReguModel!=null){
			String contentStr=ClobUtil.clobToString(trafficReguModel.getContent());
			trafficReguModel.setContentStr(contentStr);
			this.getRequest().setAttribute("model", trafficReguModel);
		}else{
			log.error("查询id="+id+"记录 不存在");
		}
		return SUCCESS;
	}
	
	
	
	
	public String doTrafficReguUpdate(){
		this.tips = "更新成功!";
		this.forward = "wqstravel/getTrafficReguList.action?searchName="+searchName+"&searchUnit="+searchUnit;
		long id=trafficReguModel.getId();
		TrafficReguModel  oldTrafficReguModel=this.businessSupportService.getWqsTravelService(). getTrafficReguModel( (int) id );	
		if(oldTrafficReguModel!=null){
			
			oldTrafficReguModel.setTitle(trafficReguModel.getTitle());
			oldTrafficReguModel.setUnit(trafficReguModel.getUnit());
			oldTrafficReguModel.setCreateTime(trafficReguModel.getCreateTime());

			String oldFileUrl=oldTrafficReguModel.getFileUrl();
			
			File saveDirectory = new File(this.FILE_UPLOAD_PATH + this.TRAFFIC_FILE_PATH);
			// 如果上传文件的存放路径文件夹不存在，则创建
			if (!saveDirectory.isDirectory()) {
				saveDirectory.mkdirs();
				log.info("创建" +saveDirectory + "目录");
			}
			//生成一个HTML文件,并且得到其路径
			String html = getHtmlString(trafficReguModel.getTitle(), trafficReguModel.getContentStr());
			String fileName = System.currentTimeMillis()+".html";
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.TRAFFIC_FILE_PATH, html, fileName, false);//覆盖原有的HTML文件
			if(!b) {
				this.tips = "生成正文文件时，出现异常，请稍后再试!";
				return SUCCESS;
			}
			String newFileUrl=this.TRAFFIC_FILE_PATH + fileName;
			
			oldTrafficReguModel.setFileUrl(newFileUrl);
			oldTrafficReguModel.setContentStr(trafficReguModel.getContentStr());
			
			int d=this.businessSupportService.getWqsTravelService().doTrafficReguUpdate(oldTrafficReguModel);
			 if(d>0){
				 this.tips = "更新成功!";
				 File file=new File(this.FILE_UPLOAD_PATH +oldFileUrl);
				 this.deleteFile(file);
			 }else{
				 this.tips = "更新失败!";
				 File file=new File(this.FILE_UPLOAD_PATH +newFileUrl);
				 this.deleteFile(file);
			 }
		}else{
			log.error("查询id="+id+"记录 不存在");
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
	
	
}

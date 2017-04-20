
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
/**
 * <p>  名镇信息 TownMsgAction.java <p>
 * <p> @author jianghuan <p>
 * <p> 时间：2013-1-23上午10:57:43<p>
 * <p> CopyRight 2012 <p>
 */
public class HospitalAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private TownMessageModel msgModel;
	
	private String searchName;
	private String searchAdd;
	
	
	
	public TownMessageModel getMsgModel() {
		return msgModel;
	}


	public void setMsgModel(TownMessageModel msgModel) {
		this.msgModel = msgModel;
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
	public String getId() {
		return id;
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
	
	
	public String getHospitalList() {
		this.log.debug("[getHospitalList]...");
		this.actionName = "getHospitalList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getHospitalList(searchName,searchAdd,String.valueOf(pageNum), String.valueOf(pageSize));
//			Pagination pageList = this.businessSupportService.getWqsTravelService().getHospitalList(pageNum+"", pageSize+"");
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
	
	public String doHospitalAdd() {
		log.debug("[doHospitalAdd]...");
		
		return SUCCESS;
	}
	
	public String doHospitalSave() {
		log.debug("[doHospitalSave]...");
		this.forward = "wqstravel/getHospitalList.action?searchName="+searchName+"&searchAdd="+searchAdd;
		if(hospitalModel != null){
			this.tips = "添加失败,请重试!";
			
			
			//生成一个HTML文件,并且得到其路径
			File saveDirectory = new File(this.FILE_UPLOAD_PATH + this.HOSPITALS_FILE_PATH);
			// 如果上传文件的存放路径文件夹不存在，则创建
			if (!saveDirectory.isDirectory()) {
				saveDirectory.mkdirs();
				log.info("创建" +saveDirectory + "目录");
			}
			String html = getHtmlString(hospitalModel.getName(), hospitalModel.getContentStr());
			String fileName = System.currentTimeMillis()+".html";
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.HOSPITALS_FILE_PATH, html, fileName, false);//覆盖原有的HTML文件
			if(!b) {
				this.tips = "";
				return SUCCESS;
			}
			hospitalModel.setFileUrl(this.HOSPITALS_FILE_PATH + fileName);//设置HTML文件的路径
			
			/*hospitalModel.setTypeId(typeId);*/
			try {
				int d = this.businessSupportService.getWqsTravelService().doHospitalSave(hospitalModel);
				if (d > 0) {
					this.tips="添加成功!";
					log.debug("[doHospitalSave]..."+this.forward);
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
			log.debug("[HospitalNull]...");
			this.tips = "添加失败,请重试!";
		}
		return SUCCESS;				
	}
	
	public String doHospitalDelete() {
		log.debug("doHospitalDelete"+id);
		this.tips = "删除失败,请重试!";
		this.forward = "wqstravel/getHospitalList.action?searchName="+searchName+"&searchAdd="+searchAdd;
		try {
			int d = this.businessSupportService.getWqsTravelService().doHospitalDelete(id);
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
		log.debug("doHospitalDelete"+id);
		return SUCCESS;
	}
	
	public String toHospitalEdit(){
		log.debug("toHospitalEdit"+id);
		HospitalModel hospitalModel=this.businessSupportService.getWqsTravelService(). getHospitalById( Integer.valueOf(id));	
		if(hospitalModel!=null){
			String  contentStr=ClobUtil.clobToString(hospitalModel.getHtmlValue());
			hospitalModel.setContentStr(contentStr);
			this.getRequest().setAttribute("model", hospitalModel);
		}
		return SUCCESS;
		
	}
	
	
	public String doHospitalEdit(){
		log.debug("toHospitalEdit"+id);
		long id=hospitalModel.getId();
		this.tips="修改成功!";
		this.forward = "wqstravel/getHospitalList.action?searchName="+searchName+"&searchAdd="+searchAdd;
		HospitalModel oldHospitalModel=this.businessSupportService.getWqsTravelService(). getHospitalById( (int)id);	
		if(oldHospitalModel!=null){
			oldHospitalModel.setName(hospitalModel.getName());
			oldHospitalModel.setAddress(hospitalModel.getAddress());
			oldHospitalModel.setTele1(hospitalModel.getTele1());
			oldHospitalModel.setTele2(hospitalModel.getTele2());
			oldHospitalModel.setTele3(hospitalModel.getTele3());
			
			String oldHtmlFilePath=oldHospitalModel.getHtmlFile();
			//生成一个HTML文件,并且得到其路径
			File saveDirectory = new File(this.FILE_UPLOAD_PATH + this.HOSPITALS_FILE_PATH);
			// 如果上传文件的存放路径文件夹不存在，则创建
			if (!saveDirectory.isDirectory()) {
				saveDirectory.mkdirs();
				log.info("创建" +saveDirectory + "目录");
			}
			String html = getHtmlString(hospitalModel.getName(), hospitalModel.getContentStr());
			String fileName = System.currentTimeMillis()+".html";
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.HOSPITALS_FILE_PATH, html, fileName, false);//覆盖原有的HTML文件
			if(!b) {
				this.tips = "生成正文文件时，出现异常，请稍后再试!";
				return SUCCESS;
			}
			String newHtmlFilePath=this.HOSPITALS_FILE_PATH + fileName;
			oldHospitalModel.setHtmlFile(newHtmlFilePath);//设置HTML文件的路径
			oldHospitalModel.setHtmlValue(ClobUtil.stringToClob(hospitalModel.getContentStr()));
			
			int d =this.businessSupportService.getWqsTravelService().doHospitalUpdate(oldHospitalModel);	
			if(d>0){
				this.tips="修改成功!";
				File file=new File(this.FILE_UPLOAD_PATH + oldHtmlFilePath);
				this.deleteFile(file);
			}else{
				this.tips="修改失败，请稍后再试!";	
				File file=new File(this.FILE_UPLOAD_PATH + newHtmlFilePath);
				this.deleteFile(file);
			}
			
		}else{
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
	
	
}

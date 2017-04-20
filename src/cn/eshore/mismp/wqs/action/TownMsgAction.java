package cn.eshore.mismp.wqs.action;

import java.io.File;

import cn.eshore.mismp.util.ClobUtil;
import cn.eshore.mismp.util.FileUtil;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.StringUtil;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.TownJobsModel;
import cn.eshore.mismp.wqs.model.TownMessageModel;
/**
 * <p>  名镇信息 TownMsgAction.java <p>
 * <p> @author jianghuan <p>
 * <p> 时间：2013-1-23上午10:57:43<p>
 * <p> CopyRight 2012 <p>
 */
public class TownMsgAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private TownMessageModel msgModel;
	private TownJobsModel jobModel;
	private String actionName;
	private String forward;
	private String tips;
	private String id;
	private String typeId;
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	private String MSG_FILE_PATH = MobileGlobals.getProperty("path.town.msg");//名镇信息文件路径
	private String NEWS_FILE_PATH = MobileGlobals.getProperty("path.local.news");//新闻文件路径
	private String JOBS_FILE_PATH = MobileGlobals.getProperty("path.local.jobs");//招聘文件路径
	
	private String searchName;
	private String searchUnit;
	
	//名镇信息列表 
	public String getTownMsgList() {
		this.log.debug("[getTownMsgList]...");
		this.actionName = "getTownMsgList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownMsgList(pageNum, pageSize);
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
	
	public String getTownMsgQuery() {
		this.log.debug("[getTownMsgQuery]...");
		this.actionName = "getTownMsgQuery.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownMsgQuery(pageNum, pageSize,msgModel);
			if ((pageList != null) && (pageList.size() > 0)) {
				getRequest().setAttribute("pageList", pageList);
			}
			getRequest().setAttribute("model", msgModel);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
		
	}
	
	public String doTownMsgEdit() {
		log.debug("[doTownMsgEdit]...");
		TownMessageModel model = this.businessSupportService.getWqsTravelService().getTownMsgById(id);
		model.setContentStr(StringUtil.trimNull(ClobUtil.clobToString(model.getContent())));
		getRequest().setAttribute("vo", model);
		return SUCCESS;
	}
	
	public String doTownMsgUpdate() {
		
		log.debug("[doTownMsgUpdate]...");
		if(msgModel != null){
			this.tips = "修改成功！";
			this.forward = "wqstravel/getTownMsgList.action";
			//生成一个HTML文件,并且得到其路径
			String html = getHtmlString(msgModel.getTitle(), msgModel.getContentStr());
			String fileName = msgModel.getFileUrl().substring(this.MSG_FILE_PATH.length(), msgModel.getFileUrl().length());
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.MSG_FILE_PATH, html, fileName, false);//覆盖原有的HTML文件
			if(!b) {
				this.tips = "修改失败！请重试！";
				return SUCCESS;
			}
			msgModel.setFileUrl(this.MSG_FILE_PATH + fileName);//设置HTML文件的路径
			
			
			try {
				int d = this.businessSupportService.getWqsTravelService().doTownMsgUpdate(msgModel);
				if (d > 0) {
					return "success";
				}
				this.tips = "数据保存失败！请重试！";
				this.forward = "wqstravel/getTownMsgList.action";
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownMsgList.action";
				return "success";
			}
		}
		return SUCCESS;
		
		
	}
	
    public String doTownMsgAdd() {
		log.debug("[doTownMsgAdd]...");
		
		return SUCCESS;
	}
	
	public String doTownMsgSave() {
		log.debug("[doTownMsgSave]...");
		if(msgModel != null){
			this.tips = "添加失败,请重试!";
			this.forward = "wqstravel/getTownMsgList.action";
			//生成一个HTML文件,并且得到其路径
			String html = getHtmlString(msgModel.getTitle(), msgModel.getContentStr());
			String fileName = System.currentTimeMillis()+".html";
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.MSG_FILE_PATH, html, fileName, false);//覆盖原有的HTML文件
			if(!b) {
				this.tips = "";
				return SUCCESS;
			}
			msgModel.setFileUrl(this.MSG_FILE_PATH + fileName);//设置HTML文件的路径
			
			
			try {
				int d = this.businessSupportService.getWqsTravelService().doTownMsgSave(msgModel);
				if (d > 0) {
					this.tips="添加成功!";
					return "success";
				}
				this.forward = "wqstravel/getTownMsgList.action";
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownMsgList.action";
				return "success";
			}
		}
		return SUCCESS;
		
		
		
	}
	
	public String doTownMsgDelete() {
		log.debug("doTownMsgDelete");
		this.tips = "删除失败,请重试!";
		this.forward =  "wqstravel/getTownMsgList.action";
		try {
			int d = this.businessSupportService.getWqsTravelService().doTownMsgDelete(id);
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
	
	/***********************************************************************************************/
	/********************       万顷沙新闻信息                                                                                        *********************/
	/**********************************************************************************************/
	public String getTownNewsList() {
		this.log.debug("[getTownNewsList]...");
		this.actionName = "getTownNewsList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownNews(searchName,searchUnit,pageNum, pageSize ,typeId);
//			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownNews(pageNum, pageSize ,typeId);
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
	
	public String getTownNewsQuery() {
		this.log.debug("[getTownNewsQuery]...");
		this.actionName = "getTownNewsQuery.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownNewsQuery(pageNum, pageSize,msgModel);
			if ((pageList != null) && (pageList.size() > 0)) {
				getRequest().setAttribute("pageList", pageList);
			}
			getRequest().setAttribute("model", msgModel);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
		
	}
	
	public String doTownNewsEdit() {
		log.debug("[doTownNewsEdit]...");
		TownMessageModel model = this.businessSupportService.getWqsTravelService().getTownNewsById(id);
		model.setContentStr(StringUtil.trimNull(ClobUtil.clobToString(model.getContent())));
		getRequest().setAttribute("vo", model);
		return SUCCESS;
	}
	
	public String doTownNewsUpdate() {
		
		log.debug("[doTownNewsUpdate]...");
		if(msgModel != null){
			this.tips = "修改成功！";
			this.forward = "wqstravel/getTownNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;
			//生成一个HTML文件,并且得到其路径
			String html = getHtmlString(msgModel.getTitle(), msgModel.getContentStr());
			String fileName = msgModel.getFileUrl().substring(this.NEWS_FILE_PATH.length(), msgModel.getFileUrl().length());
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.NEWS_FILE_PATH, html, fileName, false);//覆盖原有的HTML文件
			if(!b) {
				this.tips = "修改失败！请重试！";
				return SUCCESS;
			}
			msgModel.setFileUrl(this.NEWS_FILE_PATH + fileName);//设置HTML文件的路径
			
			
			try {
				msgModel.setTypeId(typeId);
				int d = this.businessSupportService.getWqsTravelService().doTownNewsUpdate(msgModel);
				if (d > 0) {
					return "success";
				}
				this.tips = "数据保存失败！请重试！";
				this.forward = "wqstravel/getTownNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			}
		}
		return SUCCESS;
		
		
	}
	
    public String doTownNewsAdd() {
		log.debug("[doTownNewsAdd]...");
		
		return SUCCESS;
	}
	
	public String doTownNewsSave() {
		log.debug("[doTownNewsSave]...");
		if(msgModel != null){
			this.tips = "添加失败,请重试!";
			this.forward = "wqstravel/getTownNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
			//生成一个HTML文件,并且得到其路径
			String html = getHtmlString(msgModel.getTitle(), msgModel.getContentStr());
			String fileName = System.currentTimeMillis()+".html";
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.NEWS_FILE_PATH, html, fileName, false);//覆盖原有的HTML文件
			if(!b) {
				this.tips = "";
				return SUCCESS;
			}
			msgModel.setFileUrl(this.NEWS_FILE_PATH + fileName);//设置HTML文件的路径
			
			msgModel.setTypeId(typeId);
			try {
				int d = this.businessSupportService.getWqsTravelService().doTownNewsSave(msgModel);
				if (d > 0) {
					this.tips="添加成功!";
					return "success";
				}
				this.forward = "wqstravel/getTownNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			}
		}
		return SUCCESS;
		
		
		
	}
	
	public String doTownNewsDelete() {
		log.debug("doTownNewsDelete");
		this.tips = "删除失败,请重试!";
		this.forward =  "wqstravel/getTownNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
		try {
			int d = this.businessSupportService.getWqsTravelService().doTownNewsDelete(id);
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
		
		return SUCCESS;
	}
	
	
	
	
	

	/***********************************************************************************************/
	/********************       南沙新闻                                                                                       ***************************/
	/**********************************************************************************************/
	public String getTownNsNewsList() {
		this.log.debug("[getTownNsNewsList]...");
		this.actionName = "getTownNsNewsList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownNews(searchName,searchUnit,pageNum, pageSize ,typeId);
//			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownNews(pageNum, pageSize ,typeId);
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
	
	public String getTownNsNewsQuery() {
		this.log.debug("[getTownNsNewsQuery]...");
		this.actionName = "getTownNewsQuery.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownNewsQuery(pageNum, pageSize,msgModel);
			if ((pageList != null) && (pageList.size() > 0)) {
				getRequest().setAttribute("pageList", pageList);
			}
			getRequest().setAttribute("model", msgModel);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
		
	}
	
	public String doTownNsNewsEdit() {
		log.debug("[doTownNewsEdit]...");
		TownMessageModel model = this.businessSupportService.getWqsTravelService().getTownNewsById(id);
		model.setContentStr(StringUtil.trimNull(ClobUtil.clobToString(model.getContent())));
		getRequest().setAttribute("vo", model);
		return SUCCESS;
	}
	
	public String doTownNsNewsUpdate() {
		
		log.debug("[doTownNewsUpdate]...");
		if(msgModel != null){
			this.tips = "修改成功！";
			this.forward = "wqstravel/getTownNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
			//生成一个HTML文件,并且得到其路径
			String html = getHtmlString(msgModel.getTitle(), msgModel.getContentStr());
			String fileName = msgModel.getFileUrl().substring(this.NEWS_FILE_PATH.length(), msgModel.getFileUrl().length());
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.NEWS_FILE_PATH, html, fileName, false);//覆盖原有的HTML文件
			if(!b) {
				this.tips = "修改失败！请重试！";
				return SUCCESS;
			}
			msgModel.setFileUrl(this.NEWS_FILE_PATH + fileName);//设置HTML文件的路径
			
			
			try {
				msgModel.setTypeId(typeId);
				int d = this.businessSupportService.getWqsTravelService().doTownNewsUpdate(msgModel);
				if (d > 0) {
					return "success";
				}
				this.tips = "数据保存失败！请重试！";
				this.forward = "wqstravel/getTownNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownNsNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			}
		}
		return SUCCESS;
		
		
	}
	
    public String doTownNsNewsAdd() {
		log.debug("[doTownNewsAdd]...");
		
		return SUCCESS;
	}
	
	public String doTownNsNewsSave() {
		log.debug("[doTownNewsSave]...");
		if(msgModel != null){
			this.tips = "添加失败,请重试!";
			this.forward = "wqstravel/getTownNsNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
			//生成一个HTML文件,并且得到其路径
			String html = getHtmlString(msgModel.getTitle(), msgModel.getContentStr());
			String fileName = System.currentTimeMillis()+".html";
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.NEWS_FILE_PATH, html, fileName, false);//覆盖原有的HTML文件
			if(!b) {
				this.tips = "";
				return SUCCESS;
			}
			msgModel.setFileUrl(this.NEWS_FILE_PATH + fileName);//设置HTML文件的路径
			
			msgModel.setTypeId(typeId);
			try {
				int d = this.businessSupportService.getWqsTravelService().doTownNewsSave(msgModel);
				if (d > 0) {
					this.tips="添加成功!";
					return "success";
				}
				this.forward = "wqstravel/getTownNsNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownNsNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			}
		}
		return SUCCESS;
		
		
		
	}
	
	public String doTownNsNewsDelete() {
		log.debug("doTownNewsDelete");
		this.tips = "删除失败,请重试!";
		this.forward = "wqstravel/getTownNsNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;
		try {
			int d = this.businessSupportService.getWqsTravelService().doTownNewsDelete(id);
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
		
		return SUCCESS;
	}

	/***********************************************************************************************/
	/********************       国内新闻                                                                                       ***************************/
	/**********************************************************************************************/
	public String getTownGnNewsList() {
		this.log.debug("[getTownGnNewsList]...");
		this.actionName = "getTownGnNewsList.action";
		try {
//			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownNews(pageNum, pageSize ,typeId);
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownNews(searchName,searchUnit,pageNum, pageSize ,typeId);
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
	
	public String getTownGnNewsQuery() {
		this.log.debug("[getTownGnNewsQuery]...");
		this.actionName = "getTownNewsQuery.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownNewsQuery(pageNum, pageSize,msgModel);
			if ((pageList != null) && (pageList.size() > 0)) {
				getRequest().setAttribute("pageList", pageList);
			}
			getRequest().setAttribute("model", msgModel);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
		
	}
	
	public String doTownGnNewsEdit() {
		log.debug("[doTownNewsEdit]...");
		TownMessageModel model = this.businessSupportService.getWqsTravelService().getTownNewsById(id);
		model.setContentStr(StringUtil.trimNull(ClobUtil.clobToString(model.getContent())));
		getRequest().setAttribute("vo", model);
		return SUCCESS;
	}
	
	public String doTownGnNewsUpdate() {
		
		log.debug("[doTownNewsUpdate]...");
		if(msgModel != null){
			this.tips = "修改成功！";
			this.forward = "wqstravel/getTownGnNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				
			
			try {
				msgModel.setTypeId(typeId);
				int d = this.businessSupportService.getWqsTravelService().doTownNewsUpdate(msgModel);
				if (d > 0) {
					return "success";
				}
				this.tips = "数据保存失败！请重试！";
				this.forward = "wqstravel/getTownGnNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownGnNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			}
		}
		return SUCCESS;
		
		
	}
	
    public String doTownGnNewsAdd() {
		log.debug("[doTownNewsAdd]...");
		
		return SUCCESS;
	}
	
	public String doTownGnNewsSave() {
		log.debug("[doTownNewsSave]...");
		if(msgModel != null){
			this.tips = "添加失败,请重试!";
			this.forward = "wqstravel/getTownGnNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
			msgModel.setTypeId(typeId);
			try {
				int d = this.businessSupportService.getWqsTravelService().doTownNewsSave(msgModel);
				if (d > 0) {
					this.tips="添加成功!";
					return "success";
				}
				this.forward = "wqstravel/getTownGnNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownGnNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;;
				return "success";
			}
		}
		return SUCCESS;
		
		
		
	}
	
	public String doTownGnNewsDelete() {
		log.debug("doTownNewsDelete");
		this.tips = "删除失败,请重试!";
		this.forward = "wqstravel/getTownGnNewsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;
		try {
			int d = this.businessSupportService.getWqsTravelService().doTownNewsDelete(id);
			if(d > 0 ) {
				this.tips = "删除成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		
		return SUCCESS;
	}
	
	
	
	
	/***********************************************************************************************/
	/********************       招聘信息                                                                                       ***************************/
	/**********************************************************************************************/
	public String getTownJobsList() {
		this.log.debug("[getTownJobsList]...");
		this.actionName = "getTownJobsList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownJobsList( searchName,  searchUnit,pageNum, pageSize);
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
	
//	public String getTownJobsQuery() {
//		this.log.debug("[getTownJobsQuery]...");
//		this.actionName = "getTownNewsQuery.action";
//		try {
//			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownNewsQuery(pageNum, pageSize,msgModel);
//			if ((pageList != null) && (pageList.size() > 0)) {
//				getRequest().setAttribute("pageList", pageList);
//			}
//			getRequest().setAttribute("model", msgModel);
//			return SUCCESS;
//		} catch (Exception e) {
//			e.printStackTrace();
//			this.log.error(e.getMessage());
//		}
//		return ERROR;
//		
//	}
	
	public String doTownJobsEdit() {
		log.debug("[doTownNewsEdit]...");
		TownJobsModel model = this.businessSupportService.getWqsTravelService().getTownJobsById(id);
		model.setInfoStr(StringUtil.trimNull(ClobUtil.clobToString(model.getInfoValue())));
		model.setJobStr(StringUtil.trimNull(ClobUtil.clobToString(model.getJobValue())));
		model.setId(Integer.valueOf(id));
		getRequest().setAttribute("jobModel", model);
		return SUCCESS;
	}
	public String doTownJobsUpdate() {
		
		log.debug("[doTownNewsSave]...");
		this.forward = "wqstravel/getTownJobsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;
		if(jobModel != null){
			this.tips = "操作失败,请重试!";
			String infoHtml = getHtmlString(jobModel.getTitle(), jobModel.getInfoStr());
			String fileName = jobModel.getInfoUrl().substring(this.MSG_FILE_PATH.length(), jobModel.getInfoUrl().length());
			boolean b1 = new FileUtil().write( this.FILE_UPLOAD_PATH + this.JOBS_FILE_PATH, infoHtml, fileName, false);//覆盖原有的HTML文件
			
			String jobHtml = getHtmlString(jobModel.getTitle(), jobModel.getJobStr());
			String jobName = jobModel.getJobUrl().substring(this.MSG_FILE_PATH.length(), jobModel.getJobUrl().length());
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.JOBS_FILE_PATH, jobHtml, jobName, false);//覆盖原有的HTML文件
			
			if(!b || !b1) {
				this.tips = "操作失败！请重试";
				return SUCCESS;
			}
			jobModel.setJobUrl(this.JOBS_FILE_PATH + jobName);//设置HTML文件的路径
			jobModel.setInfoUrl(this.JOBS_FILE_PATH + fileName);//设置HTML文件的路径
			try {
				int d = this.businessSupportService.getWqsTravelService().doTownJobsUpdate(jobModel);
				if (d > 0) {
					this.tips="添加成功!";
					return "success";
				}
				this.forward = "wqstravel/getTownJobsList.action?searchName="+searchName+"&searchUnit="+searchUnit;
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownJobsList.action?searchName="+searchName+"&searchUnit="+searchUnit;
				return "success";
			}
		}
		return SUCCESS;
		
		
	}
	
    public String doTownJobsAdd() {
		log.debug("[doTownNewsAdd]...");
		
		return SUCCESS;
	}
	
	public String doTownJobsSave() {
		log.debug("[doTownNewsSave]...");
		this.forward = "wqstravel/getTownJobsList.action?typeId="+typeId+"&searchName="+searchName+"&searchUnit="+searchUnit;
		if(jobModel != null){
			this.tips = "添加失败,请重试!";
			String infoHtml = getHtmlString(jobModel.getTitle(), jobModel.getInfoStr());
			String fileName = System.currentTimeMillis()+".html";
			boolean b1 = new FileUtil().write( this.FILE_UPLOAD_PATH + this.JOBS_FILE_PATH, infoHtml, fileName, false);//覆盖原有的HTML文件
			
			String jobHtml = getHtmlString(jobModel.getTitle(), jobModel.getJobStr());
			String jobName = System.currentTimeMillis()+1+".html";
			boolean b = new FileUtil().write( this.FILE_UPLOAD_PATH + this.JOBS_FILE_PATH, jobHtml, jobName, false);//覆盖原有的HTML文件
			
			if(!b || !b1) {
				this.tips = "操作失败！请重试";
				return SUCCESS;
			}
			jobModel.setJobUrl(this.JOBS_FILE_PATH + jobName);//设置HTML文件的路径
			jobModel.setInfoUrl(this.JOBS_FILE_PATH + fileName);//设置HTML文件的路径
			try {
				int d = this.businessSupportService.getWqsTravelService().doTownJobsSave(jobModel);
				if (d > 0) {
					this.tips="添加成功!";
					return "success";
				}
				this.forward = "wqstravel/getTownJobsList.action?searchName="+searchName+"&searchUnit="+searchUnit;
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownJobsList.action?searchName="+searchName+"&searchUnit="+searchUnit;
				return "success";
			}
		}
		return SUCCESS;
		
		
		
	}
	
	public String doTownJobsDelete() {
		log.debug("doTownNewsDelete");
		this.tips = "删除失败,请重试!";
		this.forward = "wqstravel/getTownJobsList.action?searchName="+searchName+"&searchUnit="+searchUnit;
		try {
			int d = this.businessSupportService.getWqsTravelService().doTownJobsDelete(id);
			if(d > 0 ) {
				this.tips = "删除成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		
		return SUCCESS;
	}
	
	

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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public TownJobsModel getJobModel() {
		return jobModel;
	}

	public void setJobModel(TownJobsModel jobModel) {
		this.jobModel = jobModel;
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
	
	
	
	
}

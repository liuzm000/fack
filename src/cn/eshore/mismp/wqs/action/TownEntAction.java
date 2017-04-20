/**
 * 
 */
package cn.eshore.mismp.wqs.action;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

import cn.eshore.mismp.util.ClobUtil;
import cn.eshore.mismp.util.FileUploader;
import cn.eshore.mismp.util.FileUtil;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.web.action.BaseAction;
import cn.eshore.mismp.wqs.model.EnterpriseModel;

/**
 * @author wanglei
 * 
 */
public class TownEntAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String actionName;
	private String forward;
	private String tips;
	private EnterpriseModel entModel;

	private File entImgFileUrl1;// 图片上传1
	private String entImgFileUrl1FileName;
	private String entImgFileUrl1ContentType;
	private File entImgFileUrl2;// 图片上传2
	private String entImgFileUrl2FileName;
	private String entImgFileUrl2ContentType;
	private File entImgFileUrl3;// 图片上传3
	private String entImgFileUrl3FileName;
	private String entImgFileUrl3ContentType;
	private File entImgFileUrl4;// 图片上传4
	private String entImgFileUrl4FileName;
	private String entImgFileUrl4ContentType;
	private File entImgFileUrl5;// 图片上传5
	private String entImgFileUrl5FileName;
	private String entImgFileUrl5ContentType;
	private File entImgFileUrl6;// 图片上传6
	private String entImgFileUrl6FileName;
	private String entImgFileUrl6ContentType;
	private File entImgFileUrl7;// 图片上传7
	private String entImgFileUrl7FileName;
	private String entImgFileUrl7ContentType;
	private File entImgFileUrl8;// 图片上传8
	private String entImgFileUrl8FileName;
	private String entImgFileUrl8ContentType;
	private File entImgFileUrl9;// 图片上传9
	private String entImgFileUrl9FileName;
	private String entImgFileUrl9ContentType;
	private File entImgFileUrl10;// 图片上传10
	private String entImgFileUrl10FileName;
	private String entImgFileUrl10ContentType;

	private String searchName;
	private String contentStr;
	private long id;
	
	private String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");

	public String getTownEntList() {
		this.log.debug("[getTownEntList]...");
		this.actionName = "getTownEntList.action";
		try {
			Pagination pageList = this.businessSupportService.getWqsTravelService().getTownEntList(searchName, pageNum, pageSize);
			if ((pageList != null) && (pageList.size() > 0)) {
				this.getRequest().setAttribute("pageList", pageList);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}
		return ERROR;
	}

	public String doTownEntDelete() {
		log.debug("[doTownEntDelete]...");
		this.tips = "删除失败,请重试!";
		this.forward = "wqstravel/getTownEntList.action?searchName="+searchName;
		String id = getRequest().getParameter("id");
		String filePath = getRequest().getParameter("url");
		try {
			int d = this.businessSupportService.getWqsTravelService().doTownEntDelete(id);
			if (d > 0) {
				this.tips = "删除成功";
				// 删除HTML文件
				File file = new File(FILE_UPLOAD_PATH + filePath);
				if (file.exists()) {
					file.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}

		return SUCCESS;
	}

	public String doTownEntAdd() {
		log.debug("[doTownEntAdd]...");
		return SUCCESS;
	}

	public String doTownEntSave() {
		log.debug("[doTownEntSave]...");
		if (entModel != null) {
			this.tips = "添加失败,请重试!";
			this.forward = "wqstravel/getTownEntList.action?searchName="+searchName;
			// 生成一个HTML文件,并且得到其路径
			String html = getHtmlString(entModel.getName(), entModel.getContentStr());
			String fileName = System.currentTimeMillis() + ".html";
			File path = new File(this.FILE_UPLOAD_PATH + "/town/enterprise/html/");
			if (!path.exists()) {
				path.mkdirs();
			}
			boolean b = new FileUtil().write(this.FILE_UPLOAD_PATH + "/town/enterprise/html/", html, fileName, false);// 覆盖原有的HTML文件
			if (!b) {
				this.tips = "";
				return SUCCESS;
			}
			entModel.setHtmlPath("/town/enterprise/html/" + fileName);// 设置HTML文件的路径
			entModel.setHtmlValue(ClobUtil.stringToClob(entModel.getContentStr()));

			try {
				int d = this.businessSupportService.getWqsTravelService().doTownEntSave(entModel);
				if (d > 0) {
					this.tips = "添加成功!";
					return "success";
				}
				this.forward = "wqstravel/getTownEntList.action?searchName="+searchName;
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				this.forward = "wqstravel/getTownEntList.action?searchName="+searchName;
				return "success";
			}
		}
		return SUCCESS;
	}

	
	
	public String doTownEntEdit(){
		log.debug("[doTownEntSave]...");
		
		EnterpriseModel enterpriseModel=this.businessSupportService.getWqsTravelService().getEnterpriseModel(id) ;
		if(enterpriseModel!=null){
			String hmtlVal=ClobUtil.clobToString(enterpriseModel.getHtmlValue());
			if(hmtlVal!=null)enterpriseModel.setContentStr(hmtlVal);
			this.getRequest().setAttribute("model", enterpriseModel);
		}
		
		return SUCCESS;
	}
	
	
	
	public String doTownEntEditSubmit(){
		log.debug("[doTownEntEditSubmit]...");
		
		this.tips = "修改成功!";
		this.forward = "wqstravel/getTownEntList.action?searchName="+searchName;
		EnterpriseModel enterpriseModel=this.businessSupportService.getWqsTravelService().getEnterpriseModel(entModel.getId()) ;
		String oldHtmlPath=null;
		if(enterpriseModel!=null){
			enterpriseModel.setName(entModel.getName());
			if(contentStr!=null){
				// 生成一个HTML文件,并且得到其路径
				String html = getHtmlString(entModel.getName(), contentStr);
				String fileName = System.currentTimeMillis() + ".html";
				File path = new File(this.FILE_UPLOAD_PATH + "/town/enterprise/html/");
				if (!path.exists()) {
					path.mkdirs();
				}
				boolean b = new FileUtil().write(this.FILE_UPLOAD_PATH + "/town/enterprise/html/", html, fileName, false);// 覆盖原有的HTML文件
				if (!b) {
					this.tips = "生成正文文件时，出现异常";
					return SUCCESS;
				}
				oldHtmlPath=enterpriseModel.getHtmlPath();
				
				enterpriseModel.setHtmlPath("/town/enterprise/html/" + fileName);// 设置HTML文件的路径
				enterpriseModel.setHtmlValue(ClobUtil.stringToClob(contentStr));
			}
			
			int d=this.businessSupportService.getWqsTravelService().doEnterpriseModelUpdate(enterpriseModel);
			if(d>0){
				if(oldHtmlPath!=null&&!"".equals(oldHtmlPath)){
					File path=new File(this.FILE_UPLOAD_PATH + oldHtmlPath);
					this.deleteFile(path);
				}
			}else{
				this.tips = "修改失败";
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
	
	
	
	
	
	
	
	
	
	
	
	public String doTownEntUpload() {
		log.debug("[doTownEntUpload]...");
		String id = this.getRequest().getParameter("id");
		String name = this.getRequest().getParameter("name");
		try {
			name = new String(name.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.getRequest().setAttribute("id", id);
		this.getRequest().setAttribute("name", name);
		return SUCCESS;
	}

	public String doTownEntUploadSubmit() {
		log.debug("[doTownEntUploadSubmit]...");
		if (entModel != null) {
			String id = String.valueOf(entModel.getId());
			if (StringUtils.isNotEmpty(id)) {
				String icon = "";
				String filePath = FILE_UPLOAD_PATH;
				String virPath = "/town/enterprise/icon/";
				tips = "添加成功！";
				forward = "wqstravel/getTownEntList.action?searchName="+searchName;
				// 图片1
				if (entImgFileUrl1 != null) {
					log.debug("图片1文件大小为:::" + entImgFileUrl1.length());
					String fileName = String.valueOf(System.currentTimeMillis())
							+ entImgFileUrl1FileName.substring(entImgFileUrl1FileName.lastIndexOf("."));
					log.debug("file1 ====" + fileName);
					// 上传文件
					long returnData = 0l;
					returnData = FileUploader.upload(entImgFileUrl1, filePath + virPath, fileName, false);
					if (returnData > 0) {
						if(StringUtils.isEmpty(icon)){
							icon += virPath + fileName;
						}else{
							icon += "," + virPath + fileName;
						}
					} else if (returnData == -1) {
						tips = "图标文件为空，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -2) {
						tips = "图标上传异常，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -3) {
						tips = "图标已存在同名文件，请改名后上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() + "&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					}
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 图片2
				if (entImgFileUrl2 != null) {
					log.debug("图片2文件大小为:::" + entImgFileUrl2.length());
					String fileName2 = String.valueOf(System.currentTimeMillis())
							+ entImgFileUrl2FileName.substring(entImgFileUrl2FileName.lastIndexOf("."));
					log.debug("file2 ====" + fileName2);
					// 上传文件
					long returnData = 0l;
					returnData = FileUploader.upload(entImgFileUrl2, filePath + virPath, fileName2, false);
					if (returnData > 0) {
						if(StringUtils.isEmpty(icon)){
							icon += virPath + fileName2;
						}else{
							icon += "," + virPath + fileName2;
						}
					} else if (returnData == -1) {
						tips = "图标文件为空，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -2) {
						tips = "图标上传异常，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -3) {
						tips = "图标已存在同名文件，请改名后上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					}
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 图片3
				if (entImgFileUrl3 != null) {
					log.debug("图片3文件大小为:::" + entImgFileUrl3.length());
					String fileName = String.valueOf(System.currentTimeMillis())
							+ entImgFileUrl3FileName.substring(entImgFileUrl3FileName.lastIndexOf("."));
					// 上传文件
					long returnData = 0l;
					returnData = FileUploader.upload(entImgFileUrl3, filePath + virPath, fileName, false);
					if (returnData > 0) {
						if(StringUtils.isEmpty(icon)){
							icon += virPath + fileName;
						}else{
							icon += "," + virPath + fileName;
						}
					} else if (returnData == -1) {
						tips = "图标文件为空，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -2) {
						tips = "图标上传异常，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -3) {
						tips = "图标已存在同名文件，请改名后上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					}
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 图片4
				if (entImgFileUrl4 != null) {
					log.debug("图片4文件大小为:::" + entImgFileUrl4.length());
					String fileName = String.valueOf(System.currentTimeMillis())
							+ entImgFileUrl4FileName.substring(entImgFileUrl4FileName.lastIndexOf("."));
					// 上传文件
					long returnData = 0l;
					returnData = FileUploader.upload(entImgFileUrl4, filePath + virPath, fileName, false);
					if (returnData > 0) {
						if(StringUtils.isEmpty(icon)){
							icon += virPath + fileName;
						}else{
							icon += "," + virPath + fileName;
						}
					} else if (returnData == -1) {
						tips = "图标文件为空，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -2) {
						tips = "图标上传异常，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -3) {
						tips = "图标已存在同名文件，请改名后上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					}
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 图片5
				if (entImgFileUrl5 != null) {
					log.debug("图片5文件大小为:::" + entImgFileUrl5.length());
					String fileName = String.valueOf(System.currentTimeMillis())
							+ entImgFileUrl5FileName.substring(entImgFileUrl5FileName.lastIndexOf("."));
					// 上传文件
					long returnData = 0l;
					returnData = FileUploader.upload(entImgFileUrl5, filePath + virPath, fileName, false);
					if (returnData > 0) {
						if(StringUtils.isEmpty(icon)){
							icon += virPath + fileName;
						}else{
							icon += "," + virPath + fileName;
						}
					} else if (returnData == -1) {
						tips = "图标文件为空，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -2) {
						tips = "图标上传异常，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -3) {
						tips = "图标已存在同名文件，请改名后上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					}
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 图片6
				if (entImgFileUrl6 != null) {
					log.debug("图片6文件大小为:::" + entImgFileUrl6.length());
					String fileName = String.valueOf(System.currentTimeMillis())
							+ entImgFileUrl6FileName.substring(entImgFileUrl6FileName.lastIndexOf("."));
					// 上传文件
					long returnData = 0l;
					returnData = FileUploader.upload(entImgFileUrl6, filePath + virPath, fileName, false);
					if (returnData > 0) {
						if(StringUtils.isEmpty(icon)){
							icon += virPath + fileName;
						}else{
							icon += "," + virPath + fileName;
						}
					} else if (returnData == -1) {
						tips = "图标文件为空，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -2) {
						tips = "图标上传异常，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -3) {
						tips = "图标已存在同名文件，请改名后上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					}
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 图片7
				if (entImgFileUrl7 != null) {
					log.debug("图片7文件大小为:::" + entImgFileUrl7.length());
					String fileName = String.valueOf(System.currentTimeMillis())
							+ entImgFileUrl7FileName.substring(entImgFileUrl7FileName.lastIndexOf("."));
					// 上传文件
					long returnData = 0l;
					returnData = FileUploader.upload(entImgFileUrl7, filePath + virPath, fileName, false);
					if (returnData > 0) {
						if(StringUtils.isEmpty(icon)){
							icon += virPath + fileName;
						}else{
							icon += "," + virPath + fileName;
						}
					} else if (returnData == -1) {
						tips = "图标文件为空，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -2) {
						tips = "图标上传异常，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -3) {
						tips = "图标已存在同名文件，请改名后上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					}
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 图片8
				if (entImgFileUrl8 != null) {
					log.debug("图片8文件大小为:::" + entImgFileUrl8.length());
					String fileName = String.valueOf(System.currentTimeMillis())
							+ entImgFileUrl8FileName.substring(entImgFileUrl8FileName.lastIndexOf("."));
					// 上传文件
					long returnData = 0l;
					returnData = FileUploader.upload(entImgFileUrl8, filePath + virPath, fileName, false);
					if (returnData > 0) {
						if(StringUtils.isEmpty(icon)){
							icon += virPath + fileName;
						}else{
							icon += "," + virPath + fileName;
						}
					} else if (returnData == -1) {
						tips = "图标文件为空，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -2) {
						tips = "图标上传异常，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -3) {
						tips = "图标已存在同名文件，请改名后上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					}
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 图片9
				if (entImgFileUrl9 != null) {
					log.debug("图片9文件大小为:::" + entImgFileUrl9.length());
					String fileName = String.valueOf(System.currentTimeMillis())
							+ entImgFileUrl9FileName.substring(entImgFileUrl9FileName.lastIndexOf("."));
					// 上传文件
					long returnData = 0l;
					returnData = FileUploader.upload(entImgFileUrl9, filePath + virPath, fileName, false);
					if (returnData > 0) {
						if(StringUtils.isEmpty(icon)){
							icon += virPath + fileName;
						}else{
							icon += "," + virPath + fileName;
						}
					} else if (returnData == -1) {
						tips = "图标文件为空，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -2) {
						tips = "图标上传异常，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -3) {
						tips = "图标已存在同名文件，请改名后上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					}
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 图片10
				if (entImgFileUrl10 != null) {
					log.debug("图片10文件大小为:::" + entImgFileUrl10.length());
					String fileName = String.valueOf(System.currentTimeMillis())
							+ entImgFileUrl10FileName.substring(entImgFileUrl10FileName.lastIndexOf("."));
					// 上传文件
					long returnData = 0l;
					returnData = FileUploader.upload(entImgFileUrl10, filePath + virPath, fileName, false);
					if (returnData > 0) {
						if(StringUtils.isEmpty(icon)){
							icon += virPath + fileName;
						}else{
							icon += "," + virPath + fileName;
						}
					} else if (returnData == -1) {
						tips = "图标文件为空，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -2) {
						tips = "图标上传异常，请重新上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					} else if (returnData == -3) {
						tips = "图标已存在同名文件，请改名后上传！";
						forward = "wqstravel/doTownEntUpload.action?id=" + entModel.getId() +"&name=" + entModel.getName()+"&searchName="+searchName;
						return SUCCESS;
					}
				}
				int d = this.businessSupportService.getWqsTravelService().updateEntIcon(icon,id);
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

	public EnterpriseModel getEntModel() {
		return entModel;
	}

	public void setEntModel(EnterpriseModel entModel) {
		this.entModel = entModel;
	}

	public File getEntImgFileUrl1() {
		return entImgFileUrl1;
	}

	public void setEntImgFileUrl1(File entImgFileUrl1) {
		this.entImgFileUrl1 = entImgFileUrl1;
	}

	public String getEntImgFileUrl1FileName() {
		return entImgFileUrl1FileName;
	}

	public void setEntImgFileUrl1FileName(String entImgFileUrl1FileName) {
		this.entImgFileUrl1FileName = entImgFileUrl1FileName;
	}

	public String getEntImgFileUrl1ContentType() {
		return entImgFileUrl1ContentType;
	}

	public void setEntImgFileUrl1ContentType(String entImgFileUrl1ContentType) {
		this.entImgFileUrl1ContentType = entImgFileUrl1ContentType;
	}

	public File getEntImgFileUrl2() {
		return entImgFileUrl2;
	}

	public void setEntImgFileUrl2(File entImgFileUrl2) {
		this.entImgFileUrl2 = entImgFileUrl2;
	}

	public String getEntImgFileUrl2FileName() {
		return entImgFileUrl2FileName;
	}

	public void setEntImgFileUrl2FileName(String entImgFileUrl2FileName) {
		this.entImgFileUrl2FileName = entImgFileUrl2FileName;
	}

	public String getEntImgFileUrl2ContentType() {
		return entImgFileUrl2ContentType;
	}

	public void setEntImgFileUrl2ContentType(String entImgFileUrl2ContentType) {
		this.entImgFileUrl2ContentType = entImgFileUrl2ContentType;
	}

	public File getEntImgFileUrl3() {
		return entImgFileUrl3;
	}

	public void setEntImgFileUrl3(File entImgFileUrl3) {
		this.entImgFileUrl3 = entImgFileUrl3;
	}

	public String getEntImgFileUrl3FileName() {
		return entImgFileUrl3FileName;
	}

	public void setEntImgFileUrl3FileName(String entImgFileUrl3FileName) {
		this.entImgFileUrl3FileName = entImgFileUrl3FileName;
	}

	public String getEntImgFileUrl3ContentType() {
		return entImgFileUrl3ContentType;
	}

	public void setEntImgFileUrl3ContentType(String entImgFileUrl3ContentType) {
		this.entImgFileUrl3ContentType = entImgFileUrl3ContentType;
	}

	public File getEntImgFileUrl4() {
		return entImgFileUrl4;
	}

	public void setEntImgFileUrl4(File entImgFileUrl4) {
		this.entImgFileUrl4 = entImgFileUrl4;
	}

	public String getEntImgFileUrl4FileName() {
		return entImgFileUrl4FileName;
	}

	public void setEntImgFileUrl4FileName(String entImgFileUrl4FileName) {
		this.entImgFileUrl4FileName = entImgFileUrl4FileName;
	}

	public String getEntImgFileUrl4ContentType() {
		return entImgFileUrl4ContentType;
	}

	public void setEntImgFileUrl4ContentType(String entImgFileUrl4ContentType) {
		this.entImgFileUrl4ContentType = entImgFileUrl4ContentType;
	}

	public File getEntImgFileUrl5() {
		return entImgFileUrl5;
	}

	public void setEntImgFileUrl5(File entImgFileUrl5) {
		this.entImgFileUrl5 = entImgFileUrl5;
	}

	public String getEntImgFileUrl5FileName() {
		return entImgFileUrl5FileName;
	}

	public void setEntImgFileUrl5FileName(String entImgFileUrl5FileName) {
		this.entImgFileUrl5FileName = entImgFileUrl5FileName;
	}

	public String getEntImgFileUrl5ContentType() {
		return entImgFileUrl5ContentType;
	}

	public void setEntImgFileUrl5ContentType(String entImgFileUrl5ContentType) {
		this.entImgFileUrl5ContentType = entImgFileUrl5ContentType;
	}

	public File getEntImgFileUrl6() {
		return entImgFileUrl6;
	}

	public void setEntImgFileUrl6(File entImgFileUrl6) {
		this.entImgFileUrl6 = entImgFileUrl6;
	}

	public String getEntImgFileUrl6FileName() {
		return entImgFileUrl6FileName;
	}

	public void setEntImgFileUrl6FileName(String entImgFileUrl6FileName) {
		this.entImgFileUrl6FileName = entImgFileUrl6FileName;
	}

	public String getEntImgFileUrl6ContentType() {
		return entImgFileUrl6ContentType;
	}

	public void setEntImgFileUrl6ContentType(String entImgFileUrl6ContentType) {
		this.entImgFileUrl6ContentType = entImgFileUrl6ContentType;
	}

	public File getEntImgFileUrl7() {
		return entImgFileUrl7;
	}

	public void setEntImgFileUrl7(File entImgFileUrl7) {
		this.entImgFileUrl7 = entImgFileUrl7;
	}

	public String getEntImgFileUrl7FileName() {
		return entImgFileUrl7FileName;
	}

	public void setEntImgFileUrl7FileName(String entImgFileUrl7FileName) {
		this.entImgFileUrl7FileName = entImgFileUrl7FileName;
	}

	public String getEntImgFileUrl7ContentType() {
		return entImgFileUrl7ContentType;
	}

	public void setEntImgFileUrl7ContentType(String entImgFileUrl7ContentType) {
		this.entImgFileUrl7ContentType = entImgFileUrl7ContentType;
	}

	public File getEntImgFileUrl8() {
		return entImgFileUrl8;
	}

	public void setEntImgFileUrl8(File entImgFileUrl8) {
		this.entImgFileUrl8 = entImgFileUrl8;
	}

	public String getEntImgFileUrl8FileName() {
		return entImgFileUrl8FileName;
	}

	public void setEntImgFileUrl8FileName(String entImgFileUrl8FileName) {
		this.entImgFileUrl8FileName = entImgFileUrl8FileName;
	}

	public String getEntImgFileUrl8ContentType() {
		return entImgFileUrl8ContentType;
	}

	public void setEntImgFileUrl8ContentType(String entImgFileUrl8ContentType) {
		this.entImgFileUrl8ContentType = entImgFileUrl8ContentType;
	}

	public File getEntImgFileUrl9() {
		return entImgFileUrl9;
	}

	public void setEntImgFileUrl9(File entImgFileUrl9) {
		this.entImgFileUrl9 = entImgFileUrl9;
	}

	public String getEntImgFileUrl9FileName() {
		return entImgFileUrl9FileName;
	}

	public void setEntImgFileUrl9FileName(String entImgFileUrl9FileName) {
		this.entImgFileUrl9FileName = entImgFileUrl9FileName;
	}

	public String getEntImgFileUrl9ContentType() {
		return entImgFileUrl9ContentType;
	}

	public void setEntImgFileUrl9ContentType(String entImgFileUrl9ContentType) {
		this.entImgFileUrl9ContentType = entImgFileUrl9ContentType;
	}

	public File getEntImgFileUrl10() {
		return entImgFileUrl10;
	}

	public void setEntImgFileUrl10(File entImgFileUrl10) {
		this.entImgFileUrl10 = entImgFileUrl10;
	}

	public String getEntImgFileUrl10FileName() {
		return entImgFileUrl10FileName;
	}

	public void setEntImgFileUrl10FileName(String entImgFileUrl10FileName) {
		this.entImgFileUrl10FileName = entImgFileUrl10FileName;
	}

	public String getEntImgFileUrl10ContentType() {
		return entImgFileUrl10ContentType;
	}

	public void setEntImgFileUrl10ContentType(String entImgFileUrl10ContentType) {
		this.entImgFileUrl10ContentType = entImgFileUrl10ContentType;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContentStr() {
		return contentStr;
	}

	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}
	
}

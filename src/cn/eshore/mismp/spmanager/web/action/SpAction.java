package cn.eshore.mismp.spmanager.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.eshore.mismp.dao.domain.ProductFeeVO;
import cn.eshore.mismp.dao.domain.ProductTypeVO;
import cn.eshore.mismp.spmanager.dao.model.Factory;
import cn.eshore.mismp.spmanager.dao.model.Product;
import cn.eshore.mismp.spmanager.dao.model.SoftAudit;
import cn.eshore.mismp.spmanager.dao.model.SoftType;
import cn.eshore.mismp.spmanager.dao.model.SoftVersion;
import cn.eshore.mismp.spmanager.dao.model.Sp;
import cn.eshore.mismp.spmanager.dao.model.Terminal;
import cn.eshore.mismp.spmanager.dao.model.Virtual;
import cn.eshore.mismp.spmanager.service.SpService;
import cn.eshore.mismp.util.FileUploader;
import cn.eshore.mismp.util.HttpUtil;
import cn.eshore.mismp.util.MD5;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.Util;
import cn.eshore.mismp.version.common.FcBizBeanFactory;
import cn.eshore.mismp.version.service.VersionOperService;
import cn.eshore.mismp.web.action.BaseAction;

/**
 * <p>Title: SpAction</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p><a href="SpAction.java.html"><i>View Source</i></a></p>
 * @author OYK
 * @version 1.0
 */
public class SpAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	/*页面传递的参数*/
	private String description;//描述
	private String name;//页面传递的名称参数
	private String prov_id;//省份
	private int flag;//是否查询的标志
	private Sp sp;//页面传递的sp
	private Factory fac;//页面传递的设备厂商
	private int id;//页面传递的ID
	private Virtual vir;//页面传递的虚拟类型
	private Terminal ter;//页面传递的机型设备
	private int fac_id;//页面传递的厂商ID	
	private int state;//页面传递的状态值
	private int pro_state=-1;//产品状态
	private String pro_code;//产品编号
	private Product pro;//页面传递的产品
	
	private String starttime;//开始时间
	private String endtime;//结束时间
	private String spName;//sp名
	
	 //上传文件
	private File datafile;// 上传的文件
	private String datafileFileName;// 要上传的文件名
	
	private ProductFeeVO profee;
	
	private ProductTypeVO protype;
	
	private File iconFile;//产品类型图标文件上传

	private String iconFileFileName;
	
	private static String uploadImagePath = null;

	static {
		uploadImagePath = MobileGlobals.getProperty("upload.file.path");
	}

	public File getDatafile() {
		return datafile;
	}
	public void setDatafile(File datafile) {
		this.datafile = datafile;
	}
	public String getDatafileFileName() {
		return datafileFileName;
	}
	public void setDatafileFileName(String datafileFileName) {
		this.datafileFileName = datafileFileName;
	}
	public int getFac_id() {
		return fac_id;
	}
	public void setFac_id(int fac_id) {
		this.fac_id = fac_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sp getSp() {
		return sp;
	}
	public void setSp(Sp sp) {
		this.sp = sp;
	}
	public Virtual getVir() {
		return vir;
	}
	public void setVir(Virtual vir) {
		this.vir = vir;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProv_id() {
		return prov_id;
	}
	public void setProv_id(String prov_id) {
		this.prov_id = prov_id;
	}
	public Factory getFac() {
		return fac;
	}
	public void setFac(Factory fac) {
		this.fac = fac;
	}
	
	
	public ProductFeeVO getProfee() {
		return profee;
	}
	public void setProfee(ProductFeeVO profee) {
		this.profee = profee;
	}
	
	
	public ProductTypeVO getProtype() {
		return protype;
	}
	public void setProtype(ProductTypeVO protype) {
		this.protype = protype;
	}
	/*
	 * SP管理
	 */
	public String spInfo(){
		HttpServletRequest req=this.getRequest();
		
		if (Util.nullToStr(req.getParameter("action")).equals("del")){
			if (0>this.getBusinessSupportService().getSpService().getSpDAO().delSp(id)){
				req.setAttribute("myinfo", "删除SP【" + 
						this.getBusinessSupportService().getSpService().getSpDAO().getSpById(id).getSp_name()+
						"】<span style='color:red;font-size:30px'>失败！</span>因为它已经被SP产品引用，不允许删除。");
				req.setAttribute("myurl", "spInfo.action");
				return "delfail";
			}
		}
		req.setAttribute("province", this.getBusinessSupportService().
				getSpService().getSpDAO().provice());
		name=Util.nullToStr(name);
		prov_id=Util.nullToStr(prov_id);
		description=Util.nullToStr(description);
		List<Sp> a=this.getBusinessSupportService().
				getSpService().getSpDAO().spList(prov_id, name,description, pageSize, pageNum);
		for (int i=0;i<a.size();i++){
			Sp sp=a.get(i);
			sp.setSp_local_name(this.getBusinessSupportService().
				getSpService().getSpDAO().getProvincebyid(sp.getSp_local()));
		}
		req.setAttribute("reslist", a);
		
		return SUCCESS;
	}
	/*
	 * SP添加
	 */
	public String spAdd(){
		HttpServletRequest req=this.getRequest();
		req.setAttribute("province", this.getBusinessSupportService().getSpService().getSpDAO().provice());
		if (flag!=0){
			sp.setSp_local(this.prov_id);
			this.getBusinessSupportService().getSpService().getSpDAO().addSp(sp);
			req.setAttribute("myinfo", "添加SP【"+sp.getSp_name()+"】完成。");
			req.setAttribute("myurl", "spInfo.action");
			return "addok";
		}
	
		return SUCCESS;
	}
	/*
	 * SP修改
	 */
	public String spModify(){
		HttpServletRequest req=this.getRequest();
		if(flag!=0){
			sp.setSp_local(this.prov_id);
			this.getBusinessSupportService().getSpService().getSpDAO().modifySp(sp);
			req.setAttribute("myinfo", "修改SP【"+sp.getSp_name()+"】完成。");
			req.setAttribute("myurl", "spInfo.action");
			return "modifyok";
		}
	    sp=this.getBusinessSupportService().getSpService().getSpDAO().getSpById(id);
		return SUCCESS;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * 设备厂商管理
	 */
	public String facInfo(){
		HttpServletRequest req = this.getRequest();
		if (Util.nullToStr(req.getParameter("action")).equals("del")) {
			if (0>this.getBusinessSupportService().getSpService().getSpDAO()
					.delFac(id)){
				req.setAttribute("myinfo", "删除设备厂商【" + 
						this.getBusinessSupportService().getSpService().getSpDAO().getFacById(id).getFac_name()+
						"】<span style='color:red;font-size:30px'>失败！</span>因为它已经被机型设备所引用，不允许删除。");
				req.setAttribute("myurl", "facInfo.action");
				return "delfail";
			}
		}
		name = Util.nullToStr(name);
		description = Util.nullToStr(description);
		List<Factory> a = this.getBusinessSupportService().getSpService().getSpDAO()
				.facList(name, description, pageSize, pageNum);
		
		req.setAttribute("reslist", a);
		return SUCCESS;
	}
	
	/*
	 * 设备厂商添加
	 */
	public String facAdd(){
		HttpServletRequest req = this.getRequest();
		if (flag != 0) {
			this.getBusinessSupportService().getSpService().getSpDAO().addFac(
					fac);
			req.setAttribute("myinfo", "添加设备厂商【" + fac.getFac_name() + "】完成。");
			req.setAttribute("myurl", "facInfo.action");
			return "addok";
		}
	
		return SUCCESS;
	}
	/*
	 * 设备厂商修改
	 */
	public String facModify(){
		HttpServletRequest req=this.getRequest();
		if(flag!=0){
			this.getBusinessSupportService().getSpService().getSpDAO().modifyFac(fac);
			req.setAttribute("myinfo", "修改设备厂商【"+fac.getFac_name()+"】完成。");
			req.setAttribute("myurl", "facInfo.action");
			return "modifyok";
		}
	    fac=this.getBusinessSupportService().getSpService().getSpDAO().getFacById(id);
		return SUCCESS;
	}
	
	/*
	 * 虚拟类型管理
	 */
	public String virInfo(){
		HttpServletRequest req = this.getRequest();
		if (Util.nullToStr(req.getParameter("action")).equals("del")) {
			if (0>this.getBusinessSupportService().getSpService().getSpDAO()
					.delVir(id)){
				req.setAttribute("myinfo", "删除虚拟类型【" + 
						this.getBusinessSupportService().getSpService().getSpDAO().getVirById(id).getVir_name()+
						"】<span style='color:red;font-size:30px'>失败！</span>因为它已经被设备机型引用，不允许删除。");
				req.setAttribute("myurl", "virInfo.action");
				return "delfail";
			}
		}
		name = Util.nullToStr(name);
		description = Util.nullToStr(description);
		List<Virtual> a = this.getBusinessSupportService().getSpService().getSpDAO()
				.virList(name, description, pageSize, pageNum);
		req.setAttribute("reslist", a);
		return SUCCESS;
	}
	/*
	 * 虚拟类型添加
	 */
	public String virAdd(){
		HttpServletRequest req = this.getRequest();
		if (flag != 0) {
			this.getBusinessSupportService().getSpService().getSpDAO().addVir(
					this.vir);
			req.setAttribute("myinfo", "添加虚拟类型【" + vir.getVir_name() + "】完成。");
			req.setAttribute("myurl", "virInfo.action");
			return "addok";
		}
	
		return SUCCESS;
	}
	/*
	 * 虚拟类型修改
	 */
	public String virModify(){
		HttpServletRequest req=this.getRequest();
		if(flag!=0){
			this.getBusinessSupportService().getSpService().getSpDAO().modifyVir(vir);
			req.setAttribute("myinfo", "修改虚拟类型【"+ vir.getVir_name()+"】完成。");
			req.setAttribute("myurl", "virInfo.action");
			return "modifyok";
		}
	    vir=this.getBusinessSupportService().getSpService().getSpDAO().getVirById(id);
		return SUCCESS;
	}
	/*
	 * 机型设备管理
	 */
	public String terInfo(){
		HttpServletRequest req = this.getRequest();
		if (Util.nullToStr(req.getParameter("action")).equals("del")) {
			String temp=this.getBusinessSupportService().getSpService().getSpDAO()
					.delTer(id);
			if (temp != "") {
				temp = req.getSession().getServletContext().getRealPath("/uploadpic/" + temp);
				new File(temp).delete();
			}
		}
		name = Util.nullToStr(name);
		description = Util.nullToStr(description);
		List<Terminal> a = this.getBusinessSupportService().getSpService().getSpDAO()
				.terList(name, description, fac_id, pageSize, pageNum);
		for (int i=0;i<a.size();i++){
			Terminal t=(Terminal)a.get(i);
			t.setFac_name(this.getBusinessSupportService().getSpService().getSpDAO().getFacById(t.getFac_id()).getFac_name());
			t.setVir_name(this.getBusinessSupportService().getSpService().getSpDAO().getVirById(t.getVir_id()).getVir_name());
		}
		req.setAttribute("faclist", this.getBusinessSupportService().getSpService().getSpDAO().facDropList());
		req.setAttribute("reslist", a);
		return SUCCESS;
	}
	
	/*
	 * 机型设备添加
	 */
	public String terAdd(){
		HttpServletRequest req = this.getRequest();
		if (flag != 0) {
			System.out.println(datafileFileName);
			String onlyFname =System.currentTimeMillis() +"_"+ datafileFileName;
				String tips = "";// 返回到客户端的信息(可能是错误提示，操作成功、失败等信息)
				String fileTempSavePath = req.getSession().getServletContext().getRealPath("/uploadpic/");
				File filePath = new File(fileTempSavePath);// 文件在服务器存放路径
				if (!filePath.isDirectory()) {
					filePath.mkdirs();
				}

				String fileName = filePath + File.separator + onlyFname;

				/** ***********************上传文件部分******************** */
				FileOutputStream fos = null;
				FileInputStream fis = null;
				try {
					fos = new FileOutputStream(fileName);
					fis = new FileInputStream(getDatafile());
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
				} catch (FileNotFoundException e) {
					tips = "内部错误，系统找不到指定的路径！";
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (fis != null)
							fis.close();
						if (fos != null)
							fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			String myinfo=tips;
			if (myinfo.equals("")){//文件上传没错误的情况
				ter.setTer_picpath(onlyFname);
				try {
					this.getBusinessSupportService().getSpService().getSpDAO().addTer(
							this.ter);
				} catch (Exception e) {
					e.printStackTrace();
				}
				myinfo="添加机型设备【" + ter.getTer_name() + "】完成。";
			}
		
			req.setAttribute("myinfo", myinfo);
			req.setAttribute("myurl", "terInfo.action");
			return "addok";
		}
		req.setAttribute("virlist",this.getBusinessSupportService().getSpService().getSpDAO().virDropList());
		req.setAttribute("faclist", this.getBusinessSupportService().getSpService().getSpDAO().facDropList());
		return SUCCESS;
	}
	
	/*
	 * 机型设备修改
	 */
	public String terModify(){
		HttpServletRequest req=this.getRequest();
		if(flag!=0){
			this.getBusinessSupportService().getSpService().getSpDAO().modifyTer(ter);
			req.setAttribute("myinfo", "修改机型设备【"+ter.getTer_name()+"】完成。");
			req.setAttribute("myurl", "terInfo.action");
			return "modifyok";
		}
		req.setAttribute("virlist",this.getBusinessSupportService().getSpService().getSpDAO().virDropList());
		req.setAttribute("faclist", this.getBusinessSupportService().getSpService().getSpDAO().facDropList());
	    ter=this.getBusinessSupportService().getSpService().getSpDAO().getTerById(id);
		return SUCCESS;
	}
	
	/*
	 * SP产品修改
	 */
	public String proModify(){
		HttpServletRequest req=this.getRequest();
		if(flag!=0){
			if (pro.getPro_type()==3){
				pro.setPro_fee(0);//对免费产品进行处理，将费用置为0
			}
			this.getBusinessSupportService().getSpService().getSpDAO().modifyPro(pro);
			int days = profee.getDays();
			int times = profee.getTimes();
			//1：时间段 2：次数'
			//天数与次数全为0
			if(days == 0 && times == 0){
				profee.setProType(1);
			}
			//天数不为0
			if(days != 0){
				profee.setProType(1);
			}
			//次数不为0
			if(times!= 0){
				profee.setProType(2);
			}
			//修改费率
			ProductFeeVO vo = this.getBusinessSupportService().getSpService().getSpDAO().getProFeeByProId(new Integer(profee.getProId()).intValue());
			//没数据，需要插入费率数据
			if(vo == null || vo.getId() == 0){
				this.getBusinessSupportService().getSpService().getSpDAO().addProFee(profee);
			}
			//需要更新费率
			else
			{
				this.getBusinessSupportService().getSpService().getSpDAO().modifyProFee(profee);
			}
			
			req.setAttribute("myinfo", "修改SP产品【"+pro.getPro_name()+"】完成。");
			req.setAttribute("myurl", "proInfo.action");
			return "modifyok";
		}
	    pro=this.getBusinessSupportService().getSpService().getSpDAO().getProById(id);
	    //获取费率
	    profee = this.getBusinessSupportService().getSpService().getSpDAO().getProFeeByProId(id);
	    profee.setProId(new Integer(pro.getPro_id()).toString());
	    req.setAttribute("splist", this.getBusinessSupportService().getSpService().getSpDAO().spDropList());
		req.setAttribute("prolist", this.getBusinessSupportService().getSpService().getSpDAO().proDropList());
		//增加产品类型 20091210
		ProductTypeVO vo = new ProductTypeVO();
		req.setAttribute("proTypeList", this.getBusinessSupportService().getSpService().getProductTypeList(vo, -1, -1));
	
	
		return SUCCESS;
	}
	/*
	 * SP产品管理
	 */
	public String proInfo(){
		HttpServletRequest req = this.getRequest();
		//删除产品
		if (Util.nullToStr(req.getParameter("action")).equals("del")) {
			int temp=this.getBusinessSupportService().getSpService().getSpDAO()
			.delPro(id);
			if (temp<0){
				String myinfo="删除SP产品【" + 
				this.getBusinessSupportService().getSpService().getSpDAO().getProById(id).getPro_name()+
				"】<span style='color:red;font-size:30px'>失败！</span>";
				if (temp==-1){
					myinfo+="因为它已经作为其他产品的父产品。";
				}else if(temp==-2){
					myinfo+="因为该产品下已经存在软件，不允许删除！";
				}
				req.setAttribute("myinfo", myinfo);
				req.setAttribute("myurl", "proInfo.action");
				return "delfail";
			}
		}
		
		//HW同步产品20100402
		if (Util.nullToStr(req.getParameter("action")).equals("synAppInfo")){
			//http调用同步地址
			String url = MobileGlobals.getProperty("http.hwSysApp.server");
			
			String userid = "hwSynUser";
			long timestamp = System.currentTimeMillis();
			String hashcode = "";
			String pwd = "eshore!@#$%^admin";
			StringBuffer unencodeHashCodeBuffer = new StringBuffer("").append(userid).append(timestamp).append(pwd);
			String unencodeHashCode = unencodeHashCodeBuffer.toString();
			String encodeHashCode = MD5.crypt(unencodeHashCode).toUpperCase();
			hashcode = encodeHashCode;
			String paramsString = "method=synAppInfo&userid=hwSynUser&timestamp=" + timestamp+"&hashcode=" + hashcode;
			HttpUtil.doGet(url, paramsString);
		} 
		
		name = Util.nullToStr(name);
		pro_code=Util.nullToStr(pro_code);
		spName = Util.nullToStr(spName);
		List<Product> a = this.getBusinessSupportService().getSpService().getSpDAO()
				.proList(spName, name, pro_code,pro_state, pageSize, pageNum);
		for (int i=0;i<a.size();i++){
			Product pro=a.get(i);
			Sp sp=this.getBusinessSupportService().getSpService().getSpDAO().getSpById(pro.getSp_id());
			sp.setSp_local_name(this.getBusinessSupportService().getSpService().getSpDAO().getProvincebyid(sp.getSp_local()));
			pro.setSp(sp);
			if (pro.getPro_parent_id()!=pro.getPro_id()){
				pro.setPro_parent_name(this.getBusinessSupportService().getSpService().getSpDAO().getProById(pro.getPro_parent_id()).getPro_name());
			}
		}
		req.setAttribute("reslist", a);
		return SUCCESS;
	}
	/*
	 * SP产品添加
	 */
	public String proAdd(){
		HttpServletRequest req = this.getRequest();
		if (flag != 0) {
			if (pro.getPro_type()==3){
				pro.setPro_fee(0);//对免费产品进行处理，将费用置为0
			}
			int proId = this.getBusinessSupportService().getSpService().getSpDAO().addPro(
					this.pro);
			profee.setProId(new Integer(proId).toString());
			int days = profee.getDays();
			int times = profee.getTimes();
			//1：时间段 2：次数'
			//天数与次数全为0
			if(days == 0 && times == 0){
				profee.setProType(1);
			}
			//天数不为0
			if(days != 0){
				profee.setProType(1);
			}
			//次数不为0
			if(times!= 0){
				profee.setProType(2);
			}
			//保存费率
			this.getBusinessSupportService().getSpService().getSpDAO().addProFee(profee);
			
			req.setAttribute("myinfo", "添加SP产品【" + pro.getPro_name() + "】完成。");
			req.setAttribute("myurl", "proInfo.action");
			return "addok";
		}
		req.setAttribute("splist", this.getBusinessSupportService().getSpService().getSpDAO().spDropList());
		req.setAttribute("prolist", this.getBusinessSupportService().getSpService().getSpDAO().proDropList());
		
		//增加产品类型 20091210
		ProductTypeVO vo = new ProductTypeVO();
		req.setAttribute("proTypeList", this.getBusinessSupportService().getSpService().getProductTypeList(vo, -1, -1));
		return SUCCESS;
	}
	/*
	 * SP软件版本审核列表
	 */
	public String svInfo(){
		HttpServletRequest req = this.getRequest();
		int ver=0;
		if (null!=req.getParameter("ver")){
			req.setAttribute("ver", 1);
			ver=1;
		}
		name = Util.nullToStr(name);
		description = Util.nullToStr(description);
		starttime=Util.nullToStr(starttime);
		endtime=Util.nullToStr(endtime);
		
		List<SoftAudit> a= this.businessSupportService.getSpService().getSpDAO().
		svList(name, description, starttime, ver, endtime, pageSize, pageNum);
		
		for (int i=0;i<a.size();i++){
			SoftAudit sa=a.get(i);
			SoftVersion sv=this.getBusinessSupportService().getSpService().getSpDAO().
				getSoftVersionById(sa.getSv_id());
			//返回结果存在
			if(sv != null){
				SoftType st = this.getBusinessSupportService().getSpService()
						.getSpDAO().getSoftTypeById(sv.getSt_id());
				Product pro = this.getBusinessSupportService().getSpService()
						.getSpDAO().getProById(st.getPro_id());
				Sp sp = this.getBusinessSupportService().getSpService()
						.getSpDAO().getSpById(pro.getSp_id());
				pro.setSp(sp);
				st.setPro(pro);
				sv.setSt(st);
				sa.setSv(sv);
			
			}
			//返回结果不存在
			else{
				a.remove(i);
				Pagination pagin = (Pagination)a;
				//记录数减1
				pagin.setRecordCount(pagin.getRecordCount() - 1);
				i --;
			}
		}
		
		req.setAttribute("reslist",  a);
		return SUCCESS;
	}
	
	/*
	 * SP软件版本审核
	 */
	public String svDetail(){
		HttpServletRequest req = this.getRequest();
		SoftAudit sa = this.getBusinessSupportService().getSpService()
				.getSpDAO().getSoftAuditById(id);
		SoftVersion sv = this.getBusinessSupportService().getSpService()
				.getSpDAO().getSoftVersionById(sa.getSv_id());
		SoftType st = this.getBusinessSupportService().getSpService()
				.getSpDAO().getSoftTypeById(sv.getSt_id());
		Product pro = this.getBusinessSupportService().getSpService()
				.getSpDAO().getProById(st.getPro_id());
		Sp sp=this.getBusinessSupportService().getSpService().getSpDAO().getSpById(pro.getSp_id());
		sp.setSp_local_name(this.getBusinessSupportService().getSpService().
				getSpDAO().getProvincebyid(sp.getSp_local()));
		pro.setSp(sp);
		st.setPro(pro);
		sv.setSt(st);
		sa.setSv(sv);
		req.setAttribute("rl", sa);
//		if (null!=req.getParameter("ver")){
//			req.setAttribute("ver", 1);
//		}
		return SUCCESS;
	}
	/*
	 * 审核软件产品
	 */
	public String svAudit(){
		HttpServletRequest req = this.getRequest();
		int state=2;
		String remark=Util.nullToStr(req.getParameter("remark"));
		String audit_by=Util.object2Str(req.getSession().getAttribute("account"));
		SoftAudit sa=this.getBusinessSupportService().getSpService().getSpDAO().getSoftAuditById(id);
		if (Util.nullToStr(req.getParameter("action")).equals("no")) {//不通过
			state=3;
			req.setAttribute("myinfo", "审核结果：SP软件产品【" + 
					sa.getSoft_name()+
						"】版本：【"+sa.getSoft_version()+"】<span style='color:red;font-size:30px'>审核未通过！</span>");				
		}else{
			req.setAttribute("myinfo", "审核结果：SP软件产品【" + 
					sa.getSoft_name()+
					"】版本：【"+sa.getSoft_version()+"】<span style='color:green;font-size:30px'>审核通过！</span>");	
		}

		int updateCount = this.getBusinessSupportService().getSpService().getSpDAO().svAudit(id, state,remark,audit_by);
		//更新数据大于0
		if(updateCount > 0){
			//更新软件统计数据
			VersionOperService versionOperService = (VersionOperService) FcBizBeanFactory.getBean("versionOperService");
			versionOperService.updateTetPtStat("" + sa.getSv_id());

		}
		
		req.setAttribute("myurl", "svInfo.action");
		return SUCCESS;
	}
	public Terminal getTer() {
		return ter;
	}
	public void setTer(Terminal ter) {
		this.ter = ter;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPro_state() {
		return pro_state;
	}
	public void setPro_state(int pro_state) {
		this.pro_state = pro_state;
	}
	public String getPro_code() {
		return pro_code;
	}
	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}


	/*
	 * 产品类型管理 20091209
	 */
	public String ptInfo(){
		HttpServletRequest req = this.getRequest();
		SpService spService = this.getBusinessSupportService().getSpService();
		if (Util.nullToStr(req.getParameter("action")).equals("del")) {
			Product vo = spService.getProByPTId(new Integer(id).toString());
			//该类型被使用
			if (vo.getPro_id() != 0){
				req.setAttribute("myinfo", "删除产品类型【" + 
						spService.getProductTypeById(new Integer(id).toString()).getName()+
						"】<span style='color:red;font-size:30px'>失败！</span>因为它已经被产品引用，不允许删除。");
				req.setAttribute("myurl", "ptInfo.action");
				return "delfail";
			}
			//未被使用，可以删除
			else{
				spService.delProType(new Integer(id).toString());
			}
		}
		name = Util.nullToStr(name);
		description = Util.nullToStr(description);
		//设置查询条件
		ProductTypeVO vo = new ProductTypeVO();
		vo.setName(name);
		vo.setDesc(description);
		List<ProductTypeVO> list = spService.getProductTypeList(vo, pageSize, pageNum);
		req.setAttribute("reslist", list);
		return SUCCESS;
	}
	/*
	 * 产品类型添加
	 */
	public String ptAdd(){
		HttpServletRequest req = this.getRequest();
		if (flag != 0) {
			SpService spService = this.getBusinessSupportService().getSpService();
			//上传类型图标
			String dbPath = "/appTypeIcon";
			String iconPath = "";
			if(iconFile != null && StringUtils.isNotEmpty(iconFileFileName)){
				iconPath = uploadImagePath + dbPath;
				long returnData = FileUploader.upload(iconFile, iconPath,
						iconFileFileName, true);
				if (returnData >= 0) {
					protype.setIconFileName(iconFileFileName);
					protype.setIconFilePath(dbPath + "/" + iconFileFileName);
				}
			}
			spService.addProductType(protype);
			req.setAttribute("myinfo", "添加产品类型【" + protype.getName() + "】完成。");
			req.setAttribute("myurl", "ptInfo.action");
			return "addok";
		}
	
		return SUCCESS;
	}
	/*
	 * 产品类型修改
	 */
	public String ptModify(){
		HttpServletRequest req=this.getRequest();
		if(flag!=0){
			//上传类型图标
			String dbPath = "/appTypeIcon";
			String iconPath = "";
			if(iconFile != null && StringUtils.isNotEmpty(iconFileFileName)){
				iconPath = uploadImagePath + dbPath;
				long returnData = FileUploader.upload(iconFile, iconPath,
						iconFileFileName, true);
				if (returnData >= 0) {
					protype.setIconFileName(iconFileFileName);
					protype.setIconFilePath(dbPath + "/" + iconFileFileName);
				}
			}
			this.getBusinessSupportService().getSpService().modifyProductType(protype);
			req.setAttribute("myinfo", "修改产品类型【"+ protype.getName()+"】完成。");
			req.setAttribute("myurl", "ptInfo.action");
			return "modifyok";
		}
		protype =this.getBusinessSupportService().getSpService().getProductTypeById(new Integer(id).toString());
		return SUCCESS;
	}
	public File getIconFile() {
		return iconFile;
	}
	public void setIconFile(File iconFile) {
		this.iconFile = iconFile;
	}
	public String getIconFileFileName() {
		return iconFileFileName;
	}
	public void setIconFileFileName(String iconFileFileName) {
		this.iconFileFileName = iconFileFileName;
	}
	public static String getUploadImagePath() {
		return uploadImagePath;
	}
	public static void setUploadImagePath(String uploadImagePath) {
		SpAction.uploadImagePath = uploadImagePath;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	
	
}

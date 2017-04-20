package cn.eshore.mismp.spmanager.dao.impl;
/**
 * @author OYK
 */
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.eshore.mismp.util.Util;

import cn.eshore.mismp.spmanager.dao.rowmapper.*;
import cn.eshore.mismp.spmanager.dao.model.*;
import cn.eshore.mismp.dao.domain.ProductFeeVO;
import cn.eshore.mismp.dao.domain.ProductTypeVO;
import cn.eshore.mismp.dao.domain.rowmapper.ProductFeeRowMapper;
import cn.eshore.mismp.dao.domain.rowmapper.ProductTyperRowMapper;
import cn.eshore.mismp.dao.impl.BaseDAOJdbcTemplate;
import cn.eshore.mismp.spmanager.dao.SpDAO;
@SuppressWarnings("unchecked")
public class SpDAOImpl extends BaseDAOJdbcTemplate implements SpDAO {

	/*
	 * 省列表
	 */ 
	public List<Prov> provice() {
		String sql = "select id,name from T_MISMP_PROV_INFO order by idx";
		return this.query(sql, new ProvRowMapper());
	}
	/*
	 * 从省ID获得省名称
	 */
	public String getProvincebyid(String prov_id){
		String sql="select name from t_mismp_prov_info where id=?";
		return (String)this.queryForObject(sql,new Object[]{prov_id},java.lang.String.class);
	}
	/*
	 * SP列表
	 */
	
	public List<Sp> spList(String prov_id,String name,String description,int pageSize,int pageNum){
		String sql=" from T_MISMP_SP where 1=1 ";
		if (!description.equals("")){
		sql+=" and SP_DESC like '%"+description+"%'";
		}
		if (!prov_id.equals("all")&&!prov_id.equals("")){
			sql+=" and SP_LOCAL='"+prov_id+"'";
		}
		if (!name.equals("")){
			sql+=" and sp_name like'%"+name+"%'";
		}
		
		sql = sql + " order by SP_ID asc ";
		String countSql="select count(*) "+sql;
		sql="select *"+sql;
		return this.list(sql, countSql,null,new SpRowMapper(), pageSize, pageNum);
	}
	/*
	 * 添加SP
	 */
	public int addSp(Sp sp){
		String sql ="select seq_t_mismp_sp.nextval from dual";
		int id=this.queryForInt(sql);
		sp.setSp_id(id);
		sql="select max(substr(sp_code,instr(sp_code,'_')+1)+1) from t_mismp_sp  where sp_local=?";
		int code=0;
		try {
			code = this.queryForInt(sql, new Object[] { sp.getSp_local() });
		} catch (Exception e) {
		}
		String temp=String.valueOf(code);
		while(temp.length()!=10){
			temp="0"+temp;
		}
		String sp_code=sp.getSp_local().toUpperCase()+"_"+temp;
		sp.setSp_code(sp_code);
		
		if (sp.getSp_local().equals("cn")){
			sp.setSp_type(1);//全国对应SP类型为1
		}else{
			sp.setSp_type(2);//省级对应SP类型为2
		}
		 sql="insert into T_MISMP_SP  " +
		 		"(SP_ID,SP_CODE,SP_TYPE,SP_LOCAL,SP_NAME,SP_DESC,SP_ADDR,SP_PERSON,SP_PHONE,SP_CREATETIME)" +
		 		"values(?,?,?,?,?,?,?,?,?,SYSDATE)";
		return this.update(sql,new Object[]{
				sp.getSp_id(),
				sp.getSp_code(),
				sp.getSp_type(),
				sp.getSp_local(),
				sp.getSp_name(),
				sp.getSp_desc(),
				sp.getSp_addr(),
				sp.getSp_person(),
				sp.getSp_phone()
				});
	}
	/*
	 * 删除SP
	 */
	public int delSp(int sp_id){
		int temp=this.queryForInt("select count(*) from  t_mismp_product where sp_id=?",
				new Object[]{sp_id});
		if (temp>0){
			return -1;//不允许删除
		}
		String sql="delete from T_MISMP_SP where sp_id=?";
		return this.update(sql,new Object[]{sp_id});
	}
	/*
	 * 修改SP
	 */
	public int modifySp(Sp sp){
		String sql="update T_MISMP_SP set " +
				"sp_name=?,sp_desc=?,sp_addr=?,sp_person=?,sp_phone=? where sp_id=?";
		
		return this.update(sql,new Object[]{
				sp.getSp_name(),
				sp.getSp_desc(),
				sp.getSp_addr(),
				sp.getSp_person(),
				sp.getSp_phone(),
				sp.getSp_id()
				});
	}
	/*
	 * 以ID获得单个SP
	 */
	public Sp getSpById(int sp_id){
		String sql="select * from  T_MISMP_SP where sp_id=?";
		Sp sp = new Sp();
		List<Sp> ls=this.query(sql, new Object[]{sp_id},new SpRowMapper());
		if(ls != null && ls.size() > 0){
			sp = ls.get(0);
			sp.setSp_local_name(this.getProvincebyid(sp.getSp_local()));
		}
		else{
			sp.setSp_local_name("");
		}
		
		
		return sp;
	}
	
	/*
	 * 设备厂商列表
	 */
	public List<Factory> facList(String name,String description,int pageSize,int pageNum){
		String sql=" from T_MISMP_FACTORY where 1=1 ";
		if (!description.equals("")){
		sql+=" and FAC_DESC like '%"+description+"%'";
		}

		if (!name.equals("")){
			sql+=" and fac_name like'%"+name+"%'";
		}
		String countSql="select count(*) "+sql;
		sql="select *"+sql;
		return this.list(sql, countSql,null,new FactoryRowMapper(), pageSize, pageNum);
	}
	
	/*
	 * 删除厂商
	 */
	public int delFac(int fac_id){
		int temp=this.queryForInt("select count(*) from t_mismp_terminal where fac_id=?",
				new Object[]{fac_id});
		if (temp>0){
			return -1;
		}
		String sql="delete from T_MISMP_FACTORY where fac_id=?";
		return this.update(sql,new Object[]{fac_id});
	}
	
	/*
	 * 添加厂商
	 */
	public int addFac(Factory fac){
		String sql="insert into T_MISMP_FACTORY " +
		 		"(FAC_ID,FAC_NAME,FAC_ADDR,FAC_PHONE,FAC_DESC)" +
		 		"values(SEQ_T_MISMP_FACTORY.NEXTVAL,?,?,?,?)";
		return this.update(sql,new Object[]{
				fac.getFac_name(),
				fac.getFac_addr(),
				fac.getFac_phone(),
				fac.getFac_desc()
				});
	}
	/*
	 * 修改厂商
	 */
	public int modifyFac(Factory fac){
		String sql="update T_MISMP_FACTORY set " +
				"fac_name=?,fac_desc=?,fac_addr=?,fac_phone=? where fac_id=?";
		
		return this.update(sql,new Object[]{
				fac.getFac_name(),
				fac.getFac_desc(),
				fac.getFac_addr(),
				fac.getFac_phone(),
				fac.getFac_id()
				});
	}
	/*
	 * 以ID获得单个厂商
	 */
	public Factory getFacById(int fac_id){
		String sql="select * from  T_MISMP_FACTORY where fac_id=?";
		List<Factory> ls=this.query(sql, new Object[]{fac_id},new FactoryRowMapper());
		Factory fac=ls.get(0);
		return fac;
	}
	/*
	 * 添加虚拟类型
	 */
	public int addVir(Virtual vir) {
		String sql="insert into T_MISMP_VIRTUAL " +
 		"(VIR_ID,VIR_NAME,VIR_DESC)"
				+ "values(SEQ_T_MISMP_VIRTUAL.NEXTVAL,?,?)";
		return this.update(sql, new Object[] { vir.getVir_name(),vir.getVir_desc() });
	}
	/*
	 * 删除虚拟类型
	 */
	public int delVir(int vir_id) {
		String sql="select count(*) from t_mismp_terminal where vir_id=?";
		int temp=this.queryForInt(sql,new Object[]{vir_id});
		if (temp>1){//如果是被其他产品作为父产品，则至少为2
			return -1;
		}
		sql="delete from T_MISMP_VIRTUAL where vir_id=?";
		return this.update(sql,new Object[]{vir_id});
	}
	/*
	 * 以ID获得单个虚拟类型
	 */
	public Virtual getVirById(int vir_id) {
		String sql="select * from  T_MISMP_VIRTUAL where vir_id=?";
		List<Virtual> ls=this.query(sql, new Object[]{vir_id},new VirtualRowMapper());
		Virtual vir=ls.get(0);
		return vir;
	}
	/*
	 * 修改虚拟类型
	 */
	public int modifyVir(Virtual vir) {
		String sql = "update T_MISMP_VIRTUAL set "
				+ "vir_name=?,vir_desc=? where vir_id=?";

		return this.update(sql, new Object[] {vir.getVir_name(),vir.getVir_desc(),vir.getVir_id()});
	}
	/*
	 * 虚拟类型列表
	 */
	public List<Virtual> virList(String name, String description, int pageSize,
			int pageNum) {
		String sql=" from T_MISMP_VIRTUAL where 1=1 ";
		if (!description.equals("")){
		sql+=" and VIR_DESC like '%"+description+"%'";
		}

		if (!name.equals("")){
			sql+=" and VIR_NAME like'%"+name+"%'";
		}
		String countSql="select count(*) "+sql;
		sql="select *"+sql;
		return this.list(sql, countSql,null,new VirtualRowMapper(), pageSize, pageNum);
	}
	
	/*
	 * 机型设备列表
	 */
	public List<Terminal> terList(String name,String description,int fac_id,int pageSize,int pageNum){
		String sql=" from T_MISMP_TERMINAL where 1=1 ";
		if (!description.equals("")){
		sql+=" and TER_DES like '%"+description+"%'";
		}

		if (!name.equals("")){
			sql+=" and ter_name like'%"+name+"%'";
		}
		
		if (fac_id>0){
			sql+=" and FAC_ID ="+String.valueOf(fac_id);
		}
		String countSql="select count(*) "+sql;
		sql="select *"+sql;
		return this.list(sql, countSql,null,new TerminalRowMapper(), pageSize, pageNum);
	}
	/*
	 * 删除机型设备
	 */
	public String delTer(int ter_id){
		String picurl=this.getTerById(ter_id).getTer_picpath();
		String sql="delete from T_MISMP_TERMINAL where ter_id=?";
		this.update(sql,new Object[]{ter_id});
		return picurl;
	}
	/*
	 * 添加机型设备
	 */
	public int addTer(Terminal ter){
		String sql="insert into t_mismp_terminal " +
 		"(TER_ID,TER_CODE,FAC_ID,VIR_ID,TER_JAVA,TER_MMS,TER_WAP,TER_EVDO,TER_PLAT,TER_PICPATH,TER_DES,TER_NAME,TER_USERAGENT)"
				+ "values(SEQ_T_MISMP_TERMINAL.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
		return this.update(sql, new Object[] {
				ter.getTer_code(),
				ter.getFac_id(),
				ter.getVir_id(),
				ter.getTer_java(),
				ter.getTer_mms(),
				ter.getTer_wap(),
				ter.getTer_evdo(),
				ter.getTer_plat(),
				ter.getTer_picpath(),
				ter.getTer_des(),
				ter.getTer_name(),
				ter.getUserAgent()
		});
	}
	/*
	 * 修改机型设备
	 */
	public int modifyTer(Terminal ter){
		String sql = "update T_MISMP_TERMINAL set "+
			"TER_CODE=?,FAC_ID=?,VIR_ID=?,TER_JAVA=?,TER_MMS=?,TER_WAP=?,TER_EVDO=?," +
			"TER_PLAT=?,TER_DES=?,TER_NAME=?,TER_USERAGENT = ? where ter_id=?";
	return this.update(sql, new Object[] {
			ter.getTer_code(),
			ter.getFac_id(),
			ter.getVir_id(),
			ter.getTer_java(),
			ter.getTer_mms(),
			ter.getTer_wap(),
			ter.getTer_evdo(),
			ter.getTer_plat(),
			ter.getTer_des(),
			ter.getTer_name(),
			ter.getUserAgent(),
			ter.getTer_id()
	});
	}
	/*
	 * 以ID获得单个机型设备
	 */
	public Terminal  getTerById(int ter_id){
		String sql="select * from  T_MISMP_TERMINAL where ter_id=?";
		List<Terminal> ls=this.query(sql, new Object[]{ter_id},new TerminalRowMapper());
		Terminal ter=ls.get(0);
		return ter;
	}
	/*
	 * 厂商列表（供选）
	 */
	public List<Factory> facDropList(){
		String sql="select fac_id,fac_name from t_mismp_factory";
		List list=this.queryForList(sql);
		List<Factory> faclist=new ArrayList<Factory>();
		Iterator it = list.iterator();
		while(it.hasNext()){
			Map map=(Map)it.next();
			Factory fac=new Factory();
			fac.setFac_id(Util.String2Int(Util.object2Str(map.get("fac_id"))));
			fac.setFac_name(Util.object2Str(map.get("fac_name")));
			faclist.add(fac);
		}
		return faclist;
	}
	
	
	/*
	 * 分辨率 （虚拟类型）（供选）
	 */
	public List<Virtual> virDropList(){
		String sql="select vir_id,vir_name from T_MISMP_VIRTUAL";
		List list=this.queryForList(sql);
		List<Virtual> virlist=new ArrayList<Virtual>();
		Iterator it = list.iterator();
		while(it.hasNext()){
			Map map=(Map)it.next();
			Virtual vir=new Virtual();
			vir.setVir_id(Util.String2Int(Util.object2Str(map.get("vir_id"))));
			vir.setVir_name(Util.object2Str(map.get("vir_name")));
			virlist.add(vir);
		}
		return virlist;
	}
	
	/*
	 * SP列表（供选）
	 */
	public List<Sp> spDropList(){
		String sql="select sp_id,sp_code,sp_name from T_MISMP_SP";
		List list=this.queryForList(sql);
		List<Sp> splist=new ArrayList<Sp>();
		Iterator it = list.iterator();
		while(it.hasNext()){
			Map map=(Map)it.next();
			Sp sp=new Sp();
			sp.setSp_id(Util.String2Int(Util.object2Str(map.get("sp_id"))));
			sp.setSp_name(Util.object2Str(map.get("sp_name")));
			sp.setSp_code(Util.object2Str(map.get("sp_code")));
			splist.add(sp);
		}
		return splist;
	}
	/*
	 * 产品列表（供选）
	 */
	public List<Product> proDropList(){
		String sql="select PRO_ID,PRO_NAME from T_MISMP_PRODUCT";
		List list=this.queryForList(sql);
		List<Product> prolist=new ArrayList<Product>();
		Iterator it = list.iterator();
		while(it.hasNext()){
			Map map=(Map)it.next();
			Product pro=new Product();
			pro.setPro_id(Util.String2Int(Util.object2Str(map.get("pro_id"))));
			pro.setPro_name(Util.object2Str(map.get("pro_name")));
			prolist.add(pro);
		}
		return prolist;
	}
	
	/*
	 * SP产品列表
	 */
	public List<Product> proList(String spName, String name,String pro_code,int pro_state,int pageSize,int pageNum){
		String sql=" from T_MISMP_PRODUCT pro inner join T_MISMP_SP sp on pro.SP_ID = sp.SP_ID where 1=1 ";
		if (!name.equals("")){
			sql+=" and pro.PRO_NAME like '%"+name+"%'";
		}
		if (!pro_code.equals("")){
			sql+=" and pro.ISMP_ID like '%"+pro_code+"%'";
		}

		if (pro_state>=0){
			sql+=" and pro.PRO_STATUS ="+String.valueOf(pro_state);
		}
		
		if(!spName.equals("")){
			sql+=" and sp.SP_NAME like '%"+spName+"%'";
		}
		
		sql = sql + "order by pro.SP_ID asc, pro.PRO_ID asc ";
		
		String countSql="select count(*) "+sql;
		sql="select pro.* "+sql;
		return this.list(sql, countSql,null,new ProductRowMapper(), pageSize, pageNum);
	}
	
	/*
	 * 添加SP产品
	 */
	public int addPro(Product pro){	
		String sql="select SEQ_T_MISMP_PRODUCT.NEXTVAL from dual";
		int temp=this.queryForInt(sql);
		sql="insert into T_MISMP_PRODUCT " +
			"(PRO_ID,ISMP_ID,PRO_NAME,PRO_DESC,	PRO_TYPE,PRO_FEE,PRO_STATUS,SP_ID,PRO_CREATETIME,PRO_PARENT_ID,PRO_REMARK,PRO_REGISTER_ID,PT_ID,PRO_DEVELOPER,PRO_SUPPLIER,PRO_IS_VOUCH,PRO_HW_ID)"+ 
			 "values(?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?)";
		 this.update(sql, new Object[] {
				temp,
				pro.getIsmp_id(),
				pro.getPro_name(),
				pro.getPro_desc(),
				pro.getPro_type(),
				pro.getPro_fee(),
				1,//默认为“审核”状态
				pro.getSp_id(),
				pro.getPro_parent_id()==0?temp:pro.getPro_parent_id(),
				pro.getPro_remark(),
				pro.getRegisterId(),
				pro.getPtId(),
				pro.getDeveloper(),
				pro.getSupplier(),
				pro.getIsVouch(),
				pro.getProHwId()
		});
		
		 return temp;
	}
	
	/*
	 * 添加SP产品费率
	 */
	public int addProFee(ProductFeeVO vo){	
		String sql="select SEQ_T_MISMP_PRODUCT_FEE.NEXTVAL from dual";
		int temp=this.queryForInt(sql);
		sql="insert into T_MISMP_PRODUCT_FEE " +
			"(PF_ID,PF_PRO_TYPE,PF_PRO_VALID,PF_PRO_DAYS,PF_PRO_TIMES,PF_FOR_TYPE,PF_FOR_VALID,PF_FOR_ORDER,PF_FOR_FEE,PRO_ID)"+ 
			 "values(?,?,?,?,?,?,?,?,?,?)";
		 this.update(sql, new Object[] {
				temp,
				vo.getProType(),
				vo.getProValid(),
				vo.getDays(),
				vo.getTimes(),
				vo.getForType(),
				vo.getForValid(),
				vo.getOrder(),
				vo.getForFee(),
				vo.getProId()
		});
		
		 return temp;
	}
	/*
	 * 修改SP产品
	 */
	public int modifyPro(Product pro){
		String sql="update T_MISMP_PRODUCT set " +
		"ISMP_ID=?,PRO_NAME=?,PRO_DESC=?,PRO_TYPE=?,PRO_FEE=?,PRO_STATUS=?,SP_ID=?," +
		"PRO_PARENT_ID=?,PRO_REMARK=?,PT_ID = ?,PRO_DEVELOPER = ?, PRO_SUPPLIER = ?,PRO_IS_VOUCH = ?, PRO_REGISTER_ID = ? ,PRO_HW_ID = ? where pro_id=?";
		return this.update(sql,new Object[]{
				pro.getIsmp_id(),
				pro.getPro_name(),
				pro.getPro_desc(),
				pro.getPro_type(),
				pro.getPro_fee(),
				pro.getPro_status(),
				pro.getSp_id(),
				(pro.getPro_parent_id()==0?pro.getPro_id():pro.getPro_parent_id()),
				pro.getPro_remark(),
			    pro.getPtId(),
			    pro.getDeveloper(),
			    pro.getSupplier(),
			    pro.getIsVouch(),
			    pro.getRegisterId(),
			    pro.getProHwId(),
			    pro.getPro_id()
		});
	}
	
	/*
	 * 修改SP产品费率
	 */
	public int modifyProFee(ProductFeeVO vo){
		String sql="update T_MISMP_PRODUCT_FEE set PF_PRO_TYPE =? ,PF_PRO_VALID = ? ,PF_PRO_DAYS = ? ,PF_PRO_TIMES = ? ,PF_FOR_TYPE = ? ,PF_FOR_VALID = ?,PF_FOR_ORDER = ?,PF_FOR_FEE = ? where PRO_ID = ? ";
		return this.update(sql,new Object[]{
				vo.getProType(),
				vo.getProValid(),
				vo.getDays(),
				vo.getTimes(),
				vo.getForType(),
				vo.getForValid(),
				vo.getOrder(),
				vo.getForFee(),
			    vo.getProId()
		});
	}
	
	/*
	 * 删除SP产品
	 */
	public int delPro(int pro_id){
		String sql="select count(*) from t_mismp_product where pro_parent_id=?";
		int temp=this.queryForInt(sql,new Object[]{pro_id});
		if (temp>1){//如果是被其他产品作为父产品，则至少为2
			return -1;
		}
		sql="select count(*) from t_mismp_softtype where PRO_ID=?";
		temp=this.queryForInt(sql,new Object[]{pro_id});
		if (temp>0){
			return -2;//如果已经有软件引用此产品，则返回-2
		}
		 sql="delete from  t_mismp_product where pro_id=?";
		 this.update(sql,new Object[]{pro_id});
		 
		 sql="delete from  T_MISMP_PRODUCT_FEE where pro_id=?";
		 return this.update(sql,new Object[]{pro_id});
		 
		 
	}
	/*
	 * 以ID获得单个产品
	 */
	public Product getProById(int pro_id){
		String sql="select * from  T_MISMP_PRODUCT where pro_id=?";
		List<Product> ls=this.query(sql, new Object[]{pro_id},new ProductRowMapper());
		Product pro= new Product();
		if(ls.size() > 0){
			pro = ls.get(0);
		}
		return pro;
	}
	
	/*
	 * 以ID获得单个计费策略
	 */
	public ProductFeeVO getProFeeByProId(int pro_id){
		ProductFeeVO vo = new ProductFeeVO();
		String sql="select * from  T_MISMP_PRODUCT_FEE where pro_id=?";
		List<ProductFeeVO> ls=this.query(sql, new Object[]{pro_id},new ProductFeeRowMapper());
		if(ls.size() > 0){
			vo = ls.get(0);
		}
		return vo;
	}
	
	/*
	 * 以ID获得单个软件类型
	 */
	public SoftType getSoftTypeById(int st_id){
		String sql="select * from T_MISMP_SOFTTYPE where ST_ID=?";
		List<SoftType> ls=this.query(sql, new Object[]{st_id},new SoftTypeRowMapper());
		SoftType st = new SoftType();
		if(ls != null && ls.size() > 0){
			st =ls.get(0);
		}
		
		return st;
	}
	
	/*
	 * 以ID获得单个软件版本
	 */
	public SoftVersion getSoftVersionById(int sv_id){
		SoftVersion sv = null;
		String sql="select sv_id,st_id from T_MISMP_SOFTVERSION where SV_ID=?";
		List<SoftVersion> ls=this.query(sql, new Object[]{sv_id},new SoftVersionRowMapper());
		if(ls != null && ls.size() > 0)
		 sv=ls.get(0);
		return sv;
	}
	/*
	 * 以ID获得单个软件审核记录
	 */
	public SoftAudit getSoftAuditById(int sv_id){
		String sql="select * from t_mismp_soft_audit where AUDIT_ID=?";
		List<SoftAudit> ls=this.query(sql, new Object[]{sv_id},new SoftAuditRowMapper());
		SoftAudit sa=ls.get(0);
		return sa;
	}
	/*
	 * 软件版本审核列表
	 * 参数：ver 0表示未审核的 1表示审核过的
	 */
	public List<SoftAudit> svList(String name,String description,String starttime,int ver,String endtime,int pageSize,int pageNum){
		String sql=" from T_MISMP_SOFT_AUDIT sa left join T_MISMP_SOFTVERSION sv on sa.SV_ID = sv.SV_ID left join T_MISMP_SOFTTYPE st on st.ST_ID = sv.ST_ID where 1=1 ";
		if (!description.equals("")){
		sql+=" and sa.SOFT_DESC like '%"+description+"%'";
		}
		if (!name.equals("")){
			sql+=" and st.ST_NAME like'%"+name+"%'";
		}
		if (!starttime.equals("")){
			
			sql+=" and sa.create_time>=to_date('"+starttime+"','YYYY-MM-DD')";
		}
		if (!endtime.equals("")){
			
			sql+=" and sa.create_time<=to_date('"+endtime+" 23:59:59','YYYY-MM-DD HH24:MI:SS')";
		}
		sql+=" and sa.AUDIT_STATUS"+ (ver==0?" =1":" !=1");
		String countSql="select count(*) "+sql;
		sql="select sa.*"+sql;
		return this.list(sql, countSql,null,new SoftAuditRowMapper(), pageSize, pageNum);
	}
	
	/*
	 * 软件版本审核
	 * 参数state 2 审核通过 3审核未通过
	 * 参数audit_by 审核人
	 */
	public int svAudit(int sv_id,int state,String remark,String audit_by){
		String sql="update t_mismp_soft_audit set AUDIT_STATUS=? ,AUDIT_DESC=?,audit_by=? where AUDIT_ID=?";
		this.update(sql,new Object[]{state,remark,audit_by,sv_id});
		int sv_id_forupdate=this.getSoftAuditById(sv_id).getSv_id();
		sql="update t_mismp_softversion set SV_STATUS=? where sv_id=? ";
		return this.update(sql,new Object[]{state,sv_id_forupdate});
	}
	
	/*
	 * 产品类型列表获取
	 */
	public List<ProductTypeVO> getProductTypeList(ProductTypeVO vo, int pageSize,
			int pageNum) {
		String sql=" from T_MISMP_PRODUCT_TYPE where 1=1 ";
		if (!vo.getDesc().equals("")){
		sql+=" and PT_NAME like '%"+vo.getDesc()+"%'";
		}

		if (!vo.getName().equals("")){
			sql+=" and PT_DESC like'%"+vo.getName()+"%'";
		}
		
		//不带翻页
		if(pageSize == -1 && pageNum == -1){
			sql="select *"+sql;
			return this.query(sql,new ProductTyperRowMapper());
		}
		//翻页
		else
		{
			String countSql="select count(*) "+sql;
			sql="select *"+sql;
			return this.list(sql, countSql,null,new ProductTyperRowMapper(), pageSize, pageNum);
		}
		
	}
	
	/*
	 * 根据ID获取产品类型
	 */
	public ProductTypeVO getProductTypeById(String id) {

		ProductTypeVO vo = new ProductTypeVO();
		String sql=" select * from  T_MISMP_PRODUCT_TYPE where PT_ID =?";
		List<ProductTypeVO> ls=this.query(sql, new Object[]{id},new ProductTyperRowMapper());
		if(ls.size() > 0){
			vo = ls.get(0);
		}
		return vo;
	
	}
	
	/*
	 * 根据ID获取父产品类型
	 */
	public List<ProductTypeVO> getProductTypeListByParentId(long parentId) {
		String sql=" select * from T_MISMP_PRODUCT_TYPE where PT_PARENT_ID = ?  ";
		//sql="select *"+sql;
		return this.query(sql,new Object[]{parentId}, new ProductTyperRowMapper());
	}
	
	
	/*
	 * 查询产品类型ID对应产品
	 */
	public Product getProByPTId(String ptId){
		Product pro = new Product();
		String sql="select * from  T_MISMP_PRODUCT where pt_id= ?";
		List<Product> ls=this.query(sql, new Object[]{ptId},new ProductRowMapper());
		if(ls.size() > 0)
		{
			pro=ls.get(0);
		}
		return pro;
	}
	
	/*
	 * 删除类型id对应产品类型
	 */
	public int delProType(String ptId){
		 String sql="delete from  T_MISMP_PRODUCT_TYPE where PT_ID=?";
		 return this.update(sql,new Object[]{ptId});
	}
	
	/*
	 * 添加产品类型
	 */
	public int addProductType(ProductTypeVO vo) {
		String sql="insert into T_MISMP_PRODUCT_TYPE " +
 		"(PT_ID,PT_NAME,PT_DESC,PT_ICON_NAME,PT_ICON_PATH)"
				+ "values(SEQ_T_MISMP_PRODUCT_TYPE.NEXTVAL,?,?,?,?)";
		return this.update(sql, new Object[] { vo.getName(), vo.getDesc(),vo.getIconFileName(),vo.getIconFilePath() });
	}
	
	/*
	 * 修改产品类型
	 */
	public int modifyProductType(ProductTypeVO vo){
		String frontSQL="update T_MISMP_PRODUCT_TYPE set PT_NAME=?,PT_DESC=? ";
		String backSQL = " where PT_ID=? ";
		String iconFileName = vo.getIconFileName();
		String iconFilePath = vo.getIconFilePath();
		List<String> paramList = new ArrayList<String>();
		paramList.add(vo.getName());
		paramList.add(vo.getDesc());
		int m = 0;
		if (StringUtils.isNotEmpty(iconFileName) && StringUtils.isNotEmpty(iconFilePath)) {
			frontSQL = frontSQL + " ,PT_ICON_NAME = ? , PT_ICON_PATH = ?";
			paramList.add(iconFileName);
			paramList.add(iconFilePath);
			paramList.add(String.valueOf(vo.getId()));
		}else{
			paramList.add(String.valueOf(vo.getId()));
		}
		final Object[] params = paramList.toArray();
		m = this.update(frontSQL + backSQL, params);
		return m;
	}
}

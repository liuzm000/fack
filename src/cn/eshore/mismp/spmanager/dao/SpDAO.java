package cn.eshore.mismp.spmanager.dao;

import java.util.List;

import cn.eshore.mismp.dao.BaseDAO;
import cn.eshore.mismp.dao.domain.ProductFeeVO;
import cn.eshore.mismp.dao.domain.ProductTypeVO;

import cn.eshore.mismp.spmanager.dao.model.*;


public interface SpDAO extends BaseDAO {

	/*
	 * 省列表
	 */ 
	public List<Prov> provice();
	/*
	 * SP列表
	 */
	public List<Sp> spList(String prov_id,String name,String description,int pageSize,int pageNum);
	/*
	 * 从省ID获得省名称
	 */
	public String getProvincebyid(String prov_id);
	/*
	 * 添加SP
	 */
	public int addSp(Sp sp);
	/*
	 * 删除SP
	 */
	public int delSp(int sp_id);
	/*
	 * 修改SP
	 */
	public int modifySp(Sp sp);
	/*
	 * 以ID获得单个SP
	 */
	public Sp getSpById(int sp_id);
	/*
	 * 设备厂商列表
	 */
	public List<Factory> facList(String name,String description,int pageSize,int pageNum);
	/*
	 * 删除厂商
	 */
	public int delFac(int fac_id);
	/*
	 * 添加厂商
	 */
	public int addFac(Factory fac);
	/*
	 * 修改厂商
	 */
	public int modifyFac(Factory fac);
	/*
	 * 以ID获得单个厂商
	 */
	public Factory getFacById(int fac_id);
	
	/*
	 * 虚拟类型列表
	 */
	public List<Virtual> virList(String name,String description,int pageSize,int pageNum);
	/*
	 * 删除虚拟类型
	 */
	public int delVir(int vir_id);
	/*
	 * 添加虚拟类型
	 */
	public int addVir(Virtual vir);
	/*
	 * 修改虚拟类型
	 */
	public int modifyVir(Virtual vir);
	/*
	 * 以ID获得单个虚拟类型
	 */
	public Virtual  getVirById(int vir_id);
	
	/*
	 * 机型设备列表
	 */
	public List<Terminal> terList(String name,String description,int fac_id,int pageSize,int pageNum);
	/*
	 * 删除机型设备
	 */
	public String delTer(int ter_id);
	/*
	 * 添加机型设备
	 */
	public int addTer(Terminal ter);
	/*
	 * 修改机型设备
	 */
	public int modifyTer(Terminal ter);
	/*
	 * 以ID获得单个机型设备
	 */
	public Terminal  getTerById(int ter_id);
	/*
	 * 厂商列表（供选）
	 */
	public List<Factory> facDropList();
	/*
	 * 分辨率 （虚拟类型）（供选）
	 */
	public List<Virtual> virDropList();
	
	/*
	 * SP列表（供选）
	 */
	public List<Sp> spDropList();
	/*
	 * SP产品列表
	 */
	public List<Product> proList(String spName, String name,String pro_code,int pro_state,int pageSize,int pageNum);
	/*
	 * 以ID获得单个产品
	 */
	public Product getProById(int pro_id);
	/*
	 * 产品列表（供选）
	 */
	public List<Product> proDropList();
	/*
	 * 添加SP产品
	 */
	public int addPro(Product pro);
	/*
	 * 修改SP产品
	 */
	public int modifyPro(Product pro);
	/*
	 * 删除SP产品
	 */
	public int delPro(int pro_id);
	/*
	 * 以ID获得单个软件类型
	 */
	public SoftType getSoftTypeById(int st_id);
	/*
	 * 以ID获得单个软件版本
	 */
	public SoftVersion getSoftVersionById(int sv_id);
	/*
	 * 以ID获得单个软件审核记录
	 */
	public SoftAudit getSoftAuditById(int sv_id);
	/*
	 * 软件版本审核列表
	 * 参数：ver 0表示未审核的 1表示审核过的
	 */
	public List<SoftAudit> svList(String name,String description,String starttime,
			int ver,String endtime,int pageSize,int pageNum);
	/*
	 * 软件版本审核
	 * 参数state 2 审核通过 3审核未通过
	 */
	public int svAudit(int sv_id,int state,String remark,String audit_by);
	/*
	 * 添加SP产品费率
	 */
	public int addProFee(ProductFeeVO vo);
	
	/*
	 * 以ID获得单个计费策略
	 */
	public ProductFeeVO getProFeeByProId(int pro_id);
	
	/*
	 * 修改SP产品费率
	 */
	public int modifyProFee(ProductFeeVO vo);
	/*
	 * 产品类型列表
	 */
	public List<ProductTypeVO> getProductTypeList(ProductTypeVO vo, int pageSize,
			int pageNum);
	
	/*
	 * 根据ID获取产品类型
	 */
	public ProductTypeVO getProductTypeById(String id);
	
	/*
	 * 根据ID获取父产品类型
	 */
	public List<ProductTypeVO> getProductTypeListByParentId(long parentId);
	
	/*
	 * 查询产品类型ID对应产品
	 */
	public Product getProByPTId(String ptId);
	
	/*
	 * 删除类型id对应产品类型
	 */
	public int delProType(String ptId);
	
	/*
	 * 添加产品类型
	 */
	public int addProductType(ProductTypeVO vo);
	
	/*
	 * 修改产品类型
	 */
	public int modifyProductType(ProductTypeVO vo);
}

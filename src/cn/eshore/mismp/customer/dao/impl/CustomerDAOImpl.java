package cn.eshore.mismp.customer.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.eshore.mismp.customer.dao.CustomerDAO;
import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.ProductVO;
import cn.eshore.mismp.dao.domain.TerUserDetailVO;
import cn.eshore.mismp.dao.domain.TerUserGroupTreeVO;
import cn.eshore.mismp.dao.domain.TerUserGroupVO;
import cn.eshore.mismp.dao.domain.TerUserOrderVO;
import cn.eshore.mismp.dao.domain.TerUserVO;
import cn.eshore.mismp.dao.domain.rowmapper.ModuleRowMapper;
import cn.eshore.mismp.dao.domain.rowmapper.ProductRowMapper;
import cn.eshore.mismp.dao.domain.rowmapper.TerUserGroupTreeRowMapper;
import cn.eshore.mismp.dao.domain.rowmapper.TerUserOrderRowMapper;
import cn.eshore.mismp.dao.domain.rowmapper.TerUserRowMapper;
import cn.eshore.mismp.dao.impl.BaseDAOJdbcTemplate;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.StringUtil;
import cn.eshore.mismp.util.Util;
import cn.eshore.mismp.version.domain.vo.FactoryVO;
import cn.eshore.mismp.version.domain.vo.SoftVersionVO;

public class CustomerDAOImpl extends BaseDAOJdbcTemplate implements CustomerDAO {

	protected static final Logger log = Logger.getLogger(CustomerDAOImpl.class);

	private static final String QUERY_TERSER_SQL = "select * from T_MISMP_TERUSER where 1 = 1 ";

	private static final String COUNT_TERSER_SQL = "select count(*) from T_MISMP_TERUSER where 1 = 1";

	private static final String QUERY_TERUSER_BY_ID = " select tu.TU_ID,tu.TU_PHONENUM,tu.TU_IMSI,tu.TU_ESN, tu.TER_ID,tu.TU_CREATETIME, tm.TER_CODE, tm.FAC_ID ,tm.VIR_ID, tm.TER_JAVA,tm.TER_MMS, tm.TER_WAP, tm.TER_EVDO, tm.TER_PLAT,tm.TER_DES, tm.TER_NAME , vt.VIR_NAME, tf.FAC_NAME from T_MISMP_TERUSER tu left join T_MISMP_TERMINAL tm on tu.TER_ID = tm.TER_ID left join T_MISMP_VIRTUAL vt on tm.VIR_ID = vt.VIR_ID left join T_MISMP_FACTORY tf on tm.FAC_ID = tf.FAC_ID where tu.TU_ID = ? ";

	private static final String QUERY_PRODUCT_SQL = " select PRO_ID, ISMP_ID, PRO_NAME, PRO_DESC, PRO_TYPE, PRO_FEE, PRO_STATUS, SP_ID, PRO_CREATETIME from T_MISMP_PRODUCT ";

	private static final String QUERY_TERSER_ORDER_SQL = "select tr.TO_ID, tr.PRO_ID, tr.TU_ID, tr.TO_STATUS, tr.TO_CREATETIME, tu.TU_PHONENUM, tu.TER_ID, pt.PRO_NAME from T_MISMP_TERUSER_ORDER tr inner join T_MISMP_TERUSER tu on tr.TU_ID = tu.TU_ID inner join T_MISMP_PRODUCT pt on tr.PRO_ID = pt.PRO_ID where 1 = 1 ";

	private static final String COUNT_TERSER_ORDER_SQL = "select count(*) from T_MISMP_TERUSER_ORDER tr inner join T_MISMP_TERUSER tu on tr.TU_ID = tu.TU_ID inner join T_MISMP_PRODUCT pt on tr.PRO_ID = pt.PRO_ID where 1 = 1 ";

	private static final String QUERY_TERSER_ORDER_BY_ID = "select tr.TO_ID, tr.PRO_ID, tr.TU_ID, tr.TO_STATUS, tr.TO_CREATETIME, tu.TU_PHONENUM, tu.TER_ID, pt.PRO_NAME from T_MISMP_TERUSER_ORDER tr inner join T_MISMP_TERUSER tu on tr.TU_ID = tu.TU_ID inner join T_MISMP_PRODUCT pt on tr.PRO_ID = pt.PRO_ID where tr.TO_ID = ? ";

	// 客户信息列表查询
	public Pagination listCustomerInfo(TerUserVO vo, int pageSize, int pageNum) {

		ArrayList paramArrayList = null;
		Object params[] = null;
		StringBuffer sqlBuffer = new StringBuffer(QUERY_TERSER_SQL);
		StringBuffer sqlCountBuffer = new StringBuffer(COUNT_TERSER_SQL);

		// 条件查询
		if (vo != null) {
			paramArrayList = new ArrayList();
			// 手机号码
			if (!StringUtil.isEmpty(vo.getPhoneNum())) {
				sqlBuffer = sqlBuffer.append(" and TU_PHONENUM like ? ");
				sqlCountBuffer = sqlCountBuffer
						.append(" and TU_PHONENUM like ? ");
				paramArrayList.add("%" + vo.getPhoneNum() + "%");
			}

			// TU_EMSI
			if (!StringUtil.isEmpty(vo.getImsi())) {
				sqlBuffer = sqlBuffer.append(" and TU_IMSI like ? ");
				sqlCountBuffer = sqlCountBuffer.append(" and TU_IMSI like ? ");
				paramArrayList.add("%" + vo.getImsi() + "%");
			}

			// TU_ESM
			if (!StringUtil.isEmpty(vo.getEsn())) {
				sqlBuffer = sqlBuffer.append(" and TU_ESN like ? ");
				sqlCountBuffer = sqlCountBuffer.append(" and TU_ESN like ? ");
				paramArrayList.add("%" + vo.getEsn() + "%");
			}

			if (paramArrayList.size() > 0) {
				params = paramArrayList.toArray();
			}

		}

		sqlBuffer = sqlBuffer.append(" order by TU_ID ");

		Pagination pageList = (Pagination) list(sqlBuffer.toString(),
				sqlCountBuffer.toString(), params, new TerUserRowMapper(),
				pageSize, pageNum);
		return pageList;
	}

	// 客户详细信息查询
	public TerUserDetailVO getCustomerInfo(long id) {

		TerUserDetailVO vo = new TerUserDetailVO();
		final Object[] params = new Object[] { id };
		List<?> rows = this.queryForList(QUERY_TERUSER_BY_ID, params);
		Iterator<?> it = rows.iterator();
		if (it.hasNext()) {
			Map<?, ?> map = (Map<?, ?>) it.next();
			vo.setId(Long.parseLong(Util.objectToStr(map.get("TU_ID"))));
			vo.setPhoneNum(Util.object2Str(map.get("TU_PHONENUM")));
			vo.setImsi(Util.object2Str(map.get("TU_IMSI")));
			vo.setEsn(Util.object2Str(map.get("TU_ESN")));
			vo.setTerId(Util.objectToStr(map.get("TER_ID")));
			vo.setCreateTime(Util.objectToStr(map.get("TU_CREATETIME")));
			vo.setTerCode(Util.objectToStr(map.get("TER_CODE")));
			vo.setVirId(Util.objectToStr(map.get("VIR_ID")));
			vo.setFacId(Util.objectToStr(map.get("FAC_ID")));
			vo.setJavaSupport(Util.objectToStr(map.get("TER_JAVA")));
			vo.setMmsSupport(Util.objectToStr(map.get("TER_MMS")));
			vo.setWapSupport(Util.objectToStr(map.get("TER_WAP")));
			vo.setEvdoSupport(Util.objectToStr(map.get("TER_EVDO")));
			vo.setTerplat(Util.objectToStr(map.get("TER_PLAT")));
			vo.setTerdesc(Util.objectToStr(map.get("TER_DES")));
			vo.setTerName(Util.objectToStr(map.get("TER_NAME")));
			vo.setVirName(Util.objectToStr(map.get("VIR_NAME")));
			vo.setFacName(Util.objectToStr(map.get("FAC_NAME")));
		}
		return vo;

	}

	// 获取产品列表
	public List<ProductVO> getAllProducts() {
		final Object[] params = new Object[] {};
		return this.query(QUERY_PRODUCT_SQL, params, new ProductRowMapper());
	}

	// 获取用户订购列表
	public Pagination listCustomerOrder(TerUserOrderVO vo, int pageSize,
			int pageNum) {

		ArrayList paramArrayList = null;
		Object params[] = null;
		StringBuffer sqlBuffer = new StringBuffer(QUERY_TERSER_ORDER_SQL);
		StringBuffer sqlCountBuffer = new StringBuffer(COUNT_TERSER_ORDER_SQL);

		// 条件查询
		if (vo != null) {
			paramArrayList = new ArrayList();
			// 手机号码
			if (!StringUtil.isEmpty(vo.getPhoneNum())) {
				sqlBuffer = sqlBuffer.append(" and tu.TU_PHONENUM like ? ");
				sqlCountBuffer = sqlCountBuffer
						.append(" and tu.TU_PHONENUM like ? ");
				paramArrayList.add("%" + vo.getPhoneNum() + "%");
			}

			// 产品ID
			if (vo.getProId() != -1) {
				sqlBuffer = sqlBuffer.append(" and tr.PRO_ID = ? ");
				sqlCountBuffer = sqlCountBuffer.append(" and tr.PRO_ID = ?  ");
				paramArrayList.add(vo.getProId());
			}

			// 订购状态
			if (vo.getStatus() != -1) {
				sqlBuffer = sqlBuffer.append(" and tr.TO_STATUS = ? ");
				sqlCountBuffer = sqlCountBuffer
						.append(" and tr.TO_STATUS = ? ");
				paramArrayList.add(vo.getStatus());
			}

			if (paramArrayList.size() > 0) {
				params = paramArrayList.toArray();
			}

		}

		sqlBuffer = sqlBuffer.append(" order by tr.TO_ID ");

		Pagination pageList = (Pagination) list(sqlBuffer.toString(),
				sqlCountBuffer.toString(), params, new TerUserOrderRowMapper(),
				pageSize, pageNum);
		return pageList;
	}

	// 获取用户订购信息
	public TerUserOrderVO getCustomerOrder(long id) {
		TerUserOrderVO vo = new TerUserOrderVO();
		final Object[] params = new Object[] { id };
		List<?> rows = this.queryForList(QUERY_TERSER_ORDER_BY_ID, params);
		Iterator<?> it = rows.iterator();
		if (it.hasNext()) {
			Map<?, ?> map = (Map<?, ?>) it.next();
			vo.setId(Long.parseLong(Util.objectToStr(map.get("TO_ID"))));
			vo.setProId(Long.parseLong(Util.objectToStr(map.get("PRO_ID"))));
			vo.setTuId(Long.parseLong(Util.objectToStr(map.get("TU_ID"))));
			vo.setStatus(Integer.parseInt(Util
					.objectToStr(map.get("TO_STATUS"))));
			vo.setCreateTime(Util.objectToStr(map.get("TO_CREATETIME")));
			vo.setPhoneNum(Util.object2Str(map.get("TU_PHONENUM")));
			vo.setTerId(Long.parseLong(Util.objectToStr(map.get("TER_ID"))));
			vo.setProName(Util.objectToStr(map.get("PRO_NAME")));
		}
		return vo;

	}

	// 更新订购状态
	public int updateCustomerOrderStatus(TerUserOrderVO vo) {
		String sql = "update T_MISMP_TERUSER_ORDER set TO_STATUS = ? where TO_ID = ?";

		final Object[] params = new Object[] { vo.getStatus(), vo.getId() };
		return this.update(sql, params);
	}

	public Pagination listAllUserGroups(int pageSize, int pageNum) {
		List<TerUserGroupVO> list = new ArrayList<TerUserGroupVO>();
		String countSql = "SELECT COUNT(*) FROM T_MISMP_TERUSER_GROUP ";
		String sql = "SELECT * FROM T_MISMP_TERUSER_GROUP order by  TG_ID  asc ";
		Pagination pageList = this.list(sql, countSql, pageSize, pageNum);
		Iterator<?> it = pageList.iterator();
		while (it.hasNext()) {
			TerUserGroupVO vo = new TerUserGroupVO();
			setValue(vo, it);
			list.add(vo);
		}
		pageList.clear();
		pageList.addAll(list);
		return pageList;
	}

	private void setValue(TerUserGroupVO vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
		vo.setTgId(Util.object2Str(map.get("TG_ID")));
		vo.setTgName(Util.object2Str(map.get("TG_NAME")));
		vo.setTgDesc(Util.object2Str(map.get("TG_DESC")));
		vo.setTgPhone(Util.object2Str(map.get("TG_PHONE")));
	}

	public int addTerUserGroup(TerUserGroupVO terUserGroupVO) {
		String seqSql = "SELECT SEQ_T_MISMP_TERUSER_GROUP.nextval FROM DUAL";
		int id = this.queryForInt(seqSql);
		String insertSql = "INSERT INTO T_MISMP_TERUSER_GROUP VALUES ( ?, ?, ?, ?)";
		final Object[] params = new Object[] { id,
				Util.cutString(terUserGroupVO.getTgName(), 50),
				Util.cutString(terUserGroupVO.getTgDesc(), 200),
				Util.cutString(terUserGroupVO.getTgPhone(), 1000) };
		int m = this.update(insertSql, params);
		return m;
	}

	public TerUserGroupVO getTerUserGroupById(String idForEdit) {
		TerUserGroupVO vo = new TerUserGroupVO();
		String sql = "SELECT T1.* FROM T_MISMP_TERUSER_GROUP T1 "
				+ "WHERE T1.TG_ID = ? ";
		List<?> result = this.queryForList(sql, new Object[] { idForEdit });
		Iterator it = result.iterator();
		if (it.hasNext()) {
			setValue(vo, it);
		}
		return vo;
	}

	public int updateTerUserGroup(TerUserGroupVO terUserGroupVO) {
		String frontSQL = "UPDATE T_MISMP_TERUSER_GROUP SET TG_NAME = ? "
				        + ", TG_DESC = ? , TG_PHONE = ? ";
		String backSQL = " WHERE TG_ID = ? ";
		List<String> paramList = new ArrayList<String>();
		paramList.add(terUserGroupVO.getTgName());
		paramList.add(terUserGroupVO.getTgDesc());
		paramList.add(terUserGroupVO.getTgPhone());
		paramList.add(terUserGroupVO.getTgId());
		final Object[] params = paramList.toArray();
		int m = this.update(frontSQL + backSQL, params);
		return m;
	}

	public int deleteTerUserGroup(String idForDel) {
		String sql = "DELETE FROM T_MISMP_TERUSER_GROUP WHERE TG_ID = ?";
		int m = 0;
		m = this.update(sql, new Object[] { idForDel });
		return m;
	}
	
	//用户群组结构查询
	public List<TerUserGroupTreeVO> getAllUserGroupTreeConfigList() {
		String sql = "select UGT_ID,UGT_NAME,UGT_DESC,UGT_PARENT_ID,UGT_TYPE,UGT_URL,UGT_ORDER from T_MISMP_TERUSER_GROUP_TREE order by UGT_ORDER asc ";
		String sqlcount = "select count(*) as c from T_MISMP_TERUSER_GROUP_TREE ";
		final Object[] params = new Object[] {};
		return this.query(sql, params, new TerUserGroupTreeRowMapper());
	}
	
	//用户群组结构查询
	public List<TerUserGroupTreeVO> getUserGroupTreeConfigList(int pageSize,int pageNum) {
		String sql = "select UGT_ID,UGT_NAME,UGT_DESC,UGT_PARENT_ID,UGT_TYPE,UGT_URL,UGT_ORDER from T_MISMP_TERUSER_GROUP_TREE order  by UGT_ORDER asc ";
		String sqlcount = "select count(*) as c from T_MISMP_TERUSER_GROUP_TREE ";
		final Object[] params = new Object[] {};
		return this.list(sql, sqlcount, params, new TerUserGroupTreeRowMapper(), pageSize,
				pageNum);
	}
	
	//查找id对应菜单结构
	public TerUserGroupTreeVO getTerUserGroupTreeById(String ugtId) {
		String sql = " select UGT_ID,UGT_NAME,UGT_DESC,UGT_PARENT_ID,UGT_TYPE,UGT_URL,UGT_ORDER from T_MISMP_TERUSER_GROUP_TREE where UGT_ID = ? ";
		final Object[] params = new Object[] { ugtId };
		List list = this.query(sql, params, new TerUserGroupTreeRowMapper());
		if (list == null || list.size() == 0)
			return null;
		TerUserGroupTreeVO ugtVO = (TerUserGroupTreeVO) list.get(0);
		return ugtVO;
	}
	
	//删除群组结构
	public int deleteUserGroupTreeConfig(ArrayList ids) {
		String sql = "delete from T_MISMP_TERUSER_GROUP_TREE";
		final Object[] params;
 		if(ids.size()>0)
		{
			sql += " where ";
			params = new Object[ids.size()];
			for(int i=0;i<ids.size();i++){
				if(i!=(ids.size()-1)){
					sql += " UGT_ID = ? or ";
				}
				if(i==(ids.size()-1)){
					sql += " UGT_ID = ? ";
				}
				params[i] = ids.get(i);
			}
			return this.update(sql, params);
		}
 		return 0;
	}
	
	//添加群组结构
	public int addUserGroupTree(TerUserGroupTreeVO vo) {
		String sql = "insert into T_MISMP_TERUSER_GROUP_TREE(UGT_ID,UGT_NAME,UGT_DESC,UGT_PARENT_ID,UGT_TYPE,UGT_URL,UGT_ORDER)"
			+ " values(?,?,?,?,?,?,?)";
		
		final Object[] params = new Object[] { Util.nullToStr(vo.getUgtId()),
											   Util.nullToStr(vo.getUgtName()),
											   Util.nullToStr(vo.getUgtDesc()),
											   Util.nullToStr(vo.getUgtParentId()),
											   vo.getUgtType(),
											   Util.nullToStr(vo.getUgtUrl()),
											   Util.nullToStr(vo.getUgtOrder())};
		return this.update(sql, params);
	}
	
	//修改群组结构
	public int modifyUserGroupTree(TerUserGroupTreeVO vo) {
		String sql = "update T_MISMP_TERUSER_GROUP_TREE set UGT_NAME = ? , UGT_DESC = ? where UGT_ID = ? ";
		final Object[] params = new Object[] { Util.nullToStr(vo.getUgtName()),
											Util.nullToStr(vo.getUgtDesc()), Util.nullToStr(vo.getUgtId())
				 };
		return this.update(sql, params);
	}
}

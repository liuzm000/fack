/**
 * 
 */
package cn.eshore.mismp.version.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.eshore.mismp.dao.impl.BaseDAOJdbcTemplate;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.Util;
import cn.eshore.mismp.version.dao.TreeViewDAO;
import cn.eshore.mismp.version.domain.form.ProductForm;
import cn.eshore.mismp.version.domain.form.SoftTypeForm;
import cn.eshore.mismp.version.domain.form.SpForm;
import cn.eshore.mismp.version.domain.vo.SoftTypeVO;

/**
 * @author wanglei
 * 
 */
public class TreeViewDAOImpl extends BaseDAOJdbcTemplate implements TreeViewDAO {

	public List<SpForm> getAllSpForList() {
		List<SpForm> list = new ArrayList<SpForm>();
		String sql = "SELECT * FROM T_MISMP_SP ORDER BY SP_ID asc";
		List<?> result = this.queryForList(sql);
		Iterator it = result.iterator();
		while (it.hasNext()) {
			SpForm vo = new SpForm();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	private void setValue(SpForm vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
		vo.setSpId(Util.object2Str(map.get("SP_ID")));
		vo.setSpCode(Util.object2Str(map.get("SP_CODE")));
		vo.setSpType(Util.object2Str(map.get("SP_TYPE")));
		vo.setSpLocal(Util.object2Str(map.get("SP_LOCAL")));
		vo.setSpName(Util.object2Str(map.get("SP_NAME")));
		vo.setSpDesc(Util.object2Str(map.get("SP_DESC")));
		vo.setSpAddr(Util.object2Str(map.get("SP_ADDR")));
		vo.setSpPerson(Util.object2Str(map.get("SP_PERSON")));
		vo.setSpPhone(Util.object2Str(map.get("SP_PHONE")));
		vo.setSpCreatetime(Util.getFormatDate((Util.object2Str(map
				.get("SP_CREATETIME"))), "yyyy-MM-dd HH:mm:ss"));
	}

	public List<ProductForm> getProductsBySpId(String spId) {
		List<ProductForm> list = new ArrayList<ProductForm>();
		String sql = "SELECT * FROM T_MISMP_PRODUCT WHERE SP_ID = ? ORDER BY PRO_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { spId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			ProductForm vo = new ProductForm();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	private void setValue(ProductForm vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
		vo.setProId(Util.object2Str(map.get("PRO_ID")));
		vo.setImspId(Util.object2Str(map.get("IMSP_ID")));
		vo.setProType(Util.object2Str(map.get("PRO_TYPE")));
		vo.setProFee(Util.object2Str(map.get("PRO_FEE")));
		vo.setProName(Util.object2Str(map.get("PRO_NAME")));
		vo.setProDesc(Util.object2Str(map.get("PRO_DESC")));
		vo.setProStatus(Util.object2Str(map.get("PRO_STATUS")));
		vo.setSpId(Util.object2Str(map.get("SP_ID")));
		vo.setProParentId(Util.object2Str(map.get("PRO_PARENT_ID")));
		vo.setProCreatetime(Util.getFormatDate((Util.object2Str(map
				.get("PRO_CREATETIME"))), "yyyy-MM-dd HH:mm:ss"));
	}

	public List<SoftTypeVO> getAllSoftTypeByProId(String proId) {
		List<SoftTypeVO> list = new ArrayList<SoftTypeVO>();
		String sql = "SELECT T1.* " + "FROM T_MISMP_SOFTTYPE T1 "
				+ "WHERE T1.PRO_ID = ? " + "ORDER BY T1.ST_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { proId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			SoftTypeVO vo = new SoftTypeVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	private void setValue(SoftTypeVO vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
		vo.setStId(Util.object2Str(map.get("ST_ID")));
		vo.setStName(Util.object2Str(map.get("ST_NAME")));
		vo.setProId(Util.object2Str(map.get("PRO_ID")));
		vo.setStDesc(Util.object2Str(map.get("ST_DESC")));
		vo.setStEngName(Util.object2Str(map.get("ST_ENG_NAME")));
		vo.setStIcon(Util.object2Str(map.get("ST_ICON")));
		vo.setStIconPath(Util.object2Str(map.get("ST_ICON_PATH")));
		vo.setStMsgPush(Util.object2Str(map.get("ST_MSG_PUSH")));
		vo.setStMsgType(Util.object2Str(map.get("ST_MSG_TYPE")));
		vo.setStListIcon(Util.object2Str(map.get("ST_LIST_ICON")));
		vo.setStListIconPath(Util.object2Str(map.get("ST_LIST_ICON_PATH")));
		vo.setStCreatetime(Util.getFormatDate((Util.object2Str(map
				.get("ST_CREATETIME"))), "yyyy-MM-dd HH:mm:ss"));
	}

	private void setValue(SoftTypeForm vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
		vo.setStId(Util.object2Str(map.get("ST_ID")));
		vo.setStName(Util.object2Str(map.get("ST_NAME")));
		vo.setProId(Util.object2Str(map.get("PRO_ID")));
		vo.setStDesc(Util.object2Str(map.get("ST_DESC")));
		vo.setStEngName(Util.object2Str(map.get("ST_ENG_NAME")));
		vo.setStIcon(Util.object2Str(map.get("ST_ICON")));
		vo.setStIconPath(Util.object2Str(map.get("ST_ICON_PATH")));
		vo.setStMsgPush(Util.object2Str(map.get("ST_MSG_PUSH")));
		vo.setStCreatetime(Util.getFormatDate((Util.object2Str(map
				.get("ST_CREATETIME"))), "yyyy-MM-dd HH:mm:ss"));

		vo.setTerCode(Util.object2Str(map.get("TER_CODE")));
		vo.setTerDes(Util.object2Str(map.get("TER_DES")));
		vo.setTerEvdo(Util.object2Str(map.get("TER_EVDO")));
		vo.setTerId(Util.object2Str(map.get("TER_ID")));
		vo.setTerJava(Util.object2Str(map.get("TER_JAVA")));
		vo.setTerMms(Util.object2Str(map.get("TER_MMS")));
		vo.setTerName(Util.object2Str(map.get("TER_NAME")));
		vo.setTerPicPath(Util.object2Str(map.get("TER_PICPATH")));
		vo.setTerPlat(Util.object2Str(map.get("TER_PLAT")));
		vo.setTerWap(Util.object2Str(map.get("TER_WAP")));
		vo.setFacId(Util.object2Str(map.get("FAC_ID")));
		vo.setVirId(Util.object2Str(map.get("VIR_ID")));

		vo.setSvId(Util.object2Str(map.get("SV_ID")));
		vo.setSvVersion(Util.object2Str(map.get("SV_VERSION")));
		vo.setSvName(Util.object2Str(map.get("SV_NAME")));
		vo.setSvForceupdate(Util.object2Str(map.get("SV_FORCEUPDATE")));
		vo.setSvDesc(Util.object2Str(map.get("SV_DESC")));
		vo.setSvFilePath(Util.object2Str(map.get("SV_FILEPATH")));
		vo.setSvStatus(Util.object2Str(map.get("SV_STATUS")));
		vo.setSvCreatetime(Util.getFormatDate((Util.object2Str(map
				.get("SV_CREATETIME"))), "yyyy-MM-dd HH:mm:ss"));
	}

	public List<SoftTypeForm> getAllSoftVersionByProId(String proId) {
		List<SoftTypeForm> list = new ArrayList<SoftTypeForm>();
		String sql = "SELECT T1.*, T2.*, T3.*, T4.* "
				+ "FROM T_MISMP_SOFTVERSION T1 "
				+ "LEFT JOIN T_MISMP_SOFTTYPE T2 ON T1.ST_ID = T2.ST_ID "
				+ "LEFT JOIN T_MISMP_SOFT_TERM_MAP T3 ON T3.ST_ID = T2.ST_ID "
				+ "LEFT JOIN T_MISMP_TERMINAL T4 ON T3.TER_ID = T4.TER_ID "
				+ "WHERE T2.PRO_ID = ? " + "ORDER BY T1.ST_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { proId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			SoftTypeForm vo = new SoftTypeForm();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	public Pagination getAllSoftTypeByProId(String proId, String pageNum,
			String pageSize) {
		List<SoftTypeVO> list = new ArrayList<SoftTypeVO>();
		String countSql = "SELECT COUNT(*) FROM T_MISMP_SOFTTYPE T1 "
				+ "WHERE T1.PRO_ID = ? ORDER BY T1.ST_ID DESC";
		String sql = "SELECT T1.* FROM T_MISMP_SOFTTYPE T1 "
				+ "WHERE T1.PRO_ID = ? ORDER BY T1.ST_ID DESC";
		Pagination pageList = this.list(sql, countSql,new Object[]{proId} ,new Integer(pageSize)
				.intValue(), new Integer(pageNum).intValue());
		Iterator<?> it = pageList.iterator();
		while (it.hasNext()) {
			SoftTypeVO vo = new SoftTypeVO();
			setValue(vo, it);
			list.add(vo);
		}
		pageList.clear();
		pageList.addAll(list);
		return pageList;
	}
}

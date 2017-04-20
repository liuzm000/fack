/**
 * 
 */
package cn.eshore.mismp.version.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import cn.eshore.mismp.dao.domain.SoftAuditVO;
import cn.eshore.mismp.dao.domain.TerPtStatVO;
import cn.eshore.mismp.dao.domain.TerUserGroupVO;
import cn.eshore.mismp.dao.domain.rowmapper.TerPtCountRowMapper;
import cn.eshore.mismp.dao.impl.BaseDAOJdbcTemplate;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.Util;
import cn.eshore.mismp.version.dao.VersionOperDAO;
import cn.eshore.mismp.version.domain.vo.FactoryVO;
import cn.eshore.mismp.version.domain.vo.ProductVO;
import cn.eshore.mismp.version.domain.vo.SoftTermVO;
import cn.eshore.mismp.version.domain.vo.SoftTypeVO;
import cn.eshore.mismp.version.domain.vo.SoftVersionVO;
import cn.eshore.mismp.version.domain.vo.StNumPolicyVO;
import cn.eshore.mismp.version.domain.vo.TerUserGroupMapVO;
import cn.eshore.mismp.version.domain.vo.TerUserGroupVersionMapVO;
import cn.eshore.mismp.version.domain.vo.TerminalVO;

/**
 * @author wanglei
 * 
 */
public class VersionOperDAOImpl extends BaseDAOJdbcTemplate implements
		VersionOperDAO {

	public int addSoftType(SoftTypeVO vo) {
		String seqSql = "SELECT SEQ_T_MISMP_SOFTTYPE.nextval FROM DUAL";
		int id = this.queryForInt(seqSql);
		String insertSql = "INSERT INTO T_MISMP_SOFTTYPE "
			             + "(ST_ID,ST_NAME,ST_DESC,PRO_ID,ST_CREATETIME,ST_ICON,ST_ICON_PATH,ST_MSG_PUSH,"
			             + "ST_ENG_NAME,ST_MSG_TYPE,APP_TYPE) "
			             + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		final Object[] params = new Object[] { id,
				Util.cutString(vo.getStName(), 30),
				Util.cutString(vo.getStDesc(), 100),
				Util.cutString(vo.getProId(), 10), vo.getStCreatetime(),
				Util.cutString(vo.getStIcon(), 30),
				Util.cutString(vo.getStIconPath(), 100),
				Util.cutString(vo.getStMsgPush(), 2),
				Util.cutString(vo.getStEngName(), 30),
				new Integer(vo.getStMsgType()).intValue(),
				Util.cutString(vo.getAppType(), 10) };
		int m = 0;
		try {
			m = this.update(insertSql, params);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (m > 0) {
			return id;
		}
		return m;
	}

	public int addSoftAudit(SoftAuditVO vo) {
		String seqSql = "SELECT SEQ_T_MISMP_SOFTAUDIT.nextval FROM DUAL";
		int id = this.queryForInt(seqSql);
		String insertSql = "INSERT INTO T_MISMP_SOFT_AUDIT (AUDIT_ID,SOFT_VERSION,SOFT_NAME,SOFT_FORCEUPDATE,SOFT_FILEPATH,CREATE_TIME,AUDIT_STATUS,SOFT_DESC,SV_ID) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		final Object[] params = new Object[] { id,
				Util.cutString(vo.getVersion(), 20),
				Util.cutString(vo.getName(), 30), vo.getForceupdate(),
				vo.getFilePath(), vo.getCreatetime(), vo.getStatus(),
				vo.getDesc(), vo.getSvId() };
		int m = 0;
		try {
			m = this.update(insertSql, params);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (m > 0) {
			return id;
		}
		return m;
	}

	public List<SoftVersionVO> getVersionsByStId(String stId) {
		List<SoftVersionVO> list = new ArrayList<SoftVersionVO>();
		String sql = "SELECT T1.* " + "FROM T_MISMP_SOFTVERSION T1 "
				+ "WHERE T1.ST_ID = ? " + "ORDER BY T1.SV_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { stId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			SoftVersionVO vo = new SoftVersionVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	private void setValue(SoftVersionVO vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
		vo.setStId(Util.object2Str(map.get("ST_ID")));
		vo.setSvId(Util.object2Str(map.get("SV_ID")));
		vo.setSvVersion(Util.object2Str(map.get("SV_VERSION")));
		vo.setSvName(Util.object2Str(map.get("SV_NAME")));
		vo.setSvForceupdate(Util.object2Str(map.get("SV_FORCEUPDATE")));
		vo.setSvDesc(Util.object2Str(map.get("SV_DESC")));
		vo.setSvFilePath(Util.object2Str(map.get("SV_FILEPATH")));
		vo.setSvStatus(Util.object2Str(map.get("SV_STATUS")));
		vo.setSvCreatetime(Util.getFormatDate((Util.object2Str(map
				.get("SV_CREATETIME"))), "yyyy-MM-dd HH:mm:ss"));
		vo.setSvSender(Util.object2Str(map.get("SV_SENDER")));
		vo.setSvSubmitid(Util.object2Str(map.get("SV_SUBMITID")));
		vo.setSvType(Util.object2Str(map.get("SV_TYPE")));
		vo.setStEngName(Util.object2Str(map.get("ST_ENG_NAME")));
		vo.setSvPreviewPath(Util.object2Str(map.get("SV_PREVIEW_PATH")));
		vo.setPublishType(Util.object2Str(map.get("PUBLISH_TYPE")));
		vo.setDownloadUrl(Util.object2Str(map.get("SV_DOWNLOAD_URL")));
	}

	public int addSoftVersion(SoftVersionVO vo) {
		String seqSql = "SELECT SEQ_T_MISMP_SOFTVERSION.nextval FROM DUAL";
		int id = this.queryForInt(seqSql);
		String insertSql = "INSERT INTO T_MISMP_SOFTVERSION(SV_ID,ST_ID,SV_VERSION,SV_NAME,SV_FORCEUPDATE,SV_DESC,SV_FILEPATH,SV_STATUS,SV_CREATETIME,SV_SENDER,SV_SUBMITID,SV_TYPE,SV_PREVIEW_PATH,PUBLISH_TYPE,SV_DOWNLOAD_URL)"
			+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		final Object[] params = new Object[] { id,
				Util.cutString(vo.getStId(), 10),
				Util.cutString(vo.getSvVersion(), 20),
				Util.cutString(vo.getSvName(), 30),
				new Integer(vo.getSvForceupdate()).intValue(),
				Util.cutString(vo.getSvDesc(), 1000),
				Util.cutString(vo.getSvFilePath(), 100),
				new Integer(vo.getSvStatus()).intValue(), vo.getSvCreatetime(),
				Util.cutString(vo.getSvSender(), 20),
				Util.cutString(vo.getSvSubmitid(), 20),
				Util.cutString(vo.getSvType(), 20),
				vo.getSvPreviewPath(),
				new Integer(vo.getPublishType()).intValue(),
				Util.cutString(vo.getDownloadUrl(), 300)
		};
		int m = 0;
		try {
			m = this.update(insertSql, params);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (m > 0) {
			return id;
		}
		return m;
	}

	public List<TerminalVO> getAllTerminals(String facId) {
		List<TerminalVO> list = new ArrayList<TerminalVO>();
		String sql = "SELECT T1.*,T2.VIR_NAME FROM T_MISMP_TERMINAL T1 "
			    + "LEFT JOIN T_MISMP_VIRTUAL T2 ON T1.VIR_ID = T2.VIR_ID "
				+ "WHERE T1.FAC_ID = ? " + "ORDER BY T1.TER_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { facId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			TerminalVO vo = new TerminalVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	private void setValue(TerminalVO vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
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
		vo.setFacName(Util.object2Str(map.get("FAC_NAME")));
		vo.setVirName(Util.object2Str(map.get("VIR_NAME")));
	}

	public SoftTypeVO getSoftTypeInfoByStId(String stId) {
		SoftTypeVO vfo = new SoftTypeVO();
		List<?> rows = null;
		String sql = "SELECT T1.* FROM T_MISMP_SOFTTYPE T1 "
				+ "WHERE T1.ST_ID = ? ORDER BY T1.ST_ID DESC";
		rows = this.queryForList(sql, new Object[] { new Long(stId) });

		Iterator<?> it = rows.iterator();
		if (it.hasNext()) {
			setValue(vfo, it);
		}
		return vfo;
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
		vo.setStCreatetime(Util.getFormatDate((Util.object2Str(map
				.get("ST_CREATETIME"))), "yyyy-MM-dd HH:mm:ss"));
		vo.setAppType(Util.object2Str(map.get("APP_TYPE")));
		vo.setStListIcon(Util.object2Str(map.get("ST_LIST_ICON")));
		vo.setStListIconPath(Util.object2Str(map.get("ST_LIST_ICON_PATH")));
	}

	public List<FactoryVO> getAllFactory() {
		List<FactoryVO> list = new ArrayList<FactoryVO>();
		String sql = "SELECT T1.* FROM T_MISMP_FACTORY T1 "
				+ "ORDER BY T1.FAC_ID DESC";
		List<?> result = this.queryForList(sql);
		Iterator it = result.iterator();
		while (it.hasNext()) {
			FactoryVO vo = new FactoryVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	private void setValue(FactoryVO vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
		vo.setFacId(Util.object2Str(map.get("FAC_ID")));
		vo.setFacName(Util.object2Str(map.get("FAC_NAME")));
		vo.setFacAddr(Util.object2Str(map.get("FAC_ADDR")));
		vo.setFacDesc(Util.object2Str(map.get("FAC_DESC")));
		vo.setFacPhone(Util.object2Str(map.get("FAC_PHONE")));
	}

	public List<SoftTermVO> getAllMatchedTerminals(String stId) {
		List<SoftTermVO> list = new ArrayList<SoftTermVO>();
		String sql = "SELECT T1.* FROM T_MISMP_SOFT_TERM_MAP T1 "
				+ "WHERE T1.ST_ID = ? " + "ORDER BY T1.ST_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { stId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			SoftTermVO vo = new SoftTermVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	private void setValue(SoftTermVO vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
		vo.setStId(Util.object2Str(map.get("ST_ID")));
		vo.setTerId(Util.object2Str(map.get("TER_ID")));
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
		vo.setFacName(Util.object2Str(map.get("FAC_NAME")));
	}

	public int deleteSoftTermMatch(String stId, String canceledIds) {
		String sql = "DELETE FROM T_MISMP_SOFT_TERM_MAP WHERE ST_ID = ? ";
		int m = 0;
		m = this.update(sql, new Object[] { stId });
		return m;
	}

	public int addSoftTermMap(String stId, String selectedId,String proId) {
		String insertSql = "INSERT INTO T_MISMP_SOFT_TERM_MAP(ST_ID,TER_ID,PRO_ID) VALUES ( ?, ?, ?)";
		final Object[] params = new Object[] { stId, selectedId,proId };
		int m = 0;
		m = this.update(insertSql, params);
		return m;
	}

	public List<TerminalVO> getAllTerminalsForCollege() {
		List<TerminalVO> list = new ArrayList<TerminalVO>();
		String sql = "SELECT T1.* FROM T_MISMP_TERMINAL T1 "
				+ "ORDER BY T1.TER_ID DESC";
		List<?> result = this.queryForList(sql);
		Iterator it = result.iterator();
		while (it.hasNext()) {
			TerminalVO vo = new TerminalVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	public int addProduct(ProductVO vo) {
		String seqSql = "SELECT SEQ_T_MISMP_PRODUCT.nextval FROM DUAL";
		int id = this.queryForInt(seqSql);
		// ISMP_ID闇�瑕�0x寮�澶�,鎬诲叡10浣嶉暱搴�
		StringBuffer ismpIdStrBuffer = new StringBuffer();
		String idString = new Integer(id).toString();
		int idStringLength = idString.length();
		ismpIdStrBuffer.append("0x");
		for (int i = 0; i < 8 - idStringLength; i++) {
			ismpIdStrBuffer.append("0");
		}
		ismpIdStrBuffer.append(idString);

		String insertSql = "INSERT INTO T_MISMP_PRODUCT VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		final Object[] params = new Object[] { id, vo.getIsmpId(),
				vo.getProName(), "", 3, 0, 0, vo.getSpId(), new Date(),
				vo.getProParentId(), vo.getProRemark() };
		this.update(insertSql, params);
		return id;
	}

	public int addStNumPolicy(StNumPolicyVO stnpVo) {
		String seqSql = "SELECT SEQ_T_MISMP_ST_NUM_POLICY.nextval FROM DUAL";
		int id = this.queryForInt(seqSql);
		String insertSql = "INSERT INTO T_MISMP_SOFTTYPE_NUM_POLICY VALUES ( ?, ?, ?, ?, ?, ?)";
		final Object[] params = new Object[] { id, stnpVo.getStId(),
				stnpVo.getPoNumSection(), stnpVo.getPoType(),
				stnpVo.getPoCreatetime(), stnpVo.getPoUpdatetime() };
		int m = this.update(insertSql, params);
		return id;
	}

	public int updateSoftType(SoftTypeVO vo) {
		String frontSQL = "UPDATE T_MISMP_SOFTTYPE SET ST_NAME = ? "
				+ ", ST_DESC = ? " + ", ST_MSG_PUSH = ? "
				+ ", ST_ENG_NAME = ? " + ", ST_MSG_TYPE = ? ";
		String backSQL = " WHERE ST_ID = ? ";
		String icon = vo.getStIcon();
		String iconPath = vo.getStIconPath();
		String listIcon = vo.getStListIcon();
		String listIconPath = vo.getStListIconPath();
		List<String> paramList = new ArrayList<String>();
		paramList.add(vo.getStName());
		paramList.add(vo.getStDesc());
		paramList.add(vo.getStMsgPush());
		paramList.add(vo.getStEngName());
		paramList.add(vo.getStMsgType());
		if (StringUtils.isNotEmpty(icon) && StringUtils.isNotEmpty(iconPath)) {
			frontSQL = frontSQL + ", ST_ICON = ? , ST_ICON_PATH = ? ";
			paramList.add(icon);
			paramList.add(iconPath);
		}
		if (StringUtils.isNotEmpty(listIcon) && StringUtils.isNotEmpty(listIconPath)) {
			frontSQL = frontSQL + ", ST_LIST_ICON = ? , ST_LIST_ICON_PATH = ? ";
			paramList.add(listIcon);
			paramList.add(listIconPath);
		}
		paramList.add(vo.getStId());
		final Object[] params = paramList.toArray();
		int m = this.update(frontSQL + backSQL, params);
		return m;
	}

	public SoftVersionVO getVersionBySvId(String svId) {
		SoftVersionVO vfo = new SoftVersionVO();
		List<?> rows = null;
		String sql = "SELECT T1.* FROM T_MISMP_SOFTVERSION T1 "
				+ "WHERE T1.SV_ID = ? ORDER BY T1.SV_ID DESC";
		rows = this.queryForList(sql, new Object[] { new Long(svId) });

		Iterator<?> it = rows.iterator();
		if (it.hasNext()) {
			setValue(vfo, it);
		}
		return vfo;
	}

	public int updateVersion(SoftVersionVO vo) {
		String frontSQL = "UPDATE T_MISMP_SOFTVERSION SET SV_NAME = ? "
				+ ", SV_DESC = ? " + ", SV_FORCEUPDATE = ? "
				+ ", SV_VERSION = ? " + ", PUBLISH_TYPE = ?, SV_DOWNLOAD_URL = ? ";
		String backSQL = " WHERE SV_ID = ? ";
		String filePath = vo.getSvFilePath();
		String previewFilePath = vo.getSvPreviewPath();
		List<String> paramList = new ArrayList<String>();
		paramList.add(vo.getSvName());
		paramList.add(vo.getSvDesc());
		paramList.add(vo.getSvForceupdate());
		paramList.add(vo.getSvVersion());
		paramList.add(vo.getPublishType());
		paramList.add(vo.getDownloadUrl());
		if (StringUtils.isNotEmpty(filePath)) {
			frontSQL = frontSQL + ", SV_FILEPATH = ? ";
			paramList.add(filePath);
		}
		if (StringUtils.isNotEmpty(previewFilePath)) {
			frontSQL = frontSQL + ", SV_PREVIEW_PATH = ? ";
			paramList.add(previewFilePath);
		}
		paramList.add(vo.getSvId());
		final Object[] params = paramList.toArray();
		log.debug("params array : >>>> " + params);
		int m = this.update(frontSQL + backSQL, params);
		return m;
	}

	public int deleteVersion(String svId) {
		String sql = "DELETE FROM T_MISMP_SOFTVERSION WHERE SV_ID = ?";
		int m = 0;
		m = this.update(sql, new Object[] { svId });
		return m;
	}

	public Pagination getVersionsByStId(String stId, String pageNum,
			String pageSize) {
		List<SoftVersionVO> list = new ArrayList<SoftVersionVO>();
		String countSql = "SELECT COUNT(*) FROM T_MISMP_SOFTVERSION T1 "
				+ "WHERE T1.ST_ID = ? ORDER BY T1.SV_ID DESC";
		String sql = "SELECT T1.* FROM T_MISMP_SOFTVERSION T1 "
				+ "WHERE T1.ST_ID = ? ORDER BY T1.SV_ID DESC";
		Pagination pageList = this.list(sql, countSql, new Object[] { stId },
				new Integer(pageSize).intValue(), new Integer(pageNum)
						.intValue());
		Iterator<?> it = pageList.iterator();
		while (it.hasNext()) {
			SoftVersionVO vo = new SoftVersionVO();
			setValue(vo, it);
			list.add(vo);
		}
		pageList.clear();
		pageList.addAll(list);
		return pageList;
	}

	public SoftVersionVO getLatestVersionByStId(String softId) {
		SoftVersionVO vfo = new SoftVersionVO();
		List<?> rows = null;
		String sql = "SELECT T1.*,T2.ST_ENG_NAME FROM T_MISMP_SOFTVERSION T1 "
				+ "LEFT JOIN T_MISMP_SOFTTYPE T2 ON T1.ST_ID = T2.ST_ID "
				+ "WHERE T1.ST_ID = ? ORDER BY T1.SV_ID DESC";
		rows = this.queryForList(sql, new Object[] { new Long(softId) });

		Iterator<?> it = rows.iterator();
		if (it.hasNext()) {
			setValue(vfo, it);
		}
		return vfo;
	}

	public List<?> getAllMatchedTerminalsByStId(String softId) {
		List<SoftTermVO> list = new ArrayList<SoftTermVO>();
		String sql = "SELECT T1.*,T2.*,T3.FAC_NAME FROM T_MISMP_SOFT_TERM_MAP T1 "
				+ "LEFT JOIN T_MISMP_TERMINAL T2 ON T1.TER_ID = T2.TER_ID "
				+ "LEFT JOIN T_MISMP_FACTORY T3 ON T2.FAC_ID = T3.FAC_ID "
				+ "WHERE T1.ST_ID = ? " + "ORDER BY T1.ST_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { softId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			SoftTermVO vo = new SoftTermVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	public List<TerUserGroupMapVO> getAllTerUserGroups() {
		List<TerUserGroupMapVO> list = new ArrayList<TerUserGroupMapVO>();
		String sql = "SELECT * FROM T_MISMP_TERUSER_GROUP ORDER BY TG_ID DESC";
		List<?> result = this.queryForList(sql);
		Iterator it = result.iterator();
		while (it.hasNext()) {
			TerUserGroupMapVO vo = new TerUserGroupMapVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	private void setValue(TerUserGroupMapVO vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
		vo.setSgmId(Util.object2Str(map.get("SGM_ID")));
		vo.setStId(Util.object2Str(map.get("ST_ID")));
		vo.setTgId(Util.object2Str(map.get("TG_ID")));
		vo.setTgName(Util.object2Str(map.get("TG_NAME")));
		vo.setTgDesc(Util.object2Str(map.get("TG_DESC")));
		vo.setTgPhone(Util.object2Str(map.get("TG_PHONE")));
	}

	public List<TerUserGroupMapVO> getAllMatchedGroups(String stId) {
		List<TerUserGroupMapVO> list = new ArrayList<TerUserGroupMapVO>();
		String sql = "SELECT T1.*,T2.* FROM T_MISMP_SOFTTYPE_UG_MAP T1 "
				+ "LEFT JOIN T_MISMP_TERUSER_GROUP T2 ON T1.TG_ID = T2.TG_ID "
				+ "WHERE T1.ST_ID = ? " + "ORDER BY T1.SGM_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { stId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			TerUserGroupMapVO vo = new TerUserGroupMapVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	public int deleteSoftGroupMatch(String stId, String canceledIds) {
		String sql = "DELETE FROM T_MISMP_SOFTTYPE_UG_MAP WHERE ST_ID = ? AND TG_ID IN ("
				+ canceledIds + ")";
		int m = 0;
		m = this.update(sql, new Object[] { stId });
		return m;
	}

	public int addSoftGroupMap(String stId, String string) {
		String seqSql = "SELECT SEQ_T_MISMP_SOFTTYPE_UG_MAP.nextval FROM DUAL";
		String insertSql = "INSERT INTO T_MISMP_SOFTTYPE_UG_MAP VALUES ( ?, ?, ?)";
		int id = this.queryForInt(seqSql);
		final Object[] params = new Object[] { id, stId, string };
		int m = 0;
		m = this.update(insertSql, params);
		return m;
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
		if (m > 0) {
			return id;
		} else {
			return m;
		}
	}

	public boolean checkUniqueImspId(String imspId) {
		String sql = "SELECT COUNT(*) FROM T_MISMP_PRODUCT WHERE ISMP_ID = ?";
		int count = this.queryForInt(sql, new Object[] { imspId });
		if (count >= 1) {
			return false;
		}
		return true;
	}

	public boolean checkUniqueSvName(String svName) {
		String sql = "SELECT COUNT(*) FROM T_MISMP_PRODUCT WHERE PRO_NAME = ?";
		int count = this.queryForInt(sql, new Object[] { svName });
		if (count >= 1) {
			return false;
		}
		return true;
	}

	public List<TerminalVO> getMobileTerminals(String facId) {
		List<TerminalVO> list = new ArrayList<TerminalVO>();
		String sql = "SELECT T1.* FROM T_MISMP_TERMINAL T1 "
				+ "WHERE T1.FAC_ID = ? AND T1.TER_PLAT = 'mobile' "
				+ " ORDER BY T1.TER_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { facId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			TerminalVO vo = new TerminalVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	public List<TerminalVO> getBrewTerminals(String facId) {
		List<TerminalVO> list = new ArrayList<TerminalVO>();
		String sql = "SELECT T1.* FROM T_MISMP_TERMINAL T1 "
				+ "WHERE T1.FAC_ID = ? AND T1.TER_PLAT = 'brew' "
				+ " ORDER BY T1.TER_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { facId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			TerminalVO vo = new TerminalVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}

	public int updateSoftTypeIcon(SoftTypeVO vo) {
		String frontSQL = "UPDATE T_MISMP_SOFTTYPE SET 1 = 1 ";
		String backSQL = " WHERE ST_ID = ? ";
		String icon = vo.getStIcon();
		String iconPath = vo.getStIconPath();
		List<String> paramList = new ArrayList<String>();
		int m = 0;
		if (StringUtils.isNotEmpty(icon) && StringUtils.isNotEmpty(iconPath)) {
			frontSQL = frontSQL + " ,ST_ICON = ? , ST_ICON_PATH = ?";
			paramList.add(icon);
			paramList.add(iconPath);
			paramList.add(vo.getStId());
			final Object[] params = paramList.toArray();
			log.debug("params array : >>>> " + params);
			m = this.update(frontSQL + backSQL, params);
		}
		return m;
	}

	public int deleteAllSoftTermMatchByStId(String softId) {
		String sql = "DELETE FROM T_MISMP_SOFT_TERM_MAP WHERE ST_ID = ? ";
		int m = 0;
		m = this.update(sql, new Object[] { softId });
		return m;
	}

	public List<TerUserGroupVersionMapVO> getAllTerUserGroupsForVersion() {
		List<TerUserGroupVersionMapVO> list = new ArrayList<TerUserGroupVersionMapVO>();
		String sql = "SELECT * FROM T_MISMP_SOFTVERSION_UG_MAP ORDER BY TG_ID DESC";
		List<?> result = this.queryForList(sql);
		Iterator it = result.iterator();
		while (it.hasNext()) {
			TerUserGroupVersionMapVO vo = new TerUserGroupVersionMapVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}
	
	private void setValue(TerUserGroupVersionMapVO vo, Iterator<?> it) {
		Map<?, ?> map = (Map<?, ?>) it.next();
		vo.setSvgmId(Util.object2Str(map.get("SVGM_ID")));
		vo.setSvId(Util.object2Str(map.get("SV_ID")));
		vo.setTgId(Util.object2Str(map.get("TG_ID")));
		vo.setTgName(Util.object2Str(map.get("TG_NAME")));
		vo.setTgDesc(Util.object2Str(map.get("TG_DESC")));
		vo.setTgPhone(Util.object2Str(map.get("TG_PHONE")));
	}
	
	public int deleteSoftVersionGroupMatch(String svId, String canceledIds) {
		String sql = "DELETE FROM T_MISMP_SOFTVERSION_UG_MAP WHERE SV_ID = ? AND TG_ID IN ("
				+ canceledIds + ")";
		int m = 0;
		m = this.update(sql, new Object[] { svId });
		return m;
	}
	
	public int addSoftVersionGroupMap(String svId, String string) {
		String seqSql = "SELECT SEQ_T_MISMP_SOFTVERSION_UG_MAP.NEXTVAL FROM DUAL";
		String insertSql = "INSERT INTO T_MISMP_SOFTVERSION_UG_MAP VALUES ( ?, ?, ?)";
		int id = this.queryForInt(seqSql);
		final Object[] params = new Object[] { id, svId, string };
		int m = 0;
		m = this.update(insertSql, params);
		return m;
	}

	public List<TerUserGroupVersionMapVO> getAllMatchedVersionGroups(String svId) {
		List<TerUserGroupVersionMapVO> list = new ArrayList<TerUserGroupVersionMapVO>();
		String sql = "SELECT T1.*,T2.* FROM T_MISMP_SOFTVERSION_UG_MAP T1 "
				+ "LEFT JOIN T_MISMP_TERUSER_GROUP T2 ON T1.TG_ID = T2.TG_ID "
				+ "WHERE T1.SV_ID = ? " + "ORDER BY T1.SVGM_ID DESC";
		List<?> result = this.queryForList(sql, new Object[] { svId });
		Iterator it = result.iterator();
		while (it.hasNext()) {
			TerUserGroupVersionMapVO vo = new TerUserGroupVersionMapVO();
			setValue(vo, it);
			list.add(vo);
		}
		return list;
	}
	
	//查找分类对应应用数量
	public int getLastestSoftVersionCount(long typeId, long terId){
		int count = 0;
		StringBuffer stIdBuffer = new StringBuffer("");
		String sql = "";
		sql = " select pt.PRO_NAME,pt.PRO_ID,pt.PRO_SUPPLIER, pf.PF_FOR_FEE, pf.PF_FOR_VALID,st.ST_LIST_ICON_PATH, sv.SV_CREATETIME,sv.SV_STATUS,sv.SV_ID, ls.LS_LEVEL, ls.LS_COUNT from T_MISMP_SOFTVERSION sv inner join T_MISMP_SOFTTYPE st on sv.ST_ID= st.ST_ID inner join T_MISMP_PRODUCT pt on st.PRO_ID =pt.PRO_ID inner join T_MISMP_PRODUCT_TYPE proType on pt.PT_ID = proType.PT_ID left join T_MISMP_PRODUCT_FEE pf on pf.PRO_ID = pt.PRO_ID left join T_MISMP_LEVEL_STAT ls on ls.PRO_ID = pt.PRO_ID inner join T_MISMP_SOFT_TERM_MAP tm on st.ST_ID = tm.ST_ID where proType.PT_ID = " + typeId + " and tm.TER_ID = " + terId + " and pt.ISMP_ID != '0x89999999' order by sv.SV_CREATETIME desc ";
		String maxSql = " SELECT MAX(T1.SV_ID) FROM T_MISMP_SOFTVERSION T1  where T1.SV_STATUS = 2 GROUP BY T1.ST_ID ";
		String finalSql = "SELECT * FROM (" + sql
				+ ") OUT WHERE OUT.SV_ID IN (" + maxSql + ") ";
		String countSql = "SELECT COUNT(*) FROM (" + sql
				+ ") OUT WHERE OUT.SV_ID IN (" + maxSql + ") ";
		StringBuffer filterSql = new StringBuffer();
		finalSql = finalSql + filterSql.toString();
		countSql = countSql + filterSql.toString();
		count = this.queryForInt(countSql);
		return count;
	
	
	}
	
	//获取终端对应分类应用统计数据
	public TerPtStatVO getTerPtStatVO(long terId, long ptId){
		TerPtStatVO vo = new TerPtStatVO();
		String sql = "select TER_ID, PT_ID, TPC_APP_COUNT from T_MISMP_TER_PT_STAT where TER_ID = ? and PT_ID = ? ";
		List<TerPtStatVO> list = (List<TerPtStatVO>) this.query(sql,
				new Object[] { terId, ptId }, new RowMapperResultSetExtractor(
						new TerPtCountRowMapper()));
		if(list != null && list.size() > 0){
			vo = list.get(0);
		}
		return vo;
	}
	
	//增加应用分类统计数据
	public int addTerPtStatVO(TerPtStatVO vo){
		String insertSql = "INSERT INTO T_MISMP_TER_PT_STAT(TER_ID,PT_ID,TPC_APP_COUNT) VALUES ( ?, ?, ?)";
		final Object[] params = new Object[] { vo.getTerId(), vo.getPtId(), vo.getCount() };
		int m = 0;
		m = this.update(insertSql, params);
		return m;
	}
	
	//更新应用分类统计数据
	public int updateTerPtStatVO(TerPtStatVO vo){
		String updateSql = "update T_MISMP_TER_PT_STAT set TPC_APP_COUNT = ? where TER_ID = ? and PT_ID = ? ";
		final Object[] params = new Object[] { vo.getCount(), vo.getTerId(), vo.getPtId()};
		int m = 0;
		m = this.update(updateSql, params);
		return m;
	}
	
	//查找分类对应应用数量
	public int getTerPtStatCount(List<Integer> list, long terId){
		int count = 0;
		StringBuffer ptIdBuffer = new StringBuffer("");
		for(Integer ptId : list){
			ptIdBuffer.append(ptId).append(",");
		}
		
		String ptIdStr = ptIdBuffer.substring(0, ptIdBuffer.length() -1);
		String countSql = " SELECT sum(TPC_APP_COUNT) FROM T_MISMP_TER_PT_STAT T1  where T1.TER_ID = " + terId + " and  T1.PT_ID in (" + ptIdStr + ")";
		count = this.queryForInt(countSql);
		return count;
	
	
	}
}

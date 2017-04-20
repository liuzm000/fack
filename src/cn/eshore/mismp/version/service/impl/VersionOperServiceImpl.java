/**
 * 
 */
package cn.eshore.mismp.version.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.eshore.mismp.dao.domain.ProductTypeVO;
import cn.eshore.mismp.dao.domain.SoftAuditVO;
import cn.eshore.mismp.dao.domain.TerPtStatVO;
import cn.eshore.mismp.dao.domain.TerUserGroupVO;
import cn.eshore.mismp.spmanager.dao.SpDAO;
import cn.eshore.mismp.spmanager.dao.model.Product;
import cn.eshore.mismp.util.Pagination;
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
import cn.eshore.mismp.version.service.VersionOperService;
import cn.eshore.mismp.util.StringUtil;

/**
 * @author wanglei
 * 
 */
public class VersionOperServiceImpl implements VersionOperService {

	private VersionOperDAO versionOperDAO;
	
	private SpDAO spDAO;

	public VersionOperDAO getVersionOperDAO() {
		return versionOperDAO;
	}

	public void setVersionOperDAO(VersionOperDAO versionOperDAO) {
		this.versionOperDAO = versionOperDAO;
	}

	public int addSoftType(SoftTypeVO vo) {
		return this.versionOperDAO.addSoftType(vo);
	}

	public List<SoftVersionVO> getVersionsByStId(String stId) {
		return this.versionOperDAO.getVersionsByStId(stId);
	}

	public int addSoftVersion(SoftVersionVO vo) {
		return this.versionOperDAO.addSoftVersion(vo);
	}
	
	

	public SpDAO getSpDAO() {
		return spDAO;
	}

	public void setSpDAO(SpDAO spDAO) {
		this.spDAO = spDAO;
	}

	public List<FactoryVO> getAllTerminals() {
		List<FactoryVO> facList = this.versionOperDAO.getAllFactory();
		if(facList != null && facList.size() > 0){
			for(int i = 0;i < facList.size();i++){
				FactoryVO vo = facList.get(i);
				List<TerminalVO> terList = this.versionOperDAO.getAllTerminals(vo.getFacId());
				if(terList != null && terList.size() > 0){
					vo.setTerList(terList);
				}
			}
		}
		return facList;
	}

	public SoftTypeVO getSoftTypeInfoByStId(String stId) {
		return this.versionOperDAO.getSoftTypeInfoByStId(stId);
	}

	public List<SoftTermVO> getAllMatchedTerminals(String stId) {
		return this.versionOperDAO.getAllMatchedTerminals(stId);
	}

	public int deleteSoftTermMatch(String stId,String canceledIds) {
		return this.versionOperDAO.deleteSoftTermMatch(stId,canceledIds);
	}

	public int addSoftTermMap(String stId, String selectedIds,String proId) {
		return this.versionOperDAO.addSoftTermMap(stId,selectedIds,proId);
	}

	public int addSoftAudit(SoftAuditVO vo){
		return this.versionOperDAO.addSoftAudit(vo);
	}

	public List<TerminalVO> getAllTerminalsForCollege() {
		return this.versionOperDAO.getAllTerminalsForCollege();
	}

	public int addProduct(ProductVO proVo) {
		return this.versionOperDAO.addProduct(proVo);
	}

	public int addStNumPolicy(StNumPolicyVO stnpVo) {
		return this.versionOperDAO.addStNumPolicy(stnpVo);
	}

	public int updateSoftType(SoftTypeVO vo) {
		return this.versionOperDAO.updateSoftType(vo);
	}

	public SoftVersionVO getVersionBySvId(String svId) {
		return this.versionOperDAO.getVersionBySvId(svId);
	}

	public int updateVersion(SoftVersionVO vo) {
		return this.versionOperDAO.updateVersion(vo);
	}

	public int deleteVersion(String svId) {
		return this.versionOperDAO.deleteVersion(svId);
	}

	public Pagination getVersionsByStId(String stId, String pageNum, String pageSize) {
		return this.versionOperDAO.getVersionsByStId(stId,pageNum,pageSize);
	}

	public SoftVersionVO getLatestVersionByStId(String softId) {
		return this.versionOperDAO.getLatestVersionByStId(softId);
	}

	public List<?> getAllMatchedTerminalsByStId(String softId) {
		return this.versionOperDAO.getAllMatchedTerminalsByStId(softId);
	}

	public List<TerUserGroupMapVO> getAllTerUserGroups() {
		return this.versionOperDAO.getAllTerUserGroups();
	}

	public List<TerUserGroupMapVO> getAllMatchedGroups(String stId) {
		return this.versionOperDAO.getAllMatchedGroups(stId);
	}

	public int deleteSoftGroupMatch(String stId, String canceledIds) {
		return this.versionOperDAO.deleteSoftGroupMatch(stId,canceledIds);
	}

	public int addSoftGroupMap(String stId, String string) {
		return this.versionOperDAO.addSoftGroupMap(stId,string);
	}
	
	public int addTerUserGroup(TerUserGroupVO terUserGroupVO){
		return versionOperDAO.addTerUserGroup(terUserGroupVO) ;
	}

	public boolean checkUniqueImspId(String imspId) {
		return versionOperDAO.checkUniqueImspId(imspId);
	}
	
	public boolean checkUniqueSvName(String svName){
		return versionOperDAO.checkUniqueSvName(svName);
	}

	public List<FactoryVO> getMobileTerminal() {
		List<FactoryVO> facList = this.versionOperDAO.getAllFactory();
		if(facList != null && facList.size() > 0){
			for(int i = 0;i < facList.size();i++){
				FactoryVO vo = facList.get(i);
				List<TerminalVO> terList = this.versionOperDAO.getMobileTerminals(vo.getFacId());
				if(terList != null && terList.size() > 0){
					vo.setTerList(terList);
				}
			}
		}
		return facList;
	}

	public List<FactoryVO> getBrewTerminal() {
		List<FactoryVO> facList = this.versionOperDAO.getAllFactory();
		if(facList != null && facList.size() > 0){
			for(int i = 0;i < facList.size();i++){
				FactoryVO vo = facList.get(i);
				List<TerminalVO> terList = this.versionOperDAO.getBrewTerminals(vo.getFacId());
				if(terList != null && terList.size() > 0){
					vo.setTerList(terList);
				}
			}
		}
		return facList;
	}

	public int updateSoftTypeIcon(SoftTypeVO stVo) {
		return this.versionOperDAO.updateSoftTypeIcon(stVo);
	}

	public int deleteAllSoftTermMatchByStId(String softId) {
		return this.versionOperDAO.deleteAllSoftTermMatchByStId(softId);
	}

	public List<TerUserGroupVersionMapVO> getAllTerUserGroupsForVersion() {
		return this.versionOperDAO.getAllTerUserGroupsForVersion();
	}

	public int addSoftVersionGroupMap(String svId, String string) {
		return this.versionOperDAO.addSoftVersionGroupMap(svId, string);
	}

	public int deleteSoftVersionGroupMatch(String svId, String canceledIds) {
		return this.versionOperDAO.deleteSoftVersionGroupMatch(svId, canceledIds);
	}

	public List<TerUserGroupVersionMapVO> getAllMatchedVersionGroups(String svId) {
		return this.versionOperDAO.getAllMatchedVersionGroups(svId);
	}
	
	//更新统计软件信息
	public void updateTetPtStat(String versionId){
		//查找版本
		SoftVersionVO sv = versionOperDAO.getVersionBySvId(versionId);
		String stId = sv.getStId();
		if(!StringUtil.isEmpty(stId)){
			int proId = spDAO.getSoftTypeById(new Integer(stId)).getPro_id();
			//查找产品
			Product product = spDAO.getProById(proId);
			//查找产品分类
			int ptId = product.getPtId();
			//查找父产品分类
			long parentPtId = spDAO.getProductTypeById("" + ptId).getParentId();
			//查找父产品分类从属子产品分类
			List<ProductTypeVO> subProductTypeList = new ArrayList<ProductTypeVO>();
			if(parentPtId != 0){
				subProductTypeList = spDAO.getProductTypeListByParentId(parentPtId);
			}
			List<Integer> ptIdArray = new ArrayList<Integer>();
			for(ProductTypeVO sub: subProductTypeList){
				ptIdArray.add(sub.getId());
			}
			
			//查找适配机型
			List<SoftTermVO> softTermList = versionOperDAO.getAllMatchedTerminals(stId);
			//遍历结果集
			for(SoftTermVO vo: softTermList){
				String terId = vo.getTerId();
				//查找分类应用结果数
				int count = versionOperDAO.getLastestSoftVersionCount(new Long(ptId), new Long(terId));
				TerPtStatVO terPtStat = versionOperDAO.getTerPtStatVO(new Long(terId),new Long(ptId));
				if(terPtStat.getTerId() == 0){
					//增加分类应用结果数
					TerPtStatVO addPtStat = new TerPtStatVO();
					addPtStat.setPtId(ptId);
					addPtStat.setTerId(new Long(terId));
					addPtStat.setCount(count);
					versionOperDAO.addTerPtStatVO(addPtStat);
				}
				else{
					//更新分类应用结果数
					TerPtStatVO addPtStat = new TerPtStatVO();
					addPtStat.setPtId(ptId);
					addPtStat.setTerId(new Long(terId));
					addPtStat.setCount(count);
					versionOperDAO.updateTerPtStatVO(addPtStat);
				}
				
				//统计分类应用数量
				if(parentPtId != 0){
					int parentCount = versionOperDAO.getTerPtStatCount(ptIdArray, new Long(terId));
					TerPtStatVO terPtParentStat = versionOperDAO.getTerPtStatVO(new Long(terId),new Long(parentPtId));
					if(terPtParentStat.getTerId() == 0){
						//增加父分类应用结果数
						TerPtStatVO addPtStat = new TerPtStatVO();
						addPtStat.setPtId(parentPtId);
						addPtStat.setTerId(new Long(terId));
						addPtStat.setCount(parentCount);
						versionOperDAO.addTerPtStatVO(addPtStat);
					}
					else{
						//更新父分类应用结果数
						TerPtStatVO addPtStat = new TerPtStatVO();
						addPtStat.setPtId(parentPtId);
						addPtStat.setTerId(new Long(terId));
						addPtStat.setCount(parentCount);
						versionOperDAO.updateTerPtStatVO(addPtStat);
					}
				}
				
				
			}
			
		}
	}
}

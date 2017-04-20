/**
 * 
 */
package cn.eshore.mismp.version.service;

import java.util.List;

import cn.eshore.mismp.dao.domain.SoftAuditVO;
import cn.eshore.mismp.dao.domain.TerUserGroupVO;
import cn.eshore.mismp.util.Pagination;
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
public interface VersionOperService{

	int addSoftType(SoftTypeVO vo);

	List<SoftVersionVO> getVersionsByStId(String stId);

	int addSoftVersion(SoftVersionVO vo);
	
	int addSoftAudit(SoftAuditVO vo);

	SoftTypeVO getSoftTypeInfoByStId(String stId);

	List<FactoryVO> getAllTerminals();

	List<SoftTermVO> getAllMatchedTerminals(String stId);

	int deleteSoftTermMatch(String stId, String canceledIds);

	int addSoftTermMap(String stId, String string,String proId);

	//校园创业：获取所有手机型号
	List<TerminalVO> getAllTerminalsForCollege();

	int addProduct(ProductVO proVo);

	int addStNumPolicy(StNumPolicyVO stnpVo);

	int updateSoftType(SoftTypeVO vo);

	SoftVersionVO getVersionBySvId(String svId);

	int updateVersion(SoftVersionVO vo);

	int deleteVersion(String svId);

	Pagination getVersionsByStId(String stId, String pageNum, String pageSize);

	SoftVersionVO getLatestVersionByStId(String softId);

	List<?> getAllMatchedTerminalsByStId(String softId);

	List<TerUserGroupMapVO> getAllTerUserGroups();

	List<TerUserGroupMapVO> getAllMatchedGroups(String stId);

	int deleteSoftGroupMatch(String stId, String canceledIds);

	int addSoftGroupMap(String stId, String string);
	
	public int addTerUserGroup(TerUserGroupVO terUserGroupVO);

	boolean checkUniqueImspId(String imspId);
	
	boolean checkUniqueSvName(String svName);

	/**
	 * 获取mobile的所有手机类型
	 * @return
	 */
	List<FactoryVO> getMobileTerminal();

	/**
	 * 获取brew的所有手机类型
	 * @return
	 */
	List<FactoryVO> getBrewTerminal();
	
	/**
	 * 更新应用图片
	 * @param stVo
	 */
	int updateSoftTypeIcon(SoftTypeVO stVo);

	/**
	 * 删除应用的所有适配
	 * @param softId
	 * @return
	 */
	int deleteAllSoftTermMatchByStId(String softId);

	/**
	 * 获取所有版本映射的用户号码群
	 * @return
	 */
	List<TerUserGroupVersionMapVO> getAllTerUserGroupsForVersion();

	int addSoftVersionGroupMap(String svId, String string);
	
	int deleteSoftVersionGroupMatch(String svId, String canceledIds);

	List<TerUserGroupVersionMapVO> getAllMatchedVersionGroups(String svId);
	
	//更新统计软件信息
	public void updateTetPtStat(String versionId);
}

/**
 * 
 */
package cn.eshore.mismp.version.dao;

import java.util.ArrayList;
import java.util.List;

import cn.eshore.mismp.dao.BaseDAO;
import cn.eshore.mismp.dao.domain.SoftAuditVO;
import cn.eshore.mismp.dao.domain.TerPtStatVO;
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
public interface VersionOperDAO extends BaseDAO{

	int addSoftType(SoftTypeVO vo);

	List<SoftVersionVO> getVersionsByStId(String stId);

	int addSoftVersion(SoftVersionVO vo);

	List<TerminalVO> getAllTerminals(String facId);

	SoftTypeVO getSoftTypeInfoByStId(String stId);

	List<FactoryVO> getAllFactory();

	List<SoftTermVO> getAllMatchedTerminals(String stId);

	int deleteSoftTermMatch(String stId,String canceledIds);

	int addSoftTermMap(String stId, String selectedIds,String proId);
	
	public int addSoftAudit(SoftAuditVO vo);

	List<TerminalVO> getAllTerminalsForCollege();

	int addProduct(ProductVO vo);

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

	int addTerUserGroup(TerUserGroupVO terUserGroupVO);

	boolean checkUniqueImspId(String imspId);
	
	boolean checkUniqueSvName(String svName);

	List<TerminalVO> getMobileTerminals(String facId);

	List<TerminalVO> getBrewTerminals(String facId);

	int updateSoftTypeIcon(SoftTypeVO stVo);

	int deleteAllSoftTermMatchByStId(String softId);

	List<TerUserGroupVersionMapVO> getAllTerUserGroupsForVersion();

	int deleteSoftVersionGroupMatch(String svId, String canceledIds);
	
	int addSoftVersionGroupMap(String svId, String string);

	List<TerUserGroupVersionMapVO> getAllMatchedVersionGroups(String svId);
	
	//查找分类对应应用数量
	public int getLastestSoftVersionCount(long typeId, long terId);
	
	//获取终端对应分类应用统计数据
	public TerPtStatVO getTerPtStatVO(long terId, long ptId);
	
//	增加应用分类统计数据
	public int addTerPtStatVO(TerPtStatVO vo);
	
//	更新应用分类统计数据
	public int updateTerPtStatVO(TerPtStatVO vo);
	
//	查找分类对应应用数量
	public int getTerPtStatCount(List<Integer> list, long terId);
}

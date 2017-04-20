/**
 * 
 */
package cn.eshore.mismp.version.service;

import java.util.List;

import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.version.domain.form.SoftTypeForm;
import cn.eshore.mismp.version.domain.vo.SoftTypeVO;

/**
 * @author wanglei
 *
 */
public interface TreeViewService {

	String getSpManageXML(String addUrl);
	
	String getProductsBySpId(String addUrl,String spId);
	
	List<SoftTypeVO> getAllSoftTypeByProId(String proId);

	List<SoftTypeForm> getAllSoftVersionByProId(String proId);

	Pagination getAllSoftTypeByProId(String proId, String pageNum, String pageSize);
}

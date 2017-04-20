/**
 * 
 */
package cn.eshore.mismp.version.dao;

import java.util.List;

import cn.eshore.mismp.dao.BaseDAO;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.version.domain.form.ProductForm;
import cn.eshore.mismp.version.domain.form.SoftTypeForm;
import cn.eshore.mismp.version.domain.form.SpForm;
import cn.eshore.mismp.version.domain.vo.SoftTypeVO;

/**
 * @author wanglei
 *
 */
public interface TreeViewDAO extends BaseDAO {
	
	List<SpForm> getAllSpForList();
	
	List<ProductForm> getProductsBySpId(String spId);

	List<SoftTypeVO> getAllSoftTypeByProId(String proId);

	List<SoftTypeForm> getAllSoftVersionByProId(String proId);

	Pagination getAllSoftTypeByProId(String proId, String pageNum, String pageSize);
}

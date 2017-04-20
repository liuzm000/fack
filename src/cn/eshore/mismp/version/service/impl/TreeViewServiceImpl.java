/**
 * 
 */
package cn.eshore.mismp.version.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.version.dao.TreeViewDAO;
import cn.eshore.mismp.version.domain.form.ProductForm;
import cn.eshore.mismp.version.domain.form.SoftTypeForm;
import cn.eshore.mismp.version.domain.form.SpForm;
import cn.eshore.mismp.version.domain.vo.SoftTypeVO;
import cn.eshore.mismp.version.service.TreeViewService;

/**
 * @author wanglei
 * 
 */
public class TreeViewServiceImpl implements TreeViewService {

	private final static Log Log = LogFactory.getLog(TreeViewServiceImpl.class);

	/**
	 * 注入DAO
	 */
	TreeViewDAO treeViewDAO;

	/**
	 * 
	 */
	public String getSpManageXML(String addUrl) {
		List<SpForm> spList = this.treeViewDAO.getAllSpForList();
		StringBuilder strbTreeXml = null;
		if (spList != null && spList.size() > 0) {
			strbTreeXml = new StringBuilder();
			for (int i = 0; i < spList.size(); i++) {
				SpForm vo = (SpForm) spList.get(i);
				strbTreeXml.append("<li  ");
				strbTreeXml.append("id='").append(vo.getSpId()).append("'>");
				strbTreeXml.append("<span class='text'>")
						.append(vo.getSpName()).append("</span>");
				strbTreeXml.append("<ul class='ajax'>");
				strbTreeXml.append("<li>{url:").append(addUrl).append(
						"/spManage.do?").append(
						"action=ajaxShowProsBySpId&spId=").append(vo.getSpId())
						.append("&addUrl=").append(addUrl).append("}");
				strbTreeXml.append("</li>");
				strbTreeXml.append("</ul>");
				strbTreeXml.append("</li>");
			}
		}
		return strbTreeXml.toString();
	}

	public TreeViewDAO getTreeViewDAO() {
		return treeViewDAO;
	}

	public void setTreeViewDAO(TreeViewDAO treeViewDAO) {
		this.treeViewDAO = treeViewDAO;
	}

	public String getProductsBySpId(String addUrl, String spId) {
		StringBuilder strbTreeXml = new StringBuilder();
		List<ProductForm> productList = this.treeViewDAO
				.getProductsBySpId(spId);
		if (productList != null && productList.size() > 0) {
			for (int j = 0; j < productList.size(); j++) {
				ProductForm vo = (ProductForm) productList.get(j);
				strbTreeXml.append("<li class='folder' ");
				strbTreeXml.append("id='").append(vo.getProId()).append("' ray='product'>");
				strbTreeXml.append("<span class='text' style='COLOR: #363636; CURSOR: pointer;'>").append(
						vo.getProName()).append("</span>");
				strbTreeXml.append("</li>");
			}
		}
		return strbTreeXml.toString();
	}

	public List<SoftTypeVO> getAllSoftTypeByProId(String proId) {
		return this.treeViewDAO.getAllSoftTypeByProId(proId);
	}

	public List<SoftTypeForm> getAllSoftVersionByProId(String proId) {
		return this.treeViewDAO.getAllSoftVersionByProId(proId);
	}

	public Pagination getAllSoftTypeByProId(String proId, String pageNum, String pageSize) {
		return this.treeViewDAO.getAllSoftTypeByProId(proId,pageNum,pageSize);
	}

}

package cn.eshore.mismp.business.service.impl;

import java.util.List;

import cn.eshore.mismp.business.dao.PubBusinessDAO;
import cn.eshore.mismp.business.model.InterfaceModel;
import cn.eshore.mismp.business.service.PubBusinessService;
import cn.eshore.mismp.util.Pagination;

public class PubBusinessServiceImpl implements PubBusinessService{
	private PubBusinessDAO pubBusinessDAO;
	
	public void setPubBusinessDAO(PubBusinessDAO pubBusinessDAO) {
		this.pubBusinessDAO = pubBusinessDAO;
	}
	@Override
	public Pagination getPubVideoList(String page, String pagesize,String interNum) {
		return this.pubBusinessDAO.getPubVideoList(page,pagesize,interNum);
	}
	
	@Override
	public InterfaceModel getInterMsgById(String accessId) {
		return this.pubBusinessDAO.getPubVideoList(accessId);
	}
	@Override
	public List getIconList(long id) {
		return this.pubBusinessDAO.getIconList(id);
	}
	@Override
	public List getMediaList(long id) {
		return this.pubBusinessDAO.getMediaList(id);
	}
	@Override
	public Pagination getInterfaceList(int pageNum, int pageSize) {
		return this.pubBusinessDAO.getInterfaceList(pageNum,pageSize);
	}

	
	
}

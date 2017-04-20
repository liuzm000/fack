package cn.eshore.mismp.business.dao;

import java.util.List;

import cn.eshore.mismp.business.model.InterfaceModel;
import cn.eshore.mismp.util.Pagination;

public interface PubBusinessDAO {

	Pagination getPubVideoList(String page, String pagesize,String interNum);

	InterfaceModel getPubVideoList(String accessId);

	List getIconList(long id);

	List getMediaList(long id);

	Pagination getInterfaceList(int pageNum, int pageSize);

}

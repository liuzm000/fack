package cn.eshore.mismp.business.service;

import java.util.List;

import cn.eshore.mismp.business.model.InterfaceModel;
import cn.eshore.mismp.util.Pagination;

public interface PubBusinessService {
	/**
	 *  分页获取基础信息表
	 * @param page
	 * @param pagesize
	 * @param interNum
	 * @return
	 */
	Pagination getPubVideoList(String page, String pagesize,String interNum);
	/**
	 * 根据接口编码返回接口配置信息
	 * @param accessId
	 * @return
	 */
	InterfaceModel getInterMsgById(String accessId);
	/**
	 *  根据id获取其多张图片
	 * @param id
	 * @return
	 */
	List getIconList(long id);
	/**
	 *  根据id获取多个媒体(视频、音频)信息
	 * @param id
	 * @return
	 */
	List getMediaList(long id);
	/**
	 * 获取所有接口信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Pagination getInterfaceList(int pageNum, int pageSize);

	
}

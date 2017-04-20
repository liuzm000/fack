package cn.eshore.mismp.business.service.process;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.eshore.mismp.business.model.MutiIconModel;
import cn.eshore.mismp.business.model.MutiMediaModel;
import cn.eshore.mismp.business.model.PubModel;
import cn.eshore.mismp.common.Consts;
import cn.eshore.mismp.service.BusinessSupportService;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;

public class WrapJsonUtils {
	
	protected static final Logger log = Logger.getLogger(WrapJsonUtils.class);

	public static String PLAT_BASE_PATH = MobileGlobals.getProperty("plat.base.path");
	/**
	 * 装载成json(基础)
	 * @param result
	 * @param list
	 * @return
	 */
	public static String paginationWarpJson(String result, Pagination list) {
		if(list != null && list.size() != 0) {
			JSONObject resultJSON = new JSONObject();
			try {
				JSONArray jsonArray = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					JSONObject json = new JSONObject();
					PubModel model = (PubModel)list.get(i);
					json.put("id", model.getId());
					json.put("name", model.getName());
					json.put("title", model.getTitle());
					if(! StringUtils.isEmpty(model.getIcon())) {
						json.put("icon", PLAT_BASE_PATH+"/download"+model.getIcon());
					}
					if(! StringUtils.isEmpty(model.getFilePath())) {
						json.put("filePath", PLAT_BASE_PATH+"/download"+model.getFilePath());
					}
					json.put("iconHD", model.getIconHD());
					json.put("icons", model.getIcons());//暂时是这样
					json.put("desc", model.getDescript());
					if(! StringUtils.isEmpty(model.getDetail())) {
						json.put("detail", PLAT_BASE_PATH+"/download"+model.getDetail());
					}
					json.put("addr", model.getAddr());
					json.put("tele", model.getTele());
					json.put("unit", model.getUnit());
					json.put("author", model.getAuthor());
					json.put("flag", model.getFlag());
					json.put("value1", model.getValue1());
					json.put("value2", model.getValue2());
					json.put("time", model.getTime());
					jsonArray.put(json);
				}
				resultJSON.put("resultlist", jsonArray);
				resultJSON.put("resultcount", list.getRecordCount());
				resultJSON.put("curpage", list.getCurrentPage());
				resultJSON.put("totalpage", list.getPageCount());
				result = resultJSON.toString();
			} catch (Exception e) {
				log.debug(e.getMessage());
				e.printStackTrace();
			}
		} else {
			result = Consts.NO_RESULT_JSON;
		}
		return result;
	}
	
	/**
	 *  装载成json(基础 + 多图片 )
	 * @param result
	 * @param list 基础信息list
	 * @param businessSupportService 获取对应多张图片
	 * @return
	 */
	public static String paginationWarpJson(String result,Pagination list,BusinessSupportService businessSupportService) {
		
		if(list != null && list.size() != 0) {
			JSONObject resultJSON = new JSONObject();
			try {
				JSONArray jsonArray = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					JSONObject json = new JSONObject();
					PubModel model = (PubModel)list.get(i);
					json.put("id", model.getId());
					json.put("name", model.getName());
					json.put("title", model.getTitle());
					json.put("icon", model.getIcon());
					if(! StringUtils.isEmpty(model.getFilePath())) {
						json.put("filePath", PLAT_BASE_PATH+"/download"+model.getFilePath());
					}
					List iconList = businessSupportService.getPubBusinessService().getIconList(model.getId());
					String str = null;
					if(list != null ) {
						str = "";
						for (int j = 0; j < iconList.size(); j++) {
							MutiIconModel iconModel = (MutiIconModel)iconList.get(j);
							if(iconList.size() ==1 ) {
								str = str + PLAT_BASE_PATH+"/download"+iconModel.getFilePath();
							}else {
								if(j != iconList.size() -1) {
									str = str + PLAT_BASE_PATH+"/download"+iconModel.getFilePath()+",";
								} else {
									str = str + PLAT_BASE_PATH+"/download"+iconModel.getFilePath();
								}
							}
						}
						json.put("icons", str);//暂时是这样
					}
					json.put("iconHD", model.getIconHD());
					json.put("desc", model.getDescript());
					if(! StringUtils.isEmpty(model.getDetail())) {
						json.put("detail", PLAT_BASE_PATH+"/download"+model.getDetail());
					}
					json.put("addr", model.getAddr());
					json.put("tele", model.getTele());
					json.put("unit", model.getUnit());
					json.put("author", model.getAuthor());
					json.put("flag", model.getFlag());
					json.put("value1", model.getValue1());
					json.put("value2", model.getValue2());
					json.put("time", model.getTime());
					jsonArray.put(json);
				}
				resultJSON.put("resultlist", jsonArray);
				resultJSON.put("resultcount", list.getRecordCount());
				resultJSON.put("curpage", list.getCurrentPage());
				resultJSON.put("totalpage", list.getPageCount());
				result = resultJSON.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = Consts.NO_RESULT_JSON;
		}
		return result;
		
	}
	/**
	 *  装载成json(基础 + 多图片+多视频(音频 )
	 * @param result
	 * @param list 基础信息list
	 * @param businessSupportService 获取对应多张图片
	 * @param mediaName 返回list名称(videolist or audiolist or other)
	 * @return
	 */
	public static String paginationWarpJson(String result,Pagination list,BusinessSupportService businessSupportService,String mediaName) {
		
		if(list != null && list.size() != 0) {
			JSONObject resultJSON = new JSONObject();
			try {
				JSONArray jsonArray = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					JSONObject json = new JSONObject();
					PubModel model = (PubModel)list.get(i);
					json.put("id", model.getId());
					json.put("name", model.getName());
					json.put("title", model.getTitle());
					json.put("icon", model.getIcon());
					if(! StringUtils.isEmpty(model.getFilePath())) {
						json.put("filePath", PLAT_BASE_PATH+"/download"+model.getFilePath());
					}
					List iconList = businessSupportService.getPubBusinessService().getIconList(model.getId());
					String str = null;
					if(list != null ) {
						str = "";
						for (int j = 0; j < iconList.size(); j++) {
							MutiIconModel iconModel = (MutiIconModel)iconList.get(j);
							if(iconList.size() ==1 ) {
								str = str + PLAT_BASE_PATH+"/download"+iconModel.getFilePath();
							}else {
								if(j != iconList.size() -1) {
									str = str + PLAT_BASE_PATH+"/download"+iconModel.getFilePath()+",";
								} else {
									str = str + PLAT_BASE_PATH+"/download"+iconModel.getFilePath();
								}
							}
						}
						json.put("icons", str);//暂时是这样
					}
					
					JSONArray videoArray = null;
					List<?> videoList =businessSupportService.getPubBusinessService().getMediaList(model.getId());
					if(videoList != null && videoList.size() != 0) {
						try {
							videoArray = new JSONArray();
							for (int j = 0; j < videoList.size(); j++) {
								JSONObject videoJson = new JSONObject();
								MutiMediaModel videoModel = (MutiMediaModel) videoList.get(j);
								videoJson.put("icon",PLAT_BASE_PATH+"/download"+videoModel.getIcon());
								videoJson.put("videoUrl",PLAT_BASE_PATH+"/download"+videoModel.getFilePath());
								videoArray.put(videoJson);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						json.put(mediaName, videoArray);
					}
					
					
					json.put("iconHD", model.getIconHD());
					json.put("desc", model.getDescript());
					if(! StringUtils.isEmpty(model.getDetail())) {
						json.put("detail", PLAT_BASE_PATH+"/download"+model.getDetail());
					}
					json.put("addr", model.getAddr());
					json.put("tele", model.getTele());
					json.put("unit", model.getUnit());
					json.put("author", model.getAuthor());
					json.put("flag", model.getFlag());
					json.put("value1", model.getValue1());
					json.put("value2", model.getValue2());
					json.put("time", model.getTime());
					jsonArray.put(json);
				}
				resultJSON.put("resultlist", jsonArray);
				resultJSON.put("resultcount", list.getRecordCount());
				resultJSON.put("curpage", list.getCurrentPage());
				resultJSON.put("totalpage", list.getPageCount());
				result = resultJSON.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = Consts.NO_RESULT_JSON;
		}
		return result;
		
	}
	
	
}

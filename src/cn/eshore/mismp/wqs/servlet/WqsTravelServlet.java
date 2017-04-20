package cn.eshore.mismp.wqs.servlet;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.eshore.mismp.common.Consts;
import cn.eshore.mismp.util.MD5;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.weather.NsWeatherUtil;
import cn.eshore.mismp.web.servlet.BaseHttpServlet;
import cn.eshore.mismp.wqs.model.AlgGoodsModel;
import cn.eshore.mismp.wqs.model.BeautifulCountryModel;
import cn.eshore.mismp.wqs.model.BeautifulCountryVideoModel;
import cn.eshore.mismp.wqs.model.EcoTourismPictorialModel;
import cn.eshore.mismp.wqs.model.EcoTourismPictorialTypeModel;
import cn.eshore.mismp.wqs.model.EnterpriseModel;
import cn.eshore.mismp.wqs.model.HospitalModel;
import cn.eshore.mismp.wqs.model.HotelModel;
import cn.eshore.mismp.wqs.model.HotelOrderDetailModel;
import cn.eshore.mismp.wqs.model.HotelOrderModel;
import cn.eshore.mismp.wqs.model.HotelOrderReqParam;
import cn.eshore.mismp.wqs.model.IconMappingModel;
import cn.eshore.mismp.wqs.model.NsFeedbackModel;
import cn.eshore.mismp.wqs.model.NsFeedbackReplyModel;
import cn.eshore.mismp.wqs.model.NsTravelFeedbackReqParam;
import cn.eshore.mismp.wqs.model.RestOrderReqParam;
import cn.eshore.mismp.wqs.model.RestaurantOrderModel;
import cn.eshore.mismp.wqs.model.RoomTypeModel;
import cn.eshore.mismp.wqs.model.ShaTianPictorialModel;
import cn.eshore.mismp.wqs.model.ShaTianPictorialTypeModel;
import cn.eshore.mismp.wqs.model.TownJobsModel;
import cn.eshore.mismp.wqs.model.TownMessageModel;
import cn.eshore.mismp.wqs.model.TownVideoModel;
import cn.eshore.mismp.wqs.model.WqsTravelReqParam;
import cn.eshore.mismp.wqs.model.YujialeModel;

public class WqsTravelServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger log = Logger.getLogger(WqsTravelServlet.class);
	String PLAT_BASE_PATH = MobileGlobals.getProperty("plat.base.path");

	static {
		registerInvokeMethod("getPictorialType",WqsTravelReqParam.class);
		registerInvokeMethod("getShaTianPictorial",WqsTravelReqParam.class);
		registerInvokeMethod("getTownVideo",WqsTravelReqParam.class);
		registerInvokeMethod("getEcoTourismType",WqsTravelReqParam.class);
		registerInvokeMethod("getEcoTourism",WqsTravelReqParam.class);
		registerInvokeMethod("getTownMessage",WqsTravelReqParam.class);
		registerInvokeMethod("getBeautifulCountry",WqsTravelReqParam.class);
		registerInvokeMethod("getAlgGoodsType",WqsTravelReqParam.class);
		registerInvokeMethod("getAlgGoods",WqsTravelReqParam.class);
		registerInvokeMethod("getRestaurantOrder",WqsTravelReqParam.class);
		registerInvokeMethod("doRestaurantSubmit",RestOrderReqParam.class);
		registerInvokeMethod("getRoomType",WqsTravelReqParam.class);
		registerInvokeMethod("doHotelSubmit",HotelOrderReqParam.class);
		registerInvokeMethod("getHotelOrder",WqsTravelReqParam.class);
		registerInvokeMethod("doRestOrderCancel",WqsTravelReqParam.class);
		registerInvokeMethod("doHotelOrderCancel",WqsTravelReqParam.class);
		registerInvokeMethod("getHospitalService",WqsTravelReqParam.class);
		registerInvokeMethod("getEnterpriseList",WqsTravelReqParam.class);
		registerInvokeMethod("getWeather",WqsTravelReqParam.class);
		registerInvokeMethod("getHotelList",WqsTravelReqParam.class);
		registerInvokeMethod("getRestaurantList",WqsTravelReqParam.class);
		registerInvokeMethod("checkVideoPassword",WqsTravelReqParam.class);
		registerInvokeMethod("getNongJiaLe",WqsTravelReqParam.class);
		registerInvokeMethod("getYujiaInfo",WqsTravelReqParam.class);
		registerInvokeMethod("getYujiaList",WqsTravelReqParam.class);
		registerInvokeMethod("getTownNews",WqsTravelReqParam.class);
		registerInvokeMethod("getTownJobs",WqsTravelReqParam.class);
		registerInvokeMethod("getTownTraffic",WqsTravelReqParam.class);
		registerInvokeMethod("getFeedbackList",WqsTravelReqParam.class);
		registerInvokeMethod("doFeedbackCommit",NsTravelFeedbackReqParam.class);
		
	}
	
	//获取沙田画卷分类
	public String getPictorialType(WqsTravelReqParam param) {
		String result = "";
		log.info("[getPictorialType] 请求参数:"+ param.toString());
		if(param.isValid()) {
			String page = param.getPage();
			String pageSize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pageSize)) {
				pageSize = "20";
			}
			
			Pagination typeList = this.businessSupportService.getWqsTravelService().getPictorialType(Integer.parseInt(page),Integer.parseInt(pageSize));
			if(typeList != null && typeList.size() != 0) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < typeList.size(); i++) {
						JSONObject json = new JSONObject();
						ShaTianPictorialTypeModel vo = (ShaTianPictorialTypeModel) typeList.get(i);
						json.put("id", vo.getId());
						json.put("title", vo.getTypeName());
						json.put("icon", this.PLAT_BASE_PATH + "/download" + vo.getIconPath());
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", typeList.getRecordCount());
					resultjson.put("curpage", typeList.getCurrentPage());
					resultjson.put("totalpage", typeList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				result = Consts.NO_RESULT_JSON;
			}
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getHomePictorial]手机端返回结果result:" + result);
		return result;
		
	}
	
	//根据分类获取沙田画卷
	public String getShaTianPictorial(WqsTravelReqParam param) {
		log.info("[getShaTianPictorial] 请求参数:" + param.toString());
		String result = "";

		if (param.isValid()) {
			String page = param.getPage();
			String pagesize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			String typeId = param.getTypeId();
			
			Pagination keyList = this.businessSupportService.getWqsTravelService().getShaTianPictorial(Integer.parseInt(page), Integer.parseInt(pagesize),Integer.parseInt(typeId));
			if ((keyList != null) && (keyList.size() > 0)) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < keyList.size(); i++) {
						JSONObject json = new JSONObject();
						ShaTianPictorialModel vo = (ShaTianPictorialModel) keyList.get(i);
						json.put("id", vo.getId());
						json.put("title", vo.getDescript());
						json.put("icon", this.PLAT_BASE_PATH + "/download" + vo.getFilePath());
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", keyList.getRecordCount());
					resultjson.put("curpage", keyList.getCurrentPage());
					resultjson.put("totalpage", keyList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result = Consts.NO_RESULT_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getHomePictorial]手机端返回结果result:" + result);
		return result;
	}
	
	//获取名镇视频
	public String getTownVideo(WqsTravelReqParam param) {
		log.info("[getTownVideo] 请求参数 :"+ param.toString());
		String result = "";

		if (param.isValid()) {
			String page = param.getPage();
			String pagesize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			
			Pagination keyList = this.businessSupportService.getWqsTravelService().getTownVideo(Integer.parseInt(page), Integer.parseInt(pagesize));
			if ((keyList != null) && (keyList.size() > 0)) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < keyList.size(); i++) {
						JSONObject json = new JSONObject();
						TownVideoModel vo = (TownVideoModel) keyList.get(i);
						json.put("id", vo.getId());
						json.put("title", vo.getTitle());
						json.put("time", vo.getCreateTime());
						json.put("author", vo.getAuthor());
						json.put("videoUrl", this.PLAT_BASE_PATH + "/download" + vo.getFilePath());
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", keyList.getRecordCount());
					resultjson.put("curpage", keyList.getCurrentPage());
					resultjson.put("totalpage", keyList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result = Consts.NO_RESULT_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getTownVideo]手机端返回结果result:" + result);
		
		return result;
	}
	
	//获取生态旅游分类
	public String getEcoTourismType(WqsTravelReqParam param) {
		log.info("[getEcoTourismType] 请求参数:"+param.toString());
		String result = "";
		if(param.isValid()) {
			String page = param.getPage();
			String pageSize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pageSize)) {
				pageSize = "20";
			}
			
			Pagination typeList = this.businessSupportService.getWqsTravelService().getEcoTourismType(Integer.parseInt(page),Integer.parseInt(pageSize));
			if(typeList != null && typeList.size() != 0) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < typeList.size(); i++) {
						JSONObject json = new JSONObject();
						EcoTourismPictorialTypeModel vo = (EcoTourismPictorialTypeModel) typeList.get(i);
						json.put("id", vo.getId());
						json.put("title", vo.getTypeName());
						json.put("icon", this.PLAT_BASE_PATH + "/download" + vo.getIconPath());
						json.put("isSkip", vo.getIsSkip());
						if(! StringUtils.isEmpty(vo.getInfoHtml())) {
							json.put("desc", this.PLAT_BASE_PATH + "/download"+vo.getInfoHtml());
						}
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", typeList.getRecordCount());
					resultjson.put("curpage", typeList.getCurrentPage());
					resultjson.put("totalpage", typeList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				result = Consts.NO_RESULT_JSON;
			}
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getEcoTourismType]手机端返回结果result:" + result);
		
		return result;
		
	}
	
	//获取生态旅游
	public String getEcoTourism(WqsTravelReqParam param) {
		log.info("[getEcoTourism]  请求参数:" + param.toString());
		String result = "";
		if (param.isValid()) {
			String page = param.getPage();
			String pagesize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			String typeId = param.getTypeId();
			
			Pagination keyList = this.businessSupportService.getWqsTravelService().getEcoTourism(Integer.parseInt(page), Integer.parseInt(pagesize),Integer.parseInt(typeId));
			if ((keyList != null) && (keyList.size() > 0)) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < keyList.size(); i++) {
						JSONObject json = new JSONObject();
						EcoTourismPictorialModel vo = (EcoTourismPictorialModel) keyList.get(i);
						json.put("id", vo.getId());
						json.put("title", vo.getDescript());
						json.put("icon", this.PLAT_BASE_PATH + "/download" + vo.getFilePath());
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", keyList.getRecordCount());
					resultjson.put("curpage", keyList.getCurrentPage());
					resultjson.put("totalpage", keyList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result = Consts.NO_RESULT_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getEcoTourism]手机端返回结果result:" + result);
		
		return result;
		
	}
	
	//分页获取名镇信息
	public String getTownMessage(WqsTravelReqParam param) {
		log.info("[getTownMessage]  请求参数:" + param.toString());
		String result = "";
		if(param.isValid()) {
			String page = param.getPage();
			if(StringUtils.isEmpty(page)) {
				page = "1";
			}
			String pagesize= param.getPagesize();
			if(StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			Pagination list = this.businessSupportService.getWqsTravelService().getTownMessage(Integer.parseInt(page),Integer.parseInt(pagesize));
			if(list != null && list.size() != 0) {
				JSONObject resultJSON = new JSONObject();
				try {
					JSONArray jsonArray = new JSONArray();
					for (int i = 0; i < list.size(); i++) {
						JSONObject json = new JSONObject();
						TownMessageModel model = (TownMessageModel)list.get(i);
						json.put("id", model.getId());
						json.put("title", model.getTitle());
						json.put("unit", model.getUnit());
						json.put("time", model.getCreateTime());
						json.put("detail", PLAT_BASE_PATH+"/download"+model.getFileUrl());
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
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getTownMessage]手机端返回结果result:" + result);
		return result;
		
	}
	
	//获取美丽乡村(没有分页获取)
	public String getBeautifulCountry(WqsTravelReqParam param) {
		log.info("[getBeautifulCountry] 请求参数:"+param.toString());
		String result = "";
		if(param.isValid()) {
			List<?> list = this.businessSupportService.getWqsTravelService().getAllCountry();
			if(list != null && list.size() != 0) {
				JSONObject resultJSON = new JSONObject();
				try {
					JSONArray jsonArray = new JSONArray();
					for (int i = 0; i < list.size(); i++) {
						JSONObject json = new JSONObject();
						BeautifulCountryModel model = (BeautifulCountryModel)list.get(i);
						String str = "";
						JSONArray videoArray = null;
						List<?> videoList =this.businessSupportService.getWqsTravelService().getCountryVideo(model.getId());
						if(videoList != null && videoList.size() != 0) {
							try {
								videoArray = new JSONArray();
								for (int j = 0; j < videoList.size(); j++) {
									JSONObject videoJson = new JSONObject();
									BeautifulCountryVideoModel videoModel = (BeautifulCountryVideoModel) videoList.get(j);
									videoJson.put("icon",PLAT_BASE_PATH+"/download"+videoModel.getIconPath());
									videoJson.put("videoUrl",PLAT_BASE_PATH+"/download"+videoModel.getVideoPath());
									videoArray.put(videoJson);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if(! StringUtils.isEmpty(model.getIcon())) {
							String icon [] = model.getIcon().split(",");
							for (int j = 0; j < icon.length; j++) {
								if(icon.length ==1 ) {
									str = str + PLAT_BASE_PATH+"/download"+icon[j];
								}else {
									if(j != icon.length -1) {
										str = str + PLAT_BASE_PATH+"/download"+icon[j]+",";
									} else {
										str = str + PLAT_BASE_PATH+"/download"+icon[j];
									}
								}
							}
						}
							
						json.put("id", model.getId());
						json.put("name", model.getName());
						json.put("icon", str);
						json.put("desc", model.getDescript());
						json.put("detail", PLAT_BASE_PATH+"/download"+model.getFileUrl());
						json.put("videolist",videoArray );
						jsonArray.put(json);
					}
					resultJSON.put("resultlist", jsonArray);
					resultJSON.put("resultcount", list.size());
					resultJSON.put("curpage", "1");
					resultJSON.put("totalpage", "1");
					result = resultJSON.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			} else {
				result = Consts.NO_RESULT_JSON;
			}
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getBeautifulCountry]手机端返回结果result:" + result);
		return result;
	}

	//获取农特产品(数据量有限 没有分页获取)
	public String getAlgGoodsType(WqsTravelReqParam param) {
		log.info("[getAlgGoodsType] 请求参数:"+param.toString());
		String result = "";
		if(param.isValid()) {
			List<?> list = this.businessSupportService.getWqsTravelService().getAlgGoods();
			if(list != null && list.size() != 0) {
				JSONObject resultJSON = new JSONObject();
				try {
					JSONArray jsonArray = new JSONArray();
					for (int i = 0; i < list.size(); i++) {
						JSONObject json = new JSONObject();
						AlgGoodsModel model = (AlgGoodsModel)list.get(i);
					
						json.put("id", model.getId());
						json.put("title", model.getName());
						json.put("icon", PLAT_BASE_PATH+"/download"+model.getPreviewUrl());
						jsonArray.put(json);
					}
					resultJSON.put("resultlist", jsonArray);
					resultJSON.put("resultcount", list.size());
					resultJSON.put("curpage", "1");
					resultJSON.put("totalpage", "1");
					result = resultJSON.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			} else {
				result = Consts.NO_RESULT_JSON;
			}
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		
		log.info("[getAlgGoodsType]手机端返回结果result:" + result);
		return  result;
		
	}
	
	//获取农产品详情
	public String getAlgGoods(WqsTravelReqParam param) {
		
		log.info("[getAlgGoods] 请求参数:"+param.toString());
		String result = "";
		if(param.isValid()) {
			AlgGoodsModel model = this.businessSupportService.getWqsTravelService().getAlgGoodsById(param.getTypeId());
			if(model != null ) {
				String str = "";
				if(! StringUtils.isEmpty(model.getIcon())) {
					String icon[] = model.getIcon().split(",");
					for (int j = 0; j < icon.length; j++) {
						if(icon.length ==1 ) {
							str = str + PLAT_BASE_PATH+"/download"+icon[j];
						}else {
							if(j != icon.length -1) {
								str = str + PLAT_BASE_PATH+"/download"+icon[j]+",";
							} else {
								str = str + PLAT_BASE_PATH+"/download"+icon[j];
							}
						}
					}
				}
				
				JSONObject resultJSON = new JSONObject();
				try {
					resultJSON.put("id", model.getId());
					resultJSON.put("title", model.getName());
					resultJSON.put("desc", model.getDescript());
					resultJSON.put("pics", str);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				result = resultJSON.toString();
				
				
				
			} else {
				result = Consts.NO_RESULT_JSON;
			}
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		
		log.info("[getAlgGoods]手机端返回结果result:" + result);
		return  result;
		
	}
	
	//获取餐馆订单
	public String getRestaurantOrder(WqsTravelReqParam param) {
		log.info("[getRestaurantOrder] 请求参数:"+param.toString());
		String result = "";
		if(param.isValid()) {
			String phoneNumber = param.getPhoneNumber();
			List<?> list = this.businessSupportService.getWqsTravelService().getRestOrderByPhone(phoneNumber);
			if(list != null && list.size() != 0) {
				JSONObject resultJSON = new JSONObject();
				try {
					JSONArray jsonArray = new JSONArray();
					for (int i = 0; i < list.size(); i++) {
						JSONObject json = new JSONObject();
						RestaurantOrderModel model = (RestaurantOrderModel)list.get(i);
						String gender = "先生";
					    if("F".equalsIgnoreCase(model.getGender())) {
					    	gender = "女士";
					    }
						json.put("orderid", model.getId());
						json.put("name", model.getName() + gender);
						json.put("num", model.getNum());
						json.put("phoneNumber", model.getPhoneNumber());
						json.put("place", model.getPlace());
						json.put("time", model.getRestTime());
						jsonArray.put(json);
					}
					resultJSON.put("resultlist", jsonArray);
					resultJSON.put("resultcount", list.size());
					resultJSON.put("curpage", "1");
					resultJSON.put("totalpage", "1");
					result = resultJSON.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				 result = "{}";
			}
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		
		log.info("[getRestaurantOrder] 手机端返回结果result:" + result);
		return result;
	}
	
	//提交餐馆订单
	public String doRestaurantSubmit(RestOrderReqParam param) {
		String result = "" ;
    	log.info("[doRestaurantSubmit] 请求参数:"+param.toString());
    	if(param.isValid()) {
    		int d = this.businessSupportService.getWqsTravelService().doRestOrderSubmit(param);
    		if(d> 0) {
    			JSONObject resultjson = new JSONObject();
    			try {
    				RestaurantOrderModel model =  this.businessSupportService.getWqsTravelService().getRestOrderById(d);
					resultjson.put("errorcode", "00");
					String gender = "先生";
				    if("F".equalsIgnoreCase(model.getGender())) {
				    	gender = "女士";
				    }
					resultjson.put("orderid", model.getId());
					resultjson.put("name", model.getName()+gender);
					resultjson.put("num", model.getNum());
					resultjson.put("phoneNumber", model.getPhoneNumber());
					resultjson.put("place", model.getPlace());
					resultjson.put("time",model.getRestTime());
					result = resultjson.toString();
				} catch (JSONException e) {
					e.printStackTrace();
					log.debug(e.getMessage());
				}
    		}else { 
    			result = Consts.INVOKE_FAIL_JSON;
    		}
    	} else {
    		result = Consts.XML_ERROR_JSON;
    	}
    	log.info("[doRestaurantSubmit]返回给手机端结果result:"+result);
    	return result;
	}
	
	//获取酒店房间信息
	public String getRoomType(WqsTravelReqParam param) {
		log.info("[getRoomType] 请求参数:"+param.toString());
		String hotelId = param.getHotelid();
		String result = "";
		
		if(param.isValid()) {
			List<?> list = this.businessSupportService.getWqsTravelService().getHotelRoomType(hotelId);
			if(list != null && list.size() != 0) {
				JSONObject resultJSON = new JSONObject();
				try {
					JSONArray jsonArray = new JSONArray();
					for (int i = 0; i < list.size(); i++) {
						JSONObject json = new JSONObject();
						RoomTypeModel model = (RoomTypeModel)list.get(i);
					
						json.put("id", model.getId());
						json.put("name", model.getName());
						json.put("price", model.getPrice());
						jsonArray.put(json);
					}
					resultJSON.put("resultlist", jsonArray);
					resultJSON.put("resultcount", list.size());
					resultJSON.put("curpage", "1");
					resultJSON.put("totalpage", "1");
					result = resultJSON.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				result = Consts.NO_RESULT_JSON;
			}
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getRoomType]返回给手机端结果result:"+result);
		return result;
	}
	
	//提交酒店订单
	public String doHotelSubmit(HotelOrderReqParam param) {
		log.info("[doHotelSubmit] 请求参数:"+param.toString());
		String result = "";
		int d = this.businessSupportService.getWqsTravelService().doHotelOrderSubmit(param);
		if(d> 0) {
			JSONObject resultjson = new JSONObject();
			try {
				String type1 = param.getType1();
				String type2 = param.getType2();
				String type3 = param.getType3();
				String type4 = param.getType4();
				String type5 = param.getType5();
				String type6 = param.getType6();
				addDetail(type1,d);
				addDetail(type2,d);
				addDetail(type3,d);
				addDetail(type4,d);
				addDetail(type5,d);
				addDetail(type6,d);
				resultjson.put("errorcode", "00");
				HotelOrderModel model = this.businessSupportService.getWqsTravelService().getHotelOrderById(d);
				JSONArray videoArray = null;
				double totalPrice = 0;
				List<?> videoList =this.businessSupportService.getWqsTravelService().getHotelOrderDetail(model.getId());
				if(videoList != null && videoList.size() != 0) {
					try {
						videoArray = new JSONArray();
						
						for (int j = 0; j < videoList.size(); j++) {
							JSONObject videoJson = new JSONObject();
							HotelOrderDetailModel videoModel = (HotelOrderDetailModel) videoList.get(j);
							videoJson.put("id",videoModel.getId());
							videoJson.put("name",videoModel.getTypeName());
							videoJson.put("num",videoModel.getNum());
							totalPrice  = totalPrice + videoModel.getNum() * videoModel.getPrice();
							videoArray.put(videoJson);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				int days = model.getDays();
				if(days <1) {
					days = 1;
				}
				String gender = "先生";
			    if("F".equalsIgnoreCase(model.getGender())) {
			    	gender = "女士";
			    }
				double all = totalPrice * days;
				resultjson.put("id", model.getId());
				resultjson.put("name", model.getName()+gender);
				resultjson.put("phoneNumber", model.getPhoneNumber());
				resultjson.put("intime", model.getIntime());
				resultjson.put("outtime", model.getOuttime());
				resultjson.put("totalPrice", all);
				resultjson.put("roomlist",videoArray );
				
				
				result = resultjson.toString();
			} catch (JSONException e) {
				e.printStackTrace();
				log.debug(e.getMessage());
			}
		}else { 
			result = Consts.INVOKE_FAIL_JSON;
		}
		
		log.info("[doHotelSubmit]返回给手机端结果result:"+result);
		return result;
	}
	
	//获取酒店订单
	public String getHotelOrder(WqsTravelReqParam param) {
		log.info("[getHotelOrder] 请求参数:"+param.toString());
		String result = "";
		if(param.isValid()) {
			List<?> list = this.businessSupportService.getWqsTravelService().getHotelOrderByPhone(param);
			if(list != null && list.size() != 0) {
				JSONObject resultJSON = new JSONObject();
				try {
					JSONArray jsonArray = new JSONArray();
					for (int i = 0; i < list.size(); i++) {
						JSONObject json = new JSONObject();
						HotelOrderModel model = (HotelOrderModel)list.get(i);
						JSONArray videoArray = null;
						double totalPrice = 0;
						List<?> videoList =this.businessSupportService.getWqsTravelService().getHotelOrderDetail(model.getId());
						if(videoList != null && videoList.size() != 0) {
							try {
								videoArray = new JSONArray();
								
								for (int j = 0; j < videoList.size(); j++) {
									JSONObject videoJson = new JSONObject();
									HotelOrderDetailModel videoModel = (HotelOrderDetailModel) videoList.get(j);
									videoJson.put("id",videoModel.getId());
									videoJson.put("name",videoModel.getTypeName());
									videoJson.put("num",videoModel.getNum());
									totalPrice  = totalPrice + videoModel.getNum() * videoModel.getPrice();
									videoArray.put(videoJson);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						int days = model.getDays();
						if(days <1) {
							days = 1;
						}
						String gender = "先生";
					    if("F".equalsIgnoreCase(model.getGender())) {
					    	gender = "女士";
					    }
						double all = totalPrice * days;
						json.put("id", model.getId());
						json.put("name", model.getName()+gender);
						json.put("phoneNumber", model.getPhoneNumber());
						json.put("intime", model.getIntime());
						json.put("outtime", model.getOuttime());
						json.put("totalPrice", all);
						json.put("roomlist",videoArray );
						jsonArray.put(json);
					}
					resultJSON.put("resultlist", jsonArray);
					resultJSON.put("resultcount", list.size());
					resultJSON.put("curpage", "1");
					resultJSON.put("totalpage", "1");
					result = resultJSON.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			} else {
				result = "{}";
			}
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		
		log.info("[getHotelOrder] 手机端返回结果result:" + result);
		return result;
		
	}
	
	//取消餐馆订单
	public String doRestOrderCancel(WqsTravelReqParam param) {
		log.info("[doRestOrderCancel]  orderId="+param.getOrderid());
		String result = "";
		
		if(param.isValid()) {
    		int d = this.businessSupportService.getWqsTravelService().doRestOrderCancel(param.getOrderid());
    		if(d> 0) {
    			JSONObject resultjson = new JSONObject();
    			try {
					resultjson.put("errorcode", "00");
					result = resultjson.toString();
				} catch (JSONException e) {
					e.printStackTrace();
					log.debug(e.getMessage());
				}
    		}else { 
    			result = Consts.INVOKE_FAIL_JSON;
    		}
    	} else {
    		result = Consts.XML_ERROR_JSON;
    	}
		
		log.info("[doRestOrderCancel] 手机端返回结果result:" + result);
		return result;
	}
	
	//取消酒店订单
	public String doHotelOrderCancel(WqsTravelReqParam param) {
		log.info("[doHotelOrderCancel] orderId="+param.getOrderid());
		String result = "";
		if(param.isValid()) {
    		int d = this.businessSupportService.getWqsTravelService().doHotelOrderCancel(param.getOrderid());
    		if(d> 0) {
    			JSONObject resultjson = new JSONObject();
    			try {
					resultjson.put("errorcode", "00");
					result = resultjson.toString();
				} catch (JSONException e) {
					e.printStackTrace();
					log.debug(e.getMessage());
				}
    		}else { 
    			result = Consts.INVOKE_FAIL_JSON;
    		}
    	} else {
    		result = Consts.XML_ERROR_JSON;
    	}
		
		log.info("[doHotelOrderCancel] 手机端返回结果result:" + result);
		return result;
	}
	/**
	 * 增加订单细节
	 * @param type
	 * @param orderid
	 */
	private void addDetail(String type,int orderid) {
		if(! StringUtils.isEmpty(type)) {
			String typeid = type.split("[*]")[0];
			String num = type.split("[*]")[1];
			if(! num.equals("0")) {
				this.businessSupportService.getWqsTravelService().doHotelOrderDetailSave(typeid,num,orderid);
			}
		}
		
	}
	
	public String getHospitalService(WqsTravelReqParam param) {
		log.info("[getHospitalService]  请求参数:" + param.toString());
		String result = "";
		if (param.isValid()) {
			String page = param.getPage();
			String pagesize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			Pagination keyList = this.businessSupportService.getWqsTravelService().getHospitalList(page,pagesize);
			if ((keyList != null) && (keyList.size() > 0)) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < keyList.size(); i++) {
						JSONObject json = new JSONObject();
						HospitalModel vo = (HospitalModel) keyList.get(i);
						String telephone = vo.getTele1();
						if(! StringUtils.isEmpty(vo.getTele2())) {
							telephone += ","+vo.getTele2();
						}
						if(! StringUtils.isEmpty(vo.getTele3())) {
							telephone += ","+vo.getTele3();
						}
						json.put("id", vo.getId());
						json.put("name", vo.getName());
						json.put("addr",vo.getAddress() );
						json.put("tel",telephone);
						json.put("desc", this.PLAT_BASE_PATH + "/download" + vo.getHtmlFile());
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", keyList.getRecordCount());
					resultjson.put("curpage", keyList.getCurrentPage());
					resultjson.put("totalpage", keyList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result = Consts.NO_RESULT_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getHospitalService]手机端返回结果result:" + result);
		
		return result;
		
	}
	public String getEnterpriseList(WqsTravelReqParam param) {
		log.info("[getEnterpriseList]  请求参数:" + param.toString());
		String result = "";
		
		if (param.isValid()) {
			String page = param.getPage();
			String pagesize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			Pagination keyList = this.businessSupportService.getWqsTravelService().getEnterpriseList(page,pagesize);
			if ((keyList != null) && (keyList.size() > 0)) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < keyList.size(); i++) {
						JSONObject json = new JSONObject();
						EnterpriseModel vo = (EnterpriseModel) keyList.get(i);
						String str = "";
						if(! StringUtils.isEmpty(vo.getIcon())) {
							String icon[] = vo.getIcon().split(",");
							
							System.out.println(icon.length);
							for (int j = 0; j < icon.length; j++) {
								if(icon.length ==1 ) {
									str = str + PLAT_BASE_PATH+"/download"+icon[j];
								}else {
									if(j != icon.length -1) {
										str = str + PLAT_BASE_PATH+"/download"+icon[j]+",";
									} else {
										str = str + PLAT_BASE_PATH+"/download"+icon[j];
									}
								}
							}
						}
						
						json.put("id", vo.getId());
						json.put("name", vo.getName());
						json.put("icon",str);
						json.put("desc", this.PLAT_BASE_PATH + "/download" + vo.getHtmlPath());
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", keyList.getRecordCount());
					resultjson.put("curpage", keyList.getCurrentPage());
					resultjson.put("totalpage", keyList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result = Consts.NO_RESULT_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getEnterpriseList]手机端返回结果result:" + result);
		
		return result;
		
	}
	
	public String getWeather(WqsTravelReqParam param) {
		String result  = "";
		log.info("[getWeather] 请求参数:"+param.toString());
		try {
			JSONObject resultjson = new JSONObject();
			resultjson.put("weather", NsWeatherUtil.getWeather());
			result = resultjson.toString();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		log.info("[getWeather]手机端返回结果result:" + result);
		
		return result;
	}
	
	public String getHotelList(WqsTravelReqParam param) {
		log.info("[getHotelList] 请求参数:"+param.toString());
		String result  = "";
		if (param.isValid()) {
			String page = param.getPage();
			String pagesize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			Pagination keyList = this.businessSupportService.getWqsTravelService().getHotelList(page,pagesize);
			if ((keyList != null) && (keyList.size() > 0)) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < keyList.size(); i++) {
						JSONObject json = new JSONObject();
						HotelModel vo = (HotelModel) keyList.get(i);
						String str = "";
						List list = this.businessSupportService.getWqsTravelService().getHotelIconById(vo.getId());
						if(list != null ) {
							for (int j = 0; j < list.size(); j++) {
								IconMappingModel model = (IconMappingModel)list.get(j);
								if(list.size() ==1 ) {
									str = str + PLAT_BASE_PATH+"/download"+model.getPath();
								}else {
									if(j != list.size() -1) {
										str = str + PLAT_BASE_PATH+"/download"+model.getPath()+",";
									} else {
										str = str + PLAT_BASE_PATH+"/download"+model.getPath();
									}
								}
							}
						}
						json.put("id", vo.getId());
						json.put("name", vo.getName());
						json.put("addr",vo.getAddr());
						json.put("tel",vo.getTele());
						json.put("icons", str);
						json.put("desc", this.PLAT_BASE_PATH + "/download" + vo.getHtmlInfo());
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", keyList.getRecordCount());
					resultjson.put("curpage", keyList.getCurrentPage());
					resultjson.put("totalpage", keyList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result = Consts.NO_RESULT_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		
		
		
		log.info("[getHotelList]手机端返回结果result:" + result);
		
		return result;
	}
	
	public String getRestaurantList(WqsTravelReqParam param) {
		
		log.info("[getRestaurantList] 请求参数:"+param.toString());
		String result  = "";
		if (param.isValid()) {
			String page = param.getPage();
			String pagesize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			Pagination keyList = this.businessSupportService.getWqsTravelService().getRestaurantList(page,pagesize);
			if ((keyList != null) && (keyList.size() > 0)) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < keyList.size(); i++) {
						JSONObject json = new JSONObject();
						HotelModel vo = (HotelModel) keyList.get(i);
						String str = "";
						List list = this.businessSupportService.getWqsTravelService().getRestIconById(vo.getId());
						if(list != null ) {
							for (int j = 0; j < list.size(); j++) {
								IconMappingModel model = (IconMappingModel)list.get(j);
								if(list.size() ==1 ) {
									str = str + PLAT_BASE_PATH+"/download"+model.getPath();
								}else {
									if(j != list.size() -1) {
										str = str + PLAT_BASE_PATH+"/download"+model.getPath()+",";
									} else {
										str = str + PLAT_BASE_PATH+"/download"+model.getPath();
									}
								}
							}
						}
						json.put("id", vo.getId());
						json.put("name", vo.getName());
						json.put("addr",vo.getAddr());
						json.put("tel",vo.getTele());
						json.put("icons", str);
						json.put("desc", this.PLAT_BASE_PATH + "/download" + vo.getHtmlInfo());
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", keyList.getRecordCount());
					resultjson.put("curpage", keyList.getCurrentPage());
					resultjson.put("totalpage", keyList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result = Consts.NO_RESULT_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		
		
		
		log.info("[getRestaurantList]手机端返回结果result:" + result);
		
		return result;
	}
	
	public String checkVideoPassword(WqsTravelReqParam param) {
		log.info("[getRestaurantList] 请求参数:"+param.getKey());
		String result  = "";
		if(param.isValid()) {
			String password = MobileGlobals.getProperty("password");
			String key = MD5.crypt(password.trim() + "wanqingsha");
			JSONObject resultjson = new JSONObject();
				try {
					resultjson.put("errorcode", "00");
					if(key.equalsIgnoreCase(param.getKey())) {
						resultjson.put("result", "success");
					}else {
						resultjson.put("result", "failed");
					}
					result = resultjson.toString();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
    	} else {
    		result = Consts.XML_ERROR_JSON;
    	}
		
		log.info("[getRestaurantList]手机端返回结果result:" + result);
	
		return result;
	}
	
	public String getNongJiaLe(WqsTravelReqParam param) {
		
		log.info("[getNongJiaLe] 请求参数:"+param.toString());
		String result  = "";
		if (param.isValid()) {
			String page = param.getPage();
			String typeid = param.getTypeId();
			String pagesize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			if (StringUtils.isEmpty(param.getTypeId())) {
				typeid = "0";
			}
			Pagination keyList = this.businessSupportService.getWqsTravelService().getNongJiaLe(page,pagesize,typeid);
			if ((keyList != null) && (keyList.size() > 0)) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < keyList.size(); i++) {
						JSONObject json = new JSONObject();
						HotelModel vo = (HotelModel) keyList.get(i);
						
						json.put("id", vo.getId());
						json.put("name", vo.getName());
						json.put("addr",vo.getAddr());
						json.put("tel",vo.getTele());
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", keyList.getRecordCount());
					resultjson.put("curpage", keyList.getCurrentPage());
					resultjson.put("totalpage", keyList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result = Consts.NO_RESULT_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		
		
		
		log.info("[getNongJiaLe]手机端返回结果result:" + result);
		
		return result;
	}
	
	public String getYujiaInfo(WqsTravelReqParam param) {
		
		log.info("[getYujiaInfo] 请求参数:"+param.toString());
		String result  = "";
		if (param.isValid()) {
			String page = param.getPage();
			String pagesize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			List keyList = this.businessSupportService.getWqsTravelService().getYujiaInfo(page,pagesize);
			if ((keyList != null) && (keyList.size() > 0)) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < keyList.size(); i++) {
						JSONObject json = new JSONObject();
						YujialeModel vo = (YujialeModel) keyList.get(i);
						String str = "";
						if(! StringUtils.isEmpty(vo.getIcons())) {
							String [] list = vo.getIcons().split(",");
							if(list != null ) {
								for (int j = 0; j < list.length; j++) {
									if(list.length ==1 ) {
										str = str + PLAT_BASE_PATH+"/download"+list[j];
									}else {
										if(j != list.length -1) {
											str = str + PLAT_BASE_PATH+"/download"+list[j]+",";
										} else {
											str = str + PLAT_BASE_PATH+"/download"+list[j];
										}
									}
								}
							}
						}
						json.put("id", vo.getId());
						json.put("name", vo.getName());
						json.put("icons", str);
						json.put("desc", this.PLAT_BASE_PATH + "/download" + vo.getDesc());
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", keyList.size());
					resultjson.put("curpage", "1");
					resultjson.put("totalpage","1");
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result = Consts.NO_RESULT_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getYujiaInfo]手机端返回结果result:" + result);
		
		return result;
	}
	
	public String getYujiaList(WqsTravelReqParam param) {
		if(StringUtils.isEmpty(param.getTypeId())) {
			param.setTypeId("1");
		}
		return getNongJiaLe(param);
	}
	
	
	//分页获取名镇新闻
	public String getTownNews(WqsTravelReqParam param) {
		log.info("[getTownNews]  请求参数:" + param.toString());
		String result = "";
		if(param.isValid()) {
			String page = param.getPage();
			if(StringUtils.isEmpty(page)) {
				page = "1";
			}
			String pagesize= param.getPagesize();
			if(StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			Pagination list = this.businessSupportService.getWqsTravelService().getTownNews(Integer.parseInt(page),Integer.parseInt(pagesize),param.getTypeId());
			if(list != null && list.size() != 0) {
				JSONObject resultJSON = new JSONObject();
				try {
					JSONArray jsonArray = new JSONArray();
					for (int i = 0; i < list.size(); i++) {
						JSONObject json = new JSONObject();
						TownMessageModel model = (TownMessageModel)list.get(i);
						json.put("id", model.getId());
						json.put("title", model.getTitle());
						json.put("unit", model.getUnit());
						json.put("time", model.getCreateTime());
						json.put("detail", PLAT_BASE_PATH+"/download"+model.getFileUrl());
						if("3".equals(param.getTypeId())){
							json.put("detail", model.getFileUrl());
							
						}
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
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getTownNews]手机端返回结果result:" + result);
		return result;
		
	}
	
	//分页获取招聘信息
	public String getTownJobs(WqsTravelReqParam param) {
		log.info("[getTownJobs]  请求参数:" + param.toString());
		String result = "";
		if(param.isValid()) {
			String page = param.getPage();
			if(StringUtils.isEmpty(page)) {
				page = "1";
			}
			String pagesize= param.getPagesize();
			if(StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			Pagination list = this.businessSupportService.getWqsTravelService().getTownJobsList(Integer.parseInt(page),Integer.parseInt(pagesize));
			if(list != null && list.size() != 0) {
				JSONObject resultJSON = new JSONObject();
				try {
					JSONArray jsonArray = new JSONArray();
					for (int i = 0; i < list.size(); i++) {
						JSONObject json = new JSONObject();
						TownJobsModel model = (TownJobsModel)list.get(i);
						json.put("id", model.getId());
						json.put("title", model.getTitle());
						json.put("unit", model.getUnit());
						json.put("time", model.getCreateTime());
						json.put("detail", PLAT_BASE_PATH+"/download"+model.getJobUrl());
						json.put("contacter", model.getContactUser());
						json.put("tel", model.getContactPhone());
						json.put("comDesc", PLAT_BASE_PATH+"/download"+model.getInfoUrl());
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
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getTownJobs]手机端返回结果result:" + result);
		return result;
		
	}
	
	//分页获取招聘信息
	public String getTownTraffic(WqsTravelReqParam param) {
		log.info("[getTownTraffic]  请求参数:" + param.toString());
		String result = "";
		if(param.isValid()) {
			String page = param.getPage();
			if(StringUtils.isEmpty(page)) {
				page = "1";
			}
			String pagesize= param.getPagesize();
			if(StringUtils.isEmpty(pagesize)) {
				pagesize = "20";
			}
			Pagination list = this.businessSupportService.getWqsTravelService().getTownTraffic(Integer.parseInt(page),Integer.parseInt(pagesize));
			if(list != null && list.size() != 0) {
				JSONObject resultJSON = new JSONObject();
				try {
					JSONArray jsonArray = new JSONArray();
					for (int i = 0; i < list.size(); i++) {
						JSONObject json = new JSONObject();
						TownMessageModel model = (TownMessageModel)list.get(i);
						json.put("id", model.getId());
						json.put("title", model.getTitle());
//						json.put("unit", model.getUnit());
//						json.put("time", model.getCreateTime());
						json.put("detail", PLAT_BASE_PATH+"/download"+model.getFileUrl());
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
			
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getTownTraffic]手机端返回结果result:" + result);
		return result;
		
	}
	
	public String getFeedbackList(WqsTravelReqParam param) {
		String result = "";
		log.info("[getFeedbackList]请求参数:" + param.toString());

		if (param.isValid()) {
			String page = param.getPage();
			String pagesize = param.getPagesize();
			if (StringUtils.isEmpty(page)) {
				param.setPage("1");
			}
			if (StringUtils.isEmpty(pagesize)) {
				param.setPagesize("10");
			}
			Pagination keyList = this.businessSupportService.getWqsTravelService().getFeedbackList(param);
			if ((keyList != null) && (keyList.size() > 0)) {
				JSONObject resultjson = new JSONObject();
				try {
					JSONArray jsonarr = new JSONArray();
					for (int i = 0; i < keyList.size(); i++) {
						JSONObject json = new JSONObject();
						NsFeedbackModel vo = (NsFeedbackModel) keyList.get(i);
						json.put("id", vo.getId());
						json.put("content", vo.getContent());
						json.put("phoneNumber", vo.getPhone());
						json.put("operator", vo.getOperatorName());
						json.put("time", vo.getSubmitTime());
						List replyList = this.businessSupportService.getWqsTravelService().getFeedbackReplyList(vo.getId() + "");
						if ((replyList != null) && (replyList.size() > 0)) {
							JSONArray replyArr = new JSONArray();
							for(int j =0;j<replyList.size();j ++) {
								JSONObject replyObject = new JSONObject();
								NsFeedbackReplyModel model = (NsFeedbackReplyModel)replyList.get(j);
								replyObject.put("replyName", model.getReplyName());
								replyObject.put("replyContent", model.getContent());
								replyObject.put("replyTime", model.getReplyTime());
								replyArr.put(replyObject);
							}
							json.put("replyList", replyArr);
						} else { //暂时没有回复
							json.put("replyList", new JSONArray());
						}
						jsonarr.put(json);
					}
					resultjson.put("resultlist", jsonarr);
					resultjson.put("resultcount", keyList.getRecordCount());
					resultjson.put("curpage", keyList.getCurrentPage());
					resultjson.put("totalpage", keyList.getPageCount());
					result = resultjson.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result = Consts.NO_RESULT_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.info("[getFeedbackList]手机端返回结果result:" + result);
		return result;
	}
	
	public String doFeedbackCommit(NsTravelFeedbackReqParam param) {
		String result = "" ;
    	log.info("[doFeedbackSubmit...请求参数]"+param.toString());
    	if(param.isValid()) {
    		NsFeedbackModel model = new NsFeedbackModel();
    		model.setOperatorName(param.getOperator());
    		model.setContent(param.getContent());
    		model.setPhone(param.getPhoneNumber());
    		int d = this.businessSupportService.getWqsTravelService().doFeedbackSave(model);
    		if(d> 0) {
    			JSONObject resultjson = new JSONObject();
    			try {
					resultjson.put("errorcode", "00");
					result = resultjson.toString();
				} catch (JSONException e) {
					e.printStackTrace();
					log.debug(e.getMessage());
				}
    		}else { 
    			result = Consts.INVOKE_FAIL_JSON;
    		}
    	} else {
    		result = Consts.XML_ERROR_JSON;
    	}
    	log.info("[doFeedbackSubmit]返回给手机端结果result:"+result);
    	return result;
	}
	
	
	
	
	
}


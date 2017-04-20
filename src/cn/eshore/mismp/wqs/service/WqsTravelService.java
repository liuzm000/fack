package cn.eshore.mismp.wqs.service;

import java.util.List;

import cn.eshore.mismp.util.Pagination;
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
import cn.eshore.mismp.wqs.model.NongJiaLeModel;
import cn.eshore.mismp.wqs.model.NsFeedbackModel;
import cn.eshore.mismp.wqs.model.NsFeedbackReplyModel;
import cn.eshore.mismp.wqs.model.RestOrderReqParam;
import cn.eshore.mismp.wqs.model.RestaurantOrderModel;
import cn.eshore.mismp.wqs.model.RoomTypeModel;
import cn.eshore.mismp.wqs.model.ShaTianPictorialModel;
import cn.eshore.mismp.wqs.model.ShaTianPictorialTypeModel;
import cn.eshore.mismp.wqs.model.TownJobsModel;
import cn.eshore.mismp.wqs.model.TownMessageModel;
import cn.eshore.mismp.wqs.model.TownVideoModel;
import cn.eshore.mismp.wqs.model.TrafficReguModel;
import cn.eshore.mismp.wqs.model.WqsTravelReqParam;
import cn.eshore.mismp.wqs.model.YujialeModel;

/**
 * <p> 万顷沙业务逻辑处理 <p>
 * <p> @author jianghuan <p>
 * <p> 时间：2012-12-18下午03:28:00<p>
 * <p> CopyRight 2012 <p>
 *  DML语句操作请用 doXXX 查询语句get
 */
public interface WqsTravelService {
	/** 农家乐和渔家乐*/
	Pagination getNongJiaLeList(String type,String searchName,String searchAdd,int pageNum,int pageSize);
	int doNongJiaLeAdd(NongJiaLeModel model);
	int doNongJiaLeEdit(NongJiaLeModel model);
	int doNongJiaLeDelete(int id);
	NongJiaLeModel getNongJiaLe(int id);
	
	
	
	
	
	
	
	
	/*生态旅游列表*/
	Pagination getEcotourismTypeList(String searchName,int pageNum,int pageSize);
	
	public int doEcotourismTypeSave(EcoTourismPictorialTypeModel model);//保存生态旅游类型
	public int doEcotourismPictureSave(EcoTourismPictorialModel model);//保存生态旅游图片
	
	
	public EcoTourismPictorialTypeModel getEcoTourismPictorialTypeModel(long typeId);
	public List<EcoTourismPictorialModel> getEcoTourismPictorialModelList(long typeId);
	public int doEcoTourismPictorialTypeModelDel(long typeId);
	public int doEcoTourismPictorialModelDel(long typeId);
	
	public int doEcotourismTypeUpdate(EcoTourismPictorialTypeModel model);//更新生态旅游类型
	
	
	public EcoTourismPictorialModel getEcoTourismPictorialModel(int id);
	public int doEcoTourismPictorialModelDelete(int id);
	public int doEcotourismPictureUpdate(EcoTourismPictorialModel model);
	
	
	/*获取乡村集合*/
	Pagination getBeautifulCountryList(String countryName,int pageNum,int pageSize);
	
	/*乡村保存*/
	public int doBeautifulCountrySave(BeautifulCountryModel model);
	public int doBeautifulCountryVideoSave(BeautifulCountryVideoModel model);
	
	/*获取乡村信息，并删除*/
	public BeautifulCountryModel getBeautifulCountry(long countryId);
	public List<BeautifulCountryVideoModel> getBeautifulCountryVideoList(long countryId);
	public int doBeautifulCountryDel(long countryId);
	public int doBeautifulCountryVideoDel(long countryId);
	
	public BeautifulCountryModel getBeautifulCountryAndVideo(long countryId);
	
	
	public int doBeautifulCountryUpdate(BeautifulCountryModel model);
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 获取沙田画卷分类
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Pagination getPictorialType(int pageNum,int pageSize);
	Pagination getPictorialTypeAndPics(String searchName,int pageNum,int pageSize);
	/**
	 * 分类获取沙田画卷
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Pagination  getShaTianPictorial(int pageNum,int pageSize,int typeId);
	/**
	 * 增加沙田画卷
	 * @return
	 */
	int doShaTianPictorialSave(ShaTianPictorialTypeModel param);
	int doShaTianPictorialListSave(ShaTianPictorialModel param);
	
	int doShaTianPictorialUpdate(int typeId,String name,String desc);
	
	int doShaTianPictorialDelete(int id);//删除画卷类型
	ShaTianPictorialTypeModel getShaTianPictorial(int id);//获取画卷类型
	
	int doShaTianPictorialListDelete(int typeId);//删除画卷类型下的画卷列表
	List<ShaTianPictorialModel> getShaTianPictorialList(int typeId);//获取画卷类型下的画卷列表
	
	
	int doShaTianOnePictorialDelete(int id);
	ShaTianPictorialModel getShaTianOnePictorialDelete(int id);
	int doShaTianOnePictorialUpdate(ShaTianPictorialModel model);
	
	
	
	/**
	 * 分页获取名镇视频
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Pagination getTownVideo(int pageNum,int pageSize);
	/**
	 * 获取生态旅游图片类型
	 * @param parseInt
	 * @param parseInt2
	 * @return
	 */
	Pagination getEcoTourismType(int parseInt, int parseInt2);
	/**
	 * 获取生态旅游图片
	 * @param parseInt
	 * @param parseInt2
	 * @param parseInt3
	 * @return
	 */
	Pagination getEcoTourism(int parseInt, int parseInt2, int parseInt3);
	/**
	 *  添加名镇视频
	 * @param videoModel
	 * @return
	 */
	int doTownVideoSave(TownVideoModel videoModel);
	/**
	 *  获取ID名镇视频
	 * @param id 视频id 
	 * @return
	 */
	TownVideoModel getTownVideoById(String id);
	/**
	 * 更新名镇视频
	 * @param videoModel
	 * @return
	 */
	int doTownVideoUpdate(TownVideoModel videoModel);
	/**
	 * 删除名镇视频
	 * @param id
	 * @return
	 */
	int doTownVideoDel(String id);
	
	/**
	 * 分页获取名镇信息
	 * @param page
	 * @param pagesize
	 * @return
	 */
	Pagination getTownMessage(int page, int pagesize);
	/**
	 * 获取美丽乡村
	 * @return
	 */
	List getAllCountry();
	/**
	 * 根据乡村ID 获取视频
	 * @param videoId
	 */
	List getCountryVideo(long videoId);
	/**
	 * 获取农特产品
	 * @return
	 */
	List getAlgGoods();
	/**
	 * 分页获取农特产品
	 * @param page
	 * @param pagesize
	 * @return
	 */
	Pagination getAlgGoods(int page, int pagesize);
	Pagination getAlgGoods(String searchName,int page, int pagesize);
	/**
	 * 根据ID 获取农特产品
	 * @param typeId
	 * @return
	 */
	AlgGoodsModel getAlgGoodsById(String typeId);
	/**
	 * 保存农特产品
	 * @param algGoodsModel
	 * @return
	 */
	int doAlgGoodsSave(AlgGoodsModel algGoodsModel);
	int doAlgGoodsUpdate(AlgGoodsModel algGoodsModel);
	
	int doAlgGoodsDelete(String id);	
	
	/**
	 * 获取餐馆订单列表
	 * @param phoneNumber
	 * @return
	 */
	List<?> getRestOrderByPhone(String phoneNumber);
	/**
	 * 提交餐馆订单
	 * @param param
	 * @return
	 */
	int doRestOrderSubmit(RestOrderReqParam param);
	/**
	 * 获取酒店房间类别
	 * @param hotelId
	 * @return
	 */
	List<?> getHotelRoomType(String hotelId);
	/**
	 * 保存酒店房间类别
	 * @param roomTypeModel
	 * @return
	 */
	int doHotelRoomTypeSave(RoomTypeModel roomTypeModel);
	/**
	 * 删除酒店房间类别
	 * @param HotelId
	 * @return
	 */
	int doHotelRoomTypeDeleteByHotelId(String hotelId);
	/**
	 * 提交酒店订单
	 * @param param
	 * @return
	 */
	int doHotelOrderSubmit(HotelOrderReqParam param);
	/**
	 *  保存订单细节
	 * @param typeid
	 * @param num
	 * @param orderid
	 */
	int doHotelOrderDetailSave(String typeid, String num, int orderid);
	/**
	 * 获取酒店订单接口
	 * @param param
	 * @return
	 */
	List<?> getHotelOrderByPhone(WqsTravelReqParam param);
	/**
	 * 获取订单细节表
	 * @param id
	 * @return
	 */
	List<?> getHotelOrderDetail(long id);
	/**
	 * 取消餐馆订单
	 * @param orderid
	 * @return
	 */
	int doRestOrderCancel(String orderid);
	/**
	 * 取消酒店订单
	 * @param orderid
	 * @return
	 */
	int doHotelOrderCancel(String orderid);
	/**
	 *  获取名镇信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	
	
	Pagination getTownMsgList(int pageNum, int pageSize);
	/**
	 * 获取医院信息
	 * @param page
	 * @param pagesize
	 * @return
	 */
	Pagination getHospitalList(String page, String pagesize);
	Pagination getHospitalList(String searchName,String searchAdd,String page, String pagesize);
	int doHospitalSave(HospitalModel msgModel);	
	int doHospitalDelete(String id);	
	HospitalModel getHospitalById(int id);	
	int doHospitalUpdate(HospitalModel msgModel);	
	
	Pagination getTrafficReguList(String page, String pagesize);
	Pagination getTrafficReguList(String searchName,String searchUnit,String page, String pagesize);
	int doTrafficReguSave(TrafficReguModel msgModel);	
	int doTrafficReguDelete(String id);	
	int doTrafficReguUpdate(TrafficReguModel model);	
	TrafficReguModel getTrafficReguModel(int id);	
	
	/**
	 * 根据ID获取餐馆订单
	 * @param d
	 * @return
	 */
	RestaurantOrderModel getRestOrderById(int d);
	/**
	 * 根据ID获取酒店订单
	 * @param d
	 * @return
	 */
	HotelOrderModel getHotelOrderById(int d);
	/**
	 *  分页获取企业名录
	 * @param page
	 * @param pagesize
	 * @return
	 */
	Pagination getEnterpriseList(String page, String pagesize);
	/**
	 *  分页获取酒店信息
	 * @param page
	 * @param pagesize
	 * @return
	 */
	Pagination getHotelList(String page, String pagesize);
	Pagination getHotelList(String searchName,String searchAdd,String page, String pagesize);
	Pagination getRestaurantList(String searchName,String searchAdd,String page, String pagesize);
	/**
	 *  根据Id获取酒店信息
	 * @param id
	 * @return
	 */
	HotelModel getHotelById(int id);
	HotelModel getRestaurantById(int id);
	/**
	 * 保存酒店信息
	 * @param hotelModel
	 * @return
	 */
	int doHotelSave(HotelModel hotelModel);
	int doHotelUpdate(HotelModel hotelModel);
	int doRestaurantSave(HotelModel hotelModel);
	int doRestaurantUpdate(HotelModel hotelModel);
	/**
	 * 删除酒店信息
	 * @param id
	 * @return
	 */
	int doHotelDelete(String id);	
	int doRestaurantDelete(int id);	
	/**
	 * 获取酒店所有图片
	 * @param id
	 * @return
	 */
	List getHotelIconById(long id);
	List getRestaurantIconById(long id);
	/**
	 * 保存酒店图片
	 * @param iconMapping
	 * @return
	 */
	int doHotelIconSave(IconMappingModel iconMapping);
	int doRestaurantIconSave(IconMappingModel iconMapping);
	/**
	 * 删除酒店图片
	 * @param hotelId
	 * @return
	 */
	int doHotelIconDeleteByHotelId(String hotelId);	
	int doRestaurantIconDeleteByHotelId(int hotelId);	
	/**
	 *  分页获取餐馆信息
	 * @param page
	 * @param pagesize
	 * @return
	 */
	Pagination getRestaurantList(String page, String pagesize);
	/**
	 *  获取餐馆所有图片
	 * @param id
	 * @return
	 */
	List getRestIconById(long id);
	/**
	 * 获取农家乐信息
	 * @param page
	 * @param pagesize
	 * @param typeid 0代表农家乐，1代表渔家乐, -1代表所有
	 * @return
	 */
	Pagination getNongJiaLe(String page, String pagesize,String typeid);
	/**
	 * 获取渔家乐介绍
	 * @param page
	 * @param pagesize
	 * @return
	 */
	List getYujiaInfo(String page, String pagesize);
	YujialeModel getYujiaInfoById(int id);
	int  doYujiaInfoUpdate(YujialeModel model);
	/**
	 *  名镇视频 分页查询
	 * @param pageNum 页码
	 * @param pageSize 每页记录数
	 * @param videoModel 
	 * @return
	 */
	Pagination getTownVideoQuery(int pageNum, int pageSize,
			TownVideoModel videoModel);
	/**
	 * 根据ID获取名镇信息
	 * @param id 信息id
	 * @return
	 */
	TownMessageModel getTownMsgById(String id);
	/**
	 * 更新名镇信息
	 * @param msgModel
	 * @return
	 */
	int doTownMsgUpdate(TownMessageModel msgModel);
	/**
	 * 添加名镇信息
	 * @param msgModel
	 * @return
	 */
	int doTownMsgSave(TownMessageModel msgModel);
	/**
	 * 删除名镇信息
	 * @param id
	 * @return
	 */
	int doTownMsgDelete(String id);
	/**
	 * 查询名镇信息
	 * @param pageNum
	 * @param pageSize
	 * @param msgModel  
	 * @return
	 */
	Pagination getTownMsgQuery(int pageNum, int pageSize, TownMessageModel msgModel);
	/**
	 * 获取南沙新闻
	 * @param pageNum
	 * @param pageSize
	 * @param typeId
	 * @return
	 */
	Pagination getTownNews(int pageNum, int pageSize, String typeId);
	Pagination getTownNews(String searchName,String searchUnit,int pageNum, int pageSize, String typeId);
	/**
	 * 获取交通
	 * @param parseInt
	 * @param parseInt2
	 * @return
	 */
	Pagination getTownTraffic(int pageNum, int pageSize);
	/**
	 * 查询新闻
	 * @param pageNum
	 * @param pageSize
	 * @param msgModel
	 * @return
	 */
	Pagination getTownNewsQuery(int pageNum, int pageSize,
			TownMessageModel msgModel);
	/**
	 * 根据id查询新闻
	 * @param id
	 * @return
	 */
	TownMessageModel getTownNewsById(String id);
	/**
	 * 更新新闻
	 * @param msgModel
	 * @return
	 */
	int doTownNewsUpdate(TownMessageModel msgModel);
	/**
	 * 增加新闻
	 * @param msgModel
	 * @return
	 */
	int doTownNewsSave(TownMessageModel msgModel);
	/**
	 * 删除新闻
	 * @param id
	 * @return
	 */
	int doTownNewsDelete(String id);
	/**
	 * 获取反馈列表
	 * @param param
	 * @return
	 */
	Pagination getFeedbackList(WqsTravelReqParam param);
	/**
	 * 获取反馈回复列表
	 * @param string
	 * @return
	 */
	List getFeedbackReplyList(String string);
	/**
	 * 提交回复
	 * @param model
	 * @return
	 */
	int doFeedbackSave(NsFeedbackModel model);
	
	/**
	 *  分页获取反馈信息列表(后台)
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public abstract Pagination getFeedbackList(String pageNum,String pageSize);
	
	/**
	 * 分页获取反馈信息列表(后台请求)
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public abstract Pagination getFeedbackList(int pageNum, int pageSize);
	/**
	 *  根据反馈ID 获取反馈信息
	 * @param id
	 * @return
	 */
	public abstract NsFeedbackModel getFeedbackById(String id);
	
	/**
	 * 增加回复
	 * @param replyModel
	 * @return
	 */
	public abstract int doFeedbackReplySave(NsFeedbackReplyModel replyModel);
	/** 
	 * 更改反馈信息表状态
	 * @param id
	 * @param string 0代表未回复 1代表已回复
	 */
	public abstract int updateFeedbackStatus(long id, String string);

	/**
	 * 删除回复信息
	 * @param replyId
	 * @return
	 */
	public abstract int doFeedbackReplyDelete(String replyId);
	/**
	 * 删除反馈信息
	 * @param feedbackId
	 * @return
	 */
	public abstract int doFeedbackDelete(String feedbackId);
	/**
	 * 获取企业名录列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Pagination getTownEntList(int pageNum, int pageSize);
	Pagination getTownEntList(String searchName,int pageNum, int pageSize);
	/**
	 * 删除企业名录
	 * @param id
	 * @return
	 */
	int doTownEntDelete(String id);
	/**
	 * 添加企业名录信息
	 * @param entModel
	 * @return
	 */
	int doTownEntSave(EnterpriseModel entModel);
	
	EnterpriseModel getEnterpriseModel(long townId);
	int doEnterpriseModelUpdate(EnterpriseModel model);
	
	/**
	 * 更新/上传插图信息
	 * @param icon
	 * @return
	 */
	int updateEntIcon(String icon,String id);
	/**
	 * 酒店订单列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Pagination getHotelOrderList(int pageNum, int pageSize);
	Pagination getHotelOrderList(String searchName,String hotelId,int pageNum, int pageSize);
	/**
	 * 获取订单详情
	 * @param id
	 * @return
	 */
	List<HotelOrderDetailModel> getHotelOrderDetailList(long id);
	/**
	 * 获取餐饮订单详情
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Pagination getFoodOrderList(int pageNum, int pageSize);
	Pagination getFoodOrderList(String searchName,String hotelId,int pageNum, int pageSize);
	/**
	 * 获取招聘信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Pagination getTownJobsList(int pageNum, int pageSize);
	Pagination getTownJobsList(String searchName,String searchUnit,int pageNum, int pageSize);
	/**
	 * 添加招聘信息
	 * @param jobModel
	 * @return
	 */
	int doTownJobsSave(TownJobsModel jobModel);
	/**
	 * 删除招聘信息
	 * @param id
	 * @return
	 */
	int doTownJobsDelete(String id);
	/**
	 * 修改招聘信息
	 * @param jobModel
	 * @return
	 */
	int doTownJobsUpdate(TownJobsModel jobModel);
	/**
	 * 根据id获取招聘信息
	 * @param id
	 * @return
	 */
	TownJobsModel getTownJobsById(String id);	
	/**
	 * type=1为酒店订单管理员
	 * type=2为餐馆订单管理员
	 * @param type
	 * @return
	 */
	public List<HotelModel> getHotelRestaurantList(int type);
	
}

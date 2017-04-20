package cn.eshore.mismp.wqs.dao;

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
 * <p>万顷沙数据库访问 <p>
 * <p> @author jianghuan <p>
 * <p> 时间：2012-12-18下午03:30:21<p>
 * <p> CopyRight 2012 <p>
 */
public interface WqsTravelDAO {

	/** 农家乐和渔家乐*/
	Pagination getNongJiaLeList(String type,String searchName,String searchAdd,int pageNum,int pageSize);
	int doNongJiaLeAdd(NongJiaLeModel model);
	int doNongJiaLeEdit(NongJiaLeModel model);
	int doNongJiaLeDelete(int id);
	NongJiaLeModel getNongJiaLe(int id);
	/*生态旅游*/
	Pagination getEcotourismTypeList(String searchName,int pageNum,int pageSize);
	public int doEcotourismPictureUpdate(EcoTourismPictorialModel model);
	
	public int doEcotourismTypeSave(EcoTourismPictorialTypeModel model);//保存生态旅游类型
	public int doEcotourismPictureSave(EcoTourismPictorialModel model);//保存生态旅游图片
	public EcoTourismPictorialModel getEcoTourismPictorialModel(int id);
	public int doEcoTourismPictorialModelDelete(int id);
	
	public EcoTourismPictorialTypeModel getEcoTourismPictorialTypeModel(long typeId);
	public List<EcoTourismPictorialModel> getEcoTourismPictorialModelList(long typeId);
	public int doEcoTourismPictorialTypeModelDel(long typeId);
	public int doEcoTourismPictorialModelDel(long typeId);
	
	public int doEcotourismTypeUpdate(EcoTourismPictorialTypeModel model);//更新生态旅游类型
	
	
	
	/*获取乡村集合*/
	Pagination getBeautifulCountryList(String countryName,int pageNum,int pageSize);
	public int doBeautifulCountrySave(BeautifulCountryModel model);
	public int doBeautifulCountryVideoSave(BeautifulCountryVideoModel model);
	
	/*获取乡村信息，并删除*/
	public BeautifulCountryModel getBeautifulCountry(long countryId);
	public List<BeautifulCountryVideoModel> getBeautifulCountryVideoList(long countryId);
	public int doBeautifulCountryDel(long countryId);
	public int doBeautifulCountryVideoDel(long countryId);
	
	public BeautifulCountryModel getBeautifulCountryAndVideo(long countryId);
	
	
	
	public int doBeautifulCountryUpdate(BeautifulCountryModel model);
	
	public List<HotelModel> getHotelRestaurantList(int type);
	
	
	public int doShaTianPictorialSave(ShaTianPictorialTypeModel param);
	public int doShaTianPictorialListSave(ShaTianPictorialModel param);
	
	
	
	int doShaTianPictorialDelete(int id);//删除画卷类型
	ShaTianPictorialTypeModel getShaTianPictorial(int id);//获取画卷类型
	
	//删除画卷类型下的画卷列表
	public int doShaTianPictorialListDelete(int typeId);
	
	//获取画卷类型下的画卷列表
	public List<ShaTianPictorialModel> getShaTianPictorialList(int typeId);
	
	
	int doShaTianOnePictorialDelete(int id);
	ShaTianPictorialModel getShaTianOnePictorialDelete(int id);
	
	
	
	
	
	
	
	int doShaTianPictorialUpdate(int typeId,String name,String desc);
	public int doShaTianOnePictorialUpdate(ShaTianPictorialModel model);
	
	
	Pagination getPictorialType(int pageNum, int pageSize);
	Pagination getPictorialTypeAndPics(String searchName,int pageNum, int pageSize);

	Pagination getShaTianPictorial(int pageNum, int pageSize, int typeId);

	Pagination getTownVideo(int pageNum, int pageSize);

	Pagination getEcoTourismType(int pageNum, int pageSize);

	Pagination getEcoTourism(int pageNum, int pageSize, int typeId);

	int doTownVideoSave(TownVideoModel videoModel);

	TownVideoModel getTownVideoById(String id);

	Pagination getTownMessage(int page, int pagesize);

	List getAllCountry();

	List getCountryVideo(long videoId);

	List getAlgGoods();
	
	int doAlgGoodsSave(AlgGoodsModel algGoodsModel);
	int doAlgGoodsUpdate(AlgGoodsModel algGoodsModel);
	int doAlgGoodsDelete(String id);
	
	Pagination getAlgGoods(int page, int pagesize);
	Pagination getAlgGoods(String searchName,int page, int pagesize);
	
	
	AlgGoodsModel getAlgGoodsById(String typeId);

	int doTownVideoUpdate(TownVideoModel videoModel);

	int doTownVideoDel(String id);

	List<?> getRestOrderByPhone(String phoneNumber);

	int doRestOrderSubmit(RestOrderReqParam param);

	List<?> getHotelRoomType(String hotelId);
	
	int doHotelRoomTypeSave(RoomTypeModel roomTypeModel);
	
	int doHotelRoomTypeDeleteByHotelId(String hotelId);

	int doHotelOrderSubmit(HotelOrderReqParam param);

	int doHotelOrderDetailSave(String typeid, String num, int orderid);

	List<?> getHotelOrderByPhone(WqsTravelReqParam param);

	List<?> getHotelOrderDetail(long id);

	int doRestOrderCancel(String orderid);

	int doHotelOrderCancel(String orderid);

	Pagination getTownMsgList(int pageNum, int pageSize);

	Pagination getHospitalList(String page, String pagesize);
	Pagination getHospitalList(String searchName,String searchAdd,String page, String pagesize);
	RestaurantOrderModel getRestOrderById(int id);

	HotelOrderModel getHotelOrderById(int d);

	Pagination getEnterpriseList(String page, String pagesize);

	Pagination getHotelList(String page, String pagesize);
	Pagination getHotelList(String searchName,String searchAdd,String page, String pagesize);
	Pagination getRestaurantList(String searchName,String searchAdd,String page, String pagesize);
	HotelModel getHotelById(int id);
	HotelModel getRestaurantById(int id);
	
	int doHotelSave(HotelModel hotelModel);
	int doHotelUpdate(HotelModel hotelModel);
	int doRestaurantSave(HotelModel hotelModel);
	int doRestaurantUpdate(HotelModel hotelModel);
	int doHotelDelete(String id);
	int doRestaurantDelete(int id);
	
	List getHotelIconById(long id);
	List getRestaurantIconById(long id);
	
	int doHotelIconSave(IconMappingModel iconMapping);
	int doRestaurantIconSave(IconMappingModel iconMapping);
	
	int doHotelIconDeleteByHotelId(String hotelId);
	int doRestaurantIconDeleteByHotelId(int hotelId);
	Pagination getRestaurantList(String page, String pagesize);

	List getRestaurantList(long id);

	Pagination getNongJiaLe(String page, String pagesize,String typeid);

	List getYujiaInfo(String page, String pagesize);
	YujialeModel getYujiaInfoById(int id);
	int  doYujiaInfoUpdate(YujialeModel model);
	Pagination getTownVideoQuery(int pageNum, int pageSize,
			TownVideoModel videoModel);

	TownMessageModel getTownMsgById(String id);

	int doTownMsgUpdate(TownMessageModel msgModel);

	int doTownMsgSave(TownMessageModel msgModel);

	int doTownMsgDelete(String id);

	Pagination getTownMsgQuery(int pageNum, int pageSize,
			TownMessageModel msgModel);

	Pagination getTownNews(int pageNum, int pageSize, String typeId);
	Pagination getTownNews(String searchName,String searchUnit,int pageNum, int pageSize, String typeId);
	Pagination getTownJobs(int pageNum, int pageSize);

	Pagination getTownTraffic(int pageNum, int pageSize);

	Pagination getTownNewsQuery(int pageNum, int pageSize,
			TownMessageModel msgModel);

	TownMessageModel getTownNewsById(String id);

	int doTownNewsUpdate(TownMessageModel msgModel);

	int doTownNewsSave(TownMessageModel msgModel);

	int doTownNewsDelete(String id);

	int doFeedbackSave(NsFeedbackModel model);

	List getFeedbackReplyList(String string);

	Pagination getFeedBackList(String pageNum, String pageSize);

	NsFeedbackModel getFeedBackById(String id);

	int doFeedbackReplySave(NsFeedbackReplyModel replyModel);

	int updateFeedbackStatus(long id, String status);

	int doFeedbackReplyDelete(String replyId);

	int doFeedbackDelete(String feedbackId);

	Pagination getTownEntList(int pageNum, int pageSize);
	Pagination getTownEntList(String searchName,int pageNum, int pageSize);

	
	
	EnterpriseModel getEnterpriseModel(long townId);
	int doEnterpriseModelUpdate(EnterpriseModel model);
	
	
	
	
	
	
	int doTownEntDelete(String id);

	int doTownEntSave(EnterpriseModel entModel);

	int updateEntIcon(String icon,String id);

	Pagination getHotelOrderList(int pageNum, int pageSize);
	Pagination getHotelOrderList(String searchName,String hotelId,int pageNum, int pageSize);
	List<HotelOrderDetailModel> getHotelOrderDetailList(long id);

	Pagination getFoodOrderList(int pageNum, int pageSize);
	Pagination getFoodOrderList(String searchName,String hotelId,int pageNum, int pageSize);
	Pagination getTownJobsList(int pageNum, int pageSize);
	Pagination getTownJobsList(String searchName,String searchUnit,int pageNum, int pageSize);
	int doTownJobsSave(TownJobsModel jobModel);

	int doTownJobsDelete(String id);

	int doTownJobsUpdate(TownJobsModel jobModel);

	TownJobsModel getTownJobsById(String id);

	int doHospitalSave(HospitalModel vo);
	int doHospitalDelete(String id);
	HospitalModel getHospitalById(int id);	
	int doHospitalUpdate(HospitalModel msgModel);	
	
	
	Pagination getTrafficReguList(String page, String pagesize);
	Pagination getTrafficReguList(String searchName,String searchUnit,String page, String pagesize);
	int doTrafficReguSave(TrafficReguModel msgModel);	
	int doTrafficReguDelete(String id);	
	
	int doTrafficReguUpdate(TrafficReguModel model);	
	TrafficReguModel getTrafficReguModel(int id);	

}

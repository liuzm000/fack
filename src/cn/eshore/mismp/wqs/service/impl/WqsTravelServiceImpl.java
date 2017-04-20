package cn.eshore.mismp.wqs.service.impl;

import java.util.List;

import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.wqs.dao.WqsTravelDAO;
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
import cn.eshore.mismp.wqs.service.WqsTravelService;

public class WqsTravelServiceImpl implements WqsTravelService {

	private WqsTravelDAO wqsTravelDAO;

	public void setWqsTravelDAO(WqsTravelDAO wqsTravelDAO) {
		this.wqsTravelDAO = wqsTravelDAO;
	}

	/** 农家乐和渔家乐*/
	public Pagination getNongJiaLeList(String type,String searchName,String searchAdd,int pageNum,int pageSize){
		return this.wqsTravelDAO.getNongJiaLeList(  type,  searchName,  searchAdd,  pageNum,  pageSize);
	}
	public int doNongJiaLeAdd(NongJiaLeModel model){
		return this.wqsTravelDAO.doNongJiaLeAdd(model);
	}
	public int doNongJiaLeEdit(NongJiaLeModel model){
		return this.wqsTravelDAO.doNongJiaLeEdit(model);
	}
	public int doNongJiaLeDelete(int id){
		return this.wqsTravelDAO.doNongJiaLeDelete(id);
	}
	public NongJiaLeModel getNongJiaLe(int id){
		return this.wqsTravelDAO.getNongJiaLe(id);
	}
	
	
	public Pagination getEcotourismTypeList(String searchName,int pageNum,int pageSize){
		return this.wqsTravelDAO.getEcotourismTypeList(searchName, pageNum, pageSize);
	}
	
	
	//保存生态旅游类型
	public int doEcotourismTypeSave(EcoTourismPictorialTypeModel model){
		return this.wqsTravelDAO.doEcotourismTypeSave(model);
	}
	//保存生态旅游图片
	public int doEcotourismPictureSave(EcoTourismPictorialModel model){
		return this.wqsTravelDAO.doEcotourismPictureSave(model);
	}
	
	
	public EcoTourismPictorialModel getEcoTourismPictorialModel(int id){
		return this.wqsTravelDAO.getEcoTourismPictorialModel(id);
	}
	public int doEcoTourismPictorialModelDelete(int id){
		return this.wqsTravelDAO.doEcoTourismPictorialModelDelete(id);
	}
	
	public int doEcotourismPictureUpdate(EcoTourismPictorialModel model){
		return this.wqsTravelDAO.doEcotourismPictureUpdate(model);
	}
	
	
	public EcoTourismPictorialTypeModel getEcoTourismPictorialTypeModel(long typeId){
		return this.wqsTravelDAO.getEcoTourismPictorialTypeModel(typeId);
	}
	public List<EcoTourismPictorialModel> getEcoTourismPictorialModelList(long typeId){
		return this.wqsTravelDAO.getEcoTourismPictorialModelList(typeId);
	}
	public int doEcoTourismPictorialTypeModelDel(long typeId){
		return this.wqsTravelDAO.doEcoTourismPictorialTypeModelDel(typeId);
	}
	public int doEcoTourismPictorialModelDel(long typeId){
		return this.wqsTravelDAO.doEcoTourismPictorialModelDel(typeId);
	}
	
	
	
	//更新生态旅游类型
	public int doEcotourismTypeUpdate(EcoTourismPictorialTypeModel model){
		return this.wqsTravelDAO.doEcotourismTypeUpdate(model);
	}
	
	
	
	/*获取乡村集合*/
	public Pagination getBeautifulCountryList(String countryName,int pageNum,int pageSize){
		return this.wqsTravelDAO.getBeautifulCountryList(  countryName,pageNum, pageSize);
	}
	
	
	public int doBeautifulCountrySave(BeautifulCountryModel model){
		return this.wqsTravelDAO.doBeautifulCountrySave(  model);
	}
	public int doBeautifulCountryVideoSave(BeautifulCountryVideoModel model){
		return this.wqsTravelDAO.doBeautifulCountryVideoSave(  model);
	}
	
	
	/*获取乡村信息，并删除*/
	public BeautifulCountryModel getBeautifulCountry(long countryId){
		return this.wqsTravelDAO.getBeautifulCountry(countryId);
	}
	public List<BeautifulCountryVideoModel> getBeautifulCountryVideoList(long countryId){
		return this.wqsTravelDAO.getBeautifulCountryVideoList(  countryId);
	}
	
	public int doBeautifulCountryDel(long countryId){
		return this.wqsTravelDAO.doBeautifulCountryDel(countryId);
	}
	public int doBeautifulCountryVideoDel(long countryId){
		return this.wqsTravelDAO.doBeautifulCountryVideoDel(countryId);
	}
	
	
	public BeautifulCountryModel getBeautifulCountryAndVideo(long countryId){
		return this.wqsTravelDAO.getBeautifulCountryAndVideo(countryId);
	}
	
	public int doBeautifulCountryUpdate(BeautifulCountryModel model){
		return this.wqsTravelDAO.doBeautifulCountryUpdate(  model);
	}
	
	
	
	@Override
	public Pagination getPictorialType(int pageNum, int pageSize) {
		return this.wqsTravelDAO.getPictorialType(pageNum,pageSize);
	}
	@Override
	public Pagination getPictorialTypeAndPics(String searchName,int pageNum, int pageSize) {
		return this.wqsTravelDAO.getPictorialTypeAndPics(searchName,pageNum,pageSize);
	}

	@Override
	public Pagination getShaTianPictorial(int pageNum, int pageSize,int typeId) {
		return this.wqsTravelDAO.getShaTianPictorial(pageNum,pageSize,typeId);
	}

	@Override
	public int doShaTianOnePictorialDelete(int id){
		return this.wqsTravelDAO.doShaTianOnePictorialDelete(id);
	}
	
	
	
	@Override
	public ShaTianPictorialModel getShaTianOnePictorialDelete(int id){
		return this.wqsTravelDAO.getShaTianOnePictorialDelete(id);
	}
	
	
	
	
	@Override
	public int doShaTianOnePictorialUpdate(ShaTianPictorialModel model){
		return this.wqsTravelDAO.doShaTianOnePictorialUpdate(model);
	}
	
	
	
	
	
	@Override
	public int doShaTianPictorialDelete(int id) {
		return this.wqsTravelDAO.doShaTianPictorialDelete(id);
	}
	
	@Override
	public ShaTianPictorialTypeModel getShaTianPictorial(int id){
		return this.wqsTravelDAO.getShaTianPictorial(  id);
	}
	
	//删除画卷类型下的画卷列表
	@Override
	public int doShaTianPictorialListDelete(int typeId){
		return this.wqsTravelDAO.doShaTianPictorialListDelete(  typeId);
	}
	
	//获取画卷类型下的画卷列表
	@Override
	public List<ShaTianPictorialModel> getShaTianPictorialList(int typeId){
		return this.wqsTravelDAO.getShaTianPictorialList(typeId);
	}
	
	
	
	
	
	
	@Override
	public int doShaTianPictorialSave(ShaTianPictorialTypeModel param) {
		return this.wqsTravelDAO.doShaTianPictorialSave(param);
	}
	@Override
	public int doShaTianPictorialListSave(ShaTianPictorialModel param) {
		return this.wqsTravelDAO.doShaTianPictorialListSave(  param);
	}

	@Override
	public int doShaTianPictorialUpdate(int typeId,String name,String desc) {
		return this.wqsTravelDAO.doShaTianPictorialUpdate(    typeId,  name,  desc);
	}
	
	@Override
	public Pagination getTownVideo(int pageNum, int pageSize) {
		return this.wqsTravelDAO.getTownVideo(pageNum,pageSize);
	}

	@Override
	public Pagination getEcoTourismType(int pageNum, int pageSize) {
		return this.wqsTravelDAO.getEcoTourismType(pageNum,pageSize);
	}

	@Override
	public Pagination getEcoTourism(int pageNum, int pageSize, int typeId) {
		return this.wqsTravelDAO.getEcoTourism(pageNum,pageSize,typeId);
	}

	@Override
	public int doTownVideoSave(TownVideoModel videoModel) {
		return this.wqsTravelDAO.doTownVideoSave(videoModel);
		
	}

	@Override
	public TownVideoModel getTownVideoById(String id) {
		return this.wqsTravelDAO.getTownVideoById(id);
	}

	@Override
	public int doTownVideoUpdate(TownVideoModel videoModel) {
		return this.wqsTravelDAO.doTownVideoUpdate(videoModel);
	}

	@Override
	public Pagination getTownMessage(int page, int pagesize) {
		return this.wqsTravelDAO.getTownMessage(page,pagesize);
	}

	@Override
	public List getAllCountry() {
		return this.wqsTravelDAO.getAllCountry();
	}

	@Override
	public List getCountryVideo(long videoId) {
		return this.wqsTravelDAO.getCountryVideo(videoId);
	}

	@Override
	public List getAlgGoods() {
		return this.wqsTravelDAO.getAlgGoods();
	}

	@Override
	public Pagination getAlgGoods(int page, int pagesize) {
		return this.wqsTravelDAO.getAlgGoods(page,pagesize);
	}
	@Override
	public Pagination getAlgGoods(String searchName,int page, int pagesize){
		return this.wqsTravelDAO.getAlgGoods(searchName,page,pagesize);
	}
	
	@Override
	public AlgGoodsModel getAlgGoodsById(String typeId) {
		return this.wqsTravelDAO.getAlgGoodsById(typeId);
	}
	
	@Override
	public int doAlgGoodsSave(AlgGoodsModel algGoodsModel){
		return this.wqsTravelDAO.doAlgGoodsSave(algGoodsModel);
	}
	
	@Override
	public int doAlgGoodsUpdate(AlgGoodsModel algGoodsModel){
		return this.wqsTravelDAO.doAlgGoodsUpdate(algGoodsModel);
	}
	
	
	
	@Override
	public int doAlgGoodsDelete(String id){
		return this.wqsTravelDAO.doAlgGoodsDelete(id);
	}
	
	@Override
	public int doTownVideoDel(String id) {
		return this.wqsTravelDAO.doTownVideoDel(id);
	}

	@Override
	public List<?> getRestOrderByPhone(String phoneNumber) {
		return this.wqsTravelDAO.getRestOrderByPhone(phoneNumber);
	}

	@Override
	public int doRestOrderSubmit(RestOrderReqParam param) {
		return this.wqsTravelDAO.doRestOrderSubmit(param);
	}

	@Override
	public List<?> getHotelRoomType(String hotelId) {
		return this.wqsTravelDAO.getHotelRoomType(hotelId);
	}
	@Override
	public int doHotelRoomTypeSave(RoomTypeModel roomTypeModel){
		return this.wqsTravelDAO.doHotelRoomTypeSave(roomTypeModel);
	}
	
	@Override
	public int doHotelRoomTypeDeleteByHotelId(String hotelId){
		return this.wqsTravelDAO.doHotelRoomTypeDeleteByHotelId(hotelId);
	}
	
	@Override
	public int doHotelOrderSubmit(HotelOrderReqParam param) {
		return this.wqsTravelDAO.doHotelOrderSubmit(param);
	}

	@Override
	public int doHotelOrderDetailSave(String typeid, String num, int orderid) {
		return this.wqsTravelDAO.doHotelOrderDetailSave(typeid,num,orderid);
		
	}

	@Override
	public List<?> getHotelOrderByPhone(WqsTravelReqParam param) {
		return this.wqsTravelDAO.getHotelOrderByPhone(param);
	}

	@Override
	public List<?> getHotelOrderDetail(long id) {
		return this.wqsTravelDAO.getHotelOrderDetail(id);
	}

	@Override
	public int doRestOrderCancel(String orderid) {
		return this.wqsTravelDAO.doRestOrderCancel(orderid);
	}

	@Override
	public int doHotelOrderCancel(String orderid) {
		return this.wqsTravelDAO.doHotelOrderCancel(orderid);
	}

	@Override
	public Pagination getTownMsgList(int pageNum, int pageSize) {
		return this.wqsTravelDAO.getTownMsgList(pageNum,pageSize);
	}

	@Override
	public Pagination getHospitalList(String page, String pagesize) {
		return this.wqsTravelDAO.getHospitalList(page,pagesize);
	}
	@Override
	public Pagination getHospitalList(String searchName,String searchAdd,String page, String pagesize){
		return this.wqsTravelDAO.getHospitalList(searchName,  searchAdd,page,pagesize);
	}
	@Override
	public int doHospitalSave(HospitalModel msgModel){
		return this.wqsTravelDAO.doHospitalSave(msgModel);
	}
	
	@Override
	public int doHospitalDelete(String id) {
		// TODO Auto-generated method stub
		return this.wqsTravelDAO.doHospitalDelete(id);
	}
	@Override
	public HospitalModel getHospitalById(int id){
		return this.wqsTravelDAO.getHospitalById(  id);
	}
	
	@Override
	public int doHospitalUpdate(HospitalModel msgModel){
		return this.wqsTravelDAO.doHospitalUpdate(  msgModel);
	}	
	
	
	@Override
	public RestaurantOrderModel getRestOrderById(int id) {
		return this.wqsTravelDAO.getRestOrderById(id);
	}

	@Override
	public HotelOrderModel getHotelOrderById(int d) {
		return this.wqsTravelDAO.getHotelOrderById(d);
	}

	@Override
	public Pagination getEnterpriseList(String page, String pagesize) {
		return this.wqsTravelDAO.getEnterpriseList(page,pagesize);
	}

	@Override
	public Pagination getHotelList(String page, String pagesize) {
		return this.wqsTravelDAO.getHotelList(page,pagesize);
	}
	@Override
	public Pagination getHotelList(String searchName,String searchAdd,String page, String pagesize){
		return this.wqsTravelDAO.getHotelList(searchName,  searchAdd,page,pagesize);
	}
	
	@Override
	public Pagination getRestaurantList(String searchName,String searchAdd,String page, String pagesize){
		return this.wqsTravelDAO.getRestaurantList(searchName,  searchAdd,page,pagesize);
	}
	
	
	
	
	public List<HotelModel> getHotelRestaurantList(int type){
		return this.wqsTravelDAO.getHotelRestaurantList(  type);
	}
	
	
	@Override
	public HotelModel getHotelById(int id) {
		return this.wqsTravelDAO.getHotelById(id);
	}
	@Override
	public HotelModel getRestaurantById(int id) {
		return this.wqsTravelDAO.getRestaurantById(id);
	}
	
	@Override
	public int doHotelSave(HotelModel hotelModel){
		return this.wqsTravelDAO.doHotelSave(hotelModel);
	}
	@Override
	public int doHotelUpdate(HotelModel hotelModel){
		return this.wqsTravelDAO.doHotelUpdate(hotelModel);
	}
	
	@Override
	public int doRestaurantSave(HotelModel hotelModel){
		return this.wqsTravelDAO.doRestaurantSave(hotelModel);
	}
	@Override
	public int doRestaurantUpdate(HotelModel hotelModel){
		return this.wqsTravelDAO.doRestaurantUpdate(hotelModel);
	}
	@Override
	public int doHotelDelete(String id){
		return this.wqsTravelDAO.doHotelDelete(id);
	}
	@Override
	public int doRestaurantDelete(int id){
		return this.wqsTravelDAO.doRestaurantDelete(id);
	}
	
	@Override
	public List getHotelIconById(long id) {
		return this.wqsTravelDAO.getHotelIconById(id);
	}
	@Override
	public List getRestaurantIconById(long id) {
		return this.wqsTravelDAO.getRestaurantIconById(id);
	}

	@Override
	public int doHotelIconSave(IconMappingModel iconMapping){
		return this.wqsTravelDAO.doHotelIconSave(iconMapping);
	}
	@Override
	public int doRestaurantIconSave(IconMappingModel iconMapping){
		return this.wqsTravelDAO.doRestaurantIconSave(iconMapping);
	}
	
	@Override
	public int doHotelIconDeleteByHotelId(String hotelId){
		return this.wqsTravelDAO.doHotelIconDeleteByHotelId(hotelId);
	}
	@Override
	public int doRestaurantIconDeleteByHotelId(int hotelId){
		return this.wqsTravelDAO.doRestaurantIconDeleteByHotelId(hotelId);
	}
	
	@Override
	public Pagination getRestaurantList(String page, String pagesize) {
		return this.wqsTravelDAO.getRestaurantList(page,pagesize);
	}

	@Override
	public List getRestIconById(long id) {
		return this.wqsTravelDAO.getRestaurantList(id);
	}

	@Override
	public Pagination getNongJiaLe(String page, String pagesize,String typeid) {
		return this.wqsTravelDAO.getNongJiaLe(page,pagesize,typeid);
	}

	@Override
	public List getYujiaInfo(String page, String pagesize) {
		return this.wqsTravelDAO.getYujiaInfo(page,pagesize);
	}
	@Override
	public YujialeModel getYujiaInfoById(int id){
		return this.wqsTravelDAO.getYujiaInfoById(  id);
	}
	@Override
	public int  doYujiaInfoUpdate(YujialeModel model){
		return this.wqsTravelDAO.doYujiaInfoUpdate(  model);
	}
	@Override
	public Pagination getTownVideoQuery(int pageNum, int pageSize,
			TownVideoModel videoModel) {
		return this.wqsTravelDAO.getTownVideoQuery(pageNum,pageSize,videoModel);
	}

	@Override
	public TownMessageModel getTownMsgById(String id) {
		return this.wqsTravelDAO.getTownMsgById(id);
	}

	@Override
	public int doTownMsgUpdate(TownMessageModel msgModel) {
		return this.wqsTravelDAO.doTownMsgUpdate(msgModel);
	}

	@Override
	public int doTownMsgSave(TownMessageModel msgModel) {
		return this.wqsTravelDAO.doTownMsgSave(msgModel);
	}

	@Override
	public int doTownMsgDelete(String id) {
		return this.wqsTravelDAO.doTownMsgDelete(id);
	}

	@Override
	public Pagination getTownMsgQuery(int pageNum, int pageSize,
			TownMessageModel msgModel) {
		return this.wqsTravelDAO.getTownMsgQuery(pageNum,pageSize,msgModel);
	}

	@Override
	public Pagination getTownNews(int pageNum, int pageSize, String typeId) {
		return this.wqsTravelDAO.getTownNews(pageNum,pageSize,typeId);
	}
	@Override
	public Pagination getTownNews(String searchName,String searchUnit,int pageNum, int pageSize, String typeId){
		return this.wqsTravelDAO.getTownNews(searchName,searchUnit,pageNum,pageSize,typeId);
	}

	@Override
	public Pagination getTownTraffic(int pageNum, int pageSize) {
		return this.wqsTravelDAO.getTownTraffic(pageNum,pageSize);
	}

	@Override
	public Pagination getTownNewsQuery(int pageNum, int pageSize,
			TownMessageModel msgModel) {
		return this.wqsTravelDAO.getTownNewsQuery(pageNum,pageSize,msgModel);
	}

	@Override
	public TownMessageModel getTownNewsById(String id) {
		return this.wqsTravelDAO.getTownNewsById(id);
	}

	@Override
	public int doTownNewsUpdate(TownMessageModel msgModel) {
		return this.wqsTravelDAO.doTownNewsUpdate(msgModel);
	}

	@Override
	public int doTownNewsSave(TownMessageModel msgModel) {
		return this.wqsTravelDAO.doTownNewsSave(msgModel);
	}

	@Override
	public int doTownNewsDelete(String id) {
		return this.wqsTravelDAO.doTownNewsDelete(id);
	}


	@Override
	public Pagination getFeedbackList(WqsTravelReqParam param) {
		return this.wqsTravelDAO.getFeedBackList(param.getPage(),param.getPagesize());
	}

	@Override
	public List getFeedbackReplyList(String string) {
		return this.wqsTravelDAO.getFeedbackReplyList(string);
	}

	
	public int doFeedbackSave(NsFeedbackModel model) {
		return this.wqsTravelDAO.doFeedbackSave(model);
	}

	
	public Pagination getFeedbackList(String pageNum, String pageSize) {
		return this.wqsTravelDAO.getFeedBackList(pageNum, pageSize);
	}

	
	public Pagination getFeedbackList(int pageNum, int pageSize) {
		return this.wqsTravelDAO.getFeedBackList(pageNum+"", pageSize+"");
	}

	
	public NsFeedbackModel getFeedbackById(String id) {
		return this.wqsTravelDAO.getFeedBackById(id);
	}

	
	public int doFeedbackReplySave(NsFeedbackReplyModel replyModel) {
		return this.wqsTravelDAO.doFeedbackReplySave(replyModel);
	}

	
	public int updateFeedbackStatus(long id, String status) {
		return this.wqsTravelDAO.updateFeedbackStatus(id,status);
		
	}
	public int doFeedbackReplyDelete(String replyId) {
		return this.wqsTravelDAO.doFeedbackReplyDelete(replyId);
	}
	public int doFeedbackDelete(String feedbackId) {
		return this.wqsTravelDAO.doFeedbackDelete(feedbackId);
	}

	public EnterpriseModel getEnterpriseModel(long townId){
		return this.wqsTravelDAO.getEnterpriseModel(townId);
	}
	public int doEnterpriseModelUpdate(EnterpriseModel model){
		return this.wqsTravelDAO.doEnterpriseModelUpdate(model);
	}

	public Pagination getTownEntList(int pageNum, int pageSize) {
		return this.wqsTravelDAO.getTownEntList(pageNum,pageSize);
	}
	public Pagination getTownEntList(String searchName,int pageNum, int pageSize) {
		return this.wqsTravelDAO.getTownEntList(searchName,pageNum,pageSize);
	}

	public int doTownEntDelete(String id) {
		return this.wqsTravelDAO.doTownEntDelete(id);
	}

	@Override
	public int doTownEntSave(EnterpriseModel entModel) {
		return this.wqsTravelDAO.doTownEntSave(entModel);
	}

	@Override
	public int updateEntIcon(String icon,String id) {
		return this.wqsTravelDAO.updateEntIcon(icon,id);
	}

	@Override
	public Pagination getHotelOrderList(int pageNum, int pageSize) {
		return this.wqsTravelDAO.getHotelOrderList(pageNum,pageSize);
	}
	@Override
	public Pagination getHotelOrderList(String searchName,String hotelId,int pageNum, int pageSize){
		return this.wqsTravelDAO.getHotelOrderList(  searchName,hotelId,pageNum,pageSize);
	}
	@Override
	public List<HotelOrderDetailModel> getHotelOrderDetailList(long id) {
		return this.wqsTravelDAO.getHotelOrderDetailList(id);
	}

	@Override
	public Pagination getFoodOrderList(int pageNum, int pageSize) {
		return this.wqsTravelDAO.getFoodOrderList(pageNum,pageSize);
	}
	@Override
	public Pagination getFoodOrderList(String searchName,String hotelId,int pageNum, int pageSize){
		return this.wqsTravelDAO.getFoodOrderList(  searchName,hotelId,pageNum,pageSize);
	}
	@Override
	public Pagination getTownJobsList(int pageNum, int pageSize) {
		return this.wqsTravelDAO.getTownJobsList(pageNum,pageSize);
	}
	
	
	@Override
	public Pagination getTownJobsList(String searchName,String searchUnit,int pageNum, int pageSize){
		return this.wqsTravelDAO.getTownJobsList( searchName,  searchUnit,pageNum,pageSize);
	}
	
	
	
	@Override
	public int doTownJobsSave(TownJobsModel jobModel) {
		return this.wqsTravelDAO.doTownJobsSave(jobModel);
	}

	@Override
	public int doTownJobsDelete(String id) {
		return this.wqsTravelDAO.doTownJobsDelete(id);
	}

	@Override
	public int doTownJobsUpdate(TownJobsModel jobModel) {
		return this.wqsTravelDAO.doTownJobsUpdate(jobModel);
	}

	@Override
	public TownJobsModel getTownJobsById(String id) {
		return this.wqsTravelDAO.getTownJobsById( id);
	}

	@Override
	public Pagination getTrafficReguList(String page, String pagesize) {
		// TODO Auto-generated method stub
		return this.wqsTravelDAO.getTrafficReguList(page, pagesize);
	}
	
	@Override
	public Pagination getTrafficReguList(String searchName,String searchUnit,String page, String pagesize){
		return this.wqsTravelDAO.getTrafficReguList(searchName,  searchUnit,page, pagesize);
	}
	
	@Override
	public int doTrafficReguUpdate(TrafficReguModel model){
		return this.wqsTravelDAO.doTrafficReguUpdate(  model);
	}	
	@Override
	public TrafficReguModel getTrafficReguModel(int id){
		return this.wqsTravelDAO.getTrafficReguModel(  id);
	}
	
	
	@Override
	public int doTrafficReguSave(TrafficReguModel msgModel) {
		// TODO Auto-generated method stub
		return this.wqsTravelDAO.doTrafficReguSave(msgModel);
	}

	@Override
	public int doTrafficReguDelete(String id) {
		// TODO Auto-generated method stub
		return this.wqsTravelDAO.doTrafficReguDelete(id);
	}

	
}

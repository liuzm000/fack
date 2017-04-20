package cn.eshore.mismp.wqs.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.axis.utils.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.lob.LobHandler;

import cn.eshore.mismp.dao.impl.BaseDAOJdbcTemplate;
import cn.eshore.mismp.util.ClobUtil;
import cn.eshore.mismp.util.DealDate;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.util.Util;
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
import cn.eshore.mismp.wqs.model.rowMapper.AlgGoodsModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.BeautifulCountryModelRow;
import cn.eshore.mismp.wqs.model.rowMapper.BeautifulCountryModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.BeautifulCountryVideoModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.EcoTourismPictorialModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.EcoTourismPictorialTypeModelRowMap;
import cn.eshore.mismp.wqs.model.rowMapper.EcoTourismPictorialTypeModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.EnterpriseModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.HospitalModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.HotelModelRowMap;
import cn.eshore.mismp.wqs.model.rowMapper.HotelModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.HotelOrderDetailModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.HotelOrderModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.IconMappingModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.NongJiaLeModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.NsFeedbackModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.NsFeedbackReplyModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.RestaurantOrderModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.RoomTypeModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.ShaTianPictorialModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.ShaTianPictorialTypeModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.ShaTianPictorialTypePicModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.TownJobsModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.TownMessageModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.TownVideoModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.TrafficReguModelRowMapper;
import cn.eshore.mismp.wqs.model.rowMapper.YujialeModelRowMapper;
@SuppressWarnings("unchecked")
public class WqsTravelDAOImpl extends BaseDAOJdbcTemplate implements
		WqsTravelDAO {

	private LobHandler lobHandler;

	
	
	
	
	/** 农家乐和渔家乐*/
	public Pagination getNongJiaLeList(String type,String searchName,String searchAdd,int pageNum,int pageSize){
		String sql=" FROM T_WQS_NONGJIALE T WHERE TYPE=? ";
		List param=new ArrayList();
		param.add(type);
		
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" AND NAME LIKE ? ";
			param.add("%"+searchName+"%");
		}
		if(searchAdd!=null&&!"".equals(searchAdd)){
			sql+=" AND ADDR LIKE ? ";
			param.add("%"+searchAdd+"%");
		}
		
		String qrySql = "SELECT ID,NAME,ADDR,TELE,TYPE,ISVALID,ORDERS "  + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ORDERS   ";
		Pagination returnList = pagination(qrySql + orderSql, countSql , param.toArray(),
				new NongJiaLeModelRowMapper(), pageSize, pageNum);
		return returnList;
		
	}
	public int doNongJiaLeAdd(NongJiaLeModel model){
		int id=this.queryForInt("SELECT SEQ_T_WQS_NONGJIALE.NEXTVAL FROM DUAL ");
		String sql="INSERT INTO T_WQS_NONGJIALE(ID,NAME,ADDR,TELE,TYPE,ISVALID,ORDERS) VALUES(?,?,?,?,?,?,?)";
		int d=0;
		try {
			d= this.update(sql,new Object[]{id,model.getName(),model.getAddr(),model.getTele(),model.getType(),1,id});
			if(d>0) d=id;
		} catch (DataAccessException e) {
			log.error("在保存农家乐时，出现异常："+e.toString());
			e.printStackTrace();
		}
		return d;
	}
	public int doNongJiaLeEdit(NongJiaLeModel model){
		String sql="UPDATE T_WQS_NONGJIALE SET NAME=?,ADDR=?,TELE=?,ORDERS=?,ISVALID=? WHERE ID=?";
		int d=0;
		
		try {
			d=this.update(sql,new Object[]{model.getName(),model.getAddr(),model.getTele(),model.getOrders(),model.getIsValid(),model.getId()});
		} catch (DataAccessException e) {
			log.error("在更新农家乐的餐馆信息时，出现异常："+e.toString());
			e.printStackTrace();
		}
		return d;
	}
	public int doNongJiaLeDelete(int id){
		String sql="delete T_WQS_NONGJIALE where id=?";
		try {
			return this.update(sql,new Object[]{id});
		} catch (DataAccessException e) {
			log.error("在删除农家乐餐馆时，出现异常："+e.toString());
			e.printStackTrace();
		}
		return 0;
	}
	public NongJiaLeModel getNongJiaLe(int id){
		String sql="select id,name,addr,tele,type,isvalid,orders FROM T_WQS_NONGJIALE where id=?";
		List<NongJiaLeModel> datas;
		try {
			datas= this.query(sql,new Object[]{id},new NongJiaLeModelRowMapper());
			if(datas!=null&&datas.size()>0) return datas.get(0);
		} catch (DataAccessException e) {
			log.error("在查询某个农家乐时，出现异常："+e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	public int doEcotourismPictureUpdate(EcoTourismPictorialModel model){
		
		String sql="UPDATE T_WQS_ECOTOURISM_PICTURE SET DESCRIPT=?,FILE_NAME=?,FILE_PATH=? WHERE ID=?";
		
		return this.update(sql,new Object[]{model.getDescript(),model.getFileName(),model.getFilePath(),model.getId()});
	}
	
	public EcoTourismPictorialModel getEcoTourismPictorialModel(int id){
		String sql="SELECT  ID,DESCRIPT,FILE_NAME,FILE_PATH,CREATE_TIME,  TYPE_ID FROM T_WQS_ECOTOURISM_PICTURE WHERE ID=?";
		List<EcoTourismPictorialModel> datas=this.query(sql, new Object[]{id},new EcoTourismPictorialModelRowMapper());
		if(datas!=null&&datas.size()>0){
			return datas.get(0);
		}
		return null;
	}
	public int doEcoTourismPictorialModelDelete(int id){
		String sql="DELETE T_WQS_ECOTOURISM_PICTURE WHERE ID=?";
		return this.update(sql,new Object[]{id});
	}
	
	
	public Pagination getEcotourismTypeList(String searchName,int pageNum,int pageSize){
		String sql=" FROM T_WQS_ECOTOURISM_PICTURE_TYPE T ";
		List param=new ArrayList();
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" WHERE   TYPENAME LIKE ? ";
			param.add("%"+searchName+"%");
		}
		String qrySql = "SELECT  ID,TYPENAME,ICON_NAME,ICON_PATH,ISSKIP,INFO," +
						"(SELECT  WMSYS.WM_CONCAT( P.FILE_PATH)   FROM T_WQS_ECOTOURISM_PICTURE P WHERE P.TYPE_ID =   T.ID) FILE_PATHS " 
						+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID   ";
		Pagination returnList = pagination(qrySql + orderSql, countSql , param.toArray(),
				new EcoTourismPictorialTypeModelRowMap(), pageSize, pageNum);
		return returnList;
		
	}
	
	//保存生态旅游类型
	public int doEcotourismTypeSave(EcoTourismPictorialTypeModel model){
		String sql="INSERT INTO T_WQS_ECOTOURISM_PICTURE_TYPE ( ID,TYPENAME,ICON_NAME,ICON_PATH,ISSKIP,TYPE_DESC,INFO) " +
				" VALUES(?,?,?,?,?,?,?)";
		int typeId=this.queryForInt("SELECT SEQ_T_WQS_ECOTOURISM_TYPE.NEXTVAL FROM DUAL");
		int d=0;
		try {
			d = this.update(sql,new Object[]{typeId,model.getTypeName(),model.getIconName(),model.getIconPath(),model.getIsSkip(),model.getTypeDesc(),model.getInfoHtml()});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(d>0){
			return typeId;
		}
		return 0;
		
	}
	
	//保存生态旅游图片
	public int doEcotourismPictureSave(EcoTourismPictorialModel model){
		String sql="INSERT INTO T_WQS_ECOTOURISM_PICTURE( ID,DESCRIPT,FILE_NAME,FILE_PATH,CREATE_TIME,ORDERDEC ,TYPE_ID)" +
		" VALUES(SEQ_T_WQS_ECOTOURISM_PICTURE.NEXTVAL,?,?,?,?,?,?)";
		try {
			return this.update(sql,new Object[]{model.getDescript(),model.getFileName(),model.getFilePath(),new Date(),null,model.getTypeId()});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public EcoTourismPictorialTypeModel getEcoTourismPictorialTypeModel(long typeId){
		String sql="SELECT ID,TYPENAME,ICON_NAME,ICON_PATH,ISSKIP,TYPE_DESC,TYPE_DESC,INFO FROM T_WQS_ECOTOURISM_PICTURE_TYPE WHERE ID=? ";
		List<EcoTourismPictorialTypeModel> datas=this.query(sql,new Object[]{typeId},new EcoTourismPictorialTypeModelRowMapper());
		if(datas!=null&&datas.size()>0){
			return   datas.get(0);
		}
		return null;
		
	}
	public List<EcoTourismPictorialModel> getEcoTourismPictorialModelList(long typeId){
		String sql="select  ID,DESCRIPT,FILE_NAME,FILE_PATH,CREATE_TIME,ORDERDEC ,TYPE_ID from T_WQS_ECOTOURISM_PICTURE where type_id=?";
		return this.query(sql, new Object[]{typeId},new EcoTourismPictorialModelRowMapper());
	}
	public int doEcoTourismPictorialTypeModelDel(long typeId){
		String sql="DELETE T_WQS_ECOTOURISM_PICTURE_TYPE WHERE ID=? ";
		return this.update(sql,new Object[]{typeId});
	}
	public int doEcoTourismPictorialModelDel(long typeId){
		String sql="DELETE T_WQS_ECOTOURISM_PICTURE WHERE TYPE_ID=? ";
		return this.update(sql,new Object[]{typeId});
	}
	
	//更新生态旅游类型
	public int doEcotourismTypeUpdate(EcoTourismPictorialTypeModel model){
		String sql="UPDATE T_WQS_ECOTOURISM_PICTURE_TYPE SET TYPENAME=?,INFO=? WHERE ID=?";
		return this.update(sql,new Object[]{model.getTypeName(),model.getInfoHtml(),model.getId()});
	}
	
	
	
	/*获取乡村信息，并删除*/
	public BeautifulCountryModel getBeautifulCountry(long countryId){
		String sql = "SELECT   ID,NAME,DESCRIPT,ICON,FILE_URL,VIDEO_ID  FROM T_WQS_BEACOUNTRY T WHERE ID=? ";
		List<BeautifulCountryModel> datas=this.query(sql, new Object[]{countryId},new BeautifulCountryModelRowMapper());
		if(datas!=null&&datas.size()>0){
			return datas.get(0);
		}
		return null;
	}
	
	public int doBeautifulCountryUpdate(BeautifulCountryModel model){
		String sql="UPDATE T_WQS_BEACOUNTRY SET NAME=?,DESCRIPT=?,ICON=?,FILE_URL=? WHERE ID=? ";
		return this.update(sql,new Object[]{model.getName(),model.getDescript(),model.getIcon(),model.getFileUrl(),model.getId()});
	}
	
	public BeautifulCountryModel getBeautifulCountryAndVideo(long countryId){
		String sql="SELECT ID,NAME,DESCRIPT,ICON,FILE_URL,VIDEO_ID ," +
				" (SELECT  WMSYS.WM_CONCAT( V.ICON_PATH)    FROM T_WQS_BEACOUNTRY_VIDEO V WHERE V.COUNTRY_ID =   T.ID) VIDEO_ICON_PATHS," +
				" (SELECT  WMSYS.WM_CONCAT( V.VIDEO_PATH)   FROM T_WQS_BEACOUNTRY_VIDEO V WHERE V.COUNTRY_ID =   T.ID) VIDEO_PATHS " +
				"   FROM T_WQS_BEACOUNTRY T  WHERE T.ID=?   ORDER BY T.ID ";
		
		List<BeautifulCountryModel> datas=this.query(sql, new Object[]{countryId},new BeautifulCountryModelRow());
		if(datas!=null&&datas.size()>0){
			return datas.get(0);
		}
		return null;
		
	}
	
	public List<BeautifulCountryVideoModel> getBeautifulCountryVideoList(long countryId){
		String sql=" SELECT T.ID,T.ICON_NAME,T.ICON_PATH,T.VIDEO_NAME,T.VIDEO_PATH,T.COUNTRY_ID  FROM T_WQS_BEACOUNTRY_VIDEO T WHERE COUNTRY_ID =? ";
		return this.query(sql, new Object[]{countryId},new BeautifulCountryVideoModelRowMapper());
	}
	
	
	public int doBeautifulCountryDel(long countryId){
		String sql="DELETE T_WQS_BEACOUNTRY WHERE ID=? ";
		return this.update(sql,new Object[]{countryId});
	}
	public int doBeautifulCountryVideoDel(long countryId){
		String sql="delete T_WQS_BEACOUNTRY_VIDEO where country_id=? ";
		return this.update(sql,new Object[]{countryId});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*获取乡村集合*/
	@SuppressWarnings("unchecked")
	public Pagination getBeautifulCountryList(String countryName,int pageNum,int pageSize){
		String sql=" FROM T_WQS_BEACOUNTRY T ";
		List param=new ArrayList();
		if(countryName!=null&&!"".equals(countryName)){
			sql+=" WHERE   NAME LIKE ? ";
			param.add("%"+countryName+"%");
		}
		String qrySql = "SELECT   ID,NAME,DESCRIPT,ICON,FILE_URL,VIDEO_ID," +
						"(SELECT  WMSYS.WM_CONCAT( V.ICON_PATH)    FROM T_WQS_BEACOUNTRY_VIDEO V WHERE V.COUNTRY_ID =   T.ID) VIDEO_ICON_PATHS, " +
						"(SELECT  WMSYS.WM_CONCAT( V.VIDEO_PATH)   FROM T_WQS_BEACOUNTRY_VIDEO V WHERE V.COUNTRY_ID =T.ID)  VIDEO_PATHS " 
						+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID   ";
		Pagination returnList = pagination(qrySql + orderSql, countSql , param.toArray(),
				new BeautifulCountryModelRow(), pageSize, pageNum);
		return returnList;
		
	}
	
	public int doBeautifulCountrySave(BeautifulCountryModel model){
		int index=this.queryForInt("SELECT SEQ_T_WQS_BEACOUNTRY.NEXTVAL FROM DUAL");
		String sql="INSERT INTO T_WQS_BEACOUNTRY(ID,NAME,ICON,DESCRIPT,FILE_URL) VALUES(?,?,?,?,?)";
		int d=0;
		try {
			d=this.update(sql,new Object[]{index,model.getName(),model.getIcon(),model.getDescript(),model.getFileUrl()});
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error("在保存乡村信息时，出现异常："+e.toString()	);
		}
		if(d>0){
			return index;
		}
		return 0;
	}
	public int doBeautifulCountryVideoSave(BeautifulCountryVideoModel model){
		String sql="INSERT INTO T_WQS_BEACOUNTRY_VIDEO(ID,ICON_NAME,ICON_PATH,VIDEO_NAME,VIDEO_PATH,COUNTRY_ID) VALUES(" +
				" SEQ_T_WQS_BEACOUNTRY_VIDEO.NEXTVAL,?,?,?,?,?)";
		return this.update(sql,new Object[]{model.getIconName(),model.getIconPath(),model.getVideoName(),model.getVideoPath(),model.getCountryID()});
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//删除画卷类型下的画卷列表
	@Override
	public int doShaTianPictorialListDelete(int typeId){
		String sql="DELETE FROM T_WQS_TOWN_PICTORIAL   WHERE  TYPE_ID = ? ";
		return this.update(sql,new Object[]{typeId});
	}
	
	//获取画卷类型下的画卷列表
	@Override
	public List<ShaTianPictorialModel> getShaTianPictorialList(int typeId){
		String sql="select * FROM T_WQS_TOWN_PICTORIAL   WHERE  TYPE_ID = ? ";
		
		return this.query(sql, new Object[]{typeId},  new ShaTianPictorialModelRowMapper());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//删除画卷类型
	public int doShaTianPictorialDelete(int id){
		String sql="DELETE T_WQS_TOWN_PICTORIAL_TYPE WHERE ID=? ";
		return this.update(sql,new Object[]{id});
	}
	
	//获取画卷类型
	public ShaTianPictorialTypeModel getShaTianPictorial(int id){
		String sql="SELECT * FROM T_WQS_TOWN_PICTORIAL_TYPE WHERE ID=?";
		ShaTianPictorialTypeModel shaTianPictorialTypeModel=null;
		try {
			shaTianPictorialTypeModel=(ShaTianPictorialTypeModel) this.queryForObject(sql, new Object[]{id}, new ShaTianPictorialTypeModelRowMapper() );
		} catch (DataAccessException e) {
			log.error("在获取[id="+id+"]的画卷类型时，出现异常："+e.toString());
			e.printStackTrace();
		}
		
		return shaTianPictorialTypeModel;
	}
	
	
	public int doShaTianPictorialSave(ShaTianPictorialTypeModel param){
		String indexSql="SELECT SEQ_T_WQS_TOWN_PICTORIAL_TYPE.NEXTVAL FROM DUAL";
		
		int id=this.queryForInt(indexSql);
		
		String sql="INSERT INTO T_WQS_TOWN_PICTORIAL_TYPE(ID,TYPENAME,ICON_NAME,ICON_PATH,TYPE_DESC) VALUES(?,?,?,?,?)";
		
		int d = this.update(sql,new Object[]{id,
				Util.nullToStr(param.getTypeName()),
				Util.nullToStr(param.getIconName()),
						Util.nullToStr(param.getIconPath())
				,Util.nullToStr(param.getTypeDesc())});
		if (d > 0) {
			return id;
		}
		
		return 0;
	}
	
	
	public int doShaTianPictorialListSave(ShaTianPictorialModel param){
		String indexSql="SELECT SEQ_T_WQS_TOWN_PICTORIAL.NEXTVAL FROM DUAL";
		
		int id=this.queryForInt(indexSql);
		
		String sql="INSERT INTO T_WQS_TOWN_PICTORIAL(ID,DESCRIPT,FILENAME,FILEPATH,CREATE_TIME,TYPE_ID) VALUES(?,?,?,?,?,?)";
		
		int d = this.update(sql,new Object[]{id,
				Util.nullToStr(param.getDescript()),
				Util.nullToStr(param.getFileName()),
				Util.nullToStr(param.getFilePath()),
				new Date()
				,param.getTypeId()
				});
		if (d > 0) {
			return id;
		}
		
		return 0;
	}
	
	
	
	
	
	
	public int doShaTianOnePictorialDelete(int id){
		String sql="DELETE   T_WQS_TOWN_PICTORIAL WHERE ID=?";
		
		return this.update(sql,new Object[]{id});
		
	}
	
	public ShaTianPictorialModel getShaTianOnePictorialDelete(int id){
		String sql="SELECT * FROM   T_WQS_TOWN_PICTORIAL WHERE ID=?";
		
		List<ShaTianPictorialModel> datas=this.query(sql, new Object[]{id},new ShaTianPictorialModelRowMapper());
		
		if(datas!=null&&datas.size()>0){
			return datas.get(0);
		}
		
		return null;
	}
	
	
	public int doShaTianOnePictorialUpdate(ShaTianPictorialModel model){
		String sql="UPDATE T_WQS_TOWN_PICTORIAL SET DESCRIPT=?, FILENAME=?,FILEPATH=? WHERE ID=? ";
		
		return this.update(sql,new Object[]{model.getDescript(),model.getFileName(),model.getFilePath(),model.getId()});
		
	}
	
	
	
	public int doShaTianPictorialUpdate(int typeId,String name,String desc){
		String sql="UPDATE T_WQS_TOWN_PICTORIAL_TYPE SET TYPENAME=?,TYPE_DESC=? WHERE ID=?";
		
		return this.update(sql,new Object[]{name,desc,typeId});
	}
	
	
	
	
	
	
	
	
	@Override
	public Pagination getPictorialType(int pageNum, int pageSize) {
		String sql = "FROM T_WQS_TOWN_PICTORIAL_TYPE T WHERE 1=1";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0],
				new ShaTianPictorialTypeModelRowMapper(), pageSize, pageNum);
		return returnList;
	}
	@Override
	public Pagination getPictorialTypeAndPics(String searchName,int pageNum, int pageSize) {
		String sql = "FROM T_WQS_TOWN_PICTORIAL_TYPE T ";
		List param=new ArrayList();
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" WHERE TYPENAME LIKE ? ";
			param.add("%"+searchName+"%");
		}
		String qrySql = "SELECT T.*, (SELECT   WMSYS.WM_CONCAT(  FILEPATH)  FROM T_WQS_TOWN_PICTORIAL WHERE TYPE_ID=T.ID) FILEPATHS " + sql; 
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				 , param.toArray(),
				new ShaTianPictorialTypePicModelRowMapper(), pageSize, pageNum);
		return returnList;
	}

	@Override
	public Pagination getShaTianPictorial(int pageNum, int pageSize, int typeId) {
		String sql = "FROM T_WQS_TOWN_PICTORIAL T WHERE 1=1 AND TYPE_ID = ?";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID ASC";
		Object param[] = { typeId };
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new ShaTianPictorialModelRowMapper(),
				pageSize, pageNum);
		return returnList;
	}

	@Override
	public Pagination getTownVideo(int pageNum, int pageSize) {
		String sql = "FROM T_WQS_TOWN_VIDEO T WHERE 1=1";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Object param[] = {};
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new TownVideoModelRowMapper(), pageSize,
				pageNum);
		return returnList;
	}

	@Override
	public Pagination getEcoTourismType(int pageNum, int pageSize) {
		String sql = "FROM T_WQS_ECOTOURISM_PICTURE_TYPE T WHERE 1=1";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0],
				new EcoTourismPictorialTypeModelRowMapper(), pageSize, pageNum);
		return returnList;
	}

	@Override
	public Pagination getEcoTourism(int pageNum, int pageSize, int typeId) {
		String sql = "FROM T_WQS_ECOTOURISM_PICTURE T WHERE 1=1 AND TYPE_ID=?";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Object param[] = { typeId };
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new EcoTourismPictorialModelRowMapper(),
				pageSize, pageNum);
		return returnList;
	}

	@Override
	public int doTownVideoSave(TownVideoModel vo) {
		String insertSql = "INSERT INTO T_WQS_TOWN_VIDEO(ID,TITLE,FILE_NAME,FILE_PATH,AUTHOR,CREATE_TIME) VALUES (SEQ_T_WQS_TOWN_VIDEO.NEXTVAL,?,?,?,?,TO_DATE(?,'yyyy-MM-dd'))";
		Object[] params = { Util.nullToStr(vo.getTitle()),
				Util.nullToStr(vo.getFileName()),
				Util.nullToStr(vo.getFilePath()),
				Util.nullToStr(vo.getAuthor()), vo.getCreateTime() };
		int d = update(insertSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public int doTownVideoUpdate(TownVideoModel vo) {
		String updateSql = "UPDATE T_WQS_TOWN_VIDEO T SET T.TITLE = ?,T.FILE_NAME = ?,T.FILE_PATH=?,T.AUTHOR=?,T.CREATE_TIME = TO_DATE(?,'yyyy-MM-dd')  WHERE  T.ID = ?";
		Object[] params = { Util.nullToStr(vo.getTitle()),
				Util.nullToStr(vo.getFileName()),
				Util.nullToStr(vo.getFilePath()),
				Util.nullToStr(vo.getAuthor()),
				Util.nullToStr(vo.getCreateTime()), vo.getId() };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;

	}

	@Override
	public TownVideoModel getTownVideoById(String id) {
		TownVideoModel model = null;
		String sql = "SELECT T.ID,T.TITLE,T.FILE_NAME,T.FILE_PATH,T.AUTHOR,T.CREATE_TIME FROM T_WQS_TOWN_VIDEO T WHERE T.ID = ?";
		Object params[] = { Integer.valueOf(id) };
		model = (TownVideoModel) queryForObject(sql, params,
				new TownVideoModelRowMapper());
		return model;
	}

	@Override
	public int doTownVideoDel(String id) {
		String delSql = "DELETE FROM T_WQS_TOWN_VIDEO T WHERE T.ID = ?";
		Object params[] = { Integer.valueOf(id) };
		int d = this.update(delSql, params);

		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public Pagination getTownMessage(int page, int pagesize) {
		String sql = "FROM T_WQS_TOWN_MESSAGE T WHERE 1=1";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC";
		Object param[] = {};
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new TownMessageModelRowMapper(), pagesize,
				page);
		return returnList;
	}

	@Override
	public List getAllCountry() {
		String sql = "SELECT * FROM T_WQS_BEACOUNTRY T WHERE 1=1 ORDER BY T.ID ASC";
		Object param[] = {};
		List list = query(sql, param, new BeautifulCountryModelRowMapper());

		return list;
	}

	@Override
	public List getCountryVideo(long videoId) {
		String sql = "SELECT T.ID,T.ICON_NAME,T.ICON_PATH,T.VIDEO_NAME,T.VIDEO_PATH,T.COUNTRY_ID  FROM T_WQS_BEACOUNTRY_VIDEO T WHERE COUNTRY_ID = ?";
		Object params[] = { videoId };
		List list = query(sql, params,
				new BeautifulCountryVideoModelRowMapper());
		return list;
	}

	@Override
	public List getAlgGoods() {
		String sql = "SELECT T.ID,T.PRIVIEW_URL,T.NAME,T.DESCRIPT,T.ICON FROM T_WQS_ALGGOODS T ORDER BY ID ASC";
		Object params[] = {};
		List list = query(sql, params, new AlgGoodsModelRowMapper());
		return list;
	}

	@Override
	public Pagination getAlgGoods(int page, int pagesize) {
		String sql = "FROM T_WQS_ALGGOODS T WHERE 1=1";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID ASC";
		Object param[] = {};
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new AlgGoodsModelRowMapper(), pagesize, page);
		return returnList;
	}

	public Pagination getAlgGoods(String searchName,int page, int pagesize){
		String sql = "FROM T_WQS_ALGGOODS T WHERE 1=1";
		List param=new ArrayList();
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" AND NAME LIKE ? ";
			param.add("%"+searchName+"%");
		}
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID ASC";
		Pagination returnList = pagination(qrySql + orderSql, countSql , param.toArray(), new AlgGoodsModelRowMapper(), pagesize, page);
		return returnList;
	}
	
	
	
	
	@Override
	public AlgGoodsModel getAlgGoodsById(String typeId) {
		AlgGoodsModel model = null;
		String sql = "SELECT T.ID,T.PRIVIEW_URL,T.NAME,T.DESCRIPT,T.ICON FROM T_WQS_ALGGOODS T  WHERE T.ID = ? ";
		Object params[] = { Integer.parseInt(typeId) };
		model = (AlgGoodsModel) queryForObject(sql, params,
				new AlgGoodsModelRowMapper());
		return model;
	}

	@Override
	public int doAlgGoodsSave(AlgGoodsModel algGoodsModel) {
		String insertSql = "insert into T_WQS_ALGGOODS (ID, PRIVIEW_URL, NAME, DESCRIPT, ICON)  values (?, ?, ?, ?, ?) ";
		int id = queryForInt("select SEQ_T_WQS_ALGGOODS.NEXTVAL from dual ");
		Object[] params = { Integer.valueOf(id),
				Util.nullToStr(algGoodsModel.getPreviewUrl()),
				Util.nullToStr(algGoodsModel.getName()),
				Util.nullToStr(algGoodsModel.getDescript()),
				Util.nullToStr(algGoodsModel.getIcon()) };

		int d = update(insertSql, params);
		if (d > 0) {
			return id;
		}
		return 0;
	}

	
	@Override
	public int doAlgGoodsUpdate(AlgGoodsModel algGoodsModel){
		String sql="update T_WQS_ALGGOODS set PRIVIEW_URL=?, NAME=?, DESCRIPT=?, ICON=? where id=?";
		try {
			return this.update(sql,new Object[]{algGoodsModel.getPreviewUrl(),algGoodsModel.getName(),
					algGoodsModel.getDescript(),algGoodsModel.getIcon(),algGoodsModel.getId()});
		} catch (DataAccessException e) {
			log.error("在更新产品类型时，出现异常："+e.toString());
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
	
	
	
	@Override
	public int doAlgGoodsDelete(String id) {
		log.debug("[DELETESQL]..." + id);
		String updateSql = "DELETE FROM  T_WQS_ALGGOODS WHERE ID = ?";
		Object[] params = { Integer.valueOf(id) };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public List<?> getRestOrderByPhone(String phoneNumber) {
		String sql = "SELECT T.ID ,T.NAME,T.PHONENUMBER,T.NUM,T.PLACE,T.REST_TIME,T.ISVALID,T.REST_ID,T.GENDER FROM T_WQS_RESTAURANT_ORDER T WHERE T.PHONENUMBER = ?  AND T.ISVALID = '1' ORDER BY ID DESC";
		Object params[] = { phoneNumber };
		List list = query(sql, params, new RestaurantOrderModelRowMapper());
		return list;
	}

	@Override
	public int doRestOrderSubmit(RestOrderReqParam param) {
		int d = 0;
		int id = this
				.queryForInt("SELECT seq_t_wqs_restaurant_order.NEXTVAL FROM DUAL");
		String sql = "INSERT INTO T_WQS_RESTAURANT_ORDER T (ID,NAME,PHONENUMBER,NUM,PLACE,REST_TIME,REST_ID,GENDER ) VALUES (?,?,?,?,?,TO_DATE(?,'yyyy-MM-dd hh24:mi'),?,?)";
		Object params[] = { id, param.getName(), param.getPhoneNumber(),
				param.getNum(), param.getPlace(), param.getTime(),
				Integer.parseInt(param.getRestid()), param.getGender() };
		d = this.update(sql, params);
		if (d > 0) {
			return id;
		}
		return d;
	}

	@Override
	public List<?> getHotelRoomType(String hotelId) {
		String sql = "SELECT T.ID,T.NAME,T.PRICE ,T.HOTELID FROM T_WQS_HOTEL_ROOMTYPE T WHERE T.HOTELID = ? ";
		Object params[] = { Integer.parseInt(hotelId) };
		List list = query(sql, params, new RoomTypeModelRowMapper());
		return list;
	}

	@Override
	public int doHotelRoomTypeSave(RoomTypeModel roomTypeModel){
		String insertSql = "insert into T_WQS_HOTEL_ROOMTYPE (ID, NAME, PRICE, ISVALID, HOTELID)  values (?, ?, ?, ?, ?) ";
		int id = queryForInt("select SEQ_T_WQS_ROOMTYPE	.NEXTVAL from dual ");
		Object[] params = { Integer.valueOf(id),
				Util.nullToStr(roomTypeModel.getName()),
				Util.nullToStr(roomTypeModel.getPrice()),
				null,
				roomTypeModel.getHotelid()};
		int d = update(insertSql, params);
		if (d > 0) {
			return id;
		}
		return 0;
	}
	
	@Override
	public int doHotelRoomTypeDeleteByHotelId(String hotelId){
		log.debug("[DELETESQL]..." + hotelId);
		String updateSql = "DELETE FROM  T_WQS_HOTEL_ROOMTYPE WHERE HOTELID = ?";
		Object[] params = { Integer.valueOf(hotelId) };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}
	
	@Override
	public int doHotelOrderSubmit(HotelOrderReqParam param) {
		int d = 0;
		int id = this
				.queryForInt("SELECT seq_T_WQS_HOTEL_ORDER.NEXTVAL FROM DUAL");
		String sql = "INSERT INTO T_WQS_HOTEL_ORDER T (ID,HOTELID,NAME,PHONE,IN_TIME,OUT_TIME,GENDER ) VALUES (?,?,?,?,TO_DATE(?,'yyyy-MM-dd'),TO_DATE(?,'yyyy-MM-dd'),?)";
		Object params[] = { id, param.getHotelid(), param.getName(),
				param.getPhoneNumber(), param.getIntime(), param.getOuttime(),
				param.getGender() };
		d = this.update(sql, params);
		if (d > 0) {
			return id;
		}
		return d;
	}

	@Override
	public int doHotelOrderDetailSave(String typeid, String num, int orderid) {
		int d = 0;
		String sql = "INSERT INTO t_wqs_hotel_order_detail T (ID,TYPEID,NUM,ORDERID ) VALUES (seq_t_wqs_hotel_order_detail.NEXTVAL,?,?,?)";
		Object params[] = { Integer.parseInt(typeid), Integer.parseInt(num),
				orderid };
		d = this.update(sql, params);
		if (d > 0) {
			return d;
		}
		return d;
	}

	@Override
	public List<?> getHotelOrderByPhone(WqsTravelReqParam param) {
		String sql = "select t.*,(t.out_time-t.in_time) DAYS from t_wqs_hotel_order t  where hotelid = ? and phone = ?  and isvalid = '1 ' ORDER BY ID DESC ";
		Object params[] = { Integer.parseInt(param.getHotelid()),
				param.getPhoneNumber() };
		List list = query(sql, params, new HotelOrderModelRowMapper());
		return list;
	}

	@Override
	public List<?> getHotelOrderDetail(long orderId) {
		String sql = "select  t.*,a.name TYPENAME, a.price  from t_wqs_hotel_order_detail t , t_wqs_hotel_roomtype a where t.typeid = a.id AND orderid = ? ORDER BY t.ID DESC ";
		Object params[] = { orderId };
		List list = query(sql, params, new HotelOrderDetailModelRowMapper());
		return list;
	}

	@Override
	public int doRestOrderCancel(String orderid) {
		int d = 0;
		String sql = "UPDATE t_wqs_restaurant_order T SET T.ISVALID = '3' WHERE T.ID = ?";
		Object params[] = { Integer.parseInt(orderid) };
		d = this.update(sql, params);
		if (d > 0) {
			return d;
		}
		return d;
	}

	@Override
	public int doHotelOrderCancel(String orderid) {
		int d = 0;
		String sql = "UPDATE t_wqs_hotel_order T SET T.ISVALID = '3' WHERE T.ID = ?";
		Object params[] = { Integer.parseInt(orderid) };
		d = this.update(sql, params);
		if (d > 0) {
			return d;
		}
		return d;
	}

	@Override
	public Pagination getTownMsgList(int pageNum, int pageSize) {
		String sql = "FROM T_WQS_TOWN_MESSAGE T WHERE 1=1";
		String qrySql = "SELECT T.ID,T.TITLE,T.UNIT,T.CREATETIME,T.FILE_URL,T.HTML_VALUE "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0], new TownMessageModelRowMapper(),
				pageSize, pageNum);
		return returnList;
	}

	@Override
	public Pagination getHospitalList(String page, String pagesize) {
		String sql = "FROM T_WQS_HOSPITAL T WHERE 1=1";
		String qrySql = "SELECT T.ID,T.NAME,T.ADDRESS,T.TELE1,T.TELE2,T.TELE3,T.HTML_FILE,T.HTML_VALUE "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ORDERS ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0], new HospitalModelRowMapper(),
				Integer.parseInt(pagesize), Integer.parseInt(page));
		return returnList;
	}

	@Override
	public Pagination getHospitalList(String searchName,String searchAdd,String page, String pagesize){
		String sql = "FROM T_WQS_HOSPITAL T WHERE 1=1";
		List param=new ArrayList();
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" AND T.NAME LIKE ? ";
			param.add("%"+searchName+"%");
		}
		
		if(searchAdd!=null&&!"".equals(searchAdd)){
			sql+=" AND T.ADDRESS LIKE ? ";
			param.add("%"+searchAdd+"%");
		}
		
		
		String qrySql = "SELECT T.ID,T.NAME,T.ADDRESS,T.TELE1,T.TELE2,T.TELE3,T.HTML_FILE,T.HTML_VALUE "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ORDERS ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				 , param.toArray(), new HospitalModelRowMapper(),
				Integer.parseInt(pagesize), Integer.parseInt(page));
		return returnList;
	}
	
	
	
	
	@Override
	public int doHospitalSave(HospitalModel vo) {
		log.debug("[INSERTSQL]...");
		String updateSql = "INSERT INTO T_WQS_HOSPITAL (ID,NAME,ADDRESS,TELE1,TELE2,TELE3,HTML_FILE,HTML_VALUE) VALUES (SEQ_T_WQS_HOSPITAL.NEXTVAL,?,?,?,?,?,?,?)";
		Object[] params = { Util.nullToStr(vo.getName()),
				Util.nullToStr(vo.getAddress()), Util.nullToStr(vo.getTele1()),
				Util.nullToStr(vo.getTele2()), Util.nullToStr(vo.getTele3()),
				Util.nullToStr(vo.getFileUrl()),
				Util.nullToStr(vo.getContentStr()) };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	/*
	 * public int doTownNewsSave(TownMessageModel vo) { String updateSql =
	 * "INSERT INTO T_WQS_TOWN_NEWS (ID,TITLE,UNIT,CREATETIME,FILE_URL,HTML_VALUE,TYPE_ID) VALUES (SEQ_T_WQS_TOWN_NEWS.NEXTVAL,?,?,TO_DATE(?,'yyyy-MM-dd'),?,?,?)"
	 * ; Object[] params =
	 * {Util.nullToStr(vo.getTitle()),Util.nullToStr(vo.getUnit()),
	 * Util.nullToStr
	 * (vo.getCreateTime()),Util.nullToStr(vo.getFileUrl()),Util.nullToStr
	 * (vo.getContentStr()),vo.getTypeId()}; int d = update(updateSql, params);
	 * if(d>0){ return d; } return 0; }
	 */

	@Override
	public int doHospitalDelete(String id) {
		// TODO Auto-generated method stub
		log.debug("[DELETESQL]..." + id);
		String updateSql = "DELETE FROM  T_WQS_HOSPITAL WHERE ID = ?";
		Object[] params = { Integer.valueOf(id) };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	
	@Override
	public HospitalModel getHospitalById(int id){
		String sql="SELECT ID,NAME,ADDRESS,TELE1,TELE2,TELE3,HTML_FILE,HTML_VALUE FROM T_WQS_HOSPITAL  WHERE ID = ?";
		List<HospitalModel> datas=this.query(sql, new Object[]{id},  new HospitalModelRowMapper());
		if(datas!=null&&datas.size()>0){
			return datas.get(0);
		}
		return null;
	}
	
	
	@Override
	public int doHospitalUpdate(final HospitalModel model){
		String sql="UPDATE T_WQS_HOSPITAL SET NAME=?,ADDRESS=?,TELE1=?,TELE2=?,TELE3=?,HTML_FILE=?,HTML_VALUE=? WHERE ID=? ";
		int d = 0;
		try {
			d = this.update(sql, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) {
					try {
						ps.setString(1, model.getName());
						ps.setString(2, model.getAddress());
						ps.setString(3, model.getTele1());
						ps.setString(4, model.getTele2());
						ps.setString(5, model.getTele3());
						ps.setString(6, model.getHtmlFile());
						lobHandler.getLobCreator().setClobAsString(ps, 7,
								ClobUtil.clobToString(model.getHtmlValue()));
						ps.setLong(8, model.getId());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (DataAccessException e) {
			log.error("在更新医疗服务时，出现异常："+e.toString());
			e.printStackTrace();
		}
		
		
		
		
		return d;
		
	}
	
	
	@Override
	public RestaurantOrderModel getRestOrderById(int id) {

		String sql = "SELECT T.ID ,T.NAME,T.PHONENUMBER,T.NUM,T.PLACE,T.REST_TIME,T.ISVALID,T.REST_ID,T.GENDER FROM T_WQS_RESTAURANT_ORDER T WHERE T.ID = ?  AND T.ISVALID = '1' ORDER BY ID DESC";
		Object params[] = { id };
		RestaurantOrderModel model = (RestaurantOrderModel) queryForObject(sql,
				params, new RestaurantOrderModelRowMapper());
		return model;
	}

	@Override
	public HotelOrderModel getHotelOrderById(int id) {
		String sql = "select t.*,(t.out_time-t.in_time) DAYS from t_wqs_hotel_order t  where t.id=? and isvalid = '1 ' ORDER BY ID DESC ";
		Object params[] = { id };
		HotelOrderModel model = (HotelOrderModel) queryForObject(sql, params,
				new HotelOrderModelRowMapper());
		return model;
	}

	@Override
	public Pagination getEnterpriseList(String page, String pagesize) {
		String sql = "FROM T_WQS_TOWN_ENTERPRISE T WHERE 1=1";
		String qrySql = "SELECT T.ID,T.NAME,T.HTML_PATH,T.ICON,T.HTML_VALUE "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ORDERS ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0], new EnterpriseModelRowMapper(),
				Integer.parseInt(pagesize), Integer.parseInt(page));
		return returnList;
	}

	@Override
	public Pagination getHotelList(String page, String pagesize) {
		String sql = "FROM T_WQS_HOTEL T WHERE 1=1";
		String qrySql = "SELECT T.ID,T.NAME ,T.TELE,T.ADDR,T.HTML_INFO,T.HTML_VALUE "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ORDERS ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0], new HotelModelRowMapper(),
				Integer.parseInt(pagesize), Integer.parseInt(page));
		return returnList;
	}
	
	
	public List<HotelModel> getHotelRestaurantList(int type){
		String sql="SELECT H.ID,H.NAME ,H.TELE,H.ADDR,H.HTML_INFO  FROM T_WQS_HOTEL H " +
		" UNION " +
		" SELECT R.ID,R.NAME ,R.TELE,R.ADDR,R.HTML_INFO  FROM T_WQS_RESTAURANT R  ";
		if(type==2){
			 sql="SELECT R.ID,R.NAME ,R.TELE,R.ADDR,R.HTML_INFO  FROM T_WQS_RESTAURANT R " ;
		}else if(type==1){
			sql="SELECT H.ID,H.NAME ,H.TELE,H.ADDR,H.HTML_INFO  FROM T_WQS_HOTEL H " ;
		}
		try {
			return this.query(sql,new HotelModelRowMap());
		} catch (DataAccessException e) {
			log.error("在查询酒店和餐馆列表时，出现异常："+e.toString());
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
	@Override
	public Pagination getHotelList(String searchName,String searchAdd,String page, String pagesize){
		String sql = "FROM T_WQS_HOTEL T WHERE 1=1";
		
		List param=new ArrayList();
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" AND T.NAME LIKE ? ";
			param.add("%"+searchName+"%");
		}
		if(searchAdd!=null&&!"".equals(searchAdd)){
			sql+=" AND T.ADDR LIKE ? ";
			param.add("%"+searchAdd+"%");
		}
		
		String qrySql = "SELECT T.ID,T.NAME ,T.TELE,T.ADDR,T.HTML_INFO,T.HTML_VALUE "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ORDERS ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql , param.toArray(), new HotelModelRowMapper(),
				Integer.parseInt(pagesize), Integer.parseInt(page));
		return returnList;
		
		
	}
	
	
	@Override
	public Pagination getRestaurantList(String searchName,String searchAdd,String page, String pagesize){
		String sql = "FROM T_WQS_RESTAURANT T WHERE 1=1";
		
		List param=new ArrayList();
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" AND T.NAME LIKE ? ";
			param.add("%"+searchName+"%");
		}
		if(searchAdd!=null&&!"".equals(searchAdd)){
			sql+=" AND T.ADDR LIKE ? ";
			param.add("%"+searchAdd+"%");
		}
		
		String qrySql = "SELECT ID,NAME,ADDR,TELE,HTML_INFO,HTML_VALUE "
			+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ORDERS ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql , param.toArray(), new HotelModelRowMapper(),
				Integer.parseInt(pagesize), Integer.parseInt(page));
		return returnList;
		
		
	}
	
	
	
	@Override
	public HotelModel getHotelById(int id) {
		HotelModel model = null;
		String sql = "SELECT T.ID,T.NAME ,T.TELE,T.ADDR,T.HTML_INFO,T.HTML_VALUE FROM T_WQS_HOTEL T  WHERE T.ID = ? ";
		Object params[] = { id };
		model = (HotelModel) queryForObject(sql, params,
				new HotelModelRowMapper());
		return model;
	}
	
	@Override
	public HotelModel getRestaurantById(int id) {
		HotelModel model = null;
		String sql = "SELECT T.ID,T.NAME ,T.TELE,T.ADDR,T.HTML_INFO,T.HTML_VALUE FROM T_WQS_RESTAURANT T  WHERE T.ID = ? ";
		Object params[] = { id };
		model = (HotelModel) queryForObject(sql, params,
				new HotelModelRowMapper());
		return model;
	}
	
	
	
	
	
	@Override
	public int doHotelSave(HotelModel hotelModel){
		String insertSql = "insert into T_WQS_HOTEL (ID, NAME, TELE, ADDR, HTML_INFO, HTML_VALUE, ORDERS, ICONS)  values (?, ?, ?, ?, ?, ?, ?, ?) ";
		int id = queryForInt("select SEQ_T_WQS_HOTEL.NEXTVAL from dual ");
		hotelModel.setId(id);
		Object[] params = { Integer.valueOf(id),
				Util.nullToStr(hotelModel.getName()),
				Util.nullToStr(hotelModel.getTele()),
				Util.nullToStr(hotelModel.getAddr()),
				Util.nullToStr(hotelModel.getHtmlInfo()), 
				Util.nullToStr(hotelModel.getHtmlContent()),
				Integer.valueOf(id),
				null};

		int d = update(insertSql, params);
		if (d > 0) {
			return id;
		}
		return 0;
	}
	
	
	
	@Override
	public int doHotelUpdate(HotelModel hotelModel){
		String sql="update T_WQS_HOTEL set name=?,addr=?,tele=?,html_info=?,html_value=? where id=? ";
		int d =0;
		try {
			d=this.update(sql,new Object[]{hotelModel.getName(),hotelModel.getAddr(),hotelModel.getTele()
					,hotelModel.getHtmlInfo(),hotelModel.getHtmlContent(),hotelModel.getId()});
		} catch (DataAccessException e) {
			log.error("在更新酒店信息时，出现异常："+e.toString());
			e.printStackTrace();
		}
		return d;
	}
	
	
	
	@Override
	public int doRestaurantSave(HotelModel hotelModel){
		int id = queryForInt("SELECT SEQ_T_WQS_RESTAURANT.NEXTVAL FROM DUAL ");
		String insertSql = "INSERT INTO T_WQS_RESTAURANT (ID, NAME, TELE, ADDR, HTML_INFO, HTML_VALUE, ORDERS)  values (?, ?, ?, ?, ?, ?, ?) ";
		hotelModel.setId(id);
		Object[] params = { Integer.valueOf(id),
				Util.nullToStr(hotelModel.getName()),
				Util.nullToStr(hotelModel.getTele()),
				Util.nullToStr(hotelModel.getAddr()),
				Util.nullToStr(hotelModel.getHtmlInfo()), 
				Util.nullToStr(hotelModel.getHtmlContent()),
				Integer.valueOf(id) 
				 };
		
		int d=0;
		try {
			d = update(insertSql, params);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error("在保存餐馆信息时，出现异常："+e.toString());
		}
		if (d > 0) {
			return id;
		}
		return d;
	}
	
	
	@Override
	public int doRestaurantUpdate(HotelModel hotelModel){
		String sql="UPDATE T_WQS_RESTAURANT SET NAME=?,TELE=?,ADDR=?,HTML_INFO=?,HTML_VALUE=? WHERE ID=? ";
		int d=0;
		try {
			d=this.update(sql,new Object[]{hotelModel.getName(),hotelModel.getTele(),hotelModel.getAddr(),hotelModel.getHtmlInfo(),hotelModel.getHtmlContent(),hotelModel.getId()});
		} catch (DataAccessException e) {
			log.error("在更新餐馆信息时，出现异常:"+e.toString());
			e.printStackTrace();
		}
		
		return d;
	}
	
	
	
	
	
	@Override
	public int doHotelDelete(String id){
		log.debug("[DELETESQL]..." + id);
		String updateSql = "DELETE FROM  T_WQS_HOTEL WHERE ID = ?";
		Object[] params = { Integer.valueOf(id) };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}
	@Override
	public int doRestaurantDelete(int id){ 
		String updateSql = "DELETE FROM  T_WQS_RESTAURANT WHERE ID = ?";
		Object[] params = { id };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public List getHotelIconById(long id) {
		String sql = "SELECT T.ID,T.NAME,T.PATH,T.HOTEL_ID FROM T_WQS_HOTEL_ICON T WHERE T.HOTEL_ID = ? ORDER BY ID ASC";
		Object param[] = { id };
		List list = query(sql, param, new IconMappingModelRowMapper());

		return list;
	}
	@Override
	public List getRestaurantIconById(long id) { 
		String sql = "SELECT T.ID,T.NAME,T.PATH,T.HOTEL_ID FROM T_WQS_RESTAURANT_ICON T WHERE T.HOTEL_ID = ? ORDER BY ID ASC";
		Object param[] = { id };
		List list = query(sql, param, new IconMappingModelRowMapper());
		
		return list;
	}
	
	@Override
	public int doHotelIconSave(IconMappingModel iconMapping){
		String insertSql = "insert into T_WQS_HOTEL_ICON (ID, NAME, PATH, HOTEL_ID)  values (?, ?, ?, ?) ";
		int id = queryForInt("select SEQ_T_WQS_ICONMAPPING.NEXTVAL from dual ");
		Object[] params = { Integer.valueOf(id),
				Util.nullToStr(iconMapping.getName()),
				Util.nullToStr(iconMapping.getPath()),
				iconMapping.getForeignId()};

		int d = update(insertSql, params);
		if (d > 0) {
			return id;
		}
		return 0;
	}
	@Override
	public int doRestaurantIconSave(IconMappingModel iconMapping){ 
		String insertSql = "INSERT INTO T_WQS_RESTAURANT_ICON (ID, NAME, PATH, HOTEL_ID)  VALUES (?, ?, ?, ?) ";
		int id = queryForInt("SELECT SEQ_T_WQS_RESTAURANT_ICON.NEXTVAL FROM DUAL ");
		Object[] params = { Integer.valueOf(id),
				Util.nullToStr(iconMapping.getName()),
				Util.nullToStr(iconMapping.getPath()),
				iconMapping.getForeignId()};
		int d=0;
		try {
			d = update(insertSql, params);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if (d > 0) {
			return id;
		}
		return d;
	}

	@Override
	public int doHotelIconDeleteByHotelId(String hotelId){
		log.debug("[DELETESQL]..." + hotelId);
		String updateSql = "DELETE FROM  T_WQS_HOTEL_ICON WHERE HOTEL_ID = ?";
		Object[] params = { Integer.valueOf(hotelId) };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}
	
	@Override
	public int   doRestaurantIconDeleteByHotelId(int hotelId){
		String updateSql = "DELETE FROM  T_WQS_RESTAURANT_ICON WHERE HOTEL_ID = ?";
		Object[] params = { hotelId};
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}
	
	@Override
	public Pagination getRestaurantList(String page, String pagesize) {
		String sql = "FROM T_WQS_RESTAURANT T WHERE 1=1";
		String qrySql = "SELECT T.ID,T.NAME ,T.TELE,T.ADDR,T.HTML_INFO,T.HTML_VALUE "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ORDERS ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0], new HotelModelRowMapper(),
				Integer.parseInt(pagesize), Integer.parseInt(page));
		return returnList;
	}

	@Override
	public List getRestaurantList(long id) {
		String sql = "SELECT T.ID,T.NAME,T.PATH,T.HOTEL_ID FROM T_WQS_RESTAURANT_ICON T WHERE T.HOTEL_ID = ? ORDER BY ID ASC";
		Object param[] = { id };
		List list = query(sql, param, new IconMappingModelRowMapper());

		return list;
	}

	@Override
	public Pagination getNongJiaLe(String page, String pagesize, String typeid) {
		String sql = "FROM T_WQS_NONGJIALE T WHERE 1=1 ";
		if (!"-1".equals(typeid)) {
			sql = sql + "AND TYPE = '" + typeid + "'";
		}
		String qrySql = "SELECT T.ID,T.NAME ,T.TELE,T.ADDR " + sql;
		String countSql = "SELECT COUNT(1) " + sql;
		String orderSql = " ORDER BY T.ORDERS ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0], new HotelModelRowMapper(),
				Integer.parseInt(pagesize), Integer.parseInt(page));
		return returnList;
	}

	public void Test() {
		this.update("");
	}

	@Override
	public List getYujiaInfo(String page, String pagesize) {
		String sql = "SELECT T.ID,T.NAME,T.ICONS,T.HTML_INFO,T.HTML_VALUE  FROM T_WQS_YUJIALE T";
		Object param[] = {};
		List list = query(sql, param, new YujialeModelRowMapper());

		return list;
	}
	@Override
	public YujialeModel getYujiaInfoById(int id){
		String sql = "SELECT T.ID,T.NAME,T.ICONS,T.HTML_INFO,T.HTML_VALUE  FROM T_WQS_YUJIALE T";
		Object param[] = {};
		List<YujialeModel> list;
		try {
			list = this.query(sql, param, new YujialeModelRowMapper());
			if(list!=null&&list.size()>0) return list.get(0);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int  doYujiaInfoUpdate(YujialeModel model){
		String sql="UPDATE T_WQS_YUJIALE SET NAME=?,ICONS=?,HTML_INFO=?,HTML_VALUE=? WHERE ID=?";
		int d=0;
		try {
			d=this.update(sql,new Object[]{model.getName(),model.getIcons(),model.getDesc(),model.getContentStr(),model.getId()});
		} catch (DataAccessException e) {
			log.error("在更新渔家乐简介信息时，出现异常："+e.toString());
			e.printStackTrace();
		}
		return d;
		
		
	}
	@Override
	public Pagination getTownVideoQuery(int pageNum, int pageSize,
			TownVideoModel videoModel) {
		String sql = " FROM T_WQS_TOWN_VIDEO T WHERE 1=1 ";
		if (!StringUtils.isEmpty(videoModel.getTitle())) {
			sql = sql + "AND T.TITLE LIKE '%" + videoModel.getTitle() + "%'";
		}
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Object param[] = {};
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new TownVideoModelRowMapper(), pageSize,
				pageNum);
		return returnList;
	}

	@Override
	public TownMessageModel getTownMsgById(String id) {
		String sql = "SELECT T.ID,T.TITLE,T.UNIT,T.CREATETIME,T.FILE_URL,T.HTML_VALUE,T.VALUE1,T.VALUE2 FROM T_WQS_TOWN_MESSAGE T WHERE ID = ?";
		Object params[] = { id };
		TownMessageModel model = (TownMessageModel) queryForObject(sql, params,
				new TownMessageModelRowMapper());
		return model;
	}

	@Override
	public int doTownMsgUpdate(TownMessageModel vo) {
		String updateSql = "UPDATE T_WQS_TOWN_MESSAGE T SET T.TITLE = ?,T.UNIT=?,T.CREATETIME=TO_DATE(?,'yyyy-MM-dd'),T.FILE_URL=?,T.HTML_VALUE=? WHERE T.ID = ?";
		Object[] params = { Util.nullToStr(vo.getTitle()),
				Util.nullToStr(vo.getUnit()),
				Util.nullToStr(vo.getCreateTime()),
				Util.nullToStr(vo.getFileUrl()),
				Util.nullToStr(vo.getContentStr()), vo.getId() };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public int doTownMsgSave(TownMessageModel vo) {
		String updateSql = "INSERT INTO T_WQS_TOWN_MESSAGE (ID,TITLE,UNIT,CREATETIME,FILE_URL,HTML_VALUE) VALUES (SEQ_T_WQS_TOWN_MESSAGE.NEXTVAL,?,?,TO_DATE(?,'yyyy-MM-dd'),?,?)";
		Object[] params = { Util.nullToStr(vo.getTitle()),
				Util.nullToStr(vo.getUnit()),
				Util.nullToStr(vo.getCreateTime()),
				Util.nullToStr(vo.getFileUrl()),
				Util.nullToStr(vo.getContentStr()) };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public int doTownMsgDelete(String id) {
		String updateSql = "DELETE FROM  T_WQS_TOWN_MESSAGE WHERE ID = ?";
		Object[] params = { Integer.valueOf(id) };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public Pagination getTownMsgQuery(int pageNum, int pageSize,
			TownMessageModel msgModel) {
		String sql = " FROM T_WQS_TOWN_MESSAGE T WHERE 1=1 ";
		if (!StringUtils.isEmpty(msgModel.getTitle())) {
			sql = sql + "AND T.TITLE LIKE '%" + msgModel.getTitle() + "%'";
		}
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Object param[] = {};
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new TownMessageModelRowMapper(), pageSize,
				pageNum);
		return returnList;
	}

	@Override
	public Pagination getTownNews(int pageNum, int pageSize, String typeId) {
		String sql = "FROM T_WQS_TOWN_NEWS T WHERE 1=1 AND TYPE_ID =?";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC";
		Object param[] = { typeId };
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new TownMessageModelRowMapper(), pageSize,
				pageNum);
		return returnList;
	}
	
	@Override
	public Pagination getTownNews(String searchName,String searchUnit,int pageNum, int pageSize, String typeId){
		String sql = "FROM T_WQS_TOWN_NEWS T WHERE 1=1 AND TYPE_ID =?";
		List param=new ArrayList();
		param.add(typeId);
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" AND TITLE LIKE ? ";
			param.add("%"+searchName+"%");
		}
		if(searchUnit!=null&&!"".equals(searchUnit)){
			sql+=" AND UNIT LIKE ? ";
			param.add("%"+searchUnit+"%");
		}
		
		
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC";
		Pagination returnList = pagination(qrySql + orderSql, countSql , param.toArray(), new TownMessageModelRowMapper(), pageSize,
				pageNum);
		return returnList;
	}
	@Override
	public Pagination getTownJobs(int pageNum, int pageSize) {
		String sql = "FROM T_WQS_TOWN_JOBS T WHERE 1=1 ";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC";
		Object param[] = {};
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new TownMessageModelRowMapper(), pageSize,
				pageNum);
		return returnList;
	}

	@Override
	public Pagination getTownTraffic(int pageNum, int pageSize) {
		String sql = "FROM T_WQS_TOWN_TRAFFIC T WHERE 1=1 ";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC";
		Object param[] = {};
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new TownMessageModelRowMapper(), pageSize,
				pageNum);
		return returnList;
	}

	@Override
	public Pagination getTownNewsQuery(int pageNum, int pageSize,
			TownMessageModel msgModel) {
		String sql = " FROM T_WQS_TOWN_NEWS T WHERE 1=1 AND T.TYPE_ID=?";
		if (!StringUtils.isEmpty(msgModel.getTitle())) {
			sql = sql + "AND T.TITLE LIKE '%" + msgModel.getTitle() + "%'";
		}
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Object param[] = { msgModel.getTypeId() };
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new TownMessageModelRowMapper(), pageSize,
				pageNum);
		return returnList;
	}

	@Override
	public TownMessageModel getTownNewsById(String id) {
		String sql = "SELECT T.ID,T.TITLE,T.TYPE_ID,T.UNIT,T.CREATETIME,T.FILE_URL,T.HTML_VALUE,T.VALUE1,T.VALUE2 FROM T_WQS_TOWN_NEWS T WHERE ID = ?";
		Object params[] = { id };
		TownMessageModel model = (TownMessageModel) queryForObject(sql, params,
				new TownMessageModelRowMapper());
		return model;
	}

	@Override
	public int doTownNewsUpdate(TownMessageModel vo) {
		String updateSql = "UPDATE T_WQS_TOWN_NEWS T SET T.TITLE = ?,T.UNIT=?,T.CREATETIME=TO_DATE(?,'yyyy-MM-dd'),T.FILE_URL=?,T.HTML_VALUE=? ,T.TYPE_ID=? WHERE T.ID = ?";
		Object[] params = { Util.nullToStr(vo.getTitle()),
				Util.nullToStr(vo.getUnit()),
				Util.nullToStr(vo.getCreateTime()),
				Util.nullToStr(vo.getFileUrl()),
				Util.nullToStr(vo.getContentStr()), vo.getTypeId(), vo.getId() };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public int doTownNewsSave(TownMessageModel vo) {
		String updateSql = "INSERT INTO T_WQS_TOWN_NEWS (ID,TITLE,UNIT,CREATETIME,FILE_URL,HTML_VALUE,TYPE_ID) VALUES (SEQ_T_WQS_TOWN_NEWS.NEXTVAL,?,?,TO_DATE(?,'yyyy-MM-dd'),?,?,?)";
		Object[] params = { Util.nullToStr(vo.getTitle()),
				Util.nullToStr(vo.getUnit()),
				Util.nullToStr(vo.getCreateTime()),
				Util.nullToStr(vo.getFileUrl()),
				Util.nullToStr(vo.getContentStr()), vo.getTypeId() };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public int doTownNewsDelete(String id) {
		String updateSql = "DELETE FROM  T_WQS_TOWN_NEWS WHERE ID = ?";
		Object[] params = { Integer.valueOf(id) };
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	public Pagination getFeedBackList(String pageNum, String pageSize) {

		String QRY_SQL = "SELECT T.ID,T.CONTENT,T.OPERATORNAME,T.PHONE,T.SUBMIT_TIME ,T.ISREPLY FROM T_WQS_FEEDBACK T WHERE 1=1  ";
		String COT_SQL = "SELECT COUNT(*) FROM T_WQS_FEEDBACK T WHERE 1 = 1";
		String ORD_SQL = " ORDER BY T.ID DESC ";
		Pagination returnList = pagination(QRY_SQL + ORD_SQL,
				COT_SQL + ORD_SQL, new Object[0],
				new NsFeedbackModelRowMapper(), Integer.valueOf(pageSize)
						.intValue(), Integer.valueOf(pageNum).intValue());
		return returnList;
	}

	public List getFeedbackReplyList(String id) {
		String QRY_SQL = "SELECT T.ID,T.FEEDBACK_ID,T.REPLY_NAME,T.CONTENT,T.REPLY_TIME  FROM T_WQS_FEEDBACK_REPLY T WHERE 1=1 AND T.FEEDBACK_ID = ?";
		String ORD_SQL = " ORDER BY T.ID DESC ";
		Object params[] = { Integer.valueOf(id) };
		List list = query(QRY_SQL + ORD_SQL, params,
				new NsFeedbackReplyModelRowMapper());

		return list;
	}

	public int doFeedbackSave(NsFeedbackModel model) {
		String insertSql = "INSERT INTO T_WQS_FEEDBACK (ID,CONTENT,OPERATORNAME,PHONE,SUBMIT_TIME) VALUES (?,?,?,?,sysdate)";
		int id = queryForInt("SELECT SEQ_T_WQS_FEEDBACK.NEXTVAL FROM DUAL");
		Object[] params = { id, model.getContent(), model.getOperatorName(),
				model.getPhone() };
		int d = update(insertSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	public NsFeedbackModel getFeedBackById(String id) {
		NsFeedbackModel model = new NsFeedbackModel();
		String querySql = "SELECT * FROM T_WQS_FEEDBACK WHERE ID=?";
		List list = (List) this
				.query(querySql,
						new Object[] { Integer.valueOf(id).intValue() },
						new RowMapperResultSetExtractor(
								new NsFeedbackModelRowMapper()));
		if (list.size() > 0)
			model = (NsFeedbackModel) list.get(0);

		return model;
	}

	public int doFeedbackReplySave(NsFeedbackReplyModel replyModel) {
		String insertSql = "INSERT INTO T_WQS_FEEDBACK_REPLY (ID,FEEDBACK_ID,REPLY_NAME,REPLY_TIME,CONTENT) VALUES (?,?,?,SYSDATE,?)";
		int id = queryForInt("SELECT SEQ_T_WQS_FEEDBACK_REPLY.NEXTVAL FROM DUAL");
		Object[] params = { id, replyModel.getFeedbackId(),
				replyModel.getReplyName(), replyModel.getContent() };
		int d = update(insertSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	public int updateFeedbackStatus(long id, String status) {
		String insertSql = "UPDATE T_WQS_FEEDBACK T SET T.ISREPLY = ? WHERE T.ID = ?";
		Object[] params = { status, id };
		int d = update(insertSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	public int doFeedbackReplyDelete(String replyId) {
		int id = Integer.valueOf(replyId);
		String querySql = "DELETE FROM T_WQS_FEEDBACK_REPLY WHERE ID = " + id;
		int d = this.update(querySql);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	public int doFeedbackDelete(String feedbackId) {
		int id = Integer.valueOf(feedbackId);
		String querySql = "DELETE FROM T_WQS_FEEDBACK WHERE ID = " + id;
		int d = this.update(querySql);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public Pagination getTownEntList(int pageNum, int pageSize) {
		String sql = "FROM T_WQS_TOWN_ENTERPRISE T WHERE 1=1";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(*)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0], new EnterpriseModelRowMapper(),
				pageSize, pageNum);
		return returnList;
	}
	@Override
	public Pagination getTownEntList(String searchName,int pageNum, int pageSize) {
		String sql = "FROM T_WQS_TOWN_ENTERPRISE T WHERE 1=1";
		List param=new ArrayList();
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" AND NAME LIKE ? ";
			param.add("%"+searchName+"%");
		}
		
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(*)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				 , param.toArray(), new EnterpriseModelRowMapper(),
				pageSize, pageNum);
		return returnList;
	}

	
	public EnterpriseModel getEnterpriseModel(long townId){
		String sql="SELECT ID,ICON,NAME,HTML_PATH,HTML_VALUE FROM T_WQS_TOWN_ENTERPRISE WHERE ID=? ";
		List<EnterpriseModel> datas=this.query(sql, new Object[]{townId}, new EnterpriseModelRowMapper());
		if(datas!=null&&datas.size()>0){
			return datas.get(0);
		}
		return null;
	}
	public int doEnterpriseModelUpdate(final EnterpriseModel model){
		String sql="UPDATE T_WQS_TOWN_ENTERPRISE SET NAME=?,HTML_PATH=?,HTML_VALUE=? WHERE ID=? ";
		int d = 0;
		try {
//			return this.update(sql,new Object[]{model.getName(),model.getHtmlPath(),model.getHtmlValue(),model.getId()});
			d = this.update(sql, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) {
					try {
						ps.setString(1, model.getName());
						ps.setString(2, model.getHtmlPath());
						lobHandler.getLobCreator().setClobAsString(ps, 3,
								ClobUtil.clobToString(model.getHtmlValue()));
						ps.setLong(4, model.getId());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (DataAccessException e) {
			log.error("在更新企业名录时，出现异常："+e.toString());
			e.printStackTrace();
		}
		return d;
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public int doTownEntDelete(String id) {
		String delSql = "DELETE FROM T_WQS_TOWN_ENTERPRISE T WHERE T.ID = ?";
		Object params[] = { Integer.valueOf(id) };
		int d = this.update(delSql, params);

		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public int doTownEntSave(final EnterpriseModel param) {
		int d = 0;
		final int id = this
				.queryForInt("SELECT SEQ_T_WQS_TOWN_ENTERPRISE.NEXTVAL FROM DUAL");
		String sql = "INSERT INTO T_WQS_TOWN_ENTERPRISE (ID,NAME,HTML_PATH,HTML_VALUE,ICON ) VALUES (?,?,?,?,?)";
		d = this.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) {
				try {
					ps.setInt(1, id);
					ps.setString(2, param.getName());
					ps.setString(3, param.getHtmlPath());
					lobHandler.getLobCreator().setClobAsString(ps, 4,
							ClobUtil.clobToString(param.getHtmlValue()));
					ps.setString(5, Util.nullToStr(param.getIcon()));

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		if (d > 0) {
			return id;
		}
		return d;
	}

	public LobHandler getLobHandler() {
		return lobHandler;
	}

	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}

	@Override
	public int updateEntIcon(String icon, String id) {
		int d = 0;
		String sql = "UPDATE T_WQS_TOWN_ENTERPRISE T SET T.ICON = ? WHERE T.ID = ?";
		Object params[] = { icon, Integer.parseInt(id) };
		d = this.update(sql, params);
		if (d > 0) {
			return d;
		}
		return d;
	}

	@Override
	public Pagination getHotelOrderList(int pageNum, int pageSize) {
		String sql = "FROM T_WQS_HOTEL_ORDER T left join t_wqs_hotel T1 on t.hotelid = T1.id WHERE 1 = 1";
		String qrySql = "SELECT T.*,T1.NAME AS HOTEL_NAME " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0], new HotelOrderModelRowMapper(),
				pageSize, pageNum);
		return returnList;
	}
	@Override
	public Pagination getHotelOrderList(String searchName,String hotelId,int pageNum, int pageSize){
		String sql = "FROM T_WQS_HOTEL_ORDER T left join t_wqs_hotel T1 on t.hotelid = T1.id WHERE 1 = 1";
		
		List paramList=new ArrayList();
		if(hotelId!=null&&!"".equals(hotelId)){
			sql+=" and t.hotelId=? ";
			paramList.add(hotelId);
		}
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" and t1.name like ? ";
			paramList.add("%"+searchName+"%");
		}
		String qrySql = "SELECT T.*,T1.NAME AS HOTEL_NAME " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql ,paramList.toArray(), new HotelOrderModelRowMapper(),
				pageSize, pageNum);
		return returnList;
		
		
		
		
	}
	@Override
	public List<HotelOrderDetailModel> getHotelOrderDetailList(long id) {
		String sql = "FROM t_wqs_hotel_order_detail t left join t_wqs_hotel_roomtype t1 on t.typeid = t1.id WHERE 1 = 1 and t.orderid = ? ";
		String qrySql = "SELECT t.*, t1.name as ROOM_NAME, t1.price as ROOM_PRICE, t1.isvalid as room_isvalid "
				+ sql;
		String orderSql = " ORDER BY T.ID DESC ";
		List<HotelOrderDetailModel> returnList = query(qrySql + orderSql,
				new Object[] { id }, new HotelOrderDetailModelRowMapper());
		return returnList;
	}

	@Override
	public Pagination getFoodOrderList(int pageNum, int pageSize) {
		String sql = "FROM T_WQS_RESTAURANT_ORDER t left join T_WQS_RESTAURANT t1 on t.rest_id = t1.id WHERE 1 = 1";
		String qrySql = "SELECT t.*,t1.NAME as REST_NAME,t1.TELE as REST_TELE,t1.ADDR as REST_ADDR "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0], new RestaurantOrderModelRowMapper(),
				pageSize, pageNum);
		return returnList;
	}
	@Override
	public Pagination getFoodOrderList(String searchName,String hotelId,int pageNum, int pageSize){
		String sql = "FROM T_WQS_RESTAURANT_ORDER t left join T_WQS_RESTAURANT t1 on t.rest_id = t1.id WHERE 1 = 1";
		List param=new ArrayList();
		if(hotelId!=null&&!"".equals(hotelId)){
			sql+=" and t.rest_id =? ";
			param.add(hotelId);
		}
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" and t1.name like ? "; 
			param.add("%"+searchName+"%");
		}
		String qrySql = "SELECT t.*,t1.NAME as REST_NAME,t1.TELE as REST_TELE,t1.ADDR as REST_ADDR "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql , param.toArray(), new RestaurantOrderModelRowMapper(),
				pageSize, pageNum);
		return returnList;
		
	}
	@Override
	public Pagination getTownJobsList(int pageNum, int pageSize) {
		String sql = "FROM T_WQS_TOWN_JOBS T WHERE 1=1 ";
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC";
		Object param[] = {};
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, param, new TownJobsModelRowMapper(), pageSize,
				pageNum);
		return returnList;
	}

	@Override
	public Pagination getTownJobsList(String searchName,String searchUnit,int pageNum, int pageSize){
		String sql = "FROM T_WQS_TOWN_JOBS T WHERE 1=1 ";
		List param=new ArrayList();
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" AND TITLE LIKE ? ";
			param.add("%"+searchName+"%");
		}
		if(searchUnit!=null&&!"".equals(searchUnit)){
			sql+=" AND UNIT LIKE ? ";
			param.add("%"+searchUnit+"%");
		}
		
		String qrySql = "SELECT * " + sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID DESC";
		Pagination returnList = pagination(qrySql + orderSql, countSql , param.toArray(), new TownJobsModelRowMapper(), pageSize,
				pageNum);
		return returnList;
		
		
		
	}
	
	
	
	
	
	@Override
	public int doTownJobsDelete(String id) {
		String delSql = "DELETE FROM T_WQS_TOWN_JOBS T WHERE T.ID = ?";
		Object params[] = { Integer.valueOf(id) };
		int d = this.update(delSql, params);

		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public int doTownJobsSave(TownJobsModel vo) {
		String updateSql = "INSERT INTO T_WQS_TOWN_JOBS (ID,TITLE,UNIT,CREATETIME,CONTACT_USER,CONTACT_PHONE,INFO_URL,INFO_VALUE,JOB_URL,JOB_VALUE) VALUES (SEQ_T_WQS_TOWN_JOBS.NEXTVAL,?,?,TO_DATE(?,'yyyy-MM-dd'),?,?,?,?,?,?)";
		Object[] params = { Util.nullToStr(vo.getTitle()),
				Util.nullToStr(vo.getUnit()),
				Util.nullToStr(vo.getCreateTime()),
				Util.nullToStr(vo.getContactUser()),
				Util.nullToStr(vo.getContactPhone()),
				Util.nullToStr(vo.getInfoUrl()),
				Util.nullToStr(vo.getInfoStr()),
				Util.nullToStr(vo.getJobUrl()), Util.nullToStr(vo.getJobStr()), };

		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public int doTownJobsUpdate(TownJobsModel vo) {
		String updateSql = "UPDATE T_WQS_TOWN_JOBS SET TITLE=?,UNIT=?,CREATETIME=TO_DATE(?,'yyyy-MM-dd'),CONTACT_USER=?,CONTACT_PHONE=?,INFO_URL=?,INFO_VALUE=?,JOB_URL=?,JOB_VALUE=? WHERE ID = ?";
		Object[] params = { Util.nullToStr(vo.getTitle()),
				Util.nullToStr(vo.getUnit()),
				Util.nullToStr(vo.getCreateTime()),
				Util.nullToStr(vo.getContactUser()),
				Util.nullToStr(vo.getContactPhone()),
				Util.nullToStr(vo.getInfoUrl()),
				Util.nullToStr(vo.getInfoStr()),
				Util.nullToStr(vo.getJobUrl()), Util.nullToStr(vo.getJobStr()),
				vo.getId() };

		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public TownJobsModel getTownJobsById(String id) {
		TownJobsModel model = new TownJobsModel();
		String querySql = "SELECT * FROM T_WQS_TOWN_JOBS WHERE ID=?";
		List list = (List) this.query(querySql,
				new Object[] { Integer.valueOf(id).intValue() },
				new RowMapperResultSetExtractor(new TownJobsModelRowMapper()));
		if (list.size() > 0)
			model = (TownJobsModel) list.get(0);

		return model;
	}

	@Override
	public Pagination getTrafficReguList(String page, String pagesize) {
		// TODO Auto-generated method stub
		String sql = "FROM T_WQS_TOWN_TRAFFIC T WHERE 1=1";
		String qrySql = "SELECT T.ID,T.TITLE,T.UNIT,T.CREATETIME,T.FILE_URL,T.HTML_VALUE "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				+ orderSql, new Object[0], new TrafficReguModelRowMapper(),
				Integer.parseInt(pagesize), Integer.parseInt(page));
		return returnList;
	}
	
	
	@Override
	public Pagination getTrafficReguList(String searchName,String searchUnit,String page, String pagesize){
		String sql = "FROM T_WQS_TOWN_TRAFFIC T WHERE 1=1";
		List param=new ArrayList();
		if(searchName!=null&&!"".equals(searchName)){
			sql+=" AND T.TITLE LIKE ? ";
			param.add("%"+searchName+"%");
		}
		if(searchUnit!=null&&!"".equals(searchUnit)){
			sql+=" AND T.UNIT LIKE ? ";
			param.add("%"+searchUnit+"%");
		}
		
		
		String qrySql = "SELECT T.ID,T.TITLE,T.UNIT,T.CREATETIME,T.FILE_URL,T.HTML_VALUE "
				+ sql;
		String countSql = "SELECT COUNT(1)" + sql;
		String orderSql = " ORDER BY T.ID ASC ";
		Pagination returnList = pagination(qrySql + orderSql, countSql
				 , param.toArray(), new TrafficReguModelRowMapper(),
				Integer.parseInt(pagesize), Integer.parseInt(page));
		return returnList;
	}
	
	
	@Override
	public int doTrafficReguUpdate(final TrafficReguModel model){
		String sql="UPDATE T_WQS_TOWN_TRAFFIC SET TITLE=?,UNIT=?,CREATETIME=TO_DATE(?,'yyyy-MM-dd'),FILE_URL=?,HTML_VALUE=? WHERE ID=? ";
		int d = 0;
		try {
			d=this.update(sql,new Object[]{model.getTitle(),model.getUnit(),model.getCreateTime(),model.getFileUrl(),model.getContentStr(),model.getId()});
		} catch (DataAccessException e) {
			log.error("在更新交规介绍时，出现异常："+e.toString());
			e.printStackTrace();
		}
		
//		try {
//			d = this.update(sql, new PreparedStatementSetter() {
//				public void setValues(PreparedStatement ps) {
//					try {
//						ps.setString(1, model.getTitle());
//						ps.setString(2, model.getUnit());
//						ps.setString(3, model.getCreateTime());
//						ps.setString(4, model.getFileUrl());
//						ps.setString(5, model.getContentStr());
//						lobHandler.getLobCreator().setClobAsString(ps, 7, model.getContentStr());
//						ps.setLong(8, model.getId());
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		} catch (DataAccessException e) {
//			log.error("在更新交规介绍时，出现异常："+e.toString());
//			e.printStackTrace();
//		}
		return d;
		
	}
	
	@Override
	public TrafficReguModel getTrafficReguModel(int id){
		String sql="SELECT  T.ID,T.TITLE,T.UNIT,T.CREATETIME,T.FILE_URL,T.HTML_VALUE FROM T_WQS_TOWN_TRAFFIC T WHERE ID=? ";
		try {
			List<TrafficReguModel> datas=this.query(sql, new Object[]{id},new  TrafficReguModelRowMapper());
			if(datas!=null&&!"".equals(datas)){
				return datas.get(0);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	
	
	
	
	
	
	
	
	

	@Override
	public int doTrafficReguSave(TrafficReguModel vo) {
		// TODO Auto-generated method stub
		String updateSql = "INSERT INTO T_WQS_TOWN_TRAFFIC (ID,TITLE,UNIT,CREATETIME,FILE_URL,HTML_VALUE) VALUES (SEQ_T_WQS_TOWN_TRAFFIC.NEXTVAL,?,?,TO_DATE(?,'yyyy-MM-dd'),?,?)";
		Object[] params = { Util.nullToStr(vo.getTitle()),
				Util.nullToStr(vo.getUnit()),
				Util.nullToStr(vo.getCreateTime()),
				Util.nullToStr(vo.getFileUrl()),
				Util.nullToStr(vo.getContentStr())};
		log.debug("sqlsave");		
		int d = update(updateSql, params);
		if (d > 0) {
			return d;
		}
		return 0;
	}

	@Override
	public int doTrafficReguDelete(String id) {
		// TODO Auto-generated method stub
				log.debug("[DELETESQL]..." + id);
				String updateSql = "DELETE FROM  T_WQS_TOWN_TRAFFIC WHERE ID = ?";
				Object[] params = { Integer.valueOf(id) };
				int d = update(updateSql, params);
				if (d > 0) {
					return d;
				}
				return 0;
	}

}

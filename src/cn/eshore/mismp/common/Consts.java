package cn.eshore.mismp.common;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

public class Consts implements Serializable {
		public static final String ROLE_ID = "ROLE_ID";
	public static final String ROOT_MODULE_PARENT_ID = "0";
	//add by helin
	public static final String KAI_HU = "1";//开户
	public static final String XIAO_HU = "2";//销户
	public static final String MODIFY_INFO = "3";//修改信息
	public static final String DATA_QUERY = "4";//数据查询
	//wangzl
	public static final String PAY_NOT ="002" ;  //未付费
    public static final String PAY_YES ="001";   //已付费
    public static final String BOA="B0A"; //   正常开户
	
	/**
	 * 属性值数据类型
	 */
	public static HashMap<String, String> attrDataTypeMap(){
		HashMap<String, String> attrDataType = new HashMap<String, String>();
		attrDataType.put("ADT000", "空");
		attrDataType.put("ADT001", "布尔");
		attrDataType.put("ADT002", "数值");
		attrDataType.put("ADT003", "字符串");
		attrDataType.put("ADT004", "日期");
		return attrDataType;
	}
	
	/**
	 * 属性录入类型
	 */
	public static HashMap<String, String> attrInputTypeMap(){
		HashMap<String, String> attrDataType = new HashMap<String, String>();
		attrDataType.put("AIT000", "空");
		attrDataType.put("AIT001", "编辑框");
		attrDataType.put("AIT002", "文件上传");
		attrDataType.put("AIT003", "日期控件");
		attrDataType.put("AIT004", "密码录入");
		return attrDataType;
	}
	
	/**
	 * 属性取值类型
	 */
	public static HashMap<String, String> attrValueTypeMap(){
		HashMap<String, String> attrDataType = new HashMap<String, String>();
		attrDataType.put("AVT000", "空");
		attrDataType.put("AVT001", "单选");
		attrDataType.put("AVT002", "多选");
/*		attrDataType.put("AVT001", "编码、单选");
		attrDataType.put("AVT002", "编码、多选");
		attrDataType.put("AVT003", "非编码");*/
		return attrDataType;
	}
	
	/**
	 * 属性特性关系类型
	 */
	public static HashMap<String, String> productAttrTypeMap(){
		HashMap<String, String> attrDataType = new HashMap<String, String>();
		attrDataType.put("PAT001", "必选");
		attrDataType.put("PAT002", "默认选");
		attrDataType.put("PAT003", "可选");
		return attrDataType;
	}

	/**
	 * 属性值是否可为空
	 */
	public static HashMap<String, String> attrNullableMap(){
		HashMap<String, String> attrDataType = new HashMap<String, String>();
		attrDataType.put(String.valueOf(1), "无限制");
		attrDataType.put(String.valueOf(2), "必须为空");
		attrDataType.put(String.valueOf(3), "不能为空");
		return attrDataType;
	}
	
	/**
	 * 产品状态类型
	 */
	public static HashMap<String, String> productStateMap(){
		HashMap<String, String> attrDataType = new HashMap<String, String>();
		attrDataType.put("00A", "有效");
		attrDataType.put("00X", "无效");
		attrDataType.put("00H", "已归档");
		attrDataType.put("00B", "已占用");
		return attrDataType;
	}
	
	/**
	 * 属性值关系类型
	 */
	public static HashMap<String, String> attrValueRelaMap(){
		HashMap<String, String> attrDataType = new HashMap<String, String>();
		attrDataType.put(String.valueOf(0), "互斥");
		attrDataType.put(String.valueOf(1), "依赖");
		attrDataType.put(String.valueOf(2), "包含");
		return attrDataType;
	}
	
	/**
	 * 计费事件类型
	 */
	public static HashMap<String, String> eventTypeMap(){
		HashMap<String, String> attrDataType = new HashMap<String, String>();
		attrDataType.put("48A", "客户交互事件");
		attrDataType.put("48B", "使用记录事件");
		return attrDataType;
	}
	
	/**
	 * 资费标准类型
	 */
	public static HashMap<String, String> tariffTypeMap(){
		HashMap<String, String> attrDataType = new HashMap<String, String>();
		attrDataType.put("89A", "一次性费用");
		attrDataType.put("89B", "周期性费用");
		attrDataType.put("89C", "使用性费用");
		return attrDataType;
	}
	
	/**
	 * 有效期类型
	 */
	public static HashMap<String, String> durativeTypeMap(){
		HashMap<String, String> attrDataType = new HashMap<String, String>();
		attrDataType.put("85A", "本帐期有效");
		attrDataType.put("85B", "下一帐期有效");
		attrDataType.put("85C", "永久有效");
		return attrDataType;
	}
	
    //华为数据调用异常
	public static String HW_INVOKE_ERROR = "{\"code\":";
	
	//add by jingfei 20090226
	public static String SYS_ERROR_JSON = "{\"errorcode\":\"99\"}";
	
	public static String INVOKE_SUCC_JSON = "{\"errorcode\":\"00\"}";
	
	public static String INVOKE_FAIL_JSON = "{\"errorcode\":\"01\"}";
	
	public static String XML_ERROR_JSON = "{\"errorcode\":\"02\"}";
	
	public static String NO_RESULT_JSON = "{\"errorcode\":\"03\"}";
	
	public static String INVOKE_ERROR_JSON = "{\"errorcode\":\"04\"}";
	
	public static String NET_ERROR_JSON = "{\"errorcode\":\"05\"}";
	
	public static String TIME_OUT_JSON = "{\"errorcode\":\"06\"}";
	
	public static String XML_NO_FLIGTH_JSON = "{\"errorcode\":\"07\"}";
	
	//受理中
	public static String PROC_OPT_JSON = "{\"errorcode\":\"20\"}";
	
	//撤销
	public static String CANCEL_OPT_JSON = "{\"errorcode\":\"21\"}";
	
	public static String XML_PREFIX = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
	
	public static String INVOKE_ERROR ="04";
	
	public static String INVOKE_FAIL = "01";
	
	public static int TICKET_ORDER_TYPE = 10;
	
	public static String SYS_ORDER_ACCOUNT = "1111111";
	
	public static String XML_ERROR_JSON_ORDER = "{\"errorcode\":\"02\",\"orderId\":\"\"}";
	
	public static String SYS_ERROR_JSON_ORDER = "{\"errorcode\":\"99\",\"orderId\":\"\"}";
	
	public static String RES_ORDER_SUPPORT ="1";
	
	public static String RES_ORDER_NOT_SUPPORT = "0";
	
	//普通114查号
	public static String COMMON_NUM_QUERY ="0";
	
	//简拼114查号
	public static String COMMON_NUM_PY_QUERY = "1";
	
	//工单未处理
	public static String ORDER_OPERAT_UNDO = "00";
	
	//工单已处理
	public static String ORDER_OPERAT_DO = "90";
	
	//工单已取消
	public static String ORDER_OPERAT_CANCEL = "99";
	
	//订购结果【系统初始】
	public static String ORDER_RESULT_INIT = "00";
	
	//机票配送级别：一般
	public static String DELIVERY_LEVEL_COMMON = "1";
	
	//机票配送级别：紧急
	public static String DELIVERY_LEVEL_SPECIAL = "2";
	
	//机场取票
	public static String DELIVERY_FROM_AIRPORT = "1";
	
	//代理取票
	public static String DELIVERY_FROM_AGENT = "2";
	
	//添加操作
	public static String ACTION_ADD = "add";
	
	//酒店查询成功码
	public static String HOTEL_QUERY_SUCC ="0";
	
	//酒店查询失败码
	public static String HOTEL_QUERY_FAIL ="1";
	
	//增加操作码
	public static String ADD_OPTION_ACTION = "add";
	
	//餐厅同步订单类型
	public static String HOTEL_ORDERSYN_SYN_TYPE = "0";
	
	//机票同步订单类型
	public static String TICKET_ORDER_SYN_TYPE = "1";
	
	//餐厅同步订单类型
	public static String RESTAURANT_ORDER_SYN_TYPE = "2";
	
	//殴飞查询成功码
	public static String OFCARD_QUERY_SUCC ="1";
	
	//殴飞查询失败码
	public static String OFCARD_QUERY_FAIL ="0";
	
	//殴飞充值成功码
	public static String OFCARD_ORDER_SUCC ="1";
	
	//殴飞充值进行中
	public static String OFCARD_ORDER_PROC ="0";
	
	//殴飞充值撤销
	public static String OFCARD_ORDER_ROOLBACK ="9";
	
	//MOBILE
	public static String CATEGORY_MOBILE = "mobile";
	
	//CE
	public static String CATEGORY_WINCE = "wince";
	
	//JAVA
	public static String CATEGORY_JAVA = "java";
	
	//JAVA
	public static String CATEGORY_BREW = "brew";
	
	//机场码
	public static String AIR_CORD = "SZX=深圳宝安&深圳宝安国际机场|XXX=上海&上海|SHA=上海虹桥&上海虹桥国际机场"
		+"|PVG=上海浦东&上海浦东国际机场|XXX=北京&北京|NAY=北京南苑&北京南苑机场|PEK=北京首都&北京首都国际机场|CGD=常德&常德斗姆湖机场"
		+"|JIL=吉林&吉林二台子机场|HFE=合肥&合肥骆岗机场|DIG=迪庆香格里拉&迪庆香格里拉|ZYI=遵义&遵义机场|NAO=南充&南充|KRY=克拉玛依&克拉玛依"
		+"|WUH=武汉&武汉天河机场|CZX=常州&常州奔牛机场|JGN=嘉峪关&嘉峪关机场|HZG=汉中&汉中|JNG=济宁&济宁济宁机场|DAT=大同&大同"
		+"|YIN=伊宁&伊宁|TOY=富山&富山|KWE=贵阳&贵阳龙洞堡机场|YUA=元谋&元谋|CIF=赤峰&赤峰|INC=银川&银川河东机场|JDZ=景德镇&景德镇机场"
		+"|HUZ=徽州&徽州|LNC=临沧&临沧|CNI=长海&长海|NDG=齐齐哈尔&齐齐哈尔三家子机场|ZHA=湛江&湛江机场|YBP=宜宾&宜宾莱坝机场|SYX=三亚&三亚凤凰机场"
		+"|WEH=威海&威海大水泊机场|SXJ=鄯善&鄯善|WXN=万州&万州|XFN=襄樊&襄樊刘集机场|MXZ=梅县&梅县机场|HTN=和田&和田|CHW=酒泉&酒泉|GNY=广元&广元"
		+"|CHG=朝阳&朝阳|LUZ=庐山&庐山|TNN=台南&台南|WHU=芜湖&芜湖|XIL=锡林浩特&锡林浩特|WEF=潍坊&潍坊|XMN=厦门&厦门高崎国际机场|TNS=天水&天水"
		+"|HKK=海南&海南|DSN=东胜&东胜|JNZ=锦州&锦州|CGQ=长春&长春大房身机场|YNJ=延吉&延吉|JMU=佳木斯&佳木斯|XEN=兴城&兴城|BSD=保山&保山"
		+"|HMI=哈密&哈密|NKG=南京&南京禄口国际机场|JIU=九江&九江|ENH=恩施&恩施|FUO=佛山&佛山|GOQ=格尔木&格尔木|HSN=舟山&舟山|SWA=汕头&汕头外砂机场"
		+"|TCG=塔城&塔城|KHN=南昌&南昌昌北机场|SCH=四川&四川|KNC=吉安&吉安|IQM=且末&且末|AKU=阿克苏&阿克苏|IQN=庆阳&庆阳|ZUH=珠海&珠海三灶机场"
		+"|TPE=台北&台北|MDG=牡丹江&牡丹江海浪机场|KCA=库车&库车|MFM=澳门&澳门|AKA=安康&安康|HLD=海拉尔&海拉尔|XIC=西昌&西昌青山机场|SYM=思茅&思茅"
		+"|FYN=富蕴&富蕴|NNY=南阳&南阳姜营机场|XNN=西宁&西宁曹家堡机场|LZO=泸州&泸州兰田坝机场|UYN=榆林&榆林|LYG=连云港&连云港白塔阜机场|WUS=武夷山&武夷山武夷山机场"
		+"|YIH=宜昌&宜昌三峡机场|LXI=林西&林西|LYI=临沂&临沂|BFU=蚌埠&蚌埠|XNT=邢台&邢台|YIW=义乌&义乌|TNA=济南&济南遥墙机场|BHY=北海&北海福城机场"
		+"|TON=铜仁&铜仁|ENY=延安&延安|KHH=高雄&高雄|DAX=达县&达县|KWL=桂林&桂林两江国际机场|FOG=阜阳&阜阳|LJG=丽江&丽江机场|WUZ=梧州&梧州"
		+"|AQG=安庆&安庆|JZH=九寨沟&九寨沟|JUZ=衢州&衢州|LHW=兰州&兰州中川机场|LXA=拉萨&拉萨|HKG=香港&香港|DLU=大理&大理|LZD=兰州东&兰州东"
		+"|NGB=宁波&宁波砾社机场|SZV=苏州&苏州|HYN=黄岩&黄岩|MIG=绵阳&绵阳南郊机场|SHS=沙市&沙市沙石机场|DDG=丹东&丹东|YLN=铱兰&铱兰|YNZ=盐城&盐城"
		+"|CGO=郑州&郑州|CKG=重庆&重庆|HAK=海口&海口美兰机场|YNT=烟台&烟台莱山机场|HRB=哈尔滨&哈尔滨太平国际机场|URC=乌鲁木齐&乌鲁木齐地窝铺机场"
		+"|XUZ=徐州&徐州观音机场|HEK=黑河&黑河|YON=永州&永州|SJW=石家庄&石家庄正定机场|DYG=张家界&张家界荷花机场|LYA=洛阳&洛阳北郊机场|TXN=黄山&黄山屯溪机场"
		+"|XIN=兴宁&兴宁|GHN=广汉&广汉|AOG=鞍山&鞍山|ZHD=中甸&中甸|BAV=包头&包头机场|HGH=杭州&杭州萧山机场|LHN=梨山&梨山|CCC=潮州&潮州|DZU=大足&大足"
		+"|DLC=大连&大连周水子机场|CJU=济州&济州|CAN=广州&广州白云国际机场|HHA=长沙/黄花&长沙/黄花|SHE=沈阳&沈阳桃仙国际机场|CIH=长治&长治|AAT=阿勒泰&阿勒泰"
		+"|KHG=喀什&喀什|TXG=台中&台中|HNY=衡阳&衡阳|NTG=南通&南通兴东机场|LZH=柳州&柳州白莲机场|XIY=西安&西安咸阳机场|DNH=敦煌&敦煌机场|HET=呼和浩特&呼和浩特白塔机场"
		+"|WUX=无锡&无锡|TYN=太原&太原武宿机场|CAL=长安&长安|FOC=福州&福州长乐国际机场|KOW=赣州&赣州|AYN=安阳&安阳|NNG=南宁&南宁吴墟机场|LIA=梁平&梁平"
		+"|CTU=成都&成都双流国际机场|ZAT=昭通&昭通|XXX=青岛&青岛|TAO=青岛流亭&青岛流亭机场|TAO=青岛流亭2&青岛流亭机场2|KMG=昆明&昆明巫家坝机场|TSN=天津&天津滨海国际机场"
		+"|KRL=库尔勒&库尔勒|TTT=台东&台东|WNZ=温州&温州永强机场|JJN=泉州晋江&泉州晋江|CSX=长沙&长沙黄花机场|SHP=秦皇岛&秦皇岛山海关机场|DGY=东营&东营";
		
	public static void main(String args[]){
		int deaprtBeginIndex = Consts.AIR_CORD.indexOf("CGO");
		int arriveBeginIndex = Consts.AIR_CORD.indexOf("CSX");
		if(deaprtBeginIndex == -1 || arriveBeginIndex == -1){
			System.out.println("ddd");
		}
		else{
			String deaprtCityStr = StringUtils.substringBetween(Consts.AIR_CORD,"CGO=","&");
			String arriveCityStr = StringUtils.substringBetween(Consts.AIR_CORD,"CSX=","&");
			System.out.println(" 航程:" + deaprtCityStr + "-" + arriveCityStr);
		}
	}
}

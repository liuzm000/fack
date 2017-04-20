package cn.eshore.mismp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/***
 *** @version 2.0
 *** @inheritDoc
 *** 实现功能：实现华为接口规范角色访问参数处理
 *** @author jsingfly 2010-02-22
 ***/

/*
  	名称	类型	是否必须	描述
	method	String	Y	API接口名称,例如: user.login（登陆）
	timestamp	String	N	时间戳,也可认为是呼叫序列号。格式为yyyy-MM-dd HH:mm:ss.SSS 例如: 2009-08-20 17:15:03.031。在同一个用户会话状态下请求传递的timestamp需保证递增
	sessionkey	String	N	用户会话码，用户在第一次登陆由服务器下发，该用户会话码是服务器验证客户端用户是否是登陆状态的标识 (生成规则详见AppMarket Web Service接口安全验证说明书V1.1)
	appkey	String	Y	appkey是客户端厂商身份的唯一标识(详见AppMarket Web Service接口安全验证说明书V1.1)
	v	String	Y	API接口版本号(详见AppMarket Web Service接口安全验证说明书V1.1)
	format	String	Y 	响应格式。xml,json。目前只支持json
	sig	String	Y	数字签名(详见AppMarket Web Service接口安全验证说明书V1.1)

 	游客	不需要用户登录，请求商城数据时不需要安全鉴权，就可以访问商城的数据	各个接口中标示
	买家	游客角色+需要用户登录才能访问商城数据	各个接口中标示
	卖家	游客角色+买家角色+卖家角色，将部分常用卖家功能以API形式开放出来	暂无

	接口验证权限描述	需要传递和验证的系统参数
	游客角色可调用的接口	appkey,v,format,method,sig
	买家角色可调用的接口（非登录状态）	appkey,v,format,method,sig
	买家角色可调用的接口（登录状态）	appkey,v,format,method,sig,seesionkey timestamp
*/
public class RoleSecurityUtil {
	
	protected static final Logger log = Logger.getLogger(RoleSecurityUtil.class);
	//KEY appkey
	private static String appkey = MobileGlobals.getProperty("sp.security.appkey");
	//密码 appsecret
	private static  String appsecret = MobileGlobals.getProperty("sp.security.appsecret");
	//版本 v
	private static String v = MobileGlobals.getProperty("sp.server.version");
	//参数排序比较器内部类
	private static Comparator sortComparator = new Comparator() {
		public int compare(Object o1, Object o2) {
			// 如果有空值，直接返回0
			if (o1 == null || o2 == null)
				return 0;
			return String.valueOf(o1).compareTo(String.valueOf(o2));
		}
	};
	
	// 获取游客系统安全参数
	public static String getVisitorSecurityParam(String method, Map<String, String> paramMap){
		String result = "";
		
		//参数排序
		TreeMap<String, String> sysParamMap = new TreeMap<String, String>(sortComparator); 

		sysParamMap.put("appkey", appkey);
		sysParamMap.put("v", v);
		sysParamMap.put("format", "json");
		sysParamMap.put("method", method);
		
		if(paramMap != null){
			Set<String> keySet = paramMap.keySet();
			for(Iterator<String> it = keySet.iterator(); it.hasNext(); ){
				String key = it.next();
				sysParamMap.put(key, paramMap.get(key));
			}
		}
		
		//生成Sign参数
		StringBuffer signUnProStr = new StringBuffer();
		StringBuffer strBuffer = new StringBuffer();
		
		for(Iterator<String> it = sysParamMap.keySet().iterator(); it.hasNext(); ){
			String key = it.next();
			String value = sysParamMap.get(key);
			signUnProStr.append(key).append(value);
			strBuffer.append(key).append("=").append(value).append("&");
		}
		
		signUnProStr.append(appsecret);
		
		try {
			log.info("未加密参数:"
					+ URLEncoder.encode(signUnProStr.toString(), "UTF-8"));
			String sign = SHA1.crypt(URLEncoder.encode(signUnProStr.toString(), "UTF-8"));
			log.info("SHA1加密后Sig:" + sign);
			strBuffer.append("sig=").append(sign);
			result = strBuffer.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getVisitorSecurityParamForUploadAppList(String method, Map<String, String> paramMap){
		String result = "";
		
		//参数排序
		TreeMap<String, String> sysParamMap = new TreeMap<String, String>(sortComparator); 

		sysParamMap.put("appkey", appkey);
		sysParamMap.put("v", v);
		sysParamMap.put("format", "json");
		sysParamMap.put("method", method);
		
		if(paramMap != null){
			Set<String> keySet = paramMap.keySet();
			for(Iterator<String> it = keySet.iterator(); it.hasNext(); ){
				String key = it.next();
				sysParamMap.put(key, paramMap.get(key));
			}
		}
		
		//生成Sign参数
		StringBuffer signUnProStr = new StringBuffer();
		StringBuffer strBuffer = new StringBuffer();
		
		for(Iterator<String> it = sysParamMap.keySet().iterator(); it.hasNext(); ){
			String key = it.next();
			String value = sysParamMap.get(key);
			signUnProStr.append(key).append(value);
			strBuffer.append(key).append("※").append(value).append("＆");
		}
		
		signUnProStr.append(appsecret);
		
		try {
			log.info("未加密参数:"
					+ URLEncoder.encode(signUnProStr.toString(), "UTF-8"));
			String sign = SHA1.crypt(URLEncoder.encode(signUnProStr.toString(), "UTF-8"));
			log.info("SHA1加密后Sig:" + sign);
			strBuffer.append("sig※").append(sign);
			result = strBuffer.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	//获取登陆注册用户系统安全参数
	public static String getLoginRegisterSecurityParam(String method){
		String result = "";
		
		
		return result;
	}
	
	public static void  main(String[] args){
		Map<String, String> paramMap = new HashMap<String, String>();
		String appInfos = "{\"objs\":[{\"appEnv\":\"android\",\"appName\":\"M0tURA==\",\"packageName\":\"com.joymasterrocks.ThreeKTD\",\"packageSig\":\"72-1022-62-108-123-13-49125-66-76-9-100116-35-109-30-77124107\",\"packageNewVersion\":19},{\"appEnv\":\"android\",\"appName\":\"5IW35quR566d\",\"packageName\":\"com.money.humor\",\"packageSig\":\"97-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127\",\"packageNewVersion\":20110105},{\"appEnv\":\"android\",\"appName\":\"5paV57655beF6Jik4X+L4X+L4X+L4X+L4X+L4X+L4X+L4X+L4X+L\",\"packageName\":\"jp.androdev.historyeraser\",\"packageSig\":\"97-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127\",\"packageNewVersion\":5},{\"appEnv\":\"android\",\"appName\":\"QVBJIERlbW9z\",\"packageName\":\"com.example.android.ray\",\"packageSig\":\"-65-344-75-83107-47-23111-3126111265397-62-11461-3\",\"packageNewVersion\":-1},{\"appEnv\":\"android\",\"appName\":\"TWFwc0RlbW8=\",\"packageName\":\"cn.maps.mapgame\",\"packageSig\":\"8597-6-871-64-3923511354-33-39-18249947-17101-87\",\"packageNewVersion\":0},{\"appEnv\":\"android\",\"appName\":\"5KOp5r685qi66Ja0\",\"packageName\":\"com.eshore.ezone\",\"packageSig\":\"-65-344-75-83107-47-23111-3126111265397-62-11461-3\",\"packageNewVersion\":3005},{\"appEnv\":\"android\",\"appName\":\"5p6t476h5K2a5Za25I6R6H+B5Jio\",\"packageName\":\"com.jason.scheduleforsms\",\"packageSig\":\"-69-162839964-3088-295538-311188898-953811738125\",\"packageNewVersion\":0},{\"appEnv\":\"android\",\"appName\":\"VULmtI7opoflmKg=\",\"packageName\":\"com.uc.browser\",\"packageSig\":\"-10975-7320123-4869102-4459-83-9890521370699-10055\",\"packageNewVersion\":31},{\"appEnv\":\"android\",\"appName\":\"VGFsa2luZyBIaXBwbw==\",\"packageName\":\"com.outfit7.talkinghippo\",\"packageSig\":\"97-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127\",\"packageNewVersion\":2},{\"appEnv\":\"android\",\"appName\":\"5YiL5Zu6UVE=\",\"packageName\":\"com.tencent.qq\",\"packageSig\":\"97-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127\",\"packageNewVersion\":11},{\"appEnv\":\"android\",\"appName\":\"H+O8l+O6vuS4geScug==\",\"packageName\":\"com.eoemobile.market\",\"packageSig\":\"392511056107-12194118-83-90-25-22-124-28-58-18-2961-6\",\"packageNewVersion\":8},{\"appEnv\":\"android\",\"appName\":\"Q1NETg==\",\"packageName\":\"net.csdn.csdnclient\",\"packageSig\":\"-41-9626-82-128-3871928-58-85-119-5433-36108-75-46-4724\",\"packageNewVersion\":0},{\"appEnv\":\"android\",\"appName\":\"6KyU5KCUOeWLruWVkOSFq+O4uw==\",\"packageName\":\"org.ssg.android.game.herogame\",\"packageSig\":\"-53-39-93-1281754-12-104-254-35-997921112-1232247365\",\"packageNewVersion\":0},{\"appEnv\":\"android\",\"appName\":\"54aq5Imo5IWF5KyY5q2h5o+G\",\"packageName\":\"com.lim.android.automemman\",\"packageSig\":\"97-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127\",\"packageNewVersion\":44},{\"appEnv\":\"android\",\"appName\":\"5a6P5Zal5puL5ZSw\",\"packageName\":\"com.gdtel.eshore.viewdata\",\"packageSig\":\"-91-54-2-4477061-826347-6-3941-99-8547-26696145\",\"packageNewVersion\":1000},{\"appEnv\":\"android\",\"appName\":\"Ui5FLeauoOaQheSZp+GAiuGAiuGAiuGAiuGAiuGAiw==\",\"packageName\":\"com.speedsoftware.rootexplorer\",\"packageSig\":\"97-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127\",\"packageNewVersion\":39},{\"appEnv\":\"android\",\"appName\":\"VW5pdmVyc2FsIEFuZHJvb3Q=\",\"packageName\":\"com.corner23.android.universalandroot\",\"packageSig\":\"-51-12623728197-123117-211108-2379-33517-355699-52\",\"packageNewVersion\":10},{\"appEnv\":\"android\",\"appName\":\"5KSH47mr\",\"packageName\":\"com.beginshare.appshare\",\"packageSig\":\"-90-24118-12268-108-20-71122-15-7598941452-1118445-60-4\",\"packageNewVersion\":2011022299},{\"appEnv\":\"android\",\"appName\":\"57695L2u5Iya\",\"packageName\":\"cn.zhui.zhuit\",\"packageSig\":\"20-1241975339812359127-12-2-11729-5912112781-2079\",\"packageNewVersion\":22},{\"appEnv\":\"android\",\"appName\":\"54eq5o6t5q2h5K22\",\"packageName\":\"com.flight.view\",\"packageSig\":\"31-681195-47-108-109-18-11012119122-41-87-89-11696-72-47-7\",\"packageNewVersion\":3}]}";
		paramMap.put("sessionkey", "983384f785a468494d3f6b3b07107df0");
		paramMap.put("timestamp", "2011-03-30 11:34:45.282");
		paramMap.put("appInfos", appInfos);
		RoleSecurityUtil.getVisitorSecurityParam("app.uploadAppList", paramMap);
		
//		String info = "appInfos%7B%22objs%22%3A%5B%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%22M0tURA%3D%3D%22%2C%22packageName%22%3A%22com.joymasterrocks.ThreeKTD%22%2C%22packageSig%22%3A%2272-1022-62-108-123-13-49125-66-76-9-100116-35-109-30-77124107%22%2C%22packageNewVersion%22%3A19%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%225IW35quR566d%22%2C%22packageName%22%3A%22com.money.humor%22%2C%22packageSig%22%3A%2297-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127%22%2C%22packageNewVersion%22%3A20110105%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%225paV57655beF6Jik4X%2BL4X%2BL4X%2BL4X%2BL4X%2BL4X%2BL4X%2BL4X%2BL4X%2BL%22%2C%22packageName%22%3A%22jp.androdev.historyeraser%22%2C%22packageSig%22%3A%2297-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127%22%2C%22packageNewVersion%22%3A5%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%22QVBJIERlbW9z%22%2C%22packageName%22%3A%22com.example.android.ray%22%2C%22packageSig%22%3A%22-65-344-75-83107-47-23111-3126111265397-62-11461-3%22%2C%22packageNewVersion%22%3A-1%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%225KOp5r685qi66Ja0%22%2C%22packageName%22%3A%22com.eshore.ezone%22%2C%22packageSig%22%3A%22-65-344-75-83107-47-23111-3126111265397-62-11461-3%22%2C%22packageNewVersion%22%3A3004%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%225p6t476h5K2a5Za25I6R6H%2BB5Jio%22%2C%22packageName%22%3A%22com.jason.scheduleforsms%22%2C%22packageSig%22%3A%22-69-162839964-3088-295538-311188898-953811738125%22%2C%22packageNewVersion%22%3A0%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%22VULmtI7opoflmKg%3D%22%2C%22packageName%22%3A%22com.uc.browser%22%2C%22packageSig%22%3A%22-10975-7320123-4869102-4459-83-9890521370699-10055%22%2C%22packageNewVersion%22%3A31%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%22VGFsa2luZyBIaXBwbw%3D%3D%22%2C%22packageName%22%3A%22com.outfit7.talkinghippo%22%2C%22packageSig%22%3A%2297-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127%22%2C%22packageNewVersion%22%3A2%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%225YiL5Zu6UVE%3D%22%2C%22packageName%22%3A%22com.tencent.qq%22%2C%22packageSig%22%3A%2297-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127%22%2C%22packageNewVersion%22%3A11%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%22H%2BO8l%2BO6vuS4geScug%3D%3D%22%2C%22packageName%22%3A%22com.eoemobile.market%22%2C%22packageSig%22%3A%22392511056107-12194118-83-90-25-22-124-28-58-18-2961-6%22%2C%22packageNewVersion%22%3A8%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%22Q1NETg%3D%3D%22%2C%22packageName%22%3A%22net.csdn.csdnclient%22%2C%22packageSig%22%3A%22-41-9626-82-128-3871928-58-85-119-5433-36108-75-46-4724%22%2C%22packageNewVersion%22%3A0%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%226KyU5KCUOeWLruWVkOSFq%2BO4uw%3D%3D%22%2C%22packageName%22%3A%22org.ssg.android.game.herogame%22%2C%22packageSig%22%3A%22-53-39-93-1281754-12-104-254-35-997921112-1232247365%22%2C%22packageNewVersion%22%3A0%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%2254aq5Imo5IWF5KyY5q2h5o%2BG%22%2C%22packageName%22%3A%22com.lim.android.automemman%22%2C%22packageSig%22%3A%2297-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127%22%2C%22packageNewVersion%22%3A44%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%225a6P5Zal5puL5ZSw%22%2C%22packageName%22%3A%22com.gdtel.eshore.viewdata%22%2C%22packageSig%22%3A%22-91-54-2-4477061-826347-6-3941-99-8547-26696145%22%2C%22packageNewVersion%22%3A1000%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%22Ui5FLeauoOaQheSZp%2BGAiuGAiuGAiuGAiuGAiuGAiw%3D%3D%22%2C%22packageName%22%3A%22com.speedsoftware.rootexplorer%22%2C%22packageSig%22%3A%2297-1955126-123-45-122-88-33-18107-12275-409111-6-91-81-127%22%2C%22packageNewVersion%22%3A39%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%22VW5pdmVyc2FsIEFuZHJvb3Q%3D%22%2C%22packageName%22%3A%22com.corner23.android.universalandroot%22%2C%22packageSig%22%3A%22-51-12623728197-123117-211108-2379-33517-355699-52%22%2C%22packageNewVersion%22%3A10%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%225KSH47mr%22%2C%22packageName%22%3A%22com.beginshare.appshare%22%2C%22packageSig%22%3A%22-90-24118-12268-108-20-71122-15-7598941452-1118445-60-4%22%2C%22packageNewVersion%22%3A2011022299%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%2257695L2u5Iya%22%2C%22packageName%22%3A%22cn.zhui.zhuit%22%2C%22packageSig%22%3A%2220-1241975339812359127-12-2-11729-5912112781-2079%22%2C%22packageNewVersion%22%3A22%7D%2C%7B%22appEnv%22%3A%22android%22%2C%22appName%22%3A%2254eq5o6t5q2h5K22%22%2C%22packageName%22%3A%22com.flight.view%22%2C%22packageSig%22%3A%2231-681195-47-108-109-18-11012119122-41-87-89-11696-72-47-7%22%2C%22packageNewVersion%22%3A3%7D%5D%7Dappkey2ec45e9835d988aeformatxmlmethodapp.uploadAppListsessionkeytimestampuserIdv1.0";
//		String sign = SHA1.crypt(info);
//		log.info("SHA1加密后Sig:" + sign);
	}
	
}

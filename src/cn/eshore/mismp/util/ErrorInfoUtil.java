/**
 * Created at:2008-10-14 上午09:38:12
 */
package cn.eshore.mismp.util;

/**
 * <p>Title: ErrorInfoUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="ErrorInfoUtil.java.html"><i>View Source</i></a></p>
 * @author Yusm
 * @version 1.0
 */
public class ErrorInfoUtil {
	
	public static String getHotelErrInfo(String errorCode){
		String description = "未知错误码";
		int m = 0;
		try {
			m = Integer.parseInt(errorCode);
		} catch (NumberFormatException e) {
			m = 99;
		}
		//System.out.println(m);
		switch (m) {
		case 0:
			description = "成功";
			break;
		case 1:
			description = "系统错误";
			break;
		case 2:
			description = "数据库错误";
			break;
		case 11:
			description = "酒店所属城市为空";
			break;
		case 12:
			description = "酒店星级出错";
			break;
		case 13:
			description = "酒店名称为空";
			break;
		case 14:
			description = "酒店价格区间出错";
			break;
		case 15:
			description = "酒店入住日期出错";
			break;
		case 16:
			description = "酒店离店日期出错";
			break;
		case 17:
			description = "酒店标识错误";
			break;
		case 99:
			description = "参数错误";
			break;
		default:
			break;
		}
		return description;
	}
	
	public static String getTicketErrInfo(String errorCode){
		String description = "未知错误码";
		int m = 0;
		try {
			m = Integer.parseInt(errorCode);
		} catch (NumberFormatException e) {
			m = 99;
		}
		//System.out.println(m);
		switch (m) {
		case 0:
			description = "成功";
			break;
		case 1:
			description = "系统错误";
			break;
		case 2:
			description = "数据库错误";
			break;		
		case 99:
			description = "参数错误";
			break;
		default:
			break;
		}
		return description;
	}
	
	public static String getContactsErrInfo(String errorCode){
		String description = "未知错误码";
		int m = 0;
		try {
			m = Integer.parseInt(errorCode);
		} catch (NumberFormatException e) {
			m = 0;
		}
		//System.out.println(m);
		switch (m) {
		case 0:
			description = "成功";
			break;
		case 1:
			description = "type类型错误";
			break;
		case 2:
			description = "帐号密码错，登录失败";
			break;
		case 3:
			description = "指定联系人不存在";
			break;
		case 4:
			description = "seqid不存在，非法连接";
			break;
		case 5:
			description = "连接超时";
			break;
		case 6:
			description = "同步到平台错误";
			break;
		case 7:
			description = "查询联系人错误";
			break;
		case 8:
			description = "获取所有联系人错误";
			break;
		case 98:
			description = "xml格式错误或缺少必须的字段";
			break;
		case 99:
			description = "系统级错误";
			break;
		default:
			break;
		}
		return description;
	}

	/**
	 * 
	 * @param ErrorInfoUtil
	 * @return void
	 * @throws @param args
	 */
	public static void main(String[] args) {
		System.out.println(ErrorInfoUtil.getHotelErrInfo("099"));
		System.out.println(ErrorInfoUtil.getHotelErrInfo("011"));
		System.out.println(ErrorInfoUtil.getHotelErrInfo("999"));
	}

}

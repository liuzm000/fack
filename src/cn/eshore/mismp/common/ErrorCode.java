package cn.eshore.mismp.common;

/**
 * <p>Title: ErrorCode</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Eshore</p>
 * 
 * @author zhuosf
 * @version 1.1
 * @since 2010/01/08
 */
public final class ErrorCode {
	
	/** MISMP ERROR CODE */
	
	// 成功
	public static final int MISMP_RESULTCODE_SUCCESS = 0;
	
	// 发端设备非法
	public static final int MISMP_RESULTCODE_DEVICE_SRC_ILLEGAL = 101;

	// IMSI非法
	public static final int MISMP_RESULTCODE_IMSI_ILLEGAL = 102;
	
	// 终端用户不存在
	public static final int MISMP_RESULTCODE_TERUSER_INEXISTENT = 103;
	
	// 手机号码非法
	public static final int MISMP_RESULTCODE_PHONE_NUM_ILLEGAL = 104;
	
	// 产品ID非法
	public static final int MISMP_RESULTCODE_PRODUCT_ID_ILLEGAL = 105;
	
	// 产品不存在
	public static final int MISMP_RESULTCODE_PRODUCT_INEXISTENT = 106;
	
	// 产品未发布
	public static final int MISMP_RESULTCODE_PRODUCT_UNISSUED = 107;
	
	// 非法定购类型
	public static final int MISMP_RESULTCODE_ORDER_TYPE_ILLEGAL = 108;
	
	// 非法参数
	public static final int MISMP_RESULTCODE_PARAM_ILLEGAL = 109;
	
	// 定购关系已存在
	public static final int MISMP_RESULTCODE_ORDER_REL_EXISTS = 110;
	
	// 定购关系不存在
	public static final int MISMP_RESULTCODE_ORDER_REL_NOT_EXISTS = 111;
	
	// 产品资费策略无效
	public static final int MISMP_RESULTCODE_PRODUCT_FEE_ILLEGAL = 112;
	
	// 订购关系数据不完整
	public static final int MISMP_RESULTCODE_DATA_HALF_BAKED = 113;
	
	// 其他错误
	public static final int MISMP_RESULTCODE_OTHER_ERROR = 999;
	
	// 定购失败
	public static final int MISMP_RESULTCODE_ORDER_FAILED = 201;
	
	// 退定失败
	public static final int MISMP_RESULTCODE_WITHDRAW_FAILED = 202;
	
	// 获取定购列表失败
	public static final int MISMP_RESULTCODE_GET_ORDERLIST_FAILED = 203;
	
	
	/** MISMP ERROR DESCRIPTION */
	
	// 成功
	public static final String MISMP_DESCRIPTION_SUCCESS = "成功";
	
	// 发端设备非法
	public static final String MISMP_DESCRIPTION_DEVICE_SRC_ILLEGAL = "发端设备非法";
	
	// IMSI非法
	public static final String MISMP_DESCRIPTION_IMSI_ILLEGAL = "IMSI非法";
	
	// 终端用户不存在
	public static final String MISMP_DESCRIPTION_TERUSER_INEXISTENT  = "终端用户不存在";

	// 手机号码非法
	public static final String MISMP_DESCRIPTION_PHONE_NUM_ILLEGAL  = "手机号码非法";
	
	// 产品ID非法
	public static final String MISMP_DESCRIPTION_PRODUCT_ID_ILLEGAL  = "产品ID非法";

	// 产品不存在
	public static final String MISMP_DESCRIPTION_PRODUCT_INEXISTENT  = "产品不存在";
	
	// 产品未发布
	public static final String MISMP_DESCRIPTION_PRODUCT_UNISSUED  = "产品未发布";
	
	// 非法定购类型
	public static final String MISMP_DESCRIPTION_ORDER_TYPE_ILLEGAL  = "非法定购类型";
	
	// 非法参数
	public static final String MISMP_DESCRIPTION_PARAM_ILLEGAL  = "参数非法";
	
	// 定购关系已存在
	public static final String MISMP_DESCRIPTION_ORDER_REL_EXISTS  = "定购关系已存在";

	// 定购关系不存在
	public static final String MISMP_DESCRIPTION_ORDER_REL_NOT_EXISTS  = "定购关系不存在";
	
	// 其他错误
	public static final String MISMP_DESCRIPTION_OTHER_ERROR  = "其他错误";

	// 定购失败
	public static final String MISMP_DESCRIPTION_ORDER_FAILED  = "定购失败";
	
	// 产品资费策略无效
	public static final String MISMP_DESCRIPTION_PRODUCT_FEE_ILLEGAL = "产品资费策略无效";
	
	// 订购关系数据不完整
	public static final String MISMP_DESCRIPTION_DATA_HALF_BAKED = "订购关系数据不完整";
	
	// 退定失败
	public static final String MISMP_DESCRIPTION_WITHDRAW_FAILED  = "退定失败";
	
	// 获取定购列表失败
	public static final String MISMP_DESCRIPTION_GET_ORDERLIST_FAILED  = "获取定购列表失败";
	

	

}

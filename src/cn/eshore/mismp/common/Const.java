package cn.eshore.mismp.common;

/**
 * <p>Title: Const</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Eshore</p>
 * 
 * @author zhuosf
 * @version 1.1
 * @since 2010/01/08
 */
public final class Const {
	
	/** 服务操作 */
	
	/* 操作类型 */
 	// 定购
	public static final int SERVICE_OPERATION_OPE_TYPE_ORDER = 0;
	
	// 暂停
	public static final int SERVICE_OPERATION_OPE_TYPE_SUSPEND = 1;
	
	// 恢复暂停
	public static final int SERVICE_OPERATION_OPE_TYPE_RESUME = 2;
	
	// 退定
	public static final int SERVICE_OPERATION_OPE_TYPE_WITHDRAW = 3;
	
	// 退定所有产品
	public static final int SERVICE_OPERATION_OPE_TYPE_WITHDRAW_ALL = 4;
	
	// 暂停所有产品
	public static final int SERVICE_OPERATION_OPE_TYPE_SUSPEND_ALL = 5;
	
	/* 发端设备 */
	// ISMP
	public static final String SERVICE_OPERATION_DEVICE_SRC_ISMP = "00";
	
	// MISMP
	public static final String SERVICE_OPERATION_DEVICE_SRC_MISMP = "01";
	
	// 手机客户端
	public static final String SERVICE_OPERATION_DEVICE_SRC_MPHONE = "02";
	
	/* 状态 */
	// 发起
	public static final byte SERVICE_OPERATION_STATE_STARTED = 1;
	
	// 完成
	public static final byte SERVICE_OPERATION_STATE_COMPLETED = 0;
	
	// 失败
	public static final byte SERVICE_OPERATION_STATE_FAILED = 2;
	
	
	/** 终端用户定购 */
	
	/* 状态 */
	// 正常(已开通)
	public static final int TERUSER_ORDER_STATUS_NORMAL = 0;

	// 申请定购
	public static final int TERUSER_ORDER_STATUS_APPLYED = 1;

	// 暂停恢复
	public static final int TERUSER_ORDER_STATUS_SUSPENDED_RESUME = 2;

	// 暂停
	public static final int TERUSER_ORDER_STATUS_SUSPENDED = 3;

	// 预注销
	public static final int TERUSER_ORDER_STATUS_PREWITHDRAW = 4;

	// 暂停预注销
	public static final int TERUSER_ORDER_STATUS_SUSPENDED_PREWITHDRAW = 5;

	// 注销
	public static final int TERUSER_ORDER_STATUS_WITHDRAWED = 6;

	// 试用
	public static final int TERUSER_ORDER_STATUS_PROBATION = 7;

	
}

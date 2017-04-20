package cn.eshore.mismp.dao.domain;

import java.util.Date;

/**
 * <p>Title: ServiceOperationVO</p>
 * <p>Description: service operation vo.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Eshore</p>
 * @author zhuosf
 * @version 1.0
 */
public class ServiceOperationVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键标识
	 */
	private long id;

	/**
	 * 流水号
	 */
	private String streamingNo;

	/**
	 * ISMP发布的产品ID
	 */
	private String productId;

	/**
	 * 手机号码
	 */
	private String phoneNum;

	/**
	 * 操作类型
	 * 	0:定购 1:暂停 2:暂停恢复 3:退定 4:退定所有产品 5:暂停所有产品
	 */
	private int opeType;

	/**
	 * 发端设备
	 * 	00:ISMP 01:MISMP 02:手机客户端
	 */
	private String deviceSrc;
	
	/**
	 * 结果码
	 */
	private int resultCode;
	
	/**
	 * 生效时间，不填表示立即生效
	 */
	private String effectiveTime;
	
	/**
	 * 失效时间，定购时，不填表示自动续定；退定时，不填表示立即失效
	 */
	private String expireTime;

	/**
	 * 操作状态 
	 * 	0:完成 1:发起 2:失败
	 */
	private byte state;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	

	public String getDeviceSrc() {
		return deviceSrc;
	}

	public void setDeviceSrc(String deviceSrc) {
		this.deviceSrc = deviceSrc;
	}

	public String getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOpeType() {
		return opeType;
	}

	public void setOpeType(int opeType) {
		this.opeType = opeType;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public String getStreamingNo() {
		return streamingNo;
	}

	public void setStreamingNo(String streamingNo) {
		this.streamingNo = streamingNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}

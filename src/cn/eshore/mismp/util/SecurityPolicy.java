package cn.eshore.mismp.util;

import java.security.*;

import org.apache.commons.logging.*;

import cryptix.util.core.Hex;

import sun.misc.*;

/**
 * <p>
 * Title: SecurityPolicy
 * </p>
 * <p>
 * Description: 安全策略类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: Eshore
 * </p>
 * 
 * @author zhuosf
 * @version 1.1
 */
public abstract class SecurityPolicy {

	private static final Log log = LogFactory.getLog(SecurityPolicy.class);
	
	/**
	 * 对给定的字符串进行MD5加密
	 * 
	 * @param source String
	 * @return String
	 */
	public static String encyptByMD5(String source) {
		if (source == null)
			return null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(source.getBytes());
			return Hex.toString(md.digest());
		} catch (NoSuchAlgorithmException ex) {
			log.error("" + ex);
			return null;
		}
	}
	
	/**
	 * 对给定的字符窜进行BASE64加密
	 * 
	 * @param source String
	 * @return String
	 */
	public static String BASE64Encode(String source) {
		if (source == null)
			return null;
		try {
			return (new BASE64Encoder()).encode(source.getBytes());
		} catch (Exception e) {
			log.error("" + e);
			return null;
		}
	}

	/**
	 * 对给定的字符窜进行BASE64解密
	 * 
	 * @param source String
	 * @return String
	 */
	public static String BASE64Decode(String source) {
		if (source == null)
			return null;
		try {
			byte[] b = new BASE64Decoder().decodeBuffer(source);
			return new String(b);
		} catch (Exception e) {
			log.error("" + e);
			return null;
		}
	}
	
	/**
	 * 对给定的字符窜进行BASE64加密
	 * 
	 * @param source String
	 * @param encoding String
	 * @return String
	 */
	public static String BASE64Encode(String source, String encoding) {
		if (source == null)
			return null;
		try {
			return (new BASE64Encoder()).encode(source.getBytes(encoding));
		} catch (Exception e) {
			log.error("" + e);
			return null;
		}
	}

	/**
	 * 对给定的字符窜进行BASE64解密
	 * 
	 * @param source String
	 * @param encoding String
	 * @return String
	 */
	public static String BASE64Decode(String source, String encoding) {
		if (source == null)
			return null;
		try {
			byte[] b = new BASE64Decoder().decodeBuffer(source);
			return new String(b, encoding);
		} catch (Exception e) {
			log.error("" + e);
			return null;
		}
	}

}


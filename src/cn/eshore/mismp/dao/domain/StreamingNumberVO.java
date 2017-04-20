package cn.eshore.mismp.dao.domain;

/**
 * <p>
 * Title: StreamingNumberVO
 * </p>
 * <p>
 * Description: Streaming number vo. A integrated streaming number is a string
 * of 60 decimal numbers, which is form of: seg4seg3seg2seg1.
 * streamingNo=seg4+seg3+seg2+seg1.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: Eshore
 * </p>
 * 
 * @author zhuosf
 * @version 1.0
 */
public class StreamingNumberVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String seg1;

	private String seg2;

	private String seg3;

	private String seg4;

	public String getSeg1() {
		return seg1;
	}

	public void setSeg1(String seg1) {
		this.seg1 = seg1;
	}

	public String getSeg2() {
		return seg2;
	}

	public void setSeg2(String seg2) {
		this.seg2 = seg2;
	}

	public String getSeg3() {
		return seg3;
	}

	public void setSeg3(String seg3) {
		this.seg3 = seg3;
	}

	public String getSeg4() {
		return seg4;
	}

	public void setSeg4(String seg4) {
		this.seg4 = seg4;
	}
}

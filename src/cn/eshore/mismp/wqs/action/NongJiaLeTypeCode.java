package cn.eshore.mismp.wqs.action;

public class NongJiaLeTypeCode {
//	0代表农家乐
//	1代表渔家乐
	private static final String NONGJIALECODE="0";
	private static final String YUJIALECODE="1";

	
	public static String getTypeName(String typeCode){
		String typeName=typeCode;
		if(NONGJIALECODE.equals(typeCode)){
			typeName="农家乐";
		}else if(YUJIALECODE.equals(typeCode)){
			typeName="渔家乐";
		}
		return typeName;
	}
}

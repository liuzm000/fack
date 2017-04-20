import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;



public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
////		TwoDimensionalUtil.generateTwoDemimension("jiangh", "d:/aa", "aa.jpg", 400,400);
//		String str = "aa,";
//		System.out.println(str.split(",").length);
//		int  []  aa= {1,2,3,4,5};
		String ss = "%E5%A4%A9%E7%BF%BC%E5%AE%A2%E8%BF%90%E9%80%9A%E5%90%8E%E5%8F%B0";
		byte b [] = ss.getBytes("GBK");
		String result = new String(b,"iso-8859-1");
		System.out.println(result);
		
	}
	 
}

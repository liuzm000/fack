package cn.eshore.mismp.util.weather;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherParser {


	public String fetchUrlContent() throws IOException {
		URL url = new URL("http://121.8.216.250/top3.jsp");
		URLConnection URLconnection = url.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
		httpConnection.setConnectTimeout(10000);
		int responseCode = httpConnection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			return this.readContent(httpConnection);
		} else {
			return "error";
		}
	}

	private String readContent(HttpURLConnection httpConnection)
			throws IOException {
		InputStreamReader urlStream = new InputStreamReader(httpConnection.getInputStream(),"GBK");
		BufferedReader bufferedReader = new BufferedReader(
				urlStream);
		String sCurrentLine = "";
		String sTotalString = "";
		Pattern pt = Pattern.compile("(<MARQUEE.*?>(.*)</marquee>)");
		while ((sCurrentLine = bufferedReader.readLine()) != null) {
			
			sTotalString += sCurrentLine;
		}
		
		Matcher mather = pt.matcher(sTotalString);
		if(mather.find()) {
			return mather.group(2);
		}
		return "";
	}
	
	public static void main(String[] args)  throws Exception{
	   String str = new WeatherParser().fetchUrlContent();
//	   String  content = new String(str.getBytes("ISO-8859-1"),"UTF-8");
	   System.out.println(str);
	}

}

package cn.eshore.mismp.util.weather;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.axis.utils.StringUtils;


public class NsWeatherUtil {
	private  static String str = "";
	private  static boolean isInit = false;
	public NsWeatherUtil() {
		init();
	}
	

	public static String getWeather() {
		if(! isInit) {
			init();
		}
		if(StringUtils.isEmpty(str)) {
			try {
				NsWeatherUtil.str = new WeatherParser().fetchUrlContent();
			} catch (IOException e) {
				NsWeatherUtil.str = "";
				e.printStackTrace();
			}
		}
		return str;
	}
	private  static void  init() {
		isInit = true;
		Timer t = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				try {
					NsWeatherUtil.str = new WeatherParser().fetchUrlContent();
					System.out.println("重新获取天气预报情况");
				} catch (IOException e) {
					NsWeatherUtil.str ="";
					e.printStackTrace();
				}
			}
			
			
		};
		t.schedule(task, 0,1000*60*30);
		
	}
}

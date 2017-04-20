package cn.eshore.mismp.util;

import java.util.HashMap;
import java.util.Properties;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * <p>Title: </p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: poson</p>
 * @author Yu Songming
 * @version 1.0
 */
public class MobileGlobals {
	public static final String PARAMS_LIST = "paramsList";
	private static Properties properties = null;
	protected static CacheManager manager = new CacheManager(MobileGlobals.class.getResource("/ehcache.xml"));
	public static Cache orderCache = manager.getCache("orderLock");

	private synchronized static void loadProperties() {
		if (properties == null) {
			properties = new InitPropLoader().getProperties("config.properties");	
		}
    }
	
	public synchronized static void reloadProperties() {
		properties = null;
		properties = new InitPropLoader().getProperties("config.properties");	
    }
	
	public static String getProperty(String property){
		loadProperties();
		String value = null;
		if (properties != null) {
			value = properties.getProperty(property);
			if (value != null) {
				value = value.trim();				
			}			
		}
		return value;
	}
	
	public static String[] getAirportCode(String cityName){
		if("北京".equals(cityName)){
			String[] str = {"NAY","PEK"};
			return str;
		} else if("长沙".equals(cityName)){
			String[] str = {"CSX","HHA"};
			return str;
		} else if("上海".equals(cityName)){
			String[] str = {"SHA","PVG"};
			return str;
		} else {
			return null;
		}
	}
	
	public static HashMap<String, String> airLineMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("EU", "鹰联航空");
		map.put("HO", "吉祥航空");
		map.put("SF", "包机航空");
		map.put("SJ", "南方航空运输");
		map.put("4G", "深圳航空");
		map.put("9C", "山西航空");
		map.put("ZH", "深圳航空");
		map.put("IV", "福建航空");
		map.put("ZV", "中西部航空");
		map.put("PN", "西部航空");
		map.put("XW", "新华航空");
		map.put("CJ", "北方航空");
		map.put("MU", "东方航空");
		map.put("CA", "国际航空");
		map.put("FM", "上海航空");
		map.put("F6", "浙江航空");
		map.put("G8", "长城航空");
		map.put("Z2", "中原航空");
		map.put("WU", "武汉航空");
		map.put("HU", "海南航空");
		map.put("G4", "贵州航空");
		map.put("2Z", "长安航空");
		map.put("FJ", "福建航空");
		map.put("SC", "山东航空");
		map.put("8C", "东星航空");
		map.put("CI", "中华航空");
		map.put("CZ", "南方航空");
		map.put("X2", "新华航空");
		map.put("WH", "西北航空");
		map.put("MF", "厦门航空");
		map.put("XO", "新疆航空");
		map.put("3Q", "云南航空");
		map.put("3U", "四川航空");
		return map;
	}

}


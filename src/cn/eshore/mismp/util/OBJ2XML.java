package cn.eshore.mismp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/***
 *** @version 实现java对象到XML转换
 *** @inheritDoc 实现java对象转换类
 *** 实现功能：实现java对象到XML转换
 *** @author jsingfly 2009-02-26
 ***/

public class OBJ2XML {
	
	public static String obj2xml(Object sourceObj, HashMap<String, Class> map) throws Exception{
		
		String xml = null;
		XStream xs = new XStream(new DomDriver());
		if(map != null){
			Set keySet = map.keySet();
			for(Iterator it = keySet.iterator(); it.hasNext(); ){
				String key = (String)it.next();
				Class value = map.get(key);
				xs.alias(key, value);
			}
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		xs.toXML(sourceObj, out); 
		byte bytes[] = out.toByteArray();
		//xml = new String(bytes, "UTF-8");
		xml = new String(bytes);
		return xml;
		
	}
	
	public static String obj2xml(Object sourceObj, HashMap<String, Class> map, HashMap<String, Class> impCollectionMap) throws Exception{
		
		String xml = null;
		XStream xs = new XStream(new DomDriver());
		if(map != null){
			Set keySet = map.keySet();
			for(Iterator it = keySet.iterator(); it.hasNext(); ){
				String key = (String)it.next();
				Class value = map.get(key);
				xs.alias(key, value);
			}
		}
		
		if(impCollectionMap != null){
			Set keySet = impCollectionMap.keySet();
			for(Iterator it = keySet.iterator(); it.hasNext(); ){
				String key = (String)it.next();
				Class value = impCollectionMap.get(key);
				xs.addImplicitCollection(value, key);
			}
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		xs.toXML(sourceObj, out); 
		byte bytes[] = out.toByteArray();
		//xml = new String(bytes, "UTF-8");
		xml = new String(bytes);
		return xml;
		
	}

	public static String obj2xmlForAttr(Object sourceObj, HashMap<String, Class> map, HashMap<String, Class> attrMap) throws Exception{
		
		String xml = null;
		XStream xs = new XStream(new DomDriver());
		if(map != null){
			Set keySet = map.keySet();
			for(Iterator it = keySet.iterator(); it.hasNext(); ){
				String key = (String)it.next();
				Class value = map.get(key);
				xs.alias(key, value);
			}
		}
		
		if(attrMap != null){
			Set keySet = attrMap.keySet();
			for(Iterator it = keySet.iterator(); it.hasNext(); ){
				String key = (String)it.next();
				Class value = attrMap.get(key);
				xs.useAttributeFor(value, key);
			}
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		xs.toXML(sourceObj, out); 
		byte bytes[] = out.toByteArray();
		//xml = new String(bytes, "UTF-8");
		xml = new String(bytes);
		return xml;
		
	}

	public static String obj2xmlForAttr(Object sourceObj, HashMap<String, Class> map, HashMap<String, Class> impCollectionMap, HashMap<String, Class> attrMap) throws Exception{
		
		String xml = null;
		XStream xs = new XStream(new DomDriver());
		if(map != null){
			Set keySet = map.keySet();
			for(Iterator it = keySet.iterator(); it.hasNext(); ){
				String key = (String)it.next();
				Class value = map.get(key);
				xs.alias(key, value);
			}
		}
		
		if(impCollectionMap != null){
			Set keySet = impCollectionMap.keySet();
			for(Iterator it = keySet.iterator(); it.hasNext(); ){
				String key = (String)it.next();
				Class value = impCollectionMap.get(key);
				xs.addImplicitCollection(value, key);
			}
		}
		
		if(attrMap != null){
			Set keySet = attrMap.keySet();
			for(Iterator it = keySet.iterator(); it.hasNext(); ){
				String key = (String)it.next();
				Class value = attrMap.get(key);
				xs.useAttributeFor(value, key);
			}
		}
		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		xs.toXML(sourceObj, out); 
		byte bytes[] = out.toByteArray();
		//xml = new String(bytes, "UTF-8");
		xml = new String(bytes);
		return xml;
		
	}

}

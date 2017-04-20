package cn.eshore.mismp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/***
 *** @version 实现XML到java对象转换
 *** @inheritDoc 实现XML到java对象转换类
 *** 实现功能：实现XML到java对象转换
 *** @author jsingfly 2009-02-26
 ***/

public class XML2OBJ {
		
	public static Object xml2Obj(String charset, String xml, Class targetObj, HashMap<String, Class> map) throws Exception{
		
		Object obj = null;
		XStream xs = new XStream(new DomDriver());
		if(map != null){
			Set keySet = map.keySet();
			for(Iterator it = keySet.iterator(); it.hasNext(); ){
				String key = (String)it.next();
				Class value = map.get(key);
				xs.alias(key, value);
			}
		}
		
		ByteArrayInputStream bis = new  ByteArrayInputStream(xml.getBytes(charset));
		obj = targetObj.newInstance();
		xs.fromXML(bis,obj);
		return obj;
		
	}
	
		public static Object xml2Obj(String xml, Class targetObj, HashMap<String, Class> map) throws Exception{
			
			Object obj = null;
			XStream xs = new XStream(new DomDriver());
			if(map != null){
				Set keySet = map.keySet();
				for(Iterator it = keySet.iterator(); it.hasNext(); ){
					String key = (String)it.next();
					Class value = map.get(key);
					xs.alias(key, value);
				}
			}
			
			ByteArrayInputStream bis = new  ByteArrayInputStream(xml.getBytes("UTF-8"));
			obj = targetObj.newInstance();
			xs.fromXML(bis,obj);
			return obj;
			
		}
		
		public static Object xml2Obj(String xml, Class targetObj, HashMap<String, Class> map, HashMap<String, Class> impCollectionMap) throws Exception{
			
			Object obj = null;
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
			
			ByteArrayInputStream bis = new  ByteArrayInputStream(xml.getBytes("UTF-8"));
			obj = targetObj.newInstance();
			xs.fromXML(bis,obj);
			return obj;
			
		}

		public static Object xml2ObjForAttr(String xml, Class targetObj, HashMap<String, Class> map, HashMap<String, Class> attrMap) throws Exception{
			
			Object obj = null;
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
			
			
			ByteArrayInputStream bis = new  ByteArrayInputStream(xml.getBytes("UTF-8"));
			obj = targetObj.newInstance();
			xs.fromXML(bis,obj);
			return obj;
			
		}
		
		
		public static Object xml2ObjForAttr(String xml, Class targetObj, HashMap<String, Class> map, HashMap<String, Class> impCollectionMap, HashMap<String, Class> attrMap) throws Exception{
			
			Object obj = null;
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
					try
					{
						xs.addImplicitCollection(value, key);
						System.out.println(xs);
					}catch(Exception e){
						e.printStackTrace();
					}
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
			
			ByteArrayInputStream bis = new  ByteArrayInputStream(xml.getBytes("UTF-8"));
			obj = targetObj.newInstance();
			xs.fromXML(bis,obj);
			return obj;
			
		}

		public static Object xml2ObjForAttr(String charset, String xml, Class targetObj, HashMap<String, Class> map, HashMap<String, Class> impCollectionMap, HashMap<String, Class> attrMap) throws Exception{
			
			Object obj = null;
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
					try
					{
						xs.addImplicitCollection(value, key);
						System.out.println(xs);
					}catch(Exception e){
						e.printStackTrace();
					}
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
			
			ByteArrayInputStream bis = new  ByteArrayInputStream(xml.getBytes(charset));
			obj = targetObj.newInstance();
			xs.fromXML(bis,obj);
			return obj;
			
		}
}

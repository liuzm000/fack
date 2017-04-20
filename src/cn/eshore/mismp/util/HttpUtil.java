package cn.eshore.mismp.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import cn.eshore.mismp.common.Consts;

/***
 *** @version 2.0
 *** @inheritDoc
 *** 实现功能：实现HTTP请求封装
 *** @author jsingfly 2010-03-01
 ***/

public class HttpUtil {
	
	private static HttpClient httpClient = null;
	
	private static String serverDetailAddress = null;

	protected static final Logger log = Logger.getLogger(HttpUtil.class);
	
	static{
		//线程安全处理
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		httpClient = new HttpClient(connectionManager);
		//构建调用客户端
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(120000);	
	}	
	
	public static String doGet(String serverAddress, String paramsString){
		
		String searchResult = "";
		String url = serverAddress + "?" + paramsString;
		log.info("doGet url=" + url);
		GetMethod getMethod = new GetMethod(url);
		
	   try {										
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				//http请求失败
				searchResult = Consts.INVOKE_FAIL_JSON;
			} else {
				//searchResult = getMethod.getResponseBodyAsString();;
				BufferedInputStream ins=new BufferedInputStream(getMethod.getResponseBodyAsStream());
				byte resultBytes[] = readUrlStream(ins);
				if(resultBytes != null && resultBytes.length >0){
					searchResult = new String(resultBytes, "UTF-8");
					log.info(searchResult);
				}
			}
			
		} catch (HttpException e) {
			log.error("HttpUtil goGet unexpected exception:", e);
			searchResult = Consts.INVOKE_FAIL_JSON;
		} catch (Exception e) {
			log.error("HttpUtil goGet unexpected exception:", e);
			searchResult = Consts.INVOKE_FAIL_JSON;
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return searchResult;
	}

	public static String doGet(String url){
		String searchResult = "";
		log.info("doGet url=" + url);
		GetMethod getMethod = new GetMethod(url);
	   try {										
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				//http请求失败
				searchResult = Consts.INVOKE_FAIL_JSON;
			} else {
				//searchResult = getMethod.getResponseBodyAsString();;
				BufferedInputStream ins=new BufferedInputStream(getMethod.getResponseBodyAsStream());
				byte resultBytes[] = readUrlStream(ins);
				if(resultBytes != null && resultBytes.length >0){
					searchResult = new String(resultBytes, "UTF-8");
				}
			}
			
		} catch (HttpException e) {
			log.error("HttpUtil goGet unexpected exception:", e);
			searchResult = Consts.INVOKE_FAIL_JSON;
		} catch (Exception e) {
			log.error("HttpUtil goGet unexpected exception:", e);
			searchResult = Consts.INVOKE_FAIL_JSON;
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return searchResult;
	}

	
	//提交GET请求
	public static byte[] deGetByte(String url){
		byte resultBytes[] = null;
		log.info("deGetByte url=" + url);
		GetMethod getMethod = new GetMethod(url);
		
	   try {										
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				log.error("http调用失败 url=" + url + "|statusCode=" + statusCode);
			} else {
				
				BufferedInputStream ins=new BufferedInputStream(getMethod.getResponseBodyAsStream());
				resultBytes = readUrlStream(ins);
				if(resultBytes == null && resultBytes.length ==0){
					log.error("http调用无数据返回 url=" + url + "|statusCode=" + statusCode);
				}
			}
			
		} catch (HttpException e) {
			log.error("deGetByte unexpected exception:", e);
		} catch (Exception e) {
			log.error("deGetByte unexpected exception:", e);
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
				
		return resultBytes;
	}
	
public static String doPost(String serverAddress, HashMap<String, String> paramMap){
		
		String searchResult = "";
		String url = serverAddress ;
		log.info("url=" + url);
		PostMethod postMethod = new PostMethod(url);
		postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		NameValuePair[] data = new NameValuePair[paramMap.size()];
		Set<String> keySet = paramMap.keySet();
		Iterator<String> it = keySet.iterator();
		int i = 0;
	    while(it.hasNext()){
	    	String key = it.next();
	    	String value = paramMap.get(key);
	    	data[i] = new NameValuePair(key, value);
	    	i ++;
	    }
		
		postMethod.setRequestBody(data);
		
	   try {										
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				//http请求失败
				searchResult = Consts.INVOKE_FAIL_JSON;
			} else {
				//searchResult = postMethod.getResponseBodyAsString();
				BufferedInputStream ins=new BufferedInputStream(postMethod.getResponseBodyAsStream());
				byte resultBytes[] = readUrlStream(ins);
				if(resultBytes != null && resultBytes.length >0){
					searchResult = new String(resultBytes, "UTF-8");
					log.info(searchResult);
				}
			}
			
		} catch (HttpException e) {
			log.error("HttpUtil doPost unexpected exception:", e);
			searchResult = Consts.INVOKE_FAIL_JSON;
		} catch (Exception e) {
			log.error("HttpUtil doPost unexpected exception:", e);
			searchResult = Consts.INVOKE_FAIL_JSON;
		} finally {
			postMethod.releaseConnection();
		}
		return searchResult;
	}
	
	
	public static String doPost(String serverAddress, HashMap<String, String> paramMap, String userAgent){
		
		String searchResult = "";
		String url = serverAddress ;
		log.info("url=" + url);
		PostMethod postMethod = new PostMethod(url);
		postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		//设置请求头
		if(!StringUtil.isNull(userAgent)){
			postMethod.addRequestHeader("User-Agent",userAgent);
		}
		
		NameValuePair[] data = new NameValuePair[paramMap.size()];
		Set<String> keySet = paramMap.keySet();
		Iterator<String> it = keySet.iterator();
		int i = 0;
	    while(it.hasNext()){
	    	String key = it.next();
	    	String value = paramMap.get(key);
	    	data[i] = new NameValuePair(key, value);
	    	i ++;
	    }
		
		postMethod.setRequestBody(data);
		
	   try {										
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				//http请求失败
				searchResult = Consts.INVOKE_FAIL_JSON;
			} else {
				//searchResult = postMethod.getResponseBodyAsString();
				BufferedInputStream ins=new BufferedInputStream(postMethod.getResponseBodyAsStream());
				byte resultBytes[] = readUrlStream(ins);
				if(resultBytes != null && resultBytes.length >0){
					searchResult = new String(resultBytes, "UTF-8");
					log.info(searchResult);
				}
			}
			
		} catch (HttpException e) {
			log.error("HttpUtil doPost unexpected exception:", e);
			searchResult = Consts.INVOKE_FAIL_JSON;
		} catch (Exception e) {
			log.error("HttpUtil doPost unexpected exception:", e);
			searchResult = Consts.INVOKE_FAIL_JSON;
		} finally {
			postMethod.releaseConnection();
		}
		return searchResult;
	}
	
	public static String doPost(String serverAddress, HashMap<String, String> paramMap, HashMap<String, String> headMap){
		
		String searchResult = "";
		String url = serverAddress ;
		log.info("url=" + url);
		PostMethod postMethod = new PostMethod(url);
		postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		
		Set headSet = headMap.keySet();
		Iterator<String> headIt = headSet.iterator();
		while(headIt.hasNext()){
	    	String key = headIt.next();
	    	String value = headMap.get(key);
	    	postMethod.addRequestHeader(key,value);
		}
		
		NameValuePair[] data = new NameValuePair[paramMap.size()];
		Set<String> keySet = paramMap.keySet();
		Iterator<String> it = keySet.iterator();
		int i = 0;
	    while(it.hasNext()){
	    	String key = it.next();
	    	String value = paramMap.get(key);
	    	data[i] = new NameValuePair(key, value);
	    	i ++;
	    }
		
		postMethod.setRequestBody(data);
		
	   try {										
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				//http请求失败
				searchResult = Consts.INVOKE_FAIL_JSON;
			} else {
				//searchResult = postMethod.getResponseBodyAsString();
				BufferedInputStream ins=new BufferedInputStream(postMethod.getResponseBodyAsStream());
				byte resultBytes[] = readUrlStream(ins);
				if(resultBytes != null && resultBytes.length >0){
					searchResult = new String(resultBytes, "UTF-8");
					log.info(searchResult);
				}
			}
			
		} catch (HttpException e) {
			log.error("HttpUtil doPost unexpected exception:", e);
			searchResult = Consts.INVOKE_FAIL_JSON;
		} catch (Exception e) {
			log.error("HttpUtil doPost unexpected exception:", e);
			searchResult = Consts.INVOKE_FAIL_JSON;
		} finally {
			postMethod.releaseConnection();
		}
		return searchResult;
	}
	
	private static byte[] readUrlStream(BufferedInputStream bufferedInputStream)
			throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100]; // buff用于存放循环读取的临时数据
		int rc = 0;
		while ((rc = bufferedInputStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}

		return swapStream.toByteArray();
	}


	public static void main(String args[]){
		String serverAddress = "http://localhost:8080/datacenter/sale.htm";
		//appkey=2ec45e9835d988ae&format=json&method=user.login&presetSign=100&v=1.0&sig=a5d22a10050746cc0f4391226394748a451562d2
		//appkey=2ec45e9835d988ae&format=json&method=user.login&presetSign=100&v=1.1&sig=f114040eabf2263f2642a210ac420c47c6781790
//		HashMap <String, String> map = new HashMap<String, String> ();
//		map.put("appkey", "2ec45e9835d988ae");
//		map.put("format", "json");
//		map.put("method", "user.login");
//		map.put("presetSign", "100");
//		map.put("v", "1.1");
//		map.put("sig", "f114040eabf2263f2642a210ac420c47c6781790");
//		
//		HashMap <String, String> headMap = new HashMap<String, String> ();
//		headMap.put("x-up-calling-line-id", "13316099369");
		String result = HttpUtil.doGet(serverAddress,"areacode=0760&phone=ME811&category=android&method=getAdslImgByLevel&type=3");
		System.out.println(result);
	}
}

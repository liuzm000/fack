package cn.eshore.mismp.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.eshore.mismp.common.Consts;

import java.util.concurrent.ConcurrentHashMap;
/***
 *** @version 1.0
 *** @inheritDoc HTTP SERVLET处理基类
 *** 实现功能：实现Http请求参数封装及ACTION方法调用框架实现
 *** @author jsingfly 2009-04-20
 ***/

public abstract class BaseHttpServlet extends ProxyHttpServlet {
	
	//动作ACTION
	private static String ACTION_METHOD = "method";
	
	//空参数常量
	private static String NULL_PARAM = "java.lang.NullPointerException";
	
	//调用方法及参数封装Map
	private static ConcurrentHashMap<String, Class> invokeMap = new ConcurrentHashMap<String, Class>();

	//doGet方法
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		OutputStream out = response.getOutputStream();
		ByteArrayOutputStream byteout = new ByteArrayOutputStream();	
		
		//返回客户端结果
		String result = "";
		
		String actionMethod = request.getParameter(ACTION_METHOD);
		//action存在
		if(StringUtil.isNull(actionMethod)){
			return;
		}
		
		//查找注册方法
		Class paramClass = invokeMap.get(actionMethod);
		
		String className = paramClass.getName();
		if(className.equals(NULL_PARAM)){
			// 封装参数并执行action对应method
			Object param = null;
			try {
				Method invokeMethod = null;
				invokeMethod = this.getClass().getMethod(actionMethod);
				result = (String) invokeMethod.invoke(this);

			} catch (Exception e) {
				e.printStackTrace();
				result = Consts.SYS_ERROR_JSON;
			}
		}
		else{
			// 封装参数并执行action对应method
			Object param = null;
			try {
				param = requestParam2Obj(request, paramClass);
				Method invokeMethod = null;
				invokeMethod = this.getClass().getMethod(actionMethod,
						paramClass);
				result = (String) invokeMethod.invoke(this, param);
			} catch (Exception e) {
				e.printStackTrace();
				result = Consts.SYS_ERROR_JSON;
			}
		}
		
		
		
		byteout.write(result.getBytes("UTF-8"));
		byteout.writeTo(out);
		byteout.close();
	}
	
	//注册调用方法及封装参数类型
	public static void registerInvokeMethod(String method, Class paramClass){
		invokeMap.put(method, paramClass);
	}
	
	//注册空参数
	public static void registerInvokeMethod(String method){
		invokeMap.put(method, NullPointerException.class);
	}
	
	//从request请求封装参数
	private Object requestParam2Obj(HttpServletRequest request, Class cls)throws Exception{
		
		Object param = null;
		param = cls.newInstance();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			StringBuffer setMethodNameBuffer = new StringBuffer("");
			String setMethodName = setMethodNameBuffer.append("set").append(
					fieldName.substring(0, 1).toUpperCase()).append(
					fieldName.substring(1)).toString();
			
			Method setMethod = cls.getMethod(setMethodName, field.getType());
			setMethod.invoke(param, request.getParameter(fieldName));		

		}

		return param;
	}
	
	
	// 字符串判断内部类
	private static class StringUtil {
		public static boolean isNull(String str) {
			return (str == null || str.trim().equals("")) ? true : false;
		}

	}
	
}

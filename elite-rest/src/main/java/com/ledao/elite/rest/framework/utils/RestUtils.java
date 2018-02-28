package com.ledao.elite.rest.framework.utils;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.rest.interceptor.MyRequestWrapper;
import com.ledao.elite.rest.interceptor.RestSafetyInterceptor;

/**
 * rest通用工具类
 * */
public class RestUtils {
	
	/**
	 * 获取request中的指定参数
	 * */
	public static MyRequestWrapper getRequest(){
		MyRequestWrapper req=RestSafetyInterceptor.localRequest.get();
		return req;
	}
	
	/**
	 * 获取request中的指定参数
	 * */
	public static String getRequestBody(){
		MyRequestWrapper req=getRequest();
		String reqJson=req.getBody();
		if(StringUtils.isEmpty(reqJson)){
			reqJson="{}";
		}
		return reqJson;
	}
	
	/**
	 * 获取request中的指定参数
	 * */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getRequestMap(){
		String reqJson=getRequestBody();
		Map<String,Object> map=JSON.parseObject(reqJson, Map.class);
		return map;
	}
	
	/**
	 * 获取request中的指定参数
	 * */
	public static Object getRequestParam(String paramKey){
		Map<String,Object> map=getRequestMap();
		Object param=null;
		for(String key:map.keySet()){
			if(paramKey.equals(key)){
				param=map.get(key);
				break;
			}
		}
		return param;
	}

}

package com.ledao.elite.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * HTTP CLIENT 工具类
 * */
public class HttpClientUtil {

	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(15000).setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000).build();
	
	public static String _postToString(String url,String reqJson){
		String result=null;
		try {
			HttpEntity entity=_executePost(url, null, reqJson);
			result=entity==null?null:EntityUtils.toString(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static InputStream _postToInputStream(String url,String reqJson){
		InputStream input=null;
		try {
			HttpEntity entity=_executePost(url, null, reqJson);
			input=entity==null?null:entity.getContent();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	public static String _postToString(String url,List<NameValuePair> formParams){
		String result=null;
		try {
			HttpEntity entity=_executePost(url, formParams, null);
			result=entity==null?null:EntityUtils.toString(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String _getToString(String url) {
		CloseableHttpClient client = HttpClients.createDefault();
		String result=null;
		try {
			HttpGet get = new HttpGet(url);
			get.setConfig(requestConfig);
			CloseableHttpResponse res = client.execute(get);
			HttpEntity entity=res.getEntity();
			result=entity==null?null:EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	private static HttpEntity _executePost(String url,
			List<NameValuePair> formParams,String reqJson) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpEntity httpEntity = null;
		try {
			HttpPost post = new HttpPost(url);
			HttpEntity entity = null;
			if(reqJson==null){
				entity=new UrlEncodedFormEntity(formParams, "UTF-8");
			}else{
				entity=new StringEntity(reqJson, "UTF-8");
			}
			post.setEntity(entity);
			post.setConfig(requestConfig);
			CloseableHttpResponse res = client.execute(post);
			if (HttpStatus.SC_OK == res.getStatusLine().getStatusCode()) {
				httpEntity = res.getEntity();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpEntity;
	}

	/**
	 * 验证时间是否已经过期
	 * 
	 * @param createTime
	 *            :开始计算时间
	 * @param expireTime
	 *            :过期时间(分钟)
	 * 
	 * @return true(未过期)false(已过期)
	 * */
	public static boolean calculateDateExpire(Date createTime,
			Integer expireTime) {
		boolean flag = false;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -expireTime);
		Long ct = calendar.getTime().getTime();
		/** 当前时间减去设置的过期时间，如果它小于等于创建时间则表示未过期 */
		if (ct <= createTime.getTime()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 获取参数map
	 * */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		if (null != request) {
			Set<String> paramsKey = request.getParameterMap().keySet();
			for (String key : paramsKey) {
				params.put(key, request.getParameter(key));
			}
		}
		return params;
	}

}

package com.ledao.elite.core.framework.plugin.sms;

import java.util.Map;

import com.ledao.elite.core.exception.EliteBusinessException;

/**
 * 短信服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface SmsService {

	/**
	 * 短信发送接口
	 * 
	 * @param paramMap
	 * @return String
	 * */
	String send(Map<String, Object> paramMap)throws EliteBusinessException;
}

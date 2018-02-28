package com.ledao.elite.core.framework.plugin.jpush;

import java.util.Map;

import com.ledao.elite.core.exception.EliteBusinessException;

public interface JPushService {
	
	/**
	 * 移动端端消息及时推送
	 * 
	 * @param paramMap
	 * @param isSync
	 * */
	void push(Map<String,Object> paramMap,boolean isSync)throws EliteBusinessException;
	
	/**
	 * 移动端消息定时推送
	 * */
	void schedulePush()throws EliteBusinessException;
}


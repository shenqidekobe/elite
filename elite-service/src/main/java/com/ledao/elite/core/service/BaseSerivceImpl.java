package com.ledao.elite.core.service;

import org.slf4j.LoggerFactory;

import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;

/**
 * 基础服务实现
 * */
public class BaseSerivceImpl {
	
	protected ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(getClass());
	
	/**
	 * 参数验证
	 * */
 	protected void verifyParams(Object...objects){
		if(objects==null)
			throw new EliteServiceException("参数不能为空", ErrorCodeEnum.PARAM_ISNULL.code);
		for(Object obj:objects){
			if(obj==null)
				throw new EliteServiceException("参数不能为空",ErrorCodeEnum.PARAM_ISNULL.code);
		}
	}

}

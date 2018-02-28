package com.ledao.elite.rest.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.LoggerFactory;

import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.rest.framework.exection.NoLoginException;
import com.ledao.elite.rest.framework.utils.RestUtils;

/**
 * Controller - 基类
 * 
 * @author kobe.liu
 * @version 1.0
 */
public class BaseController {
	
	protected ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(getClass());
	
	/** 系统404页 */
	protected static final String NOT_FOUND_VIEW = "/common/unfound";
	
	/** 系统500页 */
	protected static final String ERROR_VIEW = "/common/error";
	
	/** 会员操作无权限页 */
	protected static final String UNAUTHORIZED_VIEW = "/common/unauthorized";
	
	/** 支付失败页 */
	protected static final String PAY_FAIL_VIEW = "/common/payfail";
	
	/** 支付中页 */
	protected static final String PAY_ING_VIEW = "/common/paying";
	
	/** 默认时间格式化*/
	protected static final String DATE_FORMAT_DEFAULT_PATTEN="yyyy-MM-dd HH:mm:ss";

	@Resource
	protected EventPublishService eventPublishService;
	
	/**
	 * 解析request数据，并验证非空参数
	 * */
	protected String parseRequest(String...validFields){
		String reqJson=RestUtils.getRequestBody();
		java.util.Map<String,Object> map=RestUtils.getRequestMap();
		for(String key:map.keySet()){
			if(ArrayUtils.contains(validFields, key)){
				Object val=map.get(key);
				if(val==null){
					throw new EliteBusinessException("参数："+key+"不能为空",ErrorCodeEnum.ERROR.code);
				}
				
			}
		}
		return reqJson;
	}
	
	/**
	 * 获取会员ID，并验证其是否已经登录
	 * */
	protected Long getMemberId() {
		Object memberId=RestUtils.getRequestParam("memberId");
		if(memberId==null)
			throw new NoLoginException("当前未登录");
		return Long.parseLong(memberId.toString());
	}
	
	/**
	 * 获取会员ID，没拿到就返回null
	 * */
	protected Long getMemberIdAsNull() {
		Object memberId=RestUtils.getRequestParam("memberId");
		if(memberId==null)
			return null;
		return Long.parseLong(memberId.toString());
	}
}
package com.ledao.elite.site.chain;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.cache.custom.SmsSendCache;
import com.ledao.elite.core.framework.chain.HandlerChain;
import com.ledao.elite.core.utils.WebUtils;


/**
 * 短信发送频率限制
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component("validSms")
public class ValidSmsChain extends HandlerChain{

	@Override
	public void handle(Object args) throws EliteBusinessException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String ip=WebUtils.getClientIp(request);
		boolean flag=SmsSendCache.valid(ip);
		if(!flag){
			//throw new IllegalException("非法操作，短信发送频率超过限制");
		}
	}

}

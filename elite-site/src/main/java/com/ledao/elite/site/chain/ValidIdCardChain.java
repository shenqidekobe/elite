package com.ledao.elite.site.chain;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.cache.custom.IdCardCache;
import com.ledao.elite.core.framework.chain.HandlerChain;
import com.ledao.elite.site.exception.IllegalException;
import com.ledao.elite.site.shiro.PrincipalService;


/**
 * 验证身份证的频率限制,每人每天三次机会
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component("validIdCard")
public class ValidIdCardChain extends HandlerChain{
	
	@Resource
	protected PrincipalService principalService;

	@Override
	public void handle(Object args) throws EliteBusinessException {
		Long memberId=principalService.getPrincipalId();
		boolean flag=IdCardCache.valid(memberId);
		if(!flag){
			throw new IllegalException("非法操作，身份证验证频率超过限制");
		}
	}

}

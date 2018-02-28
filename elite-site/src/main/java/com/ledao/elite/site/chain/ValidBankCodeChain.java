package com.ledao.elite.site.chain;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.cache.custom.BankCardCache;
import com.ledao.elite.core.framework.chain.HandlerChain;
import com.ledao.elite.site.exception.IllegalException;
import com.ledao.elite.site.shiro.PrincipalService;


/**
 * 验证银行卡信息频率限制,每人每天五次机会
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component("validBankCode")
public class ValidBankCodeChain extends HandlerChain{

	@Resource
	protected PrincipalService principalService;
	
	@Override
	public void handle(Object args) throws EliteBusinessException {
		Long memberId=principalService.getPrincipalId();
		boolean flag=BankCardCache.valid(memberId);
		if(!flag){
			throw new IllegalException("非法操作，银行卡验证频率超过限制");
		}
	}

}

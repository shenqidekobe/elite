package com.ledao.elite.site.chain.identity;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.chain.HandlerChain;
import com.ledao.elite.site.exception.NoLoginException;
import com.ledao.elite.site.exception.UnauthorizedOfException;
import com.ledao.elite.site.shiro.Principal;
import com.ledao.elite.site.shiro.PrincipalService;


/**
 * 验证精英是否审核过的权限
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component("ValidIsReviewed")
public class ValidIsReviewedChain extends HandlerChain{

	@Resource
	protected PrincipalService principalService;
	
	@Resource
	protected Principal principal;
	
	@Override
	public void handle(Object args) throws EliteBusinessException {
		Long memberId=this.principalService.getPrincipalId();
		if(memberId==null)
			throw new NoLoginException("当前未登录");
		if(!this.principal.getStatus().equals(Member_Status.normal)){
			throw new UnauthorizedOfException("审核没通过，不能执行操作");
		}
	}

}

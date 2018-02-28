package com.ledao.elite.site.chain.identity;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.chain.HandlerChain;
import com.ledao.elite.site.exception.NoLoginException;
import com.ledao.elite.site.exception.UnauthorizedOfException;
import com.ledao.elite.site.shiro.Principal;
import com.ledao.elite.site.shiro.PrincipalService;


/**
 * 验证渠道项目方的权限
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component("validParnterCompany")
public class ValidParnterCompanyChain extends HandlerChain{

	@Resource
	protected PrincipalService principalService;
	
	@Resource
	protected Principal principal;
	
	@Override
	public void handle(Object args) throws EliteBusinessException {
		Long memberId=this.principalService.getPrincipalId();
		if(memberId==null)
			throw new NoLoginException("当前未登录");
		boolean flag=false;
		Map<String,String> identityMap=this.principal.getIdentityMap();
		for(String key:identityMap.keySet()){
			if(MemberIdentity_Type.partnerElite.name().equals(key)){
				flag=true;
			}
		}
		if(!flag){
			throw new UnauthorizedOfException("没有渠道列表方的权限，不能执行操作");
		}
	}

}

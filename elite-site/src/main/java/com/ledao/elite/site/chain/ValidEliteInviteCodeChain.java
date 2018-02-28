package com.ledao.elite.site.chain;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.chain.HandlerChain;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;

/**
 * 验证精英注册的邀请码
 * */
@Component("validEliteInviteCodeChain")
public class ValidEliteInviteCodeChain extends HandlerChain {

	
	@Resource
	private PlatformInviteCodeService platformInviteCodeService;
	
	@Override
	public void handle(Object args) throws EliteBusinessException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String inviteCode=request.getParameter("inviteCode");//邀请码
		String currentType=request.getParameter("currentType");//注册的类型
		if(!MemberIdentity_Type.elite.name().equals(currentType)){
			return;
		}
		if(StringUtils.isEmpty(inviteCode)){
			throw new EliteBusinessException("请输入您的邀请码", ErrorCodeEnum.PARAM_ISNULL.code);
		}
		PlatformInviteCode obj=this.platformInviteCodeService.findPlatformInviteCodeByCode(inviteCode);
		if(obj==null){
			throw new EliteBusinessException("火星上都未找到此邀请码", ErrorCodeEnum.INVITE_NOT_EXIST.code);
		}
		switch (obj.getType()) {
		case channel_company:
			throw new EliteBusinessException("火星上都未找到此邀请码", ErrorCodeEnum.INVITE_NOT_EXIST.code);
		case channel_elite:
			//渠道人才方邀请码可以无限使用多次
			break;
	    case member:
			//针对会员的邀请码只能使用一次,并且查看是否已经过期
	    	if(obj.getInviteCount()>=1){
	    		throw new EliteBusinessException("此邀请码已被使用",ErrorCodeEnum.OBJECT_USE.code);
	    	}else if(obj.getExpireTime()!=null&&obj.getExpireTime().before(new Date())){
	    		throw new EliteBusinessException("此邀请码已过期",ErrorCodeEnum.OBJECT_EXPIRE.code);
	    	}
			break;
	    case platform_one:
	    	if(obj.getInviteCount()>=1){
	    		throw new EliteBusinessException("此邀请码已被使用",ErrorCodeEnum.OBJECT_USE.code);
	    	}else if(obj.getExpireTime()!=null&&obj.getExpireTime().before(new Date())){
	    		throw new EliteBusinessException("此邀请码已过期",ErrorCodeEnum.OBJECT_EXPIRE.code);
	    	}
	    	break;
	    case platform_more:
	    	if(obj.getInviteCount()>=50){
	    		throw new EliteBusinessException("此邀请码已被使用",ErrorCodeEnum.OBJECT_USE.code);
	    	}else if(obj.getExpireTime()!=null&&obj.getExpireTime().before(new Date())){
	    		throw new EliteBusinessException("此邀请码已过期",ErrorCodeEnum.OBJECT_EXPIRE.code);
	    	}
	    	break;
	    case platform_infinite:
	    	//平台邀请码不限次数
	    	break;
		}
	}

}

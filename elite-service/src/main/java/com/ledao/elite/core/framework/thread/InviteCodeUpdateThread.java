package com.ledao.elite.core.framework.thread;

import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.core.utils.SpringContextUtil;

import lombok.Setter;

/**
 * 邀请码更新线程
 * 更新邀请码使用的数量
 * */
public class InviteCodeUpdateThread implements Runnable{
	
	@Setter
	private String inviteCode;
	
    private PlatformInviteCodeService platformInviteCodeService;
	
	public InviteCodeUpdateThread(){
		platformInviteCodeService=(PlatformInviteCodeService)SpringContextUtil.getBean("platformInviteCodeService");
	}

	@Override
	public void run() {
		PlatformInviteCode platformInviteCode=this.platformInviteCodeService.findPlatformInviteCodeByCode(inviteCode);
		if(platformInviteCode==null){
			return;
		}
		Integer inviteCount=platformInviteCode.getInviteCount()+1;
		platformInviteCode.setInviteCount(inviteCount);
		this.platformInviteCodeService.updatePlatformInviteCode(platformInviteCode);
		
	}

}

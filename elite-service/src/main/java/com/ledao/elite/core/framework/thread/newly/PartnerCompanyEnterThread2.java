package com.ledao.elite.core.framework.thread.newly;

import org.apache.commons.lang3.StringUtils;

import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.partner.PartnerProject;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.partner.PartnerProjectRecordService;
import com.ledao.elite.core.service.partner.PartnerProjectService;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.core.utils.SpringContextUtil;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目方发布项目 项目方发布项目，以 推荐人手机，验证码，项目方的手机号 来关联，）发布成功，推荐人备案项目中添加相应的一条 状态为未入住
 * ，（交完意向金后为入驻状态） 注意：以发布人填写的推荐人为主
 */
@Slf4j
public class PartnerCompanyEnterThread2 implements Runnable {

	@Setter
	private String account;//项目人帐号
	@Setter
	private String inviteCode;//邀请码
	@Setter
	private String recommdUser;//推荐人帐号
	@Setter
	private String contactPhone;//联系人手机号
	@Setter
	private Long projectId;//备案项目ID
	
	private MemberPartnerCompanyService memberPartnerCompanyService;
	private PartnerProjectService partnerProjectService;
	private PlatformInviteCodeService platformInviteCodeService;
	private PartnerProjectRecordService partnerProjectRecordService;
	
	public PartnerCompanyEnterThread2(){
		memberPartnerCompanyService=(MemberPartnerCompanyService)SpringContextUtil.getBean("memberPartnerCompanyService");
		partnerProjectService=(PartnerProjectService)SpringContextUtil.getBean("partnerProjectService");
		platformInviteCodeService=(PlatformInviteCodeService)SpringContextUtil.getBean("platformInviteCodeService");
		partnerProjectRecordService=(PartnerProjectRecordService)SpringContextUtil.getBean("partnerProjectRecordService");
	}
	
	@Override
	public void run() {
		log.info("**********************************渠道项目方项目入驻线程开始处理**********************************");
		boolean enterFlag=false;//是否需要入驻
		boolean secondFlag=false;//是否需要二次检索
		if(StringUtils.isEmpty(account)||projectId==null)
			return;
		PlatformInviteCode partnerInviteCode=null;
		PartnerProject partnerProject=null;
		log.info("渠道项目方收到新的入驻项目，推荐人信息："+recommdUser+",邀请码为："+inviteCode+",联系人手机号："+contactPhone);
		//优先考虑发布人的填写的推荐人以及邀请码
		MemberPartnerCompany mpc=null;
		if(StringUtils.isNotEmpty(recommdUser)){
			mpc =this.memberPartnerCompanyService.findMemberPartnerCompanyByPhone(recommdUser);
		}
		if(mpc==null){
			//未找到推荐人信息，则从邀请码中获取
			if(StringUtils.isNotEmpty(inviteCode)){
				//检查邀请码是否存在
				partnerInviteCode=this.platformInviteCodeService.findPlatformInviteCodeByCode(inviteCode);
				if(partnerInviteCode!=null){
					enterFlag=true;
					Integer inviteCount=partnerInviteCode.getInviteCount()+1;
					partnerInviteCode.setInviteCount(inviteCount);
					this.platformInviteCodeService.updatePlatformInviteCode(partnerInviteCode);
					mpc=this.memberPartnerCompanyService.findMemberPartnerCompanyByMemberId(partnerInviteCode.getUserId());
					partnerProject=this.partnerProjectService.findPartnerProjectAsEnter(account, recommdUser, contactPhone,mpc.getId());
				}else{
					secondFlag=true;
				}
			}else{
				//非邀请码，非邀请手机的情况
				secondFlag=true;
			}
		}else{
			log.info("项目ID:"+projectId+" 直接入驻渠道方："+mpc.getId()+"********************************");
			//直接找到了渠道方，查询其是否有备案，没备案直接插入
			partnerProject=this.partnerProjectService.findPartnerProjectAsEnter(account, recommdUser, contactPhone,mpc.getId());
			enterFlag=true;
		}
		if(secondFlag){
			//再根据项目方帐号、邀请人、联系人，三者或查询，，未查到推荐的开始根据邀请码查询
			partnerProject=this.partnerProjectService.findPartnerProjectAsEnter(account, recommdUser, contactPhone,null);
			if(partnerProject==null)return;
			enterFlag=true;
			mpc=this.memberPartnerCompanyService.findMemberPartnerCompanybyId(partnerProject.getPartnerId());
		}
		if(enterFlag){
			Long partnerProjectId=partnerProject==null?null:partnerProject.getId();
			log.info("渠道方ID:"+mpc.getId()+",推荐的项目方入驻了项目ID:"+projectId+"，其备案项目ID:"+partnerProjectId);
			this.partnerProjectRecordService.createPartnerProjectRecord(mpc.getId(), partnerProjectId, projectId);
			
			Integer enterCount=mpc.getEnterCount()+1;
			mpc.setEnterCount(enterCount);
			this.memberPartnerCompanyService.updateMemberPartnerCompanyNoFixed(mpc);
		}
	}

}

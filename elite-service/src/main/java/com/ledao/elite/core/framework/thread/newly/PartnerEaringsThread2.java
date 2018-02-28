package com.ledao.elite.core.framework.thread.newly;

import java.math.BigDecimal;

import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.framework.thread.settle.PartnerEaringsSettlement;
import com.ledao.elite.core.utils.SpringContextUtil;

import lombok.Setter;

/**
 * 渠道方结算线程3.0
 * */
public class PartnerEaringsThread2  implements Runnable{
	
	@Setter
	private Long memberId;//推荐的会员ID
	@Setter
	private MemberIdentity_Type memberType;//推荐的会员类型
	@Setter
	private BigDecimal totalAmount;//总金额
	@Setter
	private Long projectId;//项目ID
	@Setter
	private Long stageId;//阶段ID
	@Setter
	private boolean firstStage=false;//是否第一阶段
	@Setter
	private boolean lastStage=false;//是否最后一个结算
	@Setter
	private Long taskId;//任务ID
	@Setter
	private MemberIdentity_Type partnerType=MemberIdentity_Type.partnerElite;//渠道方类型{默认人才推荐方}
	
	private PartnerEaringsSettlement partnerEaringsSettlement;
	
	public PartnerEaringsThread2(){
		partnerEaringsSettlement=(PartnerEaringsSettlement)SpringContextUtil.getBean("partnerEaringsSettlement");
	}
	
	@Override
	public void run() {
		partnerEaringsSettlement.settle(memberId, memberType, totalAmount, projectId, stageId, taskId, firstStage, lastStage, partnerType);
	}
	
}

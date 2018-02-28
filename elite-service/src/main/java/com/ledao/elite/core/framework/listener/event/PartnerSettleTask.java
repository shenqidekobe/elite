package com.ledao.elite.core.framework.listener.event;

import java.math.BigDecimal;

import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 渠道方收益结算任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class PartnerSettleTask extends BaseTask{

	
	private Long memberId;//推荐的会员ID
	private MemberIdentity_Type memberType;//推荐的会员类型
	private BigDecimal totalAmount;//总金额
	private Long projectId;//项目ID
	private Long stageId;//阶段ID
	private boolean firstStage=false;//是否第一阶段
	private boolean lastStage=false;//是否最后一个结算
	private Long taskId;//任务ID
	private MemberIdentity_Type partnerType=MemberIdentity_Type.partnerElite;//渠道方类型{默认人才推荐方}
}

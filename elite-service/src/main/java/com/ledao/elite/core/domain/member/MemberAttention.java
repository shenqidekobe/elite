package com.ledao.elite.core.domain.member;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员的关注信息、CTO关注的精英对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberAttention extends IdentifiedEntity {

	private static final long serialVersionUID = 1658184973752572678L;
	
	public static final String ATTENTION_ME="attention";//关注我的
	public static final String ME_ATTENTION="attentioned";//我关注的
	
	public static final String ATTENTION_SAVE="save";//关注
	public static final String ATTENTION_CANCEL="cancel";//取消关注
	
	private Long memberId;//会员ID
	private Long attentionMemberId;//关注的会员ID
	private int orders;//排序号
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号
    
    @Transient
    private MemberDetailInfo detailInfo;
    @Transient
    private Integer partnershipNum;
    @Transient
    private Integer attenionCount;
    
    

}

package com.ledao.elite.core.domain.member;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 我的团队对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberTeam extends IdentifiedEntity {

	private static final long serialVersionUID = -4026892208964774587L;
	
	private Long memberId;//会员ID
	private Long teamMemberId;//团队成员ID
	private int partnershipNum=1;//合伙次数
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastTime;//最后合伙时间
	
	@Transient
	private MemberDetailInfo info;
	
	@Transient
	private Boolean attentioned=false;//此精英是否被关注过

}

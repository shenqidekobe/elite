package com.ledao.elite.core.domain.project;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPassport;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * CTO关注的项目对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectAttention extends IdentifiedEntity {

	private static final long serialVersionUID = -5435817313441441792L;
	
	private Long memberId;//会员ID
	private Long projectId;//项目ID
	private int orders;//排序号

	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne
	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport member;//会员
}

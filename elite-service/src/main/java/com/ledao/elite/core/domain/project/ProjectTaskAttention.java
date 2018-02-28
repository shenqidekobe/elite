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
 * CTO关注的项目任务对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectTaskAttention extends IdentifiedEntity {

	private static final long serialVersionUID = -236272597770308865L;
	
	private Long memberId;//会员ID
	private Long taskId;//任务ID
	private int orders;//排序号
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport member;//会员
    
    @ManyToOne
	@JoinColumn(name = "taskId", insertable=false, updatable=false)
    private ProjectStageTask stageTask;//任务
    

}

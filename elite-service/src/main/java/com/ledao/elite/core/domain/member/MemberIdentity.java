package com.ledao.elite.core.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 会员的身份对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MemberIdentity extends IdentifiedEntity {

	private static final long serialVersionUID = 1305464760645102118L;
	
	//会员身份类型
	public enum MemberIdentity_Type{
		company("项目方"),
		elite("精英"),
		cto("CTO"),
		partnerCompany("项目渠道方"),
		partnerElite("人才渠道方");
		public String label;
		MemberIdentity_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	private Long memberId;//会员ID
	private Long foreignId;//外键ID
	private Integer level;//等级
	@Column(length=32)
	private String type;//类型
	@Column(length=32)
	private String name;//名称
	
	private Integer completeCount;//已完成量(项目数量、任务数量)
	private Integer handCount;//进行中的量(项目数量、任务数量)
	@Column(length=16)
	private String step;//信息完善的步骤
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号

}

package com.ledao.elite.core.domain.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员项目经验对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = true,of={})
@ToString(callSuper = true)
public class MemberProjects extends IdentifiedEntity {

	private static final long serialVersionUID = -7749992059341042532L;
	
	private Long memberId;//会员ID
	@Column(length=64)
	private String project;//项目名称
	@Column(length=64)
	private String company;//公司
	@Column(length=64)
	private String position;//职位
	@Temporal(TemporalType.DATE)
	private Date startTime;//开始时间
	@Temporal(TemporalType.DATE)
	private Date endTime;//结束时间
	@Column(length=2000)
	private String intro;//项目简介
	private int orders;//排序号
	
	/************************hibernate many one config**************************/
	@JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号

}

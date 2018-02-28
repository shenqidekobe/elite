package com.ledao.elite.core.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台对会员的评价对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberEvaluate extends IdentifiedEntity {

	private static final long serialVersionUID = 3693900347839779554L;
	
	private Long memberId;//会员ID
	private Long projectId;//参与的项目ID
	private double score;//评分
	@Column(length=512)
	private String comment;//评语
	@Column(length=32)
	private String status;//状态
	@Column(length=32)
	private String createType;//评价者类型
	private Long createId;//评价者

}

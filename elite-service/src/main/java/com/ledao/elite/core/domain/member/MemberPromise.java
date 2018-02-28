package com.ledao.elite.core.domain.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员签约书对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberPromise extends IdentifiedEntity {

	private static final long serialVersionUID = 7166203998519658435L;
	
	private Long memberId;//会员ID
	@Column(length=32)
	private String type;//签约书类型
	@Column(length=512)
	private String promiseReason;//签约理由
	@Column(length=512)
	private String unwindReason;//解约理由
	@Column(length=512)
	private String recommendReason;//推荐理由
	@Column(length=512)
	private String intro;//简介
	@Column(length=32)
	private String status;//状态
	private Long createId;//创建者
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;//审核时间
	private Long auditId;//审核者
	@Temporal(TemporalType.TIMESTAMP)
	private Date promiseTime;//签约时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date unwindTime;//解约时间

}

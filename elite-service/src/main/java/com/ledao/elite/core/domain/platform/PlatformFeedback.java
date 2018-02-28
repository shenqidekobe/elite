package com.ledao.elite.core.domain.platform;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台的意见反馈记录
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformFeedback extends IdentifiedEntity {

	private static final long serialVersionUID = 6628042455821711983L;
	
	//反馈的状态
	public enum Feedback_Status{
		init("初始记录"),
		feedback("已反馈"),
		deleted("已删除");
		public String label;
		Feedback_Status(String label){
			this.label=label;
		}
	}
	//反馈的类型
	public enum Feedback_Type{
		idea("初始记录");
		public String label;
		Feedback_Type(String label){
			this.label=label;
		}
	}
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Feedback_Status status;
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Feedback_Type type;
	@Column(length=256)
	private String title;//反馈标题
	@Column(length=2000)
	private String content;//内容
	private Long attaId;//附件记录
	private int orders;//排序号
	
	private Long createId;//创建者
	private Long auditId;//审核者
	private String reply;//回复内容

}

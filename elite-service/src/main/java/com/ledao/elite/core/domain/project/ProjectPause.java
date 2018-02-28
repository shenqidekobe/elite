package com.ledao.elite.core.domain.project;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目暂停申请记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectPause extends IdentifiedEntity {

	private static final long serialVersionUID = -2276558630737887849L;
	
	//暂停的状态
	public enum Pause_Status{
		wait_process("待处理"),
		agree("同意"),
		reject("驳回");
		public String label;
		Pause_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	private Long projectId;//项目ID
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Pause_Status status;//状态
	
	@Column(length=32)
	private String startPeer;//发起方
	private double pauseDay;//暂停天数
	@Column(length=512)
	private String reason;//暂停原因
	private BigDecimal amount;//费用
	@Column(length=32)
	private String bearPeer;//承担方

	private Long createId;//创建者
	@Temporal(TemporalType.TIMESTAMP)
	private Date bearTime;//承担方确认时间
	private Long bearId;//承担人处理人ID
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;//发起方确认时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date payTime;//发起方支付时间
	private Long payId;//发起方确认人id
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date informTime;//通知所有人的时间
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
}

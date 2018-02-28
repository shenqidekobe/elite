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
 * 项目需求变更对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectDemandChange extends IdentifiedEntity {

	private static final long serialVersionUID = 935823775811347216L;
	
	//需求变更
	public enum DemandChange_Status{
		wait_confirm("待确认"),
		confirmed("已确认"),
		reject("驳回");
		public String label;
		DemandChange_Status(String label){
			this.label=label;
		}
	}
	
	private Long projectId;//项目ID
	private Long stageId;//所处阶段的ID
	private Long memberId;//需求变更发起方的会员ID
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private DemandChange_Status status;//状态
	
	@Column(length=64)
	private String title;//需求名称
	@Column(length=256)
	private String reason;//变更原因
	@Column(length=1000)
	private String intro;//需求描述
	private Long attaId;//附件ID
	

	private BigDecimal amount;//需求变更产生的费用
	private int deferDay;//延期原因
	@Column(length=32)
	private String replyResult;//回复结果
	@Column(length=512)
	private String replyReason;//回复理由
	private Integer ceoDay;//给项目方增加多少天
	private BigDecimal ceoAmount;//给项目方增加多少费用
	private Integer ctoDay;//给接收方CTO增加多少天
	private BigDecimal ctoAmount;//给接收方CTO增加多少费用
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间

	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne
	@JoinColumn(name = "stageId", insertable=false, updatable=false)
    private ProjectDefineStage stage;//项目的阶段
    
}

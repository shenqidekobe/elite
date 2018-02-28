package com.ledao.elite.core.domain.member;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectStageTask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 会员的收益记录对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MemberIncome extends IdentifiedEntity{

	private static final long serialVersionUID = 1653875941314951494L;
	
	//收益类型
	public enum Income_Type{
		task("任务收益"),
		stage("CTO阶段收益"),
		guarantee("质保金收益"),
		parent_order_award("签单奖励"),
		partner_own("渠道本身收益"),
		partner_direct("渠道直接收益"),
		partner_indirect("渠道间接收益");
		public String label;
		Income_Type(String label){
			this.label=label;
		}
		public String getLabel() {
			return label;
		}
	}
	
	private Long memberId;//会员ID
	private Long projectId;//项目ID
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Income_Type incomeType;//收益类型
	private BigDecimal amount;//收益金额
	private BigDecimal taxAmount;//被扣税金额
	@Column(length=128)
	private String intro;//收益说明
	private Long sourceMemberId;//来源会员id
	private BigDecimal commissionRatio;//收益比例
	private Long stageId;//项目阶段id
	private Long taskId;//任务Id
	
	
	
	public MemberIncome(Long memberId, Long projectId, Income_Type incomeType, BigDecimal amount,BigDecimal taxAmount, BigDecimal commissionRatio,String intro,
			Long sourceMemberId, Long stageId, Long taskId) {
		super();
		this.memberId = memberId;
		this.projectId = projectId;
		this.incomeType = incomeType;
		this.amount = amount;
		this.taxAmount = taxAmount;
		this.commissionRatio=commissionRatio;
		this.intro = intro;
		this.sourceMemberId = sourceMemberId;
		this.stageId = stageId;
		this.taskId = taskId;
	}
	
	@Transient
	private BigDecimal allamount;//全部收益
	@Transient
	private String sourceMemberRealName;//来源姓名
	
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号
    @ManyToOne
    @JoinColumn(name = "sourceMemberId", insertable=false, updatable=false)
    private MemberPassport sourceMemberPassport;//来源会员帐号
    @ManyToOne
    @JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目信息
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "stageId", insertable=false, updatable=false)
    private ProjectDefineStage stage;//项目阶段
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "taskId", insertable=false, updatable=false)
    private ProjectStageTask task;//任务

}

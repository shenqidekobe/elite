package com.ledao.elite.core.domain.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectTender;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 推荐给会员的任务、项目等对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberRecommend extends IdentifiedEntity{

	private static final long serialVersionUID = -5141856061905667953L;
	
	//推荐的类型
	public enum MemberRecommend_Type{
		r_project("项目推荐"),
		r_task("任务推荐");
		public String label;
		MemberRecommend_Type(String label){
			this.label=label;
		}
	}
	//推荐状态
	public enum MemberRecommend_Status{
		normal("正常推荐"),
		cancel("取消推荐");
		public String label;
		MemberRecommend_Status(String label){
			this.label=label;
		}
	}
	private Long recommemberId;//推荐会员ID
	private Long memberId;//会员ID
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private MemberRecommend_Type type;//类型(什么类型的推荐)
	private Long projectId;//项目ID
	private Long taskId;//任务ID
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private MemberRecommend_Status status;//状态
	
	@Column(length=256)
	private String reason;//推荐理由
	@Temporal(TemporalType.TIMESTAMP)
	private Date viewTime;//查看时间
	
	
	@Transient
	private String recommemberName;//推荐人姓名
	@Transient
	private Integer tenderCount;//竞标此项目的数量
	@Transient
	private Integer taskCount;//竞标此项目的数量
	@Transient
	private ProjectTender tender;//竞标书
	
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "taskId", insertable=false, updatable=false)
    private ProjectStageTask task;//任务
    
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//任务
	

}

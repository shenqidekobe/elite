package com.ledao.elite.core.domain.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.sys.Attas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目的文件记录、立项书每阶段的文件记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectMaterial extends IdentifiedEntity {

	private static final long serialVersionUID = -6248233510253378679L;
	
	public static final String QUERYTYPE_ALL="all";//所有的文件
	public static final String QUERYTYPE_RECEIVE="receive";//我收到的
	public static final String QUERYTYPE_SEND="send";//我发出的
	public static final String QUERYTYPE_UNREAD="unread";//我的未读文件
	
	//文件状态定义
	public enum ProjectMaterial_Status{
		wait_audit("待审核"),
		pass("审核通过"),
		unpass("审核未通过"),
		deleted("已删除");
		public String label;
		ProjectMaterial_Status(String label){
			this.label=label;
		}
		public String getLabel() {
			return label;
		}
	}
	
	private Long projectId;//项目ID
	private Long taskId;//任务ID
	private Long stageId;//阶段ID
	private Long attaId;//附件ID
	
	@Column(length=32)
	private String source;//文件的来源
	@Type(type = "yes_no")
	private boolean isRead=false;//是否已读
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ProjectMaterial_Status status;//状态
	@Type(type = "yes_no")
	private boolean deliveryed=false;//是否为交付物
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;//审核时间
	@Column(length=512)
	private String auditReason;//审核原因
	
	private Long uploadId;//上传者ID{为空标识平台上传}
	private Long receiveId;//接收者ID
	private Date downTime;//下载时间
	
	private Long removeId;//删除者ID

	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne
	@JoinColumn(name = "taskId", insertable=false, updatable=false)
    private ProjectStageTask task;//任务
    
    @ManyToOne
   	@JoinColumn(name = "stageId", insertable=false, updatable=false)
    private ProjectDefineStage stage;//项目阶段
    
    @ManyToOne
   	@JoinColumn(name = "attaId", insertable=false, updatable=false)
    private Attas atta;//附件
    
    @ManyToOne
   	@JoinColumn(name = "uploadId", insertable=false, updatable=false)
    private MemberPassport uploadMember;//上传文件的精英ID
    
    @ManyToOne
   	@JoinColumn(name = "receiveId", insertable=false, updatable=false)
    private MemberPassport receiveMember;//接收文件的精英ID
    
    
    
}

package com.ledao.elite.core.domain.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 会员消息对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MemberMessage extends IdentifiedEntity {

	private static final long serialVersionUID = -1364456590123319952L;
	
	protected static final Long SYS_MESSAGE_SEND=-1L;//系统消息
	
	//消息状态
	public enum MemberMessage_Status{
		unread("未读"),
		read("已读");
		public String label;
		MemberMessage_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	//消息状态
	public enum MemberMessage_Type{
		system("系统消息"),
		project("项目消息"),
		activity("活动消息");
		public String label;
		MemberMessage_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	private Long memberId;//接收的会员ID
	private Long projectId;//消息所属项目
	@Column(length=128)
	private String title;//标题
	@Column(length=2000)
	private String content;//内容
	private int orders;//排序号
	@Type(type = "yes_no")
	private boolean sticked=false;//是否置顶
	
	@Enumerated(EnumType.STRING)
	@Column(length=16)
	private MemberMessage_Status status;//状态
	@Enumerated(EnumType.STRING)
	@Column(length=16)
	private MemberMessage_Type type;//类型
	@Temporal(TemporalType.TIMESTAMP)
	private Date readTime;//阅读时间
	
	private Long sendUser=SYS_MESSAGE_SEND;//发送者

}

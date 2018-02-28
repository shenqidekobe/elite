package com.ledao.elite.core.domain.sys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统消息实体对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysMessage extends IdentifiedEntity {
	
	private static final long serialVersionUID = -4281861264386456091L;
	
	private Long userId;//接收消息的用户ID
	@Column(length=128)
	private String title;//消息标题
	@Column(length=2000)
	private String content;//消息内容
	@Type(type = "yes_no")
	private boolean isStick=false;//是否置顶
	@Type(type = "yes_no")
	private boolean isRead=false;//是否已读
	private Integer orders;//排序号
	private Long sendUser;//发送者用户ID
	@Column(length=16)
	private String status;//状态
	@Temporal(TemporalType.TIMESTAMP)
	private Date readTime;//阅读时间
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "sendUser", insertable=false, updatable=false)
    private SysUser sendSysUser;//发送者用户

}

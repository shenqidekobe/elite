package com.ledao.elite.core.domain.platform;

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
 * 平台回访记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformRevisit extends IdentifiedEntity {

	private static final long serialVersionUID = -4296089435056888271L;

	private Long parentId;//上级ID
	@Column(length=32)
	private String type;//回访类型
	private Long memberId;//会员ID
	@Column(length=64)
	private String title;//标题
	@Column(length=1000)
	private String content;//内容
	@Column(length=1000)
	private String reply;//回复
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyTime;//回复时间
	@Column(length=32)
	private String status;//状态
	private Long createId;//创建者
}

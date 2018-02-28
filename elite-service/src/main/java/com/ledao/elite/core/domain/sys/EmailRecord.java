package com.ledao.elite.core.domain.sys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 邮箱发送记录对象
 * @author Administrator
 *
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class EmailRecord extends IdentifiedEntity{

	private static final long serialVersionUID = 3879683053930880583L;

	@Column(length=16)
	private String emailType;//邮件类型
	@Column(length=64)
	private String title;//标题
	@Lob
	private String content;//正文
	@Column(length=64)
	private String email;//邮箱
	@Column(length=32)
	private String status;//发送状态
	private int  attaNum;//附件数量
	@Column(length=64)
	private String attaIds;//附件ID
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendTime;//发送时间
	
}

package com.ledao.elite.core.domain.sys;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统审核会员记录对象
 * @author Administrator
 *
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysAudit extends IdentifiedEntity {

	private static final long serialVersionUID = -4661541035246462047L;

	private Long userId;//系统用户ID
	private Long memberId;//会员ID
	@Column(length=32)
	private String auditResult;//审核结果
	@Column(length=512)
	private String auditReason;//审核原因
	
}

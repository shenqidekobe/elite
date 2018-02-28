package com.ledao.elite.core.domain.platform;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 平台公告信息对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformNotice extends IdentifiedEntity {

	private static final long serialVersionUID = 3337036592140857408L;
	
	@Column(length=64)
	private String title;//标题
	@Column(length=1000)
	private String content;//内容
	@Column(length=32)
	private String status;//状态
	private Long createId;//创建者
	private Long updateId;//修改者

}

package com.ledao.elite.core.domain.sys;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统常量配置对象
 * 
 * @author kobe.liu
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysConfig extends IdentifiedEntity {

	private static final long serialVersionUID = 7065784448696919545L;
	
	@Column(length=32)
	private String configKey;//key项
	@Column(length=32)
	private String configVal;//value项
	@Column(length=64)
	private String configDesc;//描述
}

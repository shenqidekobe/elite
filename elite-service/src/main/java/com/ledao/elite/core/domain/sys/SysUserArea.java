package com.ledao.elite.core.domain.sys;

import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统部门地区关系对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysUserArea extends IdentifiedEntity{
	
	private static final long serialVersionUID = 5978983819883866188L;
	
	private Long userId;//用户ID
	private Long areaId;//地区ID
	private Long createId;//创建人ID

}

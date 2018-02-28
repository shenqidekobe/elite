package com.ledao.elite.core.domain.platform;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 对平台的评价对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformEvaluate extends IdentifiedEntity{

	private static final long serialVersionUID = -6376372653542949856L;
	
	private Long userId;//平台用户ID
	
	private Long projectId;//项目ID
	
	private int score;//评分
	
	@Column(length=512)
	private String comment;//评语
	
	@Column(length=32)
	private String status;//状态
	
	@Column(length=32)
	private String createType;//评价者类型
	

}

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
 * 系统预警对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysWarning extends IdentifiedEntity{

	private static final long serialVersionUID = -4510529065240636034L;
	
	@Column(length=128)
	private String title;//标题
	
	@Lob
	private String content;//预警内容
	
	@Column(length=128)
	private String href;//链接
	
	@Column(length=32)
	private String type;//预警类型
	
	@Column(length=32)
	private String status;//状态
	
	private int level;//级别
	
	private Long createId;//创建者ID
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间
	
	private Long processId;//处理ID
	

}

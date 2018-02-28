package com.ledao.elite.core.domain.sys;

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
 * 系统人员的待处理任务对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysPending extends IdentifiedEntity{

	private static final long serialVersionUID = 3126526855545076431L;
	
	@Column(length=32)
	private String pendingNum;//待处理编号
	
	private Long userId;//用户ID
	
	@Column(length=32)
	private String type;//类型(待处理任务)
	
	@Column(length=32)
	private String title;//标题
	
	@Column(length=128)
	private String href;//链接
	
	private String orders;//排序号
	
	@Column(length=32)
	private String status;//状态
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间
	

}

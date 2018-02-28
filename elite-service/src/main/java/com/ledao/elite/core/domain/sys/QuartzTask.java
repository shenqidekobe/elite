package com.ledao.elite.core.domain.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 定时任务对象
 * @author kobe
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = true,of={})
@ToString(callSuper = true, of = {})
public class QuartzTask extends IdentifiedEntity{
	
	private static final long serialVersionUID = -3324050924164987499L;
	
	//任务的状态
	public enum QuartzTask_Status{
		wait_run("待运行"),
		runing("运行中"),
		run_end("运行结束"),
		expire("未执行已过期");
		public String label;
		QuartzTask_Status(String label){
			this.label=label;
		}
	}
	//任务的类型
	public enum QuartzTask_Type{
		withdraw("提现任务");
		public String label;
		QuartzTask_Type(String label){
			this.label=label;
		}
	}
	//任务的执行方式
	public enum QuartzTask_Way{
		one("一次性"),
		roll("轮询");
		public String label;
		QuartzTask_Way(String label){
			this.label=label;
		}
	}

	@Enumerated(EnumType.STRING)
	@Column(length=16)
	private QuartzTask_Status status;//任务状态
	
	@Enumerated(EnumType.STRING)
	@Column(length=16)
	private QuartzTask_Type type;//任务类型
	
	@Enumerated(EnumType.STRING)
	@Column(length=16)
	private QuartzTask_Way way;//任务执行方式
	
	private Long dataId;//数据ID
	
	@Column(length=16)
	private String taskTitle;//任务标题{中文标题}
	@Column(length=64)
	private String taskName;//任务名称{任务代码标题唯一}
	@Column(length=32)
	private String taskGroup;//任务分组
	@Column(length=32)
	private String taskExperess;//任务执行时间表达式(轮询任务的时候)
	private Integer intervalMinute;//间隔多少分钟执行(一次性任务的时候)
	@Column(length=128)
	private String taskImplClass;//任务实现类路径
	@Column(length=64)
	private String taskTarget;//任务目标
	@Column(length=128)
	private String taskParams;//任务参数
	
	@Column(length=128)
	private String result;//执行结果
}

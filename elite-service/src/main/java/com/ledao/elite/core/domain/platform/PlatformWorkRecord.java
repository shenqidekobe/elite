package com.ledao.elite.core.domain.platform;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目工作记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatformWorkRecord extends IdentifiedEntity {

	private static final long serialVersionUID = -4030159136525138457L;
	
	//工作记录类型
	public enum WorkRecord_Type{
		partnerCompany("渠道项目方工作记录"),
		partnerCompany_project("渠道项目方推荐的项目工作记录"),
		partnerElite("渠道猎头方工作记录"),
		partnerElite_project("渠道猎头方推荐的人才工作记录"),
		closeMember("关停"),
		projectReceivable("项目应收管理备注"),
		projectPayable("项目应付管理备注"),
		project("项目备注");
		public String label;
		WorkRecord_Type(String label){
			this.label=label;
		}
	}

	private Long userId;//用户ID{添加记录的用户ID}
	@Column(length=32)
	private String type;//工作类型
	private Long foreignId;//类型对应的外键ID
	@Column(length=1000)
	private String content;//工作记录内容
	@Column(length=32)
	private String ext1;//扩展属性1
	@Column(length=32)
	private String ext2;//扩展属性2
	@Column(length=32)
	private String ext3;//扩展属性3
	@Column(length=32)
	private String status;//状态
	@Temporal(TemporalType.TIMESTAMP)
	private Date removeTime;//删除时间
	
	@Transient
	private String realName;//添加记录操作名称
    
}

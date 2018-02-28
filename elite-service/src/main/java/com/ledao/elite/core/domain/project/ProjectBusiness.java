package com.ledao.elite.core.domain.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目商务沟通记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectBusiness extends IdentifiedEntity {

	private static final long serialVersionUID = -1930207113094415323L;

	private Long projectId;//项目ID
	private Long userId;//BM用户ID
	@Column(length=32)
	private String way;//沟通方式
	@Column(length=64)
	private String address;//走访地点
	@Column(length=128)
	private String participant;//参与者
	@Column(length=1000)
	private String content;//沟通记录
	private Long attaId;//附件ID
	@Column(length=32)
	private String status;//状态
	private Long createId;//创建者
	private Long updateId;//修改者
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
}

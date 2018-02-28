package com.ledao.elite.core.domain.project;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.cache.custom.DictCache;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * CTO应标项目的每个阶段填写的费用和时间记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectTenderRecordStage extends IdentifiedEntity {

	private static final long serialVersionUID = -8154073711134374350L;

	private Long projectId;//项目ID
	private Long recordId;//应标记录ID
	@Column(length=32)
	private String stageCode;//阶段Code{系统设置里的阶段code}
	@Column(length=50)
	private String title;//阶段标题
	private BigDecimal amount;//该阶段应标金额
	@Temporal(TemporalType.DATE)
	private Date startTime;//该阶段开始时间
	@Temporal(TemporalType.DATE)
	private Date endTime;//该阶段结束时间
	
	//金额单位：万元
//	@Transient
//	public String getAmountUnit(){
//		BigDecimal rs = amount.divide(BigDecimal.valueOf(10000), 2, BigDecimal.ROUND_HALF_UP); 
//		return rs.doubleValue()+"万";
//	}
	
	//项目开发阶段val
	@Transient
	public String getStageCodeVal(){
		return DictCache.getParamDesc(Dict.Dict_Type.project_stage.name(), stageCode);
	}
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne
    @JoinColumn(name = "recordId",insertable=false,updatable=false)
    private ProjectTenderRecord record;//应标记录
    
    @ManyToOne
	@JoinColumn(name = "stageId", insertable=false, updatable=false)
    private ProjectDefineStage stage;//项目的阶段
}

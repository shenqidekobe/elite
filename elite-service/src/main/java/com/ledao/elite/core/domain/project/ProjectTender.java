package com.ledao.elite.core.domain.project;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目招标书对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectTender extends IdentifiedEntity {

	private static final long serialVersionUID = 1920259314108812507L;
	
	//项目招标书
	public enum ProjectTender_Status{
		tender_in("招标中"),
		tender_stop("招标结束"),
		tender_cancel("招标已取消");
		public String label;
		ProjectTender_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	private Long projectId;//项目ID
	private BigDecimal budgetUpper;//预算上限
	@Column(length=64)
	private String title;//招标标题
	@Lob
	private String content;//招标内容
	@Column(length=512)
	private String attrDesc;//其他说明
	private Long attaId;//附件ID
	private BigDecimal budgetLower;//预算下限
	private BigDecimal qualityAmount;//质保金
	@Temporal(TemporalType.DATE)
	private Date startTime;//开始时间
	@Temporal(TemporalType.DATE)
	private Date endTime;//结束时间
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ProjectTender_Status status;//状态
	
	@Column(length=512)
	private String reason;//原因
	@Temporal(TemporalType.TIMESTAMP)
	private Date closeTime;//关闭时间
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date qualityTime;//质保时间
	@Column(length=32)
	private String stockRate;//股份
	private BigDecimal platformAmount;//平台出让股份
	@Column(length=256)
	private String platformIntro;//平台简介
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tenderoverTime;//招标结束时间
	
	// 交付时间天数
	public Integer getDeliveryDay() {
		return DateTimeUtils.dateSub(endTime, startTime);
	}
	@Transient
	public Long getTenderoverVal(){
		return tenderoverTime==null?0l:(tenderoverTime.getTime()/1000-new Date().getTime()/1000);
	}
	
	/************************hibernate many one config**************************/
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attaId", insertable = false, updatable = false)
	private Attas atta;

    @OneToMany(mappedBy="tender",fetch=FetchType.LAZY)
    private List<ProjectTenderRecord> tenders;    
}

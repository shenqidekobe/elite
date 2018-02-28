package com.ledao.elite.core.domain.project;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Status;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * CTO竞标记录
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectTenderRecord extends IdentifiedEntity {

	private static final long serialVersionUID = -1557334170808986090L;
	
	//竞标状态
	public enum ProjectTenderRecord_Status{
		tender_in("竞标中"),
		tender_win("已中标,待确认"),
		tender_normal("已中标,已确认"),
		tender_abandon("放弃"),
		tender_not("未中标");
		public String label;
		ProjectTenderRecord_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	//竞标来源
	public enum TenderRecord_Source{
		oneself("自己竞标"),
		friend_rd("朋友推荐"),
		platform_rd("平台推荐");
		public String label;
		TenderRecord_Source(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	//服务方式
	public enum Service_Way{
		cost("费用"),
		costAstock("费用+股权");
		public String label;
		Service_Way(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	private Long projectId;//项目ID
	private Long tenderId;//招标ID
	private Long memberId;//竞标CTO
	private BigDecimal amount;//竞标金额
	
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Service_Way serviceWay;//服务方式
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private TenderRecord_Source source;//竞标的来源{自己竞标、朋友推荐}
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private ProjectTenderRecord_Status status;//竞标状态
	@Column(length=256)
	private String advantage;//CTO的优势
	
	private Long defineId;//立项书ID{招标成功需要签订立项书}
	@Type(type = "yes_no")
	private boolean sendDefined;//是否发送了立项书
	private Long attaId;//附件文档ID
	
	
	@Transient
	private String memberName;//cto名字
	@Transient
	private int projectInCount;//cto正在进行的项目数量
	@Transient
	private int projectDoneCount;//cto已经完成的项目数量
	//是否确认了立项书
	public Boolean getAffirmDefined(){
		return defineId!=null;
	}
	
	//整体运行天数
	@Transient
	public Integer getWorkingDay() {
		Date startDate=getWorkingStart();
		Date endDate=getWorkingEnd();
		if (startDate != null&&endDate!=null) {
			return DateTimeUtils.dateSub(endDate,startDate);
		}else{
			return 0;
		}
		
	}
	//整体运行开始时间
	@Transient
	public Date getWorkingStart() {
		Date startTime=null;
		if(this.tenderStages!=null){
			
			for(int i=0;i<tenderStages.size();i++)
			{
				if(i==0){
					startTime=tenderStages.get(i).getStartTime();
				}
				if(tenderStages.get(i).getStartTime().getTime()<startTime.getTime()){
					startTime=tenderStages.get(i).getStartTime();
				}
			}
		}
		return startTime;
	}
	//整体运行结束时间
	@Transient
	public Date getWorkingEnd() {
		Date endTime=null;
		if(this.tenderStages!=null){
			
			for(int i=0;i<tenderStages.size();i++)
			{
				if(i==0){
					endTime=tenderStages.get(i).getEndTime();
				}
				if(tenderStages.get(i).getEndTime().getTime()>endTime.getTime()){
					endTime=tenderStages.get(i).getEndTime();
				}
			}
		}
		return endTime;
	}
	//金额单位：万元
//	@Transient
//	public String getAmountUnit(){
//		BigDecimal rs = amount.divide(BigDecimal.valueOf(10000), 2, BigDecimal.ROUND_HALF_UP); 
//		return rs.doubleValue()+"万";
//	}
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne
	@JoinColumn(name = "defineId", insertable=false, updatable=false)
    private ProjectDefine projectDefine;//项目立项书
    @ManyToOne
	@JoinColumn(name = "tenderId", insertable=false, updatable=false)
    private ProjectTender tender;//项目招标书
    
    @OneToMany(mappedBy="record")
    private List<ProjectTenderRecordStage> tenderStages;//阶段费用
    
    
    @ManyToOne
	@JoinColumn(name = "attaId", insertable=false, updatable=false)
    private Attas atta;//附件文档
    
    @Transient
    private Integer count;//竞标此项目有几人
    
    @Transient
    private BigDecimal qualityAmount;//所竞标冻结质保金额
    
    //当前阶段
	@Transient
	public ProjectDefineStage getTrustStage() {
		List<ProjectDefineStage> stages = this.getProjectDefine().getStages();
		ProjectDefineStage stage=stages.get(0);
		for (ProjectDefineStage pds : stages) {
			if (Pay_Status.success.equals(pds.getPayStatus())) {
				stage=pds;
			}
		}
		return stage;
	}
    
	@Transient
	public BigDecimal getTrustedAmount() {
		BigDecimal count = new BigDecimal(0);
		if(projectDefine==null){
			return new BigDecimal(0);
		}
		List<ProjectDefineStage> stages = projectDefine.getStages();
		if (stages == null) {
			return new BigDecimal(0);
		}
		for (ProjectDefineStage pds : stages) {
			if (Pay_Status.success.equals(pds.getPayStatus())) {
				count = count.add(pds.getAmount());
			}
		}
		return count;
	}
}

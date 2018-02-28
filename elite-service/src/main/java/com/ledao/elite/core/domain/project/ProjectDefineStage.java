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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberInvoice;
import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目立项阶段记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectDefineStage extends IdentifiedEntity {

	private static final long serialVersionUID = -3354762907323465815L;
	
	//项目阶段的状态
	public enum Stage_Status{
		wait_start("未开始"),
		starting("进行中"),
		wait_accept("待验收"),
		quality("质保期"),
		finish("已结束"),
		accept_fail("验收失败");
		public String label;
		Stage_Status(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	private Long projectId;//项目ID
	private Long defineId;//立项书ID
	@Column(length=32)
	private String stageCode;//阶段CODE
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Stage_Status status;//阶段状态
	@Column(length=32)
	private String title;//标题
	
	private Integer orders;//排序号
	
	@Temporal(TemporalType.DATE)
	private Date startTime;//开始时间
	@Temporal(TemporalType.DATE)
	private Date stopTime;//截止时间、交付日期{需求变更可变}
	@Temporal(TemporalType.DATE)
	private Date originalStopTime;//原始截止时间
	private double devellopCycle;//研发天数
	

	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private GlobalDefinedConstant.Pay_Status payStatus;//支付状态
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private GlobalDefinedConstant.Pay_Way payWay;//支付方式
	@Column(updatable=false)
	private BigDecimal amount;//阶段金额{待付金额}
	private BigDecimal payAmount;//已付金额
	private BigDecimal guaranteeAmount;//质保金
	@Type(type = "yes_no")
	private boolean trusted=false;//阶段资金是否已托管
	@Type(type = "yes_no")
	private boolean currented=false;//是否在当前阶段
	
	private int evaluateScore;//CEO评价分数
	@Column(length=512)
	private String evaluateText;//CEO评价内容
	
	@Column(length=256)
	private String acceptReason;//验收原因
	@Temporal(TemporalType.TIMESTAMP)
	private Date acceptTime;//验收时间
	private Integer guaranteeTime;// 质保时间{从验收开始计算}
	
	private double deferDay;//延期天数累计
	private Long ctoId;//阶段对应的CTOID
	@Temporal(TemporalType.TIMESTAMP)
	private Date trustTime;//托管时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date changeCtoTime;//更换CTO时间
	
	
	@Transient
	private BigDecimal firstAmount;//第一阶段费用{金额-已付意向金}
	@Transient
	private Boolean finished=false;//所有阶段结束标识
	@Transient
	private Boolean firstStage=false;//第一阶段
	//金额单位：万元
	@Transient
	public String getAmountUnit(){
		if(amount.compareTo(new BigDecimal(1000000))==1){
			BigDecimal rs = amount.divide(BigDecimal.valueOf(1000000), 2, BigDecimal.ROUND_HALF_UP); 
			return rs.doubleValue()+"万";
		}else{
			return amount.toString();
		}
	}
	
	// 交付时间天数
	public Integer getDeliveryDay() {
		if (stopTime == null) {
			return DateTimeUtils.dateSub(originalStopTime, startTime);
		}
		return DateTimeUtils.dateSub(stopTime, startTime);
	}
	@Transient
	public String getStageCodeVal(){
		return DictCache.getParamDesc(Dict.Dict_Type.project_stage.name(), stageCode);
	}
	
	@Transient
	private String period; //阶段时间规划
	@Transient
	private Boolean invoiced =false; //是否已开发票
	
	@Transient
	private Boolean askInvoice=false;//是否索要发票
	@Transient
	private MemberInvoice invoice;//发票信息
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;//项目
    
    @ManyToOne
 	@JoinColumn(name = "defineId", insertable=false, updatable=false)
    private ProjectDefine projectDefine;//项目书ID
    
	@OneToMany(mappedBy = "stage",fetch=FetchType.LAZY)
	private List<ProjectStageTask> tasks;//立项书阶段
	
	@OneToMany(mappedBy = "stage",fetch=FetchType.LAZY)
	private List<PlatformInOrder> inOrders;//立项书订单

}

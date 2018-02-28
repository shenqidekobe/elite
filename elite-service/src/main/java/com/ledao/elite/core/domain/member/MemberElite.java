package com.ledao.elite.core.domain.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 精英会员
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MemberElite extends IdentifiedEntity{
	
	private static final long serialVersionUID = -8794920793442348460L;
	
	public static final Integer MAX_JOBAGE=20;//代表最大的工作年限(十年以上)
	
	private Long memberId;//会员ID
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private Member_Status status;//状态
	
	@Type(type = "yes_no")
	private boolean taked=true;//是否接活
	@Type(type = "yes_no")
	private boolean stared=false;//是否明星成员
	@Type(type = "yes_no")
	private boolean ctoSigned=false;//参与云英汇CTO签约计划
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyDate;//申请成为CTO的时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyAuditTime;//申请审核时间
	
	private Integer jobAge;//工作年限(2016-08-31更新为数值类型)
	
	@Column(length=32)
	private String allotDuration;//每周可分配时长
	@Column(length=256)
	private String attentionIndustry;//关注行业
	@Type(type = "yes_no")
	private boolean onjobed;//是否在职
	private Long resumeAttaId;//简历附件ID
	@Column(length=4000)
	private String intro;//个人简介说明{自我描述}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;//审核时间
	private Long auditId;//审核ID
	@Column(length=256)
	private String auditReason;//审核原因
	
	@Type(type = "yes_no")
	private boolean ctoed=false;//是否CTO
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditCtoTime;//审核CTO时间
	private Long auditCtoId;//审核CTOID
	@Column(length=256)
	private String auditCtoReason;//审核CTO原因
	
	@Transient
	private Map<String,String> eliteJobMap;//精英职位map
	@Transient
	private Map<String,List<String>> eliteJobListMap;//精英职位map
	@Transient
	private String eliteJobMaps;//职位map的json字符串
	@Transient
	private String inviteCode;//邀请码
	
	public MemberElite(Long memberId) {
		super();
		this.memberId = memberId;
	}
	
	//工作年限
	@Transient
	public String getJobAgeVal(){
		if(jobAge==null){
			return "";
		}
		if(jobAge==MAX_JOBAGE){
			return "10年以上";
		}
		return jobAge+"年";
	}
	//每周可以分配时长
	@Transient
	public String getAllotDurationVal()
	{
		return DictCache.getParamDesc(Dict.Dict_Type.allot_duration.name(), this.allotDuration);
	}
	
	@Transient
	public List<String> getAttentionIndustryList()
	{
		return CommonUtils.separatorStrToList(attentionIndustry, GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
	}
	@Transient
	public List<String> getAttentionIndustryListVal() {
		List<String> keyList=getAttentionIndustryList();
		List<String> valList=new ArrayList<>();
		for(String key:keyList){
			valList.add(DictCache.getParamDesc(Dict.Dict_Type.choice_industry.name(), key));
		}
		return valList;
	}
	
	@Transient
	private MemberEliteJobs firstJobs;
	
	/************************hibernate many one config**************************/
    @OneToMany(mappedBy="elite")
    private List<MemberEliteJobs> eliteJobs;//精英的职位角色
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resumeAttaId", insertable = false, updatable = false)
	private Attas resumeAtta;// 简历
	
	/**
	 * 规定时间内不能再次申请，已是CTO不能申请
	 * */
	@Transient
	public Boolean getApplyFlag(){
		Boolean applyFlag = true;
		if(applyDate!=null && DateTimeUtils.dateSub(new Date(),applyDate)<=30){
			applyFlag = false;
		}
		return applyFlag;
	}
	
	/**
	 * 规定时间内不能再次修改角色，且有任务和项目在做时不能修改
	 * */
	@Transient
	public Boolean getUpdateRoleFlag(){
		Boolean applyFlag = true;
		if(this.getEliteJobs().size()>0 && this.getEliteJobs().get(0).getUpdateRoleDate()!=null && DateTimeUtils.dateSub(new Date(),this.getEliteJobs().get(0).getUpdateRoleDate())<=30){
			applyFlag = false;
		}
		return applyFlag;
	}
}

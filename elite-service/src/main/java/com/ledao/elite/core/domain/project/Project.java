package com.ledao.elite.core.domain.project;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
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
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.project.ProjectDefineStage.Stage_Status;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Status;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.core.utils.ParaseHtml;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目核心对象
 * 
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Project extends IdentifiedEntity {

	private static final long serialVersionUID = -3381164859823974432L;

	// 项目状态定义
	public enum Project_Status {
		wait_audit("待审核"), audit_in("审核中"), pass_wait("审核通过待立项"), pass_already("已立项"), stage_course("阶段开发中"), quality(
				"质保期"), finish("已结束"), unpass("审核未通过"), invalid("无效的项目"), audited("已审核"),stop("已终止");
		public String label;
		Project_Status(String label) {
			this.label = label;
		}
		public String getLabel() {
			return label;
		}
	}

	@Column(length = 32)
	private String projectNum;// 项目编号
	@Enumerated(EnumType.STRING)
	@Column(length = 16)
	private Project_Status status;// 状态
	@Column(length = 64)
	private String name;// 项目名
	@Column(length = 32)
	private String subName;// 项目副名
	@Column(length = 16)
	private String backgroundColor;//项目背景色
	private Long logo;// 项目logo
	private Long areaId;// 地区ID
	@Column(length = 32)
	private String areaName;// 地区名

	@Column(length = 128)
	private String productIndustry;// 项目行业
	@Column(length = 128)
	private String productFunction;// 项目功能
	@Column(length = 128)
	private String projectSolution;// 项目解决方案类型

	@Column(length = 32)
	private String contactName;// 联系人
	@Column(length = 32)
	private String contactPhone;// 联系电话
	@Column(length = 32)
	private String contactEmail;// 联系邮箱
	@Column(length = 32)
	private String recommendUser;// 推荐人{推荐人电话号码或者邮箱或者邀请码}
	@Column(length = 512)
	private String referProject;// 参考项目
	@Lob
	private String intro;// 项目简介
	private Long projectAtta;// 项目附件

	@Column(length = 32)
	private String projectBudget;// 项目预算
	@Column(length = 32)
	private String projectType;// 项目类型
	private BigDecimal budgetMin;// 期望预算下限
	private BigDecimal budgetMax;// 期望预算上限

	@Column(length = 32)
	@Enumerated(EnumType.STRING)
	private Pay_Status intentionStatus;//意向金提交状态
	private BigDecimal intentionAmount=new BigDecimal(0);//意向金{默认0}
	private BigDecimal backIntention=new BigDecimal(0);//退回的意向金
	@Temporal(TemporalType.TIMESTAMP)
	private Date intentionTime;//意向金提交时间
	
	private BigDecimal stockRate=new BigDecimal(0);//股权百分比{默认0}
	@Type(type = "yes_no")
	private boolean trustStocked=false;//是否已经托管股权
	@Temporal(TemporalType.TIMESTAMP)
	private Date stockTime;//股份托管时间
	
	private BigDecimal totalAmount;//项目总费用
	
	@Temporal(TemporalType.DATE)
	private Date startTime;// 起始时间
	@Temporal(TemporalType.DATE)
	private Date expectTime;// 期望交付时间

	@Temporal(TemporalType.DATE)
	private Date deliveryTime;// 确定交付时间{可变的，根据需求变更和延期的}
	@Temporal(TemporalType.DATE)
	private Date originalDeliveryTime;// 原始的确定交付时间
	private double deferDay;// 累计延期天数

	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;// 审核时间
	private Long auditId;// 审核者ID
	@Column(length = 512)
	private String auditReason;// 审核原因
	private Long planBook;// 商业计划书(附件)
	@Type(type = "yes_no")
	private boolean planed;// 是否参与计划

	private Long pmId;// 项目处理人PM
	private Long bmId;// 项目商务对接BM
	private Long ctoId;// 项目负责人CTO
	private Long companyId;// 项目发布人的会员ID
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopTime;// 终止时间
	private Long stopId;// 终止人ID

	@Column(length = 512)
	private String stopReason;// 终止项目的理由

	@Temporal(TemporalType.TIMESTAMP)
	private Date acceptTime;//验收时间{最后一个阶段的验收时间}
	private Integer guaranteeTime;// 质保时间{从验收开始计算}
	
	private Long createId;// 创建者
	private Long updateId;// 修改者
	@Temporal(TemporalType.TIMESTAMP)
	private Date bmTime;// BM审核时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date pmTime;// PM审核时间
	
	private Long defineId;//立项书ID
	@Type(type = "yes_no")
	private boolean sendDefined=false;//是否发送了立项书
	
	@Transient
	private ProjectDefineStage currentStage;// 项目当前阶段
	@Transient
	private String attaIds;// 项目附件ID字符串
	@Transient
	private Integer materialUnCount;//未读文件数量
	@Transient
	private BigDecimal partnerIncome=new BigDecimal(0);//渠道方收益
	
	//是否确认了立项书
	public Boolean getAffirmDefined(){
		return defineId!=null;
	}

	//是否有股权
	public Boolean getIsStock() {
		return stockRate.compareTo(BigDecimal.ZERO)==1;
	}
	//股权百分比,两位小数
	public String getStockPercent(){
	    NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        return percent.format(stockRate.doubleValue());
	}

	// 项目名称首字母
	public String getFirstName() {
		return name == null ? null : String.valueOf(name.charAt(0));
	}

	// 交付时间天数
	public Integer getDeliveryDay() {
		if (deliveryTime == null) {
			return DateTimeUtils.dateSub(expectTime, startTime);
		}
		return DateTimeUtils.dateSub(deliveryTime, startTime);
	}
	//项目预算
	public String getProjectBudgetVal(){
		return DictCache.getParamDesc(Dict.Dict_Type.project_budget.name(), projectBudget);
	}

	@Transient
	private String bmName; // bm姓名
	@Transient
	private String pmName; // pm姓名
	@Transient
	private String CTOName; // CTO姓名
	@Transient
	private Boolean tendered; // 是否已发立项书
	@Transient
	private String solutionVals;// 解决方案类型字符串
	
	@Transient
	private String bmPhone;//bm 电话
	@Transient
	private String ctoPhone;//cto 电话
	
	@Transient
	public String getSolutionVals(){
		List<String> list=CommonUtils.separatorStrToList(projectSolution, GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		String result="";
		for(String s:list){
			if(StringUtils.isEmpty(s))continue;
			String value=DictCache.getParamDesc(Dict_Type.project_type.name(), s);
			if(result==""){
			    result=value;
			}else{
			    result= result+"+"+value;
			}
		}
		return result;
	}
	
	// 项目行业
	@Transient
	public List<String> getIndustryList() {
		return CommonUtils.separatorStrToList(productIndustry, GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
	}
	@Transient
	public List<String> getIndustryValList() {
		List<String> keyList=getIndustryList();
		List<String> valList=new ArrayList<>();
		for(String key:keyList){
			valList.add(DictCache.getParamDesc(Dict.Dict_Type.choice_industry.name(), key));
		}
		return valList;
	}

	// 项目功能
	@Transient
	public List<String> getFunctionList() {
		return CommonUtils.separatorStrToList(productFunction, GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
	}
	@Transient
	public List<String> getFunctionValList() {
		List<String> keyList=getFunctionList();
		List<String> valList=new ArrayList<>();
		for(String key:keyList){
			valList.add(DictCache.getParamDesc(Dict.Dict_Type.project_func.name(), key));
		}
		return valList;
	}

	// 项目解决方案类型
	@Transient
	public List<String> getSolutionList() {
		return CommonUtils.separatorStrToList(projectSolution, GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
	}
	
	public List<String>getSolutionListVal(){
		List<String> keyList=getSolutionList();
		List<String> valList=new ArrayList<>();
		for(String key:keyList){
			valList.add(DictCache.getParamDesc(Dict.Dict_Type.project_type.name(), key));
		}
		return valList;
	}
     
	//项目类型
	@Transient
	public List<String>getProjectTypeList(){
		return CommonUtils.separatorStrToList(projectType, GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR); 
	}
	public List<String>getProjectTypeValList(){
		List<String> keyList=getProjectTypeList();
		List<String> valList=new ArrayList<>();
		for(String key:keyList){
			valList.add(DictCache.getParamDesc(Dict.Dict_Type.project_type.name(), key));
		}
		return valList;
	}
     
	// 当前属于第几阶段
	@Transient
	public ProjectDefineStage getForStage() {
		if(this.getProjectDefine()!=null){
			List<ProjectDefineStage> stages = this.getProjectDefine().getStages();
			ProjectDefineStage stage=stages.get(0);
			for (ProjectDefineStage pds : stages) {
				if (Pay_Status.success.equals(pds.getPayStatus())) {
					stage=pds;
				}
			}
			return stage;
		}else{
			return null;
		}
	}
	
	// 当前验收的阶段
	@Transient
	public ProjectDefineStage getAcceptStage() {
		List<ProjectDefineStage> stages = this.getProjectDefine().getStages();
		ProjectDefineStage stage=stages.get(0);
		for (ProjectDefineStage pds : stages) {
			if (Pay_Status.success.equals(pds.getPayStatus())
					&&(Stage_Status.wait_accept.equals(pds.getStatus())||Stage_Status.quality.equals(pds.getStatus()))) {
				stage=pds;
			}
		}
		return stage;
	}
	
	// 当前阶段的下一个阶段
	@Transient
	public ProjectDefineStage getTrustNextStage() {
		List<ProjectDefineStage> stages = this.getProjectDefine().getStages();
		for (ProjectDefineStage pds : stages) {
			//按顺序，未支付成功的阶段直接返回
			if (!Pay_Status.success.equals(pds.getPayStatus())) {
				return pds;
			}
		}
		return null;
	}

	// 当前需托管第几阶段{索引}
	@Transient
	public int getTrustStageCount() {
		int i = 0;
		List<ProjectDefineStage> stages = this.getProjectDefine().getStages();
		boolean flag = false;
		for (ProjectDefineStage pds : stages) {
			if (Pay_Status.success.equals(pds.getPayStatus())) {
				flag = true;
				i++;
			}
		}
		i = !flag ? 0 : i;
		return i;
	}

	// 当前需托管的阶段{第一阶段需要减掉意向金的金额}
	@Transient
	public ProjectDefineStage getTrustStage() {
		if(projectDefine==null){
			return null;
		}
		List<ProjectDefineStage> stages = projectDefine.getStages();
		if (stages == null) {
			return null;
		}
		int i=0;
		ProjectDefineStage stage = stages.get(0);
		for (ProjectDefineStage pds : stages) {
			//支付成功，且验收成功，则进入下一阶段的支付
			if (Pay_Status.success.equals(pds.getPayStatus())
					&&(Stage_Status.quality.equals(pds.getStatus())||Stage_Status.finish.equals(pds.getStatus()))) {
				i++;
				continue;
			}
			stage = pds;
			break;
		}
		if(i==0){
			ProjectDefineStage firstStage=stages.get(0);
			if(Pay_Status.success.equals(intentionStatus)){
				BigDecimal amount=firstStage.getAmount();
				amount=amount.subtract(intentionAmount).add(backIntention);
				firstStage.setFirstAmount(amount);
			}
			return firstStage;
		}else if(i==stages.size()){
			//全部结束
			ProjectDefineStage finishStage=new ProjectDefineStage();
			finishStage.setTitle("所有阶段结束");
			finishStage.setFinished(true);
			return finishStage;
		}
		return stage;
	}
	
	// 当前需托管的上一个阶段
	@Transient
	public ProjectDefineStage getTrustPrevStage() {
		List<ProjectDefineStage> stages = projectDefine.getStages();
		if (stages == null) {
			return null;
		}
		int i=0;
		for (ProjectDefineStage pds : stages) {
			i++;
			if (Pay_Status.success.equals(pds.getPayStatus())) {
				break;
			}
		}
		ProjectDefineStage statge = stages.get(i-1);
		return statge;
	}
	
	// 最后一个阶段
	@Transient
	public ProjectDefineStage getFinallyStage() {
		if(projectDefine==null){
			return null;
		}
		List<ProjectDefineStage> stages = projectDefine.getStages();
		if (stages == null) {
			return null;
		}
		int index=stages.size()-1;
		ProjectDefineStage stage = stages.get(index);
		return stage;
	}
	// 第一个阶段
	/**
	 * @return
	 */
	@Transient
	public ProjectDefineStage getFirstStage() {
		if(projectDefine==null){
			return null;
		}
		List<ProjectDefineStage> stages = projectDefine.getStages();
		if (stages == null) {
			return null;
		}
		int index=0;
		ProjectDefineStage stage = stages.get(index);
		if(Pay_Status.success.equals(intentionStatus)){
			BigDecimal amount=stage.getAmount();
			amount=amount.subtract(intentionAmount).add(backIntention);
			stage.setFirstAmount(amount);
			stage.setFirstStage(true);
		}
		return stage;
	}

	// 已验收费用总和
	@Transient
	public BigDecimal getAllPayAmount() {
		BigDecimal count = new BigDecimal(0);
		if(projectDefine==null){
			return new BigDecimal(0);
		}
		List<ProjectDefineStage> stages = projectDefine.getStages();
		if (stages == null) {
			return null;
		}
		for (ProjectDefineStage pds : stages) {
			if (Stage_Status.finish.equals(pds.getStatus())) {
				if(pds.getPayAmount()!=null){
				    count = count.add(pds.getAmount());
				}
			}
		}
		return count;
	}
	//已经付给渠道方，精英，cto非费用
	@Transient
	public BigDecimal getAllPayAbleAmount() {
		BigDecimal count = new BigDecimal(0);
		if(projectDefine==null){
			return new BigDecimal(0);
		}
		List<ProjectDefineStage> stages = projectDefine.getStages();
		if (stages == null) {
			return null;
		}
		for (ProjectDefineStage pds : stages) {
			if (Stage_Status.finish.equals(pds.getStatus())||Stage_Status.quality.equals(pds.getStatus())) {
				if(pds.getAmount()!=null){
				    count = count.add(pds.getAmount());
				}
			}
		}
		return count;
	}

	// 已验托管费用总和
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
		/*if (Pay_Status.success.equals(this.getIntentionStatus())) {
			count = count.add(this.getIntentionAmount());
		}*/
		for (ProjectDefineStage pds : stages) {
			if (Pay_Status.success.equals(pds.getPayStatus())) {
				count = count.add(pds.getAmount());
			}
		}
		return count;
	}
	// 已验付款用总和
	@Transient
	public BigDecimal getPayedAmount() {
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
				count = count.add(pds.getPayAmount());
			}
		}
		//加上意向金
		if(Pay_Status.success.equals(intentionStatus)){
			count=count.subtract(backIntention).add(intentionAmount);
		}
		
		return count;
	}
	// 第一阶段付款情况
	@Transient
	public boolean getFirstStagePayStatus() {
		if(projectDefine==null){
			return false;
		}
		List<ProjectDefineStage> stages = projectDefine.getStages();
		if (stages == null) {
			return false;
		}
		if(stages.size()<1){
			return false;
		}
		ProjectDefineStage firstStage=stages.get(0);
		if(firstStage.getPayStatus()!=null&&firstStage.getPayStatus().equals(GlobalDefinedConstant.Pay_Status.success)){
			return true;
		}
		else{	
		    return false;	
		}
	}
	@Transient
    public String getIntroStr(){
    	return ParaseHtml.ParaseHtmlContent(intro);
    }
	
	// 我的收益
	@Transient
	public BigDecimal myAmount;

	/************************hibernate many one config**************************/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "defineId", insertable = false, updatable = false)
	private ProjectDefine projectDefine;// 项目的立项书

	@OrderBy("createTime desc")
	@Where(clause = "type='project_atta' and status='normal' and id in(select w.id from t_project_atta w group by w.atta_id)")
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private List<ProjectAtta> attas;// 项目附件

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planBook", insertable = false, updatable = false)
	private Attas planBookAtta;// 商业计划书
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyId", insertable = false, updatable = false)
	private MemberPassport memberPassport;// 项目发布人的具体信息

	@OrderBy("orders desc")
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private List<ProjectSettingStage> projectSettingStages;// 项目初期设置阶段
}

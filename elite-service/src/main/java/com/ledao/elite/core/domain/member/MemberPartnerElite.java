package com.ledao.elite.core.domain.member;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.cache.custom.DictCache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 会员合作伙伴-猎头方
 * 
 * @author kobe.liu
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MemberPartnerElite extends IdentifiedEntity {

	private static final long serialVersionUID = -3795968724190797463L;
	public static final Integer MAX_JOBAGE = 20;// 代表最大的工作年限(十年以上)
	public enum MemberPartnerElite_Type {
		person("个人猎头"),
		company("猎头公司"),
		hr("HR"),
		training("培训机构"),
		technicalTalent("技术达人"),
		other("其他");
		public String label;
		MemberPartnerElite_Type(String label) {
			this.label = label;
		}
		public String getLabel() {
			return label;
		}
	}
   
	// 管理精英 查询类别
    public enum MemberPartnerElite_Search_Type {
		all("全部"), 
		last_login_over_7days("七天未登录"),
		no_task_ever("注册后从未备案"),
		no_task_over_14days("近14日未备案操作"),
		no_register("未注册");
		public String label;
		MemberPartnerElite_Search_Type(String label) {
			this.label = label;
		}
		public String getLabel() {
			return label;
		}
	}
	private Long memberId;// 会员ID
	private Long chargeId;//负责人ID
	private Long parentId;//上级ID{他的推荐人}
	private String parentPhone;//上级电话{他的推荐人电话}、冗余字段
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private Member_Status status;// 状态
	@Column(length = 32)
	private String channelNum;// 渠道编号
	@Column(length = 32)
	@Enumerated(EnumType.STRING)
	private MemberPartnerElite_Type type;// 类别
	private Integer putCount = 0;// 备案人才数量
	private Integer enterCount = 0;// 入驻人才数量,累计推荐人才数

	private Integer jobAge;// 工作年限(2016-11-1更新为数值类型)
	@Column(length = 32)
	private String attentionIndustry;// 聚焦行业

	@Column(length = 32)
	private String goodatJob;// 擅长职位
	@Column(length = 32)
	private String identity;// 身份
	@Column(length = 256)
	private String companyName;// 公司
	private Long resumeAattaId;// 简历附件ID
	@Column(length = 256)
	private String intro;// 简介说明

	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;// 审核时间
	private Long auditId;// 审核ID
	@Column(length = 256)
	private String auditReason;// 审核原因
	
	private Integer taskCount;//累计推荐的人才任务数
	private BigDecimal totalAmount;//累计任务金额
	private Integer calculateEliteCount;//当前计算人才数量{周期时间后清零}
	private Integer calculateTaskCount;//当前计算任务量{周期时间后清零}
	private BigDecimal calculateTotalAmount;//当前计算的任务金额
	private Date startOrderTime;//开始签单时间
	private Date lastOrderTime;//最后签单时间
	private Date clearCalculateTime;//周期清空时间
	
	
	@Transient
	private String parentName;//推荐人姓名
	@Transient
	private String chargeName;//负责人姓名
	@Transient
	private String name;// 会员name
	@Transient
	private String phone;// 会员联系方式
	@Transient
	private BigDecimal income;// 会员收益
	@Transient
	private BigDecimal partnerIncome;// 收益
	@Transient
	private Integer putParnterCount;//备案渠道数
	@Transient
	private Integer enterParnterCount;//入驻渠道数
	@Transient
	private MemberBasic memberBasic;// 会员基本内容
	
	public MemberPartnerElite(Long memberId) {
		super();
		this.memberId = memberId;
	}

	// 聚焦行业val
	public String getAttentionIndustryVal() {
		return DictCache.getParamDesc(Dict.Dict_Type.choice_industry.name(), attentionIndustry);
	}
	// 工作年限val
	public String getJobAgeVal() {
		if(jobAge==null){
			return "";
		}
		if(jobAge==MAX_JOBAGE){
			return "10年以上";
		}
		return jobAge+"年";
	}
	//擅长职位
	public String getGoodatJobVal() {
		return DictCache.getParamDesc(Dict.Dict_Type.good_job.name(), goodatJob);
	}
	/************************hibernate many one config**************************/

	@ManyToOne
	@JoinColumn(name = "resumeAattaId", insertable = false, updatable = false)
	private Attas atta;
	
	@ManyToOne
	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员信息
}

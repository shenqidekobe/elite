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
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Cooperate_Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 会员合作伙伴-项目方
 * 
 * @author kobe.liu
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MemberPartnerCompany extends IdentifiedEntity {

	private static final long serialVersionUID = -3795968724190797463L;
	
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
	private Cooperate_Type type;// 类别
	private String companyName;// 公司
	@Column(length = 256)
	private String intro;// 简介说明
	private Integer putCount = 0;// 备案项目数量
	private Integer enterCount = 0;// 入驻项目数量
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;// 审核时间
	private Long auditId;// 审核ID
	@Column(length = 256)
	private String auditReason;// 审核原因
	
	
	private Integer orderCount;//累计签单量
	private BigDecimal totalAmount;//累计签单金额
	private Integer calculateOrderCount;//当前计算签单量{周期时间后清零}
	private BigDecimal calculateTotalAmount;//当前计算的签单金额
	private Date startOrderTime;//开始签单时间
	private Date lastOrderTime;//最后签单时间
	private Date clearCalculateTime;//周期清空时间
	

	@Transient
	private String name;// 会员name
	@Transient
	private String parentName;//推荐人姓名
	@Transient
	private String chargeName;//负责人姓名
	@Transient
	private String phone;// 会员联系方式
	@Transient
	private BigDecimal partnerIncome;// 收益
	@Transient
	private Integer putParnterCount;//备案渠道数
	@Transient
	private Integer enterParnterCount;//入驻渠道数
	@Transient
	private MemberBasic memberBasic;// 会员基本内容
	
	@Transient
	private BigDecimal projectTotalAmount;// 推荐项目总额
	@Transient
	private BigDecimal projectTrustedAmount;//推荐项目托管总额
	
	public MemberPartnerCompany(Long memberId) {
		super();
		this.memberId = memberId;
	}
	
	/************************hibernate many one config**************************/
	@ManyToOne
	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员信息
	
	@ManyToOne
	@JoinColumn(name = "parentId", insertable=false, updatable=false)
    private MemberPartnerCompany parent;//上级信息
}

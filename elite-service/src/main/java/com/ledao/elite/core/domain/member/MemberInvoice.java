package com.ledao.elite.core.domain.member;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Attas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员索要的发票记录对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberInvoice extends IdentifiedEntity {

	private static final long serialVersionUID = 8968989808692425688L;
	
	private Long memberId;//会员ID
	private Long projectId;//项目ID
	private Long stageId;//阶段ID
	private Long taskId;//任务ID
	private BigDecimal amount;//发票金额
	private BigDecimal taxAmount;//税额
	@Column(length=32)
	private String invoviceNum;//发票编号
	@Column(length=64)
	private String invoiceRise;//发票抬头
	@Column(length=32)
	private String type;//发票类型
	@Column(length=32)
	private String status;//发票状态
	private Long invoiceAttaId;//发票附件ID
	@Column(length=32)
	private String recipients;//收件人
	@Column(length=32)
	private String address;//收取地址
	@Column(length=10)
	private String zipCode;//邮编
	@Column(length=11)
	private String phone;//联系电话
	@Column(length=32)
	private String expressName;//快递公司名
	@Column(length=32)
	private String expressNum;//快递单号
	private Long expressAttaId;//快递附件ID
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyTime;//申请时间
	private Long processId;//处理人
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间
	@Type(type = "yes_no")
	private boolean taked;//是否收到
	/************************hibernate many one config**************************/
    @ManyToOne
 	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员帐号

    @ManyToOne(fetch=FetchType.LAZY)
 	@JoinColumn(name = "invoiceAttaId", insertable=false, updatable=false)
    private Attas invoiceAtta; //发票附件
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "expressAttaId", insertable=false, updatable=false)
    private Attas expressAtta; //快递附件
}

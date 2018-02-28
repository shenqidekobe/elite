package com.ledao.elite.core.domain.partner;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Way;
import com.ledao.elite.core.domain.project.Project;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 合作伙伴推荐的项目记录
 * 
 * @author kobe.liu
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class PartnerProjectRecord extends IdentifiedEntity {

	private static final long serialVersionUID = 4672854990600411981L;

	private Long partnerId;// 渠道方ID
	private Long partnerProjectId;// 推荐的项目ID
	private Long projectId;// 备案成功的项目ID
	private BigDecimal income = new BigDecimal(0);// 对此项目的收益
	
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private PartnerElite_Way enterWay;//入驻方式

	/************************
	 * hibernate many one config
	 **************************/
	@ManyToOne
	@JoinColumn(name = "partnerId", insertable = false, updatable = false)
	private MemberPartnerCompany partner;// 合作伙伴

	@ManyToOne
	@JoinColumn(name = "partnerProjectId", insertable = false, updatable = false)
	private PartnerProject partnerProject;// 合作伙伴推荐的项目

	@ManyToOne
	@JoinColumn(name = "projectId", insertable = false, updatable = false)
	private Project project;// 项目

}

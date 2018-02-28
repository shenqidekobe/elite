package com.ledao.elite.core.domain.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 会员登录日志对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginLogs extends IdentifiedEntity {

	private static final long serialVersionUID = 1438811374754922945L;
	
	private Long memberId;//会员ID
	@Column(length=256)
	private String operType;//登录OR登出
	@Column(length=32)
	private String loginIp;//登录IP
	private Date loginTime;//登录时间
	@Column(length=2000)
	private String reqParam;//请求参数
	@Column(length=32)
	private String result;//登录结果
	
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员

}

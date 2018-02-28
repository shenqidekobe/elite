package com.ledao.elite.core.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员操作日志对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberLogs extends IdentifiedEntity {

	private static final long serialVersionUID = 1430684325029709361L;
	
	private Long  memberId;//会员ID
	@Column(length=32)
	private String  operType;//操作类型
	@Column(length=512)
	private String  content;//操作内容
	@Column(length=128)
	private String  classImpl;//请求类
	@Column(length=32)
	private String  reqMethod;//请求方法
	@Lob
	private String  reqParam;//请求参数
	@Column(length=2000)
	private String  rspParam;//返回参数
	@Column(length=32)
	private String  result;//操作结果
	@Column(length=32)
	private String reqIp;//请求IP
	
	
	/************************hibernate many one config**************************/
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport memberPassport;//会员

	
}

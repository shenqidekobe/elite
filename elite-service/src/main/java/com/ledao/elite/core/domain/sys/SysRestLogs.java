package com.ledao.elite.core.domain.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.member.MemberPassport;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 系统rest接口日志对象
 * @author Administrator
 *
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SysRestLogs extends IdentifiedEntity {

	private static final long serialVersionUID = -4462141414559290852L;

	@Column(length=128)
	private String classImpl;//请求类
	@Column(length=32)
	private String reqMethod;//请求方法
	@Lob
	private String reqParam;//请求参数
	@Lob
	private String rsqParam;//返回参数
	@Column(length=32)
	private String operType;//操作类型
	@Column(length=64)
	private String reqIp;//请求IP
	@Column(length=256)
	private String content;//操作内容
	@Column(length=32)
	private String result;//请求结果
	private Long memberId;//操作会员ID
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "memberId", insertable=false, updatable=false)
    private MemberPassport member;//会员
	
}

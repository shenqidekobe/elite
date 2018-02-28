package com.ledao.elite.core.domain.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 系统日志对象
 * @author Administrator
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class SysLogs extends IdentifiedEntity {
	
	private static final long serialVersionUID = 1049048549947340068L;
	
	public enum SysLogs_OperType{
		create,update,remove,login,logout
	}

	@Column(length=32)
	private String operType;//操作类型
	@Column(length=256)
	private String content;//操作内容
	@Column(length=128)
	private String classImpl;//请求类
	@Column(length=64)
	private String reqMethod;//请求方法
	@Lob
	private String reqParam;//请求参数
	@Column(length=32)
	private String result;//操作结果
	@Column(length=32)
	private String reqIp;//请求IP
	private Long userId;//操作用户ID
	
	
	/************************hibernate many one config**************************/
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "userId", insertable=false, updatable=false)
    private SysUser sysUser;//用户
	
}

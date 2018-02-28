package com.ledao.elite.core.domain.sys;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统用户实体
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="sysUser")  
public class SysUser extends IdentifiedEntity{

	private static final long serialVersionUID = 7509121867494472534L;
	
	private Long deptId;//部门ID
	@Column(length=32)
	private String loginName;//登录名
	@Column(length=32)
	private String password;//密码
	@Column(length=32)
	private String passSalt;//密码加盐串
	@Column(length=32)
	private String userName;//用户名称
	@Column(length=11)
	private String phone;//电话
	@Column(length=32)
	private String email;//邮箱
	@Column(length=16)
	private String sex;//性别
	private Long userPhoto;//头像
	@Column(length=16)
	private String status;//状态
	private Long superiorId;//上级ID
	@Column(length=32)
	private String lastLoginIp;//最后登录IP
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;//最后登录时间
	private Long createId;//创建者
	private Long updateId;//修改者
	
	@Transient
	private Long roleId;//提交用戶選擇的角色ID
	
	
	/************************hibernate many one config**************************/
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "deptId", insertable=false, updatable=false)
    private SysDept sysDept;//用户所属部门
    
	@OneToMany(mappedBy = "sysUser",fetch = FetchType.LAZY)
	private List<SysUserRole> roles;//用户的角色列表
	
	@OneToMany(mappedBy = "sysUser",fetch = FetchType.LAZY)
	private List<SysLogs> sysLogs;//用户系统日志列表
	
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "userPhoto", insertable=false, updatable=false)
    private Attas photo;//头像
	
}

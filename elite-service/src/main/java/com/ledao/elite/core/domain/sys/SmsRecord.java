package com.ledao.elite.core.domain.sys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 短信发送对象
 * 
 * @author Administrator
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class SmsRecord extends IdentifiedEntity{

	private static final long serialVersionUID = -7801292002366764389L;
	
	public enum Sms_Type{
		register("注册短信"),
		forget("找回密码"),
		update_phone("更换手机号"),
		register_success("注册成功提示"),
		project_pass("项目审核通过提示"),
		valid_bank("验证银行卡短信");
		public String label;
		Sms_Type(String label){
			this.label=label;
		}
	}
	public enum Sms_Status{
		wait,sending,success,fail;
	}
	
	@Column(length=32)
	private String smsType;//短信类型
	@Column(length=64)
	private String title;//标题
	@Column(length=256)
	private String content;//内容
	@Column(length=512)
	private String phones;//手机号码
	@Column(length=32)
	private String status;//状态
	@Column(length=64)
	private String ip;//发送者IP
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendTime;//发送时间
	@Type(type = "yes_no")
	private boolean isUse=false;//是否已使用

}

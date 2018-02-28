package com.ledao.elite.core.domain.member;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.System_Status;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员银行卡绑定记录对象
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberBank extends IdentifiedEntity {
	
	public static final Integer DEFAULT_MAX_COUNT=5;//每个会员最多添加五张银行卡

	private static final long serialVersionUID = -1228514516649824225L;
	
	//账户类型
	public enum MemberBank_Type{
		alipay("支付宝"),
		bank("银行卡");
		public String label;
		MemberBank_Type(String label){
			this.label=label;
		}
		public String getLabel(){
			return this.label;
		}
	}
	
	private Long memberId;//会员ID
    @Enumerated(EnumType.STRING)
	@Column(length=32)
	private MemberBank_Type type;//账户类型
    @Enumerated(EnumType.STRING)
   	@Column(length=32)
	private System_Status status;//状态
	@Column(length=32)
	private String bankName;//银行名称
	@Column(length=16)
	private String bankCode;//银行编码
	@Column(length=32)
	private String bankCard;//银行卡号
	@Column(length=16)
	private String holder;//持有人姓名
	@Column(length=16)
	private String phone;//银行绑定手机号
	
	private Integer timeLimit=0;//限时多久到账｛单位：小时，0为立即到账｝
	
	private Integer orders;//序号
	@Type(type = "yes_no")
	private boolean verifyed=false;//验证是否通过
	@Temporal(TemporalType.TIMESTAMP)
	private Date verifyTime;//验证时间
	
	@Transient
	private boolean copyed=false;//是否复制帐号
	@Transient
	private boolean asynced=true;//是否同步到其他帐号
	
	//到账时间结算
	public Date getTotime(){
		if(timeLimit==0)
			return null;
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR, timeLimit);
		return calendar.getTime();
	}
	
	
	/**
	 * 格式化姓名
	 * */
	public String getFormatHolder(){
		if(StringUtils.isEmpty(holder)){
			return "***";
		}
		String corbet=holder;
		try {
			int len=holder.length();
			String xin="";
			for (int i = 1; i <len; i++) {
				xin+="*";
			}
			corbet=corbet.substring(0,1)+xin;
		} catch (Exception e) {
		}
		return corbet;
	}
	
	/**
	 * 格式化银行卡号
	 * */
	public String getFormatBankCard(){
		if(StringUtils.isEmpty(bankCard)){
			return "****";
		}
		String corbet=bankCard;
		try {
			int len=bankCard.length();
			if(len>10){
				corbet=corbet.substring(0,6)+"****"+corbet.substring(corbet.length()-4);
			}
		} catch (Exception e) {
		}
		return corbet;
	}
	
	/**
	 * 银行卡尾号
	 * */
	public String getTailBankCard(){
		if(StringUtils.isEmpty(bankCard)){
			return "****";
		}
		String corbet=bankCard;
		try {
			int len=bankCard.length();
			if(len>4){
				corbet=corbet.substring(len-4,len);
			}
		} catch (Exception e) {
		}
		return corbet;
	}
	
	/**
	 * 银行卡logo图片名称
	 * */
	public String getBankCardLogo(){
		String[] logos={"0100","0102","0103","0104","0105","0301","0302","0303","0304","0305","0306","0309","0310","0316","0401","0403","0408","0420","1405"};
		if(!ArrayUtils.contains(logos, bankCard)){
			return bankCode;
		}
		return "other";
	}


}

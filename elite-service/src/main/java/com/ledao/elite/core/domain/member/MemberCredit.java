package com.ledao.elite.core.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Attas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员征信信息对象
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class MemberCredit extends IdentifiedEntity {

	private static final long serialVersionUID = -6797002391409097128L;

	private Long memberId;//会员ID
	@Column(length=16)
	private String realName;//真实姓名
	@Column(length=18)
	private String idCard;//身份证号码
	private Long handCard;//手持身份证
	private Long cardJust;//身份证正面
	private Long cardReverse;//身份证反面
	private Long graduateCert;//学历证书
	private Long pmpCret;//PMP证书
	private Long jobCert;//工作证
	private Long visitingCert;//个人名片
	private Long businessCert;//营业执照{其他证书}
	
	@Column(length=32)
	private String alipayAccount;//支付宝帐号
	@Column(length=32)
	private String alipayName;//支付宝姓名
	@Column(length=6)
	private String payPass;//支付密码
	@Column(length=32)
	private String payPassSalt;//支付密码盐串
	
	@Type(type = "yes_no")
	private boolean isCard=false;//是否身份认证通过
	@Type(type = "yes_no")
	private boolean isAlipay=false;//是否支付宝认证通过
	@Type(type = "yes_no")
	private boolean isJob=false;//工作证是否审核通过通过
	@Type(type = "yes_no")
	private boolean isBank=false;//银行卡是否审核通过通过
	
	
	@Transient
	private boolean copyed=false;//是否复制帐号
	@Transient
	private boolean asynced=true;//是否同步到其他帐号
	
	
	public boolean getIsCard(){
		return isCard;
	}
	
	
	/**
	 * 格式化姓名
	 * */
	public String getFormatName(){
		if(StringUtils.isEmpty(realName)){
			return "";
		}
		String corbet=realName;
		try {
			int len=realName.length();
			corbet="*"+realName.substring(1,len);
		} catch (Exception e) {
		}
		return corbet;
	}
	
	/**
	 * 格式化身份证号码
	 * */
	public String getFormatCard(){
		if(StringUtils.isEmpty(idCard)){
			return "";
		}
		String corbet=idCard;
		try {
			corbet=corbet.substring(0,4)+"**********"+corbet.substring(corbet.length()-4);
		} catch (Exception e) {
		}
		return corbet;
	}
	
	//身份证正面照片
	public String getCardJustPath(){
		if(cardJustPhoto==null){
			return "";
		}
		return cardJustPhoto.getPath();
	}
	//身份证反面照片
	public String getCardReversePath(){
		if(cardReversePhoto==null){
			return "";
		}
		return cardReversePhoto.getPath();
	}
	
	/************************hibernate many one config**************************/
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cardJust", insertable=false, updatable=false)
    private Attas cardJustPhoto;//身份证正面
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "cardReverse", insertable=false, updatable=false)
    private Attas cardReversePhoto;//身份证背面
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "graduateCert", insertable=false, updatable=false)
    private Attas graduateCertPhoto;//学历证书
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "businessCert", insertable=false, updatable=false)
    private Attas businessCertPhoto;//营业执照
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "pmpCret", insertable=false, updatable=false)
    private Attas pmpCretPhoto;//PMP证书
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "jobCert", insertable=false, updatable=false)
    private Attas jobCertPhoto;//工作证
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "visitingCert", insertable=false, updatable=false)
    private Attas visitingCertPhoto;//名片
}

package com.ledao.elite.rest.framework.response.member;

import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.sys.Attas;

import lombok.Data;

/**
 * rest返回的项目方对象
 * 
 * @author kobe.liu
 * */
@Data
public class RMemberCredit {
	
	private String realName;//真实姓名
	private boolean isCard=false;//是否身份认证通过
	private String idCard;//身份证号码
	private Long cardJust;//身份证正面文件ID
	private String cardJustPath;//身份证正面
	private Long cardReverse;//身份证反面文件ID
	private String cardReversePath;//身份证反面
	private Long graduateCert;//学历技能证书ID
	private String graduateCertPath;//学历技能证书
	
	public RMemberCredit(){}
	public RMemberCredit(MemberCredit credit){
		this.realName=credit.getRealName();
		this.isCard=credit.getIsCard();
		this.idCard=credit.getIdCard();
		this.cardJust=credit.getCardJust();
		this.cardJustPath=credit.getCardJustPath();
		this.cardReverse=credit.getCardReverse();
		this.cardReversePath=credit.getCardReversePath();
		this.graduateCert=credit.getGraduateCert();
		Attas atta=credit.getGraduateCertPhoto();
		this.graduateCertPath=atta==null?"":atta.getPath();
	}

}

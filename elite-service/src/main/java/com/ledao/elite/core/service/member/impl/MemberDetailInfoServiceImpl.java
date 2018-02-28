package com.ledao.elite.core.service.member.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.cache.custom.IdCardCache;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberDetailInfoService;

/**
 *
 *
 * @author zhiyu cao
 **/
@Service
public class MemberDetailInfoServiceImpl extends BaseSerivceImpl implements MemberDetailInfoService {

	@Resource
	private MemberBasicService memberBasicServie;
	@Resource
	private MemberCompanyService memberCompanyServie;
	@Resource
	private MemberCreditService memberCreditServie;

	@Override
	public MemberDetailInfo updateMemberDetailInfo(MemberDetailInfo obj, Long memberId) throws EliteServiceException {

		this.verifyParams(obj, memberId);
		// 第一步更新基本信息
		MemberBasic basic = this.memberBasicServie.findMemberBasicByMemberId(memberId);
		if (basic == null) {
			basic = new MemberBasic();
			basic.setMemberId(memberId);
		}
		basic.setBirthday(obj.getBasic().getBirthday());
		basic.setAreaName(obj.getBasic().getAreaName());
		basic.setMemberSign(obj.getBasic().getMemberSign());
		basic.setPhotoId(obj.getBasic().getPhotoId());
		if (basic.getId() != null) {
			if (obj.getBasic().getEmail() == null || !basic.getEmail().equals(obj.getBasic().getEmail())) {
				basic.setEmail(obj.getBasic().getEmail());
			}
			this.memberBasicServie.updateBasicInfoNoFixed(basic);
		} else {
			this.memberBasicServie.createMemberBasic(basic);
		}

		// 协助完善公司信息

		MemberCompany company = this.memberCompanyServie.findMemberCompanyByMemberId(memberId);
		if (company == null) {
			company = new MemberCompany();
			company.setMemberId(memberId);
		}
		company.setCompanyPosition(obj.getCompany().getCompanyPosition());
		company.setCompanyName(obj.getCompany().getCompanyName());
		company.setCompanyScale(obj.getCompany().getCompanyScale());
		company.setCompanyIntro(obj.getCompany().getCompanyIntro());
		company.setTeamIntro(obj.getCompany().getTeamIntro());
		company.setTeamNum(obj.getCompany().getTeamNum());
		this.memberCompanyServie.updateMemberCompanyInfoNoFixed(company);

		// 协助完善征信信息
		MemberCredit credit = this.memberCreditServie.findMemberCreditByMemberId(memberId);
		// 检查身份证姓名是否匹配
		boolean newCredit = false;
		if (credit == null || !credit.getIsCard()) {
			boolean cflag = IdCardCache.valid(memberId);
			if (!cflag) {
				throw new EliteServiceException("身份证信息验证过于频繁，请明天再试",ErrorCodeEnum.IDCARD_EXIST.code);
			}
			boolean flag = this.memberCreditServie.findValidateIdCard(obj.getCredit().getRealName(), obj.getCredit().getIdCard());
			if (!flag) {
				throw new EliteServiceException("身份证号码和姓名不匹配",ErrorCodeEnum.IDCARD_EXIST.code);
			}
		}
		if (credit == null) {
			credit = new MemberCredit();
			credit.setMemberId(memberId);
			newCredit = true;
		}
		credit.setCardJust(obj.getCredit().getCardJust());
		credit.setCardReverse(obj.getCredit().getCardReverse());
		credit.setJobCert(obj.getCredit().getJobCert());
		credit.setVisitingCert(obj.getCredit().getVisitingCert());
		credit.setBusinessCert(obj.getCredit().getBusinessCert());
		if (newCredit) {
			credit.setIdCard(obj.getCredit().getIdCard());
			credit.setRealName(obj.getCredit().getRealName());
			credit.setCard(true);
			this.memberCreditServie.createMemberCredit(credit);
		} else {
			if (!credit.getIsCard()) {
				credit.setIdCard(obj.getCredit().getIdCard());
				credit.setRealName(obj.getCredit().getRealName());
			}
			credit.setCard(true);
			this.memberCreditServie.updateCreditInfoNoFixed(credit);
		}
		obj.setBasic(basic);
		obj.setCompany(company);
		obj.setCredit(credit);
		return obj;
	}

}

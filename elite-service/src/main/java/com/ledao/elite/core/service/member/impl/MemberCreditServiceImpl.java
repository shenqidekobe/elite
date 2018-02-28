package com.ledao.elite.core.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.plugin.pay.pingpp.PingPlusPlusComponent;
import com.ledao.elite.core.repository.member.MemberCreditRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberCreditService;

@Service
public class MemberCreditServiceImpl extends BaseSerivceImpl implements MemberCreditService {

	@Resource
	private MemberCreditRepository memberCreditRepository;
	@Resource
	private PingPlusPlusComponent pingPlusPlusComponent;
	@Resource
	private EventPublishService eventPublishService;
	
	
	
	@Override
	public MemberCredit syncUpdateMemberCredit(MemberCredit memberCredit) throws EliteServiceException {
		this.verifyParams(memberCredit,memberCredit.getRealName(),memberCredit.getIdCard());
		if(memberCredit.isCopyed()){
			if(memberCredit.getId()==null){
				memberCreditRepository.save(memberCredit);
			}else{
				memberCreditRepository.updateCredit(memberCredit);
			}
		}
		return memberCredit;
	}

	@Override
	public MemberCredit createMemberCredit(MemberCredit memberCredit) throws EliteServiceException {
		this.verifyParams(memberCredit,memberCredit.getIdCard());
		MemberCredit obj = memberCreditRepository.queryMemberCreditByIdCard(memberCredit.getIdCard());
		if(obj!=null&&!memberCredit.isCopyed()){
			throw new EliteServiceException("身份证号:"+memberCredit.getIdCard()+"已存在",ErrorCodeEnum.IDCARD_EXIST.code);
		}else{
			memberCredit.setCard(true);
			this.memberCreditRepository.save(memberCredit);
			if(memberCredit.isAsynced()){
				//同步更新多重身份
				eventPublishService.publishMemberIdentityDate(null, null, memberCredit,null);
			}
		}
		return memberCredit;
	}
	
	@Override
	public MemberCredit updateMemberCredit(Long memberId,MemberCredit memberCredit) throws EliteServiceException {
		this.verifyParams(memberId,memberCredit);
		MemberCredit pojo=this.findMemberCreditByMemberId(memberId);
		if(!memberCredit.getIdCard().equals(pojo.getIdCard())){
			MemberCredit obj = memberCreditRepository.queryMemberCreditByIdCard(memberCredit.getIdCard());
			if(obj!=null){
				throw new EliteServiceException("身份证号:"+memberCredit.getIdCard()+"已存在",ErrorCodeEnum.IDCARD_EXIST.code);
			}
		}
		BeanUtils.copyProperties(memberCredit, pojo,new String[]{"id","memberId","isCard","isAlipay","isJob","isBank","createTime"});
		pojo.setCard(true);
		this.memberCreditRepository.save(pojo);
		if(pojo.isAsynced()){
			//同步更新多重身份
			eventPublishService.publishMemberIdentityDate(null, null, pojo,null);
		}
		return pojo;
	}

	@Override
	public MemberCredit findMemberCreditByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search = new Search(MemberCredit.class);
		search.addFilterEqual("memberId", memberId);
		return memberCreditRepository.searchUnique(search);
	}

	@Override
	public MemberCredit updateMemberCreditByPartnerCompany(Long memberId, MemberCredit memberCredit)
			throws EliteServiceException {
		this.verifyParams(memberId,memberCredit);
		MemberCredit pojo=this.findMemberCreditByMemberId(memberId);
		if(!memberCredit.getIdCard().equals(pojo.getIdCard())){
			MemberCredit obj = memberCreditRepository.queryMemberCreditByIdCard(memberCredit.getIdCard());
			if(obj!=null){
				throw new EliteServiceException("身份证号:"+memberCredit.getIdCard()+"已存在",ErrorCodeEnum.IDCARD_EXIST.code);
			}
		}
		pojo.setCardJust(memberCredit.getCardJust());
		pojo.setCardReverse(memberCredit.getCardReverse());
		pojo.setCard(true);
		this.memberCreditRepository.save(pojo);
		if(memberCredit.isAsynced()){
			//同步更新多重身份
			eventPublishService.publishMemberIdentityDate(null, null, pojo,null);
		}
		return pojo;
	}

	@Override
	public MemberCredit updateMemberCreditByPartnerElite(Long memberId, MemberCredit memberCredit)
			throws EliteServiceException {
		this.verifyParams(memberId,memberCredit);
		MemberCredit pojo=this.findMemberCreditByMemberId(memberId);
		if(!memberCredit.getIdCard().equals(pojo.getIdCard())){
			MemberCredit obj = memberCreditRepository.queryMemberCreditByIdCard(memberCredit.getIdCard());
			if(obj!=null){
				throw new EliteServiceException("身份证号:"+memberCredit.getIdCard()+"已存在",ErrorCodeEnum.IDCARD_EXIST.code);
			}
		}
		BeanUtils.copyProperties(memberCredit, pojo,new String[]{"id","cardJust","cardReverse","jobCert","businessCert","pmpCret"});
		pojo.setCardJust(memberCredit.getCardJust());
		pojo.setCardReverse(memberCredit.getCardReverse());
		pojo.setJobCert(memberCredit.getJobCert());
		pojo.setBusinessCert(memberCredit.getBusinessCert());
		pojo.setPmpCret(memberCredit.getPmpCret());
		pojo.setCard(true);
		this.memberCreditRepository.save(pojo);
		if(pojo.isAsynced()){
			//同步更新多重身份
			eventPublishService.publishMemberIdentityDate(null, null, pojo,null);
		}
		return pojo;
	}
	
	@Override
	public boolean findMemberCreditByAlipayCount(String alipayAccount)throws EliteServiceException{
		if(StringUtils.isEmpty(alipayAccount))
			return false;
		return this.memberCreditRepository.queryMemberCreditByAlipayCount(alipayAccount)!=null;
	}
	
	@Override
	public boolean findMemberCreditByIdCard(String idCard)throws EliteServiceException{
		if(StringUtils.isEmpty(idCard))
			return false;
		return this.memberCreditRepository.queryMemberCreditByIdCard(idCard)!=null;
	}

	@Override
	public void updateCreditInfoNoFixed(MemberCredit obj) throws EliteServiceException {
	  this.memberCreditRepository.save(obj);
	  if(obj.isAsynced()){
		  //同步更新多重身份
		  eventPublishService.publishMemberIdentityDate(null, null, obj,null);
	  }
	}
	

	@Override
	public boolean findValidateIdCard(String name,String idCard)throws EliteServiceException{
		if(StringUtils.isEmpty(name)||StringUtils.isEmpty(idCard)){
			return false;
		}
		Search search=new Search(MemberCredit.class);
		search.addFilterEqual("realName", name);
		search.addFilterEqual("idCard", idCard);
		search.addFilterEqual("isCard", true);
		List<MemberCredit> list=memberCreditRepository.search(search);
		if(list.isEmpty()||list.size()==0){
			boolean flag=pingPlusPlusComponent.validateIdCard(name, idCard);
			return flag;
		}
		return true;
	}

}

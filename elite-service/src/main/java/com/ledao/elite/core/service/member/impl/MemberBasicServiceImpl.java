package com.ledao.elite.core.service.member.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberIdentity;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberBasicRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberBasicService;

@Service
public class MemberBasicServiceImpl extends BaseSerivceImpl implements MemberBasicService {

	@Resource
	private MemberBasicRepository memberBasicRepository;
	@Resource
	private EventPublishService eventPublishService;
	
	
	
	@Override
	public MemberBasic syncUpdateMemberBasic(MemberBasic obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getMemberId());
		if(obj.isCopyed()){
			if(obj.getId()==null){
				this.memberBasicRepository.save(obj);
			}else{
				this.memberBasicRepository.updateBasic(obj);
			}
		}
		return obj;
	}

	@Override
	public MemberBasic createMemberBasic(MemberBasic obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getMemberId());
		if(StringUtils.isNotEmpty(obj.getAreaName())){
			Long areaId=DictCache.getAreaMap(obj.getAreaName());
			if(areaId==null){
				throw new EliteServiceException("请选择一个国内的省市",ErrorCodeEnum.CITY_NOT_EXIST.code);
			}
			obj.setAreaId(areaId);
		}
		if(StringUtils.isNotEmpty(obj.getEmail())&&!obj.isCopyed()){
			boolean flag=this.findMemberBasicByEmail(obj.getEmail());
	    	if(flag){
				throw new EliteServiceException("邮箱号:"+obj.getEmail()+"已存在",ErrorCodeEnum.EMAIL_EXIST.code);
		    }
		}
		this.memberBasicRepository.merge(obj);
		if(obj.isAsynced()){
			//同步更新
			eventPublishService.publishMemberIdentityDate(null, obj, null,null);
		}
		return obj;
	}

	@Override
	public MemberBasic updateMemberBasic(Long memberId, MemberBasic basic) throws EliteServiceException {
		this.verifyParams(basic);
		MemberBasic pojo = this.findMemberBasicByMemberId(memberId);
		if (StringUtils.isNotEmpty(basic.getAreaName())&&!basic.getAreaName().equals(pojo.getAreaName())) {
			Long areaId = DictCache.getAreaMap(basic.getAreaName());
			if(areaId==null){
				throw new EliteServiceException("请选择一个国内的省市",ErrorCodeEnum.CITY_NOT_EXIST.code);
			}
			basic.setAreaId(areaId);
		}
		//app注册跳过时
		if(pojo==null){
			memberBasicRepository.save(basic);
			if(basic.isAsynced()){
				//同步更新
				eventPublishService.publishMemberIdentityDate(null, basic, null,null);
			}
		}else{
			if(pojo.getEmail()!=null){
				BeanUtils.copyProperties(basic, pojo, new String[] { "memberId", "integrity","id","createTime","email"});
			}else{
				BeanUtils.copyProperties(basic, pojo, new String[] { "memberId", "integrity","id","createTime"});
			}
			this.memberBasicRepository.merge(pojo);
			if(pojo.isAsynced()){
				//同步更新
				eventPublishService.publishMemberIdentityDate(null, basic, null,null);
			}
		}
		return pojo;
	}

	@Override
	public MemberBasic findMemberBasicByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		MemberBasic obj= this.memberBasicRepository.searchUnique(new Search().addFilterEqual("memberId", memberId));
	    //精英注册时候，基本信息全部跳过，不生成MemberBasic表，这个时候查不到不能抛出异常
		/*if(obj==null)
	    	throw new EliteServiceException("帐号不存在",ErrorCodeEnum.OBJECT_NOT_EXIST.code);*/
	    return obj;
	}

	@Override
	public MemberBasic saveOrUpdateBasic(Long memberId, MemberBasic basic) throws EliteServiceException {
		this.verifyParams(memberId, basic, basic.getEmail());
		MemberBasic obj = this.findMemberBasicByMemberId(memberId);
		if (obj == null) {
			boolean flag=this.findMemberBasicByEmail(basic.getEmail());
			if (flag&&!basic.isCopyed()) {
				throw new EliteServiceException("邮箱号:" + basic.getEmail() + "已存在",ErrorCodeEnum.EMAIL_EXIST.code);
			}
			basic.setMemberId(memberId);
			obj = this.createMemberBasic(basic);
			if(basic.isAsynced()){
				//同步更新多重身份
				eventPublishService.publishMemberIdentityDate(null, basic, null,null);
			}
			
		} else {
			if (StringUtils.isNotEmpty(basic.getAreaName())&&!basic.getAreaName().equals(obj.getAreaName())) {
				Long areaId = DictCache.getAreaMap(basic.getAreaName());
				if(areaId==null){
					throw new EliteServiceException("请选择一个国内的省市",ErrorCodeEnum.CITY_NOT_EXIST.code);
				}
				basic.setAreaId(areaId);
			}
			if (!basic.getEmail().equals(obj.getEmail())&&!basic.isCopyed()) {
				boolean flag=this.findMemberBasicByEmail(basic.getEmail());
		    	if(flag){
					throw new EliteServiceException("邮箱号:" + basic.getEmail() + "已存在",ErrorCodeEnum.EMAIL_EXIST.code);
				}
			}
			if(basic.getPhotoId()==null){
				BeanUtils.copyProperties(basic, obj, new String[] { "memberId", "integrity","id","createTime","photoId"});
			}else{
				BeanUtils.copyProperties(basic, obj, new String[] { "memberId", "integrity","id","createTime"});
			}
			this.memberBasicRepository.merge(obj);
			if(obj.isAsynced()){
				//同步更新多重身份
				eventPublishService.publishMemberIdentityDate(null, obj, null,null);
			}
		}
		return obj;
	}

	@Override
	public MemberBasic updatePartnerBasic(Long memberId, MemberBasic basic, String type) throws EliteServiceException {
		this.verifyParams(basic);
		MemberBasic obj = this.findMemberBasicByMemberId(memberId);
		if (obj == null) {
			obj = new MemberBasic();
			obj.setMemberId(memberId);
		}
		if (MemberIdentity.MemberIdentity_Type.partnerCompany.name().equals(type)) {
			obj.setAreaName(basic.getAreaName());
			obj.setAreaId(basic.getAreaId());
			obj.setEmail(basic.getEmail());
		}
		if (MemberIdentity.MemberIdentity_Type.partnerElite.name().equals(type)) {
			obj.setBirthday(basic.getBirthday());
			obj.setEmail(basic.getEmail());
		}
		this.memberBasicRepository.save(obj);
		if(obj.isAsynced()){
			//同步更新多重身份
			eventPublishService.publishMemberIdentityDate(null, obj, null,null);
		}
		return obj;
	}

	@Override
	public void updateBasicInfoNoFixed(MemberBasic basic) {
		this.verifyParams(basic, basic.getId());
		if (StringUtils.isNotEmpty(basic.getAreaName())) {
			Long areaId = DictCache.getAreaMap(basic.getAreaName());
			if(areaId==null){
				throw new EliteServiceException("请选择一个国内的省市",ErrorCodeEnum.CITY_NOT_EXIST.code);
			}
			basic.setAreaId(areaId);
		}
		if(StringUtils.isNotEmpty(basic.getEmail())&&!basic.isCopyed()){
	    	boolean flag=this.findMemberBasicByEmail(basic.getEmail());
	    	if(flag){
	    		throw new EliteServiceException("邮箱号:"+basic.getEmail()+"已存在",ErrorCodeEnum.EMAIL_EXIST.code);
	    	}
		}
		this.memberBasicRepository.merge(basic);
		if(basic.isAsynced()){
			//同步更新多重身份
			eventPublishService.publishMemberIdentityDate(null, basic, null,null);
		}
	}
	@Override
	public boolean findMemberBasicByEmail(String email) throws EliteServiceException {
		if (StringUtils.isEmpty(email))return false;
		List<MemberBasic> list=this.memberBasicRepository.queryMemberBasicByEmail(email);
		if(list.isEmpty())return false;
		
		boolean flag=false;
		List<String> accountList=new ArrayList<>();
		for(MemberBasic basic:list){
			MemberPassport member=basic.getMember();
			String account=member.getAccountOffSuffix();
			if(accountList.size()>0&&!accountList.contains(account)){
				flag=true;
				break;
			}
			accountList.add(account);
		}
		return flag;
	}

}

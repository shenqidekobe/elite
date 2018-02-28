package com.ledao.elite.core.service.platform.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.platform.PlatformVisitCode;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.platform.PlatformVisitCodeRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.platform.PlatformVisitCodeService;

@Service
public class PlatformVisitCodeServiceImpl extends BaseSerivceImpl implements PlatformVisitCodeService{

	@Resource
	private PlatformVisitCodeRepository platformVisitCodeRepository;
	
	@Override
	public PlatformVisitCode findPlatformVisitCode(String visitName, String visitPhone, String visitCode)
			throws EliteServiceException {
		this.verifyParams(visitCode);
		Search search=new Search();
		if(StringUtils.isNotEmpty(visitName)){
			search.addFilterEqual("visitName", visitName);
		}
		if(StringUtils.isNotEmpty(visitPhone)){
			search.addFilterEqual("visitPhone", visitPhone);
		}
		search.addFilterEqual("visitCode", visitCode);
		return this.platformVisitCodeRepository.searchUnique(search);
	}

	@Override
	public PlatformVisitCode updatePlatformVisitCode(PlatformVisitCode obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getId());
		this.platformVisitCodeRepository.save(obj);
		return obj;
	}
	
	@Override
	public void updatePlatformVisitMember(String visitCode,Long memberId)throws EliteServiceException{
		if(StringUtils.isEmpty(visitCode)||memberId==null)
			return;
		Search search=new Search();
		search.addFilterEqual("visitCode", visitCode);
		PlatformVisitCode pojo=this.platformVisitCodeRepository.searchUnique(search);
		if(pojo==null)
			return;
		pojo.setMemberId(memberId);
		this.platformVisitCodeRepository.save(pojo);
		
	}

}

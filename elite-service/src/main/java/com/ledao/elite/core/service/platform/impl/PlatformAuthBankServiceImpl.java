package com.ledao.elite.core.service.platform.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.platform.PlatformAuthBank;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.plugin.pay.pingpp.PingPlusPlusComponent;
import com.ledao.elite.core.repository.platform.PlatformAuthBankRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.platform.PlatformAuthBankService;

@Service
public class PlatformAuthBankServiceImpl extends BaseSerivceImpl implements PlatformAuthBankService{
	
	@Resource
	private PlatformAuthBankRepository platformAuthBankRepository;
	@Resource
	private PingPlusPlusComponent pingPlusPlusComponent;

	@Override
	public PlatformAuthBank createPlatformAuthBank(PlatformAuthBank obj) throws EliteServiceException {
		if(!findPlatformAuthBank(obj.getName(), obj.getIdCard(), obj.getBankCard(), obj.getPhone())){
			platformAuthBankRepository.save(obj);
		}
		return obj;
	}

	@Override
	public boolean findPlatformAuthBank(String name, String idCard, String bankCard, String phone)
			throws EliteServiceException {
		Search search=new Search();
		search.addFilterEqual("name", name);
		search.addFilterEqual("idCard", idCard);
		search.addFilterEqual("bankCard", bankCard);
		search.addFilterEqual("phone", phone);
		List<PlatformAuthBank> list=this.platformAuthBankRepository.search(search);
		return !list.isEmpty();
	}
	
	@Override
	public boolean findValidateBankCard(String name,String idCard,String bankCard,String phone)throws EliteServiceException{
		if(StringUtils.isEmpty(name)||StringUtils.isEmpty(idCard)||StringUtils.isEmpty(bankCard)){
			return false;
		}
		//先从本地数据库验证是否已经验证
		boolean authFlag=findPlatformAuthBank(name, idCard, bankCard, phone);
		if(!authFlag){
			boolean flag=pingPlusPlusComponent.validateBankCard(name, idCard, bankCard, phone);
			if(!flag){
				return false;
			}
			createPlatformAuthBank(new PlatformAuthBank(name, idCard, bankCard, phone));
		}
		return true;
	}

}

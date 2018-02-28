package com.ledao.elite.core.service.platform;

import com.ledao.elite.core.domain.platform.PlatformAuthBank;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 平台已认证银行卡服务接口
 */
public interface PlatformAuthBankService {
	
	/**
	 * 保存
	 */
	PlatformAuthBank createPlatformAuthBank(PlatformAuthBank obj) throws EliteServiceException;
	
	/**
	 * 查询是否存在
	 * */
	boolean findPlatformAuthBank(String name,String idCard,String bankCard,String phone)throws EliteServiceException;
	
	/**
	 * 验证银行卡号
	 * */
	boolean findValidateBankCard(String name,String idCard,String bankCard,String phone)throws EliteServiceException;

}

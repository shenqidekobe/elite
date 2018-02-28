package com.ledao.elite.core.service.sys;

import com.ledao.elite.core.domain.sys.SmsRecord;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 短信发送对象接口
 *
 * @author zhiyu cao
 **/

public interface SmsRecordService {

	/**
	 * 短信发送对象
	 * 
	 * @param obj
	 *
	 **/
	SmsRecord createSmsRecord(SmsRecord obj) throws EliteServiceException;


	/**
	 * 修改短信记录状态
	 * 
	 * @param id
	 * @param status
	 * @param isUse
	 * */
	SmsRecord updateSmsRecordStatus(Long id,String status,boolean isUse)throws EliteServiceException;
	
	/**
	 * 按短信类型和手机号码查询短信记录
	 * 
	 * @param phone
	 * @param type
	 * */
	SmsRecord findSmsRecordByTypeAndPhone(String phone,String type)throws EliteServiceException;
	
	/**
	 * 验证短信验证码是否正确
	 * 
	 * @param phone
	 * @param type
	 * @param code
	 * @param expireTime
	 * */
	boolean findSmsRecordIsExists(String phone,String type,String code,Integer expireTime)throws EliteServiceException;

}

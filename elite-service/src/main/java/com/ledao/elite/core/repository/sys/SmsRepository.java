package com.ledao.elite.core.repository.sys;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.sys.SmsRecord;

/**
 * 短信发送接口
 * 
 * @author kobe.liu
 * */
public interface SmsRepository extends GenericDAO<SmsRecord, Long> {
	
	
	/**
	 * 根据手机号码和类型查询短信记录
	 * 
	 * @param phone
	 * @param type
	 * */
	SmsRecord querySmsRecordByTypeAndPhone(String phone, String type);

}

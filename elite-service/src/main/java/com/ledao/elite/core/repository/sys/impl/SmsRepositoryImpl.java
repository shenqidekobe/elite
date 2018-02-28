package com.ledao.elite.core.repository.sys.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.sys.SmsRecord;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SmsRepository;

@Repository
public class SmsRepositoryImpl extends GenericRepositoryImpl<SmsRecord, Long> implements SmsRepository {

	@Override
	public SmsRecord querySmsRecordByTypeAndPhone(String phone, String type) {
		String hql="from SmsRecord where phones=? and smsType=? order by sendTime desc";
		Query query=getSession().createQuery(hql);
		query.setString(0,phone);
		query.setString(1,type);
		query.setFirstResult(0);  
        query.setMaxResults(1);  
		return (SmsRecord) query.uniqueResult();
	}

}

package com.ledao.elite.core.repository.sys;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.sys.EmailRecord;

/**
 * 邮箱发送记录接口
 * 
 * @author kobe.liu
 * */
public interface EmailRepository extends GenericDAO<EmailRecord, Long> {

}

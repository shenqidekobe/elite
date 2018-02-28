package com.ledao.elite.core.repository.partner;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.partner.PartnerProjectRecord;
import com.ledao.elite.core.framework.base.Pager;

public interface PartnerProjectRecordRepository extends GenericDAO<PartnerProjectRecord, Long> {

	/**
	 * 根据渠道方ID分页查询
	 * 
	 * @return
	 */
	List<PartnerProjectRecord> findPartnerProjectRecordByPartnerId(Long partnerId, String keyword,Date startTime,Date endTime,Pager pager);
	/**
	 * 根据渠道方ID查询数量
	 * 
	 * @return
	 */
	Integer findPartnerProjectRecordCountByPartnerId(Long partnerId, String keyword,Date startTime,Date endTime);
	
	/**
	 * 查询时间段备案项目
	 */
	Integer findPartnerProjectRecordCountByTime(Long partnerId,Date startTime,Date endTime);
}

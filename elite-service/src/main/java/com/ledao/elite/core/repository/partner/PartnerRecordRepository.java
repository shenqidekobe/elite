package com.ledao.elite.core.repository.partner;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.partner.PartnerRecord;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 合作伙伴 人才推荐渠道方Id
 */
public interface PartnerRecordRepository extends GenericDAO<PartnerRecord, Long> {
	
	/**
	 * 邀请注册 渠道查询
	 */
	List<PartnerRecord> findPartnerRecordListByKeyword(Long partnerId,String partnerType, String status, String keyword,Date startTime,Date endTime,
			Pager pager);
	
	/**
	 * 邀请注册 渠道查询数量
	 */
	Integer findPartnerRecordListByKeywordCount(Long partnerId,String partnerType, String status, String keyword,Date startTime,Date endTime);
	/**
	 * 
	 */
	List<PartnerRecord> findPartnerRecordListBySearchType(Long partnerId, String partnerType,String status, String keyword,Date startTime,Date endTime,
			String searchType,Date beforDays,Pager pager);
	
	/**
	 * 
	 */
	Integer findPartnerEliteListRecordBySearchTypeCount(Long partnerId,String partnerType, String status, String keyword,Date startTime,Date endTime,String searchType,Date beforDays);
   
	/**
	 * 备案渠道数量
	 */
	Integer findPartnerRecordsCountByPartnerIdAndType(Long partnerId,String partnerType,Date startTime,Date endTime);
	
	/**
	 * 更新状态sql
	 */
	void updateParnterRecordStatusBySql(String status,Long id);
}

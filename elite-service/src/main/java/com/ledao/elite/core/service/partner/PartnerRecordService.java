package com.ledao.elite.core.service.partner;

import java.util.Date;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.partner.PartnerRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Status;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 合作伙伴猎头方 渠道推荐
 * 
 * @author kobe.liu
 * @version 1.0
 */
public interface PartnerRecordService {

	/**
	 * 根据会员ID查询备案性情
	 * 
	 * @param memberId
	 */
	PartnerRecord createPartnerRecord(PartnerRecord elite) throws EliteServiceException;

	/**
	 * 根据会员ID查询备案性情
	 * 
	 * @param memberId
	 */
	PartnerRecord findPartnerRecordByAccountAndStatus(String memberPhone,PartnerRecord_Type partnerType) throws EliteServiceException;

	/**
	 * 根据备案phone 备案渠道方Id查询
	 * 
	 * @param memberId
	 */
	PartnerRecord findPartnerRecordByPhoneAndPartnerId(Long partnerId, String memberPhone) throws EliteServiceException;

	/**
	 * 根据会员ID查询备案性情
	 * 
	 * @param memberId
	 */
	PartnerRecord findPartnerRecordByMemberIdAndStatus(Long memberId, PartnerRecord_Status status)
			throws EliteServiceException;

	/**
	 * 根据会员ID查询备案性情
	 * 
	 * @param memberId
	 */
	PartnerRecord findPartnerRecordByMemberId(Long memberId) throws EliteServiceException;

	/**
	 * 查询渠道
	 */
	SearchResult<PartnerRecord> findPartnerRecords(Long partnerId, String partnerType, String keyword, String status,
			Date startTime, Date endTime, Pager pager) throws EliteServiceException;

	/**
	 * 更新渠道备案信息
	 */
	PartnerRecord updatePartnerRecordInfoNoFixed(PartnerRecord record) throws EliteServiceException;

	/**
	 * 渠道管理
	 */
	SearchResult<PartnerRecord> findPartnerRecordsBySearchType(Long partnerId, Long memberId, String partnerType,
			String keyword, String searchType, Date startTime, Date endTime, Pager pager) throws EliteServiceException;

	/**
	 * 根据手机号码查询
	 */

	PartnerRecord findPartnerRecordByPhone(String phone) throws EliteServiceException;

	/**
	 * 
	 */
	Integer findPartnerRecordByEnterCount(Long partnerId, String partnerType, String status)
			throws EliteServiceException;
	/**
	 * 更新状态sql
	 */
	void updatePartnerRecordStatusBySql(String status,Long id)throws EliteServiceException;

}

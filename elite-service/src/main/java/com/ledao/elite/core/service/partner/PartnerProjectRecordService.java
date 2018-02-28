package com.ledao.elite.core.service.partner;

import java.math.BigDecimal;
import java.util.Date;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.partner.PartnerProjectRecord;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 渠道方推荐的项目记录服务接口
 * 
 * */
public interface PartnerProjectRecordService {
	
	
	/**
	 * 新增项目记录
	 * 
	 * @param partnerProjectRecord
	 * */
	PartnerProjectRecord createPartnerProjectRecord(Long partnerId,Long partnerProjectId,Long projectId)throws EliteServiceException;
	
	/**
	 * 更新项目记录的收益情况
	 * 
	 * @param projectId
	 * @param income
	 * */
	void updatePartnerProjectRecordAsIncome(Long projectId,BigDecimal income)throws EliteServiceException;
	
	/**
	 * 根据项目ID查询，每个项目ID应该只有一条记录
	 * 
	 * @param projectId
	 * @return partnerProjectRecord
	 * */
	PartnerProjectRecord findPartnerProjectRecordByProjectId(Long projectId)throws EliteServiceException;
	/**
	 * 根据项目俱到方ID 查询
	 * 
	 * @param projectId
	 * @return partnerProjectRecord
	 * */
	SearchResult<PartnerProjectRecord> findPartnerProjectRecordByPartnerId(Long partnerId,String keyword,Date startTime,Date endTime,Pager pager)throws EliteServiceException;
	/**
	 * 根据推荐的项目ID查询
	 * 
	 * @param partnerProjectId
	 * @return partnerProjectRecord
	 * */
	PartnerProjectRecord findPartnerProjectRecordByPartnerProjectId(Long partnerProjectId)throws EliteServiceException;

   /**
    * 根据推荐项目方Id查询数量
    */
	Integer findPartnerProjectRecordCountByPartnerId(Long partnerId,String keyword)throws EliteServiceException;
}

package com.ledao.elite.core.service.member;

import java.math.BigDecimal;
import java.util.Date;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberIncome;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员收益服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface MemberIncomeService {
	
	/**
	 * 新建会员收益
	 * 
	 * @param income
	 * */
	void createMemberIncome(MemberIncome income)throws EliteServiceException;
	
	
	/**
	 * 会员收益
	 */
	BigDecimal findMemberIncomeSumByMemberId(Long memberId,Date startTime,Date endTime,String incomeType)throws EliteServiceException;
	
	/**
	 * 间接会员收益
	 */
	BigDecimal findMemberIncomeSumByMemberIdAndSourceMemberId(Long memberId,Long sourceMemberId,Date startTime,Date endTime,String incomeType)throws EliteServiceException;
	
	
	/**
	 * 渠道下面子渠道中间接会员收益
	 * 
	 */
	BigDecimal findMemberIncomeSumByPartnerId(Long sourceMemberId,Long memberId,Long partnerMemberId,Date startTime,Date endTime,String incomeType)throws EliteServiceException;
    
	/**
	 * 渠道方从间接渠道直接渠道上捞到金额
	 * @param memberId 渠道方ID
	 * @param partnerId 间接渠道或者直接渠道的渠道ID
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws EliteServiceException
	 */
	BigDecimal findMemberIncomeSumByMemberIdAndParnterId(Long memberId,Long partnerId,Date startTime,Date endTime)throws EliteServiceException;
	
	/**
	 * 渠道方分页查询收益
	 */
	SearchResult<MemberIncome>findMemberIncomesByPartner(String keyword, String searchType, Long partnerId,
			Long memberId,Date startTime, Date endTime, Pager pager)throws EliteServiceException;
	
    /**
     * 人才渠道方收益中心查找
     */
	SearchResult<MemberIncome>findMemberIncomesByPartnerElite(String keyword, String searchType, 
			Long memberId,Date startTime, Date endTime, Pager pager)throws EliteServiceException;
	
	/**
	 * 项目渠道方直接收益
	 */
	SearchResult<Project> findMemberIncomesByPartnerCompanyOwn(
			Long memberId,String searchType,Date startTime, Date endTime, Pager pager)throws EliteServiceException;

	/**
	 * 项目渠道方收益记录
	 */
	SearchResult<MemberIncome> findMemberIncomesByPartnerCompanyType(
			Long memberId,String keyword,String searchType,Date startTime, Date endTime, Pager pager)throws EliteServiceException;
	
	/**
	 * 查询收益类型的数量(按项目ID分组)
	 * */
	Integer findMemberIncomeProjectCount(Long memberId,Income_Type type)throws EliteServiceException;
	
	/**
	 * 渠道方对项目的收益
	 */
	BigDecimal findMemberIncomeByMemeberIdAndProjectId(Long memberId,Long projectId)throws EliteServiceException;
}

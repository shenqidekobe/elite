package com.ledao.elite.core.repository.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberIncome;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员收益接口
 * */
public interface MemberIncomeRepository extends GenericDAO<MemberIncome, Long>{
	
	/**
	 * 查询收益
	 */
	BigDecimal findMemberIncomeSumByMemberId(Long memberId, String incomeType,Date startTime,Date endTime);
	
	/**
	 * 查询收益
	 */
	BigDecimal findMemberIncomeSumByMemberIdAndSourceMemberId(Long memberId, Long sourceMemberId,String incomeType,Date startTime,Date endTime);
	
	/**
	 * 查询渠道方下渠道间接收益
	 * @param partnerId
	 * @param memberId
	 * @param incomeType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	BigDecimal findMemberIncomeSumByPartnerId(Long sourceMemberId, Long memberId,Long partnerMemberId,String incomeType,Date startTime,Date endTime);
	
	/**
	 * 查询收益
	 */
	BigDecimal findMemberIncomeSumByMemberIdAndProjectId(Long memberId,Long projectId, String incomeType, Date startTime, Date endTime);
	
	/**
	 * 渠道方从间接渠道，直接渠道方，捞到的钱
	 * @param memberId    渠道方memberId
	 * @param partnerId   直接渠道，间接渠道 partnerId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	BigDecimal findMemberIncomeSumByMemberIdAndPartnerId(Long memberId,Long partnerId, Date startTime, Date endTime);
   
	/**
	 * 人才渠道方收益记录
	 * @param keyword
	 * @param searchType（精英，CTO，直接渠道,间接渠道）
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<MemberIncome>findMemberIncomeByPartnerElite(String keyword,Long memberId,String searchType,Date startTime,Date endTime,Pager pager);
  
	/**
	 * 人才渠道方收益记录数量
	 * @param keyword
	 * @param memberId
	 * @param searchType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Integer findMemberIncomeCountByPartnerElite(String keyword,Long memberId,String searchType,Date startTime,Date endTime);
	/**
     * 渠道方查询收益记录
     */
    List<MemberIncome>findMemberIncomesByPartner(String keyword,String searchType,Long partnerId,Date startTime,Date endTime,Pager pager); 
    
    /**
     * 渠道方查询收益记录数量
     */
    Integer findMemberIncomeCountByPartner(String keyword,String searchType,Long partnerId,Date startTime,Date endTime,Pager pager); 
    
    /**
     * 项目推荐方直接收益
     */
   List<MemberIncome> findMemberIncomeByPartnerCompanyOwn(Long memberId,Date startTime,Date endTime,String type,Pager pager);
   /**
   * 项目推荐方直接收益查询数量
   */
    Integer findMemberIncomesCountByPartnerCompanyOwn(Long memberId,String type,Date startTime,Date endTime);
    
     /**
      * 项目推荐方收益记录
      */
    List<MemberIncome> findMemberIncomeByPartnerCompany(Long memberId,String keyword,Date startTime,Date endTime,String type,Pager pager);
  
    /**
     * 项目推荐方收益记录查询数量
     */
    Integer findMemberIncomesCountByPartnerCompany(Long memberId,String keyword,String type,Date startTime,Date endTime);
    
    /**
     * 查询某个收益类型的项目数据
     * */
    Integer findMemberIncomeProjectCount(Long memberId,Income_Type type);
    
    
    
    
}

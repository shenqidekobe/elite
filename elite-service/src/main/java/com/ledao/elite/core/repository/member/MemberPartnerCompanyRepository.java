package com.ledao.elite.core.repository.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 合作伙伴 项目方接口
 */
public interface MemberPartnerCompanyRepository extends GenericDAO<MemberPartnerCompany, Long> {
	
	/**
	 * 更新渠道方的结算数据
	 * 
	 * */
	void updateMemberPartnerEliteAsCalculate(Long id,Integer orderCount,BigDecimal totalAmount,Integer calculateOrderCount,
			BigDecimal calculateTotalAmount,Date startOrderTime,Date lastOrderTime);
	
	/**
	 * 周期时间到，清空渠道方的结算数据
	 * */
	void updateMemberPartnerEliteClearCalculate(Long id,Date clearCalculateTime);

	/**
	 * 冠军榜单查询
	 */
	List<MemberPartnerCompany> findMemberPartnerCompanyChampionsList(Long partnerId, String status, Date startTime,
			Date endTime, Pager pager);

	/**
	 * 冠军榜单查询数量
	 */
	Integer findMemberPartnerCompanyChampionsListCount(Long partnerId, String status);

	/**
	 * 查询备案渠道数
	 */
	Integer findMemberPartnerPutCount(Long partnerId, Date startTime, Date endTime);
	
	/**
	 * 查询全部的冠军表单
	 */
	public List<MemberPartnerCompany> findMemberPartnerCompanyAllList(Long partnerId, String status, Date startTime,
			Date endTime);
	/**
	 * 根据类别查询
	 */
	public List<MemberPartnerCompany> findMemberPartnerCompanyListBySearchType(Long parentId, String keyword,
			String searchType, Date startTime, Date endTime,Date beforDays, Pager pager);
	
	/**
	 * 根据类别查询数量
	 */
	public Integer findMemberPartnerCompanyListBySearchTypeCount(Long parentId, String keyword, String searchType,
			Date startTime, Date endTime, Date beforDays);
	
	/**
	 * 根据父渠道查询
	 */
	List<MemberPartnerCompany>findMemberPartnerCompanyListByIncome(Long memberId,String searchType,Date startTime,Date endTime,Pager pager);
	/**
	 * 根据父渠道查询数量
	 */
	Integer findMemberPartnerCompanyListByIncomeCount(Long memberId,String searchType,Date startTime,Date endTime);
    
	/**
	 * update sql
	 */
	void updateMemberPartnerCompanyPartnerIdBysql(Long partnerId,Long id);
}

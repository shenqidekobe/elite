package com.ledao.elite.core.service.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 合作伙伴项目方服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 */
public interface MemberPartnerCompanyService {

	/**
	 * 创建项目方资料
	 * 
	 * @param obj
	 */
	MemberPartnerCompany createMemberPartnerCompany(MemberPartnerCompany obj) throws EliteServiceException;

	/**
	 * 项目推荐方 注册第二步骤 完善基本资料
	 */
	void updateMemberPartnerCompanyInfo(Long memberId, String sex, String areaName, String companyName, String email)
			throws EliteServiceException;

	/**
	 * 根据会员ID修改项目方资料
	 * 
	 * @param memberId
	 * @param obj
	 */
	MemberPartnerCompany updateMemberPartnerCompany(Long memberId, MemberPartnerCompany obj)
			throws EliteServiceException;

	/**
	 * 项目推荐方-修改基本信息
	 */

	void updateMemberPartnerCompanyBasic(Long memberId, MemberPassport member, MemberBasic basic, MemberCredit credit,
			MemberPartnerCompany company) throws EliteServiceException;

	/**
	 * 修改项目方状态
	 * 
	 * @param memberId
	 * @param status
	 */
	MemberPartnerCompany updateMemberPartnerCompanyStatus(Long memberId, String status) throws EliteServiceException;

	/**
	 * 审核项目方资料
	 * 
	 * @param memberId
	 * @param auditId
	 * @param auditReason
	 * @param status
	 */
	MemberPartnerCompany updateMemberPartnerCompanyAudit(MemberPartnerCompany company)
			throws EliteServiceException;

	/**
	 * 更新项目方资料 需更新的参数不固定
	 */
	MemberPartnerCompany updateMemberPartnerCompanyNoFixed(MemberPartnerCompany company) throws EliteServiceException;
	
	/**
	 * 更新渠道方的结算数据
	 * 
	 * */
	void updateMemberPartnerEliteAsCalculate(Long id,Integer orderCount,BigDecimal totalAmount,Integer calculateOrderCount,
			BigDecimal calculateTotalAmount,Date startOrderTime,Date lastOrderTime)throws EliteServiceException;
	
	/**
	 * 周期时间到，清空渠道方的结算数据
	 * */
	void updateMemberPartnerEliteClearCalculate(Long id,Date clearCalculateTime)throws EliteServiceException;

	/**
	 * 根据会员ID查询项目方资料
	 * 
	 * @param memberId
	 */
	MemberPartnerCompany findMemberPartnerCompanyByMemberId(Long memberId) throws EliteServiceException;

	/**
	 * 根据渠道方电话查询其信息
	 * 
	 * @param phone
	 */
	MemberPartnerCompany findMemberPartnerCompanyByPhone(String phone) throws EliteServiceException;

	/**
	 * 根据Id查询
	 * 
	 */
	MemberPartnerCompany findMemberPartnerCompanybyId(Long id) throws EliteServiceException;

	/**
	 * 冠军榜单分页查询
	 */
	SearchResult<MemberPartnerCompany> findMemberPartnerEliteCompanyByParentId(Long partnerId, Date startTime,
			Date endTime, String status, Pager pager) throws EliteServiceException;
	
	/**
	 * 按状态查询列表
	 * */
	List<MemberPartnerCompany> findMemberPartnerEliteCompanyByStatus(Member_Status status) throws EliteServiceException;

	/**
	 * 冠亚季军查询
	 */
	List<MemberPartnerCompany> findMemberPartnerCompanyListTopThree(Long parentId, Date startTime, String status,
			Date endTime) throws EliteServiceException;
	
	/**
	 * 排名查询
	 */
	public Integer findMemberPartnerCompanyIndex(Long partnerId, Long memberId, Date startTime, Date endTime,
			String status)throws EliteServiceException;
	
	/**
	 * 查询间接受益列表
	 */
	SearchResult<MemberPartnerCompany> findMemberPartnerComapnyIncomeByParent(Long memberId,String searchType,Date StartTime,Date endTime,Pager pager)throws EliteServiceException;
  
	/**
	 * 查找当前渠道的直接渠道推荐的项目数量，做单金额
	 * 
	 * @param id
	 * */
	Map<String,Object> findPartnerCompanyDirectCount(Long id,Date startTime,Date endTime)throws EliteServiceException;
	
	/**
	 * 查找当前渠道的间接渠道推荐的项目数量，做单金额
	 * 
	 * @param id
	 * */
	Map<String,Object> findPartnerCompanyInDirectCount(Long id,Date startTime,Date endTime)throws EliteServiceException;
	
	/**
	 * 更新partnerId
	 */
	void updateMemberPartnerCompanyPartnerId(Long partnerId,Long id)throws EliteServiceException;
	
	/**
	 * 查询时间段的备案入驻项目数量
	 */
	Integer findMemberPartnerProjectCountByTimes(Long partnerId,String status,Date startTime,Date endTime) throws EliteServiceException;
}

package com.ledao.elite.core.service.partner;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 合作伙伴猎头方服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 */
public interface PartnerEliteService {

	/**
	 * 猎头推荐一个精英
	 * 
	 * @param partnerElite
	 * @return partnerElite
	 */
	PartnerElite createPartnerElite(PartnerElite obj) throws EliteServiceException;
	
	/**
	 * 渠道方推荐一个精英
	 * 
	 * @param partnerElite
	 * @return partnerElite
	 */
	PartnerElite createPartnerEliteByPartner(PartnerElite obj) throws EliteServiceException;
	/**
	 * 猎头推荐一个精英不验证手机号
	 * 
	 * @param partnerElite
	 * @return partnerElite
	 */
	PartnerElite createPartnerEliteNoVerifyParams(PartnerElite obj) throws EliteServiceException;

	/**
	 * 分配负责人
	 */
	PartnerElite updatePartnerElite(Long auditId, Long id) throws EliteServiceException;

	/**
	 * 更新
	 */
	PartnerElite updatePartnerElite(PartnerElite obj) throws EliteServiceException;

	/**
	 * 分页查询精英
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 * @param status
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<PartnerElite> findPartnerEliteList(Long partnerId, String name, String phone, String email,
			String status, Pager pager) throws EliteServiceException;

	/**
	 * 根据会员ID查询精英详情
	 * 
	 * @param memberId
	 * @return
	 * @throws EliteServiceException
	 */
	PartnerElite findPartnerEliteBymemberId(Long memberId) throws EliteServiceException;

	/**
	 * 根据会员注册帐号查询是否存在推荐记录
	 * 
	 * @param account
	 * @return PartnerElite
	 */
	PartnerElite findPartnerEliteByAccount(String account) throws EliteServiceException;

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 * @throws EliteServiceException
	 */
	PartnerElite findPartnerEliteById(Long id) throws EliteServiceException;

	/**
	 * 根据关键字（精英,角色，手机号)模糊查询 状态，备案时间联合查询
	 */
	SearchResult<PartnerElite> findPartnerElitesByKeyWord(Long memberId, String status, String keyword, Long partnerId,
			String searchType, Date startTime, Date endTime, Pager pager) throws EliteServiceException;

	/**
	 * 邀请注册中 备案精英查询
	 */
	SearchResult<PartnerElite> findPartnerElitesByKeyWordAndInviteRegister(Long partnerId, String keyword,
			String status, Date startTime, Date endTime, Pager pager) throws EliteServiceException;
    
	
	
	/**
	 * 根据parnterId 查询入住数，备案数
	 */
	Integer findPartnerEliteCountByPartnerId(Long partnerId, String type) throws EliteServiceException;
	
	/**
	 * 根据合作者的memberId 查询包括收益
	 * 
	 * @param memberId
	 * @param status
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<PartnerElite> findPartnerElitesByPartner(Long MemberId, PartnerElite_Status status, Pager pager)
			throws EliteServiceException;

	/**
	 * 根据PartnerId查询
	 * 
	 * @param MemberId
	 * @return
	 * @throws EliteServiceException
	 */
	List<PartnerElite> findPartnerEliteListByPartner(Long partnerId) throws EliteServiceException;

	/**
	 * 根据phone查询 判断手机号是否存在
	 * 
	 * @param MemberId
	 * @return
	 * @throws EliteServiceException
	 */
	PartnerElite findPartnerEliteListByPhone(String phone) throws EliteServiceException;

	/**
	 * 查询渠道本身收益列表
	 * 
	 * @param partnerId
	 * @param status
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<PartnerElite> findPartnerEliteInComeList(Long partnerMemberId,Long partnerId, Long memberId,String incomeType,String status, Date startTime, Date endTime,
			Pager pager) throws EliteServiceException;
	/**
	 * 查询渠道直接收益列表
	 * 
	 * @param partnerId
	 * @param status
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<MemberPartnerElite> findPartnerEliteDirectInComeList(Long partnerId,Long memberId, String status,String incomeType, Date startTime, Date endTime,
			Pager pager) throws EliteServiceException;
	
	/**
	 * 根据会员的收益记录 时间段查询数量
	 * @param memberId
	 */
	Integer findPartnerEliteCountByIncomeDataAndPartnerId(Long memberId,Long partnerId,Date startTime,Date endTime,String IncomeType) throws EliteServiceException;

    /**
     * 更新状态sql语句
     */
    void updatePartnerEliteStatusBysql(String status,Long id)throws EliteServiceException;
}

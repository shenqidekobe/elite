package com.ledao.elite.core.service.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 合作伙伴猎头方服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 */
public interface MemberPartnerEliteService {

	/**
	 * 创建人才推荐方资料
	 * 
	 * @param obj
	 */
	MemberPartnerElite createMemberPartnerElite(MemberPartnerElite obj) throws EliteServiceException;

	/**
	 * 人才推荐方注册-基本信息注册
	 * 
	 * @param memberCredit
	 * @param elite
	 * @throws EliteServiceException
	 */
	void createMemberBasicInfo(String type, MemberBasic basic) throws EliteServiceException;

	/**
	 * 人才推荐方注册-上传认证信息
	 * 
	 * @param memberCredit
	 * @param elite
	 * @throws EliteServiceException
	 */
	void createMemberCredit(MemberCredit memberCredit, MemberCredit obj) throws EliteServiceException;

	/**
	 * 人才推荐方 _账户安全行业信息注册
	 */
	void createMemberPartnerEliteIndustry(Long memberId, MemberPartnerElite elite, MemberBasic basic)
			throws EliteServiceException;

	/**
	 * 修改基本信息，修改属性数量不固定
	 * 
	 * @param elite
	 * @throws EliteServiceException
	 */
	void updateEliteInfoNoFixed(MemberPartnerElite elite) throws EliteServiceException;
	
	/**
	 * 更新渠道方的结算数据
	 * 
	 * */
	void updateMemberPartnerEliteAsCalculate(Long id,Integer taskCount,BigDecimal taskTotalAmount,Integer calculateEliteCount,
			Integer calculateTaskCount,BigDecimal calculateTaskTotalAmount,Date startOrderTime,Date lastOrderTime)throws EliteServiceException;
	
	/**
	 * 周期时间到，清空渠道方的结算数据
	 * */
	void updateMemberPartnerEliteClearCalculate(Long id,Date clearCalculateTime)throws EliteServiceException;

	/**
	 * 修改入驻数量
	 * 
	 * @param id
	 * @param putCount
	 * @param enterCount
	 * @param calculateEliteCount
	 * @throws EliteServiceException
	 */
	void updateEliteInfoCount(Long id, Integer putCount, Integer enterCount,Integer calculateEliteCount) throws EliteServiceException;

	/**
	 * 根据会员ID查询资料
	 * 
	 * @param memberId
	 */
	MemberPartnerElite findMemberPartnerEliteByMemberId(Long memberId) throws EliteServiceException;

	/**
	 * 根据ID查询项目方资料
	 * 
	 * @param memberId
	 */
	MemberPartnerElite findMemberPartnerEliteById(Long id) throws EliteServiceException;

	/**
	 * 冠军榜单分页查询
	 */
	SearchResult<MemberPartnerElite> findMemberPartnerEliteListByPartnerId(Long partnerId, Date startTime, Date endTime,
			String status, Pager pager) throws EliteServiceException;
	
	/**
	 * 查询渠道方列表
	 * */
	List<MemberPartnerElite> findMemberPartnerEliteListByStatus(Member_Status status) throws EliteServiceException;

	/**
	 * 查询在团队中的排名
	 */
	Integer findMemberPartnerEliteIndex(Long partnerId, Long memberId, Date StartTime, Date endTime, String status);

	/**
	 * 冠亚季军查询
	 * 
	 * @param partnerId
	 * @param topnum
	 * @throws EliteServiceException
	 */
	List<MemberPartnerElite> findMemberPartnerEliteListTopThree(Long parentId, Date startTime, String status,
			Date endTime) throws EliteServiceException;

	/**
	 * 更新基本信息
	 * 
	 * @param memberId
	 * @return
	 */
	MemberPartnerElite updateMemberPartnerEliteInfo(Long id, MemberPartnerElite elite) throws EliteServiceException;

	/**
	 * 人才推荐方 _账户安全 修改基本信息
	 */
	void updateMemberPartnerEliteBasicInfo(Long memberId, String nickName, MemberBasic basic, MemberPartnerElite elite,
			MemberCredit credit) throws EliteServiceException;

	/**
	 * 根据会员ID修改项目方资料
	 * 
	 * @param memberId
	 * @param obj
	 */
	MemberPartnerElite updateMemberPartnerElite(Long memberId, MemberPartnerElite obj) throws EliteServiceException;

	/**
	 * 修改项目方状态
	 * 
	 * @param memberId
	 * @param status
	 */
	MemberPartnerElite updateMemberPartnerEliteStatus(Long memberId, String status) throws EliteServiceException;

	/**
	 * 审核资料
	 * 
	 * @param memberId
	 * @param auditId
	 * @param auditReason
	 * @param status
	 */
	MemberPartnerElite updateMemberPartnerEliteAudit(MemberPartnerElite elite) throws EliteServiceException;
	
	/**
	 * 查找当前渠道的直接渠道推荐的精英数量,任务数量，做单金额
	 * 
	 * @param id
	 * */
	Map<String,Object> findPartnerEliteDirectCount(Long id,Date startTime,Date endTime)throws EliteServiceException;
	
	/**
	 * 查找当前渠道的间接渠道推荐的精英数量,任务数量，做单金额
	 * 
	 * @param id
	 * */
	Map<String,Object> findPartnerEliteInDirectCount(Long id,Date startTime,Date endTime)throws EliteServiceException;
	
	/**
	 * 更新partnerId
	 */
	void updateMemberPartnerElitePartnerId(Long partnerId,Long id)throws EliteServiceException;
	
}

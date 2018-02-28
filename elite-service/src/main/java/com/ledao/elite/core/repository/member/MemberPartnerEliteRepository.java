package com.ledao.elite.core.repository.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 合作伙伴 人才方接口
 */
public interface MemberPartnerEliteRepository extends GenericDAO<MemberPartnerElite, Long> {

	/**
	 * 更新渠道方入驻人才数量
	 */
	void updateEliteInfoCount(Long id, Integer putCount, Integer enterCount, Integer calculateEliteCount);
	
	/**
	 * 更新渠道方结算数据
	 * */
	void updateMemberPartnerEliteAsCalculate(Long id,Integer taskCount,BigDecimal taskTotalAmount,Integer calculateEliteCount,
			Integer calculateTaskCount,BigDecimal calculateTaskTotalAmount,Date startOrderTime,Date lastOrderTime);
	
	/**
	 * 周期时间到清空结算数据
	 * */
	void updateMemberPartnerEliteClearCalculate(Long id,Date clearCalculateTime);

	/**
	 * 分页冠军榜单查询
	 */
	List<MemberPartnerElite> findMemberPartnerEliteList(Long partnerId, String status, Date startTime, Date endTime,
			Pager pager);

	/**
	 * 冠军榜单查询数量
	 */
	Integer findMemberPartnerEliteListCount(Long partnerId, String status, Date startTime, Date endTime);

	/**
	 * 查询备案数量
	 */
	Integer findMemberPartnerPutCount(Long partnerId, Date startTime, Date endTime);

	/**
	 * 冠军榜单查询
	 */
	List<MemberPartnerElite> findMemberPartnerEliteList(Long partnerId, String status, Date startTime, Date endTime);

	/**
	 * 渠道管理 分页查询渠道方
	 */
	List<MemberPartnerElite> findMemberPartnerEliteListBySearchType(Long parentId, String keyword, String searchType,
			Date startTime, Date endTime, Date beforDays, Pager pager);

	/**
	 * 渠道管理 查询数量
	 */
	Integer findMemberPartnerEliteListBySearchTypeCount(Long parentId, String keyword, String searchType,
			Date startTime, Date endTime, Date beforDays);

	/**
	 * 查询直接渠道
	 */
	List<MemberPartnerElite> findMemberPartnerEliteDirectList(Long partnerId, String status, String searchType,
			Date startTime, Date endTime, Pager pager);

	/**
	 * 
	 * 查询直接渠道数量
	 */
	public Integer findMemberPartnerEliteListDirectCount(Long partnerId, String status, String searchType,
			Date startTime, Date endTime);

	/**
	 * 查询间接间接渠道
	 */
	List<MemberPartnerElite> findMemberPartnerEliteInDirectList(Long memberId, String status, String searchType,
			Date startTime, Date endTime, Pager pager);

	/**
	 * 
	 * 查询间接间接渠道数量
	 */
	public Integer findMemberPartnerEliteListInDirectCount(Long memberId, String status, String searchType,
			Date startTime, Date endTime);

	/**
	 * update sql
	 */
	void updateMemberPartnerElitePartnerIdBysql(Long partnerId, Long id);
}

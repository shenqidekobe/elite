package com.ledao.elite.core.repository.partner;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 猎头推荐的精英接口
 */
public interface PartnerEliteRepository extends GenericDAO<PartnerElite, Long> {

	/**
	 * 根据条件分页查询
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 * @param status
	 *            0:已备案没注册
	 * @param pager
	 * @return
	 */
	SearchResult<PartnerElite> findPartnerListByNameAndPhoneAndEmailAndStatus(Long partnerId, String name, String phone,
			String email, String status, Pager pager);

	/**
	 * 根据keyword(精英名，手机号，角色)模糊查询 备案时间联合查询
	 */
	List<PartnerElite> findPartnerEliteByKeyword(Long memberId, String status, String keyword, Long partnerId,
			String recorded, Date startTime, Date endTime, Pager pager);

	/**
	 * 根据keyword(精英名，手机号，角色)模糊查询 备案时间联合查询,状态
	 */
	Integer findPartnerEliteByKeywordCount(Long memberId, String status, String keyword, Long partnerId,
			String recorded, Date startTime, Date endTime, Pager pager);

	/**
	 * 根据keyword(精英名，手机号，角色)模糊查询 备案时间联合查询
	 */
	List<PartnerElite> findPartnerEliteListByKeywordAndStatus(Long partnerId, String status, String keyword,
			Date startTime, Date endTime, Pager pager);

	/**
	 * 根据keyword(精英名，手机号，角色)模糊查询 备案时间联合查询,状态
	 */
	Integer findPartnerEliteListByKeywordAndStatusCount(Long partnerId, String status, String keyword, Date startTime,
			Date endTime);

	/**
	 * 收益列表
	 */
	List<PartnerElite> findPartnerEliteInComeList(Long partnerId,String incomeType, String status, Date startTime, Date endTime,
			Pager pager);

	/**
	 * 收益类表数量
	 */
	Integer findMemberPartnerEliteListInComeCount(Long partnerId, String incomeType,String status, Date startTime, Date endTime);

	/**
	 * 直接收益列表
	 */
	List<PartnerElite> findPartnerEliteDirectInComeList(Long partnerId, String status, Date startTime, Date endTime,
			Pager pager);

	/**
	 * 直接收益类表数量
	 */
	Integer findMemberPartnerEliteListDirectInComeCount(Long partnerId, String status, Date startTime, Date endTime);

	/**
	 * 间接收益列表
	 */
	List<PartnerElite> findPartnerEliteInDirectInComeList(Long partnerId, String status, Date startTime, Date endTime,
			Pager pager);

	/**
	 * 间接收益类表数量
	 */
	Integer findMemberPartnerEliteListInDirectInComeCount(Long partnerId, String status, Date startTime, Date endTime);
   
	/**
	 * 根据收益记录时间段查询间接，直接 备案精英数量
	 */
	Integer findPartnerEliteCountByIncomeDataAndPartnerId(Long partnerId,Long memberId, String incomeType, Date startTime, Date endTime);
	
	/**
	 * 通过sql语句更新状态
	 * @param status
	 * @param id
	 */
	 void updatePartnerEliteIdBysql(String status, Long id);
}

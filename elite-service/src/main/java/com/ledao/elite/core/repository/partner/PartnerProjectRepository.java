package com.ledao.elite.core.repository.partner;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.partner.PartnerProject;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 猎头推荐的项目接口
 */
public interface PartnerProjectRepository extends GenericDAO<PartnerProject, Long> {

	/**
	 * 分页查询项目渠道
	 * 
	 * @param partnerId
	 * @param name
	 * @param phone
	 * @param email
	 * @param status
	 * @param pager
	 * @return
	 */
	SearchResult<PartnerProject> findPartnerProjectListByNameAndPhoneAndEmailAndStatus(Long partnerId, String name,
			String phone, String email, String status, Pager pager);

	/**
	 * 根据keyword(项目名，项目联系人，手机号)模糊查询
	 */
	List<PartnerProject> findPartnerProjectByKeyword(Long memberId, String status, String keyword, Long partnerId,
			Pager pager);

	/**
	 * 根据keyword(项目名，项目联系人，手机号)模糊查询个数
	 */
	Integer findPartnerProjectByKeywordCount(Long memberId, String status, String keyword, Long partnerId);

	/**
	 * 根据partnerId 查询备案项目数 ，个人入住数
	 */
	Integer findPartnerProjectByPartnerIdCount(Long partnerId, String type);

	/**
	 * 根据状态 城市 查询 ，联系人 手机号模糊查询
	 * 
	 * @param partnerId
	 * @param areaName
	 * @param keyword
	 *            联系人 手机号模糊查询
	 * @param entryed
	 *            (true 入住)
	 * @return
	 */
	List<PartnerProject> findPartnerProjectsByStatusAndKeyWorld(long partnerId, String areaName, String keyword,
			String entryed, String status,Date startTime,Date endTime,Pager pager);

	/**
	 * 根据状态 城市 查询 ，联系人 手机号模糊查询 数量
	 * 
	 * @param partnerId
	 * @param areaName
	 * @param keyword
	 * @param entryed
	 * @param status
	 * @param pager
	 * @return
	 */
	Integer findPartnerProjectsByStatusAndKeyWorldCount(long partnerId, String areaName, String keyword, String entryed,
			String status,Date startTime,Date endTime);
	/**
	 * 根据注册时间查询数量
	 * @param partnerId
	 * @param status
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	 Integer findPartnerProjectByKeywordAndTimeCount(Long partnerId, String status,Date startTime,Date endTime);
	 
	 
	 /**
	  *更新状态sql
	  */
	 void updatePartnerProjectStatusBySql(String status,Long id);
}



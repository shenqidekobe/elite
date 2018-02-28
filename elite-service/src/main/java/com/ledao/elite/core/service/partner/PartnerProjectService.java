package com.ledao.elite.core.service.partner;

import java.util.Date;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.partner.PartnerProject;
import com.ledao.elite.core.domain.partner.PartnerProject.PartnerProject_Status;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 合作伙伴项目方服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 */
public interface PartnerProjectService {

	/**
	 * 合作伙伴推荐一个项目
	 * 
	 * @param partnerProject
	 * @return partnerProject
	 */
	PartnerProject createPartnerProject(PartnerProject obj) throws EliteServiceException;

	/**
	 * 项目方发布项目 注入一条记录
	 * 
	 * @param project
	 * @param partnerId
	 * @param account
	 * @return
	 * @throws EliteServiceException
	 */
	PartnerProject createPartnerProjectByMemberCompany(Project project, Long partnerId, String account)
			throws EliteServiceException;

	/**
	 * 更新
	 */
	PartnerProject updatePartnerProject(PartnerProject obj) throws EliteServiceException;

	/**
	 * 根据发布项目填写的推荐帐号查询
	 * 
	 * @param account
	 * @return partnerProject
	 */
	PartnerProject findPartnerProjectByAccount(String account) throws EliteServiceException;

	/**
	 * 根据发布项目填写的帐号进行查询
	 * 
	 * @param account
	 * @param recommdUser
	 * @param contactPhone
	 * @param partnerId:渠道方ID
	 * @return partnerProject
	 */
	PartnerProject findPartnerProjectAsEnter(String account, String recommdUser, String contactPhone, Long partnerId)
			throws EliteServiceException;

	/**
	 * 根据ID查询项目渠道详情
	 * 
	 * @param memberId
	 * @return
	 * @throws EliteServiceException
	 */
	PartnerProject findPartnerProjectById(Long id) throws EliteServiceException;

	/**
	 * 分页查询
	 * 
	 * @param partnerId
	 * @param name
	 * @param phone
	 * @param email
	 * @param status
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<PartnerProject> findPartnerProjectList(Long partnerId, String name, String phone, String email,
			String status, Pager pager) throws EliteServiceException;

	/**
	 * 根据关键字（项目名,项目联系人，手机号)模糊查询
	 */
	SearchResult<PartnerProject> findPartnerProjectListByKeyWord(Long memberId, Long partnerId, String keyword,
			Pager Pager) throws EliteServiceException;

	/**
	 * 根据parnterId 查询入住数，备案数
	 */
	Integer findPartnerProjectCountByPartnerId(Long partnerId, String type) throws EliteServiceException;

	/**
	 * 项目推荐方的项目备案分页
	 * 
	 * @param partnerId
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<PartnerProject> findPartnerProjectList(Long partnerId, Pager pager) throws EliteServiceException;

	/**
	 * 渠道方根据合作伙伴Id 城市，查询
	 * 
	 * @param partnerId
	 * @param areaName
	 * @param keyword
	 *            名称，手机号码
	 * @param entryed
	 *            true:入住
	 * @param status
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<PartnerProject> findPartnerProjectsByStatusAndKeyWorld(Long partnerId, String areaName, String keyword,
			String entryed, String status, Date startTime, Date endTime, Pager pager) throws EliteServiceException;

	/**
	 * 根据合作者的memberId 查询收益
	 * 
	 * @param memberId
	 * @param status
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<PartnerProject> findPartnerCompanyByPartner(Long MemberId, PartnerProject_Status status, Pager pager)
			throws EliteServiceException;

	/**
	 * 根据phone查询 判断手机号是否存在
	 * 
	 * @param MemberId
	 * @return
	 * @throws EliteServiceException
	 */
	boolean findPartnerCompanyPhoneExisted(String phone) throws EliteServiceException;

	/**
	 * 查询list中首个
	 */
	PartnerProject findPartnerCompanyListFirstByPhone(String phone) throws EliteServiceException;

	/**
	 * 查询渠道方直接备案数据
	 */
	PartnerProject findPartnerCompanyByPartnerPut(Long partnerId, String account) throws EliteServiceException;

	/**
	 * 更新状态sql
	 */
	void updatePartnerCompanyStatuaBysql(String status,long id)throws EliteServiceException;
}

package com.ledao.elite.core.service.member;


import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberRecommend;
import com.ledao.elite.core.domain.member.MemberRecommend.MemberRecommend_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员推荐服务接口
 * @author chenghao
 *
 */
public interface MemberRecommendService {
	
	/**
	 * 推荐的项目或任务
	 * @param obj
	 * @return
	 * @throws EliteServiceException
	 */
	MemberRecommend createMemberRecommend(MemberRecommend obj)throws EliteServiceException;
	
	/**
	 * 
	 * @param memberIds
	 * @param recommemberId
	 * @param type
	 * @param projectId
	 */
	void createMemberRecommends(Long[] memberIds,Long recommemberId,MemberRecommend_Type type,Long projectId)throws EliteServiceException;
	
	/**
	 * 根据该会员ID查询推荐的项目列表
	 * @param id
	 */
	SearchResult<MemberRecommend> findRecommendProjectList(Long memberId,Pager pager)throws EliteServiceException;
	
	/**
	 * 根据该会员ID查询推荐的任务列表
	 * @param id
	 */
	SearchResult<MemberRecommend> findRecommendTaskList(Long memberId,Pager pager)throws EliteServiceException;
	
	/**
	 * 根据该会员ID查询推荐的项目列表
	 * @param id
	 */
	Integer findRecommendProjectCount(Long memberId)throws EliteServiceException;
	
	/**
	 * 根据项目ID和用户ID查询推荐项目详情
	 * @param memberId
	 * @param project
	 * @return
	 * @throws EliteServiceException
	 */
	MemberRecommend findRecommendProject(Long memberId,Long projectId)throws EliteServiceException;
	/**
	 * 根据项目ID和用户ID 查询是否存在
	 * @param memberId
	 * @param project
	 * @return
	 * @throws EliteServiceException
	 */
	MemberRecommend findRecommendProjectByMemberIdAndProjectId(Long memberId,Long projectId)throws EliteServiceException;
	
	/**
	 * 根据该会员ID查询推荐的任务列表
	 * @param id
	 */
	Integer findRecommendTaskCount(Long memberId)throws EliteServiceException;
	
	/**
	 * 根据项目和任务ID查询推荐项目详情
	 * @param memberId
	 * @param project
	 * @return
	 * @throws EliteServiceException
	 */
	MemberRecommend findRecommendTask(Long memberId,Long taskId)throws EliteServiceException;


}

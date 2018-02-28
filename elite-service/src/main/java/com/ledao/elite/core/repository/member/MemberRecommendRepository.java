package com.ledao.elite.core.repository.member;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberRecommend;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 推荐给会员的任务、项目等接口
 * 
 * @author kobe.liu
 * */
public interface MemberRecommendRepository extends GenericDAO<MemberRecommend, Long> {
	
	/**
	 * 会员推荐项目列表
	 * @param memberId
	 * @param pager
	 * @return
	 */
	List<MemberRecommend> findRecommendProjectList(Long memberId,Pager pager);
	
	
	/**
	 * 会员推荐项目数量
	 * @param memberId
	 * @param pager
	 * @return
	 */
	Integer findRecommendProjectCount(Long memberId);
	
	/**
	 * 会员推荐任务列表
	 * @param memberId
	 * @param pager
	 * @return
	 */
	List<MemberRecommend> findRecommendTaskList(Long memberId,Pager pager);
	
	
	/**
	 * 会员推荐任务数量
	 * @param memberId
	 * @param pager
	 * @return
	 */
	Integer findRecommendTaskCount(Long memberId);
	
	/**
	 * 根据项目ID和用户ID查询推荐项目详情
	 * @param memberId
	 * @param project
	 * @return
	 * @throws EliteServiceException
	 */
	MemberRecommend findRecommendProject(Long memberId,Long projectId);
	
	/**
	 * 根据任务ID和用户ID查询推荐项目详情
	 * @param memberId
	 * @param project
	 * @return
	 * @throws EliteServiceException
	 */
	MemberRecommend findRecommendTask(Long memberId,Long taskId);
	
}

package com.ledao.elite.core.service.project;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit.ProjectTaskRecruit_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 任务招募服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectTaskRecruitService {
	
	
	/**
	 * 新增一条任务招募记录
	 * 
	 * @param projectTaskRecruit
	 * @return projectTaskRecruit
	 * */
	ProjectTaskRecruit createProjectTaskRecruit(ProjectTaskRecruit obj)throws EliteServiceException;
	
	/**
	 * 更新任务招募状态
	 * 
	 * @param id
	 * @param status
	 * */
	ProjectTaskRecruit updateProjectTaskRecruitStatus(Long id,String status)throws EliteServiceException;
	
	
	/**
	 * 指定精英更新状态
	 * 
	 * @param id
	 * @param status
	 * */
	void updateProjectTaskRecruitStatus(Long memberId,Long taskId)throws EliteServiceException;
	
	/**
	 * 按ID查询
	 * 
	 * @param id
	 * @return projectTaskRecruit
	 * */
	ProjectTaskRecruit findProjectTaskRecruitById(Long id)throws EliteServiceException;
	
	
	
	/**
	 * 按taskId、memberID查询
	 * 
	 * @return projectTaskRecruit
	 * */
	ProjectTaskRecruit findProjectTaskRecruitByTaskIdAndMemberId(Long memberId,Long taskId,String status)throws EliteServiceException;
	
	
	
	/** 
	 * 查询任务招募列表
	 * 
	 * @param taskId
	 * */
	SearchResult<ProjectTaskRecruit> findProjectTaskRecruitList(Long memberId,Long taskId,String status,Pager pager)throws EliteServiceException;

	/**
	 * 查询该精英认领的任务
	 * @param memberId
	 * @param taskId
	 * @return
	 */
	ProjectTaskRecruit findProjectTaskRecruit(Long memberId,Long taskId)throws EliteServiceException;
	
	/**
	 * CTO指定精英时操作
	 * @param obj
	 * @throws EliteServiceException
	 */
	void updateProjectTask(ProjectTaskRecruit obj)throws EliteServiceException;
	
	/**
	 * 任务认领的数量
	 * 
	 * @param taskId
	 * @return int
	 * */
	Integer findProjectTaskApplyCountByTaskId(Long taskId)throws EliteServiceException;
	
	/**
	 * 认领该任务精英
	 * @param taskId
	 * @return
	 * @throws EliteServiceException
	 */
	List<ProjectTaskRecruit> findProjectTaskRecruitByTaskId(Long taskId)throws EliteServiceException;
	
	/**
	 *  该精英认领的任务统计
	 * @param taskId
	 * @return
	 * @throws EliteServiceException
	 */
	Integer findProjectTaskRecruitCount(Long memberId,ProjectTaskRecruit_Status status)throws EliteServiceException;
	
	/**
	 *  精英合作任务的统计
	 * @param taskId
	 * @return
	 * @throws EliteServiceException
	 */
	Integer findTaskRecruitCount(Long taskId)throws EliteServiceException;
	
	/**
	 * 认领该任务精英详情
	 * @return
	 */
	SearchResult<ProjectTaskRecruit> findProjectTaskRecruitByTaskId(Long memberId,Long taskId,Pager pager) throws EliteServiceException ;
   
	/**
	 * 精英最后一次接单时间
	 */
	Date findProjectTaskRecuritLastRecruitTime(long memberId)throws EliteServiceException ;
	
	/**
	 * 查询精英已认领的任务(包含认领未成功的)
	 * @param memberId
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<ProjectTaskRecruit> findClaimProjectTaskRecruitList(Long memberId,Pager pager)throws EliteServiceException;
	
	/**
	 * 查询精英已认领的任务数量(包含认领未成功的)
	 * @param memberId
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	Integer findClaimProjectTaskRecruitCount(Long memberId)throws EliteServiceException;
	
	/**
	 * 定时更新任务到时状态
	 */
	void updateExpireTask()throws EliteServiceException;
	
}

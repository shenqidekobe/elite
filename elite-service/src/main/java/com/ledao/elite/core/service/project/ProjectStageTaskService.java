package com.ledao.elite.core.service.project;

import java.math.BigDecimal;
import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目阶段任务服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectStageTaskService {
	
	/**
	 * 新建任务
	 * 
	 * @param projectStageTask
	 * @param isSend
	 * @param memberId
	 * @return projectStageTask
	 * */
	ProjectStageTask createProjectStageTask(ProjectStageTask obj,boolean isSend,Long memberId)throws EliteServiceException;
	
	/**
	 * 更新阶段任务
	 * 
	 * @param obj
	 * */
	void updateProjectStageTask(ProjectStageTask obj)throws EliteServiceException;
	
	/**
	 * 更新已经过招募时间的任务
	 * */
	void updateProjectStageTaskAsExpire()throws EliteServiceException;
	
	/**
	 * 更新已经过交付时间的任务
	 * */
	void updateApplyTaskAsExpire()throws EliteServiceException;
	
	/**
	 * 提醒该任务时
	 * 
	 * @param obj
	 * */
	void updateProjectStageTaskForRemind(ProjectStageTask obj)throws EliteServiceException;
	
	/**
	 * 修改状态
	 * 
	 * @param id
	 * @return projectStageTask
	 * */
	ProjectStageTask updateProjectStageTaskStatus(Long id,String status)throws EliteServiceException;	
	/**
	 * 按ID查询任务
	 * 
	 * @param id
	 * @return projectStageTask
	 * */
	ProjectStageTask findProjectStageTaskById(Long id)throws EliteServiceException;
	
	/**
	 * 用项目ID查询任务
	 * @param projectId
	 * @return List<ProjectStageTask>
	 */
	SearchResult<ProjectStageTask> findProjectStageTaskListByProjectId(Long projectId,Pager pager);
	
	/**
	 * 按结算ID查询任务列表
	 * @param stageId
	 * @return List<ProjectStageTask>
	 */
	List<ProjectStageTask> findProjectStageTask(Long stageId,Long projectId)throws EliteServiceException;
	
	/**
	 * 当前阶段发布的有效任务
	 * @param stageId
	 * @return List<ProjectStageTask>
	 */
	List<ProjectStageTask> findValidProjectStageTask(Long stageId)throws EliteServiceException;
	
	/**
	 * 按接收任务的精英会员ID
	 * @param stageId
	 * @return List<ProjectStageTask>
	 */
	List<ProjectStageTask> findProjectStageTaskByMemberId(Long MemberId)throws EliteServiceException;
	
	/**
	 * 按状态查询任务列表
	 * @param stageId
	 * @param status
	 * @return List<ProjectStageTask>
	 */
	List<ProjectStageTask> findProjectStageTaskByStatus(Long stageId,ProjectTask_Status status)throws EliteServiceException;
	
	/**
	 * 任务大厅列表查询
	 * 
	 * @param projectId:项目ID
	 * @param taskType:任务类型
	 * @param demandType:任务需求人才
	 * @param stageId:阶段ID
	 * @param status:任务状态
	 * @param pager
	 * @return SearchResult<ProjectStageTask>
	 * */
	SearchResult<ProjectStageTask> findProjectStageTaskList(Long projectId,String taskType,String demandType,BigDecimal minAmount,BigDecimal maxAmount,
			Long stageId,String status,Pager pager)throws EliteServiceException;

	/**
	 * 此项目的任务名是否存在
	 * @param projectId
	 * @param name
	 * @return
	 */
	boolean findProjectStageTaskByName(Long projectId,String name)throws EliteServiceException;
	
	/**
	 * 查询用户发的有效任务数量
	 * @param memberId
	 * */
	Integer findProjectStageTaskCount(Long memberId)throws EliteServiceException;
	
	
}
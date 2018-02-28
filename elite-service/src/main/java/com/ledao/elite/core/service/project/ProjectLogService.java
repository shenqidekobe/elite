package com.ledao.elite.core.service.project;

import java.util.Date;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectLog;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目日志服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectLogService {

	
	/**
	 * 创建项目日志
	 * 
	 * @param projectLog
	 * @return projectLog
	 * */
	ProjectLog createProjectLog(ProjectLog obj)throws EliteServiceException;
	
	/**
	 * 查看项目日志详情
	 * 
	 * @param id
	 * @return projectLog
	 * */
	ProjectLog findProjectLogById(Long id)throws EliteServiceException;
	
	/**
	 * 查询项目日志数量
	 * 
	 * @param projectId
	 * @param type
	 * @return int
	 * */
	Integer findProjectLogCount(Long projectId,ProjectLog_Type type)throws EliteServiceException;
	
	
	/**
	 * 查询该任务日志数量
	 * 
	 * @param taskId
	 * @return int
	 * */
	Integer findTaskLogCount(Long taskId,Long memberId)throws EliteServiceException;
	
	/**
	 * 项目日志列表查询
	 * 
	 * @param projectId:项目ID
	 * @param stageCode:项目阶段
	 * @param keyword:关键词
	 * @param starTime:开始时间
	 * @param endTime:截止时间
	 * @param pager:分页
	 * 
	 * @return SearchResult<ProjectLog>
	 * */
	SearchResult<ProjectLog> findProjectLogList(Long projectId,Long memberId,String stageCode,String keyword,Date starTime,Date endTime,Pager pager)throws EliteServiceException;
	
	/**
	 * 项目任务日志列表查询
	 * 
	 * @param taskId:任务ID
	 * @param stageCode:项目阶段
	 * @param keyword:关键词
	 * @param starTime:开始时间
	 * @param endTime:截止时间
	 * @param pager:分页
	 * 
	 * @return SearchResult<ProjectLog>
	 * */
	SearchResult<ProjectLog> findTaskLogList(Long taskId,Long memberId,String stageCode,String keyword,Date starTime,Date endTime,Pager pager)throws EliteServiceException;
}

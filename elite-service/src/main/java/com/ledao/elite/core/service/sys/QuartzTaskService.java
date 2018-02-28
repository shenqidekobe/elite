package com.ledao.elite.core.service.sys;

import java.util.List;

import com.ledao.elite.core.domain.sys.QuartzTask;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Status;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Type;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Way;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 定时任务服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface QuartzTaskService {

	/**
	 * 创建定时任务
	 * */
	QuartzTask createQuartzTask(Long dataId,QuartzTask_Type type,QuartzTask_Way way,String taskExperess,Integer intervalMinute,String taskParams)throws EliteServiceException;
	
	/**
	 * 更新任务
	 * */
	QuartzTask updateQuartzTask(QuartzTask obj)throws EliteServiceException;
	
	/**
	 * 根据dataId更新指定状态
	 * 
	 * @param dataId
	 * @param status
	 * */
	QuartzTask updateQuartzTask(Long dataId,QuartzTask_Status status)throws EliteServiceException;
	
	/**
	 * 按ID查询任务
	 * */
	QuartzTask findQuartzTaskById(Long id)throws EliteServiceException;
	
	/**
	 * 按dataId查询非结束的任务
	 * */
	QuartzTask findQuartzTaskByDataId(Long dataId)throws EliteServiceException;
	
	/**
	 * 查询定时任务列表
	 * */
	List<QuartzTask> findQuartzTaskList(Long dataId,QuartzTask_Status status)throws EliteServiceException;
	
	/**
	 * 查询定时任务列表
	 * */
	List<QuartzTask> findQuartzTaskListByStatus(QuartzTask_Status status)throws EliteServiceException;
}

package com.ledao.elite.core.repository.project;

import java.math.BigDecimal;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目阶段性的任务接口
 * */
public interface ProjectStageTaskRepository extends GenericDAO<ProjectStageTask, Long>{
	
	/**
	 * 任务大厅列表查询
	 * */
	List<ProjectStageTask> queryProjectStageTaskHallList(Long projectId,String taskType,String demandType,BigDecimal minAmount,BigDecimal maxAmount,
			Long stageId,String status,Pager pager);
	
	/**
	 * 任务大厅数量查询
	 * */
	Integer queryProjectStageTaskHallCount(Long  projectId,String taskType,String demandType,BigDecimal minAmount,BigDecimal maxAmount,
			Long stageId,String status);
	
	/**
	 * 更新过期的任务
	 */
	void updateExpireTask();

}

package com.ledao.elite.core.repository.project;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.project.ProjectTaskRecruit;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目的阶段性的任务招募关系接口
 * */
public interface ProjectTaskRecruitRepository extends GenericDAO<ProjectTaskRecruit, Long>{
	
	/**
	 * 批量更新任务状态
	 * @return
	 */
	void updateProjectStageTaskByTaskId(Long taskId,String status );
	/**
	 * 任务大厅列表查询
	 * */
	List<ProjectTaskRecruit> queryProjectStageTaskHallList(Long memberId,Long taskId,String status,Pager pager);
	
	/**
	 * 任务大厅数量查询
	 * */
	Integer queryProjectStageTaskHallCount(Long memberId,Long taskId,String status);
	
	/**
	 * 精英的报名任务对象
	 * */
	ProjectTaskRecruit findProjectTaskRecruitByTaskIdAndMemberId(Long memberId,Long taskId,String status);
	
	/**
	 * 该精英的任务数量
	 * @param memberId
	 * @return
	 */
	Integer findProjectTaskCount(Long memberId,String status);
	
	
	/**
	 * 该精英的进行中的任务
	 * @param memberId
	 * @return
	 */
	Integer memberProjectTaskCount(Long memberId);
		
	/**
	 * 定时更新任务结束的
	 */
	void updateExpireRecruitTask(); 
}

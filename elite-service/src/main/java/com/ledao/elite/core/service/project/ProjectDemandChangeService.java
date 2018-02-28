package com.ledao.elite.core.service.project;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectDemandChange;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目需求变更服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectDemandChangeService {

	
	/**
	 * 发起一个新的需求变更
	 * 
	 * @param projectDemandChange
	 * @return projectDemandChange
	 * */
	ProjectDemandChange createProjectDemandChange(ProjectDemandChange obj)throws EliteServiceException;
	
	/**
	 * 更新需求变更的状态
	 * 
	 * @param projectDemandChange
	 * @return projectDemandChange
	 * */
	ProjectDemandChange updateProjectDemandChangeStatue(ProjectDemandChange obj)throws EliteServiceException;
	
	/**
	 * 按ID需求变更查询
	 * 
	 * @param id
	 * @return projectDemandChange
	 * */
	ProjectDemandChange findProjectDemandChangeById(Long id)throws EliteServiceException;
	
	/**
	 * 需求变更列表查询
	 * 
	 * @param projectId
	 * @param pager
	 * @return SearchResult<ProjectDemandChange>
	 * */
	SearchResult<ProjectDemandChange> findProjectDemandChangeList(Long projectId,Pager pager)throws EliteServiceException;
}

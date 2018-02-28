package com.ledao.elite.core.service.project;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectDefer;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目延期服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectDeferService {
	
	/**
	 * 申请延期
	 * 
	 * @param projectDefer
	 * @return projectDefer
	 * */
	ProjectDefer createProjectDefer(ProjectDefer obj)throws EliteServiceException;
	
	/**
	 * 更新延期申请状态
	 * 
	 * @param projectDefer
	 * @return projectDefer
	 * */
	ProjectDefer updateProjectDeferStatus(ProjectDefer obj)throws EliteServiceException;
	
	/**
	 * 按ID查询
	 * 
	 * @param id
	 * @return projectDefer
	 * */
	ProjectDefer findProjectDeferById(Long id)throws EliteServiceException;
	
	/**
	 * 延期申请列表查询
	 * 
	 * @param projectId
	 * @param pager
	 * @return SearchResult<ProjectDefer>
	 * */
	SearchResult<ProjectDefer> findProjectDeferList(Long projectId,Pager pager)throws EliteServiceException;

}

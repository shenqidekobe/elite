package com.ledao.elite.core.service.project;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectBusiness;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 前期商务的项目需求沟通记录服务接口
 */
public interface ProjectBusinessService {

	/**
	 * 创建一条沟通记录
	 * 
	 * @param ProjectBusiness
	 * @return ProjectBusiness
	 */
	ProjectBusiness createProjectBusiness(ProjectBusiness obj) throws EliteServiceException;

	/**
	 * 修改沟通记录
	 * 
	 * @param ProjectBusiness
	 * @return ProjectBusiness
	 */
	ProjectBusiness updateProjectBusiness(ProjectBusiness obj) throws EliteServiceException;

	/**
	 * 按ID查询沟通记录
	 * 
	 * @param id
	 * @return ProjectBusiness
	 */
	ProjectBusiness findProjectBusinessById(Long id) throws EliteServiceException;

	/**
	 * 查询项目的沟通记录列表
	 * 
	 * @param projectId
	 * @param userId
	 */
	SearchResult<ProjectBusiness> findProjectBusinessByProject(Long projectId, Long userId, Pager pager)
			throws EliteServiceException;

	/**
	 * 查询项目的沟通记录
	 */
	List<ProjectBusiness> findProjectBusinessListByProject(Long projectId, Long userId) throws EliteServiceException;

	/**
	 * 逻辑删除沟通记录
	 */
	ProjectBusiness removeLogicById(Long id) throws EliteServiceException;
}

package com.ledao.elite.core.service.project;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目招标书服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectTenderService {
	
	/**
	 * 发招标书
	 * 
	 * @param projectTender
	 * @return projectTender
	 * */
	ProjectTender createProjectTender(ProjectTender pojo)throws EliteServiceException;
	
	/**
	 * 创建招标书，项目阶段
	 */
	void createProjectTenderAndDefineStage(ProjectTender obj,List<ProjectDefineStage>defineStages)throws EliteServiceException;
	
    /**
     * 更新招标书
     * 
     * @param id
     * @param tender
     */
	void updateProjectTender(Long id,ProjectTender tender)throws EliteServiceException;
	
    /**
     * 更新
     * 
     * @param tender
     */
	void updateProjectTender(ProjectTender tender)throws EliteServiceException;
	
	/**
	 * 更新已过期的项目招标书
	 * */
	void updateProjectTenderAsExpire()throws EliteServiceException;
	
    /**
     * 查找最新标书
     * 
     * @param projectId
     */
	ProjectTender findProjectTenderByProjectId(Long projectId)throws EliteServiceException;
	
	/**
	 * 查询招标书内容
	 * 
	 * @param id
	 * @return projectTender
	 * */
	ProjectTender findProjectTenderById(Long id)throws EliteServiceException;
	
	
	/**
	 * 分页查询标书
	 * @param ProjectId
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<ProjectTender>findProjectTenders(Long ProjectId,Pager pager)throws EliteServiceException;
	
	
	/**
	 * 分页查询标书 cto投标具体内容
	 * @param ProjectId
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<ProjectTender>findProjectTendersDetail(Long projectId,Pager pager)throws EliteServiceException;

	/**
	 * 获取cto的项目
	 * （避免防止循环注入，写在这里）
	 */
	 List<Project>findProjectListByctoId(Long ctoId)throws EliteServiceException;
     
	 /**
	  * 取消招标书
	  * 
	  */
	 List<ProjectTender>updateRemoveProjectTender(ProjectTender tender)throws EliteServiceException;
}

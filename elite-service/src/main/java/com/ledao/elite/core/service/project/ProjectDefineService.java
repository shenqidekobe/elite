package com.ledao.elite.core.service.project;

import java.util.List;

import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Status;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 项目立项书服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectDefineService {

	/**
	 * 发布项目立项书
	 * 
	 * @param projectDefine
	 * @return projectDefine
	 * */
	ProjectDefine createProjectDefine(ProjectDefine obj)throws EliteServiceException;
	
	/**
     * 生成立项书 包含项目阶段
     */
    void createProjectDefinesAndDefinesStages(ProjectDefine define,List<ProjectDefineStage> defineStages);
	
	/**
	 * 更新项目立项书
	 * 
	 * @param projectDefine
	 * @return projectDefine
	 * */
	ProjectDefine updateProjectDefine(ProjectDefine obj)throws EliteServiceException;
	/**
	 * 取消立项书
	 * 
	 * @param projectDefine
	 * @return projectDefine
	 * */
	ProjectDefine updateProjectDefineCancelByProjectId(Long projectId,ProjectDefine_Type defineType)throws EliteServiceException;
	
	/**
	 * 按ID查询
	 * 
	 * @param id
	 * @return projectDefine
	 * */
	ProjectDefine findProjectDefineById(Long id)throws EliteServiceException;
	
	/**
	 * 查询项目的立项书{按类型和状态}
	 * 
	 * @param projectId
	 * @param type
	 * @param status
	 * @return projectDefine
	 * */
	ProjectDefine findProjectDefine(Long projectId,ProjectDefine_Type type,ProjectDefine_Status status)throws EliteServiceException;
	
	/**
	 * 项目立项阶段查询
	 * 
	 * @param projectId
	 * @param stageId
	 * @return projectDefineStage
	 * */
	ProjectDefineStage findProjectDefineStageByProjectIdAndStageId(Long projectId,Long stageId)throws EliteServiceException;
	
	/**
	 * 更新立项阶段
	 * 
	 * @param projectDefineStage
	 * */
	void updateProjectDefineStage(ProjectDefineStage obj)throws EliteServiceException;
	
	
	/**
	 * 根据项目id查询
	 * @param projectId
	 * @return
	 * @throws EliteServiceException
	 */
	List<ProjectDefine> findProjectDefinesByProjectId(Long projectId,ProjectDefine_Type defineType,String status)throws EliteServiceException;
   
}

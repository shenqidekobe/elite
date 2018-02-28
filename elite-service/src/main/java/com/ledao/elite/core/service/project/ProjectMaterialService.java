package com.ledao.elite.core.service.project;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目文件服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectMaterialService {

	/**
	 * 创建文件
	 * 
	 * @param projectMaterial
	 * @return projectMaterial
	 * */
	ProjectMaterial createProjectMaterial(ProjectMaterial obj)throws EliteServiceException;
	
	/**
	 * 精英上传文件
	 * @param obj
	 * @return
	 * @throws EliteServiceException
	 */
	void createProjectMaterialForElite(ProjectMaterial obj,ProjectStageTask task)throws EliteServiceException;
	
	/**
	 * 更新文件为已读
	 * 
	 * @param id
	 * @param isRead
	 * @return projectMaterial
	 * */
	ProjectMaterial updateProjectMaterialStatus(Long id,boolean isRead)throws EliteServiceException;
	
	/**
	 * Pm审核文件
	 * 
	 * @param projectMaterial
	 * @return projectMaterial
	 * */
	ProjectMaterial updateAuditProjectMaterialStatus(ProjectMaterial obj)throws EliteServiceException;
	/**
	 * 更新文件内容
	 * 
	 * @param projectMaterial
	 * @return projectMaterial
	 * */
	ProjectMaterial updateProjectMaterialNoFixed(ProjectMaterial obj)throws EliteServiceException;
	
	
	
	/**
	 * 删除文件信息
	 * 
	 * @param id
	 * @param removeId
	 * */
	void removeProjectMaterialById(Long id,Long removeId)throws EliteServiceException;
	
	/**
	 * 查询文件信息
	 * 
	 * @param id
	 * @return projectMaterial
	 * */
	ProjectMaterial findProjectMaterialById(Long id)throws EliteServiceException;
	
	/**
	 * 文件的数量
	 * 
	 * @return int
	 * */
	Integer findProjectMaterialCount(Long taskId,Long projectId,Long stageId,Long memberId,String queryType)throws EliteServiceException;
	
	/**
	 * 查询文件列表
	 * 
	 * @param projectId:项目ID
	 * @param memberId:会员ID
	 * @param statgeId:阶段ID
	 * @param queryType:查询类型{我收到的，我上传的，未读}
	 * @param pager
	 * 
	 * @return SearchResult<ProjectMaterial>
	 * */
	SearchResult<ProjectMaterial> findProjectMaterialList(Long taskId,Long projectId,Long stageId,Long memberId,String queryType,Pager pager)throws EliteServiceException;


     /**
	 * PM查询文件列表
	 * 
	 * @param projectId:项目ID
	 * @param pmId:pmId
	 * @param statgeId:阶段ID
	 * @param queryType:查询类型{我收到的，我上传的，未读}
	 * @param pager
	 * 
	 * @return SearchResult<ProjectMaterial>
	 * */
	SearchResult<ProjectMaterial> findProjectMaterialListByPm(Long projectId,Long stageId,Long pmId,String queryType,Pager pager)throws EliteServiceException;

	/**
	 * 是否存在该附件的文件
	 * @param attaId
	 * @return
	 */
	boolean findProjectMaterialExit(Long attaId)throws EliteServiceException;
	
	/**
	 * 检索项目方收到的项目文件数量
	 * @param projectId
	 * @param receiveId
	 * @return count
	 * */
	Integer findProjectMaterialCount(Long projectId,Long receiveId)throws EliteServiceException;
}

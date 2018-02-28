package com.ledao.elite.core.service.project;

import java.util.Date;
import java.util.List;

import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectDefineStage.Stage_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Status;

/**
 * 项目阶段定义服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 */
public interface ProjectDefineStageService {

	/**
	 * 创建一个项目阶段，序号每次+1
	 * 
	 * @param ProjectDefineStage
	 * @return ProjectDefineStage
	 */
	ProjectDefineStage createProjectDefineStage(ProjectDefineStage obj) throws EliteServiceException;

	/**
	 * 修改项目阶段
	 * 
	 * @param ProjectDefineStage
	 * @return ProjectDefineStage
	 */
	ProjectDefineStage updateProjectDefineStage(ProjectDefineStage obj) throws EliteServiceException;

	/**
	 * 修改项目阶段的付款方式
	 * 
	 * @param id
	 * @param payWay
	 */
	void updateProjectDefineStagePayWay(Long id, GlobalDefinedConstant.Pay_Way payWay) throws EliteServiceException;

	/**
	 * 阶段验收操作
	 * 
	 * @param id
	 * @param acceptFlag
	 * @param acceptReason
	 */
	void updateProjectDefineStageAccept(Long id, boolean acceptFlag, String acceptReason) throws EliteServiceException;
	
	/**
	 * 同步CTO阶段的状态
	 * 
	 * @param ProjectDefineStage
	 * @return ProjectDefineStage
	 */
	void updateProjectDefineStageAsCTO(Long id,boolean currented,boolean trusted,Pay_Status payStatus) throws EliteServiceException;

	/**
	 * 按ID查询阶段
	 */
	ProjectDefineStage findProjectDefineStageById(Long id) throws EliteServiceException;

	/**
	 * 按阶段code查询阶段
	 * 
	 * @param projectId
	 * @param defineId
	 * @param stageCode
	 */
	ProjectDefineStage findProjectDefineStageByCode(Long projectId, Long defineId, String stageCode)
			throws EliteServiceException;
	

	/**
	 * 根据id获取确认付款阶段
	 * 
	 * @param id
	 * @return
	 * @throws EliteServiceException
	 */
	ProjectDefineStage findProjectDefineWaitPayStageById(Long id) throws EliteServiceException;

	/**
	 * 按项目Id 创建时间查询
	 */
	List<ProjectDefineStage> findProjectDefineStagesByProjectIdAndCreateTime(Long projectId, Date createTime)
			throws EliteServiceException;

	/**
	 * 查询项目阶段列表，按立项书类型查询
	 * 
	 * @param defineType
	 * @param statgeStatus
	 * @return List<ProjectDefineStage>
	 */
	List<ProjectDefineStage> findProjectDefineStageByType(ProjectDefine_Type type, Stage_Status status)
			throws EliteServiceException;

	/**
	 * 按立项书ID查询阶段列表
	 * 
	 * @param definedId
	 */
	List<ProjectDefineStage> findProjectDefineStageByDefinedId(Long definedId) throws EliteServiceException;

	/**
	 * 按projectId,阶段code 查询
	 * 
	 * @param definedId
	 */
	List<ProjectDefineStage> findProjectDefineStageByProjectAndStageCode(long projectId, String steageCode)
			throws EliteServiceException;

}

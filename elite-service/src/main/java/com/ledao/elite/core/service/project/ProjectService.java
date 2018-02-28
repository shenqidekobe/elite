package com.ledao.elite.core.service.project;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.RoleEnum;
import com.ledao.elite.core.framework.dto.ProjectDetailInfo;

/**
 * 项目服务接口
 */
public interface ProjectService {

	/**
	 * 项目方初次发布项目
	 * 
	 * @param project
	 * @return Project
	 */
	Project createProject(Project project) throws EliteServiceException;
	/**
	 * bm初次发布项目
	 * 
	 * @param project
	 * @return Project
	 */
	Project createBmProject(Project project) throws EliteServiceException;

	/**
	 * Bm发项目立项书
	 * 
	 * @param project
	 * @return Project
	 */
	Project createProjectDefinesAndDefinesStages(ProjectDefine define, List<ProjectDefineStage> defineStages,
			Project project,Long projectTenderRecordId) throws EliteServiceException;

	/**
	 * 更新项目信息
	 * 
	 * @param project
	 * @return Project
	 */
	Project updateProjectInfo(Project project) throws EliteServiceException;
	
	/**
	 * 合并更新项目信息
	 * 
	 * @param project
	 * @return Project
	 */
	Project mergeProjectInfo(Project project) throws EliteServiceException;

	/**
	 * 审核项目
	 */
	Project updateProjectAudit(Project project) throws EliteServiceException;
	
	/**
	 * 项目通过更新为不通过{退回意向金}
	 * 
	 * @param id
	 * @param auditId
	 * @param auditReason
	 * @return project
	 * */
	Project updateProjectPassToUnPass(Long id,Long auditId,String auditReason)throws EliteServiceException;

	/**
	 * 更新项目
	 * 
	 * @param project
	 * @return Project
	 */
	void updateProject(Project project) throws EliteServiceException;

	/**
	 * 提交项目意向金，更新项目信息
	 * 
	 * @param projectId
	 * @param intentionAmount
	 */
	void updateProjectAsIntention(Long projectId, BigDecimal intentionAmount) throws EliteServiceException;

	/**
	 * 提交项目托管费用，更新项目信息
	 * 
	 * @param projectId
	 * @param stageId
	 * @param payAmount
	 */
	void updateProjectAsTrust(Long projectId, Long stageId, BigDecimal payAmount) throws EliteServiceException;

	/**
	 * 协助完善项目信息
	 */
	Project updateProjectPrefect(Project project) throws EliteServiceException;

	/**
	 * 更新项目状态
	 */
	void updateProjectStatusById(Long id, Project_Status status) throws EliteServiceException;

	/**
	 * 发招标书
	 * 
	 */
	void createProjectTender(Project project, ProjectTender obj) throws EliteServiceException;
	/**
	 * 重发发招标书
	 * 
	 */
	void createResetProjectTender(Project project, ProjectTender obj) throws EliteServiceException;

	/**
	 * 设置项目为审核状态
	 */
	void updateProjectStatusInById(Long id) throws EliteServiceException;

	/**
	 * 项目方确定项目立项书
	 * 
	 * @param projectId
	 */
	void updateProjectAffirmDefinedAsCompany(Long projectId, boolean isAffirm) throws EliteServiceException;

	/**
	 * CTO确定项目立项书
	 * 
	 * @param projectId
	 * @param memberId
	 */
	void updateProjectAffirmDefinedAsCTO(Long projectId, Long memberId, boolean isAffirm) throws EliteServiceException;
	
	/**
	 * 托管股权
	 * 
	 * @param projectId
	 * */
	Project updateProjectTrustStockAsCompany(Long projectId)throws EliteServiceException;

	/**
	 * 删除项目
	 * 
	 * @param projectId
	 */
	void removeProject(Long projectId) throws EliteServiceException;

	/**
	 * 整个项目验收
	 * 
	 * @param id
	 */
	void updateProjectEntireAccept(Long id) throws EliteServiceException;
	
	/**
	 * 对于已经交意向金的项目，退回意向金到会员账户
	 * 
	 * @param projectId
	 * */
	void updateProjectBackIntentionAmount(Long projectId)throws EliteServiceException;

	/**
	 * 根据ID查询项目信息
	 * 
	 * @param projectId
	 * @return project
	 */
	Project findProjectById(Long projectId) throws EliteServiceException;

	/**
	 * 项目详细信息查询
	 * 
	 * @param projectId
	 * @return projectDetailInfo
	 */
	ProjectDetailInfo findProjectDetailById(Long projectId) throws EliteServiceException;
	
	/**
	 * 根据项目id，type（bm，pm）查询
	 * 
	 * @param projectId
	 * @return projectDetailInfo
	 */
	ProjectDetailInfo findProjectDetailByIdAndType(Long projectId,String type) throws EliteServiceException;

	/**
	 * 查询项目方发布的项目数量(按项目状态)
	 * 
	 * @param memberId
	 * @param status
	 * @return count
	 */
	Integer findProjectCountByCompanyId(Long memberId, Project_Status status) throws EliteServiceException;

	/**
	 * 查询项目方的项目
	 * 
	 * @param memberId
	 * @return List<Project>
	 */
	List<Project> findProjectListByCompanyId(Long memberId) throws EliteServiceException;
	
	/**
	 * 根据状态查询
	 * 
	 * @param status
	 * @return List<Project>
	 */
	List<Project> findProjectListByStatus(Project_Status status) throws EliteServiceException;

	/**
	 * 后台项目列表查询
	 * 
	 * @param userId:管理员ID
	 * @param role:管理员角色
	 * @param keyword:项目名称或编号
	 * @param status:项目状态
	 * @param startTime:项目开始时间
	 * @param endTime:项目结束时间
	 * @param holdBmId:商务负责人ID
	 * @param holdPmId:项目负责人ID
	 * @param productIndustry:行业
	 * @param productFunction:功能
	 * @param projectSolution:解决方法类型
	 * @param contactName:项目方
	 * @param contactPhone:项目方电话
	 * @param pager:分页对象
	 * @return SearchResult<ProjectDetailInfo>
	 */
	SearchResult<ProjectDetailInfo> findManagerProjectList(Long userId, RoleEnum role, String keyword, String status,
			Date startTime, Date endTime, Long holdBmId, Long holdPmId, String productIndustry, String productFunction,
			String projectSolution, String contactName, String contactPhone, Pager pager) throws EliteServiceException;

	/**
	 * 前端我的项目列表查询
	 * 
	 * @param memberId
	 * @param projectNum
	 * @param status
	 * @param pager
	 * @return SearchResult<Project>
	 */
	SearchResult<Project> findProjectList(Long memberId, String projectNum, String status, Pager pager)throws EliteServiceException;
	
	/**
	 * 前端项目大厅列表查询
	 * 
	 * @param projectSolution
	 * @param status
	 * @param pager
	 * @return SearchResult<Project>
	 * */
	SearchResult<Project> findProjectListAsHall(String projectSolution,String status,Pager pager)throws EliteServiceException;

	/**
	 * 应收管理查询
	 * @param keyword(项目名，编号）
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<Project> findProjectListByReceivable(String keyword, Pager pager) throws EliteServiceException;
	/**
	 * 应付管理查询
	 * @param keyword(项目名，编号）
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<Project> findProjectListByPayable(String keyword, Pager pager) throws EliteServiceException;
	/**
	 * 应付管理查询详情
	 * @return
	 * @throws EliteServiceException
	 */
    Project findProjectDetailByPayable(Long id) throws EliteServiceException;
	
	/**
	 * 根据ctoId查询
	 */
	List<Project>findProjectListByctoId(Long ctoId)throws EliteServiceException;

	/**
	 * 项目方查询正在进行中的项目数
	 * 
	 * @param memberId
	 */
	int findProjectDoingCountBy(Long memberId)throws EliteServiceException;
	
	/**
	 * 分页通过查询通过渠道方Id
	 */
	SearchResult<Project> findProjectListByMemberPartnerId(Long partnerId,Long memberId,String incomeType,Pager pager)throws EliteServiceException;
	
	
	/**
	 * 查看cto最后接项目时间
	 */
	Date findparojectListByCtoId(Long ctoId)throws EliteServiceException;
}

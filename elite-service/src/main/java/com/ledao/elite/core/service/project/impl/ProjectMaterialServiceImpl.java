package com.ledao.elite.core.service.project.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectDefineStage.Stage_Status;
import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.domain.project.ProjectMaterial.ProjectMaterial_Status;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.project.ProjectMaterialRepository;
import com.ledao.elite.core.repository.project.ProjectStageTaskRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectMaterialService;

@Service
public class ProjectMaterialServiceImpl extends BaseSerivceImpl implements ProjectMaterialService {

	@Resource
	private ProjectMaterialRepository projectMaterialRepository;
	@Resource
	private ProjectStageTaskRepository projectStageTaskRepository;
	@Resource
	protected EventPublishService eventPublishService;
	@Resource
	protected ProjectDefineStageService projectDefineStageService;

	@Override
	public ProjectMaterial createProjectMaterial(ProjectMaterial obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getProjectId(), obj.getStatus());
		obj.setRead(false);
		this.projectMaterialRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectMaterial updateProjectMaterialStatus(Long id, boolean isRead) throws EliteServiceException {
		this.verifyParams(id);
		ProjectMaterial pojo = this.findProjectMaterialById(id);
		pojo.setRead(isRead);
		pojo.setDownTime(new Date());
		this.projectMaterialRepository.save(pojo);
		return pojo;
	}

	@Override
	public void removeProjectMaterialById(Long id, Long removeId) throws EliteServiceException {
		ProjectMaterial obj=findProjectMaterialById(id);
		if(obj==null){
			throw new EliteServiceException("文件不存在", ErrorCodeEnum.FAILURE.code);
		}else if(!obj.getUploadId().equals(removeId)){
			throw new EliteServiceException("非自己上传的文件，不能删除", ErrorCodeEnum.FAILURE.code);
		}
//		else if(ProjectMaterial_Status.pass.equals(obj.getStatus())){
//			throw new EliteServiceException("此文件已经审核通过，不能删除", ErrorCodeEnum.FAILURE.code);
//		}
		this.projectMaterialRepository.remove(obj);
	}

	@Override
	public ProjectMaterial findProjectMaterialById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectMaterialRepository.find(id);
	}

	@Override
	public Integer findProjectMaterialCount(Long taskId, Long projectId, Long stageId, Long memberId, String queryType)
			throws EliteServiceException {
		this.verifyParams(queryType, memberId);
		Search search = findProjectMaterialSearch(taskId, projectId, stageId, memberId, queryType);
		return this.projectMaterialRepository.count(search);
	}

	@Override
	public SearchResult<ProjectMaterial> findProjectMaterialList(Long taskId, Long projectId, Long stageId,
			Long memberId, String queryType, Pager pager) throws EliteServiceException {
		this.verifyParams(queryType, memberId);
		if (pager == null) {
			pager = new Pager();
		}
		Search search = findProjectMaterialSearch(taskId, projectId, stageId, memberId, queryType);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		return this.projectMaterialRepository.searchAndCount(search);
	}

	private Search findProjectMaterialSearch(Long taskId, Long projectId, Long stageId, Long memberId,
			String queryType) {
		Search search = new Search();
		if (taskId != null) {
			search.addFilterEqual("taskId", taskId);
		} else {
			search.addFilterEqual("projectId", projectId);
		}
		if (stageId != null) {
			search.addFilterEqual("stageId", stageId);
		}
		switch (queryType) {
		case ProjectMaterial.QUERYTYPE_ALL:
			search.addFilterOr(new Filter("receiveId", memberId), new Filter("uploadId", memberId));
			search.addFilterEqual("status", ProjectMaterial_Status.pass);
			break;
		case ProjectMaterial.QUERYTYPE_RECEIVE:
			search.addFilterEqual("receiveId", memberId);
			search.addFilterEqual("status", ProjectMaterial_Status.pass);
			break;
		case ProjectMaterial.QUERYTYPE_SEND:
			search.addFilterEqual("uploadId", memberId);
			break;
		case ProjectMaterial.QUERYTYPE_UNREAD:
			search.addFilterEqual("receiveId", memberId);
			search.addFilterEqual("status", ProjectMaterial_Status.pass);
			search.addFilterEqual("isRead", false);
			break;
		}
		search.addSort("createTime", true);
		return search;
	}

	@Override
	public void createProjectMaterialForElite(ProjectMaterial obj, ProjectStageTask task) throws EliteServiceException {
		this.verifyParams(obj, obj.getProjectId(), obj.getUploadId(), obj.getStatus());
		obj.setRead(false);
		this.projectMaterialRepository.save(obj);
		projectStageTaskRepository.save(task);
		if (task.getStatus().equals(ProjectTask_Status.wait_accept)) {
			projectStageTaskRepository.save(task);
		}
	}

	@Override
	public SearchResult<ProjectMaterial> findProjectMaterialListByPm(Long projectId, Long stageId, Long pmId,
			String queryType, Pager pager) throws EliteServiceException {
		this.verifyParams(projectId);
		if (pager == null)
			pager = new Pager();
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		if (stageId != null) {
			search.addFilterEqual("stageId", stageId);
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		switch (queryType) {
		case ProjectMaterial.QUERYTYPE_RECEIVE:
			search.addFilterEqual("status", ProjectMaterial_Status.wait_audit);
			break;
		case ProjectMaterial.QUERYTYPE_SEND:
			search.addFilterEqual("uploadId", pmId);
			break;
		case ProjectMaterial.QUERYTYPE_UNREAD:
			search.addFilterEqual("status", ProjectMaterial_Status.wait_audit);
			break;
		}
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.projectMaterialRepository.searchAndCount(search);
	}

	@Override
	public ProjectMaterial updateAuditProjectMaterialStatus(ProjectMaterial obj) throws EliteServiceException {
		this.verifyParams(obj.getId());
		this.projectMaterialRepository.save(obj);

		// 审核cto上传文件，当对应阶段处于未开始，进行中状态时
		if (obj.getStage() != null&&obj.getReceiveMember()!=null) {
			String stageCode = obj.getStage().getStageCode();
			if (obj.getReceiveMember().getCurrentType().equals(MemberIdentity_Type.company.name())
					&& obj.getStatus().equals(ProjectMaterial_Status.pass) && stageCode != null) {
				List<ProjectDefineStage> stages = this.projectDefineStageService
						.findProjectDefineStageByProjectAndStageCode(obj.getProjectId(), stageCode);
				for (int i = 0; i < stages.size(); i++) {
					ProjectDefineStage stage = stages.get(i);
					if (stage.getStatus().equals(Stage_Status.wait_start)
							|| stage.getStatus().equals(Stage_Status.starting)) {
						stage.setStatus(Stage_Status.wait_accept);
						this.projectDefineStageService.updateProjectDefineStage(stage);
					}
				}
			}
		}
		return obj;
	}

	@Override
	public ProjectMaterial updateProjectMaterialNoFixed(ProjectMaterial obj) throws EliteServiceException {
		this.verifyParams(obj.getId());
		this.projectMaterialRepository.save(obj);
		return null;
	}

	@Override
	public boolean findProjectMaterialExit(Long attaId) throws EliteServiceException{
		boolean flag = true;
		Search search = new Search();
		search.addFilterEqual("attaId", attaId);
		ProjectMaterial material = projectMaterialRepository.searchUnique(search);
		if (material == null) {
			flag = false;
		}
		return flag;
	}
	
	@Override
	public Integer findProjectMaterialCount(Long projectId,Long receiveId)throws EliteServiceException{
		if(projectId==null||receiveId==null)return 0;
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("receiveId", receiveId);
		search.addFilterEqual("isRead", false);
		search.addFilterEqual("status", ProjectMaterial_Status.pass);
		return this.projectMaterialRepository.count(search);
	}

}

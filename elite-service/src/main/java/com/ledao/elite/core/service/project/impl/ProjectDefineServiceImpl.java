package com.ledao.elite.core.service.project.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Status;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.project.ProjectDefineRepository;
import com.ledao.elite.core.repository.project.ProjectRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectDefineService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;

@Service("projectDefineService")
public class ProjectDefineServiceImpl extends BaseSerivceImpl implements ProjectDefineService {

	@Resource
	private ProjectDefineRepository projectDefineRepository;
	@Resource
	private ProjectRepository projectRepository;

	@Resource
	private ProjectDefineStageService projectDefineStageService;

	@Override
	public ProjectDefine createProjectDefine(ProjectDefine obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getProjectId());
		obj.setStatus(ProjectDefine.ProjectDefine_Status.wait);
		this.projectDefineRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectDefine updateProjectDefine(ProjectDefine obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getProjectId());
		this.projectDefineRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectDefine updateProjectDefineCancelByProjectId(Long projectId,ProjectDefine_Type defineType) throws EliteServiceException {
		// TODO Auto-generated method stub
		
		List<ProjectDefine> defines = this.findProjectDefinesByProjectId(projectId,defineType,null);
		//立项书
		if(ProjectDefine_Type.company.equals(defineType)){
			Project project=this.projectRepository.find(projectId);
			project.setSendDefined(false);
			this.projectRepository.save(project);
		}
		if(defines.size()>0){
			ProjectDefine define=defines.get(0);
			define.setStatus(ProjectDefine_Status.cancel);
			define.setRecallTime(new Date());
			 return this.updateProjectDefine(define);
		}else{
			return null;
		}
	}

	@Override
	public ProjectDefine findProjectDefineById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectDefineRepository.find(id);
	}
	
	@Override
	public ProjectDefine findProjectDefine(Long projectId,ProjectDefine_Type type,ProjectDefine_Status status)throws EliteServiceException{
		this.verifyParams(projectId,type);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("defineType", type);
		if(status!=null){
			search.addFilterEqual("status", status);
		}
		search.addSort("createTime", true);//倒序
		List<ProjectDefine> list=this.projectDefineRepository.search(search);
		return list.isEmpty()?null:list.get(0);
	}
	
	@Override
	public ProjectDefineStage findProjectDefineStageByProjectIdAndStageId(Long projectId,Long stageId)throws EliteServiceException{
		this.verifyParams(projectId,stageId);
		return this.projectDefineStageService.findProjectDefineStageById(stageId);
	}
	
	@Override
	public void updateProjectDefineStage(ProjectDefineStage obj)throws EliteServiceException{
		this.verifyParams(obj,obj.getId());
		this.projectDefineStageService.updateProjectDefineStage(obj);
	}

	@Override
	public List<ProjectDefine> findProjectDefinesByProjectId(Long projectId,ProjectDefine_Type defineType,String status) throws EliteServiceException {
		this.verifyParams(projectId);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("defineType", defineType);
		if(status!=null){
			search.addFilterNotEqual("status", ProjectDefine_Status.valueOf(ProjectDefine_Status.class, status));
		}
		search.addSort("createTime", true);
		return this.projectDefineRepository.search(search);
	}

	@Override
	public void createProjectDefinesAndDefinesStages(ProjectDefine define, List<ProjectDefineStage> defineStages) {
		this.createProjectDefine(define);
		if (defineStages.size() > 0) {
			for (int i = 0; i < defineStages.size(); i++) {
				defineStages.get(i).setDefineId(define.getId());
				this.projectDefineStageService.createProjectDefineStage(defineStages.get(i));
			}
		}

	}


}

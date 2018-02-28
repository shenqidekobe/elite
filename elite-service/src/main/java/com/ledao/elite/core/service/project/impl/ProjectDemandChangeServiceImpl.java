package com.ledao.elite.core.service.project.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectDemandChange;
import com.ledao.elite.core.domain.project.ProjectDemandChange.DemandChange_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.project.ProjectDemandChangeRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectDemandChangeService;

@Service
public class ProjectDemandChangeServiceImpl extends BaseSerivceImpl implements ProjectDemandChangeService{
	
	
	@Resource
	private ProjectDemandChangeRepository projectDemandChangeRepository;

	@Override
	public ProjectDemandChange createProjectDemandChange(ProjectDemandChange obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getProjectId());
		obj.setStatus(DemandChange_Status.wait_confirm);
		this.projectDemandChangeRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectDemandChange updateProjectDemandChangeStatue(ProjectDemandChange obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getId(),obj.getStatus());
		//TODO:需求审核通过，需更新交付日期和价钱
		ProjectDemandChange pojo=this.findProjectDemandChangeById(obj.getId());
		pojo.setStatus(obj.getStatus());
		return pojo;
	}

	@Override
	public ProjectDemandChange findProjectDemandChangeById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectDemandChangeRepository.find(id);
	}

	@Override
	public SearchResult<ProjectDemandChange> findProjectDemandChangeList(Long projectId, Pager pager)
			throws EliteServiceException {
		this.verifyParams(projectId);
		Search search=new Search();
		search.addFilterEqual("projectId", projectId);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		return this.projectDemandChangeRepository.searchAndCount(search);
	}

}

package com.ledao.elite.core.service.project.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectChangeCto.ChangeCto_Status;
import com.ledao.elite.core.domain.project.ProjectDefer;
import com.ledao.elite.core.domain.project.ProjectDefer.Defer_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.project.ProjectDeferRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectDeferService;

@Service
public class ProjectDeferServiceImpl extends BaseSerivceImpl implements ProjectDeferService{
	
	@Resource
	private ProjectDeferRepository projectDeferRepository;

	@Override
	public ProjectDefer createProjectDefer(ProjectDefer obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getProjectId());
		obj.setStatus(Defer_Status.wait_process);
		this.projectDeferRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectDefer updateProjectDeferStatus(ProjectDefer obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getId(),obj.getStatus(),obj.getProcessId());
		ProjectDefer pojo=this.findProjectDeferById(obj.getId());
		pojo.setStatus(obj.getStatus());
		pojo.setProcessId(obj.getProcessId());
		pojo.setProcessTime(new Date());
		//TODO:审核发送消息通知cto
		if(obj.getStatus().equals(ChangeCto_Status.agree)){
			
		}
		return pojo;
	}

	@Override
	public ProjectDefer findProjectDeferById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectDeferRepository.find(id);
	}
	
	@Override
	public SearchResult<ProjectDefer> findProjectDeferList(Long projectId,Pager pager)throws EliteServiceException{
		this.verifyParams(projectId);
		Search search=new Search();
		search.addFilterEqual("projectId", projectId);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		return this.projectDeferRepository.searchAndCount(search);
	}

}

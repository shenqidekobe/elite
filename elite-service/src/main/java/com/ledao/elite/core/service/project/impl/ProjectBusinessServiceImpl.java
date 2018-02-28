package com.ledao.elite.core.service.project.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectBusiness;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.repository.project.ProjectBusinessRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectBusinessService;

@Service
public class ProjectBusinessServiceImpl extends BaseSerivceImpl implements ProjectBusinessService {

	@Resource
	private ProjectBusinessRepository projectBusinessRepository;

	@Override
	public ProjectBusiness createProjectBusiness(ProjectBusiness obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getContent());
		obj.setStatus(GlobalDefinedConstant.System_Status.normal.name());
		this.projectBusinessRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectBusiness updateProjectBusiness(ProjectBusiness obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getId());
		this.projectBusinessRepository.save(obj);
		return obj;
	}

	@Override
	public SearchResult<ProjectBusiness> findProjectBusinessByProject(Long projectId, Long userId, Pager pager)
			throws EliteServiceException {
		this.verifyParams(projectId);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		if (userId != null)
			search.addFilterEqual("userId", userId);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		return this.projectBusinessRepository.searchAndCount(search);
	}

	@Override
	public ProjectBusiness findProjectBusinessById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectBusinessRepository.find(id);
	}

	@Override
	public List<ProjectBusiness> findProjectBusinessListByProject(Long projectId, Long userId)
			throws EliteServiceException {

		this.verifyParams(projectId);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		if (userId != null)
			search.addFilterEqual("userId", userId);
		search.addSort("createTime", true);
		search.addFilterEqual("status", GlobalDefinedConstant.System_Status.normal.name());
		return this.projectBusinessRepository.search(search);
	}

	@Override
	public ProjectBusiness removeLogicById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		ProjectBusiness obj = this.projectBusinessRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("商务沟通记录信息不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		obj.setStatus(GlobalDefinedConstant.System_Status.deleted.name());
		return this.projectBusinessRepository.save(obj) ? obj : null;
	}

}

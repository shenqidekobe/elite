package com.ledao.elite.core.service.project.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectLog;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.project.ProjectLogRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.project.ProjectLogService;


@Service
public class ProjectLogServiceImpl extends BaseSerivceImpl implements ProjectLogService{
	
	
	@Resource
	private ProjectLogRepository projectLogRepository;

	@Override
	public ProjectLog createProjectLog(ProjectLog obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getProjectId());
		projectLogRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectLog findProjectLogById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectLogRepository.find(id);
	}
	
	@Override
	public Integer findProjectLogCount(Long projectId,ProjectLog_Type type)throws EliteServiceException{
		this.verifyParams(projectId);
		Search search=new Search();
		search.addFilterEqual("projectId", projectId);
		search.addFilterEmpty("taskId");
		if(type!=null){
			search.addFilterEqual("logType", type);
		}
		return this.projectLogRepository.count(search);
	}

	@Override
	public SearchResult<ProjectLog> findProjectLogList(Long projectId,Long memberId, String stageCode, String keyword, Date startTime,
			Date endTime, Pager pager) throws EliteServiceException {
		this.verifyParams(projectId);
		if (pager == null)
			pager = new Pager();
		Search search = new Search(ProjectLog.class);
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("createId", memberId);
		search.addFilterEmpty("taskId");
		if(StringUtils.isNotEmpty(stageCode))
			search.addFilterEqual("stageCode", stageCode);
		if(startTime!=null)
			search.addFilterGreaterOrEqual("createTime", startTime);
		if(endTime!=null)
			search.addFilterLessOrEqual("createTime", endTime);
		if(StringUtils.isNotEmpty(keyword)){
			search.addFilterOr(new Filter("content", "%"+keyword+"%",Filter.OP_LIKE),new Filter("title", "%"+keyword+"%",Filter.OP_LIKE));
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime",true);
		}
		return this.projectLogRepository.searchAndCount(search);
	}
	
	@Override
	public SearchResult<ProjectLog> findTaskLogList(Long taskId,Long memberId,String stageCode, String keyword, Date startTime,
			Date endTime, Pager pager) throws EliteServiceException {
		this.verifyParams(taskId);
		if (pager == null)
			pager = new Pager();
		Search search = new Search(ProjectLog.class);
		search.addFilterEqual("taskId", taskId);
		search.addFilterEqual("createId", memberId);
		if(StringUtils.isNotEmpty(stageCode))
			search.addFilterEqual("stageCode", stageCode);
		if(startTime!=null)
			search.addFilterGreaterOrEqual("createTime", startTime);
		if(endTime!=null)
			search.addFilterLessOrEqual("createTime", endTime);
		if(StringUtils.isNotEmpty(keyword)){
			search.addFilterLike("content", "%"+keyword+"%");
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime",true);
		}
		return this.projectLogRepository.searchAndCount(search);
	}
	
	@Override
	public Integer findTaskLogCount(Long taskId,Long memberId)throws EliteServiceException{
		this.verifyParams(taskId);
		Search search=new Search();
		search.addFilterEqual("taskId", taskId);
		search.addFilterEqual("createId", memberId);
		return this.projectLogRepository.count(search);
	}

}

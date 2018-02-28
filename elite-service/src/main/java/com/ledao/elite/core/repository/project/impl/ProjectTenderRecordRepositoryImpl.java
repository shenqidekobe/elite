package com.ledao.elite.core.repository.project.impl;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.domain.project.ProjectTenderRecord.ProjectTenderRecord_Status;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.project.ProjectTenderRecordRepository;

@Repository
public class ProjectTenderRecordRepositoryImpl extends GenericRepositoryImpl<ProjectTenderRecord, Long>
		implements ProjectTenderRecordRepository {

	@Override
	public SearchResult<ProjectTenderRecord> findProjectTenderRecordsByProjectId(Long projectId, Pager pager) {

		if (pager == null)
			pager = new Pager();
		Search search = new Search(ProjectTenderRecord.class);
		search.addFilterEqual("projectId", projectId);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.searchAndCount(search);
	}

	@Override
	public SearchResult<ProjectTenderRecord> findCtoProjectTender(Long memberId, Integer status, Pager pager) {
		if (pager == null)
			pager = new Pager();
		Search search = new Search(ProjectTenderRecord.class);
		search.addFilterEqual("memberId", memberId);
		// 如果未中标竞标中
		if(status!=null){
			if (status == 0) {
				search.addFilterIn("status",
						Arrays.asList(ProjectTenderRecord_Status.tender_in, ProjectTenderRecord_Status.tender_not));
			} else {
				search.addFilterIn("status",
						Arrays.asList(ProjectTenderRecord_Status.tender_win, ProjectTenderRecord_Status.tender_normal));
			}
		}
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		return this.searchAndCount(search);
	}


	@Override
	public SearchResult<ProjectTenderRecord> findProjectTenderRecordsByProjectIdAndTender(Long projectId, Long tenderId,
			String status, Pager pager) {
		if (pager == null)
			pager = new Pager();
		Search search = new Search(ProjectTenderRecord.class);
		search.addFilterEqual("projectId", projectId);
		if(tenderId!=null){
			search.addFilterEqual("tenderId", tenderId);
		}
		if (StringUtils.isNotEmpty(status)) {
			search.addFilterEqual("status", ProjectTenderRecord_Status.valueOf(ProjectTenderRecord_Status.class, status));
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.searchAndCount(search);
	}

}

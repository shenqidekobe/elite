package com.ledao.elite.core.service.project.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.domain.project.ProjectTender.ProjectTender_Status;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.domain.project.ProjectTenderRecord.ProjectTenderRecord_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.repository.project.ProjectRepository;
import com.ledao.elite.core.repository.project.ProjectTenderRecordRepository;
import com.ledao.elite.core.repository.project.ProjectTenderRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectTenderService;

@Service
public class ProjectTenderServiceImpl extends BaseSerivceImpl implements ProjectTenderService {

	@Resource
	private ProjectTenderRepository projectTenderRepository;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private ProjectRepository projectRepository;
	@Resource
	private EventPublishService eventPublishService;
	@Resource
	private LocalCoreConfig localCoreConfig;
	@Resource
	private ProjectTenderRecordRepository projectTenderRecordRepository;

	@Override
	public ProjectTender createProjectTender(ProjectTender obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getProjectId());
		obj.setStatus(ProjectTender.ProjectTender_Status.tender_in);
		this.projectTenderRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectTender findProjectTenderById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectTenderRepository.find(id);
	}

	@Override
	public void createProjectTenderAndDefineStage(ProjectTender obj, List<ProjectDefineStage> defineStages)
			throws EliteServiceException {

		Date nowDate = new Date();
		obj.setCreateTime(nowDate);
		this.createProjectTender(obj);
		if (defineStages.size() > 0) {
			for (int i = 0; i < defineStages.size(); i++) {
				defineStages.get(i).setCreateTime(nowDate);
				this.projectDefineStageService.createProjectDefineStage(defineStages.get(i));
			}
		}
	}

	@Override
	public ProjectTender findProjectTenderByProjectId(Long projectId) throws EliteServiceException {
		this.verifyParams(projectId);
		Search search = new Search();
		search.addFilterEqual("projectId", projectId);
		search.addSort("createTime", true);
		List<ProjectTender> tenders = this.projectTenderRepository.search(search);
		return tenders.isEmpty() ? null : tenders.get(0);
	}

	@Override
	public void updateProjectTender(Long id, ProjectTender tender) throws EliteServiceException {
		this.verifyParams(id);
		ProjectTender obj = this.projectTenderRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("未找到标书记录", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		obj.setStatus(tender.getStatus());
		obj.setStockRate(tender.getStockRate());
		obj.setQualityAmount(tender.getQualityAmount());
		obj.setQualityTime(tender.getQualityTime());
		obj.setPlatformAmount(tender.getPlatformAmount());
		this.projectTenderRepository.save(obj);
	}

	@Override
	public void updateProjectTender(ProjectTender tender) throws EliteServiceException {
		this.verifyParams(tender.getId(), tender);
		this.projectTenderRepository.save(tender);
	}

	@Override
	public void updateProjectTenderAsExpire() throws EliteServiceException {
		Search search = new Search();
		search.addFilterLessOrEqual("tenderoverTime", Calendar.getInstance().getTime());
		search.addFilterEqual("status", ProjectTender_Status.tender_in);
		List<ProjectTender> list = this.projectTenderRepository.search(search);

		for (ProjectTender pst : list) {
			pst.setStatus(ProjectTender_Status.tender_stop);
			this.projectTenderRepository.save(pst);
		}
	}

	@Override
	public SearchResult<ProjectTender> findProjectTenders(Long projectId, Pager pager) throws EliteServiceException {
		if (pager == null)
			pager = new Pager();
		Search search = new Search(ProjectTender.class);
		search.addFilterEqual("projectId", projectId);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.projectTenderRepository.searchAndCount(search);
	}

	@Override
	public SearchResult<ProjectTender> findProjectTendersDetail(Long projectId, Pager pager)
			throws EliteServiceException {
		// TODO Auto-generated method stub
		SearchResult<ProjectTender> sr = this.findProjectTenders(projectId, pager);
		List<ProjectTender> tenders = sr.getResult();
		for (int i = 0; i < tenders.size(); i++) {
			List<ProjectTenderRecord> records = tenders.get(i).getTenders();
			for (int j = 0; j < records.size(); j++) {
				if (records.get(j).getMemberId() != null) {
					MemberCredit credit = this.memberCreditService
							.findMemberCreditByMemberId(records.get(j).getMemberId());
					if (credit != null) {
						records.get(j).setMemberName(credit.getRealName());
					}
					// cto正在进行/完成的项目数量
					int doingCount = 0;
					int doneCount = 0;
					List<Project> projectList = this
							.findProjectListByctoId(records.get(j).getMemberId());
					for (int k = 0; k < projectList.size(); k++) {
						Project project = projectList.get(k);
						if ( project.getStatus().equals(Project_Status.pass_wait)
								|| project.getStatus().equals(Project_Status.stage_course)
								|| project.getStatus().equals(Project_Status.pass_wait)
								|| project.getStatus().equals(Project_Status.pass_already)
								|| project.getStatus().equals(Project_Status.quality)) {
							doingCount++;
						}
						if (project.getStatus().equals(Project_Status.finish)) {
							doneCount++;

						}
					}
					records.get(j).setProjectInCount(doingCount);
					records.get(j).setProjectDoneCount(doneCount);
				}
			}
			tenders.get(i).setTenders(records);
		}
		sr.setResult(tenders);
		return sr;
	}
	@Override
    public List<Project>findProjectListByctoId(Long ctoId)throws EliteServiceException {
    	this.verifyParams(ctoId);
		Search search = new Search();
		search.addFilterEqual("ctoId", ctoId);
		return this.projectRepository.search(search);
    }

	@Override
	public List<ProjectTender> updateRemoveProjectTender(ProjectTender projectTender) throws EliteServiceException {
		// TODO Auto-generated method stub
		this.updateProjectTender(projectTender);
		
		//所有竞标cto 未中标
		List<ProjectTenderRecord> tenders=projectTender.getTenders();
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PM_CANCEL_TENDER);
		String url = localCoreConfig.getDomainServer();
		String title = String.format(message.getKey(), projectTender.getProject().getName());
		String replace ="<a href='" + url+ "/member/index' target='_blank'>查看详情</a>";
		String content = String.format(message.getValue(), projectTender.getProject().getName(),replace);
		for(int i=0;i<tenders.size();i++ ){
			ProjectTenderRecord	record=tenders.get(i);
			record.setStatus(ProjectTenderRecord_Status.tender_not);
			this.projectTenderRecordRepository.save(record);
			eventPublishService.publishMessageEvent(record.getMemberId(), projectTender.getProjectId(), null, title, content, true,
					MemberMessage_Type.system);
		}
		return null;
	}
}

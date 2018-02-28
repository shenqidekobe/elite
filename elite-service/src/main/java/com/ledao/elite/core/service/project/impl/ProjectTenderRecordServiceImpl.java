package com.ledao.elite.core.service.project.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.project.ProjectMaterial.ProjectMaterial_Status;
import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.domain.project.ProjectTenderRecord.ProjectTenderRecord_Status;
import com.ledao.elite.core.domain.project.ProjectTenderRecordStage;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.repository.project.ProjectRepository;
import com.ledao.elite.core.repository.project.ProjectTenderRecordRepository;
import com.ledao.elite.core.repository.project.ProjectTenderRecordStageRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.project.ProjectMaterialService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectTenderRecordService;
import com.ledao.elite.core.service.project.ProjectTenderService;

@Service("projectTenderRecordService")
public class ProjectTenderRecordServiceImpl extends BaseSerivceImpl implements ProjectTenderRecordService {

	@Resource
	private ProjectTenderRecordRepository projectTenderRecordRepository;
	@Resource
	private ProjectTenderRecordStageRepository projectTenderRecordStageRepository;

	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectRepository projectRepository;
	@Resource
	private EventPublishService eventPublishService;
	@Resource
	private LocalCoreConfig localCoreConfig;
	@Resource
	private ProjectMaterialService projectMaterialService;
	@Resource
	private ProjectTenderService projectTenderService;

	@Override
	public ProjectTenderRecord createProjectTenderRecord(ProjectTenderRecord obj, String recordStages)
			throws EliteServiceException {
		this.verifyParams(obj, obj.getTenderId(), obj.getMemberId());

		List<ProjectTenderRecordStage> recordStageList = null;
		try {
			recordStageList = JSON.parseArray(recordStages, ProjectTenderRecordStage.class);
		} catch (Exception e) {
			throw new EliteServiceException("阶段费用的各项参数不能为空", ErrorCodeEnum.PARAM_ISNULL.code);
		}
		if (recordStageList.isEmpty()) {
			throw new EliteServiceException("阶段费用的各项参数不能为空", ErrorCodeEnum.PARAM_ISNULL.code);
		}
		obj.setStatus(ProjectTenderRecord.ProjectTenderRecord_Status.tender_in);
		this.projectTenderRecordRepository.save(obj);
		Long recordId = obj.getId();
		for (ProjectTenderRecordStage ptrs : recordStageList) {
			ptrs.setRecordId(recordId);
			ptrs.setProjectId(obj.getProjectId());
			this.projectTenderRecordStageRepository.save(ptrs);
		}
		ProjectTender tender = projectTenderService.findProjectTenderById(obj.getTenderId());
		// 竞标时保存竞标文档到文件里面
		if (tender.getAtta() != null) {
			boolean flag = projectMaterialService.findProjectMaterialExit(tender.getAttaId());
			if (!flag) {
				ProjectMaterial material = new ProjectMaterial();
				material.setProjectId(obj.getProjectId());
				material.setReceiveId(obj.getMemberId());
				material.setAttaId(tender.getAttaId());
				material.setDeliveryed(false);
				material.setStatus(ProjectMaterial_Status.pass);
				projectMaterialService.createProjectMaterial(material);
			}
		}
		return obj;
	}

	@Override
	public ProjectTenderRecord findProjectTenderRecordById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectTenderRecordRepository.find(id);
	}

	@Override
	public ProjectTenderRecord findProjectTenderRecordByProject(Long tenderId, Long projectId, Long memberId)
			throws EliteServiceException {
		this.verifyParams(tenderId, projectId, memberId);
		Search search = new Search();
		search.addFilterEqual("tenderId", tenderId);
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("memberId", memberId);
		return this.projectTenderRecordRepository.searchUnique(search);
	}

	@Override
	public void updateProjectTenderRecordById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		ProjectTenderRecord tenderRecord = this.projectTenderRecordRepository.find(id);
		Project project = this.projectService.findProjectById(tenderRecord.getProjectId());
		String url = localCoreConfig.getDomainServer();
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_TENDER_SUCCESS);
		String replace = "<a href='" + url + "/project/c/"+project.getId()+"/index' target='_blank'>查看详情</a>";
		tenderRecord.setStatus(ProjectTenderRecord_Status.tender_win);
		String title = String.format(message.getKey(), project.getName());
		String content = String.format(message.getValue(), project.getName(), replace);
		this.projectTenderRecordRepository.save(tenderRecord);
		eventPublishService.publishMessageEvent(tenderRecord.getMemberId(), project.getId(), null, title, content, true,
				MemberMessage_Type.system);
	}

	@Override
	public void updateProjectTenderRecord(ProjectTenderRecord obj) throws EliteServiceException {
		this.verifyParams(obj.getId(), obj);
		this.projectTenderRecordRepository.save(obj);
	}

	public List<ProjectTenderRecord> findProjectTenderRecordsByProjectId(Long projectId) {
		this.verifyParams(projectId);
		return this.projectTenderRecordRepository.search(new Search().addFilterEqual("projectId", projectId));
	}

	@Override
	public SearchResult<ProjectTenderRecord> findProjectTenderRecordsByProjectId(Long projectId, Long tenderId,
			String status, Pager pager) throws EliteServiceException {
		SearchResult<ProjectTenderRecord> recordResult = this.projectTenderRecordRepository
				.findProjectTenderRecordsByProjectIdAndTender(projectId, tenderId, status, pager);
		List<ProjectTenderRecord> records = recordResult.getResult();
		if (records.size() > 0) {
			for (int i = 0; i < records.size(); i++) {
				ProjectTenderRecord t = records.get(i);
				if (t.getMemberId() != null) {
					MemberCredit credit = this.memberCreditService.findMemberCreditByMemberId(t.getMemberId());
					if (credit != null) {
						t.setMemberName(credit.getRealName());
					}
				}
				int doingCount = 0;
				int doneCount = 0;
				if (t.getMemberId() != null) {
					List<Project> projectList = this.findProjectListByctoId(t.getMemberId());
					for (int k = 0; k < projectList.size(); k++) {
						Project project = projectList.get(k);
						if (project.getStatus().equals(Project_Status.pass_wait)
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
					t.setProjectInCount(doingCount);
					t.setProjectDoneCount(doneCount);
				}
				records.set(i, t);
			}
			recordResult.setResult(records);
		}
		return recordResult;
	}

	@Override
	public SearchResult<ProjectTenderRecord> findCtoProjectTender(Long memberId, Integer status, Pager pager)
			throws EliteServiceException {
		this.verifyParams(memberId);
		SearchResult<ProjectTenderRecord> recordResult = this.projectTenderRecordRepository
				.findCtoProjectTender(memberId, status, pager);
		List<ProjectTenderRecord> records = recordResult.getResult();
		if (records.size() > 0) {
			for (int i = 0; i < records.size(); i++) {
				ProjectTenderRecord t = records.get(i);
				if (t.getProjectId() != null) {
					// cto项目质冻结质保金(定标的总金额乘以百分比除以总共的阶段)
					if (t.getProjectDefine() != null) {
						BigDecimal qualityAmount = t.getProjectDefine().getTotalAmount()
								.multiply(localCoreConfig.getQualityAmountPercent())
								.divide(new BigDecimal(t.getProjectDefine().getStages().size()), 2);
						t.setQualityAmount(qualityAmount);
					}
					Integer count = this.findProjectTenderRecordsByProjectId(t.getProjectId()).size();
					t.setCount(count);
				}
				records.set(i, t);
			}
		}
		return recordResult;
	}

	@Override
	public Integer findCtoProjectTenderInCount(Long memberId, Integer status) throws EliteServiceException {
		this.verifyParams(memberId, status);
		Search search = new Search(ProjectTenderRecord.class);
		search.addFilterEqual("memberId", memberId);
		// 如果未中标竞标中
		if (status == 0) {
			search.addFilterIn("status",
					Arrays.asList(ProjectTenderRecord_Status.tender_in, ProjectTenderRecord_Status.tender_not));

		} else {
			search.addFilterIn("status",
					Arrays.asList(ProjectTenderRecord_Status.tender_win, ProjectTenderRecord_Status.tender_normal));
		}
		return projectTenderRecordRepository.count(search);
	}

	@Override
	public ProjectTenderRecord findProjectTenderRecordByMemberId(Long projectId, Long memberId)
			throws EliteServiceException {
		this.verifyParams(memberId, projectId);
		Search search = new Search(ProjectTenderRecord.class);
		search.addFilterEqual("memberId", memberId);
		search.addFilterEqual("projectId", projectId);
		ProjectTenderRecord record = projectTenderRecordRepository.searchUnique(search);
		if (record != null) {
			Integer count = this.findProjectTenderRecordsByProjectId(projectId).size();
			record.setCount(count);
			if (record.getProjectDefine() != null) {
				BigDecimal qualityAmount = record.getProjectDefine().getTotalAmount()
						.multiply(localCoreConfig.getQualityAmountPercent())
						.divide(new BigDecimal(record.getProjectDefine().getStages().size()), 2);
				record.setQualityAmount(qualityAmount);
			}
		}
		/**
		 * if (status == 0) { search.addFilterIn("status",
		 * Arrays.asList(ProjectTenderRecord_Status.tender_in,
		 * ProjectTenderRecord_Status.tender_not));
		 * 
		 * } else { search.addFilterIn("status",
		 * Arrays.asList(ProjectTenderRecord_Status.tender_win,
		 * ProjectTenderRecord_Status.tender_normal)); }
		 **/
		return record;
	}

	@Override
	public ProjectTenderRecord findProjectTenderRecord(Long projectId, Long memberId) throws EliteServiceException {
		this.verifyParams(projectId, memberId);
		Search search = new Search(ProjectTenderRecord.class);
		search.addFilterEqual("memberId", memberId);
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("status", ProjectTenderRecord_Status.tender_normal);
		return projectTenderRecordRepository.searchUnique(search);
	}

	public List<Project> findProjectListByctoId(Long ctoId) throws EliteServiceException {
		this.verifyParams(ctoId);
		Search search = new Search();
		search.addFilterEqual("ctoId", ctoId);
		return this.projectRepository.search(search);
	}

	@Override
	public Boolean findProjectTenderWin(Long tenderId,ProjectTenderRecord_Status status) throws EliteServiceException {
		// TODO Auto-generated method stub
		Boolean returnWin = false;
		Search search = new Search();
		search.addFilterEqual("tenderId", tenderId);
		search.addFilterEqual("status", status);
		List<ProjectTender> list = this.projectTenderRecordRepository.search(search);
		if (list != null && list.size() > 0) {
			returnWin = true;
		}
		return returnWin;
	}

}

package com.ledao.elite.core.service.project.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.project.ProjectAtta;
import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Status;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectDefineStage.Stage_Status;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.domain.project.ProjectMaterial;
import com.ledao.elite.core.domain.project.ProjectMaterial.ProjectMaterial_Status;
import com.ledao.elite.core.domain.project.ProjectTender;
import com.ledao.elite.core.domain.project.ProjectTender.ProjectTender_Status;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.domain.project.ProjectTenderRecord.ProjectTenderRecord_Status;
import com.ledao.elite.core.domain.sys.CommonCode.COMMON_PREVAL;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.DictCache;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Data_Oper;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Status;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Way;
import com.ledao.elite.core.framework.constant.RoleEnum;
import com.ledao.elite.core.framework.dto.ProjectDetailInfo;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.framework.thread.SyncCTOProjectStageThread;
import com.ledao.elite.core.framework.thread.newly.PartnerCompanyEnterThread2;
import com.ledao.elite.core.repository.member.MemberIncomeRepository;
import com.ledao.elite.core.repository.member.MemberPassportRepository;
import com.ledao.elite.core.repository.project.ProjectAttaRepository;
import com.ledao.elite.core.repository.project.ProjectDefineRepository;
import com.ledao.elite.core.repository.project.ProjectRepository;
import com.ledao.elite.core.repository.project.ProjectTenderRecordRepository;
import com.ledao.elite.core.repository.project.ProjectTenderRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.project.ProjectDefineService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectMaterialService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectTenderService;
import com.ledao.elite.core.service.sys.CommonCodeService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.core.service.sys.SysUserService;
import com.ledao.elite.core.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("projectService")
public class ProjectServiceImpl extends BaseSerivceImpl implements ProjectService {

	@Resource
	private ProjectRepository projectRepository;
	@Resource
	private MemberIncomeRepository memberIncomeRepository;
	@Resource
	private ProjectAttaRepository projectAttaRepository;
	@Resource
	private ProjectDefineRepository projectDefineRepository;
	@Resource
	private ProjectTenderRecordRepository projectTenderRecordRepository;
	@Resource
	private CommonCodeService commonCodeService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private DictService dictService;
	@Resource
	private ProjectDefineService projectDefineService;
	@Resource
	private ProjectTenderService projectTenderService;
	@Resource
	private ProjectTenderRepository projectTenderRepository;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private MemberCompanyService memberCompanyService;
	@Resource
	private MemberPassportRepository memberPassportRepository;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private LocalCoreConfig localCoreConfig;
	@Resource
	private EventPublishService eventPublishService;
	@Resource
	private ProjectMaterialService projectMaterialService;

	@Override
	public Project createProject(Project project) throws EliteServiceException {
		this.verifyParams(project, project.getName(), project.getProjectSolution());
		if (StringUtils.isNotEmpty(project.getAreaName())) {
			Long areaId = DictCache.getAreaMap(project.getAreaName());
			if (areaId == null) {
				throw new EliteServiceException("请选择一个国内的省市", ErrorCodeEnum.CITY_NOT_EXIST.code);
			}
			project.setAreaId(areaId);
		}
		String projectNum = commonCodeService.disposeOddNumber("projectNum", COMMON_PREVAL.P.name(), "yyyyMM", 4, null);
		List<String> colorList = this.localCoreConfig.getRandomColor();
		int size = colorList.size();
		int index = size <= 1 ? 0 : new Random().nextInt(size);
		String backgroundColor = colorList.get(index);
		// 自动分配BM
		Long bmId = this.sysUserService.findSysUserAsRole(RoleEnum.business_manager);
		log.info("新建项目：" + project.getName() + "自动分配BMID=" + bmId);
		project.setBmId(bmId);
		project.setProjectNum(projectNum);
		project.setBackgroundColor(backgroundColor);
		project.setStatus(Project.Project_Status.wait_audit);
		this.projectRepository.save(project);
		if (StringUtils.isNotEmpty(project.getAttaIds())) {
			saveProjectAtta(project);
		}
		return project;
	}

	@Override
	public Project createBmProject(Project project) throws EliteServiceException {
		this.verifyParams(project, project.getName(), project.getProjectSolution());
		if (StringUtils.isNotEmpty(project.getAreaName())) {
			Long areaId = DictCache.getAreaMap(project.getAreaName());
			if (areaId == null) {
				throw new EliteServiceException("请选择一个国内的省市", ErrorCodeEnum.CITY_NOT_EXIST.code);
			}
			project.setAreaId(areaId);
		}
		String projectNum = commonCodeService.disposeOddNumber("projectNum", COMMON_PREVAL.P.name(), "yyyyMM", 4, null);
		List<String> colorList = this.localCoreConfig.getRandomColor();
		int size = colorList.size();
		int index = size <= 1 ? 0 : new Random().nextInt(size);
		String backgroundColor = colorList.get(index);
		// 自动分配BM
		project.setProjectNum(projectNum);
		project.setBackgroundColor(backgroundColor);
		project.setStatus(Project.Project_Status.wait_audit);
		this.projectRepository.save(project);
		if (StringUtils.isNotEmpty(project.getAttaIds())) {
			saveProjectAtta(project);
		}
		return project;
	}

	@Override
	public Project updateProjectInfo(Project project) throws EliteServiceException {
		this.verifyParams(project, project.getId());
		if (StringUtils.isNotEmpty(project.getAreaName())) {
			Long areaId = DictCache.getAreaMap(project.getAreaName());
			if (areaId == null) {
				throw new EliteServiceException("请选择一个国内的省市", ErrorCodeEnum.CITY_NOT_EXIST.code);
			}
			project.setAreaId(areaId);
		}
		Project pojo = this.findProjectById(project.getId());
		if (!Project_Status.wait_audit.equals(pojo.getStatus()) && !Project_Status.unpass.equals(pojo.getStatus())
				&& !Project_Status.audit_in.equals(pojo.getStatus())
				&& !Project_Status.pass_wait.equals(pojo.getStatus())) {
			throw new EliteServiceException("项目已审核，不能进行修改", ErrorCodeEnum.OBJECT_AUDIT_UNUPDATE.code);
		}
		BeanUtils.copyProperties(project, pojo, new String[] { "projectNum", "status", "intentionAmount", "bmId",
				"pmId", "ctoId", "createId", "createTime", "backgroundColor" });
		String attaIds = toAttaIds(pojo);
		// 附件有变动则更新附件
		log.debug("项目旧附件ID：" + attaIds + "-------新附件ID：" + project.getAttaIds());
		if (!attaIds.equals(project.getAttaIds())) {
			this.projectAttaRepository.deleteProjectAtta(project.getId());
			saveProjectAtta(project);
		}
		// 审核未通过的更新为待审核
		if (Project_Status.unpass.equals(pojo.getStatus())) {
			pojo.setStatus(Project_Status.wait_audit);
		}
		this.projectRepository.save(pojo);
		return project;
	}
	
	@Override
	public void updateProject(Project project) throws EliteServiceException {
		this.verifyParams(project, project.getId());
		this.projectRepository.save(project);
	}
	
	@Override
	public Project mergeProjectInfo(Project project) throws EliteServiceException{
		this.verifyParams(project, project.getId());
		this.projectRepository.mergeProject(project);
		return project;
	}

	@Override
	public void updateProjectAsIntention(Long projectId, BigDecimal intentionAmount) throws EliteServiceException {
		this.verifyParams(projectId);
		Project project = this.projectRepository.find(projectId);
		if (project == null || Pay_Status.success.equals(project.getIntentionStatus()))
			return;
		project.setStatus(Project_Status.pass_wait);
		project.setIntentionStatus(Pay_Status.success);
		project.setIntentionAmount(intentionAmount);
		project.setIntentionTime(new Date());
		this.projectRepository.save(project);

		// 项目审核通过，通知渠道方
		// 通知渠道方
		MemberPassport memberPassport = memberPassportRepository.find(project.getCompanyId());
		PartnerCompanyEnterThread2 companyEnterThread = new PartnerCompanyEnterThread2();
		companyEnterThread.setAccount(memberPassport.getAccountOffSuffix());
		companyEnterThread.setContactPhone(project.getContactPhone());
		companyEnterThread.setInviteCode(project.getRecommendUser());
		companyEnterThread.setRecommdUser(project.getRecommendUser());
		companyEnterThread.setProjectId(project.getId());
		new Thread(companyEnterThread).start();
		
		String url = localCoreConfig.getDomainServer();
		// publish log event
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.company, projectId, null, null,
				project.getCompanyId(), "立项阶段", "我提交了意向金" + intentionAmount.doubleValue() + "元", null);
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.company, projectId, null, null,
				project.getCompanyId(), "立项阶段", "项目审核通过", null);

		// publish message event
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_SUBMIT_INTENTION_SUCCESS);
		String title = message.getKey();
		String replace = "<a href='" + url + "/project/" + project.getId() + "/index' target='_blank'>查看详情</a>";
		String content = String.format(message.getValue(), project.getName(), replace);
		eventPublishService.publishMessageEvent(project.getCompanyId(), projectId, null, title, content, false,
				MemberMessage_Type.project);
		String contentbm = String.format(message.getValue(), project.getName(),
				"<a href='" + url + "/manager/project/bm' target='_blank'>看看</a>");
		eventPublishService.publishSysMessageEvent(project.getBmId(), null, title, contentbm, false);
	}

	@Override
	public void updateProjectAsTrust(Long projectId, Long stageId, BigDecimal payAmount) throws EliteServiceException {
		this.verifyParams(projectId, stageId);
		Project project = this.projectRepository.find(projectId);
		if (project == null)
			return;
		ProjectDefine projectDefine = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.company,
				ProjectDefine_Status.normal);
		if (projectDefine == null)
			return;

		ProjectDefineStage stage = this.projectDefineStageService.findProjectDefineStageById(stageId);
		if (stage == null)
			return;
		if (Pay_Status.success.equals(stage.getPayStatus())) {
			throw new EliteServiceException("阶段金额已经支付成功过了", ErrorCodeEnum.PAY_EXIST.code);
		}
		project.setStatus(Project_Status.stage_course);// 进入阶段开发中，由CTO确认立项书开始
		this.projectRepository.save(project);

		stage.setCurrented(true);
		stage.setPayAmount(payAmount);
		stage.setTrusted(true);
		stage.setPayStatus(Pay_Status.success);
		stage.setStatus(Stage_Status.starting);
		stage.setPayWay(Pay_Way.online);
		this.projectDefineStageService.updateProjectDefineStage(stage);
		// 同步cto的立项阶段
		SyncCTOProjectStageThread syncCtoStage = new SyncCTOProjectStageThread();
		syncCtoStage.setProjectId(projectId);
		syncCtoStage.setCurrentStageCode(stage.getStageCode());
		new Thread(syncCtoStage).start();

		// publish log event
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.company, projectId, stage.getStageCode(), null,
				project.getCompanyId(), stage.getTitle(),
				"我提交了 " + stage.getTitle() + "款项" + payAmount.doubleValue() + "元", null);
		String url = localCoreConfig.getDomainServer();
		// publish message event

		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_SUBMIT_STAGECOST_SUCCESS);
		String replace = "<a href='" + url + "/project/" + project.getId() + "/index' target='_blank'>查看详情</a>";
		String content = String.format(message.getValue(), project.getName(), stage.getTitle(), replace);
		String ts = project.getName() + "项目" + stage.getTitle() + "阶段";

		eventPublishService.publishMessageEvent(project.getCompanyId(), projectId, null, message.getKey(), content,
				false, MemberMessage_Type.project);
		String contentCto = String.format(message.getValue(), project.getName(), stage.getTitle(),
				"<a href='" + url + "/project/c/" + project.getId() + "/index' target='_blank'>发布任务</a>");

		eventPublishService.publishMessageEvent(project.getCtoId(), projectId, null, message.getKey(), contentCto,
				false, MemberMessage_Type.project);
		eventPublishService.publishSysMessageEvent(project.getBmId(), null, ts + "费用已托管", ts + "费用已托管！", false);
	}

	@Override
	public Project updateProjectPrefect(Project project) throws EliteServiceException {
		this.verifyParams(project, project.getId(), project.getName());
		Project obj = this.projectRepository.find(project.getId());
		if (obj == null)
			throw new EliteServiceException("找不到项目信息", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		obj.setProductIndustry(project.getProductIndustry());
		obj.setBudgetMax(project.getBudgetMax());
		obj.setBudgetMin(project.getBudgetMin());
		obj.setName(project.getName());
		obj.setIntro(project.getIntro());
		obj.setDeferDay(project.getDeferDay());
		obj.setAreaName(project.getAreaName());
		obj.setProjectType(project.getProjectType());
		obj.setProductFunction(project.getProductFunction());
		obj.setProjectSolution(project.getProjectSolution());
		obj.setStartTime(project.getStartTime());
		obj.setExpectTime(project.getExpectTime());
		obj.setContactName(project.getContactName());
		obj.setContactPhone(project.getContactPhone());
		obj.setReferProject(project.getReferProject());
		obj.setPlaned(project.isPlaned());
		obj.setRecommendUser(project.getRecommendUser());
		if (Project_Status.wait_audit.name().equals(obj.getStatus().name())) {
			obj.setStatus(Project_Status.audit_in);
		}
		obj.setProjectBudget(project.getProjectBudget());
		this.updateProjectInfo(obj);
		return obj;
	}

	@Override
	public void removeProject(Long projectId) throws EliteServiceException {
		this.verifyParams(projectId);
		Project project = this.projectRepository.find(projectId);
		if (!Project_Status.wait_audit.equals(project.getStatus())) {
			throw new EliteServiceException("项目进行中，不能直接删除", ErrorCodeEnum.OBJECT_USE.code);
		}
		this.projectAttaRepository.deleteProjectAtta(projectId);
		this.projectRepository.removeById(projectId);
	}

	@Override
	public void updateProjectEntireAccept(Long id) throws EliteServiceException {
		this.verifyParams(id);
		Project obj = this.projectRepository.find(id);
		// 更新验收的状态，并且通知其他需要结算的服务
		if (obj == null)
			return;
		ProjectDefine define = obj.getProjectDefine();
		if (define == null)
			return;
		List<ProjectDefineStage> stages = define.getStages();
		if (stages.isEmpty())
			return;
		for (ProjectDefineStage pds : stages) {
			if (!Stage_Status.quality.equals(pds.getStatus())) {
				return;
			}
		}
		obj.setStatus(Project_Status.quality);
		this.projectRepository.save(obj);
	}
	
	@Override
	public void updateProjectBackIntentionAmount(Long projectId)throws EliteServiceException{
		this.verifyParams(projectId);
		Project obj = this.projectRepository.find(projectId);
		if(obj==null)return;
		if(!Pay_Status.success.equals(obj.getIntentionStatus()))return;
		BigDecimal amount=obj.getIntentionAmount();
		//退钱到会员账户
		this.memberAccountService.updateMemberAccountBalance(obj.getCompanyId(), amount, Data_Oper.sum);
		
		//更新项目意向金状态
		obj.setIntentionStatus(null);
		obj.setIntentionAmount(new BigDecimal(0));
		obj.setBackIntention(amount);
		this.projectRepository.save(obj);
		
	}

	@Override
	public void updateProjectStatusById(Long id, Project_Status status) throws EliteServiceException {
		this.verifyParams(id, status);
		Project obj = this.projectRepository.find(id);
		if (obj == null)
			return;
		obj.setStatus(status);
		this.projectRepository.save(obj);
	}

	@Override
	public void updateProjectStatusInById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		Project obj = this.projectRepository.find(id);
		if (obj == null) {
			throw new EliteServiceException("找不到项目信息", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		} else if (Project_Status.wait_audit.equals(obj.getStatus())) {
			obj.setStatus(Project_Status.audit_in);
			this.projectRepository.save(obj);
		}

	}

	@Override
	public void updateProjectAffirmDefinedAsCompany(Long projectId, boolean isAffirm) throws EliteServiceException {
		this.verifyParams(projectId);
		ProjectDefine define = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.company,
				ProjectDefine_Status.wait);
		if (define == null)
			throw new EliteServiceException("未找到对应的项目立项书", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		// 更新项目信息，已立项
		Project project = this.projectRepository.find(projectId);
		project.setDefineId(define.getId());
		project.setStatus(Project_Status.pass_already);
		project.setStockRate(define.getStock());// 更新股权
		project.setTotalAmount(define.getTotalAmount());// 更新总费用
		project.setDeliveryTime(define.getDeliveryTime());// 更新交付时间
		
		//更新是否托管
		if(define.getStock()!=null&&define.getStock().compareTo(BigDecimal.ZERO)==1){
			project.setTrustStocked(true);
		}
		
		this.projectRepository.save(project);

		// 标书确认
		define.setStatus(ProjectDefine_Status.normal);
		this.projectDefineService.updateProjectDefine(define);
	}

	@Override
	public void updateProjectAffirmDefinedAsCTO(Long projectId, Long memberId, boolean isAffirm)
			throws EliteServiceException {
		this.verifyParams(projectId);

		// 确认CTO
		Project project = this.projectRepository.find(projectId);
		project.setCtoId(memberId);
		projectRepository.save(project);

		ProjectDefine define = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.cto,
				ProjectDefine_Status.wait);
		if (define == null)
			throw new EliteServiceException("未找到对应的项目立项书", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		// 标书确认
		define.setAffirmTime(new Date());
		define.setStatus(ProjectDefine_Status.normal);
		this.projectDefineService.updateProjectDefine(define);

		// 更新投标记录，立项
		ProjectTender tender = this.projectTenderService.findProjectTenderByProjectId(projectId);
		if (tender == null)
			throw new EliteServiceException("未找到对应的招标记录", ErrorCodeEnum.OBJECT_NOT_EXIST.code);

		// 招标结束
		tender.setStatus(ProjectTender_Status.tender_stop);
		this.projectTenderService.updateProjectTender(tender);

		Search search = new Search();
		// search.addFilterEqual("recordId", tender.getId());
		search.addFilterEqual("projectId", projectId);
		search.addFilterEqual("memberId", memberId);
		ProjectTenderRecord tenderRecord = this.projectTenderRecordRepository.searchUnique(search);
		if (tenderRecord == null)
			throw new EliteServiceException("未找到您对应的投标记录", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		tenderRecord.setStatus(ProjectTenderRecord_Status.tender_normal);
		tenderRecord.setDefineId(define.getId());
		this.projectTenderRecordRepository.save(tenderRecord);

		// 保存定标文档到文件里面
		if (define.getAtta() != null) {
			ProjectMaterial material = new ProjectMaterial();
			material.setProjectId(project.getId());
			material.setReceiveId(memberId);
			material.setStageId(project.getForStage().getId());
			material.setAttaId(define.getAttaId());
			material.setDeliveryed(false);
			material.setStatus(ProjectMaterial_Status.pass);
			projectMaterialService.createProjectMaterial(material);
		}

		// 同步cto的立项阶段
		SyncCTOProjectStageThread syncCtoStage = new SyncCTOProjectStageThread();
		syncCtoStage.setProjectId(projectId);
		syncCtoStage.setConfirmDefine(true);
		new Thread(syncCtoStage).start();
	}

	@Override
	public Project updateProjectTrustStockAsCompany(Long projectId) throws EliteServiceException {
		this.verifyParams(projectId);
		Project project = this.projectRepository.find(projectId);
		if (project == null)
			return null;
		// 股权在确定立项书时定
		project.setTrustStocked(true);
		project.setStockTime(new Date());
		this.projectRepository.save(project);
		// 更新项目方托管股权的总数
		this.memberAccountService.updateMemberAccountTrustStock(project.getCompanyId(), project.getStockRate(),
				Data_Oper.sum);
		return project;
	}

	@Override
	public Project findProjectById(Long projectId) throws EliteServiceException {
		this.verifyParams(projectId);
		Project project = this.projectRepository.find(projectId);
		if (project != null) {
			project.setSolutionVals(dictService.findDictValsByKeys(Dict.Dict_Type.project_type.name(),
					project.getProjectSolution(), "+"));
			String attaIds = toAttaIds(project);
			project.setAttaIds(attaIds);
			if (project.getPmId() != null) {
				SysUser user = this.sysUserService.findSysUserById(project.getPmId());
				if (user != null)
					project.setPmName(user.getUserName());
			}
			if (project.getBmId() != null) {
				SysUser bmuser = this.sysUserService.findSysUserById(project.getBmId());
				if (bmuser != null) {
					project.setBmName(bmuser.getUserName());
					project.setBmPhone(bmuser.getPhone());
				}
			}
		}
		return project;
	}

	@Override
	public ProjectDetailInfo findProjectDetailById(Long projectId) throws EliteServiceException {
		Project project = this.findProjectById(projectId);

		if (project.getBmId() != null) {
			SysUser user = this.sysUserService.findSysUserById(project.getBmId());
			if (user != null)
				project.setBmName(user.getUserName());
		}
		if (project.getPmId() != null) {
			SysUser user = this.sysUserService.findSysUserById(project.getPmId());
			if (user != null)
				project.setPmName(user.getUserName());
		}
		/*
		 * ProjectDefine pd = project.getProjectDefine(); if (pd != null &&
		 * pd.getStages().size() > 0) { List<ProjectDefineStage> stageList =
		 * pd.getStages(); for (ProjectDefineStage s : stageList) { if
		 * (s.getStatus().name().equals(Stage_Status.starting.name())) {
		 * project.setCurrentStage(s); break; } } }
		 */
		ProjectDetailInfo detail = new ProjectDetailInfo(project);
		return detail;
	}

	@Override
	public ProjectDetailInfo findProjectDetailByIdAndType(Long projectId, String type) throws EliteServiceException {
		ProjectDetailInfo pInfo = this.findProjectDetailById(projectId);
		ProjectDefine_Type defineType;
		if (type == "bm") {
			defineType = ProjectDefine_Type.company;
		} else {
			defineType = ProjectDefine_Type.cto;
		}

		// 是否发过立项书，定标书
		List<ProjectDefine> defines = this.projectDefineService.findProjectDefinesByProjectId(projectId, defineType,
				"cancel");
		if (defines.size() > 0) {
			pInfo.setTendered(true);
		} else {
			pInfo.setTendered(false);
		}
		return pInfo;
	}

	@Override
	public Integer findProjectCountByCompanyId(Long memberId, Project_Status status) throws EliteServiceException {
		this.verifyParams(memberId, status);
		Search search = new Search();
		search.addFilterEqual("companyId", memberId);
		if (!Project_Status.audited.equals(status)) {
			search.addFilterEqual("status", status);
		} else {
			search.addFilterNotIn("status", Arrays.asList(Project_Status.wait_audit, Project_Status.audit_in));
		}
		return this.projectRepository.count(search);
	}

	@Override
	public List<Project> findProjectListByCompanyId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search = new Search();
		search.addFilterEqual("companyId", memberId);
		return this.projectRepository.search(search);
	}

	@Override
	public List<Project> findProjectListByStatus(Project_Status status) throws EliteServiceException {
		this.verifyParams(status);
		Search search = new Search();
		search.addFilterEqual("status", status);
		return this.projectRepository.search(search);
	}

	@Override
	public SearchResult<Project> findProjectList(Long memberId, String projectNum, String status, Pager pager)
			throws EliteServiceException {
		this.verifyParams(memberId);
		Search search = new Search(Project.class);
		search.addFilterEqual("companyId", memberId);
		if (StringUtils.isNotEmpty(projectNum)) {
			search.addFilterEqual("projectNum", projectNum);
		}
		// APP查询无状态
		if (StringUtils.isNotEmpty(status)) {
			if (!Project_Status.audited.name().equals(status)) {
				search.addFilterEqual("status", Project_Status.valueOf(Project_Status.class, status));
			} else {
				search.addFilterNotIn("status", Arrays.asList(Project_Status.wait_audit, Project_Status.audit_in));
			}
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		SearchResult<Project> sr = this.projectRepository.searchAndCount(search);
		return sr;
	}

	@Override
	public SearchResult<ProjectDetailInfo> findManagerProjectList(Long userId, RoleEnum role, String keyword,
			String status, Date startTime, Date endTime, Long holdBmId, Long holdPmId, String productIndustry,
			String productFunction, String projectSolution, String contactName, String contactPhone, Pager pager)
			throws EliteServiceException {
		this.verifyParams(userId, role);
		ProjectDefine_Type defineType = null;
		Search search = new Search(Project.class);
		switch (role) {
		case business_manager:
			search.addFilterEqual("bmId", userId);
			defineType = ProjectDefine_Type.company;
			break;
		case project_mamager:
			search.addFilterEqual("pmId", userId);
			defineType = ProjectDefine_Type.cto;
			break;
		default:
			break;
		}
		if (StringUtils.isNotEmpty(keyword)) {
			search.addFilterOr(new Filter("name", "%" + keyword + "%", Filter.OP_LIKE),
					new Filter("subName", "%" + keyword + "%", Filter.OP_LIKE),
					new Filter("projectNum", "%" + keyword + "%", Filter.OP_LIKE));
		}
		if (StringUtils.isNotEmpty(status)) {
			search.addFilterEqual("status", Project_Status.valueOf(Project_Status.class, status));
		}else{
			search.addFilterNotIn("status", Project_Status.invalid);
		}
		if (startTime != null) {
			search.addFilterGreaterOrEqual("createTime", startTime);
		}
		if (endTime != null) {
			search.addFilterLessOrEqual("createTime",endTime);
		}
		if (holdBmId != null) {
			search.addFilterEqual("bmId", holdBmId);
		}
		if (holdPmId != null) {
			search.addFilterEqual("pmId", holdPmId);
		}
		if (StringUtils.isNotEmpty(productIndustry)) {
			search.addFilterIn("productIndustry", CommonUtils.separatorStrToList(productIndustry,
					GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR));
		}
		if (StringUtils.isNotEmpty(productFunction)) {
			search.addFilterIn("productFunction", CommonUtils.separatorStrToList(productFunction,
					GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR));
		}
		if (StringUtils.isNotEmpty(projectSolution)) {
			search.addFilterIn("projectSolution", CommonUtils.separatorStrToList(projectSolution,
					GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR));
		}
		if (StringUtils.isNotEmpty(contactName)) {
			search.addFilterEqual("contactName", contactName);
		}
		if (StringUtils.isNotEmpty(contactPhone)) {
			search.addFilterEqual("contactPhone", contactPhone);
		}
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		// 结果转换成项目详细信息
		SearchResult<Project> sr = this.projectRepository.searchAndCount(search);
		SearchResult<ProjectDetailInfo> result = new SearchResult<>();
		List<Project> list = sr.getResult();
		List<ProjectDetailInfo> resultList = new ArrayList<>();
		ProjectDetailInfo pInfo = null;
		for (Project p : list) {
			p.setSolutionVals(
					dictService.findDictValsByKeys(Dict.Dict_Type.project_type.name(), p.getProjectSolution(), "+"));
			pInfo = new ProjectDetailInfo(p);
			if (pInfo.getBmId() != null) {
				SysUser user = this.sysUserService.findSysUserById(pInfo.getBmId());
				if (user != null)
					pInfo.setBmName(user.getUserName());
			}
			if (pInfo.getPmId() != null) {
				SysUser user = this.sysUserService.findSysUserById(pInfo.getPmId());
				if (user != null)
					pInfo.setPmName(user.getUserName());
			}
			if (pInfo.getCompanyId() != null) {
				MemberCompany company = this.memberCompanyService.findMemberCompanyByMemberId(pInfo.getCompanyId());
				if (company != null) {
					pInfo.setAuditedMemberCompany(company.getStatus().name());
				}
			}
			if (pInfo.getCtoId() != null) {
				MemberPassport pass = this.memberPassportRepository.find(pInfo.getCtoId());
				if (pass != null) {
					pInfo.setCTOName(pass.getNickName());
					pInfo.setCTOPhone(pass.getAccount());
				}
			}

			List<ProjectDefine> defines = this.projectDefineService.findProjectDefinesByProjectId(p.getId(), defineType,
					"cancel");
			if (defines.size() > 0) {
				pInfo.setTendered(true);
			} else {
				pInfo.setTendered(false);
			}
			MemberPassport pass = this.memberPassportRepository.find(pInfo.getCompanyId());
			pInfo.setContactName(pass.getNickName());
			pInfo.setContactPhone(pass.getAccount());
			resultList.add(pInfo);
		}
		result.setTotalCount(sr.getTotalCount());
		result.setResult(resultList);
		return result;
	}

	@Override
	public SearchResult<Project> findProjectListAsHall(String projectSolution, String status, Pager pager)
			throws EliteServiceException {
		if (pager == null) {
			pager = new Pager(0, 12);
		}
		Search search = new Search(Project.class);
		if (StringUtils.isNotEmpty(projectSolution)) {
			search.addFilterIn("projectSolution", CommonUtils.separatorStrToList(projectSolution,
					GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR));
		}
		if (StringUtils.isNotEmpty(status)) {
			search.addFilterEqual("status", Project_Status.valueOf(Project_Status.class, status));
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		return this.projectRepository.searchAndCount(search);
	}

	private String toAttaIds(Project project) {
		List<ProjectAtta> attas = project.getAttas();
		String attaIds = "";
		if (attas != null && !attas.isEmpty()) {
			for (ProjectAtta ps : attas) {
				attaIds += ps.getAttaId() + ",";
			}
			attaIds = StringUtils.chop(attaIds);
		}
		return attaIds;
	}

	private void saveProjectAtta(Project project) {
		String[] attaIdArray = StringUtils.split(project.getAttaIds(),
				GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		if (attaIdArray == null)
			return;
		for (String attaId : attaIdArray) {
			this.projectAttaRepository.save(new ProjectAtta(project.getId(), Long.parseLong(attaId),
					GlobalDefinedConstant.System_Status.normal.name(), project.getCreateId()));
		}
	}

	@Override
	public Project createProjectDefinesAndDefinesStages(ProjectDefine define, List<ProjectDefineStage> defineStages,
			Project project, Long projectTenderRecordId) throws EliteServiceException {
		define.setStock(define.getStock().divide(new BigDecimal("100")));
		ProjectDefine returnObj = this.projectDefineService.createProjectDefine(define);
		if (defineStages.size() > 0) {
			for (int i = 0; i < defineStages.size(); i++) {
				defineStages.get(i).setDefineId(define.getId());
				this.projectDefineStageService.createProjectDefineStage(defineStages.get(i));
			}
		}

		Project obj = this.findProjectById(project.getId());
		obj.setSendDefined(true);
		obj.setStockRate(define.getStock());
		this.projectRepository.save(obj);
		String url = localCoreConfig.getDomainServer();
		// 发立项书（定标书）给cto cto竞标列表状态改变
		if (projectTenderRecordId != null) {
			ProjectTenderRecord record = this.projectTenderRecordRepository.find(projectTenderRecordId);
			record.setDefineId(returnObj.getId());
			record.setSendDefined(true);
			this.projectTenderRecordRepository.save(record);

			// 定标书通知
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_RECEIVE_DEFINE);
			String replace = "<a href='" + url + "/project/c/" + project.getId() + "/index' target='_blank'>查看详情</a>";
			String content = String.format(message.getValue(), obj.getName(), replace);
			eventPublishService.publishMessageEvent(record.getMemberId(), define.getProjectId(), null, message.getKey(),
					content, true, MemberMessage_Type.project);

		} else {
			// 立项书通知，
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_RECEIVE_DEFINE);
			String replace = "<a href='" + url + "/project/" + project.getId() + "/index' target='_blank'>查看详情</a>";
			String content = String.format(message.getValue(), obj.getName(), replace);
			eventPublishService.publishMessageEvent(obj.getCompanyId(), define.getProjectId(), null, message.getKey(),
					content, true, MemberMessage_Type.project);
		}
		return obj;
	}

	@Override
	public void createProjectTender(Project project, ProjectTender obj) throws EliteServiceException {

		Project pro = this.findProjectById(obj.getProjectId());
		pro.setExpectTime(obj.getEndTime());
		pro.setName(project.getName());
		pro.setIntro(project.getIntro());
		pro.setReferProject(project.getReferProject());
		this.projectRepository.save(pro);
		if (obj.getStockRate() == null) {
			obj.setStockRate("0");
		}
		this.projectTenderService.createProjectTender(obj);
	}

	@Override
	public void createResetProjectTender(Project project, ProjectTender obj) throws EliteServiceException {
		ProjectTender tender = this.projectTenderService.findProjectTenderByProjectId(obj.getProjectId());
		if (tender != null) {
			if (tender.getStatus().equals(ProjectTender_Status.tender_in)) {
				tender.setStatus(ProjectTender_Status.tender_cancel);
				this.projectTenderService.updateProjectTender(tender);
			}
		}
		this.createProjectTender(project, obj);

	}

	@Override
	public Project updateProjectAudit(Project project) throws EliteServiceException {
		this.verifyParams(project.getId());
		Project obj = this.findProjectById(project.getId());

		//在 审核通过待立项情况下，审评项目不通过 返还意向金 
		if(Project_Status.unpass.equals(project.getStatus())&&obj.getStatus().equals(Project_Status.pass_wait)){
			this.updateProjectBackIntentionAmount(project.getId());
		}
			
		obj.setStatus(project.getStatus());
		obj.setAuditId(project.getAuditId());
		obj.setAuditReason(project.getAuditReason());
		this.projectRepository.save(obj);
		return obj;
	}

	@Override
	public Project updateProjectPassToUnPass(Long id, Long auditId, String auditReason) throws EliteServiceException {
		this.verifyParams(id);
		Project obj = this.findProjectById(id);
		obj.setStatus(Project_Status.unpass);
		obj.setAuditId(auditId);
		obj.setAuditTime(new Date());
		obj.setAuditReason(auditReason);
		if (Pay_Status.success.equals(obj.getIntentionStatus())) {
			BigDecimal amount = obj.getIntentionAmount();
			obj.setBackIntention(amount);
			// 更新账户余额
			this.memberAccountService.updateMemberAccountBalance(obj.getCompanyId(), amount, Data_Oper.sum);
		}
		this.projectRepository.save(obj);
		return obj;
	}

	@Override
	public SearchResult<Project> findProjectListByReceivable(String keyword, Pager pager) throws EliteServiceException {
		Search search = new Search(Project.class);
		if (StringUtils.isNotEmpty(keyword)) {
			search.addFilterOr(new Filter("name", "%" + keyword + "%", Filter.OP_LIKE),
					new Filter("subName", "%" + keyword + "%", Filter.OP_LIKE),
					new Filter("projectNum", "%" + keyword + "%", Filter.OP_LIKE));
		}
		search.addFilterEqual("intentionStatus", Pay_Status.success);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		search.addSort("createTime", true);
		return this.projectRepository.searchAndCount(search);

	}

	@Override
	public SearchResult<Project> findProjectListByPayable(String keyword, Pager pager) throws EliteServiceException {
		Search search = new Search(Project.class);
		if (StringUtils.isNotEmpty(keyword)) {
			search.addFilterOr(new Filter("name", "%" + keyword + "%", Filter.OP_LIKE),
					new Filter("subName", "%" + keyword + "%", Filter.OP_LIKE),
					new Filter("projectNum", "%" + keyword + "%", Filter.OP_LIKE));
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		search.addSort("createTime", true);
		// 应付管理确认定标书后的project
		search.addFilterNotNull("ctoId");
		SearchResult<Project> sr = this.projectRepository.searchAndCount(search);
		List<Project> projectList = sr.getResult();
		for (int i = 0; i < projectList.size(); i++) {
			// 获取定标书
			ProjectDefine define = this.projectDefineService.findProjectDefine(projectList.get(i).getId(),
					ProjectDefine_Type.cto, ProjectDefine_Status.normal);
			projectList.get(i).setProjectDefine(define);
		}
		sr.setResult(projectList);
		return sr;

	}

	@Override
	public Project findProjectDetailByPayable(Long id) throws EliteServiceException {
		this.verifyParams(id);
		Project project = this.findProjectById(id);
		if (project.getCtoId() != null) {
			MemberPassport member = this.memberPassportRepository.find(project.getCtoId());
			if (member != null) {
				project.setCTOName(member.getNickName());
				project.setCtoPhone(member.getAccount());
			}
		}
		// 获取定标书
		List<ProjectDefine> defines = this.projectDefineService.findProjectDefinesByProjectId(project.getId(),
				ProjectDefine_Type.cto, "cancel");
		if (defines.size() > 0) {
			project.setProjectDefine(defines.get(0));
		}
		return project;
	}

	@Override
	public List<Project> findProjectListByctoId(Long ctoId) throws EliteServiceException {
		this.verifyParams(ctoId);
		Search search = new Search();
		search.addFilterEqual("ctoId", ctoId);
		return this.projectRepository.search(search);
	}

	@Override
	public int findProjectDoingCountBy(Long memberId) throws EliteServiceException {
		Search search = new Search();
		search.addFilterEqual("companyId", memberId);
		search.addFilterNotIn("status", Project_Status.finish, Project_Status.unpass);
		List<Project> projects = this.projectRepository.search(search);
		return projects.size();
	}

	public SearchResult<Project> findProjectListByMemberPartnerId(Long partnerId,Long memberId,String incomeType, Pager pager) {
		this.verifyParams(partnerId, pager);
		SearchResult<Project> sr = new SearchResult<>();
		List<Project> list = this.projectRepository.findProjectListByMemberPartnerId(partnerId, null, pager);
		for(int i=0;i<list.size();i++){
			BigDecimal partnerIncome=this.memberIncomeRepository.findMemberIncomeSumByMemberIdAndProjectId(memberId, list.get(i).getId(),incomeType,null,null);
			list.get(i).setPartnerIncome(partnerIncome);
		}
		Integer totalCount = this.projectRepository.findProjectListCountByMemberPartnerId(partnerId, null);
		sr.setTotalCount(totalCount);
		sr.setResult(list);
		return sr;
	}

	@Override
	public Date findparojectListByCtoId(Long ctoId) throws EliteServiceException {
		// TODO Auto-generated method stub
		List<ProjectDefine> defines=this.projectDefineRepository.findProjectDefineListByCtoId(ctoId);
		if(defines.size()>0){
			Date affrmTime=defines.get(0).getAffirmTime();
			return affrmTime;
		}
		return null;
	}

}

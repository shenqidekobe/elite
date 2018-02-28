package com.ledao.elite.core.framework.thread.settle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberIncome;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Type;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectDefineStage.Stage_Status;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Data_Oper;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.platform.PlatformOutOrderService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.project.ProjectStageTaskService;
import com.ledao.elite.core.service.project.ProjectTenderRecordService;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 项目方验收阶段的结算算法
 * */
@Slf4j
@Component("acceptStageSettlement")
public class AcceptStageSettlement{
	
	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private ProjectStageTaskService projectStageTaskService;
	@Resource
	private ProjectTenderRecordService projectTenderRecordService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private MemberIncomeService memberIncomeService;
	@Resource
	private PlatformOutOrderService platformOutOrderService;
	@Resource
	private PlatformFundService platformFundService;
	@Resource
	private EventPublishService eventPublishService;
	@Resource
	private LocalCoreConfig localCoreConfig;
	
	
	public void settle(Long stageId,boolean acceptFlag,String acceptReason){
		if (stageId == null)
			return;
		log.info("**************************************验收阶段线程【"+stageId+"】开始启动处理**************************************");
		ProjectDefineStage projectDefineStage = this.projectDefineStageService.findProjectDefineStageById(stageId);
		if (projectDefineStage == null)
			return;
		if (acceptFlag) {
			projectDefineStage.setStatus(Stage_Status.finish);// 验收阶段，直接更新为已完成
		} else {
			projectDefineStage.setStatus(Stage_Status.accept_fail);
			projectDefineStage.setAcceptReason(acceptReason);
		}
		projectDefineStage.setCurrented(false);
		projectDefineStage.setAcceptTime(new Date());
		this.projectDefineStageService.updateProjectDefineStage(projectDefineStage);

		String stageCode = projectDefineStage.getStageCode();
		Long projectId = projectDefineStage.getProjectId();
		BigDecimal stageAmount = projectDefineStage.getAmount();
		Project project = this.projectService.findProjectById(projectId);
		if (project == null)
			return;
		String url = localCoreConfig.getDomainServer();
		// 验证是否是项目的最后一个阶段，是则验收整个项目
		List<ProjectDefineStage> pdsList = this.projectDefineStageService
				.findProjectDefineStageByDefinedId(project.getDefineId());
		int index = pdsList.size() - 1;
		ProjectDefineStage finallyStage = pdsList.get(index);
		boolean firstStage=false;//是否第一阶段
		boolean lastStage=false;//是否最后一个阶段
		if(stageId.equals(pdsList.get(0).getId())){
			firstStage=true;
		}
		if (stageId.equals(finallyStage.getId())) {
			log.info("项目【"+projectId+"】的最后一个阶段【"+finallyStage.getTitle()+"】进行验收");
			lastStage=true;
			// 最后一个阶段
			project.setGuaranteeTime(localCoreConfig.getProjectQualityTime());
			project.setStatus(Project_Status.quality);
			project.setAcceptTime(new Date());
			this.projectService.mergeProjectInfo(project);
			// publish log event
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.company, projectId, projectDefineStage.getStageCode(), null,
					project.getCompanyId(), finallyStage.getTitle(), "我验收通过了 " + project.getName(), null);

			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_ACCEPT_SUCCESS);
			String replace = "<a href='" + url + "/project/" + project.getId() + "/index' target='_blank'>查看详情</a>";
			String content = String.format(message.getValue(), project.getName(), projectDefineStage.getTitle(),
					replace);
			// publish message event
			eventPublishService.publishMessageEvent(project.getCompanyId(), projectId, null, message.getKey(), content,
					false, MemberMessage_Type.project);
		} else {
			// publish log event
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_STAGE_ACCEPT_SUCCESS);
			String title = message.getKey();
			String replace = "<a href='" + url + "/project/" + project.getId() + "/index' target='_blank'>查看详情</a>";
			String content = String.format(message.getValue(), project.getName(), projectDefineStage.getTitle(),
					projectDefineStage.getTitle(), replace);
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.company, projectId, projectDefineStage.getStageCode(), null,
					project.getCompanyId(), projectDefineStage.getTitle(), content, null);
			// publish message event
			eventPublishService.publishMessageEvent(project.getCompanyId(), projectId, null, title, content, false,
					MemberMessage_Type.project);

			StringKeyValue messageCTO = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_STAGE_ACCEPT_SUCCESS);
			String titleCTO = String.format(messageCTO.getKey(), project.getName(), projectDefineStage.getTitle());
			String replaceCTO = "<a href='" + url + "/member/index?rp=settlement' target='_blank'>查看详情</a>";
			String contentCTO = String.format(messageCTO.getValue(), project.getName(), projectDefineStage.getTitle(),
					replaceCTO);
			eventPublishService.publishMessageEvent(project.getCtoId(), projectId, null, titleCTO, contentCTO, false,
					MemberMessage_Type.project);
		}
		// 获取cto的立项书ID
		Long ctoId = project.getCtoId();
		ProjectTenderRecord ptr = this.projectTenderRecordService.findProjectTenderRecord(projectId, ctoId);
		if (ptr == null || ptr.getDefineId() == null)
			return;
		Long ctoDefineId = ptr.getDefineId();
		log.info("项目ID：" + projectId + ",竞标成功的CTO：" + ctoId + "，其竞标书ID：" + ctoDefineId + "，验收阶段code：" + stageCode);
		// 获取CTO的立项阶段
		ProjectDefineStage ctoPds = this.projectDefineStageService.findProjectDefineStageByCode(projectId, ctoDefineId,
				stageCode);
		if (ctoPds == null)
			return;
		// 第一步：精英任务结算{精英任务前提需通过CTO验收}
		Long ctoStageId = ctoPds.getId();
		List<ProjectStageTask> taskList = this.projectStageTaskService.findProjectStageTask(ctoStageId,null);
		BigDecimal eliteAmount = new BigDecimal(0);
		for (ProjectStageTask psk : taskList) {
			/*
			 * if(!psk.getStatus().equals(ProjectTask_Status.wait_accept)){
			 * continue; }
			 */	
			BigDecimal taskAmount=psk.getAmount();//任务总金额
			Long memberId = psk.getEliteMemberId();
			log.info("开始结算任务编号："+psk.getTaskNum()+"的精英人才【"+memberId+"】收益******acceptFlag："+acceptFlag);
			if (acceptFlag) {
				eliteAmount = eliteAmount.add(taskAmount);
				BigDecimal guaranteeAmount = psk.getAmount().multiply(localCoreConfig.getQualityAmountPercent());
				psk.setStatus(ProjectTask_Status.quality);
				psk.setGuaranteeAmount(guaranteeAmount);
				Integer guaranteeTime = DateTimeUtils.taskQuaTime(psk.getDeliveryTime(), psk.getStartTime());// 质保时间
				psk.setGuaranteeTime(guaranteeTime);
				psk.setAcceptTime(new Date());

				// 更新会员账户余额
			
				BigDecimal settleAmount = new BigDecimal(0);
				settleAmount = taskAmount.subtract(guaranteeAmount);//结算金额
				BigDecimal tax=CommonUtils.calculateAmountTax(taskAmount);//税额(按总额计算)
				BigDecimal atIncome=settleAmount.subtract(tax);//税后收入=任务阶段金额-税
				
				this.memberAccountService.updateMemberAccountBalance(memberId, atIncome, guaranteeAmount, Data_Oper.sum,
						Data_Oper.sum);

				// 更新会员收益账单
				this.memberIncomeService.createMemberIncome(
						new MemberIncome(memberId, projectId, Income_Type.task, atIncome,tax,null, "任务【"+psk.getName()+"】结算报酬", null,ctoStageId,psk.getId()));

				// 增加平台出账订单
				this.platformOutOrderService.createPlatformOutOrder(memberId, PlatformOutOrder_Type.task_income,
						projectId, ctoStageId, psk.getId(), atIncome,tax);
				
				//添加平台流水记录
				this.platformFundService.createPlatformFund("任务【"+psk.getName()+"】的收益", psk.getId().toString(), projectId, ctoStageId, memberId, atIncome, PlatformFund_Type.income, PlatformFund_Status.success);

				// 通知渠道方结算精英的提成
				eventPublishService.publishPartnerSettleEvent(memberId, MemberIdentity_Type.elite, settleAmount, projectId, ctoStageId,
						psk.getId(), firstStage, lastStage, MemberIdentity_Type.partnerElite);
			/*	PartnerEaringsThread2 partnerEarningsThread = new PartnerEaringsThread2();
				partnerEarningsThread.setMemberId(memberId);
				partnerEarningsThread.setMemberType(MemberIdentity_Type.elite);
				partnerEarningsThread.setTotalAmount(settleAmount);
				partnerEarningsThread.setProjectId(projectId);
				partnerEarningsThread.setStageId(ctoStageId);
				partnerEarningsThread.setTaskId(psk.getId());
				partnerEarningsThread.setPartnerType(MemberIdentity_Type.partnerElite);
				new Thread(partnerEarningsThread).start();*/
			} else {
				psk.setStatus(ProjectTask_Status.accept_fail);
				psk.setAcceptReason(acceptReason);
			}
			this.projectStageTaskService.updateProjectStageTask(psk);
		}
		// 第二步：CTO项目结算
		if (acceptFlag) {
			log.info("开始结算CTO【"+ctoId+"】的项目阶段："+ctoPds.getTitle()+"的收益******");
			BigDecimal totalAmount = ctoPds.getAmount();
			BigDecimal settleAmount = totalAmount.subtract(eliteAmount);//结算总金额
			BigDecimal guaranteeAmount = settleAmount.multiply(localCoreConfig.getQualityAmountPercent());
			// 更新cto账户余额
			BigDecimal tax=CommonUtils.calculateAmountTax(settleAmount);//税额(阶段剩余总额)
			settleAmount = settleAmount.subtract(guaranteeAmount);
			BigDecimal atIncome=settleAmount.subtract(tax);//税后收入=本金-税
			
			this.memberAccountService.updateMemberAccountBalance(ctoId, atIncome, guaranteeAmount, Data_Oper.sum,
					Data_Oper.sum);

			ctoPds.setStatus(Stage_Status.finish);// 阶段状态直接更新为完成
			ctoPds.setGuaranteeAmount(guaranteeAmount);
			ctoPds.setAcceptTime(new Date());
			// 更新cto收益账单
			this.memberIncomeService.createMemberIncome(
					new MemberIncome(ctoId, projectId, Income_Type.stage, atIncome,tax, null,"项目阶段【"+ctoPds.getTitle()+"】的结算报酬", null,ctoStageId,null));

			// 增加平台出账订单
			this.platformOutOrderService.createPlatformOutOrder(ctoId, PlatformOutOrder_Type.task_income, projectId,
					ctoStageId, null, atIncome,tax);
			
			//添加平台流水记录
			this.platformFundService.createPlatformFund("项目阶段【"+ctoPds.getTitle()+"】的收益", ctoPds.getId().toString(), projectId, ctoStageId, ctoId, atIncome, PlatformFund_Type.income, PlatformFund_Status.success);

			// 通知渠道方结算CTO的提成
			eventPublishService.publishPartnerSettleEvent(ctoId, MemberIdentity_Type.cto, settleAmount, projectId, ctoStageId, null, firstStage, lastStage, MemberIdentity_Type.partnerElite);
			
		/*	PartnerEaringsThread2 partnerEarningsThread = new PartnerEaringsThread2();
			partnerEarningsThread.setMemberId(ctoId);
			partnerEarningsThread.setMemberType(MemberIdentity_Type.cto);
			partnerEarningsThread.setTotalAmount(settleAmount);
			partnerEarningsThread.setProjectId(projectId);
			partnerEarningsThread.setStageId(ctoStageId);
			partnerEarningsThread.setPartnerType(MemberIdentity_Type.partnerElite);
			new Thread(partnerEarningsThread).start();*/
		} else {
			ctoPds.setStatus(Stage_Status.accept_fail);
			ctoPds.setAcceptReason(acceptReason);
		}
		ctoPds.setCurrented(false);
		this.projectDefineStageService.updateProjectDefineStage(ctoPds);

		// 更新项目方的托管费用，验收后此阶段费用减去(扣除托管的总费用)
		log.info("项目方:"+project.getCompanyId()+"验收结束，开始扣除项目方的托管费用："+stageAmount);
		this.memberAccountService.updateMemberAccountTrustAmount(project.getCompanyId(), stageAmount, Data_Oper.sub);

		if (acceptFlag) {
			// 通知项目渠道方阶段
			eventPublishService.publishPartnerSettleEvent(project.getCompanyId(), MemberIdentity_Type.cto, projectDefineStage.getAmount(), projectId, stageId, null,
					firstStage, lastStage, MemberIdentity_Type.partnerCompany);
			/*PartnerEaringsThread2 partnerEarningsThread = new PartnerEaringsThread2();
			partnerEarningsThread.setMemberId(project.getCompanyId());
			// BigDecimal
			// quamount=projectDefineStage.getGuaranteeAmount()==null?new
			// BigDecimal(0):projectDefineStage.getGuaranteeAmount();
			partnerEarningsThread.setTotalAmount(projectDefineStage.getAmount());// 不扣质保金，全阶段金额结算
			partnerEarningsThread.setProjectId(projectId);
			partnerEarningsThread.setMemberType(MemberIdentity_Type.cto);
			partnerEarningsThread.setStageId(stageId);
			partnerEarningsThread.setLastStage(lastStage);
			partnerEarningsThread.setFirstStage(firstStage);
			partnerEarningsThread.setPartnerType(MemberIdentity_Type.partnerCompany);
			new Thread(partnerEarningsThread).start();*/
		}

		log.info("**************************************验收阶段线程开处理结束**************************************");
	}
	
}

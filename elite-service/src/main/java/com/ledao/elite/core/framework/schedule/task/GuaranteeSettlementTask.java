package com.ledao.elite.core.framework.schedule.task;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.ledao.elite.core.domain.project.ProjectStageTask;
import com.ledao.elite.core.domain.project.ProjectStageTask.ProjectTask_Status;
import com.ledao.elite.core.domain.project.ProjectTenderRecord;
import com.ledao.elite.core.exception.EliteBusinessException;
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
import com.ledao.elite.core.utils.DateTimeUtils;

/**
 * 质保结算任务处理， 1、项目的质保期到更新项目状态和各个到账的钱 2、所有的质保期内任务更新状态和任务至抱歉的钱到精英账户
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Component
public class GuaranteeSettlementTask extends ExpireAbstractTask {

	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectTenderRecordService projectTenderRecordService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private ProjectStageTaskService projectStageTaskService;
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
	protected LocalCoreConfig localCoreConfig;

	@Override
	public void execute() throws EliteBusinessException {
		log.info("*****质保到期定时任务处理开始执行*****");
		// 所有的任务质保验收{按照阶段ID查询}
		List<ProjectStageTask> taskList = projectStageTaskService.findProjectStageTaskByStatus(null,
				ProjectTask_Status.quality);
		if (taskList.isEmpty())
			return;
		for (ProjectStageTask task : taskList) {
			Date aTime = task.getAcceptTime();
			try {
				aTime=aTime==null?null:DateTimeUtils.dayFormat.parse(DateTimeUtils.dayFormat.format(aTime));
			} catch (ParseException e) {}
			Integer qCount = task.getGuaranteeTime();
			boolean tflag = DateTimeUtils.timeIsExpire(aTime, qCount);
			if (!tflag)
				continue;
			task.setStatus(ProjectTask_Status.finish);
			this.projectStageTaskService.updateProjectStageTask(task);// 更新任务

			BigDecimal eliteQuaAmount = task.getGuaranteeAmount();
			if(eliteQuaAmount==null)continue;
			
			Long eliteId = task.getEliteMemberId();
			log.info("任务：" + task.getId()+"【"+task.getName() + "】的质保期结束，精英ID:" + eliteId + ",得到质保金：" + eliteQuaAmount);
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_TASK_QUALITY_END);
			String title = String.format(message.getKey(), task.getName());
			String url = localCoreConfig.getDomainServer();
			String replace = "<a href='" + url + "/member/index' target='_blank'>去看看</a>";
			String content = String.format(message.getValue(),task.getName(),replace);
			eventPublishService.publishSysMessageEvent(task.getCreateId(), null, title, content,false);
			eventPublishService.publishSysMessageEvent(eliteId, null, title, content, false);

			BigDecimal tax=new BigDecimal(0);//质保金不扣税
			BigDecimal atIncome=eliteQuaAmount.subtract(tax);//税后收入=本金-税
			// 更新精英账户余额，扣减被压制的质保金
			this.memberAccountService.updateMemberAccountBalance(eliteId, atIncome, eliteQuaAmount, Data_Oper.sum,
					Data_Oper.sub);
			// 更新会员收益账单
			this.memberIncomeService.createMemberIncome(
					new MemberIncome(eliteId, task.getProjectId(), Income_Type.guarantee, atIncome,tax,null,"任务结算的质保金报酬", null,null,task.getId()));
			// 增加平台出账订单
			this.platformOutOrderService.createPlatformOutOrder(eliteId, PlatformOutOrder_Type.guarantee_income,
					task.getProjectId(), task.getStageId(), task.getId(), atIncome,tax);
			
			//添加平台流水记录
			this.platformFundService.createPlatformFund("任务【"+task.getName()+"】的质保金", task.getId().toString(), task.getProjectId(), task.getStageId(), eliteId, atIncome, PlatformFund_Type.guarantee, PlatformFund_Status.success);

			// 通知渠道方结算精英的提成
			eventPublishService.publishPartnerSettleEvent(eliteId, MemberIdentity_Type.elite, eliteQuaAmount, 
					task.getProjectId(), task.getStageId(), task.getId(), false, false, MemberIdentity_Type.partnerElite);
			/*PartnerEaringsThread2 partnerEarningsThread = new PartnerEaringsThread2();
			partnerEarningsThread.setMemberId(eliteId);
			partnerEarningsThread.setMemberType(MemberIdentity_Type.elite);
			partnerEarningsThread.setTotalAmount(eliteQuaAmount);
			partnerEarningsThread.setProjectId(task.getProjectId());
			partnerEarningsThread.setStageId(task.getStageId());
			partnerEarningsThread.setTaskId(task.getId());
			partnerEarningsThread.setPartnerType(MemberIdentity_Type.partnerElite);
			new Thread(partnerEarningsThread).start();*/
		}
		// 所有质保期内的项目
		List<Project> projectList = this.projectService.findProjectListByStatus(Project_Status.quality);
		if (projectList.isEmpty())
			return;
		for (Project project : projectList) {
			Long projectId = project.getId();
			Date acceptTime = project.getAcceptTime();
			try {
				acceptTime=acceptTime==null?null:DateTimeUtils.dayFormat.parse(DateTimeUtils.dayFormat.format(acceptTime));
			} catch (ParseException e) {}
			Integer quaCount = project.getGuaranteeTime();
			boolean flag = DateTimeUtils.timeIsExpire(acceptTime, quaCount);
			if (!flag)
				continue;
			List<ProjectDefineStage> stageList = this.projectDefineStageService.findProjectDefineStageByDefinedId(project.getDefineId());
			if (stageList.isEmpty())
				return;
			// 计算CTO的各个阶段的质保金
			Long ctoId = project.getCtoId();
			BigDecimal quAmount = new BigDecimal(0);
			Set<Long> definedIdSet = new HashSet<>();
			for (ProjectDefineStage stage : stageList) {
				String stageCode = stage.getStageCode();

				// 验证项目阶段是否全部验收完毕，则开始验收项目
				definedIdSet.add(stage.getDefineId());
				// 验收CTO的阶段
				ProjectTenderRecord ptr = this.projectTenderRecordService.findProjectTenderRecord(projectId, ctoId);
				if (ptr == null || ptr.getDefineId() == null)
					continue;

				ProjectDefineStage ctoPds = this.projectDefineStageService.findProjectDefineStageByCode(projectId,ptr.getDefineId(), stageCode);
				if (ctoPds == null)
					continue;
				/*Date ctoAcceptTime = ctoPds.getAcceptTime();
				Integer ctoQuaCount = ctoPds.getGuaranteeTime();
				boolean ctoflag = DateTimeUtils.timeIsExpire(ctoAcceptTime, ctoQuaCount);
				if (!ctoflag)
					continue;*/
				quAmount = quAmount.add(ctoPds.getGuaranteeAmount());
			}
			log.info("项目：" + project.getName() + "的质保期结束，CTO ID:" + ctoId + ",得到总质保金：" + quAmount);
			BigDecimal tax=new BigDecimal(0);//质保金不扣税
			BigDecimal atIncome=quAmount.subtract(tax);//税后收入=本金-税
			
			// 更新CTO的账户余额和扣除压制的质保金
			this.memberAccountService.updateMemberAccountBalance(ctoId, atIncome, quAmount, Data_Oper.sum,
					Data_Oper.sub);// 质保到期，质保金进入cto账户
			// 更新CTO会员收益账单
			this.memberIncomeService.createMemberIncome(
					new MemberIncome(ctoId, projectId, Income_Type.guarantee, atIncome,tax,null,"项目阶段结算的质保金报酬", null,null,null));
			// 增加平台出账订单
			this.platformOutOrderService.createPlatformOutOrder(ctoId, PlatformOutOrder_Type.guarantee_income, projectId,
					null, null, atIncome,tax);
			//添加平台流水记录
			this.platformFundService.createPlatformFund("项目【"+project.getName()+"】的质保金", projectId.toString(), projectId, null, ctoId, atIncome, PlatformFund_Type.guarantee, PlatformFund_Status.success);

			// 更新项目状态为已完成
			project.setStatus(Project_Status.finish);
			this.projectService.updateProject(project);

			// 通知渠道方结算CTO的提成
			eventPublishService.publishPartnerSettleEvent(ctoId, MemberIdentity_Type.cto, quAmount, 
					projectId, null, null, false, false, MemberIdentity_Type.partnerElite);
			/*PartnerEaringsThread2 partnerEarningsThread = new PartnerEaringsThread2();
			partnerEarningsThread.setMemberId(ctoId);
			partnerEarningsThread.setMemberType(MemberIdentity_Type.cto);
			partnerEarningsThread.setTotalAmount(quAmount);
			partnerEarningsThread.setProjectId(projectId);
			partnerEarningsThread.setPartnerType(MemberIdentity_Type.partnerElite);
			new Thread(partnerEarningsThread).start();*/

			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_QUALITY_END);
			String content = String.format(message.getValue(), project.getName());

			// publish message event,项目质保结束，通知项目方和商务经理
			eventPublishService.publishMessageEvent(project.getCompanyId(), projectId, null, message.getKey(), content,
					false, MemberMessage_Type.project);
			eventPublishService.publishSysMessageEvent(project.getBmId(), null, message.getKey(), content, false);
		}
	}

}

package com.ledao.elite.core.framework.schedule;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.config.DependCoreConfig;
import com.ledao.elite.core.framework.schedule.task.GuaranteeSettlementTask;
import com.ledao.elite.core.framework.schedule.task.MemberPartnerPeriodClearTask;
import com.ledao.elite.core.framework.schedule.task.PlatformOrderExpireTask;
import com.ledao.elite.core.framework.schedule.task.ProjectStageTaskExpire;
import com.ledao.elite.core.framework.schedule.task.ReplenishDataTask;
import com.ledao.elite.core.framework.schedule.task.TenderExpireTask;
import com.ledao.elite.core.framework.schedule.task.WithdrawTransferTask;

/**
 * 任务调度中心
 * */
@Component
public class ScheduleDispatchCentre {
	
	@Resource
	private ProjectStageTaskExpire projectStageTaskExpire;
	@Resource
	private GuaranteeSettlementTask guaranteeSettlementTask;
	@Resource
	private PlatformOrderExpireTask platformOrderExpireTask;
	@Resource
	private TenderExpireTask tenderExpireTask;
	@Resource
	private WithdrawTransferTask withdrawTransferTask;
	@Resource
	private MemberPartnerPeriodClearTask memberPartnerPeriodClearTask;
	@Resource
	private ReplenishDataTask replenishDataTask;
	@Resource
	private DependCoreConfig dependCoreConfig;
	
	
	/**
	 * 处理项目过期任务
	 * 每天凌晨3点30分开始处理
	 * */
	@Scheduled(cron="0 30 3 * * ?")
	public void processExpireTask(){
		if(dependCoreConfig.getScheduleTaskFlag()){
			projectStageTaskExpire.execute();
		}
	}
	
	/**
	 * 处理质保到期的任务
	 * 每天凌晨0点20分开始处理
	 * 0 20 2 * * ?
	 * */
	@Scheduled(cron="0 20 2 * * ?")
	public void processGuaranteeTask(){
		if(dependCoreConfig.getScheduleTaskFlag()){
		    guaranteeSettlementTask.execute();
		}
	}
	
	/**
	 * 处理过期订单任务
	 * 每隔半小时执行一次
	 * */
	@Scheduled(cron="0 0/30 * * * ?")
	public void processExpireOrder(){
		if(dependCoreConfig.getScheduleTaskFlag()){
		    platformOrderExpireTask.execute();
		}
	}
	/**
	 * 处理招标过期任务
	 * 每天凌晨两点开始执行
	 * */
	@Scheduled(cron="0 0 2 * * ?")
	public void processExpireTender(){
		if(dependCoreConfig.getScheduleTaskFlag()){
			tenderExpireTask.execute();
		}
	}
	
	/**
	 * 处理提现失败的任务
	 * 每间隔一小时执行
	 * */
	@Scheduled(cron="0 0/60 * * * ?")
	public void processW(){
		if(dependCoreConfig.getScheduleTaskFlag()){
			withdrawTransferTask.execute();
		}
	}
	
	/**
	 * 处理渠道方周期结算数据过期任务
	 * 每月一号0点01分开始处理
	 * */
	@Scheduled(cron="0 1 0 1 * ?")
	public void processPartnerExpireTask(){
		if(dependCoreConfig.getScheduleTaskFlag()){
			memberPartnerPeriodClearTask.execute();
		}
	}
	
	
	/**
	 * 补充数据任务
	 * 每月一号4点20分开始处理
	 * 0 20 4 * * ?
	 * */
	@Scheduled(cron="0 20 4 * * ?")
	public void processReplenishDataTask(){
		if(dependCoreConfig.getScheduleTaskFlag()){
			replenishDataTask.execute();
		}
	}
}

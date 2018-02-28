package com.ledao.elite.core.framework.plugin.quartz;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 应用初始化启动任务线程
 * @author kobe
 * */
@Slf4j
@Service
public class QuartzJobInitExecutor extends Thread{
	
	@Resource
	private QuartzJobScheduler quartzJobScheduler;
	
	@PostConstruct
	public void onstart(){
		log.info("提现job调度初始化线程开始启动...");
		this.setName("job-schedule-init");
		this.start();
		try {
			quartzJobScheduler.startJobs();
		} catch (SchedulerException e) {
			log.error("启动提现job调度器失败："+e);
			e.printStackTrace();
		}
	}
	
	@PreDestroy
	public void ondestory(){
		try {
			quartzJobScheduler.shutdownJobs();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}finally{
			this.interrupt();
		}
	}

	@Override
	public void run() {
		try {
			/*//加载系统已经开启的任务
			List<RefundTaskInfo> jobTaskList=this.jobTaskService.findJobTaskListByState(TASK_STATE.Enable);
			for (RefundTaskInfo jobTask : jobTaskList) {
				jobSchedulerCentre.addJob(jobTask);
			}
			//加载生成栏目历史的列表任务
			RefundTaskInfo jobTask=RefundTaskInfo.newOf();
			jobTask.setTaskType(TASK_TYPE.RefreshColumnHistoryList);
			jobTask.setTaskExperess(RefreshColumnHistoryListJobTask.cronExpression_month);
			jobTask.setTaskName("built-in-makeHistoryListFile-001");
			jobTask.setTaskTitle("生成栏目的历史列表任务");
			jobTask.setTargets(null);
			jobSchedulerCentre.addJob(RefundTaskInfo.newOf(jobTask));*/
		} catch (Exception e) {
			log.error("批量添加提现任务失败："+e);
			e.printStackTrace();
		}
	}

}

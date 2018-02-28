package com.ledao.elite.core.framework.plugin.quartz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.sys.QuartzTask;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Status;
import com.ledao.elite.core.framework.config.DependCoreConfig;
import com.ledao.elite.core.service.sys.QuartzTaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统启动初始化未执行的任务
 * 防止系统重启导致的定时任务未执行
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Slf4j
@Component
public class InitStartQuartzJob implements InitializingBean {

	@Resource
	private QuartzTaskService quartzTaskService;
	
	@Resource
	private QuartzJobScheduler quartzJobScheduler;
	
	@Resource
	private DependCoreConfig dependCoreConfig;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(!dependCoreConfig.getScheduleTaskFlag()){
			return;
		}
		List<QuartzTask> list=quartzTaskService.findQuartzTaskListByStatus(QuartzTask_Status.wait_run);
		if(list.isEmpty())return;
		log.info("系统启动初始化发现有未执行的任务："+list.size()+"条");
		for(QuartzTask jobTask:list){
			Date createTime=jobTask.getCreateTime();
			long ct=createTime.getTime();
			long rt=System.currentTimeMillis();
			long it=rt-ct;
			int mt=(int) (it/60000);
			Integer minute=jobTask.getIntervalMinute();
			int et=minute-mt;
			if(et<0){
				//未执行的过期任务
				jobTask.setStatus(QuartzTask_Status.expire);
				jobTask.setResult("系统初始化时发现此任务已过期");
				quartzTaskService.updateQuartzTask(jobTask);
				continue;
			}
			jobTask.setIntervalMinute(et);//计算新的时间开始任务
			quartzJobScheduler.addJob(jobTask);
		}
	}

}

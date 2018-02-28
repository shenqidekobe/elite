package com.ledao.elite.core.framework.plugin.quartz;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ledao.elite.core.domain.sys.QuartzTask;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Way;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 提现任务调度中心
 * @author kobe
 * */
@Slf4j
@Service
public class QuartzJobScheduler {
	
	private static Logger logger=LoggerFactory.getLogger(QuartzJobScheduler.class);
	
	@Resource
	private SchedulerFactory stdSchedulerFactory;
	
	@Resource(name="schedulerFactoryBean")
	private Scheduler scheduler;
	
	public static final String JobDataMapKey="scheduleJobDataMap";

	
	/**
	 * 向任务调度器中添加任务
	 * 时间表达式为null表示立即执行
	 * @param jobTask
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addJob(QuartzTask jobTask)throws SchedulerException{
		String name=jobTask.getTaskName();
		String group=jobTask.getTaskGroup();
		TriggerKey triggerKey = TriggerKey.triggerKey(name,group);
		Trigger trigger =scheduler.getTrigger(triggerKey);
	    //验证当前任务是否已经存在，如果不存在就创建一个新的任务加入任务调度器
	    if(trigger==null){
	    	Class clazz=null;
			try {
				String implClass=jobTask.getTaskImplClass();
				if(StringUtils.isEmpty(implClass))return;
				clazz = Class.forName(implClass);
			} catch (ClassNotFoundException e) {
				logger.error(jobTask.getTaskImplClass()+"，任务未定义。");
				throw new SchedulerException(e);
			}
			logger.info("job调度器开始创建任务 trigger = "+triggerKey);
			JobDataMap jobDataMap=new JobDataMap();
			jobDataMap.put(JobDataMapKey, jobTask);
	    	JobDetail jobDetail = JobBuilder.newJob(clazz).setJobData(jobDataMap).withIdentity(name,group).build();
	    	if(QuartzTask_Way.one.equals(jobTask.getWay())){
	    		Calendar cal=Calendar.getInstance();
	    		cal.add(Calendar.MINUTE, jobTask.getIntervalMinute());
	    		Date triggerStartTime=cal.getTime();//开始执行的时间
	    		trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startAt(triggerStartTime).build();
	    	}else{
	    		CronScheduleBuilder scheduleBuilder=null;
		    	try {
		    		scheduleBuilder = CronScheduleBuilder.cronSchedule(jobTask.getTaskExperess());
				} catch (Exception e) {
					throw new SchedulerException("任务："+name+"的时间表达式不符合规范");
				}
		    	trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
	    	}
	        Date ft = scheduler.scheduleJob(jobDetail, trigger);  
	        log.info("定时任务名称："+jobDetail.getKey().getName() + " 将在 : " + DateTimeUtils.defaultFormat.format(ft) + " 时运行");  
	        //scheduler.scheduleJob(jobDetail, trigger);
	    }
	}
	
	/**
	 * 立即运行任务：触发任务
	 * @param jobTask
	 * */
	public void triggerJob(QuartzTask jobTask)throws SchedulerException{
		JobKey jobKey = JobKey.jobKey(jobTask.getTaskName(),jobTask.getTaskGroup());
		if (jobKey != null)
			logger.info("job调度器开始触发任务 jobKey = "+jobKey);
			scheduler.triggerJob(jobKey);
	}
	
	/**
	 * 启动任务调度器
	 * */
	public void startJobs()throws SchedulerException {  
    	if (scheduler.isShutdown()) {  
    		logger.info("job调度器scheduler开始启动...");
    		scheduler.start();  
    	}
    }  
  
    /** 
     * 停止任务调度器
     */  
    public void shutdownJobs()throws SchedulerException{
        if (!scheduler.isShutdown()) {  
        	logger.info("job调度器scheduler开始停止...");
        	scheduler.shutdown();  
        }  
    }  

}

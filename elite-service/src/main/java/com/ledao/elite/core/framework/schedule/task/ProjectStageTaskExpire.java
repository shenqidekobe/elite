package com.ledao.elite.core.framework.schedule.task;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.service.project.ProjectStageTaskService;

/**
 * 项目任务过期处理
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class ProjectStageTaskExpire extends ExpireAbstractTask{
	
	
	@Resource
	private ProjectStageTaskService projectStageTaskService;

	@Override
	public void execute() throws EliteBusinessException {
		log.info("*****任务到期定时任务处理开始执行*****");
		this.projectStageTaskService.updateProjectStageTaskAsExpire();
		log.info("*****任务交付时间过7天后定时任务处理开始执行*****");
		this.projectStageTaskService.updateApplyTaskAsExpire();
	}


} 

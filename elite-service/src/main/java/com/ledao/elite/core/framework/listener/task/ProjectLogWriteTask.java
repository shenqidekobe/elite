package com.ledao.elite.core.framework.listener.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.listener.AsyncTaskListener;
import com.ledao.elite.core.framework.listener.event.BaseTask;
import com.ledao.elite.core.framework.listener.event.ProjectLogTask;
import com.ledao.elite.core.framework.listener.event.BaseTask.BaseTask_Type;
import com.ledao.elite.core.service.project.ProjectLogService;

/**
 * 写项目日志任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class ProjectLogWriteTask extends AsyncTaskListener{
	
	
	@Resource
	private ProjectLogService projectLogService;

	@Async
	@Override
	protected void execute(BaseTask task) {
		ProjectLogTask plTask=(ProjectLogTask) task;
		projectLogService.createProjectLog(plTask.getProjectLog());
	}

	@Override
	public boolean supportsTaskType(BaseTask_Type taskType) {
		return BaseTask_Type.write_project_log.equals(taskType);
	}

}

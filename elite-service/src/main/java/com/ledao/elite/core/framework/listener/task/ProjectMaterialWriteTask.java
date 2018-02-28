package com.ledao.elite.core.framework.listener.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.listener.AsyncTaskListener;
import com.ledao.elite.core.framework.listener.event.BaseTask;
import com.ledao.elite.core.framework.listener.event.MaterialTask;
import com.ledao.elite.core.framework.listener.event.BaseTask.BaseTask_Type;
import com.ledao.elite.core.service.project.ProjectMaterialService;

/**
 * 项目文件记录任务处理
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class ProjectMaterialWriteTask extends AsyncTaskListener{
	
	@Resource
	private ProjectMaterialService projectMaterialService;

	@Async
	@Override
	protected void execute(BaseTask task) {
		MaterialTask mTask=(MaterialTask) task;
		projectMaterialService.createProjectMaterial(mTask.getMaterial());
	}

	@Override
	public boolean supportsTaskType(BaseTask_Type taskType) {
		return BaseTask_Type.write_project_material.equals(taskType);
	}

}

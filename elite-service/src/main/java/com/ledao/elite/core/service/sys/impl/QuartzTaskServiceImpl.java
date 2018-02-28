package com.ledao.elite.core.service.sys.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.ledao.elite.core.domain.sys.QuartzTask;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Status;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Type;
import com.ledao.elite.core.domain.sys.QuartzTask.QuartzTask_Way;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.plugin.quartz.QuartzJobScheduler;
import com.ledao.elite.core.repository.sys.QuartzTaskRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.QuartzTaskService;

@Service("quartzTaskService")
public class QuartzTaskServiceImpl extends BaseSerivceImpl implements QuartzTaskService{
	
	@Resource
	private QuartzTaskRepository quartzTaskRepository;
	@Resource
	private QuartzJobScheduler quartzJobScheduler;

	@Override
	public QuartzTask createQuartzTask(Long dataId,QuartzTask_Type type,QuartzTask_Way way,String taskExperess,Integer intervalMinute,String taskParams) throws EliteServiceException {
		QuartzTask task=new QuartzTask();
		task.setDataId(dataId);
		task.setType(type);
		task.setWay(way);
		task.setTaskExperess(taskExperess);
		task.setIntervalMinute(intervalMinute);
		task.setTaskParams(taskParams);
		String taskName=UUID.randomUUID().toString();
		String taskTitle="";
		String taskGroup="";
		String taskImplClass="";
		switch (type) {
		case withdraw:
			taskTitle="会员提现任务";
			taskGroup="group1";
			taskImplClass="com.ledao.elite.core.framework.plugin.quartz.QuartzJobTask";
			break;
		}
		task.setTaskName(taskName);
		task.setTaskTitle(taskTitle);
		task.setTaskGroup(taskGroup);
		task.setTaskImplClass(taskImplClass);
		task.setStatus(QuartzTask_Status.wait_run);
		this.quartzTaskRepository.save(task);
		
		//添加任务
		try {
			this.quartzJobScheduler.addJob(task);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return task;
	}

	@Override
	public QuartzTask updateQuartzTask(QuartzTask obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getId());
		this.quartzTaskRepository.save(obj);
		return obj;
	}
	
	@Override
	public QuartzTask updateQuartzTask(Long dataId,QuartzTask_Status status)throws EliteServiceException{
		QuartzTask obj=findQuartzTaskByDataId(dataId);
		if(obj!=null){
			obj.setStatus(status);
			this.quartzTaskRepository.save(obj);
		}
		return obj;
	}

	@Override
	public QuartzTask findQuartzTaskById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.quartzTaskRepository.find(id);
	}
	
	@Override
	public QuartzTask findQuartzTaskByDataId(Long dataId)throws EliteServiceException{
		this.verifyParams(dataId);
		Search search=new Search(QuartzTask.class);
		search.addFilterEqual("dataId", dataId);
		search.addFilterNotEqual("status", QuartzTask_Status.run_end);
		List<QuartzTask> list=this.quartzTaskRepository.search(search);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<QuartzTask> findQuartzTaskList(Long dataId, QuartzTask_Status status) throws EliteServiceException {
		Search search=new Search(QuartzTask.class);
		if(dataId!=null){
			search.addFilterEqual("dataId", dataId);
		}
		if(status!=null){
			search.addFilterEqual("status", status);
		}
		return this.quartzTaskRepository.search(search);
	}
	
	@Override
	public List<QuartzTask> findQuartzTaskListByStatus(QuartzTask_Status status)throws EliteServiceException{
		Search search=new Search(QuartzTask.class);
		if(status!=null){
			search.addFilterEqual("status", status);
		}
		return this.quartzTaskRepository.search(search);
	}

}

package com.ledao.elite.core.framework.schedule.task;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.service.project.ProjectTenderService;

/**
 * 招标过期处理任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class TenderExpireTask extends ExpireAbstractTask{

	@Resource
	private ProjectTenderService projectTenderService;
	
	
	@Override
	public void execute() throws EliteBusinessException {
		log.info("*****招标书到期定时任务处理开始执行*****");
		this.projectTenderService.updateProjectTenderAsExpire();
	}

}

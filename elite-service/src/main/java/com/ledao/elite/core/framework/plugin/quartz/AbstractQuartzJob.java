package com.ledao.elite.core.framework.plugin.quartz;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ledao.elite.core.domain.sys.QuartzTask;
import com.ledao.elite.core.framework.plugin.pay.PaymentService;
import com.ledao.elite.core.service.member.MemberBankService;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.ledao.elite.core.service.sys.QuartzTaskService;
import com.ledao.elite.core.utils.SpringContextUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * 作业类抽象封装
 * @author kobe
 * */
public abstract class AbstractQuartzJob extends QuartzJobBean {
	
	protected static Logger logger=LoggerFactory.getLogger(AbstractQuartzJob.class);
	
	protected MemberWithdrawService memberWithdrawService;
	protected MemberBankService memberBankService;
	protected PaymentService pingPlusPlusService;
	protected QuartzTaskService quartzTaskService;
	
	public AbstractQuartzJob(){
		memberWithdrawService=(MemberWithdrawService)SpringContextUtil.getBean("memberWithdrawService");
		memberBankService=(MemberBankService)SpringContextUtil.getBean("memberBankService");
		pingPlusPlusService=(PaymentService)SpringContextUtil.getBean("pingPlusPlusService");
		quartzTaskService=(QuartzTaskService)SpringContextUtil.getBean("quartzTaskService");
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		this.setExeDate(new Date());
		JobDataMap dataMap=context.getMergedJobDataMap();
		QuartzTask jobTask=(QuartzTask) dataMap.get(QuartzJobScheduler.JobDataMapKey);
		
		this.setDataId(jobTask.getDataId());
		runTimingTask(context);
	}
	
	protected abstract void runTimingTask(JobExecutionContext context) throws JobExecutionException;
	
	
	@Setter
	@Getter
	private Date exeDate;
	@Setter
	@Getter
	private Long dataId;

}

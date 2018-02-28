package com.ledao.elite.core.framework.component;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.project.ProjectLog;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.domain.sys.SmsRecord;
import com.ledao.elite.core.domain.sys.SmsRecord.Sms_Type;
import com.ledao.elite.core.domain.sys.SysMessage;
import com.ledao.elite.core.framework.dto.MemberUpdatePass;
import com.ledao.elite.core.framework.listener.event.AcceptSettleTask;
import com.ledao.elite.core.framework.listener.event.BaseTask;
import com.ledao.elite.core.framework.listener.event.BaseTask.BaseTask_Type;
import com.ledao.elite.core.framework.listener.event.EliteEvent;
import com.ledao.elite.core.framework.listener.event.MemberIdentityDataTask;
import com.ledao.elite.core.framework.listener.event.MessagePushTask;
import com.ledao.elite.core.framework.listener.event.PartnerSettleTask;
import com.ledao.elite.core.framework.listener.event.ProjectLogTask;
import com.ledao.elite.core.framework.listener.event.SmsPushTask;
import com.ledao.elite.core.framework.listener.event.SysMessagePushTask;

/**
 * 事件发布服务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component("eventPublishService")
public class EventPublishService {
	
	@Resource
	private ApplicationContext applicationContext;
	
	/**
	 * 发布消息事件
	 * */
	public void publishMessageEvent(Long memberId,Long projectId,Long sendUser,String title,String content,boolean sticked,MemberMessage_Type type){
		if(memberId==null)return;
		MessagePushTask task=new MessagePushTask();
		task.setTaskType(BaseTask_Type.message_push_member);
		MemberMessage message=new MemberMessage();
		message.setMemberId(memberId);
		message.setProjectId(projectId);
		if(sendUser!=null){
			message.setSendUser(sendUser);
		}
		message.setTitle(title);
		message.setContent(content);
		message.setSticked(sticked);
		message.setType(type);
		task.setMessage(message);
		publishEvent(task);
	}
	
	/**
	 * 发布系统消息事件
	 * */
	public void publishSysMessageEvent(Long userId,Long sendUser,String title,String content,boolean sticked){
		if(userId==null)return;
		SysMessagePushTask task=new SysMessagePushTask();
		task.setTaskType(BaseTask_Type.message_push_sys);
		SysMessage message=new SysMessage();
		message.setUserId(userId);
		if(sendUser!=null){
			message.setSendUser(sendUser);
		}
		message.setTitle(title);
		message.setContent(content);
		message.setStick(sticked);
		task.setMessage(message);
		publishEvent(task);
	}
	
	/**
	 * 发布项目日志事件
	 * */
	public void publishProjectLogEvent(ProjectLog_Type logType,Long projectId,String stageCode,Long taskId,Long memberId,String title,String content,Long attaId){
		ProjectLogTask task=new ProjectLogTask();
		ProjectLog projectlog=new ProjectLog();
		projectlog.setLogType(logType);
		projectlog.setCreateId(memberId);
		projectlog.setProjectId(projectId);
		projectlog.setStageCode(stageCode);
		projectlog.setTaskId(taskId);
		projectlog.setTitle(title);
		projectlog.setContent(content);
		projectlog.setAttaId(attaId);
		task.setTaskType(BaseTask_Type.write_project_log);
		task.setProjectLog(projectlog);
		publishEvent(task);
	}
	
	/**
	 * 发布短信推送事件
	 * */
	public void publishSmsPushEvent(Sms_Type type,String phone,String content){
		SmsPushTask task=new SmsPushTask();
		SmsRecord sms=new SmsRecord();
		sms.setPhones(phone);
		sms.setContent(content);
		task.setTaskType(BaseTask_Type.push_sms);
		task.setSms(sms);
		publishEvent(task);
	}
	
	/**
	 * 发布结算验收结算事件
	 * */
	public void publishAcceptSettleEvent(Long stageId,boolean acceptFlag,String acceptReason){
		AcceptSettleTask task=new AcceptSettleTask();
		task.setStageId(stageId);
		task.setAcceptFlag(acceptFlag);
		task.setAcceptReason(acceptReason);
		task.setTaskType(BaseTask_Type.accept_settle);
		publishEvent(task);
	}
	
	/**
	 * 发布渠道方结算事件
	 * */
	public void publishPartnerSettleEvent(Long memberId,MemberIdentity_Type memberType,BigDecimal totalAmount,Long projectId,
			Long stageId,Long taskId,boolean firstStage,boolean lastStage,MemberIdentity_Type partnerType){
		PartnerSettleTask task=new PartnerSettleTask();
		task.setMemberId(memberId);
		task.setMemberType(memberType);
		task.setTotalAmount(totalAmount);
		task.setProjectId(projectId);
		task.setStageId(stageId);
		task.setTaskId(taskId);
		task.setFirstStage(firstStage);
		task.setLastStage(lastStage);
		task.setPartnerType(partnerType);
		task.setTaskType(BaseTask_Type.partner_settle);
		publishEvent(task);
	}
	
	/**
	 * 同步更新会员各个身份的数据
	 * @param memberId
	 * @param updateType
	 */
	public void publishMemberIdentityDate(MemberBank bank,MemberBasic basic,MemberCredit credit,MemberUpdatePass updatePass){
		MemberIdentityDataTask task=new MemberIdentityDataTask();
		task.setBank(bank);
		task.setBasic(basic);
		task.setCredit(credit);
		task.setUpdatePass(updatePass);
		task.setTaskType(BaseTask_Type.sync_member_data);
		publishEvent(task);
	}

	protected void publishEvent(BaseTask task) {
		this.applicationContext.publishEvent(new EliteEvent(this, task));
	}
	

}

package com.ledao.elite.core.framework.listener.task;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.listener.AsyncTaskListener;
import com.ledao.elite.core.framework.listener.event.BaseTask;
import com.ledao.elite.core.framework.listener.event.BaseTask.BaseTask_Type;
import com.ledao.elite.core.framework.thread.SyncMemberIdentityDate;
import com.ledao.elite.core.framework.listener.event.MemberIdentityDataTask;

/**
 * 同步更新会员各个身份的数据任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class SyncMemberIdentityDataTask extends AsyncTaskListener{
	
	@Resource
	private SyncMemberIdentityDate syncMemberIdentityDate;
	
	@Override
	protected void execute(BaseTask task) {
		MemberIdentityDataTask data=(MemberIdentityDataTask)task;
		syncMemberIdentityDate.SyncMemberIdentity(data.getBank(),data.getBasic(),data.getCredit(),data.getUpdatePass());
		log.info(data.toString());
	}

	@Override
	public boolean supportsTaskType(BaseTask_Type taskType) {
		return BaseTask_Type.sync_member_data.equals(taskType);
	}

}

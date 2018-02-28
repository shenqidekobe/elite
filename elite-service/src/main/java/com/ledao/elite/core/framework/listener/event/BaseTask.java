package com.ledao.elite.core.framework.listener.event;

import lombok.Getter;
import lombok.Setter;

/**
 * 处理事件监听的任务基类
 * */
public abstract class BaseTask{
	
	@Getter
	@Setter
	private BaseTask_Type taskType;
	
	public enum BaseTask_Type {
		push_sms("推送短信"),
		write_project_log("写项目日志"),
		message_push_member("推送消息给会员"),
		message_push_sys("推送消息给系统用户"),
		write_project_material("记录项目文件"),
		accept_settle("验收结算"),
		partner_settle("渠道结算"),
		sync_member_data("同步更新会员身份数据");
		BaseTask_Type(String desc) {
			this.desc = desc;
		}
		@Getter
		private String desc;
	}
}

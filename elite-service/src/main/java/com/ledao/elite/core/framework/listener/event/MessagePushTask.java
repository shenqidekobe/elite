package com.ledao.elite.core.framework.listener.event;

import com.ledao.elite.core.domain.member.MemberMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 消息推送任务对象
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MessagePushTask extends BaseTask{

	private MemberMessage message;

}

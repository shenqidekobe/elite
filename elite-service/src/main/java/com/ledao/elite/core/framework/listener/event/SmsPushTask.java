package com.ledao.elite.core.framework.listener.event;

import com.ledao.elite.core.domain.sys.SmsRecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 短信推送任务定义
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class SmsPushTask extends BaseTask{
	
	private SmsRecord sms;

}

package com.ledao.elite.core.framework.listener.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 验收结算任务事件
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class AcceptSettleTask extends BaseTask{

	
	private Long stageId;// 项目方立项书对应的阶段ID
	private boolean acceptFlag = true;
	private String acceptReason;

}

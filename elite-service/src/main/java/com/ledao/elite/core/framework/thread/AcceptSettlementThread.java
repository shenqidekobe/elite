package com.ledao.elite.core.framework.thread;

import com.ledao.elite.core.framework.thread.settle.AcceptStageSettlement;
import com.ledao.elite.core.utils.SpringContextUtil;

import lombok.Setter;

/**
 * 阶段验收结算线程 如遇最后一个阶段成功验收，则项目开始进入质保期
 */
public class AcceptSettlementThread implements Runnable {

	@Setter
	private Long stageId;// 项目方立项书对应的阶段ID
	@Setter
	private boolean acceptFlag = true;
	@Setter
	private String acceptReason;

	private AcceptStageSettlement acceptStageSettlement;

	public AcceptSettlementThread() {
		acceptStageSettlement = (AcceptStageSettlement) SpringContextUtil.getBean("acceptStageSettlement");
	}

	@Override
	public void run() {
		acceptStageSettlement.settle(stageId, acceptFlag, acceptReason);
	}

}

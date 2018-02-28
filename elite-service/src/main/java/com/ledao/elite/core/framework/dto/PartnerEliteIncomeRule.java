package com.ledao.elite.core.framework.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 渠道人才方收益规则
 * */
@Data
public class PartnerEliteIncomeRule {
	
	private Integer statisticsPeriod;//统计周期
	private BigDecimal taskOrderAward;//精英人才任务首单奖励
	private ProjectOrderAward firstProjectOrderAward;//CTO项目首单第一阶梯奖励
	private ProjectOrderAward secondProjectOrderAward;//CTO项目首单第二阶梯奖励
	private ProjectOrderAward threeProjectOrderAward;//CTO项目首单第三阶梯奖励
	
	private PartnerOwnRule partnerOwnRule;//本人发展的规则
	private PartnerDirectRule partnerDirectRule;//直接渠道规则
	private PartnerIndirectRule partnerIndirectRule;//间接渠道规则
	
	@Data
	public static class PartnerOwnRule{
		private LadderRule firstLadder;//第一阶梯
		private LadderRule secondLadder;//第二阶梯
		private LadderRule threeLadder;//第三阶梯
	}
	@Data
	public static class PartnerDirectRule{
		private LadderRule firstLadder;//第一阶梯
		private LadderRule secondLadder;//第二阶梯
		private LadderRule threeLadder;//第三阶梯
	}
	@Data
	public static class PartnerIndirectRule{
		private LadderRule firstLadder;//第一阶梯
		private LadderRule secondLadder;//第二阶梯
		private LadderRule threeLadder;//第三阶梯
	}
	
	@Data
	public static class  LadderRule{
		private Integer minAmount;//最小签单额
		private Integer maxAmount;//最大签单额
		private Integer minElite;//最小人才数量
		private Integer maxElite;//最大人才数量
		private Integer minTask;//最小任务数量
		private Integer maxTask;//最大任务数量
		private BigDecimal commissionRatio;//提成比例
	}
	
	@Data
	public static class ProjectOrderAward{
		private Integer minAmount;//最小金额
		private Integer maxAmount;//最大金额
		private BigDecimal orderAward;//订单奖励
	}
	
	public static void main(String[] args) {
	}
	

}

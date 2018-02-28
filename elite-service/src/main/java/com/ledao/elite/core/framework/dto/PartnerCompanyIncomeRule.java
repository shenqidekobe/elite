package com.ledao.elite.core.framework.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 渠道项目方收益规则
 * */
@Data
public class PartnerCompanyIncomeRule {
	
	private Integer statisticsPeriod;//统计周期
	private BigDecimal orderAward;//周期内的每单奖励
	private Integer periodCount;//周期达标数量
	private BigDecimal periodOrderAward;//周期内达标奖励
	
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
	public static class LadderRule{
		private Integer minAmount;//最小签单额
		private Integer maxAmount;//最大签单额
		private Integer minOrder;//最小订单量
		private Integer maxOrder;//最大订单量
		private BigDecimal commissionRatio;//收益比例提成
	}
	
	public static void main(String[] args) {
	}

}

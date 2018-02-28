package com.ledao.elite.core.framework.thread.settle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.ledao.elite.core.domain.member.MemberIncome;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Type;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Type;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Data_Oper;
import com.ledao.elite.core.framework.dto.PartnerCompanyIncomeRule;
import com.ledao.elite.core.framework.dto.PartnerCompanyIncomeRule.LadderRule;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.platform.PlatformOutOrderService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 渠道项目方阶段帮助类
 * */
@Slf4j
public class PartnerEaringsThread2CompanyUtils {
	
	@Data
	public static class PartnerCommissionRatio{
		private BigDecimal firstCommission;//一级比例
		private BigDecimal secondCommission;//二级比例
		private BigDecimal threeCommission;//三级比例
	}

	/**
	 * 渠道项目方提成收益结算
	 * */
	public static PartnerCommissionRatio settlePartnerCompany(PartnerCompanyIncomeRule partnerIncomeRule,MemberPartnerCompany partner,MemberPartnerCompany direct,MemberPartnerCompany indirect,
			boolean firstStage,boolean lastStage,BigDecimal totalAmount,Long partnerMemberId,Long projectId,Long memberId,Long stageId,Long taskId,
			MemberIncomeService memberIncomeService,MemberAccountService memberAccountService,PlatformOutOrderService platformOutOrderService,MemberPartnerCompanyService memberPartnerCompanyService,PlatformFundService platformFundService){
		PartnerCommissionRatio ratio=new PartnerCommissionRatio();
		Map<String,Object> directMap=memberPartnerCompanyService.findPartnerCompanyDirectCount(partner.getId(), null, null);
		Map<String,Object> indirectMap=memberPartnerCompanyService.findPartnerCompanyInDirectCount(partner.getId(), null, null);
		
		BigDecimal firstCommission=compare("本人项目渠道【"+partnerMemberId+"】",partner.getCalculateOrderCount(), partner.getCalculateTotalAmount(),partnerIncomeRule.getPartnerOwnRule().getFirstLadder(), partnerIncomeRule.getPartnerOwnRule().getSecondLadder(), partnerIncomeRule.getPartnerOwnRule().getThreeLadder());//一级比例
		BigDecimal secondCommission=compare("直接项目渠道【"+(direct==null?"":direct.getMemberId())+"】",(Integer)directMap.get("orderCount"),(BigDecimal)directMap.get("orderAmount"),partnerIncomeRule.getPartnerDirectRule().getFirstLadder(), partnerIncomeRule.getPartnerDirectRule().getSecondLadder(), partnerIncomeRule.getPartnerDirectRule().getThreeLadder());//二级比例
		BigDecimal threeCommission=compare("间接项目渠道【"+(indirect==null?"":indirect.getMemberId())+"】",(Integer)indirectMap.get("orderCount"),(BigDecimal)indirectMap.get("orderAmount"), partnerIncomeRule.getPartnerIndirectRule().getFirstLadder(), partnerIncomeRule.getPartnerIndirectRule().getSecondLadder(), partnerIncomeRule.getPartnerIndirectRule().getThreeLadder());//三级比例
		ratio.setFirstCommission(firstCommission);
		ratio.setSecondCommission(secondCommission);
		ratio.setThreeCommission(threeCommission);
		
    	BigDecimal orderAward=partnerIncomeRule.getOrderAward();
    	BigDecimal periodOrderAward=partnerIncomeRule.getPeriodOrderAward();
    	Integer orderCount=partner.getOrderCount()==null?0:partner.getOrderCount();//签单总量
		Integer calculateOrderCount=partner.getCalculateOrderCount()==null?0:partner.getCalculateOrderCount();//结算签单总量
		BigDecimal allTotalAmount=partner.getTotalAmount()==null?new BigDecimal(0):partner.getTotalAmount();//签单总额
		BigDecimal calculateTotalAmount=partner.getCalculateTotalAmount()==null?new BigDecimal(0):partner.getCalculateTotalAmount();//结算签单额
		if(lastStage){
			orderCount=orderCount+1;
			calculateOrderCount=calculateOrderCount+1;//最后一个阶段累加结算单量
		}
		//更新渠道方的签单数据/并且验证是否在周期内
		if(firstStage){
			//签单奖励
			BigDecimal awardAmount=orderAward;
			int periodCount=partnerIncomeRule.getPeriodCount();
			if(calculateOrderCount>periodCount){
				awardAmount=periodOrderAward;
			}
			log.info("渠道项目方【"+partnerMemberId+"】在本周期内结算签单数："+partner.getCalculateOrderCount()+",本次奖励金额："+awardAmount);
			//增加渠道方的收益记录
			memberIncomeService.createMemberIncome(new MemberIncome(partnerMemberId, projectId, Income_Type.parent_order_award, awardAmount,new BigDecimal(0),null,"签单奖励", memberId,stageId,taskId));
			//更新渠道方的账户收益和余额
			memberAccountService.updateMemberAccountTotalIncome(partnerMemberId, awardAmount, awardAmount, Data_Oper.sum);
			//增加平台出账订单
			platformOutOrderService.createPlatformOutOrder(partnerMemberId, PlatformOutOrder_Type.commission_income, projectId, null,null, awardAmount,new BigDecimal(0));
			//添加平台流水记录
			platformFundService.createPlatformFund("我的签单奖励",projectId.toString(), projectId, stageId, partnerMemberId, awardAmount, PlatformFund_Type.income, PlatformFund_Status.success);
		}
		try {
			calculateTotalAmount=calculateTotalAmount.add(totalAmount);
			allTotalAmount=allTotalAmount.add(totalAmount);
			Date startOrderTime=orderCount==0?new Date():partner.getStartOrderTime();
			Date lastOrderTime=new Date();
			Long id=partner.getId();
			log.info("更新渠道项目方【"+partner.getId()+"】的结算数据orderCount:"+orderCount+",totalAmount:"+allTotalAmount+",calculateTotalAmount:"+calculateTotalAmount+",calculateOrderCount:"+calculateOrderCount);
			memberPartnerCompanyService.updateMemberPartnerEliteAsCalculate(id, orderCount, allTotalAmount, calculateOrderCount, calculateTotalAmount, startOrderTime, lastOrderTime);
		} catch (Exception e) {
			log.error("保存项目渠道方："+partner.getId()+"出现异常："+e.getMessage());
		}
    	
		return ratio;
	}
	
	
	public static BigDecimal compare(String desc,Integer calculateOrderCount,BigDecimal calculateTotalAmount,LadderRule firstLadder,LadderRule secondLadder,LadderRule threeLadder){
		BigDecimal commissionRatio=null;
		try {
			calculateOrderCount=calculateOrderCount==null?0:calculateOrderCount;
			calculateTotalAmount=calculateTotalAmount==null?new BigDecimal(0):calculateTotalAmount;
			log.info(desc+"的当前周期内签单数："+calculateOrderCount+"，当前周期内签单总额："+calculateTotalAmount);
			//结算金额大于等于第三阶段最小值，结算量大于等于第三阶段最小值为第三阶梯，结算金额大于等于第二阶段最小值并且小于最大值并且结算量大于等于最小值：为第三阶梯(t>=150 and o>=11)
			//结算金额大于等于第二阶梯最小值且小于最大值并且结算量大于等于第二阶梯最小值，或结算量大于等于第二阶梯最小值小于等于最大值并且结算金额大于等于第二阶梯最小值：为第二阶梯(50<=t<150 and o>=6 or 6<=o<=10 and t>=50)
			if(calculateTotalAmount.compareTo(new BigDecimal(threeLadder.getMinAmount()))!=-1
					&&calculateOrderCount.compareTo(threeLadder.getMinOrder())!=-1){
				commissionRatio=threeLadder.getCommissionRatio();
			}else if((calculateTotalAmount.compareTo(new BigDecimal(secondLadder.getMinAmount()))!=-1
					&&calculateTotalAmount.compareTo(new BigDecimal(secondLadder.getMaxAmount()))==-1
					&&calculateOrderCount.compareTo(secondLadder.getMinOrder())!=-1)
					||(calculateOrderCount.compareTo(secondLadder.getMinOrder())!=-1
					&&calculateOrderCount.compareTo(secondLadder.getMaxOrder())!=1
					&&calculateTotalAmount.compareTo(new BigDecimal(secondLadder.getMinAmount()))!=-1)){
				commissionRatio=secondLadder.getCommissionRatio();
			}else{
				commissionRatio=firstLadder.getCommissionRatio();
			}
			} catch (Exception e) {
			}
		return commissionRatio;
	}

}

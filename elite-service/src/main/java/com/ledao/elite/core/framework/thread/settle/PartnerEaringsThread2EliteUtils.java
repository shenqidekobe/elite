package com.ledao.elite.core.framework.thread.settle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberIncome;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Type;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Type;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Data_Oper;
import com.ledao.elite.core.framework.dto.PartnerEliteIncomeRule;
import com.ledao.elite.core.framework.dto.PartnerEliteIncomeRule.LadderRule;
import com.ledao.elite.core.framework.dto.PartnerEliteIncomeRule.ProjectOrderAward;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.platform.PlatformOutOrderService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 渠道方阶段帮助类
 * */
@Slf4j
public class PartnerEaringsThread2EliteUtils {
	
	@Data
	public static class PartnerCommissionRatio{
		private BigDecimal firstCommission;//一级比例
		private BigDecimal secondCommission;//二级比例
		private BigDecimal threeCommission;//三级比例
	}

	/**
	 * 渠道人才方提成收益结算
	 * */
	public static PartnerCommissionRatio settlePartnerElite(PartnerEliteIncomeRule partnerRule,MemberPartnerElite partner,MemberPartnerElite direct,MemberPartnerElite indirect,
			boolean firstOrder,BigDecimal totalAmount,BigDecimal projectAllAmount,MemberIdentity_Type memberType,Long partnerMemberId,Long projectId,Long memberId,Long stageId,Long taskId,Date startTime,Date endTime,
			MemberIncomeService memberIncomeService,MemberAccountService memberAccountService,PlatformOutOrderService platformOutOrderService,MemberPartnerEliteService memberPartnerEliteService,PlatformFundService platformFundService){
		PartnerCommissionRatio ratio=new PartnerCommissionRatio();
		Map<String,Object> directMap=memberPartnerEliteService.findPartnerEliteDirectCount(partner.getId(), startTime, endTime);
		Map<String,Object> indirectMap=memberPartnerEliteService.findPartnerEliteInDirectCount(partner.getId(), startTime, endTime);
		BigDecimal firstCommission=compare("本人人才渠道【"+partnerMemberId+"】",partner.getCalculateEliteCount(),partner.getCalculateTaskCount(),partner.getCalculateTotalAmount(), partnerRule.getPartnerOwnRule().getFirstLadder(), partnerRule.getPartnerOwnRule().getSecondLadder(), partnerRule.getPartnerOwnRule().getThreeLadder());//一级比例
		BigDecimal secondCommission=compare("直接人才渠道【"+(direct==null?"":direct.getMemberId())+"】",(Integer)directMap.get("eliteCount"),(Integer)directMap.get("taskCount"),(BigDecimal)directMap.get("taskAmount"),partnerRule.getPartnerDirectRule().getFirstLadder(), partnerRule.getPartnerDirectRule().getSecondLadder(), partnerRule.getPartnerDirectRule().getThreeLadder());//二级比例
		BigDecimal threeCommission=compare("间接人才渠道【"+(indirect==null?"":indirect.getMemberId())+"】",(Integer)indirectMap.get("eliteCount"),(Integer)indirectMap.get("taskCount"),(BigDecimal)indirectMap.get("taskAmount"), partnerRule.getPartnerIndirectRule().getFirstLadder(), partnerRule.getPartnerIndirectRule().getSecondLadder(), partnerRule.getPartnerIndirectRule().getThreeLadder());;//三级比例
		ratio.setFirstCommission(firstCommission);
		ratio.setSecondCommission(secondCommission);
		ratio.setThreeCommission(threeCommission);
		
		Integer calculateEliteCount=partner.getCalculateEliteCount()==null?0:partner.getCalculateEliteCount();//结算人才总量
		Integer taskCount=partner.getTaskCount()==null?0:partner.getTaskCount();//总任务数量
		Integer calculateTaskCount=partner.getCalculateTaskCount()==null?0:partner.getCalculateTaskCount();//结算任务总量
		BigDecimal taskTotalAmount=partner.getTotalAmount()==null?new BigDecimal(0):partner.getTotalAmount();//任务总额
		BigDecimal calculateTaskTotalAmount=partner.getCalculateTotalAmount()==null?new BigDecimal(0):partner.getCalculateTotalAmount();//结算任务额
		
		if(firstOrder){
			//签单奖励
			BigDecimal awardAmount=null;
			if(MemberIdentity_Type.elite.equals(memberType)){
				awardAmount=partnerRule.getTaskOrderAward();
			}else if(MemberIdentity_Type.cto.equals(memberType)){
				awardAmount=compareCTOAward(projectAllAmount, partnerRule.getFirstProjectOrderAward(), partnerRule.getSecondProjectOrderAward(), partnerRule.getThreeProjectOrderAward());
			}
		
			log.info("渠道人才方【"+partnerMemberId+"】首单奖励金额："+awardAmount);
			//增加渠道方的收益记录
			memberIncomeService.createMemberIncome(new MemberIncome(partnerMemberId, projectId, Income_Type.parent_order_award, awardAmount,new BigDecimal(0),null,"首单奖励", memberId,stageId,taskId));
			//更新渠道方的账户收益和余额
			memberAccountService.updateMemberAccountTotalIncome(partnerMemberId, awardAmount, awardAmount, Data_Oper.sum);
			//增加平台出账订单
			platformOutOrderService.createPlatformOutOrder(partnerMemberId, PlatformOutOrder_Type.commission_income, projectId, null,null, awardAmount,new BigDecimal(0));
			//添加平台流水记录
			platformFundService.createPlatformFund("我的首单奖励",(taskId==null?projectId:taskId)+"", projectId, stageId, partnerMemberId, awardAmount, PlatformFund_Type.income, PlatformFund_Status.success);
		}
		try {
			taskCount=taskCount+1;
			calculateEliteCount=calculateEliteCount+1;
			calculateTaskCount=calculateTaskCount+1;
			calculateTaskTotalAmount=calculateTaskTotalAmount.add(totalAmount);
			taskTotalAmount=taskTotalAmount.add(totalAmount);
			Date startOrderTime=taskCount==0?new Date():partner.getStartOrderTime();
			Date lastOrderTime=new Date();
			Long id=partner.getId();
			log.info("更新渠道人才方【"+partner.getId()+"】的结算数据taskCount:"+taskCount+",taskTotalAmount:"+taskTotalAmount+",calculateEliteCount:"+calculateEliteCount+",calculateTaskCount:"+calculateTaskCount+",calculateTaskTotalAmount:"+calculateTaskTotalAmount);
			memberPartnerEliteService.updateMemberPartnerEliteAsCalculate(id, taskCount, taskTotalAmount, calculateEliteCount, calculateTaskCount, calculateTaskTotalAmount, startOrderTime, lastOrderTime);
		} catch (Exception e) {
			log.error("保存人才渠道方："+partner.getId()+"出现异常："+e.getMessage());
		}
		return ratio;
	}
	
	public static BigDecimal compare(String desc,Integer calculateEliteCount,Integer calculateTaskCount,BigDecimal calculateTaskTotalAmount,LadderRule firstLadder,LadderRule secondLadder,LadderRule threeLadder){
		BigDecimal commissionRatio=null;
		try {
			
			calculateEliteCount=calculateEliteCount==null?0:calculateEliteCount;
			calculateTaskCount=calculateTaskCount==null?0:calculateTaskCount;
			calculateTaskTotalAmount=calculateTaskTotalAmount==null?new BigDecimal(0):calculateTaskTotalAmount;
			log.info(desc+"的签约人才总数："+calculateEliteCount+"，当前周期内签约人才数："+calculateEliteCount+"，当前周期内人才做任务总数："+calculateTaskCount+"，当前周期做任务总额："+calculateTaskTotalAmount);
			//结算人数大于等于第三阶梯最小值且结算任务大于等于最小值且结算金额大于等于最小值，同时满足则为：第三阶梯(p>=121 and t>=31 and o>=24)
			//结算人数满足第二阶梯人数条件且结算任务数大于等于第二阶梯最小值且结算金额大于第二阶梯最小值，或结算任务数满足条件且结算人数大于第二阶梯最小数且结算金额大于第二阶梯最小值，或结算金额满足条件且结算人数大于等于最小值且结算任务数大于等于最小值，
			//第二阶梯：(41<=p<=120 and t>=11 and o>=8 or 11<=t<=30 and t>=41 and o>=8 or 8<=o<24 and p>=41 and t>=11)
			if(calculateEliteCount>=threeLadder.getMinElite()
					&&calculateTaskCount>=threeLadder.getMinTask()
					&&calculateTaskTotalAmount.compareTo(new BigDecimal(threeLadder.getMinAmount()))!=-1){
				commissionRatio=threeLadder.getCommissionRatio();
			}else if((calculateEliteCount>=secondLadder.getMinElite()
					&&calculateEliteCount<=secondLadder.getMaxElite()
					&&calculateTaskCount>=secondLadder.getMinElite()
					&&calculateTaskTotalAmount.compareTo(new BigDecimal(secondLadder.getMinAmount()))!=-1)
					||(calculateEliteCount>=secondLadder.getMinElite()
					&&calculateEliteCount<=secondLadder.getMaxElite()
					&&calculateEliteCount>=secondLadder.getMinElite()
					&&calculateTaskTotalAmount.compareTo(new BigDecimal(secondLadder.getMinAmount()))!=-1)
					||(calculateTaskTotalAmount.compareTo(new BigDecimal(secondLadder.getMinAmount()))!=-1
					&&calculateTaskTotalAmount.compareTo(new BigDecimal(secondLadder.getMaxAmount()))!=1
					&&calculateEliteCount>=secondLadder.getMinElite()
					&&calculateTaskCount>=secondLadder.getMinElite())){
				commissionRatio=secondLadder.getCommissionRatio();
			}else{
				commissionRatio=firstLadder.getCommissionRatio();	
			}
		} catch (Exception e) {
		}
		return commissionRatio;
	}
	
	private static BigDecimal compareCTOAward(BigDecimal amount,ProjectOrderAward firstProjectOrderAward,ProjectOrderAward secondProjectOrderAward, ProjectOrderAward threeProjectOrderAward){
		BigDecimal orderAward=null;
		if(amount.compareTo(new BigDecimal(firstProjectOrderAward.getMinAmount()))!=-1
				&& amount.compareTo(new BigDecimal(firstProjectOrderAward.getMaxAmount()))!=1){
			orderAward=firstProjectOrderAward.getOrderAward();
		}else if(amount.compareTo(new BigDecimal(secondProjectOrderAward.getMinAmount()))!=-1
				&& amount.compareTo(new BigDecimal(secondProjectOrderAward.getMaxAmount()))!=1){
			orderAward=secondProjectOrderAward.getOrderAward();
		}else if(amount.compareTo(new BigDecimal(threeProjectOrderAward.getMinAmount()))!=-1
				&& amount.compareTo(new BigDecimal(threeProjectOrderAward.getMaxAmount()))!=1){
			orderAward=threeProjectOrderAward.getOrderAward();
		}
		return orderAward;
	}

}

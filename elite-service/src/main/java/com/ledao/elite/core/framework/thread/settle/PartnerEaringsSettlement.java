package com.ledao.elite.core.framework.thread.settle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberIncome;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.domain.partner.PartnerProjectRecord;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Type;
import com.ledao.elite.core.domain.platform.PlatformOutOrder.PlatformOutOrder_Type;
import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Status;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Data_Oper;
import com.ledao.elite.core.framework.dto.PartnerCompanyIncomeRule;
import com.ledao.elite.core.framework.dto.PartnerEliteIncomeRule;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.partner.PartnerEliteService;
import com.ledao.elite.core.service.partner.PartnerProjectRecordService;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.platform.PlatformOutOrderService;
import com.ledao.elite.core.service.project.ProjectDefineService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 渠道方结算算法
 * */
@Slf4j
@Component("partnerEaringsSettlement")
public class PartnerEaringsSettlement{
	
	
	@Resource
	private MemberIncomeService memberIncomeService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private PartnerEliteService partnerEliteService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private PartnerProjectRecordService partnerProjectRecordService;
	@Resource
	private MemberPartnerEliteService memberPartnerEliteService;
	@Resource
	private MemberPartnerCompanyService memberPartnerCompanyService;
	@Resource
	private ProjectDefineService projectDefineService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private PlatformOutOrderService platformOutOrderService;
	@Resource
	private PlatformFundService platformFundService;
	@Resource
	private DictService dictService;
	
	@SuppressWarnings("incomplete-switch")
	public void settle(Long memberId,MemberIdentity_Type memberType,BigDecimal totalAmount,Long projectId,
			Long stageId,Long taskId,boolean firstStage,boolean lastStage,MemberIdentity_Type partnerType){
		try {
			String threadName=Thread.currentThread().getId()+":"+Thread.currentThread().getName()+":"+Thread.currentThread().getState().name();
			log.info("**********************************线程【"+threadName+"】【"+memberType+"】的【"+partnerType.label+"】的提成收益结算线程开始处理**********************************");
			if(memberId==null||totalAmount==null)
				return;
			BigDecimal firstCommission=null;//一级比例
			BigDecimal secondCommission=null;//二级比例
			BigDecimal threeCommission=null;//三级比例
			
			Long partnerMemberId=null;//渠道方会员ID
			Long directMemberId=null;//当前渠道的直接领导
			Long indirectMemberId=null;//当前渠道的间接领导
			
			String incomeIntro="";
			PartnerElite elite=null;
			PartnerProjectRecord record=null;
			//检查每个类型的推荐数据
			switch (partnerType) {
			case partnerElite:
				elite=this.partnerEliteService.findPartnerEliteBymemberId(memberId);//获取会员对应的渠道方信息
				if(elite==null){
					return;
				}
				MemberPartnerElite partner=elite.getPartner();
				partnerMemberId=partner.getMemberId();
				log.info("开始结算会员【"+memberId+"】的人才渠道方【"+partnerMemberId+"】的提成收益***********");
				Dict dict=dictService.findDictByKeyAndType(Dict.Dict_Type.partnerElite_option.name(), Dict.PartnerIncome_Option_Key.PartnerEliteIncomeRule.name());
				if(dict==null)return;
				String ruleJson=dict.getDictVal();
				PartnerEliteIncomeRule partnerEliteIncomeRule=new Gson().fromJson(ruleJson, PartnerEliteIncomeRule.class);
				incomeIntro="推荐"+memberType+"人才做任务的提成";
				
				//如果会员被禁用或被删除则不结算
				MemberPassport memberPassport=this.memberPassportService.findMemberPassportById(partnerMemberId);
				if(memberPassport==null||Member_Status.disabled.equals(memberPassport.getStatus())
						||Member_Status.deleted.equals(memberPassport.getStatus())){
					log.info("渠道【"+partnerMemberId+"】已经被禁用------------------");
					return;
				}
				//检索其上级渠道
				MemberPartnerElite directPE=null;
				MemberPartnerElite indirectPE=null;
				if(partner.getParentId()!=null){
					directPE=this.memberPartnerEliteService.findMemberPartnerEliteById(partner.getParentId());
					if(directPE!=null&&directPE.getParentId()!=null){
						indirectPE=this.memberPartnerEliteService.findMemberPartnerEliteById(directPE.getParentId());
					}
					directMemberId=directPE.getMemberId();//直接渠道
					indirectMemberId=indirectPE==null?null:indirectPE.getMemberId();//间接渠道
				}
				log.info("----------人才【"+memberId+"】的本人渠道【"+partner+"】直接渠道【"+directPE+"】间接渠道【"+indirectPE+"】-----------------");
				
				ProjectDefine pd=projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.cto, ProjectDefine_Status.normal);
				if(pd==null)return;
				//计算是否首单
				boolean firstOrder=false;
				if(MemberIdentity_Type.elite.equals(memberType)){
					Integer projectCount=this.memberIncomeService.findMemberIncomeProjectCount(memberId,Income_Type.task);
					if(projectCount==0){
						firstOrder=true;
					}
				}else if(MemberIdentity_Type.cto.equals(memberType)&&lastStage){
					Integer projectCount=this.memberIncomeService.findMemberIncomeProjectCount(memberId,Income_Type.stage);
					//如果只有一个阶段，那count应该==0
					List<ProjectDefineStage> pdsList = this.projectDefineStageService.findProjectDefineStageByDefinedId(pd.getId());
					if(pdsList.isEmpty())return;
					int stageSize=pdsList.size();
					int flagCount=stageSize==1?0:1;
					if(projectCount==flagCount){
						firstOrder=true;
					}
				}
				Integer period=partnerEliteIncomeRule.getStatisticsPeriod();
				Date startTime=DateTimeUtils.getDateMonthFirstDay(partner.getClearCalculateTime(), period);
				Date endTime=DateTimeUtils.getDateMonthLastDay(partner.getClearCalculateTime(), period);
				log.info("会员"+memberType+"【"+memberId+"】是否首单："+firstOrder+"渠道方【"+partner.getMemberId()+"】的上次周期结算时间："+partner.getClearCalculateTime()+",本次统计时间范围："+DateTimeUtils.defaultFormat.format(startTime)+"【至】"+DateTimeUtils.defaultFormat.format(endTime));
				
				BigDecimal projectAllAmount=pd==null?new BigDecimal(0):pd.getTotalAmount();
				//计算渠道方的比例提成
				PartnerEaringsThread2EliteUtils.PartnerCommissionRatio pet=PartnerEaringsThread2EliteUtils.settlePartnerElite(partnerEliteIncomeRule, partner, directPE, indirectPE, firstOrder, totalAmount, projectAllAmount, memberType, 
						partnerMemberId, projectId, memberId, stageId, taskId, startTime,endTime,memberIncomeService, memberAccountService, platformOutOrderService, memberPartnerEliteService,platformFundService);
				firstCommission=pet.getFirstCommission();
				secondCommission=pet.getSecondCommission();
				threeCommission=pet.getThreeCommission();
				break;
	        case partnerCompany:
	        	record=this.partnerProjectRecordService.findPartnerProjectRecordByProjectId(projectId);
	        	if(record==null){
	        		return;
	        	}
	        	MemberPartnerCompany company=record.getPartner();
				partnerMemberId=company.getMemberId();
	        	Dict dict2=dictService.findDictByKeyAndType(Dict.Dict_Type.partnerCompany_option.name(), Dict.PartnerIncome_Option_Key.PartnerCompanyIncomeRule.name());
				if(dict2==null)return;
				String ruleJson2=dict2.getDictVal();
	        	PartnerCompanyIncomeRule partnerCompanyIncomeRule=new Gson().fromJson(ruleJson2, PartnerCompanyIncomeRule.class);
	        	
	        	log.info("开始结算项目【"+projectId+"】是否第一阶段【"+firstStage+"】的项目渠道方【"+record.getPartner().getMemberId()+"】的提成收益***********");
	        	
	        	incomeIntro="项目推荐的提成";
				//如果会员被禁用或被删除则不结算
				memberPassport=this.memberPassportService.findMemberPassportById(partnerMemberId);
				if(memberPassport==null||Member_Status.disabled.equals(memberPassport.getStatus())
						||Member_Status.deleted.equals(memberPassport.getStatus())){
					return;
				}
				//检索其上级渠道
				MemberPartnerCompany direct=null;
				MemberPartnerCompany indirect=null;
				if(company.getParentId()!=null){
					direct=this.memberPartnerCompanyService.findMemberPartnerCompanybyId(company.getParentId());
					if(direct!=null&&direct.getParentId()!=null){
					     indirect=this.memberPartnerCompanyService.findMemberPartnerCompanybyId(direct.getParentId());
					}
					directMemberId=direct.getMemberId();//直接渠道
					indirectMemberId=indirect==null?null:indirect.getMemberId();//间接渠道
				}
				//计算渠道方的比例提成
				PartnerEaringsThread2CompanyUtils.PartnerCommissionRatio pct=PartnerEaringsThread2CompanyUtils.settlePartnerCompany(partnerCompanyIncomeRule, company, direct, indirect, firstStage,lastStage, totalAmount, partnerMemberId,
						projectId, memberId, stageId, taskId, memberIncomeService, memberAccountService, platformOutOrderService, memberPartnerCompanyService,platformFundService);
				firstCommission=pct.getFirstCommission();
				secondCommission=pct.getSecondCommission();
				threeCommission=pct.getThreeCommission();
				break;
			}
			log.info(partnerType.label+"的一级【"+partnerMemberId+"】收益比例："+firstCommission+",二级【"+directMemberId+"】收益比例："+secondCommission+",三级【"+indirectMemberId+"】收益比例："+threeCommission+",本次任务总额："+totalAmount);
			if(firstCommission==null)return;
			
			BigDecimal earnings=totalAmount.multiply(firstCommission);
			BigDecimal tax=CommonUtils.calculateAmountTax(earnings);//税额
			BigDecimal atIncome=earnings.subtract(tax);//税后收入=本金-税
			
			log.info("开始计算【"+memberType+"】对应的"+partnerType.label+"的本人渠道方ID:"+partnerMemberId+"的收益总金额："+earnings+",其中含税："+tax+",其税后所得："+atIncome);
			//一级渠道收益
			//增加渠道方的收益记录
			this.memberIncomeService.createMemberIncome(new MemberIncome(partnerMemberId, projectId, Income_Type.partner_own, atIncome,tax,firstCommission,incomeIntro, memberId,stageId,taskId));
			//更新渠道方的账户收益和余额
			this.memberAccountService.updateMemberAccountTotalIncome(partnerMemberId, atIncome, atIncome, Data_Oper.sum);
			//增加平台出账订单
			this.platformOutOrderService.createPlatformOutOrder(partnerMemberId, PlatformOutOrder_Type.commission_income, projectId, null,null, atIncome,tax);
			//添加平台流水记录
			this.platformFundService.createPlatformFund("我的提成收益",(taskId==null?projectId:taskId)+"", projectId, stageId, partnerMemberId, atIncome, PlatformFund_Type.income, PlatformFund_Status.success);
			
			//更新对应的推荐数据
			switch (partnerType) {
			case partnerElite:
				//更新对人才的收益
				elite.setIncome(elite.getIncome().add(earnings));
				this.partnerEliteService.updatePartnerElite(elite);
				break;
	        case partnerCompany:
	        	//按阶段更新的？
	        	this.partnerProjectRecordService.updatePartnerProjectRecordAsIncome(projectId, earnings);
				break;
			}
			
			//二级渠道收益
			if(directMemberId!=null){
				earnings=totalAmount.multiply(secondCommission);
				BigDecimal stax=CommonUtils.calculateAmountTax(earnings);//税额
				BigDecimal satIncome=earnings.subtract(stax);//税后收入=本金-税
				log.info("开始计算【"+memberType+"】对应的"+partnerType.label+"的直接渠道ID:"+directMemberId+"的收益金额："+earnings+",其中含税："+stax+",其税后所得："+satIncome);
				//增加渠道方的收益记录
				this.memberIncomeService.createMemberIncome(new MemberIncome(directMemberId, projectId, Income_Type.partner_direct, satIncome,stax,secondCommission,incomeIntro+"(直接渠道)", partnerMemberId,stageId,taskId));
				//更新渠道方的账户收益和余额
				this.memberAccountService.updateMemberAccountTotalIncome(directMemberId, satIncome, satIncome, Data_Oper.sum);
				//增加平台出账订单
				this.platformOutOrderService.createPlatformOutOrder(directMemberId, PlatformOutOrder_Type.commission_income, projectId, null,null, satIncome,stax);
				//添加平台流水记录
				this.platformFundService.createPlatformFund("我的提成收益",(taskId==null?projectId:taskId)+"", projectId, stageId, directMemberId, satIncome, PlatformFund_Type.income, PlatformFund_Status.success);
			}
			
			//三级渠道收益
			if(indirectMemberId!=null){
				earnings=totalAmount.multiply(threeCommission);
				BigDecimal stax=CommonUtils.calculateAmountTax(earnings);//税额
				BigDecimal satIncome=earnings.subtract(stax);//税后收入=本金-税
				log.info("开始计算【"+memberType+"】对应的"+partnerType.label+"的间接渠道ID:"+indirectMemberId+"的收益金额："+earnings+",其中含税："+stax+",其税后所得："+satIncome);
				//增加渠道方的收益记录
				this.memberIncomeService.createMemberIncome(new MemberIncome(indirectMemberId, projectId, Income_Type.partner_indirect, satIncome,stax,threeCommission,incomeIntro+"(间接渠道)", partnerMemberId,stageId,taskId));
				//更新渠道方的账户收益和余额
				this.memberAccountService.updateMemberAccountTotalIncome(indirectMemberId, satIncome, satIncome, Data_Oper.sum);
				//增加平台出账订单
				this.platformOutOrderService.createPlatformOutOrder(indirectMemberId, PlatformOutOrder_Type.commission_income, projectId, null,null, satIncome,stax);
				//添加平台流水记录
				this.platformFundService.createPlatformFund("我的提成收益",(taskId==null?projectId:taskId)+"", projectId, stageId, indirectMemberId, satIncome, PlatformFund_Type.income, PlatformFund_Status.success);
			}
			
			log.info("**************************************线程【"+threadName+"】【"+memberType+"】的【"+partnerType.label+"】的提成收益结算线程处理结束**************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

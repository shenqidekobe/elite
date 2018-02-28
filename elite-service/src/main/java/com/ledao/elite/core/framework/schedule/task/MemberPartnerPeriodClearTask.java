package com.ledao.elite.core.framework.schedule.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.dto.PartnerCompanyIncomeRule;
import com.ledao.elite.core.framework.dto.PartnerEliteIncomeRule;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.core.utils.DateTimeUtils;

/**
 * 渠道人周期到时清空结算数据处理任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Component
public class MemberPartnerPeriodClearTask extends ExpireAbstractTask{
	
	@Resource
	private MemberPartnerEliteService memberPartnerEliteService;
	@Resource
	private MemberPartnerCompanyService memberPartnerCompanyService;
	@Resource
	private DictService dictService;

	@Override
	public void execute() throws EliteBusinessException {
		log.info("*****渠道方周期到时清空结算数据任务处理开始执行*****");
		Dict dict=dictService.findDictByKeyAndType(Dict.Dict_Type.partnerElite_option.name(), Dict.PartnerIncome_Option_Key.PartnerEliteIncomeRule.name());
		String ruleJson=dict.getDictVal();
		PartnerEliteIncomeRule partnerEliteIncomeRule=new Gson().fromJson(ruleJson, PartnerEliteIncomeRule.class);
		
		List<MemberPartnerElite> mpeList=memberPartnerEliteService.findMemberPartnerEliteListByStatus(Member_Status.normal);
		for(MemberPartnerElite mpe:mpeList){
			Date calculateTime=mpe.getClearCalculateTime();
			Integer period=partnerEliteIncomeRule.getStatisticsPeriod();
			if(validateExpirePeriod(calculateTime, period)){
				log.info("渠道人才方【"+mpe.getId()+"】的已过周期统计时间范围，开始清空其结算数据，上次清除时间："+calculateTime);
				this.memberPartnerEliteService.updateMemberPartnerEliteClearCalculate(mpe.getId(), new Date());
			}
		}
		
		Dict dict2=dictService.findDictByKeyAndType(Dict.Dict_Type.partnerCompany_option.name(), Dict.PartnerIncome_Option_Key.PartnerCompanyIncomeRule.name());
		if(dict2==null)return;
		String ruleJson2=dict2.getDictVal();
    	PartnerCompanyIncomeRule partnerCompanyIncomeRule=new Gson().fromJson(ruleJson2, PartnerCompanyIncomeRule.class);
		
		List<MemberPartnerCompany> mpcList=memberPartnerCompanyService.findMemberPartnerEliteCompanyByStatus(Member_Status.normal);
        for(MemberPartnerCompany mpc:mpcList){
        	Date calculateTime=mpc.getClearCalculateTime();
        	Integer period=partnerCompanyIncomeRule.getStatisticsPeriod();
        	if(validateExpirePeriod(calculateTime, period)){
        		log.info("渠道项目方【"+mpc.getId()+"】的已过周期统计时间范围，开始清空其结算数据，上次清除时间："+calculateTime);
        		this.memberPartnerCompanyService.updateMemberPartnerEliteClearCalculate(mpc.getId(), new Date());
        	}
		}
	}
	
	//验证时间是否在当前周期内
	private boolean validateExpirePeriod(Date clearCalculateTime,Integer period){
		boolean flag=false;
		clearCalculateTime=clearCalculateTime==null?new Date():clearCalculateTime;
		Date calPeriodDate=DateTimeUtils.getDateMonthLastDay(clearCalculateTime, period);
		//清空结算时间大于周期的最后一天，则过期
		log.info("清空结算时间clearCalculateTime："+DateTimeUtils.defaultFormat.format(clearCalculateTime)+",结算周期最后一天calPeriodDate："+DateTimeUtils.defaultFormat.format(calPeriodDate));
		if(clearCalculateTime.before(calPeriodDate)){
			flag=true;
		}
		return flag;
	}
	
}

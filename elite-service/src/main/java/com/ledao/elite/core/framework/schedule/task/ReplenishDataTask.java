package com.ledao.elite.core.framework.schedule.task;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.platform.PlatformOutOrder;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.platform.PlatformOutOrderService;

/**
 * 补充数据任务，比如出账订单补充会员姓名方便查询等
 * */
@Component
public class ReplenishDataTask extends ExpireAbstractTask{
	
	@Resource
	private PlatformOutOrderService platformOutOrderService;
	@Resource
	private MemberCreditService memberCreditService;

	@Override
	public void execute() throws EliteBusinessException {
		log.info("*****补充数据的任务处理开始执行*****");
		SearchResult<PlatformOutOrder> sr=platformOutOrderService.findPlatformOutOrderList(null,null, null, new Pager(1,Integer.MAX_VALUE));
		if(sr==null)return;
		Map<Long,MemberCredit> map=new HashMap<>();
		for(PlatformOutOrder oo:sr.getResult()){
			if(oo.getMemberName()!=null)continue;
			Long memberId=oo.getMemberId();
			if(memberId==null)continue;
			MemberCredit memberCredit=null;
			if(map.containsKey(memberId)){
				memberCredit=map.get(memberId);
			}else{
				memberCredit=memberCreditService.findMemberCreditByMemberId(oo.getMemberId());
			}
			if(memberCredit==null)continue;
			map.put(memberId, memberCredit);
			oo.setMemberName(memberCredit.getRealName());
			this.platformOutOrderService.updatePlatformOutOrder(oo);
		}
	}

}

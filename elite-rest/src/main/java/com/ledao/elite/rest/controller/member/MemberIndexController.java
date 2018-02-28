package com.ledao.elite.rest.controller.member;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.platform.PlatforMarketing;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Marketing_Platform;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Marketing_Type;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Role_Channel;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.platform.PlatforMarketingService;
import com.ledao.elite.rest.controller.BaseController;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.request.MemberCircleRequest;
import com.ledao.elite.rest.framework.request.RPlatforMarketingRequest;
import com.ledao.elite.rest.framework.response.PlatforMarketingResponse;
import com.ledao.elite.rest.framework.response.member.RMemberCircle;
import com.ledao.elite.rest.framework.utils.RestUtils;

/**
 * 首页
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("memberIndexController")
@RequestMapping("/member")
public class MemberIndexController  extends BaseController{
	
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private PlatforMarketingService platforMarketingService;
	
	/**
	 * 精英圈列表查询
	 * */
	@ResponseBody
	@RequestMapping(value="/circle/listData",method=RequestMethod.POST)
	public ModelAndView eliteCircleListData(){
		String reqJson=parseRequest("role","pageth","keyword","memberId");
		MemberCircleRequest request=JSON.parseObject(reqJson, MemberCircleRequest.class);
		Pager pager = new Pager();
		pager.setPageth(request.getPageth());
		SearchResult<MemberPassport>  sr=this.memberPassportService.findMemberPassportListByEliteCircle(request.getMemberId(),request.getKeyword(), request.getRole(), null, null, null, pager);
		List<RMemberCircle> memberlist = new ArrayList<RMemberCircle>();
		for(MemberPassport member:sr.getResult()){
			memberlist.add(new RMemberCircle(member));
		}
		ResponseResultData<List<RMemberCircle>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(memberlist);
	}
	
	/**
	 * 入驻精英查询 
	 */
	@ResponseBody
	@RequestMapping(value="/homeElite/listData",method=RequestMethod.POST)
	public ModelAndView homeElitetListData(){
		Object memberId=RestUtils.getRequestParam("memberId");
		List<MemberDetailInfo> list = memberPassportService.findMemberEliteListAuditPass(3);
		List<RMemberCircle> memberlist= new ArrayList<>();
		for(MemberDetailInfo info:list){
			RMemberCircle member = new RMemberCircle(info);
			if(memberId!=null){
				MemberAttention maObj=this.memberAttentionService.checkAttentioned(Long.parseLong(memberId.toString()), info.getId());
				member.setAttentioned(maObj!=null);
			}
			memberlist.add(member);
		}
		ResponseResultData<List<RMemberCircle>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(memberlist);
	}
	
	
	/**
	 * 首页banner或活动
	 */
	@ResponseBody
	@RequestMapping(value="/platform/listData",method=RequestMethod.POST)
	public ModelAndView platformListData(){
		String reqJson=parseRequest("type");
		RPlatforMarketingRequest request = JSON.parseObject(reqJson, RPlatforMarketingRequest.class);
		List<PlatforMarketing> list = platforMarketingService.findPlatforMarketingList(Marketing_Platform.valueOf("app"), Marketing_Type.valueOf(request.getType()),request.getChannel()==null?null:Role_Channel.valueOf(request.getChannel()));
		List<PlatforMarketingResponse> marketingList= new ArrayList<>();
		for(PlatforMarketing marketing:list){
			marketingList.add(new PlatforMarketingResponse(marketing));
		}
		ResponseResultData<List<PlatforMarketingResponse>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(marketingList);
	}
}

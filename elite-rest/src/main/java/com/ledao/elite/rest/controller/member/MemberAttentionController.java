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
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.project.ProjectAttentionService;
import com.ledao.elite.core.service.project.ProjectTaskAttentionService;
import com.ledao.elite.rest.controller.BaseController;
import com.ledao.elite.rest.framework.RequestBaseRest;
import com.ledao.elite.rest.framework.ResponseBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.request.AttentionListRequest;
import com.ledao.elite.rest.framework.response.member.RMemberCircle;

/**
 * 会员关注信息接口中心
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("memberAttentionController")
@RequestMapping("/member/attention")
public class MemberAttentionController  extends BaseController{
	
	
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private ProjectAttentionService projectAttentionService;
	@Resource
	private ProjectTaskAttentionService projectTaskAttentionService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberEliteService memberEliteService;
	

	/**
	 *添加/取消关注
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveAttention(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long attentionMemberId=request.getDataId();
		Long memberId=getMemberId();
		ResponseBaseRest result=new ResponseBaseRest();
		//只有精英才能互相关注
		MemberElite elite=this.memberEliteService.findMemberEliteByMemberId(memberId);
		if(elite==null){
			result.setCode(ErrorCodeEnum.NOT_ELITE.code);
			result.setMsg("当前非精英，不能互相关注");
		}
		MemberAttention obj = this.memberAttentionService.checkAttentioned(memberId, attentionMemberId);
		if(obj!=null){
			memberAttentionService.removePhysicalById(getMemberId(), attentionMemberId);
		}else{
			obj = new MemberAttention();
			obj.setMemberId(getMemberId());
			obj.setAttentionMemberId(attentionMemberId);
			memberAttentionService.addPhysicalById(obj);
		}
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(result);
	}
	
	/**
	 * 会员关注信息
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/listData",method=RequestMethod.POST)
	public ModelAndView viewListData(){
		String reqJson=parseRequest("type","pageth");
		AttentionListRequest request=JSON.parseObject(reqJson, AttentionListRequest.class);
		String keyWord=request.getKeyword(),type=request.getType();
		Pager pager = new Pager();
		pager.setPageth(request.getPageth());
		SearchResult<MemberAttention> sr = memberAttentionService.findAttenTionUser(type, getMemberId(), keyWord, pager);
		List<RMemberCircle> list = new ArrayList<>();
		for (MemberAttention m : sr.getResult()) {
			MemberPassport member;
			if(type.equals("attentioned")){
				member = memberPassportService.findMemberDetailInfoById(m.getAttentionMemberId());
			}else{
				member = memberPassportService.findMemberDetailInfoById(m.getMemberId());
			}
			MemberAttention maObj = this.memberAttentionService.checkAttentioned(getMemberId(), member.getId());
			member.setAttentioned(maObj != null);
			list.add(new RMemberCircle(member));
		}
		ResponseResultData<List<RMemberCircle>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(list);
	}
	
	/**
	 *是否关注
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/check",method=RequestMethod.POST)
	public ModelAndView checkAttentioned(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long attentionMemberId=request.getDataId();
		MemberAttention memberAttention = memberAttentionService.checkAttentioned(getMemberId(), attentionMemberId);
		Boolean flag= memberAttention!=null;
		ResponseResultData<Boolean> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(flag);
	}
	
}

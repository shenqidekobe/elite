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
import com.ledao.elite.core.domain.member.MemberMessage;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Status;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.dto.MemberMessageInfo;
import com.ledao.elite.core.service.member.MemberMessageService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.rest.controller.BaseController;
import com.ledao.elite.rest.framework.RequestBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.request.MessageListRequest;
import com.ledao.elite.rest.framework.response.project.RMemberMessage;

/**
 * 会员消息的接口中心
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("memberMessageController")
@RequestMapping("/member")
public class MemberMessageController extends BaseController{

	@Resource
	private MemberMessageService memberMessageService;
	@Resource
	private ProjectService projectService;
	
	/**
	 * 会员消息首页
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/message/index",method=RequestMethod.POST)
	public ModelAndView index(){
		List<MemberMessageInfo> list = memberMessageService.findIndexMessage(getMemberId());
		ResponseResultData<List<MemberMessageInfo>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(list);
	}
	
	/**
	 * 会员消息列表查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/message/listData",method=RequestMethod.POST)
	public ModelAndView messageListData(){
		String reqJson=parseRequest("type");
		MessageListRequest request=JSON.parseObject(reqJson, MessageListRequest.class);
		String status=request.getStatus(),type=request.getType();
		Long projectId=request.getProjectId();
		Pager pager=new Pager(0, 100);
		SearchResult<MemberMessage> sr=this.memberMessageService.findMemberMessageList(getMemberId(), status, type,projectId,pager);
		List<RMemberMessage> messages = new ArrayList<RMemberMessage>();
		for(MemberMessage message:sr.getResult()){
			messages.add(new RMemberMessage(message));
		}
		ResponseResultData<List<RMemberMessage>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(messages);
	}
	
	/**
	 * 查看消息
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/message/get",method=RequestMethod.POST)
	public ModelAndView messageView(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long id=request.getDataId();
		MemberMessage message=this.memberMessageService.findMemberMessageById(id);
		//更新状态为已读
		this.memberMessageService.updateMemberMessageStatus(id, MemberMessage_Status.read.name());
		
		ResponseResultData<MemberMessage> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(message);
	}
}

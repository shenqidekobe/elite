package com.ledao.elite.site.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberMessage;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Status;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.member.MemberMessageService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.site.controller.BaseController;

/**
 * 会员消息接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("memberMessgaeController")
@RequestMapping("member")
public class MemberMessageController extends BaseController{
	
	@Resource
	private MemberMessageService memberMessageService;
	@Resource
	private ProjectService projectService;
	
	/**
	 * 会员消息首页
	 * */
	@SSOToken
	@RequestMapping(value="/message",method=RequestMethod.GET)
	public String index(Model model){
		//检查是否有未读消息
		Integer unreadCount=this.memberMessageService.findMemberMessageCount(getMemberId(),null,MemberMessage_Status.unread.name(),null);
		model.addAttribute("unreadCount", unreadCount);
		return "/member/message/message";
	}
	
	/**
	 * 检查会员是否有未读消息
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/message/examine",method=RequestMethod.POST)
	public Map<String,Integer> examineUnreadMessage(String type,Long projectId){
		Map<String,Integer> map=new HashMap<>();
		Integer unreadCount=this.memberMessageService.findMemberMessageCount(getMemberId(),null,MemberMessage_Status.unread.name(),projectId);
		Integer typeUnread=this.memberMessageService.findMemberMessageCount(getMemberId(), type, MemberMessage_Status.unread.name(),projectId);
		Integer typeRead=this.memberMessageService.findMemberMessageCount(getMemberId(), type, MemberMessage_Status.read.name(),projectId);
		map.put("unreadCount", unreadCount);
		map.put("typeUnread", typeUnread);
		map.put("typeRead", typeRead);
		map.put("messageCount", (typeUnread+typeRead));
		return map;
	}
	
	/**
	 * 会员消息列表查询
	 * */
	@SSOToken
	@RequestMapping(value="/message/list",method=RequestMethod.POST)
	public String messageList(String type,Model model){
		Integer readCount=this.memberMessageService.findMemberMessageCount(getMemberId(), type,MemberMessage_Status.read.name(),null);
		Integer unreadCount=this.memberMessageService.findMemberMessageCount(getMemberId(), type,MemberMessage_Status.unread.name(),null);
		model.addAttribute("projectList",this.projectService.findProjectListByCompanyId(getMemberId()));
		model.addAttribute("readCount", readCount);
		model.addAttribute("unreadCount", unreadCount);
		model.addAttribute("messageCount", (readCount+unreadCount));
		model.addAttribute("type", type);
		return "/member/message/message_list";
	}
	
	/**
	 * 会员消息列表查询
	 * */
	@SSOToken
	@RequestMapping(value="/message/listData",method=RequestMethod.POST)
	public String messageListData(String status,String type,Long projectId,Pager pager,Model model){
		if(pager==null)
			new Pager(0, 30);
		SearchResult<MemberMessage> sr=this.memberMessageService.findMemberMessageList(getMemberId(), status, type,projectId,pager);
		pager.calPageCount((long)sr.getTotalCount());
		model.addAttribute("status", status);
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pagination",pager);
		return "/member/message/message_list_frag";
	}
	
	/**
	 * 查看消息
	 * */
	@SSOToken
	@RequestMapping(value="/message/{id}/view",method=RequestMethod.POST)
	public String messageView(@PathVariable Long id,Model model){
		MemberMessage message=this.memberMessageService.findMemberMessageById(id);
		//更新状态为已读
		this.memberMessageService.updateMemberMessageStatus(id, MemberMessage_Status.read.name());
		model.addAttribute("obj",message);
		return "/member/message/message_view";
	}

}

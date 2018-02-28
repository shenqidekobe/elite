package com.ledao.elite.site.controller.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberTeam;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.member.MemberTeamService;
import com.ledao.elite.site.controller.BaseController;

/**
 * @author Chenghao
 * @version 1.0
 * */
@Controller("MemberTeamController")
@RequestMapping("/member/team")
public class MemberTeamController  extends BaseController{

	@Resource
	private MemberTeamService memberTeamService;
	
	
	/**
	 * CTO团队信息
	 * */
	@SSOToken
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public String viewMemberTeam(){
		return "/member/cto/team/team_list";
	}

	
	@SSOToken
	@RequestMapping(value="/listData",method=RequestMethod.POST)
	public String TeamList(Model model,Pager pager,String keyWord){
		SearchResult<MemberTeam> sr = memberTeamService.findMyMemberTeam(keyWord,getMemberId(),pager);
		model.addAttribute("result", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		return "/member/cto/team/team_list_frag";
	}
	
}

package com.ledao.elite.site.controller.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberTeam;
import com.ledao.elite.core.domain.project.ProjectAttention;
import com.ledao.elite.core.domain.project.ProjectTaskAttention;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberTeamService;
import com.ledao.elite.core.service.project.ProjectAttentionService;
import com.ledao.elite.core.service.project.ProjectTaskAttentionService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.site.controller.BaseController;
import com.ledao.elite.site.dto.ResponseResult;

/**
 * 精英互相关注控制管理中心
 * @author chenghao
 * @version 1.0
 * */
@Controller("MemberAttentionController")
@RequestMapping("/member/attendtion")
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
	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	@Resource
	private MemberTeamService memberTeamService;
	
	
	/**
	 * 会员关注信息
	 * */
	@SSOToken
	@RequestMapping(value="/view",method=RequestMethod.POST)
	public String viewAttention(){
		return "/member/attention/attention_list";
	}
	
	/**
	 * 会员关注信息
	 * */
	@SSOToken
	@RequestMapping(value="/listData",method=RequestMethod.POST)
	public String viewListData(String type,String keyWord,Model model,Pager pager){
		SearchResult<MemberAttention> sr = memberAttentionService.findAttenTionUser(type, getMemberId(), keyWord, pager);
		for (MemberAttention m : sr.getResult()) {
			if(type.equals("attentioned")){
				MemberTeam team = memberTeamService.findMemberTeamByMemberId(m.getMemberId(),m.getAttentionMemberId());
				MemberDetailInfo memberDetailInfo =  memberPassportService.findMemberDetailInfoById(m.getAttentionMemberId());
				MemberAttention maObj = this.memberAttentionService.checkAttentioned(getMemberId(),m.getAttentionMemberId());
				memberDetailInfo.setAttentioned(maObj != null);
				m.setDetailInfo(memberDetailInfo);
				m.setPartnershipNum(team==null?0:team.getPartnershipNum());
			}else{
				MemberTeam team = memberTeamService.findMemberTeamByMemberId(m.getAttentionMemberId(),m.getMemberId());
				MemberDetailInfo memberDetailInfo =  memberPassportService.findMemberDetailInfoById(m.getMemberId());
				MemberAttention maObj = this.memberAttentionService.checkAttentioned(getMemberId(),m.getMemberId());
				memberDetailInfo.setAttentioned(maObj != null);
				m.setDetailInfo(memberDetailInfo);
				m.setPartnershipNum(team==null?0:team.getPartnershipNum());
			}
		}
		model.addAttribute("list",sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		model.addAttribute("type", type);
	    return "/member/attention/attention_list_frag";
	}
	
	/**
	 * 会员关注项目信息
	 * */
	@SSOToken
	@RequestMapping(value="project/listData",method=RequestMethod.POST)
	public String viewProjectList(Model model,Pager pager){
		SearchResult<ProjectAttention> sr = projectAttentionService.findAttenTionProjects(getMemberId(), pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination",pager);
		return null;
	}
	
	/**
	 * 会员关注项目任务信息
	 * */
	@SSOToken
	@RequestMapping(value="projectTask/listData",method=RequestMethod.POST)
	public String viewProjectTaskList(Model model,Pager pager){
		SearchResult<ProjectTaskAttention> sr = projectTaskAttentionService.findAttenTionProjectTasks(getMemberId(), pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination",pager);
		return null;
	}
	
	/**
	 *添加/取消关注
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/addOrRemoveAttention",method=RequestMethod.POST)
	public ResponseResult<String> addOrRemoveAttention(Model model,Long attentionMemberId,String type){
		Long memberId=getMemberId();
		//只有精英才能互相关注
		MemberElite elite=this.memberEliteService.findMemberEliteByMemberId(memberId);
		if(elite==null){
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"不是精英，暂不能关注哦");
		}
		MemberAttention memberAttention = null;
		if("remove".equals(type)){
			memberAttention = memberAttentionService.removePhysicalById(getMemberId(), attentionMemberId);
		}else{
			if(memberId.equals(attentionMemberId)){
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"自己不能关注自己");
			}
			memberAttention = new MemberAttention();
			memberAttention.setMemberId(getMemberId());
			memberAttention.setAttentionMemberId(attentionMemberId);
			memberAttentionService.addPhysicalById(memberAttention);
		}
		return new ResponseResult<>();
	}
	
	/**
	 *是否关注
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/checkAttentioned",method=RequestMethod.POST)
	public Boolean checkAttentioned(Model model,Long attentionMemberId){
		MemberAttention memberAttention = memberAttentionService.checkAttentioned(getMemberId(), attentionMemberId);
		return memberAttention!=null;
	}
	
	/**
	 * 搭伙次数
	 */
	@SSOToken
	@RequestMapping(value="/getCooperationCount",method=RequestMethod.POST)
	public void getCooperationCount(Model model,Long attentionMemberId){
	}
	
}

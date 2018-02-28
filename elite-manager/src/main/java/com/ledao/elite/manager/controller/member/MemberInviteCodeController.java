package com.ledao.elite.manager.controller.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 会员邀请码控制器
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("memberInviteCodeController")
@RequestMapping("/member")
public class MemberInviteCodeController extends BaseController {

	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private PlatformInviteCodeService platformInviteCodeService;

	/**
	 * 给精英发验证码首页
	 * @param model
	 * @param currentType
	 * @return
	 */
	@RequestMapping(value = "/invitecode/index", method = RequestMethod.POST)
	private String inviteCodeIndex(Model model, String currentType) {
		model.addAttribute("currentType", currentType);
		return "/member/invitecode/list";
	}

	/**
	 * 给精英发验证码精英查询
	 * @param model
	 * @param keyword
	 * @param currentType
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/invitecode/listData", method = RequestMethod.POST)
	private String inviteCodeList(Model model, String keyword, String currentType, Pager pager,String orderType,int changePageSize) {
		pager.setPageSize(changePageSize);
		SearchResult<MemberPassport> sr = this.memberPassportService.findMemberPassportListByCurrentType(keyword, null,
				currentType, pager,orderType);
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pager", pager);
		model.addAttribute("orderType", orderType);
		return "/member/invitecode/list_frag";
	}

	/**
	 * 给精英发验证码
	 * @param model
	 * @param ids
	 * @return
	 */
	@SystemHandleLog(description = "给精英发送邀请码", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/invitecode/sendCode", method = RequestMethod.POST)
	private ResponseBase sendCode(Model model,@RequestParam(value = "ids[]") long[] ids) {
		this.platformInviteCodeService.createPlatformInviteCodesToElite(ids);
		return new ResponseBase();
	}

}

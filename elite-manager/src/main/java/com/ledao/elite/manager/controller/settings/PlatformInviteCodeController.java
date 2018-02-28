package com.ledao.elite.manager.controller.settings;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.domain.platform.PlatformInviteCode.InviteCode_Type;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 邀请码管理控制器
 * 
 */
@Controller("platformInviteCodeController")
@RequestMapping("/settings")
public class PlatformInviteCodeController extends BaseController {
	@Resource
	private PlatformInviteCodeService platformInviteCodeService;

	/**
	 * 邀请码管理首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/invitecode")
	private String index(Model model) {
		model.addAttribute("types", InviteCode_Type.values());
		return "/settings/invitecode/list";
	}

	/**
	 * 组合条件查询邀请码
	 * 
	 * @param model
	 * @param keyword(邀请码)
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/invitecode/listData", method = RequestMethod.POST)
	private String listDate(Model model, String keyword, String type, Pager pager) {
		SearchResult<PlatformInviteCode> sr = this.platformInviteCodeService.findPaltformInviteCodes(keyword, type,
				pager);
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("list", sr.getResult());
		model.addAttribute("pager", pager);
		return "/settings/invitecode/list_frag";
	}

	/**
	 * 生成邀请码页面显示
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/invitecode/addview", method = RequestMethod.POST)
	private String viewAdd(Model model) {
		model.addAttribute("types", InviteCode_Type.values());
		return "/settings/invitecode/invitecode_addview";
	}

	/**
	 * 生成邀请码
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/invitecode/save", method = RequestMethod.POST)
	private ResponseBase save(Model model, PlatformInviteCode code) {
		this.platformInviteCodeService.createPlatformInviteCode(code);
		return new ResponseBase();
	}

	/**
	 * 查询邀请码全部
	 * 
	 * @param model
	 * @param keyword(邀请码)
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/invitecode/listAllData", method = RequestMethod.POST)
	private String listAllDate(Model model) {
		List<PlatformInviteCode> codes = this.platformInviteCodeService.findAllPaltformInviteCodes();
		model.addAttribute("list", codes);
		return "/settings/invitecode/alllist_frag";
	}

}

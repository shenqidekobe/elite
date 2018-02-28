package com.ledao.elite.manager.controller.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberDetailInfoService;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 项目方管理中心
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("memberCompanyController")
@RequestMapping("/member")
public class MemberCompanyController extends BaseController {

	@Resource
	private MemberCompanyService memberCompanyService;

	@Resource
	private MemberPartnerCompanyService memberPartnerCompanyService;

	@Resource
	private MemberPassportService memberPassportService;

	@Resource
	private MemberDetailInfoService memberDetailInfoService;
	@Resource
	private DictService dictService;

	/**
	 * 查询审核项目方资料,跳转到详情页面
	 */
	@RequestMapping(value = "company/{type}/view")
	public String view(@PathVariable String type, Model model, Long id) {
		MemberDetailInfo member = this.memberPassportService.findMemberDetailInfoById(id);
		List<Dict>dicts=this.dictService.findDictListByDictType(Dict_Type.company_scale.name());
		model.addAttribute("member", member);
		model.addAttribute("dicts", dicts);
		return "/member/company/" + type;
	}

	/**
	 * 协助完善项目方资料
	 */
	@SystemHandleLog(description = "协助完善项目方信息", type = LogsEnum.update)
	@RequestMapping(value = "company/prefect/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBase prefect(MemberDetailInfo obj, Long memberId) {
		this.memberDetailInfoService.updateMemberDetailInfo(obj, memberId);
		return new ResponseBase();
	}
	
	/**
	 * 协助完善项目方资料
	 */
	@RequestMapping(value = "company/searchlist", method = RequestMethod.POST)
	public String searchCompanyList(Model model,String keyword) {
		List<MemberPassport> list = this.memberPassportService.findMemberpassPortCompanyList(keyword);
		model.addAttribute("list", list);
		return "/project/bm/company_frag";
		
	}
}

package com.ledao.elite.manager.controller.channel;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.domain.partner.PartnerRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Type;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.partner.PartnerEliteService;
import com.ledao.elite.core.service.partner.PartnerRecordService;
import com.ledao.elite.manager.controller.BaseController;

import flexjson.JSONSerializer;

/**
 * 精英渠道控制器
 * 
 */
@Controller("partnerEliteController")
@RequestMapping("/channel")
public class PartnerEliteController extends BaseController {

	@Resource
	private MemberPassportService memberPassportService;

	@Resource
	private PartnerEliteService partnerEliteService;
	@Resource
	private MemberPartnerEliteService memberPartnerEliteService;
	@Resource
	private PartnerRecordService partnerRecordService;
	/**
	 * 根据id查询会员信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("partnerElite/view")
	public String view(Model model, Long id) {

		MemberPassport member = this.memberPassportService.findMemberDetailInfoById(id);
	    MemberPartnerElite elite=this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(id);
		model.addAttribute("member", member);
		model.addAttribute("elite", elite);
		return "channel/elite/detail";
	}

	/**
	 * 推荐人才分页查询查询
	 * @param model
	 * @param pager
	 * @param partnerId
	 * @param memberId
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value = "partnerElite/elite/listData", method = RequestMethod.POST)
	public String elitteListDate(Model model, Pager pager, Long partnerId, Long memberId,String status,String keyword) {
		SearchResult<PartnerElite> result = this.partnerEliteService.findPartnerElitesByKeyWord(memberId,status, keyword, partnerId,null, null, null, pager);
		List<PartnerElite> list = result.getResult();
		pager.calPageCount((long) result.getTotalCount());
		model.addAttribute("eliteInfoUrl", localCoreConfig.getDomainServer());
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		return "channel/elite/elite_list_frag";
	}
	
	/**
	 * 推荐的渠道分页查询
	 * @param model
	 * @param pager
	 * @param partnerId
	 * @param memberId
	 * @param keyword
	 * @return
	 */						 	
	@RequestMapping(value = "partnerElite/partner/listData", method = RequestMethod.POST)
	public String partnerListDate(Model model, Pager pager, Long partnerId, Long memberId,String status,String keyword) {
		SearchResult<PartnerRecord> result = partnerRecordService.findPartnerRecords(partnerId,PartnerRecord_Type.partnerElite.name(),keyword,status,null,null,pager);
		pager.calPageCount((long) result.getTotalCount());
		model.addAttribute("list", result.getResult());
		model.addAttribute("pager", pager);
		return "channel/elite/partner_list_frag";
	}
	
	/**
	 * 渠道精英详情
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "company/detail/view")
	public String view(Long id) {
		PartnerElite elite = this.partnerEliteService.findPartnerEliteBymemberId(id);
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		return serializer.serialize(elite);
	}
	
}

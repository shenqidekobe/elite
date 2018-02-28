package com.ledao.elite.manager.controller.channel;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.partner.PartnerProject;
import com.ledao.elite.core.domain.partner.PartnerProjectRecord;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.partner.PartnerProjectRecordService;
import com.ledao.elite.core.service.partner.PartnerProjectService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 项目渠道控制器
 * 
 */
@Controller("partnerProjectController")
@RequestMapping("/channel")
public class PartnerProjectController extends BaseController {

	@Resource
	private MemberPassportService memberPassportService;

	@Resource
	private PartnerProjectService partnerProjectService;
	@Resource
	private PartnerProjectRecordService partnerProjectRecordService;

	/**
	 * 根据id查询会员信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("partnerProject/view")
	public String view(Model model, Long id) {

		MemberPassport member = this.memberPassportService.findMemberDetailInfoById(id);
		model.addAttribute("member", member);
		return "channel/company/detail";
	}

	/**
	 * 分页查询查询
	 * 
	 * @param model
	 * @param status
	 * @param areaId
	 * @param phone
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "partnerProject/listData", method = RequestMethod.POST)
	public String listDate(Model model, Long partnerId, String areaName, String keyword, String entryed, String status,
			Pager pager) {
		
		if ("true".equals(entryed)) {
			  SearchResult<PartnerProjectRecord>sr=this.partnerProjectRecordService.findPartnerProjectRecordByPartnerId(partnerId, keyword,null,null, pager);
			  List<PartnerProjectRecord> list = sr.getResult();
				pager.calPageCount((long) sr.getTotalCount());
				model.addAttribute("list", list);
				model.addAttribute("pager", pager);
			return "channel/company/checkedDetail_frag";
		} else {
			SearchResult<PartnerProject> result = this.partnerProjectService
					.findPartnerProjectsByStatusAndKeyWorld(partnerId, areaName, keyword, entryed, status,null,null, pager);
			List<PartnerProject> list = result.getResult();
			pager.calPageCount((long) result.getTotalCount());
			model.addAttribute("list", list);
			model.addAttribute("pager", pager);
			return "channel/company/recordDetail_frag";
		}
	}
}

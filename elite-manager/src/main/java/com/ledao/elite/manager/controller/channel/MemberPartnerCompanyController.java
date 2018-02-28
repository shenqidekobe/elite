package com.ledao.elite.manager.controller.channel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.partner.PartnerProject;
import com.ledao.elite.core.domain.partner.PartnerProjectRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Status;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Type;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord.WorkRecord_Type;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.constant.RoleEnum;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.partner.PartnerProjectRecordService;
import com.ledao.elite.core.service.partner.PartnerProjectService;
import com.ledao.elite.core.service.partner.PartnerRecordService;
import com.ledao.elite.core.service.platform.PlatformWorkRecordService;
import com.ledao.elite.core.service.sys.SysUserService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 项目方渠道控制器
 * 
 */
@Controller("memberPartnerCompanyController")
@RequestMapping("/channel")
public class MemberPartnerCompanyController extends BaseController {

	@Resource
	private MemberPartnerCompanyService memberPartnerCompanyService;
	@Resource
	private MemberPassportService memberPassprotService;
	@Resource
	private PartnerRecordService partnerRecordService;
	@Resource
	private MemberIncomeService	memberIncomeService;
	@Resource
	private PartnerProjectRecordService	partnerProjectRecordService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private MemberPartnerEliteService memberPartnerEliteService;
	@Resource
	private PlatformWorkRecordService platformWorkRecordService;
	@Resource
	private PartnerProjectService partnerProjectService;

	/**
	 * 项目渠道首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("project/list")
	public String list(Model model) {
		model.addAttribute("list", GlobalDefinedConstant.Cooperate_Type.values());
		model.addAttribute("status", Member_Status.values());
		return "channel/company/list";
	}

	/**
	 * 分页查询渠道中心 项目方
	 * @param model
	 * @param keyword
	 * @param type
	 * @param status
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "company/listData", method = RequestMethod.POST)
	public String listData(Model model, String keyword, String type, String status, Date startTime,Date endTime,Pager pager) {
		SearchResult<MemberPassport> result = this.memberPassprotService.findMemberPassportListByMemberParnter(keyword,
				type, MemberIdentity_Type.partnerCompany.name(), startTime,endTime,pager, status);
		List<MemberPassport> eliteList = result.getResult();
		pager.calPageCount((long) result.getTotalCount());
		Integer allCount = this.memberPassprotService.findMemberPartnerCount(MemberIdentity_Type.partnerCompany.name(),
				null,startTime,endTime);
		Integer normalCount = this.memberPassprotService
				.findMemberPartnerCount(MemberIdentity_Type.partnerCompany.name(), Member_Status.normal.name(),startTime,endTime);
		model.addAttribute("allCount", allCount);
		model.addAttribute("normalCount", normalCount);
		model.addAttribute("list", eliteList);
		model.addAttribute("pager", pager);
		return "channel/company/list_frag";

	}

	/**
	 * 分页查询渠道中心 项目方 详情
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "company/detail", method = RequestMethod.POST)
	public String detial(Model model, Long id) {
		MemberPassport member = this.memberPassprotService.findMemberDetailInfoById(id);
		MemberPartnerCompany company = member.getPartnerCompany();
		if(member.getPartnerCompany().getAuditId()!=null){
			SysUser user = sysUserService.findSysUserById(member.getPartnerCompany().getAuditId());
			model.addAttribute("user", user);
		}
		if (member.getPartnerCompany().getParentId() != null) {
			MemberPartnerCompany mem = this.memberPartnerCompanyService
					.findMemberPartnerCompanybyId(member.getPartnerCompany().getParentId());
			model.addAttribute("parent", mem);
		}
		if (company.getParentId() != null) {
			MemberPartnerCompany mem = this.memberPartnerCompanyService
					.findMemberPartnerCompanybyId(company.getParentId());
			company.setParentName(mem.getMemberPassport().getNickName());
		}
		if (company.getChargeId() != null) {
			SysUser user = this.sysUserService.findSysUserById(company.getChargeId());
			company.setChargeName(user.getUserName());
		}
		// 收益
		BigDecimal partnerIncome = memberIncomeService.findMemberIncomeSumByMemberId(member.getId(), null, null,
				null);
		// 入驻渠道数
		Integer enterParnterCount = partnerRecordService.findPartnerRecordByEnterCount(company.getId(),
				MemberIdentity_Type.partnerCompany.name(), PartnerRecord_Status.audit_pass.name());
		// 备案渠道数
		Integer putParnterCount = partnerRecordService.findPartnerRecordByEnterCount(company.getId(),
				MemberIdentity_Type.partnerCompany.name(), null);
		
		company.setPartnerIncome(partnerIncome);
		company.setEnterParnterCount(enterParnterCount);
		company.setPutParnterCount(putParnterCount);
		member.setPartnerCompany(company);
		
		List<PlatformWorkRecord> list = this.platformWorkRecordService.findPlatFromWorkRecords(WorkRecord_Type.partnerCompany.name(), id);
		model.addAttribute("member", member);
		model.addAttribute("list", list);
		return "channel/company/detail";
	}

	/**
	 * 显示项目渠道方审核内容
	 */
	@RequestMapping(value = "company/aduit/view", method = RequestMethod.POST)
	public String auditView(Model model, Long memberId) {
		MemberPassport member = this.memberPassprotService.findMemberDetailInfoById(memberId);
		model.addAttribute("it", member);
		return "channel/company/aduit";
	}

	/**
	 * 审核
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@SystemHandleLog(description = "审核项目渠道方", type = LogsEnum.update)
	@RequestMapping(value = "company/aduit", method = RequestMethod.POST)
	public ResponseBase audit(MemberPartnerCompany company) {
		
		// 备案操作
		if (company.getStatus().name().equals(Member_Status.normal.name())) {
			PartnerRecord record = this.partnerRecordService
					.findPartnerRecordByMemberIdAndStatus(company.getMemberId(),
							PartnerRecord_Status.registered);
			if (record != null) {
				record.setStatus(PartnerRecord_Status.audit_pass);
				this.partnerRecordService.updatePartnerRecordInfoNoFixed(record);
				company.setParentId(record.getParentId());
			}
		}
		company.setAuditId(getUserId());
		this.memberPartnerCompanyService.updateMemberPartnerCompanyAudit(company);
		// 审核通过发送消息给会员
		StringKeyValue message = null;
		String replace = null;
		String url = localCoreConfig.getDomainServer();
		if (Member_Status.normal.name().equals(company.getStatus().name())) {
			message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.COMPANY_PARTNER_AUDIT_PASS);
			replace = "<a href='" + url + "/member/index?rp=inviteRegister' target='_blank'>备案项目</a>";
		} else {
			message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.COMPANY_PARTNER_AUDIT_UNPASS);
			replace = "<a href='" + url + "/member/index' target='_blank'>修改</a>";
		}
		String content = String.format(message.getValue(), replace);
		eventPublishService.publishMessageEvent(company.getMemberId(), null, null, message.getKey(), content, false,
				MemberMessage_Type.system);
		return new ResponseBase();
	}
	
	/**
	 * 推荐的项目列表
	 * @param model
	 * @param pager
	 * @param partnerId
	 * @return
	 */
	@RequestMapping(value = "company/project/listData", method = RequestMethod.POST)
	public String projectListData(Model model,Pager pager,Long partnerId,String status) {
		if ("registered".equals(status)) {
			SearchResult<PartnerProjectRecord> sr = partnerProjectRecordService.findPartnerProjectRecordByPartnerId(partnerId,null,null,null,pager);
            model.addAttribute("list", sr.getResult());
			pager.calPageCount((long) sr.getTotalCount());
			model.addAttribute("pagination", pager);
			return "channel/company/project_enter_list_frag";
		}
		// 备案未入住项目查询
		else {
			SearchResult<PartnerProject> sr = partnerProjectService.findPartnerProjectsByStatusAndKeyWorld(partnerId, null, null, null, null, null,null,pager);
			model.addAttribute("list", sr.getResult());
			pager.calPageCount((long) sr.getTotalCount());
			model.addAttribute("pager", pager);
		}
		return "channel/company/project_put_list_frag";
	}
	
	/**
	 * 推荐的渠道列表
	 * @param model
	 * @param pager
	 * @param partnerId
	 * @return
	 */
	@RequestMapping(value = "company/partner/listData", method = RequestMethod.POST)
	public String partnerListData(Model model,String status,String keyword,Pager pager,Long partnerId) {
		SearchResult<PartnerRecord> result = partnerRecordService.findPartnerRecords(partnerId,PartnerRecord_Type.partnerCompany.name(),keyword,status,null,null,pager);
		pager.calPageCount((long) result.getTotalCount());
		model.addAttribute("list", result.getResult());
		model.addAttribute("pager", pager);
		return "channel/company/partner_list_frag";
	}

	/**
	 * 收益结构
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{type}/income", method = RequestMethod.POST)
	public String income(@PathVariable String type,Model model,Long id) {
		//总收益
		BigDecimal totalIncome = memberIncomeService.findMemberIncomeSumByMemberId(id,null,null,null);
		//直接项目收益
		BigDecimal projectIncome = memberIncomeService.findMemberIncomeSumByMemberId(id,null,null,Income_Type.partner_own.name());
		//直接渠道收益
		BigDecimal channelIncome = memberIncomeService.findMemberIncomeSumByMemberId(id,null,null,Income_Type.partner_direct.name());
		//间接渠道收益
		BigDecimal indirectIncome = memberIncomeService.findMemberIncomeSumByMemberId(id,null,null,Income_Type.partner_indirect.name());
		model.addAttribute("totalIncome", totalIncome);
		model.addAttribute("projectIncome", projectIncome);
		model.addAttribute("channelIncome", channelIncome);
		model.addAttribute("indirectIncome", indirectIncome);
		return "channel/"+type+"/income_detail";
	}
	
	/**
	 *分配bm
	 */
	@RequestMapping(value = "/{type}/allot/view")
	public String alloutPmView(@PathVariable String type,Model model, Long id) {
		MemberPartnerCompany company = memberPartnerCompanyService.findMemberPartnerCompanyByMemberId(id);
		MemberPartnerElite elite = memberPartnerEliteService.findMemberPartnerEliteByMemberId(id);
		String chargeName="";
		if (company!=null && company.getChargeId() != null) {
			SysUser user = this.sysUserService.findSysUserById(company.getChargeId());
			chargeName = user.getUserName();
		}else if(elite!=null  && elite.getChargeId() != null){
			SysUser user = this.sysUserService.findSysUserById(elite.getChargeId());
			chargeName = user.getUserName();
		}
		model.addAttribute("company", company);
		model.addAttribute("elite", elite);
		model.addAttribute("chargeName", chargeName);
		return "channel/"+type+"/allot";
	}
	
	/**
	 * 查询bm用户列表
	 */
	@RequestMapping(value = "/{type}/allot/listData")
	public String listdata(@PathVariable String type,Model model, Long id, String keyword,Pager pager) {
		Long roleId = RoleEnum.business_manager.getRoleId();
		if(type.equals("elite")){
			roleId = RoleEnum.project_mamager.getRoleId();
		}
		SearchResult<SysUser> result = this.sysUserService.findSysUserListByKeyWordAndType(keyword, roleId, pager,
				GlobalDefinedConstant.System_Status.normal.name());
		pager.calPageCount((long) result.getTotalCount());
		model.addAttribute("list", result.getResult());
		model.addAttribute("pager", pager);
		return "channel/"+type+"/allotlist_frag";
	}
	
	/**
	 * 分配负责人
	 */
	@RequestMapping(value = "/company/allot/update")
	@ResponseBody
	public ResponseBase updateCharge(Model model, Long chargeId,Long memberId) {
		MemberPartnerCompany company = memberPartnerCompanyService.findMemberPartnerCompanyByMemberId(memberId);
		company.setChargeId(chargeId);
		memberPartnerCompanyService.updateMemberPartnerCompanyNoFixed(company);
		return  new ResponseBase();
	}

	@RequestMapping(value = "/{type}/view/remarks", method = RequestMethod.POST)
	public String viewRemarks(@PathVariable String type,Model model, Long id) {
		List<PlatformWorkRecord> list;
		if(type.equals("company")){
			list = this.platformWorkRecordService.findPlatFromWorkRecords(WorkRecord_Type.partnerCompany.name(), id);
			model.addAttribute("type", "partnerCompany");
		}else{
			list = this.platformWorkRecordService.findPlatFromWorkRecords(WorkRecord_Type.partnerElite.name(), id);
			model.addAttribute("type", "partnerElite");
		}
		model.addAttribute("list", list);
		model.addAttribute("foreignId", id);
		return "channel/"+type+"/remarks";
	}

}

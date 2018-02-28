package com.ledao.elite.manager.controller.channel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPartnerElite.MemberPartnerElite_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.domain.partner.PartnerRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Status;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord.WorkRecord_Type;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.partner.PartnerEliteService;
import com.ledao.elite.core.service.partner.PartnerRecordService;
import com.ledao.elite.core.service.platform.PlatformWorkRecordService;
import com.ledao.elite.core.service.sys.SysUserService;
import com.ledao.elite.manager.controller.BaseController;

/**
 * 精英渠道控制器
 * 
 */
@Controller("memberPartnerEliteController")
@RequestMapping("/channel")
public class MemberPartnerEliteController extends BaseController {

	@Resource
	private MemberPartnerEliteService memberPartnerEliteService;
	@Resource
	private MemberPassportService memberPassprotService;
	@Resource
	private PartnerRecordService partnerRecordService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private PlatformWorkRecordService platformWorkRecordService;
	@Resource
	private MemberIncomeService memberIncomeService;
	@Resource
	private MemberEliteService memberEliteService;
	@Resource
	private PartnerEliteService partnerEliteService;

	/**
	 * 人才渠道首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("member/list")
	public String list(Model model) {
		model.addAttribute("list", MemberPartnerElite_Type.values());
		model.addAttribute("status", Member_Status.values());
		return "channel/elite/list";
	}

	/**
	 * 分页查询渠道精英
	 * 
	 * @param model
	 * @param name
	 * @param phone
	 * @param email
	 * @param status
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "elite/listData", method = RequestMethod.POST)
	public String listData(Model model, String keyword, String type, Date startTime,Date endTime,Pager pager, String status) {
		SearchResult<MemberPassport> result = this.memberPassprotService.findMemberParnterList(keyword,
				type, MemberIdentity_Type.partnerElite.name(), startTime,endTime,pager, status);
		List<MemberPassport> eliteList = result.getResult();
		pager.calPageCount((long) result.getTotalCount());
		Integer allCount = this.memberPassprotService.findMemberPartnerCount(MemberIdentity_Type.partnerElite.name(),
				null,startTime,endTime);
		Integer normalCount = this.memberPassprotService.findMemberPartnerCount(MemberIdentity_Type.partnerElite.name(),
				Member_Status.normal.name(),startTime,endTime);
		model.addAttribute("allCount", allCount);
		model.addAttribute("normalCount", normalCount);
		model.addAttribute("list", eliteList);
		model.addAttribute("pager", pager);
		return "channel/elite/list_frag";
	}

	/**
	 * 显示项目渠道方审核内容
	 */
	@RequestMapping(value = "elite/aduit/view", method = RequestMethod.POST)
	public String auditView(Model model, Long memberId) {
		MemberPassport member = this.memberPassprotService.findMemberDetailInfoById(memberId);
		model.addAttribute("it", member);
		return "channel/elite/aduit";
	}

	/**
	 * 审核
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@SystemHandleLog(description = "审核渠道人才方", type = LogsEnum.update)
	@RequestMapping(value = "elite/aduit", method = RequestMethod.POST)
	public ResponseBase audit(Long memberId, String auditReason, String status, MemberPartnerElite elite) {
		// 备案操作
		if (elite.getStatus().name().equals(Member_Status.normal.name())) {
			PartnerRecord record = this.partnerRecordService
					.findPartnerRecordByMemberIdAndStatus(memberId,
							PartnerRecord_Status.registered);
			if (record != null) {
				record.setStatus(PartnerRecord_Status.audit_pass);
				this.partnerRecordService.updatePartnerRecordInfoNoFixed(record);
				elite.setParentId(record.getParentId());
			}
		}
		this.memberPartnerEliteService.updateMemberPartnerEliteAudit(elite);
		StringKeyValue message = null;
		String replace = null;
		String url = localCoreConfig.getDomainServer();
		if (status.equals("normal")) {
			message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_PARTNER_AUDIT_PASS);
			replace = "<a href='" + url + "/member/index?rp=inviteRegister' target='_blank'>备案人才</a>";
		} else {
			message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.COMPANY_PARTNER_AUDIT_UNPASS);
			replace = "<a href='" + url + "/member/index' target='_blank'>修改</a>";
		}
		String content = String.format(message.getValue(), replace);
		eventPublishService.publishMessageEvent(memberId, null, null, message.getKey(), content, false,
				MemberMessage_Type.system);
		return new ResponseBase();
	}

	/**
	 * 人才方 详情
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "elite/detail", method = RequestMethod.POST)
	public String detial(Model model, Long id) {
		MemberPassport member = this.memberPassprotService.findMemberDetailInfoById(id);
		MemberPartnerElite partnerElite = member.getPartnerElite();
		if(member.getPartnerElite().getAuditId()!=null){
			SysUser user = sysUserService.findSysUserById(member.getPartnerElite().getAuditId());
			model.addAttribute("user", user);
		}if(member.getPartnerElite().getParentId()!=null){
			MemberPartnerElite elite = memberPartnerEliteService.findMemberPartnerEliteById(member.getPartnerElite().getParentId());
			model.addAttribute("elite", elite);
		}
		// 推荐人姓名
		if (partnerElite.getParentId() != null) {
			MemberPartnerElite mem = this.memberPartnerEliteService.findMemberPartnerEliteById(partnerElite.getParentId());
			partnerElite.setParentName(mem.getMemberPassport().getNickName());
		}
		if (partnerElite.getChargeId() != null) {
			SysUser user = this.sysUserService.findSysUserById(partnerElite.getChargeId());
			partnerElite.setChargeName(user.getUserName());
		}
		// 收益
		BigDecimal partnerIncome = memberIncomeService.findMemberIncomeSumByMemberId(member.getId(), null, null,
				null);
		// 入驻渠道数
		Integer enterParnterCount = partnerRecordService.findPartnerRecordByEnterCount(partnerElite.getId(),
				MemberIdentity_Type.partnerElite.name(), PartnerRecord_Status.audit_pass.name());
		// 备案渠道数
		Integer putParnterCount = partnerRecordService.findPartnerRecordByEnterCount(partnerElite.getId(),
				MemberIdentity_Type.partnerElite.name(), null);
		
		partnerElite.setPartnerIncome(partnerIncome);
		partnerElite.setEnterParnterCount(enterParnterCount);
		partnerElite.setPutParnterCount(putParnterCount);
		member.setPartnerElite(partnerElite);
		
		List<PlatformWorkRecord> list = this.platformWorkRecordService.findPlatFromWorkRecords(WorkRecord_Type.partnerCompany.name(), id);
		model.addAttribute("member", member);
		model.addAttribute("list", list);
		return "channel/elite/detail";
	}
	
	/**
	 * 分配负责人
	 */
	@RequestMapping(value = "/elite/allot/update")
	@ResponseBody
	public ResponseBase updateCharge(Model model, Long chargeId,Long memberId) {
		MemberPartnerElite elite = memberPartnerEliteService.findMemberPartnerEliteByMemberId(memberId);
		elite.setChargeId(chargeId);
		memberPartnerEliteService.updateEliteInfoNoFixed(elite);
		return  new ResponseBase();
	}
	
	@MemberHandleLog(description = "人才推荐方_上传简历", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/partnerElite/save/resume")
	public ResponseBase savePartnerEliteResume(Long resumeId, String account) {
		MemberPassport member = this.memberPassprotService.findMemberPassportByAccount(account);
		Boolean updatePartnerElite = false;
		if (member != null) {
			MemberElite elite = this.memberEliteService.findMemberEliteById(member.getId());
			if (elite != null) {
				elite.setResumeAttaId(resumeId);
				this.memberEliteService.updateMemberElite(elite);
			} else {
				updatePartnerElite = true;
			}
		}
		if (member == null || updatePartnerElite) {
			PartnerElite elite = this.partnerEliteService.findPartnerEliteByAccount(account);
			elite.setAttaId(resumeId);
			this.partnerEliteService.updatePartnerElite(elite);
		}
		return  new ResponseBase();
	}

}

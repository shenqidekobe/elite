package com.ledao.elite.manager.controller.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberBank.MemberBank_Type;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberEliteJobs;
import com.ledao.elite.core.domain.member.MemberIdentity;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.cache.custom.CommandCache;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.framework.thread.newly.PartnerEliteEnterThread2;
import com.ledao.elite.core.service.member.MemberBankService;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberEliteJobsService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberIdentityService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.platform.PlatformWorkRecordService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.manager.controller.BaseController;
import com.ledao.elite.manager.model.EliteJobsModel;

/**
 * 会员管理中心
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("memberController")
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberEliteService memberEliteService;
	@Resource
	private PlatformWorkRecordService platformWorkRecordService;
	@Resource
	private MemberIdentityService memberIdentityService;
	@Resource
	private MemberEliteJobsService memberEliteJobsService;
	@Resource
	private MemberCompanyService memberCompanyService;
	@Resource
	private MemberBasicService MemberBasicService;
	@Resource
	private MemberBankService MemberBankService;
	@Resource
	private ProjectService projectService;
	@Resource
	protected EventPublishService eventPublishService;
	@Resource
	protected DictService dictService;

	/**
	 * 会员管理首页
	 */
	@RequestMapping(value = "{type}", method = RequestMethod.GET)
	public String index(@PathVariable String type, Model model) {
		List<Dict>jobs=this.dictService.findDicJobRoleTop(Dict_Type.job_role.name());
		model.addAttribute("jobs", jobs);
		model.addAttribute("list", Member_Status.values());
		model.addAttribute("type", type);
		return "/member/" + type + "/list";
	}

	/**
	 * 人才中心 会员列表
	 */
	@RequestMapping(value = "/{type}/listData", method = RequestMethod.POST)
	public String listData(@PathVariable String type, String keyword,Date startTime, Date endTime,Pager pager, Model model, String status,
			String inviteCode,String role) {
		Boolean ctoed = false;
		if (type.equals(MemberIdentity_Type.cto.name())) {
			ctoed = true;
		}
		SearchResult<MemberPassport> sr;
		if (type.equals(MemberIdentity_Type.company.name())) {
			sr = this.memberPassportService.findMemberPassportListByMemberCompany(keyword, status,MemberIdentity_Type.company.name(),startTime,endTime,pager);
			List<MemberPassport> memberlist = sr.getResult();
			for (int i = 0; i < memberlist.size(); i++) {
				MemberBasic basic = this.MemberBasicService.findMemberBasicByMemberId(memberlist.get(i).getId());
				memberlist.get(i).setBasic(basic);
				int count = this.projectService.findProjectDoingCountBy(memberlist.get(i).getId());
				memberlist.get(i).setProjectDoCount(count);
			}
			Integer normalCount = this.memberPassportService.findMemberCompanyCount(Member_Status.normal.name(),startTime,endTime);
			Integer allCount = this.memberPassportService.findMemberCompanyCount(null,startTime,endTime);
			model.addAttribute("normalCount", normalCount);
			model.addAttribute("allCount", allCount);
			sr.setResult(memberlist);
		} else {
			sr = this.memberPassportService.findMemberPassportListByMemberElite(keyword, ctoed, status, startTime,endTime,pager,inviteCode,role);
			Map<String, Integer> countMap = this.memberEliteService.findMemberEliteStatusCount(ctoed, null,startTime,endTime);
			model.addAttribute("normalCount", countMap.get(Member_Status.normal.name()));
			model.addAttribute("allCount", countMap.get("allCount"));
		}

		model.addAttribute("dataList", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pager", pager);
		return "/member/" + type + "/list_frag";
	}

	/**
	 * 会员详情列表
	 */
	@RequestMapping(value = "/{type}/detail")
	public String view(@PathVariable String type, Model model, Long id) {
		MemberDetailInfo member = this.memberPassportService.findMemberDetailInfoById(id);
		if (type.equals(MemberIdentity_Type.company.name())) {
			int count = this.projectService.findProjectDoingCountBy(member.getId());
			member.setProjectDoCount(count);
		}
		model.addAttribute("member", member);
		List<MemberBank> alipays = this.MemberBankService.findMemberBankByMemberIdAndType(id, MemberBank_Type.alipay);
		List<MemberBank> banks = this.MemberBankService.findMemberBankByMemberIdAndType(id, MemberBank_Type.bank);
		model.addAttribute("alipays", alipays);
		model.addAttribute("banks", banks);
		return "/member/" + type + "/detail";
	}

	/**
	 * 显示审核内容
	 */
	@RequestMapping(value = "/{type}/auditview")
	public String auditView(@PathVariable String type, Model model, Long id) {
		MemberDetailInfo member = this.memberPassportService.findMemberDetailInfoById(id);
		model.addAttribute("member", member);
		return "/member/" + type + "/audit";
	}

	/**
	 * 审核CTO，精英,项目方
	 * 
	 * @param noCto:审核通过为精英，但不通过CTO
	 */
	@SystemHandleLog(description = "审核会员", type = LogsEnum.update)
	@RequestMapping(value = "/{type}/audit")
	@ResponseBody
	public ResponseBase aduit(@PathVariable String type, Long memberId, String status, String auditReason,
			Boolean ctoSigned, String noCto) {
		String title = "";
		String content = "";
		String url = localCoreConfig.getDomainServer();
		if (MemberIdentity.MemberIdentity_Type.cto.name().equals(type)) {
			this.memberEliteService.updateMemberEliteAudit(memberId, getUserId(), auditReason, status, true);
			if (Member_Status.normal.name().equals(status)) {
				StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.CTO_AUDIT_PASS);
				title = message.getKey();
				String value = message.getValue();
				String replace = "<a href='" + url + "/circle' target='_blank'>精英圈</a>";
				content = String.format(value, replace);
			} else {
				StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_UPDATE_ROLE_UNPASS);
				title = message.getKey();
				content = message.getValue();
			}
		} else if (MemberIdentity.MemberIdentity_Type.company.name().equals(type)) {
			this.memberCompanyService.updateMemberCompanyAudit(memberId, getUserId(), auditReason, status);
			if (Member_Status.normal.name().equals(status)) {
				StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.COMPANY_AUDIT_PASS);
				String replace = "<a href='" + url + "/project/publish' target='_blank'>去发布项目</a>";
				title = message.getKey();
				content = String.format(message.getValue(), replace);
			} else {
				StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.COMPANY_AUDIT_UNPASS);
				title = message.getKey();
				String value = message.getValue();
				String replace = "<a href='" + url + "/member/personage' target='_blank'>去完善</a>";
				content = String.format(value, replace);
			}
		} else {
			if ("N".equals(noCto) || "NAgain".equals(noCto)) {
				ctoSigned = false;
			}
			StringKeyValue message = null;
			String replace = null;
			this.memberEliteService.updateMemberEliteAudit(memberId, getUserId(), auditReason, status, ctoSigned);
			if (Member_Status.normal.name().equals(status)) {
				if ("Y".equals(noCto) && ctoSigned == true) {
					message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_TO_CTO_PASS);
					replace = "<a href='" + url + "/circle' target='_blank'>精英圈</a>";
				}
				// 再次申请成为cto批复不通过
				else if ("NAgain".equals(noCto)) {
					message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_TO_CTO_UNPASS);
				} else {
					message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_AUDIT_PASS);
					replace = "<a href='" + url + "/hall' target='_blank'>精英圈认领任务</a>";
				}
			} else {
				message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_AUDIT_UNPASS);
				replace = "<a href='" + url + "/member/personage' target='_blank'>完善资料</a>";
			}
			title = message.getKey();
			String value = message.getValue();
			content = String.format(value, replace);
		}
		// 审核通过发送消息给会员
		eventPublishService.publishMessageEvent(memberId, null, null, title, content, false, MemberMessage_Type.system);

		// 审核通过，通知渠道方
		if (Member_Status.normal.name().equals(status)) {
			MemberPassport memberPassport = this.memberPassportService.findMemberPassportById(memberId);
			PartnerEliteEnterThread2 eliteEnterThread = new PartnerEliteEnterThread2();
			eliteEnterThread.setMemberId(memberId);
			eliteEnterThread.setAccount(memberPassport.getAccountOffSuffix());
			new Thread(eliteEnterThread).start();
		}
		return new ResponseBase();
	}

	/**
	 * 显示关停内容
	 */
	@RequestMapping(value = "/{type}/closeview")
	public String closeView(@PathVariable String type, Model model, Long memberId, String closetype) {
		MemberPassport member = this.memberPassportService.findMemberPassportById(memberId);
		model.addAttribute("member", member);
		// 查询关停记录
		List<PlatformWorkRecord> list = this.platformWorkRecordService.findPlatFromWorkRecords(closetype, memberId);
		model.addAttribute("list", list);
		return "member/" + type + "/close";
	}

	/**
	 * 关停CTO,精英
	 */
	@SystemHandleLog(description = "关停会员", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/{type}/updateclose")
	public ResponseBase updateClose(@PathVariable String type, Long memberId, double closeTime, String closeReason,
			String status) {
		String controlType;
		if (status.equals(Member_Status.normal.name())) {
			controlType = "停止关停";
		} else {
			controlType = "关停";
		}
		this.memberPassportService.updateCloseMemberPassport(memberId, status, closeTime, controlType, closeReason,
				getUserId());
		return new ResponseBase();
	}

	/**
	 * 显示评级信息
	 */

	@RequestMapping(value = "/{type}/levelview")
	public String levelView(@PathVariable String type, Model model, Long id) {
		List<MemberEliteJobs> eliteJobs = this.memberEliteJobsService.findMemberEliteJobsListByMemberId(id);
		model.addAttribute("jobs", eliteJobs);
		return "member/" + type + "/level";
	}

	/**
	 * 重新评级
	 */
	@ResponseBody
	@SystemHandleLog(description = "重新评级", type = LogsEnum.update)
	@RequestMapping(value = "/{type}/updatelevel")
	public ResponseBase updateLevel(@PathVariable String type, EliteJobsModel jobsModel) {
		List<MemberEliteJobs> jobList = jobsModel.getJobs();
		for (int i = 0; i < jobList.size(); i++) {
			MemberEliteJobs job = this.memberEliteJobsService.findMemberEliteJobsById(jobList.get(i).getId());
			job.setLevel(jobList.get(i).getLevel());
			this.memberEliteJobsService.updateMemberEliteJobsLevel(jobList.get(i).getId(), job);
		}
		return new ResponseBase();
	}

	/**
	 * 首页显示
	 */
	@ResponseBody
	@SystemHandleLog(description = "首页显示", type = LogsEnum.update)
	@RequestMapping(value = "/elite/updateHomeShow")
	public ResponseBase updateLevel(Model model, Long memberId, boolean homeShow) {
		this.memberPassportService.updateMemberHomeShow(memberId, homeShow);
		return new ResponseBase();
	}

	/**
	 * 禁用
	 */
	@ResponseBody
	@SystemHandleLog(description = "禁用", type = LogsEnum.disable)
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public ResponseBase disabled(Long memberId, String status) {
		MemberPassport member = this.memberPassportService.findMemberPassportById(memberId);
		member.setStatus(Member_Status.disabled);
		member.setDisabledId(getUserId());
		member.setDisabledTime(new Date());
		this.memberPassportService.updateDisableMemberPassport(member);
		return new ResponseBase();
	}

	/**
	 * 禁用
	 */
	@ResponseBody
	@SystemHandleLog(description = "启用", type = LogsEnum.enable)
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public ResponseBase enable(Long memberId, String status) {
		MemberPassport member = this.memberPassportService.findMemberPassportById(memberId);
		member.setStatus(Member_Status.normal);
		member.setDisabledId(getUserId());
		member.setDisabledTime(new Date());
		this.memberPassportService.updateDisableMemberPassport(member);
		return new ResponseBase();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@SystemHandleLog(description = "删除用户", type = LogsEnum.remove)
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseBase delete(Long memberId,String directive){
		if(!CommandCache.compare((getUserId()+"-"+memberId), directive)){
			return new ResponseBase("指令错误");
		}
		return new ResponseBase("此功能暂不开放");
		//this.memberPassportService.deleteMember(memberId);
		//return new ResponseBase();
	}

}

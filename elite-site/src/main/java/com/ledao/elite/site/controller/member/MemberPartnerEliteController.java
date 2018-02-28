package com.ledao.elite.site.controller.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAccount;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIdentity;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberIncome;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.domain.partner.PartnerElite;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Search_Type;
import com.ledao.elite.core.domain.partner.PartnerElite.PartnerElite_Status;
import com.ledao.elite.core.domain.partner.PartnerRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Status;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Type;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.domain.platform.PlatformInviteCode.InviteCode_Type;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.domain.sys.SmsRecord.Sms_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.IdCardCache;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.PartnerEliteIncomeRule;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.ledao.elite.core.service.partner.PartnerEliteService;
import com.ledao.elite.core.service.partner.PartnerRecordService;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.core.service.project.ProjectTaskRecruitService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.core.service.sys.SmsRecordService;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.site.controller.BaseController;
import com.ledao.elite.site.dto.ResponseResult;

/**
 * 合作伙伴接口
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("memberPartnerEliteController")
@RequestMapping("/partner")
public class MemberPartnerEliteController extends BaseController {
	
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private SmsRecordService smsRecordService;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private PartnerEliteService partnerEliteService;
	@Resource
	private MemberPartnerEliteService memberPartnerEliteService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private DictService dictService;
	@Resource
	private PlatformInviteCodeService platformInviteCodeService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private ProjectTaskRecruitService projectTaskRecruitService;
	@Resource
	private MemberIncomeService memberIncomeService;
	@Resource
	private PartnerRecordService partnerRecordService;
	@Resource
	private MemberEliteService memberEliteService;
	@Resource
	private MemberWithdrawService memberWithdrawService;

	/**
	 * 人才推荐方注册
	 */
	@MemberHandleLog(description = "人才推荐方注册", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/partnerElite/register", method = RequestMethod.POST)
	public ResponseResult<String> save(MemberPassport member, String verifycode, HttpServletRequest request) {
		boolean flag = this.smsRecordService.findSmsRecordIsExists(member.getAccount(), Sms_Type.register.name(),
				verifycode, null);
		if (!flag) {
			String password = member.getPassword();
			member.setCurrentType(MemberIdentity.MemberIdentity_Type.partnerElite.name());

			// 判断邀请码，推荐手机号
			if (StringUtils.isNotEmpty(member.getInviteCode())) {
				if (member.getInviteCode().length() == 11) {
					member.setInvitePhone(member.getInviteCode());
					member.setInviteCode(null);
				}
			}
			MemberPassport mepp = this.memberPassportService.createMemberPassport(member);
			String url = localCoreConfig.getDomainServer();
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.ELITE_PARTNER_REGISTER_SUCCESS);
			String replace = "<a href='" + url + "/member/index?rp=accountSecurity' >完善资料</a>";
			String content = String.format(message.getValue(), replace);
			eventPublishService.publishMessageEvent(mepp.getId(), null, null, message.getKey(), content, false,
					MemberMessage_Type.system);
			login(member.getAccount(), password, request);
			return new ResponseResult<>();
		}
		return new ResponseResult<>(GlobalDefinedConstant.FAILURE, "验证码错误");
	}

	/**
	 * 人才推荐方注册-基本信息 注册页面显示
	 */

	@RequestMapping(value = "/partnerElite/basic/register/view")
	public String partnerEliteBasicView(Model model) {
		MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(getMemberId());
		MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
		model.addAttribute("basic", basic);
		model.addAttribute("elite", elite);
		return "/member/partnerElite/registerBasic";
	}

	/**
	 * 人才推荐方注册-基本信息注册
	 */
	@SSOToken
	@MemberHandleLog(description = "人才推荐方基本信息修改", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/partnerElite/basicInfo/save", method = RequestMethod.POST)
	public ResponseResult<String> savePartnerEliteBasicInfo(MemberBasic basic, String type) {
		basic.setMemberId(getMemberId());
		this.memberPartnerEliteService.createMemberBasicInfo(type, basic);
		return new ResponseResult<>();
	}

	/**
	 * 人才推荐方注册-行业信息 注册页面显示
	 */
	@RequestMapping(value = "/partnerElite/industry/register/view")
	public String partnerEliteIndustryView(Model model) {
		MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(getMemberId());
		MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
		model.addAttribute("basic", basic);
		model.addAttribute("elite", elite);
		return "/member/partnerElite/registerIndustry";
	}

	/**
	 * 人才推荐方注册-行业信息
	 */
	@SSOToken
	@MemberHandleLog(description = "人才推荐行业信息注册", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/partnerElite/industry/save", method = RequestMethod.POST)
	public ResponseResult<String> savePartnerElite(MemberPartnerElite elite, String areaName) {
		MemberPartnerElite obj = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
		obj.setJobAge(elite.getJobAge());
		obj.setAttentionIndustry(elite.getAttentionIndustry());
		obj.setGoodatJob(elite.getGoodatJob());
		obj.setCompanyName(elite.getCompanyName());
		MemberBasic basic = new MemberBasic();
		basic.setAreaName(areaName);
		this.memberPartnerEliteService.createMemberPartnerEliteIndustry(getMemberId(), obj, basic);
		return new ResponseResult<>();
	}

	/**
	 * 人才推荐方注册-征信信息注册页面显示
	 */

	@RequestMapping(value = "/partnerElite/credit/register/view")
	public String partnerEliteCreditView(Model model) {
		MemberCredit credit = this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		model.addAttribute("credit", credit);
		return "/member/partnerElite/registerCredit";
	}

	/**
	 * 人才推荐方注册-征信信息
	 */
	@SSOToken
	@MemberHandleLog(description = "人才推荐征信信息上传", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/partnerElite/credit/save", method = RequestMethod.POST)
	public ResponseResult<String> savePartnerEliteCredite(MemberCredit credit) {
		credit.setMemberId(getMemberId());
		MemberCredit obj=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		//检查身份证姓名是否匹配
		if(obj==null||!obj.getIsCard()){
			boolean cflag=IdCardCache.valid(getMemberId());
			if(!cflag){
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"频繁认证，请明天再试");
			}
			boolean flag=this.memberCreditService.findValidateIdCard(credit.getRealName(), credit.getIdCard());
			if(!flag){
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE, "身份证号码和姓名不匹配");
			}
		}
		try {
			if(obj==null){
				this.memberCreditService.createMemberCredit(credit);
			}else{
				memberCreditService.updateMemberCreditByPartnerElite(getMemberId(), credit);
			}
		} catch (EliteServiceException e) {
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE, e.getMsg());
		}
		
		return new ResponseResult<>();
	}

	/**
	 * 人才推荐方_主页 (侧边栏选择)
	 */
	@SSOToken
	@RequestMapping(value = "/partnerElite/{type}")
	public String projectManageView(Model model, @PathVariable String type) {
		MemberPassport port = this.memberPassportService.findMemberDetailInfoById(getMemberId());
		MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
		// 冠军榜单
		if (type.equals("champions")) {
			BigDecimal myIncome = this.memberIncomeService.findMemberIncomeSumByMemberId(getMemberId(), null, null,
					null);
		    //收益规则
			PartnerEliteIncomeRule rule=(PartnerEliteIncomeRule)this.dictService.findDictPartnerRule(Dict_Type.partnerElite_option.name());
			model.addAttribute("rule", rule);
			model.addAttribute("myIncome", myIncome);
		} else
		// 邀请注册
		if (type.equals("inviteRegister")) {
			int registeredCount = 0;
			int enterCount = 0;
			int allCount = 0;
			List<PartnerElite> partnerElites = this.partnerEliteService.findPartnerEliteListByPartner(elite.getId());
			for (int i = 0; i < partnerElites.size(); i++) {
				if (partnerElites.get(i).getStatus().equals(PartnerElite_Status.registered)) {
					registeredCount++;
				} else if (partnerElites.get(i).getStatus().equals(PartnerElite_Status.audit_pass)) {
					enterCount++;
				}
			}
			allCount = partnerElites.size();
			model.addAttribute("registeredCount", registeredCount);
			model.addAttribute("enterCount", enterCount);
			model.addAttribute("allCount", allCount);
			// 邀请码 邀请链接
			PlatformInviteCode code = platformInviteCodeService.findPlatformInviteCodeByUserId(getMemberId(),
					InviteCode_Type.channel_elite);
			code.setHref(localCoreConfig.getDomainServer() + "/register?ts=elite");
			model.addAttribute("code", code);
		}
		// 收益中心
		else if (type.equals("revenue")) {
			MemberAccount account = this.memberAccountService.findMemberAccountByMemberId(getMemberId());
			MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
			model.addAttribute("account", account);
			model.addAttribute("credit", credit);

		}
		model.addAttribute("it", port);
		model.addAttribute("elite", elite);

		List<Dict> roleList = this.dictService.findRootDictListByDictType(Dict_Type.job_role.name());
		model.addAttribute("rolelist", roleList);
		List<Dict> ageList = this.dictService.findRootDictListByDictType(Dict_Type.job_age.name());
		model.addAttribute("agelist", ageList);
		model.addAttribute("typelist", MemberPartnerElite.MemberPartnerElite_Type.values());
		return "/member/partnerElite/" + type;
	}

	/**
	 * 人才推荐方_新增推荐精英人才备案
	 */
	@SSOToken
	@MemberHandleLog(description = "新增推荐精英人才备案", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/partnerElite/saveElite", method = RequestMethod.POST)
	public ResponseResult<String> createPartnerElite(PartnerElite elite) {
		MemberPartnerElite partnerElite = this.memberPartnerEliteService
				.findMemberPartnerEliteByMemberId(getMemberId());
		elite.setPartnerId(partnerElite.getId());
		elite.setStatus(PartnerElite_Status.noRegister);
		partnerEliteService.createPartnerEliteByPartner(elite);
		return new ResponseResult<>();
	}

	/**
	 * 新增推荐人才渠道备案
	 */
	@SSOToken
	@MemberHandleLog(description = "新增推荐人才渠道备案", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/memberPartnerElite/saveMemberPartnerElite", method = RequestMethod.POST)
	public ResponseResult<String> createMemberPartnerElite(PartnerRecord record) {
		MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
		record.setParentId(elite.getId());
		record.setPartnerType(PartnerRecord_Type.partnerElite);
		record.setStatus(PartnerRecord_Status.no_register);
		this.partnerRecordService.createPartnerRecord(record);
		return new ResponseResult<>();
	}

	/**
	 * 冠军列表
	 */
	@SSOToken
	@RequestMapping(value = "/partnerElite/champions/listData")
	public String championListData(Model model, Pager pager, String searchType, Date startTime, Date endTime) {
		MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
		Long parentId;
		if(endTime!=null){
			endTime=DateTimeUtils.addOrSub(endTime, 1);
		}
		if (pager != null) {
			pager.setPageSize(7);
		}
		// 我所在团队
		if ("myParentTeam".equals(searchType)) {
			parentId = elite.getParentId();
			if (parentId != null) {
				MemberPartnerElite partnerElite = this.memberPartnerEliteService.findMemberPartnerEliteById(parentId);
				model.addAttribute("partnerElite", partnerElite);
				Integer ranking = this.memberPartnerEliteService.findMemberPartnerEliteIndex(parentId, getMemberId(),
						startTime, endTime, Member_Status.normal.name());
				model.addAttribute("ranking", ranking);
			}

		}
		// 我的团队
		else {
			parentId = elite.getId();
		}
		SearchResult<MemberPartnerElite> sr = this.memberPartnerEliteService.findMemberPartnerEliteListByPartnerId(
				parentId, startTime, endTime, Member_Status.normal.name(), pager);

		// 冠亚季军
		List<MemberPartnerElite> toplist = this.memberPartnerEliteService.findMemberPartnerEliteListTopThree(parentId,
				startTime, Member_Status.normal.name(), endTime);

		// 会员收益
		BigDecimal myIncome = this.memberIncomeService.findMemberIncomeSumByMemberId(getMemberId(), startTime, endTime,
				null);
		model.addAttribute("toplist", toplist);
		model.addAttribute("list", sr.getResult());
		model.addAttribute("myIncome", myIncome);
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		return "/member/partnerElite/champions_list_frag";

	}

	/**
	 * 邀请注册综合查询
	 */
	@SSOToken
	@RequestMapping(value = "/partnerElite/inviteRegister/listData")
	public String listData(Model model, Pager pager, String status, String searchType, String keyword, Date startTime,
			Date endTime, Boolean searchTypeCount) {
		MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
		// 人才渠道管理
		if(endTime!=null){
			endTime=DateTimeUtils.addOrSub(endTime, 1);
		}
		if ("channel".equals(searchType)) {
			if(status==null){
				status="no_register";
			}
			SearchResult<PartnerRecord> sr = this.partnerRecordService.findPartnerRecords(elite.getId(),PartnerRecord_Type.partnerElite.name(), keyword,
					status, startTime, endTime, pager);
			pager.calPageCount((long) sr.getTotalCount());
			model.addAttribute("list", sr.getResult());
			model.addAttribute("pagination", pager);
             
			if (searchTypeCount) {
				Integer allCount = this.partnerRecordService.findPartnerRecordByEnterCount(elite.getId(),PartnerRecord_Type.partnerElite.name(), null);
				Integer registerCount = this.partnerRecordService.findPartnerRecordByEnterCount(elite.getId(),PartnerRecord_Type.partnerElite.name(),
						PartnerRecord_Status.registered.name());
				Integer enterCount = this.partnerRecordService.findPartnerRecordByEnterCount(elite.getId(),PartnerRecord_Type.partnerElite.name(),
						PartnerRecord_Status.audit_pass.name());
				model.addAttribute("allCount", allCount);
				model.addAttribute("registerCount", registerCount);
				model.addAttribute("enterCount", enterCount);
			}
			return "/member/partnerElite/inviteChannelRegister_list_frag";

		} else {
			if(status==null){
				status="noRegister";
			}
			SearchResult<PartnerElite> sr = this.partnerEliteService.findPartnerElitesByKeyWordAndInviteRegister(
					elite.getId(), keyword, status, startTime, endTime, pager);
			pager.calPageCount((long) sr.getTotalCount());
			model.addAttribute("list", sr.getResult());
			model.addAttribute("pagination", pager);
			if (searchTypeCount) {
				Integer allCount = this.partnerEliteService.findPartnerEliteCountByPartnerId(elite.getId(),PartnerElite_Status.noRegister.name());
				Integer registerCount = this.partnerEliteService.findPartnerEliteCountByPartnerId(elite.getId(),
						PartnerRecord_Status.registered.name());
				Integer enterCount = this.partnerEliteService.findPartnerEliteCountByPartnerId(elite.getId(),
						PartnerRecord_Status.audit_pass.name());
				model.addAttribute("allCount", allCount);
				model.addAttribute("registerCount", registerCount);
				model.addAttribute("enterCount", enterCount);
			}
			return "/member/partnerElite/inviteRegister_list_frag";
		}

	}

	/**
	 * 精英管理列表查询
	 */
	@SSOToken
	@RequestMapping(value = "/partnerElite/personActive/listData")
	public String personActiveListData(Model model, Date startTime, Date endTime, String keyword, String status,
			String searchType, Pager pager) {
		if(endTime!=null){
			endTime=DateTimeUtils.addOrSub(endTime, 1);
		}
		if (!(searchType == null || searchType.equals(PartnerElite_Search_Type.all.name())
				|| searchType.equals(PartnerElite_Search_Type.no_register.name()))) {
			SearchResult<MemberPassport> sr = this.memberPassportService.findMemberPassportListByMemberParnterElite(
					getMemberId(), startTime, endTime, keyword, searchType, pager);
			model.addAttribute("list", sr.getResult());
			pager.calPageCount((long) sr.getTotalCount());
			model.addAttribute("pagination", pager);
			return "/member/partnerElite/personActive_list_frag";
		} else {
			if (PartnerElite_Search_Type.no_register.name().equals(searchType)) {
				status = PartnerElite_Status.noRegister.name();
			} else {
				status = null;
			}
			MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
			SearchResult<PartnerElite> sr = this.partnerEliteService.findPartnerElitesByKeyWordAndInviteRegister(
					elite.getId(), keyword, status, startTime, endTime, pager);
			model.addAttribute("list", sr.getResult());
			pager.calPageCount((long) sr.getTotalCount());
			model.addAttribute("pagination", pager);
			return "/member/partnerElite/personActiveAll_list_frag";
		}
	}

	/**
	 * 渠道管理列表查询
	 */
	@SSOToken
	@RequestMapping(value = "/partnerElite/channelManage/listData")
	public String channelManageListData(Model model, Date startTime, Date endTime, String keyword, String status,
			String searchType, Pager pager) {
		if(endTime!=null){
			endTime=DateTimeUtils.addOrSub(endTime, 1);
		}
		MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
		SearchResult<PartnerRecord> sr = this.partnerRecordService.findPartnerRecordsBySearchType(elite.getId(),
				getMemberId(), MemberIdentity_Type.partnerElite.name(), keyword, searchType, startTime, endTime, pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		return "/member/partnerElite/channelManage_list_frag";
	}

	/**
	 * 人才推荐方_收益中心 列表
	 */
	@SSOToken
	@RequestMapping(value = "/partnerElite/revenueDetail/{type}/listData")
	public String revenueListData(@PathVariable String type, Model model, Date startTime, Date endTime, Pager pager) {
		MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
		SearchResult<?> sr;
		if (Income_Type.partner_own.name().equals(type)) {
			sr = this.partnerEliteService.findPartnerEliteInComeList(getMemberId(),elite.getId(), getMemberId(), type, null,
					startTime, endTime, pager);
		} 
		else {
			sr = this.partnerEliteService.findPartnerEliteDirectInComeList(elite.getId(), getMemberId(), null, type,
					startTime, endTime, pager);
		}
		BigDecimal myIncome = this.memberIncomeService.findMemberIncomeSumByMemberId(getMemberId(), startTime, endTime,
				Income_Type.valueOf(Income_Type.class, type).name());
		model.addAttribute("myIncome", myIncome);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		if (Income_Type.partner_indirect.name().equals(type)) {
			type = "partner_direct";
		}
		return "/member/partnerElite/revenue_" + type + "_list_frag";
	}

	/**
	 * 人才推荐方_账户安全_基本信息修改
	 */
	@SSOToken
	@MemberHandleLog(description = "人才推荐方_账户安全_基本信息修改", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/partnerElite/basic/update")
	public ResponseResult<String> updateBasicMessage(MemberBasic basic, String nickName, MemberPartnerElite elite,
			MemberCredit credit) {
		this.memberPartnerEliteService.updateMemberPartnerEliteBasicInfo(getMemberId(), nickName, basic, elite, credit);
		return new ResponseResult<>();
	}

	/**
	 * 人才推荐方 设置新密码
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/partnerElite/reset/pass", method = RequestMethod.POST)
	public ResponseResult<String> resetPass(String oldPass, String newPass, String phone, String companySmsPhone,
			HttpServletRequest request) {
		this.memberPassportService.updateMemberPassword(getMemberId(), oldPass, newPass);
		return new ResponseResult<>();
	}

	/**
	 * 人才推荐方_上传简历
	 */
	@SSOToken
	@MemberHandleLog(description = "人才推荐方_上传简历", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/partnerElite/save/resume")
	public ResponseResult<String> savePartnerEliteResume(Long resumeId, String account) {
		MemberPassport member = this.memberPassportService.findMemberPassportByAccount(account);
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
		return new ResponseResult<>();
	}

	/**
	 * 直接渠道，间接渠道 推荐的精英列表查询
	 */
	@RequestMapping(value = "/partnerElite/revenue/elite/listData", method = RequestMethod.POST)
	public String revenueEliteList(Model model, Long partnerId,Long memberId, String incomeType, Date startTime, Date endTime,
			Pager pager) {
		SearchResult<PartnerElite> sr = this.partnerEliteService.findPartnerEliteInComeList(getMemberId(),partnerId, memberId,
				incomeType, null, startTime, endTime, pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		return "/member/partnerElite/revenue_partner_elite_list_frag";
	}

	/**
	 * 提现记录查询
	 */
	@RequestMapping(value = "/withdraw/listData")
	public String withdrawListData(Model model, Pager pager) {
		SearchResult<MemberWithdraw> sr = this.memberWithdrawService.findMemberWithdrawsByMemberId(null, getMemberId(),
				null, pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		return "/member/partnerElite/revenue_withdraw_list_frag";
	}

	/**
	 * 收益中心 收益记录查询
	 */
	@RequestMapping(value = "/partnerElite/income/listData", method = RequestMethod.POST)
	public String revenueEarningsListData(Model model, String keyword, String searchType, Date startTime, Date endTime,
			Pager pager) {
		SearchResult<MemberIncome> sr = this.memberIncomeService.findMemberIncomesByPartnerElite(keyword, searchType, getMemberId(), startTime, endTime, pager);
		PartnerEliteIncomeRule rule=(PartnerEliteIncomeRule)this.dictService.findDictPartnerRule(Dict_Type.partnerElite_option.name());
		model.addAttribute("incomeRule", rule);
		model.addAttribute("incomelist", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		return "/member/partnerElite/revenue_income_list_frag";
	}

	/**
	 * 是否具有申请资格
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/valid")
	public ResponseResult<String> valid() {
		ResponseResult<String> rsp = new ResponseResult<>();
		MemberPassport member = memberPassportService.findMemberDetailInfoById(getMemberId());
		// 判断是否达到审核要求
		if (member.getBasic().getId() == null || member.getCredit().getId() == null || !member.getCredit().getIsCard()) {
			rsp.setCode(ErrorCodeEnum.FAILURE.code);
			rsp.setMsg("您的资料度完善度程度还未达到审核条件,先完善哦！");
			return rsp;
		}
		return rsp;
	}

	/**
	 * 申请审核
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/partnerElite/audit", method = RequestMethod.POST)
	public ResponseResult<String> audit() {
		ResponseResult<String> rsp = new ResponseResult<>();
		try {
			MemberPartnerElite elite = this.memberPartnerEliteService.findMemberPartnerEliteByMemberId(getMemberId());
			elite.setStatus(Member_Status.aduitIn);
			memberPartnerEliteService.updateMemberPartnerEliteInfo(getMemberId(), elite);
		} catch (Exception e) {
			e.printStackTrace();
			rsp.setCode(ErrorCodeEnum.FAILURE.code);
		}
		return rsp;
	}

}

package com.ledao.elite.site.controller.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ledao.elite.core.domain.member.MemberIdentity;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberIncome;
import com.ledao.elite.core.domain.member.MemberIncome.Income_Type;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPartnerCompany;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.partner.PartnerProject;
import com.ledao.elite.core.domain.partner.PartnerProject.PartnerProject_Status;
import com.ledao.elite.core.domain.partner.PartnerProjectRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Status;
import com.ledao.elite.core.domain.partner.PartnerRecord.PartnerRecord_Type;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.domain.platform.PlatformInviteCode.InviteCode_Type;
import com.ledao.elite.core.domain.project.Project;
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
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Cooperate_Type;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.PartnerCompanyIncomeRule;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.framework.thread.settle.PartnerEaringsThread2CompanyUtils;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.partner.PartnerProjectRecordService;
import com.ledao.elite.core.service.partner.PartnerProjectService;
import com.ledao.elite.core.service.partner.PartnerRecordService;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.core.service.project.ProjectService;
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
 * @param <K>
 */
@Controller("memberPartnerCompanyController")
@RequestMapping("/partner")
public class MemberPartnerCompanyController<K> extends BaseController {

	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private SmsRecordService smsRecordService;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private MemberPartnerCompanyService memberPartnerCompanyService;
	@Resource
	private PartnerProjectService partnerProjectService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private DictService dictService;
	@Resource
	private PlatformInviteCodeService platformInviteCodeService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private PartnerProjectRecordService partnerProjectRecordService;
	@Resource
	private MemberIncomeService memberIncomeService;
	@Resource
	private PartnerRecordService partnerRecordService;
	@Resource
	private ProjectService projectService;

	/**
	 * 项目推荐方账号注册
	 */
	@MemberHandleLog(description = "项目推荐方注册", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/partnerCompany/register", method = RequestMethod.POST)
	public ResponseResult<String> create(MemberPassport member, String verifycode, HttpServletRequest request) {
		boolean flag = this.smsRecordService.findSmsRecordIsExists(member.getAccount(), Sms_Type.register.name(),
				verifycode, null);
		if (!flag) {
			String password = member.getPassword();
			member.setCurrentType(MemberIdentity.MemberIdentity_Type.partnerCompany.name());
			// 判断邀请码，推荐手机号
			if (StringUtils.isNotEmpty(member.getInviteCode())) {
				if (member.getInviteCode().length() == 11) {
					member.setInvitePhone(member.getInviteCode());
					member.setInviteCode(null);
				}
			}
			MemberPassport mepp = this.memberPassportService.createMemberPassport(member);
			String url = localCoreConfig.getDomainServer();
			StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.COMPANY_PARTNER_REGISTER_SUCCESS);
			String replace = "<a href='" + url + "/member/index?rp=accountSecurity' target='_blank'>完善资料</a>";
			String content = String.format(message.getValue(), replace);
			eventPublishService.publishMessageEvent(mepp.getId(), null, null, message.getKey(), content, false,
					MemberMessage_Type.system);
			login(member.getAccount(), password, request);
			return new ResponseResult<>();
		}
		return new ResponseResult<>(GlobalDefinedConstant.FAILURE, "验证码错误");
	}

	/**
	 * 项目推荐方资料完善 页面
	 */
	@RequestMapping(value = "/partnerCompany/basic/register/view")
	public String partnerCompanyBasicView(Model model) {
		model.addAttribute("memberId", getMemberId());
		MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(getMemberId());
		model.addAttribute("basic", basic);
		MemberPartnerCompany company = this.memberPartnerCompanyService
				.findMemberPartnerCompanyByMemberId(getMemberId());
		model.addAttribute("company", company);
		return "/member/partnerCompany/registerBasic";
	}

	/**
	 * 项目推荐方注册第二步 资料完善
	 */
	@MemberHandleLog(description = "项目推荐方完善资料", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/partnerCompany/info/update", method = RequestMethod.POST)
	public ResponseResult<String> updateMemberInfo(String sex, String areaName, String companyName, String email) {
		this.memberPartnerCompanyService.updateMemberPartnerCompanyInfo(getMemberId(), sex, areaName, companyName,
				email);
		return new ResponseResult<>();
	}

	/**
	 * 项目推荐方注册 征信信息
	 */
	@RequestMapping(value = "/partnerCompany/credit/register/view")
	public String partnerCompanyCreditView(Model model) {
		model.addAttribute("memberId", getMemberId());
		return "/member/partnerCompany/registerCredit";
	}

	/**
	 * 项目推荐方注册第三步 征信信息
	 */
	@MemberHandleLog(description = "项目渠道方征信信息注册", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/partnerCompany/credit/save", method = RequestMethod.POST)
	public ResponseResult<String> updateCreditInfo(MemberCredit credit) {
		credit.setMemberId(getMemberId());
		MemberCredit obj = this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		// 检查身份证姓名是否匹配
		if (obj == null || !obj.getIsCard()) {
			boolean cflag = IdCardCache.valid(getMemberId());
			if (!cflag) {
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE, "频繁认证，请明天再试");
			}
			boolean flag = this.memberCreditService.findValidateIdCard(credit.getRealName(), credit.getIdCard());
			if (!flag) {
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE, "身份证号码和姓名不匹配");
			}
		}
		try {
			if (obj == null) {
				this.memberCreditService.createMemberCredit(credit);
			} else {
				memberCreditService.updateMemberCreditByPartnerCompany(getMemberId(), credit);
			}
		} catch (EliteServiceException e) {
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE, e.getMsg());
		}

		return new ResponseResult<>();
	}

	/**
	 * 新增项目备案
	 */
	@MemberHandleLog(description = "新增项目备案", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/partnerCompany/project/save", method = RequestMethod.POST)
	public ResponseResult<String> savePartnerProject(String contactpartner, PartnerProject project, Model model) {
		MemberPartnerCompany company = this.memberPartnerCompanyService
				.findMemberPartnerCompanyByMemberId(getMemberId());
		project.setPartnerId(company.getId());
		project.setStatus(PartnerProject_Status.wait_audit);
		partnerProjectService.createPartnerProject(project);
		return new ResponseResult<>();
	}

	/**
	 * 新增项目渠道方备案
	 */
	@SSOToken
	@MemberHandleLog(description = "新增项目渠道方备案", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/partnerCompany/saveMemberPartnerCompany", method = RequestMethod.POST)
	public ResponseResult<String> createMemberPartnerElite(PartnerRecord record) {
		MemberPartnerCompany company = this.memberPartnerCompanyService
				.findMemberPartnerCompanyByMemberId(getMemberId());
		record.setParentId(company.getId());
		record.setPartnerType(PartnerRecord_Type.partnerCompany);
		record.setStatus(PartnerRecord_Status.no_register);
		this.partnerRecordService.createPartnerRecord(record);
		return new ResponseResult<>();
	}

	@ResponseBody
	@RequestMapping(value = "/memberPartnerCompany/project/save", method = RequestMethod.POST)
	public ResponseResult<String> saveMemberPartnerProject(Model model, MemberPartnerCompany company) {
		company.setParentId(getMemberId());
		this.memberPartnerCompanyService.createMemberPartnerCompany(company);
		return new ResponseResult<>();
	}

	/**
	 * 项目备案信息
	 */
	@RequestMapping(value = "/partnerCompany/{id}/view", method = RequestMethod.POST)
	public String view(@PathVariable Long id, Model model) {
		PartnerProject project = partnerProjectService.findPartnerProjectById(id);
		model.addAttribute("project", project);
		return "/";
	}

	/**
	 * 项目推荐方_主页 (侧边栏选择）
	 */
	@RequestMapping(value = "/partnerCompany/{type}", method = RequestMethod.POST)
	public String projectManageView(Model model, @PathVariable String type) {
		MemberPassport port = this.memberPassportService.findMemberDetailInfoById(getMemberId());
		MemberPartnerCompany company = this.memberPartnerCompanyService
				.findMemberPartnerCompanyByMemberId(getMemberId());

		// 冠军榜单
		if (type.equals("champions")) {
			BigDecimal myIncome = this.memberIncomeService.findMemberIncomeSumByMemberId(getMemberId(), null, null,
					null);
            //收益规则
			PartnerCompanyIncomeRule rule=(PartnerCompanyIncomeRule)this.dictService.findDictPartnerRule(Dict_Type.partnerCompany_option.name());
			model.addAttribute("rule", rule);
			model.addAttribute("myIncome", myIncome);
		}
		// 邀请注册
		else if (type.equals("inviteRegister")) {
			PlatformInviteCode code = platformInviteCodeService.findPlatformInviteCodeByUserId(getMemberId(),
					InviteCode_Type.channel_company);
			code.setHref(localCoreConfig.getDomainServer() + "/register?ts=company");
			model.addAttribute("code", code);
			model.addAttribute("typelist", Cooperate_Type.values());
		}
		// 收益中心
		else if (type.equals("revenue")) {
			MemberAccount account = this.memberAccountService.findMemberAccountByMemberId(getMemberId());
			MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
			model.addAttribute("credit", credit);
			model.addAttribute("account", account);
		}
		// 收益中心
		else if (type.equals("accountSecurity")) {
			model.addAttribute("typelist", Cooperate_Type.values());
		}
		model.addAttribute("it", port);
		model.addAttribute("company", company);

		// 待整理
		List<Dict> stageList = this.dictService.findRootDictListByDictType(Dict_Type.project_stage.name());
		model.addAttribute("stagelist", stageList);
		List<Dict> industryList = this.dictService.findRootDictListByDictType(Dict_Type.choice_industry.name());
		model.addAttribute("industrylist", industryList);
		return "/member/partnerCompany/" + type;
	}

	/**
	 * 项目推荐方数据列表
	 */
	@RequestMapping(value = "/partnerCompany/{type}/listData", method = RequestMethod.POST)
	public String listData(@PathVariable String type, Model model, Pager pager, String keyword, String status,
			String searchType, Date startTime, Date endTime, boolean searchTypeCount) {
		MemberPartnerCompany company = this.memberPartnerCompanyService
				.findMemberPartnerCompanyByMemberId(getMemberId());
		if(endTime!=null){
			endTime=DateTimeUtils.addOrSub(endTime, 1);
		}
		// 项目管理
		if (type.equals("revenue") || type.equals("projectManage")) {

			SearchResult<PartnerProjectRecord> sr = this.partnerProjectRecordService
					.findPartnerProjectRecordByPartnerId(company.getId(), keyword,null,null, pager);
					model.addAttribute("list", sr.getResult());
			pager.calPageCount((long) sr.getTotalCount());
		}
		// 邀请备案
		else if (type.equals("inviteRegister")) {
			
			//备案渠道
			if ("channel".equals(searchType)) {
				if (status == null) {
					status = "no_register";
				}
				SearchResult<PartnerRecord> sr = this.partnerRecordService.findPartnerRecords(company.getId(),
						PartnerRecord_Type.partnerCompany.name(), keyword, status, startTime, endTime, pager);
				pager.calPageCount((long) sr.getTotalCount());
				if (searchTypeCount) {
					Integer allCount = this.partnerRecordService.findPartnerRecordByEnterCount(company.getId(),
							PartnerRecord_Type.partnerCompany.name(), null);
					Integer registerCount = this.partnerRecordService.findPartnerRecordByEnterCount(company.getId(),
							PartnerRecord_Type.partnerCompany.name(), PartnerRecord_Status.registered.name());
					Integer enterCount = this.partnerRecordService.findPartnerRecordByEnterCount(company.getId(),
							PartnerRecord_Type.partnerCompany.name(), PartnerRecord_Status.audit_pass.name());
					model.addAttribute("allCount", allCount);
					model.addAttribute("registerCount", registerCount);
					model.addAttribute("enterCount", enterCount);
				}
				model.addAttribute("list", sr.getResult());
				model.addAttribute("pagination", pager);
				return "/member/partnerCompany/inviteChannelRegister_list_frag";
			}

			// 备案项目查询
			else {
				if (searchTypeCount) {
					Integer allCount = this.partnerProjectService.findPartnerProjectCountByPartnerId(company.getId(),
							null);
					Integer enterCount = this.partnerProjectRecordService.findPartnerProjectRecordCountByPartnerId(company.getId(),
							null);
					model.addAttribute("allCount", allCount);
					model.addAttribute("enterCount", enterCount);
				}

				String entered = "false";
				// 入驻项目查询
				if ("registered".equals(status)) {
                    SearchResult<PartnerProjectRecord>sr=this.partnerProjectRecordService.findPartnerProjectRecordByPartnerId(company.getId(), keyword,startTime,endTime, pager);
                    model.addAttribute("list", sr.getResult());
					pager.calPageCount((long) sr.getTotalCount());
					model.addAttribute("pagination", pager);
					return "/member/partnerCompany/inviteRegister_enter_list_frag";
				}
				// 备案未入住项目查询
				else {
					SearchResult<PartnerProject> sr = partnerProjectService.findPartnerProjectsByStatusAndKeyWorld(
							company.getId(), null, keyword, entered, null, startTime,endTime,pager);
					model.addAttribute("list", sr.getResult());
					pager.calPageCount((long) sr.getTotalCount());
				}

			}
		}
		// 邀请备案
		else if (type.equals("channelManage")) {
			SearchResult<PartnerRecord> sr = this.partnerRecordService.findPartnerRecordsBySearchType(company.getId(),
					getMemberId(), MemberIdentity_Type.partnerCompany.name(), keyword, searchType, startTime, endTime,
					pager);
			model.addAttribute("list", sr.getResult());
			pager.calPageCount((long) sr.getTotalCount());
			model.addAttribute("pagination", pager);
		}
		// 冠军列表
		else if (type.equals("champions")) {
			Long parentId;
			// 我所在团队
			if ("myParentTeam".equals(searchType)) {
				parentId = company.getParentId();
				if (parentId != null) {
					MemberPartnerCompany partnerCompany = this.memberPartnerCompanyService
							.findMemberPartnerCompanybyId(parentId);
					model.addAttribute("partnerCompany", partnerCompany);
					Integer ranking = this.memberPartnerCompanyService.findMemberPartnerCompanyIndex(parentId,
							getMemberId(), startTime, endTime, Member_Status.normal.name());
					model.addAttribute("ranking", ranking);
				}
			}
			// 我的团队
			else {
				parentId = company.getId();

			}
			SearchResult<MemberPartnerCompany> sr = this.memberPartnerCompanyService
					.findMemberPartnerEliteCompanyByParentId(parentId, startTime, endTime, Member_Status.normal.name(),
							pager);

			// 冠亚季军
			List<MemberPartnerCompany> toplist = this.memberPartnerCompanyService
					.findMemberPartnerCompanyListTopThree(parentId, startTime, Member_Status.normal.name(), endTime);
			// 会员收益
			BigDecimal myIncome = this.memberIncomeService.findMemberIncomeSumByMemberId(getMemberId(), startTime,
					endTime, null);
			model.addAttribute("list", sr.getResult());
			pager.calPageCount((long) sr.getTotalCount());
			model.addAttribute("toplist", toplist);
			model.addAttribute("list", sr.getResult());
			model.addAttribute("myIncome", myIncome);
		}
		model.addAttribute("pagination", pager);
		return "/member/partnerCompany/" + type + "_list_frag";
	}

	/**
	 * 项目推荐方_账户安全基本信息修改
	 */
	@MemberHandleLog(description = "项目推荐方基本信息更改", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/partnerCompany/basic/update", method = RequestMethod.POST)
	public ResponseResult<String> updateBasicMessage(MemberPassport member, MemberBasic basic,
			MemberPartnerCompany company, MemberCredit credit) {
		this.memberPartnerCompanyService.updateMemberPartnerCompanyBasic(getMemberId(), member, basic, credit, company);
		return new ResponseResult<>();
	}

	/**
	 * 项目推荐方 设置新密码
	 */
	@ResponseBody
	@MemberHandleLog(description = "项目推荐方修改密码", type = LogsEnum.update)
	@RequestMapping(value = "/partnerCompany/reset/pass", method = RequestMethod.POST)
	public ResponseResult<String> resetPass(String oldPass, String newPass, String phone, String companySmsPhone) {
		this.memberPassportService.updateMemberPassword(getMemberId(), oldPass, newPass);
		return new ResponseResult<>();
	}

	/**
	 * 协议书查看
	 */
	@RequestMapping(value = "/{type}/viewbook")
	public String viewbook(@PathVariable String type) {
		return "/member/partner/" + type;
	}

	/**
	 * 收益详情查询
	 */
	@RequestMapping(value = "/partnerCompany/revenueDetail/listData", method = RequestMethod.POST)
	public String revenueDetailList(Model model, Date startTime, Date endTime) {
		return "/member/partnerCompany/revenuedetail";
	}

	/**
	 * 收益中心 项目直接收益查询
	 */
	@RequestMapping(value = "/partnerCompany/revenueDetail/{type}/listData", method = RequestMethod.POST)
	public String revenueDetailTypeList(@PathVariable String type, Model model, Date startTime, Date endTime,
			Pager pager) {
		SearchResult<?> sr;
		MemberPartnerCompany company = this.memberPartnerCompanyService
				.findMemberPartnerCompanyByMemberId(getMemberId());
		PartnerCompanyIncomeRule partnerIncomeRule=(PartnerCompanyIncomeRule)this.dictService.findDictPartnerRule(Dict_Type.partnerCompany_option.name());
		Map<String,Object> directMap=memberPartnerCompanyService.findPartnerCompanyDirectCount(company.getId(), null, null);
		Map<String,Object> indirectMap=memberPartnerCompanyService.findPartnerCompanyInDirectCount(company.getId(), null, null);
		BigDecimal proportion;
		if (Income_Type.partner_own.name().equals(type)) {
			sr = this.memberIncomeService.findMemberIncomesByPartnerCompanyOwn(getMemberId(),
					Income_Type.partner_own.name(), startTime, endTime, pager);
			proportion=	PartnerEaringsThread2CompanyUtils.compare("本人",company.getCalculateOrderCount(), company.getCalculateTotalAmount(),partnerIncomeRule.getPartnerOwnRule().getFirstLadder(), partnerIncomeRule.getPartnerOwnRule().getSecondLadder(), partnerIncomeRule.getPartnerOwnRule().getThreeLadder());//一级比例
		} else {
			sr = this.memberPartnerCompanyService.findMemberPartnerComapnyIncomeByParent(getMemberId(),
					Income_Type.valueOf(Income_Type.class, type).name(), startTime, endTime, pager);
			if(Income_Type.partner_direct.name().equals(type)){
				proportion=PartnerEaringsThread2CompanyUtils.compare("直接",(Integer)directMap.get("orderCount"),(BigDecimal)directMap.get("orderAmount"),partnerIncomeRule.getPartnerDirectRule().getFirstLadder(), partnerIncomeRule.getPartnerDirectRule().getSecondLadder(), partnerIncomeRule.getPartnerDirectRule().getThreeLadder());//二级比例
			}
			else{
				proportion=	PartnerEaringsThread2CompanyUtils.compare("间接",(Integer)indirectMap.get("orderCount"),(BigDecimal)indirectMap.get("orderAmount"), partnerIncomeRule.getPartnerIndirectRule().getFirstLadder(), partnerIncomeRule.getPartnerIndirectRule().getSecondLadder(), partnerIncomeRule.getPartnerIndirectRule().getThreeLadder());//三级比例
			}
		}
		BigDecimal myIncome = this.memberIncomeService.findMemberIncomeSumByMemberId(getMemberId(), startTime, endTime,
				Income_Type.valueOf(Income_Type.class, type).name());
		

		model.addAttribute("myIncome", myIncome);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
    	model.addAttribute("proportion", proportion);
		return "/member/partnerCompany/revenue_" + type + "_list_frag";
	}

	/**
	 * 饼状图数据查询
	 */
	@ResponseBody
	@RequestMapping(value = "/partnerCompany/searchPieChart")
	public ResponseResult<String> searchPieChart(Date startTime, Date endTime, Pager pager) {
		MemberPassport member = this.memberPassportService.findMemberPassportById(getMemberId());
		BigDecimal ownIncome = this.memberIncomeService.findMemberIncomeSumByMemberId(getMemberId(), startTime, endTime,
				Income_Type.partner_own.name());
		BigDecimal directIncome = this.memberIncomeService.findMemberIncomeSumByMemberId(getMemberId(), startTime,
				endTime, Income_Type.partner_direct.name());
		BigDecimal indirectIncome = this.memberIncomeService.findMemberIncomeSumByMemberId(getMemberId(), startTime,
				endTime, Income_Type.partner_indirect.name());
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		String returnStr;
		if (member.getCurrentType().equals(PartnerRecord_Type.partnerCompany.name())) {
			map.put("项目收益", ownIncome);
			returnStr="{\"项目收益\":\""+ownIncome+"\"";
		} else {
			map.put("人才渠道", ownIncome);
			returnStr="{\"人才收益\":\""+ownIncome+"\"";
		}
		map.put("直接渠道", directIncome);
		returnStr +=",\"直接渠道\":\""+directIncome+"\"";
		map.put("间接渠道", indirectIncome);
		returnStr +=",\"间接渠道\":\""+indirectIncome+"\"}";
		ResponseResult<String> rsp = new ResponseResult<>();
		rsp.setObject(returnStr);
		return rsp;
	}

	/**
	 * 收益中心，间接渠道 直接渠道 推荐的
	 */
	@RequestMapping(value = "/partnerCompany/revenue/project/listData", method = RequestMethod.POST)
	public String revenueProjectList(Model model, Long partnerId, Date startTime, Date endTime,String incomeType, Pager pager) {
		SearchResult<Project> sr = this.projectService.findProjectListByMemberPartnerId(partnerId,getMemberId(),incomeType,pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		return "/member/partnerCompany/revenue_project_list_frag";
	}

	/**
	 * 收益中心
	 */
	@RequestMapping(value = "/partnerCompany/income/listData", method = RequestMethod.POST)
	public String revenueIncomesList(Model model, String keyword, String searchType, Date startTime, Date endTime,
			Pager pager) {
		SearchResult<MemberIncome> sr = this.memberIncomeService.findMemberIncomesByPartnerCompanyType(getMemberId(),
				 keyword,searchType, startTime, endTime, pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		return "/member/partnerCompany/revenue_income_list_frag";
	}

	/**
	 * 申请审核
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/partnerCompany/audit", method = RequestMethod.POST)
	public ResponseResult<String> audit() {
		ResponseResult<String> rsp = new ResponseResult<>();
		try {
			MemberPartnerCompany company = this.memberPartnerCompanyService
					.findMemberPartnerCompanyByMemberId(getMemberId());
			company.setStatus(Member_Status.aduitIn);
			memberPartnerCompanyService.updateMemberPartnerCompanyNoFixed(company);
		} catch (Exception e) {
			e.printStackTrace();
			rsp.setCode(ErrorCodeEnum.FAILURE.code);
		}
		return rsp;
	}

}

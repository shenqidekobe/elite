package com.ledao.elite.site.controller.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Type;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.Project.Project_Status;
import com.ledao.elite.core.domain.project.ProjectDefine;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Status;
import com.ledao.elite.core.domain.project.ProjectDefine.ProjectDefine_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.project.ProjectLog.ProjectLog_Type;
import com.ledao.elite.core.framework.annotation.ESChain;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.MessageBoxCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Channel;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Status;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.ProjectDetailInfo;
import com.ledao.elite.core.framework.dto.StringKeyValue;
import com.ledao.elite.core.framework.plugin.pay.PaymentService;
import com.ledao.elite.core.framework.plugin.pay.alipay.AliPaymentServiceImpl;
import com.ledao.elite.core.framework.plugin.pay.alipay.builder.AlipayTradePayContentBuilder;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberPartnerCompanyService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.partner.PartnerProjectService;
import com.ledao.elite.core.service.platform.PlatformInOrderService;
import com.ledao.elite.core.service.project.ProjectDefineService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectMaterialService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.utils.WebUtils;
import com.ledao.elite.site.controller.BaseController;
import com.ledao.elite.site.dto.ResponseResult;
import com.pingplusplus.model.Charge;

/**
 * 项目方的项目信息接口
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("projectInfoController")
@RequestMapping("/project")
public class ProjectCompanyController extends BaseController {

	@Resource
	private ProjectService projectService;
	@Resource(name="aliPaymentService")
	private PaymentService paymentService;
	@Resource(name="pingPlusPlusService")
	private PaymentService pingPlusPlusService;
	@Resource
	private PlatformInOrderService platformInOrderService;
	@Resource
	private ProjectDefineService projectDefineService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private ProjectMaterialService projectMaterialService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberCompanyService memberCompanyService;
	@Resource
	private PartnerProjectService partnerProjectService;
	@Resource
	private MemberPartnerCompanyService memberPartnerCompanyService;

	/**
	 * 进入发布项目页
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public String publish(Model model) {
		examineIsUnreadMessage(model, true);
		return "/project/publish";
	}

	/**
	 * 项目方发布项目
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@MemberHandleLog(description = "项目方发布项目", type = LogsEnum.create)
	@ResponseBody
	@RequestMapping(value = "/publish/save", method = RequestMethod.POST)
	public ResponseResult<String> publishProject(Project project) {
		project.setCompanyId(getMemberId());
		// project.setIntentionAmount(appConfig.getProjectIntentionAmount());
		if (project.getStartTime() == null) {
			project.setStartTime(new Date());
		}
		Project obj = this.projectService.createProject(project);
		// publish log event
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.company, obj.getId(), null, null, getMemberId(),
				"立项阶段", "发布项目", null);
		// publish msg event
		String url = localCoreConfig.getDomainServer();
        //test
		
		StringKeyValue message = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_PUBLISH_SUCCESS);
		String replace = "<a href='" + url + "/project/" + obj.getId() + "/index' target='_blank'>查看详情</a>";
		String content = String.format(message.getValue(), project.getName(), replace);
		eventPublishService.publishMessageEvent(getMemberId(), obj.getId(), null, message.getKey(), content, false,
				MemberMessage_Type.project);
		// 通知bm
		StringKeyValue messagetobm = MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_PUBLISH_SUCCESS_TOBM);
		String title = String.format(messagetobm.getKey(), project.getName());
		String replacetobm = "<a href='" + url + "/manager/project/bm' target='_blank'>看看</a>";
		String contenttobm = String.format(messagetobm.getValue(), project.getName(), replacetobm);
		eventPublishService.publishSysMessageEvent(obj.getBmId(), null, title, contenttobm, false);
		return new ResponseResult<>();
	}

	/**
	 * 编辑项目
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/company/{projectId}/edit", method = RequestMethod.GET)
	public String editProject(@PathVariable Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		// 非自己的项目不能修改
		if (!project.getCreateId().equals(getMemberId())) {
			return ERROR_VIEW;
		}
		// 除了待审核和审核未通过的状态，其他修改视为非法请求
		if (Project_Status.wait_audit.equals(project.getStatus())
				|| Project_Status.unpass.equals(project.getStatus())) {
			examineIsUnreadMessage(model, true);
			model.addAttribute("obj", project);
			return "/project/edit";
		}
		return ERROR_VIEW;
	}

	/**
	 * 更新项目信息
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@MemberHandleLog(description = "项目方更新项目", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/company/update", method = RequestMethod.POST)
	public ResponseResult<String> updateProject(Project project) {
		project.setCompanyId(getMemberId());
		this.projectService.updateProjectInfo(project);

		// publish event
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.company, project.getId(), null, null, getMemberId(),
				"立项阶段", "更新项目信息", null);
		return new ResponseResult<>();
	}

	/**
	 * 删除项目
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@MemberHandleLog(description = "项目方删除项目", type = LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value = "/company/remove", method = RequestMethod.POST)
	public ResponseResult<String> removeProject(Long projectId) {
		this.projectService.removeProject(projectId);
		return new ResponseResult<>();
	}

	/**
	 * 获取我的所有的项目
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/company/myprojects", method = RequestMethod.POST)
	public List<Project> projectListMap() {
		return this.projectService.findProjectListByCompanyId(getMemberId());
	}

	/**
	 * 我的项目列表
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/company/list", method = RequestMethod.POST)
	public String myProjectList(Model model) {
		model.addAttribute("waitCount",
				projectService.findProjectCountByCompanyId(getMemberId(), Project_Status.wait_audit));
		model.addAttribute("auditInCount",
				projectService.findProjectCountByCompanyId(getMemberId(), Project_Status.audit_in));
		model.addAttribute("auditedCount",
				projectService.findProjectCountByCompanyId(getMemberId(), Project_Status.audited));
		return "/member/company/main/project_list";
	}

	/**
	 * 我的项目列表查询
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/company/listData", method = RequestMethod.POST)
	public String myProjectListData(String status, Pager pager, Model model) {
		SearchResult<Project> sr = this.projectService.findProjectList(getMemberId(), null, status, pager);
		
		model.addAttribute("waitCount",
				projectService.findProjectCountByCompanyId(getMemberId(), Project_Status.wait_audit));
		model.addAttribute("auditInCount",
				projectService.findProjectCountByCompanyId(getMemberId(), Project_Status.audit_in));
		model.addAttribute("auditedCount",
				projectService.findProjectCountByCompanyId(getMemberId(), Project_Status.audited));
		List<Project> list=sr.getResult();
		for(Project p:list){
			Integer munc=projectMaterialService.findProjectMaterialCount(p.getId(), p.getCompanyId());
			p.setMaterialUnCount(munc);
		}
		model.addAttribute("list", list);
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination", pager);
		return "/member/company/main/project_list_frag";
	}

	/**
	 * 项目方的项目信息主页
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/{projectId}/index", method = RequestMethod.GET)
	public String projectIndex(@PathVariable Long projectId, Model model) {
		model.addAttribute("projectId", projectId);
		model.addAttribute("pro",this.projectService.findProjectById(projectId));
		examineIsUnreadMessage(model, true);
		return "/project/record/index";
	}

	/**
	 * 项目详情
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/company/detail", method = RequestMethod.POST)
	public String projectDetail(Long projectId, Model model) {
		ProjectDetailInfo project = this.projectService.findProjectDetailById(projectId);
		List<ProjectDefineStage> stageList = new ArrayList<ProjectDefineStage>();
		ProjectDefine define = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.company, null);
		if (define != null) {
			define = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.company, null);
			if (define != null) {
				stageList = projectDefineStageService.findProjectDefineStageByDefinedId(define.getId());
			}
		}
		model.addAttribute("stageList", stageList);
		model.addAttribute("obj", project);
		examineIsUnreadMessage(model, true);
		return "/project/record/project_view";
	}

	/**
	 * 去提交意向金
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/{projectId}/intention", method = RequestMethod.GET)
	public String intentionAmount(@PathVariable Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		// TODO:验证项目阶段费用是否已经提交//已支付成功跳转...
		if (Pay_Status.success.equals(project.getIntentionStatus())) {
			model.addAttribute("payFlag", "yet");
		}
		model.addAttribute("obj", project);
		model.addAttribute("intentionAmount", localCoreConfig.getProjectIntentionAmount());
		examineIsUnreadMessage(model, true);
		return "member/company/global/submit_intention";
	}

	/**
	 * 去托管项目股权
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/{projectId}/stock", method = RequestMethod.GET)
	public String trustStock(@PathVariable Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		if (project == null || !project.getIsStock()) {
			return REDIRECT_COMMAND + "/member/index";
		}
		model.addAttribute("obj", project);
		examineIsUnreadMessage(model, true);
		return "member/company/global/trust_stock";
	}

	/**
	 * 确认托管股权
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@MemberHandleLog(description = "项目股权托管", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/stock/save", method = RequestMethod.POST)
	public ResponseResult<String> saveTrustStock(Long projectId) {
		this.projectService.updateProjectTrustStockAsCompany(projectId);

		// publish event
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.company, projectId, null, null, getMemberId(),
				"立项阶段", "项目方托管股权", null);
		// 推送消息
		eventPublishService.publishMessageEvent(getMemberId(), projectId, null, "立项阶段", "托管股权成功", false,
				MemberMessage_Type.project);
		return new ResponseResult<>();
	}

	/**
	 * 去确认项目立项书
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/{projectId}/define", method = RequestMethod.GET)
	public String affirmDefine(@PathVariable Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		ProjectDefine define = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.company,
				ProjectDefine_Status.wait);
		if (define == null) {
			return REDIRECT_COMMAND + "/member/index";
		}
		examineIsUnreadMessage(model, true);
		model.addAttribute("define", define);
		model.addAttribute("obj", project);
		return "member/company/global/affirm_define";
	}

	/**
	 * 项目方确定项目立项书
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@MemberHandleLog(description = "确定项目立项书", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/define/save", method = RequestMethod.POST)
	public ResponseResult<String> saveAffirmDefine(Long projectId) {
		// 验证项目方是否审核通过，不通过不能立项
		MemberCompany company = this.memberCompanyService.findMemberCompanyByMemberId(getMemberId());
		if (!Member_Status.normal.equals(company.getStatus())) {
			return new ResponseResult<>("您的信息还未审核通过,请联系我们");
		}
		this.projectService.updateProjectAffirmDefinedAsCompany(projectId, true);
		Project project=this.projectService.updateProjectTrustStockAsCompany(projectId);
		// publish log event
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.company, projectId, null, null, getMemberId(),
				"立项阶段", "项目立项", null);
		// publish msg event
		String url = localCoreConfig.getDomainServer();
		StringKeyValue message=MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.PROJECT_AFFIRM_DEFINE_SUCCESS);
		 String replace="<a href='" + url + "/project/" + projectId	+ "/trustcost' target='_blank'>托管</a>";
		 String content = String.format(message.getValue(), project.getName(),replace);
		eventPublishService.publishMessageEvent(getMemberId(), projectId, null, message.getKey(), content, false,
				MemberMessage_Type.project);
		
		//通知bm
		 StringKeyValue messagebm=MessageBoxCache.get(MessageBoxCache.MESSAGE_KEY.BM_RECEIVE_CTO_AFFIRM_TENDER);
		 String title=String.format(messagebm.getKey(), project.getName());
		 String replacebm="<a href='" + url + "/manager/project/bm' target='_blank'>去看看</a>";
		 String contentbm = String.format(message.getValue(), project.getName(),replacebm);
		 eventPublishService.publishSysMessageEvent(project.getBmId(), null, title, contentbm, false);
		return new ResponseResult<>();
	}

	/**
	 * 去托管费用
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/{projectId}/trustcost", method = RequestMethod.GET)
	public String trustCost(@PathVariable Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		// 如项目未立项，不能托管
		List<Project_Status> unList = Arrays.asList(Project_Status.audit_in, Project_Status.audit_in,
				Project_Status.pass_wait, Project_Status.unpass, Project_Status.invalid);
		if (unList.contains(project.getStatus())) {
			return REDIRECT_COMMAND + "/member/index";
		}
		// TODO:验证该阶段是否已托管，或者上个阶段是否已经验收
		ProjectDefineStage stage = project.getTrustStage();
		if (stage == null) {
			return REDIRECT_COMMAND + "/member/index";
		}
		if (Pay_Status.success.equals(stage.getPayStatus()) || stage.isTrusted() || stage.getFinished()) {
			model.addAttribute("payFlag", "yet");
		}
		model.addAttribute("obj", project);
		examineIsUnreadMessage(model, true);
		return "member/company/global/trust_cost";
	}

	/**
	 * 审核失败查看原因
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/{projectId}/cause", method = RequestMethod.GET)
	public String auditCause(@PathVariable Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		model.addAttribute("obj", project);
		examineIsUnreadMessage(model, true);
		return "member/company/global/audit_cause";
	}

	/**
	 * 去验收
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/{projectId}/accept", method = RequestMethod.GET)
	public String acceptStage(@PathVariable Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		model.addAttribute("obj", project);
		examineIsUnreadMessage(model, true);
		return "member/company/global/accept_stage";
	}

	/**
	 * 验收
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@MemberHandleLog(description = "项目阶段验收", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/accept/save", method = RequestMethod.POST)
	public ResponseResult<String> saveAcceptStage(Long projectId, String password, Model model) {
		MemberPassport memberPassport = this.memberPassportService.findMemberPassportByIdValidPassword(getMemberId(),
				password);
		if (memberPassport != null) {
			Project project = this.projectService.findProjectById(projectId);
			ProjectDefineStage stage = project.getForStage();
			if (stage == null) {
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE, "该项目阶段无法进行验收");
			}
			this.projectDefineStageService.updateProjectDefineStageAccept(stage.getId(), true, null);
		}
		return new ResponseResult<>();
	}

	/**
	 * 去评价
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@RequestMapping(value = "/{projectId}/evaluate", method = RequestMethod.GET)
	public String evaluate(@PathVariable Long projectId, Model model) {
		Project project = this.projectService.findProjectById(projectId);
		model.addAttribute("obj", project);
		examineIsUnreadMessage(model, true);
		return "member/company/global/evaluate";
	}

	/**
	 * 更新项目阶段支付方式为线下支付
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@MemberHandleLog(description = "更新项目阶段支付方式", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/company/prostage/payWay", method = RequestMethod.POST)
	public ResponseResult<String> payWay(Long projectId, String invoiceRise,String invoiceAddress,String invoiceName,String invoicePhone) {
		Project project = this.projectService.findProjectById(projectId);
		this.projectDefineStageService.updateProjectDefineStagePayWay(project.getTrustStage().getId(),
				GlobalDefinedConstant.Pay_Way.offline);
		return new ResponseResult<>();
	}
	
	/**
	 * 发起Ping++的支付,异步支付
	 * */
	@ESChain(ref = "validCompany")
	@SSOToken
	@MemberHandleLog(description = "去托管费用", type = LogsEnum.pay)
	@ResponseBody
	@RequestMapping(value="/pingplus/pay",method = RequestMethod.POST)
	public ResponseResult<Charge> pingplusPay(String type, Long projectId,String channel,
			String invoiceRise,String invoiceAddress,String invoiceName,String invoicePhone,HttpServletRequest request){
		if (StringUtils.isEmpty(type) || projectId == null) {
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE, "提交失败");
		}
		Project project = this.projectService.findProjectById(projectId);
		if (project == null) {
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE, "未找到该项目");
		}
		PlatformInOrder_Type orderType = PlatformInOrder_Type.valueOf(PlatformInOrder_Type.class, type);
		BigDecimal orderAmount = null;
		String subject = "";
		switch (orderType) {
		case intention:
			if (Pay_Status.paying.equals(project.getIntentionStatus())
					|| Pay_Status.success.equals(project.getIntentionStatus())) {
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE, "意向金已支付过");
			}
			orderAmount = localCoreConfig.getProjectIntentionAmount();
			subject ="提交意向金到云英汇平台";
			break;
		case prostage:
			ProjectDefineStage stage = project.getTrustStage();
			if (Pay_Status.paying.equals(stage.getPayStatus()) || Pay_Status.success.equals(stage.getPayStatus())
					|| stage.isTrusted()) {
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE,project.getTrustStage().getTitle()+"费用已支付过");
			}
			orderAmount = stage.getFirstAmount() == null ? stage.getAmount() : stage.getFirstAmount();
			subject = "托管" + project.getTrustStage().getTitle() + "费用";
			break;
		}
		String orderNum=createPayOrder(project, projectId, type, channel,invoiceRise,invoiceAddress,invoiceName,invoicePhone);
		
		Map<String,Object> payParams=new HashMap<String, Object>();
		payParams.put("orderNum", orderNum);
		payParams.put("amount", orderAmount.multiply(new BigDecimal(100)));//金额单位为分
		payParams.put("channel",channel);//cp_b2b，upacp_pc
		payParams.put("ip", WebUtils.getClientIp(request));
		payParams.put("subject", subject);
		payParams.put("body", "云英汇平台资金");
		Map<String, String> extra = new HashMap<String, String>();
		if(Pay_Channel.upacp_pc.name().equals(channel)){
			extra.put("result_url",localCoreConfig.getDomainServer()+"/pingpp/chinapay/notify");
			payParams.put("extra", extra);
		}else if(Pay_Channel.wx_pub_qr.name().equals(channel)){
			extra.put("product_id",UUID.randomUUID().toString().replaceAll("-", ""));
			payParams.put("extra", extra);
		}
		Map<String,Object> result=pingPlusPlusService.pay(payParams);
		Charge charge=(Charge) result.get("pingpp");
		if(charge==null){
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"支付请求失败");
		}
		logger.info("支付信息charge："+charge.toString());
		return new ResponseResult<Charge>(charge);
	}

	/**
	 * 支付宝支付,跳转支付
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@MemberHandleLog(description = "去托管费用", type = LogsEnum.pay)
	@RequestMapping(value = "/alipay/pay", method = RequestMethod.POST)
	public String trustProjectAmountPay(String type, Long projectId, String channel,
			String invoiceRise,String invoiceAddress,String invoiceName,String invoicePhone,
			Model model, AlipayTradePayContentBuilder builder) {
		if (StringUtils.isEmpty(type) || projectId == null) {
			return ERROR_VIEW;
		}
		Project project = this.projectService.findProjectById(projectId);
		if (project == null) {
			return ERROR_VIEW;
		}
		PlatformInOrder_Type orderType = PlatformInOrder_Type.valueOf(PlatformInOrder_Type.class, type);
		BigDecimal orderAmount = null;
		String subject = "";
		switch (orderType) {
		case intention:
			if (Pay_Status.paying.equals(project.getIntentionStatus())
					|| Pay_Status.success.equals(project.getIntentionStatus())) {
				return PAY_ING_VIEW;
			}
			orderAmount = localCoreConfig.getProjectIntentionAmount();
			subject = project.getName() + "提交意向金到云英汇平台";
			break;
		case prostage:
			ProjectDefineStage stage = project.getTrustStage();
			if (Pay_Status.paying.equals(stage.getPayStatus()) || Pay_Status.success.equals(stage.getPayStatus())
					|| stage.isTrusted()) {
				return PAY_ING_VIEW;
			}
			orderAmount = stage.getFirstAmount() == null ? stage.getAmount() : stage.getFirstAmount();
			subject = project.getName() + "托管" + project.getTrustStage().getTitle() + "费用到云英汇平台";
			break;
		}
		String orderNum=createPayOrder(project, projectId, type,Pay_Channel.alipay.name(),invoiceRise,invoiceAddress,invoiceName,invoicePhone);
		builder.setOutTradeNo(orderNum);
		builder.setSubject(subject);
		builder.setTotalAmount(orderAmount.toString());
		Map<String, Object> map = new HashMap<>();
		map.put("alipay", builder);
		Map<String, Object> resultMap = this.paymentService.pay(map);
		model.addAttribute("alipayForm", resultMap.get(AliPaymentServiceImpl.PAY_RETURN_KEY));
		return "alipay/pay";
	}
	
	/**
	 * 查询订单是否支付状态
	 */
	@ESChain(ref = "validCompany")
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/query/pay", method = RequestMethod.POST)
	public String queryPayOrder(String orderId) {
		PlatformInOrder order=this.platformInOrderService.findPlatformInOrderByOrderId(orderId);
		if(order==null){
			return "";
		}
		return order.getStatus().name();
	}
	
	/**
	 * 创建订单
	 * */
	private String createPayOrder(Project project,Long projectId,String type,String channel,String invoiceRise,String invoiceAddress,String invoiceName,String invoicePhone){
		PlatformInOrder order = new PlatformInOrder();
		PlatformInOrder_Type orderType = PlatformInOrder_Type.valueOf(PlatformInOrder_Type.class, type);
		BigDecimal orderAmount = null;
		switch (orderType) {
		case intention:
			orderAmount = localCoreConfig.getProjectIntentionAmount();
			break;
		case prostage:
			ProjectDefineStage stage = project.getTrustStage();
			orderAmount = stage.getFirstAmount() == null ? stage.getAmount() : stage.getFirstAmount();
			order.setStageId(stage.getId());
			break;
		}
		Pay_Channel payType = Pay_Channel.valueOf(Pay_Channel.class, channel);
		order.setPayType(payType);
		order.setType(orderType);
		order.setMemberId(getMemberId());
		order.setProjectId(projectId);
		order.setOrderAmount(orderAmount);
		order.setInvoiceRise(invoiceRise);
		order.setInvoiceAddress(invoiceAddress);
		order.setInvoiceName(invoiceName);
		order.setInvoicePhone(invoicePhone);
		String orderNum = this.platformInOrderService.createPlatformInOrder(order);
		return orderNum;
	}

}

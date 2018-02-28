package com.ledao.elite.rest.controller.project;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCompany;
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
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.AttachmentsConstant;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Channel;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Status;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.ProjectDetailInfo;
import com.ledao.elite.core.framework.plugin.pay.PaymentService;
import com.ledao.elite.core.service.member.MemberCompanyService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.platform.PlatformInOrderService;
import com.ledao.elite.core.service.project.ProjectDefineService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.utils.WebUtils;
import com.ledao.elite.rest.controller.BaseController;
import com.ledao.elite.rest.framework.RequestBaseRest;
import com.ledao.elite.rest.framework.ResponseBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.request.PayRequest;
import com.ledao.elite.rest.framework.request.ProjectListRequest;
import com.ledao.elite.rest.framework.response.PayResponse;
import com.ledao.elite.rest.framework.response.project.RProject;
import com.ledao.elite.rest.framework.response.project.RProjectDefine;
import com.ledao.elite.rest.framework.response.project.RProjectDefineStage;
import com.ledao.elite.rest.framework.utils.ColorImageUtils;
import com.pingplusplus.model.Charge;

/**
 * 项目方的项目接口中心
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("projectCompanyController")
@RequestMapping("/project")
public class ProjectCompanyController extends BaseController{

	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private PlatformInOrderService platformInOrderService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberCompanyService memberCompanyService;
	@Resource
	private LocalCoreConfig localCoreConfig;
	@Resource
	private ProjectDefineService projectDefineService;
	@Resource(name="aliPaymentService")
	private PaymentService paymentService;
	@Resource(name="pingPlusPlusService")
	private PaymentService pingPlusPlusService;
	
	/**
	 * 发布项目
	 * */
	@SSOToken
	@MemberHandleLog(description="项目方发布项目",type=LogsEnum.create)
	@ResponseBody
	@RequestMapping(value="/company/publish",method=RequestMethod.POST)
	public ModelAndView publish(){
		String reqJson=parseRequest("name","intro","projectBudget","projectSolution","areaName","productIndustry","productFunction","contactName","contactPhone","referProject","startTime","expectTime","planed","recommendUser");
		Project project=JSON.parseObject(reqJson, Project.class);
		project.setCompanyId(getMemberId());
		project.setCreateId(getMemberId());
		if(project.getStartTime()==null){
			project.setStartTime(new Date());
		}
		Project obj=this.projectService.createProject(project);
		//publish log event
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.company,obj.getId(), null, null, getMemberId(), "立项阶段", "发布项目",null);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	/**
	 * 更新项目信息
	 * */
	@SSOToken
	@MemberHandleLog(description="项目方更新项目",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/company/update",method=RequestMethod.POST)
	public ModelAndView updateProject(){
		ResponseBaseRest rs=new ResponseBaseRest();
		rs.setCode(ErrorCodeEnum.SUCCESS.code);
		String reqJson=parseRequest("name","intro","projectBudget","projectSolution");
		Project project=JSON.parseObject(reqJson, Project.class);
		project.setCompanyId(getMemberId());
		try {
			this.projectService.updateProjectInfo(project);
		} catch (EliteServiceException e) {
			rs.setCode(e.getCode());
			rs.setMsg(e.getMessage());
		}
		//publish event
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.company,project.getId(), null, null, getMemberId(), "立项阶段", "更新项目信息",null);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(rs);
	}
	
	/**
	 * 删除项目
	 * */
	@SSOToken
	@MemberHandleLog(description="项目方删除项目",type=LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value="/company/remove",method=RequestMethod.POST)
	public ModelAndView removeProject(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		this.projectService.removeProject(projectId);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	/**
	 * 获取项目方的所有项目
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/company/findAll",method=RequestMethod.POST)
	public ModelAndView findAllProjects(){
		List<Project> list=this.projectService.findProjectListByCompanyId(getMemberId());
		List<RProject> dataList=new ArrayList<>();
		for(Project mp:list){
			RProject data=new RProject(mp);
			dataList.add(data);
		}
		ResponseResultData<List<RProject>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(dataList);
	}
	
	
	/**
	 * 我的项目列表查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/company/listData",method=RequestMethod.POST)
	public ModelAndView projectListData(){
		String reqJson=parseRequest();
		ProjectListRequest request=JSON.parseObject(reqJson, ProjectListRequest.class);
		String status=request.getStatus();
		Pager pager=new Pager(0, 50);
		SearchResult<Project> sr=this.projectService.findProjectList(getMemberId(), null, status, pager);
		BigDecimal amount=localCoreConfig.getProjectIntentionAmount();
		List<RProject> dataList=new ArrayList<>();
		for(Project mp:sr.getResult()){
			RProject data=new RProject(mp);
			Map<String,String> map = ColorImageUtils.getColorImage(localCoreConfig.getRandomColor());
			String imgPath = AttachmentsConstant.ATTA_SERVER_PATH +"/"+"preview/img/projectPicture/png"+"/"+map.get(mp.getBackgroundColor());
			data.setImgPath(imgPath);
			data.setIntentionAmount(amount);
			dataList.add(data);
		}
		ResponseResultData<List<RProject>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(dataList);
	}
	
	/**
	 * 项目详情
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/company/detail",method=RequestMethod.POST)
	public ModelAndView projectDetail(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		ProjectDetailInfo project=this.projectService.findProjectDetailById(projectId);
		BigDecimal amount=localCoreConfig.getProjectIntentionAmount();
		RProject obj=new RProject(project);
		Map<String,String> map = ColorImageUtils.getColorImage(localCoreConfig.getRandomColor());
		String imgPath = AttachmentsConstant.ATTA_SERVER_PATH +"/"+"preview/img/projectPicture/png"+"/"+map.get(project.getBackgroundColor());
		obj.setImgPath(imgPath);
		obj.setIntentionAmount(amount);
		
		List<String> stageList = new ArrayList<>();
		Integer stageCount=0;
		stageList.add("审核");stageList.add("立项");
		if(project.getProjectDefine()==null){
			stageList.add("阶段开发");
		}else{
			for(ProjectDefineStage stage:project.getProjectDefine().getStages()){
				stageList.add(stage.getTitle());
			}
			stageList.add(Project_Status.quality.label);
			stageList.add(Project_Status.finish.label);
		}
		if(project.getStatus().name().equals(Project_Status.pass_wait.name())||project.getStatus().name().equals(Project_Status.pass_already.name())){
			stageCount=1;
		}else if(project.getStatus().name().equals(Project_Status.stage_course.name())){
			stageCount=1+project.getTrustStageCount();
		}else if(project.getStatus().name().equals(Project_Status.quality.name())){
			stageCount=2+project.getTrustStageCount();
		}else if(project.getStatus().name().equals(Project_Status.finish.name())){
			stageCount=3+project.getTrustStageCount();
		}
		obj.setStageList(stageList);
		obj.setStageCount(stageCount);
		ResponseResultData<RProject> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(obj);
	}
	
	/**
	 * 获取意向金的金额
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/company/intention",method=RequestMethod.POST)
	public ModelAndView intentionAmount(){
		BigDecimal amount=localCoreConfig.getProjectIntentionAmount();
		ResponseResultData<BigDecimal> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(amount);
	}
	
	/**
	 * 确认托管股权
	 * */
	@SSOToken
	@MemberHandleLog(description="项目股权托管",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/company/stock/save",method=RequestMethod.POST)
	public ModelAndView saveTrustStock(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		this.projectService.updateProjectTrustStockAsCompany(projectId);
		
		//publish event
		eventPublishService.publishProjectLogEvent(ProjectLog_Type.company,projectId, null, null, getMemberId(), "立项阶段", "项目方托管股权",null);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	@SSOToken
	@ResponseBody
	@RequestMapping(value = "/define", method = RequestMethod.POST)
	public ModelAndView viewProjectDefine() {
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		ProjectDefine define = this.projectDefineService.findProjectDefine(projectId, ProjectDefine_Type.company,
				ProjectDefine_Status.wait);
		RProjectDefine projectDefine=new RProjectDefine(define);
		ResponseResultData<RProjectDefine> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(projectDefine);
	}
	
	
	/**
	 * 项目方确定项目立项书
	 * */
	@SSOToken
	@MemberHandleLog(description="确定项目立项书",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/company/define/save",method=RequestMethod.POST)
	public ModelAndView saveAffirmDefine(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		//验证项目方是否审核通过，不通过不能立项
		ResponseBaseRest result=new ResponseBaseRest();
		MemberCompany company=this.memberCompanyService.findMemberCompanyByMemberId(getMemberId());
		if(!Member_Status.normal.equals(company.getStatus())){
			result.setCode(ErrorCodeEnum.ACCOUNT_AUDIT_UNPASS.code);
			result.setMsg("您的信息还未审核通过,请联系我们");
		}else{
			this.projectService.updateProjectAffirmDefinedAsCompany(projectId, true);
			//publish log event
			eventPublishService.publishProjectLogEvent(ProjectLog_Type.company,projectId, null, null, getMemberId(), "立项阶段", "项目立项",null);
		}

		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(result);
	}
	
	/**
	 * 更新项目阶段支付方式为线下支付
	 */
	@SSOToken
	@MemberHandleLog(description = "更新项目阶段支付方式", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/prostage/payWay", method = RequestMethod.POST)
	public ModelAndView payWay() {
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		Project project = this.projectService.findProjectById(projectId);
		this.projectDefineStageService.updateProjectDefineStagePayWay(project.getTrustStage().getId(),
				GlobalDefinedConstant.Pay_Way.offline);
		return rsp.responseResult();
	}	
	
	/**
	 * 去托管费用
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/company/trustcost",method=RequestMethod.POST)
	public ModelAndView trustCost(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		Project project=this.projectService.findProjectById(projectId);
		//如项目未立项，不能托管
		List<Project_Status> unList=Arrays.asList(Project_Status.audit_in,Project_Status.audit_in,Project_Status.pass_wait,Project_Status.unpass,Project_Status.invalid);
		RProjectDefineStage data=new RProjectDefineStage();
		ResponseBaseRest result=new ResponseBaseRest();
		ResponseResultData<RProjectDefineStage> rsp=new ResponseResultData<>(); 
		if(unList.contains(project.getStatus())){
			result.setCode(ErrorCodeEnum.NOT_DEFINE.code);
			result.setMsg("项目未立项，不能托管费用");
			return rsp.responseResult(result);
		}
		//TODO:验证该阶段是否已托管，或者上个阶段是否已经验收
		ProjectDefineStage stage=project.getTrustStage();
		if(stage==null){
			result.setCode(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
			result.setMsg("项目阶段为空，不能托管费用");
			return rsp.responseResult(result);
		}
		if(Pay_Status.success.equals(stage.getPayStatus())
				||stage.isTrusted()||stage.getFinished()){
			result.setCode(ErrorCodeEnum.PAY_EXIST.code);
			result.setMsg("阶段已经托管过了");
			return rsp.responseResult(result);
		}
		data=new RProjectDefineStage(stage);
		data.setIntentionAmount(localCoreConfig.getProjectIntentionAmount());
		return rsp.responseResult(result,data);
	}
	
	
	/**
	 * 验收
	 * */
	@SSOToken
	@MemberHandleLog(description="项目阶段验收",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/company/accept/save",method=RequestMethod.POST)
	public ModelAndView saveAcceptStage(){
		String reqJson=parseRequest("dataId","password");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		String password=request.getValue();
		MemberPassport memberPassport=this.memberPassportService.findMemberPassportByIdValidPassword(getMemberId(), password);
		ResponseBaseRest result=new ResponseBaseRest();
		if(memberPassport!=null){
			Project project=this.projectService.findProjectById(projectId);
			ProjectDefineStage stage=project.getForStage();
			if(stage==null){
				result.setCode(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
				result.setMsg("该项目阶段无法进行验收");
			}else{
				this.projectDefineStageService.updateProjectDefineStageAccept(stage.getId(), true, null);
			}
		}
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(result);
	}
	
	
	/**
	 * 更新项目阶段支付方式为线下支付
	 * */
	@SSOToken
	@MemberHandleLog(description="更新项目阶段支付方式",type=LogsEnum.update)
	@ResponseBody
	@RequestMapping(value="/company/payWay/offline",method=RequestMethod.POST)
	public ModelAndView payWayOffline(){
		String reqJson=parseRequest("dataId");
		RequestBaseRest request=JSON.parseObject(reqJson, RequestBaseRest.class);
		Long projectId=request.getDataId();
		Project project=this.projectService.findProjectById(projectId);
		this.projectDefineStageService.updateProjectDefineStagePayWay(project.getTrustStage().getId(),GlobalDefinedConstant.Pay_Way.offline);
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		return rsp.responseResult();
	}
	
	/**
	 * 发起Ping++的支付,异步支付
	 * */
	@SSOToken
	@MemberHandleLog(description = "去托管费用", type = LogsEnum.pay)
	@ResponseBody
	@RequestMapping(value="/pingplus/pay",method = RequestMethod.POST)
	public ModelAndView pingplusPay(HttpServletRequest req){
		String reqJson=parseRequest("projectId","type","channel","invoiceRise","invoiceAddress","invoiceName","invoicePhone");
		PayRequest request=JSON.parseObject(reqJson, PayRequest.class);
		ResponseBaseRest result=new ResponseBaseRest();
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		if (StringUtils.isEmpty(request.getType()) || request.getProjectId() == null) {
			result.setCode(ErrorCodeEnum.APPERROR.code);
			result.setMsg("提交失败");
			return rsp.responseResult(result);
		}
		Project project = this.projectService.findProjectById(request.getProjectId());
		if (project == null) {
			result.setCode(ErrorCodeEnum.APPERROR.code);
			result.setMsg("未找到该项目");
			return rsp.responseResult(result);
		}
		PlatformInOrder_Type orderType = PlatformInOrder_Type.valueOf(PlatformInOrder_Type.class,request.getType());
		BigDecimal orderAmount = null;
		String subject = "";
		switch (orderType) {
		case intention:
			if (Pay_Status.paying.equals(project.getIntentionStatus())
					|| Pay_Status.success.equals(project.getIntentionStatus())) {
				result.setCode(ErrorCodeEnum.APPERROR.code);
				result.setMsg("意向金已支付过");
				return rsp.responseResult(result);
			}
			orderAmount = localCoreConfig.getProjectIntentionAmount();
			subject ="提交意向金到云英汇平台";
			break;
		case prostage:
			ProjectDefineStage stage = project.getTrustStage();
			if (Pay_Status.paying.equals(stage.getPayStatus()) || Pay_Status.success.equals(stage.getPayStatus())
					|| stage.isTrusted()) {
				result.setCode(ErrorCodeEnum.APPERROR.code);
				result.setMsg(project.getTrustStage().getTitle()+"费用已支付过");
				return rsp.responseResult(result);
			}
			orderAmount = stage.getFirstAmount() == null ? stage.getAmount() : stage.getFirstAmount();
			subject = "托管" + project.getTrustStage().getTitle() + "费用";
			break;
		}
		String orderNum=createPayOrder(project, request.getProjectId(), request.getType(), request.getChannel(),request.getInvoiceRise(),request.getInvoiceAddress(),request.getInvoiceName(),request.getInvoicePhone());
		
		Map<String,Object> payParams=new HashMap<String, Object>();
		payParams.put("orderNum", orderNum);
		payParams.put("amount", orderAmount.multiply(new BigDecimal(100)));//金额单位为分
		payParams.put("channel",request.getChannel());//cp_b2b，upacp_pc
		payParams.put("ip", WebUtils.getClientIp(req));
		payParams.put("subject", subject);
		payParams.put("body", "云英汇平台资金");
		Map<String, String> extra = new HashMap<String, String>();
		if(Pay_Channel.upacp_pc.name().equals(request.getChannel())){
			extra.put("result_url",localCoreConfig.getDomainServer()+"/pingpp/chinapay/notify");
			payParams.put("extra", extra);
		}else if(Pay_Channel.wx_pub_qr.name().equals(request.getChannel())){
			extra.put("product_id",UUID.randomUUID().toString().replaceAll("-", ""));
			payParams.put("extra", extra);
		}
		Map<String,Object> results=pingPlusPlusService.pay(payParams);
		Charge charge=(Charge) results.get("pingpp");
		if(charge==null){
			result.setCode(ErrorCodeEnum.APPERROR.code);
			result.setMsg("支付请求失败");
			return rsp.responseResult(result);
		}
		logger.info("支付信息charge："+charge.toString());
		return rsp.responseResult(charge);
	}

	
	
	/**
	 * 项目托管费用开始支付
	 * */
	@SSOToken
	@MemberHandleLog(description="去托管费用",type=LogsEnum.pay)
	@RequestMapping(value="/company/pay",method=RequestMethod.POST)
	public ModelAndView trustProjectAmountPay(){
		String reqJson=parseRequest("projectId","type","invoiceRise","invoiceAddress","invoiceName","invoicePhone");
		PayRequest request=JSON.parseObject(reqJson, PayRequest.class);
		Long projectId=request.getProjectId();
		String type=request.getType(),invoiceRise=request.getInvoiceRise();
		PayResponse data=new PayResponse();
		ResponseBaseRest result=new ResponseBaseRest();
		Project project=this.projectService.findProjectById(projectId);
		ResponseResultData<PayResponse> rsp=new ResponseResultData<>(); 
		
		if(project==null){
			result.setCode(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
			result.setMsg("未找到该项目ID："+projectId);
			return rsp.responseResult(result);
		}
		PlatformInOrder order=new PlatformInOrder();
		PlatformInOrder_Type orderType=PlatformInOrder_Type.valueOf(PlatformInOrder_Type.class,type);
		BigDecimal orderAmount=null;
		String subject = "";
		switch (orderType) {
		case intention:
			if(Pay_Status.paying.equals(project.getIntentionStatus())
					||Pay_Status.success.equals(project.getIntentionStatus())){
				result.setCode(ErrorCodeEnum.PAY_EXIST.code);
				result.setMsg("意向金已经支付过了");
				return rsp.responseResult(result);
			}
			orderAmount=localCoreConfig.getProjectIntentionAmount();
			subject = project.getName() + "提交意向金到云英汇平台";
			break;
        case prostage:
        	ProjectDefineStage stage=project.getTrustStage();
        	if(Pay_Status.paying.equals(stage.getPayStatus())
    				||Pay_Status.success.equals(stage.getPayStatus())
    				||stage.isTrusted()){
        		result.setCode(ErrorCodeEnum.PAY_EXIST.code);
        		result.setMsg(stage.getTitle()+"费用已经支付过了");
        		return rsp.responseResult(result);
    		}
        	orderAmount=stage.getFirstAmount()==null?stage.getAmount():stage.getFirstAmount();
        	subject = project.getName() + "托管" + project.getTrustStage().getTitle() + "费用到云英汇平台";
        	order.setStageId(stage.getId());
			break;
		}
		
		String orderNum=createPayOrder(project, projectId, type,Pay_Channel.alipay.name(),invoiceRise,request.getInvoiceAddress(),request.getInvoiceName(),request.getInvoicePhone());
		data.setOrderNum(orderNum);
		data.setSubject(subject);
		data.setOrderAmount(orderAmount);
		return rsp.responseResult(result,data);
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

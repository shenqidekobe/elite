package com.ledao.elite.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.sys.Area;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.domain.sys.SmsRecord;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.cache.custom.BankCardCache;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.platform.PlatformAuthBankService;
import com.ledao.elite.core.service.sys.AreaService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.core.service.sys.SmsRecordService;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.rest.framework.ResponseBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.request.DictRequest;
import com.ledao.elite.rest.framework.request.SendSmsRequest;
import com.ledao.elite.rest.framework.request.ValidSmsRequest;
/**
 * 公用的接口控制器
 * 
 * @author kobe.liu
 * */
@Controller("commonController")
@RequestMapping("/common")
public class CommonController extends BaseController{
	
	@Resource
	private SmsRecordService smsRecordService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private DictService dictService;
	@Resource
	private AreaService areaService;
	@Resource
	private PlatformAuthBankService platformAuthBankService;
	@Resource
	private MemberCreditService memberCreditService;
	
	/**
	 * 短信发送
	 * 
	 * @param phone
	 * @param type
	 * @return responseBase
	 * */
	@MemberHandleLog(description="发送短信",type=LogsEnum.sendSms)
	@ResponseBody
	@RequestMapping(value="/sms/send",method=RequestMethod.POST)
	public ModelAndView sendSms(){
		String reqJson=parseRequest("account","type","bankCard","phone");
		SendSmsRequest obj=JSON.parseObject(reqJson, SendSmsRequest.class);
		String account=obj.getAccount(),type=obj.getType();
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		ResponseBaseRest data=new ResponseBaseRest();
		String content=null;
		//注册时验证是否已经注册
		if(SmsRecord.Sms_Type.register.name().equals(type)
				||SmsRecord.Sms_Type.update_phone.name().equals(type)){
			boolean flag=this.memberPassportService.findMemberAccountIsExists(account);
			if(flag){
				data.setCode(ErrorCodeEnum.APPERROR.code);
				data.setMsg("该帐号已注册");
				return rsp.responseResult(data);
			}
		}
		//找回密码时，验证手机号是否存在
		if(SmsRecord.Sms_Type.forget.name().equals(type)){
			boolean flag=this.memberPassportService.findMemberAccountIsExists(account);
			if(!flag){
				data.setCode(ErrorCodeEnum.APPERROR.code);
				data.setMsg("该帐号未注册");
				return rsp.responseResult(data);
			}
		}
		//验证银行卡时需要验证卡号和身份证匹配
		if(SmsRecord.Sms_Type.valid_bank.name().equals(type)){
			MemberCredit credit = memberCreditService.findMemberCreditByMemberId(getMemberId());
			boolean flag=BankCardCache.valid(getMemberId());
			if(!flag){
				data.setCode(ErrorCodeEnum.APPERROR.code);
				data.setMsg("银行卡信息验证过于频繁，请明天再试");
				return rsp.responseResult(data);
			}
			if (!this.platformAuthBankService.findValidateBankCard(credit.getRealName(), credit.getIdCard(), obj.getBankCard(), obj.getPhone())){
				data.setCode(ErrorCodeEnum.APPERROR.code);
				data.setMsg("银行卡信息不匹配");
				return rsp.responseResult(data);
			}
		}
		if(SmsRecord.Sms_Type.register.name().equals(type)
				||SmsRecord.Sms_Type.valid_bank.name().equals(type)
				||SmsRecord.Sms_Type.forget.name().equals(type)
				||SmsRecord.Sms_Type.update_phone.name().equals(type)){
			content=CommonUtils.randomString(6);
		}
		SmsRecord sms=new SmsRecord();
		sms.setPhones(account);
		sms.setContent(content);
		sms.setSmsType(type);
		SmsRecord pojo=this.smsRecordService.createSmsRecord(sms);
		if(pojo==null){
			data.setCode(ErrorCodeEnum.APPERROR.code);
			data.setMsg("短信发送失败");
		}else{
			data.setMsg(content);
			logger.info("发送短信到手机号："+account+",验证码："+content);
		}
		return rsp.responseResult(data);
	}

	/**
	 * 手机号验证短信
	 * */
	@ResponseBody
	@RequestMapping(value="/sms/valid",method=RequestMethod.POST)
	public ModelAndView validSms(){
		String reqJson=parseRequest("account","verifyCode","type");
		ValidSmsRequest request=JSON.parseObject(reqJson, ValidSmsRequest.class);
		String account=request.getAccount(),verifyCode=request.getVerifyCode(),type=request.getType();
		
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		ResponseBaseRest data=new ResponseBaseRest();
		try {
			boolean flag=this.smsRecordService.findSmsRecordIsExists(account,type, verifyCode, null);
			if(flag){
				data.setCode(ErrorCodeEnum.VERIFYCODEFAULT.code);
				data.setMsg("手机验证码错误");
			}
		} catch (EliteServiceException e) {
			data.setCode(ErrorCodeEnum.ERROR.code);
			data.setMsg(e.getMsg());
		}
		return rsp.responseResult(data);
	}
	
	/**
	 * 获取数据字典的数据
	 * */
	@ResponseBody
	@RequestMapping(value="/dict/list",method=RequestMethod.POST)
	public ModelAndView getDictList(){
		String reqJson=parseRequest("dictType");
		DictRequest request=JSON.parseObject(reqJson, DictRequest.class);
		String dictType=request.getDictType();
		Long parentId=request.getParentId();
		List<Dict> list=null;
		if(parentId==null){
			list=this.dictService.findRootDictListByDictType(dictType);
		}else{
			list=this.dictService.findDictsByDictType(dictType,parentId);
		}
		ResponseResultData<List<Dict>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(list);
	}

	
	/**
	 * 查询会员的角色
	 * 
	 * @param parentId
	 * */
	@ResponseBody
	@RequestMapping(value="/dict/jobrole",method=RequestMethod.POST)
	public ModelAndView findJobRoleMap(){
		String reqJson=parseRequest();
		DictRequest request=JSON.parseObject(reqJson, DictRequest.class);
		Long parentId=request.getParentId();
		List<Dict> pList=this.dictService.findDictsByDictType(Dict_Type.job_role.name(),parentId);
		for(Dict d:pList){
			List<Dict> cList=this.dictService.findDictsByDictType(Dict_Type.job_role.name(),d.getId());
			d.setChildren(cList);
		}
		ResponseResultData<List<Dict>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(pList);
	}
	
	/**
	 * 地区树形查询
	 * */
	@ResponseBody
	@RequestMapping(value="/area/tree",method=RequestMethod.POST)
	public ModelAndView areaTree(){
		List<Area> list=this.areaService.findRootAreaList();
		
		ResponseResultData<List<Area>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(list);
	}
}

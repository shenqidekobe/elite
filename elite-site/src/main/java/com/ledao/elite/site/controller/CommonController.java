package com.ledao.elite.site.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.platform.PlatformAuthBank;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.domain.sys.SmsRecord;
import com.ledao.elite.core.framework.annotation.ESChain;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.cache.custom.BankCardCache;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.plugin.pay.pingpp.PingPlusPlusComponent;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.platform.PlatformAuthBankService;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.core.service.sys.SmsRecordService;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.core.utils.QRCodeBuilder;
import com.ledao.elite.site.dto.ResponseResult;

/**
 * 公用处理控制器
 * */
@Controller("commonController")
@RequestMapping("/common")
public class CommonController extends BaseController{
	
	@Resource
	private SmsRecordService smsRecordService;
	@Resource
	private DictService dictService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private PingPlusPlusComponent pingPlusPlusComponent;
	@Resource
	private PlatformAuthBankService platformAuthBankService;
	
	/**
	 * 未找到资源
	 * */
	@RequestMapping(value="/unfound",method=RequestMethod.GET)
	public String unfound(Model model){
		return NOT_FOUND_VIEW;
	}
	/**
	 * 支付失败
	 * */
	@RequestMapping(value="/payfail",method=RequestMethod.GET)
	public String payfail(Model model){
		return PAY_FAIL_VIEW;
	}
	
	/**
	 * 错误页
	 * */
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public String error(Model model){
		getMemberId();
		return ERROR_VIEW;
	}
	
	/**
	 * 无权限页
	 * */
	@RequestMapping(value="/unauthorized",method=RequestMethod.GET)
	public String unauthorized(Model model,HttpServletResponse rsp){
		rsp.setHeader("flag", "unauthorized");
		return UNAUTHORIZED_VIEW;
	}
	
	/**
	 * 短信发送
	 * 
	 * @param phone
	 * @param type
	 * @return ResponseResult
	 * */
	@ESChain(ref="validEliteInviteCodeChain")
	@MemberHandleLog(description="发送短信",type=LogsEnum.sendSms)
	@ResponseBody
	@RequestMapping(value="/sms/send",method=RequestMethod.POST)
	public ResponseResult<String> sendSms(String phone,String type,String currentType,String inviteCode,
			String name,String idCard,String bankCard,HttpServletRequest request){
		String content=null;
		//注册时验证是否已经注册
		if(SmsRecord.Sms_Type.register.name().equals(type)){
			if (this.memberPassportService.findMemberAccountIsExists(phone))
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"该帐号已存在");
		}
		//找回密码时，验证手机号是否存在
		if(SmsRecord.Sms_Type.forget.name().equals(type)){
			boolean flag=this.memberPassportService.findMemberAccountIsExists(phone);
			if(!flag){
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"该帐号不存在");
			}
		}
		//验证银行卡时需要验证卡号和身份证匹配
		if(SmsRecord.Sms_Type.valid_bank.name().equals(type)){
			boolean flag=BankCardCache.valid(getMemberId());
			if(!flag){
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"银行卡信息验证过于频繁，请明天再试");
			}
			if (!this.platformAuthBankService.findValidateBankCard(name, idCard, bankCard, phone))
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"银行卡信息不匹配");
		}
		if(SmsRecord.Sms_Type.register.name().equals(type)
				||SmsRecord.Sms_Type.valid_bank.name().equals(type)
				||SmsRecord.Sms_Type.forget.name().equals(type)){
			content=CommonUtils.randomString(6);
		}
		SmsRecord sms=new SmsRecord();
		sms.setPhones(phone);
		sms.setContent(content);
		sms.setSmsType(type);
		SmsRecord obj=this.smsRecordService.createSmsRecord(sms);
		String result=GlobalDefinedConstant.SUCCESS;
		if(obj==null)
			result=GlobalDefinedConstant.FAILURE;
		logger.info("发送短信到手机号："+phone+",验证码："+content);
		return new ResponseResult<>(result,null);
	}
	
	/**
	 * 验证身份证号码和姓名是否匹配
	 * 
	 * @param name
	 * @param idCard
	 * @return ResponseResult
	 * */
	@ESChain(ref="validIdCard")
	@SSOToken
	@MemberHandleLog(description="验证身份证姓名",type=LogsEnum.validCard)
	@ResponseBody
	@RequestMapping(value="/valid/idcard",method=RequestMethod.POST)
	public ResponseResult<String> validateIdCard(String name,String idCard){
		if(StringUtils.isEmpty(name)||StringUtils.isEmpty(idCard)
				||idCard.length()<15||idCard.length()>18){
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"参数不能为空");
		}
		getMemberId();
		boolean flag=pingPlusPlusComponent.validateIdCard(name, idCard);
		if(!flag){
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"身份证号码和姓名不匹配");
		}
		return new ResponseResult<>();
	}
	
	/**
	 * 验证银行卡是否和身份证号码和姓名是否匹配
	 * 
	 * @param name
	 * @param idCard
	 * @param bankCode
	 * @param phone
	 * @return ResponseResult
	 * */
	@ESChain(ref="validBankCode")
	@SSOToken
	@MemberHandleLog(description="验证银行卡",type=LogsEnum.validBank)
	@ResponseBody
	@RequestMapping(value="/valid/bank",method=RequestMethod.POST)
	public ResponseResult<String> validateBankCode(String name,String idCard,String bankCard,String phone){
		if(StringUtils.isEmpty(name)||StringUtils.isEmpty(idCard)||StringUtils.isEmpty(bankCard)
				||idCard.length()<15||idCard.length()>18){
			return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"参数不能为空");
		}
		getMemberId();
		//先从本地数据库验证是否已经验证
		boolean authFlag=platformAuthBankService.findPlatformAuthBank(name, idCard, bankCard, phone);
		if(!authFlag){
			boolean flag=pingPlusPlusComponent.validateBankCard(name, idCard, bankCard, phone);
			if(!flag){
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"非本人银行卡");
			}
			platformAuthBankService.createPlatformAuthBank(new PlatformAuthBank(name, idCard, bankCard, phone));
		}
		return new ResponseResult<>();
	}
	
	/**
	 * 验证短信验证码
	 * 
	 * @param phone
	 * @param type
	 * @param code
	 * @return boolean
	 * */
	@ResponseBody
	@RequestMapping(value="/sms/verify",method=RequestMethod.POST)
	public ResponseResult<String> verifySms(String phone,String type,String code){
		boolean flag=this.smsRecordService.findSmsRecordIsExists(phone, type, code, null);
		return !flag?new ResponseResult<>():new ResponseResult<>(GlobalDefinedConstant.FAILURE,"验证码错误");
	}
	
	/**
	 * 查询数据字典的内容
	 * 
	 * @param dictType
	 * @param parentId
	 * */
	@ResponseBody
	@RequestMapping(value="/dict/list",method=RequestMethod.POST)
	public List<Dict> findDictList(String dictType,Long parentId){
		return this.dictService.findDictsByDictType(dictType,parentId);
	}
	
	/**
	 * 查询会员的角色
	 * 
	 * @param parentId
	 * */
	@RequestMapping(value="/dict/jobrole",method=RequestMethod.POST)
	public String findJobRoleMap(Long parentId,Model model){
		Map<Dict, List<Dict>> map=new LinkedHashMap<>();
		List<Dict> pList=this.dictService.findDictsByDictType(Dict_Type.job_role.name(),parentId);
		for(Dict d:pList){
			List<Dict> cList=this.dictService.findDictsByDictType(Dict_Type.job_role.name(),d.getId());
			map.put(d, cList);
		}
		model.addAttribute("map", map);
		return "/member/elite/jobrole_frag";
	}
	
	/**
	 * 生成二维码
	 * 
	 * @param url
	 * @param size
	 * */
	@RequestMapping(value="/produce/qr",method=RequestMethod.GET)
	public void produceQRCodeImg(String url,Integer size,HttpServletResponse rsp){
		if(StringUtils.isEmpty(url)){
			return;
		}
		size=size==null?150:size;
		QRCodeBuilder.getQRCode(url, size, rsp);
	}
	
	@ResponseBody
	@RequestMapping(value="/clipboard",method=RequestMethod.POST)
	public void clipboard(String str){
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
  		//设置字符串
  		//构建String数据类型
  		StringSelection selection = new StringSelection(str);
  		//添加文本到系统剪切板
  		clipboard.setContents(selection, null);
	}
}

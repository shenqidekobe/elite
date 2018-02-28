package com.ledao.elite.core.service.sys.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ledao.elite.core.domain.sys.SmsRecord;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.config.TemplateConfig;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.plugin.sms.SmsService;
import com.ledao.elite.core.repository.sys.SmsRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SmsRecordService;
import com.ledao.elite.core.utils.WebUtils;

@Service
public class SmsRecordServiceImpl extends BaseSerivceImpl implements SmsRecordService {

	@Resource
	private SmsRepository smsRepository;
	@Resource(name="vipSmsServiceImpl")
	private SmsService smsService;
	@Resource(name="templateConfig")
	private TemplateConfig templateConfig;
	
	public static boolean IS_SEND_SMS_TO_PHONE=false;//是否发送短信到手机上

	@Override
	public SmsRecord createSmsRecord(SmsRecord obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getPhones(),obj.getContent());
		String result=GlobalDefinedConstant.SUCCESS;
		try {
			Map<String, Object> paramMap=new HashMap<String, Object>();
			//组织短信的模版内容
			String content=obj.getContent();
			if(SmsRecord.Sms_Type.register.name().equals(obj.getSmsType())){
				content=String.format(templateConfig.getRegisterSmsTemplate(), content);
			}else if(SmsRecord.Sms_Type.forget.name().equals(obj.getSmsType())){
				//content=String.format(templateConfig.getForgetPassSmsTemplate(), content);
			}
			paramMap.put("phone", obj.getPhones());
			paramMap.put("content", content);
			paramMap.put("needstatus", true);//状态报告
			if(IS_SEND_SMS_TO_PHONE){
				result=this.smsService.send(paramMap);
			}
		} catch (Exception e) {
			throw new EliteServiceException("短信发送失败:"+e.getMessage());
		}
		if(GlobalDefinedConstant.SUCCESS.equals(result)){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			String ip=WebUtils.getClientIp(request);
			obj.setStatus(SmsRecord.Sms_Status.success.name());
			obj.setSendTime(new Date());
			obj.setIp(ip);
			this.smsRepository.save(obj);
			return obj;
		}
		return null;
	}

	@Override
	public SmsRecord updateSmsRecordStatus(Long id, String status, boolean isUse) throws EliteServiceException {
		this.verifyParams(id,status);
		SmsRecord sms=this.smsRepository.find(id);
		if(sms==null)
			throw new EliteServiceException("更新失败,未找到相应短信记录",ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		sms.setStatus(status);
		sms.setUse(isUse);
		this.smsRepository.save(sms);
		return sms;
	}

	@Override
	public SmsRecord findSmsRecordByTypeAndPhone(String phone, String type) throws EliteServiceException {
		this.verifyParams(phone,type);
		return this.smsRepository.querySmsRecordByTypeAndPhone(phone, type);
	}

	@Override
	public boolean findSmsRecordIsExists(String phone, String type, String code,Integer expireTime) throws EliteServiceException {
		SmsRecord sms=this.findSmsRecordByTypeAndPhone(phone, type);
		expireTime=expireTime!=null?expireTime:GlobalDefinedConstant.System_Constant.SMS_EXPIRE_TIME;
		if(sms==null||sms.isUse())
			return true;
		Long time=sms.getSendTime().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -expireTime);//短信过期时间设置
		Long ct = calendar.getTime().getTime();
		if (ct <= time &&sms.getContent().equals(code)) {
			sms.setUse(true);
			this.smsRepository.save(sms);
			return false;
		}
		return true;
	}


}

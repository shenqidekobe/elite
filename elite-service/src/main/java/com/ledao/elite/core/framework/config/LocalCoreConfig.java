package com.ledao.elite.core.framework.config;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.constant.AttachmentsConstant;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.service.sys.impl.SmsRecordServiceImpl;
import com.ledao.elite.core.utils.CommonUtils;

/**
 * 加载本地资源配置文件
 * 
 * @author kobe.liu
 */
@Component("localCoreConfig")
@Configuration
@PropertySource(value = { "classpath:/profile/annotationRes.properties","classpath:/profile/alipay_settlement.properties" })
public class LocalCoreConfig {

	@Resource
	Environment env;

	/**
	 * 项目意向金的金额
	 */
	public BigDecimal getProjectIntentionAmount() {
		return env.getProperty("project_intention_amount", BigDecimal.class);
	}

	/**
	 * 域名地址
	 */
	public String getDomainServer() {
		return env.getProperty("domain.server");
	}

	/**
	 * 文件上传地址
	 */
	public List<String> getUploadServer() {
		String uploadServer = env.getProperty("upload.server");
		List<String> list = CommonUtils.separatorStrToList(uploadServer,
				GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		AttachmentsConstant.ATTA_SERVER_PATH = list.get(0);
		return list;
	}

	/**
	 * 项目的背景色集合
	 */
	public List<String> getRandomColor() {
		String randomColor = env.getProperty("project_random_color");
		List<String> list = CommonUtils.separatorStrToList(randomColor,
				GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		return list;
	}
	

	/**
	 * 短信发送开关
	 */
	public boolean getSmsSendSwitch() {
		boolean onoff = env.getProperty("sms_send_switch", Boolean.class);
		SmsRecordServiceImpl.IS_SEND_SMS_TO_PHONE = onoff;
		return onoff;
	}
	
	//项目质保期时间
	public Integer getProjectQualityTime(){
		return env.getProperty("project_quality_time",Integer.class);
	}

	// 质保金比例
	public BigDecimal getQualityAmountPercent() {
		return env.getProperty("quality.amount_percent", BigDecimal.class);
	}

	// 通知（小红点）文档地址
	public String getNoticeFilePath() {
		return env.getProperty("notices.filePath");
	}

	// 通知（小红点）文档名字
	public String getNoticeFileName() {
		return env.getProperty("notices.fileName");
	}

	/**
	 * 任务背景图
	 */
	public List<String> getTaskRandomImg() {
		String randomImg = env.getProperty("task_random_img");
		List<String> list = CommonUtils.separatorStrToList(randomImg,
				GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		return list;
	}

	
	
	
	
	
	// alipay
	public String getAliPayUrl() {
		return env.getProperty("alipay.alipayUrl");
	}

	public String getPrivateKey() {
		return env.getProperty("alipay.privatekey");
	}

	public String getAppId() {
		return env.getProperty("alipay.appId");
	}

	public String getAliPayPublicKey() {
		return env.getProperty("alipay.alipaypublickey");
	}

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，
	public String getPartner() {
		return env.getProperty("alipay.partner");
	}

	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public String getSellerId() {
		return env.getProperty("alipay.seller_id");
	}

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public String getNotifyUrl() {
		return env.getProperty("alipay.notify_url");
	}

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问// 服务器异步通知页面路径
	// 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public String getReturnUrl() {
		return env.getProperty("alipay.return_url");
	}

	// 日志路径
	public String getLogPath() {
		return env.getProperty("alipay.log_path");
	}

	// 字符编码格式 目前支持 gbk 或 utf-8
	public String getInputCharset() {
		return env.getProperty("alipay.input_charset");
	}

	// 防钓鱼时间戳 若要使用请调用类文件submit中的query_timestamp函数
	public String getAnTiPhishingKey() {
		return env.getProperty("alipay.anti_phishing_key");
	}

	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public String getExterInvokeIp() {
		return env.getProperty("alipay.exter_invoke_ip");
	}

	public String getSmsAccount() {
		return env.getProperty("sms.account");
	}

	public String getSmsPasswrod() {
		return env.getProperty("sms.password");
	}

	public String getSmsUrl() {
		return env.getProperty("sms.url");
	}
}

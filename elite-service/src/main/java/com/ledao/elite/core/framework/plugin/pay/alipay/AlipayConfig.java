package com.ledao.elite.core.framework.plugin.pay.alipay;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.config.LocalCoreConfig;

import lombok.extern.slf4j.Slf4j;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 */
@Slf4j
@Component
public class AlipayConfig  implements InitializingBean{
	
	@Resource
	private LocalCoreConfig localCoreConfig;
	
	public static  final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
    
	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "/usr/local";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";

	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
	
	public void init(){
		log.info("****************************支付宝接口参数初始化****************************");
		partner=this.localCoreConfig.getPartner();
		seller_id=this.localCoreConfig.getSellerId();
		private_key=this.localCoreConfig.getPrivateKey();
		alipay_public_key=this.localCoreConfig.getAliPayPublicKey();
		notify_url=this.localCoreConfig.getNotifyUrl();
		return_url=this.localCoreConfig.getReturnUrl();
		log_path=this.localCoreConfig.getLogPath();
		input_charset=this.localCoreConfig.getInputCharset();
		anti_phishing_key=this.localCoreConfig.getAnTiPhishingKey();
		exter_invoke_ip=this.localCoreConfig.getExterInvokeIp();
		log.info("****************************支付宝接口参数初始化完成****************************");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
		
}


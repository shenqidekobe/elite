package com.ledao.elite.rest.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.plugin.pay.alipay.util.AlipayNotify;
import com.ledao.elite.core.service.platform.PlatformInOrderService;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.response.AlipayPropertyResponse;

/**
 * 支付宝通知控制中心
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller
@RequestMapping("alipay")
public class AlipayNotifyController extends BaseController{
	
	
	@Resource
	private PlatformInOrderService platformInOrderService;
	@Resource
	private LocalCoreConfig localCoreConfig;
	
	/**
	 * 接收支付宝异步通知
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/config",method=RequestMethod.POST)
	public ModelAndView alipayProperty(){
		ResponseResultData<AlipayPropertyResponse> rsp=new ResponseResultData<>(); 
		AlipayPropertyResponse alipay = new AlipayPropertyResponse();
		alipay.setAppId(localCoreConfig.getAppId());
		alipay.setPrivatekey(localCoreConfig.getPrivateKey());
		alipay.setPartner(localCoreConfig.getPartner());
		alipay.setSellerId(localCoreConfig.getSellerId());
		alipay.setAlipayUrl(localCoreConfig.getAliPayUrl());
		alipay.setNotifyUrl(localCoreConfig.getNotifyUrl());
		return rsp.responseResult(alipay);
	}
	
	
	/**
	 * 接收支付宝异步通知
	 * */
	@ResponseBody
	@RequestMapping(value="/asyn/notify",method=RequestMethod.POST)
	public String alipayAsynNotify(HttpServletRequest request)throws Exception{
		Map<String,String> params=CommonUtils.requestParameterToMap(request);
		logger.info("alipay notify params:"+params.toString());
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)
	    String orderNo = request.getParameter("out_trade_no");//获取订单号 
	    String totalFee = request.getParameter("total_fee"); //获取总金额 
	    String tradeNo = request.getParameter("trade_no");//支付宝交易号 
	    String buyerEmail = request.getParameter("buyer_email");//买家支付宝账号 
	    String tradeStatus = request.getParameter("trade_status");//交易状态 
	    String gmtPayment=request.getParameter("gmt_payment");//交易付款事件
	    //计算得出通知验证结果  
	    boolean verify_result = AlipayNotify.verify(params);  
	    if(verify_result){
	        boolean isSuccess=(tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS"))?true:false;
			Date payTime=DateTimeUtils.defaultFormat.parse(gmtPayment);
	        this.platformInOrderService.updatePlatformInOrderAsCallback(orderNo,totalFee,tradeNo,buyerEmail,payTime,isSuccess);
	    }else{
	    	logger.error("解析支付宝异步通知返回结果出现错误,签名验证失败");
	    	return "fail";
	    }  
		return "success";
	}
}

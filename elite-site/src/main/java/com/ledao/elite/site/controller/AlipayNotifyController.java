package com.ledao.elite.site.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.framework.plugin.pay.alipay.util.AlipayNotify;
import com.ledao.elite.core.service.platform.PlatformInOrderService;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.site.config.AppConfig;

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
	private AppConfig appConfig;
	

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
	
	/**
	 * 接收支付宝返回通知
	 *  String subject = CommonUtils.strEnCoding(request.getParameter("subject"));
	    String body = "";  
	    if(request.getParameter("body") != null){  
	        body = CommonUtils.strEnCoding(request.getParameter("body"));//商品描述、订单备注、描述 
	    } 
	 * */
	@RequestMapping(value="/return/notify",method=RequestMethod.GET)
	public String alipayReturnNotify(HttpServletRequest request,HttpServletResponse rsp){
		String resultView="";//根据订单号查询返回的视图
		Map<String,String> params=CommonUtils.requestParameterToMap(request);
	    String orderId = request.getParameter("out_trade_no");//获取订单号 
	    String tradeStatus = request.getParameter("trade_status");//交易状态 
	    boolean verify_result = AlipayNotify.verify(params);
	    boolean isSuccess=(tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS"))?true:false;
	    String server=localCoreConfig.getDomainServer();
	    if(verify_result&&isSuccess){
	    	PlatformInOrder order=this.platformInOrderService.findPlatformInOrderByOrderId(orderId);
	    	if(order==null){
	    		resultView=UNAUTHORIZED_VIEW;
	    	}else{
	    		switch (order.getType()) {
				case intention:
					resultView="/project/"+order.getProjectId()+"/intention";
					//resultView="/member/index";//支付成功跳回首页
					break;
				case prostage:
					resultView="/project/"+order.getProjectId()+"/trustcost";
					//resultView="/member/index";
					break;
		    	}
	    	}
	    	return REDIRECT_COMMAND+server+resultView; 
	    } 
	    return REDIRECT_COMMAND+server+PAY_FAIL_VIEW;
	}
	
}

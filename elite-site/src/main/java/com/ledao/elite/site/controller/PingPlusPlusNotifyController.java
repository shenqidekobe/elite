package com.ledao.elite.site.controller;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.domain.sys.CommonCode.COMMON_PREVAL;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Way;
import com.ledao.elite.core.framework.plugin.pay.pingpp.WebhooksVerify;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.ledao.elite.core.service.platform.PlatformInOrderService;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Refund;
import com.pingplusplus.model.Transfer;
import com.pingplusplus.model.Webhooks;

/**
 * Ping++的webhooks事件处理
 * 
 * @author kobe.liu
 * */
@Controller
@RequestMapping("/pingpp")
public class PingPlusPlusNotifyController extends BaseController{
	
	@Resource
	private PlatformInOrderService platformInOrderService;
	
	@Resource
	private MemberWithdrawService memberWithdrawService;

	@Resource
	private WebhooksVerify webhooksVerify;
	
	/**
	 * 处理回调通知
	 * */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/webhooks",method=RequestMethod.POST)
	public String execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF8");
        //获取头部所有信息
        Enumeration headerNames = request.getHeaderNames();
        String pingSign="";
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            if("x-pingplusplus-signature".equals(key)){
            	pingSign= request.getHeader(key);
            	break;
            }
        }
        if(pingSign==""){
        	logger.info("Ping++的签名信息串为空，不能验签***********");
        	response.setStatus(500);
        	return "signIsNull";
        }
        BufferedReader reader = request.getReader();
        StringBuffer buffer = new StringBuffer();
        String string;
        while ((string = reader.readLine()) != null) {
            buffer.append(string);
        }
        reader.close();
        // 解析异步通知数据
        String eventStr=buffer.toString();
        Event event = Webhooks.eventParse(eventStr);
        logger.info("验签参数字符串："+eventStr);
        boolean verifyFlag=webhooksVerify.verifyData(new String(eventStr.getBytes(),"UTF-8"), pingSign);
        if(!verifyFlag){
        	logger.info("Ping++的签名信息串："+pingSign+",服务器验签失败***********");
        	response.setStatus(500);
        	return "signFail";
        }
        logger.info("Ping++的Webhooks回调参数体："+eventStr);
        if ("charge.succeeded".equals(event.getType())) {
        	Charge charge=(Charge) event.getData().getObject();
        	String orderNo=charge.getOrderNo();
        	logger.info("支付接口回调成功，订单号："+orderNo);
        	BigDecimal totalFee=new BigDecimal(charge.getAmount());
        	totalFee=totalFee.divide(new BigDecimal(100));//交易的费用
        	Date payTime=new Date(charge.getTimePaid()*1000);
        	this.platformInOrderService.updatePlatformInOrderAsCallback(orderNo,totalFee.toString(),charge.getId(),null,payTime,true);
            response.setStatus(200);
        } else if ("refund.succeeded".equals(event.getType())) {
        	Refund refund=(Refund) event.getData().getObject();
        	String orderNo=refund.getOrderNo();
        	logger.info("退款接口回调成功，订单号："+orderNo);
            response.setStatus(200);
        } else if ("transfer.succeeded".equals(event.getType())) {
        	Transfer transfer=(Transfer) event.getData().getObject();
        	String orderNo=transfer.getOrderNo();
        	//银联订单号需要加上前缀
        	orderNo=COMMON_PREVAL.W.name()+orderNo;
        	logger.info("企业付款接口回调成功，订单号："+orderNo);
        	BigDecimal receiptAmount=new BigDecimal(transfer.getAmount());
        	receiptAmount=receiptAmount.divide(new BigDecimal(100));//实际提现金额
        	Date processTime=new Date(transfer.getTimeTransferred()*1000);
        	this.memberWithdrawService.updateMemberWithdrawAsCallback(orderNo, receiptAmount, Pay_Way.online, processTime, true);
            response.setStatus(200);
        } else {
            response.setStatus(500);
            return "error";
        }
        return "success";
	}
	
	/**
	 * 接收Ping++银联的返回通知
	 * 
	 * */
	@RequestMapping(value="/chinapay/notify",method=RequestMethod.POST)
	public String alipayReturnNotify(HttpServletRequest request,HttpServletResponse rsp)throws Exception{
		String resultView="";//根据订单号查询返回的视图
		//Map<String,String> params=CommonUtils.requestParameterToMap(request);
	    String orderId = request.getParameter("orderId");//获取订单号 
	    String respMsg = request.getParameter("respMsg");//交易状态 
	    logger.info("订单号："+orderId+"的支付结果："+respMsg);
	    //boolean verify_result=webhooksVerify.verifyData(new String(params.toString().getBytes(),"UTF-8"), pingSign);
	    boolean isSuccess=respMsg.equals("success")?true:false;
	    String server=localCoreConfig.getDomainServer();
	    if(isSuccess){
	    	PlatformInOrder order=this.platformInOrderService.findPlatformInOrderByOrderId(orderId);
	    	if(order==null){
	    		resultView=UNAUTHORIZED_VIEW;
	    	}else{
	    		switch (order.getType()) {
				case intention:
					resultView="/project/"+order.getProjectId()+"/intention";
					break;
				case prostage:
					resultView="/project/"+order.getProjectId()+"/trustcost";
					break;
		    	}
	    	}
	    	return REDIRECT_COMMAND+server+resultView; 
	    } 
	    return REDIRECT_COMMAND+server+PAY_FAIL_VIEW;
	}
	
}

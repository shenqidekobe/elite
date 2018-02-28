package com.ledao.elite.core.framework.plugin.pay.pingpp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.config.PingPlusPlusConfig;
import com.ledao.elite.core.framework.plugin.pay.PaymentService;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Refund;
import com.pingplusplus.model.Transfer;

/**
 * Ping++聚合支付
 * 
 * @author kobe.liu
 * */
@Service("pingPlusPlusService")
public class PingPlusPlusServiceImpl implements PaymentService{
	
	public static final String PING_RESULT_KEY="pingpp";
	@Resource
	private PingPlusPlusConfig pingPlusPlusConfig;

	/**
	 * orderNum
	 * amount
	 * channel
	 * ip
	 * subject
	 * body
	 * */
	@Override
	public Map<String, Object> pay(Map<String, Object> payParams) throws EliteBusinessException {
		Map<String, Object> result=new HashMap<>();
		Charge charge=null;
		Pingpp.apiKey = pingPlusPlusConfig.getLiveSecretKey();
		String appId=pingPlusPlusConfig.getAppId();
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);
		chargeParams.put("app", app);
		chargeParams.put("order_no", payParams.get("orderNum"));
		chargeParams.put("amount", payParams.get("amount"));// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
		chargeParams.put("channel", payParams.get("channel"));//upacp_pc:银联PC网页支付
		chargeParams.put("currency", "cny");//币种：默认人名币
		chargeParams.put("client_ip",payParams.get("ip"));
		chargeParams.put("subject", payParams.get("subject"));
		chargeParams.put("body",payParams.get("body"));
		chargeParams.put("extra",payParams.get("extra"));
		try {
			charge = Charge.create(chargeParams);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException e) {
			e.printStackTrace();
		}
		result.put(PING_RESULT_KEY, charge);
		return result;
	}

	@Override
	public Map<String, Object> query(Map<String, Object> queryParams) throws EliteBusinessException {
		Map<String, Object> result=new HashMap<>();
		String queryType=queryParams.get("queryType").toString();
		String id=queryParams.get("id").toString();
		if("charge".equals(queryType)){
			try {
				Charge charge=Charge.retrieve(id);
				result.put(PING_RESULT_KEY, charge);
			} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
					| ChannelException e) {
				e.printStackTrace();
			}
		}else if("transfer".equals(queryType)){
			try {
				Transfer transfer=Transfer.retrieve(id);
				result.put(PING_RESULT_KEY, transfer);
			} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
					| ChannelException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public Map<String,Object> refund(Map<String,Object> refundParams)throws EliteBusinessException{
		Map<String, Object> result=new HashMap<>();
		Map<String, Object> refundMap = new HashMap<String, Object>();
		Pingpp.apiKey = pingPlusPlusConfig.getLiveSecretKey();
		Pingpp.privateKey=pingPlusPlusConfig.getPingPrivateKey();
		refundMap.put("id", refundParams.get("id"));
		refundMap.put("extra", refundParams.get("extra"));
		Refund refund=null;
		try {
			Charge charge= Charge.retrieve(refundParams.get("id").toString());
			refund=charge.getRefunds().create(refundMap);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException e) {
			e.printStackTrace();
		}
		result.put(PING_RESULT_KEY, refund);
		return result;
	}

	@Override
	public Map<String, Object> transfer(Map<String, Object> transferParams) throws EliteBusinessException {
		Map<String, Object> result=new HashMap<>();
		Map<String, Object> transferMap = new HashMap<String, Object>();
		String appId=pingPlusPlusConfig.getAppId();
		Pingpp.apiKey = pingPlusPlusConfig.getLiveSecretKey();
		Pingpp.privateKey=pingPlusPlusConfig.getPingPrivateKey();
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);
		transferMap.put("app", app);
		transferMap.put("channel", "unionpay");//目前支持  wx (新渠道)、  wx_pub 和  unionpay 企业付款（银行卡)，我们默认开通的是unionpay
		transferMap.put("order_no", transferParams.get("orderNum"));
		transferMap.put("amount", new BigDecimal(transferParams.get("amount").toString()).intValue());//订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
		transferMap.put("type", "b2c");//目前只支持B2C付款
		transferMap.put("currency", "cny");
		transferMap.put("recipient", transferParams.get("recipient"));//企业付款给指定用户的 open_id
		transferMap.put("description", transferParams.get("desc"));
		transferMap.put("extra", transferParams.get("extra"));
		Transfer transfer=null;
		try {
			transfer = Transfer.create(transferMap);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException e) {
			e.printStackTrace();
		}
		result.put(PING_RESULT_KEY, transfer);
		return result;
	}
	
	@Override
	public Map<String,Object> queryTransfer(String id)throws EliteBusinessException{
		Map<String, Object> result=new HashMap<>();
		Pingpp.apiKey = pingPlusPlusConfig.getLiveSecretKey();
		Pingpp.privateKey=pingPlusPlusConfig.getPingPrivateKey();
		Transfer transfer=null;
		try {
			transfer = Transfer.retrieve(id);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException e) {
			e.printStackTrace();
		}
		result.put(PING_RESULT_KEY, transfer);
		return result;
	}

}

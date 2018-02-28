package com.ledao.elite.core.framework.plugin.pay.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.plugin.pay.PaymentService;
import com.ledao.elite.core.framework.plugin.pay.alipay.builder.AlipayTradePayContentBuilder;
import com.ledao.elite.core.framework.plugin.pay.alipay.builder.AlipayTradeQueryCententBuilder;
import com.ledao.elite.core.framework.plugin.pay.alipay.util.AlipaySubmit;

import lombok.extern.slf4j.Slf4j;

/**
 * 支付宝支付接口实现
 * 
 * @author kobe.liu
 * */
@Slf4j
@Service("aliPaymentService")
public class AliPaymentServiceImpl implements PaymentService {

	@Resource
	private LocalCoreConfig localCoreConfig;
	
	public static final String PAY_RETURN_KEY="alipayForm";

	/**
	 * 支付接口
	 * 
	 * @param payParams
	 * 
	 * @return Map
	 */
	public Map<String, Object> pay(Map<String, Object> payParams) throws EliteBusinessException {
		Map<String, Object> MapReturn = new HashMap<String, Object>();
		Iterator<Entry<String, Object>> it = payParams.entrySet().iterator();

		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service",AlipayConfig.service);
		sParaTemp.put("partner",AlipayConfig.partner);
		sParaTemp.put("seller_id",AlipayConfig.seller_id);
		sParaTemp.put("_input_charset",AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url",AlipayConfig.notify_url);
		sParaTemp.put("return_url",AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key",AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip",AlipayConfig.exter_invoke_ip);

		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			AlipayTradePayContentBuilder payBuilder = (AlipayTradePayContentBuilder) entry.getValue();
			sParaTemp.put("out_trade_no", payBuilder.getOutTradeNo());
			sParaTemp.put("subject", payBuilder.getSubject());
			sParaTemp.put("total_fee", payBuilder.getTotalAmount());
			sParaTemp.put("body", payBuilder.getBody());
		}
		log.info("支付宝服务支付接口收到支付请求，请求参数："+sParaTemp.toString());
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
		MapReturn.put(PAY_RETURN_KEY, sHtmlText);
		return MapReturn;

	}

	/**
	 * 支付接口
	 * 
	 * @param payParams
	 *            通过商户订单号，或者支付宝交易号
	 * @return Map
	 */
	public Map<String, Object> query(Map<String, Object> queryParams) throws EliteBusinessException {

		Map<String, Object> MapReturn = new HashMap<String, Object>();
		AlipayClient alipayClient = new DefaultAlipayClient(localCoreConfig.getAliPayUrl(), localCoreConfig.getAppId(),
				localCoreConfig.getPrivateKey(), "json", "GBK", localCoreConfig.getAliPayPublicKey());

		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

		Iterator<Entry<String, Object>> it = queryParams.entrySet().iterator();
		while (it.hasNext()) {

			Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			AlipayTradeQueryCententBuilder querBuilder = (AlipayTradeQueryCententBuilder) entry.getValue();
			request.setBizContent(querBuilder.toString());
			try {
				AlipayTradeQueryResponse response = alipayClient.execute(request);
				MapReturn.put(key, response);
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
		}
		return MapReturn;
	}
	
	@Override
	public Map<String,Object> refund(Map<String,Object> refundParams)throws EliteBusinessException{
		return null;
	}

	@Override
	public Map<String, Object> transfer(Map<String, Object> transferParams) throws EliteBusinessException {
		return null;
	}

	@Override
	public Map<String,Object> queryTransfer(String id)throws EliteBusinessException{return null;}
}

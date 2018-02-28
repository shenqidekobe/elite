package com.ledao.elite.core.framework.plugin.pay.pingpp;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.config.PingPlusPlusConfig;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Identification;

import lombok.extern.slf4j.Slf4j;

/**
 * ping++ 服务组件
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Slf4j
@Component
public class PingPlusPlusComponent {

	@Resource
	private PingPlusPlusConfig pingPlusPlusConfig;

	/**
	 * 验证身份证信息
	 * 
	 * @param name
	 * @param idCard
	 */
	public boolean validateIdCard(String name, String idCard) {
		if(!pingPlusPlusConfig.getValidCard())return true;
		try {
			Pingpp.apiKey = pingPlusPlusConfig.getLiveSecretKey();
			Pingpp.privateKey=pingPlusPlusConfig.getPingPrivateKey();
			String appId = pingPlusPlusConfig.getAppId();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("app", appId);
			params.put("type", "id_card"); // 身份证信息或//
											// "id_card"（身份证信息串）；"bank_card"（银行卡信息串）。
			Map<String, String> data = new HashMap<String, String>();
			data.put("id_name", name);
			data.put("id_number", idCard);
			params.put("data", data);
			Identification result = Identification.identify(params);
			if (result.getResultCode() == 0) {
				return true;
			} else {
				log.info("Ping++身份证【" + name + ":" + idCard + "】的验证失败：【" + result.getResultCode() + ":"
						+ result.getMessage() + "】");
				return false;
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		} catch (ChannelException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 验证银行卡信息
	 * 
	 * @param name
	 * @param idCard
	 * @param bankCode
	 * @param bankPhone
	 */
	public boolean validateBankCard(String name, String idCard, String bankCard, String bankPhone) {
		if(!pingPlusPlusConfig.getValidCard())return true;
		try {
			Pingpp.apiKey = pingPlusPlusConfig.getLiveSecretKey();
			Pingpp.privateKey=pingPlusPlusConfig.getPingPrivateKey();
			String appId = pingPlusPlusConfig.getAppId();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("app", appId);
			params.put("type", "bank_card");
			Map<String, String> data = new HashMap<String, String>();
			data.put("id_name", name);
			data.put("id_number",idCard);
			data.put("card_number",bankCard);
			if(StringUtils.isNotEmpty(bankPhone)){
				data.put("phone_number",bankPhone);
			}
			params.put("data", data);
			Identification result = Identification.identify(params);
			if (result.getResultCode() == 0) {
				return true;
			} else {
				log.info("Ping++银行卡【" + name + ":" + bankCard + "】的验证失败：【" + result.getResultCode() + ":"
						+ result.getMessage() + "】");
				return false;
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		} catch (ChannelException e) {
			e.printStackTrace();
		}
		return false;
	}
}

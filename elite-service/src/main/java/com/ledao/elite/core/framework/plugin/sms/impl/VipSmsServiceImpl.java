package com.ledao.elite.core.framework.plugin.sms.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.plugin.sms.SmsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VipSmsServiceImpl implements SmsService {

	@Resource
	private LocalCoreConfig localCoreConfig;

	@Override
	public String send(Map<String, Object> paramMap) throws EliteBusinessException {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(localCoreConfig.getSmsUrl(), false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.setQueryString(new NameValuePair[] { new NameValuePair("account", localCoreConfig.getSmsAccount()),
					new NameValuePair("pswd", localCoreConfig.getSmsPasswrod()),
					new NameValuePair("mobile", paramMap.get("phone").toString()),
					new NameValuePair("needstatus", String.valueOf(paramMap.get("needstatus"))),
					new NameValuePair("msg", paramMap.get("content").toString()),
					new NameValuePair("product", paramMap.get("product") == null ? null
							: paramMap.get("product").toString()),
					new NameValuePair("extno",
							paramMap.get("extno") == null ? null : paramMap.get("extno").toString()), });
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				String resultStr = URLDecoder.decode(baos.toString(), "UTF-8");
				log.debug("VIP SmsService HttpBatchSendSM result = " + result);
				if (StringUtils.isNotEmpty(resultStr)) {
					String[] codes = resultStr.split(",");
					if (codes != null && codes.length > 1) {
						String codeByMsg = codes[1].trim();
						if (codeByMsg.startsWith("0")) {
							return GlobalDefinedConstant.SUCCESS;
						}
					}
				}
			} else {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		}catch(Exception e){
			
		} finally {
			method.releaseConnection();
		}
		return null;
	}

}

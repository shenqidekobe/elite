package com.ledao.elite.rest.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ledao.elite.core.domain.sys.SysRestLogs;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.service.sys.SysRestLogsService;
import com.ledao.elite.core.utils.LogHelper;
import com.ledao.elite.core.utils.encry.Md5;
import com.ledao.elite.rest.config.GlobalData;
import com.ledao.elite.rest.framework.utils.RestUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * rest接口安全拦截器
 */
@Slf4j
@Component
public class RestSafetyInterceptor extends HandlerInterceptorAdapter {

	public static ThreadLocal<MyRequestWrapper> localRequest = new ThreadLocal<MyRequestWrapper>();

	@Resource
	private LocalCoreConfig localCoreConfig;
	@Resource
	private SysRestLogsService sysRestLogsService;

	private static final String PARAM_SIGN_KEY = "sign";// 签名参数key值
	

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		if(mav!=null&&mav.getModel()!=null){
			Object rspJson=mav.getModel().get(GlobalData.RESULT_RESPONSE_NAME);
			String jsonStr=JSON.toJSONString(rspJson);
			log.info("APP接口返回数据："+jsonStr);
			try {
				MyRequestWrapper request=RestUtils.getRequest();
				SysRestLogs logs = new SysRestLogs();
				String reqIp=com.ledao.elite.core.utils.WebUtils.getClientIp(request);
				Object memberId=RestUtils.getRequestParam("memberId");
				logs.setReqIp(reqIp);
				logs.setClassImpl(request.getRequestURL().toString());
				logs.setContent(request.getHeader(PARAM_SIGN_KEY));
				logs.setMemberId(memberId==null?null:(Long)memberId);
				logs.setReqParam(request.getBody());
				logs.setRsqParam(jsonStr);
				this.sysRestLogsService.createSysRestLogs(logs);
			} catch (Exception e) {}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		MyRequestWrapper req = new MyRequestWrapper(request);
		localRequest.set(req);

		String requestJson = req.getBody();
		String host = request.getRemoteHost();
		String requestURL = request.getRequestURL().toString();
		String method = request.getMethod();

		String requestSign = request.getHeader(PARAM_SIGN_KEY);
		String sourceStr = requestJson + GlobalData.RSA_PUBLIC_KEY;
		String sign = Md5.getMD5(sourceStr);

		List<String> logs = Lists.newArrayList(LogHelper.titleOutput("APPREST LOGGING"),
				String.format("RequestURL: %s", requestURL), String.format("Method: %s", method),
				String.format("SignComparison: %s = %s", requestSign, sign), String.format("Host: %s", host),
				String.format("RequestParameters: %s", requestJson));
		log.info("\n" + StringUtils.join(logs, "\n") + "\n");
		/*if (!sign.equals(requestSign)) {
			ResponseBase rsp = new ResponseBase();
			rsp.setCode(ErrorCodeEnum.AUTHFAILED.code);
			rsp.setMsg("签名失败");
			rsp.setResult(GlobalDefinedConstant.FAILURE);
			response.setCharacterEncoding(GlobalDefinedConstant.Encoding_Type.utf8.val);
			response.getWriter().write(JSON.toJSONString(rsp));
			return false;
		}*/
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public static void main(String[] args) {
		String sign = Md5.getMD5("zijirenduizhangbiekaiqiang12306");
		System.out.println(sign);
	}

}

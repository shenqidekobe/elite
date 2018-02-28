package com.ledao.elite.site.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;

public class ExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object paramObject, Exception ex) {
		ex.printStackTrace();
		boolean isAjax = false;
		if (request.getParameter(GlobalDefinedConstant.JS_DEFINED.FLAG_ASYNC) != null
				&& request.getParameter(GlobalDefinedConstant.JS_DEFINED.FLAG_ASYNC)
						.equals(String.valueOf(GlobalDefinedConstant.TRUE_FALSE_DEFINE.STATE_YES))) {
			isAjax = true;
		}
		if (isAjax) {
			response.setHeader(GlobalDefinedConstant.JS_DEFINED.RESULTID,
					GlobalDefinedConstant.JS_DEFINED.JS_RESULT.ERROR);
			String errMsg = "";
			if (ex instanceof EliteBusinessException) {
				errMsg = ((EliteBusinessException) ex).getMessage();
			} else if (ex instanceof EliteServiceException) {
				errMsg = ((EliteServiceException) ex).getCode();
			} else {
				errMsg = "未知异常";
			}
			request.setAttribute("errMsg", errMsg);
			return getModelAndView("/ajaxError", ex, request);
		} else {
			return super.doResolveException(request, response, paramObject, ex);
		}
	}

}

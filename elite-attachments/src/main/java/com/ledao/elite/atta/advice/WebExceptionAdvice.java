package com.ledao.elite.atta.advice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.utils.WebUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 异常处理
 * @author kobe.liu
 * @date 2016/5/9
 */
@Slf4j
@ControllerAdvice
public class WebExceptionAdvice {

    @SuppressWarnings("rawtypes")
	@ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        if (!(e instanceof EliteBusinessException
        		|| e instanceof EliteServiceException
                || e instanceof ConstraintViolationException
                || e instanceof MaxUploadSizeExceededException)){
            log.warn(e.getMessage());
        }

        String code=GlobalDefinedConstant.EXCEPTION;
        String msg = e.getMessage();
        if (e instanceof EliteServiceException) {
        	EliteServiceException ce = (EliteServiceException) e;
            msg = ce.getMsg();
            code=ce.getCode();
        }else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ce = (ConstraintViolationException) e;
            msg = "";
            for (ConstraintViolation violation : ce.getConstraintViolations()) {
                msg += violation.getMessage() + "<br>";
            }
        } else if (e instanceof MaxUploadSizeExceededException) {
            MaxUploadSizeExceededException ex = (MaxUploadSizeExceededException) e;
            msg = "文件大小超过限制[" + ex.getMaxUploadSize() + "]";
        }

        if (e instanceof DataIntegrityViolationException) {
            msg = "数据已经使用,不能删除";
        }

        if(WebUtils.isAjaxRequest(request)){
            Map<String, String> result = new HashMap<>();
            result.put("result", GlobalDefinedConstant.FAILURE);
            result.put("code", code);
            result.put("msg", msg);
            return JSON.toJSONString(result);
        } else {
            try {
                response.getWriter().write(msg);
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
            return "";
        }
    }

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
